package test.holiday;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.holiday.HolidayPage;
import test.BaseTest;

public class PastDateHolidayTest extends BaseTest {

    @Test
    public void testPastDateHoliday() {
        HolidayPage holidayPage = new HolidayPage(driver);
        holidayPage.navigateToAddHoliday();
        
        holidayPage.setDate("15-08-2023"); // Past date
        holidayPage.setHolidayName("Independence Day 2023");
        holidayPage.selectState("All India");
        holidayPage.clickSubmit();
        
        String successMsg = holidayPage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added"), 
                "Success message mismatch for past date! Actual: " + successMsg);
    }
}
