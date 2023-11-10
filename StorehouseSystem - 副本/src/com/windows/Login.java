package com.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.Style.Style;
import com.dao.LoginDao;
import com.dao.StaffStokDao;
import com.dao.SupManDao;
import com.manage.panl.InStockPan;
import com.tool.Tool;



public class Login {
	
	public static JTextField jtextfield;
	public static JPasswordField jtextfield1;
	
	final int WIDTH=500;//顶层框架宽
	final int HEIGHT=230;//顶层框架高
	
	String title;
	JFrame jframe=new JFrame();
	
	FlowLayout flowlayout;
	
	
	
	
	Login(String title){
		this.title = title;
		init();
		jframe.setVisible(true);//设置窗口是否显示
		jframe.setResizable(false);//窗口大小不可变
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认关闭方式
		jframe.validate();//组件生效
		
		
	}
	void init() {
		//设置标题
		jframe.setTitle(title);
		
		//设置窗口位置
		
		
		//设置窗口大小
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);//获取对象大小，以及设置窗口位置
		//Dimension screenSize = kit.getScreenSize();
		//int width=screenSize.width;//获取屏幕的高和宽
		//int height=screenSize.height;
		//int x=(width-WIDTH)/2;
		//int y=(height-HEIGHT)/2;
		//jframe.setBounds(x,y,WIDTH,HEIGHT);
		
		
		//大小设置完毕，然后设置内部信息
		flowlayout = new FlowLayout(flowlayout.CENTER);//居中对齐
		Style style = new Style();
		jframe.setLayout(null);//流布局
		
		//放入图片
		ImageIcon img = new ImageIcon("src/img/Login.jpg");//设置背景图到img变量里
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0,0,500,280);//设置背景图片以及位置
		jframe.add(bgimg);
		
	    //定义两个框框   分别装入帐号密码以及标题
		JPanel jpnel1 = new JPanel();
		jpnel1.setLayout(flowlayout);
		jpnel1.setBounds(0,0,500,45);

		
		//添加标题
		JLabel jlabel1 = new JLabel("仓库管理系统登录");
	    jlabel1.setFont(style.title);
		jpnel1.add(jlabel1);
		jpnel1.setOpaque(false);
		
		
		//创建盘子二
		JPanel jpnel2 = new JPanel();
		jpnel2.setLayout(flowlayout);
		jpnel2.setBounds(125,45,240,230);
		jpnel2.setOpaque(false);
		
		
		
		JLabel jlabel2 = new JLabel("账号：");
		jlabel2.setFont(style.account);
	    jpnel2.add(jlabel2);
		//添加账号框
		jtextfield = new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpnel2.add(jtextfield);
		
		JLabel jlabel3 = new JLabel("密码：");
		jlabel3.setFont(style.account);
	    jpnel2.add(jlabel3);
	    
	  //添加密码框
	    jtextfield1 = new JPasswordField(15);
	  	jtextfield1.setFont(style.accounttext);
	  	jpnel2.add(jtextfield1);
	  	
		//登录按钮
	  	JButton jbutton = new JButton("安全登录");
	  	jbutton.setFont(style.ok);
	  	jbutton.setPreferredSize(new Dimension(200,35));
	    jbutton.setBackground(Color.gray);
	  	jbutton.setForeground(new Color(     255,215,0));
	  	
	  	jpnel2.add(jbutton);
	  			
				
				
				
		jframe.add(jpnel2);
		jframe.add(jpnel1);
		jframe.add(bgimg);
		
		
		//以下是监听事件
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("点击了登录");
				//将账号和密码进行获取并相应
				//将账号密码和数据库匹配，同时匹配权限，从而跳转不同文件
				
				String account=jtextfield.getText();//获取账号
				
				char[]str=jtextfield1.getPassword();
				String password = new String(str);
				
				System.out.println(account);
				System.out.println(password);
				boolean star = LoginDao.loginStar(account, password);
				if(star==true) {
					
					System.out.println("登录成功");
					//之后判断权限所在等级  根据等级进行跳转不同界面
					
					
					
					int pow = LoginDao.loginPow(account, password);
					if(pow==2){
						jframe.dispose();
						//管理员
						ManagePeopleWindows A = new ManagePeopleWindows();
						SupManDao.readSup(InStockPan.cmbSupName);
					}else if(pow==1){
						jframe.dispose();
						//普通用户
						JOptionPane.showMessageDialog(null, "进入员工界面","登陆消息",JOptionPane.WARNING_MESSAGE);
						StaiffWindows a = new StaiffWindows();
					}else {
						//报错
						JOptionPane.showMessageDialog(null, "系统错误！","登陆消息",JOptionPane.WARNING_MESSAGE);

					}
				}else {
					
					JOptionPane.showMessageDialog(null, "账号或密码有误！","登陆消息",JOptionPane.WARNING_MESSAGE);
					
				}
			}
			
		});
		
		
		
	}
	private void add(JPanel jpnel2) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

