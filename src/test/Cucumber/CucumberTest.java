package Cucumber;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = {"IMDb.steps", "IMDb.steps.hooks"},
        tags = "@all",
        dryRun = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class CucumberTest {


}
