package com.gabrielmttl.websiteBlocker;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * This class ...
 *
 * <p>
 *      To be used as a single point of entry for the module.
 * </p>
 *
 * @author Gabriel Mititelu
 */
public final class WebsiteBlockerFacade {
    private final WebsiteBlocker blocker;

    private static WebsiteBlockerFacade facade = null;

    private WebsiteBlockerFacade() {
        BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
        blocker = (WebsiteBlocker) factory.getBean("blocker");
    }

    public static WebsiteBlockerFacade getInstance() {
        if (facade == null) {
            facade = new WebsiteBlockerFacade();
        }

        return facade;
    }

    /**
     *
     * @param url must be prefixed with http or https.
     *            will return false otherwise.
     *
     * @return true if website was blocked successfully.
     */

    public boolean block(String url) {
        return blocker.blockWebsite(url);
    }

    /**
     *
     * @param url must be prefixed with http or https.
     *            will return false otherwise.
     *
     * @return true if website was blocked successfully.
     */

    public boolean unblock(String url) {
        return blocker.unblockWebsite(url);
    }

}
