package com.andreidodu.andreitest.util.webdriverstrategy.common;

import org.openqa.selenium.Proxy;

public class WebDriverUtil {

    public static final String OS_WINDOWS = "windows";
    public static final String FILE_EXTENSION_EXE = ".exe";

    public static Proxy buildProxy() {
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        return proxy;
    }

    public static String calculateExtension(String os) {
        if (OS_WINDOWS.equalsIgnoreCase(os)) {
            return FILE_EXTENSION_EXE;
        }
        return "";
    }

}
