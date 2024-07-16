package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	static WebDriver driver;
	static Properties p;
	static Logger logger;
	
	public static WebDriver initializeBrowser() throws IOException 
	{
		p = getProperties();
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			//select os
			switch(getProperties().getProperty("os"))
			{
				case "windows": cap.setPlatform(Platform.WIN11);break;
				case "mac": cap.setPlatform(Platform.MAC);break;
				case "linux": cap.setPlatform(Platform.LINUX);break;
				default: System.out.println("No matching OS found!"); driver = null;
			}
			//select browser
			switch(p.getProperty("browser"))
			{
				case "chrome": cap.setBrowserName("chrome");break;
				case "edge": cap.setBrowserName("MicrosoftEdge");break;
				case "firefox": cap.setBrowserName("firefox");break;
				default: System.out.println("No matching Browser found!"); driver = null;
			}
			driver = new RemoteWebDriver(new URL("http://192.168.1.11:4444/wd/hub"),cap);
		}
		else
		{  //execution_env = local
			switch(p.getProperty("browser"))
			{
				case "chrome": driver = new ChromeDriver();break;
				case "edge": driver = new EdgeDriver(); break;			 
				default: System.out.println("Wrong browser options");driver = null;			
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
		
		return driver;
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Properties getProperties() throws IOException {
		
		FileReader file = new FileReader("./src//test//resources/config.properties");
		p = new Properties();
		p.load(file);
		
		return p;
		
	}
	
	public static Logger getLogger() {
		logger = LogManager.getLogger();	
		return logger;
		
	}
	
	public static String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	
	public static String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
		
	public static String randomAlphaNumeric()
	{
	String str=RandomStringUtils.randomAlphabetic(5);
	 String num=RandomStringUtils.randomNumeric(10);
	return str+num;
	}
}
