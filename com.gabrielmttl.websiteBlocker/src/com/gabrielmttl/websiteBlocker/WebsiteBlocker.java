package com.gabrielmttl.websiteBlocker;

public interface WebsiteBlocker {
    boolean blockWebsite(String url);
    boolean unblockWebsite(String url);
}
