import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WebsiteBlockerTests {

    @Test
    public void testObjectCreationSuccessful() {
        WebsiteBlocker wblocker = WebsiteBlocker.getInstance();
        assertNotNull(wblocker, "websiteBlocker creation failed on Mac OS");
    }

    @Test
    public void testSuccessfulBlockingOfWebsite() {
        assertNotNull(WebsiteBlocker.getInstance(), "WebsiteBlocker is null. Unrecognised operating system.");
        assertTrue(WebsiteBlocker.getInstance().blockWebsite("facebook.com"), "Failed to block website.");
    }

    @Test
    public void testSuccessfullUnblockingWebsite() {
        assertNotNull(WebsiteBlocker.getInstance(), "WebsiteBlocker is null. Unrecognised operating system.");
        assertTrue(WebsiteBlocker.getInstance().unblockWebsite("facebook.com"), "Failed to unblock website.");
    }
}
