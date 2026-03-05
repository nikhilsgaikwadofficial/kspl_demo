package pages.login;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;
import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    public By emailInput = By.xpath("//input[@name='email']");
    public By passwordInput = By.xpath("//input[@name='password']");
    public By signInButton = By.xpath("//button[@type='submit']");
    public By alertMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    public By successIndicator = By.xpath("//a[contains(@href,'/logout')]");

    public void enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        el.clear();
        el.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        btn.click();
    }

    public String getAlertMessage() {
        try {
            WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
            return msg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getValidationMessage(By locator) {
        try {
            return (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successIndicator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
