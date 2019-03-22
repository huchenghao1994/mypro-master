package com.yaspeed.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * 
 * Data 工具类
 * author:zy
 */
public class DateUtils {
	   /** 
	    * 获取过去第几天的日期 
	    * 
	    * @param past 
	    * @return 
	    */ 
	 public static String getPastDate(int past) {  
	      Calendar calendar = Calendar.getInstance();  
	      calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
	      Date today = calendar.getTime();  
	      SimpleDateFormat format = new SimpleDateFormat("y-M-d");  
	      String result = format.format(today);  
	      return result;  
	  } 
	 
     /** 
	   * 获取过去 任意天内的日期数组 
	    * @param intervals      intervals天内 
	    * @return              日期数组 
	    */  
	   public static ArrayList<String> getDateList(int intervals ) {  
	       ArrayList<String> pastDaysList = new ArrayList<String>();
	       for (int i = 0; i <intervals; i++) {  
	           pastDaysList.add(getPastDate(i));  
	       }  
	       return pastDaysList;  
	   }
	   
	   public static Date getNowTime(){
	        return new Timestamp(System.currentTimeMillis());
	    }
	   
	    public static String getYear(){
	        SimpleDateFormat date = new SimpleDateFormat("yy");
	        return date.format(new Date());
	    }
	    public static String getYearStr(){
	        SimpleDateFormat date = new SimpleDateFormat("yyyy");
	        return date.format(new Date());
	    }
	    
	    public static String getMonth(){
	        SimpleDateFormat date = new SimpleDateFormat("MM");
	        return date.format(new Date());
	    }
	    
	    public static String getDay(){
	        SimpleDateFormat date = new SimpleDateFormat("dd");
	        return date.format(new Date());
	    }
	    
	    public static String getHour(){
	        SimpleDateFormat date = new SimpleDateFormat("HH");
	        return date.format(new Date());
	    }
	    
	    public static String getMinute(){
	        SimpleDateFormat date = new SimpleDateFormat("mm");
	        return date.format(new Date());
	    }
	    
	    public static String getSecond(){
	        SimpleDateFormat date = new SimpleDateFormat("ss");
	        return date.format(new Date());
	    }
	    
	    public static String getMillisecond (){
	        SimpleDateFormat date = new SimpleDateFormat("SSS");
	        return date.format(new Date());
	    }
	    
	    public static boolean isToday(Timestamp time){
	        SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd");
	        
	        if(format.format(time).equals(format.format(new Date()))){
	            return true;
	        }        
	        return false;
	    }
	    
		public static String getDjqBh(){
	    	String time = getRandomA1(4)+""+getMinute()+""+getSecond()+""+getMillisecond();    //
	    	
	    	return time;
	    }  
	    
		 public static String getRandomA1(int count) {
			    Random ran = new Random();//用来生成随机数
			    String stringChar = "";//用来存储字母
			    String[] alphabet = {"A","B","C","D","E","F","G","H"
			            ,"I","J","K","L","M","N","O","P","Q","R","S"
			            ,"T","U","V","W","X","Y","Z"};//存放大写字母
			    
			    for(int j = 0; j < count; j++){ //字母
		            stringChar = stringChar + alphabet[ran.nextInt(26)];
		        }
				return stringChar;
			}
		/**
		 * 获取SQL组拼的日期时间格式
		 * @param obj
		 * @param returnStr
		 * @return
		 */
		public static String getSqlDt(Object obj,String returnStr){
			if(null == obj){
				return returnStr;
			}else{
				return "'"+obj.toString()+"'";
			}
		}
	    
	    /**
	     * 获取当前指定格式化的日期时间
	     * @param dateFormat //yyyy-MM-dd HH:mm:ss
	     * @return
	     */
	    public static String getCurrentDay(String dateFormat){
	        SimpleDateFormat format=new SimpleDateFormat(dateFormat);
	        return format.format(new Date());
	    } 
	    
