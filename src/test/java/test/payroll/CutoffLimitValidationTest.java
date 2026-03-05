package test.payroll;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.payroll.PayrollPage;
import test.BaseTest;

public class CutoffLimitValidationTest extends BaseTest {

    @Test
    public void testCutoffLimitValidation() {
        PayrollPage payrollPage = new PayrollPage(driver);
        payrollPage.navigateToPayrollSettings();
        
        payrollPage.setPfCutoffLimit(""); 
        payrollPage.clickUpdate();
        
        String errorMsg = payrollPage.getFieldError(payrollPage.pfCutoffLimit);
        if (errorMsg.isEmpty()) errorMsg = payrollPage.getValidationMessage(payrollPage.pfCutoffLimit);
        
        Assert.assertEquals(errorMsg, "PF Cutoff Limit is required.", "Validation message mismatch!");
    }
}
