package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith (Cucumber.class)
@CucumberOptions(
		features = {".//Features/"},   // to execute all features in "Features" package.
		// features = {".//Features/Login.feature"},
		// features = {".//Features/LoginDDTExcel.feature"},
		// features = {".//Features/Registration.feature"},
		// features = {"@target/rerun.txt"},   // to execute only failed scenarios.

		glue = "stepDefinitions",

		plugin = { "pretty", "html:reports/myreport.html",                                  // to generate Cucumber report.
				   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",  // to generate ExtentReport.
				   "rerun:target/rerun.txt"                                                 // to capture failed scenarios.
		         },

		dryRun = false,     // to check mapping between Scenario Steps and Step Definition methods.
		monochrome = true,  // to avoid junk characters in output.
		publish = true      // to publish Cucumber report in Cucumber server.

		// tags = "@sanity"                       // to execute every scenario with "@sanity" tag.
		// tags = "@sanity and @regression"       // to execute scenarios with both "@sanity" tag and "@regression" tag.
		// tags = "@sanity and not @regression"   // to execute scenarios with "@sanity" tag but not with "@regression" tag.
		// tags = "@sanity or @regression"        // to execute scenarios with either "@sanity" tag or "@regression" tag.
		)
public class TestRunner
{

}