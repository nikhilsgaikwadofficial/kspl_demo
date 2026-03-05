package test.holiday;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.holiday.HolidayPage;
import test.BaseTest;

public class EmptyNameValidationTest extends BaseTest {

    @Test
    public void testEmptyNameValidation() {
        HolidayPage holidayPage = new HolidayPage(driver);
        holidayPage.navigateToAddHoliday();
        
        holidayPage.setDate("01-01-2027");
        holidayPage.setHolidayName(""); // Empty name
        holidayPage.clickSubmit();
        
        String errorMsg = holidayPage.getFieldError(holidayPage.holidayNameInput);
        if (errorMsg.isEmpty()) errorMsg = holidayPage.getValidationMessage(holidayPage.holidayNameInput);
        
        Assert.assertEquals(errorMsg, "Holiday Name is required.", "Validation message mismatch!");
    }
}
