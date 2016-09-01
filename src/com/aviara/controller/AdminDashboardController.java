/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;


import aviarapayroll.AviaraPayroll;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.codehaus.groovy.tools.shell.util.SimpleCompletor;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * FXML Controller class
 *
 * @author comp2
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Pane date_pane;
    @FXML
    private Pane time_pane;
    @FXML
    private AnchorPane content; 
    @FXML
    private Pane back;
    @FXML
    private Pane c_menu;
    @FXML
    private Pane e_menu;
    @FXML
    private Pane r_menu;
    @FXML
    private Pane main_pane;
    @FXML
    private Pane words_panel;
    @FXML
    private ContextMenu compony_menu;
    @FXML
    private Pane compony_pane;
    @FXML
    private Label compony;
    @FXML
    private Label today;
    @FXML
    private Label time_label;
    @FXML
    private Label day;
    private boolean isSecondShown=false;
    
    private boolean isWordsShown=false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDate();
        getDay();
    }    
    private void getDate()
    {
        SimpleDateFormat sd=new SimpleDateFormat("dd-MMM-yyyy");
        Date d=new Date();
        today.setText(sd.format(d));
       
    }
    private void getDay()
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sd=new SimpleDateFormat("EEEE", Locale.ENGLISH);
        day.setText(sd.format(date.getTime()));
    }
    
    @FXML
    public void animation(MouseEvent evt)
    {
        if(!isSecondShown)
        {
            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), date_pane);
            slideOut.setByY(-135);
            slideOut.play();
            
            TranslateTransition slideOut1 = new TranslateTransition(Duration.seconds(1), time_pane);
            slideOut1.setByX(-320);
            slideOut1.play();  
            isSecondShown=true;
            
            Task dynamicTimeTask = new Task<Void>() {
	    @Override
	    protected Void call() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
		while (true) {
			updateMessage(sdf.format(new Date()));
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				break;
			}
		}
		return null;
	   }
      };
        time_label.textProperty().bind(dynamicTimeTask.messageProperty());
        Thread t2 = new Thread(dynamicTimeTask);
        t2.setName("Tesk Time Updater");
        t2.setDaemon(true);
        t2.start();
        }
       
    }
    @FXML
    public void handleAddEmployee(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Employee.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewEmployee(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/AllEmployee.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewBank(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpAttendance.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
     @FXML
    public void viewCompony(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Company_Info.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewBranch(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/View_Branch.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewAllowance(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/View_Allowance.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewDepartment(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/View_Department.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewDeduction(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Deduction.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewCalender(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/HolidayCalender.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
           // scene.getStylesheets().add(getClass().getResource("../aviara/style/calendar.css").toExternalForm());
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewPaymentDetails(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/EmpSalary.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewEmpDeduction(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Emp_deduction.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void viewLeave(MouseEvent e) throws IOException
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/Leave.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            root1.setLayoutX((main_pane.getPrefWidth() - root1.getPrefWidth()) / 2);
            root1.setLayoutY((main_pane.getPrefHeight() - root1.getPrefHeight()) / 2);
            //System.out.println("height:"+main_pane.getPrefWidth()+"2nd height:"+root1.getPrefWidth());
            //root1.setLayoutX(290);
            //root1.setLayoutY(25.5);
            fadeTransition(root1);
            main_pane.getChildren().removeAll(main_pane.getChildren());
            main_pane.getChildren().add(root1);
           
           
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    public void openComponyMenu(MouseEvent evt)
    {
        //compony_menu.show(compony, compony_pane.getLayoutX(), compony_pane.getLayoutY());
            c_menu.setVisible(true);
            e_menu.setVisible(false);
            r_menu.setVisible(false);
            /*TranslateTransition slideOut = new TranslateTransition(Duration.seconds(1), c_menu);
            slideOut.setByY(500);
            slideOut.play();*/
            
            /*FadeTransition ft = new FadeTransition(Duration.millis(2000), c_menu);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            RotateTransition rotator = new RotateTransition(Duration.millis(10000), c_menu);
            rotator.setAxis(Rotate.Y_AXIS);
            rotator.setFromAngle(0);
            rotator.setToAngle(360);
            rotator.setInterpolator(Interpolator.LINEAR);
            rotator.setCycleCount(10);
            rotator.play();*/
            scaleTransition(c_menu);
            
    }
    @FXML
    public void openEmpMenu(MouseEvent evt)
    {
        c_menu.setVisible(false);
        r_menu.setVisible(false);
        e_menu.setVisible(true);
        scaleTransition(e_menu);
    }
    @FXML
    public void openReportMenu(MouseEvent evt)
    {
        c_menu.setVisible(false);
        e_menu.setVisible(false);
        r_menu.setVisible(true);
        scaleTransition(r_menu);
    }
    @FXML
    public void closeComponyMenu(MouseEvent evt)
    {
           
        c_menu.setVisible(false);
    }
    @FXML
    public void closeReportMenu(MouseEvent evt)
    {
           
        r_menu.setVisible(false);
    }
    @FXML
    public void closeEmpMenu(MouseEvent evt)
    {
           
        e_menu.setVisible(false);
    }
    public void scaleTransition(Pane node)
    {
            ScaleTransition stShowBack = new ScaleTransition(Duration.millis(1000), node);
            stShowBack.setFromX(0);
            stShowBack.setToX(1);
            stShowBack.play();
            /*TranslateTransition swipeTransition = new TranslateTransition();
            swipeTransition.setNode(node);
            swipeTransition.setDuration(Duration.millis(1000));
            swipeTransition.setToX(node.getPrefWidth());
            swipeTransition.play();*/
    }
    public void fadeTransition(Pane node)
    {
            /*TranslateTransition swipeTransition = new TranslateTransition();
            swipeTransition.setNode(node);
            swipeTransition.setDuration(Duration.millis(1000));
            swipeTransition.setToX(node.getPrefWidth());
            swipeTransition.play();*/
            FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
    }
    @FXML
    public void handleLogout(MouseEvent evt)
    {
        close(evt);
        /*Aviara d=new Aviara();
        d.openLogin();*/
    }
    private void close(MouseEvent evt)  {
          final Node source = (Node) evt.getSource();
          final Stage stage = (Stage) source.getScene().getWindow();
          stage.close();
    }
}
