package guru.qa.formula.model;

public abstract class Car {
    private static final int FUEL_CAPACITY = 100;

    private int maxLapsForTrack;

    public abstract double getFuelEconomy();

    public int fuelCapacity() {
        return FUEL_CAPACITY;
    }

    private void lapsForTrack(Track track) {
        int distance = track.distance();
        double fuelEconomy = getFuelEconomy();
        int fuelCapacity = fuelCapacity();
        double maxDistance = fuelCapacity * 100 / fuelEconomy;
        this.maxLapsForTrack = (int) (maxDistance * 1000) / distance;
    }

    public boolean isPitStopNeeded(Track track) {
        lapsForTrack(track);
        return maxLapsForTrack < track.laps();
    }

    public int maxLapsForTrack() {
        return this.maxLapsForTrack;
    }
}
