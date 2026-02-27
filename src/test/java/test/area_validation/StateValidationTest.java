package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class StateValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateStateField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        
        String validationMsg = areaPage.getFieldErrorText(areaPage.state);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.state);

        System.out.println("\n Validating State field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "State is required.", "State validation message mismatch");
        softAssert.assertAll();
    }
}
