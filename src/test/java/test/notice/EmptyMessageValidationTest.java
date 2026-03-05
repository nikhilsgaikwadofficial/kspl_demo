package test.notice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.notice.NoticePage;
import test.BaseTest;

public class EmptyMessageValidationTest extends BaseTest {

    @Test
    public void testEmptyMessageValidation() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navigateToAddNotice();
        
        noticePage.setTitle("Test Title");
        noticePage.setMessage(""); 
        noticePage.clickSubmit();
        
        String errorMsg = noticePage.getFieldError(noticePage.msgInput);
        if (errorMsg.isEmpty()) errorMsg = noticePage.getValidationMessage(noticePage.msgInput);
        
        Assert.assertEquals(errorMsg, "Notice Message is required.", "Validation message mismatch!");
    }
}
