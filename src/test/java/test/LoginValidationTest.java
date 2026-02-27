package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.time.Duration;

public class LoginValidationTest {

        WebDriver driver;
        WebDriverWait wait;

        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://ourattendance.com/web-portal_uat/public/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

            System.out.println("Executing Scenario: " + scenario);
            System.out.println("Username: " + user);
            System.out.println("Password: " + pass);

            try {
                lp.Username(user);
                lp.password(pass);
                lp.Signin();

                if (isValid) {
                    System.out.println("Expected Result: Login Successful");

                    // Wait until the dashboard/post-login page is detected
                    wait.until(d -> lp.isDashboardDisplayed());
                    boolean dashboardDisplayed = lp.isDashboardDisplayed();

                    if (dashboardDisplayed) {
                        softAssert.assertTrue(true, "Login successful");
                    } else {
                        softAssert.assertTrue(false, " FAILED: " + scenario + " → User should be logged in successfully but dashboard is not displayed");
                    }
                } else {
                    boolean dashboardDisplayed = lp.isDashboardDisplayed();

                    if (!dashboardDisplayed) {
                        String errorMessage = lp.getErrorMessage();
                        if (errorMessage != null && errorMessage.length() > 0) {
                            softAssert.assertTrue(true, "Login correctly rejected");
                        } else {
                            softAssert.assertTrue(false, " FAILED: " + scenario + " → Error message is not displayed");
                        }
                    } else {
                        softAssert.assertFalse(true, " FAILED: " + scenario + " → User should NOT be logged in");
                    }
                }
            } catch (Exception e) {
                throw e;
            }

            softAssert.assertAll();

        }
}
