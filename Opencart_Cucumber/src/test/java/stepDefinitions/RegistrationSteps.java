package stepDefinitions;

import java.util.Map;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class RegistrationSteps {
	HomePage hp;
	AccountRegistrationPage regp;
	
	@Given("the user navigated to Register Account Page")
	public void navigateToRegisterAccountPage() {
		BaseClass.getLogger().info("Click on My Account -> Register ....");
	    hp = new HomePage(BaseClass.getDriver());
	    hp.clickMyAccount();
	    hp.clickRegister();
	}

	@When("the user enters details into the below fields")
	public void enterUserDetails(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		Map<String , String> userTestData = dataTable.asMap(String.class,String.class);
		
		regp = new AccountRegistrationPage(BaseClass.getDriver());
		regp.setFirstName(userTestData.get("firstName"));
		regp.setLastName(userTestData.get("lastName"));
		regp.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		regp.setTelephone(userTestData.get("telephone"));
		regp.setPassword(userTestData.get("password"));
		regp.setConfirmPassword(userTestData.get("password"));
	    
	}

	@When("the user selects Privacy Policy")
	public void setPprivacyPolicy() {
	    regp.setPrivaryPolicy();
	}

	@When("the user clicks on Continue button")
	public void clickContinueButton() {
		regp.clickContinue();
	}

	@Then("the user account should get created successfully")
	public void confirmRegisteration() {
	    String actual_msg = regp.getConfirmationMessage();
	    Assert.assertEquals(actual_msg, "Your Account Has Been Created!");
	}
}
