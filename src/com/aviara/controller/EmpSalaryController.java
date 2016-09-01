/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Salary;
import com.aviara.report.ReportFunction;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author comp2
 */
public class EmpSalaryController implements Initializable {
    Query query=new Query();
    @FXML
    private Button save;
    @FXML
    private Button print;
    @FXML
    private Label emp_id;
    @FXML
    private Label name;
    @FXML
    private Label basic_sal;
    @FXML
    private TextField hra;
    @FXML
    private Label allwnce;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    private ScrollPane dscroll;
    @FXML
    private TextField inc;
    @FXML
    private Label tdeduct;
    @FXML
    private Label total_sal;
    @FXML
    private TextField bonus;
    @FXML
    private Label total1;
    @FXML
    private TextField reim;
     @FXML
    private TextField other;
    @FXML
    private Label twork;
    @FXML
    private Label pdays;
    @FXML
    private Label uleave;
    @FXML
    private Label paid;
    @FXML
    private Label unpaid;
    @FXML
    private Label grand;
    @FXML
    private DatePicker doj;
    @FXML
    private AnchorPane employee;
    @FXML
    private Label close_btn;
    Salary sbean=new Salary();
    String toemail=null;
    DecimalFormat df=new DecimalFormat("#.##");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        java.sql.Date sqlDate=query.convert();
        doj.setValue(sqlDate.toLocalDate());
        emp_id.setText(AllEmployeeController.emp_id);
        getEmployeeSalaryDetails();
        loadMonth();
        loadYear();
        checkSalary();
        getAllowances();
        getLeaveDetails();
        getPaidLeaves();
        total1.setText(""+df.format(calculate()));
        buildData();
        getDeductions();
        getEmail();
       

    }    
    private void getEmployeeSalaryDetails()
    {
          List<Map> q=query.getPayment(emp_id.getText());
          for (int i = 0; i < q.size(); i++) 
          {
            name.setText(q.get(i).get("first_name")+" "+q.get(i).get("last_name"));
            basic_sal.setText(""+q.get(i).get("totalSal"));
            //hra.setText(""+q.get(i).get("hra"));
            //allwnce.setText(""+q.get(i).get("otherAllowance"));
            inc.setText("0.00");
            bonus.setText("0.00");
            reim.setText("0.00");
            other.setText("0.00");
            //LocalDate dt = LocalDate.parse(q.get(i).get("doj").toString());
            //doj.setValue(dt);
          
        }
    }
    private void getAllowances()
    {
          List<Map> q=query.getAllowancesByEmpId(emp_id.getText());
          double a=0.00;
          for (int i = 0; i < q.size(); i++) 
          {
            
            a+=Double.parseDouble(q.get(i).get("value").toString());
          }
          Double total=Double.parseDouble(basic_sal.getText());
          Double t=(a/total)*100;
          allwnce.setText(""+df.format(t));
          //Double gt=total-t;
          //allwnce.setText(""+df.format(a));
    }
    private void getDeductions()
    {
          List<Map> q=query.getDeductionsByEmpId(emp_id.getText());
          double a=0.00;
          for (int i = 0; i < q.size(); i++) 
          {
            
            a+=Double.parseDouble(q.get(i).get("deduction_percent").toString());
          }
          Double total=Double.parseDouble(total1.getText());
          Double t=(a/total)*100;
          Double gt=total-t;
          tdeduct.setText(""+df.format(t));
          total_sal.setText(""+df.format(gt));
          grand.setText(""+Math.round(gt));
          //other.setText("0.00");
    }
    private void getEmail()
    {
          List<Map> q=query.getEmailByEmpId(emp_id.getText());
          
          for (int i = 0; i < q.size(); i++) 
          {
            
            toemail=q.get(i).get("email_id").toString();
          }
          
    }
    private void buildData()
    {
        try{
            Pane p=new Pane();
        
             List<Map> q=query.getDeductionsByEmpId(emp_id.getText());
             int count=1,j=0;
             String [] ids=new String[100];
             String[] name=new String[100];;
             String[] percentage=new String[100];
             for (int i = 0; i < q.size(); i++) 
             {
              count++;j++;
              ids[i]=q.get(i).get("deduction_id").toString();
              name[i]=q.get(i).get("deductio_name").toString();
              percentage[i]=q.get(i).get("deduction_percent").toString();
            
            }
        int x=30,y=20;
        for(int i=0;i<j;i++)
        {
            
            /*CheckBox check=new CheckBox();
            check.setLayoutX(x);
            check.setLayoutY(y);
            check.setId(ids[i]);
            String temp=ids[i];
            */
                
            Label n=new Label(name[i]);
            n.setLayoutX(x);
            n.setLayoutY(y);
            Label per=new Label(percentage[i]+"%");
            per.setLayoutX(n.getLayoutX()+n.getPrefWidth()+100);
            per.setLayoutY(y);
            p.getChildren().addAll(n,per);
            y=y+30;
        }
        dscroll.setContent(p);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            
        }
    private void getLeaveDetails()
    {
        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month= c.get(Calendar.MONTH) + 1;
        twork.setText(""+monthMaxDays);
          List<Map> q=query.getEmpAttendance(emp_id.getText(), month);
          for (int i = 0; i < q.size(); i++) 
          {
            uleave.setText(q.get(i).get("leave").toString());
            
        }
    }
    private void getPaidLeaves()
    {
        int t=0;
        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month= c.get(Calendar.MONTH) + 1;
          List<Map> q=query.getPaidLeaves(emp_id.getText(), month);
          for (int i = 0; i < q.size(); i++) 
          {
             t+= Integer.parseInt(q.get(i).get("totalDays").toString());
            
          }
          paid.setText(""+t);
          unpaid.setText(""+(Integer.parseInt(uleave.getText())-Integer.parseInt(paid.getText())));
          pdays.setText(""+(Integer.parseInt(twork.getText())-Integer.parseInt(unpaid.getText())));
    }
    public Double calculate()
    {
        Double bs=Double.parseDouble(basic_sal.getText());
        int days=Integer.parseInt(twork.getText());
        int up=Integer.parseInt(unpaid.getText());
        Double perday=(bs)/(days);
        Double total=perday*(up);
        total=bs-total;
        total=total+Double.parseDouble(inc.getText())+Double.parseDouble(bonus.getText())+
                Double.parseDouble(allwnce.getText())+Double.parseDouble(reim.getText())-Double.parseDouble(other.getText());
        
        return total;
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
    private void loadMonth()
    {
        month.getItems().addAll("January"
                               ,"February"
                               ,"March"
                               ,"April"
                               ,"May"
                               ,"June"
                               ,"July"
                               ,"August"
                               ,"September"
                               ,"Octomber"
                               ,"November"
                               ,"December");
        month.getSelectionModel().select(getCurrentMonth()-2);
        //checkSalary();
    }
    private void loadYear()
    {
        List<Map> q=query.getAllComponies();
          for (int i = 0; i < q.size(); i++) 
          {
             year.getItems().addAll(q.get(i).get("fyear"),q.get(i).get("tyear"));
             
          }
          year.getSelectionModel().select(0);
          //checkSalary();
    }
    @FXML
    private void save(ActionEvent evt)
    {
        String path=null;
        setSalaryBean();
        query.saveSalary(sbean);
        ReportFunction rf=new ReportFunction();
        try{
        path=rf.createReportByMonth(emp_id.getText(),(month.getSelectionModel().getSelectedIndex()+1),Integer.parseInt(year.getSelectionModel().getSelectedItem().toString()),sbean);
        SendPayslip s=new SendPayslip();
        s.send(path, toemail,sbean);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText("PaySlip Sent successfully!!");
        alert.showAndWait();
        }catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Error while sending mail!!");
        alert.showAndWait();
        }
        
                
    }
    private void setSalaryBean()
    {
        sbean.setEmp_id(emp_id.getText());
        sbean.setName(name.getText());
        java.sql.Date date = java.sql.Date.valueOf(doj.getValue());
        sbean.setSalaryDate(date);
        sbean.setBasic_sal(Double.parseDouble(basic_sal.getText()));
        sbean.setAllowance(Double.parseDouble(allwnce.getText()));
        sbean.setIncrement(Double.parseDouble(inc.getText()));
        sbean.setBonus(Double.parseDouble(bonus.getText()));
        sbean.setReimbursment(Double.parseDouble(reim.getText()));
        sbean.setWorkingDays(Integer.parseInt(twork.getText()));
        sbean.setTotalLeaves(Integer.parseInt(uleave.getText()));
        sbean.setPaidLeaves(Integer.parseInt(paid.getText()));
        sbean.setUnpaidLeaves(Integer.parseInt(unpaid.getText()));
        sbean.setPaybleDays(Integer.parseInt(pdays.getText()));
        sbean.setTotalDeductions(Double.parseDouble(tdeduct.getText()));
        sbean.setTotalSalary(Double.parseDouble(total1.getText()));
        sbean.setGrandTotal(Double.parseDouble(total_sal.getText()));
        sbean.setPaidSalary(Double.parseDouble(grand.getText()));
        sbean.setOther(Double.parseDouble(other.getText()));
        sbean.setMonth(month.getSelectionModel().getSelectedIndex()+1);
        sbean.setMonthInWords(month.getSelectionModel().getSelectedItem().toString());
        sbean.setYear(Integer.parseInt(year.getSelectionModel().getSelectedItem().toString()));
    }
    @FXML
    private void handleCalculate()
    {
        total1.setText(""+df.format(calculate()));
        buildData();
        getDeductions();
    }
    @FXML
    private void check(ActionEvent evt)
    {
        
        checkSalary();
    }
    private void checkSalary()
    {
        List<Map> q=query.checkSalary(emp_id.getText(),month.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(year.getSelectionModel().getSelectedItem().toString()));
        if(q.isEmpty())
        {
           save.setDisable(false);
           print.setDisable(true);
        }
        else
        {
           save.setDisable(true);
           print.setDisable(false);
        }
    }
    @FXML
    private void print(ActionEvent evt)
    {
        openFile();
    }
    private void openFile()
    {
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File("C:/jasperoutput/"+(month.getSelectionModel().getSelectedIndex()+1)+"-"
                +year.getSelectionModel().getSelectedItem().toString()+"/"+emp_id.getText()+".pdf");
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
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
