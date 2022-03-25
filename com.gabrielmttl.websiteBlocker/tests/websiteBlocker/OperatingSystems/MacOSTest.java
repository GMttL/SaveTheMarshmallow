package websiteBlocker.OperatingSystems;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MacOSTest {

    @Test
    void DNSflush() throws IOException {
        assertTrue(MacOS.getInstance().DNSflush(), "MacOS.flush failed.");
    }
}