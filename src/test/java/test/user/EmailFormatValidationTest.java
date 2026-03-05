package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class EmailFormatValidationTest extends BaseTest {

    @Test
    public void testEmailFormatValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_ID");
        userPage.setName("Test User");
        userPage.setMobile("9876543210");
        userPage.setEmail("invalid-email"); // Invalid format
        userPage.setPassword("Pass@123");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.emailInput);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.emailInput);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for invalid email format");
    }
}
