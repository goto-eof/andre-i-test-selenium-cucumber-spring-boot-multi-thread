package com.andreidodu.andreitest;


import com.andreidodu.andreitest.constants.CucumberConfigurationValues;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.*;


@Slf4j
@CucumberOptions(
        plugin = {CucumberConfigurationValues.PLUGIN_PRETTY, CucumberConfigurationValues.PLUGIN_JSON_TARGET},
        glue = {CucumberConfigurationValues.GLUE, CucumberConfigurationValues.CUCUMBER_CONFIG},
        features = CucumberConfigurationValues.FEATURES)
public class RunCucumberTests extends AbstractTestNGCucumberTests {

    @Autowired
    private AbstractTransactionalTestNGSpringContextTests context;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        try {
        } catch (Exception e) {
            throw e;
        }
    }

    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        super.tearDownClass();
    }

}