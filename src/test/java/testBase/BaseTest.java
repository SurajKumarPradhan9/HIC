
package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    public static WebDriver driver;
    public Properties p;
    public Logger log;

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setUp(
            @Optional("windows") String os,
            @Optional("chrome") String browser
    ) throws IOException {

        //Loading config.properties file
        FileReader file=new FileReader("./src//test//resources//config.properties");
        p=new Properties();
        p.load(file);

        log=LogManager.getLogger(this.getClass());  //lOG4J2

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No matching os");
                return;
            }

            //browser
            switch(browser.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default: System.out.println("No matching browser"); return;
            }

            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }


        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {

            switch(browser.toLowerCase())
            {
                case "chrome" : driver=new ChromeDriver(); break;
                case "edge" : driver=new EdgeDriver(); break;
                case "firefox": driver=new FirefoxDriver(); break;
                default : System.out.println("Invalid browser name.."); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appUrl")); // reading url from properties file.
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                log.info("Driver quit successfully");
            } catch (Exception e) {
                log.warn("Error while quitting driver: {}", e.getMessage());
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void logResult(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                log.info("{} - PASSED", testName);
                break;
            case ITestResult.FAILURE:
                log.error("{} - FAILED: {}", testName, result.getThrowable());
                break;
            case ITestResult.SKIP:
                log.warn("{} - SKIPPED: {}", testName, result.getThrowable());
                break;
            default:
                log.info("{} - STATUS: {}", testName, result.getStatus());
        }
    }

    public boolean isUrlMatching(String expectedUrl) {
        return driver.getCurrentUrl().equals(expectedUrl);
    }

    public boolean isTitleMatching(String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
