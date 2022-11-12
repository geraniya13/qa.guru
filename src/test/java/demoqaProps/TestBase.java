package demoqaProps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import demoqaProps.helpers.Attach;
import demoqaProps.page.RegistrationFormPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase {
//    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();
    TestData testData = new TestData();
    static String remoteUrl = System.getProperty("remote_url");

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser_name", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "100.0");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        if (remoteUrl != null) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("enableVNC", true);
            dc.setCapability("enableVideo", true);
            Configuration.browserCapabilities = dc;
            Configuration.remote = remoteUrl;
        }
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
        if (remoteUrl != null) {
            Attach.saveVideo();
        }
    }
}
