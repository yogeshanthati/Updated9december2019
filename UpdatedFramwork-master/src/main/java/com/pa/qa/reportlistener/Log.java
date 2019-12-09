
package com.pa.qa.reportlistener;

import org.apache.log4j.Logger;

 public class Log {
	 
	 private static Logger Log = Logger.getLogger(Log.class.getName());  

 public static void info(String message) {
		Log.info(message);
		}
 public static void warn(String message) {
    Log.warn(message);
	}
 public static void error(String message) {
    Log.error(message);
	}
 public static void fatal(String message) {
    Log.fatal(message);
	}
 public static void debug(String message) {
    Log.debug(message);
	}
 
 public static void startTestCase(String sTestCaseName){
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 }
	 
	 //This is to print log for the ending of the test case
	 
	 public static void endTestCase(String sTestCaseName){
		 
		 

		 
	 
	 Log.info("*****************************           "+"-E---N---D-"+"            *****************************");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 }
	 
	
}