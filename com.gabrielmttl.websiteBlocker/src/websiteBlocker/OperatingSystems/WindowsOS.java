package websiteBlocker.OperatingSystems;

import websiteBlocker.DNSFlushingBehaviour;

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
        // TODO: implement
        return false;
    }
}
