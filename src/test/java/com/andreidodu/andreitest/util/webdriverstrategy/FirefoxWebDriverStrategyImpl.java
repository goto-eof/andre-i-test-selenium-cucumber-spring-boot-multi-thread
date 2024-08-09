package com.andreidodu.andreitest.util.webdriverstrategy;


import com.andreidodu.andreitest.constants.ConfigurationConstants;
import com.andreidodu.andreitest.util.webdriverstrategy.common.WebDriverUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FirefoxWebDriverStrategyImpl implements WebDriverStrategy {

    public static final String BROWSER_NAME = "firefox";
    public static final String CAPABILITY_PROXY = "proxy";

    @Value("${com.andreidodu.test.filename.web-driver.firefox}")
    private String webDriverChrome;

    @Value("${com.andreidodu.test.os}")
    private String os;

    @Override
    public boolean accept(String browserName) {
        return (BROWSER_NAME.equalsIgnoreCase(browserName));
    }

    @Override
    public WebDriver buildDriver() {
        System.setProperty(ConfigurationConstants.WEB_DRIVER_GECKO_DRIVER, webDriverChrome + WebDriverUtil.calculateExtension(os));
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = WebDriverUtil.buildProxy();
        firefoxOptions.setCapability(CAPABILITY_PROXY, proxy);
        return new FirefoxDriver(firefoxOptions);
    }

}
