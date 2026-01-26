package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends Base{
    public ResultPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@class='result-container']")
    WebElement resultBox;

    @FindBy(tagName = "h2")
    WebElement heading;

    @FindBy(id="healthbox")
    WebElement result;

    @FindBy(id="feedback")
    WebElement feedbackBox;

    public boolean isResultBoxPresent(){
        return resultBox.isDisplayed();
    }
    public boolean isHeadingPresent() {
        return heading.isDisplayed();
    }
    public boolean isResultDisplayed(){
        return result.isDisplayed();
    }
    public boolean isFeedbackBoxPresent(){
        return feedbackBox.isDisplayed();
    }
}
