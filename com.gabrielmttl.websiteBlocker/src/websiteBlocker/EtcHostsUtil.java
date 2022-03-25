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
    private final String IP_ADDRESS = "0.0.0.0";

    private static final EtcHostsUtil instance = new EtcHostsUtil();

    private static String OS;
    private String hostFile;


    private EtcHostsUtil() {
        OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            hostFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }
        else if (OS.contains("mac") || OS.contains("nux")) {
            hostFile = "hostsTestFile"; //TODO: change to point to the actual file
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
            - OS X
            - Most Linux Distributions (this may be a source of errors in small or niche distributions that change
                                        the location of system files)
         */
        if (OS != null) {
            return instance;
        }

        return null;
    }

    /**
     *
     * @param url must be prefixed with http or https
     *
     * @return false if file is not prefixed with http or https
     *          false if we cannot write to file
     *          true if we wrote the correct url to file.
     */

    public boolean blockWebsite(String url) {

        if (url.contains("http") || url.contains("https")) {
            url = formatURL(url);
        }
        else {return false;}

        try {
            Files.write(Paths.get(hostFile), ("\n" + IP_ADDRESS + " " + url).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("websiteBlocker.EtcHostsUtil.blockWebsite -- Could not write to host file.");
        }

        return false;
    }

    /**
     *
     * @param url must start with http or https otherwise hell with ensue.
     *            If you get some weird trying to reference null value this is probably the cause.
     *
     *            formatURL returns null if the url passed to it is not formatted properly.
     *
     * @return true if we can go through the file successfully
     *         false if any errors occur when manipulating the file.
     *         false if the url passed to it does not contain http or https.
     */

    public boolean unblockWebsite(String url) {
        File inputFile = new File(hostFile);
        File tempFile = new File(hostFile + ".tmp");

        if (url.contains("http") || url.contains("https")) {
            url = formatURL(url);
        }
        else { return false; }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(hostFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(url) && currentLine.contains(IP_ADDRESS) || currentLine.isEmpty()) continue;
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

    /**
     * This method formats the url for the /etc/hosts file.
     *
     * @param url HTTP:// or HTTPS:// + name.domain.
     *
     * @return www.name.domain
     */
    private String formatURL(String url) {
        url = url.replaceAll("\\s", ""); // remove all possible whitespace

        StringBuilder formattedURL = new StringBuilder("www.");
        if (url.contains("https")) {
            formattedURL.append(url.substring("https://".length()));
        }
        else if (url.contains("http")) {
            formattedURL.append(url.substring("http://".length()));
        }

        return formattedURL.toString();
    }
}
