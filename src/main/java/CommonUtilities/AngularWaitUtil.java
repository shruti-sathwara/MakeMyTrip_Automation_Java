package CommonUtilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AngularWaitUtil {
	public AngularWaitUtil() {
		
	}
	
	
	private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor js;
    
    //Get the driver 
    public static void setDriver (WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, null);
        js = (JavascriptExecutor) jsWaitDriver;
    }
	
	public static void waitForAngularLoad() {
		String angularReadynessScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadynessScript );

    }
	
	 public static void waitUntilAngularReady() {
		 try {
		        Object angular5Check = js.executeScript(
		        "return getAllAngularRootElements()[0].attributes['ng-version']");
		        if (angular5Check != null) {
		        Boolean angularPageLoaded = (Boolean) js.executeScript(
		        "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
		        if (!angularPageLoaded) {
		        sleep(10);
		        waitForAngularLoad();
		        sleep(10);
		        }
		        }
		        } catch (Exception e) {
		        }

	    }
	 
	 
	 private static void angularLoads(String angularReadynessScript ) {
		 try {
	            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
	                .executeScript(angularReadynessScript ).toString());
	 
	            boolean angularReady = Boolean.valueOf(js.executeScript(angularReadynessScript ).toString());
	 
	            if (!angularReady) {
	                jsWait.until(angularLoad);
	            }
	        } catch (Exception e) {
	        }

	    }
	 private static void sleep(long milis) {
	        try {
	            Thread.sleep(milis);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	

}