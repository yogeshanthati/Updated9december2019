package com.pa.qa.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pa.qa.base.TestBase;
import com.pa.qa.pages.HomePage;
import com.pa.qa.pages.SingleItemAddPage;
import com.pa.qa.reportlistener.CaptureScreenShot;
import com.pa.qa.reportlistener.Log;
import com.pa.qa.util.*;
import com.relevantcodes.extentreports.LogStatus;

public class DataBaseTest {
	
	    HomePage HomePage;
	    SingleItemAddPage SingleItemAddPage;
	
	
	 @BeforeTest
	    public void ConfigureReport(){
	    	TestBase.Cofigurereport();
	    }
	 
	 @BeforeMethod
	    public void setUp() throws MalformedURLException
	    {
	        TestBase.intialization();
	        TestBase.configureDataBase();
	        Log.startTestCase(this.getClass().getSimpleName());
			Constants.test =  Constants.extent.startTest(this.getClass().getSimpleName());
	    }
	 
	 @Test(alwaysRun = true)
	    public void HomePage() throws SQLException 
	   {
		List<String> data=DataBaseConnection.getDBData("username");
		System.out.println(data);
	   }
	 
	 
	 @AfterMethod
	    public void getResult(ITestResult result) throws IOException
	    {	
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = CaptureScreenShot.captureScreen( Constants.driver, this.getClass().getSimpleName());
	            Constants.test.log(LogStatus.FAIL, result.getThrowable());
	            Constants.test.log(LogStatus.FAIL, "Snapshot below: " +  Constants.test.addScreenCapture(screenShotPath));
	        }
	        
	      Constants.extent.endTest(Constants.test);
	      Log.endTestCase(this.getClass().getSimpleName());
	      Constants.driver.quit();
	 
	    } 
	    @AfterTest
	    public void tearDown()
	    
	    {	try {
			Constants.connect.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
	    	 Constants.extent.flush();
	         Constants.extent.close();
	     
	    }
}
