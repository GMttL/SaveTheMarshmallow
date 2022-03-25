package websiteBlocker.OperatingSystems;

import websiteBlocker.DNSFlushingBehaviour;

import java.io.IOException;

/**
 * This class...
 * <p>
 *     Helps us communicate with the underlying system (Mac OS).
 *     // TODO: decide how many versions of OS X you'll support
 * </p>
 *
 * @author Gabriel Mititelu
 */
public final class MacOS implements DNSFlushingBehaviour {
    private final String version;

    private static MacOS os = null;

    private MacOS() {
        version = System.getProperty("os.version");
        System.out.println(version);
    }

    public static MacOS getInstance() {
        if (os == null) {
            os = new MacOS();
        }

        return os;
    }

    @Override
    public boolean DNSflush() {
        return execute("dscacheutil -flushcache");
    }

    private boolean execute(String command) {
        String [] args = new String[] {"/bin/bash", "-c", command};
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(args);
            return true;
        } catch (IOException e) {
            System.err.println("MacOS could not execute bash command.");
        }

        return false;
    }
}
