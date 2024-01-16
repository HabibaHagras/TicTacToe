/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecordGame;

import gameScreen.GameController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class RecordBoardController implements Initializable {

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
    private Button againButton;
    ArrayList<Button> buttons;
    ArrayList<String> b;
    Timeline timeline;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    String positionB1, positionB2, positionB3, positionB4, positionB5, positionB6, positionB7, positionB8, positionB9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        b = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9));
    }

    public void getData(String player1, String player2, String textB1, String positionB1, String textB2, String positionB2,
            String textB3, String positionB3, String textB4, String positionB4, String textB5, String positionB5, String textB6,
            String positionB6, String textB7, String positionB7, String textB8, String positionB8,
            String textB9, String positionB9, String scoreP1, String scoreP2, String winner) {

        b1 = textB1;
        b2 = textB2;
        b3 = textB3;
        b4 = textB4;
        b5 = textB5;
        b6 = textB6;
        b7 = textB7;
        b8 = textB8;
        b9 = textB9;
        this.positionB1 = positionB1;
        this.positionB2 = positionB2;
        this.positionB3 = positionB3;
        this.positionB4 = positionB4;
        this.positionB5 = positionB5;
        this.positionB6 = positionB6;
        this.positionB7 = positionB7;
        this.positionB8 = positionB8;
        this.positionB9 = positionB9;

        this.player1.setText(player1);
        this.player2.setText(player2);
//        if ("X".equals(textB1)) {
//            button1.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB1)) {
//            button1.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button1.setText(textB1);
//
//        if ("X".equals(textB2)) {
//            button2.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB2)) {
//            button2.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button2.setText(textB2);
//
//        if ("X".equals(textB3)) {
//            button3.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB3)) {
//            button3.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button3.setText(textB3);
//
//        if ("X".equals(textB4)) {
//            button4.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB4)) {
//            button4.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button4.setText(textB4);
//
//        if ("X".equals(textB5)) {
//            button5.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB5)) {
//            button5.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button5.setText(textB5);
//
//        if ("X".equals(textB6)) {
//            button6.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB6)) {
//            button6.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button6.setText(textB6);
//
//        if ("X".equals(textB7)) {
//            button7.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB7)) {
//            button7.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button7.setText(textB7);
//
//        if ("X".equals(textB8)) {
//            button8.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB8)) {
//            button8.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button8.setText(textB8);
//
//        if ("X".equals(textB9)) {
//            button9.setTextFill(Paint.valueOf("#ff0000"));
//        } else if ("O".equals(textB9)) {
//            button9.setTextFill(Paint.valueOf("#ffc300"));
//        }
//        button9.setText(textB9);
//
        score1.setText(scoreP1);
        score2.setText(scoreP2);
//        
//        highLight();
    }

    void highLight() {
        if (button1.getText().equals(button2.getText()) && button1.getText().equals(button3.getText())) {
            button1.setStyle("-fx-background-color: aliceblue;");
            button2.setStyle("-fx-background-color: aliceblue;");
            button3.setStyle("-fx-background-color: aliceblue;");

        } else if (button4.getText().equals(button5.getText()) && button4.getText().equals(button6.getText())) {
            button4.setStyle("-fx-background-color: aliceblue;");
            button5.setStyle("-fx-background-color: aliceblue;");
            button6.setStyle("-fx-background-color: aliceblue;");

        } else if (button7.getText().equals(button8.getText()) && button7.getText().equals(button9.getText())) {
            button7.setStyle("-fx-background-color: aliceblue;");
            button8.setStyle("-fx-background-color: aliceblue;");
            button9.setStyle("-fx-background-color: aliceblue;");

        } else if (button1.getText().equals(button4.getText()) && button1.getText().equals(button7.getText())) {
            button1.setStyle("-fx-background-color: aliceblue;");
            button4.setStyle("-fx-background-color: aliceblue;");
            button7.setStyle("-fx-background-color: aliceblue;");

        } else if (button2.getText().equals(button5.getText()) && button2.getText().equals(button8.getText())) {
            button2.setStyle("-fx-background-color: aliceblue;");
            button5.setStyle("-fx-background-color: aliceblue;");
            button8.setStyle("-fx-background-color: aliceblue;");

        } else if (button3.getText().equals(button6.getText()) && button3.getText().equals(button9.getText())) {
            button3.setStyle("-fx-background-color: aliceblue;");
            button6.setStyle("-fx-background-color: aliceblue;");
            button9.setStyle("-fx-background-color: aliceblue;");

        } else if (button1.getText().equals(button5.getText()) && button1.getText().equals(button9.getText())) {
            button1.setStyle("-fx-background-color: aliceblue;");
            button5.setStyle("-fx-background-color: aliceblue;");
            button9.setStyle("-fx-background-color: aliceblue;");

        } else if (button3.getText().equals(button5.getText()) && button3.getText().equals(button7.getText())) {
            button3.setStyle("-fx-background-color: aliceblue;");
            button5.setStyle("-fx-background-color: aliceblue;");
            button7.setStyle("-fx-background-color: aliceblue;");

        }
    }

    @FXML
    private void navigateBack(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/tictactoe/ListOfRecords/Records.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

    void draw() {
        Duration duration = Duration.seconds(1);
        for (int i = 1; i < 10; i++) {
            if (b1 != null && positionB1.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b1)) {
                        button1.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b1)) {
                        button1.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button1.setText(b1);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b2 != null && positionB2.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b2)) {
                        button2.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b2)) {
                        button2.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button2.setText(b2);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b3 != null && positionB3.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b3)) {
                        button3.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b3)) {
                        button3.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button3.setText(b3);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b4 != null && positionB4.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b4)) {
                        button4.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b4)) {
                        button4.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button4.setText(b4);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b5 != null && positionB5.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b5)) {
                        button5.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b5)) {
                        button5.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button5.setText(b5);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b6 != null && positionB6.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b6)) {
                        button6.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b6)) {
                        button6.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button6.setText(b6);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b7 != null && positionB7.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b7)) {
                        button7.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b7)) {
                        button7.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button7.setText(b7);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b8 != null && positionB8.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b8)) {
                        button8.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b8)) {
                        button8.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button8.setText(b8);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }

            if (b9 != null && positionB9.equals(String.valueOf(i))) {
                timeline = new Timeline(new KeyFrame(duration, event -> {
                    if ("X".equals(b9)) {
                        button9.setTextFill(Paint.valueOf("#ff0000"));
                    } else if ("O".equals(b9)) {
                        button9.setTextFill(Paint.valueOf("#ffc300"));
                    }
                    button9.setText(b9);
                }));
                timeline.playFromStart();
                duration = duration.add(Duration.seconds(1));
            }
        }

        timeline = new Timeline(new KeyFrame(duration, event -> {
            againButton.setDisable(false);
            highLight();
        }));
        timeline.playFromStart();

        againButton.setText("Paly Again");

    }

    @FXML
    public void playRecord(ActionEvent event) throws IOException {
        clearButton();
        againButton.setDisable(true);
        draw();
    }

    void clearButton() {
        buttons.forEach((button) -> {
            button.setText(" ");
            button.setStyle("-fx-background-color: transparent;");
        });
    }
}
