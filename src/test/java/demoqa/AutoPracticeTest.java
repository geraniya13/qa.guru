package demoqa;

import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.page.components.Countries;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


public class AutoPracticeTest extends TestBase {
    @Test
    void fillFormWithPageObjects() {
        step("Opening main page", () -> {
            registrationFormPage.openPage();
        });
        step("Filling in form with random data", () -> {
            registrationFormPage.setFirstName(testData.name)
                    .setLastName(testData.surname)
                    .setEmail(testData.email)
                    .setGender(testData.gender)
                    .setNumber(testData.phoneNumber)
                    .setBirthDate(testData.day, testData.month, testData.year)
                    .setSubjects(testData.subjects)
                    .setHobbies(testData.hobbies)
                    .setPicture(testData.path, testData.imgName)
                    .setAddress(testData.address)
                    .setCountry(Countries.HARYANA)
                    .setCity(testData.city);
        });
        step("Submitting form", () -> {
            registrationFormPage.submitButtonClick();
        });
        step("Checking data compliance", () -> {
            registrationFormPage.checkResultsTableVisible()
                    .checkResult("Student Name", testData.name + " " + testData.surname)
                    .checkResult("Student Email", testData.email)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.phoneNumber)
                    .checkResult("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                    .checkResult("Subjects", registrationFormPage.getArrayAsString(testData.subjects))
                    .checkResult("Hobbies", registrationFormPage.getArrayAsString(testData.hobbies))
                    .checkResult("Picture", testData.imgName)
                    .checkResult("Address", testData.address)
                    .checkResult("State and City", Countries.HARYANA + " " + testData.city);
        });
    }
}
