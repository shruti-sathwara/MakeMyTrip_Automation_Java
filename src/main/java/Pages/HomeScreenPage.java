package Pages;

import Base.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.time.Month;


public class HomeScreenPage extends TestBase {

    //xpath
    @FindBy(id = "second-img")
    WebElement dialogBox;
    @FindBy(css = "#exampleId")
    WebElement locator;

    @FindBy(id = "webklipper-publisher-widget-container-notification-close-div")
    WebElement closeButton;

    @FindBy(xpath = "//li[contains(text(),'Round Trip')]")
    WebElement roundTripRadioBtn;

    @FindBy(xpath = "//p[@id='range_error']")
    WebElement DisappearingAlertText;

    @FindBy(xpath = "//input[@id='fromCity']")
    WebElement fromCityTxt;

    @FindBy(xpath = "//input[@id='toCity']")
    WebElement toCityTxt;

    @FindBy(xpath = "//ul[@role='listbox']/li[1]")
    WebElement firstOption;

    @FindBy(xpath = "//input[@id='departure']")
    WebElement departureTxt;

    @FindBy(xpath = "//input[@id='return']")
    WebElement returnTxt;

    @FindBy(xpath = "//p[@class='makeFlex vrtlCenter ']//a")
    WebElement searchBtn;

    @FindBy(xpath = "//div[@class='flt_fsw_inputBox flightTravllers inactiveWidget']")
    WebElement selectTravelList;
    @FindBy(xpath = "(//ul[@class='guestCounter font12 darkText gbCounter'])[1]")
    WebElement adultList;

    public HomeScreenPage(WebDriver driver) throws IOException {

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // Page Methods
    public void closeDialogBox() {
        try {
            Thread.sleep(5000);
            try {
                WebElement iframeElement = driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
                Thread.sleep(3000);
                driver.switchTo().frame(iframeElement);
                Thread.sleep(5000);
                closeButton.click();
            } finally {
                // Switch back to the default content outside the iframe
                driver.switchTo().defaultContent();

            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectRoundTripRadioButton() throws InterruptedException {
        WebElement roundTripButton = driver.findElement(By.xpath("//li[@data-cy='roundTrip']"));
        roundTripButton.click();
    }

    public void enterFromCity(String fromCity) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(fromCityTxt));
        fromCityTxt.sendKeys(fromCity);
    }

    public void enterToCity(String toCity) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(toCityTxt));
        toCityTxt.sendKeys(toCity);
    }

    public void selectFirstOptionToCity() throws InterruptedException {
        Thread.sleep(2000);
        firstOption.click();
    }

    //
    public void enterDepartureDate(String departureDate) throws InterruptedException {

        selectDate(driver, "01/01/2024", "departure");

    }

    public void enterReturnDate(String returnDate) throws InterruptedException {

        Thread.sleep(2000);
        selectDate(driver, "05/04/2024", "return");
    }

    public void enterAdult(String adult) throws InterruptedException {

        Thread.sleep(2000);
        WebElement selectTravelList1 = driver.findElement(By.xpath("//div[@class='flt_fsw_inputBox flightTravllers inactiveWidget ']"));
        selectTravelList1.click();

        System.out.println("adult==>" + adult);
        WebElement adultListSelect = driver.findElement(By.xpath("//li[@data-cy='adults-" + adult + "']"));
        System.out.println("adt==>" + adultListSelect.getText());
        adultListSelect.click();
    }

    public void enterChild(String child) throws InterruptedException {
        Thread.sleep(2000);
        WebElement childListSelect = driver.findElement(By.xpath("//li[@data-cy='children-" + child + "']"));
        childListSelect.click();
    }

    public void clickOnApplyButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement clickApply = driver.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']"));
        clickApply.click();
    }

    public void clickOnSearchButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement clickApply = driver.findElement(By.xpath("//p[@data-cy='submit']//a"));
        clickApply.click();
    }

    private static void selectDate(WebDriver driver, String dateToSelect, String departure) throws InterruptedException {
        Thread.sleep(2000);
        WebElement dateInput = driver.findElement(By.xpath("(//span[@class='lbl_input appendBottom10'])[3]"));
        dateInput.click();

        WebElement datePicker = driver.findElement(By.xpath("(//div[@class='DayPicker-Month'])[1]"));
//
        // Split the date into day, month, and year
        String[] dateParts = dateToSelect.split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

//        // Select the year
        WebElement yearDropdown = datePicker.findElement(By.xpath("//div[@class='DayPicker-Caption']"));

//        System.out.println("yearDropdown==>" + yearDropdown.getText());

        String inputString = yearDropdown.getText();
        String monthSelect = inputString.replaceAll("[^a-zA-Z]", "");
        String yearSelect = inputString.replaceAll("[^0-9]", "");

        if (!yearSelect.equals(year)) {
            WebElement nextButton = datePicker.findElement(By.xpath("(//span[@role='button'])[2]"));
            nextButton.click();
        }

        if (departure.equals("departure")) {
            forDeparture(day, datePicker);
        } else {
            forReturn(day, datePicker, monthSelect, month, year);
        }

    }

    private static String removeLeadingZeros(String day) {
        // Convert the string to an integer and back to a string
        return Integer.toString(Integer.parseInt(day));
    }

    private static void forDeparture(String day, WebElement datePicker) {
        //date
        String formattedDate1 = removeLeadingZeros(day);
        // Select the day
        WebElement dayButton = datePicker.findElement(By.xpath("//*[@class='dateInnerCell']//p[text()='" + formattedDate1 + "']"));
        dayButton.click();
    }


    private static void forReturn(String day, WebElement datePicker, String monthSelect, String month, String year) {
        //month
        WebElement monthyearDropdown = datePicker.findElement(By.xpath("(//div[@class='DayPicker-Caption'])[2]"));
        String monthyearDropdownTxt = monthyearDropdown.getText();

        String monthTxt = monthyearDropdownTxt.replaceAll("[^a-zA-Z]", "");
        String yearTxt = monthyearDropdownTxt.replaceAll("[^0-9]", "");

        int monthNumber = Integer.parseInt(month);
        if (monthNumber >= 1 && monthNumber <= 12) {

            // Get the Month enum corresponding to the month number
            Month monthNameList = Month.of(monthNumber);

            // Get the full month name
            String monthName = monthNameList.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault());

            do {
                WebElement nextButton = datePicker.findElement(By.xpath("(//span[@role='button'])[2]"));
                nextButton.click();
                nextButton.click();
            }
            while (monthTxt.equals(monthName));

            //date
            String formattedDate1 = removeLeadingZeros(day);
            // Select the day
            WebElement dayButton = datePicker.findElement(By.xpath("//*[@class='dateInnerCell']//p[text()='" + formattedDate1 + "']"));
            dayButton.click();

        } else {
        }
    }

    public String GetDisappearingAlertText() throws InterruptedException {
        Thread.sleep(2000);
        String AlertMessage = DisappearingAlertText.getText();
        System.out.println(AlertMessage);
        return AlertMessage;
    }


}




