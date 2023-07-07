package com.example.projkecik;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import  com.example.projkecik.util.HibernateUtil;
import java.io.IOException;

import com.example.projkecik.D.RoomD;
import com.example.projkecik.Constructor.Room;
/**

 The main class that runs the JavaFX application.


 */

public class Application extends javafx.application.Application {
    /**

The RoomD object that is used to interact with the Room table in the database.
*/
 RoomD roomD = new RoomD();
    /**
   The Stage object that is used to set and display the scene for the application.
   */
    private static Stage stg;
    /**

     Initializes the application by creating default rooms if the database is empty.

     @throws Exception
     */
    @Override
    public void init() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();

      if (roomD.GetRooms().isEmpty()) {
          Room room1 = new Room("Bora Bora", "Apartament typu Deluxe\n z aneksem kuchennym", 5L, """
                  Sypialnia 1:1 łóżko pojedyncze i łóżko podwójne
                  Sypialnia 2:1 łóżko podwójne""");
          Room room2 = new Room("Mauritius", "Pokój typu Deluxe", 4L, "2 łóżka pojedyncze i 1 duże łóżko podwójne");
          Room room3 = new Room("Santa Lucia", "Pokój typu Economy", 2L, "1 łóżko podwójne");
          Room room4 = new Room("Korfu", "Pokój typu Standard", 2L, "1 łóżko podwójne");
          Room room5 = new Room("Ko Phi Phi", "Apartament typu Standard \n z aneksem kuchennym", 3L, "Sypialnia 1:1 łóżko pojedyncze\n" +
                  "Sypialnia 2:1 łóżko podwójne");
          Room room6 = new Room("Capri", "Pokój typu Superior", 3L, "1 rozkładana sofa i 1 duże łóżko podwójne");
          Room room7 = new Room("Madera", "Pokój typu Standard", 2L, "1 łóżko podwójne");
          Room room8 = new Room("Hvar", "Pokój typu Economy", 2L, "1 łóżko podwójne");
          Room room9 = new Room("Bali", "Pokój typu Deluxe", 4L, "2 łóżka pojedyncze i 1 duże łóżko podwójne");
          Room room10 = new Room("Palawan", "Pokój typu Standard", 2L, "1 łóżko podwójne");

          new RoomD().CreateRoom(room1);

          new RoomD().CreateRoom(room2);

          new RoomD().CreateRoom(room3);

          new RoomD().CreateRoom(room4);

          new RoomD().CreateRoom(room5);

          new RoomD().CreateRoom(room6);

          new RoomD().CreateRoom(room7);

          new RoomD().CreateRoom(room8);

          new RoomD().CreateRoom(room9);

          new RoomD().CreateRoom(room10);
      }

    }
    /**

     Starts the program by loading the main window of the application and setting its properties.

     @param stage the main stage of the application

     @throws IOException if there is any problem with loading the fxml file for the main window
     */

    @Override
    public void start(Stage stage) throws IOException {


        stg = stage;

       FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        stage.setResizable(false);
       Scene scene = new Scene(fxmlLoader.load(),1207,966);
        stage.setTitle("Alabama Hotel");
        stage.setScene(scene);
        stage.show();
        
    }
    /**

     Changes the scene of the primary stage to the specified FXML file
     @param fxml the path to the FXML file to be loaded as the new scene
     @throws IOException if the FXML file cannot be loaded
     */
   public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    /**

     Opens a new window with the specified FXML file as the scene
     @param fxml the path to the FXML file to be loaded as the new scene
     @throws IOException if the FXML file cannot be loaded
     */
    public void NewWindow(String fxml) throws IOException{
        Stage stage = new Stage();
        Pane main = FXMLLoader.load(Application.class.getResource(fxml));
        stage.setResizable(false);
        stage.setScene(new Scene(main));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene();
        stage.showAndWait();
    }
    /**

     The stop method is an overridden method from the javafx.application.Application class.
     It is called when the application is stopped and is used to perform cleanup operations.
     In this case, it is used to shutdown the Hibernate connection by calling the shutdown method from the HibernateUtil class.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtil.shutdown();
    }
    /**

     The close method is a static method that is used to close the current window.
     It takes in an ActionEvent as a parameter, which is used to determine the source of the event.
     The source is then casted to a Node and its associated Stage is obtained and closed.
     */
    public static void close(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    /**

     The main method is the entry point of the application. It calls the launch method from the javafx.application.Application class,
     which starts the JavaFX application by calling the init, start, and stop methods in the specified order.
     */
    public static void main(String[] args) {
        launch();
    }
}