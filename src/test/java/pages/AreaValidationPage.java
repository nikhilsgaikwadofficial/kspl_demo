
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class AreaValidationPage extends BasePage {


        WebDriver driver;

        public AreaValidationPage(WebDriver driver) {
            super(driver);
            js = (JavascriptExecutor) driver;
            this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        }


        By master = By.xpath("(//i[@class='fa fa-database '])");
        By area=By.xpath("(//i[@class='fa fa-map-marked-alt '])");
        By add=By.xpath("(//i[@class='fa fa-edit '])[1]");
        By state=By.xpath("//select[@id='states']");
        By branch=By.xpath("//input[@name='name']");
        By address=By.xpath("//input[@name='address']");
        By latitudeField=By.xpath("//input[@name='lat']");
        By longitudeField=By.xpath("//input[@name='long']");
        By workType=By.xpath("//select[@id='work_type']");
        By startTime=By.xpath("//input[@name='start_time']");
        By endTime=By.xpath("//input[@name='end_time']");
        By checkout=By.xpath("//select[@id='enable_force_checkout']");
        By allowLate=By.xpath("//select[@name='allow_late']");
        By allowOt=By.xpath("//select[@name='allow_ot']");
        By radius=By.xpath("//select[@id='enable_radius']");
        By saveButton=By.xpath("//button[@id='saveLocationData']");
        By errorMessages = By.xpath("//div[contains(@class,'error')]");


        public void clickSave() {
            driver.findElement(saveButton).click();
        }

        public String getDefaultDropdownText(By locator) {
            return new Select(driver.findElement(locator)).getFirstSelectedOption().getText();
        }

        public boolean isFieldDisplayed(By locator) {
            return driver.findElement(locator).isDisplayed();
        }

        public int getErrorCount() {
            return driver.findElements(errorMessages).size();
        }

        public void enterLatitude(String value) {
            driver.findElement(latitudeField).sendKeys(value);
        }

        public void enterLongitude(String value) {
            driver.findElement(longitudeField).sendKeys(value);
        }

        public String getLatitudeValue() {
            return driver.findElement(latitudeField).getAttribute("value");
        }

        public String getLongitudeValue() {
            return driver.findElement(longitudeField).getAttribute("value");
        }
    }




