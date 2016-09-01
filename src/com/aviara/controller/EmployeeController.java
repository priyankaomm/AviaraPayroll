/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Address;
import com.aviara.bean.Contact;
import com.aviara.bean.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
 * @author comp2
 */
public class EmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static public String eid="";
    final int N_SECS = 1;
    Employee ebean = new Employee();
    Contact cbean = new Contact();
    Address abean=new Address();
    Query query = new Query();
    @FXML
    private AnchorPane employee;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField emp_id;
    @FXML
    private TextField device_id;
    @FXML
    private TextField fname;
    @FXML
    private TextField mname;
    @FXML
    private TextField lname;
    @FXML
    private TextField designation;
    
    
    @FXML
    private DatePicker doj;
    @FXML
    private DatePicker dor;
    @FXML
    private DatePicker lastPay;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    private ComboBox dept;
    @FXML
    private ComboBox branch;
    @FXML
    private TextField contact;
    @FXML
    private TextField alt_contact;
    @FXML
    private TextField email;
    @FXML
    private TextField country1;
    @FXML
    private TextField state1;
    @FXML
    private TextField city1;
    @FXML
    private TextField pincode1;
    @FXML
    private TextField address1;
    @FXML
    private Label close_btn;
    @FXML
    private Button save;
    @FXML
    private Button edit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        if(AllEmployeeController.flag)
        {
            save.setDisable(true);
            edit.setDisable(false);
            eid=AllEmployeeController.emp_id;
            getEmployeeInfo();
            getContact();
        }
        else
        {
            save.setDisable(false);
            edit.setDisable(true);
        }
    }    
    @FXML
        public void saveAll(ActionEvent evt)
        {
            if(fname.getText().equals("")
               ||mname.getText().equals("")
               ||lname.getText().equals("")
                   
               ||designation.getText().equals("")     
               ||emp_id.getText().equals("") 
               ||device_id.getText().equals("") 
               ||branch.getSelectionModel().isEmpty()
               ||dept.getSelectionModel().isEmpty()
                   )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please fill all the Employee details!!");
                alert.showAndWait();
            }
            else if(contact.getText().equals("")
               ||alt_contact.getText().equals("")
               ||email.getText().equals("")
               )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please fill all the Contact details!!");
                alert.showAndWait();
            }
            else if(country1.getText().equals("")
                   ||state1.getText().equals("")
                   ||city1.getText().equals("")     
                   ||address1.getText().equals("") 
                   ||pincode1.getText().equals("")
               
               )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please fill all the Address details!!");
                alert.showAndWait();
            }
            else
            {
                try{
                setEmpBean();
                eid=emp_id.getText();
                setContactBean();
                setAddressBean();
                final Node source = (Node) evt.getSource();
                final Scene stage = (Scene) source.getScene();
                createProgressBar(stage,evt);
                //clearEmp();
                //clearAddress();
                //clearContact();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Employee Added successfully!!");
                alert.showAndWait();
                
                employee.setVisible(false);
                openPayDetails(evt,eid);
                //Button btn=(Button)evt.getSource();
                //btn.setDisable(true);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                
            }
        }
        public void openPayDetails(ActionEvent e,String eid) throws IOException
    {
        try{
            eid=emp_id.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/PayDetails.fxml"));
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
        private void setEmpBean() throws ParseException
    {
        ebean.setEmp_id(emp_id.getText());
        ebean.setDevice_id(device_id.getText());
        //empid=emp_id.getText();
        ebean.setFirstName(fname.getText());
        ebean.setMidName(mname.getText());
        ebean.setLastName(lname.getText());
        String d=day.getSelectionModel().getSelectedItem().toString();
        String m=month.getSelectionModel().getSelectedItem().toString();
        String y=year.getSelectionModel().getSelectedItem().toString();
        String dt=d+"-"+m+"-"+y;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date1 = sdf1.parse(dt);
        java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());  
         
        ebean.setDob(sqlStartDate);
        Date date = java.sql.Date.valueOf(doj.getValue());
        ebean.setDoj(date);
        if(dor.getValue()==null)
        {
            
        }
        else
        {
        date = java.sql.Date.valueOf(dor.getValue());
        ebean.setDor(date);
        }
        date = java.sql.Date.valueOf(lastPay.getValue());
        ebean.setLastPayment(date);
        ebean.setDesignation(designation.getText());
        //ebean.setQualification(qualification.getText());
        if(male.isSelected())
        {
           ebean.setGender("Male");
        }
        else
        {
            ebean.setGender("Female");
        }
        ebean.setBranch(branch.getSelectionModel().getSelectedItem().toString());
        ebean.setDepartment(dept.getSelectionModel().getSelectedItem().toString());
        //return ebean;
    }
        private void setContactBean()
    {
        cbean.setContact_no(contact.getText());
        cbean.setAlt_contact(alt_contact.getText());
        cbean.setEmail_id(email.getText());
        cbean.setGuardian_name("N/A");
        cbean.setG_contact("N/A");
        cbean.setMother_name("N/A");
        cbean.setM_contact("N/A");
        cbean.setEmergency1_name("N/A");
        cbean.setEmergency1("N/A");
        cbean.setEmergency2_name("N/A");
        cbean.setEmergency2("N/A");
        cbean.setEmp_id(ebean.getEmp_id());
    }
        private void setAddressBean()
    {
        abean.setCountry1(country1.getText());
        abean.setCountry2("N/A");
        abean.setState1(state1.getText());
        abean.setState2("N/A");
        abean.setCity1(city1.getText());
        abean.setCity2("N/A");
        abean.setPincode1(pincode1.getText());
        abean.setPincode2("N/A");
        abean.setAddress1(address1.getText());
        abean.setAddress2("N/A");
        abean.setEmp_id(ebean.getEmp_id());
    }
        private Label createCounter(Task task) {
        Label counter = new Label();

        counter.setMinWidth(20);
        counter.setAlignment(Pos.CENTER_RIGHT);
        //counter.setLayoutX(100);
        //counter.setLayoutY(100);
        counter.textProperty().bind(task.messageProperty());
        counter.setStyle("-fx-border-color: forestgreen;");

        return counter;
    }
        private ProgressIndicator createProgressIndicator(Task task) {
        ProgressIndicator progress = new ProgressIndicator();

        progress.progressProperty().bind(task.progressProperty());

        return progress;
    }
         private boolean createProgressBar(Scene stage,ActionEvent evt)
    {
        boolean flag=false;
        Task task = createTask("emp_info",evt);
        Task task2 = createTask("contact_info",evt);
        Task task3 = createTask("address_info",evt);
        Label l=createCounter(task);
        ProgressIndicator pi=createProgressIndicator(task);
        employee.getChildren().addAll(
            pi,l
        );
        pi.setLayoutX(employee.getPrefWidth()/2);
        pi.setLayoutY(employee.getPrefHeight()/2);
       // pi.setVisible(false);
        l.setVisible(false);
        new Thread(task).start();
        new Thread(task2).start();
        new Thread(task3).start();
        if(task.isDone()&&task2.isDone()&&task3.isDone())
        {
            System.out.println("completed.....");
            pi.setVisible(false);
            flag=true;
            
            //System.out.println("done");
        }
        return flag;
       
    }
    private Task<Void> createTask(String type,ActionEvent evt) {
        return new Task<Void>() {
            @Override public Void call() {
                for (int i=0; i < N_SECS; i++) {
                    if (isCancelled()) {
                        break;
                    }
                          if(type.equals("emp_info"))
                           saveEmployee(evt);
                          else if(type.equals("contact_info"))
                           saveEmployeeContact(evt);
                          else if(type.equals("address_info"))
                           saveEmployeeAddress(evt);
                           
                     updateProgress(i, N_SECS);
                    updateMessage((N_SECS - i) + "");
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return null;
                    }
                }

                updateMessage(0 + "");
                updateProgress(N_SECS, N_SECS);

                return null;
            }
        };
    }
    private void clearEmp()
        {
            emp_id.setText("");
        fname.setText("");
        mname.setText("");
        lname.setText("");
        designation.setText("");
        //qualification.setText("");
        doj.setValue(null);
        dor.setValue(null);
        lastPay.setValue(null);
        //dob.setValue(null);
        branch.getSelectionModel().clearSelection();
        dept.getSelectionModel().clearSelection();
        //exp.getSelectionModel().clearSelection();
        male.setSelected(false);
        female.setSelected(false);
        
        }
    public void clearContact()
        {
           contact.setText("");
           alt_contact.setText("");
           email.setText("");
           
        
        }
        private void clearAddress()
        {
            country1.setText("");
            state1.setText("");
            city1.setText("");
            pincode1.setText("");
            address1.setText("");
            pincode1.setText("");
        
        }
    @FXML
    public void saveEmployee(ActionEvent evt)
    {
        
        
        //ebean.setExp(exp.getSelectionModel().getSelectedItem().toString());
        query.saveEmployeeInfo(ebean);
        
    }
    @FXML
    public void saveEmployeeContact(ActionEvent evt)
    {
        
        
        query.saveEmployeeContact(cbean);
        //clearContact();
    }
    @FXML
    public void saveEmployeeAddress(ActionEvent evt)
    {
        
        
        query.saveEmployeeAddress(abean);
        //clearAddress();
    }
    @FXML
    private void handleCancel(ActionEvent evt)
    {
        employee.setVisible(false);
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
    private void getEmployeeInfo()
    {
       
          List<Map> q=query.getEmpName(eid);
          emp_id.setText(eid);
          for (int i = 0; i < q.size(); i++) 
          {
            fname.setText(q.get(i).get("first_name").toString());
            mname.setText(q.get(i).get("mid_name").toString());
            lname.setText(q.get(i).get("last_name").toString());
            device_id.setText(q.get(i).get("device_id").toString());
            java.sql.Date sdate=(Date)q.get(i).get("doj");
            doj.setValue(sdate.toLocalDate());
            if(q.get(i).get("date_of_resignation")==null)
            {
                
            }
            else
            {
                sdate=(Date)q.get(i).get("date_of_resignation");
                dor.setValue(sdate.toLocalDate());
            }
            sdate=(Date)q.get(i).get("lastPayment");
            lastPay.setValue(sdate.toLocalDate());
            designation.setText(q.get(i).get("designation").toString());
            branch.getSelectionModel().select(q.get(i).get("branch").toString());
            dept.getSelectionModel().select(q.get(i).get("branch").toString());
            if(q.get(i).get("gender").toString().equalsIgnoreCase("male"))
            {
                male.setSelected(true);
            }
            else
            {
               female.setSelected(true); 
            }
            String[] dob1=q.get(i).get("dob").toString().split("-");
            year.getSelectionModel().select(dob1[0]);
            month.getSelectionModel().select(dob1[1]);
            day.getSelectionModel().select(dob1[2]);
          }
          
    }
   private void getContact()
   {
       List<Map> q=query.getEmpContact(eid);
       for (int i = 0; i < q.size(); i++) 
          {
            country1.setText(q.get(i).get("country1").toString());
            state1.setText(q.get(i).get("state1").toString());
            city1.setText(q.get(i).get("city1").toString());
            address1.setText(q.get(i).get("address1").toString());
            pincode1.setText(q.get(i).get("pincode1").toString());
            contact.setText(q.get(i).get("contact_no").toString());
            alt_contact.setText(q.get(i).get("alt_contact").toString());
            email.setText(q.get(i).get("email_id").toString());
          }
         
   }
   
   @FXML
   private void edit(ActionEvent evt)
   {
       try{
          setEmpBean();
          setAddressBean();
          setContactBean();
          query.updateEmpInfo(ebean);
          query.updateEmpAddress(abean);
          query.updateEmpContact(cbean);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Message");
          alert.setContentText("Employee Updated successfully!!");
          alert.showAndWait();
          query.openAllEmployee(employee);
       }catch(Exception e)
       {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setContentText(e.getMessage()+"!!");
              alert.showAndWait();  
       }
       
   }
   
   
}
