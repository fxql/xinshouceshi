package com.manage.panl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.InStockDao;
import com.dao.OutStockDao;
import com.dao.SupManDao;
import com.tool.Tool;





import javax.swing.JFrame;



public class InStockPan extends JPanel{


	final int WIDTH=730;//顶层框架宽
	final int HEIGHT=50;//顶层框架高

	
	
	//public static JTextField intUser;
	
	public static JTextField intID;
	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static JComboBox cmbSupName;
	public static JComboBox cmbStockName;
	public static JComboBox Intuser;

	public static JTextField Inquire;

	
	
	
	
	
	//表格数据
	Object columns[] = {"ID标识","物品大类","物品小类","入库时间","物品细节备注","物品库存","入库人员"};/////标题信息,"物品入库数量"


	JTable tablel = null;//表格


	JScrollPane jscrollpane;//滚动条
	//JScrollPane scrollPane = new JScrollPane(columns);
	public static DefaultTableModel model;//定义表格的控制权
	
	
	
	public InStockPan(int x,int y,int width,int height){
		//第一个wh是所在位置
		//第二个wwss是
		this.setBounds(x,y,width,height);
	
		init();
	}
	
	void init() {
		
		//空布局
		this.setLayout(null);
		
		//设置颜色
		this.setBackground(Color.gray);
		
		//添加三个盘子
		JPanel jpan1 = new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,18,13));//左对齐
		//设置大小和位置
		jpan1.setBounds(0,0,WIDTH,HEIGHT);
		jpan1.setBackground(Color.GREEN);
		
		this.add(jpan1);
		
		//定义五个按钮
		JButton JB1 = new JButton("保存入库");
		jpan1.add(JB1);
		
		JButton JB2 = new JButton("删除入库");
		jpan1.add(JB2);
		
		JButton JB3 = new JButton("更改入库");
		jpan1.add(JB3);
		
		JButton JB4 = new JButton("ID或全部查找");
		jpan1.add(JB4);


		JButton JB5 = new JButton("大类查找入库");
		jpan1.add(JB5);
		//JButton JB5 = new JButton("重置界面");
		//jpan1.add(JB5);


		JButton JB6 = new JButton("查看出库数据");
		jpan1.add(JB6);
		
		
		
		
		//添加  四个标签    三个文本框      一个下拉  在定义一个盘子二
		//先加入盘子
		JPanel jpan2 = new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,33,18));//左对齐
		//设置大小和位置
		jpan2.setBounds(0,60,WIDTH,100);
		jpan2.setBackground(Color.ORANGE);
		
		//添加标签
		JLabel JL1 = new JLabel("物品大类");
		jpan2.add(JL1);
		
		
		cmbSupName = new JComboBox();
		cmbSupName.addItem("--请选择物品大类--");
		jpan2.add(cmbSupName);
		
		
		

		JLabel JL2 = new JLabel("物品小类");
		jpan2.add(JL2);
		
		cmbStockName = new JComboBox();
		cmbStockName.addItem("--请选择小类--");
		jpan2.add(cmbStockName);
		

		
		
		
		
		//JLabel JL3 = new JLabel("物品数量");
		//jpan2.add(JL3);
		
		//stockNumIn = new JTextField(8);
		//jpan2.add(stockNumIn);
		
		
		
		
		JLabel JL4 = new JLabel("细节备注");
		jpan2.add(JL4);
		
		stockPricIn = new JTextField(9);
		jpan2.add(stockPricIn);	
		
		
		
		JLabel JL5 = new JLabel("入库人员");
		jpan2.add(JL5);

		Intuser = new JComboBox();
		Intuser.setPreferredSize(new Dimension(122,24));
		Intuser.addItem("--  入库人员--");

		jpan2.add(Intuser);
