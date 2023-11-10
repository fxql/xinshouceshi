package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.until.DBUtil;

public class AddAcount {
	static Connection con = DBUtil.conn;//将连接的conn传进来
	public static int writeAccount(String account,String password,String sname,String saddress,String semail) {
		PreparedStatement preSql;//预处理
		
		//int num1 = Integer.parseInt(num);//将字符串变成整数字
		
		String sqlStr="INSERT INTO users(account,`password`,sname,saddress,semail) VALUES(?,?,?,?,?)";
		int num=0;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			preSql.setString(3, sname);
			preSql.setString(4, saddress);
			preSql.setString(5, semail);
			
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
}
