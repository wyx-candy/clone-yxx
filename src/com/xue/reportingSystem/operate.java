package com.xue.reportingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class operate {
	
	//显示所有个人信息
    public void show() {
    	System.out.println("\n学号" + "\t" + "姓名" + "\t" + "性别" + "\t" +  "学院" + "\t" + "是否湖北" + "\t" + "是否武汉");  
        System.out.println("-----------------------------------------------"); 
        String sql = "select * from person";
        Connection conn = JDBCTest.getConn();
        Statement state = null;
        ResultSet rs = null;

        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String college = rs.getString("college");
                String hubei = rs.getString("hubei");
                String wuhan = rs.getString("wuhan");
                
                System.out.println(id + "\t" + name +  "\t" + sex + "\t" + college + "\t     " + hubei + "\t     " + wuhan);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTest.close(rs, state, conn);
        }
    }
    
    //查询具体人员某天疫情信息
    public void query(int id,String date) {
        System.out.println("\n学号\t姓名\t是否接触患者\t是否疑似\t是否确诊\t填写日期");
        String sql = "select * from ill where id ='" + id + "' and DATE_FORMAT(date,'%Y%m%d') = " + date;
        Connection conn = JDBCTest.getConn();
        Statement state = null;
        ResultSet rs = null;
        
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");			//获取姓名
                String contact = rs.getString("contact");	//获取是否接触患者
                String doubt = rs.getString("doubt");		//获取是否
                String confirm = rs.getString("confirm");	//获取是否确诊
                
                System.out.println(id + "\t" + name +  "\t" + contact + "\t\t" + doubt + "\t" + confirm + "\t" + date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCTest.close(rs, state, conn);
        }
    }
    
    public void statistis(int month){
    	int count11 =0,count12 =0;	//分别计数疑似病例的是否
    	int count21 =0,count22 =0;	//分别计数确诊病例的是否
    	int count=0;
    	
    	String sql = "select * from ill where month(date) = " + month;
        Connection conn = JDBCTest.getConn();
        Statement state = null;
        ResultSet rs = null;
        
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                String doubt = rs.getString("doubt");
                String confirm = rs.getString("confirm");
                
                if(doubt.equals("是"))
                	count11 ++;
                else
                	count12 ++;
                
                if(confirm.equals("是"))
                	count21 ++;
                else
                	count22 ++;
            } 
            //打印统计结果
            System.out.println("\n\t ^(状态)");
            System.out.print("\t |");
            
            count = count11/5;
            System.out.print("\n 疑\ty|");
            while((count--)!=0)
            	System.out.print("*");
            count = count12/5;
            System.out.print(" " + count11 + "\n 似\tn|");         
            while((count--)!=0)
            	System.out.print("*");
            
            count = count21/5;
            System.out.print(" " + count12 + "\n\t |\n 确\ty|");
            while((count--)!=0)
            	System.out.print("*");
            count = count22/5;
            System.out.print(" " + count21 + "\n 诊\tn|");
            while((count--)!=0)
            	System.out.print("*");
            System.out.println(" " + count22 + "\n\t  —————————————————————————————————————————————————————————————————————————————>(5人/*)");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCTest.close(rs, state, conn);
        }
    }
}

