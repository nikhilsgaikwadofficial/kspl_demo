package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class LatitudeValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateLatitudeField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.latitudeField);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.latitudeField);

        System.out.println("\n[DEMO] Validating Latitude field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "Latitude is required.", "Latitude validation message mismatch");
        softAssert.assertAll();
    }
}
