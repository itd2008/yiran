package com.yiran.common.utils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author yiran
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static final long ONE_DAY_SECONDS = 86400L;
    public static final String shortFormat = "yyyyMMdd";
    public static final String longFormat = "yyyyMMddHHmmss";
    public static final String webFormat = "yyyy-MM-dd";
    public static final String timeFormat = "HHmmss";
    public static final String monthFormat = "yyyyMM";
    public static final String chineseDtFormat = "yyyy年MM月dd日";
    public static final String newFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String noSecondFormat = "yyyy-MM-dd HH:mm";
    public static final long ONE_DAY_MILL_SECONDS = 86400000L;

    public static DateFormat getNewDateFormat(String pattern)
    {
      DateFormat df = new SimpleDateFormat(pattern);

      df.setLenient(false);
      return df;
    }

    public static String format(Date date, String format) {
      if (date == null) {
        return null;
      }

      return new SimpleDateFormat(format).format(date);
    }

    public static Date parseDateNoTime(String sDate) throws ParseException {
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

      if ((sDate == null) || (sDate.length() < "yyyyMMdd".length())) {
        throw new ParseException("length too little", 0);
      }

      if (!StringUtils.isNumeric(sDate)) {
        throw new ParseException("not all digit", 0);
      }

      return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
      if (StringUtils.isBlank(format)) {
        throw new ParseException("Null format. ", 0);
      }

      DateFormat dateFormat = new SimpleDateFormat(format);

      if ((sDate == null) || (sDate.length() < format.length())) {
        throw new ParseException("length too little", 0);
      }

      return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit) throws ParseException
    {
      sDate = sDate.replaceAll(delimit, "");

      DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

      if ((sDate == null) || (sDate.length() != "yyyyMMdd".length())) {
        throw new ParseException("length not match", 0);
      }

      return dateFormat.parse(sDate);
    }

    public static Date parseDateLongFormat(String sDate) {
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      Date d = null;

      if ((sDate != null) && (sDate.length() == "yyyyMMddHHmmss".length())) {
        try {
          d = dateFormat.parse(sDate);
        } catch (ParseException ex) {
          return null;
        }
      }

      return d;
    }

    public static Date parseDateNewFormat(String sDate) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date d = null;
      if ((sDate != null) && (sDate.length() == "yyyy-MM-dd HH:mm:ss".length())) {
        try {
          d = dateFormat.parse(sDate);
        } catch (ParseException ex) {
          return null;
        }
      }
      return d;
    }

    public static Date addHours(Date date, long hours)
    {
      return addMinutes(date, hours * 60L);
    }

    public static Date addMinutes(Date date, long minutes)
    {
      return addSeconds(date, minutes * 60L);
    }

    public static Date addSeconds(Date date1, long secs)
    {
      return new Date(date1.getTime() + secs * 1000L);
    }

    public static boolean isValidHour(String hourStr)
    {
      if ((!StringUtils.isEmpty(hourStr)) && (StringUtils.isNumeric(hourStr))) {
        int hour = new Integer(hourStr).intValue();

        if ((hour >= 0) && (hour <= 23)) {
          return true;
        }
      }

      return false;
    }

    public static boolean isValidMinuteOrSecond(String str)
    {
      if ((!StringUtils.isEmpty(str)) && (StringUtils.isNumeric(str))) {
        int hour = new Integer(str).intValue();

        if ((hour >= 0) && (hour <= 59)) {
          return true;
        }
      }

      return false;
    }

    public static Date addDays(Date date1, long days)
    {
      return addSeconds(date1, days * 86400L);
    }

    public static String getTomorrowDateString(String sDate) throws ParseException {
      Date aDate = parseDateNoTime(sDate);

      aDate = addSeconds(aDate, 86400L);

      return getDateString(aDate);
    }

    public static String getTomorrowWebDate(String sDate) throws ParseException {
      String tomorrowShortDate = getTomorrowDateString(convertWeb2ShortFormat(sDate));

      return convert2WebFormat(tomorrowShortDate);
    }

    public static String getLongDateString(Date date) {
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

      return getDateString(date, dateFormat);
    }

    public static String getNewFormatDateString(Date date) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return getDateString(date, dateFormat);
    }

    public static String getDateString(Date date, DateFormat dateFormat) {
      if ((date == null) || (dateFormat == null)) {
        return null;
      }

      return dateFormat.format(date);
    }

    public static String getYesterDayDateString(String sDate) throws ParseException {
      Date aDate = parseDateNoTime(sDate);

      aDate = addSeconds(aDate, -86400L);

      return getDateString(aDate);
    }

    public static String getDateString(Date date)
    {
      DateFormat df = getNewDateFormat("yyyyMMdd");

      return df.format(date);
    }

    public static String getWebDateString(Date date) {
      DateFormat dateFormat = getNewDateFormat("yyyy-MM-dd");

      return getDateString(date, dateFormat);
    }

    public static String getNoSecondDateString(Date date) {
      DateFormat dateFormat = getNewDateFormat("yyyy-MM-dd HH:mm");

      return getDateString(date, dateFormat);
    }

    public static String getChineseDateString(Date date)
    {
      DateFormat dateFormat = getNewDateFormat("yyyy年MM月dd日");

      return getDateString(date, dateFormat);
    }

    public static String getTodayString() {
      DateFormat dateFormat = getNewDateFormat("yyyyMMdd");

      return getDateString(new Date(), dateFormat);
    }

    public static String getTimeString(Date date) {
      DateFormat dateFormat = getNewDateFormat("HHmmss");

      return getDateString(date, dateFormat);
    }

    public static String getBeforeDayString(int days) {
      Date date = new Date(System.currentTimeMillis() - 86400000L * days);
      DateFormat dateFormat = getNewDateFormat("yyyyMMdd");

      return getDateString(date, dateFormat);
    }

    public static long getDiffSeconds(Date one, Date two)
    {
      Calendar sysDate = new GregorianCalendar();

      sysDate.setTime(one);

      Calendar failDate = new GregorianCalendar();

      failDate.setTime(two);
      return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000L;
    }

    public static long getDiffMinutes(Date one, Date two) {
      Calendar sysDate = new GregorianCalendar();

      sysDate.setTime(one);

      Calendar failDate = new GregorianCalendar();

      failDate.setTime(two);
      return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 60000L;
    }

    public static long getDiffDays(Date one, Date two)
    {
      Calendar sysDate = new GregorianCalendar();

      sysDate.setTime(one);

      Calendar failDate = new GregorianCalendar();

      failDate.setTime(two);
      return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 86400000L;
    }

    public static String getBeforeDayString(String dateString, int days)
    {
      DateFormat df = getNewDateFormat("yyyyMMdd");
      Date date =null;
      try
      {
        date = df.parse(dateString);
      }
      catch (ParseException e)
      {
        
        date = new Date();
      }

       date = new Date(date.getTime() - 86400000L * days);

      return df.format(date);
    }

    public static boolean isValidShortDateFormat(String strDate) {
      if (strDate.length() != "yyyyMMdd".length()) {
        return false;
      }
      try
      {
        Integer.parseInt(strDate);
      } catch (Exception NumberFormatException) {
        return false;
      }

      DateFormat df = getNewDateFormat("yyyyMMdd");
      try
      {
        df.parse(strDate);
      } catch (ParseException e) {
        return false;
      }

      return true;
    }

    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
      String temp = strDate.replaceAll(delimiter, "");

      return isValidShortDateFormat(temp);
    }

    public static boolean isValidLongDateFormat(String strDate)
    {
      if (strDate.length() != "yyyyMMddHHmmss".length()) {
        return false;
      }
      try
      {
        Long.parseLong(strDate);
      } catch (Exception NumberFormatException) {
        return false;
      }

      DateFormat df = getNewDateFormat("yyyyMMddHHmmss");
      try
      {
        df.parse(strDate);
      } catch (ParseException e) {
        return false;
      }

      return true;
    }

    public static boolean isValidLongDateFormat(String strDate, String delimiter)
    {
      String temp = strDate.replaceAll(delimiter, "");

      return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
      return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
      if (StringUtils.isBlank(strDate)) {
        return null;
      }

      String temp = strDate.replaceAll(delimiter, "");

      if (isValidShortDateFormat(temp)) {
        return temp;
      }

      return null;
    }

    public static String getShortFirstDayOfMonth() {
      Calendar cal = Calendar.getInstance();
      Date dt = new Date();

      cal.setTime(dt);
      cal.set(5, 1);

      DateFormat df = getNewDateFormat("yyyyMMdd");

      return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
      DateFormat df = getNewDateFormat("yyyy-MM-dd");

      return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
      Calendar cal = Calendar.getInstance();
      Date dt = new Date();

      cal.setTime(dt);
      cal.set(5, 1);

      DateFormat df = getNewDateFormat("yyyy-MM-dd");

      return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
      try {
        Date date = formatIn.parse(dateString);

        return formatOut.format(date);
      } catch (ParseException e) {
      }return "";
    }

    public static String convertWeb2ShortFormat(String dateString)
    {
      DateFormat df1 = getNewDateFormat("yyyy-MM-dd");
      DateFormat df2 = getNewDateFormat("yyyyMMdd");

      return convert(dateString, df1, df2);
    }

    public static String convert2WebFormat(String dateString) {
      DateFormat df1 = getNewDateFormat("yyyyMMdd");
      DateFormat df2 = getNewDateFormat("yyyy-MM-dd");

      return convert(dateString, df1, df2);
    }

    public static String convert2ChineseDtFormat(String dateString) {
      DateFormat df1 = getNewDateFormat("yyyyMMdd");
      DateFormat df2 = getNewDateFormat("yyyy年MM月dd日");

      return convert(dateString, df1, df2);
    }

    public static String convertFromWebFormat(String dateString) {
      DateFormat df1 = getNewDateFormat("yyyyMMdd");
      DateFormat df2 = getNewDateFormat("yyyy-MM-dd");

      return convert(dateString, df2, df1);
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
      DateFormat df = getNewDateFormat("yyyy-MM-dd");

      return dateNotLessThan(date1, date2, df);
    }

    public static boolean dateNotLessThan(String date1, String date2, DateFormat format)
    {
      try
      {
        Date d1 = format.parse(date1);
        Date d2 = format.parse(date2);

        if (d1.before(d2)) {
          return false;
        }
        return true;
      }
      catch (ParseException e) {
      }return false;
    }

    public static String getEmailDate(Date today)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

      String todayStr = sdf.format(today);
      return todayStr;
    }

    public static String getSmsDate(Date today)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

      String todayStr = sdf.format(today);
      return todayStr;
    }

    public static String formatTimeRange(Date startDate, Date endDate, String format) {
      if ((endDate == null) || (startDate == null)) {
        return null;
      }

      String rt = null;
      long range = endDate.getTime() - startDate.getTime();
      long day = range / 86400000L;
      long hour = range % 86400000L / 3600000L;
      long minute = range % 3600000L / 60000L;

      if (range < 0L) {
        day = 0L;
        hour = 0L;
        minute = 0L;
      }

      rt = format.replaceAll("dd", String.valueOf(day));
      rt = rt.replaceAll("hh", String.valueOf(hour));
      rt = rt.replaceAll("mm", String.valueOf(minute));

      return rt;
    }

    public static String formatMonth(Date date) {
      if (date == null) {
        return null;
      }

      return new SimpleDateFormat("yyyyMM").format(date);
    }

    public static Date getBeforeDate()
    {
      Date date = new Date();

      return new Date(date.getTime() - 86400000L);
    }

    public static Date getDayBegin(Date date)
    {
      DateFormat df = new SimpleDateFormat("yyyyMMdd");
      df.setLenient(false);

      String dateString = df.format(date);
      try
      {
        return df.parse(dateString); } catch (ParseException e) {
      }
      return date;
    }

    public static boolean dateLessThanNowAddMin(Date date, long min)
    {
      return addMinutes(date, min).before(new Date());
    }

    public static boolean isBeforeNow(Date date)
    {
      if (date == null)
        return false;
      return date.compareTo(new Date()) < 0;
    }

    public static boolean isValidate(Date requestTime, Date effectTime, Date expiredTime)
    {
      if ((requestTime == null) || (effectTime == null) || (expiredTime == null)) {
        return false;
      }
      return (effectTime.compareTo(requestTime) <= 0) && (expiredTime.compareTo(requestTime) >= 0);
    }

    public static void main(String[] args) {
      System.out.println("[" + convert2WebFormat(null) + "]");
    }

    public static Date parseNoSecondFormat(String sDate) throws ParseException {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

      if ((sDate == null) || (sDate.length() < "yyyy-MM-dd HH:mm".length())) {
        throw new ParseException("length too little", 0);
      }

      if (!StringUtils.isNumeric(sDate)) {
        throw new ParseException("not all digit", 0);
      }

      return dateFormat.parse(sDate);
    }

    public static boolean isSameDay(Date date1, Date date2)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      return sdf.format(date1).equals(sdf.format(date2));
    }

    public static Date getCurrentTS()
    {
      Calendar calendar = Calendar.getInstance();
      Date date = calendar.getTime();
      return date;
    }

    public static Date getWeekBefore()
    {
      Calendar calendar = Calendar.getInstance();
      Date date = calendar.getTime();
      return date;
    }

    public static Date getDateNearCurrent(int days)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.add(5, days);
      Date date = calendar.getTime();
      return date;
    }
    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    
    /**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
	    Calendar date = Calendar.getInstance();
	    date.setTime(nowTime);
	    Calendar begin = Calendar.getInstance();
	    begin.setTime(beginTime);
	    Calendar end = Calendar.getInstance();
	    end.setTime(endTime);
	    if (date.after(begin) && date.before(end)) {
	        return true;
	    }else if(nowTime.compareTo(beginTime)==0 || nowTime.compareTo(endTime) == 0 ){
	    	return true;
	    }else {
	        return false;
	    }
	}

}
