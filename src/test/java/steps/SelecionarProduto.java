package steps;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SelecionarProduto {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
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
                .amend("sauce:options", Map.ofEntries(Map.entry("name", "Teste com cucumber")))
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("https://oauth-fernandasaucedemo-749ac:cdd73555-5859-425a-8deb-85124018e37b@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("que preciso comprar um presente")
    public void que_preciso_comprar_um_presente() {
        // O app da loja é aberto no BeforeClass

    }
    @When("clico no {string}")
    public void clico_no(String nomeProduto) {
    var imgProduto = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"" + nomeProduto + "\"])[1]"));
        imgProduto.click();


    }
    @Then("exibe a página com o nome do {string} e {string}")
    public void exibe_a_página_com_o_nome_do_e(String nomeProduto, String precoProduto) {
        var lblNomeProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals(lblNomeProduto.getText(), nomeProduto);

        var lblPrecoProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        assertEquals(lblPrecoProduto.getText(), precoProduto);

    }
}
