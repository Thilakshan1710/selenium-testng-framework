package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.DriverFactory;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        if (DriverFactory.getDriver() != null) {
            ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    testName
            );
        }

        System.out.println("❌ FAILED: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ PASS: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ SKIP: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 TEST SUITE STARTED: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 TEST SUITE FINISHED: " + context.getName());
    }
}