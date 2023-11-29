package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import java.io.IOException;
import java.util.List;

public class SwagLabsTests extends TestBase{

    SauceDemoHomePage webpage = null;
    MyProducts MySwagLabs = null;
    MyCart MyCart = null;
    MyCheckout MyCheckout = null;
    LastPage LastPage = null;

    @Test(priority = 1, description="Verify navigation to saucedemo web page")
    public void VerifyCorrectWebPage()  {
        webpage = new SauceDemoHomePage(driver());
        String title = webpage.getWebPageTitle();
        Assert.assertEquals(title,"Swag Labs","Wrong webpage");
    }

    @Test(priority = 2, description="Verify message in web page when user doesn't insert user and password")
    public void VerifyBothEmptyCredentialsFieldMessage()  {
        webpage = new SauceDemoHomePage(driver());
        String errorMsgUser = webpage.loginWithoutCredentials();
        Assert.assertEquals(errorMsgUser,"Epic sadface: Username is required","Wrong message both credentials not inserted");
    }

    @Test(priority = 3, description="Verify message in web page when user inserts username, but doesn't insert the password")
    public void VerifyEmptyPasswordFieldMessage() throws IOException {
        webpage = new SauceDemoHomePage(driver());
        String errorMsgPw = webpage.loginWithoutPassword();
        Assert.assertEquals(errorMsgPw,"Epic sadface: Password is required","Wrong message when password not inserted");
    }

    @Test(priority = 4, description="Verify message in web page when user inserts fake username and password")
    public void VerifyWrongCredentials()  {
        webpage = new SauceDemoHomePage(driver());
        String wrongCredentialsMsg = webpage.loginWithWrongCredentials();
        Assert.assertEquals(wrongCredentialsMsg,"Epic sadface: Username and password do not match any user in this service","Wrong message when inserting wrong user credentials");
    }

