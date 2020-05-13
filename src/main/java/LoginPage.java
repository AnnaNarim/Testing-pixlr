import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-btn");
    private By failedState = By.cssSelector("#__next > span > section > div > div > div.mx-auto.position-absolute > div");

    public LoginPage(WebDriver driver) { this.driver = driver; }
    private void setEmail(String email) { driver.findElement(emailField).sendKeys((email)); }
    private void setPassword(String password) { driver.findElement(passwordField).sendKeys((password)); }

    public void clickLoginBtn() {
        driver.findElement(loginButton).click();
    }

    public void setParams(String username, String password) {
        setEmail(username);
        setPassword(password);
    }

    public UserPage successfulLogin(String username, String password) {
        setParams(username, password);
        clickLoginBtn();
        return new UserPage(driver);
    }

    public String failedLogin(String username, String password) {
        setParams(username, password);
        clickLoginBtn();
        WebElement errorMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(failedState));
        return errorMessage.getText();
    }
}



