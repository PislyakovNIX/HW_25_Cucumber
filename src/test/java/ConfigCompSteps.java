import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConfigCompSteps {

    WebDriver driver = BaseTest.getDriver();

    @Given("I am start on product page")
    public void iAmStartOnProductPage() {
        driver.get("https://demo.nopcommerce.com/build-your-own-computer");
    }

    @And("I configure new comp with parameters {string} and {string} and {string} and {string}")
    public void iConfigureNewCompWithParametersProcessorAndRamAndOperationSystemAndHdd(String processor, String ram, String operationSystem, String hdd) {
        Select selectProcessorDropdown = new Select(driver.findElement(By.id("product_attribute_1")));
        Select selectRamDropdown = new Select(driver.findElement(By.id("product_attribute_2")));

        selectProcessorDropdown.selectByVisibleText(processor);
        selectRamDropdown.selectByVisibleText(ram);
        driver.findElement(By.xpath("//label[contains(text(),'" + operationSystem + "')]/preceding-sibling::input")).click();
        driver.findElement(By.xpath("//label[contains(text(),'" + hdd + "')]/preceding-sibling::input")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        BaseTest.wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='bar-notification']"))));
    }

    @When("I open shopping cart")
    public void iOpenShoppingCart() {
        driver.findElement(By.xpath("//span[@class='cart-label']")).click();
    }

    @Then("I see comp with parameters {string} and {string} and {string} and {string}")
    public void iSeeCompWithParametersProcessorAndRamAndOperationSystemAndHdd(String processor, String ram, String operationSystem, String hdd) {
        WebElement configuration = driver.findElement(By.xpath("//td[@class='product']/div[@class='attributes']"));
        assertThat(configuration.getText().contains(processor));
        assertThat(configuration.getText().contains(ram));
        assertThat(configuration.getText().contains(operationSystem));
        assertThat(configuration.getText().contains(hdd));
    }
}
