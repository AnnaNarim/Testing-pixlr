import org.junit.jupiter.api.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.google.common.io.Files;

import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
//    public static  RemoteWebDriver driver;
    public static WebDriver driver;
    private  static EventFiringWebDriver eventHandler;
    public static String baseUrl ="https://pixlr.com/";
    public static String loginUrl ="https://accounts.inmagine.com/login?response_type=code&client_id=5q8r8hkkgm1pk636vb5cig95rv&redirect_uri=https://pixlr.com/authorize/";
    private String email = "begemotik.test@gmail.com";
    private String correctPassword = "asdasdasd";
    private String wrongPassword = "qweqweqwe";

    @BeforeAll
    public static void initWebDriver() throws MalformedURLException {
//        DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
//        chromeCapabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());

//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
        System.setProperty("webdriver.chrome.driver", "./resources/Driver/chromedriver");
        driver = new ChromeDriver();
        EventReporter eReporter = new EventReporter();
        eventHandler = new EventFiringWebDriver(driver);
        eventHandler.register(eReporter);
        eventHandler.get(baseUrl);
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot)eventHandler;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("./resources/screenshots/" + result.getName() + ".png"));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @DisplayName("Successful Login starting from loginURL from HW3 and HW4")
    public void testSuccessfulLogin() {
        eventHandler.get(loginUrl);
        LoginPage loginPageTest = new LoginPage(eventHandler);
        UserPage userPageTest = loginPageTest.successfulLogin(email, correctPassword);
        assertTrue(userPageTest.loggedIn().contains("HI, B"), "Successful login that returns UserPage instance");
    }

    @Test
    @DisplayName("Successful Login from Home Page")
    public void testSuccessfulLoginFromHome() {
        HomePage homePage = new HomePage(eventHandler);
        LoginPage loginPageTest = homePage.clickLoginBtn();
        UserPage userPageTest = loginPageTest.successfulLogin(email, correctPassword);
        assertTrue(userPageTest.loggedIn().contains("HI, B"), "Assertion for successful login");
    }

    @Test
    @DisplayName("Successful Login and then go to user profile and see if you are in my subscription page")
    public void testSuccessfulProfilePage() {
        HomePage homePage = new HomePage(eventHandler);
        LoginPage loginPageTest = homePage.clickLoginBtn();
        UserPage userPageTest = loginPageTest.successfulLogin(email, correctPassword);
        ProfilePage profile = userPageTest.goToProfile();
        String title = profile.getTitle();
        String plan = profile.getPlan();
        String planName = profile.getPlanName();
        String subscribeButton = profile.getSubscribeButton();

        assertTrue(title.contains("MY SUBSCRIPTION"), "Assertion for Title");
        assertTrue(plan.contains("PLAN"), "Assertion for Plan");
        assertTrue(planName.contains("Current plan"), "Assertion for Plan name");
        assertTrue(subscribeButton.contains("SUBSCRIBE"), "Assertion for button name");
    }

    @Test
    @DisplayName("Successful Login and then go to user profile then to plans page to see all the plans with titles and prices")
    public void testSuccessfulPlans() {
        HomePage homePage = new HomePage(eventHandler);
        LoginPage loginPageTest = homePage.clickLoginBtn();
        UserPage userPageTest = loginPageTest.successfulLogin(email, correctPassword);
        ProfilePage profile = userPageTest.goToProfile();
        PlansPage planspage = profile.clickSubscribe();
        planspage.findPricesTitlesNames();
        List<String> prices = planspage.getPrices();
        List<String> titles = planspage.getTitles();

        assertTrue(prices.get(0).contains("FREE"), "Assertion for price of first item");
        assertTrue(prices.get(1).contains("$3.99"), "Assertion for price of second item");
        assertTrue(prices.get(2).contains("$14.99"), "Assertion for price of third item");

        assertTrue(titles.get(0).contains("ALWAYS"), "Assertion for title of first item");
        assertTrue(titles.get(1).contains("ADVANCED"), "Assertion for title of second item");
        assertTrue(titles.get(2).contains("PROFESSIONAL"), "Assertion for title of third item");
    }

    @Test
    @DisplayName("Failed Login")
    public void testFailedLogin() {
        eventHandler.get(loginUrl);
        LoginPage loginPageTest = new LoginPage(eventHandler);
        String userFailed = loginPageTest.failedLogin(email, wrongPassword);

        assertTrue(userFailed.contains("Invalid"), "Invalid login with wrong credentials that returns Error message");
    }

    @AfterAll
    public static void tearDown() {
        eventHandler.quit();
    }
}
