package guru.qa.formula.service;

import guru.qa.formula.model.Car;
import guru.qa.formula.model.Track;
import guru.qa.formula.repo.CarStorage;
import guru.qa.formula.repo.TrackStorage;

import javax.swing.*;

public class GuiInputInterface extends InputInterface {
    public final static String GUI = "Gui";
    private final CarStorage carStorage;
    private final TrackStorage trackStorage;

    public GuiInputInterface(CarStorage carStorage, TrackStorage trackStorage) {
        this.carStorage = carStorage;
        this.trackStorage = trackStorage;
    }

    @Override
    public Car chooseCarInGui() {
        String[] carList = carStorage.getArray();
        String desiredCar = carList[JOptionPane.showOptionDialog(null, "Choose the car", "Option dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, carList, carList[0])];
        return carStorage.lookUp(desiredCar);
    }

    @Override
    public Track chooseTrackInGui() {
        String[] trackList = trackStorage.getArray();
        String desiredTrack = trackList[JOptionPane.showOptionDialog(null, "Choose the track", "Option dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, trackList, trackList[0])];
        return trackStorage.lookUp(desiredTrack);
    }
}
