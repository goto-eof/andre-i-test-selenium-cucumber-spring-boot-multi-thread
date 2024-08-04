package com.andreidodu.andre_i_test.configuration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"com.andreidodu.andre_i_test"},
        features = "src/test/resources/features",
        dryRun = true,
        monochrome = true,
        publish = false
)
public class CucumberConfiguration {

}