	    public static String formatDate(Date date, String dateFormat){
	        SimpleDateFormat format=new SimpleDateFormat(dateFormat);
	        return format.format(date);
	    } 
	    
	  
	    /**
	     * 格式化指定的日期时间
	     * @param dt
	     * @param formatStr
	     * @return
	     */
	    public static String formatDateTime(Timestamp dt,String formatStr){  
	    	if(null == dt)
	    		return "";
	    	SimpleDateFormat time=new SimpleDateFormat(formatStr); //yyyy-MM-dd HH mm ss
	    	return time.format(dt);   }   
	    
//	    public static String distanceToday(int das,String format){
//	        
//	    	  Calendar cal = Calendar.getInstance();
//	    	  cal.add(Calendar.DATE,das);
//	    	  String day = new SimpleDateFormat(format).format(cal.getTime());
//	    	      return day;
//	    }    	    
	    
	    public static String return2Str(String str){
		     if(str.length()<2)
		     	return "0"+str;
		     else
		    	return str;
	    } 

	    
	    /**
	     * String 转 Timestamp
	     * @param str
	     * @return
	     */
	    public static Timestamp string2Timestamp(String str) {
	    	Timestamp ts = null;
	    	if(StringUtil.isNotEmpty(str)){
	    		ts = Timestamp.valueOf(str);
	    	} 
	    	return   ts;
	    } 
	    
	    /**
	     * String 转 Date
	     * @param str
	     * @return
	     */
	    public static Date string2Date(String str) {
	    	Date dt = null;
	    	if(StringUtil.isNotEmpty(str)){
	    		dt = java.sql.Date.valueOf(str);
	    	} 
	    	return   dt;
	    }     
	    
	    //日期之间相隔的天数
	    public static int compareDate(Date beforeDate, Date nowDate){
	    	SimpleDateFormat sim = new SimpleDateFormat( "yyyy-MM-dd");
	    	
	    	Date d1 = new Date();
	    	Date d2 = new Date();
			try {
		    	d1 = sim.parse(beforeDate.toString()); 
		    	d2 = sim.parse(nowDate.toString()); 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return (int) ((d2.getTime() - d1.getTime()) / (3600L * 1000 * 24));
	    
	    }
	    
	    /**
	     * 两个日期时间相差(未来日期 减去 当前日期)
	     * @param time
	     * @return
	     */
	    public static long afterDiffSec(Timestamp time) {
	    	if(time == null)  return 0;
	    	String afterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
	    	String currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(new Date().getTime()));
		    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    long diff = 0;   //获得两个时间的时间差(秒)
		    try {
				diff = (sd.parse(afterTime).getTime() - sd.parse(currTime).getTime())/1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		    return diff;
	    }
	    
	    /**
	     * 获取多少天之前或多少月之前的日期

	     * @param hourTime
	     * @param beforeDate
	     * @param beforeMonth
	     * @return
	     */
	    public static String beforeMonthDate(int hourTime,int beforeDate,int beforeMonth){

	        Calendar calendar = Calendar.getInstance();

	        if(beforeMonth>0){

	            calendar.add(Calendar.MONTH, -beforeMonth);

	        }else if(beforeDate>0){

	            calendar.add(Calendar.DATE, -beforeDate);

	        }else if(hourTime>0){

	            calendar.add(Calendar.HOUR_OF_DAY, -hourTime);

	        }

	        Date date = calendar.getTime();
	        
	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	        return format.format(date);

	 
	     }
	    
	    /**
	     * 获取多少天之后的日期

	     * @param
	     * @param beforeDate
	     * @param
	     * @return
	     */
	    public static String afterDate(int beforeDate){

	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE,beforeDate+1);
	        Date date = calendar.getTime();
	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return format.format(date);
	     }
	    
	    /**
	     * 获取多少小时之后的日期

	     * @param
	     * @param
	     * @param
	     * @return
	     */
	    public static String afterHour(int beforeHour){

	        Calendar calendar = Calendar.getInstance();
//	       Date d =DateTime.string2Date("2018-04-09 10:25:12");
//	        //System.out.println(calendar);
	        calendar.add(Calendar.HOUR,beforeHour);
	        Date date = calendar.getTime();
	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return format.format(date);
	     }
	    
