package test;



import org.testng.annotations.Test;
import pages.NoticePage;

    public class AddNotice extends BaseTest{

        @Test
        public void verify_addNotice(){
            NoticePage np=new NoticePage(driver);
            np.clickMaster();
            np.setNotice();
            np.setAddNotice();
            np.setTitle("Leave Request");
            np.setMsg("Requesting to approve my leave request");
            np.setSubmit();
        }
    }


