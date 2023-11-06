package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CalculatorSeleniumTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Softwares\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:3000/");

    }

    @Test
    public void RegisterTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/signup");
        driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);

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

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        usernameInput.sendKeys("nkb");
        passwordInput.sendKeys("nkb");

        WebElement submitButton = driver.findElement(By.id("loginBtn"));
        assertEquals("nkb", usernameInput.getAttribute("value"));
        assertEquals("nkb", passwordInput.getAttribute("value"));
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);

        String currentUrl = driver.getCurrentUrl();
       driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
        assertEquals("http://localhost:3000/login", currentUrl);

    }


    @Test
    public void UnauthenticatedUserTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/calc");
       driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        WebElement unauthorizedHeader = driver.findElement(By.tagName("h1"));
        WebElement unauthorizedMessage = driver.findElement(By.tagName("p"));
        WebElement loginLink = driver.findElement(By.linkText("Login here"));

        assertEquals("Unauthorized Access", unauthorizedHeader.getText());
        assertEquals("You do not have permission to access this page.", unauthorizedMessage.getText());

        assertEquals(true, loginLink.isDisplayed());

       driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
        driver.quit();
    }


    @Test
    public void testAdminCalculatorPageAccess() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        WebElement loginButton = driver.findElement(By.id("loginBtn"));

        usernameInput.sendKeys("nkb");
        passwordInput.sendKeys("nkb");

        loginButton.click();
       driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);

        String currentUrl = driver.getCurrentUrl();
       driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        assertEquals("http://localhost:3000/login", currentUrl);

        WebElement btn4 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[4]"));
        btn4.click();
        WebElement btn2 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[2]"));
        btn2.click();
        WebElement btnPlus = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[2]/button[1]"));
        btnPlus.click();
        WebElement btn1  = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[1]"));
        btn1.click();
        WebElement btn0 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[10]"));
        btn0.click();

        WebElement displayInput = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[1]/input"));
//   //     assertEquals("42+10", displayInput.getAttribute("value"));

        WebElement btnEqual = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[2]/button[5]"));
        btnEqual.click();

        WebElement resultDisplay = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[1]/input"));
        assertEquals("52",resultDisplay.getAttribute("value"));
       driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
        driver.quit();
    }
    @Test
    public void testUserCalculatorPageAccess() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        WebElement loginButton = driver.findElement(By.id("loginBtn"));

        usernameInput.sendKeys("jay");
        passwordInput.sendKeys("jay");

        loginButton.click();

       // driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);

        WebElement btn4 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[4]"));
        btn4.click();
        WebElement btn2 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[2]"));
        btn2.click();
        WebElement btnPlus = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[2]/button[1]"));
        btnPlus.click();
        WebElement btn1  = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[1]"));
        btn1.click();
        WebElement btn0 = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[3]/button[10]"));
        btn0.click();

        WebElement btnEqual = driver.findElement(By.xpath("//*[@id=\"mycalculator\"]/div[2]/div[2]/button[5]"));
        btnEqual.click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        assertEquals("Login as an admin to view the result.", alertText);
       driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
        alert.accept();

        driver.quit();
    }
}
