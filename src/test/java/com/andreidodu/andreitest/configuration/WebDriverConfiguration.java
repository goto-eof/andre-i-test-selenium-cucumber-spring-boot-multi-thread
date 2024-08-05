package com.andreidodu.andreitest.configuration;

import com.andreidodu.andreitest.constants.BrowserConst;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Configuration
public class WebDriverConfiguration {

    public static final String WEBDRIVER_CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    public static final String WEBDRIVER_GECKO_DRIVER_KEY = "webdriver.gecko.driver";
    @Value("${filename.web-driver.linux.chrome}")
    private String filenameWebDriverLinuxChrome;

    @Value("${filename.web-driver.linux.firefox}")
    private String filenameWebDriverLinuxFirefox;

    @Value("${browser}")
    private String browser;

    @PostConstruct
    public void initializeWebDrivers() throws IOException {

        if (BrowserConst.BROWSER_CHROME.equalsIgnoreCase(browser)) {
            Resource resource = new ClassPathResource(filenameWebDriverLinuxChrome);
            System.setProperty(WEBDRIVER_CHROME_DRIVER_KEY, resource.getURI().getPath());
            return;
        }

        if (BrowserConst.BROWSER_FIREFOX.equalsIgnoreCase(browser)) {
            Resource resource = new ClassPathResource(filenameWebDriverLinuxFirefox);
            System.setProperty(WEBDRIVER_GECKO_DRIVER_KEY, resource.getURI().getPath());
        }

    }

    public WebDriver buildFirefoxDriver() throws IOException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = buildProxySettings();
        firefoxOptions.setCapability("proxy", proxy);
        return new FirefoxDriver(firefoxOptions);
    }

    private static Proxy buildProxySettings() {
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        return proxy;
    }

    public WebDriver buildChromeDriver() throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        Proxy proxy = buildProxySettings();
        chromeOptions.setCapability("proxy", proxy);
        return new ChromeDriver(chromeOptions);
    }


    public WebDriver createNewWebDriver() throws IOException {

        if (BrowserConst.BROWSER_CHROME.equalsIgnoreCase(browser)) {
            return this.buildChromeDriver();
        }
        if (BrowserConst.BROWSER_FIREFOX.equalsIgnoreCase(browser)) {
            return this.buildFirefoxDriver();
        }

        throw new IllegalArgumentException("Invalid browser value");
    }


}