//		intUser = new JTextField(8);
//		jpan2.add(intUser);

		
		JLabel JL6 = new JLabel("  ID标识  ");
		jpan2.add(JL6);
		
		intID  = new JTextField(10);
		jpan2.add(intID);



		JLabel JL7 = new JLabel("大类查找");
		jpan2.add(JL7);

		Inquire = new JTextField(9);
		jpan2.add(Inquire);



		this.add(jpan2);
		table();
		this.add(jscrollpane);
		SupManDao.readSup(InStockPan.cmbSupName);
		SupManDao.IntuserDao(InStockPan.Intuser);
	
		//放一个表格
		
		//添加JB1的监听
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取数据写入到数据库
				
				//InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择大类","消息",JOptionPane.WARNING_MESSAGE);

				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择小类","消息",JOptionPane.WARNING_MESSAGE);

				//}else if(stockNumIn.getText().equals("")) {
				//	JOptionPane.showMessageDialog(null, "请输入数量","消息",JOptionPane.WARNING_MESSAGE);

				//}else if(stockPricIn.getText().equals("")) {
					//JOptionPane.showMessageDialog(null, "请输入细节备注","消息",JOptionPane.WARNING_MESSAGE);

				}
				else if(intID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入物品ID","消息",JOptionPane.WARNING_MESSAGE);
				}else if(Intuser.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择人员","消息",JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					String sup = (String) cmbSupName.getSelectedItem();
					String sun = (String) cmbStockName.getSelectedItem();
					//String num = stockNumIn.getText();
					String pri = stockPricIn.getText();
					String ID = intID.getText();
					String User = (String) Intuser.getSelectedItem();
					//String User = intUser.getText();
					int a = InStockDao.writeStock(sup, sun, pri, ID,User);
					if (a==0) {
						JOptionPane.showMessageDialog(null, "添加失败","消息",JOptionPane.WARNING_MESSAGE);
					}if (a==3) {
						JOptionPane.showMessageDialog(null, "核实添加内容","消息",JOptionPane.WARNING_MESSAGE);
					}if (a==1) {
						JOptionPane.showMessageDialog(null, "添加成功","消息",JOptionPane.WARNING_MESSAGE);
					}
				}	
			}
			
		});
		
		//下拉框监听
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
			
				SupManDao.readSun(cmbStockName,(String)cmbSupName.getSelectedItem());
				SupManDao.IntuserDao(Intuser);
			}
			
		});
		

		
		//查询所有
		JB4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//全部   和单个
				String num = intID.getText();
				ResultSet rs;
				if(num.equals("")) {
					//查找全部
					rs=InStockDao.findStockallData();
					//传递一个存储数据的rs 和一个表格    还要一个表格的  宽度
					int a =Tool.addDataTable(rs, model,7);
					if (a==0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据","消息",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					//单个
					rs=InStockDao.findStockoneData(num);
					ResultSet rs1=InStockDao.findStockoneData(num);
					
					try {
						if(rs1.next()) {
						String sup = rs1.getString("supname");
						String sun = rs1.getString("stockname");
						//String nu = rs1.getString("num");
						String pr = rs1.getString("pric");
						String user = rs1.getString("user");
						String id = rs1.getString("ID");
						
						//遍历两个下拉框
						
						for(int i=0;i<cmbStockName.getItemCount();i++) {
							String a=(String) cmbStockName.getItemAt(i);
							if(a.equals(sun)) {
								cmbStockName.setSelectedIndex(i);
								cmbStockName.repaint();
							}
						}
						
						for(int i=0;i<cmbSupName.getItemCount();i++) {
							String a=(String) cmbSupName.getItemAt(i);
							if(a.equals(sup)) {
								cmbSupName.setSelectedIndex(i);
								cmbSupName.repaint();
							}
						}
							for(int i=0;i<Intuser.getItemCount();i++) {
								String a=(String) Intuser.getItemAt(i);
								if(a.equals(user)) {
									Intuser.setSelectedIndex(i);
									Intuser.repaint();
								}
							}
						
						
//						for(int i=0;i<intUser.getItemCount();i++) {
//							String a=(String) intUser.getItemAt(i);
//							if(a.equals(sup)) {
//								intUser.setSelectedIndex(i);
//								intUser.repaint();
//							}
//						}
						
						stockPricIn.setText(pr);
						//stockNumIn.setText(nu);
						//intUser.setText(user);
						
						myUpdateUI();
						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
					//传递一个存储数据的rs 和一个表格    还要一个表格的  宽度
					int a =Tool.addDataTable(rs, model,7);
					if (a==0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据","消息",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
			
		});
		
		//删除
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//直接通过id删除
				String num = intID.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "输入要删除的数据编号","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					int a = InStockDao.dellStockDao(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "请检查输入编号是否正确","消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "删除成功","消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "检查输入编号是否为数字","消息",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		
		//更改
		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取数据写入到数据库
				
				String sup=null;//供应商
				String sun=null;//子产品
				//String num=null;//数量
				String pric=null;//价格
				String user=null;
				String ID=null;
				
				if(intID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入id号","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					if(cmbSupName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择物品大类","消息",JOptionPane.WARNING_MESSAGE);
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择物品小类","消息",JOptionPane.WARNING_MESSAGE);
					//}else if(stockNumIn.getText().equals("")) {
					//	JOptionPane.showMessageDialog(null, "数量不能为空","消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "备注不能为空","消息",JOptionPane.WARNING_MESSAGE);
					}else if(Intuser.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "入库人员不能为空","消息",JOptionPane.WARNING_MESSAGE);
					}
					
					else {
						//写入
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						pric=stockPricIn.getText();
						user=(String) Intuser.getSelectedItem();
						ID=intID.getText();
						//将四个值传入数据库
						int a = InStockDao.changeStockData(sup, sun, pric,user,ID);//num,
						if(a==0) {
							JOptionPane.showMessageDialog(null, "数据未更改","消息",JOptionPane.WARNING_MESSAGE);
						}if(a==1) {
							JOptionPane.showMessageDialog(null, "数据更改成功","消息",JOptionPane.WARNING_MESSAGE);
						}if(a==3) {
							JOptionPane.showMessageDialog(null, "请检查输入格式","消息",JOptionPane.WARNING_MESSAGE);
						}
					}
				}	
			}
			
		});

		JB5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//大类查找

				String Insup = Inquire.getText();
				ResultSet rs;
				rs=InStockDao.InquireDao(Insup);
				ResultSet rs1=InStockDao.InquireDao(Insup);

				try {
					if(rs1.next()) {
						String sup = rs1.getString("supname");
						String sun = rs1.getString("stockname");
						//String nu = rs1.getString("num");
						String pr = rs1.getString("pric");
						String user = rs1.getString("user");
						String id = rs1.getString("ID");

						//遍历两个下拉框

						for(int i=0;i<cmbStockName.getItemCount();i++) {
							String a=(String) cmbStockName.getItemAt(i);
							if(a.equals(sun)) {
								cmbStockName.setSelectedIndex(i);
								cmbStockName.repaint();
							}
						}

						for(int i=0;i<cmbSupName.getItemCount();i++) {
							String a=(String) cmbSupName.getItemAt(i);
							if(a.equals(sup)) {
								cmbSupName.setSelectedIndex(i);
								cmbSupName.repaint();
							}
						}
						for(int i=0;i<Intuser.getItemCount();i++) {
							String a=(String) Intuser.getItemAt(i);
							if(a.equals(user)) {
								Intuser.setSelectedIndex(i);
								Intuser.repaint();
							}
						}
						stockPricIn.setText(pr);

						myUpdateUI();

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				//传递一个存储数据的rs 和一个表格    还要一个表格的  宽度
				int a =Tool.addDataTable(rs, model,7);
				if (a==0) {
					JOptionPane.showMessageDialog(null, "没有查到相关数据","消息",JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		JB6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//全部   和单个
				String num = OutStockPan.outID.getText();
				ResultSet rs;
				if(num.equals("")) {
					//查找全部
					rs= OutStockDao.findStockallData();

					//传递一个存储数据的rs 和一个表格    还要一个表格的  宽度
					int a =Tool.addDataTable(rs, model,7);
					if (a==0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据","消息",JOptionPane.WARNING_MESSAGE);
					}
				}


			}

		});

	}
	
	void table() {
		tablel = getTable();//初始化表格
		
		jscrollpane = new JScrollPane(tablel);//添加浏览窗口
		tablel.setPreferredSize(new Dimension(WIDTH-30,10000));//给表格设计大小
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示
		
		jscrollpane.setBounds(0,170,WIDTH-30,365);
	}
	
	JTable getTable() {
		//表格为空就新建表格
		if(tablel==null) {
			tablel=new JTable();//创建
			
			model = new DefaultTableModel() {
				//添加对表格的控制   设置表格不可动   不可编辑
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
				
			};
			model.setColumnIdentifiers(columns);//引用表头
			tablel.setModel(model);//设置表格模式
			
			tablel.getTableHeader().setReorderingAllowed(false);//让表格不可拖动
			tablel.getTableHeader().setResizingAllowed(false);//让表格不可移动
			
				
		}
		return tablel;
	}
	
	
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);//添加组件    此组件是更新
	}


}
