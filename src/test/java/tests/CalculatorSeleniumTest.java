package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.security.PublicKey;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CalculatorSeleniumTest {
//    public CalculatorSeleniumTest() {
//        super();
//    }
     private WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
         System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Softwares\\chromedriver-win64\\chromedriver.exe");
         WebDriver driver = new ChromeDriver();

         driver.get("http://localhost:3000/");

    }

    @Test
    public void RegisterTest() {
    //    System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Softwares\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/signup");
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);

        WebElement emailInput = driver.findElement(By.name("email"));

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement selectRole = driver.findElement(By.id("2"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        emailInput.sendKeys("nehabawiskar2308@gmail.com");
        usernameInput.sendKeys("nkb");
        selectRole.click();
        passwordInput.sendKeys("nkb");
        WebElement submitButton = driver.findElement(By.className("registerbtn"));
        submitButton.click();

        driver.quit();
    }

    @Test
    public void LoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        usernameInput.sendKeys("nkb");
        passwordInput.sendKeys("nkb");

        WebElement submitButton = driver.findElement(By.id("loginBtn"));
        assertEquals("nkb", usernameInput.getAttribute("value"));
        assertEquals("nkb", passwordInput.getAttribute("value"));
        submitButton.click();
        WebElement calculatorLink = driver.findElement(By.linkText("Calculator"));
        calculatorLink.click();

        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.SECONDS);
        driver.get("http://localhost:3000/calc");
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.SECONDS);
        driver.quit();
    }


    @Test
    public void RoleUserCalculatorTest() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        usernameInput.sendKeys("nkb");
        passwordInput.sendKeys("nkb");

        assertEquals("nkb", usernameInput.getAttribute("value"));
        assertEquals("nkb", passwordInput.getAttribute("value"));
        WebElement submitButton = driver.findElement(By.id("loginBtn"));
        assertEquals("nkb", usernameInput.getAttribute("value"));
        assertEquals("nkb", passwordInput.getAttribute("value"));
        submitButton.click();

        // Click on the "Calculator" link in the navbar
        WebElement calculatorLink = driver.findElement(By.linkText("Calculator"));
        calculatorLink.click();

        driver.manage().timeouts().implicitlyWait(90000, TimeUnit.SECONDS);

        // Wait for the calculator page to load (assuming there is an element unique to the calculator page)
//        WebElement calculatorTitle = driver.findElement(By.id("mycalculator")); // Replace with actual element locator


    // Assert that the URL matches the expected calculator page URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/calc", currentUrl);

    }

        @Test
    public void UnauthenticatedUserTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/calc");

//        WebElement calculator = driver.findElement(By.className("Unauthorized"));
////        assertEquals(calculator.getText(),"You do not have permission to access this page");
//         assertEquals(calculator.getText(),"Unauthorized Access");
         driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        driver.quit();
    }


    @Test
    public void testAdminCalculatorPageAccess() {
        WebDriver driver = new ChromeDriver() ;

        // Given hardcoded JWT token for authentication
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJua2IiLCJpYXQiOjE2OTg4MzQ2MzgsImV4cCI6MTY5ODkyMTAzOH0.8T-Bbss-aTTPayhl0Sr2T1Z5RxMGG8Wu7C3It_a44MI"; // Replace this with the actual JWT token

        // Navigate to the login page
        driver.get("http://localhost:3000/login");

        // Fill in the login form with hardcoded username, password, and JWT token
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement jwtTokenInput = driver.findElement(By.name("jwtToken"));
        WebElement loginButton = driver.findElement(By.id("loginBtn"));

        usernameInput.sendKeys("nkb");
        passwordInput.sendKeys("nkb");
        jwtTokenInput.sendKeys(jwtToken);
        loginButton.click();

        // Wait for the page to load (you may need explicit waits here for specific elements)

        // Click on the Calculator link
        WebElement calculatorLink = driver.findElement(By.linkText("Calculator"));
        calculatorLink.click();

        // Wait for the calculator page to load
        // Perform any necessary assertions on the calculator page

        // Assert that the URL contains the expected calculator URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/calc", currentUrl);
    }

}

