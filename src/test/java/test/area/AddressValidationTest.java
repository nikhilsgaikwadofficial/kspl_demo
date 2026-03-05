package test.area;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.area.AreaPage;
import test.BaseTest;

public class AddressValidationTest extends BaseTest {

    @Test
    public void testAddressValidation() {
        AreaPage areaPage = new AreaPage(driver);
        areaPage.navigateToAddArea();
        
        areaPage.setAddress(""); // Empty address
        areaPage.clickSubmit();
        
        String errorMsg = areaPage.getFieldError(areaPage.addressInput);
        if (errorMsg.isEmpty()) errorMsg = areaPage.getValidationMessage(areaPage.addressInput);
        
        Assert.assertTrue(errorMsg.contains("required") || errorMsg.contains("address"), 
                "Validation message mismatch for Address!");
    }
}
