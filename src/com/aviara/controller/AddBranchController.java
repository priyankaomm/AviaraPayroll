/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Branch;
import com.aviara.bean.Company_Info;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
public class AddBranchController implements Initializable {

    Branch bbean=new Branch();
    Query query=new Query();
    Company_Info cbean=new Company_Info();
    @FXML
    private AnchorPane brnch_add;
    @FXML
    private Label close_btn;
    @FXML
    private TextField bname;
    @FXML
    private TextField b_add;
    @FXML
    private TextField b_city;
    @FXML
    private TextField b_state;
    @FXML
    private TextField b_zip;
    @FXML
    private TextField b_country;
    @FXML
    private TextField b_contact1;
    @FXML
    private TextField b_contact2;
    @FXML
    private TextField b_email;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void close_window(MouseEvent event)  {
          brnch_add.setVisible(false);
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
    public void addBranchInfo(ActionEvent evt)
    {
        
        bbean.setBranch_name(bname.getText());
        bbean.setBranch_address(b_add.getText());
        bbean.setBranch_city(b_city.getText());
        bbean.setBranch_state(b_state.getText());
        bbean.setBranch_zip(Integer.parseInt(b_zip.getText()));
        bbean.setBranch_country(b_country.getText());
        bbean.setBranch_contact1(Integer.parseInt(b_contact1.getText()));
        bbean.setBranch_contact2(Integer.parseInt(b_contact2.getText()));
        bbean.setBranch_email((b_email.getText()));
        bbean.setCompany_id(cbean.getCompany_id());
        query.saveBranchInfo(bbean);
        
        System.out.println("here");
        final Node source = (Node) brnch_add.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false); 
        main_pane.getChildren().removeAll(brnch_add);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/View_Branch.fxml"));
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
