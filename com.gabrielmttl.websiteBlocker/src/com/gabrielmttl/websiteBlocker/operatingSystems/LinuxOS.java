package com.gabrielmttl.websiteBlocker.operatingSystems;

import com.gabrielmttl.websiteBlocker.DNSFlushingBehaviour;

/**
 * This class...
 * <p>
 *     Helps us communicate with the underlying system (Linux).
 *     Most of Linux distributions at least.
 * </p>
 *
 * @author Gabriel Mititelu
 */
public final class LinuxOS implements DNSFlushingBehaviour {

    private static LinuxOS os = null;

    private LinuxOS() {}

    public static LinuxOS getInstance() {
        if (os == null) {
            os = new LinuxOS();
        }

        return os;
    }

    @Override
    public boolean DNSFlush() {
        // TODO: implement
        return false;
    }
}
