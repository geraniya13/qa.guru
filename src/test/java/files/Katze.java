package files;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;

public class Katze {
    private String name;
    private int age;
    private String color;
    private String breed;
    private boolean male;
    private Measures measures;
    private String[] children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[] children) {
        this.children = children;
    }

    public static Katze getKatzeFromJson(ObjectMapper objectMapper, ClassLoader classLoader, String path) {
        Katze katze = new Katze();
        try (InputStreamReader isr = new InputStreamReader(classLoader.getResourceAsStream(path))) {
            katze = objectMapper.readValue(isr, Katze.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return katze;
    }
}
