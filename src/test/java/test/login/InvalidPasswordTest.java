package test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import test.BaseTest;

public class InvalidPasswordTest extends BaseTest {

    @Test
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("venture@example.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickSignIn();
        
        String alert = loginPage.getAlertMessage();
        Assert.assertFalse(alert.isEmpty(), "An alert message should be shown for invalid password.");
        Assert.assertTrue(alert.toLowerCase().contains("invalid") || alert.toLowerCase().contains("wrong") || alert.toLowerCase().contains("credentials"), 
                "Alert message should indicate invalid credentials: " + alert);
    }
}
