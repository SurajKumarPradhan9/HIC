package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CalculatorForm;
import pageObjects.Header;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC002_HomePageValidationTest extends BaseTest {

    HomePage hp;
    Header h;
    CalculatorForm cf;
    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

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
        log.info("TC002_HomePageValidationTest - Starting test: URLAndTitle_Matched");

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
    public void isHeaderPresent() {
        log.info("TC002_HomePageValidationTest - Starting test: isHeaderPresent");

        try {
            log.info("STEP ➜ Validate Header is present/visible");
            boolean headerPresent = h.isHeaderPresent();
            log.debug("Header present: {}", headerPresent);

            Assert.assertTrue(headerPresent, "Header not present");
            log.info("PASS ✓ STEP: Header present");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Header present): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Header present) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while checking Header presence: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC002_HomePageValidationTest - Starting test: goToHomeViaLogoText");

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
            log.info("STEP 2 ➜ Validate URL contains '#container'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#container"),
                    "Expected '#container' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#container'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#container'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#container') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#container': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHome() {
        log.info("TC002_HomePageValidationTest - Starting test: goToHome");

        try {
            log.info("STEP 1 ➜ Click Home");
            h.goToHome();
            log.info("PASS ✓ STEP 1: Clicked Home");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Home): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Home) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Home: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate URL contains '#container'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#container"),
                    "Expected '#container' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#container'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#container'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#container') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#container': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToHeartPulse() {
        log.info("TC002_HomePageValidationTest - Starting test: goToHeartPulse");

        try {
            log.info("STEP 1 ➜ Click Heart Pulse");
            h.goToHeartPulse();
            log.info("PASS ✓ STEP 1: Clicked Heart Pulse");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Heart Pulse): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Heart Pulse) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Heart Pulse: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate URL contains '#pulseRate'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#pulseRate"),
                    "Expected '#pulseRate' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#pulseRate'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#pulseRate'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#pulseRate') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#pulseRate': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToBloodPressure() {
        log.info("TC002_HomePageValidationTest - Starting test: goToBloodPressure");

        try {
            log.info("STEP 1 ➜ Click Blood Pressure");
            h.goToBloodPressure();
            log.info("PASS ✓ STEP 1: Clicked Blood Pressure");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Blood Pressure): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Blood Pressure) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Blood Pressure: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate URL contains '#bloodPressure'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#bloodPressure"),
                    "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#bloodPressure'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#bloodPressure'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#bloodPressure') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#bloodPressure': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToAgeFactor() {
        log.info("TC002_HomePageValidationTest - Starting test: goToAgeFactor");

        try {
            log.info("STEP 1 ➜ Click Age Factor");
            h.goToAgeFactor();
            log.info("PASS ✓ STEP 1: Clicked Age Factor");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Age Factor): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Age Factor) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Age Factor: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate URL contains '#ageFactor'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#ageFactor"),
                    "Expected '#ageFactor' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#ageFactor'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#ageFactor'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#ageFactor') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#ageFactor': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToTeamDetails() {
        log.info("TC002_HomePageValidationTest - Starting test: goToTeamDetails");

        try {
            log.info("STEP 1 ➜ Click Team Details");
            h.goToTeamDetails();
            log.info("PASS ✓ STEP 1: Clicked Team Details");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Click Team Details): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Click Team Details) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while clicking Team Details: " + e.getMessage(), e);
        }

        try {
            log.info("STEP 2 ➜ Validate URL contains '#foot'");
            String currentUrl = driver.getCurrentUrl();
            log.debug("Current URL: {}", currentUrl);

            Assert.assertTrue(currentUrl.contains("#foot"),
                    "Expected '#foot' in URL. Actual: " + currentUrl);
            log.info("PASS ✓ STEP 2: URL contains '#foot'");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (URL contains '#foot'): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (URL contains '#foot') threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating URL for '#foot': " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 9,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isLeftImagePresent() {
        log.info("TC002_HomePageValidationTest - Starting test: isLeftImagePresent");

        try {
            log.info("STEP ➜ Validate Left Image is present/visible");
            boolean leftImagePresent = hp.isleftImagePresent();
            log.debug("Left image present: {}", leftImagePresent);

            Assert.assertTrue(leftImagePresent, "Left image not present");
            log.info("PASS ✓ STEP: Left image present");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Left image present): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Left image present) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while checking Left image presence: " + e.getMessage(), e);
        }
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isCalculatorFormPresent() {
        log.info("TC002_HomePageValidationTest - Starting test: isCalculatorFormPresent");

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
            priority = 11,
            dependsOnMethods = {"isCalculatorFormPresent"},
            groups = {"Sanity","Regression","UI","Functionality","Master"}
    )
    public void hasCalculatorFieldsAndWorking() {
        log.info("TC002_HomePageValidationTest - Starting test: hasCalculatorFieldsAndWorking");

        try {
            log.info("STEP ➜ Validate Calculator fields and basic interactions");
            boolean calculatorFieldsAndWorking = cf.hasCalculatorFieldsAndWorking();
            log.debug("Calculator fields working: {}", calculatorFieldsAndWorking);

            Assert.assertTrue(calculatorFieldsAndWorking, "Calculator fields not working");
            log.info("PASS ✓ STEP: Calculator fields working as expected");
        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ (Calculator fields working): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ (Calculator fields working) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating Calculator fields: " + e.getMessage(), e);
        }
    }
}