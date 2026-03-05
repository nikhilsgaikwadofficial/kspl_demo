package test.notice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.notice.NoticePage;
import test.BaseTest;

public class LongMessageTest extends BaseTest {

    @Test
    public void testLongMessage() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navigateToAddNotice();
        
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            longMsg.append("This is a very long message for testing purpose. ");
        }
        
        noticePage.setTitle("Long Message Test");
        noticePage.setMessage(longMsg.toString());
        noticePage.clickSubmit();
        
        String successMsg = noticePage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added"), 
                "Success message mismatch for long message! Actual: " + successMsg);
    }
}
