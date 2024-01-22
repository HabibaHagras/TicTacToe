/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameScreen;


import java.io.BufferedReader;
import java.io.DataInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    boolean recordFlag = false;
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
    public Button record;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button newButton;
    @FXML
    private AnchorPane apane;

    private int playerTurn = 0;
    ArrayList<Button> buttons;
    public static String winner;


    int countX = 0;
    int countO = 0;
private Socket server;
    private DataInputStream reader;
    private PrintStream printStream;

    

    private int[] position = new int[9];
    private int i = 1;


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
            if (recordFlag) {
                if (button == button1) {
                    position[0] = i;
                }
                if (button == button2) {
                    position[1] = i;
                }
                if (button == button3) {
                    position[2] = i;
                }
                if (button == button4) {
                    position[3] = i;
                }
                if (button == button5) {
                    position[4] = i;
                }
                if (button == button6) {
                    position[5] = i;
                }
                if (button == button7) {
                    position[6] = i;
                }
                if (button == button8) {
                    position[7] = i;
                }
                if (button == button9) {
                    position[8] = i;
                }
                i++;
                createXMLFile();
            }
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
        button.setStyle("-fx-background-color: #003055;");
        record.setDisable(false);
        i = 1;
    }

    public void onclickback(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
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
            playAgainWinController.setBoll(true);

            playAgainWinController.setGameController(this);
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            playAgainWinController.setStage(stage);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showgameover() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/gameover/gameover.fxml"));
            root = loader.load();
            GameoverController GameoverController = loader.getController();
            GameoverController.setGameController(this);
            GameoverController.setBoll(1);
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
    public void startRecord(ActionEvent event) throws IOException {
        record.setDisable(true);
        recordFlag = true;
        createXMLFile();
    }

    private void createXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Date d = new Date();

            Element main = document.createElement("Game");

            Element p1 = document.createElement("Player1");
            p1.setTextContent(player1.getText());
            main.appendChild(p1);

            Element p2 = document.createElement("Player2");
            p2.setTextContent(this.player2.getText());
            main.appendChild(p2);

            Element date = document.createElement("Date");
            date.setTextContent(d.toString());
            main.appendChild(date);

            Element b1 = document.createElement("Button1");
            b1.setAttribute("position", String.valueOf(position[0]));
            b1.setTextContent(button1.getText());
            main.appendChild(b1);
            Element b2 = document.createElement("Button2");
            b2.setAttribute("position", String.valueOf(position[1]));
            b2.setTextContent(button2.getText());
            main.appendChild(b2);
            Element b3 = document.createElement("Button3");
            b3.setAttribute("position", String.valueOf(position[2]));
            b3.setTextContent(button3.getText());
            main.appendChild(b3);
            Element b4 = document.createElement("Button4");
            b4.setAttribute("position", String.valueOf(position[3]));
            b4.setTextContent(button4.getText());
            main.appendChild(b4);
            Element b5 = document.createElement("Button5");
            b5.setAttribute("position", String.valueOf(position[4]));
            b5.setTextContent(button5.getText());
            main.appendChild(b5);
            Element b6 = document.createElement("Button6");
            b6.setAttribute("position", String.valueOf(position[5]));
            b6.setTextContent(button6.getText());
            main.appendChild(b6);
            Element b7 = document.createElement("Button7");
            b7.setAttribute("position", String.valueOf(position[6]));
            b7.setTextContent(button7.getText());
            main.appendChild(b7);
            Element b8 = document.createElement("Button8");
            b8.setAttribute("position", String.valueOf(position[7]));
            b8.setTextContent(button8.getText());
            main.appendChild(b8);
            Element b9 = document.createElement("Button9");
            b9.setAttribute("position", String.valueOf(position[8]));
            b9.setTextContent(button9.getText());
            main.appendChild(b9);

            Element scorePalyer1 = document.createElement("ScorePalyer1");
            scorePalyer1.setTextContent(String.valueOf(countX));
            main.appendChild(scorePalyer1);

            Element scorePalyer2 = document.createElement("ScorePalyer2");
            scorePalyer2.setTextContent(String.valueOf(countO));
            main.appendChild(scorePalyer2);

            Element win = document.createElement("Winner");
            win.setTextContent(winner);
            main.appendChild(win);

            document.appendChild(main);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            String name = player1.getText() + "VS" + player2.getText() + String.valueOf(d.getDate()) + "_" + String.valueOf(d.getHours()) + String.valueOf(d.getMinutes());
            FileOutputStream output = new FileOutputStream("F:\\Java\\Java Project\\TicTacToe\\src\\Files\\" + name + ".xml");
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
            System.err.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.err.println(ex);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (TransformerException ex) {
            System.err.println(ex);
        }
    }

}
