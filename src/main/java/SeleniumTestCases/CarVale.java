package SeleniumTestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarVale {

	public static void main(String[] args) throws InterruptedException {
		
		String url = "https://www.carwale.com/";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(op);
		Actions act = new Actions(driver);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebDriverWait wt = new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//		Click on Used
		WebElement usedcars = driver.findElement(By.xpath("//li[@class='welcome-box__search-item js-welcome-box__list-item']"));
		usedcars.click();
		System.out.println("Used Cars menu is Clicked");
		Thread.sleep(5000);
		
//		Select the City as Chennai
		WebElement city = driver.findElement(By.id("usedCarsList"));
		city.sendKeys("Chennai");
		Thread.sleep(2000);
		WebElement cityselect = driver.findElement(By.id("usedCarsList"));
		cityselect.sendKeys(Keys.TAB);
		System.out.println("Chennai city has been selected");
		Thread.sleep(5000);
		
//		 Select budget min (8L) and max(12L) and Click Search
//		WebElement budget = driver.findElement(By.id("minMaxContainer"));
//		budget.click();
		WebElement minlakhs = driver.findElement(By.id("minInput"));
		minlakhs.sendKeys("8",Keys.TAB);
		System.out.println("Minimum budget value has been set");
		WebElement maxlakhs = driver.findElement(By.id("maxInput"));
		maxlakhs.sendKeys("12",Keys.TAB);
		System.out.println("Maximum budget value has been set");
		WebElement search = driver.findElement(By.id("btnFindCar"));
		search.click();
		System.out.println("Search operation is clicked");
		
//		Select Cars with Photos under Only Show Cars With
		WebElement carphotos = driver.findElement(By.name("CarsWithPhotos"));
		carphotos.click();
		if (carphotos.isSelected()) {
			System.out.println("Carphotos is selected");
		}
		Thread.sleep(3000);
		
		
//		Select Manufacturer as "Hyundai" --> Creta
		js.executeScript("window.scrollBy(0,300)", "");
		WebElement hyundai = driver.findElement(By.xpath("//span[text()=' Hyundai ']"));
		hyundai.click();
		System.out.println("Hyundai is clicked");
		WebElement creta = driver.findElement(By.xpath("//span[text()='Creta']"));
		creta.click();
		System.out.println("Creta is clicked");
		
//		Select Fuel Type as Petrol
		WebElement fueltype = driver.findElement(By.xpath("(//h3[@class='sub-values'])[6]"));
		act.moveToElement(fueltype).perform();
		System.out.println("Fuel type is clicked");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,300)", "");
//		wt.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[@class='us-sprite']//span[text()='Petrol']"))));
		WebElement petrol = driver.findElement(By.xpath("//span[text()='Petrol']"));
		js.executeScript("arguments[0].click()", petrol);
		System.out.println("Petrol is clicked");
		
//		Select Best Match as "KM: Low to High"
		WebElement bestmatch = driver.findElement(By.id("sort"));
		Select s = new Select(bestmatch);
		s.selectByIndex(4);
		System.out.println("KM: Low to High dropdown option is selected");
		
//		Validate the Cars are listed with KMs Low to High
		List<WebElement> kmsElement = driver.findElements(By.xpath("//table[@class='card-detail__vehicle-data']//td[1]/span"));
		List<Integer> lis = new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < kmsElement.size(); i++) {
			String string = kmsElement.get(i).getText().replaceAll("\\D", "");
			int km = Integer.parseInt(string);
			lis.add(km);
		}
		for (int i = 0; i < kmsElement.size(); i++) {
			String string = kmsElement.get(i).getText().replaceAll("\\D", "");
			int km = Integer.parseInt(string);
			list.add(km);
		}
		
		Collections.sort(list);
		System.out.println("sorted" + list);

		if (list == lis) {
			System.out.println("KM sorted low to high");
		} else {
			System.out.println("KM not sorted correctly from low to high");
		}
		
//		Add the least KM ran car to Wishlist
		WebElement wishlist = driver.findElement(By.xpath("//span[@class='shortlist shortlist-icon--inactive']"));
		wishlist.click();
		System.out.println("Wishlist icon is Clicked");
		
		
//		Go to Wishlist and Click on More Details
		WebElement wishclick = driver.findElement(By.xpath("//li[@class='action-box shortlistBtn overflowVisible']"));
		wishclick.click();
		System.out.println("Wishlist checkout screen is Clicked");
		WebElement moredetails = driver.findElement(By.xpath("//a[text()='More details »']"));
		moredetails.click();
		System.out.println("More details screen is Clicked");
		
		
//		Print all the details under Overview
		Set<String> allwin = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(allwin);
		driver.switchTo().window(child.get(1));
		System.out.println("The Current Window title is:" +driver.getTitle());
	    
		List<WebElement> overview = driver.findElements(By.xpath("//div[@class='cw-tabs-data']/div[@class='overview-list padding-bottom10']/ul/li/div"));
		for (WebElement print : overview) {
			String text = print.getText();
			System.out.println(text);
			
		}
		
		System.out.println("CarVale TestCases Completed");
	
        driver.quit();
	}

}
