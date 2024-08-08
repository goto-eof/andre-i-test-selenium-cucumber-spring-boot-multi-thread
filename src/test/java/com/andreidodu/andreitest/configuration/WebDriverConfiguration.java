package com.andreidodu.andreitest.configuration;

import com.andreidodu.andreitest.constants.BrowserConst;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Configuration
@RequiredArgsConstructor
public class WebDriverConfiguration {

    public static final String WEBDRIVER_CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    public static final String WEBDRIVER_GECKO_DRIVER_KEY = "webdriver.gecko.driver";

    private final Environment env;

    @Value("${com.andreidodu.test.browser}")
    private String browser;

    @Value("${com.andreidodu.test.os}")
    private String os;

    @PostConstruct
    public void initializeWebDrivers() throws IOException {

        String driverPath = env.getRequiredProperty("com.andreidodu.test.filename.web-driver." + os + "." + browser);

        if (BrowserConst.BROWSER_CHROME.equalsIgnoreCase(browser)) {
            Resource resource = new ClassPathResource(driverPath);
            System.setProperty(WEBDRIVER_CHROME_DRIVER_KEY, resource.getURI().getPath());
            return;
        }

        if (BrowserConst.BROWSER_FIREFOX.equalsIgnoreCase(browser)) {
            Resource resource = new ClassPathResource(driverPath);
            System.setProperty(WEBDRIVER_GECKO_DRIVER_KEY, resource.getURI().getPath());
        }

    }

    public WebDriver buildFirefoxDriver() throws IOException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = buildProxySettings();
        firefoxOptions.setCapability("proxy", proxy);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        return driver;
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
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
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

