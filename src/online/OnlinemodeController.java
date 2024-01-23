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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.BreakNode;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import onlineUserScrren.OnlineUserController;
import tictactoe.LoginController;
import static tictactoe.LoginController.inputStream;
import static tictactoe.LoginController.outputStream;
import static tictactoe.LoginController.server;
import static tictactoe.LoginController.thread;
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
    private ImageView backBtn;
    @FXML
    private Button newButton;

    private int playerTurn = 0;
    ArrayList<Button> buttons;
    public static String winner;

    int countX = 0;
    int countO = 0;
    @FXML
    private Text player1;
    @FXML
    private Text player2;
    Socket server;

    @FXML
    private Button logout;
Button button;
    String symbol;
    String Player11;
    String Player22;
    InputStream inputStream;
  OutputStream outputStream;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  LoginController.thread.stop();
        
            buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
            
            buttons.forEach(button -> {
                setupButton(button);
                
            });
            
        try {
            //            try {
            server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
            outputStream= server.getOutputStream();
            inputStream = server.getInputStream();
        } catch (UnknownHostException ex) {
            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
////            byte[] responseBuffer = new byte[1024];
////            int responseBytes = inputStream.read(responseBuffer);
////            String serverResponse = new String(responseBuffer, 0, responseBytes);
////            System.out.println("client response: " + serverResponse);
////            
////            StringTokenizer tokenizer = new StringTokenizer(serverResponse);
////            String command = tokenizer.nextToken();
////            if(command.equals("twoPlayers")){
////                System.out.println("playyyyy");
////                
////            }
////            else if(command.equals("response")){
////                System.out.println("response");
////            }
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        thread= new Thread(() -> {
            System.out.println("starttttttttt");
            try {
                server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                outputStream = server.getOutputStream();
                inputStream = server.getInputStream();

                


                    while (true) {
                       
                        
                byte[] responseBuffer = new byte[1024];
                int responseBytes = inputStream.read(responseBuffer);
                 if (responseBytes > 0) {
                String serverResponse = new String(responseBuffer, 0, responseBytes);
                System.out.println("Server responsenm,nkjk: " + serverResponse);

               
//                        StringTokenizer tokenizer = new StringTokenizer(onlineUser);
//
//                        String command = tokenizer.nextToken();
 StringTokenizer tokenizer = new StringTokenizer(serverResponse);

                    String command = tokenizer.nextToken();
                        System.out.println("command"+command);

// Assuming the second token is the username
                    //   String username = tokenizer.nextToken();
// Assuming the third token is the additional information (e.g., "111")
                    //String additionalInfo = tokenizer.nextToken();
                    
                    if (command.equals("MOVE")) {
                    String playerName = tokenizer.nextToken();
                    String buttonClicked = tokenizer.nextToken()+"p";
                    String symbol = tokenizer.nextToken();
                    
                    String [] id=buttonClicked.split("=");
                String [] id1=id[1].split(",");
                String [] strSymbol=symbol.split("'");
                String [] strSymbol1=strSymbol[1].split("'");


                    Platform.runLater(() -> {
                        
                        Button clickedButton = getButtonById(id1[0]);

            
                        //Button clickedButton = getButtonById(buttonClicked);
                        System.out.println("buttonClicked "+buttonClicked);
                        System.out.println("clickedButton"+clickedButton);
                        System.out.println("symbol "+symbol);
                        clickedButton.setText(strSymbol1[0]);
                        System.out.println("Clicked" + "   " + buttonClicked);
                       
                        // return true;
                  // setPlayerSymbol(clickedButton);
                         //button.setText(command);
                    });
                            }

//                        String usernamee = tokenizer.nextToken();
                        //   String additionalInfo = tokenizer.nextToken();
                       
                       
//                        if(command.equals("MOVE")){ 
//                                    Platform.runLater(()  -> { 
////                           Platform.runLater(new Runnable(){
////                    
////                public void run(){
//                     //  while(true){
//                            String player = tokenizer.nextToken();
//                            String receivedButtonId = tokenizer.nextToken()+"l";
//
//                               String receivedButtonSymbol = tokenizer.nextToken();
//
//        System.out.println(" num of button"+receivedButtonId);
////        String [] id=receivedButtonId.split("=");
////                String [] id1=id[1].split(",");
//                        Button receivedButton = getButtonById(id1[0]);
//
//            
//        setPlayerSymbol(receivedButton);
//                            System.out.println(receivedButtonId);
////       //Platform.runLater(() -> onlineGameController.sendXmove(button));
//        System.out.println("moooooove");
                       
                     // }
//        });
//        //receivedButton.setText(receivedButtonText);
//    
////                            Platform.runLater(() -> {
////                            onlineGameController.setPlayerSymbol(button);
////                 });
//                        }
                 }
                        
                    }

                  //  System.out.println("Login successful!");
                } catch (UnknownHostException ex) { 
                     ex.printStackTrace();
            } catch (IOException ex) {
                  ex.printStackTrace();            } 
             
        });
       thread.start();
            

    }
  

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
           
          
          
               setPlayerSymbol(button);
               
       
       //  button.setText("X");
         
                     String playMessage = "MOVE"+" " + player1.getText() + " " + button+" "+(button).getText();
                 //   String playMessage = "MOVE " + button.getId();

                    System.out.println("Sending move: " + playMessage);

            try {
                outputStream.write(playMessage.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
              
            button.setDisable(true);
            checkWinner(player1.getText(), player2.getText());
           // updateBoardAfterRequest();
        });
    }
     public void setPlayerSymbol(Button button) {
     //  new Thread(()->{
//         while(true){
// Platform.runLater(new Runnable(){
////                    
//          public void run(){
//Platform.runLater(() -> {
    if (playerTurn % 2 == 0) {
       // Platform.runLater(() -> {
            button.setText("X");
            button.setTextFill(Paint.valueOf("#ff0000"));
          //  });  
       // sendXmove(button.getId());
        playerTurn = 1;
    } else {
                //    Platform.runLater(() -> {
            button.setText("O");
            button.setTextFill(Paint.valueOf("#ffc300"));
                // sendXmove(button.getId());  
       // });  
        playerTurn = 0;
    }
//     }
//        });
//    sendXmove(button.getId());
//     playerTurn = (playerTurn + 1) % 2;
//         //}
//          }
//         });   
//       }).start();
}

