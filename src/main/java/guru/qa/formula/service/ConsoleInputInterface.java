package guru.qa.formula.service;

import guru.qa.formula.model.Car;
import guru.qa.formula.model.Track;
import guru.qa.formula.repo.CarStorage;
import guru.qa.formula.repo.TrackStorage;

import java.util.Scanner;

public class ConsoleInputInterface extends InputInterface {
    public final static String CONSOLE = "Console";
    private final CarStorage carStorage;
    private final TrackStorage trackStorage;

    public ConsoleInputInterface(CarStorage carStorage, TrackStorage trackStorage) {
        this.carStorage = carStorage;
        this.trackStorage = trackStorage;
    }

    @Override
    public Car chooseCarInGui() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the car");
        String desiredCar = scanner.next();
        return carStorage.lookUp(desiredCar);
    }

    @Override
    public Track chooseTrackInGui() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the track");
        String desiredTrack = scanner.next();
        return trackStorage.lookUp(desiredTrack);
    }


}
