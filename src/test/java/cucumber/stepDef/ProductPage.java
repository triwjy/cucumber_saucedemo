package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("User is on products page with standard_user as user")
    public void userIsOnProductsPageWithStandard_userAsUser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

    }


    @When("User add Sauce Labs Bolt T-Shirt to cart")
    public void userAddSauceLabsBoltTShirtToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
    }

    @Then("Item in cart has the value of one")
    public void itemInCartHasTheValueOfOne() {
        String cart_value = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(cart_value, "1");

        driver.close();
    }

    @And("Remove Sauce Labs Bolt T-Shirt from cart in product page")
    public void removeSauceLabsBoltTShirtFromCartInProductPage() {
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
    }

    @Then("Item in cart has the value of zero")
    public void itemInCartHasTheValueOfZero() {
        Boolean cart_value = driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).size() == 0;
        Assert.assertEquals(cart_value, true);

        driver.close();
    }
}