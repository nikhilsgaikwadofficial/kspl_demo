package test.user_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;
import test.BaseTest;

public class RoleValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.navigateToAddUser();
    }

    @Test
    public void validateRoleField() {
        SoftAssert softAssert = new SoftAssert();
        
        // Trigger validation by clicking submit with Role unselected
        validationPage.clickSubmit();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        boolean errorDisplayed = validationPage.isRoleErrorDisplayed();
        String validationMsg = validationPage.getFieldErrorText(validationPage.role);
        if (validationMsg.isEmpty()) validationMsg = validationPage.getValidationMessage(validationPage.role);

        System.out.println("\n[DEMO] Validating Role field...");
        System.out.println("Error Displayed (HTML): " + errorDisplayed);
        System.out.println("Captured Validation Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertTrue(errorDisplayed || !validationMsg.isEmpty(), "Role validation error should be displayed");
        softAssert.assertAll();
    }
}
