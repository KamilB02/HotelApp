package com.example.projkecik.Controllers;


import com.example.projkecik.D.ReservationD;
import com.example.projkecik.Application;
import com.example.projkecik.Constructor.Reservation;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
/**

 The ReservationController class is used to handle the interactions between the reservation view and the reservation model.

 It contains methods to handle adding, deleting and editing reservations, as well as methods to navigate between scenes.

 */
public class ReservationController {
    @FXML
    private Button Clear;

    @FXML
    private TextField Find;
    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker startDate;
    @FXML
    private Button AddRez;

    @FXML
    private TableColumn<Reservation, LocalDate> DateO;

    @FXML
    private TableColumn<Reservation, LocalDate> DateZ;

    @FXML
    private Button DeleteRez;

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
    private TableColumn<Reservation, String> guestId;

    @FXML
    private TableView<Reservation> resTable;

    @FXML
    private TableColumn<Reservation, String> roomId;

    @FXML
    private TableColumn<Reservation, Long> guestNumber;

   ReservationD reservationD = new ReservationD();
   ObservableList<Reservation> resObList = FXCollections.observableArrayList();
    /**
     * The initialize method is called after the FXML file has been loaded.
     * It sets the data for the table and makes it sortable and searchable.
     */
    @FXML
    private void initialize() {

        setObList();
        fillTable();
        clearSearchResults();

    }
    /**
     * This method sets the data for the table by clearing the existing data and adding new data from the database.
     */
    private void setObList() {
        resObList.clear();
        resObList.addAll(reservationD.getReservations());
    }

    /**
     * This method fills the table with data and sets the columns for the table.
     */
    private void fillTable(){

        DateZ.setCellValueFactory(new PropertyValueFactory<>("rezstart"));
        DateO.setCellValueFactory(new PropertyValueFactory<>("rezstop"));
        roomId.setCellValueFactory(new PropertyValueFactory<>("room"));
        guestId.setCellValueFactory(new PropertyValueFactory<>("guest"));
        guestNumber.setCellValueFactory(new PropertyValueFactory<>("iloscgosci"));
        resTable.setItems(getSortedList());
   }
    /**

     This method is responsible for handling the 'DeleteRezer' button event.
     It deletes the selected reservations from the table and refreshes the scene by changing it to 'dodawanierezerwacji.fxml'.
     @param event - the action event that triggers this method
     @throws IOException - if there is an issue with changing the scene
     */
    @FXML
    void DeleteRezer(ActionEvent event) throws IOException {
        ObservableList<Reservation> selectedRows = resTable.getSelectionModel().getSelectedItems();
        for (Reservation reservation : selectedRows) {
           reservationD.deleteReservation(reservation);
        }
        Application m = new Application();
        m.changeScene("dodawanierezerwacji.fxml");
    }
    /**

     This method returns a sorted list of reservations filtered by both date and search string.
     @return sortedList - a sorted list of reservations
     */
    private SortedList<Reservation> getSortedList() {
        SortedList<Reservation> sortedList = new SortedList<>(getFilteredListByDates());
        sortedList.comparatorProperty().bind(resTable.comparatorProperty());
        return sortedList;
    }
    /**

     This method returns a filtered list of reservations filtered by date range.

     @return filteredItems - a filtered list of reservations based on date range
     */
    @FXML
    private FilteredList<Reservation> getFilteredListByDates() {
        FilteredList<Reservation> filteredItems = new FilteredList<>(getFilteredListByString());
        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(()->{
            LocalDate minDate = startDate.getValue();
            LocalDate maxDate = endDate.getValue();
            final LocalDate finalMin = minDate ==null ? LocalDate.MIN : minDate;
            final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;

            return  rezerwacja -> !finalMin.isAfter(rezerwacja.getRezstart()) && !finalMax.isBefore(rezerwacja.getRezstop());
        },
        startDate.valueProperty(),
                endDate.valueProperty()));
    return filteredItems;
    }
    /**

     This method returns a filtered list of reservations filtered by search string.

     @return filteredList - a filtered list of reservations based on search string
     */
    private FilteredList<Reservation> getFilteredListByString() {
        FilteredList<Reservation> filteredList = new FilteredList<>(resObList, b -> true);
        Find.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(rezerwacja -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (rezerwacja.getGuest().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (rezerwacja.getRoom().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return rezerwacja.getId().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }
    /**

     The clearSearchResults method is used to clear the search results of the TableView and restore it to its default state.
     It is triggered when the Clear button is pressed and it sets the start and end date pickers to null,
     sets the text in the Find text field to an empty string and updates the TableView with the new filtered and sorted list.

     */
    private void clearSearchResults() {
        Clear.setOnAction(event -> {
            startDate.setValue(null);
            endDate.setValue(null);
            Find.setText("");
            resTable.setItems(getSortedList());
        });
    }
    /**

     The ZmianaScenyDodanieApart, ZmianaScenyDodanieGosc, ZmianaScenyStronaGlowna and ZmianaScenyWyjscie methods are used to change the scene in the application to the corresponding FXML file.
     These methods are triggered when the corresponding button is pressed.
     The AddRezer method is used to open a new window with the "nowarezerwacja.fxml" file. It is triggered when the "AddRez" button is pressed.
     */
    @FXML
    void ZmianaScenyDodanieApart(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("dodawanieapartament.fxml");
    }


    @FXML
    void ZmianaScenyDodanieGosc(ActionEvent event) throws  IOException {
        Application m = new Application();
        m.changeScene("dodawaniegoscia.fxml");
    }

    @FXML
    void ZmianaScenyStronaGlowna(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("pokojeszef.fxml");
    }

    @FXML
    void ZmianaScenyWyjscie(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("hello-view.fxml");
    }
    @FXML
    void AddRezer(ActionEvent event) throws IOException  {
        Application m = new Application();
        m.NewWindow("nowarezerwacja.fxml");
    }

}
