package test;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddAreaPage;

public class AddAreaTest extends BaseTest{

        @Test
        public void verify_addArea(){
            AddAreaPage addAreaPages=new AddAreaPage(driver);
            addAreaPages.clickMaster();
            addAreaPages.clickArea();
            addAreaPages.clickAdd();
            addAreaPages.selectState("Maharashtra");
            addAreaPages.setBranch("(vikroli)");
            addAreaPages.setAddress("M G Road");
            addAreaPages.setLatitude("19.11");
            addAreaPages.setLongitude("72.93");
            addAreaPages.selectWorkType("Shift");
            addAreaPages.setStartTime("08:00");
            addAreaPages.setEndTime("20:00");
            addAreaPages.selectCheckout("No");
            addAreaPages.selectAllowLate("Yes");
            addAreaPages.selectAllowOt("Yes");
            addAreaPages.selectRadius("No");
            addAreaPages.clickSubmit();
            Assert.assertTrue(addAreaPages.isSuccessMessageDisplayed(),
                    "Area was not added successfully");
        }
    }


