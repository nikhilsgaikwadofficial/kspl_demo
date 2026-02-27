package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class AddressValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateAddressField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("");
        areaPage.enterBranch("");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.address);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.address);

        System.out.println("\n Validating Address field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertEquals(validationMsg, "Address is required.", "Address validation message mismatch");
        softAssert.assertAll();
    }
}
