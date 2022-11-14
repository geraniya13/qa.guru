package vet;

public class Doctors {
    static String[] actualDoctors = {"Black", "White", "Brown"};

    public static String[] getActualDoctors() {
        return actualDoctors;
    }

    public static void setActualDoctors(String[] actualDoctors) {
        Doctors.actualDoctors = actualDoctors;
    }

    public static void dismissDoctor(String name) {
        int i = 0;
        while(true) {
            if (actualDoctors[i].equals(name)) {
                actualDoctors[i] = null;
                break;
            }
            i++;
        }
    }

}
