package core;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import java.io.File;


public class TestReporter {

    private ExtentReports extent;   // specify the report location
    private ExtentTest test;        // what details should be populated in the report
    private WebDriver driver;

    public TestReporter(){
        extent = new ExtentReports("./src/main/test-output/TestReport.html", true);
                 extent.addSystemInfo("Tests","Saucedemo Regressions")
                .addSystemInfo("Host Name", "LocalHost")
                .addSystemInfo("Environment","QA Server")
                .addSystemInfo("User Name", "DanieleM");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
        File fileoutput = new File("./src/main/test-output");
        if(!fileoutput.exists()){ fileoutput.mkdir();}
        File file = new File("./screenshots");
        if(!file.exists()){file.mkdir();}
    }

    public void startReporting(String testName, WebDriver driver){
        this.driver =  driver;
        switch(testName.toLowerCase()) {
            case "verifycorrectwebpage":
                test = extent.startTest(testName, "Test1: Navigation to correct webpage: www.saucedemo.com");
                break;
            case "verifybothemptycredentialsfieldmessage":
                test = extent.startTest(testName, "Test2: No credentials inserted");
                break;
            case "verifyemptypasswordfieldmessage":
                test = extent.startTest(testName, "Test3: Missing password field");
                break;
            case "verifywrongcredentials":
                test = extent.startTest(testName, "Test4: Verify Wrong credentials inserted");
                break;
            case "verifycorrectlogin":
                test = extent.startTest(testName, "Test5: Verify correct credentials inserted");
                break;
            case "verifyproductnumberinpage":
                test = extent.startTest(testName, "Test6: Testing the correct product number in myPage");
                break;
            case "verifyproductorderedbylowprice":
                test = extent.startTest(testName, "Test7: Testing filter 'low to high' prices in myPage");
                break;
            case "verifyproductorderedbyhighprice":
                test = extent.startTest(testName, "Test8: Testing filter 'high to low' prices in myPage");
                break;
            case "selectionproducttocart":
                test = extent.startTest(testName, "Test9: Verify correct navigation to my cart page");
                break;
            case "verifyproductsincart":
                test = extent.startTest(testName, "Test10: Verify correct number of product selected  in cart equals to two");
                break;
            case "checkoutsection":
                test = extent.startTest(testName, "Test11: Verify correct navigation to the checkout web page");
                break;
            case "aftercheckoutverifymessagewhennopersonalinformationinserted":
                test = extent.startTest(testName, "Test12: Verify message in web page when user skips all three fields 'First Name' 'Last Name' and 'Postal Code'");
                break;
            case "aftercheckoutverifymessagewhenonlyfirstnameinserted":
                test = extent.startTest(testName, "Test13: Verify message in web page when user skips the 'Last Name' and 'Postal Code' fields");
                break;
            case "aftercheckoutverifymessagewhenpostalcodeismissing":
                test = extent.startTest(testName, "Test14: Verify message in web page when user skips the 'Postal Code' field");
                break;
            case "verifycheckoutoverview":
                test = extent.startTest(testName, "Test15: Verify correct navigation to the checkout overview web page");
                break;
            case "verifypaymentshippingtotalprice":
                test = extent.startTest(testName, "Test16: Verify correct navigation to the final web page with the order dispached");
                break;
            case "verifyfinalpage":
                test = extent.startTest(testName, "Test17: Verify the final thank you message displayed in the web page");
                break;
            case "verifylockedusercredentials":
                test = extent.startTest(testName, "Test18: Verify message in web page when user inserts locked_out_user as username");
                break;
            case "verifysocialsinfooter":
                test = extent.startTest(testName, "Test19: Verify correct number of social media in the footer");
                break;
            case "verifynavigationtolinkedin":
                test = extent.startTest(testName, "Test20: Verify correct navigation to LinkedIn web page");
                break;
            case "verifynavigationtofacebook":
                test = extent.startTest(testName, "Test21: Verify correct navigation to Facebook web page");
                break;
            case "verifynavigationtotwitter":
                test = extent.startTest(testName, "Test22: Verify correct navigation to Twitter web page");
                break;
            case "navigatetomypages":
                test = extent.startTest(testName, "Test24 to Test27: Verify correct login of users problem_user, performance_glitch_user, error_user, visual_user");
                break;
            case "readpolicyinfooter":
                test = extent.startTest(testName, "Test23 Verify message in Privacy footer web page");
                break;
        }
    }

    public void endReporting(){
        extent.endTest(test);
    }

    public void flushReport(){
        extent.flush();
        extent.close();
    }

     public void report(LogStatus status, String description){
        if(test!=null){
            test.log(status, description);
        }
    }

    public void report(LogStatus status,  Throwable description){
        if(test!=null){
            test.log(status, description);
        }
    }


}
