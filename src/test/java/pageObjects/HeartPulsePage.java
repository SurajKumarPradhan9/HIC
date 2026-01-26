package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeartPulsePage extends Base{
    public HeartPulsePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@class='heading']//h2[contains(text(),'heart rate?')]")
    WebElement heading;

    @FindBy(xpath="//div[@class='agepara']//h4")
    WebElement subHeading;

    @FindBy(xpath="//div[@class='agepara' and contains(normalize-space(),'A healthy heart')] ")
    WebElement info;

    @FindBy(xpath="//div[@class='agepara']//img")
    WebElement table;

    public boolean isHeadigPresent(){
        return heading.isDisplayed();
    }

    public boolean isSubHeadingPresent(){
        return subHeading.isDisplayed();
    }

    public boolean isInfoPresent(){
        return info.isDisplayed();
    }

    public boolean isTablePresent(){
        return table.isDisplayed();
    }
}
