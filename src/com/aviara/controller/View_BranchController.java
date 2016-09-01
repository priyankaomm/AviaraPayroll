/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Branch;
import com.aviara.bean.Deduction;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
public class View_BranchController implements Initializable {

    @FXML
    private Pane main_pane;
    @FXML
    private AnchorPane add_branch;
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
    private TableColumn col6;
    @FXML
    private TableColumn col7;
    @FXML
    private TableColumn col8;
     @FXML
    private TableColumn col9;
    @FXML
    private TableColumn col10;
    @FXML
    private TableView view_branch;
    @FXML
    private AnchorPane brnch;
    
    Query query = new Query();
    private ObservableList data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildData1();
    }  
    /*
      @FXML
    public void handleAddBranch(ActionEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Branch.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   */ 
       @FXML
       public void openAnother(MouseEvent e) throws IOException
    {
        System.out.println("here");
        final Node source = (Node) brnch.getParent();
        Pane main_pane=(Pane)source;
        //employee.setVisible(false); 
        main_pane.getChildren().removeAll(brnch);
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Add_Branch.fxml"));
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
    private void close_window(MouseEvent event)  {
          brnch.setVisible(false);
        }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
        RotateTransition rotation=new RotateTransition(Duration.millis(500), close_btn);
        rotation.setByAngle(180);
        rotation.play();
        //System.out.println("rotate...");
    }
    public void removeAllRow(TableView qtable){
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
        
    
     public void buildData1()
        {
            try{
            removeAllRow(view_branch);
            
                setColumn(col1, 0);
                setColumn(col2, 1);
                setColumn(col3, 2);
                setColumn(col4, 3);
                setColumn(col5, 4);
                setColumn(col6, 5);
                setColumn(col7, 6);
                setColumn(col8, 7);
                //setColumn(col9, 8);
                //setColumn(col10,9);
                
                
        //System.out.println(qtable.getColumns());
        List<Branch> q=query.fetchBranch();
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
            Button edit=new Button();
            Button delete=new Button();
            Branch user = (Branch) q.get(i);
            //data .add(user);
            //System.out.println("id="+user.getEmp_id());
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+user.getBranch_name());
           row.add(user.getBranch_address());
           row.add(user.getBranch_city());
           row.add(user.getBranch_state());
          // row.add(String.valueOf(user.getBranch_zip()));
           row.add(user.getBranch_country());
           row.add(String.valueOf(user.getBranch_contact1()));
           row.add(String.valueOf(user.getBranch_contact2()));
           row.add(user.getBranch_email());
           //row.add(""+edit);
           //row.add(""+delete);
           //row.add(user.getUniversity());
           System.out.println("Row [1] added "+row );
           data.add(row);
           System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        view_branch.setItems(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            System.out.println("Success..");
        }
}
