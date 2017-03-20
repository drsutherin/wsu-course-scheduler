/**
 * APPLICATION:		Wright State Automated Course Scheduler
 * AUTHOR:			David Sutherin
 * DATE:			March 2017
 * DESCRIPTION:		This application was developed to automate the process of scheduling courses at
 * 					Wright State University using the WINGS Express online portal
 * 
 * USE:				After exporting this project as a Runnable JAR (including Selenium libs), it can
 * 					be scheduled to run automatically at the time you're allowed to register
 * 
 * REFERENCES:		Creating a Runnable JAR from a Selenium script: http://doc.alertsite.com/synthetic/monitors/selenium/create-runnable-jar-from-selenium-script-using-eclipse.htm#main
 * 					Waits in Selenium scripts:						http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp
 * 
 * TODO:			Add fancy GUI to request UID/password/term/CRNs (+ automate task scheduling/display instructions in window)
 */

package autoregister;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

// Added for main method
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Autoregister {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	// Main method is necessary to export as a Runnable JAR
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(Autoregister.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://wingsexpress.wright.edu/pls/PROD/twbkwbis.P_WWWLogin";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testJava() throws Exception {
		// Log in
		driver.get(baseUrl);
		driver.findElement(By.id("UserID")).clear();
		driver.findElement(By.id("UserID")).sendKeys("u00011000");							// ENTER YOUR UID HERE
		driver.findElement(By.name("PIN")).clear();
		driver.findElement(By.name("PIN")).sendKeys("p@ssw0rd");		  					// ENTER YOUR PASSWORD HERE
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		Thread.sleep(1000);		// Use Thread.sleep() due to inconsistent performance of WebDriverWait & implicitly_wait()
		
		// Navigate to add/drop page
		driver.findElement(By.partialLinkText("Student")).click();
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Registration")).click();	// Use partial b/c ampersand was preventing detection
		Thread.sleep(1000);
		driver.findElement(By.linkText("Add or Drop Classes")).click();
		Thread.sleep(1000);
		
		// Select term
		new Select(driver.findElement(By.id("term_id"))).selectByVisibleText("Summer 2017");// ADD THE TERM YOU WANT TO REGISTER FOR HERE
		driver.findElement(By.cssSelector("div.pagebodydiv > form > input[type=\"submit\"]")).click();
		
		// ADD UP TO 10 COURSES BY CRN (leave extras blank)
		driver.findElement(By.id("crn_id1")).clear();
		driver.findElement(By.id("crn_id1")).sendKeys("11111");
		driver.findElement(By.id("crn_id2")).clear();
		driver.findElement(By.id("crn_id2")).sendKeys("22222");
		driver.findElement(By.id("crn_id3")).clear();
		driver.findElement(By.id("crn_id3")).sendKeys("33333");
		driver.findElement(By.id("crn_id4")).clear();
		driver.findElement(By.id("crn_id4")).sendKeys("");
		driver.findElement(By.id("crn_id5")).clear();
		driver.findElement(By.id("crn_id5")).sendKeys("");
		driver.findElement(By.id("crn_id6")).clear();
		driver.findElement(By.id("crn_id6")).sendKeys("");
		driver.findElement(By.id("crn_id7")).clear();
		driver.findElement(By.id("crn_id7")).sendKeys("");
		driver.findElement(By.id("crn_id8")).clear();
		driver.findElement(By.id("crn_id8")).sendKeys("");
		driver.findElement(By.id("crn_id9")).clear();
		driver.findElement(By.id("crn_id9")).sendKeys("");
		driver.findElement(By.id("crn_id10")).clear();
		driver.findElement(By.id("crn_id10")).sendKeys("");
		
		// Submit form
		driver.findElement(By.xpath("(//input[@name='REG_BTN'])[2]")).click();
		
		// Sleep 60s to allow for confirmation before closing window
		// TODO: screenshot/email confirmation
		Thread.sleep(60000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
