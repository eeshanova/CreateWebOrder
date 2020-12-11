package io.duotech;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreatingAWebOrder {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\BrowserDrivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		WebElement userName = driver.findElement(By.name("ctl00$MainContent$username"));
		userName.sendKeys("Tester");
		WebElement password = driver.findElement(By.name("ctl00$MainContent$password"));
		password.sendKeys("test" + Keys.ENTER);
		driver.findElement(By.linkText("Order")).click();
		WebElement quantity = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity"));
		quantity.click();
		quantity.sendKeys(Keys.BACK_SPACE);
		quantity.clear();
		Random rn = new Random();
		
		quantity.sendKeys(Integer.toString((rn.nextInt(100)+1)));
		Thread.sleep(1000);
		WebElement customerName = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName"));
		customerName.sendKeys("Jon ");
		customerName.sendKeys(String.valueOf((char)(65 + (int)(Math.random()*26))));
		for (int i = 0; i < 4; i++) {
			String middleName = String.valueOf((char)(97 + (int)(Math.random()*26)));
			customerName.sendKeys(middleName);
		}
		customerName.sendKeys(" Doe");
		Thread.sleep(1000);
		
		WebElement street = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2"));
		street.sendKeys("8607 Westwood Center Dr");
		WebElement city = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3"));
		city.sendKeys("Vienna");
		WebElement state = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4"));
		state.sendKeys("Virginia");
		WebElement zipcode = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5"));
		
		for (int i = 0; i < 5; i++) {
		String zipcodeRandom = Integer.toString((rn.nextInt(10)));	
		zipcode.sendKeys(zipcodeRandom);
		}
		
		Thread.sleep(1000);
		List<WebElement> creditCardOption = driver.findElements(By.cssSelector("input[type='radio']"));
		
		creditCardOption.get(rn.nextInt(creditCardOption.size())).click();
			
		WebElement creditCard = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6"));
		WebElement radioButton1 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
		WebElement radioButton2 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
		WebElement radioButton3 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2"));
		
		if (radioButton1.isSelected()) {
		//visa
		creditCard.sendKeys("4");
		for (int i = 0; i < 15; i++) {
			String visaCard = Integer.toString((rn.nextInt(10)));
		creditCard.sendKeys(visaCard);
		}
		}
		//mastercard
		else if (radioButton2.isSelected()) {
		creditCard.sendKeys("5");
		for (int i = 0; i < 15; i++) {
			String masterCard = Integer.toString((rn.nextInt(10)));
		creditCard.sendKeys(masterCard);
		}
		}
		else if (radioButton3.isSelected()) {
		//american express
		creditCard.sendKeys("3");
		for (int i = 0; i < 14; i++) {
			String americanExpress = Integer.toString((rn.nextInt(10)));
		creditCard.sendKeys(americanExpress);
		}
		}
		Thread.sleep(1000);
		WebElement expDate = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));
		
		LocalDate currentDate = LocalDate.now(); 
		 
		String futureDate = String.valueOf(currentDate.plusMonths(1));
		String year=futureDate.substring(2,4);
		String month=futureDate.substring(5,7);
		
//		System.out.println(futureDate);
//		System.out.println(year);
//		System.out.println(month);
				
		expDate.sendKeys(month + "/" + year);

		
		WebElement processButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton"));
		processButton.click();
		
		boolean actualResult = driver.getPageSource().contains("New order has been successfully added");
		if (actualResult) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
	}

}
