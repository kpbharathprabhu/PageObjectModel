package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest()
	{
		super(); // we are calling TestBase class constructor
	}
	
	//Test cases should be seperated -- independent with each other (we should avoid dependency between the test cases)
	// Before each test cases -- launch the browser and login
	// @Test -- execute test case
	// after each test case -- close the browser
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage(); // creating object of loginPage class
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() // whenever you write test methods at the end always write Test keyword
	{
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page Title not matched");
		//Home Page Title not matched  - this will be printed only when the assertion is getting failed
	}
	
	@Test(priority=2)
	public void verifyUserNameTest()
	{
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactslink();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
