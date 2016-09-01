/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Address;
import com.aviara.bean.Contact;
import com.aviara.bean.Employee;
import com.aviara.bean.Qualification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class AllEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn col1;
    @FXML
    private TableColumn col2;
    @FXML
    private TableColumn col3;
    @FXML
    private TableColumn col4;
    @FXML
    private TableColumn col5;
    @FXML
    private TableColumn col6;
    @FXML
    TableColumn col7 = new TableColumn<>("Edit");
    @FXML
    private TableColumn col8;
    @FXML
    private TableView emptable;
    @FXML
    private AnchorPane allEmp;
    @FXML
    private Label add;
    @FXML
    private Label edit;
    @FXML
    private Label delete;
    @FXML
    private Label pay;
    @FXML
    private Pane bottom_pane;
    
    @FXML
    private Label close_btn;
    
    
    public static String emp_id="";
    public static boolean flag=false;
    Employee ebean = new Employee();
    Query query = new Query();
    private ObservableList data;
    final int rowsPerPage=10;
    Pagination pagination = new Pagination();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        buildData1(0);
        edit.setDisable(true);
        delete.setDisable(true);
        pay.setDisable(true);
    }
    @FXML
    private void disableAdd(MouseEvent evt)
    {
        edit.setDisable(false);
        delete.setDisable(false);
        pay.setDisable(false);
        add.setDisable(true);
    }
    @FXML
    private void enableAdd(MouseEvent evt)
    {
        edit.setDisable(true);
        delete.setDisable(true);
        pay.setDisable(true);
        add.setDisable(false);
        //buildData1(0);
    }

    public void buildData()
        {
            try{
            query.removeAllRows(emptable);
            
                query.setColumn(col1, 0);
                query.setColumn(col2, 1);
                query.setColumn(col3, 2);
                query.setColumn(col4, 3);
                query.setColumn(col5, 4);
                query.setColumn(col6, 5);
                query.setColumn(col7, 6);
                query.setColumn(col8, 7);
        //System.out.println(qtable.getColumns());
        List<Employee> q=query.fetchEmployee();
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Button edit=new Button();
            Button delete=new Button();
            Employee user = (Employee) q.get(i);
            //data .add(user);
            System.out.println("id="+user.getEmp_id());
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+user.getEmp_id());
           row.add(user.getFirstName());
           row.add(user.getLastName());
           row.add(""+user.getDesignation());
           row.add(""+user.getDepartment());
           row.add(""+user.getDoj());
           col7.getColumns().add(edit);
           //row.add(""+delete);
           //row.add(user.getUniversity());
           System.out.println("Row [1] added "+row );
           data.add(row);
           System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        emptable.setItems(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
    public TableView buildData1(int index)
        {
            try{
            query.removeAllRows(emptable);
            
                query.setColumn(col1, 0);
                query.setColumn(col2, 1);
                query.setColumn(col3, 2);
                query.setColumn(col4, 3);
                query.setColumn(col5, 4);
                query.setColumn(col6, 5);
        //System.out.println(qtable.getColumns());
        List<Map> q=query.listEmployeesScalar();
         data=FXCollections.observableArrayList();
          int count=1;
        for (int i = 0; i < q.size(); i++) {
            count++;
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+q.get(i).get("emp_id"));
           row.add(""+q.get(i).get("first_name"));
           row.add(""+q.get(i).get("last_name"));
           row.add(""+q.get(i).get("designation"));
           row.add(""+q.get(i).get("dept"));
           row.add(""+q.get(i).get("contact_no"));
          
           //System.out.println("Row [1] added "+row );
           data.add(row);
           //System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        //emptable.setItems(data);
         createPaginator(data,count);
        //emptable.getItems().setAll(data.subList(index, rowsPerPage));
         //personView.getItems().setAll(persons.subList(0, itemsPerPage));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            return emptable;
        }
        
        
        @FXML
    public void viewEmployee(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Employee.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            //fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewSalary(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpSalary.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
    public void viewLeaves(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/LeaveDetails.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void createPaginator(ObservableList data,int c)
    {
        int count=c/10;
        if(c%10!=0)
        count++;
        pagination=new Pagination(count, 0);
        
        bottom_pane.getChildren().add(pagination);
        //for(int i=1;i<count;i++)
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(data,pageIndex);
            }
        });
 

    }
    private Node createPage(ObservableList data,int pageIndex) {

    int fromIndex = pageIndex * rowsPerPage;
    int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
    emptable.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

    return new BorderPane(emptable);
}
 private String getEmpId()
 {
     int i=emptable.getSelectionModel().getSelectedIndex();
     TableView.TableViewSelectionModel selectionModel = emptable.getSelectionModel();
     ObservableList selectedCells = emptable.getColumns();
     TableColumn tablePosition =   (TableColumn) selectedCells.get(0);//1 is 2nd column
     Object val = tablePosition.getCellData(i);
     //System.out.println("emp_id is :" + val);
     return val.toString();
 }
 @FXML
    private void handleClose(MouseEvent evt)
    {
        query.close(allEmp);
    }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
       query.rotate(close_btn);
        //System.out.println("rotate...");
    }
    @FXML
    public void editEmployee(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            flag=true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Employee.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
    public void editDeduction(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            flag=true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Emp_deduction.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
    public void editAllowance(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            flag=true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpAllowance.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
    public void editLeave(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            flag=true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpLeaves.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
    public void editSalary(MouseEvent e) throws IOException
    {
        try{
            emp_id=getEmpId();
            flag=true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/PayDetails.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)allEmp.getParent();
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
