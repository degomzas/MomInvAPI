package testSuites;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        //strict = false,
        features = {"src/test/features"},
        plugin = {"pretty", "html:reports/cucumber-reports.html"},
        glue = "steps")

public class CucumberTestSuite {

}