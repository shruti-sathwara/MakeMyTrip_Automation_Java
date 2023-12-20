package CommonUtilities;

import Base.TestBase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.google.common.base.Predicate;
//import com.paulhammant.ngwebdriver.NgWebDriver;


public class TestUtil extends TestBase {
	
	public TestUtil() throws IOException {
		super();
		
		// TODO Auto-generated constructor stub
	}
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 10;
	
	public static String TESTDATA_SHEET_PATH = "C:\\WorkSpace\\MakeMyTrip\\src\\main\\java\\TestData\\MakeMyTripExcel.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor jsDriver;
	public static WebDriverWait wait;
	static String futuredate;
	


	

	

	public static Object[][] getTestData(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	
	public static boolean PageloadJS() {
		if (jsDriver.executeScript("return document.readyState").toString().equals("complete")){
			   System.out.println("Page has loaded");
			   return true;
		}
		else {
			return false;
			
		}
	
	
	}
	
	public static String GetFutureDate() {
		
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
		 Calendar calendar = Calendar.getInstance();
		    
		 	
		    Date today = calendar.getTime();
		    System.out.println("today:    " + today);
		    calendar.add(Calendar.DAY_OF_YEAR, 1);
		    
		   Date tomorrow = calendar.getTime();
		    System.out.println("tomorrow: " + tomorrow);
		    String futuredate= DateFor.format(tomorrow);
			System.out.println(futuredate);
		    
		    //String futuredate=tomorrow.toString();

		    
		    return futuredate;
		  	}
	
		public static String GetPastDate() {
		
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
		 Calendar calendar = Calendar.getInstance();
		    
		 	
		    Date today = calendar.getTime();
		    System.out.println("today:    " + today);
		    calendar.add(Calendar.DAY_OF_YEAR, -3);
		    
		   Date pastdate = calendar.getTime();
		    System.out.println("pastdate: " + pastdate);
		    String PastDate= DateFor.format(pastdate);
			System.out.println(PastDate);
		    
		    //String futuredate=tomorrow.toString();

		    
		    return PastDate;
		  	}
	

		
	//method to get ngWebDriver
//	public static NgWebDriver getNGDriver() {
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		return(new NgWebDriver(js));
//	}

	//ngwebdriver=new NgWebDriver(jsDriver);
		//ngwebdriver.waitForAngularRequestsToFinish();
//	public static void waitForAngularRequestsToFinish() {
//		try{
//			getNGDriver().waitForAngularRequestsToFinish();
//		}
//		catch(Exception e) {
//			AssertJUnit.fail("Error while waiting for angular request to finish:" +e.getMessage());
//		}
//	}
	
	
	
}
