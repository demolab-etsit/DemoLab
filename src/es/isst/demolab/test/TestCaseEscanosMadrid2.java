package es.isst.demolab.test;
//Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
public class TestCaseEscanosMadrid2 {
private WebDriver driver;
private Map<String, Object> vars;
JavascriptExecutor js;
@Before
public void setUp() {
	//Indicamos donde se encuentra el archivo con el driver de chrome
	System.setProperty( "webdriver.chrome.driver", "C:\\Users\\smp-d\\eclipse-workspace-DemoLab3\\DemoLab\\src\\es\\isst\\demolab\\test\\chromedriver.exe");
	driver = new ChromeDriver();
js = (JavascriptExecutor) driver;
vars = new HashMap<String, Object>();
}
@After
public void tearDown() {
driver.quit();
}
@Test
public void TestCaseEscanosMadrid2() {
  driver.get("http://localhost:8080/ISST2019undefined");
  driver.findElement(By.cssSelector(".button:nth-child(3)")).click();
  driver.findElement(By.name("PSOEMadrid")).click();
  driver.findElement(By.name("PSOEMadrid")).sendKeys("1024452");
  driver.findElement(By.name("PPMadrid")).click();
  driver.findElement(By.name("PPMadrid")).sendKeys("699903");
  driver.findElement(By.name("CsMadrid")).click();
  driver.findElement(By.name("CsMadrid")).sendKeys("786026");
  driver.findElement(By.name("PODEMOSMadrid")).click();
  driver.findElement(By.name("PODEMOSMadrid")).sendKeys("609803");
  driver.findElement(By.name("VOXMadrid")).click();
  driver.findElement(By.name("VOXMadrid")).sendKeys("520416");
  driver.findElement(By.cssSelector(".button:nth-child(4)")).click();
}
}
