package SeleniumTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.honda2wheelersindia.com";

		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions ac = new Actions(driver);
		driver.manage().window().maximize();
		driver.get(url);
		String parent = driver.getWindowHandle();
		System.out.println("The parent window id is:" + parent);
		System.out.println("Url Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000);

		try {
			driver.findElement(By.xpath("//button[@class='close']"));
		} catch (Exception e) {
			System.out.println("Banner message is not displayed");
			e.printStackTrace();
		}
		Thread.sleep(5000);

//		Click on scooters and click dio
		try {
			WebElement scooter = driver.findElement(By.xpath("//a[@id='link_Scooter']"));
//			WebElement scooter = driver.findElement(By.partialLinkText("link_Scoot"));
			scooter.click();
			System.out.println("Scooter one is clicked");
			WebElement duo = driver.findElement(By.xpath("(//a[@href='/dio-BS-VI/'])[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(duo));
			duo.click();
			System.out.println("Activa Duo 2020 is Clicked");
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Click on Specifications and mouseover on ENGINE
		WebElement spec = driver.findElement(By.xpath("//a[text()='Specifications']"));
//		WebElement spec = driver.findElement(By.xpath("Specifications"));
		spec.click();
		System.out.println("Specifications is Clicked");
		WebElement engine = driver.findElement(By.xpath("//a[contains(text(),'ENGIN')]"));
		ac.moveToElement(engine).pause(2000).perform();
		System.out.println("Engine is clicked");

//		Get Displacement value for scooter dio
		String disp = driver.findElement(By.xpath("//span[text()='109.51cc']")).getText();
		String replaceAll = disp.replaceAll("\\D", "");
		int sct = Integer.parseInt(replaceAll);
		System.out.println("Get Displacement value:" + sct);

//		Go to Scooters and click Activa 125
		WebElement scootertwo = driver.findElement(By.id("link_Scooter"));
		scootertwo.click();
		System.out.println("Scooter two is clicked");
		WebElement act125 = driver.findElement(By.xpath("//a[@href='/activa125-BS-VI/']"));
		act125.click();
		System.out.println("Activa 125 is Clicked");

//		Click on Specifications and mouseover on ENGINE
		WebElement spec125 = driver.findElement(By.xpath("//a[text()='Specifications']"));
		spec125.click();
		System.out.println("Specifications Activa 125 is Clicked");
		WebElement eng125 = driver.findElement(By.xpath("//a[text()='ENGINE']"));
		ac.moveToElement(eng125).perform();

//		Get Displacement value for activa 125
		String disp125 = driver.findElement(By.xpath("//span[text()='124 cc']")).getText();
		String replace = disp125.replaceAll("\\D", "");
		int activa = Integer.parseInt(replace);
		System.out.println("Get Displacement value:" + activa);

//		Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if (sct < activa) {
			System.out.println("Activa 125 has a better displacement than Dio");
		} else {
			System.out.println("Dio has a better displacement than Activa");

//	    Click FAQ from Menu
			WebElement faq = driver.findElement(By.linkText("FAQ"));
			faq.click();
			System.out.println("FAQ Item is clicked");

//			Click Activa 125 BS-VI under Browse By Product
			WebElement bpact = driver.findElement(By.linkText("Activa 125 BS-VI"));
			bpact.click();
			System.out.println("Activa 125 BS-VI is clicked");

//			Click  Vehicle Price 
			WebElement vehicle = driver.findElement(By.xpath("//a[@href='#6a']"));
			vehicle.click();

//          Make sure Activa 125 BS-VI selected and click submit
			WebElement model = driver.findElement(By.id("ModelID6"));
			if (model.isSelected()) {
				System.out.println("Activa 125BVI is Default selected");
			} else {
				System.out.println("Activa 125BVI is not selected");
			}

//          click the price link
			WebElement pricelink = driver
					.findElement(By.xpath("//a[@href='https://www.honda2wheelersindia.com/activa125-BS-VI/price']"));
			pricelink.click();

//          Go to the new Window and select the state as Tamil Nadu and  city as Chennai
			Set<String> s = driver.getWindowHandles();
			List<String> ls = new ArrayList<String>(s);
			System.out.println(ls.get(0));

//          Go to the new Window and select the state as Tamil Nadu and  city as Chennai
			WebElement state = driver.findElement(By.id("StateID"));
			Select s1 = new Select(state);
			s1.selectByValue("28");
			WebElement district = driver.findElement(By.id("CityID"));
			Select s2 = new Select(district);
			s2.selectByValue("1524");

//          Click Search
			WebElement search = driver.findElement(By.linkText("Search"));
			search.click();

//          Print all the 3 models and their prices
		    List<WebElement> modelone = driver.findElements(By.xpath("//table[@id='gvshow']//td"));
		    for (WebElement string : modelone) {
		    	System.out.println("The print models are:" +string.getText());
			}
		    
		    System.out.println("Honda TestCases Completed");

			driver.close();

		}

	}
}
