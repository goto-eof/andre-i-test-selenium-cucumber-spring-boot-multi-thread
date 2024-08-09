package com.andreidodu.andreitest.util.webdriverstrategy;

import org.openqa.selenium.WebDriver;

public interface WebDriverStrategy {

    boolean accept(String browserName);

    WebDriver buildDriver();

}
