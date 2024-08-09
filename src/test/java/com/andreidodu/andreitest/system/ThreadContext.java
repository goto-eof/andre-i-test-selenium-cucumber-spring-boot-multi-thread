package com.andreidodu.andreitest.system;

import org.openqa.selenium.WebDriver;

public class ThreadContext {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver user) {
        driver.set(user);
    }

    public static void clear() {
        driver.remove();
    }
}