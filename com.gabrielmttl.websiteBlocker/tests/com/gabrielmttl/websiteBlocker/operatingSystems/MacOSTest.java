package com.gabrielmttl.websiteBlocker.operatingSystems;

import com.gabrielmttl.websiteBlocker.operatingSystems.MacOS;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MacOSTest {

    @Test
    void DNSFlush() throws IOException {
        assertTrue(MacOS.getInstance().DNSFlush(), "MacOS.flush failed.");
    }
}
