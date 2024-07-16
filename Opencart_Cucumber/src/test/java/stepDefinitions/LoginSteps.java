package stepDefinitions;


import java.util.HashMap;
import java.util.List;

import org.junit.Assert;


import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
	
	HomePage hp;
	LoginPage lp;
	MyAccountPage maccp;
	static List<HashMap<String, String>> testData; //Data driven
	int row_index;
	
	
	@Given("the user navigates to the login page")
	public void navigateLoginPage() {
		
		BaseClass.getLogger().info("Click my account-->Click on Login.. ");
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
	}

	@When("the user enters email as {string} and password as {string}")
	public void setCredentials(String email, String password) {
		BaseClass.getLogger().info("Entering email and password.. ");
		lp = new LoginPage(BaseClass.getDriver());
		lp.setEmailAddress(email);
		lp.setPassword(password);
	    
	}

	@When("the user clicks on the Login button")
	public void clickLoginButton() {
	   lp.clickLoginButton();
	   BaseClass.getLogger().info("Clicked on login button.. ");
	}

	@Then("the user should be redirected to MyAccountPage")
	public void validateRedirectionToMyAccountpage() {
		maccp = new MyAccountPage(BaseClass.getDriver());
		boolean targetPage = maccp.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true);
	}
	
	/* -----------------DDT with Excel-------------------- */
	@When("the user enters email and password as present in excel sheet {string}")
	public void readAndSetCredentialsFromExcel(String row_number) {
		row_index = Integer.parseInt(row_number)-1;
		
		try {
			if(row_index == 0) //if the first time read from the excel sheet and get all the data
			{
				testData=DataReader.getExcelTestData(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");
				
			}
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
		
		String email = testData.get(row_index).get("username");
		String passwd = testData.get(row_index).get("password");
		BaseClass.getLogger().info("Entering email and password.. ");
		lp=new LoginPage(BaseClass.getDriver());
        lp.setEmailAddress(email);
        lp.setPassword(passwd);
	}
	
	@Then("the user should be redirected to MyAccountPage only with valid credentials")
	public void validateLoginStatus() {
		
		String exp_result = testData.get(row_index).get("res");
		maccp = new MyAccountPage(BaseClass.getDriver());
		
		try {
			boolean isLoginSuccess = maccp.isMyAccountPageExists();
			
			/* Valid data - login success - pass
			 * 				login failed - fail
			 * Invalid data - login success - fail
			 * 				  login failed - pass
			 * 
			*/
			if(exp_result.equalsIgnoreCase("Valid")) {
				if(isLoginSuccess)
				{
					maccp.clickLogout();
					BaseClass.getLogger().info("Logging out ...");
					Assert.assertTrue(true); //test case passed
				}
				else
				{
					Assert.assertTrue(false); //test case failed if not logged in by valid data
				}
			}
			else 
			{
				if(isLoginSuccess)
				{
					maccp.clickLogout();
					BaseClass.getLogger().info("Logging out ...");
					Assert.assertTrue(false); //test case failed as it shouldn't login with invalid credentials
				}
				else
				{
					Assert.assertTrue(true); //test case passed
				}
				
			}
		}catch(Exception e) {
			Assert.fail();
		}
		
	}

}
