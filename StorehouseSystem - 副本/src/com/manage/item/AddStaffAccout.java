package com.manage.item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Style.Style;
import com.dao.AddAcount;
import com.tool.Tool;

public class AddStaffAccout {
	final int WIDTH=240;//顶层框架宽
	final int HEIGHT=280;//顶层框架高
	JFrame jframe=new JFrame();
	
	public AddStaffAccout() {
		
		init();
		jframe.setVisible(true);//设置窗口是否显示
		jframe.setResizable(false);//窗口大小不可变
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认关闭方式
		jframe.validate();//组件生效
	}
	
	
	
	void init() {
		jframe.setTitle("添加员工账号");
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		//五个标签四个文本框
		JLabel JL = new JLabel("添加员工账号");
		jframe.add(JL);
		Style style = new Style();
		JL.setFont(style.title);
		
		JLabel JL1 = new JLabel("员  工  号:   ");
		jframe.add(JL1);
		JTextField JT1 = new JTextField(10);
		jframe.add(JT1);
		
		
		JLabel JL5 = new JLabel("此号密码：");
		jframe.add(JL5);
		JPasswordField JT5 = new JPasswordField(10);
		jframe.add(JT5);
		
		
		JLabel JL6 = new JLabel("确认密码：");
		jframe.add(JL6);
		JPasswordField JT6 = new JPasswordField(10);
		jframe.add(JT6);

		
		JLabel JL2 = new JLabel("员工姓名：");
		jframe.add(JL2);
		JTextField JT2 = new JTextField(10);
		jframe.add(JT2);
		
		JLabel JL3 = new JLabel("员工地址：");
		jframe.add(JL3);
		JTextField JT3 = new JTextField(10);
		jframe.add(JT3);

		
		JLabel JL4 = new JLabel("员工邮箱：");
		jframe.add(JL4);
		JTextField JT4 = new JTextField(10);
		jframe.add(JT4);
		
		JButton JB = new JButton("添加员工 ");
		jframe.add(JB);
		
		JButton JB1 = new JButton(" 重置信息");
		jframe.add(JB1);
		//重置所有内容
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String account=JT1.getText();
				String password = new String(JT5.getPassword());
				String okpassword = new String(JT6.getPassword());
				String name = JT2.getText();
				String address = JT3.getText();
				String emain = JT4.getText();
				
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "员工号不能为空","消息",JOptionPane.WARNING_MESSAGE);
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "员工密码不能为空","消息",JOptionPane.WARNING_MESSAGE);
				}else if(okpassword.equals("")) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空","消息",JOptionPane.WARNING_MESSAGE);
				}else if(!okpassword.equals(okpassword)) {
					JOptionPane.showMessageDialog(null, "两次密码不一致重新输入","消息",JOptionPane.WARNING_MESSAGE);
				}else {
					int a = AddAcount.writeAccount(account,  okpassword, name, address, emain);
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "添加失败","消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "添加成功","消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "请检查工号是否重复","消息",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
			
		});
	}
}		

