
    package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class DeleteAreaPage extends BasePage {

        WebDriver driver;

        public DeleteAreaPage(WebDriver driver) {
            super(driver);
            js = (JavascriptExecutor) driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        By master = By.xpath("(//i[@class='fa fa-database '])");
        By area = By.xpath("(//i[@class='fa fa-map-marked-alt '])");
        By List = By.xpath("//a[@href='https://ourattendance.com/web-portal_uat/public/areas']");
        By delete = By.xpath("(//i[@class='fa fa-trash'])[7]");


        public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }

        public void clickArea() {
            WebElement areaa = wait.until(ExpectedConditions.visibilityOfElementLocated(area));
            js.executeScript("arguments[0].click();", areaa);
        }

        public void clickList() {
            WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(List));
            js.executeScript("arguments[0].click();", list);
        }

        public void clickDelete() {
            WebElement deletee = wait.until(ExpectedConditions.visibilityOfElementLocated(delete));
            js.executeScript("arguments[0].click();", deletee);
        }
        public void deleteAreaAndAcceptAlert() {

            WebElement deletee = wait.until(ExpectedConditions.presenceOfElementLocated(delete));


            js.executeScript("arguments[0].scrollIntoView({block:'center'});", delete);

            wait.until(ExpectedConditions.elementToBeClickable(delete)).click();


            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String alertText = alert.getText();
            System.out.println("Alert message: " + alertText);

            alert.accept(); // Click OK
        }

        // Optional: Cancel delete
        public void deleteAreaAndCancelAlert() {
            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(delete));
            deleteIcon.click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss(); // Click Cancel
        }
    }





