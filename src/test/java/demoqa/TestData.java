package demoqa;

public class TestData {
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
    public String getMonth(int monthNumber) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[monthNumber];
    }

    public String getGender(int i) {
        String[] genders = {"Female", "Male", "Other"};
        return genders[i];
    }
}
