package com.dao;

import java.sql.*;


import com.until.DBUtil;

public class LoginDao {
	 //处理登录事件的类型
	static Connection con = DBUtil.conn;//将连接的conn传进来
	//登陆状态  成功是true 失败是false
	
	public static boolean loginStar(String account,String password) {
		
		//Statement ;//定义处理
		
		PreparedStatement preSql;//预处理
		ResultSet rs;//存放结果
		String sqlStr="select * FROM users WHERE account=? AND PASSWORD=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			
			preSql.setString(1, account);
			preSql.setString(2, password);
			
			rs = preSql.executeQuery();//将结果放到rs
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e) {
			return false;
		}
		
	}
	////---------------------
	public static int loginPow(String account,String password) {
		
		//Statement ;//定义处理
		
		PreparedStatement preSql;//预处理
		ResultSet rs;//存放结果
		String sqlStr="select * FROM users WHERE account=? AND PASSWORD=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			
			preSql.setString(1, account);
			preSql.setString(2, password);
			
			rs = preSql.executeQuery();//将结果放到RS
			if(rs.next()) {
				if(rs.getString("pow").equals("2")) {
					return 2;
					//管理员
				}else {
					//普通
					return 1;
				}
			}else {
				return 3;
			}
			
		}catch(SQLException e) {
			return 3;
		}
		
	}
}
