import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class WebsiteBlockerTests {

    @Test
    public void testObjectCreationSuccessful() {
        WebsiteBlocker wblocker = WebsiteBlocker.getInstance();
        assertNotNull(wblocker, "websiteBlocker creation failed on Mac OS");
    }

}
