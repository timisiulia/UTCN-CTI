package presentationLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class LoginController {
    protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private PasswordClient pass;

    public LoginController(PasswordClient pass) {
        this.pass = pass;

        pass.userButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(new JFrame(), " ");

            }
        });
        pass.passButtonListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(pass.getPassword());
                JOptionPane.showMessageDialog(new JFrame(),
                        "Password is " + password);

            }
        });

    }

    private PasswordAdmin pass1;

    public LoginController(PasswordAdmin pass1) {
        this.pass1 = pass1;

        pass1.userButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        pass1.passButtonListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(pass1.getPassword());
                JOptionPane.showMessageDialog(new JFrame(),
                        "Password is " + password);

            }
        });

    }

    private PasswordEmployee pass2;

    public LoginController(PasswordEmployee pass2) {
        this.pass2 = pass2;

        pass2.userButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JFrame(), " ");

            }
        });
        pass2.passButtonListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(pass2.getPassword());
                JOptionPane.showMessageDialog(new JFrame(),
                        "Password is " + password);

            }
        });

    }

}
