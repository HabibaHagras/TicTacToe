/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermode;

import gameScreen.GameController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class ComputerGameController implements Initializable {

    Random random = new Random();
    ArrayList<Button> buttons;
    AdversarialSearchTicTacToe ticTacToeAI = new AdversarialSearchTicTacToe();
    String winner = "";
    
    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    @FXML
    private Text player;
    @FXML
    private Text score1;
    @FXML
    private Text txt;
    @FXML
    private Text score2;
    @FXML
    private Button button1;
    @FXML
    private Button button4;
    @FXML
    private Button button7;
    @FXML
    private Button button2;
    @FXML
    private Button button5;
    @FXML
    private Button button8;
    @FXML
    private Button button3;
    @FXML
    private Button button6;
    @FXML
    private Button button9;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button newButton;
    @FXML
    private Text computer;
    
    int countX=0;
    int countO=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
        });
    }    
    
    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        button.setStyle("-fx-background-color: #003055;");
    }
    
    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            winner = "";
            button.setText("X");
            button.setDisable(true);
            button.setTextFill(Paint.valueOf("#ff0000"));
            checkIfGameIsOver();
            makeAIMove();
            checkIfGameIsOver();
        });
    }

    public void makeAIMove() {
        int move = ticTacToeAI.minMaxDecision(getBoardState());
        if("".equals(winner)){
            pickButton(move);
        }
    }

    private void pickButton(int index) {
        buttons.get(index).setText("O");
        buttons.get(index).setTextFill(Paint.valueOf("#ffc300"));
        buttons.get(index).setDisable(true);
    }
    

    public State getBoardState() {
        String[] board = new String[9];

        for (int i = 0; i < buttons.size(); i++) {
            board[i] = buttons.get(i).getText();
        }

        return new State(0, board);
    }
     public void updateScore(String symbol) {

        if (symbol.equalsIgnoreCase("XXX")) {
            System.out.println(countX);
            countX++;
            System.out.println(countX);
            score2.setText(String.valueOf(countX));
            System.out.println(countX);
        } else if (symbol.equalsIgnoreCase("OOO")) {
            countO++;
            score1.setText(String.valueOf(countO));
        }

    }
    
    public void checkIfGameIsOver() {

        /*for (int a = 0; a < 8; a++) {

            switch (a) {
                case 0:
                    winner = button1.getText() + button2.getText() + button3.getText();
                    break;
                case 1:
                    winner = button4.getText() + button5.getText() + button6.getText();
                    break;
                case 2:
                    winner = button7.getText() + button8.getText() + button9.getText();
                    break;
                case 3:
                    winner = button1.getText() + button5.getText() + button9.getText();
                    break;
                case 4:
                    winner = button3.getText() + button5.getText() + button7.getText();
                    break;
                case 5:
                    winner = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 6:
                    winner = button2.getText() + button5.getText() + button8.getText();
                    break;
                case 7:
                    winner = button3.getText() + button6.getText() + button9.getText();
                    break;
                default:
                    winner = null;
            }*/
            
            String b1 = button1.getText();
            String b2 = button2.getText();
            String b3 = button3.getText();
            String b4 = button4.getText();
            String b5 = button5.getText();
            String b6 = button6.getText();
            String b7 = button7.getText();
            String b8 = button8.getText();
            String b9 = button9.getText();
            
            if (b1.equals(b2) && b1.equals(b3)) {
            System.out.println("x player");
            if (b1.equals("X")) {
                winner = "XXX";
                button1.setStyle("-fx-background-color: aliceblue;");
                button2.setStyle("-fx-background-color: aliceblue;");
                button3.setStyle("-fx-background-color: aliceblue;");
              
            } else if (b1.equals("O")) {
                winner = "OOO";
                button1.setStyle("-fx-background-color: aliceblue;");
                button2.setStyle("-fx-background-color: aliceblue;");
                button3.setStyle("-fx-background-color: aliceblue;");
               

            }
           
        }
        if (b4.equals(b5) && b4.equals(b6)) {
            if (b4.equals("X")) {
                winner = "XXX";
                button4.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                
            } else if (b4.equals("O")) {
                winner = "OOO";
                button4.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
               

            }
            
        }
        if (b7.equals(b8) && b7.equals(b9)) {
            if (b7.equals("X")) {
                winner = "XXX";
                button7.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
               

            } else if (b7.equals("O")) {
                winner = "OOO";
                button7.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
             
            }
            
        }
        if (b1.equals(b4) && b1.equals(b7)) {
            if (b1.equals("X")) {
                winner = "XXX";
                button1.setStyle("-fx-background-color: aliceblue;");
                button4.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
               

            } else if (b1.equals("O")) {
                winner = "OOO";
                button1.setStyle("-fx-background-color: aliceblue;");
                button4.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
               
            }
           
        }
        if (b2.equals(b5) && b2.equals(b8)) {
            if (b2.equals("X")) {
                winner = "XXX";
                button2.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                
            } else if (b2.equals("O")) {
                winner = "OOO";
                button2.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
          
            }
           
        }
        if (b3.equals(b6) && b3.equals(b9)) {
            if (b3.equals("X")) {
                winner = "XXX";
                button3.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                
                

            } else if (b3.equals("O")) {
                winner = "OOO";
                button3.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
            
                
            }
        }
        if (b1.equals(b5) && b1.equals(b9)) {
            if (b1.equals("X")) {
                winner = "XXX";
                button1.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                
                
            } else if (b1.equals("O")) {
                winner = "OOO";
                button1.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                
                
            }
           
        }
        if (b3.equals(b5) && b3.equals(b7)) {
            if (b3.equals("X")) {
                winner = "XXX";
                button3.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
            } else if (b3.equals("O")) {
                winner = "OOO";
                button3.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
            }
        }

            //X winner
            if (winner.equals("XXX")) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"the palyer win");
                alert.show();
                
            } //O winner
            else if (winner.equals("OOO")) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"the computer win");
                alert.show(); 
            }
            updateScore(winner);
        }
    //}
    
    public void back(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/OnePlayerpckg/OnePlayerPage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        buttons.forEach(this::resetButton);
        pickButton(random.nextInt(9));
    }
   
}
