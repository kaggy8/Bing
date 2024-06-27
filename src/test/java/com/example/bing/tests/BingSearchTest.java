package com.example.bing.tests;

import com.example.bing.pages.MainPage;
import com.example.bing.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Search in Bing")
    public void searchFieldTest() {
        String input = "Selenium";
        MainPage mainPage = new MainPage(driver);
        mainPage.sendText(input);

        ResultsPage resultsPage = new ResultsPage(driver);

        assertEquals(input, resultsPage.getTextFromSearchField(), "Текст не совпал");
    }

    @Test
    @DisplayName("Click on Selenium")
    public void searchResultTest() {
        String input = "Selenium";
        String currentLink = "https://www.selenium.dev/";
        int indexPage = 0;

        MainPage mainPage = new MainPage(driver);
        mainPage.sendText(input);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.waitForLink();
        resultsPage.clickOnIndex(indexPage);
        resultsPage.switchWindow();

        String seleniumMainPage = driver.getCurrentUrl();
        assertEquals(currentLink, seleniumMainPage, "Открылась не верная ссылка");
    }
}