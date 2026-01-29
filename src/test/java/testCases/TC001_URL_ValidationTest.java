package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CalculatorForm;
import pageObjects.Header;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC001_URL_ValidationTest extends BaseTest {

    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    HomePage hp;
    CalculatorForm cf;
    Header h;

    @BeforeMethod(alwaysRun = true) // important when running by groups
    public void initPages() {
        h  = new Header(driver);
        hp = new HomePage(driver);
        cf = new CalculatorForm(driver);
    }

    @Test(priority = 1, groups = {"Smoke","Sanity","Regression","UI","Master"})
    public void URLAndTitle_Matched() {
        log.info("TC001_URL_ValidationTest - Starting test: URLAndTitle_Matched");

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
    public void urlWorking() {
        log.info("TC001_URL_ValidationTest - Starting test: urlWorking");

        boolean headerPresent = false;
        boolean leftImagePresent = false;
        boolean calculatorPresent = false;

        // STEP 1: Header present
        try {
            log.info("STEP 1 ➜ Validate Header is present/visible");
            headerPresent = h.isHeaderPresent();
            log.debug("Header present: {}", headerPresent);

            Assert.assertTrue(headerPresent, "Header is not present/visible");
            log.info("PASS ✓ STEP 1: Header present");

        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 1 (Header): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 1 (Header) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating Header: " + e.getMessage(), e);
        }

        // STEP 2: Left image present
        try {
            log.info("STEP 2 ➜ Validate Left Image is present/visible");
            leftImagePresent = hp.isleftImagePresent();
            log.debug("Left image present: {}", leftImagePresent);

            Assert.assertTrue(leftImagePresent, "Left image is not present/visible");
            log.info("PASS ✓ STEP 2: Left image present");

        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 2 (Left image): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 2 (Left image) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating Left image: " + e.getMessage(), e);
        }

        // STEP 3: Calculator form present
        try {
            log.info("STEP 3 ➜ Validate Calculator Form is present/visible");
            calculatorPresent = cf.isCalculatorFormPresent();
            log.debug("Calculator form present: {}", calculatorPresent);

            Assert.assertTrue(calculatorPresent, "Calculator form is not present/visible");
            log.info("PASS ✓ STEP 3: Calculator form present");

        } catch (AssertionError ae) {
            log.error("ASSERTION FAILED ✗ STEP 3 (Calculator Form): {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            log.error("ERROR ✗ STEP 3 (Calculator Form) threw unexpected exception: {}", e.getMessage(), e);
            Assert.fail("Unexpected error while validating Calculator Form: " + e.getMessage(), e);
        }

        log.info("SUMMARY → Header: {}, Left Image: {}, Calculator Form: {}",
                headerPresent, leftImagePresent, calculatorPresent);
    }
}