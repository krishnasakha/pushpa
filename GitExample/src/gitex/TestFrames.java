package gitex;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestFrames {

	WebDriver wd;
	List<WebElement> framesList;

	@BeforeClass
	public void beforeClass() {
		String path = "C:\\Selenium\\CoreFiles\\";
		System.setProperty("webdriver.gecko.driver", path + "geckodriver.exe");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		wd = new FirefoxDriver(firefoxOptions);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterClass
	public void afterClass() {
		wd.quit();
	}

	@Test(priority = 1)
	public void TestLaunchFrames() {
		wd.get("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Assert.assertEquals(wd.getTitle(), "Overview");

	}

	@Test(priority = 2, dependsOnMethods = "TestLaunchFrames")
	
	public void TestFramesinPage(){
		framesList = wd.findElements(By.tagName("iframe"));
		Assert.assertEquals(framesList.size(), 3);
	}

	@Test(priority = 3, dependsOnMethods = "TestFramesinPage")
	public void TestFrameNavigation() {
//		framesList.size();
//		System.out.println(framesList.size());
		for (WebElement frame : framesList) {

			String frameName = frame.getAttribute("name");

			System.out.println("--------" + frameName + "-------");
			wd.switchTo().frame(frameName);

			List<WebElement> linksList = wd.findElements(By.tagName("a"));
			for (WebElement link : linksList) {

				System.out.println(link.getText());
			}
			wd.switchTo().defaultContent();
		}
	}
}