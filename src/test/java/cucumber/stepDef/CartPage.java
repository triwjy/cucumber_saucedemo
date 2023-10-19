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

public class CartPage {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User is on products page as standard_user")
    public void UserIsOnProductsPageAsStandardUser() {
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

    @When("User add Sauce Labs Bolt T-Shirt and Sauce Labs Backpack to cart")
    public void userAddSauceLabsBoltTShirtAndSauceLabsBackpackToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("User go to cart page")
    public void userGoToCartPage() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @Then("Cart page should show the added Sauce Labs Bolt T-Shirt and Sauce Labs Backpack")
    public void cartPageShouldShowTheAddedSauceLabsBoltTShirtAndSauceLabsBackpack() {
        String backpackItem = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        String tshirtItem = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText();
        String itemCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();

        Assert.assertEquals(backpackItem, "Sauce Labs Backpack");
        Assert.assertEquals(tshirtItem, "Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(itemCount, "2");

        driver.close();
    }

    @And("Remove Sauce Labs Bolt T-Shirt from cart in cart page")
    public void removeSauceLabsBoltTShirtFromCartInCartPage() {
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
    }
    @Then("Cart page should only show the Sauce Labs Backpack")
    public void cartPageShouldOnlyShowTheSauceLabsBackpack() {
        String backpackItem = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        String itemCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();

        Assert.assertEquals(backpackItem, "Sauce Labs Backpack");
        Assert.assertEquals(itemCount, "1");

        driver.close();
    }
}
