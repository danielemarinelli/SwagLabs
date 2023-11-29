package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBase;

import java.util.List;

public class MyCart extends TestBase {

    @FindBy(xpath =".//div[@class='inventory_item_name']")
    private List<WebElement> productsInCart;

    @FindBy(xpath =".//span[@class='shopping_cart_badge']")
    private WebElement iconCartButton;

    @FindBy(xpath =".//button[@id='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath =".//span[@class='title']")
    private WebElement checkoutTitle;

   private WebDriver driver;

    public MyCart(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public int numberProductsInCart(){
        iconCartButton.click();
        System.out.println("Products in the cart are listed below:");
        for (WebElement product : productsInCart) {System.out.println(product.getText());}
        return productsInCart.size();
    }

    public String checkout() {
        checkoutButton.click();
        String[] splitTitle = checkoutTitle.getText().split(" ");
        return splitTitle[0].replace(":"," ");
    }



}
