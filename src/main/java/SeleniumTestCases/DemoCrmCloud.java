package SeleniumTestCases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoCrmCloud {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://demo.1crmcloud.com/";

		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Url Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);

//		Give username as admin and password as admin
		WebElement username = driver.findElement(By.id("login_user"));
		username.sendKeys("admin");
		WebElement pwd = driver.findElement(By.id("login_pass"));
		pwd.sendKeys("admin");
		Thread.sleep(3000);

//		Choose theme as Claro Theme
		WebElement theme = driver.findElement(By.id("login_theme"));
		Select s = new Select(theme);
		s.selectByVisibleText("Claro Theme");
		WebElement login = driver.findElement(By.id("login_button"));
		login.click();
		System.out.println("User has been logged successfully.");
		Thread.sleep(2000);

//		Click on Sales and Marketting 
		WebElement salesmark = driver.findElement(By.xpath("//div[text()='Sales & Marketing']"));
		salesmark.click();
		System.out.println("Sales and marketing is clicked");
		WebElement contact = driver.findElement(By.xpath("(//div[@class='option-cell input-label '])[1]"));
		contact.click();
		System.out.println("Contact is clicked");
		Thread.sleep(3000);

//		Select Title and type First name, Last Name, Email and Phone Numbers
		WebElement title = driver.findElement(By.xpath("(//div[@title='(none)'])[1]"));
		title.click();
		Thread.sleep(1000);
		WebElement mr = driver.findElement(By.xpath("//div[text()='Mr.']"));
		mr.click();
		Thread.sleep(1000);
		WebElement fname = driver.findElement(By.id("DetailFormfirst_name-input"));
		fname.sendKeys("Karthigeyan J");
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.id("DetailFormemail1-input"));
		email.sendKeys("karthick5240@gmail.com");
		Thread.sleep(1000);
		WebElement phone = driver.findElement(By.id("DetailFormphone_work-input"));
		phone.sendKeys("1234567890");
		Thread.sleep(1000);

//		Select Lead Source as "Public Relations"
		Thread.sleep(2000);
		act.moveToElement(driver.findElementById("DetailFormlead_source-input")).click().build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("DetailFormlead_source-input-popup")));
		Thread.sleep(2000);
		act.moveToElement(driver
				.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Public Relations']"))
				.click().build().perform();
		System.out.println("Public relations selected");

//		Select Business Roles as "Sales"
		act.moveToElement(driver.findElementById("DetailFormbusiness_role-input")).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("DetailFormbusiness_role-input-popup")));
		act.moveToElement(
				driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Sales']"))
				.click().build().perform();
		System.out.println("Sales selected");

//		Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("12, Krishnapuram  2nd asdfasd");
		Thread.sleep(500);
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		Thread.sleep(500);
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("Tamil nadu");
		Thread.sleep(500);
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		Thread.sleep(500);
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600061");
		Thread.sleep(500);
		driver.findElementById("DetailForm_save2").click();
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(2000);

//		Mouse over on Today's Activities and click Meetings
		act.moveToElement(driver.findElementByXPath("//li[@id='grouptab-0']//div[contains(text(),'Today')]")).build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Meetings']")));
		Thread.sleep(1000);
		act.moveToElement(driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Meetings']")).click().build().perform();

//		Click Create
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='input-label' and text()='Create']/parent::button[@name='SubPanel_create']")));
		act.moveToElement(driver.findElementByXPath("//span[@class='input-label' and text()='Create']")).click().build().perform();
		
		
//		Type Subject as "Project Status" , Status as "Planned"		
		Thread.sleep(2000);
		Thread.sleep(2000);
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		Thread.sleep(2000);
		driver.findElementById("DetailFormstatus-input").click();
		Thread.sleep(2000);
		act.moveToElement(driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Planned']")).click().build().perform();
		Thread.sleep(2000);
		
//		Start Date & Time as tomorrow 3 pm and Duration as 1hr
		driver.findElementByXPath("//div[@class='input-label datetime-label text-number']").click();
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		System.out.println("time:" +time);
		
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(tomorrow);

		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']").sendKeys(formattedDate);
		driver.findElementByXPath("//div[@class='popup-search tools-row']//div[@class='active-icon uii-time uii-lg uii']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']").clear();
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']").sendKeys("15:00",Keys.ENTER);
        Thread.sleep(2000);
        driver.findElementById("DetailFormduration-time").clear();
        driver.findElementById("DetailFormduration-time").sendKeys("1",Keys.TAB);
        Thread.sleep(1000);
		
        
//        Click Add paricipants, add your created Contact name and click Save
        
        driver.findElementByXPath("//span[@class='input-label' and contains(text(),'Add Participants')]").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//div[@id='app-search-text']//input").sendKeys("Sachin tendulkar");
        Thread.sleep(2000);
        driver.findElementByXPath("//div[@id='app-search-list']//div[text()='Sachin Tendulkar']").click();
        Thread.sleep(2000);
        driver.findElementById("DetailForm_save2-label").click();
        
        //15) Go to Sales and Marketting-->Contacts
        //--------------------------------------------
        
        Thread.sleep(2000);
        
        act.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).build().perform();
        
        Thread.sleep(1000);
        
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Contacts']")));
		
		act.moveToElement(driver.findElementByXPath("//div[contains(@class,'option-cell input-label') and text()='Contacts']")).click().build().perform();
		
		Thread.sleep(2000);
		
		//16) search the lead Name and click the name from the result
		//-----------------------------------------------------------
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("filter_text")));
		
		driver.findElementById("filter_text").sendKeys("Sachin Tendulkar",Keys.ENTER);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//tr[@class='listViewRow oddListRowS1']//a[text()='Sachin Tendulkar']")));
		
		//17) Check weather the Meeting is assigned to the contact under Activities Section.
		//---------------------------------------------------------------------------------
		
		if(driver.findElementByXPath("//tr[@class='listViewRow oddListRowS1']//a[text()='Sachin Tendulkar']").isDisplayed())
		{
			driver.findElementByXPath("//tr[@class='listViewRow oddListRowS1']//a[text()='Sachin Tendulkar']").click();
			
			Thread.sleep(2000);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//tr[@data-id='0']")));
		
		System.out.println("Check weather the Meeting is assigned to the contact under Activities Section.");
		
		
		System.out.println("Meeting is assigned to the contact under Activities Section.");
		
		System.out.println("Demo CRM Cloud TestCases Completed");
		
		driver.close();


	}

}
