package com.example.projkecik.Controllers;
import com.example.projkecik.D.ReservationD;
import com.example.projkecik.Application;
import com.example.projkecik.Constructor.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;

/**

 The ActualReservationController class is responsible for handling the logic and events related to the actual reservations table view.

 It contains the methods and variables necessary to display and update the table of actual reservations.
 */
public class ActualReservationController {

        @FXML
        private Button DodanieApart;

        @FXML
        private Button DodanieGosc;

        @FXML
        private Button DodanieRezer;

        @FXML
        private Button StronaGlowna;

        @FXML
        private Button Wyjscie;
        @FXML
        private TableColumn<Reservation, LocalDate> DateO;

        @FXML
        private TableColumn<Reservation, LocalDate> DateZ;

        @FXML
        private TableColumn<Reservation, String> guestId;

        @FXML
        private TableView<Reservation> resTable;

        @FXML
        private TableColumn<Reservation, String> roomId;

        @FXML
        private TableColumn<Reservation, Long> guestNumber;

        ReservationD reservationD = new ReservationD();
    /**
     * Initializes the actual reservations table view by setting the cell value factories for each column and setting the items in the table to the list of actual reservations.
     */
        @FXML
        private void initialize() {
            DateZ.setCellValueFactory(new PropertyValueFactory<>("rezstart"));
            DateO.setCellValueFactory(new PropertyValueFactory<>("rezstop"));
            roomId.setCellValueFactory(new PropertyValueFactory<>("room"));
            guestId.setCellValueFactory(new PropertyValueFactory<>("guest"));
            guestNumber.setCellValueFactory(new PropertyValueFactory<>("iloscgosci"));
            resTable.setItems(getActualResList());
        }
    /**
     * Returns an observable list of actual reservations to be displayed in the table view.
     * @return an ObservableList of actual reservations
     */
        private ObservableList<Reservation> getActualResList() {
            ObservableList<Reservation> reservations = FXCollections.observableArrayList();
            reservations.addAll(reservationD.getActualReservation());
            return reservations;
        }
    /**

     The ZmianaScenyDodanieApart,ZmianaScenyDodanieGosc, ZmianaScenyRezerwacja and ZmianaScenyWyjscie methods are used to change the scene in the application to the corresponding FXML file.
     These methods are triggered when the corresponding button is pressed.

     */

        @FXML
        void ZmianaScenyDodanieApart(ActionEvent event) throws IOException {
            Application m = new Application();
            m.changeScene("dodawanieapartament.fxml");
        }

        @FXML
        void ZmianaScenyDodanieGosc(ActionEvent event) throws IOException {
            Application m = new Application();
            m.changeScene("dodawaniegoscia.fxml");
        }

        @FXML
        void ZmianaScenyRezerwacja(ActionEvent event) throws IOException {
            Application m = new Application();
            m.changeScene("dodawanierezerwacji.fxml");
        }

        @FXML
        void ZmianaScenyWyjscie(ActionEvent event) throws IOException {


            Application m = new Application();
            m.changeScene("hello-view.fxml");

        }

    }


