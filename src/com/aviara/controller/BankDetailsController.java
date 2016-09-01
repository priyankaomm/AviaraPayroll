/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Bank;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class BankDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Query query=new Query();
    @FXML
    private Label e_id;
    @FXML
    private TextField bank_name;
    @FXML
    private TextField acc_no;
    @FXML
    private TextField ifsc_code;
    @FXML
    private TextField pf_no;
    @FXML
    private TextField pan_no;
    @FXML
    private TextField voter_id;
    @FXML
    private TextField adhar_no;
    @FXML
    private AnchorPane employee;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        e_id.setText(Emp_deductionController.eid);
    }    
    @FXML
    private void saveBankDetails(ActionEvent evt)
    {
        Bank bean=new Bank();
        bean.setEmp_id(e_id.getText());
        bean.setBank_name(bank_name.getText());
        bean.setAcc_no(acc_no.getText());
        bean.setIfsc_code(ifsc_code.getText());
        bean.setPf_no(pf_no.getText());
        bean.setPan_no(pan_no.getText());
        bean.setVoter_id(voter_id.getText());
        bean.setAdhar_no(adhar_no.getText());
        if(query.saveBank(bean))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setContentText("Done!!");
            alert.showAndWait(); 
            close(evt);
        }
    }
    @FXML
    private void close(ActionEvent e)
    {
         employee.setVisible(false);
    }
    @FXML
    public void openLeaveForm(ActionEvent e) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpLeaves.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)employee.getParent();
            //((Label)root1.lookup("#e_id")).setText(eid);
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
