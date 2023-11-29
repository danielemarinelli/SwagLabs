package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBase;


public class LastPage extends TestBase {

    @FindBy(xpath =".//h2[@class='complete-header']")
    private WebElement thanksMsg;

    @FindBy(xpath =".//div[@class='complete-text']")
    private WebElement completeMsg;

    @FindBy(xpath =".//button[@id='back-to-products']")
    private WebElement backHomeBtn;

    @FindBy(xpath =".//button[@id='react-burger-menu-btn']")
    private WebElement menuBtn;

    @FindBy(xpath =".//a[@id='logout_sidebar_link']")
    private WebElement logOutBtn;



    private WebDriver driver;

    public LastPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String thanksMessage(){return thanksMsg.getText();}

    public void displayCompleteTextOrder(){System.out.println(completeMsg.getText());}

    public void backHomePageAndLogOut() throws InterruptedException {
        backHomeBtn.click();
        menuBtn.click();
        Thread.sleep(1000);
        logOutBtn.click();
    }

}
