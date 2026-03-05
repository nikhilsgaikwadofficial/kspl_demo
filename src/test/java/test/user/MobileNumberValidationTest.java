package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class MobileNumberValidationTest extends BaseTest {

    @Test
    public void testMobileNumberValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_ID");
        userPage.setName("Test User");
        // Leave Mobile empty
        userPage.setEmail("test@user.com");
        userPage.setPassword("Pass@123");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.mobileInput);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.mobileInput);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for empty mobile number");
    }
}
