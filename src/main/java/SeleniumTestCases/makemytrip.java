package SeleniumTestCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class makemytrip  {


	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.makemytrip.com/";

		//To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
//		To Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(5000);
		// Click Hotels
		WebElement hotels = driver.findElement(By.xpath("(//span[contains(@class,'chNavIcon appendBottom2')])[2]"));
		hotels.click();
		Thread.sleep(5000);
		// Enter city as Goa, and choose Goa, India
		WebElement fromdate = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter city')]"));
		fromdate.clear();
		fromdate.sendKeys("Delhi", Keys.TAB);
		Thread.sleep(5000);
		// Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElement(By.xpath("//div[@aria-label='Fri May 15 2020']")).click();
		driver.findElement(By.xpath("//span[text()='Select Checkout Date']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Tue May 19 2020']")).click();

		// 5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click
		// Apply Button.
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'hsw_inputBox roomGuests')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul/li[@data-cy='adults-2']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul/li[@data-cy='children-1']")).click();

		Select child_age = new Select(driver.findElement(By.className("ageSelectBox")));
		child_age.selectByIndex(1);

		driver.findElement(By.xpath("//button[text()='APPLY']")).click();

		// 6) Click Search button
		driver.findElement(By.xpath("//button[text()='Search']")).click();

		// 7) Select locality as Baga
		driver.findElement(By.xpath("//div[contains(@class, 'mmBackdrop wholeBlack')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='Baga']")).click();

		// 8) Select 5 start in Star Category under Select Filters
		js.executeScript("window.scrollBy(0,850)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='5 Star']")).click();

		// 9) Click on the Second resulting hotel and go to the new window
		driver.findElement(By.xpath("//div[@class='infinite-scroll-component ']/div[2]")).click();

		Set<String> window = driver.getWindowHandles();
		List<String> tabs = new ArrayList<String>(window);
		String tab1_title = driver.switchTo().window(tabs.get(1)).getTitle();
		System.out.println("Main Window title : " + tab1_title);

		// 10) Print the Hotel Name
		String hotel_name = driver.findElement(By.id("detpg_hotel_name")).getText();
		System.out.println("Hotel Name : " + hotel_name);

		// 11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElement(By.xpath("//span[text()='MORE OPTIONS']")).click();
		driver.findElement(By.xpath("//table[@class='tblEmiOption']/tbody/tr[2]/td[6]/span")).click();
		driver.findElement(By.xpath("//span[@class='close']")).click();

		// 12) Click on BOOK THIS NOW
		driver.findElement(By.id("detpg_headerright_book_now")).click();
		
		// 13) Print the Total Payable amount
		String paymnt_amt = driver.findElement(By.id("revpg_total_payable_amt")).getText();
		String amt = paymnt_amt.replaceAll("\\D", "");
		int booking_amt = Integer.parseInt(amt);
		System.out.println("Total Payable : " + booking_amt);

		// 14) Close the browser
		driver.quit();

	}

}
	

