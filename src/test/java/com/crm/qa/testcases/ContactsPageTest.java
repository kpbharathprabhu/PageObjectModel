/*
 * Aauthor Bharath
 * 
 */




package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest()
	{
		super(); // we are calling TestBase class constructor
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage(); // creating object of loginPage class
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		//contactsPage = homePage.clickOnContactslink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContactsByName("AAaa_Queen Elis");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactsByName("AAaa_Queen Elis");
		contactsPage.selectContactsByName("Aakash Anmash");
	}
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][] = TestUtil.getTestData(sheetName);
		// Most of the time we will be using two dimensional object array.
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company)
	// In excel sheet if we have 10 columns then 10 parameters we need to pass (its compulsory)
	{
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.","Tom", "Peter", "Isoft");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	//Page Object Model with Data Driven Approach
	// Dont use keyword driven approach.Dont use selenium with keyworddriven.
	// Because the maintanence is more in keyword driven.
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
