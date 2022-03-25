package websiteBlocker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This class ...
 *
 * <p>
 *     Handles all things related to the /etc/hosts file.
 * </p>
 *
 * @author Gabriel Mititelu
 * </p>
 */
public final class EtcHostsUtil implements WebsiteBlocker {

    private static final EtcHostsUtil instance = new EtcHostsUtil();

    private static String OS;
    private String hostFile;


    private EtcHostsUtil() {

        OS = System.getProperty("os.name").toLowerCase();
        System.out.println(OS);
        if (OS.contains("win")) {
            hostFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }
        else if (OS.contains("mac") || OS.contains("nux")) {
            hostFile = "/etc/hosts";
        }
        else {
            // unknown OS. We cannot perform action.
            OS = null;
        }

    }

    public static EtcHostsUtil getInstance() {
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
        try {
            Files.write(Paths.get(hostFile), ("\n0.0.0.0 " + url).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("websiteBlocker.EtcHostsUtil.blockWebsite -- Could not write to host file.");
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
            System.err.println("websiteBlocker.EtcHostsUtil.unblockWebsite -> could not find the file to open for reading. " +
                    "hostFile location corrupt or non-existent.");
            return false;
        } catch (IOException e) {
            System.err.println("websiteBlocker.EtcHostsUtil.unblockWebsite -> could not open file for writing.");
            return false;
        }

    }
}
