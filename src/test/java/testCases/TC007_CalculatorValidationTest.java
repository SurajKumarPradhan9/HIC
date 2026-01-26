
package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.CalculatorForm;
import pageObjects.Header;
import pageObjects.HomePage;
import testBase.BaseTest;

import java.time.Duration;

public class TC007_CalculatorValidationTest extends BaseTest {
    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    Header h;
    HomePage hp;
    CalculatorForm cf;

    @BeforeMethod(alwaysRun = true) // ensures setup runs even when filtering by groups
    public void initPages() {
        h  = new Header(driver);
        hp = new HomePage(driver);
        cf = new CalculatorForm(driver);
    }

    @Test(
            priority = 1,
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void URLAndTitle_Matched() {
        log.info("TC007_CalculatorValidationTest - Starting test: URLAndTitle_Matched");
        boolean expectedUrlMatched   = isUrlMatching(expectedUrl);
        boolean expectedTitleMatched = isTitleMatching(expectedTitle);

        Assert.assertTrue(expectedUrlMatched, "URL not matched. Actual: " + driver.getCurrentUrl());
        Assert.assertTrue(expectedTitleMatched, "Title not matched. Actual: " + driver.getTitle());
    }

    @Test(
            priority = 2,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isHeaderPresent() {
        log.info("TC007_CalculatorValidationTest - Starting test: isHeaderPresent");
        boolean headerPresent = h.isHeaderPresent();
        Assert.assertTrue(headerPresent, "Header not present");
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToHomeViaLogoText");
        h.clickLogoText();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHome() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToHome");
        h.goToHome();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"} // keep Smoke minimal
    )
    public void goToHeartPulse() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToHeartPulse");
        h.goToHeartPulse();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#pulseRate"), "Expected '#pulseRate' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToBloodPressure() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToBloodPressure");
        h.goToBloodPressure();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#bloodPressure"), "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToAgeFactor() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToAgeFactor");
        h.goToAgeFactor();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#ageFactor"), "Expected '#ageFactor' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToTeamDetails() {
        log.info("TC007_CalculatorValidationTest - Starting test: goToTeamDetails");
        h.goToTeamDetails();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#foot"), "Expected '#foot' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 9,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isLeftImagePresent() {
        log.info("TC007_CalculatorValidationTest - Starting test: isLeftImagePresent");
        boolean leftImagepresent = hp.isleftImagePresent();
        Assert.assertTrue(leftImagepresent, "Left image not present");
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isCalculatorFormPresent() {
        log.info("TC007_CalculatorValidationTest - Starting test: isCalculatorFormPresent");
        boolean calcFormPresent = cf.isCalculatorFormPresent();
        Assert.assertTrue(calcFormPresent, "Calculator form not present");
    }

    @Test(
            priority = 11,
            dependsOnMethods = {"isCalculatorFormPresent"},
            groups = {"Sanity","Regression","UI","Functionality","Master"}
    )
    public void hasCalculatorFieldsAndWorking() {
        log.info("TC007_CalculatorValidationTest - Starting test: hasCalculatorFieldsAndWorking");
        boolean calculatorFieldsAndWorking = cf.hasCalculatorFieldsAndWorking();
        Assert.assertTrue(calculatorFieldsAndWorking, "Calculator fields not working");
    }

    @Test(
            priority = 12,
            dataProvider = "dp",
            dataProviderClass = utilities.MainDataProvider.class,
            dependsOnMethods = {"hasCalculatorFieldsAndWorking"},
            groups = {"Sanity","Regression","Functionality","DataValidation","Master"} // core data validation
    )
    public void setDataAndCalculate(String age, String pulse, String bp,
                                    String testType, String testResult) {

        log.info("TC007_CalculatorValidationTest - Starting test: setDataAndCalculate");

        // Enter inputs
        try {
            log.info("Entering Age: " + age);
            cf.enterAge(age);

            log.info("Entering Pulse Rate: " + pulse);
            cf.enterPulse(pulse);

            log.info("Entering Blood Pressure: " + bp);
            cf.enterBloodPressure(bp);
        } catch (Exception ex) {
            log.error("Exception while entering input values: " + ex.getMessage());
        }

        String type = testType.toLowerCase();
        SoftAssert sa = new SoftAssert();

        // Trigger calculation
        log.info("Clicking Calculate button");
        cf.clickCalculate();

        // Alert handling
        Alert alert = null;
        try {
            log.info("Waiting for alert presence (3 seconds)");
            alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ignored) {
            log.error("No alert appeared within timeout");
        }

        boolean validInputs = type.equalsIgnoreCase("positive");
        boolean invalidInputs = type.equalsIgnoreCase("negative");

        if (alert != null) {
            log.info("Alert appeared. Accepting alert.");
            alert.accept();
            if (invalidInputs) {
                log.info("Alert appeared for invalid inputs as expected");
                sa.assertTrue(true, "Invalid inputs are generating alerts");
            } else if (validInputs) {
                log.error("Unexpected alert for valid inputs");
                sa.fail("Valid inputs are generating alerts");
            } else {
                log.error("Unrecognized test type: " + testType);
                sa.fail("Unrecognized test type led to alert handling ambiguity");
            }
        } else {
            log.info("No alert appeared. Verifying navigation result.");
            String currUrl = driver.getCurrentUrl();
            String resultUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/result.html";
            log.info("Current URL: " + currUrl);

            if (validInputs) {
                log.info("Expecting navigation to result page for valid inputs");
                boolean navigated = currUrl.equals(resultUrl);
                if (navigated) {
                    log.info("Navigation to result page succeeded for valid inputs");
                    sa.assertTrue(true, "Valid inputs navigated to the result page");
                    log.info("Navigating back to the previous page");
                    driver.navigate().back();
                } else {
                    log.error("Navigation to result page failed for valid inputs");
                    sa.fail("Valid inputs did not navigate to the result page");
                }
            } else if (invalidInputs) {
                log.info("Expecting no navigation to result page for invalid inputs");
                boolean navigated = currUrl.equals(resultUrl);
                if (navigated) {
                    log.error("Unexpected navigation to result page for invalid inputs");
                    sa.fail("Invalid inputs navigated to the result page");
                    log.info("Navigating back to the previous page");
                    driver.navigate().back();
                } else {
                    log.info("No navigation occurred for invalid inputs as expected");
                    sa.assertTrue(true, "Invalid inputs did not navigate to the result page");
                }
            } else {
                log.error("Unrecognized test type: " + testType);
                sa.fail("Unrecognized test type: " + testType);
            }
        }
        sa.assertAll();

        /*
        For Learning purpose only
        @AfterMethod in TestNG runs after every test method in the class.
        If you add a parameter Method method to your @AfterMethod, TestNG will automatically tell you which test just finished.
        method.getName() returns the name of the test method that just ran (for example, "setDataAndCalculate").
        Inside @AfterMethod, you use an if-condition to check this name.
        Because of this check, your redirect logic runs only when the test that just finished is "setDataAndCalculate".
        For the other 11 tests, the condition is false, so nothing happens.
        This means the @AfterMethod will be called after every test, but the code inside the IF block runs only for the one test you choose.
         */
        // @AfterMethod
        // public void redirectOnlyAfterThisTest(Method method) throws IOException {
        //     if ("setDataAndCalculate".equals(method.getName())) {
        //         String URL = p.getProperty("appUrl");
        //         driver.get(URL);
        //         driver.manage().window().maximize();
        //     }
        // }
    }
}
