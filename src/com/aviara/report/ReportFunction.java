/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.report;


import com.aviara.bean.Salary;
import com.aviara.controller.NumberToString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Aditya
 */
public class ReportFunction {
    public String createReportByMonth(String emp_id,int m,int y,Salary sbean) throws IOException, JRException, SQLException
    {
        String path=null;
          try
        {
            JasperReport jasperReport=JasperCompileManager.compileReport("Payslip.jrxml");
            Class.forName("com.mysql.jdbc.Driver");
	    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll","root","vertrigo");
            Statement stmt = null;
            ResultSet rset = null;
            String queryString = "SELECT" +
            "     deduction_info.`deductio_name` AS deduction_info_deductio_name," +
            "     deduction_info.`deduction_percent` AS deduction_info_deduction_percent," +
            "     emp_salary.`id` AS emp_salary_id," +
            "     emp_salary.`emp_id` AS emp_salary_emp_id," +
            "     emp_salary.`name` AS emp_salary_name," +
            "     emp_salary.`salaryDate` AS emp_salary_salaryDate," +
            "     emp_salary.`basic_sal` AS emp_salary_basic_sal," +
            "     emp_salary.`allowance` AS emp_salary_allowance," +
            "     emp_salary.`increment` AS emp_salary_increment," +
            "     emp_salary.`bonus` AS emp_salary_bonus," +
            "     emp_salary.`reimbursment` AS emp_salary_reimbursment," +
            "     emp_salary.`workingDays` AS emp_salary_workingDays," +
            "     emp_salary.`totalLeaves` AS emp_salary_totalLeaves," +
            "     emp_salary.`paidLeaves` AS emp_salary_paidLeaves," +
            "     emp_salary.`unpaidLeaves` AS emp_salary_unpaidLeaves," +
            "     emp_salary.`paybleDays` AS emp_salary_paybleDays," +
            "     emp_salary.`totalSalary` AS emp_salary_totalSalary," +
            "     emp_salary.`totalDeductions` AS emp_salary_totalDeductions," +
            "     emp_salary.`grandTotal` AS emp_salary_grandTotal," +
            "     emp_salary.`paidSalary` AS emp_salary_paidSalary," +
            "     emp_salary.`month` AS emp_salary_month," +
            "     emp_salary.`year` AS emp_salary_year," +
            "     emp_salary.`other` AS emp_salary_other," +
            "     employee.`doj` AS employee_doj," +
            "     employee.`dept` AS employee_dept," +
            "     employee.`branch` AS employee_branch," +
            "     employee.`designation` AS employee_designation," +
            "     emp_bank.`acc_no` AS emp_bank_acc_no," +
            "     emp_bank.`pf_no` AS emp_bank_pf_no" +
            " FROM" +
            "     `employee` employee INNER JOIN `emp_bank` emp_bank ON employee.`emp_id` = emp_bank.`emp_id`" +
            "     INNER JOIN `emp_salary` emp_salary ON employee.`emp_id` = emp_salary.`emp_id`" +
            "     INNER JOIN `emp_deduction` emp_deduction ON employee.`emp_id` = emp_deduction.`emp_id`" +
            "     INNER JOIN `deduction_info` deduction_info ON emp_deduction.`deduction_id` = deduction_info.`deduction_id` "
            + "where employee.`emp_id`='"+emp_id+"' and emp_salary.`salaryDate`='"+sbean.getSalaryDate()+"' and emp_salary.`month`='"+sbean.getMonth()+"' and emp_salary.`year`='"+sbean.getYear()+"'";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            JRResultSetDataSource jasperReports = new JRResultSetDataSource(rset);
            Map<String, Object> map = new HashMap<String, Object>();
            NumberToString n=new NumberToString();
            map.put("month",sbean.getMonthInWords());
            map.put("amt_words",n.convertNumberToWords(sbean.getPaidSalary().intValue()));
            JasperPrint print = JasperFillManager.fillReport(jasperReport,map, jasperReports);
            JasperViewer jv = new JasperViewer( print, false );
            jv.viewReport( print, false );
            // Make sure the output directory exists.
           File outDir = new File("C:/jasperoutput/"+m+"-"+y);
           outDir.mkdirs();
           String file="C:/jasperoutput/"+m+"-"+y+"/"+emp_id+".pdf";
        // PDF Exportor.
           JRPdfExporter exporter = new JRPdfExporter();
           File pdf = File.createTempFile("output.", ".pdf");
           JasperExportManager.exportReportToPdfFile(print,
               file);
        path=file;
        System.out.print("Done!");
        
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    
     return path;
    }
    
}
