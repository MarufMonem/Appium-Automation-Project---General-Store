import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class eCommerece_Test_Case_2 extends baseTest {

    @Test
    public void fillForm() throws InterruptedException {
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        scrollToItem("Bangladesh");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Bangladesh\"]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Maruf");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    }

    @Test
    public void selectItem() throws InterruptedException {
        scrollToItem("Jordan 6 Rings");
        int onScreenItemCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        //Find the element and click on Add to Cart
        for (int i =0; i< onScreenItemCount; i++){
            String displayedItemName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            System.out.println(displayedItemName);
            if (displayedItemName.equalsIgnoreCase( "Jordan 6 Rings")){
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

//        Verify Cart Item
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart" ));
        String shoeName = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(shoeName, "Jordan 6 Rings");


    }
}
