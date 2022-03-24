package websiteBlocker.OperatingSystems;

import websiteBlocker.DNSFlushingBehaviour;

/**
 * This class...
 * <p>
 *     Helps us communicate with the underlying system (Mac OS).
 *     // TODO: decide how many versions of OS X you'll support
 * </p>
 *
 * @author Gabriel Mititelu
 */
public class MacOS implements DNSFlushingBehaviour {

    @Override
    public boolean flush() {
        // TODO: change command to actually flushing the cache
        String [] args = new String[] {"/bin/bash", "-c", ""}
    }
}
