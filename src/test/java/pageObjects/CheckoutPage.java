package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class CheckoutPage extends BasePage
{
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
	}


	// ELEMENTS:
	@FindBy(xpath = "//input[@id='input-payment-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	WebElement txtAddress1;
	
	@FindBy(xpath = "//input[@id='input-payment-address-2']")
	WebElement txtAddress2;

	@FindBy(xpath = "//input[@id='input-payment-city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	WebElement txtPin;

	@FindBy(xpath = "//select[@id='input-payment-country']")
	WebElement drpCountry;

	@FindBy(xpath = "//select[@id='input-payment-zone']")
	WebElement drpState;

	@FindBy(xpath = "//input[@id='button-payment-address']")
	WebElement btnPaymentAddress;
	
	@FindBy(xpath = "//input[@id='button-shipping-address']")
	WebElement btnShippingAddress;
	
	@FindBy(xpath = "//textarea[@name='comment']")
	WebElement txtDeliveryMethod;
	
	@FindBy(xpath = "//input[@id='button-shipping-method']")
	WebElement btnShippingMethod;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkTerms;

	@FindBy(xpath = "//input[@id='button-payment-method']")
	WebElement btnPaymentMethod;

	@FindBy(xpath = "//strong[text()='Total:']//following::td")
	WebElement lblTotalPrice;

	@FindBy(xpath = "//input[@id='button-confirm']")
	WebElement btnConfirmOrder;
	
	@FindBy(xpath = "//*[@id='content']/h1")
	WebElement lblOrderConfirmMsg;


	// ACTION METHODS:

	public void setFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}

	public void setAddress1(String address1)
	{
		txtAddress1.sendKeys(address1);
	}

	public void setAddress2(String address2)
	{
		txtAddress2.sendKeys(address2);
	}

	public void setCity(String city)
	{
		txtCity.sendKeys(city);
	}

	public void setPin(String pin)
	{
		txtPin.sendKeys(pin);
	}

	public void setCountry(String country)
	{
		new Select(drpCountry).selectByVisibleText(country);
	}

	public void setState(String state)
	{
		new Select(drpState).selectByVisibleText(state);
	}

	public void clickContinueAfterPaymentAddress()
	{
		btnPaymentAddress.click();
	}
	
	public void clickContinueAfterDeliveryAddress()
	{
		btnShippingAddress.click();
	}
	
	public void setDeliveryMethodComment(String deliveryMsg)
	{
		txtDeliveryMethod.sendKeys(deliveryMsg);
	}
	
	public void clickContinueAfterDeliveryMethod()
	{
		btnShippingMethod.click();
	}
	
	public void selectTermsAndConditions()
	{
		chkTerms.click();
	}
	
	public void clickContinueAfterPaymentMethod()
	{
		btnPaymentMethod.click();
	}
	
	public String getTotalPriceBeforeConfirmingOrder()
	{
		return lblTotalPrice.getText();
	}
	
	public void clickConfirmOrder()
	{
		btnConfirmOrder.click();
	}
	
	public boolean isOrderPlaced() throws InterruptedException
	{
		try
		{
			driver.switchTo().alert().accept();
			Thread.sleep(2000);

			btnConfirmOrder.click();
			Thread.sleep(3000);

			if (lblOrderConfirmMsg.getText().equals("Your order has been placed!"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		catch (Exception e)
		{
			return false;
		}
	}

}