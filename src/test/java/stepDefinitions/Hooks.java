package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks
{
    WebDriver driver;
    Properties properties;


	@Before
    public void setup() throws IOException
    {
        driver = BaseClass.initializeBrowser();
    	    	
    	properties = BaseClass.getProperties();

    	driver.get(properties.getProperty("appURL"));

    	driver.manage().window().maximize();
	}

    @After
    public void tearDown(Scenario scenario)
    {
       driver.quit();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario)  // For every failed test scenario, a screenshot will be taken and attach to the test report.
    {
        if (scenario.isFailed())
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot,"image/png",scenario.getName());
        }
    }

}