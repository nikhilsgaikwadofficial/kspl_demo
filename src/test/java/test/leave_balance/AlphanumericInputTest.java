package test.leave_balance;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.leave_balance.LeaveBalancePage;
import test.BaseTest;

public class AlphanumericInputTest extends BaseTest {

    @Test
    public void verifyAlphanumericInput() {
        LeaveBalancePage lbp = new LeaveBalancePage(driver);
        lbp.clickLeaveMenu();
        lbp.setAddLBal();
        
        lbp.setEmpId("101");
        lbp.setPaidLeave("abc");
        lbp.setCasualLeave("10");
        lbp.setSickLeave("5");
        lbp.selectYears("2026");
        lbp.setSubmit();

        String validationMsg = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", driver.findElement(lbp.paidLeave));
        
        Assert.assertFalse(validationMsg.isEmpty(), "System should prevent non-numeric input for leave counts");
        System.out.println("Alphanumeric input validation: " + validationMsg);
    }
}
