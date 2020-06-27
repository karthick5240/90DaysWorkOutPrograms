package SeleniumTestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Naukri {

	public static void main(String[] args) throws InterruptedException, AWTException {
		
		String filePath = "C:\\Users\\VAIBHAV\\Desktop\\Capture.PNG"; 

		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		RemoteWebDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Set<String> st = driver.getWindowHandles();
		List<String> allwin = new ArrayList<String>(st);
		for (int i = 1; i < allwin.size(); i++) {
            driver.switchTo().window(allwin.get(i));
              String attribute = driver.findElement(By.xpath("//a[@target='_blank']//img")).getAttribute("alt");
              System.out.println(attribute);
              driver.close();
	}
		driver.switchTo().window(allwin.get(0));
		Thread.sleep(2000);
		
		
		try {
			driver.findElement(By.xpath("//span[text()='Later']")).click();
		}catch (Exception e) {
			System.out.println("Location pop is not shown");
		}

	    Robot rb = new Robot(); 
	    driver.findElementById("wdgt-file-upload").click();
	    
	    rb.setAutoDelay(2000);
	    
	    StringSelection sc = new StringSelection(filePath); 
	    
	    // This line is similar to copying the file into clipboard 
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sc, null); 
	    
	    rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V); 
		
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_V); 
		
		rb.keyPress(KeyEvent.VK_ENTER); 
		rb.keyRelease(KeyEvent.VK_ENTER);
	    
		
		//To print the error message when we uplaod the form.
		String text = driver.findElement(By.xpath("//div[text()='CV Upload Error']/following-sibling::div")).getText();
		 System.out.println("Displayed error are:"  +text);
		 
		 driver.close();
	}
	

}
