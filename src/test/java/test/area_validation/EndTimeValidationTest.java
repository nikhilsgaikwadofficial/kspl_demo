package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class EndTimeValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateEndTimeField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        areaPage.enterLatitude("22.808244");
        areaPage.enterLongitude("92.0808273");
        areaPage.selectWorkType("Shift");
        areaPage.enterStartTime("09:30");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.endTime);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.endTime);

        System.out.println("\n[DEMO] Validating End Time field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "End Time is required.", "End Time validation message mismatch");
        softAssert.assertAll();
    }
}
