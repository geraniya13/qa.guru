package guru.qa;

import guru.qa.formula.App;
import guru.qa.formula.repo.CarStorage;
import guru.qa.formula.repo.TrackStorage;
import guru.qa.formula.service.ConsoleInputInterface;
import guru.qa.formula.service.GuiInputInterface;

import javax.swing.*;

import static guru.qa.formula.App.MODES;

public class Main {
    public static void main(String[] args) {
//        Administrator administrator = new Administrator();
//        administrator.work();

        /* ----------------------------------------------------------------------------------------------------------------------------------------------------------------- */

        String mode = MODES[JOptionPane.showOptionDialog(null, "Choose mode", "Option dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, MODES, MODES[0])];
        if (mode.equals("Gui")) {
            new App(new GuiInputInterface(new CarStorage(), new TrackStorage())).run();
        }
        else {
            new App(new ConsoleInputInterface(new CarStorage(), new TrackStorage())).run();
        }
    }
}
