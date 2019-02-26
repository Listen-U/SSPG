package com.listen.sspg.tools;

import java.io.File;

import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;


/** 
* 通用日志器，依赖于slf4j的API，最大限度优化性能 
*  
*  
*/
public class LogUtil {

	
	public static final Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);
	
	
	
    private static final Object[] EMPTY_ARRAY = new Object[] {}; 
    /** 该类的名称，用于识别该类的堆栈 */  
    private static final String thisClassName = LogUtil.class.getName();  
	
    
    public static void writeLog(String level,String message){
    	if(!StringUtil.isEmpty(message)){
    		if(level!=null && "error".equals(level.trim().toLowerCase())){
    			error(message);
    		}else if(level!=null && "warn".equals(level.trim().toLowerCase())){
    			warn(message);
    		}else{
    			info(message);
    		}
    	}
    }
    
    
    
    public static void writeLog(String level,String message,String  className, String methodName,int lineNumber){
    	if(!StringUtil.isEmpty(message)){
    		if(level!=null && "error".equals(level.trim().toLowerCase())){
    			error("{}.{}:{} --> {}", className,methodName,lineNumber,message);
    		}else if(level!=null && "warn".equals(level.trim().toLowerCase())){
    			warn("{}.{}:{} --> {}", className,methodName,lineNumber,message);
    		}else{
    			info("{}.{}:{} --> {}", className,methodName,lineNumber,message);
    		}
    	}
    }
    
	/**
	 * 
	 * 获得Logger
	 * 
	 * @param clazz 日志发出的类
	 * @return Logger
	 * 
	 */

	public static Logger get(Class<?> clazz) {

		return LoggerFactory.getLogger(clazz);

	}

	/**
	 * 
	 * 获得Logger
	 * 
	 * @param name
	 *            自定义的日志发出者名称
	 * 
	 * @return Logger
	 * 
	 */

	public static Logger get(String name) {

		return LoggerFactory.getLogger(name);

	}

	/**
	 * 
	 * @return 获得日志，自动判定日志发出者
	 * 
	 */

	public static Logger get() {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		return LoggerFactory.getLogger(stackTrace[2].getClassName());

	}

	/**
	 * 
	 * Trace等级日志，小于debug<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void trace(String format, Object... arguments) {

		trace(innerGet(), format, arguments);

	}

	/**
	 * 
	 * Trace等级日志，小于Debug
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void trace(Logger log, String format, Object... arguments) {

		log.trace(format, arguments);

	}

	// ------------------------ debug
	public static void debug(String format) {

		debug(innerGet(), format);

	}
	/**
	 * 
	 * Debug等级日志，小于Info<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void debug(String format, Object... arguments) {

		debug(innerGet(), format, arguments);

	}

	/**
	 * 
	 * Debug等级日志，小于Info
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void debug(Logger log, String format, Object... arguments) {

		log.debug("["+log.getName()+"] "+format, arguments);

	}
	
	
	public static void debug(Logger log,int lineNumber, String format, Object... arguments) {

		log.debug("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments);

	}

	// ------------------------ info
	
	public static void info(String message) {

		info(innerGet(), message);
	}

	/**
	 * 
	 * Info等级日志，小于Warn<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void info(String format, Object... arguments) {

		info(innerGet(), format, arguments);

	}

	/**
	 * 
	 * Info等级日志，小于Warn
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void info(Logger log, String format, Object... arguments) {

		log.info("["+log.getName()+"] "+format, arguments);

	}
	
	
	public static void info(Logger log, int lineNumber,String format, Object... arguments) {

		log.info("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments);

	}
	// ------------------------ warn
	public static void warn(String message) {

		warn(innerGet(), message);

	}
	/**
	 * 
	 * Warn等级日志，小于Error<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void warn(String format, Object... arguments) {

		warn(innerGet(), format, arguments);

	}

	/**
	 * 
	 * Warn等级日志，小于Error
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void warn(Logger log, String format, Object... arguments) {

		log.warn("["+log.getName()+"] "+format, arguments);

	}
	
	public static void warn(Logger log,int lineNumber, String format, Object... arguments) {

		log.warn("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments);

	}

	/**
	 * 
	 * Warn等级日志，小于Error<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param e
	 *            需在日志中堆栈打印的异常
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void warn(Throwable e, String format, Object... arguments) {

		warn(innerGet(), e, format(format, arguments));

	}

	/**
	 * 
	 * Warn等级日志，小于Error
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param e
	 *            需在日志中堆栈打印的异常
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void warn(Logger log, Throwable e, String format, Object... arguments) {

		log.warn(format("["+log.getName()+"] "+format, arguments), e);

	}
	
	
	public static void warn(Logger log, int lineNumber,Throwable e, String format, Object... arguments) {

		log.warn(format("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments), e);

	}

	// ------------------------ error
	public static void error(String message) {
		error(innerGet(), message);

	}
	/**
	 * 
	 * Error等级日志<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void error(String format, Object... arguments) {
		error(innerGet(), format, arguments);

	}
	
	
	
	
	

	/**
	 * 
	 * Error等级日志<br>
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void error(Logger log, String format, Object... arguments) {

		log.error("["+log.getName()+"] "+format, arguments);

	}
	
	public static void error(Logger log,int lineNumber, String format, Object... arguments) {

		log.error("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments);

	}
	
	

	/**
	 * 
	 * Error等级日志<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param e
	 *            需在日志中堆栈打印的异常
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void error(Throwable e, String format, Object... arguments) {

		error(innerGet(), e, format(format, arguments));

	}



	
	
	/**
	 * 
	 * Error等级日志<br>
	 * 
	 * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
	 * 
	 * @param log
	 *            日志对象
	 * 
	 * @param e
	 *            需在日志中堆栈打印的异常
	 * 
	 * @param format
	 *            格式文本，{} 代表变量
	 * 
	 * @param arguments
	 *            变量对应的参数
	 * 
	 */

	public static void error(Logger log, Throwable e, String format, Object... arguments) {

		log.error(format("["+log.getName()+"] "+format, arguments), e);

	}
	
	public static void error(Logger log,int lineNumber, Throwable e, String format, Object... arguments) {

		log.error(format("["+log.getName()+", lineNumber="+lineNumber+" ] "+format, arguments), e);

	}
	

	// ----------------------------------------------------------- Logger method
	// end

	// ----------------------------------------------------------- Private
	// method start

	/**
	 * 
	 * 格式化文本
	 * 
	 * @param template
	 *            文本模板，被替换的部分用 {} 表示
	 * 
	 * @param values
	 *            参数值
	 * 
	 * @return 格式化后的文本
	 * 
	 */

	private static String format(String template, Object... values) {

		return String.format(template.replace("{}", "%s"), values);

	}

	/**
	 * 
	 * @return 获得日志，自动判定日志发出者
	 * 
	 */

	private static Logger innerGet() {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		return LoggerFactory.getLogger(stackTrace[3].getClassName());

	}

	// ----------------------------------------------------------- Private
	// method end

	
	
	
	
	    /** 
	     * 获取适配日志器，供内部调用 
	     *  
	     * @return 
	     */  
	    @SuppressWarnings({ "restriction", "deprecation" })  
	    private static LocationAwareLogger getLocationAwareLogger(final int depth) {  
	        String className = sun.reflect.Reflection.getCallerClass(depth)  
	                .getName();  
	        return (LocationAwareLogger) LoggerFactory.getLogger(className);  
	    }  
	  
	    /** 
	     * 静态的获取日志器 
	     *  
	     * @return 
	     */  
	    public static Logger getLogger() {  
	        return getLocationAwareLogger(3);  
	    }  
	  
	    public static String getName() {  
	        return getLocationAwareLogger(3).getName();  
	    }  
	  
	    public static boolean isTraceEnabled() {  
	        return getLocationAwareLogger(3).isTraceEnabled();  
	    }  
	  
	    public static void trace3(String msg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void trace3(String format, Object arg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void trace3(String format, Object arg1, Object arg2) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void trace3(String format, Object... arguments) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format, arguments, null);  
	    }  
	  
	    public static void trace3(String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isTraceEnabled(Marker marker) {  
	        return getLocationAwareLogger(3).isTraceEnabled(marker);  
	    }  
	  
	    public static void trace3(Marker marker, String msg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void trace3(Marker marker, String format, Object arg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void trace3(Marker marker, String format, Object arg1,  
	            Object arg2) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void trace3(Marker marker, String format, Object... argArray) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.TRACE_INT, format, argArray, null);  
	    }  
	  
	    public static void trace3(Marker marker, String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isInfoEnabled() {  
	        return getLocationAwareLogger(3).isInfoEnabled();  
	    }  
	  
	    public static void info3(String msg) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.INFO_INT,  
	                msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void info3(String format, Object arg) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.INFO_INT,  
	                format, new Object[] { arg }, null);  
	    }  
	  
	    public static void info3(String format, Object arg1, Object arg2) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.INFO_INT,  
	                format, new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void info3(String format, Object... arguments) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.INFO_INT,  
	                format, arguments, null);  
	    }  
	  
	    public static void info3(String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.INFO_INT,  
	                msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isInfoEnabled(Marker marker) {  
	        return getLocationAwareLogger(3).isInfoEnabled(marker);  
	    }  
	  
	    public static void info3(Marker marker, String msg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void info3(Marker marker, String format, Object arg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.INFO_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void info3(Marker marker, String format, Object arg1,  
	            Object arg2) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.INFO_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void info3(Marker marker, String format, Object... argArray) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.INFO_INT, format, argArray, null);  
	    }  
	  
	    public static void info3(Marker marker, String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isDebugEnabled() {  
	        return getLocationAwareLogger(3).isDebugEnabled();  
	    }  
	  
	    public static void debug3(String msg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void debug3(String format, Object arg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void debug3(String format, Object arg1, Object arg2) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void debug3(String format, Object... arguments) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format, arguments, null);  
	    }  
	  
	    public static void debug3(String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isDebugEnabled(Marker marker) {  
	        return getLocationAwareLogger(3).isDebugEnabled(marker);  
	    }  
	  
	    public static void debug3(Marker marker, String msg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void debug3(Marker marker, String format, Object arg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void debug3(Marker marker, String format, Object arg1,  
	            Object arg2) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void debug3(Marker marker, String format, Object... argArray) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, format, argArray, null);  
	    }  
	  
	    public static void debug3(Marker marker, String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isWarnEnabled() {  
	        return getLocationAwareLogger(3).isWarnEnabled();  
	    }  
	  
	    public static void warn3(String msg) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.WARN_INT,  
	                msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void warn3(String format, Object arg) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.WARN_INT,  
	                format, new Object[] { arg }, null);  
	    }  
	  
	    public static void warn3(String format, Object arg1, Object arg2) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.WARN_INT,  
	                format, new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void warn3(String format, Object... arguments) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.WARN_INT,  
	                format, arguments, null);  
	    }  
	  
	    public static void warn3(String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(null, thisClassName, LocationAwareLogger.WARN_INT,  
	                msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isWarnEnabled(Marker marker) {  
	        return getLocationAwareLogger(3).isWarnEnabled(marker);  
	    }  
	  
	    public static void warn3(Marker marker, String msg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void warn3(Marker marker, String format, Object arg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.WARN_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void warn3(Marker marker, String format, Object arg1,  
	            Object arg2) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.WARN_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void warn3(Marker marker, String format, Object... argArray) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.WARN_INT, format, argArray, null);  
	    }  
	  
	    public static void warn3(Marker marker, String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isErrorEnabled() {  
	        return getLocationAwareLogger(3).isErrorEnabled();  
	    }  
	  
	    public static void error3(String msg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void error3(String format, Object arg) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void error3(String format, Object arg1, Object arg2) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void error3(String format, Object... arguments) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format, arguments, null);  
	    }  
	  
	    public static void error3(String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(null, thisClassName,  
	                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);  
	    }  
	  
	    public static boolean isErrorEnabled(Marker marker) {  
	        return getLocationAwareLogger(3).isErrorEnabled(marker);  
	    }  
	  
	    public static void error3(Marker marker, String msg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);  
	    }  
	  
	    public static void error3(Marker marker, String format, Object arg) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format, new Object[] { arg },  
	                null);  
	    }  
	  
	    public static void error3(Marker marker, String format, Object arg1,  
	            Object arg2) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format,  
	                new Object[] { arg1, arg2 }, null);  
	    }  
	  
	    public static void error3(Marker marker, String format, Object... argArray) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.ERROR_INT, format, argArray, null);  
	    }  
	  
	    public static void error3(Marker marker, String msg, Throwable t) {  
	        getLocationAwareLogger(3).log(marker, thisClassName,  
	                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);  
	    }  

	    
	    /********************4************************/
	    /** 
	    * 是否打印日志，true表示打印日志，false表示不打印。 
	    * <p> 
	    * 开发阶段可以将其设为ture，运行阶段可以设为false 
	    */  
	    private static final boolean enabled = true;  
	    /** 是否进行源代码定位，ture表示输出源代码所在类以及代码行 */  
	    private static boolean showLocSrc = true;  
	    /** 指定的日志级别 */  
	    private static int level = 1;  
	    /** 日志级别：普通 */  
	    private static final int info = 1;  
	    /** 日志级别：调试 */  
	    private static final int debug = 2;  
	    /** 日志级别：警告 */  
	    private static final int warn = 3;  
	    /** 日志级别：错误 */  
	    private static final int error = 4;  
	    /** 消息所属和消息内容的分隔符 */  
	    private static final String msgSplit = ":";  
	    /** 默认输出日志的日志工具：log4j */  
