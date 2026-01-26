
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

    @Test(priority = 1 , groups = {"Smoke","Sanity","Regression","UI","Master"})
    public void URLAndTitle_Matched() {
        log.info("TC001_URL_ValidationTest - Starting test: URLAndTitle_Matched");

        boolean expectedUrlMatched = isUrlMatching(expectedUrl);
        boolean expectedTitleMatched = isTitleMatching(expectedTitle);

        Assert.assertTrue(expectedUrlMatched, "URL not matched. Actual: " + driver.getCurrentUrl());
        Assert.assertTrue(expectedTitleMatched, "Title not matched. Actual: " + driver.getTitle());
    }

    @Test(
            priority = 2,
            dependsOnMethods = {"URLAndTitle_Matched"}
            , groups = {"Smoke","Sanity","Regression","UI","Master"}
    )
    public void urlWorking() {
        log.info("TC001_URL_ValidationTest - Starting test: urlWorking");

        boolean header = h.isHeaderPresent();
        boolean leftImage = hp.isleftImagePresent();
        boolean calc = cf.isCalculatorFormPresent();

        Assert.assertTrue(
                header && leftImage && calc,
                "URL Working Validation Failed: Expected all elements to be present (Header, Left Image, Calculator Form), "
                        + "but found -> Header: " + header + ", Left Image: " + leftImage + ", Calculator Form: " + calc
        );
    }
}
