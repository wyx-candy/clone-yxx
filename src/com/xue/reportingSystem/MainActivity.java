package com.xue.reportingSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class MainActivity {
	static Scanner input=new Scanner(System.in);//创建一个键盘扫描类对象
	
	public static void main(String[] args) throws SQLException{
		int op;	//菜单操作数
		operate ope=new operate();	//操作类对象
		   
		do{
			System.out.println("\n1.查看所有人员信息      2.查询某人某天疫情信息      3.显示某月疫情情况      4.退出");
			System.out.println("-------------------------------------------------------------------");
			System.out.print("\n请选择您所需要的服务：");
			op=input.nextInt(); //输入整型
			
			if(op==1)	//显示所有人员信息
				ope.show();
			if(op==2)	//查询具体人员具体日期疫情信息
			{
				String data;//日期
				int id;
				System.out.print("请输入人员id：");
				id=input.nextInt(); //输入整型
				System.out.print("请输入查询日期(格式yyyyMMdd):");
				data = input.next(); //输入字符串
				
				ope.query(id,data);
			}
			if(op==3)
			{
				int mon;
				System.out.print("请输入查询月份(2/3/4/5)：");
				mon = input.nextInt();
				
				ope.statistis(mon);
			}
				
		}while(op!=4);
		   
	}

	

}
