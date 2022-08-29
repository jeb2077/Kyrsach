package sample.controllers;

import java.sql.Date;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Label;

public class Controller implements Initializable {
    public static int role;
    @FXML
    ObservableList<Passenger> tableData = FXCollections.observableArrayList();
    @FXML
    TableView<Passenger> table = new TableView<>(tableData);

    @FXML
    private TableColumn<Passenger, Integer> passengernumberColumn;
    @FXML
    private TableColumn<Passenger, String> firstnameColumn;
    @FXML
    private TableColumn<Passenger, String> lastnameColumn;
    @FXML
    private TableColumn<Passenger, Integer> passportnumberColumn;
    @FXML
    private TableColumn<Passenger, String> flightnumberColumn;
    @FXML
    private TableColumn<Passenger, Date> departuredateColumn;
    @FXML
    private TableColumn<Passenger, Time> departuretimeColumn;
    @FXML
    private TableColumn<Passenger, String> seatColumn;
    @FXML
    private Button addPassenger = new Button();
    @FXML
    private Button addClient = new Button();
    @FXML
    private Button addSale = new Button();
    @FXML
    private Button order = new Button();
    @FXML
    private Button delete = new Button();
    @FXML
    private Button edit = new Button();
    @FXML
    private Label noteLabel = new Label();


    @FXML
    private void addProductClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/addBook.fxml"));

            Stage addStage = new Stage();
            addStage.setTitle("Добавление книги");
            addStage.setScene(new Scene(root));
            addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    ReloadProductsTable();
                }
            });
            addStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteClick(ActionEvent e){
        if (table.getSelectionModel().getSelectedIndex() == -1){
            noteLabel.setText("Выберите книгу в таблице");
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bookstore", "root", "1234");
                Statement stmt = con.createStatement();
                String sql = "DELETE FROM books WHERE numberInventor=" + tableData.get(table.getSelectionModel().getSelectedIndex()).getPassengerNumber();
                stmt.executeUpdate(sql);
                con.close();
                noteLabel.setText("Книга удалена");
                ReloadProductsTable();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    @FXML
    private void addClientClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addClient.fxml"));

            Stage addStage = new Stage();
            addStage.setTitle("Добавление клиента");
            addStage.setScene(new Scene(root));
            addStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addSaleClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addReceipt.fxml"));

            Stage addStage = new Stage();
            addStage.setTitle("Добавление продажи");
            addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    ReloadProductsTable();
                }
            });
            addStage.setScene(new Scene(root));
            addStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void viewSalesClick(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));

            Stage addStage = new Stage();
            addStage.setTitle("Список продаж");
            addStage.setScene(new Scene(root));
            addStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReloadProductsTable(){
        tableData.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cashier", "root", "1234");
            Statement stmt = con.createStatement();

            String SQL = "SELECT * FROM passenger";
            ResultSet resultSet = stmt.executeQuery(SQL);

            while (resultSet.next()) {
                int passengerNumber = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int passportNumber = resultSet.getInt(4);
                String flightNumber = resultSet.getString(5);
                Date departureDate = resultSet.getDate(6);
                Time departureTime = resultSet.getTime(7);
                String Seat = resultSet.getString(8);
                Passenger addPassenger = new Passenger(passengerNumber, firstName, lastName, passportNumber,flightNumber, departureDate, departureTime, Seat);
                tableData.add(addPassenger);
            }


            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void IsAuth(){ //если окно авторизации закрыто с помощью крестика в верхней панели приложения
        if (role == 0){
            addPassenger.setDisable(true);
            addClient.setDisable(true);
            addSale.setDisable(true);
            order.setDisable(true);
            delete.setDisable(true);
            edit.setDisable(true);
            return;
        }
        if (role == 1){ //если админ
            addPassenger.setDisable(false);
            addClient.setDisable(true);
            addSale.setDisable(true);
            order.setDisable(false);
            delete.setDisable(false);
            edit.setDisable(false);
        }
        if (role == 2){ //если сотрудник
            addPassenger.setDisable(true);
            addClient.setDisable(false);
            addSale.setDisable(false);
            order.setDisable(true);
            delete.setDisable(true);
            edit.setDisable(true);

        }
        passengernumberColumn.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("passengerNumber"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("lastName"));
        passportnumberColumn.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("passportNumber"));
        flightnumberColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("flightNumber"));
        departuredateColumn.setCellValueFactory(new PropertyValueFactory<Passenger, Date>("departureDate"));
        departuretimeColumn.setCellValueFactory(new PropertyValueFactory<Passenger, Time>("departureTime"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("Seat"));
        table.setItems(tableData);
        ReloadProductsTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/Auth.fxml"));

            Stage addStage = new Stage();
            addStage.setTitle("Авторизация");
            addStage.setScene(new Scene(root));
            addStage.initModality(Modality.WINDOW_MODAL);
            addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    IsAuth();
                }
            });
            addStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}