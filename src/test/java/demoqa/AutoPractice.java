package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.selector.ByText;
import demoqa.page.RegistrationFormPage;
import demoqa.page.components.Countries;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;


public class AutoPractice extends TestBase {
    String name,
            surname,
            email,
            gender,
            phoneNumber,
            day,
            month,
            year,
            address,
            path = "src/test/resources/",
            imgName = "picture.jpg",
            city = "Karnal";

    String[] subjects = {"Maths", "Computer science", "History"},
            hobbies = {"Sports", "Music"};


    @BeforeEach
    void prepareData() {
        name = faker.name().firstName();
        surname = faker.name().lastName();
        email = faker.internet().emailAddress();
        gender = testData.getGender(faker.number().numberBetween(0, 2));
        phoneNumber = faker.phoneNumber().subscriberNumber(10);
        day = faker.number().numberBetween(1, 29) + "";
        month = testData.getMonth(faker.number().numberBetween(0, 11));
        year = faker.number().numberBetween(2000, 2010) + "";
        address = faker.address().streetAddress();
    }


    @Test
    void fillFormWithPageObjects() {
        registrationFormPage.openPage()
                .setFirstName(name)
                .setLastName(surname)
                .setEmail(email)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(path, imgName)
                .setAddress(address)
                .setCountry(Countries.COUNTRY1)
                .setCity(city);

        registrationFormPage.submitButtonClick();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", name + " " + surname)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", registrationFormPage.getArrayAsString(subjects))
                .checkResult("Hobbies", registrationFormPage.getArrayAsString(hobbies))
                .checkResult("Picture", imgName)
                .checkResult("Address", address)
                .checkResult("State and City", Countries.COUNTRY1 + " " + city);
    }
}
