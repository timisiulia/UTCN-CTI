package View;

import Model.Utilizator;
import Presenter.PresenterUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUserLogin extends JFrame implements InterfataLogin {

    private PresenterUser presenterUser;
    private JPanel panel1;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JRadioButton administratorRadioButton;




    public ViewUserLogin(){

        this.setTitle("Autentificare");
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.setVisible(true);

        presenterUser = new PresenterUser(this);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String username;
            String password;
            String rol;


            username=getUsername();
            password=getPassword();
            rol=getRole();

            Utilizator u =new Utilizator(username,password,rol);
                System.out.println(u);
            presenterUser.goodUser(u);

            }
        });

    }

    @Override
    public String getUsername() {
        String username;
        username=textField1.getText();
        return username;
    }

    @Override
    public String getPassword() {
        String pass;
        pass=passwordField1.getText();
        return pass;


    }

    @Override
    public String getRole() {
       // String r1;
       // r1=administratorRadioButton.getText();

        String rol;
        if(administratorRadioButton.isSelected()){
            rol="Administrator";
        }
        else
        {
            rol="Angajat";}


          return rol;


    }
}