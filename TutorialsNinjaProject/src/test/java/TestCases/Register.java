package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import utility.Utilites;

public class Register extends BaseClass {
	
	
	
	
	WebDriver driver;
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL("edge"); 
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click(); 
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountWithMandatoryFields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sharif");
		driver.findElement(By.id("input-lastname")).sendKeys("Bhuiyan");
		driver.findElement(By.id("input-email")).sendKeys(Utilites.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("98614149389");
		
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessMessage,"Your Account Has Been Created!", "Account has not been created successfully");
		
			
	}
	@Test(priority=2)
	public void verifyRegisterAccountWithAllFields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sharif");
		driver.findElement(By.id("input-lastname")).sendKeys("Bhuiyan");
		driver.findElement(By.id("input-email")).sendKeys(Utilites.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("98614149389");
		
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessMessage,"Your Account Has Been Created!", "Account has not been created successfully");
		
		
	}
	
	@Test(priority=3)
	public void verifyingAccountWithExistingEmail() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sharif");
		driver.findElement(By.id("input-lastname")).sendKeys("Bhuiyan");
		driver.findElement(By.id("input-email")).sendKeys("symtst21@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("98614149389");
		
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message regarding duplicate Email address is not displaying");
	}
	@Test(priority=4)
	public void registeringAccountWithoutFillingAnyDetials() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),"Privacy policy waring message is not showing");
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@name='firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"),"First name waring message is not showing"); 
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@name='lastname']/following-sibling::div")).getText();
		Assert.assertTrue(actualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"),"Last name waring message is not showing"); 
		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@name='email']/following-sibling::div")).getText();
		Assert.assertTrue(actualEmailWarning.contains("E-Mail Address does not appear to be valid!"),"Invalid Email address warning is not showing"); 
		
		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@name='telephone']/following-sibling::div")).getText();
		Assert.assertTrue(actualTelephoneWarning.contains("Telephone must be between 3 and 32 characters!"),"Telephone must be between 3 and 32 characters! warning is not showing");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@name='password']/following-sibling::div")).getText();
		Assert.assertTrue(actualPasswordWarning.contains("Password must be between 4 and 20 characters!"),"Password must be between 4 and 20 characters!, warning is not showing");
		
		
	}
}
