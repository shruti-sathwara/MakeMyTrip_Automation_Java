package Test;

import Base.TestBase;
import CommonUtilities.TestUtil;
import Pages.HomeScreenPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class HomeScreenTest extends TestBase {

    HomeScreenPage homeScreenPage;
    TestUtil testutil = new TestUtil();
    private SoftAssert softassert;

    public HomeScreenTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver = Initialization();
        homeScreenPage = new HomeScreenPage(driver);
        softassert = new SoftAssert();
        testutil = new TestUtil();

    }

    @DataProvider(name = "GetSearchData")
    public Object[][] getTransportMasterDataAdd() throws InvalidFormatException {
        Object data[][] = TestUtil.getTestData("SearchDataHomePage");
        return data;
    }

    @Test(dataProvider = "GetSearchData", priority = 1)
    public void VerifyFlightBookingData(String fromCityData, String toCityData, String DepartureDateData, String ReturnDateData) throws Exception {

        driver.get(prop.getProperty("url"));
        Thread.sleep(2000);
        homeScreenPage.closeDialogBox();
        homeScreenPage.selectRoundTripRadioButton();
        homeScreenPage.enterFromCity(fromCityData);
        homeScreenPage.enterToCity(toCityData);
        homeScreenPage.selectFirstOptionToCity();
        homeScreenPage.enterDepartureDate(DepartureDateData);
        homeScreenPage.enterReturnDate(ReturnDateData);

        String AlertMessage = homeScreenPage.GetDisappearingAlertText();
        Reporter.log(AlertMessage);
        softassert.assertEquals(AlertMessage.replaceAll("×", "").replaceAll("\\s+", ""), "You are booking for more than 30 days");

        Thread.sleep(5000);
    }

    @DataProvider(name = "getPassengerDetails")
    public Object[][] VerifyPassengerDataAdd() throws InvalidFormatException {
        Object data[][] = TestUtil.getTestData("EnterPassenger");
        return data;
    }

    @Test(dataProvider = "getPassengerDetails", priority = 2)
    public void verifyPassengerLimitValidation(String fromCityData, String toCityData, String DepartureDateData, String ReturnDateData, String Adult, String Child) throws Exception {

        driver.get(prop.getProperty("url"));
        Thread.sleep(2000);
        homeScreenPage.closeDialogBox();
        homeScreenPage.selectRoundTripRadioButton();
        homeScreenPage.enterFromCity(fromCityData);
        homeScreenPage.enterToCity(toCityData);
        homeScreenPage.selectFirstOptionToCity();
        homeScreenPage.enterDepartureDate(DepartureDateData);
        homeScreenPage.enterReturnDate(ReturnDateData);
        homeScreenPage.enterAdult(Adult);
        homeScreenPage.enterChild(Child);

    }

    @DataProvider(name = "getPassengerDetailswithSearch")
    public Object[][] getPassengerDataSearch() throws InvalidFormatException {
        Object data[][] = TestUtil.getTestData("SearchTraveller");
        return data;
    }

    @Test(dataProvider = "getPassengerDetailswithSearch", priority = 3)
    public void verifyPassengerDataSearch(String fromCityData, String toCityData, String DepartureDateData, String ReturnDateData, String Adult, String Child) throws Exception {

        driver.get(prop.getProperty("url"));
        Thread.sleep(2000);
        homeScreenPage.closeDialogBox();
        homeScreenPage.selectRoundTripRadioButton();
        homeScreenPage.enterFromCity(fromCityData);
        homeScreenPage.enterToCity(toCityData);
        homeScreenPage.selectFirstOptionToCity();
        homeScreenPage.enterDepartureDate(DepartureDateData);
        homeScreenPage.enterReturnDate(ReturnDateData);
        homeScreenPage.enterAdult(Adult);
        homeScreenPage.enterChild(Child);
        homeScreenPage.clickOnApplyButton();
        homeScreenPage.clickOnSearchButton();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}