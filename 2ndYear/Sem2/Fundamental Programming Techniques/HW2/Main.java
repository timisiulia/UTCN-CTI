import controller.Controller;
import model.SimulationManager;
import view.View;

import java.io.File;

public class Main {
    public static void main(String args[]) {
        try {
            //SimulationManager gen = new SimulationManager(new File(args[0]), new File(args[1]));
            View v = new View();

            SimulationManager gen = new SimulationManager(new File("nimicIN.txt"), new File("NimicOUT.txt"), v);
            Controller c = new Controller(gen, v);
            v.setSize(1000,1000);
            v.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


