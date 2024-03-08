package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import utilities.DataReader;


public class LoginSteps
{
    WebDriver driver;

    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;

    List < HashMap<String,String> > dataMap;
    // Each row in "Opencart_LoginData" Excel file is corresponding to one HashMap, and we are storing these HashMaps in a List called "dataMap".

    @Given ("the user navigates to login page")
    public void user_navigate_to_login_page()
    {
        BaseClass.getLogger().info("Go to MyAccount page and click Login");

        homePage = new HomePage(BaseClass.getDriver());
    	homePage.clickMyAccount();
    	homePage.clickLogin();
    }

    @When ("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as (String email, String pwd)
    {
        BaseClass.getLogger().info("Enter the email and password");

        loginPage = new LoginPage(BaseClass.getDriver());

       	loginPage.setEmail(email);
        loginPage.setPassword(pwd);
    }

    @When ("the user clicks on the Login button")
    public void click_on_login_button()
    {
        loginPage.clickLogin();

        BaseClass.getLogger().info("Login button has clicked");
    }

    @Then ("the user should be redirected to the MyAccount Page")
    public void user_navigates_to_my_account_page()
    {
        myAccountPage = new MyAccountPage(BaseClass.getDriver());

        boolean targetPage = myAccountPage.isMyAccountPageExist();

		Assert.assertEquals(targetPage, true);
    }


    // ********** Data-Driven Testing **********
    // This method is the Step Definition method of "THEN" step in "LoginDDTExcel.feature".
    @Then ("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data (String rows)
    {
        dataMap = DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index = Integer.parseInt(rows)-1;

        String email = dataMap.get(index).get("username");
        String pwd = dataMap.get(index).get("password");
        String exp_res = dataMap.get(index).get("res");

        loginPage = new LoginPage(BaseClass.getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(pwd);
        loginPage.clickLogin();


        myAccountPage = new MyAccountPage(BaseClass.getDriver());

        try
        {
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            /*
            *** We need to check whether the test data are valid.
                Because sometimes the test result may be "failed" even though the valid test data are used.
                Or the test result may be "passed" with invalid test data.
            **  Checking-1:
            ->  If the "exp_res (expected result)" value in "Opencart_LoginData" Excel file is "Valid" and Login process is successful
                for the specified user (it means "targetPage = true"), then our test is PASSED!
            ->  If the "exp_res (expected result)" value in "Opencart_LoginData" Excel file is "Valid" but Login process is failed
                for the specified user (it means "targetPage = false"), then our test is FAILED!
            */
            if (exp_res.equals("Valid"))
            {
                if (targetPage == true)
                {
                    MyAccountPage myAccountPage2 = new MyAccountPage(BaseClass.getDriver());

                    myAccountPage2.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            /*
            ** Checking-2:
            -> If the "exp_res (expected result)" value in "Opencart_LoginData" Excel file is "Invalid" but Login process is successful
               for the specified user (it means "targetPage = true"), then our test is FAILED!
            -> If the "exp_res (expected result)" value in "Opencart_LoginData" Excel file is "Invalid" and Login process is failed
               for the specified user (it means "targetPage = false"), then our test is PASSED!
            */
            if (exp_res.equals("Invalid"))
            {
                if (targetPage == true)
                {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }
        }

        catch (Exception e)
        {
            Assert.assertTrue(false);  // Assert.fail();
        }

    }

}