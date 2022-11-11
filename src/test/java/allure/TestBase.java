package allure;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static final String repo = "eroshenkoam/allure-example";
    static final String issue = "issue_to_test_allure_report";

    WebSteps webSteps = new WebSteps();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }
}
