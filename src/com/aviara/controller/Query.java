/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

import com.aviara.bean.Address;
import com.aviara.bean.Allowance;
import com.aviara.bean.AttendanceLogs;
import com.aviara.bean.Bank;
import com.aviara.bean.Branch;
import com.aviara.bean.Company_Info;
import com.aviara.bean.Contact;
import com.aviara.bean.Dbinfo;
import com.aviara.bean.Deduction;
import com.aviara.bean.Deduction1;
import com.aviara.bean.Department;
import com.aviara.bean.EmpAllowance;
import com.aviara.bean.EmpLeave;
import com.aviara.bean.EmpLeaveTaken;
import com.aviara.bean.Employee;
import com.aviara.bean.Experiance;
import com.aviara.bean.Holiday;
import com.aviara.bean.Leave;
import com.aviara.bean.Leave1;
import com.aviara.bean.MonthlyLeaves;
import com.aviara.bean.PaymentDetails;
import com.aviara.bean.Qualification;
import com.aviara.bean.Salary;
import com.aviara.bean.Userregistration;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 *
 * @author comp2
 */
public class Query {
     public void rotate(Label btn)
    {
        RotateTransition rotation=new RotateTransition(Duration.millis(500), btn);
        rotation.setByAngle(180);
        rotation.play();
    }
     public void close(AnchorPane employee)
    {
        employee.setVisible(false);
    }
    public boolean saveEmployeeInfo(Employee ebean)
    {
        boolean flag=false;
        try{
        
        System.out.println("processing emp info..........."); 
        //creating session object  
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveEmployeeContact(Contact cbean)
    {
        boolean flag=false;
        try{
        System.out.println("processing contact..........."); 
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveAttendance(List<AttendanceLogs> cbean)
    {
        boolean flag=false;
        try{
        //System.out.println("processing attendance..........."); 
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction(); 
        for ( int i=0; i<cbean.size(); i++ ) {
        AttendanceLogs abean=new AttendanceLogs();
        abean=cbean.get(i);
         session.save(abean);
        }
        //session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close(); 
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            
           // e.printStackTrace();
        }
        return flag;
    }
    public boolean saveDbinfo(Dbinfo cbean)
    {
        boolean flag=false;
        try{
        //System.out.println("processing attendance..........."); 
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close(); 
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveEmployeeAddress(Address cbean)
    {
        boolean flag=false;
        try{
        System.out.println("processing address..........."); 
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveEmployeeQualification(Qualification cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public List<Qualification> fetchQualification(Qualification qbean)
    {
        boolean flag=false;
        List<Qualification> allUsers=null;
        ObservableList<Qualification> list=FXCollections.observableArrayList();
        try{
        
        Session session=getTransaction();
        org.hibernate.Query query = session.createSQLQuery("select Id,degree,university,collage_name,marks from qtemp where "
                + "emp_id = '" + qbean.getEmp_id() + "'");
        
         allUsers = query.setResultTransformer(Transformers.aliasToBean(Qualification.class) ).list();
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return allUsers;
    }
    public List<Employee> fetchEmployee()
    {
        
        List<Employee> emp=new ArrayList<Employee>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Employee";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Employee e=(Employee)results.get(i);
            System.out.println(i+":"+e.getEmp_id());
            emp.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return emp;
    }
    public List<?> getReport() {
    Session session = getTransaction();
     String hql = "select e.emp_id as 'EmpId',first_name as 'FirstName',last_name as 'LastName',designation as 'Designation' " +
                                    ",dept as 'Department',contact_no as 'ContactNo' from employee e,contact_info c where e.emp_id=c.emp_id ";
    org.hibernate.Query  query = session.createQuery(hql);
   
    System.out.println(query.list());
    return query.list();

    }
    public List<Map> listEmployeesScalar( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select e.emp_id,first_name,last_name,designation  " +
         ",dept  ,contact_no  from employee e,contact_info c where e.emp_id=c.emp_id";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         //query.setFirstResult(1);
         //query.setMaxResults(10);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getPayment(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select first_name,last_name,doj,basicSal,hra,otherAllowance,totalSal "
                 + "from employee e,emp_payment p where e.emp_id=p.emp_id and e.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getAllowancesByEmpId(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select a.allowance_id,value from allowance_info a,emp_allowance ea where  a.allowance_id=ea.allowance_id and ea.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getDeductionsByEmpId(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select a.deduction_id,deductio_name,deduction_percent from deduction_info a,emp_deduction ea where  a.deduction_id=ea.deduction_id and ea.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getLeavesByEmpId(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select a.leave_id,applicableFrom from leave_info a,emp_leave ea where  a.leave_id=ea.leave_id and ea.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getEmailByEmpId(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select email_id from contact_info c,employee e where  e.emp_id=c.emp_id and e.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getEmpContact(String emp_id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select country1,state1,city1,pincode1,address1,contact_no,alt_contact,email_id from contact_info c,address_info a where c.emp_id=a.emp_id and c.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getPaymentDetailsByEmpId(String emp_id){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select emp_type,basicSal,duration,hra,otherAllowance,totalSal,nextPay,incDate from emp_payment ep,employee e "
                 + "where e.emp_id=ep.emp_id and e.emp_id='"+emp_id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getAllComponies(){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select company_name,fmonth,fyear,tyear,tmonth from company_info";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> checkSalary(String emp_id,String month,int year){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from emp_salary where emp_id='"+emp_id+"' and monthInWords='"+month+"' and year="+year;
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getAllUser(Userregistration ubean){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from registration_info where username='"+ubean.getUsername()+"' "
                 + "and password='"+ubean.getPassword()+"' and compony_id="+ubean.getCompony_id();
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getEmpAttendance(String emp_id,int month ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select count(present) as 'leave' from attendancelogs a,employee e where e.device_id=a.device_id " +
                      "and e.emp_id='"+emp_id+"' and present=1 and  month(a.attendanceDate)='"+month+"' and a.attendanceDate not in (select `date` from holidays where month(`date`)='"+month+"')";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getPaidLeaves(String emp_id,int month ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select totalDays from empleavetaken where emp_id='"+emp_id+"' and month(fromDate)='"+month+"' and status=1";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getAttendance(java.sql.Date sqlDate ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select e.emp_id,attendanceDate,first_name,last_name," +
         "inTime,outTime,status  from employee e,attendancelogs c where e.device_id=c.device_id and attendanceDate='"+sqlDate+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         //query.setFirstResult(1);
         //query.setMaxResults(10);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getDbinfo( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from dbinfo where status=1";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         //query.setFirstResult(1);
         //query.setMaxResults(10);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> leaves( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
          java.util.Date date=new java.util.Date();
          java.sql.Date sqlDate =  new java.sql.Date(date.getTime());
         tx = session.beginTransaction();
         String sql = "select `status`,emp_id from emp_leave where date='"+sqlDate+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         //query.setFirstResult(1);
         //query.setMaxResults(10);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> listHoliday(int m,int y){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from holidays where month(date)='"+m+"' and year(date)='"+y+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         //query.setFirstResult(1);
         //query.setMaxResults(10);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
             
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getDeductions( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from deduction_info";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
   public List<Map> getHolidays(java.sql.Date sdate){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from holidays where date='"+sdate+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
   
    public List<Map> getEmpName(String id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from employee where emp_id='"+id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
     public List<Map> getAllLeavesBeEmpId(String id,int month ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select id,leave_name,emp_id,fromDate,toDate,totalDays,`status`,approvedByLead,approvedBy,remarks from "
                 + "empleavetaken et,leave_info l where l.leave_id=et.leave_id and et.emp_id='"+id+"' and month(fromDate)='"+month+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public int getLeaveIdByName(String name ){
      Session session = getTransaction();
      Transaction tx = null;
       int lid=0;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select leave_id from leave_info where leave_name='"+name+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
        
         for(int i=0;i<t.size();i++)
         {
             lid=Integer.parseInt(t.get(i).get("leave_id").toString());
             System.out.println("leave id="+lid);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return lid;
   }
    public String getLeaveNameById(int id){
      Session session = getTransaction();
      Transaction tx = null;
       String lname="";
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select leave_name from leave_info where leave_id='"+id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
        
         for(int i=0;i<t.size();i++)
         {
             lname=t.get(i).get("leave_name").toString();
             //System.out.println("leave id="+lid);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return lname;
   }
    public int getComponyIdByReg(String no ){
      Session session = getTransaction();
      Transaction tx = null;
       int lid=0;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select company_id from company_info where reg_no='"+no+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
        
         for(int i=0;i<t.size();i++)
         {
             lid=Integer.parseInt(t.get(i).get("company_id").toString());
             System.out.println("company_id="+lid);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return lid;
   }
    public int getComponyIdByName(String no ){
      Session session = getTransaction();
      Transaction tx = null;
       int lid=0;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select company_id from company_info where company_name='"+no+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
        
         for(int i=0;i<t.size();i++)
         {
             lid=Integer.parseInt(t.get(i).get("company_id").toString());
             System.out.println("company_id="+lid);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return lid;
   }
    public java.sql.Date convert() 
        {
            java.sql.Date sqlDate=null;
            try{
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date t=new java.util.Date();
         String dt=sd.format(t);
         java.util.Date tt=sd.parse(dt);
          sqlDate= new java.sql.Date(tt.getTime());
            }catch(Exception e){}
         return sqlDate;
        }
    public List<Map> getEmpLeave(String id ,Date sqlDate){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select leave_name from leave_info l,emp_leave el,employee e where e.emp_id=el.emp_id and "
                 + "el.emp_id='"+id+"' and l.leave_id=el.leave_id and el.applicableFrom <= '"+sqlDate+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getNoOfLeave(String lname,String id ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select remLeaves from leave_info l,emp_leave el where l.leave_id=el.leave_id and l.leave_name='"+lname+"' "
                 + "and  el.emp_id='"+id+"'";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public List<Map> getMonthlyLeave(String id ,int month,int year){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from monthly_leaves where emp_id='"+id+"' and month<='"+month+"' and year<='"+year+"' and isTaken=0";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
     public List<Map> getLeaves( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from leave_info";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
     
    public List<Map> getAllowances( ){
      Session session = getTransaction();
      Transaction tx = null;
      List<Map> t=new ArrayList<Map>();
      try{
         tx = session.beginTransaction();
         String sql = "select * from allowance_info";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
         List data = query.list();
         for(Object object : data)
         {
            Map row = (Map)object;
            t.add(row);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return t;
   }
    public boolean saveEmployeeExperiance(Experiance cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean savePayment(PaymentDetails cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("Payment successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveDeduction(Deduction cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
     public boolean saveLeaves(EmpLeave cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("leaves successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
     public boolean saveMonthlyLeaves(MonthlyLeaves cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("leaves successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
     public boolean saveAllowance(EmpAllowance cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
     public boolean saveSalary(Salary cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("salary successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
      public boolean saveEmpTakenLeaves(EmpLeaveTaken cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
      public boolean updateRemLeaves(EmpLeave cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
        int lt=cbean.getLeavesTaken();
        Transaction t=session.beginTransaction();  
         org.hibernate.Query qry = session.createQuery("update EmpLeave e set e.leavesTaken=e.leavesTaken+:newlt,e.remLeaves=:newrt "+
          "where e.emp_id=:oldId and e.leave_id=:lid");
	        qry.setParameter("newlt",cbean.getLeavesTaken());
                qry.setParameter("newrt",cbean.getRemLeaves());
                qry.setParameter("oldId",cbean.getEmp_id());
                qry.setParameter("lid",cbean.getLeave_id());
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        System.out.println("Numer of records effected due to delete query"+res);
        t.commit();
        session.close();
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
      public boolean updateEmpInfo(Employee ebean)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("update Employee e set e.firstName=:newFname,e.midName=:newMname,e.lastName=:newLname,e.device_id=:dev_id,"+
                "e.dob=:newDob,e.doj=:newDoj,e.gender=:newGender,e.designation=:newDesg,e.department=:newDept,e.branch=:newBranch," +
                "e.dor=:newDor,e.lastPayment=:newLp where e.emp_id=:oldId");
	        qry.setParameter("newFname",ebean.getFirstName());
                qry.setParameter("newMname",ebean.getMidName());
                qry.setParameter("newLname",ebean.getLastName());
                qry.setParameter("dev_id",ebean.getDevice_id());
                qry.setParameter("newDob",ebean.getDob());
                qry.setParameter("newDoj",ebean.getDoj());
                qry.setParameter("newGender",ebean.getGender());
                qry.setParameter("newDesg",ebean.getDesignation());
                qry.setParameter("newDept",ebean.getDepartment());
                qry.setParameter("newBranch",ebean.getBranch());
                qry.setParameter("newDor",ebean.getDor());
                qry.setParameter("newLp",ebean.getLastPayment());
                qry.setParameter("oldId",ebean.getEmp_id());
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
      public boolean updateEmpAddress(Address ebean)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("update Address e set e.country1=:newFname,e.state1=:newMname,e.city1=:newLname,e.pincode1=:dev_id,"+
                "e.address1=:newDob where e.emp_id=:oldId");
	        qry.setParameter("newFname",ebean.getCountry1());
                qry.setParameter("newMname",ebean.getState1());
                qry.setParameter("newLname",ebean.getCity1());
                qry.setParameter("dev_id",ebean.getPincode1());
                qry.setParameter("newDob",ebean.getAddress1());
                qry.setParameter("oldId",ebean.getEmp_id());
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
      public boolean deleteDeductions(String eid)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("delete Deduction where emp_id = :oldId");
	        qry.setParameter("oldId",eid);
                
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
       public boolean deleteAllowances(String eid)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("delete EmpAllowance where emp_id = :oldId");
	        qry.setParameter("oldId",eid);
                
	        int res = qry.executeUpdate();
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
        public boolean deletePayment(String eid)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("delete PaymentDetails where emp_id = :oldId");
	        qry.setParameter("oldId",eid);
                
	        int res = qry.executeUpdate();
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
       public boolean deleteLeaves(String eid)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("delete Leave where emp_id = :oldId");
	        qry.setParameter("oldId",eid);
                
	        int res = qry.executeUpdate();
                qry = session.createQuery("delete MonthlyLeaves where emp_id = :oldId");
	        qry.setParameter("oldId",eid);
                
	        qry.executeUpdate();
	        System.out.println("Command successfully executed....");
	        
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
      public boolean updateEmpContact(Contact ebean)
    {
        boolean flag=false;
        try{
                Session session=getTransaction();
                
                Transaction t=session.beginTransaction();  
                org.hibernate.Query qry = session.createQuery("update Contact e set e.contact_no=:newFname,e.alt_contact=:newMname,e.email_id=:newLname"+
                " where e.emp_id=:oldId");
	        qry.setParameter("newFname",ebean.getContact_no());
                qry.setParameter("newMname",ebean.getAlt_contact());
                qry.setParameter("newLname",ebean.getEmail_id());
                qry.setParameter("oldId",ebean.getEmp_id());
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        
                t.commit();
                session.close();
                
                flag=true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        return flag;
    }
       public boolean updatePaidLeaves(MonthlyLeaves cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
        
        Transaction t=session.beginTransaction();  
         org.hibernate.Query qry = session.createQuery("update MonthlyLeaves e set e.isTaken=1 "+
          "where e.emp_id=:oldId and e.month=:oldMonth");
	       
                qry.setParameter("oldId",cbean.getEmp_id());
                qry.setParameter("oldMonth",cbean.getMonth());
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        System.out.println("Numer of records effected due to delete query"+res);
        t.commit();
        session.close();
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
      public boolean updateUserComponyId(String uname,int compony_id)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
        //int lt=cbean.getLeavesTaken();
        Transaction t=session.beginTransaction();  
         org.hibernate.Query qry = session.createQuery("update Userregistration e set e.compony_id=:newlt "+
          "where e.username=:oldId ");
	        qry.setParameter("newlt",compony_id);
                qry.setParameter("oldId",uname);
                
	        int res = qry.executeUpdate();
 
	        System.out.println("Command successfully executed....");
	        System.out.println("Numer of records effected due to delete query"+res);
        t.commit();
        session.close();
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveLeave(Leave cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("deduction successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveBank(Bank cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("bank details successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveHoliday(Holiday cbean)
    {
        boolean flag=false;
        try{
        
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        //cbean.setEmp_id("123");
        session.persist(cbean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("Holiday successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public List<Experiance> fetchExperiance(Experiance qbean)
    {
        boolean flag=false;
        List<Experiance> allUsers=null;
        ObservableList<Experiance> list=FXCollections.observableArrayList();
        try{
        
        Session session=getTransaction();
        org.hibernate.Query query = session.createSQLQuery("select Id,experiance,compony_name,designation from emp_experiance where "
                + "emp_id = '" + qbean.getEmp_id() + "'");
       
         allUsers = query.setResultTransformer(Transformers.aliasToBean(Experiance.class) ).list();
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return allUsers;
    }
    private Session getTransaction()
    {
        Transaction t=null;
        Session session=null;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        session=factory.openSession();  
      
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return session;
    }
    public void setColumn(TableColumn tc,int count)
        {
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                        
	                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                             

	                        return new SimpleStringProperty(param.getValue().get(count).toString());                       

	                    }                   

	                });
        }
    public void removeAllRows(TableView qtable){
        for ( int i = 0; i<qtable.getItems().size(); i++) {
        qtable.getItems().clear(); 
        } 
        }
    
    
    public boolean saveCompanyInfo(Company_Info ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
   
    public boolean saveBranchInfo(Branch ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveDepartmentInfo(Department ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean saveAllowanceInfo(Allowance ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveDeductionInfo(Deduction ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveLeaveInfo(Leave ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    /*
    public GridPane fetchData()
    {
        try{
        Session session=getTransaction();
        String hql = "FROM Company_Info";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
       
    }*/
    public List<Department> fetchDepartment()
    {
        
        List<Department> dept=new ArrayList<Department>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Department";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Department e=(Department)results.get(i);
            System.out.println(i+":"+e.getDept_name());
            dept.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return dept;
    }
    
    public List<Leave1> fetchLeave()
    {
        
        List<Leave1> dept=new ArrayList<Leave1>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Leave1";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Leave1 e=(Leave1)results.get(i);
            //System.out.println(i+":"+e.getDept_name());
            dept.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return dept;
    }
    
     public List<Allowance> fetchAllowance()
    {
        
        List<Allowance> allow=new ArrayList<Allowance>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Allowance";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Allowance e=(Allowance)results.get(i);
           // System.out.println(i+":"+e.getDept_name());
            allow.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return allow;
    }
     
     
     public List<Deduction1> fetchDeduction()
    {
        
        List<Deduction1> deduct=new ArrayList<Deduction1>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Deduction1";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Deduction1 e=(Deduction1)results.get(i);
            System.out.println(i+":"+e.getDeductio_name());
            deduct.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return deduct;
    }
     
    public List<Branch> fetchBranch()
    {
        
        List<Branch> brnch=new ArrayList<Branch>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Branch";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Branch e=(Branch)results.get(i);
            //System.out.println(i+":"+e.getB());
            brnch.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return brnch;
    }
    public List<Company_Info> fetchCompany_Info()
    {
        
        List<Company_Info> info=new ArrayList<Company_Info>();
        System.out.println("in fun");
        try{
        Session session=getTransaction();
        String hql = "FROM Company_Info";
        org.hibernate.Query query = session.createQuery(hql);
        //query.setFirstResult(1);
        //query.setMaxResults(10);
        List results = query.list();
        
        for(int i=0;i<results.size();i++){
            Company_Info e=(Company_Info)results.get(i);
            //System.out.println(i+":"+e.getB());
            info.add(e);
             
         }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return info;
    }
    public boolean saveDeductionInfo1(Deduction1 ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveLeaveInfo1(Leave1 ebean)
    {
        boolean flag=false;
        try{
        Configuration cfg=new Configuration();  
        cfg.configure("com/aviara/configuration/hibernate.cfg.xml");//populates the data of the configuration file  
      
        //creating seession factory object  
        SessionFactory factory=cfg.buildSessionFactory();  
      
        //creating session object  
        Session session=factory.openSession();  
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ebean);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean saveUserregistrationInfo(Userregistration ureg)
    {
        boolean flag=false;
        try{
        
        System.out.println("processing User Registration info..........."); 
        //creating session object  
        Session session=getTransaction();
      
        //creating transaction object  
        Transaction t=session.beginTransaction();  
        session.persist(ureg);//persisting the object  
        t.commit();//transaction is commited  
        session.close();  
        System.out.println("successfully saved"); 
        flag=true;
        }catch(Exception e)
        {
            //e.printStackTrace();
        }
        return flag;
    }
    
    public void openAllEmployee(AnchorPane employee) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/AllEmployee.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Admin");
            Scene scene=new Scene(root1);
            stage.setScene(scene);  
            Pane main_pane = (Pane)employee.getParent();
            //((Label)root1.lookup("#e_id")).setText(eid);
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
