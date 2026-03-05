package test.area;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.area.AreaPage;
import test.BaseTest;

public class LatitudeNumericValidationTest extends BaseTest {

    @Test
    public void testLatitudeNumericValidation() {
        AreaPage areaPage = new AreaPage(driver);
        areaPage.navigateToAddArea();
        
        areaPage.setLatitude("abc"); // Invalid latitude
        areaPage.clickSubmit();
        
        String errorMsg = areaPage.getFieldError(areaPage.latitudeInput);
        if (errorMsg.isEmpty()) errorMsg = areaPage.getValidationMessage(areaPage.latitudeInput);
        
        Assert.assertTrue(errorMsg.contains("numeric") || errorMsg.contains("invalid") || errorMsg.contains("lat"), 
                "Validation message mismatch for non-numeric Latitude!");
    }
}
