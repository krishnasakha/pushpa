package Testng1;

import org.testng.annotations.Test;

import Practicedprg.foreach;
import package123.BrowserSetup;
import package123.BrowserSetup.Browser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestFrames {
	WebDriver wd=null;
	List<WebElement> framesList;
  @BeforeClass
  public void beforeClass() throws Exception {
	 
		BrowserSetup b;
		b=new BrowserSetup(Browser.CHROME,false);
		wd=b.navigateToURL("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
		
  }

  @AfterClass
  public void afterClass() {
	  wd.close();
 
  }
  @Test(priority=1)
  public void TestLaunchFrames() {
	  wd.get("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
	  wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	  Assert.assertEquals(wd.getTitle(), "Overview");
  }
  @Test(priority=2,dependsOnMethods="TestLaunchFrames")
  public void TestFramesinPage() {

		  framesList=wd.findElements(By.tagName("iframe"));
		  Assert.assertEquals(framesList.size(), 3);
	  }
  @Test(priority=3, dependsOnMethods="TestLaunchFrames")
  public void Testfamenavigation() {
	  for(WebElement frame:framesList) {
		  String frameName=frame.getAttribute("name");
				  System.out.println("-------"+frameName+"-------");
		  wd.switchTo().frame(frameName);
		  List<WebElement> linksList=wd.findElements(By.tagName("a"));
		  for(WebElement link:linksList) {
			  System.out.println(link.getText());
		  }
		  wd.switchTo().defaultContent();
		  
	  }
  }
}
