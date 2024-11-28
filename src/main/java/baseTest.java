import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Map;

public class baseTest {

    public AndroidDriver driver; // AndroidDriver instance to interact with the Android device/emulator
    public AppiumDriverLocalService service; // Appium service instance to manage the Appium server

    @BeforeClass
    public void configureAppium() throws URISyntaxException, MalformedURLException {
        // Define the path to the Appium executable
        File appiumJS = new File("C:\\Users\\Anik\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

        // Build and start the Appium server with specified configurations
        service = AppiumDriverLocalService.buildService(
                new io.appium.java_client.service.local.AppiumServiceBuilder()
                        .withAppiumJS(appiumJS) // Path to the Appium main.js file
                        .withIPAddress("127.0.0.1") // IP address for the Appium server
                        .usingPort(4723) // Port number for the Appium server
        );
        service.start(); // Start the Appium server

        // Configure the UiAutomator2 options for the Android device/emulator
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone API 35"); // Name of the emulator/device
        options.setApp("C:\\Users\\Anik\\IdeaProjects\\General-Store-Appium-Project\\src\\main\\resources\\General-Store.apk");
        // Path to the app's APK file


        // Initialize the AndroidDriver to establish a connection to the Appium server
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // global wait for each element
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver session to clean up resources
        if (driver != null) {
            driver.quit();
        }

        // Stop the Appium service
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    public void longPressAction(WebElement element, int durationInSeconds){
        WebElement longPressElement = element;
        Actions actions = new Actions(driver);
        actions.clickAndHold(longPressElement).pause(Duration.ofSeconds(durationInSeconds)).release().perform();

    }

    public void scrollToItem (String itemTextValue){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollTextIntoView(\"" + itemTextValue +"\")"
        ));
    }

    public void scrollToEnd (){
        boolean canScrollMore = true;
        while (canScrollMore) {
//            System.out.println(canScrollMore);
            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", Map.of(
                    "left", 100, "top", 100,
                    "width", 500, "height", 800,
                    "direction", "down",
                    "percent", 1.0
            ));

        }

    }

    public void swipe(WebElement element, String direction,  double percentage){
        driver.executeScript("mobile: swipeGesture", Map.of(
                "elementId", ((RemoteWebElement)element).getId(), // Get the element ID of the first image
                "direction", direction, // Specify the direction of the swipe (left)
                "percent", percentage // Define the length of the swipe (swiping 25% of the screen width)
        ));
    }

    public void dragAndDrop (WebElement sourceElement, WebElement destinationElement){
        Actions actions = new Actions(driver);
        actions.clickAndHold(sourceElement).moveToElement(destinationElement).release().perform();

    }

    public void dragAndDropUsingCoordinates (WebElement sourceElement, int targetX, int targetY){
        driver.executeScript("mobile: dragGesture", Map.of(
                "elementId", ((RemoteWebElement)sourceElement).getId(),
                "endX", targetX,
                "endY", targetY
        ));
    }
}
