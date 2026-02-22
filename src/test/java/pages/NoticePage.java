
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class NoticePage extends BasePage{
        public NoticePage(WebDriver driver){
            super(driver);
            js = (JavascriptExecutor) driver;
        }

        By  master = By.xpath("//i[@class='fa fa-database ']");
        By notice=By.xpath("//p[normalize-space()='Notice']");
        By addNotice=By.xpath("//p[normalize-space()='Add Notice']");
        By title=By.xpath("//input[@name='title']");
        By msg=By.xpath("//textarea[@name='message']");
        By submit=By.xpath("//button[@id='btn-admin-member-submit']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }

        public void setNotice() {
            WebElement noticee = wait.until(ExpectedConditions.visibilityOfElementLocated(notice));
            js.executeScript("arguments[0].click();", noticee);
        }

        public void setAddNotice(){
            WebElement addNoticee = wait.until(ExpectedConditions.visibilityOfElementLocated(addNotice));
            js.executeScript("arguments[0].click();", addNoticee);
        }

        public void setTitle(String titlee) {
            WebElement tit= driver.findElement(title);
            tit.click();
            tit.sendKeys(titlee);
        }

        public void setMsg(String mssg) {
            WebElement msgg=driver.findElement(msg);
            msgg.click();
            msgg.sendKeys(mssg);
        }

        public void setSubmit() {
            WebElement submitt = wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
            js.executeScript("arguments[0].click();", submitt);
        }

    }


