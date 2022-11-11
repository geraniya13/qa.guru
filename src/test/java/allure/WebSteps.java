package allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Opening main page")
    public void openMainPage() {
        open("/");
    }

    @Step("Searching repository {repoName}")
    public void searchRepository(String repoName) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repoName);
        $(".header-search-input").submit();
    }

    @Step("Choosing link of repository {repoName}")
    public void chooseRepository(String repoName) {
        $(linkText(repoName)).click();
    }

    @Step("Opening issue tab")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Checking issue {issueName} name")
    public void checkIssueName(String issueName) {
        $(withText("#80")).should(exist);
    }

    @Attachment(value = "Result", type = "image/png", fileExtension = "png")
    public byte[] takeResultScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
