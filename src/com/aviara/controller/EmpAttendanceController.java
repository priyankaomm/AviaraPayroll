/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.AttendanceLogs;
import com.aviara.bean.Dbinfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.proteanit.sql.DbUtils;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class EmpAttendanceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String file="",drive="";
    @FXML
    private TextField tname;
    @FXML
    private CheckBox set;
    @FXML
    private CheckBox reset;
    @FXML
    private Button browse;
    @FXML
    private Label path;
    @FXML
    private Button connect_btn;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView emptable;
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
    private AnchorPane employee;
    @FXML
    private Label close_btn;
    private ObservableList data;
    Query query=new Query();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buildData();
        try{
        java.sql.Date sqlDate=convert();
        getAttendanceInfo(sqlDate);
        }catch(Exception e){}
    }    
    @FXML
    private void openFileChooser(ActionEvent event) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Access Files",
                        "*.accdb", "*.mdb"));
        chooser.setTitle("Open File");
        File selectedfile = chooser.showOpenDialog(new Stage());
        //System.out.println("File name="+file.getPath());
        file=selectedfile.getName();
        drive=selectedfile.getParent();
        file=drive+"\\"+file;
        System.out.println("Selected file: " + file);
        path.setText(file);
        
    }
    @FXML
    private void connectToAccess(ActionEvent event)
    {
        if(tname.getText().equals(""))
        {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setContentText("Please Enter Attendance Log name!!");
           alert.showAndWait();  
        }
        else
        {
        connect();
        }
        
    }
    @FXML
    private void setDefault(ActionEvent event)
    {
        Dbinfo dbean=new Dbinfo();
        dbean.setStatus(1);
        dbean.setLogname(tname.getText());
        dbean.setPath(file);
        query.saveDbinfo(dbean);
    }
    private void connect()
    {
        
    Connection con = null;
    
    try {
       
       con=DriverManager.getConnection("jdbc:ucanaccess://"+file);
      //con = DriverManager.getConnection("jdbc:odbc:SamplePayroll");
        System.out.println("connected...");
      //Statement sta = con.createStatement(); 
        
         //Connection con1=MyConnection.createConnection();
        java.sql.Date sqlDate=convert();
         String sql="select * from "+tname.getText();
                 //+" where month(AttendanceDate)=?";
         System.out.println("query:"+sql);
         PreparedStatement st=con.prepareStatement(sql);
         //st.setInt(1, sqlDate.getMonth()+1);
        ResultSet rs=st.executeQuery();
        
        List<AttendanceLogs> abean1=new ArrayList<AttendanceLogs>();
        while(rs.next())
        {
            try{
                AttendanceLogs abean=new AttendanceLogs();
                abean.setId(rs.getInt(1));
                abean.setAttendanceDate(rs.getDate(2));
                abean.setDevice_id(rs.getString(3));
                abean.setInTime(rs.getString(4));
                abean.setOutTime(rs.getString(6));
                abean.setPresent(rs.getInt("Present"));
                abean.setPresent(rs.getInt("Absent"));
                abean.setStatus(rs.getString("Status"));
                abean1.add(abean);
                
            }catch(Exception e){
            //e.printStackTrace();
            }
        }
        query.saveAttendance(abean1);  //Batch save query
        System.out.println("after save...");
         
         
        getAttendanceInfo(sqlDate);
      st.close();
      con.close();        
    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
    }
  

    }
    public void buildData()
        {
            try{
           
        //System.out.println(qtable.getColumns());
        List<Map> q=query.getDbinfo();
        
          
        for (int i = 0; i < q.size(); i++) {
            
            ObservableList<String> row = FXCollections.observableArrayList();
           tname.setText(""+q.get(i).get("logname"));
           file=""+q.get(i).get("path");
           browse.setDisable(true);
           //connect_btn.setDisable(true);
           path.setText(file);
           tname.setDisable(true);
           set.setDisable(true);
        }
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            
        }
          private void getAttendanceInfo(java.sql.Date sqlDate)
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
        List<Map> q=query.getAttendance(sqlDate);
         data=FXCollections.observableArrayList();
          int count=1;
        for (int i = 0; i < q.size(); i++) {
            count++;
            ObservableList<String> row = FXCollections.observableArrayList();
           row.add(""+q.get(i).get("emp_id"));
           row.add(""+q.get(i).get("attendanceDate"));
           row.add(""+q.get(i).get("first_name")+" "+q.get(i).get("last_name"));
           row.add(""+q.get(i).get("inTime"));
           row.add(""+q.get(i).get("outTime"));
           row.add(""+q.get(i).get("status"));
          
           //System.out.println("Row [1] added "+row );
           data.add(row);
           //System.out.println("data [1] added "+data );
           //qtable.setItems(data);
           
           
        }
        emptable.setItems(data);
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            //return emptable;
        }
        private java.sql.Date convert() throws ParseException
        {
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         Date t=new Date();
         String dt=sd.format(t);
         Date tt=sd.parse(dt);
         java.sql.Date sqlDate = new java.sql.Date(tt.getTime());
         return sqlDate;
        }
    @FXML
    private void reset(ActionEvent evt)
    {
        browse.setDisable(false);
        tname.setDisable(false);
        tname.setText("");
        set.setDisable(false);
    }
    @FXML
    private void handleClose(MouseEvent evt)
    {
        if(AllEmployeeController.flag)
        {
            query.openAllEmployee(employee);
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
}
