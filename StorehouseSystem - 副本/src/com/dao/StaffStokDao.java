package com.dao;

import com.until.DBUtil;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StaffStokDao {
    public static Connection con= DBUtil.conn;
    //获取订单时间
    // 获取系统时间进行对比
    // 超过三分钟不允许操作
    //删除更改都要在三分钟之内

    public static int showTime(String num){
        PreparedStatement preSql;//预处理
        ResultSet rs;//存放结果
        String sqlStr="select * FROM instock WHERE id=?";

        int a = 0;

        try {
            preSql=con.prepareStatement(sqlStr);
            preSql.setString(1, num);
            String t = null;
            rs = preSql.executeQuery();//将结果放到rs
            while (rs.next()){
                a++;
                t = rs.getString("intime");

                Date day = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ac = df.format(day);
                Long between_daInteger = between_days(t, df.format(day));

                if (between_daInteger>3){
                    return 0;
                }else {
                    return 1;
                }
            }

            return 0;
        }catch(SQLException e) {
            return 3;
        }
    }
    public static Long between_days(String a,String b){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar_a = Calendar.getInstance();
        Calendar calendar_b = Calendar.getInstance();

        Date date_a=null;
        Date date_b=null;

        try{
            date_a=simpleDateFormat.parse(a);
            date_b=simpleDateFormat.parse(b);
            calendar_a.setTime(date_a);
            calendar_b.setTime(date_b);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long time_a = calendar_a.getTimeInMillis();
        long time_b = calendar_b.getTimeInMillis();

        long between_days = (time_b - time_a)/(1000*3600*24*60*60);

        return between_days;
    }
}
