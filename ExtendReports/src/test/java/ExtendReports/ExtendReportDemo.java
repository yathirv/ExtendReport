package ExtendReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportDemo {

	ExtentReports extent; // Declaring ExtentReports class globally

	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation results");
		reporter.config().setDocumentTitle("test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gowtham");
	}

	@Test
	public void initialDemo()

	{
//		to configure Testcase report to the main class variable 

		ExtentTest test = extent.createTest("Demo"); // to access here

		System.setProperty("webdriver.chrome.driver", "/Users/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		/*
		 * driver.manage().window().maximize(); JavascriptExecutor executor =
		 * (JavascriptExecutor)driver;
		 * executor.executeScript("document.body.style.zoom = '1'");
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 */
		driver.get("https://youtube.com");
		System.out.println(driver.getTitle());

		// self not working
		String path = System.getProperty("user.dir") + "\\reports\\snap.png";
		test.addScreenCaptureFromBase64String(path);
		
		driver.close();
		test.fail("Failed");
		extent.flush();

	}

}
