
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class LeaveBalancePage extends BasePage {
        WebDriverWait wait;
        public LeaveBalancePage(WebDriver driver) {
            super(driver);
            this.wait=new WebDriverWait(driver,Duration.ofSeconds(5));


        }


        By leave = By.xpath("(//p[normalize-space()='Leave Management'])");
        By addLBal = By.xpath("//p[normalize-space()='Add Leave Balance']");
        By empId = By.xpath("//input[@name='user_id']");
        By paidLeave = By.xpath("//input[@name='paid_leaves']");
        By casualLeave = By.xpath("//input[@name='casual_leaves']");
        By sickLeave = By.xpath("//input[@name='sick_leaves']");
        By selectYear = By.xpath("//select[@name='year']");
        By submit = By.xpath("//button[@id='btn-admin-member-submit']");



        public void clickLeaveMenu() {
            WebElement leaveMenu = wait.until(ExpectedConditions.elementToBeClickable(leave));
            js.executeScript("arguments[0].click();", leaveMenu);
        }


        public void setAddLBal() {
            WebElement addLeave = wait.until(ExpectedConditions.elementToBeClickable(addLBal));
            js.executeScript("arguments[0].click();", addLeave);

        }


        public void setEmpId(String eId) {
            WebElement empID=driver.findElement(empId);
            empID.click(); empID.sendKeys(eId);
        }

        public void setPaidLeave(String pl) {
            WebElement paidleave=  driver.findElement(paidLeave);
            paidleave.click(); paidleave.sendKeys(pl);
        }

        public void setCasualLeave(String cL) {
            WebElement casualleave=  driver.findElement(casualLeave);
            casualleave.click(); casualleave.sendKeys(cL);
        }

        public void setSickLeave(String sL) {
            WebElement sickleave  = driver.findElement(sickLeave);
            sickleave.click(); sickleave.sendKeys(sL);
        }

        public void selectYears(String year) {
            Select yearDropdown = new Select(driver.findElement(selectYear));
            yearDropdown.selectByVisibleText(year);
        }

        public void setSubmit() {
            WebElement sub=  wait.until(ExpectedConditions.elementToBeClickable(submit));
            js.executeScript("arguments[0].click();",sub);
            //  driver.findElement(submit).click();

        }
    }


