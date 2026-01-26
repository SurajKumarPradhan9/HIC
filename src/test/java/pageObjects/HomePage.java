package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Base {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@class='image']//img")
    WebElement leftImage;

    public boolean isleftImagePresent(){
        return leftImage.isDisplayed();
    }
}
