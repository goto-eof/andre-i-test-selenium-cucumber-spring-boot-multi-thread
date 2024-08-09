package com.andreidodu.andreitest.util.webdriverstrategy;

import com.andreidodu.andreitest.constants.ConfigurationConstants;
import com.andreidodu.andreitest.util.webdriverstrategy.common.WebDriverUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChromeWebDriverStrategyImpl implements WebDriverStrategy {

    public static final String BROWSER_NAME = "chrome";
    public static final String CAPABILITY_PROXY = "proxy";

    @Value("${com.andreidodu.test.filename.web-driver.chrome}")
    private String webDriverChrome;

    @Value("${com.andreidodu.test.os}")
    private String os;

    @Override
    public boolean accept(String browserName) {
        return (BROWSER_NAME.equalsIgnoreCase(browserName));
    }

    @Override
    public WebDriver buildDriver() {
        System.setProperty(ConfigurationConstants.WEB_DRIVER_CHROME_DRIVER, webDriverChrome + WebDriverUtil.calculateExtension(os));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        Proxy proxy = WebDriverUtil.buildProxy();
        chromeOptions.setCapability(CAPABILITY_PROXY, proxy);
        return new ChromeDriver(chromeOptions);
    }


}
