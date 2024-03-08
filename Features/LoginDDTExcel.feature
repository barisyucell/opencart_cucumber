Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven Excel
    Given the user navigates to login page
    Then the user should be redirected to the MyAccount Page by passing email and password with excel row "<row_index>"

    Examples:
      | row_index |
      | 1         |
      | 2         |
      | 3         |
      | 4         |
      | 5         |


# Login Automation by reading data from "Opencart_LoginData" Excel file.

# in "Opencart_LoginData" Excel file in "testData" package;
#    1) "pavanoltraining@gmail.com - test@123" account exists        and expected result("res") is Valid.   So the test result should be PASSED!
#    2) "lakshmi@yahoo.com - Laxmi"            account exists        and expected result("res") is Invalid. So the test result should be FAILED!
#    3) "laksh@yahoo.com - Lakshmi"            account exists        and expected result("res") is Invalid. So the test result should be FAILED!
#    4) "laks@yahoo.com - xyz"                 account doesn't exist and expected result("res") is Invalid. So the test result should be PASSED!
#    5) "abc123@gmail.com - test@123"          account exists        and expected result("res") is Valid.   So the test result should be PASSED!