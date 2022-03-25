package websiteBlocker;

import java.util.ArrayList;


/**
 * This class ...
 *
 * <p>
 *      Singleton.
 *      To be used as a single point of entry for the module.
 *
 * </p>
 *
 * @author Gabriel Mititelu
 */
public final class WebsiteBlockerFacade {
    private ArrayList<String> urls;

    private static WebsiteBlockerFacade facade = null;

    private WebsiteBlockerFacade() {
        urls = new ArrayList<>();
    }

    public WebsiteBlockerFacade getInstance() {
        if (facade == null) {
            facade = new WebsiteBlockerFacade();
        }

        return facade;
    }

    public boolean block(String url) {
        return false;
    }

    public boolean unblock(String url) {
        return false;
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

    private boolean isValidURL(String url) {
        return false;
    }


}
