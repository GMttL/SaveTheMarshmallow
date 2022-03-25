package websiteBlocker;

import org.testng.annotations.Test;
import websiteBlocker.EtcHostsUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EtcHostsUtilTests {

    @Test
    public void testObjectCreationSuccessful() {
        assertNotNull(EtcHostsUtil.getInstance(), "websiteBlocker.EtcHostsUtil creation failed");
    }

    @Test
    public void testSuccessfullyBlockingOfWebsite() {
        assertNotNull(EtcHostsUtil.getInstance(), "websiteBlocker.EtcHostsUtil is null. Unrecognised operating system.");
        assertTrue(EtcHostsUtil.getInstance().blockWebsite("facebook.com"), "Failed to block website.");
    }

    @Test
    public void testSuccessfullyUnblockingWebsite() {
        assertNotNull(EtcHostsUtil.getInstance(), "websiteBlocker.EtcHostsUtil is null. Unrecognised operating system.");
        assertTrue(EtcHostsUtil.getInstance().unblockWebsite("facebook.com"), "Failed to unblock website.");
    }
}
