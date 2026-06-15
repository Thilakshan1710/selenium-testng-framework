package seleniumframework1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginValidation {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name"))
              .sendKeys("wrong_user");

        driver.findElement(By.id("password"))
              .sendKeys("wrong_password");

        driver.findElement(By.id("login-button"))
              .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        String title = driver.getTitle();

        if(title.equals("Swag Labs")) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        String error =
        		driver.findElement(By.cssSelector("h3[data-test='error']"))
        		      .getText();

        		System.out.println(error);
        
        driver.quit();
    }
}