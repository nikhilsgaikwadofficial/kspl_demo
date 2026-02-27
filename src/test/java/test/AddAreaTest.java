package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddAreaPage;

public class AddAreaTest extends BaseTest {

    @Test(priority = 1)
    public void verify_addArea() {
        AddAreaPage addAreaPages = new AddAreaPage(driver);
        addAreaPages.clickMaster();
        addAreaPages.clickArea();
        addAreaPages.clickAdd();

        addAreaPages.selectState("");
        addAreaPages.setBranch("(Kurla)");
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

        boolean success = addAreaPages.isSuccessMessageDisplayed();
        if (success) {
            System.out.println("=== Valid Data Entry Test ===");
            System.out.println("RESULT: Area added successfully");
        } else {
            System.out.println("=== Valid Data Entry Test ===");
            System.out.print("RESULT: FAILED - Area was not added");
            if (addAreaPages.isGenericErrorMessageDisplayed()) {
                System.out.println(" | Error displayed on page");
            } else {
                System.out.println();
            }
        }

        Assert.assertTrue(success, "Area was not added successfully");
    }

    @Test(priority = 2)
    public void verify_emptyFormValidation() {
        SoftAssert softAssert = new SoftAssert();
        AddAreaPage addAreaPages = new AddAreaPage(driver);
        addAreaPages.clickMaster();
        addAreaPages.clickArea();
        addAreaPages.clickAdd();

        addAreaPages.clickSubmit();

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        System.out.println("=== Empty Form Validation Test Results ===");

        // Only print if the field validation is triggered (error displayed)
        
        if (addAreaPages.isStateErrorDisplayed()) {
            System.out.println("State field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("State field validation error not displayed");
        }

        if (addAreaPages.isBranchErrorDisplayed()) {
            System.out.println("Branch Name field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Branch Name field validation error not displayed");
        }

        if (addAreaPages.isAddressErrorDisplayed()) {
            System.out.println("Address field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Address field validation error not displayed");
        }

        if (addAreaPages.isLatitudeErrorDisplayed()) {
            System.out.println("Latitude field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Latitude field validation error not displayed");
        }

        if (addAreaPages.isLongitudeErrorDisplayed()) {
            System.out.println("Longitude field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Longitude field validation error not displayed");
        }

        if (addAreaPages.isWorkTypeErrorDisplayed()) {
            System.out.println("Work Type field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Work Type field validation error not displayed");
        }

        if (addAreaPages.isStartTimeErrorDisplayed()) {
            System.out.println("Start Time field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Start Time field validation error not displayed");
        }

        if (addAreaPages.isEndTimeErrorDisplayed()) {
            System.out.println("End Time field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("End Time field validation error not displayed");
        }

        if (addAreaPages.isCheckoutErrorDisplayed()) {
            System.out.println("Force Checkout field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Force Checkout field validation error not displayed");
        }

        if (addAreaPages.isAllowLateErrorDisplayed()) {
            System.out.println("Allow Late field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Allow Late field validation error not displayed");
        }

        if (addAreaPages.isAllowOtErrorDisplayed()) {
            System.out.println("Allow OT field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Allow OT field validation error not displayed");
        }

        if (addAreaPages.isRadiusErrorDisplayed()) {
            System.out.println("Radius field: Validation triggered (Kept Empty)");
            softAssert.assertTrue(true);
        } else {
            softAssert.fail("Radius field validation error not displayed");
        }

        softAssert.assertAll();
    }
}
