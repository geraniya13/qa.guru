package demoqa;

import demoqa.page.components.Countries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AutoPracticeTest extends TestBase {

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


    @Test
    void fillFormWithPageObjects() {
        registrationFormPage.openPage()
                .setFirstName(testData.name)
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

        registrationFormPage.submitButtonClick();

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
    }
}
