package com.gabrielmttl.websiteBlocker;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;


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
    // TODO: Preferably the app returns the system back to normal when it exits, however, if that's not possible or dangerous, will have to store the blocked urls on disk and retrieve them at start time.
    private final ArrayList<String> urls;
    private final WebsiteBlocker blocker;

    private static WebsiteBlockerFacade facade = null;

    private WebsiteBlockerFacade() {
        BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
        blocker = (WebsiteBlocker) factory.getBean("blocker");
        urls = new ArrayList<>();
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

        if (blocker.blockWebsite(url)) {
            urls.add(url);
            return true;
        }

        return false;
    }


    public boolean unblock(String url) {
        boolean unblocked = blocker.unblockWebsite(url);

        if (unblocked) {
            urls.remove(url);
        }

        return unblocked;
    }


    public boolean unblockAll() {
        ArrayList<String> urlsCopy = getUrls();

        for (String url: urlsCopy) {
            boolean unblocked = unblock(url);
            if (!unblocked) {
                System.err.println("Couldn't unblock all websites. current url: " + url);
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns a shallow copy of an array list of strings.
     *
     * @return shallow copy of the list of urls.
     */
    public ArrayList<String> getUrls() {
        return new ArrayList<>(urls);
    }

}
