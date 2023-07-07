package com.example.projkecik.Controllers;
import com.example.projkecik.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
/**

 The LoginController class is responsible for handling the login process for the user.

 It contains methods and fields for handling the login button, log text field, password field, and wrong label.


 */
public class LoginController {
    /**

FXML field for the login text field
*/
    @FXML
    private TextField log;
    /**

     FXML field for the login button
     */
    @FXML
    private Button login;
    /**

     FXML field for the password text field
     */
    @FXML
    private PasswordField password;
    /**

     FXML field for the wrong label
     */
    @FXML
    private Label wrong;

    /**

     Method that is called when the user clicks the login button. It calls the checkLogin() method.
     @param event The ActionEvent that is triggered when the login button is clicked
     @throws IOException If there is an issue with loading the next scene
     */
    @FXML
    void UserLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }
    /**

     Method that checks the input in the login and password fields against the correct login credentials.

     If the credentials are correct, it changes the scene to the next view.

     If the credentials are incorrect or if the fields are empty, it displays an error message.

     @throws IOException If there is an issue with loading the next scene
     */
    private void checkLogin() throws IOException {
       Application m = new Application();
        if(log.getText().toString().equals("admin")&& password.getText().toString().equals("admin")){
           m.changeScene("pokojeszef.fxml");

        } else if (log.getText().isEmpty() && password.getText().isEmpty() ) {
            wrong.setText("Podaj dane logowania");
        }
       else {
           wrong.setText("Zły e-mail lub hasło!");
        }
    }
}