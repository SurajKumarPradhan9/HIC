
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
        log.info("TC002_HomePageValidationTest - Starting test: isHeaderPresent");
        boolean headerPresent = h.isHeaderPresent();
        Assert.assertTrue(headerPresent, "Header not present");
    }

    @Test(
            priority = 3,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC002_HomePageValidationTest - Starting test: goToHomeViaLogoText");
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
        log.info("TC002_HomePageValidationTest - Starting test: goToHome");
        h.goToHome();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#container"), "Expected '#container' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"} // usually not needed for Smoke
    )
    public void goToHeartPulse() {
        log.info("TC002_HomePageValidationTest - Starting test: goToHeartPulse");
        h.goToHeartPulse();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#pulseRate"), "Expected '#pulseRate' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"} // usually not needed for Smoke
    )
    public void goToBloodPressure() {
        log.info("TC002_HomePageValidationTest - Starting test: goToBloodPressure");
        h.goToBloodPressure();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#bloodPressure"), "Expected '#bloodPressure' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"} // usually not needed for Smoke
    )
    public void goToAgeFactor() {
        log.info("TC002_HomePageValidationTest - Starting test: goToAgeFactor");
        h.goToAgeFactor();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("#ageFactor"), "Expected '#ageFactor' in URL. Actual: " + currentUrl);
    }

    @Test(
            priority = 8,
            dependsOnMethods = {"isHeaderPresent"},
            groups = {"Sanity","Regression","UI","Master"} // usually not needed for Smoke
    )
    public void goToTeamDetails() {
        log.info("TC002_HomePageValidationTest - Starting test: goToTeamDetails");
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
        log.info("TC002_HomePageValidationTest - Starting test: isLeftImagePresent");
        boolean leftImagePresent = hp.isleftImagePresent();
        Assert.assertTrue(leftImagePresent, "Left image not present");
    }

    @Test(
            priority = 10,
            dependsOnMethods = {"URLAndTitle_Matched"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void isCalculatorFormPresent() {
        log.info("TC002_HomePageValidationTest - Starting test: isCalculatorFormPresent");
        boolean calcFormPresent = cf.isCalculatorFormPresent();
        Assert.assertTrue(calcFormPresent, "Calculator form not present");
    }

    @Test(
            priority = 11,
            dependsOnMethods = {"isCalculatorFormPresent"},
            groups = {"Sanity","Regression","UI","Functionality","Master"} // more than just presence
    )
    public void hasCalculatorFieldsAndWorking() {
        log.info("TC002_HomePageValidationTest - Starting test: hasCalculatorFieldsAndWorking");
        boolean calculatorFieldsAndWorking = cf.hasCalculatorFieldsAndWorking();
        Assert.assertTrue(calculatorFieldsAndWorking, "Calculator fields not working");
    }
}
