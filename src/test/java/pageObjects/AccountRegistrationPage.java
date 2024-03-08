package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage (WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:
	@FindBy(name = "firstname")
	WebElement txtFirstName;

	@FindBy(name = "lastname")
	WebElement txtLastName;

	@FindBy(name = "email")
	WebElement txtEmail;

	@FindBy(name = "telephone")
	WebElement txtTelephone;

	@FindBy(name = "password")
	WebElement txtPassword;

	@FindBy(name = "confirm")
	WebElement txtConfirmPassword;

	@FindBy(name = "agree")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;


	// ACTION METHODS:
	public void setFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}
	public void setLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}

	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmedPassword)
	{
		txtConfirmPassword.sendKeys(confirmedPassword);
	}

	public void setPrivacyPolicy()
	{
		chkPolicy.click();
	}

	public void clickContinue()
	{
		btnContinue.click();
	}

	public String getConfirmationMsg()
	{
		try
		{
			return (msgConfirmation.getText());
		}
		catch (Exception e)
		{
			return (e.getMessage());
		}
	}

}