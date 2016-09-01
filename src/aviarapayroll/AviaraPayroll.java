/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviarapayroll;

import java.io.IOException;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author comp2
 */
public class AviaraPayroll extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //int bid= Integer.parseInt(builder_id.getText());
            /*((Pane)root1.lookup("#c_menu")).setVisible(false);
            ((Pane)root1.lookup("#e_menu")).setVisible(false);
            ((Pane)root1.lookup("#r_menu")).setVisible(false);*/
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            stage.setScene(new Scene(root1));  
            stage.show();
            
          }
         catch (Exception e) {
             e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error");
           //alert.setHeaderText("Look, an Information Dialog");
           alert.setContentText(e.getMessage());
           alert.showAndWait();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    
    
}
