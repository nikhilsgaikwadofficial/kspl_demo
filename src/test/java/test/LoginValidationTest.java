package test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.ExtentReportsManager;

import java.time.Duration;

@Listeners(listeners.ExtentTestListener.class)
public class LoginValidationTest {

        WebDriver driver;
        WebDriverWait wait;
        ExtentTest extentTest;

        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://ourattendance.com/web-portal_uat/public/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            extentTest = ExtentReportsManager.getTest();
        }

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }


        @DataProvider(name = "loginData")
        public Object[][] getLoginTestData() {
            return new Object[][]{
                    {"venture@example.com", "12345678", true, "Valid credentials"},
                    {"kadmin@test.com", "Kadmin@Test", false, "Invalid password"},
                    {"KAdmin@test.com", "123455678", false, "Invalid username"},
                    {"", "12345678", false, "Empty username"},
                    {"kadmin@test.com", "", false, "Empty password"}};

        }


        @Test(dataProvider = "loginData")
        public void validateLogin(String user, String pass, boolean isValid, String scenario) {

            SoftAssert softAssert = new SoftAssert();
            LoginPage lp = new LoginPage(driver);
            
            // Log test information to Extent Report
            if (extentTest != null) {
                extentTest.info("Executing Scenario: " + scenario);
                extentTest.info("Username: " + (user.isEmpty() ? "[Empty]" : user));
                extentTest.info("Password: " + (pass.isEmpty() ? "[Empty]" : "******"));
                extentTest.info("Expected Result: " + (isValid ? "Login Successful" : "Login Should Fail"));
            }
            
            System.out.println("Executing Scenario: " + scenario);
            System.out.println("Username: " + user);
            System.out.println("Password: " + pass);

            try {
                lp.Username(user);
                lp.password(pass);
                lp.Signin();

                if (isValid) {
                    System.out.println("Expected Result: Login Successful");
                    
                    if (extentTest != null) {
                        extentTest.info("Attempting login with valid credentials");
                    }

                    // Wait until the dashboard/post-login page is detected
                    wait.until(d -> lp.isDashboardDisplayed());
                    boolean dashboardDisplayed = lp.isDashboardDisplayed();
                    
                    if (dashboardDisplayed) {
                        if (extentTest != null) {
                            extentTest.pass(MarkupHelper.createLabel("Login Successful - Dashboard displayed", ExtentColor.GREEN));
                        }
                        softAssert.assertTrue(true, "Login successful");
                    } else {
                        if (extentTest != null) {
                            extentTest.fail(MarkupHelper.createLabel("Login Failed - Dashboard not displayed", ExtentColor.RED));
                        }
                        softAssert.assertTrue(false, " FAILED: " + scenario + " → User should be logged in successfully but dashboard is not displayed");
                    }
                } else {
                    boolean dashboardDisplayed = lp.isDashboardDisplayed();
                    
                    if (extentTest != null) {
                        extentTest.info("Attempting login with invalid credentials");
                    }
                    
                    if (!dashboardDisplayed) {
                        String errorMessage = lp.getErrorMessage();
                        if (errorMessage != null && errorMessage.length() > 0) {
                            if (extentTest != null) {
                                extentTest.pass(MarkupHelper.createLabel("Login Correctly Rejected - Error message: " + errorMessage, ExtentColor.GREEN));
                            }
                            softAssert.assertTrue(true, "Login correctly rejected");
                        } else {
                            if (extentTest != null) {
                                extentTest.fail(MarkupHelper.createLabel("Login Rejected but Error Message Not Displayed", ExtentColor.RED));
                            }
                            softAssert.assertTrue(false, " FAILED: " + scenario + " → Error message is not displayed");
                        }
                    } else {
                        if (extentTest != null) {
                            extentTest.fail(MarkupHelper.createLabel("Login Should Have Failed but Dashboard is Displayed", ExtentColor.RED));
                        }
                        softAssert.assertFalse(true, " FAILED: " + scenario + " → User should NOT be logged in");
                    }
                }
            } catch (Exception e) {
                if (extentTest != null) {
                    extentTest.fail("Exception occurred: " + e.getMessage());
                    extentTest.log(Status.FAIL, e);
                }
                throw e;
            }

            softAssert.assertAll();

        }
}






