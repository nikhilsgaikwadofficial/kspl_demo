package test.leave_balance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.leave_balance.LeaveBalancePage;
import test.BaseTest;

public class DuplicateYearTest extends BaseTest {

    @Test
    public void verifyDuplicateYearEntry() {
        LeaveBalancePage lbp = new LeaveBalancePage(driver);
        lbp.clickLeaveMenu();
        lbp.setAddLBal();
        
        lbp.setEmpId("101");
        lbp.setPaidLeave("15");
        lbp.setCasualLeave("10");
        lbp.setSickLeave("5");
        lbp.selectYears("2026"); // Assuming this combination already exists
        lbp.setSubmit();

        try {
            // Duplicate entries usually show a general alert or specific warning
            WebElement alert = driver.findElement(By.xpath("//*[contains(text(),'already') or contains(text(),'Duplicate')]"));
            String errorText = alert.getText();
            Assert.assertFalse(errorText.isEmpty(), "System should notify about duplicate entry");
            System.out.println("Duplicate entry error: " + errorText);
        } catch (Exception e) {
            // Fallback check: successful addition message should NOT be present
            Assert.fail("No notification found for duplicate leave balance entry");
        }
    }
}
