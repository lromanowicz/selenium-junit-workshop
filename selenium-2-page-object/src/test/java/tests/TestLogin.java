package tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.omg.PortableInterceptor.ServerRequestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageobjects.Login;

public class TestLogin {

    private WebDriver driver;
    private Login login;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "etc/chromedriver.exe");
        driver = new ChromeDriver();
        login = new Login(driver);
    }

    @Test
    public void whenLoggedInWithCorrectCredentials_thenSuccessVisible(){
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue( login.successMessagePresent());
    }

    /**
     * Fill the content of the test below
     */
    @Test
    public void whenLoggedInWithIncorrectCredentials_thenInvalidPasswordVisible() {
        login.with("tomsmith", "SuperSecretPasswod!");

        for (char ch: login.getErrorMessage().toCharArray()) {
            System.out.print((int)ch + ",");
        }
        String expected = "Your password is invalid!\n" + (char)215;
        System.out.println("");
        for (char ch: expected.toCharArray()) {
            System.out.print((int)ch + ",");
        }
        assertEquals(expected, login.getErrorMessage());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
