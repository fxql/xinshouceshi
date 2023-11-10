package com.windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.dao.SupManDao;
import com.manage.item.AddStaffAccout;
import com.manage.panl.InStockPan;
import com.manage.panl.OutStockPan;
import com.manage.panl.SupplierPan;
import com.tool.Tool;

public class ManagePeopleWindows {
	
	String buton[] = {"商品入库","商品出库","添加物品"};//按钮名字
	String butonName[] = {"stockIn","stockOut","supplier"};//名称区分不同
	
	final int WIDTH=900;//顶层框架宽
	final int HEIGHT=600;//顶层框架高
	
	public JFrame jframe=new JFrame();
	
	
	public ManagePeopleWindows() {
		
		
		init();
		jframe.setVisible(true);//设置窗口是否显示
		jframe.setResizable(false);//窗口大小不可变
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认关闭方式
		jframe.validate();//组件生效
	}
	void init() {
		
		jframe.setLayout(null);//设置空布局
		jframe.setTitle("仓库管理系统");//命名
		
		
		//窗口居中
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1 = new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		//没想好啥布局暂时不涉及
		
		
		//设计第一个盒子
		jpanel1.setBounds(5,5,150,HEIGHT-10);
		jpanel1.setBackground(Color.BLUE);
		jpanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		/*
		JButton inButton = new JButton("物品入库");
		jpanel1.add(inButton);
		
		JButton outButton = new JButton("物品出库");
		jpanel1.add(outButton);
		*/
		
		//JButton outButton1 = new JButton("增加供货商");
		//jpanel1.add(outButton1);
		
		//增加菜单栏  存放账号管理   以及增加供应商
		JMenuBar menubar = new JMenuBar();//创建菜单栏
		JMenu menu = new JMenu("账号管理");
		//JMenu menu1 = new JMenu("仓库管理");
		JMenuItem item1_1 = new JMenuItem("添加员工账号");
		//JMenuItem item1_2 = new JMenuItem("删除员工账号");
		menu.add(item1_1);
		//menu.add(item1_2);
		
		JMenuItem item2_1 = new JMenuItem("更改员工账号信息");
		//menu1.add(item2_1);
		
		menubar.add(menu);//将菜单放入菜单栏
		//menubar.add(menu1);
		jframe.setJMenuBar(menubar);
		
		
		
		
		
		//添加员工账号完毕
		item1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStaffAccout a = new AddStaffAccout();
			}
			
		});
		
		
		
		
		
		//入库窗格
		InStockPan inpan = new InStockPan(0,0,665+50,HEIGHT-10);
		//JButton btn = new JButton("测试按钮");
		jpanel2.add(inpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		
		//出库窗格
		OutStockPan outpan = new OutStockPan(0,0,665+50,HEIGHT-10);
		jpanel2.add(outpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		
		//添加运营商
		SupplierPan suppan = new SupplierPan(0,0,665+50,HEIGHT-10);
		jpanel2.add(suppan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		
		
		jpanel2.setBounds(215-50,5,680+50,HEIGHT-10);//设置盘子二
		jpanel2.setBackground(Color.ORANGE);
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
		
		//入库监听
		//对按钮添加事件
		/*outButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将入库设置为顶层
				
				jpanel2.moveToFront(outpan);
				
				
			}
			
		});
		
		
		//出库监听
		//对按钮添加事件
		inButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				jpanel2.moveToFront(inpan);
				
			}
			
		});*/
		
		for(int i=0;i<buton.length;i++) {
			JButton bu = new JButton(buton[i]);
			jpanel1.add(bu);
			bu.setName(butonName[i]);
			bu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					JButton jbl = (JButton)e.getSource();
					if(jbl.getName().equals(butonName[0])) {
						//将商品盘子放到最上面
						jpanel2.moveToFront(inpan);
						SupManDao.readSup(InStockPan.cmbSupName);

						
						
						
					}
					if(jbl.getName().equals(butonName[1])) {
						//将商品盘子放到最上面
						jpanel2.moveToFront(outpan);
						SupManDao.readSup(OutStockPan.cmbSupName);
					}
					if(jbl.getName().equals(butonName[2])) {
						//将商品盘子放到最上面
						jpanel2.moveToFront(suppan);
						
						SupManDao.readSup(suppan.cmb1);
					}
				}
				
			});
				
		}
		
		
		//////////////24装开始
		
		
		
		
		
		
		
		
	}
	
}