//	    private static org.apache.log4j.Logger logger = null;
//
//	    static {
//	    	   // 可以单独指定log4j的配置文件，也可以使用默认。
//	    	   // org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
//	    	   // 得到logger实例，作为输出工具。
//	    	   // 此句作用是用一个输出实例，取代每个类里面的： Logger.getLogger(X.class)
//	    	   logger = org.apache.log4j.Logger.getLogger("");
//	    	}
	    /** 
	    * 根据日志级别，输出日志。 
	    * <p> 
	    * 如果要改变日志输出工具， 
	    * <p> 
	    * 如：由原来的log4j改为System.out.println()或logging，则只需改动此类即可。 
	    *  
	    * @param level 
	    *            日志级别 
	    * @param message 
	    *            日志消息 
	    * @param ste 
	    *            堆栈信息。 
	    *            <p> 
	    *            如果不需要输出源代码信息，则只需将静态成员属性 showLocSrc设为false即可。 
	    */  
//	    private static void log4(int level, Object message, StackTraceElement[] ste) {
//	       if (ste != null) {
//	        // 加入源代码定位
//	        message = getStackMsg(ste) + msgSplit + message;
//	       }
//	       // 转入具体实现，此处为log4j，可以改为其他不同的日志实现。
//	       switch (level) {
//	       case info:
//	        logger.info(message);
//	        break;
//	       case debug:
//	        logger.debug(message);
//	        break;
//	       case warn:
//	        logger.warn(message);
//	        break;
//	       case error:
//	        logger.error(message);
//	        break;
//	       default:
//	        logger.debug(message);
//	       }
//	    }
	    /** 
	    * 根据堆栈信息得到源代码行信息 
	    * <p> 
	    * 原理：本工具类的堆栈下一行即为源代码的最原始堆栈。 
	    *  
	    * @param ste 
	    *            堆栈信息 
	    * @return 调用输出日志的代码所在的类.方法.代码行的相关信息 
	    *         <p> 
	    *         如：com.MyClass 类里的 fun()方法调用了Logs.debug("test"); 
	    *         <p> 
	    *         则堆栈信息为: com.MyClass.fun(MyClass.java 代码行号) 
	    */  
	    private static String getStackMsg(StackTraceElement[] ste) {  
	       if (ste == null) {
			   return null;
		   }
	       boolean srcFlag = false;  
	       for (int i = 0; i < ste.length; i++) {  
	        StackTraceElement s = ste[i];  
	        // 如果上一行堆栈代码是本类的堆栈，则该行代码则为源代码的最原始堆栈。  
	        if (srcFlag) {  
	         return s==null?"":s.toString();  
	        }  
	        // 定位本类的堆栈  
	        if (thisClassName.equals(s.getClassName())) {  
	         srcFlag = true;  
	        }  
	       }  
	       return null;  
	    }  
	    /** 
	    * 输出info信息 
	    *  
	    * @param message 
	    */  
