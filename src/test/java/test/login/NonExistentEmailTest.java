package test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import test.BaseTest;

public class NonExistentEmailTest extends BaseTest {

    @Test
    public void testNonExistentEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("unknown@test.com");
        loginPage.enterPassword("12345678");
        loginPage.clickSignIn();
        
        String alert = loginPage.getAlertMessage();
        Assert.assertFalse(alert.isEmpty(), "An alert message should be shown for non-existent email.");
        Assert.assertTrue(alert.toLowerCase().contains("not found") || alert.toLowerCase().contains("invalid") || alert.toLowerCase().contains("credentials"), 
                "Alert message should indicate non-existent user or invalid credentials: " + alert);
    }
}
