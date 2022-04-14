package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import com.gabrielmttl.websiteBlocker.WebsiteBlockerFacade;

import java.util.ArrayList;

/**
 * Central repo of data in the app.
 * Singleton.
 *
 * @author Gabriel Mititelu
 */
public class SaveTheMarshmallowModel {

    private final WebsiteBlockerFacade blocker = WebsiteBlockerFacade.getInstance();
    private ArrayList<String> urls;

    /**
     * Flag representing whether block is ON or OFF.
     */
    private boolean toggle = false;

    // Makes class singleton
    private final SaveTheMarshmallowModel instance = new SaveTheMarshmallowModel();

    private SaveTheMarshmallowModel() {
        urls = new ArrayList<>();
    }

    public SaveTheMarshmallowModel getInstance() {
        return instance;
    }

    /**
     * Add url to list.
     * @param url must be prefixed with http:// or https://
     */
    public boolean addWebsite(String url) {
        if (toggle) {
            if (!blocker.block(url)) {
                return false;
            }
        }

        urls.add(url);
        return true;
    }

    /**
     * Removes url from list.
     * @param url must be prefixed with http:// or https://
     */
    public boolean removeWebsite(String url) {
        if (toggle) {
           if (!blocker.unblock(url)) {
               return false;
           }
        }

        urls.remove(url);
        return true;
    }

    /**
     * Removes all websites from list.
     */
    public boolean removeAllWebsites() {
       for (String url: urls) {
           if (!removeWebsite(url)) {
               return false;
           }
       }

       return true;
    }

    /**
     * Iterates through all urls in list and blocks them.
     */
    public boolean turnBlockON() {
        for (String url: urls) {
            if (!blocker.block(url)){
                return false;
            }
        }
        toggle = true;
        return true;
    }

    /**
     * Iterates through all urls in list and unblocks them.
     * Does not remove the urls from the list.
     */
    public boolean turnBlockOFF() {
        for (String url: urls) {
            if (!blocker.unblock(url)) {
                return false;
            }
        }
        toggle = false;
        return true;
    }


}
