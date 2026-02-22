
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

    public class LoginPage extends BasePage {

        By usernameField = By.xpath("//input[@placeholder='Email']");
        By passwordField = By.xpath("//input[@placeholder='Password']");
        By signinButton = By.xpath("//button[@class='btn btn-primary btn-block btn-flat']");
        By errorMessage = By.xpath("//div[contains(@class,'alert')]");

        public LoginPage(WebDriver driver){
            super(driver);
        }

        public void Username(String name){
            WebElement usernameElement = wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
            usernameElement.click();
            usernameElement.clear();
            usernameElement.sendKeys(name);
        }

        public void password(String pass){
            WebElement passwordElement = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
            passwordElement.click();
            passwordElement.clear();
            passwordElement.sendKeys(pass);
        }

        public void Signin(){
            WebElement signinElement = wait.until(ExpectedConditions.elementToBeClickable(signinButton));
            signinElement.click();
        }

        public boolean isDashboardDisplayed() {
            try {
                String currentUrl = driver.getCurrentUrl();
                // Check for dashboard or home (post-login pages)
                return currentUrl.contains("dashboard") ||
                       (currentUrl.contains("home") && !currentUrl.endsWith("public/"));
            } catch (Exception e) {
                // If the session/window is not available, treat as \"not displayed\"
                return false;
            }
        }

        public String getErrorMessage() {
            try {
                WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
                return errorElement.getText();
            } catch (Exception e) {
                return ""; // Return empty string if error message element is not found
            }
        }

    }


