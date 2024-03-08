package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass
{
	static WebDriver driver;
	static Properties properties;
	static Logger logger;


	public static WebDriver initializeBrowser() throws IOException
	{
        /*
        -> "execution_env = remote" means that the execution is going to perform in Selenium Grid environment,
           and we set the value of "execution_env" parameter in "config.properties" file.
        */
		if (getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// OS (Operating System) configuration:
			if (getProperties().getProperty("os").equalsIgnoreCase("windows"))
			{
			    capabilities.setPlatform(Platform.WIN11);
			}
			else if (getProperties().getProperty("os").equalsIgnoreCase("mac"))
			{
			    capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS...");
			}

			// Browser configuration:
			switch (getProperties().getProperty("browser").toLowerCase())
			{
			    case "chrome":
					capabilities.setBrowserName("chrome");
					break;
				case "edge":
			        capabilities.setBrowserName("MicrosoftEdge");
			        break;
			    default:
			        System.out.println("No matching browser...");
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

        /*
        -> "execution_env = local" means that the execution is going to perform in our local environment,
           and we set the value of "execution_env" parameter in "config.properties" file.
        */
		else if (getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(getProperties().getProperty("browser").toLowerCase())
			{
				case "chrome":
			        driver = new ChromeDriver();
			        break;
			    case "edge":
			    	driver = new EdgeDriver();
			        break;
			    default:
			        System.out.println("No matching browser...");
			        driver = null;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	public static WebDriver getDriver()
	{
			return driver;
	}

	public static Properties getProperties() throws IOException
	{
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");

        properties = new Properties();
		properties.load(file);
		return properties;
	}

	public static Logger getLogger()
	{
		logger = LogManager.getLogger();
		return logger;
	}

	public static String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public static String randomNumber()
	{
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	public static String randomAlphaNumeric()
	{
		String str = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(10);
		return str + num;
	}

}