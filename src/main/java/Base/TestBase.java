package Base;

import CommonUtilities.TestUtil;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static JavascriptExecutor jsDriver;
	public static WebDriverWait wait;
   
	
	public TestBase() throws IOException {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\WorkSpace\\MakeMyTrip\\src\\main\\java\\Config\\config.properties");
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

}

	
	
	public static  WebDriver Initialization(){
		//DesiredCapabilities capabilities = new DesiredCapabilities();
		
		
		String Browsername=prop.getProperty("Browser");
		if (Browsername.equals("chrome"));
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vca001\\eclipse-workspace\\AceERP_Testautomation\\src\\main\\Drivers\\chromedriver.exe");
		//driver=new ChromeDriver();
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		
		//options.addArguments("--disable-web-security");
		//options.addArguments("--no-proxy-server");
		options.addArguments("disable-popup-blocking");
		options.addArguments("--remote-allow-origins=*");


		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking","enable-automation"));
		
//		WebDriver driver=new ChromeDriver(options);
		WebDriver driver=new ChromeDriver(options);
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("url"));
		
		//ngwebdriver.waitForAngularRequestsToFinish();
		
		return driver;

		


	}
	
	
	
	



	
	
	
	
	
	
	
	
	
	
}
