/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Leave;
import com.aviara.bean.Leave1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Add_LeaveController implements Initializable {

    Leave1 lbean=new Leave1();
    Query query=new Query();
    
    @FXML
    private AnchorPane add_leave;
    @FXML
    private Label close_btn;
    @FXML
    private ComboBox lname;
    @FXML
    private TextField l_no;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void close_window(MouseEvent event)  {
          add_leave.setVisible(false);
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
    public void addLeaveInfo(ActionEvent evt)
    {
        
        lbean.setLeave_name(lname.getSelectionModel().getSelectedItem().toString());
        lbean.setNo_of_leave(Integer.parseInt(l_no.getText()));
        if(lname.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("paid leave"))
        {
            lbean.setMonthly(1);
            lbean.setCarryForward(1);
        }
        else
        {
            lbean.setMonthly(0);
            lbean.setCarryForward(0);
        }
        query.saveLeaveInfo1(lbean);
        //openLeave();
        
        
         System.out.println("here");
        final Node source = (Node) add_leave.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(add_leave);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Leave.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
       public void openLeave(ActionEvent evt)
    {
        System.out.println("here");
        final Node source = (Node) add_leave.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(add_leave);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Leave.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    } 
}
