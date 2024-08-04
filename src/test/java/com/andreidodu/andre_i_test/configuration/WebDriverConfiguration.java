package com.andreidodu.andre_i_test.configuration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebDriverConfiguration {
    @Value("${browser}")
    private String browser;


    public WebDriver buildFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/webdriver/linux/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        firefoxOptions.setCapability("proxy", proxy);
        return new FirefoxDriver(firefoxOptions);
    }

    public WebDriver buildChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/linux/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        chromeOptions.setCapability("proxy", proxy);
        return new ChromeDriver(chromeOptions);
    }


    public WebDriver createNewWebDriver() {
        if ("chrome".equalsIgnoreCase(browser)) {
            return this.buildChromeDriver();
        }
        if ("firefox".equalsIgnoreCase(browser)) {
            return this.buildFirefoxDriver();
        }
        throw new IllegalArgumentException("Invalid browser value");
    }

}

