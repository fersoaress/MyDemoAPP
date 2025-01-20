package simples;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.net.MalformedURLException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MochilaNovo {

    private AndroidDriver driver;

    @BeforeMethod
    public URL setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:platformVersion", "9.0")
                .amend("appium:deviceName", "Samsung Galaxy S9 HD GoogleAPI Emulator")
                .amend("appium:deviceOrientation", "portrait")
                .amend("appium:app", "storage:filename=mda-2.2.0-25.apk")
                .amend("appium:appPackage", "com.saucelabs.mydemoapp.android")
                .amend("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("sauce:options", Map.ofEntries(Map.entry("name", "Appium Desktop Session -- Jan 9, 2025 4:35 PM")))
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("https://oauth-fernandasaucedemo-749ac:cdd73555-5859-425a-8deb-85124018e37b@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, options);
        return remoteUrl;
    }


    @Test
    public void selecionarMochila() {
        var imgProduto = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]"));
        imgProduto.click();
        var lblNomeProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals(lblNomeProduto.getText(), "Sauce Labs Backpack");
        var lblPrecoProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        assertEquals(lblPrecoProduto.getText(), "$ 29.99");
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(348, 891);
        var end = new Point (346, 162);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        var btnAdicionaNoCarrinho = driver.findElement(AppiumBy.accessibilityId("Tap to add product to cart"));
        btnAdicionaNoCarrinho.click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
