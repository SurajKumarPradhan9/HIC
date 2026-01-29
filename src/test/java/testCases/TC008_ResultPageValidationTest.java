package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.CalculatorForm;
import pageObjects.Header;
import pageObjects.ResultPage;
import testBase.BaseTest;

import java.time.Duration;

public class TC008_ResultPageValidationTest extends BaseTest {
    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    ResultPage rp;
    Header h;
    CalculatorForm cf;

    @BeforeMethod(alwaysRun = true) // ensures setup runs even when filtering by groups
    public void initPages() {
        h  = new Header(driver);
        rp = new ResultPage(driver);
        cf = new CalculatorForm(driver);
    }

    @Test(
            priority = 1,
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void URLAndTitle_Matched() {
        log.info("TC008_ResultPageValidationTest- Starting test: URLAndTitle_Matched");

        // STEP 1: Validate URL
        try {
            log.info("STEP 1 ➜ Validate current URL matches expected");
            log.debug("Expected URL: {}", expectedUrl);

            boolean expectedUrlMatched = isUrlMatching(expectedUrl);
            String actualUrl = driver.getCurrentUrl();
            log.debug("Actual URL: {}", actualUrl);

            Assert.assertTrue(expectedUrlMatched, "URL not matched. Actual: " + actualUrl);
            log.info("PASS ✓ STEP 1: URL matched");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (URL validation): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (URL validation) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error during URL validation: " + e.getMessage(), e);
        }

        // STEP 2: Validate Title
        try {
            log.info("STEP 2 ➜ Validate page title matches expected");
            log.debug("Expected Title: {}", expectedTitle);

            boolean expectedTitleMatched = isTitleMatching(expectedTitle);
            String actualTitle = driver.getTitle();
            log.debug("Actual Title: {}", actualTitle);

            Assert.assertTrue(expectedTitleMatched, "Title not matched. Actual: " + actualTitle);
            log.info("PASS ✓ STEP 2: Title matched");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (Title validation): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (Title validation) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error during Title validation: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 2,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isCalculatorFormPresent() {
        log.info("TC008_ResultPageValidationTest- Starting test: isCalculatorFormPresent");

        try {
            log.info("STEP ➜ Validate Calculator Form is present/visible");
            boolean calcFormPresent = cf.isCalculatorFormPresent();
            log.debug("Calculator form present: {}", calcFormPresent);

            Assert.assertTrue(calcFormPresent, "Calculator form not present");
            log.info("PASS ✓ STEP: Calculator form present");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Calculator form present): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Calculator form present) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while checking Calculator form presence: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 3,
            dataProvider = "resultPageCheck",
            dataProviderClass = utilities.MainDataProvider.class,
            dependsOnMethods = {"isCalculatorFormPresent"},
            groups = {"Sanity","Regression","Functionality","DataValidation","UI","Master"}
    )
    public void puttingCalculatorFieldsAndNavigatingToResultPage(String age, String pulse, String bp) {
        log.info("TC008_ResultPageValidationTest - Starting test: puttingCalculatorFieldsAndNavigatingToResultPage");

        // STEP 1: Enter inputs
        try {
            log.info("STEP 1 ➜ Enter input values (age, pulse, bp)");
            log.debug("Age: {}, Pulse: {}, BP: {}", age, pulse, bp);

            cf.enterAge(age);
            cf.enterPulse(pulse);
            cf.enterBloodPressure(bp);

            log.info("PASS ✓ STEP 1: Entered all inputs");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Enter inputs): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Enter inputs) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while entering input values: " + e.getMessage(), e);
        }

        // STEP 2: Click calculate
        try {
            log.info("STEP 2 ➜ Click Calculate button");
            cf.clickCalculate();
            log.info("PASS ✓ STEP 2: Clicked Calculate");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (Click Calculate): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (Click Calculate) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Calculate: " + e.getMessage(), e);
        }

        // STEP 3: Wait for alert or URL to be result page (timing safety)
        String resultUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/result.html";
        try {
            log.info("STEP 3 ➜ Wait up to 3s for alert OR navigation to result page");
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.alertIsPresent(),
                            ExpectedConditions.urlToBe(resultUrl)
                    ));
            log.info("PASS ✓ STEP 3: One of the expected conditions occurred");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 3 (Wait condition): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            // Keep behavior same as original (was catching and ignoring),
            // but now we log it for traceability
            log.error("No alert or URL change detected within timeout (continuing to assert URL). Details: {}", e.getMessage(), e);
        }

        // STEP 4: Verify redirection to result page
        try {
            log.info("STEP 4 ➜ Verify redirection to result page");
            String currUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currUrl);

            Assert.assertTrue(resultUrl.equals(currUrl),
                    "Should be redirected to result page. Actual: " + currUrl);
            log.info("PASS ✓ STEP 4: Navigated to result page");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 4 (Verify result page URL): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 4 (Verify result page URL) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while verifying result page URL: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void resultPageUICheck() {
        log.info("TC008_ResultPageValidationTest - Starting test: resultPageUICheck");

        try {
            log.info("STEP ➜ Validate presence/visibility of key UI elements on Result page");
            Assert.assertTrue(h.isLogoPresent(),         "Logo should be visible");
            Assert.assertTrue(h.isLogoTextPresent(),     "Logo text should be visible");
            Assert.assertTrue(rp.isResultBoxPresent(),   "Result box should be visible");
            Assert.assertTrue(rp.isHeadingPresent(),     "Heading should be visible");
            Assert.assertTrue(rp.isResultDisplayed(),    "Result should be displayed");
            Assert.assertTrue(rp.isFeedbackBoxPresent(), "Feedback box must be present");
            log.info("PASS ✓ STEP: Result page UI elements are present");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Result page UI checks): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Result page UI checks) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error during Result page UI validation: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    void isScrollable() {
        log.info("TC008_ResultPageValidationTest - Starting test: isScrollable");

        SoftAssert sa = new SoftAssert();
        try {
            log.info("STEP ➜ Validate result feedback textbox is scrollable");
            WebElement textArea = driver.findElement(By.id("feedback"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            long scrollHeight = ((Number) js.executeScript("return arguments[0].scrollHeight;", textArea)).longValue();
            long clientHeight = ((Number) js.executeScript("return arguments[0].clientHeight;", textArea)).longValue();
            log.debug("scrollHeight={}, clientHeight={}", scrollHeight, clientHeight);

            sa.assertTrue(scrollHeight > clientHeight, "Result textbox should be scrollable");
            log.info("PASS ✓ STEP: Scrollability validated (using SoftAssert)");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Scrollable check): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Scrollable check) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while checking scrollability: " + e.getMessage(), e);
        } finally {
            sa.assertAll();
        }
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    void isResizable() {
        log.info("TC008_ResultPageValidationTest - Starting test: isResizable");

        SoftAssert sa = new SoftAssert();
        try {
            log.info("STEP ➜ Validate result feedback textbox is resizable");
            WebElement textArea = driver.findElement(By.id("feedback"));
            String resizeCSS = textArea.getCssValue("resize");
            log.debug("CSS 'resize' value: {}", resizeCSS);

            sa.assertTrue(resizeCSS != null && !resizeCSS.equalsIgnoreCase("none"),
                    "Result textbox should be resizable");
            log.info("PASS ✓ STEP: Resizability validated (using SoftAssert)");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Resizable check): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Resizable check) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while checking resizability: " + e.getMessage(), e);
        } finally {
            sa.assertAll();
        }
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC008_ResultPageValidationTest- Starting test: goToHomeViaLogoText");

        try {
            log.info("STEP 1 ➜ Click Logo Text");
            h.clickLogoText();
            log.info("PASS ✓ STEP 1: Clicked Logo Text");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Logo Text): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Logo Text) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Logo Text: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate redirected back to home (URL contains 'index.html')");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("index.html"),
                    "Expected 'index.html' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: Home redirection validated");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (Home URL check): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (Home URL check) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating home URL: " + e.getMessage(), e);
        }
    }
}