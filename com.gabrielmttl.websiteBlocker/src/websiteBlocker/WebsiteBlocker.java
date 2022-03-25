package websiteBlocker;

public interface WebsiteBlocker {

    boolean blockWebsite(String url);
    boolean unblockWebsite(String url);
}
