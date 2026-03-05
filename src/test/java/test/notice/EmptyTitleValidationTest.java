package test.notice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.notice.NoticePage;
import test.BaseTest;

public class EmptyTitleValidationTest extends BaseTest {

    @Test
    public void testEmptyTitleValidation() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navigateToAddNotice();
        
        noticePage.setTitle(""); 
        noticePage.setMessage("Test Message");
        noticePage.clickSubmit();
        
        String errorMsg = noticePage.getFieldError(noticePage.titleInput);
        if (errorMsg.isEmpty()) errorMsg = noticePage.getValidationMessage(noticePage.titleInput);
        
        Assert.assertEquals(errorMsg, "Notice Title is required.", "Validation message mismatch!");
    }
}
