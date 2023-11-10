package com.Style;

import java.awt.Font;

public class Style {
      
	//字体样式
	
	public static Font title;//定义标题 只限于登陆页面
	public static Font account;//账号样式
	public static Font accounttext;//登录框样式
	public static Font ok;//登录按钮样式
	//public static Font
	//public static Font
	
	
	//字体样式
	public Style(){
		
		title = new Font("宋体",Font.BOLD,28);//字体  加粗  大小
		account = new Font("华文楷体",Font.BOLD,18);
		accounttext = new Font("宋体",Font.PLAIN,18);
	}
}
