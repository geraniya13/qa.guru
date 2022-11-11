package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueSearchTests extends TestBase {

    @BeforeEach
    void setListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void selenideSearchTest() {
        open("/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $("#issue_81_link").shouldHave(text("issue_to_test_allure_report"));
    }

    @Test
    void lambdaStepSearchTestWithAttachment() {
        step("Opening main page", () -> {
            open("/");
        });
        step("Searching repository " + repo, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repo);
            $(".header-search-input").submit();
        });
        step("Choosing link of repository " + repo, () -> {
            $(linkText(repo)).click();
        });
        step("Opening issue tab", () -> {
            $("#issues-tab").click();
        });
        step("Checking issue " + issue + " name", () -> {
            $("#issue_81_link").shouldHave(text(issue));
            attachment("Result", webdriver().driver().source());
        });
    }

    @Test
    void annotatedSearchTestWithAttachment() {
        webSteps.openMainPage();
        webSteps.searchRepository(repo);
        webSteps.chooseRepository(repo);
        webSteps.openIssueTab();
        webSteps.checkIssueName(issue);
        webSteps.takeResultScreenshot();
    }
}
