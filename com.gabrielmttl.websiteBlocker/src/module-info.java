module com.gabrielmttl.websiteBlocker {
    requires spring.beans;
    requires spring.context;

    opens com.gabrielmttl.websiteBlocker;

    exports com.gabrielmttl.websiteBlocker;
}