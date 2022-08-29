package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddPassenger implements Initializable{
    @FXML
    private TextField firstname = new TextField();
    @FXML
    private TextField lastname = new TextField();
    @FXML
    private Button addButton = new Button();
    @FXML
    private TextField passportnumber = new TextField();
    @FXML
    private TextField flightnumber = new TextField();
    @FXML
    private TextField departuredate = new TextField();
    @FXML
    private TextField departuretime = new TextField();

    @FXML
    Label errorLabel = new Label();

    private boolean ok1 = true;
    private boolean ok2 = true;
    private boolean ok3 = true;
    private boolean ok4 = true;
    private boolean ok5 = true;
    private boolean ok6 = true;


    @FXML
    public void ValidateFirstName(){
        if (firstname.getText().trim().equals("")){
            errorLabel.setText("Введите имя");
            ok1 = false;
        }
        else {
            errorLabel.setText("");
            ok1 = true;
        }
    }
    @FXML
    public void ValidateLastName(){
        if (lastname.getText().trim().equals("")){
            errorLabel.setText("Введите фамилию");
            ok2 = false;
        }
        else {
            errorLabel.setText("");
            ok2 = true;
        }
    }
    @FXML
    public void Validatepassportnumber(){
        if (passportnumber.getText().trim().equals("")){
            errorLabel.setText("Введите номер паспорта");
            ok3 = false;
        }
        else {
            errorLabel.setText("");
            ok3 = true;
        }
    }
    @FXML
    public void Validateflightnumber(){
        if (flightnumber.getText().trim().equals("")){
            errorLabel.setText("Введите номер билета");
            ok4 = false;
        }
        else {
            errorLabel.setText("");
            ok4 = true;
        }
    }

    @FXML
    public void Validatedeparturedate(){
        if (departuredate.getText().trim().equals("")){
            errorLabel.setText("Введите дату полёта");
            ok5 = false;
        }
        else {
            errorLabel.setText("");
            ok5 = true;
        }
    }

    @FXML
    public void Validatedeparturetime(){
        if (departuretime.getText().trim().equals("")){
            errorLabel.setText("Введите время вылета");
            ok6 = false;
        }
        else {
            errorLabel.setText("");
            ok6 = true;
        }
    }



    @FXML
    public void add(ActionEvent e){
        if (ok1 && ok2 && ok3 && ok4 && ok5 && ok6){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("1");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/cashier", "root", "1234");
                Statement stmt = con.createStatement();
                String sql = "INSERT INTO passenger (firstName, lastName, passportNumber, flightNumber, departureDate, departureTime) " +
                        "VALUES ('" + firstname.getText().trim() + "', '" +
                        lastname.getText().trim() + "', '" +
                        passportnumber.getText().trim() + "', '" +
                        flightnumber.getText().trim() + "', '" +
                        departuredate.getText().trim() + "', '" +
                        departuretime.getText().trim()  + "')";
                stmt.executeUpdate(sql);
                con.close();
                errorLabel.setText("Пассажир успешно добавлен");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            errorLabel.setText("Укажите все данные о пассажире");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cashier", "root", "1234");
            Statement stmt = con.createStatement();
            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}