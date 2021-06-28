package IMDb.steps.hooks;

import IMDb.Values;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.*;

import static IMDb.Values.URL;

public class Hooks {

    @Before (order = 10)
    public void prepareMaps() {
        Values.populateMap();
    }

    @Before (order = 20)
    public void get(){
        open(URL);
    }

    @After
    public void quit() {
        closeWebDriver();
    }
}
