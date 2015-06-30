package main.custom.logUtil;

public class UniLogger {

	/**
	 * 记录错误日志
	 * 
	 * @param className
	 * @param functionName
	 * @param logInfo
	 * @throws Exception
	 */
	public void error(String className, String functionName, String logInfo) {
		try {
			log4JLogger.wifiLogError(String.format("[TYPE_ERROR]-%s.%s",
					className, functionName), logInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 记录错误日志
	 * 
	 * @param className
	 * @param functionName
	 * @param logInfo
	 * @throws Exception
	 */
	public void debug(String className, String functionName, String logInfo) {
		// 只有debug等级才行
		try {
			log4JLogger.wifiLogInfo(String.format("[TYPE_DEBUG]-%s.%s",
					className, functionName), logInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 记录业务日志
	 * 
	 * @param className
	 * @param functionName
	 * @param logInfo
	 * @throws Exception
	 */
	public void info(String className, String functionName, String logInfo) {
		try {
			log4JLogger
					.wifiLogInfo(String.format("[TYPE_INFO]-%s.%s", className,
							functionName), logInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
