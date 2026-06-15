package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import org.testng.annotations.Listeners;
import listeners.TestListener;

import org.testng.annotations.DataProvider;



@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] loginData() {

	    return new Object[][] {
	        {"standard_user", "secret_sauce"},
	        {"wrong_user", "wrong_password"},
	        {"locked_out_user", "secret_sauce"}
	    };
	}
	
	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password) {

	    driver.get("https://www.saucedemo.com/");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

	    LoginPage loginPage = new LoginPage(driver);

	    loginPage.login(username, password);

	    System.out.println("Executed: " + username);
	}
	
    @Test
    public void verifyLogin() {

        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }
    
    @Test
    public void verifyInvalidLogin() {

        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("wrong_user", "wrong_password");

        String errorMessage = driver.findElement(
                By.cssSelector("h3[data-test='error']"))
                .getText();

        Assert.assertTrue(
                errorMessage.contains("Username and password do not match"));
    }
    
    @Test(enabled = false)
    public void verifyLoginFailureDemo() {

        driver.get("https://www.saucedemo.com/");

        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertTrue(error.contains("Username and password do not match"));
    }
}