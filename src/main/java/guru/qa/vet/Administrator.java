package guru.qa.vet;

import java.util.HashMap;
import java.util.Map;

public class Administrator {
    public Map<String, Integer> report = new HashMap<>();

    Schedule schedule = new Schedule();

    public int workingHours = 0;

    public void dismissDoctor(String name) {
        String action = "Doctor's dismissed";
        Doctors.dismissDoctor(name);
        workingHours++;
        addActionToReport(report, action);
    }

    public void addPatient(String name) {
        String action = "New Patient";
        Patients.pets.add(name);
        workingHours++;
        addActionToReport(report, action);
    }

    public void strikeOutPatient(String name) {
        String action = "Patient is down";
        Patients.strikeOutPatient(name);
        workingHours++;
        addActionToReport(report, action);
    }

    public void makeNewAppointment(int i, int j, String time) {
        String action = "New Appointment was made";
        schedule.makeNewAppointment(schedule.appointments, getAppointmentName(Doctors.actualDoctors[i], Patients.pets.get(j), time));
        workingHours++;
        addActionToReport(report, action);
    }

    public void cancelAppointment(int i, int j, String time) {
        String action = "Appointment was cancelled";
        schedule.cancelAppointment(schedule.appointments, getAppointmentName(Doctors.actualDoctors[i], Patients.pets.get(j), time));
        workingHours++;
        addActionToReport(report, action);
    }


    public void addActionToReport(Map<String, Integer> report, String actionName) {
        if (!report.containsKey(actionName)) {
            report.put(actionName, 1);
        } else {
            for (Map.Entry<String, Integer> entry : report.entrySet()) {
                if (entry.getKey().equals(actionName)) {
                    entry.setValue(entry.getValue() + 1);
                }
            }
        }
    }

    public int getRandomDoctor() {
        return (int) (Math.random() * Doctors.actualDoctors.length);
    }

    public int getRandomPatient() {
        return (int) (Math.random() * Patients.pets.size());
    }

    public String getRandomTimeString() {
        return (int) (Math.random() * 60) + 1 + "." + (int) (Math.random() * 60) + 1;
    }

    public String getAppointmentName(String doctor, String patient, String time) {
        return doctor + ":" + patient + ":" + time;
    }

    public void doReport() {
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }


    public void work() {
        addPatient("Meow");
        addPatient("Kitty");
        addPatient("Tom");
        addPatient("Lacey");
        int doc = getRandomDoctor(),
                pet = getRandomPatient();
        String time = getRandomTimeString();
        makeNewAppointment(doc, pet, time);
        do {
            makeNewAppointment(getRandomDoctor(), getRandomPatient(), getRandomTimeString());
        } while (workingHours < 15);
        strikeOutPatient(Patients.pets.get(getRandomPatient()));
        cancelAppointment(doc,pet,time);
        dismissDoctor("Black");
        doReport();
    }
}
