package com.windows;

import com.dao.StaffStokDao;
import com.manage.item.AddStaffAccout;
import javax.swing.JFrame;
import com.until.DBUtil;

public class StoreSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//打开登录页面，第二步连接数据库

		
		DBUtil dbutil = new DBUtil("root","021023","storemanage");
		Login lojin = new Login("仓库管理系统");
		
//		JFrame frame = new JFrame();
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		//ManagePeopleWindows A = new ManagePeopleWindows();
		//StaiffWindows A = new StaiffWindows();
		//StaffStokDao.showTime("3");
		//DBUtil dbutil = new DBUtil("root","021023","storemanage");
		//AddStaffAccout a = new AddStaffAccout();
	}

}
