package stepDefinitions;

import java.util.Map;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;


public class RegistrationSteps
{
	WebDriver driver;

	HomePage homePage;

	LoginPage loginPage;

	AccountRegistrationPage accountRegPage;


	@Given ("the user navigates to Register Account page")
	public void user_navigates_to_register_account_page()
	{
		homePage = new HomePage(BaseClass.getDriver());

    	homePage.clickMyAccount();
        homePage.clickRegister();
	}

	@When ("the user enters the details into below fields")
	public void user_enters_the_details_into_below_fields (DataTable dataTable)
	{
		/*
		*** DataTable ***
		(HashMap in Java corresponds to the DataTable concept in Cucumber).
		"DataTable" is a Cucumber class and actually a type of table.
		We use this structure when we specify multiple data in a Feature step ("When" step in "Registration.feature").
		The "dataTable" method parameter here is a single parameter that stores all the data for the "When" step in "Registration.feature".
		*/

		Map <String,String> dataMap = dataTable.asMap(String.class, String.class);
		// Reading data from DataTable cannot be done directly. For this, we have to convert the "dataTable" to "Map" type.

		accountRegPage = new AccountRegistrationPage(BaseClass.getDriver());

		accountRegPage.setFirstName(dataMap.get("firstName"));
		accountRegPage.setLastName(dataMap.get("lastName"));
		accountRegPage.setEmail(BaseClass.randomAlphaNumeric() + "@gmail.com");
		accountRegPage.setTelephone(dataMap.get("telephone"));
		accountRegPage.setPassword(dataMap.get("password"));
		accountRegPage.setConfirmPassword(dataMap.get("password"));
	}

	@When ("the user selects Privacy Policy")
	public void user_selects_privacy_policy()
	{
		accountRegPage.setPrivacyPolicy();
	}

	@When ("the user clicks on Continue button")
	public void user_clicks_on_continue_button()
	{
		accountRegPage.clickContinue();
	}

	@Then ("the user account should get created successfully")
	public void user_account_should_get_created_successfully()
	{
		String confirmationMsg = accountRegPage.getConfirmationMsg();
		/*
		-> "getConfirmationMsg()" method returns "msgConfirmation = Your Account Has Been Created!" element that exists on the page
		   opens after successful account registration.
		*/

		Assert.assertEquals("Your Account Has Been Created!", confirmationMsg);
	}

}