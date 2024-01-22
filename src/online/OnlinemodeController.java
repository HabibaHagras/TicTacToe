/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import static gameScreen.GameController.winner;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.BreakNode;
import tictactoe.LoginController;
import tictactoe.gameover.GameoverController;
import tictactoe.playagainwin.PlayagainwinController;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class OnlinemodeController implements Initializable {

    String msg;
    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    @FXML
    private AnchorPane apane;
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
    private Button newButton;

    public int playerTurn = 0;
    ArrayList<Button> buttons;
    public static String winner;

    int countX = 0;
    int countO = 0;
    @FXML
    private Text player1;
    @FXML
    private Text player2;
    Socket server;

    DataInputStream ear;
    PrintStream mouth;
    @FXML
    private Button logout;

    // private Socket server;
    private DataInputStream reader;
    private PrintStream printStream;

    String symbol;
    String Player11;
    String Player22;
    OutputStream outputStream;
    InputStream inputStream;
    int boll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);

        });

        if (player1.getText().equals("ahmed")) {
            player2.setText("Habiba");
        } else if (player1.getText().equals("Habiba")) {
            player2.setText("ahmed");

        }
    }

    public void setupButton(Button button) {

        button.setOnMouseClicked(mouseEvent -> {

            setPlayerSymbol(button);

            button.setDisable(true);
            checkWinner(player1.getText(), player2.getText());

        });

    }

    public void setPlayerSymbol(Button button) {

        new Thread(() -> {
            if (playerTurn % 2 == 0) {

                try {
                    button.setText("X");
                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    OutputStream outputStream = server.getOutputStream();
                    String playMessage = "MOVEX" + " " + player2.getText() + " " + button.getId() + " " + button.getText();
                    outputStream.write(playMessage.getBytes());
                    System.out.println(button.getId());
                    sendXmove(button.getId());
                    button.setTextFill(Paint.valueOf("#ff0000"));
                    playerTurn = 1;
                    System.out.println("pdddddlaaann");
                } catch (IOException ex) {
                    Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                try {
                    button.setText("O");
                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    OutputStream outputStream = server.getOutputStream();
                    String playMessage = "MOVEO" + " " + player2.getText() + " " + button.getId() + " " + button.getText();
                    System.out.println("Sending move: " + playMessage);
                    outputStream.write(playMessage.getBytes());
                    sendOmove(button.getId());
                    button.setTextFill(Paint.valueOf("#ffc300"));
                    playerTurn = 0;
                } catch (IOException ex) {
                    Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).
                start();

    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        button.setStyle("-fx-background-color: #003055;");
    }

    public void onclickback(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(gameScreen.GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

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
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            playAgainWinController.setStage(stage);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(gameScreen.GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showgameover() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/gameover/gameover.fxml"));
            root = loader.load();
            GameoverController GameoverController = loader.getController();
            GameoverController.setGameController(this);
            GameoverController.setBoll(3);
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            GameoverController.setStage(stage);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(gameScreen.GameController.class.getName()).log(Level.SEVERE, null, ex);
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

                button1.setStyle("-fx-background-color: aliceblue;");
                button2.setStyle("-fx-background-color: aliceblue;");
                button3.setStyle("-fx-background-color: aliceblue;");

                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                button1.setStyle("-fx-background-color: aliceblue;");
                button2.setStyle("-fx-background-color: aliceblue;");
                button3.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b4.equals(b5) && b4.equals(b6)) {
            if (b4.equals("X")) {
                winner = playerX;
                button4.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b4.equals("O")) {
                winner = playerO;
                button4.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b7.equals(b8) && b7.equals(b9)) {
            if (b7.equals("X")) {
                winner = playerX;
                button7.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b7.equals("O")) {
                winner = playerO;
                button7.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b1.equals(b4) && b1.equals(b7)) {
            if (b1.equals("X")) {
                winner = playerX;
                button1.setStyle("-fx-background-color: aliceblue;");
                button4.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                button1.setStyle("-fx-background-color: aliceblue;");
                button4.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b2.equals(b5) && b2.equals(b8)) {
            if (b2.equals("X")) {
                winner = playerX;
                button2.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b2.equals("O")) {
                winner = playerO;
                button2.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button8.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b3.equals(b6) && b3.equals(b9)) {
            if (b3.equals("X")) {
                winner = playerX;
                button3.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b3.equals("O")) {
                winner = playerO;
                button3.setStyle("-fx-background-color: aliceblue;");
                button6.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b1.equals(b5) && b1.equals(b9)) {
            if (b1.equals("X")) {
                winner = playerX;
                button1.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b1.equals("O")) {
                winner = playerO;
                button1.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button9.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        } else if (b3.equals(b5) && b3.equals(b7)) {
            if (b3.equals("X")) {
                winner = playerX;
                button3.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            } else if (b3.equals("O")) {
                winner = playerO;
                button3.setStyle("-fx-background-color: aliceblue;");
                button5.setStyle("-fx-background-color: aliceblue;");
                button7.setStyle("-fx-background-color: aliceblue;");
                showwin();
                updateScore(winner);

            }
        }

        if (b1.isEmpty() || b2.isEmpty() || b3.isEmpty()
                || b4.isEmpty() || b5.isEmpty() || b6.isEmpty()
                || b7.isEmpty() || b8.isEmpty() || b9.isEmpty()) {
            return null;
        } else if (winner == null) {
            showgameover();
            return null;
        } else {
            return null;
        }

    }

    public void closeGameStage() {
        Stage gameStage = (Stage) apane.getScene().getWindow();
        gameStage.close();
    }

    @FXML
    private void newGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/StartScreen.fxml"));
            root = loader.load();
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TicTacToe");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void OnClickLogout(ActionEvent event) throws IOException {
        server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);

        String enteredUsername = player1.getText();
        OutputStream outputStream = server.getOutputStream();
        InputStream inputStream = server.getInputStream();
        String msg = "LOGOUT" + " " + "Habiba" + " " + "1234";
        System.out.println(msg);

        outputStream.write(msg.getBytes());
        System.out.println("feild: " + enteredUsername);
        byte[] responseBuffer = new byte[1024];
        int responseBytes = inputStream.read(responseBuffer);
        String serverResponse = new String(responseBuffer, 0, responseBytes);
        System.out.println("Server response: " + serverResponse);
        if (serverResponse.equals("LOGOUT succeed")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/login.fxml"));
            Parent onlinePlayersPage = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(onlinePlayersPage);
            stage.setScene(scene);
            stage.setTitle("TicTacToe");
            stage.show();
            System.out.println("LOGOUT  process!");
            System.out.println("LOGOUT successful!");
        }

    }

    private void sendXmove(String buttonId) {

        try {
            InputStream inputStream = server.getInputStream();
            System.out.println(getButtonById(buttonId));
            System.out.println("thisSyymol" + getButtonById(buttonId).getText());
            byte[] responseBuffer = new byte[1024];
            int responseBytes = inputStream.read(responseBuffer);
            String serverResponse = new String(responseBuffer, 0, responseBytes);
            System.out.println("Server responseeeeeee: " + serverResponse);
            StringTokenizer tokenizer = new StringTokenizer(serverResponse);
            String command = tokenizer.nextToken();
            if (command.equals("MOVEXTO")) {
                String playerName = tokenizer.nextToken();
                String buttonClicked = tokenizer.nextToken();
                String symbol = tokenizer.nextToken();
                Button clickedButton = getButtonById(buttonClicked);
                clickedButton.setText(symbol);
                setupButton(clickedButton);

                System.out.println("ClickedX" + "   " + buttonClicked);

            }

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

    }

    public void setPlayerrSymbol(Button button, String sy) {
        if (playerTurn % 2 == 0) {
            button.setText(sy);
            button.setTextFill(Paint.valueOf("#ff0000"));

            playerTurn = 1;
        } else {
            button.setText("O");
            button.setTextFill(Paint.valueOf("#ffc300"));

            playerTurn = 0;
        }
    }

    public Button getButtonById(String buttonId) {
        switch (buttonId) {
            case "button1":
                return button1;
            case "button2":
                return button2;
            case "button3":
                return button3;
            case "button4":
                return button4;
            case "button5":
                return button5;
            case "button6":
                return button6;
            case "button7":
                return button7;
            case "button8":
                return button8;
            case "button9":
                return button9;
            default:
                System.out.println("nulllllllllllllllllllllllllllll");
                return null;
        }
    }

    public void setPlayer1Name(String Player1) {
        System.out.println("Player 1" + Player1);
        this.Player11 = Player1;
        Platform.runLater(() -> player1.setText(Player1));
        if (Player11.equals("ahmed")) {
            Platform.runLater(() -> player2.setText("Habiba"));
        } else if (Player11.equals("Habiba")) {
            Platform.runLater(() -> player2.setText("ahmed"));

        }
    }

    public void setPlayer2Name() {

        if (player1.getText().equals("ahmed")) {
            Platform.runLater(() -> player2.setText("Habiba"));
        } else if (player1.getText().equals("Habiba")) {
            Platform.runLater(() -> player2.setText("ahmed"));

        }
    }

    private void sendOmove(String buttonId) {

        try {
            InputStream inputStream = server.getInputStream();
            System.out.println(getButtonById(buttonId));
            System.out.println("thisSyymol" + getButtonById(buttonId).getText());
            byte[] responseBuffer = new byte[1024];
            int responseBytes = inputStream.read(responseBuffer);
            String serverResponse = new String(responseBuffer, 0, responseBytes);

            System.out.println("Server responseeeeeee: " + serverResponse);
            StringTokenizer tokenizer = new StringTokenizer(serverResponse);

            String command = tokenizer.nextToken();
            if (command.equals("MOVEOTO")) {
                String playerName = tokenizer.nextToken();
                String buttonClicked = tokenizer.nextToken();
                String symbol = tokenizer.nextToken();

                Button clickedButton = getButtonById(buttonClicked);
                clickedButton.setText(symbol);
                setupButton(clickedButton);
                System.out.println("Clicked0" + "   " + buttonClicked);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public int setboll(int boll) {

        this.boll = boll;
        return boll;
    }
}
