package StepDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;

public class JavaMethods {
	
	public ChromeDriver driver;
	public JavascriptExecutor js;
	
	@Given("User opens the Chrome browser")
	public void user_opens_the_Chrome_browser() {
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		driver = new ChromeDriver(op);
	}

	@Given("maximises the browser")
	public void maximises_the_browser() {
		driver.manage().window().maximize();
	    
	}

	@Given("launches the CarWale application")
	public void launches_the_CarWale_application() {
		driver.get("https://www.carwale.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("clicks Used option")
	public void clicks_used_option() throws InterruptedException {
		WebElement usedcars = driver.findElement(By.xpath("//li[@class='welcome-box__search-item js-welcome-box__list-item']"));
		usedcars.click();
		System.out.println("Used Cars menu is Clicked");
		Thread.sleep(5000);
	    
	}

	@Given("selects city as Chennai")
	public void selects_city_as_Chennai() throws InterruptedException {
		WebElement city = driver.findElement(By.id("usedCarsList"));
		city.sendKeys("Chennai");
		Thread.sleep(2000);
		WebElement cityselect = driver.findElement(By.id("usedCarsList"));
		cityselect.sendKeys(Keys.TAB);
		System.out.println("Chennai city has been selected");
		Thread.sleep(5000);
	    
	}

	@Given("selects budget min 8L and max 12L")
	public void selects_budget_min_8L_and_max_12L() throws InterruptedException {
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		driver.findElementByXPath("(//span[@class='welcome-box__search-icon'])[2]").click();
		Thread.sleep(2000);

	    	}

	@Given("clicks Search")
	public void clicks_Search() {
		WebElement search = driver.findElement(By.id("btnFindCar"));
		search.click();
		System.out.println("Search operation is clicked");
	    
	}

	@Given("selects Cars with Photos under Only Show Cars with from the search results")
	public void selects_Cars_with_Photos_under_Only_Show_Cars_with_from_the_search_results() throws InterruptedException {
		WebElement carphotos = driver.findElement(By.name("CarsWithPhotos"));
		carphotos.click();
		if (carphotos.isSelected()) {
			System.out.println("Carphotos is selected");
		}
		Thread.sleep(3000);
	    
	}

	@Given("selects Manufacturer as Hyundai and car as Creta")
	public void selects_Manufacturer_as_Hyundai_and_car_as_Creta() {
		js.executeScript("window.scrollBy(0,300)", "");
		WebElement hyundai = driver.findElement(By.xpath("//span[text()=' Hyundai ']"));
		hyundai.click();
		System.out.println("Hyundai is clicked");
		WebElement creta = driver.findElement(By.xpath("//span[text()='Creta']"));
		creta.click();
		System.out.println("Creta is clicked");
	    
	}

	@Given("selects Fuel type as Petrol")
	public void selects_Fuel_type_as_Petrol() throws InterruptedException {
		WebElement fueltype = driver.findElement(By.xpath("(//h3[@class='sub-values'])[6]"));
		Actions act = new Actions(driver);
		act.moveToElement(fueltype).perform();
		System.out.println("Fuel type is clicked");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,300)", "");
//		wt.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[@class='us-sprite']//span[text()='Petrol']"))));
		WebElement petrol = driver.findElement(By.xpath("//span[text()='Petrol']"));
		js.executeScript("arguments[0].click()", petrol);
		System.out.println("Petrol is clicked");
	    
	}

	@Given("sorts the results as KM: Low to High")
	public void sorts_the_results_as_KM_Low_to_High() {
		WebElement bestmatch = driver.findElement(By.id("sort"));
		Select s = new Select(bestmatch);
		s.selectByIndex(4);
		System.out.println("KM: Low to High dropdown option is selected");
	    
	}

	@Given("validates the Cars listed with KM Low to High and adds the least KM run car to Wishlist")
	public void validates_the_Cars_listed_with_KM_Low_to_High_and_adds_the_least_KM_run_car_to_Wishlist() {
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
		WebElement wishlist = driver.findElement(By.xpath("//span[@class='shortlist shortlist-icon--inactive']"));
		wishlist.click();
		System.out.println("Wishlist icon is Clicked");
	    
	}

	@Given("goes to Wishlist and Click on More Details")
	public void goes_to_Wishlist_and_Click_on_More_Details() {
		WebElement wishclick = driver.findElement(By.xpath("//li[@class='action-box shortlistBtn overflowVisible']"));
		wishclick.click();
		System.out.println("Wishlist checkout screen is Clicked");
		WebElement moredetails = driver.findElement(By.xpath("//a[text()='More details »']"));
		moredetails.click();
		System.out.println("More details screen is Clicked");
	    
	}

	@When("prints all the details under Overview in the Same way as displayed in application")
	public void prints_all_the_details_under_Overview_in_the_Same_way_as_displayed_in_application() {
		Set<String> allwin = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(allwin);
		driver.switchTo().window(child.get(1));
		System.out.println("The Current Window title is:" +driver.getTitle());
	    
		List<WebElement> overview = driver.findElements(By.xpath("//div[@class='cw-tabs-data']/div[@class='overview-list padding-bottom10']/ul/li/div"));
		for (WebElement print : overview) {
			String text = print.getText();
			System.out.println(text);
			
		}
	    
	}

	@Then("Close the browser.")
	public void close_the_browser() {
		driver.quit();
	    
	}
	
	     
	}


	     
	




