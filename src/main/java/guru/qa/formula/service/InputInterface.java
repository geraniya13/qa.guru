package guru.qa.formula.service;

import guru.qa.formula.model.Car;
import guru.qa.formula.model.Track;

import javax.swing.*;

public abstract class InputInterface {
    public abstract Car chooseCarInGui();

    public abstract Track chooseTrackInGui();

    public void showResult(Car car, Track track) {
        if (car.isPitStopNeeded(track)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Pit-stop needed in " + car.maxLapsForTrack() + " laps",
                    "Result:",
                    1
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Pit-stop not needed in",
                    "Result:",
                    1
            );
        }
    }
}
