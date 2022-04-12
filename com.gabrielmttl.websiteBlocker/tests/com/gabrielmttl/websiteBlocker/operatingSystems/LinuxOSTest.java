package com.gabrielmttl.websiteBlocker.operatingSystems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinuxOSTest {

    @Test
    void DNSFlush() {
        assertTrue(LinuxOS.getInstance().DNSFlush(), "Failed to flush Linux's local DNS");
    }
}