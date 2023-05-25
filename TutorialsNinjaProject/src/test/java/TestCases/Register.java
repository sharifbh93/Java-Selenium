package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utility.Utilites;

public class Register {
	
	@Test
	public void RegisterAccountWithMandatoryFields() {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
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
}
