package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class InStockDao {

	static Connection con = DBUtil.conn;//将连接的conn传进来
	
	
	
	public static int writeStock(String sup,String sunname,String pri,String ID,String user) {
		PreparedStatement preSql;//预处理
		
		//int num1 = Integer.parseInt(num);//将字符串变成整数字
		
		String sqlStr="INSERT INTO instock(supname,stockname,intime,num,pric,ID,user) VALUES(?,?,now(),1,?,?,?)";
		int num=0;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sunname);
			//preSql.setString(3, num);
			preSql.setString(3, pri);
			preSql.setString(4, ID);
			preSql.setString(5, user);
			
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	//查找找单个和全部
	public static ResultSet findStockoneData(String num) {
		PreparedStatement preSql;//预处理
	
		String data[] = new String[7];
		String sqlStr="SELECT instock.ID,instock.supname,instock.stockname,instock.intime,instock.pric,product.stock,instock.`user` FROM instock,product WHERE product.supname=instock.supname AND product.`name`=instock.stockname and id=?";//,instock.num
		ResultSet rs=null;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, num);
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return rs;
		}	
	}
	//查找所有的
	public static ResultSet findStockallData() {
		PreparedStatement preSql;//预处理
	
		String data[] = new String[7];
		String sqlStr="SELECT instock.ID,instock.supname,instock.stockname,instock.intime,instock.pric,product.stock,instock.`user` FROM instock,product WHERE product.supname=instock.supname AND product.`name`=instock.stockname ";//instock.num
		ResultSet rs=null;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return rs;
		}	
	}
	
	public static int dellStockDao(String id) {
		PreparedStatement preSql;//预处理
		
		//int num1 = Integer.parseInt(num);//将字符串变成整数字
		
		String sqlStr="delete from instock where id=?";
		int num=0;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, id);
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}

	public static ResultSet InquireDao(String Insup) {
		PreparedStatement preSql;//预处理

		String data[] = new String[7];
		String sqlStr="SELECT instock.ID,instock.supname,instock.stockname,instock.intime,instock.pric,product.stock,instock.`user` FROM instock,product WHERE product.supname=instock.supname AND product.`name`=instock.stockname and instock.supname=?";//,instock.num
		ResultSet rs=null;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, Insup);
			rs = preSql.executeQuery();
			return rs;

		}catch(SQLException e) {
			return rs;
		}
	}
	public static int changeStockData(String sup,String sun,String pric,String user,String id) {//,String num
		PreparedStatement preSql;//预处理
		
		
		String sqlStr="update instock set supname=?,stockname=?,pric=?,user=? where id=?";//,num=?
		int num1=0;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sun);
			//preSql.setString(3, num);
			preSql.setString(3, pric);
			preSql.setString(4, user);
			preSql.setString(5, id);
			
			num1=preSql.executeUpdate();
			return num1;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	
}
