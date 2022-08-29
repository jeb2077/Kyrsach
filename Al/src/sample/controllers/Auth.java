package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

import javafx.scene.control.PasswordField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
public class Auth implements Initializable{
    public boolean auth = false;

    @FXML
    TextField login = new TextField();
    @FXML
    PasswordField password = new PasswordField();

    @FXML
    Label errorLabel = new Label();

    @FXML
    Button cancelButton = new Button();

    @FXML
    private void loginClick(){
        boolean ok = false;
        int role = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cashier", "root", "1234");
            Statement stmt = con.createStatement();

            String SQL = "SELECT * FROM user";
            ResultSet resultSet = stmt.executeQuery(SQL);

            while (resultSet.next()) {
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                role = resultSet.getInt(1);
                if (userLogin.equals(login.getText().trim()) && userPassword.equals(password.getText().trim())){
                    ok = true;
                    break;
                }
            }
            con.close();

            if (ok){
                this.auth = true;
                errorLabel.setText("");
                Controller.role = role;
                Window w = cancelButton.getScene().getWindow();
                Stage stage = (Stage) w;
                w.fireEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();
            }
            else {
                this.auth = true;
                errorLabel.setText("Неверный логин или пароль");
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void cancelClick(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
