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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User is on Swag Labs login page")
    public void user_is_on_swag_labs_login_page() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(baseUrl);

        String loginPageText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageText, "Swag Labs");
    }

    @When("User enter the standard_user username")
    public void user_enter_the_standard_user_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @When("User enter valid password")
    public void user_enter_valid_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("Click on Login")
    public void click_on_login() {

        driver.findElement(By.id("login-button")).click();
    }

    @Then("Products Page should be displayed")
    public void products_page_should_be_displayed() {
        String productsPageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(productsPageTitle, "Products");
        driver.close();
    }

    @And("User enter invalid password")
    public void UserEnterInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("wrong_password");
    }

    @Then("Error Message should be displayed")
    public void ErrorMessageShouldBeDisplayed() {
        String loginErrorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(loginErrorMessage, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }

    @When("User enter the (.*) as username$")
    public void user_enter_the_username_as_username(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("User enter (.*) as password$")
    public void userEnterPasswordAsPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("Verify login is (.*)$")
    public void verifyLoginIsStatus(String status) {
        if (status.equals("success")) {
            String productsPageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(productsPageTitle, "Products");
        } else if (status.equals("fail")) {
            String loginErrorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(loginErrorMessage, "Epic sadface: Username and password do not match any user in this service");
        }
        driver.close();
    }
}


