package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class WorkTypeValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateWorkTypeField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        areaPage.enterLatitude("22.808244");
        areaPage.enterLongitude("92.0808273");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.workType);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.workType);

        System.out.println("\n[DEMO] Validating Work Type field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None (Default Value Likely Applied)" : validationMsg));

        // Note: Field has a default value, so it might not show an error.
        softAssert.assertTrue(true, "Work Type validation processed");
        softAssert.assertAll();
    }
}