    @Test(priority = 5, description="Verify correct navigation to my web page when user inserts correct credentials")
    public void VerifyCorrectLogin() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        String myPageLabel = webpage.myPage();
        Assert.assertEquals(myPageLabel,"Products","Did not access to myPage");
    }

    @Test(priority = 6, description="Verify correct number of product selected")
    public void VerifyProductNumberInPage() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        int totalProductsInPage = MySwagLabs.productsDisplayedInPage();
        Assert.assertEquals(totalProductsInPage,6,"mySwagLabsPage contains "+ totalProductsInPage +" products and not 6");
    }

    @Test(priority = 7, description="Verify filter order by low price")
    public void VerifyProductOrderedByLowPrice() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        String lowestPrice = MySwagLabs.productsDisplayedByPriceLowToHigh();
        Assert.assertEquals(lowestPrice,"$7.99","The lowest product price is not $7.99");
    }

    @Test(priority = 8, description="Verify filter order by high price")
    public void VerifyProductOrderedByHighPrice() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        String highestPrice = MySwagLabs.productsDisplayedByPriceHighToLow();
        Assert.assertEquals(highestPrice,"$49.99","The highest product price is not $49.99");
    }

    @Test(priority = 9, description="Verify correct navigation to my cart web page")
    public void SelectionProductToCart() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        String cart = MySwagLabs.selectProducts();
        Assert.assertEquals(cart,"Your Cart", "You did not land in the cart page");
    }

    @Test(priority = 10, description="Verify correct number of product selected equals to two")
    public void VerifyProductsInCart() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MyCart = new MyCart(driver());
        int productsInCart = MyCart.numberProductsInCart();
        Assert.assertEquals(productsInCart,2,"Products in cart aren't two");
    }

    @Test(priority = 11, description="Verify correct navigation to the checkout web page")
    public void CheckoutSection() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        String checkTitle = MyCart.checkout();
        Assert.assertEquals(checkTitle,"Checkout ", "You did not land in the checkout page");
    }

    @Test(priority = 12, description="Verify message in web page when user skips all three fields 'First Name' 'Last Name' and 'Postal Code'")
    public void AfterCheckoutVerifyMessageWhenNoPersonalInformationInserted() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout MyCheckout = new MyCheckout(driver());
        String noInfoInserted = MyCheckout.noInfoPassed();
        Assert.assertEquals(noInfoInserted,"Error: First Name is required", "Wrong message when no information is inserted");
    }

    @Test(priority = 13, description="Verify message in web page when user skips the 'Last Name' and 'Postal Code' fields")
    public void AfterCheckoutVerifyMessageWhenOnlyFirstNameInserted() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout MyCheckout = new MyCheckout(driver());
        String noInfoInserted = MyCheckout.onlyFirstNamePassed();
        Assert.assertEquals(noInfoInserted,"Error: Last Name is required", "Wrong message when last name is not inserted");
    }

    @Test(priority = 14, description="Verify message in web page when user skips the 'Postal Code' field")
    public void AfterCheckoutVerifyMessageWhenPostalCodeIsMissing() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout MyCheckout = new MyCheckout(driver());
        String noInfoInserted = MyCheckout.missingPostalCodeInformation();
        Assert.assertEquals(noInfoInserted,"Error: Postal Code is required", "Wrong message when postal code is not inserted");
    }

    @Test(priority = 15, description="Verify correct navigation to the checkout overview web page")
    public void VerifyCheckoutOverview() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout = new MyCheckout(driver());
        String overview = MyCheckout.allInformationPassedForCheckout();
        Assert.assertEquals(overview,"Overview", "You did not land in the checkout overview page");
    }

    @Test(priority = 16, description="Verify correct navigation to the final web page with the order dispached")
    public void VerifyPaymentShippingTotalPrice() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout = new MyCheckout(driver());
        MyCheckout.allInformationPassedForCheckout();
        String shippingInfo = MyCheckout.displayInformationCheckoutOverview();
        Assert.assertEquals(shippingInfo,"Complete!", "Dispatched order page incorret");
    }

    @Test(priority = 17, description="Verify the final thank you message displayed in the web page")
    public void VerifyFinalPage() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        MySwagLabs = new MyProducts(driver());
        MySwagLabs.selectProducts();
        MySwagLabs.clickCart();
        MyCart = new MyCart(driver());
        MyCart.checkout();
        MyCheckout = new MyCheckout(driver());
        MyCheckout.allInformationPassedForCheckout();
        MyCheckout.displayInformationCheckoutOverview();
        LastPage = new LastPage(driver());
        LastPage.displayCompleteTextOrder();
        String thanks = LastPage.thanksMessage();
        LastPage.backHomePageAndLogOut();
        Assert.assertEquals(thanks, "Thank you for your order!", "Final message in last page incorret");
    }

    @Test(priority = 18, description="Verify message in web page when user inserts locked_out_user as username")
    public void VerifyLockedUserCredentials() throws IOException {
        webpage = new SauceDemoHomePage(driver());
        String lockedUserMsg = webpage.lockedUserLogin();
        Assert.assertEquals(lockedUserMsg,"Epic sadface: Sorry, this user has been locked out.","Wrong message when inserting locked user credentials");
    }


    @Test(priority = 19, description="Verify correct number of social media in the footer")
    public void VerifySocialsInFooter() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        int totalSocialsInFooter = webpage.totalSocialsMediaInFooter();
        Assert.assertEquals(totalSocialsInFooter,3,"Footer does not display three socials icons");
    }

    @Test(priority = 20, description="Verify correct navigation to LinkedIn web page")
    public void VerifyNavigationToLinkedin() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        String linkedinNote = webpage.linkedin();
        Assert.assertEquals(linkedinNote,"New to LinkedIn? Join now","Footer link to LinkedIn doesn't work");
    }

    @Test(priority = 21, description="Verify correct navigation to Facebook web page")
    public void VerifyNavigationToFacebook() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        String facebookNote = webpage.facebook();
        Assert.assertEquals(facebookNote,"Connect with Sauce Labs on Facebook","Footer link to Facebook doesn't work");
    }

    @Test(priority = 22, description="Verify correct navigation to Twitter web page")
    public void VerifyNavigationToTwitter() throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        String twitterURL = webpage.twitter();
        Assert.assertEquals(twitterURL,"https://twitter.com/saucelabs","Footer link to Twitter doesn't work");
    }

      @Test(priority = 23, description="Verify correct Policy message in footer web page")
    public void readPolicyInFooter() throws IOException {
        webpage = new SauceDemoHomePage(driver());
        webpage.correctLogin();
        String lastWordInPolicySentence = webpage.readPolicy();
        Assert.assertEquals(lastWordInPolicySentence,"Policy","Policy isn't present or it is incorrect in footer page");
    }


    @Test(priority = 24, dataProvider = "testdata_credentials", description="Verify correct login of users problem_user, performance_glitch_user, error_user, visual_user")
    public void NavigateToMyPages(String username, String password) throws Exception {
        webpage = new SauceDemoHomePage(driver());
        webpage.credentialsLoginFromWebPage(username, password);
        String myPageLabel = webpage.myPage();
        Assert.assertEquals(myPageLabel,"Products","Did not access to myPage");
    }



    @DataProvider(name="testdata_credentials")
    public Object[][] tData(){
        return new Object[][] {
                {"problem_user","secret_sauce"},
                {"performance_glitch_user","secret_sauce"},
                {"error_user","secret_sauce"},
                {"visual_user","secret_sauce"}
        };
    }

}
