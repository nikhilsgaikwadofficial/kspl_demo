
    package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class BasePage {
        WebDriver driver;
        JavascriptExecutor js;
        WebDriverWait wait;
        public BasePage(WebDriver driver){
            this.driver=driver;
            this.js=(JavascriptExecutor)driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

    }


