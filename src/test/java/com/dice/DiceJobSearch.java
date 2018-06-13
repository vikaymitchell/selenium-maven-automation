package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	
//	 
//    Test case:
//      Title: dice job search 
//

//                -Page title should contain count of results , 
//                along with search keyword.
//                -Count of results should be displayed on the page.
	
	public static void main (String[] args) {
		
		//Set up chrome driver path
		WebDriverManager.chromedriver().setup();
		
		//invoke Selenium webdriver
		WebDriver driver = new ChromeDriver();
		
		//full screen
		//driver.manage().window().fullscreen();
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//step 1: Launch the browser and navigate  to https://dice.com 
        //Expected: dice home page should be displayed
		
		String url = "https://dice.com";
		driver.get(url);
		
//      Step 2. Insert search keyword and location then click on
//      find tech jobs
//      Expected: -Search results page should be loaded.
		String actual = driver.getTitle();
		String expected = "Job Search for Technology Professionals | Dice.com";
		
		if(actual.equals(expected)) {
			System.out.println("Step PASS. Dice homepage loaded.");
		}else {
			System.out.println("Step FAIL. Dice homepage loaded.");
			throw new RuntimeException("Step FAIL. Dice homepage loaded.");
		}
		
		String keyword = "javascript developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "77064";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		if(countResult>0) {
			System.out.println("Step PASS. Keyword:" + keyword + " search returned " + 
		countResult + " results in " + location);
		}else {
			System.out.println("Step FAIL. Keyword:" + keyword + " search returned " + 
					countResult + " results in " + location);
		}
		driver.close();
		System.out.println("Test completed - " + LocalDateTime.now());
	}

}
