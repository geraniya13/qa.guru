package guru.qa.formula.repo;

import guru.qa.formula.model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class TrackStorage extends Storage {
    Map<String, Track> storage = Map.of(
        "Spa", new Spa(),
        "Nurburgring", new Nurburgring()
    );

    public final String storageName = "Track";

    public Track lookUp(String trackName) {
        for (String key : storage.keySet()) {
            if (key.equalsIgnoreCase(trackName)) {
                return storage.get(key);
            }
        }
        throw new IllegalArgumentException("Track not found");
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
