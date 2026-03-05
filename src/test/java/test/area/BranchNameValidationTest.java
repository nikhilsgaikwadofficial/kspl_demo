package test.area;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.area.AreaPage;
import test.BaseTest;

public class BranchNameValidationTest extends BaseTest {

    @Test
    public void testBranchNameValidation() {
        AreaPage areaPage = new AreaPage(driver);
        areaPage.navigateToAddArea();
        
        areaPage.setBranch(""); // Leave branch empty
        areaPage.clickSubmit();
        
        String errorMsg = areaPage.getFieldError(areaPage.branchInput);
        if (errorMsg.isEmpty()) errorMsg = areaPage.getValidationMessage(areaPage.branchInput);
        
        Assert.assertTrue(errorMsg.contains("required") || errorMsg.contains("name"), 
                "Validation message mismatch for Branch Name!");
    }
}
