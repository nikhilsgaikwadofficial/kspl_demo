package pages.holiday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class HolidayPage extends BasePage {
    
    public HolidayPage(WebDriver driver) {
        super(driver);
    }

    By master = By.xpath("(//i[@class='fa fa-database '])");
    By publicHoliday = By.xpath("(//i[@class='fa fa-calendar '])[1]");
    By addHoli = By.xpath("(//i[@class='fa fa-edit '])[3]");
    public By dateInput = By.xpath("//input[@id='max']");
    public By holidayNameInput = By.xpath("//input[@name='name']");
    public By stateSelect = By.xpath("//select[@id='state_id']");
    public By submitButton = By.xpath("//button[@id='btn-admin-member-submit']");

    public void navigateToAddHoliday() {
        WebElement mast = wait.until(ExpectedConditions.elementToBeClickable(master));
        js.executeScript("arguments[0].click();", mast);
        
        WebElement holiday = wait.until(ExpectedConditions.elementToBeClickable(publicHoliday));
        js.executeScript("arguments[0].click();", holiday);
        
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(addHoli));
        js.executeScript("arguments[0].click();", add);
    }

    public void setDate(String date) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        el.click();
        el.clear();
        el.sendKeys(date);
    }

    public void setHolidayName(String name) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(holidayNameInput));
        el.click();
        el.clear();
        el.sendKeys(name);
    }

    public void selectState(String stateName) {
        Select select = new Select(driver.findElement(stateSelect));
        select.selectByVisibleText(stateName);
    }

    public void clickSubmit() {
        WebElement sub = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        js.executeScript("arguments[0].click();", sub);
    }

    public String getFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field.findElement(By.xpath(".."));
            WebElement error = parent.findElement(By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback')]"));
            if (error.isDisplayed()) return error.getText();
        } catch (Exception e) {}
        return "";
    }

    public String getValidationMessage(By locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
    }

    public String getSuccessMessageText() {
        By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return msg.getText();
    }
}
