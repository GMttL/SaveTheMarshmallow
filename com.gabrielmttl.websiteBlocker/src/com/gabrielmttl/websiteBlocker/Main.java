package com.gabrielmttl.websiteBlocker;

import com.gabrielmttl.websiteBlocker.operatingSystems.MacOS;

/**
 * This class has been created for the purposes of testing the module
 *
 * @author Gabriel Mititelu
 */

public class Main {

    public static void main(String[] args) {
        WebsiteBlockerFacade wb = WebsiteBlockerFacade.getInstance();

        boolean flag = false;
        System.out.println("Blocking http://facebook.com");
        if (wb.block("http://facebook.com")) {
            System.out.println("Success");
        }
        else {
            System.out.println("Failed blocking website");
        }

        System.out.println("Flushing DNS -> this should remove the test.txt");
        MacOS.getInstance().DNSFlush();

    }

}
