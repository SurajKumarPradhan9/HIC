package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamDetails extends Base{
    public TeamDetails(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//footer/h3")
    WebElement heading;

    @FindBy(tagName = "ul")
    WebElement teamMembers;

    public boolean isHeadingPresent(){
        return heading.isDisplayed();
    }

    public boolean isTeamDetailsPresent(){
        return teamMembers.isDisplayed();
    }
}
