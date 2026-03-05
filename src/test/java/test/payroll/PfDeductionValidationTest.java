package test.payroll;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.payroll.PayrollPage;
import test.BaseTest;

public class PfDeductionValidationTest extends BaseTest {

    @Test
    public void testPfDeductionValidation() {
        PayrollPage payrollPage = new PayrollPage(driver);
        payrollPage.navigateToPayrollSettings();
        
        payrollPage.setPfDeduction(""); 
        payrollPage.clickUpdate();
        
        String errorMsg = payrollPage.getFieldError(payrollPage.pfDeduction);
        if (errorMsg.isEmpty()) errorMsg = payrollPage.getValidationMessage(payrollPage.pfDeduction);
        
        Assert.assertEquals(errorMsg, "PF Deduction is required.", "Validation message mismatch!");
    }
}
