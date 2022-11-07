package demoqa;

public class TestData {
    public String getMonth(int monthNumber) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[monthNumber];
    }

    public String getGender(int i) {
        String[] genders = {"Female", "Male", "Other"};
        return genders[i];
    }
}
