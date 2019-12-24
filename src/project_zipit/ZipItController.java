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
 *
 * @author Chinedu
 */
public class ZipItController implements Initializable {
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
    /*
    This method opens a new window for the compress menu
    */
    @FXML
    private void compressMenu (ActionEvent evt) throws IOException
    {
        System.out.println("Switching to compress menu");
        
        Parent homeStageParent = FXMLLoader.load(getClass().getResource("compressMenu.fxml"));
        Scene homeScene = new Scene(homeStageParent);
        Stage appStage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        appStage.setScene(homeScene);
        appStage.show();
        
    }
    
    /*
    This method opens a new window for the decompress menu
    */
    @FXML
    private void decompressMenu (ActionEvent evt) throws IOException
    {
        System.out.println("Switching to decompress menu");
        
        Parent homeStageParent = FXMLLoader.load(getClass().getResource("decompressMenu.fxml"));
        Scene homeScene = new Scene(homeStageParent);
        Stage appStage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        appStage.setScene(homeScene);
        appStage.show();
        
    }
    
}
