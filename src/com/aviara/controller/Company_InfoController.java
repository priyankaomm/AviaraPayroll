/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Branch;
import com.aviara.bean.Company_Info;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author comp11
 */
public class Company_InfoController implements Initializable {

    @FXML
    private AnchorPane com_info;
    @FXML
    private Label close_btn;
    @FXML
    private Label name;
    @FXML
    private Label reg_no;
    
    @FXML
    private Label pan;
    @FXML
    private Label esi;
    @FXML
    private Label pf;
    @FXML
    private Label id;
    @FXML
    private Label addrss;
    @FXML
    private Label zip;
    @FXML
    private Label ph_no1;
    @FXML
    private Label ph_no2;
    @FXML
    private Label email_id;
    
    Query query = new Query();
    private ObservableList data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCompanyInfo();
        id.setVisible(false);
        
    }    
    
    @FXML
    private void close_window(MouseEvent event)  {
          com_info.setVisible(false);
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
       public void openAddCompanyInfo(ActionEvent evt)
    {
        System.out.println("here");
        final Node source = (Node) com_info.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(com_info);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Conpany_Info.fxml"));
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
       /*
       @FXML
       public void openEditCompanyInfo(ActionEvent evt)
    {
        System.out.println("here");
        final Node source = (Node) com_info.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(com_info);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Conpany_Info.fxml"));
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
       */
       @FXML
       public void showCompanyInfo()
       {
          try 
           {
               List<Company_Info> q=query.fetchCompany_Info();
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Company_Info user = (Company_Info) q.get(i);
                     name.setText(user.getCompany_name());
                     reg_no.setText(user.getReg_no());
                     pan.setText(user.getPan_no());
                     esi.setText(user.getEsi_no());
                     pf.setText(user.getPf_no());
                     addrss.setText(user.getAddress1());
                     zip.setText(String.valueOf(user.getZipcode()));
                     ph_no1.setText(String.valueOf(user.getContact_no1()));
                     ph_no2.setText(String.valueOf(user.getContact_no2()));
                     email_id.setText(user.getEmail());
                     id.setText(String.valueOf(user.getCompany_id()));
           }
           }
          catch(Exception ex)
           {
             ex.printStackTrace();  
           }
       
       }
       
}
