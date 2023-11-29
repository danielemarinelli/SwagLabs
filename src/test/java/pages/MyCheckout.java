package pages;

import core.ExcelDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyCheckout extends TestBase {

    @FindBy(xpath =".//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath =".//h3[@data-test='error']")
    private WebElement errorDisplayed;

    @FindBy(xpath =".//input[@id='first-name']")
    private WebElement firstNameField;

    @FindBy(xpath =".//input[@id='last-name']")
    private WebElement lastNameField;

    @FindBy(xpath =".//input[@id='postal-code']")
    private WebElement postalCodeField;

    @FindBy(xpath =".//span[@class='title']")
    private WebElement checkoutTitle;

    @FindBy(xpath ="(.//div[@class='summary_info_label'])[1]")
    private WebElement paymentLabel;

    @FindBy(xpath ="(.//div[@class='summary_info_label'])[2]")
    private WebElement shippingLabel;

    @FindBy(xpath ="(.//div[@class='summary_info_label'])[3]")
    private WebElement priceLabel;

    @FindBy(xpath ="(.//div[@class='summary_value_label'])[1]")
    private WebElement paymentLabelValue;

    @FindBy(xpath ="(.//div[@class='summary_value_label'])[2]")
    private WebElement shippingLabelValue;

    @FindBy(xpath =".//div[@class='summary_info_label summary_total_label']")
    private WebElement totalPriceValue;

    @FindBy(xpath =".//div[@class='summary_tax_label']")
    private WebElement taxValue;

    @FindBy(xpath =".//button[@id='finish']")
    private WebElement finishButton;



    public static List<Map<String,String>> personalInformationFromExcelSheet;
    private WebDriver driver;

    public MyCheckout(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String noInfoPassed(){
        continueButton.click();
        return errorDisplayed.getText();  //checking first name error message
    }

    public String onlyFirstNamePassed() throws IOException {
        //personalInformationFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        firstNameField.sendKeys("Jake");
        //firstNameField.sendKeys(personalInformationFromExcelSheet.get(1).get("first name"));
        continueButton.click();
        return errorDisplayed.getText();  //checking last name error message
    }

    public String missingPostalCodeInformation() throws IOException {
        //personalInformationFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        firstNameField.sendKeys("Jake");
        lastNameField.sendKeys("Kelly");
        //firstNameField.sendKeys(personalInformationFromExcelSheet.get(1).get("first name"));
        //lastNameField.sendKeys(personalInformationFromExcelSheet.get(1).get("last name"));
        continueButton.click();
        return errorDisplayed.getText();  //checking postal code error message
    }

    public String allInformationPassedForCheckout() throws IOException {
        //personalInformationFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        firstNameField.sendKeys("Jake");
        lastNameField.sendKeys("Kelly");
        postalCodeField.sendKeys("14221");
        //firstNameField.sendKeys(personalInformationFromExcelSheet.get(1).get("first name"));
        //lastNameField.sendKeys(personalInformationFromExcelSheet.get(1).get("last name"));
        continueButton.click();
        String[] splitTitle = checkoutTitle.getText().split(" ");
        return splitTitle[1];
    }

    public String displayInformationCheckoutOverview()  {
        System.out.println(paymentLabel.getText()+" =====>>> "+paymentLabelValue.getText());
        System.out.println("###########");
        System.out.println(shippingLabel.getText()+" =====>>> "+shippingLabelValue.getText());
        System.out.println("###########");
        String[] price = totalPriceValue.getText().split(" ");
        String[] tax = taxValue.getText().split(" ");
        System.out.println(priceLabel.getText()+" =====>>> "+price[1]+ " with Tax equal to: "+tax[1]);
        System.out.println("###########");
        finishButton.click();
        String[] splitTitle = checkoutTitle.getText().split(" ");
        return splitTitle[1];
    }


}
