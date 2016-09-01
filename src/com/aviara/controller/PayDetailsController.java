/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.PaymentDetails;
import static com.aviara.controller.EmpLeavesController.eid;
import static com.aviara.controller.EmployeeController.eid;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class PayDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static String eid;
    PaymentDetails pbean=new PaymentDetails();
    Query query=new Query();
    @FXML
    private Label e_id;
    @FXML
    private ComboBox emp_type;
    @FXML
    private TextField basic_sal;
    
    @FXML
    private TextField hra;
    @FXML
    private TextField other;
    @FXML
    private TextField total;
    @FXML
    private DatePicker nextPay;
    @FXML
    private DatePicker incDate;
    @FXML
    private AnchorPane employee;
    @FXML
    private Button edit;
    @FXML
    private Button save;
    @FXML
    private Button back_btn;
    @FXML
    private Button next;
    @FXML
    private Label close_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //System.out.println("emp_id:"+EmployeeController.eid);
        if(AllEmployeeController.flag)
        {
            eid=AllEmployeeController.emp_id;
            e_id.setText(eid);
            save.setVisible(false);
            back_btn.setVisible(false);
            next.setVisible(false);
            edit.setVisible(true);
            getPaymentDetails();
            System.out.println("true");
        }
        else
        {
            System.out.println("false id="+EmployeeController.eid);
            
            eid=EmployeeController.eid;
            e_id.setText(EmployeeController.eid);
            save.setVisible(true);
            back_btn.setVisible(true);
            next.setVisible(true);
            edit.setVisible(false);
        }
    }    
    private void getPaymentDetails()
    {
        List<Map> q=query.getPaymentDetailsByEmpId(eid);
        for (int i = 0; i < q.size(); i++) 
        {
           emp_type.getSelectionModel().select(q.get(i).get("emp_type").toString());
           basic_sal.setText(q.get(i).get("basicSal").toString());
           hra.setText(q.get(i).get("hra").toString());
           other.setText(q.get(i).get("otherAllowance").toString());
           total.setText(q.get(i).get("totalSal").toString());
           java.sql.Date sdate=(Date)q.get(i).get("incDate");
           incDate.setValue(sdate.toLocalDate());
           sdate=(Date)q.get(i).get("nextPay");
           nextPay.setValue(sdate.toLocalDate());
        }
    }
    @FXML
    private void savePaymentDetails(ActionEvent evt)
    {
        setPaymentBean();
        if(query.savePayment(pbean))
        {
            eid=e_id.getText();
            //clearForm();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setContentText("Done!!");
            alert.showAndWait(); 
            closeCurrent(evt);
            openNext(evt);
        }
        
    }
    private void setPaymentBean()
    {
        pbean.setEmp_id(e_id.getText());
        pbean.setEmp_type(emp_type.getSelectionModel().getSelectedItem().toString());
        pbean.setBasicSal(Double.parseDouble(basic_sal.getText()));
        pbean.setDuration("month");
        pbean.setHra(Double.parseDouble(hra.getText()));
        pbean.setOther(Double.parseDouble(other.getText()));
        pbean.setTotal(Double.parseDouble(total.getText()));
        Date date = java.sql.Date.valueOf(nextPay.getValue());
        pbean.setNextPay(date);
        date = java.sql.Date.valueOf(incDate.getValue());
        pbean.setIncDate(date);
    }
    @FXML
    private void calculateTotal(ActionEvent evt)
    {
        TextField text=(TextField)evt.getSource();
        if(text.getText().equals(""))
        {
            text.setText("0.00");
        }
        
        Double t=Double.parseDouble(basic_sal.getText())+
                Double.parseDouble(hra.getText())+
                Double.parseDouble(other.getText());
        total.setText(t.toString());
        
    }
    @FXML
    private void setTextBox(MouseEvent evt)
    {
        
        TextField text=(TextField)evt.getSource();
        if(text.getText().equals("0.00"))
        text.setText("");
    }
    private void clearForm()
    {
        emp_type.getSelectionModel().clearSelection();
        basic_sal.setText("0.00");
        other.setText("0.00");
        hra.setText("0.00");
        total.setText("0.00");
        nextPay.setValue(null);
        incDate.setValue(null);
    }
    @FXML
    private void closeCurrent(ActionEvent evt)
    {
        employee.setVisible(false);
    }
    @FXML
    private void openNext(ActionEvent evt)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Emp_deduction.fxml"));
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
    @FXML
    public void openEmployee(ActionEvent e) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Employee.fxml"));
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
    @FXML
    private void handleClose(MouseEvent evt)
    {
        if(save.isDisable())
        {
            query.openAllEmployee(employee);
            AllEmployeeController.flag=false;
        }
        else
        {
           query.close(employee);
        }
    }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
       query.rotate(close_btn);
        //System.out.println("rotate...");
    }
    @FXML
    private void edit(ActionEvent evt)
    {
        query.deletePayment(eid);
        setPaymentBean();
        if(query.savePayment(pbean))
        {
            eid=e_id.getText();
            //clearForm();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setContentText("Done!!");
            alert.showAndWait(); 
            query.openAllEmployee(employee);
            AllEmployeeController.flag=false;
        }
    }
}
