package com.gabrielmttl.websiteBlocker.operatingSystems;

import com.gabrielmttl.websiteBlocker.operatingSystems.WindowsOS;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WindowsOSTest {

    @Test
    void DNSFlush() {
        assertTrue(WindowsOS.getInstance().DNSFlush(), "Windows couldn't flush DNS");
    }
}