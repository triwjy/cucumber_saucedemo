package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ProductDetail {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User is on Products page with standard_user as user")
    public void UserIsOnProductsPageWithStandardUserAsUser() {
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

    @When("User click Sauce Labs Bike Light")
    public void userClickSauceLabsBikeLight() {
        driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).click();
    }

    @Then("It will show the Sauce Labs Bike Light product detail")
    public void itWillShowTheSauceLabsBikeLightProductDetail() {
        String productTitle = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        Assert.assertEquals(productTitle, "Sauce Labs Bike Light");
        driver.close();
    }

    @When("User add Sauce Labs Bike Light from product page")
    public void userAddSauceLabsBikeLightFromProductPage() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @Then("It will show the Sauce Labs Bike Light has been added to cart in product detail")
    public void itWillShowTheSauceLabsBikeLightHasBeenAddedToCartInProductDetail() {
        driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"));

        Boolean removeButtonExist = driver.findElements(By.id("remove-sauce-labs-bike-light")).size() == 1;
        Assert.assertEquals(removeButtonExist, true);

        String cartItemCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(cartItemCount, "1");

        driver.close();
    }
}
