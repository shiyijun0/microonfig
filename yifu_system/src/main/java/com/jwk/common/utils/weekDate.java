package com.jwk.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class weekDate {
	/** * 获取指定日期是星期几
	  * 参数为null时表示获取当前日期是星期几
	  * @param date
	  * @return
	*/
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	
	public static void main(String[] args) throws Exception{
	    //今天是2014-12-25 星期四
	    String weekOfDate = "2018-05-09 00:16:34";
	    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(weekOfDate); 
	   
	    weekOfDate = getWeekOfDate(date);
	    System.out.println(weekOfDate);
	    //输出 星期四
	 
	   /* Date date = new Date();
	    date.setDate(24);
	    weekOfDate = getWeekOfDate(date);
	    System.out.println(weekOfDate);*/
	    //输出 星期三
	 }
}
