/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.helpcontroller;


/**
 *
 * @author HP
 */
public class GameController implements Initializable {
    Scene scene;
    Stage stage;
    Parent root; 
    @FXML
    private Text player1;
    @FXML
    private Text player2;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button newButton;
    
    private int playerTurn = 0;

    ArrayList<Button> buttons;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(button ->{
            setupButton(button);
        });
    } 
    
    
    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            //checkWinner(player1.getText(), player2.getText());
        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            button.setTextFill(Paint.valueOf("#ff0000"));
            playerTurn = 1;
        } else{
            button.setText("O");
            button.setTextFill(Paint.valueOf("#ffc300"));
            playerTurn = 0;
        }
    }
    
  
    
    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }
 
    public void onclickback (ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("/TwoPlayerPage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

    
    public void onclicknewgame(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
       }
                  
    
    
    public String checkWinner(String playerX , String playerO){
        playerX = player1.getText();
        playerO = player2.getText();
        String winner = null;
        String b1 = button1.getText();
        String b2 = button2.getText();
        String b3 = button3.getText();
        String b4 = button4.getText();
        String b5 = button5.getText();
        String b6 = button6.getText();
        String b7 = button7.getText();
        String b8 = button8.getText();
        String b9 = button9.getText();
        
        if(b1.equals(b2) && b1.equals(b3)){
            if(b1.equals("X")){
                winner = playerX;
            }else if(b1.equals("O")){
                winner = playerO;
            }
        }
        if(b4.equals(b5) && b4.equals(b6)){
            if(b4.equals("X")){
                winner = playerX;
            }else if(b4.equals("O")){
                winner = playerO;
            }
        }
        if(b7.equals(b8) && b7.equals(b9)){
            if(b7.equals("X")){
                winner = playerX;
            }else if(b7.equals("O")){
                winner = playerO;
            }
        }
        if(b1.equals(b4) && b1.equals(b7)){
            if(b1.equals("X")){
                winner = playerX;
            }else if(b1.equals("O")){
                winner = playerO;
            }
        }
        if(b2.equals(b5) && b2.equals(b8)){
            if(b2.equals("X")){
                winner = playerX;
            }else if(b2.equals("O")){
                winner = playerO;
            }
        }
        if(b3.equals(b6) && b3.equals(b9)){
            if(b3.equals("X")){
                winner = playerX;
            }else if(b3.equals("O")){
                winner = playerO;
            }
        }
        if(b1.equals(b5) && b1.equals(b9)){
            if(b1.equals("X")){
                winner = playerX;
            }else if(b1.equals("O")){
                winner = playerO;
            }
        }
        if(b3.equals(b5) && b3.equals(b7)){
            if(b3.equals("X")){
                winner = playerX;
            }else if(b3.equals("O")){
                winner = playerO;
            }
        }
      return winner;   
    }
}