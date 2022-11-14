package vet;

import java.util.ArrayList;

public class Patients {
    static ArrayList<String> pets = new ArrayList<>();

    public static ArrayList<String> getPets() {
        return pets;
    }

    public static void setPets(ArrayList<String> pets) {
        Patients.pets = pets;
    }

    public static void strikeOutPatient(String petName) {
        for (int i = 0; i < Patients.pets.size(); i++) {
            if (pets.get(i).equals(petName)) {
                pets.remove(i);
                break;
            }
        }
    }
}
