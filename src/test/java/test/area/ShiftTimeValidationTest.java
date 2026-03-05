package test.area;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.area.AreaPage;
import test.BaseTest;

public class ShiftTimeValidationTest extends BaseTest {

    @Test
    public void testShiftTimeValidation() {
        AreaPage areaPage = new AreaPage(driver);
        areaPage.navigateToAddArea();
        
        areaPage.selectState("Bihar");
        areaPage.setBranch("Validation Test Branch");
        areaPage.setAddress("123 Validation St, Mumbai");
        areaPage.setLatitude("19.0760");
        areaPage.setLongitude("72.8777");
        areaPage.selectWorkType("Shift");
        
        areaPage.setStartTime("19:00");
        areaPage.setEndTime("08:00"); // End time before start time
        areaPage.clickSubmit();
        
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        String fieldError = areaPage.getFieldError(areaPage.endTimeInput);
        String genericError = areaPage.getGenericErrorMessage();
        String validationMsg = areaPage.getValidationMessage(areaPage.endTimeInput);
        
        System.out.println("Field Error: " + fieldError);
        System.out.println("Generic Error: " + genericError);
        System.out.println("HTML5 Msg: " + validationMsg);

        String finalError = !fieldError.isEmpty() ? fieldError : (!genericError.isEmpty() ? genericError : validationMsg);
        
        Assert.assertFalse(finalError.isEmpty(), "An error message should be shown (either field-level, alert, or HTML5) when End Time is before Start Time!");
    }
}
