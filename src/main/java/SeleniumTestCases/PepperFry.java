package SeleniumTestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {

		String url = "https://www.pepperfry.com/";

		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		ChromeOptions ct = new ChromeOptions();
		ct.merge(dc);
		WebDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Url Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions ac = new Actions(driver);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Thread.sleep(3000);

		// Mouseover on Furniture and click Office Chairs under Chairs
		WebElement furniture = driver.findElement(By.xpath("(//a[text()='Furniture'])[1]"));
		ac.moveToElement(furniture).pause(2000).perform();
		System.out.println("Furniture is Clicked");
		WebElement chair = driver.findElement(By.xpath("//a[text()='Office Chairs']"));
		ac.moveToElement(chair).click().build().perform();
		Thread.sleep(3000);

//		click Executive Chairs
		WebElement execchair = driver.findElement(By.xpath("//h5[contains(text(),'Executive Chairs')]"));
		wait.until(ExpectedConditions.elementToBeClickable(execchair));
		execchair.click();
		System.out.println("Executive chair is Clicked");
		Thread.sleep(3000);

//		Change the minimum Height as 50 in under Dimensions
		js.executeScript("window.scrollBy(0,500)", "");
		WebElement heightone = driver.findElement(By.xpath("(//input[@class='clipFilterDimensionHeightValue'])[1]"));
		heightone.clear();
		heightone.sendKeys("50");
		System.out.println("Entered the value: 50");
		Thread.sleep(3000);

		try {
			driver.findElement(By.xpath("//a[@class='popup-close']")).click();
		} catch (Exception e) {
			System.out.println("Close icon is not displayed");
			e.printStackTrace();
	
		}
//		Add "Poise Executive Chair in Black Colour" chair to Wishlist
		try {
			WebElement wishlist = driver.findElement(By.xpath("(//a[@class='clip-heart-icn pf-right'])[2]"));
//			WebElement wishlist = driver.findElement(By.xpath("//a[text()='Poise Executive Chair in Black Colour']//ancestor::div[@class='clip-dtl-ttl row']/following-sibling::div[@class='row clip-dtl-brand']//a[@id='clip_wishlist_']"));
			wishlist.click();
			System.out.println("Poise Executive Chair in Black Colour chair to Wishlist successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(3000);

//		Mouseover on Homeware and Click Pressure Cookers under Cookware
		WebElement homeware = driver.findElement(By.xpath("(//a[text()='Homeware'])[1]"));
		ac.moveToElement(homeware).pause(2000).perform();
		System.out.println("Homeware is Clicked");
		WebElement pressure = driver.findElement(By.xpath("//a[text()='Pressure Cookers']"));
		pressure.click();

		System.out.println("Homeware and pressure cookware is clicked");
		Thread.sleep(3000);

//		Select Prestige as Brand
		WebElement prestige = driver.findElement(By.xpath("//label[@for='brandsnamePrestige']"));
		prestige.click();
		System.out.println("Prestige is clicked");
		Thread.sleep(2000);

//		Select Capacity as 1-3 Ltr
		WebElement litre = driver.findElement(By.xpath("//label[text()='1 Ltr - 3 Ltr']"));
		litre.click();
		System.out.println("Litres is clicked");
		Thread.sleep(2000);

//		Add "Induction Base Aluminium Pressure Cooker- 3 Ltr" to Wishlist
		WebElement induction = driver.findElement(By.xpath("(//a[@class='clip-heart-icn pf-right'])[1]"));
		induction.click();
		Thread.sleep(2000);

//		Verify the number of items in Wishlist
		String count = driver.findElement(By.xpath("(//span[@class='count_alert'])[2]")).getText();
		System.out.println(count);
		if (count.matches("2")) {
			System.out.println("Verified the number of items in wishlsit");
		}

		//Navigate to Wishlist
		driver.findElement(By.xpath("//div[@class='wishlist_bar']//a")).click();
		System.out.println("Navigating to Wishlist");

		//Move Pressure Cooker only to Cart from Wishlist
		Thread.sleep(500);
		WebElement ele = driver.findElement(By.xpath(
				"//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By...']/ancestor::div[@class='item_details']//a[text()='Add to Cart']"));
		js.executeScript("arguments[0].click()", ele);
		System.out.println("Pressure Cooker added to cart from Wishlist");

		//Check for the availability for Pincode 600073
		driver.findElement(By.xpath("//div[@class='tabs']//a[contains(text(),'My Cart')]")).click();
		driver.findElement(By.xpath("//input[@class='srvc_pin_text']")).sendKeys("600073");
		driver.findElement(By.xpath("//a[text()='Check']")).click();
		System.out.println("Pincode Check Complete");

		// Click Proceed to Pay Securely
		Thread.sleep(1000);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed to pay securely')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Proceed to pay securely')]")).click();
		System.out.println("Proceed to pay securely clicked");

		//Click Proceed to Pay
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='PLACE ORDER'])[1]")));
		ele = driver.findElement(By.xpath("(//a[text()='PLACE ORDER'])[1]"));
		js.executeScript("arguments[0].click()", ele);
		System.out.println("order button is  clicked");

		//Capture the screenshot of the item under Order Item
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='ORDER SUMMARY']")));
		ele = driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']"));
		js.executeScript("arguments[0].click()", ele);
		WebElement screenShotEle = driver.findElement(By.xpath("//div[@class='slick-track']//li"));
		File src = screenShotEle.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/image.png");
		FileUtils.copyFile(src, dest);
		System.out.println("Element Screenshot captured under screenshot folder successfully." + '\n'
				+ "Screen shot File Name : image.png");

		driver.close();

	}
}

	