package pages.leave_balance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class LeaveBalancePage extends BasePage {

    public LeaveBalancePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    By leave = By.xpath("(//p[normalize-space()='Leave Management'])");
    By addLBal = By.xpath("//p[normalize-space()='Add Leave Balance']");
    public By empId = By.xpath("//input[@name='user_id']");
    public By paidLeave = By.xpath("//input[@name='paid_leaves']");
    public By casualLeave = By.xpath("//input[@name='casual_leaves']");
    public By sickLeave = By.xpath("//input[@name='sick_leaves']");
    public By selectYear = By.xpath("//select[@name='year']");
    public By submit = By.xpath("//button[@id='btn-admin-member-submit']");

    public void clickLeaveMenu() {
        WebElement leaveMenu = wait.until(ExpectedConditions.elementToBeClickable(leave));
        js.executeScript("arguments[0].click();", leaveMenu);
    }

    public void setAddLBal() {
        WebElement addLeave = wait.until(ExpectedConditions.elementToBeClickable(addLBal));
        js.executeScript("arguments[0].click();", addLeave);
    }

    public void setEmpId(String eId) {
        WebElement empID = driver.findElement(empId);
        empID.click();
        empID.clear();
        empID.sendKeys(eId);
    }

    public void setPaidLeave(String pl) {
        WebElement paidleave = driver.findElement(paidLeave);
        paidleave.click();
        paidleave.clear();
        paidleave.sendKeys(pl);
    }

    public void setCasualLeave(String cL) {
        WebElement casualleave = driver.findElement(casualLeave);
        casualleave.click();
        casualleave.clear();
        casualleave.sendKeys(cL);
    }

    public void setSickLeave(String sL) {
        WebElement sickleave = driver.findElement(sickLeave);
        sickleave.click();
        sickleave.clear();
        sickleave.sendKeys(sL);
    }

    public void selectYears(String year) {
        Select yearDropdown = new Select(driver.findElement(selectYear));
        yearDropdown.selectByVisibleText(year);
    }

    public void setSubmit() {
        WebElement sub = wait.until(ExpectedConditions.elementToBeClickable(submit));
        js.executeScript("arguments[0].click();", sub);
    }

    public String getSuccessMessageText() {
        By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return msg.getText();
    }

    public String getFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            // Search for an error message in the parent container
            WebElement parent = field.findElement(By.xpath(".."));
            WebElement error = parent.findElement(By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'help-block')]"));
            if (error.isDisplayed()) {
                return error.getText();
            }
        } catch (Exception e) {
            // ignore if not found
        }
        return "";
    }

    public String getValidationMessage(By locator) {
        WebElement element = driver.findElement(locator);
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }
}
