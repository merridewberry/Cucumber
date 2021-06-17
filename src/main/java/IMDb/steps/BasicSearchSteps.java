package IMDb.steps;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IMDb.Values;
import IMDb.steps.hooks.Hooks;
import io.cucumber.java.en.*;

import static IMDb.Values.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BasicSearchSteps {
    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(xpath = ".//div[@class='ipc-error-message ipc-error-message--on-baseAlt']/" +
            "div[contains(., 'No results found.')]")
    WebElement noResultsAlert;

    @FindBy(partialLinkText = "See all results for ")
    WebElement allResults;

    @FindBy(id = "suggestion-search-button")
    WebElement searchButton;

    @FindBy(xpath = ".//p[text()='Search IMDb by typing a word or phrase in the search box at the top of this page.']")
    WebElement emptySearchResult;

    @FindBy(xpath = ".//h1[text()='No results found for ']")
    WebElement noResultPageMessage;

    String tail;
    WebDriver driver = Hooks.driver;

    public BasicSearchSteps() {PageFactory.initElements(driver, this);}

    @Given("^I enter title (.+) into the search field$")
    public void inputTitle(String query) {
        searchField.sendKeys(query);

    }

    @Given("^I click on the searched movie link with URL containing (.+) in the dropdown results$")
    public void clickSearchedMovieFromTable(String url) {
        tail = url;
        getSearchedMovie(tail).click();
    }

    @Given("^I click on the searched movie link with URL containing (.+) in the results$")
    public void clickSearchedMovieFromTableII(String url) {
        tail = url;
        getSearchedMovieII(tail).click();
    }

    @Given("I enter movie title {string} into the search field")
    public void saveVariableAndInput(String query) {
        tail = Values.MOVIE.get(query);
        searchField.sendKeys(query);
    }

    @Given("I click on the searched movie link in the dropdown results")
    public void clickSearchedMovie() {
        getSearchedMovie(tail).click();
    }

    private WebElement getSearchedMovie(String url) {
        List<WebElement> searchedMovieList = driver.findElements(By.xpath(".//a[@href='" + url + MOVIE_URL_TAIL + "']"));
        Assumptions.assumeTrue(searchedMovieList.size() > 0);
        return searchedMovieList.get(0);
    }

    @Given("I click on the searched movie link")
    public void clickSearchedMovieII() {
        getSearchedMovieII(tail).click();
    }

    private WebElement getSearchedMovieII(String url) {
        List<WebElement> searchedMovieList = driver.findElements(By.xpath(".//a[@href='" + url + MOVIE_URL_TAIL_II + "']"));
        Assumptions.assumeTrue(searchedMovieList.size() > 0);
        return searchedMovieList.get(0);
    }

    @Then("movie page URL is correct")
    public void checkUrl() {
        Assertions.assertEquals(driver.getCurrentUrl(),URL + tail + "/" + MOVIE_URL_TAIL);
    }

    @Then("movie URL is correct")
    public void checkUrlII() {
        Assertions.assertEquals(driver.getCurrentUrl(),URL + tail + MOVIE_URL_TAIL_II);
    }

    @Then("\"No results found\" alert is displayed")
    public void checkNoResults() {
        Assertions.assertTrue(noResultsAlert.isDisplayed());
    }

    @Given("I press the search button")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Then("page with no search results is displayed")
    public void checkNoResultPage() {
        Assertions.assertTrue(noResultPageMessage.isDisplayed());
    }

    @Given("I click \"All results\" link")
    public void clickAllResults() {
        allResults.click();
    }

    @Then("the corresponding message is displayed")
    public void checkEmptySearch() {
        Assertions.assertTrue(emptySearchResult.isDisplayed());
    }
}