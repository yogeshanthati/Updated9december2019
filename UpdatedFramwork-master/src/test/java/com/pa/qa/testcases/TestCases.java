 package com.pa.qa.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pa.qa.base.TestBase;
import com.pa.qa.pages.*;
import com.pa.qa.reportlistener.CaptureScreenShot;
import com.pa.qa.reportlistener.Log;
import com.pa.qa.util.Constants;
import com.pa.qa.util.ExcelReader;

import com.relevantcodes.extentreports.LogStatus;

public class TestCases
{
	//written  all the pages class references
    HomePage HomePage;
    SingleItemAddPage SingleItemAddPage;
    MyAccountPageRegisterAndLogin MyAccountPageRegisterAndLogin;
    String sheetName="MyLogIn";
    @BeforeTest
    public void ConfigureReport(){

		TestBase.Cofigurereport();
    }
    @BeforeMethod
    public void setUp() throws MalformedURLException
    {
        TestBase.intialization();
    }
   @Test(priority=1)
   public void HomePageNavigateTest() 
   {
    Log.startTestCase(this.getClass().getEnclosingMethod().getName());
   	Constants.test =  Constants.extent.startTest(this.getClass().getEnclosingMethod().getName());
  	HomePage=new HomePage();
  	HomePage=HomePage.verifyNewArrivals();
   	SingleItemAddPage=HomePage.verifyNewArrivalsNavigation();	
   	Log.endTestCase(this.getClass().getEnclosingMethod().getName());
  }
  @Test(priority=2)
    public void HomePageTest()
   {
	  Log.startTestCase(this.getClass().getEnclosingMethod().getName());
     Constants.test =  Constants.extent.startTest(this.getClass().getEnclosingMethod().getName());
   	HomePage=new HomePage();
   	HomePage=HomePage.VerifyHomePageSlides();
   	HomePage=HomePage.verifyNewArrivals();
    Log.endTestCase(this.getClass().getEnclosingMethod().getName());
   }
  @Test(priority=3, dataProvider="getLogInTestData")
	public  void LogInToApp(String UserName, String Password,String UserName1, String Password1){
	    Log.startTestCase(this.getClass().getEnclosingMethod().getName());
	    Constants.test =  Constants.extent.startTest(this.getClass().getEnclosingMethod().getName());
		 MyAccountPageRegisterAndLogin=new MyAccountPageRegisterAndLogin();
		MyAccountPageRegisterAndLogin	=MyAccountPageRegisterAndLogin.LogIn(UserName, Password, UserName1, Password1);
		Log.endTestCase(this.getClass().getEnclosingMethod().getName());
	}
    @DataProvider
	public Object[][] getLogInTestData(){
		Object data[][] = ExcelReader.getTestData(sheetName, "LogInToApp");
		return (data);
	}    
    @AfterMethod
    public void getResult(ITestResult result) throws IOException
    {	
        if(result.getStatus() == ITestResult.FAILURE)
        {
            String screenShotPath = CaptureScreenShot.captureScreen( Constants.driver, "ScreenName");
            Constants.test.log(LogStatus.FAIL, result.getThrowable());
            Constants.test.log(LogStatus.FAIL, "Snapshot below: " +  Constants.test.addScreenCapture(screenShotPath));
        }
      
        Constants.driver.quit();
        
   	
    } 
    @AfterTest
    public void tearDown()
    
    {
    	 Constants.extent.endTest(Constants.test);
    	 Constants.extent.flush();
         Constants.extent.close();
     
    }
    
    
}
