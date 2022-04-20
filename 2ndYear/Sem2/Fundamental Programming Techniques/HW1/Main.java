import controller.Controller;
import model.Polynomial;
import view.View;

public class Main {
    public static void main(String[] args) {
        Polynomial Model = new Polynomial();
        View view = new View(Model);
        Controller controller = new Controller(Model, view);
        view.setVisible(true);
    }
    //3x^4+1x^3+2x^1+5x^0
    //7x^4+2x^3+3x^0

}