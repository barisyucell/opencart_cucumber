package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;


	// ACTION METHODS:
	public boolean isMyAccountPageExist()
	{
		try
		{
			return (msgHeading.isDisplayed());
			/*
            -> If "msgHeading" element is displayed, then the login process is successful.
               In other words, if the login process is successful, then "msgHeading" element should be displayed.
            */
		}
		catch (Exception e)
		{
			return (false);
			/*
			-> If "msgHeading" element can't be displayed, then the login process is unsuccessful.
               In other words, if the login process is unsuccessful, then "msgHeading" element shouldn't be displayed.
            */
		}
	}

	public void clickLogout()
	{
		lnkLogout.click();
	}
	
}