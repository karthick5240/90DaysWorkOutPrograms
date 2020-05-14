package SeleniumTestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Studyabroad {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://studyabroad.shiksha.com/";
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

//		Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		WebElement college = driver.findElement(By.xpath("(//label[@class='menuTab-div fnt-wt melabel'])[3]"));
		act.moveToElement(college).perform();
		System.out.println("College mouse hover has been performed");
		WebElement computer = driver.findElement(By.xpath("//a[text()='MS in Computer Science &Engg']"));
		computer.click();
		System.out.println("MS Engg Colleges is Clicked");

//        Select GRE under Exam Accepted and Score 300 & Below
		WebElement gre = driver.findElement(By.xpath("//p[text()='GRE']"));
		gre.click();
		System.out.println("GRE Checkbox is clicked");
		Thread.sleep(5000);
		WebElement select = driver.findElement(By.name("examsScore[]"));
		Select s = new Select(select);
		s.selectByVisibleText("300 & below");
		System.out.println("300 below dropdown option is selected");
		Thread.sleep(3000);

//        Max 10 Lakhs under 1st year Total fees, USA under countries
		WebElement lakhs = driver.findElement(By.xpath("//p[text()='Max 10 Lakhs']"));
		lakhs.click();
		System.out.println("Max 10 Lakhs is Clicked");
		Thread.sleep(3000);
		WebElement usa = driver.findElement(By.xpath("//a[text()='USA']//parent::p//preceding-sibling::span"));
		usa.click();
		System.out.println("USA Checkbox is Clicked");
		Thread.sleep(3000);

//        Select Sort By: Low to high 1st year total fees
		WebElement sortby = driver.findElement(By.id("categorySorter"));
		Select s1 = new Select(sortby);
		s1.selectByVisibleText("Low to high 1st year total fees");
		System.out.println("Low to high 1st year total fees is selected");

//        Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation
		List<WebElement> collegelist = driver
				.findElements(By.xpath("(//div[contains(@id,'categoryPageListing_tupleId')])"));
		List<Double> totalFees = new ArrayList<Double>();
		for (int i = 1; i <= collegelist.size(); i++) {
			Thread.sleep(500);
			// check if there are 3 ✔ available and if yes, add that particular college to
			// compare
			if (driver.findElements(By
					.xpath("(//div[contains(@id,'categoryPageListing_tupleId')])[" + i + "]//span[@class='tick-mark']"))
					.size() == 3) {
				// add the fee of the colleges that has 3 ✔
				totalFees.add(Double.parseDouble(driver
						.findElement(By.xpath("(//div[@class='detail-col flLt']/p[contains(text(),'Rs')])[" + i + "]"))
						.getText().replaceAll("[^0-9\\s.]+|\\.(?!\\d)", "")));
			}
		}

		Collections.sort(totalFees);
		WebElement compare = driver.findElement(By.xpath("(//div[@class='detail-col flLt'])/p[contains(text(),'"
				+ totalFees.get(0)
				+ "')]/ancestor::div[@class='clearwidth']/following-sibling::div[@class='compare-box flRt customInputs']//span"));
		compare.click();

//		Select the first college under Compare with similar colleges
		WebElement comparecoll = driver.findElement(By.xpath("(//li[@id='compareLayerRecommendations']//a)[1]"));
		js.executeScript("arguments[0].click()", comparecoll);
		System.out.println("First college under Compare with similar colleges clicked");

//		Click on Compare College>
		WebElement comparebutton = driver.findElement(By.xpath("//div[@class='compare-col flLt']//strong"));
		comparebutton.click();
		System.out.println("Compare Colleges button clicked");

//		Select When to Study as 2021
		WebElement Study = driver.findElement(By.xpath("//div[@class='signUp-child-wrap']//strong[text()='2021']"));
		Study.click();
		System.out.println("2021 selected");

//		Select Preferred Countries as USA
		WebElement ele = driver.findElement(By.xpath("//div[text()='Preferred Countries']"));
		js.executeScript("arguments[0].click()", ele);
		driver.findElement(By.xpath("//div[@class='city-lr ctry-lr']//label[text()[normalize-space()='USA']]")).click();
		driver.findElement(By.xpath("//a[text()='ok']")).click();
		System.out.println("Preferred country USA selected");

//		Select Level of Study as Masters
		driver.findElement(By.xpath("//div[@class='signUp-child-wrap ']//strong[text()='Masters']")).click();
		System.out.println("Masters selected");

//		Select Preferred Course as MS
		driver.findElement(By.xpath("//div[@class='sp-frm selectField signup-fld invalid']")).click();
		driver.findElement(By.xpath("//li[text()='MS']")).click();
		System.out.println("MS selected");

//		Select Specialization as "Computer Science & Engineering"
		driver.findElement(By.xpath("//div[@class='sp-frm selectField signup-fld invalid  filled']")).click();
		driver.findElement(By.xpath("//li[text()='Computer Science & Engineering']")).click();
		System.out.println("Computer Science & Engineering selected");

//		Click on Sign Up
		driver.findElement(By.id("signup")).click();
		System.out.println("SignUp button clicked");
		
//		Print all the warning messages displayed on the screen for missed mandatory fields 
		System.out.println("Below are the error messages displayed for not entering the mandatory values");
		if(driver.findElements(By.xpath("//div[@class='input-helper']//div[contains(text(),'Please')]")).size()>0)
		{
			List<WebElement> errorMessagesElement = driver.findElements(By.xpath("//div[@class='input-helper']//div[contains(text(),'Please')]"));

			for (WebElement errorMessage : errorMessagesElement) 
			{
				{
					if(errorMessage.getText().length()>0) 
					{
						System.out.println(errorMessage.getText());
					}
				}
			}
		}

		driver.close();
	}

}
