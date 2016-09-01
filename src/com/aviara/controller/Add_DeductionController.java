/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Deduction;
import com.aviara.bean.Deduction1;
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
public class Add_DeductionController implements Initializable {
    
    
    Deduction1 dbean=new Deduction1();
    Query query=new Query();
    
    @FXML
    private TextField de_name;
    @FXML
    private TextField de_percent;
    @FXML
    private TextField emp_contri;
    @FXML
    private TextField com_contri;
    @FXML
    private AnchorPane add_deduction;
     @FXML
    private Label close_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void close_window(MouseEvent event)  {
          add_deduction.setVisible(false);
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
    public void addDeductionInfo(ActionEvent evt)
    {
        
        dbean.setDeductio_name(de_name.getText());
        dbean.setDeduction_percent(Double.parseDouble(de_percent.getText()));
        query.saveDeductionInfo1(dbean);
        
        System.out.println("here");
        final Node source = (Node) add_deduction.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(add_deduction);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Deduction.fxml"));
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
