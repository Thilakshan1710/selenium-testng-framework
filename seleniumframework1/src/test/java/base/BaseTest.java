package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        DriverFactory.initDriver();

        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();

        System.out.println("Browser Started");
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();

        System.out.println("Browser Closed");
    }
}