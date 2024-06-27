package com.example.bing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = "h2 > a[href]")
    private List <WebElement> results;

    public void clickOnIndex(int index) {
        results.get(index).click();
        System.out.println("Нажатие на результат под номером " + index);
    }

    public String getTextFromSearchField() {
        String result = searchField.getAttribute("value");
        System.out.println("В строке поиска текст: " + result);
        return result;
    }

    public void waitForLink() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.cssSelector("h2 > a[href]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2 > a[href]"))
        ));
    }

    public void switchWindow() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        PageFactory.initElements(driver, this);
    }
}