//      public void updateBoardAfterRequest() {
//          new Thread(() -> {
//        try {
//              
//             server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
//             outputStream= server.getOutputStream();
//             inputStream = server.getInputStream();
//              byte[] responseBuffer = new byte[1024];
//                int responseBytes = inputStream.read(responseBuffer);
//                String serverResponse = new String(responseBuffer, 0, responseBytes);
//                System.out.println("client response: " + serverResponse);
//                String str="twoPlayers"+" "+"yomna"+" "+"rawan";
//            outputStream.write(str.getBytes());
//          //  outputStream.flush();
//           StringTokenizer tokenizer = new StringTokenizer(serverResponse);
//          String command = tokenizer.nextToken();
//          
//        } catch (IOException ex) {
//            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }).
//                start();
//    }
     

  
    

    /*
    public void setPlayerSymbol(Button button) {
        try {
            server = new Socket("127.0.0.1", 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());
            if (playerTurn % 2 == 0) {
                String msg = "x";
                mouth.println(msg);

                button.setTextFill(Paint.valueOf("#ff0000"));
                playerTurn = 1;

            } else {
                String msg = "O";
                mouth.println(msg);
                button.setTextFill(Paint.valueOf("#ffc300"));

                playerTurn = 0;

            }
            new Thread() {
                public void run() {

                    while (true) {
                        String msg;
                        //String msgo;

                        try {
                            msg = ear.readLine();
                            button.setText(msg);

                        } catch (IOException ex) {
                            // Logger.getLogger(BorderChat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

            }.start();

        } catch (IOException ex) {
            Logger.getLogger(OnlinemodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
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
//            playAgainWinController.setWinner(winner);
//            playAgainWinController.setGameController(this);
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
//            GameoverController GameoverController = loader.getController();
//            GameoverController.setGameController(this);
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
//            GameoverController.setStage(stage);
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
    private void back(MouseEvent event) {
    }

    @FXML
    private void newGame(ActionEvent event) {
    }

    @FXML
    private void OnClickLogout(ActionEvent event) throws IOException {
       // server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);

        String enteredUsername = player1.getText();
//        OutputStream outputStream = server.getOutputStream();
//        InputStream inputStream = server.getInputStream();
        String msg = "LOGOUT" + " " + "Habiba" + " " + "1234"+" "+"678";
        System.out.println(msg);

        outputStream.write(msg.getBytes());
        System.out.println("feild: " + enteredUsername);
        byte[] responseBuffer = new byte[1024];
        int responseBytes = inputStream.read(responseBuffer);
        String serverResponse = new String(responseBuffer, 0, responseBytes);
       // System.out.println("Server response: " + serverResponse);
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

    public void sendXmove(String button) {
                 //  new Thread(()->{
                      // while(true){
                     
           
           if (button != null) {
                       System.out.println("ooooooooo");

    if (server != null && server.isConnected()) {

                try {
                    
                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    InputStream inputStream = server.getInputStream();
                    OutputStream outputStream = server.getOutputStream();
                   
                     System.out.println("Sending move: ");


                     String playMessage = "MOVE"+" " + player1.getText() + " " + button+" "+getButtonById(button).getText();
                 //   String playMessage = "MOVE " + button.getId();

                    System.out.println("Sending move: " + playMessage);

                    outputStream.write(playMessage.getBytes());

                    // Assuming the server responds with the updated board state
                    
                    byte[] responseBuffer = new byte[1024];
                    
                    int responseBytes = inputStream.read(responseBuffer);
                    String serverResponse = new String(responseBuffer, 0, responseBytes);

                    System.out.println("Server responseeeeeee: " + serverResponse);
                    StringTokenizer tokenizer = new StringTokenizer(serverResponse);

                    String command = tokenizer.nextToken();
                    

// Assuming the second token is the username
                    //   String username = tokenizer.nextToken();
// Assuming the third token is the additional information (e.g., "111")
                    //String additionalInfo = tokenizer.nextToken();
//                    
//                    if (command.equals("MOVE")) {
//                    String playerName = tokenizer.nextToken();
//                    String buttonClicked = tokenizer.nextToken();
//                    String symbol = tokenizer.nextToken();
//
//                    Platform.runLater(() -> {
//                        Button clickedButton = getButtonById(buttonClicked);
//                        clickedButton.setText(symbol);
//                        System.out.println("Clicked" + "   " + buttonClicked);
//                        
//                        // return true;
//                   setPlayerSymbol(clickedButton);
//                         //button.setText(command);
//                    });
//                            }

                } catch (IOException ex) {
                    ex.printStackTrace(); // Handle the exception appropriately
                }
            
           }
           }
          
                 //  }
                //   });
               //  }).start();

    }

//    public void setSymol(String symbol) {
//        System.out.println("setSymol" + symbol);
//        this.symbol = symbol;
//    }
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
            // Add cases for other buttons
            default:
                System.out.println("nulllllllllllllllllllllllllllll");
                return null;
        }

    }
    public void setPlayer1Name(String Player1) {
        System.out.println("Player 1" + Player1);
        this.Player11 = Player1;
    Platform.runLater(() -> player1.setText( Player1));
    }

    public void setPlayer2Name(String Player2) {
        System.out.println( Player2);
        this.Player22 = Player2;
    Platform.runLater(() -> player2.setText( Player2));
    }

}
