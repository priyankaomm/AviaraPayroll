/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.aviara.bean.Deduction;
import com.aviara.bean.Leave;
import com.aviara.controller.Query;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class AttendenceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Query query = new Query();
    @FXML
    private ComboBox e_id;
    @FXML
    private ComboBox e_id1;
    @FXML
    private Button save_btn;
    @FXML
    private Button edit_btn;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label tdate;
    private ObservableList data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buildData();
        setDate();
        e_id.setVisible(false);
        e_id1.setVisible(false);
        
    }    
    private void buildData()
    {
        try{
            Pane p=new Pane();
        //System.out.println(qtable.getColumns());
        List<Map> q=query.listEmployeesScalar();
         data=FXCollections.observableArrayList();
          int count=1,j=0;
          String [] ids=new String[100];
          String[] fname=new String[100];
          String[] lname=new String[100];
          //String[] percentage=new String[100];
        for (int i = 0; i < q.size(); i++) 
        {
             count++;j++;
             ids[i]=q.get(i).get("emp_id").toString();
             fname[i]=q.get(i).get("first_name").toString();
             lname[i]=q.get(i).get("last_name").toString();
             fname[i]=fname[i]+" "+lname[i];
             fname[i]=fname[i].toUpperCase();
          
        }
        String [] lids=new String[100];
        String [] type=new String[100];
         List<Map> q1=query.leaves();
         for (int i = 0; i < q1.size(); i++) 
        {
             //count++;j++;
             lids[i]=q1.get(i).get("emp_id").toString();
             type[i]=q1.get(i).get("status").toString();
           
        }
        int x=30,y=20;
        for(int i=0;i<j;i++)
        {
            
            
            String temp=ids[i];
            String ltemp=lids[i];
            //String t=type[i];
            Label n=new Label(fname[i]);
            n.setLayoutX(x);
            n.setLayoutY(y);
            //n.setStyle("-fx-font-size: 12pt;");
            /*Label per=new Label(lname[i]);
            per.setLayoutX(n.getLayoutX()+50);
            per.setLayoutY(y);
            per.setStyle("-fx-font-size: 12pt;");*/
            ComboBox cb=new ComboBox();
            cb.getItems().add("Present");
            cb.getItems().add("Half Day");
            cb.getItems().add("Absent");
            cb.setLayoutX(n.getLayoutX()+280);
            cb.setLayoutY(y);
            
            cb.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    //label.setText("Accepted");
                     if(cb.getSelectionModel().getSelectedIndex()==1)
                     e_id.getItems().add(temp);
                     else if(cb.getSelectionModel().getSelectedIndex()==2)
                     e_id1.getItems().add(temp);  
                     else if(cb.getSelectionModel().getSelectedIndex()==0)
                     {
                        e_id1.getItems().remove(temp);  
                        e_id.getItems().add(temp);  
                     }
                    
                }
            });
            cb.getSelectionModel().select(0);
            //save_btn.setDisable(false);
            //edit_btn.setDisable(true);
            for(int k=0;k<lids.length;k++)
            {
               if(temp.equals(lids[k]))
            {
               save_btn.setDisable(true);
               edit_btn.setDisable(false);
               if(type[k].equals("0"))
               {
                   System.out.println("half");
                 cb.getSelectionModel().select(1);
                 //break;
               }
               else if(type[k].equals("1"))
               {
                   System.out.println("absent");
                 cb.getSelectionModel().select(2);
                // break;
                 
               }
               
               
            }
               
            
            }
            p.getChildren().addAll(n,cb);
            y=y+50;
        }
        scroll.setContent(p);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            
        }
    private void setDate()
    {
        SimpleDateFormat sd=new SimpleDateFormat("dd.MM.yyyy");
        Date d=new Date();
        tdate.setText(sd.format(d));
    }
    @FXML
    private void saveLeaveDetails(ActionEvent evt)
    {
        //Leave lbean =new Leave();
        /*if(e_id.getItems().isEmpty())
        {
            
        }
        else
        {*/
           for(int i=0;i<e_id.getItems().size();i++)
        {
            Leave lbean =new Leave();
            lbean.setEmp_id(e_id.getItems().get(i).toString());
            lbean.setType("Half Day");
            lbean.setStatus(0);
            lbean.setDate(convert());
            query.saveLeave(lbean);
        }
        /*}
          if(e_id1.getSelectionModel().isEmpty())
        {
            
        }
        else
        {*/
           for(int i=0;i<e_id1.getItems().size();i++)
        {
            Leave lbean =new Leave();
            lbean.setEmp_id(e_id1.getItems().get(i).toString());
            lbean.setType("Absent");
            lbean.setStatus(1);
            lbean.setDate(convert());
            query.saveLeave(lbean);
        }
        //}  
        
    }
    private java.sql.Date convert()
    {
        java.sql.Date sqlDate=null;
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                java.util.Date date = sdf.parse(tdate.getText());
               
                sqlDate =  new java.sql.Date(date.getTime());
               
                System.out.println("String converted to java.sql.Date :" + sqlDate);
        }catch(Exception e){}
        return sqlDate;
    }
}
