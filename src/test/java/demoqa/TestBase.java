package demoqa;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import demoqa.page.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();
    TestData testData = new TestData();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
}
