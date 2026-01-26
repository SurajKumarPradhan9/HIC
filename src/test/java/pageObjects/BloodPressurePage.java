package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BloodPressurePage extends Base {
    public BloodPressurePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='heading']/h2[contains(text(),'Blood Pressure')]")
    WebElement heading;

    @FindBy(xpath = " //div[@class='agepara' and  contains(normalize-space(),'Abnormal blood pressure and an aging population')]")
    WebElement info;

    public boolean isHeadingPresent(){
        return heading.isDisplayed();
    }

    public boolean isInfoPresent(){
        return info.isDisplayed();
    }
}
