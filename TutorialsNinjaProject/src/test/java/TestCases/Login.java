package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import utility.Utilites;

public class Login extends BaseClass{
	
	public Login() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("symtst21@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		
	}
	
	@Test (priority = 2)
	public void verifyLoginWithInValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys(Utilites.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("12sf345");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed ");

	}
	@Test(priority = 3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys("symtst21@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12sf345");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed ");
	
	}
	
	@Test(priority = 4)
	public void InvalidEmailAndValidPassword() {
		
			driver.findElement(By.id("input-email")).sendKeys(Utilites.generateEmailWithTimeStamp());
			driver.findElement(By.id("input-password")).sendKeys("12345");
			driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
			
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
			
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed ");
		
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed ");
	
	}

}
