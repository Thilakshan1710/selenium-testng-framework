package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        String path = System.getProperty("user.dir")
                + "/screenshots/"
                + testName + ".png";

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(path);

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved: " + path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}