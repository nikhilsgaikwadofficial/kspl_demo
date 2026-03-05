package test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import test.BaseTest;

public class LoginSuccessTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("venture@example.com");
        loginPage.enterPassword("12345678");
        loginPage.clickSignIn();
        
        Assert.assertTrue(loginPage.isLoginSuccessful(), "User should be logged in successfully.");
    }
}
