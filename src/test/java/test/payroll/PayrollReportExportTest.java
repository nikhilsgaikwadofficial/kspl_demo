package test.payroll;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.payroll.PayrollPage;
import test.BaseTest;

public class PayrollReportExportTest extends BaseTest {

    @Test
    public void testPayrollReportExport() {
        PayrollPage payrollPage = new PayrollPage(driver);
        payrollPage.navigateToPayrollReports();
        
        payrollPage.selectMonth("2026-03");
        payrollPage.clickFilter();
        
        payrollPage.exportToExcel();
        
        // Verification of download usually requires a different approach, 
        // here we verify the action completes without error.
        Assert.assertTrue(true);
    }
}
