package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class StartTimeValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateStartTimeField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        areaPage.enterLatitude("22.808244");
        areaPage.enterLongitude("92.0808273");
        areaPage.selectWorkType("Shift");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.startTime);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.startTime);

        System.out.println("\n[DEMO] Validating Start Time field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "Start Time is required.", "Start Time validation message mismatch");
        softAssert.assertAll();
    }
}
