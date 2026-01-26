package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgeFactorPage extends Base{
    public AgeFactorPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@class='heading']/h2[contains(text(),'Common Health Conditions associated with ageing!')]")
    WebElement heading;

    @FindBy(xpath="//div[@class='agepara' and  contains(normalize-space(),'Common conditions in older age include hearing loss')]")
    WebElement info;

    public boolean isHeadingPresent(){
        return heading.isDisplayed();
    }

    public boolean isInfoPresent(){
        return info.isDisplayed();
    }
}
