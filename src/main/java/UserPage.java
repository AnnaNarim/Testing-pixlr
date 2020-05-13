import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {
    private WebDriver driver;
    private By loggedInState = By.className("sub-menu");
    private By menu = By.id("user-menu");
    private By menuOption = By.xpath("//*[@id=\"user-menu\"]/li[1]/a");

    public UserPage(WebDriver driver) { this.driver = driver; }

    public String loggedIn() {
        WebElement wait = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(loggedInState));
        return driver.findElement(loggedInState).getText();
    }

    public ProfilePage goToProfile() {
        WebElement wait = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.presenceOfElementLocated(loggedInState));

        Actions actions = new Actions(driver);
        WebElement visibleMenu = driver.findElement(loggedInState);
        actions.moveToElement(visibleMenu).perform();

        WebElement selectMenuOption = driver.findElement(menuOption);
        WebElement waitForOption = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(menu));
        selectMenuOption.click();
        return new ProfilePage(driver);
    }

}
