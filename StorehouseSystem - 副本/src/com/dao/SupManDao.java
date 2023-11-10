package com.dao;
import java.awt.Component;
import java.sql.*;

import javax.swing.JComboBox;

import com.until.DBUtil;


public class SupManDao {

	
	
	//实现添加供应商以及删除运营商
	//添加旗下的产品
	
	static Connection con=DBUtil.conn;
	
	
	//定义整数返回值    0失败    1成功
	public static int wiretSup(String name) {
		
		PreparedStatement preSql;//预处理
		int num;//存放结果	
		String sqlStr="INSERT INTO supplier(`name`)VALUES(?)";
		
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	
	
	
	//删除   为0的时候失败    1是成功
	public static int dellSup(String name) {
		
		PreparedStatement preSql;//预处理
		int num;//存放结果	
		String sqlStr="DELETE FROM supplier WHERE `name`=?";
		
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num = preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	
	
	//将运营商和产品都写入数据库内
	public static int wiretSupSun(String subname,String sunname) {
		
		PreparedStatement preSql;//预处理
		int num;//存放结果	
		String sqlStr="INSERT into product (`name`,`supname`) VALUES(?,?)";
		
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sunname);
			preSql.setString(2, subname);
			
			num = preSql.executeUpdate();
			return num;//
			
		}catch(SQLException e) {
			return 3;
		}	
	}
	
	
	public static void readSup(JComboBox cmd1) {
		
		//移除传递过来的所有项目
		
		cmd1.removeAllItems();//移除下拉框所有的东西
		cmd1.addItem("--选择物品大类--");
		
		
		int star=0;//0没数据    1有数据
		
		PreparedStatement preSql;//预处理
		
		String sqlStr="SELECT * FROM supplier";
		ResultSet rs;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
				//执行到这里证明有数据
				if(star==0) {
					star++;
				}
				String tempname = rs.getString("name");
				cmd1.addItem(tempname);
			}
			
			cmd1.repaint();
			
		}catch(SQLException e) {
			
		}
	}


	
	//读取子产品
public static void readSun(JComboBox cmb1,String sup) {
		
		//移除传递过来的所有项目
		
		cmb1.removeAllItems();//移除下拉框所有的东西
		cmb1.addItem("--请选择小类--");		
		int star=0;//0没数据    1有数据
		PreparedStatement preSql;//预处理
		
		String sqlStr="SELECT * FROM product where supname=?";
		ResultSet rs;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();
			while(rs.next()) {
				//执行到这里证明有数据
				if(star==0) {
					star++;
				}
				String tempname = rs.getString("name");
				cmb1.addItem(tempname);
			}
			
			cmb1.repaint();
			
		}catch(SQLException e) {
			
		}
	}
	public static int delSunStock(String sup,String sun){
		PreparedStatement preSql;//预处理
		int num;//存放结果
		String sqlStr="DELETE FROM product WHERE `name`=? and supname=?";

		try {

			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sun);
			preSql.setString(2, sup);
			num = preSql.executeUpdate();
			return num;

		}catch(SQLException e) {
			return 3;
		}
	}


	public static void IntuserDao(JComboBox cmd3) {
//人员
		//移除传递过来的所有项目

		cmd3.removeAllItems();//移除下拉框所有的东西
		cmd3.addItem(" -- 入库人员 -- ");


		int star=0;//0没数据    1有数据

		PreparedStatement preSql;//预处理

		String sqlStr="SELECT * FROM initial";
		ResultSet rs;
		try {

			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
				//执行到这里证明有数据
				if(star==0) {
					star++;
				}
				String tempname = rs.getString("user");
				cmd3.addItem(tempname);
			}

			cmd3.repaint();

		}catch(SQLException e) {

		}
	}


//入库人员下拉栏
//public static void Initial(JComboBox user) {
	//person person = new person();
	//移除传递过来的所有项目
	
	//user.removeAllItems();//移除下拉框所有的东西
	//user.addItem(" - -入库人员- - ");
	//int star=0;//0没数据    1有数据
	//PreparedStatement preSql;//预处理
	
	//String sqlStr="SELECT * FROM initial ";//where `user`=?
	//ResultSet rs;
	//String us = user.getSelectedItem().toString();
	//System.out.print(us);
	//try {
		
	//	preSql=con.prepareStatement(sqlStr);
	//	rs = preSql.executeQuery();
	//	while(rs.next()) {
			//执行到这里证明有数据
	//		if(star==0) {
	//			star++;
	//		}
	//		String tempname = rs.getString("user");
	//		((JComboBox) user).addItem(tempname);
	//	}
		
	//	((Component) user).repaint();
		
	//}catch(SQLException e) {
		
	//}
}
	
	
	

