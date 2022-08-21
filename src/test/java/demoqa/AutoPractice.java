package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.selector.ByText;
import demoqa.page.RegistrationFormPage;
import demoqa.page.components.Countries;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;


public class AutoPractice {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    String[] subjects = {"P.E.", "Maths", "History"};
    String path = "src/test/resources/";
    String dateOfBirth = "01.01.1991";
    String parsedDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy")).format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", new Locale("en")));


    @Disabled
    @Test
    void fillForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("NewName");
        $("#lastName").setValue("NewLastName");
        $("#userEmail").setValue("newmail@got.com");
        $("#genterWrapper").find(new ByText("Other")).click();
        $("#userNumber").setValue("999999999");
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
        $("#dateOfBirthInput").sendKeys(dateOfBirth + Keys.ENTER);
        for (int i = 0; i < subjects.length; i++) {
            $("#subjectsInput").setValue(subjects[i]).pressEnter();
        }
        $("#hobbiesWrapper").find(new ByText("Sports")).click();
        $("#uploadPicture").uploadFile(new File(path + "picture.jpg"));
        $("#currentAddress").setValue("New Address");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        $(".modal-body").find(new ByText("Student Name")).parent().lastChild().shouldHave(new Text("NewName NewLastName"));
        $(".modal-body").find(new ByText("Student Email")).parent().lastChild().shouldHave(new Text("newmail@got.com"));
        $(".modal-body").find(new ByText("Gender")).parent().lastChild().shouldHave(new Text("Other"));
        $(".modal-body").find(new ByText("Mobile")).parent().lastChild().shouldHave(new Text("999999999"));
        $(".modal-body").find(new ByText("Date of Birth")).parent().lastChild().shouldHave(new Text(parsedDate));
        $(".modal-body").find(new ByText("Subjects")).parent().lastChild().shouldHave(new Text(Arrays.toString(subjects).replace("[", "").replace("]", "")));
        $(".modal-body").find(new ByText("Hobbies")).parent().lastChild().shouldHave(new Text("Sports"));
        $(".modal-body").find(new ByText("Picture")).parent().lastChild().shouldHave(new Text("picture.jpg"));
        $(".modal-body").find(new ByText("Address")).parent().lastChild().shouldHave(new Text("New Address"));
        $(".modal-body").find(new ByText("State and City")).parent().lastChild().shouldHave(new Text("Haryana Karnal"));
    }

    @Test
    void fillFormWithPageObjects() {
        registrationFormPage.openPage()
                .setFirstName("NewName")
                .setLastName("NewLastName")
                .setEmail("newmail@got.com")
                .setGender("Other")
                .setNumber("9999999999")
                .setBirthDate("01", "January", "1991")
                .setSubjects(new String[]{"Maths", "Computer science", "History"})
                .setHobbies(new String[]{"Sports", "Music"})
                .setPicture("src/test/resources/", "picture.jpg")
                .setAddress("New Address")
                .setCountry(Countries.COUNTRY1)
                .setCity("Karnal");

        registrationFormPage.submitButtonClick();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "NewName NewLastName")
                .checkResult("Student Email", "newmail@got.com")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "9999999999")
                .checkResult("Date of Birth", "01 January,1991")
                .checkResult("Subjects", "Maths, Computer science, History")
                .checkResult("Hobbies", "Sports, Music")
                .checkResult("Picture", "picture.jpg")
                .checkResult("Address", "New Address")
                .checkResult("State and City", Countries.COUNTRY1 + " Karnal");
    }
}
