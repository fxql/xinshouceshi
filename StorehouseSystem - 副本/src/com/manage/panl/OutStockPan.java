package com.manage.panl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class OutStockPan extends JPanel{
	
	final int WIDTH=730;//顶层框架宽
	final int HEIGHT=50;//顶层框架高

	
	//表格数据
	Object columns[] = {"ID标识","物品大类","物品小类","出库时间","物品细节备注","物品库存","出库人员"};/////标题信息,"物品出库数量"
	JTable tablel = null;//表格
	JScrollPane jscrollpane;//滚动条
	public static DefaultTableModel model;//定义表格的控制权
	public static JTextField stockPricOut;
	public static JTextField stockNumOut;
	public static JTextField outID;
	public static JTextField Outquire;
	public static JComboBox cmbSupName;
	public static JComboBox stockName;
	public static JComboBox Outuser;


	public OutStockPan(int x,int y,int width,int height){
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
		JButton JB1 = new JButton("保存出库");
		jpan1.add(JB1);
		
		JButton JB2 = new JButton("删除出库");
		jpan1.add(JB2);
		
		JButton JB3 = new JButton("更改出库");
		jpan1.add(JB3);

		JButton JB4 = new JButton("ID或全部查找");
		jpan1.add(JB4);

		JButton JB5 = new JButton("大类查找入库");
		jpan1.add(JB5);
		
//		JButton JB5 = new JButton("同步入库");
//		jpan1.add(JB5);

		JButton JB6 = new JButton("查看入库数据");
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
		
	//JL1.setFont(new Font("宋体",Font.BOLD,15));
		
		cmbSupName = new JComboBox();
		cmbSupName.addItem("--请选择大类--");
		jpan2.add(cmbSupName);
		
		
		

		JLabel JL2 = new JLabel("物品小类");
		jpan2.add(JL2);
		
		stockName = new JComboBox();
		stockName.addItem("--请选择小类--");
		jpan2.add(stockName);
		

		
		
		
		
//		JLabel JL3 = new JLabel("物品数量");
//		jpan2.add(JL3);
//
//		stockNumOut = new JTextField(8);
//		jpan2.add(stockNumOut);
		
		
		
		
		JLabel JL4 = new JLabel("细节备注");
		jpan2.add(JL4);
		
		stockPricOut = new JTextField(9);
		jpan2.add(stockPricOut);




		JLabel JL5 = new JLabel("入库人员");
		jpan2.add(JL5);

		Outuser = new JComboBox();
		Outuser.setPreferredSize(new Dimension(122,24));
		Outuser.addItem("--  入库人员--");
		jpan2.add(Outuser);
		
		
		JLabel JL6 = new JLabel("  ID标识  ");
		jpan2.add(JL6);
		
		outID = new JTextField(10);
		jpan2.add(outID);

		JLabel JL7 = new JLabel("大类查找");
		jpan2.add(JL7);

		Outquire = new JTextField(9);
		jpan2.add(Outquire);
		
		
		
		
		this.add(jpan2);
		table();
		this.add(jscrollpane);
		//放一个表格
		//下拉监听
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
			
				SupManDao.readSun(stockName,(String)cmbSupName.getSelectedItem());
				SupManDao.IntuserDao(Outuser);
			}
			
		});
		
		//增加数据监听
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取数据写入到数据库
				
				//InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择供应商","消息",JOptionPane.WARNING_MESSAGE);

				}else if(stockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择产品","消息",JOptionPane.WARNING_MESSAGE);

//				}else if(stockNumOut.getText().equals("")) {
//					JOptionPane.showMessageDialog(null, "请输入数量","消息",JOptionPane.WARNING_MESSAGE);

				}else if(outID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入物品ID","消息",JOptionPane.WARNING_MESSAGE);

				}else if(Outuser.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请输入用户名","消息",JOptionPane.WARNING_MESSAGE);
					
				}else{
					String sup = (String)cmbSupName.getSelectedItem();
					String sun = (String)stockName.getSelectedItem();
				//	String num = stockNumOut.getText();
					String pri = stockPricOut.getText();
					String outuser = (String)Outuser.getSelectedItem();
					String id = outID.getText();
					
					int a = OutStockDao.writeStock(sup, sun, pri,outuser,id);
					
					
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
		
		//查询
		JB4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//全部   和单个
				String num = outID.getText();
				ResultSet rs;
				if(num.equals("")) {
					//查找全部
					rs=OutStockDao.findStockallData();
					
					//传递一个存储数据的rs 和一个表格    还要一个表格的  宽度
					int a =Tool.addDataTable(rs, model,7);
					if (a==0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据","消息",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					//单个
					rs=OutStockDao.findStockoneData(num);
					ResultSet rs1=OutStockDao.findStockoneData(num);
					
					
					try {
						if(rs1.next()) {
						String sup = rs1.getString("supname");
						String sun = rs1.getString("stockname");
						//String nu = rs1.getString("num");
						String pr = rs1.getString("pri");
						String user = rs1.getString("user");
						String id = rs1.getString("ID");
						
						//遍历两个下拉框
						
						for(int i=0;i<cmbSupName.getItemCount();i++) {
							String a=(String) cmbSupName.getItemAt(i);
							if(a.equals(sup)) {
								cmbSupName.setSelectedIndex(i);
								cmbSupName.repaint();
								for(int j=0;j<stockName.getItemCount();j++) {
									String c=(String) stockName.getItemAt(j);
									if(c.equals(sun)) {
										stockName.setSelectedIndex(j);
										stockName.repaint();
									}
							}
						}


						}
						
						stockPricOut.setText(pr);
						//stockNumOut.setText(nu);
						//myUpdateUI();

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
				String num = outID.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "输入要删除的数据编号","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					//int a = OutStockDao.dellStockDate(num);
					int a = OutStockDao.dellStockDate(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "请检查输入编号是否正确","消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1)
					{
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
				String ID=null;
				String user=null;
				
				if(outID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入id号","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					if(cmbSupName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择供应商","消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择子产品","消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "细节编号不能为空","消息",JOptionPane.WARNING_MESSAGE);
					}else if(Outuser.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "用户名不能为空","消息",JOptionPane.WARNING_MESSAGE);
					}
					else {
						//写入
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) stockName.getSelectedItem();
						pric=stockPricOut.getText();
						ID=outID.getText();
						user=(String) Outuser.getSelectedItem();
						//将四个值传入数据库
						int a = OutStockDao.changeStockData(sup, sun, pric,user,ID);
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

				String Outsup = Outquire.getText();
				ResultSet rs;
				rs=OutStockDao.OutquireDao(Outsup);
				ResultSet rs1=OutStockDao.OutquireDao(Outsup);

				try {
					if(rs1.next()) {
						String sup = rs1.getString("supname");
						String sun = rs1.getString("stockname");
						String pr = rs1.getString("pric");
						String user = rs1.getString("user");
						String id = rs1.getString("ID");

						//遍历两个下拉框

						for(int i=0;i<cmbSupName.getItemCount();i++) {
							String a=(String) cmbSupName.getItemAt(i);
							if(a.equals(sun)) {
								cmbSupName.setSelectedIndex(i);
								cmbSupName.repaint();
							}
						}

						for(int i=0;i<stockName.getItemCount();i++) {
							String a=(String) stockName.getItemAt(i);
							if(a.equals(sup)) {
								stockName.setSelectedIndex(i);
								stockName.repaint();
							}
						}
						for(int i=0;i<Outuser.getItemCount();i++) {
							String a=(String) Outuser.getItemAt(i);
							if(a.equals(user)) {
								Outuser.setSelectedIndex(i);
								Outuser.repaint();
							}
						}
						stockPricOut.setText(pr);

						//myUpdateUI();

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
				String num = InStockPan.intID.getText();
				ResultSet rs;
				if(num.equals("")) {
					//查找全部
					rs=InStockDao.findStockallData();
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
	

}
