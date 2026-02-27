package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

        public WebDriver driver;
        @BeforeClass
        public void setUp(){


            driver=new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://ourattendance.com/web-portal_uat/public/home");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("goldys8ul@gmail.com");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("S8ulmortal");
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-flat']")).click();
        }
        @AfterClass
        public void tearDown()  {

           driver.quit();
        }}


