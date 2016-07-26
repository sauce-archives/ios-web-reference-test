import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by umutuzgur on 04/07/16.
 */
public class ReferenceIosWebTest {

	private AppiumDriver driver;
	private long startTime;

	/* This is the setup that will be run before the test. */
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobject_app_id", "1");
		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY")); // API key through env variable
		capabilities.setCapability("testobject_device", System.getenv("TESTOBJECT_DEVICE_ID")); // device id through env variable
		capabilities.setCapability("testobject_appium_version", "1.5.2");
		capabilities.setCapability("testobject_cache_device", System.getenv("TESTOBJECT_CACHE_DEVICE"));

		long allocationTime = startTime = System.currentTimeMillis();
		driver = new IOSDriver(new URL("https://app.testobject.com:443/api/appium/wd/hub"), capabilities);
		System.out.println("Device allocation took: " + (System.currentTimeMillis() - allocationTime));
	}

	@After
	public void tearDown() {
		long tearDown = System.currentTimeMillis();
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Driver quit took: " + (System.currentTimeMillis() - tearDown));
		System.out.println("The whole test took: " + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void openWebpageAndTakeScreenshotFirst() {
		String url = "https://www.google.com";

		driver.get(url);
		takeScreenshot();
	}

	@Test
	public void openWebpageAndTakeScreenshotSecond() {
		String url = "https://www.google.com";

		driver.get(url);
		takeScreenshot();
	}

	private void takeScreenshot() {
		try {
			driver.getScreenshotAs(OutputType.FILE).delete();
		} catch (Exception e) {
			System.out.println("Exception while saving the file " + e);
		}
	}
}
