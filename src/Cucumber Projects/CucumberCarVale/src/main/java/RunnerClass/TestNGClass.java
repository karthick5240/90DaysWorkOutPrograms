package RunnerClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/main/java/Feature"
					,glue = "StepDefinition"
					,monochrome = true
                     ,strict = true)

public class TestNGClass extends AbstractTestNGCucumberTests {

}
