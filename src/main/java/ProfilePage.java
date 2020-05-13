import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;
    private By title = By.cssSelector("body > section > div > h3");
    private By plan = By.cssSelector("body > section > div > div.col.lines.plans > div > h4");
    private By planName = By.cssSelector("body > section > div > div.col.lines.plans > div > div > div > div > p");
    private By subscribeButton = By.id("subs-now");

    public ProfilePage(WebDriver driver) { this.driver = driver; }

    public String getTitle() {
        WebElement wait = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(title));
        return driver.findElement(title).getText();
    }

    public String getPlan() {
        WebElement waitForPlan = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(plan));
        return  driver.findElement(plan).getText();
    }

    public String getPlanName() {
        WebElement waitForPlanName = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(planName));
        return driver.findElement(planName).getText();
    }

    public String getSubscribeButton() {
        WebElement waitForButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(subscribeButton));
        return driver.findElement(subscribeButton).getText();
    }

    public PlansPage clickSubscribe() {
        WebElement waitForButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(subscribeButton));
        driver.findElement(subscribeButton).click();
        return new PlansPage(driver);
    }
}
