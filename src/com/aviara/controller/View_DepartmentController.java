/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Department;
import com.aviara.bean.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author comp11
 */
public class View_DepartmentController implements Initializable {

    @FXML
    private AnchorPane view_dept;
    @FXML
    private Label close_btn;
    
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
    private TableView dept_table;
    
    Query query = new Query();
    private ObservableList data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildData();
    }    
     @FXML
    private void close_window(MouseEvent event)  {
          view_dept.setVisible(false);
        }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
        RotateTransition rotation=new RotateTransition(Duration.millis(500), close_btn);
        rotation.setByAngle(180);
        rotation.play();
        //System.out.println("rotate...");
    }
    public void openAddDepartment(MouseEvent e) throws IOException
    {
        System.out.println("here");
        final Node source = (Node) view_dept.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false);
        main_pane.getChildren().removeAll(view_dept);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Department.fxml"));
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
    
    
    
    public void removeAllRows(TableView qtable){
        for ( int i = 0; i<qtable.getItems().size(); i++) {
        qtable.getItems().clear(); 
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
    
     public void buildData()
        {
            try{
            removeAllRows(dept_table);
            
                setColumn(col1, 0);
                setColumn(col2, 1);
                setColumn(col3, 2);
                //setColumn(col4, 3);
               //setColumn(col5, 4);
                
        //System.out.println(qtable.getColumns());
        List<Department> q=query.fetchDepartment();
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Button edit=new Button();
            Button delete=new Button();
            Department user = (Department) q.get(i);
            //data .add(user);
            //System.out.println("id="+user.getEmp_id());
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+user.getDept_name());
           row.add(user.getOt_status());
           row.add(String.valueOf(user.getRate()));
           //row.add(""+edit);
          // row.add(""+delete);
           //row.add(user.getUniversity());
           System.out.println("Row [1] added "+row );
           data.add(row);
           System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        dept_table.setItems(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
    
}
