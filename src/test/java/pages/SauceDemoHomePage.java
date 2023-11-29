package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBase;
import java.io.IOException;
import java.util.*;

public class SauceDemoHomePage extends TestBase {

    @FindBy(xpath =".//input[@id='user-name']")
    private WebElement userNameField;

    @FindBy(xpath =".//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath =".//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath =".//h3[@data-test='error']")
    private WebElement errorMessage;

    @FindBy(xpath =".//span[@class='title']")
    private WebElement productsLabel;

    @FindBy(xpath =".//li[@class='social_twitter']")
    private WebElement twitter;

    @FindBy(xpath =".//li[@class='social_facebook']")
    private WebElement facebook;

    @FindBy(xpath =".//li[@class='social_linkedin']")
    private WebElement linkedin;

    @FindBy(xpath =".//div[@class='footer_copy']")
    private WebElement privacyPolicy;

    @FindBy(xpath =".//p[@class='contextual-sign-in-modal__join-now m-auto font-sans text-md text-color-text']")
    private WebElement linkedInNote;

    @FindBy(xpath =".//span[@class='x1lliihq x6ikm8r x10wlt62 x1n2onr6 xg8j3zb']")
    private WebElement facebookNote;


    public static List<Map<String,String>> credentialsFromExcelSheet;
    private WebDriver driver;


    public SauceDemoHomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public String loginWithWrongCredentials()  {
        userNameField.sendKeys("fake_user");
        passwordField.sendKeys("fake_pw");
        loginButton.click();
        return errorMessage.getText();
    }

    public String getWebPageTitle()  {return driver.getTitle();}

    public String loginWithoutCredentials()  {
        userNameField.sendKeys("");
        passwordField.sendKeys("");
        loginButton.click();
        return errorMessage.getText();
    }

    public String loginWithoutPassword() throws IOException  {
        //credentialsFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        //userNameField.sendKeys(credentialsFromExcelSheet.get(1).get("username"));
        //passwordField.sendKeys(credentialsFromExcelSheet.get(1).get("password"));
        userNameField.sendKeys("fake_user");
        passwordField.sendKeys("");
        loginButton.click();
        return errorMessage.getText();
    }

    public void correctLogin() throws IOException {
        //credentialsFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        //userNameField.sendKeys(credentialsFromExcelSheet.get(0).get("username"));
        //passwordField.sendKeys(credentialsFromExcelSheet.get(0).get("password"));
        userNameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
    }

    public void acceptAlert() throws InterruptedException {
        Thread.sleep(3000);
        driver.switchTo( ).alert( ).accept();
    }

    public String myPage()  {return productsLabel.getText();}

    public String lockedUserLogin() throws IOException {
        //credentialsFromExcelSheet = ExcelDataProvider.getCredentialsTestData();
        //userNameField.sendKeys(credentialsFromExcelSheet.get(0).get("username"));
        //passwordField.sendKeys(credentialsFromExcelSheet.get(0).get("password"));
        userNameField.sendKeys("locked_out_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        return errorMessage.getText();
    }

    public void credentialsLoginFromWebPage(String username, String password) {
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public int totalSocialsMediaInFooter() {
        List<String> socials = new ArrayList<>();
        socials.add(twitter.getText());
        socials.add(facebook.getText());
        socials.add(linkedin.getText());
        System.out.println(twitter.getText()+" -- "+facebook.getText()+" -- "+linkedin.getText());
        return socials.size();
    }

    public String linkedin() {
        linkedin.click();
        goToChildWindow();
        String note = linkedInNote.getText();
        return note;
    }

    public String facebook() {
        facebook.click();
        goToChildWindow();
        String note = facebookNote.getText();
        return note;
    }

    public String twitter() {
        twitter.click();
        goToChildWindow();
        return driver.getCurrentUrl();
    }

    public String readPolicy(){
        System.out.println(privacyPolicy.getText());
        String[] splitPolicy = privacyPolicy.getText().split(" ");
        return splitPolicy[12];}

}
