package test.holiday;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.holiday.HolidayPage;
import test.BaseTest;

public class OverlappingHolidayTest extends BaseTest {

    @Test
    public void testOverlappingHoliday() {
        HolidayPage holidayPage = new HolidayPage(driver);
        holidayPage.navigateToAddHoliday();
        
        holidayPage.setDate("26-01-2026"); // Mocking an existing holiday date
        holidayPage.setHolidayName("Duplicate Holiday");
        holidayPage.selectState("All India");
        holidayPage.clickSubmit();
        
        String errorMsg = holidayPage.getFieldError(holidayPage.dateInput);
        Assert.assertEquals(errorMsg, "Holiday already exists on this date.", "Error message mismatch for duplicate date!");
    }
}
