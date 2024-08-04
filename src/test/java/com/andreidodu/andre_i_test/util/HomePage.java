package com.andreidodu.andre_i_test.util;

import com.andreidodu.andre_i_test.configuration.WebDriverConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Durations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HomePage {

    @Value("${application.url}")
    private String baseURL;

    @Autowired
    private WebDriverConfiguration webDriverConfiguration;

    private final static String SEARCH_BOX_ID = "field-:r1h:";

    private WebDriver driver;

    public HomePage goToHomePage() {
        this.driver = webDriverConfiguration.createNewWebDriver();
        driver.get(baseURL);
        return this;
    }

    public HomePage goToSearchBoxAndSearch(String projectName) {
        WebElement element = this.driver.findElement(new By.ById(SEARCH_BOX_ID));
        click(element);
        try {
            writeText(element, projectName);
        } catch (Exception e) {
            log.error("Error: {}", e.toString());
        }
        return this;
    }

    public void quit() {
        try {
            this.driver.close();
            this.driver.quit();
        } catch (Exception e) {
            log.error("error: {}", e.toString());
        }
    }

    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            driver
                    .findElement((By) elementAttr)
                    .click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public <T> String readTextMessage(T elementAttr) throws InterruptedException {
        Thread.sleep(2000);
        return driver
                .findElement((By) elementAttr)
                .getText();
    }

    public <T> void waitElement(T elementAttr) {
        WebDriverWait tempWait = new WebDriverWait(driver, Durations.FIVE_SECONDS);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            tempWait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            tempWait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void writeText(T elementAttr, String text) throws InterruptedException {
        WebDriverWait tempWait = new WebDriverWait(driver, Durations.FIVE_SECONDS);
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            tempWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver
                    .findElement((By) elementAttr)
                    .sendKeys(text);
        } else {
            tempWait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
        Thread.sleep(3000);

    }

}
