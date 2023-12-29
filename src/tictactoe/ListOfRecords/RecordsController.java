/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ListOfRecords;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RecordsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    AnchorPane ancPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ancPane.getStylesheets().add(getClass().getResource("records.css").toString());
    }    
    
}
