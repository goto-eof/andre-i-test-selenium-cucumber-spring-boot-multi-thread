package com.andreidodu.andreitest.step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@AllArgsConstructor
public class CommonStep {

    @Given("cleaning database")
    public void cleaningDatabase() {
        System.out.println("cleaning database");
    }

    @And("restoring session")
    public void restoringSession() {
        System.out.println("restoring session");
    }

    @And("saving data on db")
    public void saveDataOnDB() {
        System.out.println("saving data on db");
    }

}
