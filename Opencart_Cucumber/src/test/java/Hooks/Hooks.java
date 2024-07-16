package Hooks;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	WebDriver driver;
	
	@Before
	public void setup() throws IOException {
		driver = BaseClass.initializeBrowser();
	}
	
	 @After
	 public void tearDown(Scenario scenario) { 		
	       driver.quit();
	 }
	    

	  @AfterStep
	  public void addScreenshot(Scenario scenario) {
	        
	  // this is for cucumber junit report
	  if(scenario.isFailed()) {   	
	      TakesScreenshot ts=(TakesScreenshot) driver;
	      byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
	      scenario.attach(screenshot, "image/png",scenario.getName());
	        	            
	    }
	      
	  }
}
