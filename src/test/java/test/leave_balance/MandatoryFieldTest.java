package test.leave_balance;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.leave_balance.LeaveBalancePage;
import test.BaseTest;

public class MandatoryFieldTest extends BaseTest {

    @Test
    public void verifyMandatoryFields() {
        LeaveBalancePage lbp = new LeaveBalancePage(driver);
        lbp.clickLeaveMenu();
        lbp.setAddLBal();
        lbp.setSubmit();

        // 1. Employee ID
        String empIdMsg = lbp.getFieldError(lbp.empId);
        if (empIdMsg.isEmpty()) empIdMsg = lbp.getValidationMessage(lbp.empId);
        Assert.assertEquals(empIdMsg, "Employee ID is required.", "Employee ID msg mismatch!");

        // 2. Paid Leave
        String paidLeaveMsg = lbp.getFieldError(lbp.paidLeave);
        if (paidLeaveMsg.isEmpty()) paidLeaveMsg = lbp.getValidationMessage(lbp.paidLeave);
        Assert.assertEquals(paidLeaveMsg, "Paid Leave is required.", "Paid Leave msg mismatch!");

        // 3. Casual Leave
        String casualLeaveMsg = lbp.getFieldError(lbp.casualLeave);
        if (casualLeaveMsg.isEmpty()) casualLeaveMsg = lbp.getValidationMessage(lbp.casualLeave);
        Assert.assertEquals(casualLeaveMsg, "Casual Leave is required.", "Casual Leave msg mismatch!");

        // 4. Sick Leave
        String sickLeaveMsg = lbp.getFieldError(lbp.sickLeave);
        if (sickLeaveMsg.isEmpty()) sickLeaveMsg = lbp.getValidationMessage(lbp.sickLeave);
        Assert.assertEquals(sickLeaveMsg, "Sick Leave is required.", "Sick Leave msg mismatch!");

        System.out.println("Mandatory field assertions completed.");
    }
}
