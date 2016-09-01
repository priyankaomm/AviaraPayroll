/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Address;
import com.aviara.bean.Contact;
import com.aviara.bean.Employee;
import com.aviara.bean.Experiance;
import com.aviara.bean.Qualification;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class AddEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    final int N_SECS = 1;
    @FXML
    private Tab quali;
    @FXML
    private Tab prof;
    @FXML
    private AnchorPane employee;
    @FXML
    private Pane emp_main;
    @FXML
    private Pane personal_pane;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField emp_id;
    @FXML
    private TextField fname;
    @FXML
    private TextField mname;
    @FXML
    private TextField lname;
    @FXML
    private TextField designation;
    
    @FXML
    private DatePicker dob;
    @FXML
    private DatePicker doj;
    
    @FXML
    private ComboBox dept;
    @FXML
    private ComboBox branch;
    @FXML
    private Label close_btn;
    //Contact Info
    @FXML
    private TextField contact;
    @FXML
    private TextField alt_contact;
    @FXML
    private TextField email;
    @FXML
    private TextField g_name;
    @FXML
    private TextField g_contact;
    @FXML
    private TextField m_name;
    @FXML
    private TextField m_contact;
    @FXML
    private TextField e1_name;
    @FXML
    private TextField e1_contact;
    @FXML
    private TextField e2_name;
    @FXML
    private TextField e2_contact;
    //Address Info
    @FXML
    private ComboBox country1;
    @FXML
    private ComboBox state1;
    @FXML
    private TextField city1;
    @FXML
    private TextField pincode1;
    @FXML
    private TextField address1;
    @FXML
    private ComboBox country2;
    @FXML
    private ComboBox state2;
    @FXML
    private TextField city2;
    @FXML
    private TextField pincode2;
    @FXML
    private TextField address2;
    @FXML
    private CheckBox same;
    //Qualification
    @FXML
    private ComboBox degree;
    @FXML
    private TextField university;
    @FXML
    private TextField collage_name;
    @FXML
    private TextField marks;
    @FXML
    private TableView qtable;
    @FXML
    private TableColumn col1;
    @FXML
    private TableColumn col2;
    @FXML
    private TableColumn col3;
    @FXML
    private TableColumn col4;
    @FXML
    private TableColumn id;
    private ObservableList data;
    //Experiance
    @FXML
    private ComboBox texp;
    @FXML
    private TextField compony_name;
    @FXML
    private TextField p_designation;
    @FXML
    private TableView ctable;
    @FXML
    private RadioButton fresher;
    @FXML
    private RadioButton professional;
    @FXML
    private Pane exp_panel;
    @FXML
    private TableColumn p_col1;
    @FXML
    private TableColumn p_col2;
    @FXML
    private TableColumn p_col3;
    @FXML
    private TableColumn p_col4;
    Employee ebean = new Employee();
    Contact cbean = new Contact();
    Address abean=new Address();
    Query query = new Query();
    Qualification qbean=new Qualification();
    String empid=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quali.setDisable(true);
        prof.setDisable(true);
        ToggleGroup group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        ToggleGroup group1 = new ToggleGroup();
        fresher.setToggleGroup(group1);
        professional.setToggleGroup(group1);
        exp_panel.setVisible(false);
        
    }    
    @FXML
    public void saveEmployee(ActionEvent evt)
    {
        
        
        //ebean.setExp(exp.getSelectionModel().getSelectedItem().toString());
        query.saveEmployeeInfo(ebean);
        
    }
    private void setEmpBean()
    {
        ebean.setEmp_id(emp_id.getText());
        //empid=emp_id.getText();
        ebean.setFirstName(fname.getText());
        ebean.setMidName(mname.getText());
        ebean.setLastName(lname.getText());
        Date date = java.sql.Date.valueOf(dob.getValue());
        ebean.setDob(date);
        date = java.sql.Date.valueOf(doj.getValue());
        ebean.setDoj(date);
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
    @FXML
    private void close_window(MouseEvent event)  {
          employee.setVisible(false);
        }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
        RotateTransition rotation=new RotateTransition(Duration.millis(500), close_btn);
        rotation.setByAngle(180);
        rotation.play();
        //System.out.println("rotate...");
    }
    private ProgressIndicator createProgressIndicator(Task task) {
        ProgressIndicator progress = new ProgressIndicator();

        progress.progressProperty().bind(task.progressProperty());

        return progress;
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
    @FXML
    public void saveEmployeeContact(ActionEvent evt)
    {
        
        
        query.saveEmployeeContact(cbean);
        //clearContact();
    }
    private void setContactBean()
    {
        cbean.setContact_no(contact.getText());
        cbean.setAlt_contact(alt_contact.getText());
        cbean.setEmail_id(email.getText());
        cbean.setGuardian_name(g_name.getText());
        cbean.setG_contact(g_contact.getText());
        cbean.setMother_name(m_name.getText());
        cbean.setM_contact(m_contact.getText());
        cbean.setEmergency1_name(e1_name.getText());
        cbean.setEmergency1(e1_contact.getText());
        cbean.setEmergency2_name(e2_name.getText());
        cbean.setEmergency2(e2_contact.getText());
        cbean.setEmp_id(ebean.getEmp_id());
    }
    @FXML
    public void saveEmployeeAddress(ActionEvent evt)
    {
        
        
        query.saveEmployeeAddress(abean);
        //clearAddress();
    }
    private void setAddressBean()
    {
        abean.setCountry1(country1.getSelectionModel().getSelectedItem().toString());
        abean.setCountry2(country2.getSelectionModel().getSelectedItem().toString());
        abean.setState1(state1.getSelectionModel().getSelectedItem().toString());
        abean.setState2(state2.getSelectionModel().getSelectedItem().toString());
        abean.setCity1(city1.getText());
        abean.setCity2(city2.getText());
        abean.setPincode1(pincode1.getText());
        abean.setPincode2(pincode2.getText());
        abean.setAddress1(address1.getText());
        abean.setAddress2(address2.getText());
        abean.setEmp_id(ebean.getEmp_id());
    }
    @FXML
    public void handleCheckEvent(ActionEvent evt)
    {
        if(same.isSelected())
        {
        country2.getSelectionModel().select(country1.getSelectionModel().getSelectedItem().toString());
        state2.getSelectionModel().select(state1.getSelectionModel().getSelectedItem().toString());
        city2.setText(city1.getText());
        pincode2.setText(pincode1.getText());
        address2.setText(address1.getText());
        }
        else
        {
           country2.getSelectionModel().clearSelection();
           state2.getSelectionModel().clearSelection();
           city2.setText("");
           pincode2.setText("");
           address2.setText("");
        }
    }
    @FXML
    public void openAnother(ActionEvent evt)
    {
        final Node source = (Node) employee.getParent();
        Pane main_pane=(Pane)source;
        main_pane.getChildren().removeAll(employee);
        //employee.setVisible(false);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/YearCalender.fxml"));
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
    public void saveEmployeeQualification(ActionEvent evt)
    {
        try{
            if(ebean.getEmp_id()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please Save Primary informaion first!!");
                alert.showAndWait();  
            }
            else
            {
              Qualification qbean=new Qualification();
             qbean.setDegree(degree.getSelectionModel().getSelectedItem().toString());
             qbean.setUniversity(university.getText());
             qbean.setCollage_name(collage_name.getText());
             qbean.setMarks(Double.parseDouble(marks.getText()));
             qbean.setEmp_id(ebean.getEmp_id());
             query.saveEmployeeQualification(qbean);
             buildData(qbean);
             clearQualification();
           }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    @FXML
    public void saveEmployeeExperiance(ActionEvent evt)
    {
        try{
            Experiance qbean=new Experiance();
        qbean.setExperiance(texp.getSelectionModel().getSelectedItem().toString());
        qbean.setCompony_name(compony_name.getText());
        qbean.setDesignation(p_designation.getText());
        qbean.setEmp_id(ebean.getEmp_id());
        query.saveEmployeeExperiance(qbean);
        //qtable.getItems().clear();
        buildData(qbean);
        clearExperiance();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
        public void buildData(Qualification qbean)
        {
            try{
            removeAllRows(qtable);
            
                setColumn(col1, 0);
                setColumn(col2, 1);
                setColumn(col3, 2);
                setColumn(col4, 3);
            
        //System.out.println(qtable.getColumns());
        List<Qualification> q=query.fetchQualification(qbean);
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Qualification user = (Qualification) q.get(i);
            //data .add(user);
            System.out.println("id="+user.getId());
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+user.getId());
           row.add(user.getUniversity());
           row.add(user.getCollage_name());
           row.add(""+user.getMarks());
           //row.add(user.getUniversity());
           System.out.println("Row [1] added "+row );
           data.add(row);
           System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        qtable.setItems(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        public void removeAllRows(TableView qtable){
        for ( int i = 0; i<qtable.getItems().size(); i++) {
        qtable.getItems().clear(); 
        } 
        }
        public void buildData(Experiance qbean)
        {
            try{
            removeAllRows(ctable);
            
                setColumn(p_col1, 0);
                setColumn(p_col2, 1);
                setColumn(p_col3, 2);
                setColumn(p_col4, 3);
            
        //System.out.println(qtable.getColumns());
        List<Experiance> q=query.fetchExperiance(qbean);
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Experiance user = (Experiance) q.get(i);
            //data .add(user);
            System.out.println("id="+user.getId());
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+user.getId());
           row.add(user.getExperiance());
           row.add(user.getCompony_name());
           row.add(""+user.getDesignation());
           //row.add(user.getUniversity());
           System.out.println("Row [1] added "+row );
           data.add(row);
           System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        ctable.setItems(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        @FXML
        public void handleExpEvent(ActionEvent evt)
        {
            if(fresher.isSelected())
        {
            exp_panel.setVisible(false);
        }
            else if(professional.isSelected())
        {
            exp_panel.setVisible(true);
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
               ||g_name.getText().equals("")
               ||g_contact.getText().equals("")     
               ||m_name.getText().equals("")     
               ||m_contact.getText().equals("") 
               ||e1_name.getText().equals("")
               ||e1_contact.getText().equals("")
               ||e2_name.getText().equals("")
               ||e2_contact.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Please fill all the Contact details!!");
                alert.showAndWait();
            }
            else if(country1.getSelectionModel().isEmpty()
               ||country2.getSelectionModel().isEmpty()
               ||state1.getSelectionModel().isEmpty()
               ||state2.getSelectionModel().isEmpty()
               ||city1.getText().equals("")     
               ||city2.getText().equals("")     
               ||address1.getText().equals("") 
               ||address2.getText().equals("") 
               ||pincode1.getText().equals("")
               ||pincode2.getText().equals("")
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
                setEmpBean();
                setContactBean();
                setAddressBean();
                final Node source = (Node) evt.getSource();
                final Scene stage = (Scene) source.getScene();
                createProgressBar(stage,evt);
                clearEmp();
                clearAddress();
                clearContact();
                Button btn=(Button)evt.getSource();
                btn.setDisable(true);
                quali.setDisable(false);
                prof.setDisable(false);
            }
        }
        public void clearContact()
        {
           contact.setText("");
           alt_contact.setText("");
           email.setText("");
           g_name.setText("");
           g_contact.setText("");
           m_name.setText("");
           m_contact.setText("");
           e1_name.setText("");
           e1_contact.setText("");
           e2_name.setText("");
           e2_contact.setText("");
        
        }
        private void clearAddress()
        {
            country1.getSelectionModel().clearSelection();
            country2.getSelectionModel().clearSelection();
            state1.getSelectionModel().clearSelection();
            state2.getSelectionModel().clearSelection();
            city1.setText("");
            city2.setText("");
            pincode1.setText("");
            pincode2.setText("");
            address1.setText("");
            address2.setText("");
            pincode1.setText("");
            pincode2.setText("");
        
        }
        private void clearExperiance()
        {
            texp.getSelectionModel().clearSelection();
            compony_name.setText("");
            p_designation.setText("");
        
        }
        private void clearQualification()
        {
            degree.getSelectionModel().clearSelection();
            university.setText("");
            collage_name.setText("");
            marks.setText("");
        
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
        dob.setValue(null);
        branch.getSelectionModel().clearSelection();
        dept.getSelectionModel().clearSelection();
        //exp.getSelectionModel().clearSelection();
        male.setSelected(false);
        female.setSelected(false);
        
        }
        @FXML
        public void setEmpid(ActionEvent evt)
        {
            if(emp_id.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Employee id is complusory!!");
                alert.showAndWait();  
            }
            else{
            empid=emp_id.getText();
            }
        }
        public void setColumn(TableColumn tc,int count)
        {
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                        
	                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                             

	                        return new SimpleStringProperty(param.getValue().get(count).toString());                       

	                    }                   

	                });
        }
}