	    /**
	     * 百分数 返回%
	     * @param a
	     * @param b
	     * @return
	     */
	    public static String getPercent(double a, double b) {  
	    	NumberFormat PercentFormat = NumberFormat.getPercentInstance();
	 
	    	return PercentFormat.format(a/b);
	    }  
	    
	    /** 
	     * 得到几天前的时间 
	     *  
	     * @param d 
	     * @param day 
	     * @return 
	     */  
	    public static Date getDateBefore(Date d, int day) {  
	        Calendar now = Calendar.getInstance();  
	        now.setTime(d);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
	        return now.getTime();  
	    }  
	    
	    /** 
	     * 得到几天后的时间 
	     *  
	     * @param d 
	     * @param day 
	     * @return 
	     */  
	    public static Date getDateAfter(Date d, int day) {  
	        Calendar now = Calendar.getInstance();  
	        now.setTime(d);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
	        return now.getTime();  
	    } 
	    
	    
	    /**
	    * 根据日期算星期 
	    * @param
	    * @return 星期
	    * 
	    * */
	    public static String getDay(String date,String pattern) {
	       // 返回结果
	       String ret = "";
	       // 星期
	       int day = 0;
	       // 中文星期
	       String zhou = null;
	       // 日历类
	       Calendar cal = Calendar.getInstance();
	       cal.setTime(string2Date(date));
	       day = cal.get(Calendar.DAY_OF_WEEK);
	       // 判断为周几
	       switch (day) {
	       case 1:
	        zhou = "周日";
	        break;
	       case 2:
	        zhou = "周一";
	        break;
	       case 3:
	        zhou = "周二";
	        break;
	       case 4:
	        zhou = "周三";
	        break;
	       case 5:
	        zhou = "周四";
	        break;
	       case 6:
	        zhou = "周五";
	        break;
	       case 7:
	        zhou = "周六";
	        break;
	       default:
	        zhou = "周日";

	       }
	       //ret = pattern + zhou;
	       ret = zhou;
	       return ret;
	    }
	    
	    /**
		 * 上周周一
		 * 
		 * @return
		 */
		public static Date getPreviousMonday() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			
			c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        
			Date date = c.getTime();
			return date;
		}
		
		/**
		 * 上周周二
		 * 
		 * @return
		 */
		public static Date getPreviousTuesDay() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			Date date = c.getTime();
			return date;
		}

		/**
		 * 上周周三
		 * 
		 * @return
		 */
		public static Date getPreviousWednesday() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			Date date = c.getTime();
			return date;
		}

		/**
		 * 上周周四
		 * 
		 * @return
		 */
		public static Date getPreviousThursDAY() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			Date date = c.getTime();
			return date;
		}

		/**
		 * 上周周五
		 * 
		 * @return
		 */
		public static Date getPreviousFriday() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			Date date = c.getTime();
			return date;
		}

		/**
		 * 上周周六
		 * 
		 * @return
		 */
		public static Date getPreviousSaturday() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			Date date = c.getTime();
			return date;
		}
		
		/**
		 * 上周周日
		 * 
		 * @return
		 */
		public static Date getPreviousSunday() {
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			c.add(Calendar.WEEK_OF_MONTH, -1);
			
			c.set(Calendar.HOUR_OF_DAY, 23);
	        c.set(Calendar.MINUTE, 59);
	        c.set(Calendar.SECOND, 59);
	        
			Date date = c.getTime();
			return date;
		}
		
		/**
		 * 返回指定日期的前一天
		 * @param date
		 * @return
		 */
		public static String getPreviousDay(Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.DATE, -1);
	        
	        //c.set(Calendar.HOUR_OF_DAY, 0);
	        //c.set(Calendar.MINUTE, 0);
	        //c.set(Calendar.SECOND, 0);
	        
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        return df.format(c.getTime());
	    }
		
	    public static void main(String[] args){
	    	getPreviousDay(new Date());
	    	Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        
			Date date = c.getTime();
	    	System.out.println(date + " " + new Timestamp(date.getTime()));
	     }
	    
}
