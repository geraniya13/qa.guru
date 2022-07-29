import java.io.IOException;
import java.util.ArrayList;

public class First {

    public static void main(String[] args) throws IOException {
        Cat strayCat = new Cat("Red", true);
        Cat othersCat = new Cat("White", "Dick", true);
        Cat myCat = new Cat(5, "July", false, "Black");
        ArrayList<Cat> catsList = new ArrayList<>();
        catsList.add(strayCat);
        catsList.add(othersCat);
        catsList.add(myCat);
        for (int i = 0; i < catsList.size(); i++) {
            catsList.get(i).whatsTheCat();
            catsList.get(i).HasHome();
            catsList.get(i).whatsBreed();
            catsList.get(i).getSex();
            catsList.get(i).adoption();
        }
    }
}
