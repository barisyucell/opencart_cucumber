package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage
{
	WebDriver driver;

	public SearchPage(WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:
	@FindBy(xpath = "//*[@id='content']/div[3]//img")
	List<WebElement> searchProducts;
				
	@FindBy(xpath = "//input[@id='input-quantity']")
	WebElement txtQuantity;
		
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement btnAddToCart;
		
	@FindBy(xpath = "//div[contains(text(),'Success: You have added')]")
	WebElement confirmationMsg;


	// ACTION METHODS:

	public boolean isProductExist(String productName)
	{
		boolean flag = false;

		for (WebElement product: searchProducts)
		{
			if (product.getAttribute("title").equals(productName))
			{
				flag = true;
				break;
			}
		}

		return flag;
	}

	public void selectProduct(String productName)
	{
		for (WebElement product: searchProducts)
		{
			if (product.getAttribute("title").equals(productName))
			{
				product.click();
			}
		}
	}

	public void setQuantity(String quantity)
	{
		txtQuantity.clear();
		txtQuantity.sendKeys(quantity);
	}

	public void addToCart()
	{
		btnAddToCart.click();
	}

	public boolean checkConfirmationMessage()
	{
		try
		{
			return confirmationMsg.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}

}