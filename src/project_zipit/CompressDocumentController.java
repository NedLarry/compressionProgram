/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_zipit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project_zipit.CompressionSystem;

/**
 * FXML Controller class
 *
 * @author Chinedu
 */
public class CompressDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private TextField sourcePath;
    
    @FXML
    private TextField destinationPath;
    
    @FXML
    private Button fileChooseButton;
    
    @FXML
    private Button destinationChooseButton;
    
    
    
    
    
    String fileSourcePath;
    String fileDestinationPath;
    String locationPath;
    
    CompressionSystem compressBot = new CompressionSystem();
    Alert alert = new Alert(AlertType.INFORMATION);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void mainMenu(ActionEvent evt) throws IOException
    {
        System.out.println("Going to Main menu");
        
        Parent homeParent = FXMLLoader.load(getClass().getResource("zipIt.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }
    
    @FXML
    private void backMain(ActionEvent evt) throws IOException
    {
        System.out.println("Going to compress menu");
        
        Parent homeParent = FXMLLoader.load(getClass().getResource("compressMenu.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }
    
    
    @FXML
    private String sourceFile(ActionEvent event) throws IOException {
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Chooose a file");
        
        //Stage stage = (Stage)anchorpane.getScene().getWindow();
        
        // stores the selected file in a file objec
        
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("word documents", "*.docx"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Text file", "*.txt")
        );
        
        File file = filechooser.showOpenDialog(null);
        //a check to see if a file was choosen
        if (file != null){
            
            fileSourcePath = file.getAbsolutePath();
            
            //this prints the absolute path of the file.
            //System.out.println(file.getAbsolutePath());
            
            sourcePath.setText(fileSourcePath);
        }   
        
        //System.out.println(fileSourcePath);
        return fileSourcePath;
    }
    
    
    @FXML
    private String destinationFile(ActionEvent event) throws IOException {
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Chooose a file");
        
        //Stage stage = (Stage)anchorpane.getScene().getWindow();
        
        // stores the selected file in a file objec
        
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("word documents", "*.docx"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Text file", "*.txt")
        );
        
        File file = filechooser.showSaveDialog(null);
        //a check to see if a file was choosen
        if (file != null){
            
            
            fileDestinationPath = file.getAbsolutePath();
            locationPath = file.getParent();
            
            //this prints the absolute path of the file.
            System.out.println(file.getAbsolutePath());
            
            destinationPath.setText(fileDestinationPath);
        }   
        
        return fileDestinationPath;
    }
    
   
    @FXML
    private void documentCompress(ActionEvent event) throws IOException
    {
        
        if ((sourcePath.getText().isEmpty()) && (destinationPath.getText().isEmpty()))
        {
            
            alert.setTitle("Information");
            alert.setContentText("You have not selected a file or Destination path");
            alert.showAndWait();
            
            sourcePath.setPromptText("Select a file");
            destinationPath.setPromptText("Destination path not set");
            
            fileChooseButton.requestFocus();
            
            
        }
        else if (sourcePath.getText().isEmpty())
        {
            
            alert.setTitle("Information");
            alert.setContentText("You have to choose a document to compress");
            alert.showAndWait();
            
            sourcePath.setPromptText("Select file");
            fileChooseButton.requestFocus();
            
            
        }else if (destinationPath.getText().isEmpty())
        {
            alert.setTitle("Information");
            alert.setContentText("Choose a path to save the compressed document");
            alert.showAndWait();
            
            destinationPath.setPromptText("Destination path not set");
            destinationChooseButton.requestFocus();
            
        }else{
            
            File source = new File(sourcePath.getText());
            File destination = new File (destinationPath.getText());
        
            try{
            
                compressBot.compressTest(source, destination);
                compressBot.openTargetLocation(locationPath);
                System.out.println("Document compressed!");
                mainMenu(event);
            
            
            }catch(IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        
        
        
        
    }
    
    
}
