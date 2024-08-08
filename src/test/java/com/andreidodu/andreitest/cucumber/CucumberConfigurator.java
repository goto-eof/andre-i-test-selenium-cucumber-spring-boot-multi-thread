package com.andreidodu.andreitest.cucumber;

import com.andreidodu.andreitest.AndreITestApplication;
import com.andreidodu.andreitest.constants.CucumberConfigurationValues;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {CucumberConfigurationValues.GLUE, CucumberConfigurationValues.CUCUMBER_CONFIG},
        features = CucumberConfigurationValues.FEATURES)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfigurator {
}
