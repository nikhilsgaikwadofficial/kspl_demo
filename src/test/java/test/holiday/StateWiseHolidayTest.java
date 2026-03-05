package test.holiday;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.holiday.HolidayPage;
import test.BaseTest;

public class StateWiseHolidayTest extends BaseTest {

    @Test
    public void testStateWiseHoliday() {
        HolidayPage holidayPage = new HolidayPage(driver);
        holidayPage.navigateToAddHoliday();
        
        holidayPage.setDate("01-05-2026");
        holidayPage.setHolidayName("Maharashtra Day");
        holidayPage.selectState("Maharashtra");
        holidayPage.clickSubmit();
        
        String successMsg = holidayPage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added"),
                "Success message mismatch for state-wise holiday! Actual: " + successMsg);
    }
}
