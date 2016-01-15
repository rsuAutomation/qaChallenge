package framework;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestCaseFramework {

	protected Log log = LogFactory.getLog(this.getClass());
	public static final String GROUP_SMOKE = "smoke";
	public static final String GROUP_REGRESSION= "regression";

	@BeforeMethod(alwaysRun=true)
	@Parameters("browser")
		protected void testMethodStart(@Optional("chrome") String browser){
		DriverManager.setupDriver(browser);
	}
	
	@AfterMethod(alwaysRun=true)
	protected void testMethodEnd(){
		DriverManager.quitDriver();
	}
	
	
	@BeforeClass(alwaysRun=true)
	protected void testCaseStart(){
		log.info("#####################################################");
		log.info("\\/\\/\\/\\/\\/\\/---TestCase = "+ this.getClass().getSimpleName()+"---\\/\\/\\/\\/\\/\\/");
		
	}
	
	@AfterClass(alwaysRun=true)
	protected void testCaseEnd(){
		log.info("/\\/\\/\\/\\/\\/\\---TestCase = "+ this.getClass().getSimpleName()+"---/\\/\\/\\/\\/\\/\\");
		log.info("#####################################################");
	}
	
	public static class DriverManager {
		/**
		 * shares the same web driver and use thread local to handle the multi-thread
		 */
		public static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>() ;

		public static String browserType = "firefox";
		
		/**
		 * create a driver for this thread if not exist. or return it directly
		 */
		public static WebDriver getDriver(){
			WebDriver driver= DriverManager.ThreadDriver.get();
			if (driver == null) {
				if (browserType.equals("firefox")) {
					driver = new EventFiringWebDriver(new FirefoxDriver()).register(new LogEventListener());
					ThreadDriver.set(driver);
				}
				//you can add chrome driver
				else if (browserType.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
					driver = new EventFiringWebDriver(new ChromeDriver()).register(new LogEventListener());
					ThreadDriver.set(driver);
				} else if (browserType.equals("internetexplorer")) {
					System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
					driver = new EventFiringWebDriver(new InternetExplorerDriver()).register(new LogEventListener());
					ThreadDriver.set(driver);
				}
			/*maximize the browser*/
			DriverManager.getDriver().manage().window().maximize();

			//wait 3 second for every find by
			DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			}
			return driver;
		}

		/**
		 * save the browser type but not create it
		 * @param browser
		 */
		public static void setupDriver(String browser){
			browserType=browser;

		}		
	
		/**
		 * quit the driver
		 */
		public static void quitDriver(){
			getDriver().quit();
			DriverManager.ThreadDriver.set(null);
		}
	}
}