package com.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {
                "html:target/TestResult/reports.html",
                "json:target/TestResult/cucumber-json.json"
        },
        //glue = {"src/test/java/com/stepDefinations"}
        glue={"com.stepDefinition"},
        tags = {"@Test1"}
)

public class CucumberRunnerTest {
        private static WebDriverFactory driverFactory;
}
