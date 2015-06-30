package main.custom.logUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class log4JLogger {
	private static Logger logger = Logger.getLogger(log4JLogger.class);
	private static final String LS = "|";
	private static final String CS = ",";
	private static final String logName = "wifilog";

	private static Logger wifiLog = Logger.getLogger(logName);

	public static String getLogName() {
		return logName;
	}

	/**
	 * 
	 * Function: 记录info信息
	 * 
	 * @author huangww DateTime 2015-1-8 下午9:37:54
	 * @param key
	 * @param values
	 */
	private static void writeLogInfo(String key, Object... values) {
		StringBuilder sb = new StringBuilder();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// String curTime = dateFormat.format(Calendar.getInstance().getTime());
		// sb.append(curTime).append(CS);
		// sb.append(key).append(CS);
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != values.length - 1) {
				sb.append(CS);
			}
		}
		MDC.put("key", key);
		MDC.put("message", sb.toString());
		// sb.append(LS);
		logger.info(key + ":" + sb.toString());
	}

	private static void writeLogInfo(Logger logger, String key,
			Object... values) {
		StringBuilder sb = new StringBuilder();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// String curTime = dateFormat.format(Calendar.getInstance().getTime());
		// sb.append(curTime).append(CS);
		// sb.append(key).append(CS);
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != values.length - 1) {
				sb.append(CS);
			}
		}
		MDC.put("key", key);
		MDC.put("message", sb.toString());
		logger.info(key + " : " + sb.toString());
	}

	/**
	 * 
	 * Function: 记录error信息
	 * 
	 * @author huangww DateTime 2015-1-8 下午9:38:15
	 * @param key
	 * @param values
	 */
	private static void writeLogError(String key, Object... values) {
		StringBuilder sb = new StringBuilder();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// String curTime = dateFormat.format(Calendar.getInstance().getTime());
		// sb.append(curTime).append(CS);
		// sb.append(key).append(CS);
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != values.length - 1) {
				sb.append(CS);
			}
		}
		MDC.put("key", key);
		MDC.put("message", sb.toString());
		logger.info(key + " : " + sb.toString());
	}

	private static void writeLogError(Logger logger, String key,
			Object... values) {
		StringBuilder sb = new StringBuilder();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// String curTime = dateFormat.format(Calendar.getInstance().getTime());
		// sb.append(curTime).append(CS);
		// sb.append(key).append(CS);
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != values.length - 1) {
				sb.append(CS);
			}
		}
		MDC.put("key", key);
		MDC.put("message", sb.toString());
		logger.info(key + " : " + sb.toString());
	}

	/**
	 * 
	 * Function: 对外提供调用接口
	 * 
	 * @author huangww DateTime 2015-1-8 下午9:38:56
	 * @param key
	 * @param values
	 */
	public static void wifiLogInfo(String key, Object... values) {
		writeLogInfo(wifiLog, key, values);
	}

	/**
	 * 
	 * Function: 对外提供调用接口
	 * 
	 * @author huangww DateTime 2015-1-8 下午9:39:38
	 * @param key
	 * @param values
	 */
	public static void wifiLogError(String key, Object... values) {
		writeLogError(wifiLog, key, values);
	}

}
