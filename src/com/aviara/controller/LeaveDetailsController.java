/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.EmpLeave;
import com.aviara.bean.EmpLeaveTaken;
import com.aviara.bean.MonthlyLeaves;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author comp2
 */
    
public class LeaveDetailsController implements Initializable{
    @FXML
    private TextField emp_id;
    @FXML
    private TextField name;
    @FXML
    private TextField aname;
    @FXML
    private Label total;
    @FXML
    private Label rem;
    @FXML
    private CheckBox status;
    @FXML
    private DatePicker fdate;
    @FXML
    private DatePicker tdate;
    @FXML
    private ComboBox leave_type;
    @FXML
    private ComboBox appBy;
    @FXML
    private TextArea remarks;
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
    private TableColumn col7 ;
    @FXML
    private TableColumn col8;
    @FXML
    private TableView ltable;
    @FXML
    private AnchorPane employee;
    @FXML
    private Label close_btn;
    private ObservableList data;
    Query query=new Query();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emp_id.setText(AllEmployeeController.emp_id);
        getName();
        getEmpLeave();
        buildData1();
    }
    private void getName()
    {
        List<Map> q=query.getEmpName(emp_id.getText());
          for (int i = 0; i < q.size(); i++) 
          {
            name.setText(q.get(i).get("first_name")+" "+q.get(i).get("last_name"));
           
        }
    }
    @FXML
    private void total1()
    {
        total.setText("1");
    }
    @FXML
    private void total2()
    {
        Date date1 = java.sql.Date.valueOf(fdate.getValue());
        Date date2= java.sql.Date.valueOf(tdate.getValue());
        System.out.println("date1:"+date1);
        System.out.println("date2:"+date2);
        int t=tdate.getValue().compareTo(fdate.getValue());
        total.setText(""+t);
    }
    private void getEmpLeave()
    {
        try{
        leave_type.getItems().removeAll();
        java.sql.Date sqlDate=convert();
        List<Map> q=query.getEmpLeave(emp_id.getText(),sqlDate);
        if(q.isEmpty())
        {
             leave_type.getItems().add("Unpaid");
        }
        else
        {
          for (int i = 0; i < q.size(); i++) 
          {
            leave_type.getItems().add(q.get(i).get("leave_name").toString());
            
          }
        }
        }catch(Exception e){}
    }
    @FXML
    private void getNoOfLeaves(ActionEvent evt)
    {
        int nol=0,trem=0;
        if(leave_type.getSelectionModel().getSelectedItem().toString().equals("Paid Leave"))
        {
            System.out.println("true");
            Calendar c = Calendar.getInstance();
            int month= c.get(Calendar.MONTH) + 1;
            int year=c.get(Calendar.YEAR);
            List<Map> q=query.getMonthlyLeave(emp_id.getText(), month,year);
            
            for (int i = 0; i < q.size(); i++) 
           {
               
               nol+=Integer.parseInt(q.get(i).get("total").toString());
            
           } 
          trem=nol-Integer.parseInt(total.getText()); 
          
         rem.setText(""+trem);
        }
        else
        {
       List<Map> q=query.getNoOfLeave(leave_type.getSelectionModel().getSelectedItem().toString(),emp_id.getText());
      
          for (int i = 0; i < q.size(); i++) 
          {
            nol=Integer.parseInt(q.get(i).get("remLeaves").toString());
            
        } 
         trem=nol-Integer.parseInt(total.getText()); 
         rem.setText(""+trem);
        }
        if(trem<0)
        {
            status.setDisable(true);
        }
        else
        {
            status.setDisable(false);
        }
    }
    private java.sql.Date convert() throws ParseException
        {
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date t=new java.util.Date();
         String dt=sd.format(t);
         java.util.Date tt=sd.parse(dt);
         java.sql.Date sqlDate = new java.sql.Date(tt.getTime());
         return sqlDate;
        }
    @FXML
    private void handleStatus(ActionEvent evt)
    {
        if(status.isSelected())
        {
            status.setText("Approved");
        }
        else
        {
            status.setText("Pending");
        }
    }
    @FXML
    private void saveEmpLeaves(ActionEvent evt)
    {
       
       // List<Map> q=query.getLeaveIdByName(leave_type.getSelectionModel().getSelectedItem().toString());
        
        int lid=query.getLeaveIdByName(leave_type.getSelectionModel().getSelectedItem().toString());
        EmpLeaveTaken el=new EmpLeaveTaken();
        el.setEmp_id(emp_id.getText());
        Date date1 = java.sql.Date.valueOf(fdate.getValue());
        el.setFromDate(date1);
        date1 = java.sql.Date.valueOf(tdate.getValue());
        el.setToDate(date1);
        el.setTotalDays(Integer.parseInt(total.getText()));
        int s=0;
        if(status.isSelected())
        {
            s=1;
        }
        else
        {
            s=0;
        }
        el.setLeave_id(lid);
        el.setStatus(s);
        el.setApprovedByLead(appBy.getSelectionModel().getSelectedItem().toString());
        el.setApprovedBy(aname.getText());
        el.setRemarks(remarks.getText());
        query.saveEmpTakenLeaves(el);
        if(status.isSelected())
        {
            
            EmpLeave e=new EmpLeave();
            e.setLeavesTaken(Integer.parseInt(total.getText()));
            e.setRemLeaves(Integer.parseInt(rem.getText()));
            e.setEmp_id(emp_id.getText());
            e.setLeave_id(lid);
            query.updateRemLeaves(e);
            if(leave_type.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("paid leave"))
            {
                int t=Integer.parseInt(total.getText());
                
                for(int i=0;i<t;i++)
                {
                List<Map> q=query.getMonthlyLeave(emp_id.getText(), getCurrentMonth(),getCurrentYear());
                int m2=Integer.parseInt(q.get(i).get("month").toString());
                
                  MonthlyLeaves m=new MonthlyLeaves();
                  m.setEmp_id(emp_id.getText());
                  m.setMonth(m2);
                  query.updatePaidLeaves(m);
                 
                }
            }
            
        }
        buildData1();
        
    }
     public void buildData1()
        {
            try{
            query.removeAllRows(ltable);
            query.setColumn(col1, 0);
            query.setColumn(col2, 1);
            query.setColumn(col3, 2);
            query.setColumn(col4, 3);
            query.setColumn(col5, 4);
            query.setColumn(col6, 5);
            query.setColumn(col7, 6);
            query.setColumn(col8, 7);
            Calendar c = Calendar.getInstance();
            int month= c.get(Calendar.MONTH) + 1;
            List<Map> q=query.getAllLeavesBeEmpId(emp_id.getText(),month);
            data=FXCollections.observableArrayList();
            int count=1;
            for (int i = 0; i < q.size(); i++) 
            {
                count++;
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(""+q.get(i).get("id"));
                row.add(""+q.get(i).get("emp_id"));
                row.add(""+q.get(i).get("fromDate"));
                row.add(""+q.get(i).get("toDate"));
                row.add(""+q.get(i).get("totalDays"));
                row.add(""+q.get(i).get("leave_name"));
                if(Integer.parseInt(q.get(i).get("status").toString())==1)
                {
                    row.add("Approved");
                }
                else
                {
                    row.add("Pending"); 
                }
                row.add(""+q.get(i).get("approvedBy"));
                data.add(row);
          
        }
        ltable.setItems(data);
       
        }catch(Exception e)
        {
            e.printStackTrace();
        }
          
        }
        private int getCurrentMonth()
        {
            Calendar c = Calendar.getInstance();
            int month= c.get(Calendar.MONTH) + 1;
            return month;
        }
        private int getCurrentYear()
        {
            Calendar c = Calendar.getInstance();
            int month= c.get(Calendar.YEAR) ;
            return month;
        }
        private int getLeaveDetails()
    {
        int totalLeaves=0;
        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month= c.get(Calendar.MONTH) + 1;
        //twork.setText(""+monthMaxDays);
          List<Map> q=query.getEmpAttendance(emp_id.getText(), month);
          for (int i = 0; i < q.size(); i++) 
          {
            totalLeaves=Integer.parseInt(q.get(i).get("leave").toString());
            
        }
          return totalLeaves;
    }
        
        @FXML
    private void handleClose(MouseEvent evt)
    {
        query.close(employee);
    }
    @FXML
    private void rotate(MouseEvent event)  {
          //close_btn.setRotate(90);
       query.rotate(close_btn);
        //System.out.println("rotate...");
    }
}
