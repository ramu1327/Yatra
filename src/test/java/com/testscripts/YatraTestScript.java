package com.testscripts;

//Importing packages
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pageobjects.YatraFlightDetailsPageObject;
import com.pageobjects.YatraPageObject;
import com.pageobjects.YatraTravellerDetailsPageObject;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.Config;
import com.utilities.Utilities;
import java.lang.*;

public class YatraTestScript extends BaseClass {
	
	// Create instance of Config Class
	Config con = new Config();
	// Create Instance of YatraPageObject Class
	YatraPageObject input = new YatraPageObject(driver);
	// Create Instance of YatraFlightPageObject Class
	YatraFlightDetailsPageObject flightObject = new YatraFlightDetailsPageObject(driver);
	// Create Instance of YatraTravellerPageObject Class
	YatraTravellerDetailsPageObject travellerObject = new YatraTravellerDetailsPageObject(driver);
	// Create Instance of Utilities Class
	Utilities utilities = new Utilities();
	// store current working directory path
	public static String pth = System.getProperty("user.dir");
	// Create Reports Folder In Current Working Directory Path
	public static String reportFilePath = pth + "/Reports/";
	// Create Instance for ExtentReports Class
	public ExtentReports reports;
	// create variable for extent test
	ExtentTest test;
	// creating object for logger class
	Logger logger = Logger.getLogger(YatraTestScript.class);

	// Constructor Calling
	public YatraTestScript() throws Exception {
		// Calling Config File in Constructor
		con.loadPropertyFile();
		// Calling Log4j.propertie file
		PropertyConfigurator.configure("./Log4j/log4j.properties");
	}

	// Declared BeforeClass TestNG annotation
	@BeforeSuite()
	// Declared Open method
	public void open() {
		// Calling LaunchBrowser
		launchBrowser(con.getProperty("url"));
		//logs an info message
		logger.info("Browser launched & url opened");
	}

	@Test(priority = 1)
	public void flightJourneyDate() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - Flight Booking Information");
		// Wait for 2 Seconds
		Thread.sleep(2000);
		input.sendSourcePlace(con.getProperty("from"), driver);
		// Thread.sleep(15000);
		String source = input.getTextFromsource(driver);
		//logs an info message with parameter
		logger.info("Entered Source Place : " + source);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered Source Place : " + source);
		// Wait for 5 Seconds
		
		Thread.sleep(5000);
		input.sendDestinationPlace(con.getProperty("to"), driver);
		String destination = input.getTextFromDestination(driver);
		//logs an info message with parameter
		logger.info("Entered Destination Place : " + destination);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered Destination Place : " + destination);

		input.selectFromDate(driver);
		String getDate = input.getTextFromDate(driver);
		//logs an info message with parameter
		logger.info("Selected Depart Date : " + getDate);
		// log(Status, details)
		test.log(LogStatus.INFO, "Selected Depart Date : " + getDate);
		// Wait for 2 Seconds
		Thread.sleep(2000);
	
		input.clickSearchBtn(driver);
		//logs an info message
		logger.info("Clicked on Search Button");
		// test.log(LogStatus.INFO, "Clicked on Search Button");
		String getText = input.getSetGoBtnValidation(driver);
		// Using if-else condition to compare the Expected getText and string "Set Fare Alerts". As per the below lines of code (if-else condition)
		if (getText.equalsIgnoreCase("Set Fare Alerts"))
			// log(Status, details)
			test.log(LogStatus.PASS, "Clicked on Set_Go_Btn");
		else
			// log(Status, details)
			test.log(LogStatus.FAIL, "Clicked on Set_Go_Btn");
		
		// Report Ends
		utilities.endReport();
	}

	@Test(priority = 2)
	public void flightList() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - Flight Information");
		flightObject.bottomScroll(driver);
		//logs an info message
		logger.info("Scrolled Bottom Of The Page");
		
		flightObject.clickViewAllFightsBtn(driver);
		//logs an info message
		logger.info("Clicked on View All Flights Button");
		
		flightObject.clickOnPrice(driver);
		//logs an info message
		logger.info("Scrolled Bottom Of The Page");
		int ar[] = flightObject.getPriceList(driver);
		test.log(LogStatus.INFO, "Lowest Flight Ticket Rate  : " + ar[0]);
		test.log(LogStatus.INFO, "Highest Flight Ticket Rate : " + ar[1]);
		logger.info("Got Price-List Of Flights ");
		flightObject.highestFlightTicketSelect(driver);
		logger.info("Highest Flight Ticket Selected : " + ar[1]);
		String getTicketDetails = flightObject.bookingValidation(driver);
		// Using if-else condition to compare the Expected getTicketDetails and string "ticket DEtails". As per the below lines of code (if-else condition)
		if (getTicketDetails.equalsIgnoreCase("ticket DEtails"))
			//To generate the log when the test case is passed
			test.log(LogStatus.PASS, "Highest Flight Ticket Selected : " + ar[1]);
		else
			//To generate the log when the test case is passed
			test.log(LogStatus.FAIL, "Highest Flight Ticket Selected");
		// Report Ends
		utilities.endReport();
	}

	@Test(priority = 3)
	public void travellerDetails() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - Traveller Information");

		travellerObject.selectTitle(con.getProperty("title"), driver);
		logger.info("Title Selected : " + con.getProperty("title"));

		travellerObject.sendFirstName(con.getProperty("firstName"), driver);
		logger.info("Entered First Name : " + con.getProperty("firstName"));

		travellerObject.sendLastName(con.getProperty("lastName"), driver);
		logger.info("Entered lastName Name : " + con.getProperty("lastName"));

		travellerObject.sendEmail(con.getProperty("email"), driver);
		logger.info("Entered Email : " + con.getProperty("email"));

		travellerObject.sendMobileNumber(con.getProperty("mobileNumber"), driver);
		logger.info("Entered Mobile Number : " + con.getProperty("mobileNumber"));

		travellerObject.clickProceedBtn(driver);
		logger.info("Clicked On Proceed-To-Payment Button");

		String getTicketText = travellerObject.proceedBtnValidation(driver);
		// Using if-else condition to compare the Expected getTicketText and string "View DEtaIls". As per the below lines of code (if-else condition)
		if (getTicketText.equalsIgnoreCase("View DEtaIls"))
			//To generate the log when the test case is passed
			test.log(LogStatus.PASS, "Clicked On Proceed-To-Payment Button");
		else
			//To generate the log when the test case is failed
			test.log(LogStatus.FAIL, "Clicked On Proceed-To-Payment Button");
		String str[] = travellerObject.getContactDetails(driver);
		logger.info("Got Contact Details");
		// log(Status, details)
		test.log(LogStatus.INFO, "Traveller Details");
		
		// log(Status, details)
		test.log(LogStatus.INFO, "Title Selected : " + str[0]);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered First Name : " + str[1]);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered lastName Name : " + str[2]);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered Email : " + str[3]);
		// log(Status, details)
		test.log(LogStatus.INFO, "Entered Mobile Number : " + str[4]);

		// calls the method to take the screenshot
		Utilities.captureScreenshot(driver, "Displayed Contact Details");
		// Report Ends
		utilities.endReport();
	}

	@AfterSuite()
	public void close() {
		//'close' method is used to close the browser window
		driver.close();
		logger.info("Browser closed");
	}
}