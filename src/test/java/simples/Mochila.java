package simples;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Mochila {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("name", "Nosso Teste");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "9.0");
        desiredCapabilities.setCapability("appium:deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
        desiredCapabilities.setCapability("appium:deviceOrientation", "portrait");
        desiredCapabilities.setCapability("appium:app", "storage:filename=mda-2.2.0-25.apk");
        desiredCapabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
        desiredCapabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("sauce:options", sauceOptions);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("https://oauth-fernandasaucedemo-749ac:cdd73555-5859-425a-8deb-85124018e37b@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void selecionarMochila() {
        //Tela principal
        WebElement imgProduto = (WebElement) driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Product Image\"])[1]"));
        assertEquals(imgProduto.getText(), "Sauce Labs Backpack");


        //Tela do produto
        WebElement lblNomeProduto = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals(lblNomeProduto.getText(), "$ 29.99");
        WebElement lblPrecoProduto = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        lblPrecoProduto.click();

        //Arrasta para cima
        (new TouchAction(driver))
                .press(PointOption.point(551, 1337))
                .moveTo(PointOption.point(579, 639))
                .release()
                .perform();

       WebElement btnAdicionarNoCarrinho = (WebElement) driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Tap to add product to cart\"]"));
       btnAdicionarNoCarrinho.click();
       WebElement icoCarrinho = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/cartIV"));
       icoCarrinho.click();
}

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

//android.widget.ImageView[@content-desc="Sauce Labs Backpack"]

//android.widget.FrameLayout[@content-desc="Container for fragments"]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView

//android.widget.FrameLayout[@content-desc="Container for fragments"]/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView

//android.widget.Button[@content-desc="Tap to add product to cart"]

//android.widget.ImageView[@content-desc="Displays number of items in your cart"]