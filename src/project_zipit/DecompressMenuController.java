/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_zipit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chinedu
 */
public class DecompressMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void documentMenuDecompress(ActionEvent evt) throws IOException
    {
        System.out.println("Decompress Document option selected");
        
        Parent homeParent = FXMLLoader.load(getClass().getResource("DecompressDocument.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }
    
    
    @FXML
    private void imageMenuDecompress(ActionEvent evt) throws IOException
    {
        System.out.println("Decompress Image option selected");
        
        Parent homeParent = FXMLLoader.load(getClass().getResource("decompressImage.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }
    
    @FXML
    private void archiveMenuDecompress(ActionEvent evt) throws IOException
    {
        System.out.println("Create Archive option selected");
        
        Parent homeParent = FXMLLoader.load(getClass().getResource("extractArchive.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
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
    
}
