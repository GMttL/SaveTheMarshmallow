package websiteBlocker.OperatingSystems;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WindowsOSTest {

    @Test
    void DNSflush() {
        assertTrue(WindowsOS.getInstance().DNSflush(), "Windows couldn't flush DNS");
    }
}