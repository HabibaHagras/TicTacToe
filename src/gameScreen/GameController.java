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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.gameover.GameoverController;
import tictactoe.helpcontroller;
import tictactoe.playagainwin.PlayagainwinController;

/**
 *
 * @author HP
 */
public class GameController implements Initializable {

    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    @FXML
    private Text player1;
    @FXML
    private Text player2;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button button9;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button newButton;

    private int playerTurn = 0;

    ArrayList<Button> buttons;
    // String winner = null;
    public static String winner;
    @FXML

    AnchorPane apane;

    Parent myNewScene = null;

    int countX = 0;
    int countO = 0;

    @FXML
    private Text txt;

    //public static String  name = checkWinner(player1.getText(), player2.getText());
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);

        });

    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkWinner(player1.getText(), player2.getText());
        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            button.setTextFill(Paint.valueOf("#ff0000"));
            playerTurn = 1;
        } else {
            button.setText("O");
            button.setTextFill(Paint.valueOf("#ffc300"));
            playerTurn = 0;
        }
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    public void onclickback(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/TwoPlayerPage.fxml"));
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
    public void onclicknewgame(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();

    }

    public void showwin() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/playagainwin/playagainwin.fxml"));
            root = loader.load();
            PlayagainwinController playAgainWinController = loader.getController();
            playAgainWinController.setWinner(winner);
            playAgainWinController.setGameController(this); // Set the GameController reference

            //root = FXMLLoader.load((getClass().getResource("/tictactoe/playagainwin/playagainwin.fxml")));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            playAgainWinController.setStage(stage);

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showgameover() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/gameover/gameover.fxml"));
            root = loader.load();
            GameoverController GameoverController = loader.getController();
            GameoverController.setGameController(this); // Set the GameController reference

            //root = FXMLLoader.load((getClass().getResource("/tictactoe/playagainwin/playagainwin.fxml")));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            GameoverController.setStage(stage);

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateScore(String symbol) {

        if (symbol.equalsIgnoreCase(player1.getText())) {
            countX++;
            score1.setText(String.valueOf(countX));
        } else if (symbol.equalsIgnoreCase(player2.getText())) {
            countO++;
            score2.setText(String.valueOf(countO));
        }

    }

    public void displayPlayers(String x, String o) {
        player1.setText(x);
        player2.setText(o);
    }

    public String checkWinner(String playerX, String playerO) {
        playerX = player1.getText();
        playerO = player2.getText();
        //String winner = null;

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
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b4.equals(b5) && b4.equals(b6)) {
            if (b4.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b4.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b7.equals(b8) && b7.equals(b9)) {
            if (b7.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b7.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b1.equals(b4) && b1.equals(b7)) {
            if (b1.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b2.equals(b5) && b2.equals(b8)) {
            if (b2.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b2.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b3.equals(b6) && b3.equals(b9)) {
            if (b3.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b3.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b1.equals(b5) && b1.equals(b9)) {
            if (b1.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b3.equals(b5) && b3.equals(b7)) {
            if (b3.equals("X")) {
                winner = playerX;
                showwin();
                updateScore(winner);

            } else if (b3.equals("O")) {
                winner = playerO;
                showwin();
                updateScore(winner);

            }
        }
        if (b1.isEmpty() || b2.isEmpty() || b3.isEmpty()
                || b4.isEmpty() || b5.isEmpty() || b6.isEmpty()
                || b7.isEmpty() || b8.isEmpty() || b9.isEmpty()) {
            // Game still in progress
            return null;
        } else {

            // It's a draw
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Game Over");
//            alert.setHeaderText("It's a draw!");
//            alert.setContentText("Click OK to play again.");
//            alert.showAndWait();
//            resetButton(button1);
//            resetButton(button2);
//            resetButton(button3);
//            resetButton(button4);
//            resetButton(button5);
//            resetButton(button6);
//            resetButton(button7);
//            resetButton(button8);
//            resetButton(button9);
            showgameover();

            return null;
        }
        //  System.out.println(winner);

        //return winner;
    }

    public void closeGameStage() {
        Stage gameStage = (Stage) apane.getScene().getWindow();
        gameStage.close();
    }

}
