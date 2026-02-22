
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class AddHolidaysPage extends BasePage{
        public AddHolidaysPage(WebDriver driver){
            super(driver);
            js = (JavascriptExecutor) driver;
            this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        By master = By.xpath("(//i[@class='fa fa-database '])");
        By publicHoliday= By.xpath("(//i[@class='fa fa-calendar '])[1]");
        By addHoli=By.xpath("(//i[@class='fa fa-edit '])[3]");
        By date= By.xpath("//input[@id='max']");
        By selectDate=By.xpath("(//td[@class='today day'])");
        By holidayname=By.xpath("//input[@name='name']");
        By state=By.xpath("//select[@id='state_id']");
        By submit=By.xpath("//button[@id='btn-admin-member-submit']");


        public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }

        public void clickPublicHoliday() {
            WebElement holiday = wait.until(ExpectedConditions.visibilityOfElementLocated(publicHoliday));
            js.executeScript("arguments[0].click();", holiday);
        }
        public void clickAddHoli() {
            WebElement addd = wait.until(ExpectedConditions.visibilityOfElementLocated(addHoli));
            js.executeScript("arguments[0].click();", addd);
        }



        public void setDate(String date1){
            WebElement dat= wait.until(ExpectedConditions.visibilityOfElementLocated(date));
            dat.click(); dat.sendKeys(date1);
        }
       /* public void selectDate(String setDate){
            new Select(driver.findElement(selectDate)).selectByVisibleText(setDate);
        }*/
        public void setHolidayName(String name1){
            WebElement namee=wait.until(ExpectedConditions.visibilityOfElementLocated(holidayname)) ;
            namee.click();
            namee.sendKeys(name1);
        }

        public void selectState(String statee){
            new Select(driver.findElement(state)).selectByVisibleText(statee);
        }
        public void clickSubmit(){
            WebElement add=  wait.until(ExpectedConditions.elementToBeClickable(submit));
            js.executeScript("arguments[0].click();",add);
        }
    }


