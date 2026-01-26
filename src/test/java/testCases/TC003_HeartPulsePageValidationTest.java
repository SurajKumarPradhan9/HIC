
package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Header;
import pageObjects.HeartPulsePage;
import testBase.BaseTest;

public class TC003_HeartPulsePageValidationTest extends BaseTest {

    String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/";
    String expectedTitle = "VitalGuard";

    Header h;
    HeartPulsePage hpp;

    @BeforeMethod(alwaysRun = true) // ensures setup runs even when filtering by groups
    public void initPages() {
        h   = new Header(driver);
        hpp = new HeartPulsePage(driver);
    }

    @Test(
            priority = 1,
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void URLAndTitle_Matched() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: URLAndTitle_Matched");
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
        log.info("TC003_HeartPulsePageValidationTest - Starting test: isHeaderPresent");
        boolean headerPresent = h.isHeaderPresent();
        Assert.assertTrue(headerPresent, "Header not present");
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHeartPulse() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToHeartPulse");
        h.goToHeartPulse();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#pulseRate"), "Expected '#pulseRate' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"goToHeartPulse"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isHeadingPresent() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: isHeadingPresent");
        boolean isHeadingPresent = hpp.isHeadigPresent();
        Assert.assertTrue(isHeadingPresent, "Heading not present");
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"goToHeartPulse"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isSubHeadingPresent() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: isSubHeadingPresent");
        boolean isSubHeadingPresent = hpp.isSubHeadingPresent();
        Assert.assertTrue(isSubHeadingPresent, "Sub heading not present");
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"goToHeartPulse"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isInfoPresent() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: isInfoPresent");
        boolean isInfoPresent = hpp.isInfoPresent();
        Assert.assertTrue(isInfoPresent, "Info not present");
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"goToHeartPulse"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void isTablePresent() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: isTablePresent");
        boolean isTablePresent = hpp.isTablePresent();
        Assert.assertTrue(isTablePresent, "Table not present");
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToHomeViaLogoText");
        h.clickLogoText();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 9,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHome() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToHome");
        h.goToHome();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToBloodPressure() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToBloodPressure");
        h.goToBloodPressure();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#bloodPressure"), "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 11,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToAgeFactor() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToAgeFactor");
        h.goToAgeFactor();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#ageFactor"), "Expected '#ageFactor' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 12,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void goToTeamDetails() {
        log.info("TC003_HeartPulsePageValidationTest - Starting test: goToTeamDetails");
        h.goToTeamDetails();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#foot"), "Expected '#foot' in URL. Actual: " + currentUrl);
    }
}
