
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
    public void isCalculatorFormPresent() {
        log.info("TC008_ResultPageValidationTest- Starting test: isCalculatorFormPresent");
        boolean calcFormPresent = cf.isCalculatorFormPresent();
        Assert.assertTrue(calcFormPresent, "Calculator form not present");
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

        // Enter
        cf.enterAge(age);
        cf.enterPulse(pulse);
        cf.enterBloodPressure(bp);

        // Action
        cf.clickCalculate();

        // (Optional) small wait to avoid timing issues
        String expectedUrl = "https://hackvyatharth.github.io/Vitalguard_Team_Vanguardians/result.html";
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.alertIsPresent(),
                            ExpectedConditions.urlToBe(expectedUrl)
                    ));
        } catch (Exception ignored) {}

        // Verify
        String currUrl = driver.getCurrentUrl();
        Assert.assertTrue(expectedUrl.equals(currUrl),
                "Should be redirected to result page. Actual: " + currUrl);
    }

    @Test(
            priority = 4,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    public void resultPageUICheck() {
        log.info("TC008_ResultPageValidationTest - Starting test: resultPageUICheck");
        Assert.assertTrue(h.isLogoPresent(),         "Logo should be visible");
        Assert.assertTrue(h.isLogoTextPresent(),     "Logo text should be visible");
        Assert.assertTrue(rp.isResultBoxPresent(),   "Result box should be visible");
        Assert.assertTrue(rp.isHeadingPresent(),     "Heading should be visible");
        Assert.assertTrue(rp.isResultDisplayed(),    "Result should be displayed");
        Assert.assertTrue(rp.isFeedbackBoxPresent(), "Feedback box must be present");
    }

    @Test(
            priority = 5,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    void isScrollable() {
        log.info("TC008_ResultPageValidationTest - Starting test: isScrollable");

        SoftAssert sa = new SoftAssert();
        WebElement textArea = driver.findElement(By.id("feedback"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long scrollHeight = ((Number) js.executeScript("return arguments[0].scrollHeight;", textArea)).longValue();
        long clientHeight = ((Number) js.executeScript("return arguments[0].clientHeight;", textArea)).longValue();

        sa.assertTrue(scrollHeight > clientHeight, "Result textbox should be scrollable");
        sa.assertAll();
    }

    @Test(
            priority = 6,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Sanity","Regression","UI","Master"}
    )
    void isResizable() {
        log.info("TC008_ResultPageValidationTest - Starting test: isResizable");

        SoftAssert sa = new SoftAssert();
        WebElement textArea = driver.findElement(By.id("feedback"));
        String resizeCSS = textArea.getCssValue("resize");

        sa.assertTrue(resizeCSS != null && !resizeCSS.equalsIgnoreCase("none"),
                "Result textbox should be resizable");
        sa.assertAll();
    }

    @Test(
            priority = 7,
            dependsOnMethods = {"puttingCalculatorFieldsAndNavigatingToResultPage"},
            groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void goToHomeViaLogoText() {
        log.info("TC008_ResultPageValidationTest- Starting test: goToHomeViaLogoText");
        h.clickLogoText();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("index.html"),
                "Expected 'index.html' in URL. Actual: " + currentUrl);
    }
}
