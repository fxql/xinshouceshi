package com.until;

import java.sql.*;


public class DBUtil {
	
	public static Connection conn = null;
	
	
	public DBUtil(String account ,String password ,String database) {
		//账号   密码   名称
		
		//链接驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功");
		}catch(Exception e) {
			System.out.println("驱动加载失败");
		}
		
		
		try {
			
			//storemanage         是数据库名称
			String url="jdbc:mysql://localhost:3306/storemanage?characterEncoding=utf-8&useSSL=false";
			
			
			
			conn=DriverManager.getConnection(url, account, password);
			System.out.println("数据库加载成功");
		}catch(SQLException e1) {
			System.out.println("-------------------");
			System.out.println("数据库加载失败");
			System.out.println();//打印报错信息
			System.out.println("-------------------");
		}
		
		//链接数据库
	}
	//检测连接是否关闭自动关闭
	//第一个参数  读取数据库的接口
	//第二个参数  是预处理的接口
	public static void CloseDB(ResultSet rs,PreparedStatement stm) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(stm!=null) {
			try {
				stm.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
	    }
	}
}