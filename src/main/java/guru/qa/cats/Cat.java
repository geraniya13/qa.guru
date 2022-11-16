package guru.qa.cats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cat {
    int age = -1;
    String name;
    String color;
    String breed;
    boolean sex;
    boolean hasHome = true;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void getSex() {
        if (this.sex) {
            System.out.println("Male");
        } else {
            System.out.println("Female");
        }
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void HasHome() {
        if (this.hasHome) {
            System.out.println("Has home");
        } else {
            System.out.println("No home");
        }
    }

    public void setHasHome(boolean hasHome) {
        this.hasHome = hasHome;
    }

    public Cat(int age, String name, boolean sex, String color) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.color = color;
    }

    public Cat(String color, String name, boolean sex) {
        this.color = color;
        this.name = name;
        this.sex = sex;
    }

    public Cat(String color, boolean sex) {
        this.sex = sex;
        this.color = color;
        this.hasHome = false;

    }

    void whatsTheCat() {
        if (this.name == null) {
            System.out.println("Stray cat");
        } else if (this.name != null && this.age == -1) {
            System.out.println("Neighbour's cat");
        } else {
            System.out.println("My cat");
        }
    }

    void adoption() throws IOException {
        if (this.name == null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Do you want to adopt this cat?");
            String agreement = reader.readLine();
            if (agreement.equalsIgnoreCase("yes")) {
                System.out.println("Give it a name");
                setName(reader.readLine());
                System.out.println("Define age");
                setAge(Integer.parseInt(reader.readLine()));
                setHasHome(true);
                System.out.println("Now it's yours");
                reader.close();
            } else if (agreement.equalsIgnoreCase("no")) {
                System.out.println("Just leave it");
                reader.close();
            } else {
                System.out.println("Just pass by");
                reader.close();
            }


        } else {
            System.out.println("This cat has owner");
        }
    }

    public void whatsBreed() {
        switch (this.color) {
            case "Red":
                System.out.println("Persian");
                setBreed("Persian");
                break;
            case "Grey":
                System.out.println("Siberian");
                setBreed("Siberian");
                break;
            case "White":
                System.out.println("British");
                setBreed("British");
                break;
            default:
                System.out.println("Exotic");
                setBreed("Exotic");
        }

    }
}
