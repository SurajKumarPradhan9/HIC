
package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AgeFactorPage;
import pageObjects.Header;
import testBase.BaseTest;

public class TC005_AgeFactorPageValidationTest extends BaseTest {

    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    Header h;
    AgeFactorPage afp;

    @BeforeMethod(alwaysRun = true) // ensures setup runs even when filtering by groups
    public void initPages() {
        h   = new Header(driver);
        afp = new AgeFactorPage(driver);
    }

    @Test(
            priority = 1,
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void URLAndTitle_Matched() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: URLAndTitle_Matched");
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
        log.info("TC005_AgeFactorPageValidationTest - Starting test: isHeaderPresent");
        boolean headerPresent = h.isHeaderPresent();
        Assert.assertTrue(headerPresent, "Header not present");
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToAgeFactor() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToAgeFactor");
        h.goToAgeFactor();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#ageFactor"), "Expected '#ageFactor' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"goToAgeFactor"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isHeadingPresent() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: isHeadingPresent");
        boolean isHeadingPresent = afp.isHeadingPresent();
        Assert.assertTrue(isHeadingPresent, "Heading not present");
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"goToAgeFactor"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isInfoPresent() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: isInfoPresent");
        boolean isInfoPresent = afp.isInfoPresent();
        Assert.assertTrue(isInfoPresent, "Info not present");
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToHomeViaLogoText");
        h.clickLogoText();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHome() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToHome");
        h.goToHome();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToHeartPulse() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToHeartPulse");
        h.goToHeartPulse();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#pulseRate"), "Expected '#pulseRate' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 9,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToBloodPressure() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToBloodPressure");
        h.goToBloodPressure();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#bloodPressure"), "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToTeamDetails() {
        log.info("TC005_AgeFactorPageValidationTest - Starting test: goToTeamDetails");
        h.goToTeamDetails();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#foot"), "Expected '#foot' in URL. Actual: " + currentUrl);
    }
}
