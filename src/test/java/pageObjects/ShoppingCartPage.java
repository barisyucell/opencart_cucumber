package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage
{
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:

	@FindBy(xpath = "//div[@id='cart']")  // @FindBy(xpath="//button[@aria-expanded='false']")
	WebElement btnItems;
	
	@FindBy(xpath = "//strong[normalize-space()='View Cart']")
	WebElement lnkViewCart;
	
	@FindBy(xpath = "//*[@id='content']/div[2]/div/table//strong[text()='Total:']//following::td")
	WebElement lblTotalPrice;

	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement btnCheckout;


	// ACTION METHODS:
	
	public void clickItemsToNavigateToCart()
	{
		btnItems.click();
	}
	
	public void clickViewCart()
	{
		lnkViewCart.click();
	}
	
	public String getTotalPrice()
	{
		return lblTotalPrice.getText();
	}
	
	public void clickCheckout()
	{
		btnCheckout.click();
	}

}