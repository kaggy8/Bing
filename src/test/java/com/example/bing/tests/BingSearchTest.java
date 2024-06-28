package com.example.bing.tests;

import com.example.bing.pages.MainPage;
import com.example.bing.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BingSearchTest {
    public static  final String INPUT = "Selenium";
    private WebDriver driver;
    private MainPage mainPage;
    private ResultsPage resultsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com");
        mainPage = new MainPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Search in Bing")
    public void searchFieldTest() {
        mainPage.sendText(INPUT);
        assertEquals(INPUT, resultsPage.getTextFromSearchField(), "Текст не совпал");
    }

    @Test
    @DisplayName("Click on Selenium")
    public void searchResultTest() {
        String currentLink = "https://www.selenium.dev/";
        int indexPage = 0;

        mainPage.sendText(INPUT);
        resultsPage.waitForLink();
        resultsPage.clickOnIndex(indexPage);
        resultsPage.switchWindow();

        String seleniumMainPage = driver.getCurrentUrl();
        assertEquals(currentLink, seleniumMainPage, "Открылась неверная ссылка");
    }
}