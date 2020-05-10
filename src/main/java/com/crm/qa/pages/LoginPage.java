package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// Page Factory or Object Repository (OR)
	@FindBy(name="username")
	WebElement username;
	//@FindBy is equal to driver.findElement
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signUpLink;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	// How will you initialize the page Factory ?
	///Initializing the Page Objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
		// this means current class object
		// All the above variables will be initialized with driver Ex:username, password, login, signUpLink and crmLogo
		// instead of this we can write LoginPage.class
	}
	
	//Actions: Actions means different features available in LoginPage
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage()
	{
		return crmLogo.isDisplayed();
	}
	
	public HomePage Login(String un, String pwd) throws InterruptedException
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(3000);
		loginBtn.click();
		
		return new HomePage();
	}
			
}
