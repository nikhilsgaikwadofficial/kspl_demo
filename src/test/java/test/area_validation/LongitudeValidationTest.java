package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class LongitudeValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateLongitudeField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        areaPage.enterLatitude("22.808244");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.longitudeField);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.longitudeField);

        System.out.println("\n[DEMO] Validating Longitude field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "Longitude is required.", "Longitude validation message mismatch");
        softAssert.assertAll();
    }
}
