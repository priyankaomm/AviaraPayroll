/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Userregistration;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author comp14
 */
public class UserRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Userregistration ureg=new Userregistration();
     Query query = new Query();
     
    @FXML
    private AnchorPane userreg;
    @FXML
    private Label close_btn;
    
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField conf_password;
    @FXML
    private ComboBox seq_question1;
    @FXML
    private TextField seq_ans1;
    @FXML
    private ComboBox seq_question2;
    @FXML
    private TextField seq_ans2;
    static public String uname;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
       
     private void setRegBean()
    {
        try{ 
       uname=username.getText();
       ureg.setUsername(username.getText());
       ureg.setPassword(password.getText());
       ureg.setConf_password(conf_password.getText());
       ureg.setSeq_question1(seq_question1.getSelectionModel().getSelectedItem().toString());
       ureg.setSeq_ans1(seq_ans1.getText());
       ureg.setSeq_question2(seq_question2.getSelectionModel().getSelectedItem().toString());
       ureg.setSeq_ans2(seq_ans2.getText());
       ureg.setCompony_id(0);
    }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
 
     
      @FXML
        public void saveRegAll(ActionEvent evt)
        {
            try{
            
               if(username.getText().equals("")
               ||password.getText().equals("")
               ||conf_password.getText().equals("")
               ||seq_ans1.getText().equals("")
               ||seq_ans2.getText().equals("")     
               ||seq_question1.getSelectionModel().isEmpty()
               ||seq_question2.getSelectionModel().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please fill all the Enter Login Details !!");
                alert.showAndWait();
            }
           
          
            else
            {
                setRegBean();
                if(query.saveUserregistrationInfo(ureg))
                {
                    
                    final Node source = (Node) evt.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    openComponyInfo();
                    clearReg();
                }
                else
                {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setContentText("Duplicate Username!!Please Try again!!");
                   alert.showAndWait(); 
                   username.setText("");
                }
                
            }
            }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        }
        public void clearReg()
        {
          
           username.setText("");
           password.setText("");
           conf_password.setText("");
           seq_ans1.setText("");
           seq_ans2.setText("");       
          
           seq_question1.getSelectionModel().clearSelection();
           seq_question2.getSelectionModel().clearSelection();
         
        }     
     
     @FXML
    private void close_window(MouseEvent event)  {
         // userreg.setVisible(false);
          final Node source = (Node) event.getSource();
          final Stage stage = (Stage) source.getScene().getWindow();
          stage.close();
        }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
        RotateTransition rotation=new RotateTransition(Duration.millis(500), close_btn);
        rotation.setByAngle(180);
        rotation.play();
        //System.out.println("rotate...");
    }
   private void openComponyInfo()
   {
       try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Conpany_Info.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("User Registration");
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
}