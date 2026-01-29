package utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.internal.BaseClassFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import testBase.BaseTest;
import utilities.AIFailureAnalyzer;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;  // UI of the report
    public ExtentReports extent;  //populate common info on the report
    private static ThreadLocal<ExtentTest> testt = new ThreadLocal<>();
    public static ExtentTest test; // creating test case entries in the report and update status of the test methods
    private static final Logger log= LogManager.getLogger(ExtentReportManager.class);
    String repName;

    public void onStart(ITestContext context) {
    /*
        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt=new Date();
        String currentdatetimestamp=df.format(dt);
    */
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName="Test-Report-"+timeStamp+".html";

        sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);//specify location of the report

        sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
        sparkReporter.config().setReportName("Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Health Index Calculator");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("Tester Name","POD1");
        extent.setSystemInfo("os","Windows11");
        extent.setSystemInfo("Browser name","Chrome");

        String os=context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System",os);

        String browser=context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",browser);

        List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
        if(!includeGroups.isEmpty()){
            extent.setSystemInfo("Groups",includeGroups.toString());
        }

    }


    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName()); // create a new enty in the report
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName()+" got successfully executed"); // update status p/f/s

    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName()+" got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        BaseTest base = (BaseTest) result.getInstance();

        try{
            String impPath=base.captureScreen(result.getName());
            test.addScreenCaptureFromPath(impPath);
            byte[] sb= Files.readAllBytes(Paths.get(impPath));
            Allure.addAttachment("Screenshot ", new ByteArrayInputStream(sb));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        performAIFailureAnalysis(result);
    }

    private void performAIFailureAnalysis(ITestResult result) {
        try {
            AIFailureAnalyzer analyzer = AIFailureAnalyzer.getInstance();

            if (!analyzer.isEnabled()) {
                log.debug("AI Failure Analysis is disabled");
                return;
            }

            String testName = result.getName();
            Throwable throwable = result.getThrowable();
            String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
            String stackTrace = getStackTraceAsString(throwable);

            // Get test method parameters/arguments
            Object[] testParameters = result.getParameters();

            log.info("Performing AI analysis for failed test: " + testName);

            // Get AI analysis with test parameters
            String analysis = analyzer.analyzeFailure(testName, errorMessage, stackTrace, testParameters);

            if (analysis != null && !analysis.isEmpty()) {
                // Add to Extent Report (HTML formatted)
                String extentFormatted = analyzer.formatForExtentReport(analysis);
                if (extentFormatted != null) {
                    test.log(Status.INFO, extentFormatted);
                    log.info("AI analysis added to Extent Report");
                }

                // Add to Allure Report
                String allureFormatted = analyzer.formatForAllureReport(analysis);
                if (allureFormatted != null) {
                    Allure.addAttachment("AI Failure Analysis", "text/plain", allureFormatted);
                    log.info("AI analysis added to Allure Report");
                }
            } else {
                log.warn("AI analysis returned empty result for test: " + testName);
            }
        } catch (Exception e) {
            log.error("Error during AI failure analysis: " + e.getMessage(), e);
            // Don't fail the test reporting if AI analysis fails
        }
    }
    private String getStackTraceAsString(Throwable throwable) {
        if (throwable == null) {
            return "No stack trace available";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        // Include cause if present
        Throwable cause = throwable.getCause();
        if (cause != null) {
            sb.append("Caused by: ").append(cause.toString()).append("\n");
            for (StackTraceElement element : cause.getStackTrace()) {
                sb.append("\tat ").append(element.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+"got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }


    public void onFinish(ITestContext context) {
        extent.flush();

        //To directly open the report upon creation
        String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport=new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest getTest() {
        return testt.get();
    }

}
