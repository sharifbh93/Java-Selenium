package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;

public class Search extends BaseClass{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL("firefox");
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchingWithValidProductName() {
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Valid search product HP is not displayed");
	}
	
	@Test(priority=2)
	public void verifySearchingWithInValidProductName() {
		
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,"There is no product that matches the search criteria.", "No product in search result is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchingWithoutProductName() {
		
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,"There is no product that matches the search criteria.", "No product in search result is not displayed");
	}
}
