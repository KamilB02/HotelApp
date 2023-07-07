package com.example.projkecik.Controllers;
import com.example.projkecik.D.GuestD;
import com.example.projkecik.D.ReservationD;
import com.example.projkecik.Application;
import com.example.projkecik.Constructor.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.projkecik.Constructor.Room;
import com.example.projkecik.Constructor.Guest;
import com.example.projkecik.D.RoomD;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**

 The NewReservationController class is a controller class for handling the new reservation window.

 It handles the initialization of the window, input validation and creating a new reservation.

 */
public class NewReservationController {
    @FXML
    private Button Cancel;

    @FXML
    private ComboBox<Guest> ChoiceGuest;

    @FXML
    private ComboBox<Room> ChoiceRoom;

    @FXML
    private DatePicker DateEnd;

    @FXML
    private DatePicker DateStart;

    @FXML
    private Button Dodaj;
    @FXML
    private TextField guestNumber;
    @FXML
    private Label wrong;


    ReservationD reservationD = new ReservationD();
    /**

     The initialize method is called when the window is opened.
     It sets the items for the guest and room combo boxes by calling the getGuestObList and getApartObList methods.
     */

    @FXML
    private void initialize() {
        ChoiceGuest.setItems(getGuestObList());
        ChoiceRoom.setItems(getApartObList());
    }
    /**

     The method getGuestObList returns an observable list of guests from the database.
     @return ObservableList of guests
     */
    private ObservableList<Guest> getGuestObList() {
        ObservableList<Guest> list = FXCollections.observableArrayList();
        list.addAll(new GuestD().getGuests());
        return list;
    }
    /**

     The method getApartObList returns an observable list of rooms from the database.
     @return ObservableList of rooms
     */
    private ObservableList<Room> getApartObList() {
        ObservableList<Room> list = FXCollections.observableArrayList();
        list.addAll(new RoomD().GetRooms());
        return list;
    }
    /**

     The method validateInputs checks if the inputs provided by the user are valid.

     It checks if the date fields are not empty and if the end date is after the start date.

     It also checks if the selected room and guest are not null and if the number of guests is not higher than

     the maximum capacity of the selected room.

     @return true if the inputs are valid, false otherwise
     */
    private boolean validateInputs() {

        if (DateEnd.getValue() == null) {
            wrong.setText("Nie uzupełniono wszystkich pól");
            return false;
        }if(DateStart.getValue() == null) {
            wrong.setText("Nie uzupełniono wszystkich pól");
            return false;
        }if (DateEnd.getValue().isBefore(DateStart.getValue())) {

            wrong.setText("Źle wybrana data");
            return false;
        }if (LocalDate.now().isAfter(DateStart.getValue())) {

            wrong.setText("nie można zrobić rezerwacji wstecz");
            return false;
        }if(ChoiceRoom.getValue() == null) {
            wrong.setText("Nie uzupełniono wszystkich pól");
            return false;
        }if (ChoiceGuest.getValue() == null) {
            wrong.setText("Nie uzupełniono wszystkich pól");
            return false;
        }if (ChoiceRoom.getValue().getMaxPerson().longValue()<Long.valueOf(guestNumber.getText())) {
            wrong.setText("Zbyt duża ilość gości");
            return false;
        }if (0>=Long.valueOf(guestNumber.getText())) {
            wrong.setText("Liczba gości nie może być ujemna lub równa 0");
            return false;
        }if (guestNumber.getText().equals("")) {
            wrong.setText(" Nie uzupełniono wszystkich pól");
            return false;
        }
        return true;
    }
    /**
     * Creates a new {@link Reservation} object from user inputs.
     * @return created Reservation object
     */
    private Reservation createRezerFromInput() {
        Reservation reservation = new Reservation();
        reservation.setRezstart(DateStart.getValue());
        reservation.setRezstop(DateEnd.getValue());
        reservation.setRoom(ChoiceRoom.getValue());
        reservation.setGuest(ChoiceGuest.getValue());
        reservation.setIloscgosci(Long.valueOf(guestNumber.getText()));
        return reservation;
    }

    /**
     * Handles the event when the user clicks the "Dodaj" button,
     * creates a new reservation and saves it to the database,
     * and changes the scene to the reservation table view.
     * @param event the button click event
     * @throws IOException
     */
    @FXML
    void DodajRezerwacje(ActionEvent event) throws IOException {
        if(validateInputs()) {
            Reservation reservation = createRezerFromInput();
            new ReservationD().createReservation(reservation);

            Application m = new Application();
            m.changeScene("dodawanierezerwacji.fxml");

            Application.close(event);

        }
    }
    /**
     * Closes the current window when the "Anuluj" button is clicked.
     * @param event the button click event
     */
    @FXML
    void Canceloperation(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
}