package guis;

import game.Game;
import game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Bejelentkező ablak
 */
public class LoginFrame extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton registerButton,loginButton;
    private final Game game;

    /**
     * Konstruktor
     * @param game játék
     * @throws HeadlessException
     */
    public LoginFrame(Game game) throws HeadlessException {
        this.game = game;
        init();
    }

    /**
     * Kirajzolja a grafikát
     */
    private void init(){
        this.setTitle("Grepo - Bejelentkezés");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(70,0,70,0));
        GridLayout gridLayout = new GridLayout(3,1);
        mainPanel.setLayout(gridLayout);

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Név:"));
        nameField = new JTextField(20);
        namePanel.add(nameField);
        mainPanel.add(namePanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Jelszó:"));
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        passwordPanel.add(passwordField);
        mainPanel.add(passwordPanel);

        JPanel buttons = new JPanel();
        registerButton = new JButton("Regisztráció");
        registerButton.addActionListener(new RegisterButtonListener());
        buttons.add(registerButton);
        loginButton = new JButton("Belépés");
        loginButton.addActionListener(new LoginButtonListener());
        buttons.add(loginButton);
        mainPanel.add(buttons);

        this.add(mainPanel,BorderLayout.CENTER);
    }

    /**
     * Kezeli a regisztráló gombot
     */
    private class RegisterButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            if (name == null || name.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null,"Hibás név vagy jelszó!","Hiba",JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player player = game.getPlayer(name);
            if(player != null){
                JOptionPane.showMessageDialog(null,"Van már ilyen nevű játékos!","Hiba",JOptionPane.ERROR_MESSAGE);
                return;
            }

            game.registerPlayer(name,password);
            JOptionPane.showMessageDialog(null,"Sikeres regisztráció, menj a belépés gombra!","Információ",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     * Kezeli a bejelentkezés gombot, ha sikeres betölti a játékhoz tartozó ablakokat
     */
    private class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            if (name == null || name.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null,"Hiányzó adat","Hiba",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(game.authenticate(game.getPlayer(name),password)){
                dispose();

                if(game.getPlayerCities(game.getAuthenticatedPlayer()).size() == 0){
                    ChooseIslandFrame frame = new ChooseIslandFrame(game);
                    dispose();
                    frame.setVisible(true);
                }
                else{
                    CityFrame frame= new CityFrame(game);
                    frame.setVisible(true);
                }

            }
            else{
                JOptionPane.showMessageDialog(null,"Hibás név vagy jelszó!","Hiba",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