//	    public static void info4(Object message) {
//	       // 如果禁止日志或者输出级别不符，则返回。
//	       if (!enabled || info < level)
//	        return;
//	       if (showLocSrc) {
//	        log4(info, message, Thread.currentThread().getStackTrace());
//	       } else {
//	        log4(info, message, null);
//	       }
//	    }
//	    /**
//	    * 输出debug信息
//	    *
//	    * @param message
//	    */
//	    public static void debug4(Object message) {
//	       // 如果禁止日志或者输出级别不符，则返回。
//	       if (!enabled || debug < level)
//	        return;
//	       if (showLocSrc) {
//	        log4(debug, message, Thread.currentThread().getStackTrace());
//	       } else {
//	        log4(debug, message, null);
//	       }
//	    }
//	    /**
//	    * 输出warn信息
//	    *
//	    * @param message
//	    */
//	    public static void warn4(Object message) {
//	       // 如果禁止日志或者输出级别不符，则返回。
//	       if (!enabled || warn < level)
//	        return;
//	       if (showLocSrc) {
//	        log4(warn, message, Thread.currentThread().getStackTrace());
//	       } else {
//	        log4(warn, message, null);
//	       }
//	    }
//	    /**
//	    * 输出error信息
//	    *
//	    * @param message
//	    */
//	    public static void error4(Object message) {
//	       // 如果禁止日志或者输出级别不符，则返回。
//	       if (!enabled || error < level)
//	        return;
//	       if (showLocSrc) {
//	        log4(error, message, Thread.currentThread().getStackTrace());
//	       } else {
//	        log4(error, message, null);
//	       }
//	    }
	   
}
