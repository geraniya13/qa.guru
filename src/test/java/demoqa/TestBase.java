package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import demoqa.helpers.Attach;
import demoqa.page.RegistrationFormPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();
    TestData testData = new TestData();

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());

        DesiredCapabilities dc = new DesiredCapabilities();
//        dc.setCapability("browserName", "chrome");
//        dc.setCapability("browserVersion", "100.0");
        dc.setCapability("enableVNC", true);
        dc.setCapability("enableVideo", true);
//        dc.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        RemoteWebDriver driver = new RemoteWebDriver(
//                URI.create("http://selenoid:4444/wd/hub").toURL(),
//                dc
//        );
        Configuration.browserCapabilities = dc;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @BeforeEach
    void prepareData() {
        testData.name = faker.name().firstName();
        testData.surname = faker.name().lastName();
        testData.email = faker.internet().emailAddress();
        testData.gender = testData.getGender(faker.number().numberBetween(0, 2));
        testData.phoneNumber = faker.phoneNumber().subscriberNumber(10);
        testData.day = faker.number().numberBetween(1, 29) + "";
        testData.month = testData.getMonth(faker.number().numberBetween(0, 11));
        testData.year = faker.number().numberBetween(2000, 2010) + "";
        testData.address = faker.address().streetAddress();
    }

    @AfterEach
    void addAttachments() {
        Attach.saveScreenshot("Result image");
        Attach.savePageSource();
        Attach.saveBrowserConsoleLogs();
        Attach.saveVideo();
    }
}
