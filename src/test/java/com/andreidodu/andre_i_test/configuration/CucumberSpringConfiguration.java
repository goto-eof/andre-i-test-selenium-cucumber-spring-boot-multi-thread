package com.andreidodu.andre_i_test.configuration;

import com.andreidodu.andre_i_test.AndreITestApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@EnableConfigurationProperties
@CucumberContextConfiguration
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringConfiguration.class)
@SpringBootTest(classes = {AndreITestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberContextConfiguration.class);

//    @DynamicPropertySource
//    public static void dynamicProperties(DynamicPropertyRegistry registry) {
//        String profile = System.getProperty("spring.profiles.active", "default");
//        registry.add("spring.profiles.active", () -> profile);
//    }

    @Before
    public void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
