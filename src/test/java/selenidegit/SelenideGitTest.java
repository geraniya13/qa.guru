package selenidegit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideGitTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void navigateProject() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box li.wiki-more-pages-link button").click();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("body").shouldHave(text("Using JUnit5"));
    }
}
