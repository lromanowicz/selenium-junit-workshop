package tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) {
            System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver");
        }
        driver = new ChromeDriver();
    }

    @Test
    public void whenLoggedInWithCorrectCredentials_thenSuccessVisible() {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button")).click();

        WebElement message = driver.findElement(By.cssSelector(".flash.success"));
        assertTrue( message.isDisplayed() );
        assertTrue( message.getText().contains("You logged into a secure area!") );
    }

    /**
     * Fill the content of the test below
     */
    @Test
    public void whenLoggedInWithIncorrectCredentials_thenInvalidPasswordVisible() {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!!");
        driver.findElement(By.cssSelector("button")).click();

        WebElement message = driver.findElement(By.cssSelector(".flash.error"));
        assertTrue( message.isDisplayed() );
        assertTrue( message.getText().contains("Your password is invalid!") );

    }

    /**
     * Additional tasks and questions
     *  - How this code could be optimized
     *  - Closing driver after each test
     */

    @Test
    public void shouldHaveCorrectBannerHeader(){
        driver.get("http://jacekokrojek.github.io/jak-to-zrobic-w-js/index.html");
        WebElement bannerHeader = driver.findElement(By.cssSelector("div.active.item h1"));
        assertTrue( bannerHeader.getText().contains("Example Headline 1") );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}