package websiteBlocker.OperatingSystems;

import websiteBlocker.DNSFlushingBehaviour;

import java.io.IOException;

/**
 * This class...
 * <p>
 *     Helps us communicate with the underlying system (Windows).
 *     From year 2000 onwards...
 * </p>
 *
 * @author Gabriel Mititelu
 */

public final class WindowsOS implements DNSFlushingBehaviour {

    private static WindowsOS os = null;

    private WindowsOS() {}

    public static WindowsOS getInstance() {
        if (os == null) {
            os = new WindowsOS();
        }

        return os;
    }

    @Override
    public boolean DNSflush() {
        return execute("ipconfig/flushdns");
    }

    private boolean execute(String command) {
        String [] args = new String[] {"cmd.exe", "/c", command};
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(args);
            return true;
        } catch (IOException e) {
            System.err.println("Windows could not execute cmd.exe command.");
        }

        return false;
    }
}
