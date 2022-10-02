package com.testscripts;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;    
import org.testng.asserts.SoftAssert; 

public class SoftAssertions {

	  private SoftAssert softAssert = new SoftAssert();
	  
	  @Test
	  public void test() {
		  try {
			  softAssert.assertFalse(false, "*****ERROR LINE 13********");
			  softAssert.assertTrue(true, "*****ERROR LINE 14********");
			  System.out.println("welcome");
			  softAssert.assertAll();
		  }
		  catch (java.lang.AssertionError e) {
			    System.out.println(e.getMessage());
			} 
		  
		  try {
			  softAssert.assertFalse(false, "*****ERROR LINE 21********");
			  softAssert.assertTrue(false, "*****ERROR LINE 22********");
			  System.out.println("welcome");
			  softAssert.assertAll();
		  } catch (java.lang.AssertionError e) {
			    System.out.println(e.getMessage());
			} 
		  
		  
	  }
	  
	  @Test
	  public void test1() {
		  try {
			  softAssert.assertFalse(false, "*****ERROR LINE 21********");
			  softAssert.assertTrue(false, "*****ERROR LINE 22********");
			  System.out.println("welcome");
			  softAssert.assertAll();
		  } catch (java.lang.AssertionError e) {
			    System.out.println(e.getMessage());
			} 
		  
		  
		 
	  }
	  
	  @Test
	  public void test2() {
		  try {
			  softAssert.assertFalse(false, "*****ERROR LINE 29********");
			  softAssert.assertTrue(false, "*****ERROR LINE 30********");
			  System.out.println("welcome");
			  softAssert.assertAll();
			} catch (java.lang.AssertionError e) {
			    System.out.println(e.getMessage());
			} 
	  }

}
