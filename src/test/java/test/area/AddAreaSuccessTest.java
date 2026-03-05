package test.area;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.area.AreaPage;
import test.BaseTest;

public class AddAreaSuccessTest extends BaseTest {

    @Test
    public void testAddAreaSuccess() {
        AreaPage areaPage = new AreaPage(driver);
        areaPage.navigateToAddArea();
        
        areaPage.selectState("Assam");
        areaPage.setBranch("Guwahati Branch");
        areaPage.setAddress("GS Road, Guwahati");
        areaPage.setLatitude("26.11");
        areaPage.setLongitude("91.73");
        areaPage.selectWorkType("Flexible");
        areaPage.setStartTime("09:00");
        areaPage.setEndTime("18:00");
        areaPage.clickSubmit();
        
        String successMsg = areaPage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added") || successMsg.contains("saved"), 
                "Success message mismatch! Actual: " + successMsg);
    }
}
