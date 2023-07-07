package com.example.projkecik.Controllers;



import com.example.projkecik.Constructor.Guest;
import com.example.projkecik.D.RoomD;
import com.example.projkecik.Constructor.Room;
import com.example.projkecik.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**

 The RoomController class is responsible for handling the logic and events related to the room management view in the application.
 It implements the Initializable interface to provide the necessary initialization code for the room management view.

 */
public class RoomController implements Initializable {

    @FXML
    private TableColumn<Room, String> Description;

    @FXML
    private Button DodanieApart;

    @FXML
    private Button DodanieGosc;

    @FXML
    private Button DodanieRezer;



    @FXML
    private TableColumn<Room, Long> MaxPerson;



    @FXML
    private TableColumn<Room, String> RoomName;

    @FXML
    private TableView<Room> RoomTable;

    @FXML
    private TableColumn<Room, String> RoomType;

    @FXML
    private Button StronaGlowna;

    @FXML
    private Button Wyjscie;

    RoomD roomD =new RoomD();
    ObservableList<Room> RoomObList = FXCollections.observableArrayList();
    /**
     * The initialize() method is called by the FXMLLoader when the view is loaded and ready to be displayed.
     * It sets up the TableView with the necessary data and properties, such as setting the cell value factories for each column,
     * enabling editing for the TableView, and setting the selection mode to allow for multiple selections.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle) {



       RoomObList.clear();
       RoomObList.addAll(roomD.GetRooms());


       RoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
       RoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
       MaxPerson.setCellValueFactory(new PropertyValueFactory<>("maxPerson"));
       Description.setCellValueFactory(new PropertyValueFactory<>("description"));


       RoomTable.setEditable(true);
       RoomTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       RoomTable.setItems(RoomObList);
    }
    /**
     changeMaxPersonCell and changeDescriptionCell methods are responsible for updating the max person, and description
     of a selected room in the table and saving the changes to the database through the roomD object.
     */
    @FXML
    private void changeMaxPersonCell(TableColumn.CellEditEvent<Room, Long> editEvent) {
        Room selectedroom = RoomTable.getSelectionModel().getSelectedItem();
        selectedroom.setMaxPerson(Long.valueOf(editEvent.getNewValue().toString()));
        roomD.updateRoom(selectedroom);
    }

    @FXML
    private void changeDescriptionCell(TableColumn.CellEditEvent<Room, String> editEvent) {
        Room selectedroom = RoomTable.getSelectionModel().getSelectedItem();
        selectedroom.setDescription(editEvent.getNewValue().toString());
        roomD.updateRoom(selectedroom);
    }
    /**

     The ZmianaScenyDodanieGosc,ZmianaScenyDodanieRezer, ZmianaScenyStronaGlowna and ZmianaScenyWyjscie methods are used to change the scene in the application to the corresponding FXML file.
     These methods are triggered when the corresponding button is pressed.

     */
    @FXML
    void ZmianaScenyDodanieGosc(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("dodawaniegoscia.fxml");
    }


    @FXML
    void ZmianaScenyDodanieRezer(ActionEvent event) throws IOException {
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


