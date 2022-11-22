package api.demoWebShop;

import api.demoWebShop.config.DWSConfig;
import api.demoWebShop.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase {
    TestData testData = new TestData();
    Faker faker = new Faker();
    static DWSConfig config = ConfigFactory.create(DWSConfig.class, System.getProperties());

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();
        if (!config.remoteUrl().equals("")) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("enableVNC", true);
            dc.setCapability("enableVideo", true);
            Configuration.browserCapabilities = dc;
            Configuration.remote = config.remoteUrl();
        }
    }

    @BeforeEach
    void prepareData() {
        testData.setName(faker.name().firstName());
        testData.setLastName(faker.name().lastName());
        testData.setEmail(faker.internet().emailAddress());
        testData.setPassword(faker.number().digits(7));
    }

    @AfterEach
    void addAttachments() {
        Attach.saveScreenshot("Result image");
        Attach.savePageSource();
        Attach.saveBrowserConsoleLogs();
        if (!config.remoteUrl().equals("")) {
            Attach.saveVideo();
        }
    }
}
