package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(linkText = "Register")
	WebElement lnkRegister;

	@FindBy(linkText = "Login")
	WebElement lnkLogin;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement txtSearchBox;

	@FindBy(xpath = "//div[@id='search']//button[@type='button']")
	WebElement btnSearch;


	// ACTION METHODS:
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}

	public void clickRegister()
	{
		lnkRegister.click();
	}

	public void clickLogin()
	{
		lnkLogin.click();
	}

	public void enterProductName(String productName)
	{
		txtSearchBox.sendKeys(productName);
	}

	public void clickSearch()
	{
		btnSearch.click();
	}

}