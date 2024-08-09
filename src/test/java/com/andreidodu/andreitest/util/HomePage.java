package com.andreidodu.andreitest.util;

import com.andreidodu.andreitest.system.ThreadContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Durations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomePage {

    private final static String SEARCH_BOX_ID = "field-:r1h:";

    @Value("${application.url}")
    private String baseURL;

    @Value("${com.andreidodu.test.browser}")
    private String browserName;

    private final WebDriverBuilder webDriverBuilder;

    public HomePage goToHomePage() throws IOException {
        ThreadContext.setDriver(webDriverBuilder.createNewWebDriver(browserName));
        ThreadContext.getDriver().get(baseURL);
        return this;
    }

    public void goToSearchBoxAndSearch(String projectName) {
        WebElement element = ThreadContext.getDriver().findElement(new By.ById(SEARCH_BOX_ID));
        click(element);
        try {
            writeText(element, projectName, Durations.FIVE_MINUTES);
        } catch (Exception e) {
            log.error("Error: {}", e.toString());
        }
    }

    public void quit() {
        try {
            ThreadContext.getDriver().close();
            ThreadContext.getDriver().quit();
        } catch (Exception e) {
            log.error("error: {}", e.toString());
        }
    }

    public <T> void click(T elementAttr) {
        waitElement(elementAttr, Durations.FIVE_MINUTES);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            ThreadContext.getDriver()
                    .findElement((By) elementAttr)
                    .click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public <T> String readTextMessage(T elementAttr, Duration duration) throws InterruptedException {
        waitElement(elementAttr, duration);
        return ThreadContext.getDriver()
                .findElement((By) elementAttr)
                .getText();
    }

    public <T> boolean isElementPresent(T elementAttr, Duration duration) throws InterruptedException {
        return ThreadContext.getDriver()
                .findElement((By) elementAttr).isDisplayed();
    }

    public <T> void waitElement(T elementAttr, Duration duration) {
        WebDriverWait tempWait = new WebDriverWait(ThreadContext.getDriver(), duration);
        // waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            tempWait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            tempWait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void writeText(T elementAttr, String text, Duration duration) throws InterruptedException {
        WebDriverWait tempWait = new WebDriverWait(ThreadContext.getDriver(), duration);
        waitElement(elementAttr, Durations.FIVE_MINUTES);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            tempWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            ThreadContext.getDriver()
                    .findElement((By) elementAttr)
                    .sendKeys(text);
        } else {
            tempWait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
        Thread.sleep(3000);

    }

}
