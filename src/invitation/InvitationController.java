/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invitation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InvitationController implements Initializable {

    @FXML
    private AnchorPane apane;
    @FXML
    private Text Text;
    @FXML
    private Label labelplayer;
    @FXML
    private Button button1yes;
    @FXML
    private Button button1no;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void onclickNo(ActionEvent event) {
    }

    @FXML
    private void onclickAccept(ActionEvent event) {
    }
    
}
