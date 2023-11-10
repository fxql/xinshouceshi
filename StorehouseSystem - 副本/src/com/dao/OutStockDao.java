package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class OutStockDao {
	static Connection con = DBUtil.conn;//将连接的conn传进来
	
	
	
	public static int writeStock(String sup,String sunname,String pri,String user,String ID) {
		PreparedStatement preSql;//预处理
		
		//int num1 = Integer.parseInt(num);//将字符串变成整数字
		
		String sqlStr="INSERT INTO outstock(supname,stockname,outtime,num,pri,user,ID) VALUES(?,?,now(),1,?,?,?)";
		int num=0;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sunname);
			//preSql.setString(3, num1);
			preSql.setString(3, pri);
			preSql.setString(4, user);
			preSql.setString(5, ID);
			
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	
	public static ResultSet findStockoneData(String num) {
		PreparedStatement preSql;//预处理
	
		String sqlStr="SELECT outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.pri,product.stock,outstock.`user` FROM outstock ,product WHERE product.supname=outstock.supname AND product.`name`=outstock.stockname and ID=?";
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


	//删除入库中id相同的内容
	public static ResultSet outinstockdate() {


		PreparedStatement preSql;//预处理


		String sqlStr="DELETE  FROM instock a where exists (select 1 from outstock b where a.id=b.id )";
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


	//查找所有的
	public static ResultSet findStockallData() {
		PreparedStatement preSql;//预处理
	

		String sqlStr="SELECT outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.pri,product.stock,outstock.`user` FROM outstock ,product WHERE product.supname=outstock.supname AND product.`name`=outstock.stockname";
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

	public static ResultSet OutquireDao(String Outsup) {
		PreparedStatement preSql;//预处理

		String data[] = new String[7];
		String sqlStr="SELECT outstock.ID,outstock.supname,outstock.stockname,outstock.outtime,outstock.pri,product.stock,outstock.`user` FROM outstock ,product WHERE product.supname=outstock.supname AND product.`name`=outstock.stockname and outstock.supname=?";//,instock.num
		ResultSet rs=null;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, Outsup);
			rs = preSql.executeQuery();
			return rs;

		}catch(SQLException e) {
			return rs;
		}
	}
	
	
	
	public static int dellStockDate(String id) {
		PreparedStatement preSql;//预处理
		
		//int num1 = Integer.parseInt(num);//将字符串变成整数字

		String sqlStr="delete from outstock where id=?";
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
	//更改数据
	public static int changeStockData(String sup,String sun,String pric,String user,String id) {
		PreparedStatement preSql;//预处理
		
		
		String sqlStr="update outstock set supname=?,stockname=?,pri=?,user=? where id=?";
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
