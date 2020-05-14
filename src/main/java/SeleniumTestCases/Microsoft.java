package SeleniumTestCases;

import java.io.File;
import java.util.HashMap;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


public class Microsoft {

	public static void main(String[] args) throws Exception {
		
		String url = "https://azure.microsoft.com/en-in/";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		HashMap<String, Object> chromeprefs = new HashMap<String, Object>();
		chromeprefs.put("plugins.always_open_pdf_externally", true);
        chromeprefs.put("download.default_directory", "D:\\downloads");
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("prefs", chromeprefs);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
//		Click on Pricing
		driver.findElement(By.xpath("//a[text()='Pricing']")).click();
		System.out.println("Pricing is Clicked");
		Thread.sleep(1000);
		
//      Click on Pricing Calculator
        driver.findElement(By.xpath("//a[contains(text(),'Pricing calculator')]")).click();
        System.out.println("Pricing Calculator is clicked");
        Thread.sleep(2000);
        
        
//      Click on Containers
        js.executeScript("window.scrollBy(0,500)", "");
     	driver.findElement(By.xpath("//button[text()='Containers']")).click(); 
     	System.out.println("Containers is clicked");
     	Thread.sleep(3000);
     		
//      Select Container Instances
     	driver.findElement(By.xpath("(//button[@title='Container Instances'])[2]")).click();
     	System.out.println("Containers Instance is clicked");
     	Thread.sleep(3000);
     	
     	
//     	Click on Container Instance Added View
     	driver.findElement(By.xpath("//a[text()='View']")).click();
     	System.out.println("Container Instances view is Clicked");
     	
     	
//     	Select Region as "South India"
     	js.executeScript("window,scrollBy(0,800)", "");
     	WebElement region = driver.findElement(By.xpath("(//select[@class='select'])[1]"));
     	Select st = new Select(region);
     	st.selectByVisibleText("South India");
     	System.out.println("South Region is selected from the dropdown list");
     	
     	
//     	Set the Duration as 180000 seconds
     	WebElement seconds = driver.findElement(By.xpath("//input[@aria-label='Seconds']"));
     	seconds.clear();
     	seconds.sendKeys("80000");
     	
//     	Select the Memory as 4GB
		WebElement memoryEle = driver.findElement(By.xpath("//select[@name='memory']"));
		Select memory = new Select(memoryEle);
		memory.selectByVisibleText("4 GB");
		
//		Enable SHOW DEV/TEST PRICING
		driver.findElement(By.xpath("//span[text()='Show Dev/Test Pricing']")).click();
		System.out.println("Enable Show Dev/Test Pricing is enabled");
		Thread.sleep(500);
		
//		Select Indian Rupee  as currency
		Thread.sleep(500);
		WebElement eleCurr = driver.findElement(By.xpath("//select[@class='select currency-dropdown']"));
		Select ddCurr = new Select(eleCurr);
		ddCurr.selectByVisibleText("Indian Rupee (₹)");
		
		//12 Print the Estimated monthly price
		Thread.sleep(500);
		String monthEstimate = driver.findElement(By.xpath("(//span[@class='numeric']/span)[6]")).getText();
		String replmonth = monthEstimate.replaceAll("\\D", "");
		int parseInt = Integer.parseInt(replmonth);
		System.out.println("The monthly estimate is:  "+parseInt);
		
		
//		Click on Export to download the estimate as excel
		driver.findElement(By.xpath("//button[@class='calculator-button button-transparent export-button']")).click();
		
//		Verify the downloded file in the local folder
		File download = new File("C:\\Users\\VAIBHAV\\Downloads\\ExportedEstimate.xlsx");   
		//Desktop desktop = Desktop.getDesktop();  
		Thread.sleep(1000);
		if(download.exists()) {        
			System.out.println("Containers Instance file exists in the specied path");
				 //desktop.open(file);
		}  
		Thread.sleep(3000);
		
//		Navigate to Example Scenarios and Select CI/CD for Containers
		WebElement egEle = driver.findElement(By.xpath("//a[text()='Example Scenarios']"));
		js.executeScript("arguments[0].click();", egEle);
		Thread.sleep(500);
		WebElement ciConButton = driver.findElement(By.xpath("//button[@title='CI/CD for Containers']"));
		js.executeScript("arguments[0].click();", ciConButton);
		
//		Click Add to Estimate
		WebElement addest = driver.findElement(By.xpath("//button[text()='Add to estimate']"));
		js.executeScript("arguments[0].click();", addest);
		
//		Change the Currency as Indian Rupee
		WebElement curir = driver.findElement(By.xpath("//select[@class='select currency-dropdown']"));
		Select crr = new Select(curir);
		crr.selectByVisibleText("Indian Rupee (₹)");
		
//		Enable SHOW DEV/TEST PRICING
		WebElement devPricing = driver.findElement(By.xpath("//span[text()='Show Dev/Test Pricing']"));
		js.executeScript("arguments[0].click();", devPricing);
		
//		Export the Estimate
		WebElement expEle = driver.findElement(By.xpath("//button[@class='calculator-button button-transparent export-button']"));
		js.executeScript("arguments[0].click();", expEle);
		Thread.sleep(2000);
		
//		Verify the downloded file in the local folder		
		File fileCI = new File("E:\\Selenium\\90_Day_Training\\OutputFiles\\ExportedEstimate (1).xlsx");   
		if(fileCI.exists()) {        
			System.out.println("CI CD Containers Estimate file exists in the specied path");
		}
		
		System.out.println("Microsoft azure Testcase completed");
		driver.quit();
	
	}

}
