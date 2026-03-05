package test.payroll;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.payroll.PayrollPage;
import test.BaseTest;

public class UpdateSettingsSuccessTest extends BaseTest {

    @Test
    public void testUpdateSettingsSuccess() {
        PayrollPage payrollPage = new PayrollPage(driver);
        payrollPage.navigateToPayrollSettings();
        
        payrollPage.enablePf();
        payrollPage.setPfCutoffLimit("15000");
        payrollPage.setPfDeduction("12");
        payrollPage.clickUpdate();
        
        String successMsg = payrollPage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("updated"), 
                "Success message mismatch! Actual: " + successMsg);
    }
}
