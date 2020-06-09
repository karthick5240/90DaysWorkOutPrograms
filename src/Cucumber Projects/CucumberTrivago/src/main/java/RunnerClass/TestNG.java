package RunnerClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/main/java/feature"}
							,glue = "StepDefs"
							,monochrome = true
							,strict = true)

public class TestNG extends AbstractTestNGCucumberTests {

}
