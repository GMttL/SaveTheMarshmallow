package websiteBlocker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteBlockerFacadeTest {

    @Test
    void getInstance() {
        assertNotNull(WebsiteBlockerFacade.getInstance(), "Could not create object of type WebsiteBlockerFacade");
    }

    @Test
    void block() {

    }

    @Test
    void unblock() {

    }

    @Test
    void unblockAll() {

    }

}