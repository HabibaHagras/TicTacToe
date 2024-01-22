/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ListOfRecords;

import RecordGame.RecordBoardController;
import gameScreen.GameController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RecordsController implements Initializable {

    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    ObservableList list = FXCollections.observableArrayList();
    AnchorPane ancPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text txtRecords;
    @FXML
    private ListView<?> recordList;
    @FXML
    private ImageView backBtn;
    
    private String p1 = null;
    private String p2 = null;
    private String textB1 = null;
    private String textB2 = null;
    private String textB3 = null;
    private String textB4 = null;
    private String textB5 = null;
    private String textB6 = null;
    private String textB7 = null;
    private String textB8 = null;
    private String textB9 = null;
    private String scoreP1 = null;
    private String scoreP2 = null;
    
    private String positionB1 = null;
    private String positionB2 = null;
    private String positionB3 = null;
    private String positionB4 = null;
    private String positionB5 = null;
    private String positionB6 = null;
    private String positionB7 = null;
    private String positionB8 = null;
    private String positionB9 = null;
    
    
    private String win = null;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }

    

    @FXML
    public void navigateBack(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

    @FXML
    private void displayBoard(MouseEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/RecordGame/RecordBoard.fxml"));
            root =loader.load();
            RecordBoardController controller= loader.getController();
            String record = recordList.getSelectionModel().getSelectedItem().toString();
            loadFile(record);
            controller.getData(p1,p2,textB1,positionB1,textB2,positionB2,textB3,positionB3,
                    textB4,positionB4,textB5,positionB5,textB6,positionB6,textB7,positionB7,
                    textB8,positionB8,textB9,positionB9,scoreP1,scoreP2,win);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    
    private void loadFile(String name){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream  stream = new FileInputStream("F:\\Java\\Java Project\\TicTacToe\\src\\Files\\"+name);
            Document document = builder.parse(stream);
            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            NodeList nList = document.getElementsByTagName("Game");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                org.w3c.dom.Node nNode = nList.item(temp);
                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    p1 = eElement.getElementsByTagName("Player1").item(0).getTextContent();
                    System.out.println("Player1: " + eElement.getElementsByTagName("Player1").item(0).getTextContent());
                    p2 = eElement.getElementsByTagName("Player2").item(0).getTextContent();
                    System.out.println("Player2: " + eElement.getElementsByTagName("Player2").item(0).getTextContent());
                    System.out.println("Date: " + eElement.getElementsByTagName("Date").item(0).getTextContent());
                    scoreP1 = eElement.getElementsByTagName("ScorePalyer1").item(0).getTextContent();
                    System.out.println("ScorePalyer1: " + eElement.getElementsByTagName("ScorePalyer1").item(0).getTextContent());
                    scoreP2 = eElement.getElementsByTagName("ScorePalyer2").item(0).getTextContent();
                    System.out.println("ScorePalyer2: " + eElement.getElementsByTagName("ScorePalyer2").item(0).getTextContent());
                    win = eElement.getElementsByTagName("Winner").item(0).getTextContent();
                    System.out.println("Winner: " + eElement.getElementsByTagName("Winner").item(0).getTextContent());
                }
            }
            
            NodeList buttonList1 = document.getElementsByTagName("Button1");
            for (int temp = 0; temp < buttonList1.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList1.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB1 = element.getTextContent();
                    positionB1 = element.getAttribute("position");
                    System.out.println(textB1 + " : "+positionB1);
                }
            }
            
            NodeList buttonList2 = document.getElementsByTagName("Button2");
            for (int temp = 0; temp < buttonList2.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList2.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB2 = element.getTextContent();
                    positionB2 = element.getAttribute("position");
                    System.out.println(textB2 + " : "+positionB2);
                }
            }
            
            NodeList buttonList3 = document.getElementsByTagName("Button3");
            for (int temp = 0; temp < buttonList3.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList3.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB3 = element.getTextContent();
                    positionB3 = element.getAttribute("position");
                    System.out.println(textB3 + " : "+positionB3);
                }
            }
            
            NodeList buttonList4 = document.getElementsByTagName("Button4");
            for (int temp = 0; temp < buttonList4.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList4.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB4 = element.getTextContent();
                    positionB4 = element.getAttribute("position");
                    System.out.println(textB4 + " : "+positionB4);
                }
            }
            
            NodeList buttonList5 = document.getElementsByTagName("Button5");
            for (int temp = 0; temp < buttonList5.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList5.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB5 = element.getTextContent();
                    positionB5 = element.getAttribute("position");
                    System.out.println(textB5 + " : "+positionB5);
                }
            }
            
            NodeList buttonList6 = document.getElementsByTagName("Button6");
            for (int temp = 0; temp < buttonList6.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList6.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB6 = element.getTextContent();
                    positionB6 = element.getAttribute("position");
                    System.out.println(textB6 + " : "+positionB6);
                }
            }
            
            NodeList buttonList7 = document.getElementsByTagName("Button7");
            for (int temp = 0; temp < buttonList7.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList7.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB7 = element.getTextContent();
                    positionB7 = element.getAttribute("position");
                    System.out.println(textB7 + " : "+positionB7);
                }
            }
            
            NodeList buttonList8 = document.getElementsByTagName("Button8");
            for (int temp = 0; temp < buttonList8.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList8.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB8 = element.getTextContent();
                    positionB8 = element.getAttribute("position");
                    System.out.println(textB8 + " : "+positionB8);
                }
            }
            
            NodeList buttonList9 = document.getElementsByTagName("Button9");
            for (int temp = 0; temp < buttonList9.getLength(); temp++) {
                org.w3c.dom.Node node =  buttonList9.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textB9 = element.getTextContent();
                    positionB9 = element.getAttribute("position");
                    System.out.println(textB9 + " : "+positionB9);
                }
            }
            
            
        } catch (ParserConfigurationException ex) {
            System.err.println(ex);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (SAXException ex) {
           System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private void loadData() {
        list.removeAll(list);
        String[] pathnames;
        File file = new File("F:\\Java\\Java Project\\TicTacToe\\src\\Files");
        pathnames = file.list();
        for (String pathname : pathnames) {
            list.add(pathname);
        }
        recordList.getItems().addAll(list);
    }
}
