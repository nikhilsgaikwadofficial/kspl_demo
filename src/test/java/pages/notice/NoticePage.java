package pages.notice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class NoticePage extends BasePage {
    
    public NoticePage(WebDriver driver) {
        super(driver);
    }

    By master = By.xpath("//i[@class='fa fa-database ']");
    By notice = By.xpath("//p[normalize-space()='Notice']");
    By addNotice = By.xpath("//p[normalize-space()='Add Notice']");
    public By titleInput = By.xpath("//input[@name='title']");
    public By msgInput = By.xpath("//textarea[@name='message']");
    public By submitButton = By.xpath("//button[@id='btn-admin-member-submit']");

    public void navigateToAddNotice() {
        WebElement mast = wait.until(ExpectedConditions.elementToBeClickable(master));
        js.executeScript("arguments[0].click();", mast);
        
        WebElement noticeMenu = wait.until(ExpectedConditions.elementToBeClickable(notice));
        js.executeScript("arguments[0].click();", noticeMenu);
        
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(addNotice));
        js.executeScript("arguments[0].click();", add);
    }

    public void setTitle(String titleText) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput));
        el.click();
        el.clear();
        el.sendKeys(titleText);
    }

    public void setMessage(String messageText) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(msgInput));
        el.click();
        el.clear();
        el.sendKeys(messageText);
    }

    public void clickSubmit() {
        WebElement sub = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        js.executeScript("arguments[0].click();", sub);
    }

    public String getFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field.findElement(By.xpath(".."));
            WebElement error = parent.findElement(By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback')]"));
            if (error.isDisplayed()) return error.getText();
        } catch (Exception e) {}
        return "";
    }

    public String getValidationMessage(By locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
    }

    public String getSuccessMessageText() {
        By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return msg.getText();
    }
}
