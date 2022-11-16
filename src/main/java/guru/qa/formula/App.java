package guru.qa.formula;

import guru.qa.formula.model.Car;
import guru.qa.formula.model.Track;
import guru.qa.formula.service.InputInterface;

import static guru.qa.formula.service.ConsoleInputInterface.CONSOLE;
import static guru.qa.formula.service.GuiInputInterface.GUI;

public class App {
    private final InputInterface userInterface;

    public static final String[] MODES = {CONSOLE, GUI};

    public App(InputInterface userInterface) {
        this.userInterface = userInterface;
    }


    public void run() {
            Car car = userInterface.chooseCarInGui();
            Track track = userInterface.chooseTrackInGui();
            userInterface.showResult(car, track);
    }
}
