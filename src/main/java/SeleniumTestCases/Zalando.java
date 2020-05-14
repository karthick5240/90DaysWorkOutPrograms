package SeleniumTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.zalando.com/";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(op);
		Actions act = new Actions(driver);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebDriverWait wt = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		Get the Alert text and print it
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		System.out.println(text);

//		Close the Alert box and click on Zalando.uk
		WebElement uk = driver.findElement(By.xpath("//a[@class='nav_link nav_link-gb']"));
		uk.click();
		System.out.println("Zalando UK is Clicked");

//		Click Women--> Clothing and click Coat
		WebElement women = driver.findElement(By.xpath("(//span[text()='Women'])[2]"));
		women.click();
		System.out.println("Women is Clicked");
		WebElement cloth = driver.findElement(By.xpath("//span[text()='Clothing']"));
		cloth.click();
		System.out.println("Clothing is Clicked");
		WebElement coat = driver.findElement(By.xpath("(//a[@class='cat_link-mTK6o'])[2]"));
		coat.click();
		System.out.println("Coat is Clicked");
		Thread.sleep(5000);

//		Choose Material as cotton (100%) and Length as thigh-length
		WebElement Material = driver.findElement(By.xpath("(//button[@class='cat_head-3QSpK cat_brd-4-27afw'])[6]"));
		Material.click();
		System.out.println("Material is Clicked");
		WebElement cotton = driver.findElement(By.xpath("//span[text()='cotton (100%)']"));
		cotton.click();
		WebElement save = driver.findElement(By.xpath("save"));
		save.click();
		System.out.println("Cotton 100% is Clicked");
		try {
			driver.findElement(By.xpath("//button[@class='uc-btn uc-btn-primary']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//span[text()='Length']")).click();
		driver.findElement(By.xpath("//span[text()='thigh-length']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();

//		Click on Q/S designed by MANTEL - Parka coat
		WebElement mango = driver.findElement(By.xpath("//div[text()='MANTEL - Parka - navy']"));
		act.moveToElement(mango).click().perform();

		// Check the availability for Color as Olive and Size as 'M'
		driver.findElement(By.xpath("(//img[@alt='olive'])[2]")).click();
		driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
		driver.findElement(By.xpath("//span[text()='M']")).click();

//		If the previous preference is not available, check  availability for Color Navy and Size 'M'
		if (driver.findElement(By.xpath("//h2[text()='Out of stock']")).isDisplayed()) {
			driver.findElement(By.xpath("(//img[@alt='navy'])[2]")).click();
			System.out.println("Olive isn't available, Selected Navy coat");
		} else {
			driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
			driver.findElement(By.xpath("//span[text()='M']")).click();
			System.out.println("Oilve is in stock is selected");
		}
		// Navy and Size 'M'
		driver.findElement(By.xpath("//span[text()='Choose your size']")).click();
		driver.findElement(By.xpath("//span[text()='M']")).click();

		// Add to bag only if Standard Delivery is free
		if (driver.findElement(By.xpath("(//span[text()='Free'])")).isDisplayed()) {
			driver.findElement(By.xpath("(//span[text()='Add to bag'])")).click();
			System.out.println("Free Delivery Available");
		} else {
			System.out.println("Standard Delivery fees may apply");
		}
		Thread.sleep(3000);
		// Mouse over on Your Bag and Click on "Go to Bag"
		ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Your bag']"));
		driver.findElement(By.xpath("//div[text()='Go to bag']")).click();
		Thread.sleep(3000);
		// Capture the Estimated Deliver Date and print
		System.out.println(driver.findElement(By.xpath("//div[@data-id='delivery-estimation']//span")).getText());

		// Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Free delivery & returns*']"));
		System.out.println(
				driver.findElement(By.xpath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]"))
						.getAttribute("title"));

		// Click on FREE DElIVERY & RETURNS*
		driver.findElement(By.xpath("//a[text()='Free delivery & returns*']")).click();
		Thread.sleep(5000);

		// Click on Start chat in the Start chat and go to the new window
		driver.findElement(By.xpath("(//button[@class='faq-dx-button'])[1]")).click();

		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		Thread.sleep(3000);

		// Enter you first name and a dummy email and click Start Chat
		driver.findElement(By.id("prechat_customer_name_id")).sendKeys("Karthick");
		driver.findElement(By.id("prechat_customer_email_id")).sendKeys("karthick@gmail.com");
		driver.findElement(By.id("prechat_submit")).click();
		Thread.sleep(6000);

		// Type Hi, click Send and print thr reply message and close the chat window.
		driver.findElement(By.id("liveAgentChatTextArea")).sendKeys("Hello How are u");
		driver.findElement(By.xpath("//button[text()='Send']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("(//span[@class='messageText'])[last()]")).getText());
		// driver.findElement(By.xpath("//button[text()='End Chat']")).click();
		driver.close();
		driver.quit();

		driver.close();

	}

}
