package SeleniumTestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class airbnb {

	public static void main(String[] args) throws Exception {
		
		String url = "https://www.airbnb.co.in/";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		try {
		driver.findElement(By.xpath("//button[@title='OK']")).click();
		}catch (Exception e) {
			throw e;
		}
		
//		Type Coorg in location and Select Coorg, Karnataka
		driver.findElement(By.xpath("//div[text()='Location']/following-sibling::input")).sendKeys("Coorg");
		Thread.sleep(1000);
		WebElement coorg = driver.findElement(By.xpath("//div[text()='Coorg, Karnataka']"));
		System.out.println("Location selected as: "+coorg.getText());
		
//      Select the Start Date as June 1st and End Date as June 5th
        driver.findElement(By.xpath("(//button[contains(@class,'_esmga')])[1]")).click();
        Thread.sleep(5000);
        WebElement checkin = driver.findElement(By.xpath("(//div[text()='1'])[3]"));
        checkin.click();
        System.out.println("June 1st is selected");
        WebElement checkout = driver.findElement(By.xpath("(//div[text()='5'])[3]"));
        checkout.click();
        System.out.println("June 5st is selected");
        
        
     // Adding Guests 
     	driver.findElement(By.xpath("//div[text()='Add guests']")).click(); 
     	Thread.sleep(3000);
     		
//      Select guests as 6 adults, 3 child and Click Search
     	WebElement adultscount = driver.findElement(By.xpath("//div[@id='stepper-adults']//button[@aria-label='increase value']"));
     	for (int i = 0; i < 6; i++) {
     		adultscount.click();			
		}
     	WebElement childcount = driver.findElement(By.xpath("(//button[@class='_11yg8kv'])[4]"));
     	for (int i = 0; i < 3; i++) {
     	     childcount.click();	
		}
     	WebElement search = driver.findElement(By.xpath("//div[@class='_h6px0p']"));
     	search.click();
     	System.out.println("Search option is Clicked");
     	
     	
//     	Click Cancellation flexibility and enable the filter and Save
     	WebElement cancellation = driver.findElement(By.xpath("//div[@id='menuItemButton-flexible_cancellation']//button"));
     	cancellation.click();
     	System.out.println("Cancellation flexibility is Clicked");
     	WebElement filter = driver.findElement(By.id("filterItem-switch-flexible_cancellation-true")); 
     	filter.click();
     	System.out.println("Cancellation filter is disabled");
     	WebElement save = driver.findElement(By.id("filter-panel-save-button"));
     	save.click();
     	System.out.println("Save button is clicked");
     	
//     	Select Type of Place as Entire Place and Save
    	driver.findElement(By.xpath("//div[@id='menuItemButton-room_type']//button")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Entire place']")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.id("filter-panel-save-button")).click(); 
		Thread.sleep(5000);
		
//		Set Min price as 3000 and  max price as 5000
	    driver.findElement(By.xpath("//div[@id='menuItemButton-price_range']//button")).click(); 
		driver.findElement(By.id("price_filter_min")).sendKeys(Keys.CONTROL, "a", Keys.DELETE); 
		Thread.sleep(2000);
		driver.findElement(By.id("price_filter_min")).sendKeys("3000"); 
		driver.findElement(By.id("price_filter_max")).sendKeys(Keys.CONTROL, "a", Keys.DELETE); 
		Thread.sleep(2000);
		driver.findElement(By.id("price_filter_max")).sendKeys("5000"); 
		driver.findElement(By.id("filter-panel-save-button")).click();
		Thread.sleep(5000);
		
		
//		Click More Filters and set 3 Bedrooms and 3 Bathrooms
		WebElement morefilters = driver.findElement(By.xpath("//div[@id='menuItemButton-dynamicMoreFilters']//button"));
		morefilters.click();
		System.out.println("More Filters is Clicked");
		Thread.sleep(3000);
		
		WebElement bedrooms = driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bedrooms-0']//button[@aria-label='increase value']"));
		for (int i = 0; i < 3; i++) {
			bedrooms.click();
		}
		WebElement bathrooms = driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bathrooms-0']//button[@aria-label='increase value']"));
		for (int i = 0; i < 3; i++) {
			bathrooms.click();
		}
		
		
//		Check the Amenities with Kitchen, Facilities with Free parking on premisses, Property as House and Host Language as English
//	    and click on Stays only when stays available
		driver.findElement(By.xpath("//div[text()='Kitchen']")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Free parking on premises']")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='House']")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='English']")).click(); 
		Thread.sleep(2000);
		System.out.println("All the above filters are applied");
		
//		Click Prahari Nivas, the complete house
		WebElement house = driver.findElement(By.xpath("//div[@class='_ttw0d']/a[@aria-label='Prahari Nivas, the complete house']"));
		js.executeScript("arguments[0].click();", house);
		Set<String> all = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(all);
		driver.switchTo().window(child.get(1));
		
//		Click on "Show all * amenities"
		WebElement amenties = driver.findElement(By.xpath("(//*[contains(text(),'amenities')])[1]"));
		amenties.click();
		System.out.println("Show all amentities is clicked");
		
		
//		Print all the Not included amenities
		List<WebElement> amentieslist = driver.findElements(By.xpath("(//div[@class='_aujnou'])[6]"));
		for (WebElement print : amentieslist) {
			String text = print.getText();
			System.out.println(text);
			
		}
		try {
			driver.findElement(By.xpath("//button[@aria-label='Close' and @type='button']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
			throw e;  
		} 
//		Verify the Check-in date, Check-out date and Guests
		String checkinDate = driver.findElement(By.xpath("//input[@id='checkin']/following-sibling::div")).getText(); 
		String checkoutDate = driver.findElement(By.xpath("//input[@id='checkout']/following-sibling::div")).getText();
		
		System.out.println("\nCheckin Checkout details: ");
		if (checkinDate.equalsIgnoreCase("6/1/2020")) {
			System.out.println("Checkin Date is correct. " + checkinDate);
		} else { 
			System.out.println("Incorrect Checkin Date. " + checkinDate);
		}
		
		if (checkoutDate.equalsIgnoreCase("6/5/2020")) {
			System.out.println("Checkout Date is correct. " + checkoutDate);
		} else { 
			System.out.println("Incorrect Checkout Date. " + checkoutDate);
		}
		
//		Verifying the number of Guests 
				String guest = driver.findElement(By.xpath("//span[@class='guest-label__text guest-label__text-guests']")).getText(); 
				
				System.out.println("\nGuests details: ");
				if (guest.contains("9")) {
					System.out.println("Number of Guests are correct. " + guest); 
				} else { 
					System.out.println("Incorrect guests number. " + guest);
				}
				
				Thread.sleep(3000);
				
				
				Map<String, String> sleepMap = new LinkedHashMap<String, String>(); 
				
				List<WebElement> bedList = driver.findElements(By.xpath("//div[contains(text(),'Bed')]")); 
				List<WebElement> bedArList = driver.findElements(By.xpath("//div[contains(text(),'Bed')]/following-sibling::div")); 
				
				for (int b = 0; b < bedList.size(); b++) { 
					String sleepingInfo1 = bedList.get(b).getText(); 
					String sleepingInfo2 = bedArList.get(b).getText(); 
					driver.findElement(By.xpath("//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc']")).click(); 
					driver.findElement(By.xpath("(//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc'])[2]")).click(); 
					sleepingInfo1 = bedList.get(b).getText(); 
					sleepingInfo2 = bedArList.get(b).getText(); 
					sleepMap.put(sleepingInfo1, sleepingInfo2); 
				}
				
				
				System.out.println("\nSleeping arrangements: ");
				
				for (Map.Entry<String, String> eachMap : sleepMap.entrySet()) {
					System.out.println(eachMap.getKey() + " - " + eachMap.getValue());
				}
				
				
     	driver.quit();
     	
        }

}
