package test.resources.utilities;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
        sparkReporter.config().setDocumentTitle("Test-Roche");
        sparkReporter.config().setReportName("JavaTestProj");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestSuccess(ITestResult testResult)
    {
        test = extent.createTest(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.createNode(testResult.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult testResult)
    {
        test = extent.createTest(testResult.getName());
        test.createNode(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, testResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult testResult)
    {
        test = extent.createTest(testResult.getName());
        test.createNode(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, testResult.getThrowable().getMessage());
    }

    @Override
    public synchronized void onFinish(ITestContext context)
    {
        extent.flush();
    }
}
