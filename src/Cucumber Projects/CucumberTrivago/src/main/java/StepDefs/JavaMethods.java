package StepDefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import cucumber.api.java.en.*;

public class JavaMethods {
	
	public WebDriver driver;
	public JavascriptExecutor js;
	public WebDriver wt;
	public Actions act;
	
	@Given("Launch the Browser")
	public void launch_the_Browser() {
		// To display the console output in readable manner
				System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--disable-notifications");
				driver = new ChromeDriver(op);
				System.out.println("Browser is launched successfully");
	     
	}

	@Given("Load the URL")
	public void load_the_URL() {
		driver.get("https://www.trivago.com/");
	     
	}

	@Given("Maximise the Browser")
	public void maximise_the_Browser() {
		driver.manage().window().maximize();
	     
	}

	@Given("Set the Timeouts")
	public void set_the_Timeouts() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     
	}

	@Given("Type Agra in Destination and select Agra, Uttar Pradesh.")
	public void type_Agra_in_Destination_and_select_Agra_Uttar_Pradesh() {
		 WebElement destination = driver.findElement(By.id("querytext"));
		    destination.sendKeys("Agra",Keys.ENTER);
	     
	}

	@Given("Choose May {int} as check in and May {int} as check out")
	public void choose_May_as_check_in_and_May_as_check_out(Integer int1, Integer int2) throws InterruptedException {
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//time[@datetime='2020-06-19']")).click(); 
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//time[@datetime='2020-06-30']")).click(); 
	    Thread.sleep(3000);
	     
	     
	}

	@Given("Select Room as Family Room")
	public void select_Room_as_Family_Room() {
		WebElement familyroom = driver.findElement(By.xpath("(//div[@class='roomtype-btn__wrap'])[3]"));
	    familyroom.click();
	     
	}

	@Given("Choose Number of Adults {int}, Childern {int} and set Child's Age as {int}")
	public void choose_Number_of_Adults_Childern_and_set_Child_s_Age_as(Integer int1, Integer int2, Integer int3) {
		WebElement adult = driver.findElement(By.id("select-num-adults-2"));
		Select s = new Select(adult);
		s.selectByValue("2");
		WebElement child = driver.findElement(By.id("select-num-children-2"));
		Select s1 = new Select(child);
		s1.selectByValue("1");
	}

	@Given("Click Confirm button and click Search")
	public void click_Confirm_button_and_click_Search() {
	     WebElement confirm = driver.findElement(By.xpath("//span[text()='Confirm']/parent::button"));
	     confirm.click();
	     WebElement search = driver.findElement(By.xpath("//span[text()='Search']/parent::button"));
	     search.click();
	}

	@Given("Select Accommodation type as Hotels only and choose {int} stars")
	public void select_Accommodation_type_as_Hotels_only_and_choose_stars(Integer int1) throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement acchotel = driver.findElement(By.xpath("(//button[@class='filter-item filter-item--select js-toolbar-hover-button'])[1]"));
	     act.moveToElement(acchotel).perform();
	    WebElement filter = driver.findElement(By.id("acc-type-filter-1"));
	    filter.click();
	    Thread.sleep(2000);
	    WebElement stars = driver.findElement(By.xpath("//div[@class='refinement-row__content']//button[@title='4-star hotels']"));
	    stars.click();
	}

	@Given("Select Guest rating as Very Good")
	public void select_Guest_rating_as_Very_Good() throws InterruptedException {
		driver.findElement(By.xpath("//span[@class='filter-item__placeholder']/span[text()='All']")).click();
	    driver.findElement(By.xpath("//span[text()='Very good']")).click();
	    System.out.println("Rating -> Very Good selected");
	     Thread.sleep(3000);
	}

	@Given("Set Hotel Location as Agra Fort and click Done")
	public void set_Hotel_Location_as_Agra_Fort_and_click_Done() {
		WebElement hotelloc = driver.findElement(By.xpath("(//span[@class='filter-item__placeholder']/parent::button)[3]"));
		hotelloc.click();
		WebElement adult = driver.findElement(By.id("pois"));
		Select s = new Select(adult);
		s.selectByVisibleText("Agra Fort");
	    WebElement done = driver.findElement(By.id("filter-popover-done-button"));
	    done.click();
	}

	@Given("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void in_more_Filters_select_Air_conditioning_Restaurant_and_WiFi_and_click_Done() {driver.findElement(By.xpath("//span[@class='filter-item__placeholder']/span[text()='Select']")).click();
		   
		    driver.findElement(By.xpath("//label[text()='Air conditioning']")).click();
		    driver.findElement(By.xpath("//label[text()='WiFi']")).click();
		    driver.findElement(By.id("more-filters-search")).sendKeys("Restaurant");
		    driver.findElement(By.xpath("//mark[text()='Restaurant']")).click();
		    driver.findElement(By.id("filter-popover-done-button")).click();
		    System.out.println("More filters set with Air Conditioning, Restaurant and Wifi");
	     
	}

	@Given("Sort the result as Rating & Recommended")
	public void sort_the_result_as_Rating_Recommended() {
	     WebElement sortby = driver.findElement(By.id("mf-select-sortby"));
	     Select s4 = new Select(sortby);
	     s4.selectByVisibleText("Rating & Recommended");
	     
	}

	@Given("Print the Hotel name, Rating, Number of Reviews and Click View Deal")
	public void print_the_Hotel_name_Rating_Number_of_Reviews_and_Click_View_Deal() {
		String text = driver.findElement(By.xpath("//span[@class='item-link name__copytext']")).getText();
		System.out.println("The hotel names are:" + text);
		String text2 = driver.findElement(By.xpath("//span[@class='reviews reviews--hover']")).getText();
		System.out.println("The Recviews List are:" + text2);
		String text3 = driver.findElement(By.xpath("//span[text()='8.4']")).getText();
		System.out.println("The hotel names are:" + text3);
	     
	}

	@Given("Print the URL of the Page")
	public void print_the_URL_of_the_Page() {
	     Set<String> windows = driver.getWindowHandles();
	     List<String> windowHandlesList = new ArrayList<String>(windows);
	     System.out.println(windowHandlesList.get(1));
	     System.out.println("Current URL: "+driver.getCurrentUrl());

	}
	@Given("Print the Price of the Room and click Choose Your Room")
	public void print_the_Price_of_the_Room_and_click_Choose_Your_Room() {
		WebElement inr = driver.findElement(By.id("currency"));
	     Select s4 = new Select(inr);
	     s4.selectByVisibleText("INR");
	     String price = driver.findElement(By.xpath("(//div[@class='prco-ltr-right-align-helper']/child::div)[2]")).getText();	
	     System.out.println(price);
	     WebElement searchroom = driver.findElement(By.xpath("(//span[@class='bui-button__text'])[1]/parent::a"));
	     searchroom.click();
	}
	@Given("Click Reserve and I'll Reserve")
	public void click_Reserve_and_I_ll_Reserve() {
	     WebElement reserve = driver.findElement(By.id("hp_book_now_button"));
	     reserve.click();
	     JavascriptExecutor js = ((JavascriptExecutor) driver);
	     js.executeScript("window.scrollBy(0,800)","");
	     WebElement illreserve = driver.findElement(By.id("b_tt_holder_7"));
	     illreserve.click();
	}

	@Then("Close the browser")
	public void close_the_browser() {
	     driver.quit();
	     
	     System.out.println("Trivago testcases Completed");
	     
	}

   


}
