package test.leave_balance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.leave_balance.LeaveBalancePage;
import test.BaseTest;

public class NegativeLeaveCountTest extends BaseTest {

    @Test
    public void verifyNegativeLeaveCount() {
        LeaveBalancePage lbp = new LeaveBalancePage(driver);
        lbp.clickLeaveMenu();
        lbp.setAddLBal();
        
        lbp.setEmpId("101");
        lbp.setPaidLeave("-5");
        lbp.setCasualLeave("10");
        lbp.setSickLeave("5");
        lbp.selectYears("2026");
        lbp.setSubmit();

        try {
            WebElement error = driver.findElement(By.xpath("//input[@name='paid_leaves']/following-sibling::*[contains(@class,'error')]"));
            String errorText = error.getText();
            Assert.assertFalse(errorText.isEmpty(), "Validation error should appear for negative leave count");
            System.out.println("Negative leave count error: " + errorText);
        } catch (Exception e) {
            Assert.fail("No validation error found for negative leave count");
        }
    }
}
