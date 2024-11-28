import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerece_Test_Case_1 extends baseTest {

    @Test
    public void fillForm() throws InterruptedException {
        error_FillForm();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        scrollToItem("Bangladesh");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Bangladesh\"]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Maruf");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);

    }
@Test
    public void error_FillForm() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String errorMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(errorMessage, "Please enter your name");
        Thread.sleep(2000);
    }
}
