import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventReporter implements WebDriverEventListener {
    @Override
    public void beforeClickOn(WebElement webElement, WebDriver arg1) {
        System.out.println("----> beforClickOn "+ webElement.getText());
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("----> beforeFindBy " + by); // easier way to show the type and the value
    }

    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }
    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }
    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }
    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void afterNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterNavigateRefresh(WebDriver arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }
    @Override
    public void beforeNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
        System.out.println("Before Navigating Back "+arg0.getCurrentUrl());
    }
    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void beforeNavigateRefresh(WebDriver arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }
    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }
    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }
    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }
    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }
    @Override
    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onException(Throwable arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }
}