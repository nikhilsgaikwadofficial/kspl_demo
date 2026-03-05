package test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import test.BaseTest;

public class MandatoryPasswordTest extends BaseTest {

    @Test
    public void testMandatoryPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("venture@example.com");
        // Skip password
        loginPage.clickSignIn();
        
        String validationMsg = loginPage.getValidationMessage(loginPage.passwordInput);
        Assert.assertFalse(validationMsg.isEmpty(), "Validation message should be shown for empty password.");
        Assert.assertTrue(validationMsg.toLowerCase().contains("fill") || validationMsg.toLowerCase().contains("required"), 
                "Validation message should indicate password is required: " + validationMsg);
    }
}
