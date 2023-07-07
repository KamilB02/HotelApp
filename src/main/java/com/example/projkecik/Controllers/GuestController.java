package com.example.projkecik.Controllers;


import com.example.projkecik.D.GuestD;
import com.example.projkecik.Constructor.Guest;
import com.example.projkecik.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
/**

 The GuestController class handles the logic for the guest management page of the application.

 It allows for adding, editing, and deleting guests from the system.


 */
public class GuestController implements Initializable {
    @FXML
    private TextField Szukaj;
    @FXML
    private Button DodajGoscia;
    @FXML
    private Button UsunGoscia;
    @FXML
    private Button DodanieApart;

    @FXML
    private Button DodanieGosc;

    @FXML
    private Button DodanieRezer;
    



    @FXML
    private TableColumn<Guest, String> KolumnaNazwisko;

    @FXML
    private TableColumn<Guest,Long> KolumnaNrTele;

    @FXML
    private Button StronaGlowna;

    @FXML
    private Button Wyjscie;



    @FXML
    private TableColumn<Guest,String> kolumnaImie;

    @FXML
    private TableView<Guest> tabelagoscie;
    @FXML
    private TextField PoleImie;



    @FXML
    private TextField PoleNazwisko;

    @FXML
    private TextField PoleNrTelefonu;

    GuestD guestD =new GuestD();
    ObservableList<Guest> goscObList = FXCollections.observableArrayList();

    /**
     The initialize() method is called when the view is loaded. It first clears the ObservableList and populates it with all guests from the GuestD object.
     Then, it sets the cell value factories for the table columns to the appropriate fields in the Guest class.
     It also sets the cell factories for the table columns to allow editing. Finally, it sets the table view to be editable,
     sets the selection mode to multiple and sets the table's items to the sorted list of guests.
     */

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle) {
        goscObList.clear();
        goscObList.addAll(guestD.getGuests());


        kolumnaImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        KolumnaNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        KolumnaNrTele.setCellValueFactory(new PropertyValueFactory<>("nrtelefonu"));


        kolumnaImie.setCellFactory(TextFieldTableCell.forTableColumn());
        KolumnaNazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
        KolumnaNrTele.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        tabelagoscie.setEditable(true);
        tabelagoscie.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabelagoscie.setItems(getSortedList());
    }

    /**
     The validateInputs() method checks if the input fields for guest's name,
     surname and phone number are empty,
     and returns false if any of them is empty.
     */
    private boolean validateInputs() {
        if (PoleImie.getText().equals("")) {
            return false;
        }

        if (PoleNazwisko.getText().equals("")) {
            return false;
        }
    if (0>=Long.valueOf(PoleNrTelefonu.getText())) {
            return false;
        }
        if (PoleNrTelefonu.getText().equals("")) {
            return false;
        }


        return true;
    }

    /**
     The createGoscFromInput() method creates a new Guest object and sets its properties to the values from the input fields.
     */
    private Guest createGoscFromInput() {
       Guest gosc = new Guest();
        gosc.setImie(PoleImie.getText());
        gosc.setNazwisko(PoleNazwisko.getText());
        gosc.setNrtelefonu(Long.valueOf(PoleNrTelefonu.getText()));

        return gosc;
    }

    /**
     The Dodajgoscia() method is called when the "Add Guest" button is clicked. It first checks if the input fields are valid,
     then creates a new guest object and calls the createGuest() method in the GuestD class to save it to the database.
     */
    @FXML
    void Dodajgoscia(ActionEvent event) throws IOException {
        if (validateInputs()) {
            Guest gosc = createGoscFromInput();
            new GuestD().createGuest(gosc);
        }
        refreshScreen(event);
    }

    /**
     The Usungoscia() method is called when the "Delete Guest" button is clicked.
     It gets the selected rows in the table, and calls the delete
     */

    @FXML
    void Usungoscia(ActionEvent event) throws IOException {
        ObservableList<Guest> selectedRows = tabelagoscie.getSelectionModel().getSelectedItems();
        for (Guest gos : selectedRows) {
            guestD.deleteGuest(gos);
        }
        refreshScreen(event);
    }
    /**

     refreshScreen method changes the current scene to the same scene using the Application class
     */
    @FXML
    void refreshScreen(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("dodawaniegoscia.fxml");
    }

    /**
     getSortedList method returns a sorted list of guests using a filtered list
     and binding it to the comparator property of the guest table
     */
    private SortedList<Guest> getSortedList() {
        SortedList<Guest> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(tabelagoscie.comparatorProperty());
        return sortedList;
    }

    /**
     getFilteredList method returns a filtered list of guests based on user input in the search field,
     comparing it to the name, last name, phone number, and ID of each guest
     */
    private FilteredList<Guest> getFilteredList() {
        FilteredList<Guest> filteredList = new FilteredList<>(goscObList, b -> true);
        Szukaj.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(gosc -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (gosc.getImie().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gosc.getNazwisko().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gosc.getNrtelefonu().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else return gosc.getId().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    /**
     changeNameCell, changeLastNameCell and changePhoneNumberCell methods are responsible for updating the name, last name, and phone number
     of a selected guest in the table and saving the changes to the database through the guestD object.
     */
    @FXML
    private void changeNameCell(TableColumn.CellEditEvent<Guest, String> editEvent) {
        Guest selectedGosc = tabelagoscie.getSelectionModel().getSelectedItem();
        selectedGosc.setImie(editEvent.getNewValue().toString());
        guestD.updateGuest(selectedGosc);
    }

    @FXML
    private void changeLastNameCell(TableColumn.CellEditEvent<Guest, String> editEvent) {
        Guest selectedGosc = tabelagoscie.getSelectionModel().getSelectedItem();
        selectedGosc.setNazwisko(editEvent.getNewValue().toString());
        guestD.updateGuest(selectedGosc);
    }

    @FXML
    private void changePhoneNumberCell(TableColumn.CellEditEvent<Guest, Long> editEvent) {
        Guest selectedGosc = tabelagoscie.getSelectionModel().getSelectedItem();
        selectedGosc.setNrtelefonu(Long.valueOf(editEvent.getNewValue().toString()));
        guestD.updateGuest(selectedGosc);
    }

    /**

     The ZmianaScenyDodanieApart,ZmianaScenyDodanieRezer, ZmianaScenyStronaGlowna and ZmianaScenyWyjscie methods are used to change the scene in the application to the corresponding FXML file.
     These methods are triggered when the corresponding button is pressed.

     */
    @FXML
    void ZmianaScenyDodanieApart(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("dodawanieapartament.fxml");
    }

    @FXML
    void ZmianaScenyDodanieRezer(ActionEvent event) throws  IOException {
        Application m = new Application();
        m.changeScene("dodawanierezerwacji.fxml");
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


}


