package RunnerClass;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/main/java/Feature/bigbasket.feature"}
							,glue = "StepDefinition"
							,monochrome = true
							,snippets= SnippetType.CAMELCASE
							,strict = true,
							 plugin= {"pretty", "html:target"})

public class Bigbasket_testng  extends AbstractTestNGCucumberTests{

}
