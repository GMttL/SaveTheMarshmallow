package websiteBlocker;

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
    private ArrayList<String> urls;
    private WebsiteBlocker blocker;

    private static WebsiteBlockerFacade facade = null;

    private WebsiteBlockerFacade() {
        BeanFactory factory = new ClassPathXmlApplicationContext("websiteBlocker/resources/beans.xml");
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
        if (url.contains("http://") || url.contains("https://")) {
            boolean blocked = blocker.blockWebsite(url);
            if (blocked) {
                urls.add(url);
                return true;
            }
        }
        return false;
    }


    public boolean unblock(String url) {
        boolean unblocked = blocker.unblockWebsite(url);

        if (unblocked) { return true; }
        else { return false; }
    }


    public boolean unblockAll() {

        for (String url: urls) {
            boolean unblocked = unblock(url);
            if (!unblocked) {
                System.err.println("Couldn't unblock all websites. current url: " + url);
                return false;
            }
            urls.remove(url);
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
