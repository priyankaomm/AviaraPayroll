/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Address;
import com.aviara.bean.Userregistration;
import com.aviara.bean.loginbean;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    loginbean lbean=new loginbean();
    Query query = new Query();
    
    @FXML
    private AnchorPane login;
    @FXML
    private TextField username;
     @FXML
    private PasswordField Password;
     @FXML
    private ComboBox cname;
     @FXML
    private ComboBox fyear;
      @FXML
    private Label close_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getComponyInfo();
    }    
     @FXML
    public void savelogin(ActionEvent evt)
    {
        
        
        //ebean.setExp(exp.getSelectionModel().getSelectedItem().toString());
        //query.saveEmployeeInfo(lbean);
        //query.saveEmployeeloginbean(lbean);
        
    }
     private void setloginBean()
    {
        try{
       // lbean.setEmp_id(emp_id.getText());
        lbean.setUsername(username.getText());
        //empid=emp_id.getText();
        lbean.setPassword(Password.getText());
        
      
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void close_window(MouseEvent event)  {
          //login.setVisible(false);
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
    @FXML
    private void loginAdmin(ActionEvent event) {
            
        
          try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/AdminDashboard.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            ((Pane)root1.lookup("#c_menu")).setVisible(false);
            ((Pane)root1.lookup("#e_menu")).setVisible(false);
            ((Pane)root1.lookup("#r_menu")).setVisible(false);
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
    @FXML
    private void SignUp(ActionEvent event) {
            
          try{
            final Node source = (Node) event.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/UserRegistration.fxml"));
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
    private void getComponyInfo()
    {
          List<Map> q=query.getAllComponies();
          for (int i = 0; i < q.size(); i++) 
          {
              cname.getItems().add(q.get(i).get("company_name"));
              fyear.getItems().add(q.get(i).get("fyear")+"-"+q.get(i).get("tyear"));
          }
    }
    @FXML
    private void getAllUser(ActionEvent evt)
    {
        Userregistration ubean=new Userregistration();
        ubean.setUsername(username.getText());
        ubean.setPassword(Password.getText());
        ubean.setCompony_id(query.getComponyIdByName(cname.getSelectionModel().getSelectedItem().toString()));
         
        List<Map> q=query.getAllUser(ubean);
        if(q.isEmpty())
        {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setContentText("Invalid username or password!!");
           alert.showAndWait(); 
        }
        else
        {
            final Node source = (Node) evt.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            loginAdmin(evt);
        }
    }
}