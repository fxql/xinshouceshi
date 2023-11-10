package com.manage.panl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.dao.SupManDao;

public class SupplierPan extends JPanel{
	static int num = 1;//添加次数
	final int WIDTH=730;//顶层框架宽
	final int HEIGHT=50;//顶层框架高
	public JComboBox cmb1;
	public static JTextField  jt1;
	
	public SupplierPan(int x,int y,int width,int height){
		//第一个wh是所在位置
		//第二个wwss是
		this.setBounds(x,y,width,height);
	
		init();
	}
	
	void init(){
		
		this.setBackground(Color.yellow);
		this.setLayout(null);
		JPanel jpan1 = new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,31,15));//居中
		jpan1.setBounds(0,0,WIDTH,100);
		jpan1.setBackground(Color.orange);
		this.add(jpan1);
		
		
		JLabel jl1 = new JLabel("物品大类");
		jt1 = new JTextField(8);
		JButton jb1 = new JButton("添加大类");
		JButton jb5 = new JButton("删除大类");



		jpan1.add(jl1);
		jpan1.add(jt1);
		jpan1.add(jb1);
		jpan1.add(jb5);

		//新建标签
		JLabel JL1 = new JLabel("物品大类");
		jpan1.add(JL1);


		JComboBox cmbSupName = new JComboBox();
		cmbSupName.addItem("--请选择大类--");
		jpan1.add(cmbSupName);




		JLabel JL2 = new JLabel("物品小类");
		jpan1.add(JL2);

		JComboBox cmbStockName = new JComboBox();
		cmbStockName.addItem("--请选择小类--");
		jpan1.add(cmbStockName);

		JButton jb = new JButton("删除大类旗下的小类");
		jpan1.add(jb);

		//下拉框监听
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				SupManDao.readSun(cmbStockName,(String)cmbSupName.getSelectedItem());
			}

		});

		SupManDao.readSup(cmbSupName);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//将满足条件的进行删除
				if (cmbSupName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择大类","消息",JOptionPane.WARNING_MESSAGE);
				}else if (cmbStockName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择小类","消息",JOptionPane.WARNING_MESSAGE);
				}else{
					String sup = (String) cmbSupName.getSelectedItem();
					String sun = (String) cmbStockName.getSelectedItem();
					int a = SupManDao.delSunStock(sup,sun);
					if (a==1){
						JOptionPane.showMessageDialog(null, "删除成功","消息",JOptionPane.WARNING_MESSAGE);
						SupManDao.readSun(cmbStockName,(String)cmbSupName.getSelectedItem());
					}if (a==0){
						JOptionPane.showMessageDialog(null, "删除失败","消息",JOptionPane.WARNING_MESSAGE);
					}if (a==3){
						JOptionPane.showMessageDialog(null, "删除功能报错","消息",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		//定义盘子二  和总体宽度  一致
		JPanel jpan2 = new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		jpan2.setBackground(Color.CYAN);
		jpan2.setBounds(0,110,WIDTH,470);
		this.add(jpan2);
		
		
		
		JLabel jl2 = new JLabel("供应商");
		cmb1 = new JComboBox();
		cmb1.addItem("--请选择供应商--");
		JButton jb2 = new JButton("添加旗下产品");
		//JButton jb6 = new JButton("删除旗下产品");
		JButton jb3 = new JButton("保存数据");
		JButton jb4 = new JButton("重置");
		

		//JTextField jt2=new JTextField(8);

		
		jpan2.add(jl2);
		jpan2.add(cmb1);
		jpan2.add(jb2);
		jpan2.add(jb3);
		jpan2.add(jb4);

		//JLabel JL11=new JLabel("子产品名称");
		//jpan2.add(jt2);
		//jpan2.add(jb6);
		
		///在定义一个盘子  放进盘子2中    做套娃
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp3.setBackground(Color.PINK);
		jp3.setPreferredSize(new Dimension(200,420));
		JTextField A = new JTextField(12);
		JLabel B = new JLabel("产品名称");
		A.setName("sun");//子类的意思
		jp3.add(B);
		jp3.add(A);
		
		jpan2.add(jp3);
		
		
		
		
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(num<5){
					JTextField A = new JTextField(12);
					JLabel B = new JLabel("产品名称");
					A.setName("sun");//子类的意思
					jp3.add(B);
					jp3.add(A);
					myUpdateUI();
					num++;
				}else {
					JOptionPane.showMessageDialog(null, "最多添加5个","消息",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		
		//添加供应商
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//将数据写入数据库
				
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "供应商不能为空","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					int star = SupManDao.wiretSup(jt1.getText());
					if(star==0) {
						JOptionPane.showMessageDialog(null, "供应商添加失败","消息",JOptionPane.WARNING_MESSAGE);

					}
					if(star==1) {
						JOptionPane.showMessageDialog(null, "供应商添加成功","消息",JOptionPane.WARNING_MESSAGE);
						//添加后刷新
						SupManDao.readSup(cmb1);
						SupManDao.readSup(cmbSupName);
						//刷新下拉栏
					}
					if(star==3) {
						JOptionPane.showMessageDialog(null, "供应商名字重复","消息",JOptionPane.WARNING_MESSAGE);

					}
					
				}
				
			}
			
		});
		//添加删除按钮监听  dellSup
		
		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//将数据写入数据库
				
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "供应商不能为空","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					int star = SupManDao.dellSup(jt1.getText());
					if(star==0) {
						JOptionPane.showMessageDialog(null, "删除失败，请检查名称","消息",JOptionPane.WARNING_MESSAGE);

					}
					if(star==1) {
						JOptionPane.showMessageDialog(null, "删除成功","消息",JOptionPane.WARNING_MESSAGE);
						//添加后刷新
						SupManDao.readSup(cmb1);
						SupManDao.readSup(cmbSupName);
						//刷新下拉栏
					}
					if(star==3) {
						JOptionPane.showMessageDialog(null, "报错，检查输入内容","消息",JOptionPane.WARNING_MESSAGE);

					}
					
				}
				
			}
			
		});
		
		
		
		
		//获取盘子中的所有东西
		
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a = 0;
				Component[] tem = jp3.getComponents();
				for(int i=0;i<tem.length;i++) {
					
				
					if(tem[i].getName()!=null&&tem[i].getName().equals("sun")) {
						//证明这个组件时文本框
						JTextField TEMP1 = (JTextField)tem[i];
						String text = TEMP1.getText();//把文本框内容变成文本储存
						if(cmb1.getSelectedIndex()==0) {
							JOptionPane.showMessageDialog(null, "请检查供应商","消息",JOptionPane.WARNING_MESSAGE);
						}else {
							
							//获取项目名字
							String sup=(String) cmb1.getSelectedItem();
							//text是子产品   sup是运营商
							
							a = SupManDao.wiretSupSun(sup, text);
										
						}
						
					}
					
				}
				//后面高级的时候   让他自动出那行出错
				if(a==3) {
					JOptionPane.showMessageDialog(null, "请检查子产品名称是否重复","消息",JOptionPane.WARNING_MESSAGE);
				}
				else if(a==0) {
					JOptionPane.showMessageDialog(null, "添加失败","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "添加成功","消息",JOptionPane.WARNING_MESSAGE);
					SupManDao.readSun(cmbStockName,(String)cmbSupName.getSelectedItem());
				}
				
			}
			
		});
		
		//jb4 重置     将下面子菜单清空只留一个
		jb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jp3.removeAll();
				
				JTextField A = new JTextField(12); 
				JLabel B = new JLabel("产品名称");
				A.setName("sun");//子类的意思
				jp3.add(B);
				jp3.add(A);
				num=1;
				
				myUpdateUI();
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		///接下来进行
		//1.下拉框每次添加结束后自动刷新
		//2.点击保存后将数据写入数据库
		//3.重置是将五个框框变成一个   并且清空内容
		
		
		
	}
	
	
	
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);//添加组件    此组件是更新
	}
	
	
	
	
	
}
