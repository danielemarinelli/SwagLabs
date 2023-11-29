package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.TestBase;

import java.util.List;


public class MyProducts extends TestBase {

    @FindBy(xpath =".//div[@class='inventory_item_name ']")
    private List<WebElement> productsInventoryName;

    @FindBy(xpath =".//select[@class='product_sort_container']")
    private WebElement filter;

    @FindBy(xpath =".//div[@class='inventory_item_price']")
    private List<WebElement> productsInventoryPrice;

    @FindBy(xpath =".//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement bikeLightAddToCartButton;

    @FindBy(xpath =".//button[@id='add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement fleeceJacketAddToCartButton;

    @FindBy(xpath =".//span[@class='shopping_cart_badge']")
    private WebElement iconCartButton;

    @FindBy(xpath =".//span[@class='title']")
    private WebElement titleCartPage;



    private WebDriver driver;

    public MyProducts(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public int productsDisplayedInPage()  {
        for (WebElement product : productsInventoryName) {
            System.out.println(product.getText());
            System.out.println("-----");}
        return productsInventoryName.size();
    }

    public String productsDisplayedByPriceLowToHigh()  {
        String lowestPrice = null;
        Select se = new Select(filter);
        se.selectByVisibleText("Price (low to high)");
        System.out.println("Lowest to Highest prices in the page:");
        for (WebElement productPrice : productsInventoryPrice) {
                if(productPrice.getText().contentEquals("$7.99")){lowestPrice = productPrice.getText();}
            System.out.println(productPrice.getText());
            System.out.println("-----");}
        System.out.println("Lowest price --->> "+lowestPrice);
        return lowestPrice;
    }

    public String productsDisplayedByPriceHighToLow()  {
        String highestPrice = null;
        Select se = new Select(filter);
        se.selectByVisibleText("Price (high to low)");
        System.out.println("Highest to Lowest prices in the page:");
        for (WebElement productPrice : productsInventoryPrice) {
            if(productPrice.getText().contentEquals("$49.99")){highestPrice = productPrice.getText();}
            System.out.println(productPrice.getText());
            System.out.println("-----");}
        System.out.println("Highest price --->> "+highestPrice);
        return highestPrice;
    }

    public String selectProducts() throws InterruptedException {
        bikeLightAddToCartButton.click();
        fleeceJacketAddToCartButton.click();
        Thread.sleep(1000);
        iconCartButton.click();
        return titleCartPage.getText();
    }

    public void clickCart()  {iconCartButton.click();}

}
