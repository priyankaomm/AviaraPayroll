/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Holiday;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import sun.util.BuddhistCalendar;

/**
 * FXML Controller class
 *
 * @author comp2
 */

public class HolidayCalenderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView htable;
    @FXML
    private TableColumn hcol1;
    @FXML
    private TableColumn hcol2;
    @FXML
    private Pane cal_pane;
    @FXML
    private Pane dialog1;
    @FXML
    private AnchorPane cal;
    private DatePicker dp=new DatePicker();
    
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    private TextField event;
    @FXML
    private Label date;
    Stage dialog =null;
    Query query=new Query();
    private ObservableList data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            setMonth();
            setYear();
            String arr[]=getDate();
            int y=Integer.parseInt(arr[2]);
            int m=Integer.parseInt(arr[1]);
            int d=Integer.parseInt(arr[0]);
            addCalender(y, m, d);
            month.getSelectionModel().select(m-1);
            year.getSelectionModel().select(""+y);
    }    
    private void setMonth()
    {
        for(int i=1;i<=12;i++)
        {
            month.getItems().add(i);
        }
    }
    private void setYear()
    {
        for(int i=2000;i<=2100;i++)
        {
            year.getItems().add(i);
        }
    }
    @FXML
    private void changeMonth(ActionEvent evt)
    {
        int y=0;
        if(year.getSelectionModel().getSelectedItem()==null)
        {
            String[] arr=getDate();
            y=Integer.parseInt(arr[2]);
        }
        else
        {
            y=Integer.parseInt(year.getSelectionModel().getSelectedItem().toString());
        }
        
        int mon=Integer.parseInt(month.getSelectionModel().getSelectedItem().toString());
        addCalender(y,mon,1);
        
    }
    private String[] getDate()
         
    {
        SimpleDateFormat sd=new SimpleDateFormat("dd-MM-yyyy");
        Date d=new Date();
        String td=sd.format(d);
        String[] arr=td.split("-");
        return arr;
    }
    @FXML
    private void changeYear(ActionEvent evt)
    {
        int y=0;
        if(month.getSelectionModel().getSelectedItem()==null)
        {
            String[] arr=getDate();
            y=Integer.parseInt(arr[1]);
        }
        else
        {
            y=Integer.parseInt(month.getSelectionModel().getSelectedItem().toString());
        }
        
        int mon=Integer.parseInt(year.getSelectionModel().getSelectedItem().toString());
        addCalender(mon, y, 1);
    }
    private void addCalender(int y,int m,int d)
    {
        dp=new DatePicker(LocalDate.of(y, m, d));
        
        dp.setOnAction(e ->
        {
            LocalDate date = dp.getValue();
            java.sql.Date sdate=java.sql.Date.valueOf(dp.getValue());
            List<Map> q=query.getHolidays(sdate);
            if(q.isEmpty())
            {
            String arr[]=date.toString().split("-");
            String dt=arr[2]+"-"+arr[1]+"-"+arr[0];
            System.out.println("selected date:"+dt);
            createDialog(dt);
            }else
            {
                String et=q.get(0).get("event").toString();
                String arr[]=date.toString().split("-");
                String dt=arr[2]+"-"+arr[1]+"-"+arr[0];
                System.out.println("selected date:"+dt);
                createDialog(dt,et);
            }
            
        });
        dp.setShowWeekNumbers(true);
        
        DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
        Node popupContent = datePickerSkin.getPopupContent();
        cal_pane.getChildren().removeAll(cal_pane.getChildren());
        cal_pane.getChildren().add(popupContent);
        buildData1(m,y);
        
        
    }
    private void createDialog(String d)
    {
       
        dialog=new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Holiday");
        date.setText(d);
        Scene scene = new Scene(new Group(dialog1));
       // dialog.setWidth(dialog1.getPrefWidth());
        //dialog.setHeight(dialog1.getPrefHeight());
        dialog.setScene(scene);
        dialog.show();
    }
    private void createDialog(String d,String et)
    {
       
        dialog=new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Holiday");
        date.setText(d);
        event.setText(et);
        Scene scene = new Scene(new Group(dialog1));
       // dialog.setWidth(dialog1.getPrefWidth());
        //dialog.setHeight(dialog1.getPrefHeight());
        dialog.setScene(scene);
        dialog.show();
    }
    @FXML
    private void saveHoliday(ActionEvent evt)
    {
        Holiday hbean=new Holiday();
        java.sql.Date data=convertToSql();
        hbean.setDate(data);
        hbean.setEvent(event.getText());
        if(query.saveHoliday(hbean))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
                //alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Holiday Saved!!");
            alert.showAndWait();
            dialog.close();
        }
        //hbean.setCompony_id();
    }
    private java.sql.Date convertToSql()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date parsed = null;
    try {
        parsed = sdf.parse(date.getText());
    } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    java.sql.Date data = new java.sql.Date(parsed.getTime());
        return data;
    }
    public void buildData1(int m,int y)
        {
            try{
            query.removeAllRows(htable);
            
                query.setColumn(hcol1, 0);
                query.setColumn(hcol2, 1);
                
        //System.out.println(qtable.getColumns());
        List<Map> q=query.listHoliday(m,y);
         data=FXCollections.observableArrayList();
          
        for (int i = 0; i < q.size(); i++) {
           
            ObservableList<String> row = FXCollections.observableArrayList();
           //row.add(""+q.get(i).get("emp_id"));
           row.add(""+q.get(i).get("date"));
           row.add(""+q.get(i).get("event"));
           data.add(row);
        }
           htable.setItems(data);
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            
        }
        
}
