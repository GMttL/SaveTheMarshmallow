package com.gabrielmttl.websiteBlocker;

import com.gabrielmttl.websiteBlocker.WebsiteBlockerFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteBlockerFacadeTest {

    @Test
    void getInstance() {
        assertNotNull(WebsiteBlockerFacade.getInstance(), "Could not create object of type WebsiteBlockerFacade");
    }

    @Test
    void block() {
        String url1 = "www.facebook.com";
        assertFalse(WebsiteBlockerFacade.getInstance().block(url1), "facade.block accepts non-http/https urls " + url1);

        String url2 = "http://  facebook.com";
        assertTrue(WebsiteBlockerFacade.getInstance().block(url2), "facade.block cannot handle whitespace correctly " + url2);

        String url3 = "https://facebook.com";
        assertTrue(WebsiteBlockerFacade.getInstance().block(url3), "facade.block cannot handle basic use case correctly " + url3);
    }

    @Test
    void unblock() {
        String url1 = "www.facebook.com";
        assertFalse(WebsiteBlockerFacade.getInstance().unblock(url1), "facade.block accepts non-http/https urls " + url1);

        String url2 = "http://  facebook.com";
        assertTrue(WebsiteBlockerFacade.getInstance().unblock(url2), "facade.unblock does not handle whitespaces correctly " + url2);

        String url3 = "https://facebook.com";
        assertTrue(WebsiteBlockerFacade.getInstance().unblock(url3), "facade.unblock does not handle basic use case correctly " + url3);
    }

    @Test
    void unblockAll() {
        WebsiteBlockerFacade blocker = WebsiteBlockerFacade.getInstance();
        block();
        boolean unblockedAll = blocker.unblockAll();
        assertTrue(unblockedAll, "facade.unblockAll could not unblock all websites");
        assertEquals(0, blocker.getUrls().size(), "facade.unblockAll could not clear array");
    }

}