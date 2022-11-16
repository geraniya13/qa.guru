package guru.qa.formula.repo;

import guru.qa.formula.model.Ferrari;
import guru.qa.formula.model.Mercedes;
import guru.qa.formula.model.Car;

import java.util.Map;

public class CarStorage extends Storage {
    Map<String, Car> storage = Map.of(
        "Ferrari", new Ferrari(),
        "Mercedes", new Mercedes()
    );

    public final String storageName = "Car";

    public Car lookUp(String carName) {
        for (String key : storage.keySet()) {
            if (key.equalsIgnoreCase(carName)) {
                return storage.get(key);
            }
        }
        throw new IllegalArgumentException("Car not found");
    }

    @Override
    public String[] getArray() {
        String[] listModel = new String[storage.size()];
        int i = 0;
        for (String key : storage.keySet()) {
            listModel[i] = key;
            i++;
        }
        return listModel;
    }



    @Override
    public String getStorageName() {
        return storageName;
    }
}
