package websiteBlocker.OperatingSystems;

import websiteBlocker.DNSFlushingBehaviour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    }

    public static MacOS getInstance() {
        if (os == null) {
            os = new MacOS();
        }

        return os;
    }

    /**
     * @method Only works for OS X 10.12 and above.
     *
     * @return true -- command could be executed. Does not guarantee efficacy.
     *         false -- could not execute due to internal reasons.
     */
    @Override
    public boolean DNSflush() {
        // TODO: remove this line and uncomment the original command.
        return execute("sudo rm test.txt"); // testing purposes only. Application will need to be run as superuser. to fully test.
        //return execute("sudo killall -HUP mDNSResponder");
    }

    private boolean execute(String command) {
        String [] args = new String[] {"/bin/bash", "-c", command};
        try {
            Process pb = Runtime.getRuntime().exec(args);
            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

            return true;
        } catch (IOException e) {
            System.err.println("MacOS could not execute bash command.");
        }

        return false;
    }
}
