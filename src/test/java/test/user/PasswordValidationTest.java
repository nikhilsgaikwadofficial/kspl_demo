package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class PasswordValidationTest extends BaseTest {

    @Test
    public void testPasswordValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_PASS_" + System.currentTimeMillis());
        userPage.setName("Password Test User");
        userPage.setMobile("9000000004");
        userPage.setEmail("pass@test.com");
        // Skip Password
        
        userPage.selectRole("Level 1");
        userPage.selectDesignation("F&B Executive");
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.passwordInput);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.passwordInput);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for empty Password");
    }
}
