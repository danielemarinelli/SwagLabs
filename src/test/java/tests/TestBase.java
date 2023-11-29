package tests;

import com.relevantcodes.extentreports.LogStatus;
import core.DriverFactory;
import core.TestReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import core.TestConfig;
import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;


public class TestBase {

    String date = null;
    public static WebDriver driver;
    private final TestReporter reporter = new TestReporter();


    @BeforeSuite
    public void initSuite() throws Exception {
        TestConfig.load(System.getenv("env"));
        TestConfig.addProperty("browser",System.getenv("browser"));
        TestConfig.addProperty("env",System.getenv("env"));
    }

    @BeforeClass
    public void initDriver() {
        driver =  new DriverFactory().getDriver(TestConfig.getProperty("browser"));
    }

    @BeforeMethod
    public void launchApp() {
        driver.get(TestConfig.getProperty("appHomeURL"));
    }

    public WebDriver driver() {
        return driver;
    }

    @AfterClass
    public void closeBrowser() {
        if(driver!=null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void initTestReport(Method method){reporter.startReporting(method.getName(), driver); }

    public TestReporter reporter(){
        if(reporter!=null){
            return reporter;
        }
        return null;
    }

    @AfterMethod
    public void closeReport(){ reporter.endReporting(); }

    @AfterSuite
    public void clearReport(){reporter.flushReport();}

    @AfterMethod
    public void testStatusInExtentReport(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()){
            reporter().report(LogStatus.FAIL,"Failed test is: "+result.getName());
            reporter().report(LogStatus.FAIL,result.getThrowable());
            reporter().report(LogStatus.INFO,"<b>TEST MUST BE CHECKED</b>");
            //test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
        }else if(ITestResult.SUCCESS == result.getStatus()){
            reporter().report(LogStatus.PASS,"Test passed: "+result.getName());
            reporter().report(LogStatus.INFO,"<i>TEST ENDED SUCCESSFULLY</i>");
        }else if(ITestResult.SKIP == result.getStatus()){
            reporter().report(LogStatus.SKIP,"Test skipped: "+result.getName());
            reporter().report(LogStatus.INFO,"<i>TEST IS SKIPPED. INVESTIGATION NEEDED FOR THE REASON</i>");
        }
    }

    @AfterMethod
    public void takeScreenShotIfTestsFails(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot camera = ((TakesScreenshot) driver());
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./screenshots/"+result.getName()+"_Fail_"+formatDate()+".png");
            FileHandler.copy(screenShot, DestFile);
        }
    }

    public String formatDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        date = date.replaceAll("/","").replaceAll(":","").replaceAll(" ","");
        return date;
    }

    public String getParentWindowHandler(){return driver.getWindowHandle();}

    public void goToChildWindow(){
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!getParentWindowHandler().equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }
    }

    public void goBackToParentWindow(){driver.switchTo().window(getParentWindowHandler());}

}
