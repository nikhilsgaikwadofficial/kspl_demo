package test.user_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;
import test.BaseTest;

public class PasswordValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.navigateToAddUser();
    }

    @Test
    public void validatePasswordField() {
        SoftAssert softAssert = new SoftAssert();
        
        // Trigger validation by clicking submit with Password empty
        validationPage.clickSubmit();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        boolean errorDisplayed = validationPage.isPasswordErrorDisplayed();
        String validationMsg = validationPage.getFieldErrorText(validationPage.password);
        if (validationMsg.isEmpty()) validationMsg = validationPage.getValidationMessage(validationPage.password);

        System.out.println("\n[DEMO] Validating Password field...");
        System.out.println("Error Displayed (HTML): " + errorDisplayed);
        System.out.println("Captured Validation Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertTrue(errorDisplayed || !validationMsg.isEmpty(), "Password validation error should be displayed");
        softAssert.assertAll();
    }
}
