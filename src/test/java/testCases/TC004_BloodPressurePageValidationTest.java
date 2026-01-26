
package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BloodPressurePage;
import pageObjects.Header;
import testBase.BaseTest;

public class TC004_BloodPressurePageValidationTest extends BaseTest {

    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    Header h;
    BloodPressurePage bpp;

    @BeforeMethod(alwaysRun = true) // ensures setup runs even when filtering by groups
    public void initPages() {
        h   = new Header(driver);
        bpp = new BloodPressurePage(driver);
    }

    @Test(
            priority = 1,
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void URLAndTitle_Matched() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: URLAndTitle_Matched");

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
        log.info("TC004_BloodPressurePageValidationTest - Starting test: isHeaderPresent");
        boolean headerPresent = h.isHeaderPresent();
        Assert.assertTrue(headerPresent, "Header not present");
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToBloodPressure() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToBloodPressure");
        h.goToBloodPressure();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#bloodPressure"),
                "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"goToBloodPressure"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isHeadingPresent() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: isHeadingPresent");
        boolean isHeadingPresent = bpp.isHeadingPresent();
        Assert.assertTrue(isHeadingPresent, "Heading not present");
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"goToBloodPressure"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isInfoPresent() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: isInfoPresent");
        boolean isInfoPresent = bpp.isInfoPresent();
        Assert.assertTrue(isInfoPresent, "Info not present");
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToHomeViaLogoText");
        h.clickLogoText();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"),
                "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHome() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToHome");
        h.goToHome();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"),
                "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToHeartPulse() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToHeartPulse");
        h.goToHeartPulse();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#pulseRate"),
                "Expected '#pulseRate' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 9,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToAgeFactor() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToAgeFactor");
        h.goToAgeFactor();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#ageFactor"),
                "Expected '#ageFactor' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToTeamDetails() {
        log.info("TC004_BloodPressurePageValidationTest - Starting test: goToTeamDetails");
        h.goToTeamDetails();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#foot"),
                "Expected '#foot' in URL. Actual: " + currentUrl);
    }
}
