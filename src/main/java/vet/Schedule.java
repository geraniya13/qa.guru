package vet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Schedule {
    public Set<String> appointments = new HashSet<>();

    public Set<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<String> appointments) {
        this.appointments = appointments;
    }

    public void makeNewAppointment(Set<String> appointments, String appointment) {
        for (String s : appointments) {
            if (s.equals(appointment)) {
                System.out.println("Appointment exists");
            }
            else {
                appointments.add(appointment);
            }
        }
    }

    public void cancelAppointment(Set<String> appointments, String appointment) {
        for (String s : appointments) {
            if (s.equals(appointment)) {
                appointments.remove(appointment);
            }
            else {
                System.out.println("Appointment doesn't exist");
            }
        }
    }
}
