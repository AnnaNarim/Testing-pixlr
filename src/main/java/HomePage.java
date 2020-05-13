import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private By loginButton = By.id("login");

    public HomePage(WebDriver driver) { this.driver = driver; }

    public LoginPage clickLoginBtn() {
        WebElement waitForButton = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }
}



