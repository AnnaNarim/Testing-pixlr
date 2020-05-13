import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PlansPage {
    private WebDriver driver;
    private By item = By.className("pricing-item");
    private By price = By.className("pricing-price");
    private By title = By.className("pricing-title");
    private List<String> plansPrices;
    private List<String> plansTitles;

    public PlansPage(WebDriver driver) { this.driver = driver; }

    public void setTitles(List<String> t) { this.plansTitles = t; }
    public void setPrices(List<String> p) { this.plansPrices = p; }

    public List<String> getTitles() { return plansTitles; }
    public List<String> getPrices() { return plansPrices; }

    public void findPricesTitlesNames() {
        List<WebElement> plans = driver.findElements(item);
        plansPrices = new ArrayList<String>();
        plansTitles = new ArrayList<String>();

        for(int i = 0; i < plans.size(); i++) {
            plansPrices.add(plans.get(i).findElement(price).getText());
            plansTitles.add(plans.get(i).findElement(title).getText());
        }

        setPrices(plansPrices);
        setTitles(plansTitles);
    }

}
