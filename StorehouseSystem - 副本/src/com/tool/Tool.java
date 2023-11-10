package com.tool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Tool {
	//设置窗口居中
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame jframe) {
		
		Toolkit kit = Toolkit.getDefaultToolkit();//获取对象大小，以及设置窗口位置
		Dimension screenSize = kit.getScreenSize();
		int width=screenSize.width;//获取屏幕的高和宽
		int height=screenSize.height;
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		jframe.setBounds(x,y,WIDTH,HEIGHT);
	}
	//添加表格的模块化工具
	public static int addDataTable(ResultSet rs,DefaultTableModel model,int index) {
		
		int count=0;
		model.setNumRows(0);
		String data[] = new String[index];
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
				}
				model.addRow(data);
			}
			rs.close();
			return count;
		}catch(SQLException e) {
			e.printStackTrace();
			return count;

		}
		
	}
}
