/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Company_Info;
import java.net.URL;
import java.sql.Date;
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
public class Add_Company_InfoController implements Initializable {

    @FXML
    private Pane main_pane;
    @FXML
    private AnchorPane add_info;
    @FXML
    private Label close_btn;
    @FXML
    private TextField cmp_name;
    @FXML
    private TextField reg;
    @FXML
    private TextField pan;
    @FXML
    private TextField esi;
    @FXML
    private TextField pf;
    @FXML
    private TextField add1;
    @FXML
    private TextField add2;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField country;
    @FXML
    private TextField cntc1;
    @FXML
    private TextField cntc2;
    @FXML
    private Label uname;
    @FXML
    private TextField email;
    @FXML
    private ComboBox fmonth;
    @FXML
    private ComboBox tmonth;
    @FXML
    private TextField fyear;
    @FXML
    private TextField tyear;
    Company_Info ebean=new Company_Info();
    Query query=new Query();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        uname.setText(UserRegistrationController.uname);
        
    }    
     @FXML
    private void close_window(MouseEvent event)  {
          add_info.setVisible(false);
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
    public void addCompanyInfo(ActionEvent evt)
    {
        
        ebean.setCompany_name(cmp_name.getText());
        ebean.setReg_no(reg.getText());
        ebean.setPan_no(pan.getText());
        ebean.setEsi_no(esi.getText());
        ebean.setPf_no(pf.getText());
        ebean.setAddress1(add1.getText());
        ebean.setAddress2(add2.getText());
        ebean.setCity(city.getText());
        ebean.setState(state.getText());
        ebean.setZipcode(Integer.parseInt(zip.getText()));
        ebean.setCountry(country.getText());
        ebean.setContact_no1(Integer.parseInt(cntc1.getText()));
        ebean.setContact_no2(Integer.parseInt(cntc2.getText()));
        ebean.setEmail((email.getText()));
        ebean.setFmonth(fmonth.getSelectionModel().getSelectedIndex()+1);
        ebean.setTmonth(tmonth.getSelectionModel().getSelectedIndex()+1);
        ebean.setFyear(Integer.parseInt(fyear.getText()));
        ebean.setTyear(Integer.parseInt(tyear.getText()));
        if(query.saveCompanyInfo(ebean))
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setContentText("Compony saved successfully!!");
            alert.showAndWait();
            
        }
        else
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Compony already exist!!");
            alert.showAndWait();
           
        }
        int compony_id=query.getComponyIdByReg(reg.getText());
        query.updateUserComponyId(uname.getText(), compony_id);
        final Node source = (Node) evt.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        openLogin();
        
    }
    private void openLogin()
    {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
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
}
