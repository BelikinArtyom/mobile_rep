package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserStackDriver implements WebDriverProvider {

        @NonNull
        @Override
        public WebDriver createWebDriver(@NonNull Capabilities capabilities) {
            MutableCapabilities caps = new MutableCapabilities();

            // W3C standard capabilities (must be at root level)
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:platformVersion", "12.0");
            caps.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:app", "bs://sample.app");

            // Set BrowserStack specific capabilities using bstack:options
            Map<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("userName", "bsuser_V1r0pT");
            bstackOptions.put("accessKey", "Hq1XmAwuvpK7JeCJQxs4");
            bstackOptions.put("projectName", "First Java Project");
            bstackOptions.put("buildName", "browserstack-build-1");
            bstackOptions.put("sessionName", "first_test");

            caps.setCapability("bstack:options", bstackOptions);

            // Initialise the remote Webdriver using BrowserStack remote URL
            // and desired capabilities defined above
            RemoteWebDriver driver = new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
            return driver;
        }
}