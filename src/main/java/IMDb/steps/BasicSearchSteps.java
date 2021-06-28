package IMDb.steps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

import io.cucumber.java.en.*;

import static IMDb.Values.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Condition.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BasicSearchSteps {

    String searchField_name = "q";
    String noResultsAlert_xpath = ".//div[@class='ipc-error-message ipc-error-message--on-baseAlt']/" +
            "div[contains(., 'No results found.')]";
    String allResults_plink = "See all results for ";
    String searchButton_id = "suggestion-search-button";
    String emptySearchResult_xpath = ".//p[text()='Search IMDb by typing a word or phrase in the search box " +
            "at the top of this page.']";
    String noResultPageMessage_xpath = ".//h1[text()='No results found for ']";
    String tail;


    public BasicSearchSteps() {}

    @Given("I enter movie title {string} into the search field")
    public void saveVariableAndInput(String query) {
        tail = MOVIE.get(query);
        $(By.name(searchField_name)).val(query);
    }

    @Given("^I enter title (.+) into the search field$")
    public void inputTitle(String query) {
        $(By.name(searchField_name)).val(query);
    }

    @Given("^I click on the searched movie link with URL containing (.+) in the dropdown results$")
    public void clickSearchedMovieFromTable_dropdown(String url) {
        tail = url;
        $(By.xpath(".//a[@href='" + tail + MOVIE_URL_TAIL_DROPDOWN + "']")).click();
    }

    @Given("I click on the searched movie link in the dropdown results")
    public void clickSearchedMovie_dropdown() {
        $(By.xpath(".//a[@href='" + tail + MOVIE_URL_TAIL_DROPDOWN + "']")).click();
    }

    @Given("I click on the searched movie link")
    public void clickSearchedMovie_page() {
        $(By.xpath(".//a[@href='" + tail + MOVIE_URL_TAIL_PAGE + "']")).click();
    }

    @Then("movie page URL is correct")
    public void checkUrl() {
        Assertions.assertEquals(url(),URL + tail + "/" + MOVIE_URL_TAIL_DROPDOWN);
    }

    @Then("movie URL is correct")
    public void checkUrlII() {
        Assertions.assertEquals(url(),URL + tail + MOVIE_URL_TAIL_PAGE);
    }

    @Then("\"No results found\" alert is displayed")
    public void checkNoResults() {
        $(By.xpath(noResultsAlert_xpath)).shouldBe(visible);
    }

    @Given("I press the search button")
    public void clickSearchButton() {
        $(By.id(searchButton_id)).click();
    }

    @Then("page with no search results is displayed")
    public void checkNoResultPage() {
        $(By.xpath(noResultPageMessage_xpath)).shouldBe(visible);
    }

    @Given("I click \"All results\" link")
    public void clickAllResults() {
        $(By.partialLinkText(allResults_plink)).click();
    }

    @Then("the corresponding message is displayed")
    public void checkEmptySearch() {
        $(By.xpath(emptySearchResult_xpath)).shouldBe(visible);
    }
}