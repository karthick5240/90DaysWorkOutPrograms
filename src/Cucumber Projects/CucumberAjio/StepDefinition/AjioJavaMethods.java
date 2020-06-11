package StepDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;

public class AjioJavaMethods {
	
	public WebDriver driver;
	public Actions act;
	String url = "https://www.ajio.com/shop/women";

	@Given("Go to https:\\/\\/www.ajio.com\\/shop\\/women")
	public void go_to_https_www_ajio_com_shop_women() {
		String url = "https://www.ajio.com/shop/women";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     
	}

	@Given("Mouseover on Women, CATEGORIES and click on Kurtas")
	public void mouseover_on_Women_CATEGORIES_and_click_on_Kurtas() throws InterruptedException {
		Thread.sleep(2000);
		Actions act = new Actions(driver);
	     WebElement women = driver.findElement(By.xpath("//a[@href='/shop/women']"));
	     act.moveToElement(women).build().perform();
	     System.out.println("Women Mouse hover done");
	     driver.findElement(By.xpath("(//a[text()='Kurtas'])[2]")).click();
	     System.out.println("Kurtas menu is clicked");
 
	}

	@Given("Click on Brands and choose Ajio")
	public void click_on_Brands_and_choose_Ajio() {
	     WebElement brands = driver.findElement(By.xpath("(//span[@class='facet-left-pane-label'])[4]"));
	     brands.click();
	     System.out.println("Brands is Clicked");
	     WebElement ajio = driver.findElement(By.xpath("//label[@class='facet-linkname facet-linkname-brand facet-linkname-AJIO']"));
	     ajio.click();
	     System.out.println("Ajio is Clicked");
	     
	}

	@Given("Check all the results are Ajio")
	public void check_all_the_results_are_Ajio() {
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='brand']"));
		String text ="";
		for (int i = 0; i < list.size(); i++) {
			 text = list.get(i).getText();
		}
			if (text.equalsIgnoreCase("AJIO")) {
				System.out.println("All Selected items are of brand AJIO");
			} else {
				System.out.println("Brands are not AJIO");
			}
	    
	     
	}

	@Given("Set Sort by the result as Discount")
	public void set_Sort_by_the_result_as_Discount() {
	     WebElement sortby = driver.findElement(By.xpath("//div[@class='filter-dropdown']/child::select"));
	     Select s = new Select(sortby);
	     s.selectByVisibleText("Discount");
	     System.out.println("Discount is selected");
	     
	}

	@Given("Select the Color and click ADD TO BAG")
	public void select_the_Color_and_click_ADD_TO_BAG() throws InterruptedException {
		Thread.sleep(3000);
		WebElement color = driver.findElement(By.xpath("(//img[contains(@class,'rilrtl-lazy-img ')])[1]"));
		color.click();
		System.out.println("First RedColor brand is selected");
		Set<String> set = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(set);
		driver.switchTo().window(ls.get(1));
		driver.findElement(By.xpath("//img[@alt='blue']")).click();
		Thread.sleep(3000);
		WebElement addbag = driver.findElement(By.xpath("//div[@class='btn-gold']"));
		addbag.click();
		System.out.println("Add to Bag is Clicked");
	     
	}

	@Given("Verify the error message Select your size to know your estimated delivery date")
	public void verify_the_error_message_Select_your_size_to_know_your_estimated_delivery_date() {
		WebElement errorMsg = driver.findElement(By.className("edd-pincode-msg-details"));
		System.out.println("Error Msg is :" + errorMsg.getText());
	     
	}

	@Given("Select size and click ADD TO BAG")
	public void select_size_and_click_ADD_TO_BAG() throws InterruptedException {
		WebElement size = driver.findElement(By.xpath("(//div[text()='XS'])[1]"));
		size.click();
		Thread.sleep(3000);
		WebElement addbag = driver.findElement(By.xpath("//span[text()='ADD TO BAG']"));
		addbag.click();
		Thread.sleep(8000);
	     
	}

	@Given("click on Enter pin-code to know estimated delivery date")
	public void click_on_Enter_pin_code_to_know_estimated_delivery_date() {
		WebElement estimated = driver.findElement(By.xpath("//span[contains(@class,'edd-pincode-msg-details edd-pincode-msg-details-pointer')]"));
		estimated.click();
	}

	@Given("Enter the pincode as {int} and click Confirm pincode")
	public void enter_the_pincode_as_and_click_Confirm_pincode(Integer int1) throws InterruptedException {
		Thread.sleep(3000);
		WebElement enterpin = driver.findElement(By.className("edd-pincode-modal-text"));
		enterpin.sendKeys("603103");
		WebElement confirm = driver.findElement(By.className("edd-pincode-modal-submit-btn"));
		confirm.click();
		Thread.sleep(2000);
	     
	}

	@Given("Print the message and click Go to Bag")
	public void print_the_message_and_click_Go_to_Bag() throws InterruptedException {
		String msg = driver.findElement(By.className("edd-message-success-details")).getText();
		System.out.println("Delivery message is: " + msg);
		WebElement cartadd = driver.findElement(By.className("ic-pdp-add-cart"));
		cartadd.click();
		Thread.sleep(3000);
	     
	}

	@Then("Click on Proceed to Shipping and close the browser")
	public void click_on_Proceed_to_Shipping_and_close_the_browser() {
		WebElement proceed = driver.findElement(By.xpath("//button[text()='Proceed to shipping']"));
		proceed.click();
		driver.quit();
	     
	}

}
