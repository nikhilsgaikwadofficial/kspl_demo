package test.leave_balance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.leave_balance.LeaveBalancePage;
import test.BaseTest;

public class InvalidEmpIdTest extends BaseTest {

    @Test
    public void verifyInvalidEmpId() {
        LeaveBalancePage lbp = new LeaveBalancePage(driver);
        lbp.clickLeaveMenu();
        lbp.setAddLBal();
        
        lbp.setEmpId("9999"); // Non-existent ID
        lbp.setPaidLeave("10");
        lbp.setCasualLeave("5");
        lbp.setSickLeave("5");
        lbp.selectYears("2026");
        lbp.setSubmit();

        // Check for field error text near Emp ID
        try {
            WebElement error = driver.findElement(By.xpath("//input[@name='user_id']/following-sibling::*[contains(@class,'error')]"));
            String errorText = error.getText();
            Assert.assertFalse(errorText.isEmpty(), "Error message should be shown for invalid Employee ID");
            System.out.println("Invalid Emp ID error: " + errorText);
        } catch (Exception e) {
            // If no inline error, check success message contains 'not found' or similar if applicable
            // For now, failure to find error element is considered a test failure
            Assert.fail("No error message found for invalid Employee ID");
        }
    }
}
