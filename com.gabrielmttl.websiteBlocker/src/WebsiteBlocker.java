import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class WebsiteBlocker {

    private static final WebsiteBlocker instance = new WebsiteBlocker();

    private static String OS;
    private String hostFile;


    private WebsiteBlocker() {

        OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            hostFile = "C:\\Windows\\System32\\drivers\\etc\\hostsTestFile";
        }
        else if (OS.contains("mac") || OS.contains("nux")) {
            hostFile = "/etc/hostsTestFile"; // TODO: change this line to point to /etc/hosts
        }
        else {
            // unknown OS. We cannot perform action.
            OS = null;
        }

    }

    public static WebsiteBlocker getInstance() {
        /*
            Returns null in case of unknown or unsupported Operating Systems.

            Supported Operating Systems:
            - Windows since year 2000+
            - Mac OS
            - Most Linux Distributions (this may be a source of errors in small or niche distributions that change
                                        the location of system files)
         */
        if (OS != null) {
            return instance;
        }

        return null;
    }

    public boolean blockWebsite(String url) {
        // TODO: clear system dns cache as well to successfully block the website. Different responsibility. Need new class.
        try {
            Files.write(Paths.get(hostFile), ("\n0.0.0.0 " + url).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("WebsiteBlocker.blockWebsite -- Could not write to host file.");
        }
        return false;
    }

    public boolean unblockWebsite(String url) {
        File inputFile = new File(hostFile);
        File tempFile = new File(hostFile + ".tmp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(hostFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(url) && currentLine.contains("0.0.0.0") || currentLine.isEmpty()) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            return tempFile.renameTo(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("WebsiteBlocker.unblockWebsite -> could not find the file to open for reading. " +
                    "hostFile location corrupt or non-existent.");
            return false;
        } catch (IOException e) {
            System.err.println("WebsiteBlocker.unblockWebsite -> could not open file for writing.");
            return false;
        }

    }
}
