package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.PublicKey;

public class CalculatorSeleniumTest {
    public CalculatorSeleniumTest() {
        super();
    }

    @BeforeAll
    public static void setUp() {
         System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Softwares\\chromedriver_win32\\chromedriver.exe");
         WebDriver driver = new ChromeDriver();
         driver.get("http://http://localhost:3000/signup");
    }

    @Test
    public void testRegister() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://http://localhost:3000/signup");
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement selectRole = driver.findElement(By.id("2"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        emailInput.sendKeys("nehabawiskar2308@gmail.com");
        usernameInput.sendKeys("Neha Bawiskar");
        selectRole.click();
        passwordInput.sendKeys("neha44");
        WebElement submitButton = driver.findElement(By.className("registerbtn"));
        submitButton.click();

        driver.quit();


    }
//    @Test
//    public void testLogin() {
//        WebDriver driver = new ChromeDriver();
//        driver.quit();
//    }
//
//    @Test
//    public void testUnauthenticatedUser() {
//        WebDriver driver = new ChromeDriver();
//        driver.quit();
//    }
//
//    @Test
//    public void testRoleUserCalculator() {
//        WebDriver driver = new ChromeDriver();
//        driver.quit();
//    }
//
//    @Test
//    public void testRoleAdminCalculator() {
//        WebDriver driver = new ChromeDriver();
//        driver.quit();
//    }

//    private void login(String username ,String password) {
//
//    }
//

}
