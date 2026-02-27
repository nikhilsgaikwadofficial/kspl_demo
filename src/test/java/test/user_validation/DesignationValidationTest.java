package test.user_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;
import test.BaseTest;

public class DesignationValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.navigateToAddUser();
    }

    @Test
    public void validateDesignationField() {
        SoftAssert softAssert = new SoftAssert();
        

        validationPage.clickSubmit();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        boolean errorDisplayed = validationPage.isDesignationErrorDisplayed();
        String validationMsg = validationPage.getFieldErrorText(validationPage.designation);
        if (validationMsg.isEmpty()) validationMsg = validationPage.getValidationMessage(validationPage.designation);

        System.out.println("\n[DEMO] Validating Designation field...");
        System.out.println("Error Displayed (HTML): " + errorDisplayed);
        System.out.println("Captured Validation Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertTrue(errorDisplayed || !validationMsg.isEmpty(), "Designation validation error should be displayed");
        softAssert.assertAll();
    }
}
