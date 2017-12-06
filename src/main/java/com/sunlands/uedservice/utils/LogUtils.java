package com.sunlands.uedservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvpenghui on 2017/12/6.
 */
public class LogUtils {
	private static Logger logger = LoggerFactory.getLogger("businesslog");
	
	public static void bussnissLog(String log) {
		logger.info(log);
	}
	
	public static void bussnissLogError(String log) {
		logger.error(log);
	}
	
	public static void bussnissLogError(String log, Exception e) {
		logger.error(log, e);
	}
	
	public static String getStringTime() {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return time.format(new Date());
	}
	
	public static String getString_Time() {
		SimpleDateFormat time_ = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return time_.format(new Date());
	}
}
