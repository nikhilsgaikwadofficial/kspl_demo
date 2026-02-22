
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

    public class LoginValidationPage {


        WebDriver driver;


        By usernameField = By.id("username");
        By passwordField = By.id("password");
        By signInButton = By.id("loginBtn");
        By errorMsg = By.id("errorMsg");
        By dashboard = By.id("dashboard");


        public LoginValidationPage(WebDriver driver) {
            this.driver = driver;
        }


        public void Username(String user) {
            driver.findElement(usernameField).clear();
            driver.findElement(usernameField).sendKeys(user);
        }


        public void password(String pass) {
            driver.findElement(passwordField).clear();
            driver.findElement(passwordField).sendKeys(pass);
        }


        public void SignIn() {
            driver.findElement(signInButton).click();
        }


        public String getErrorMessage() {
            try {
                WebElement err = driver.findElement(errorMsg);
                return err.getText();
            } catch (Exception e) {
                return "";
            }
        }


        public boolean isDashboardDisplayed() {
            try {
                return driver.findElement(dashboard).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }
    }




