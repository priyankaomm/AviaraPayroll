/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Allowance;
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

/**
 * FXML Controller class
 *
 * @author comp11
 */
public class Add_AllowanceController implements Initializable {
    
    Allowance abean=new Allowance();
    Query query=new Query();

    @FXML
    private AnchorPane add_allow;
    @FXML
    private Label close_btn;
    @FXML
    private TextField a_name;
    @FXML
    private TextField a_type;
    @FXML
    private TextField a_value;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void close_window(MouseEvent event)  {
          add_allow.setVisible(false);
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
    public void addAllowanceInfo(ActionEvent evt)
    {
        
        abean.setAllow_name(a_name.getText());
        abean.setAllow_type(a_type.getText());
        abean.setValue(Double.parseDouble(a_value.getText()));
        query.saveAllowanceInfo(abean);
        
        System.out.println("here");
        final Node source = (Node) add_allow.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(add_allow);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/View_Allowance.fxml"));
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
