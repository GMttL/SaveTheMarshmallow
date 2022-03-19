public final class WebsiteBlocker {

    private static final WebsiteBlocker instance = new WebsiteBlocker();

    private static String OS;
    private static String hostFile;


    private WebsiteBlocker() {
        OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            hostFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }
        else if (OS.contains("mac") || OS.contains("nux")) {
            hostFile = "/etc/hosts";
        }
        else {
            // unknown OS. We cannot perform action.
            OS = null;
        }

    }

    public static WebsiteBlocker getInstance() {
        /*
            Returns null in case of unknown or unsupported Operating Systems.

            Supported Operating Systems:
            - Windows since year 2000+
            - Mac OS
            - Most Linux Distributions (this may be a source of errors in small or niche distributions that change
                                        the location of system files)
         */
        if (OS != null) {
            return instance;
        }

        return null;
    }


}
