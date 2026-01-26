package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorForm extends Base{

    public CalculatorForm(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath="//div[@class='calc']")
    WebElement calculatorForm;

    @FindBy(xpath = "//div[@class='calc']//h2")
    WebElement formHeading;

    @FindBy(xpath = "//input[@id='age']")
    WebElement ageField;

    @FindBy(xpath = "//input[@id='pulse']")
    WebElement pulseField;

    @FindBy(xpath = "//input[@id='bp']")
    WebElement bpField;

    @FindBy(xpath = "//button[text()='Calculate']")
    WebElement calculateBtn;

    public boolean isCalculatorFormPresent(){
        return calculatorForm.isDisplayed();
    }

    public boolean isFormHeadingPresent(){
        return formHeading.isDisplayed();
    }
    public boolean isAgeFieldPresentAndEnabled(){
        return ageField.isDisplayed();
    }
    public boolean isPulseRateFieldPresentAndEnabled(){
        return pulseField.isDisplayed();
    }
    public boolean isBloodPressureFieldPresentAndEnabled(){
        return bpField.isDisplayed();
    }
    public boolean isButtonPresentAndClickable(){
        return calculateBtn.isDisplayed() && calculateBtn.isEnabled();
    }
    public boolean hasCalculatorFieldsAndWorking() {
        return isFormHeadingPresent()
                && isAgeFieldPresentAndEnabled()
                && isPulseRateFieldPresentAndEnabled()
                && isBloodPressureFieldPresentAndEnabled()
                && isButtonPresentAndClickable();
    }

    public void enterAge(String age) {
        ageField.clear();
        ageField.sendKeys(age);
    }
    public void enterPulse(String pulse) {
        pulseField.clear();
        pulseField.sendKeys(pulse);
    }
    public void enterBloodPressure(String bp) {
        bpField.clear();
        bpField.sendKeys(bp);
    }
    public void clickCalculate() {
        calculateBtn.click();
    }
}
