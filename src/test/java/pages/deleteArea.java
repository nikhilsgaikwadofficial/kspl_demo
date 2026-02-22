package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class deleteArea {


        public static void main(String[] args) throws InterruptedException {
            WebDriver driver =new ChromeDriver();

            driver.get("https://ourattendance.com/web-portal_uat/public/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("venture@example.com");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("12345678");
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-flat']")).click();




            WebElement master = driver.findElement(By.xpath("//i[@class='fa fa-database ']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", master);

            WebElement area= driver.findElement(By.xpath("(//i[@class='fa fa-map-marked-alt '])"));
            JavascriptExecutor js1=(JavascriptExecutor) driver;
            js1.executeScript("arguments[0].click();",area);

            WebElement List= driver.findElement(By.xpath("//a[@href='https://ourattendance.com/web-portal_uat/public/areas']"));
            JavascriptExecutor js2=(JavascriptExecutor) driver;
            js1.executeScript("arguments[0].click();",List);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement delete=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[@class='fa fa-trash'])[7]")));
            //  delete.click();

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", delete);

            wait.until(ExpectedConditions.elementToBeClickable(delete)).click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            if (alertText.equalsIgnoreCase("Are you sure want to delete this?")) {
                alert.accept();
            } else {
                System.out.println("Unexpected alert message!");
                alert.dismiss();
            }

        }}

