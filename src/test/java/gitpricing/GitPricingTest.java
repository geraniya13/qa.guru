package gitpricing;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitPricingTest {
    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void comparePlans(){
        open("https://github.com");
        $("div nav").$(byText("Pricing")).hover();
        $("div nav").$(byText("Compare plans")).click();
        $("div h1.h1").shouldHave(text("Compare features"));
    }
}
