package com.listen.sspg.basecore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.util.StringUtil;

/**
 * 接口统一返回对象
 * @author Listen
 * @date 2019/2/26
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ApiReturnObj<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private T datas;

	public ApiReturnObj() {

	}
/*
	public ApiReturnObj(int code, T datas) {
		super();
		this.code = code;
		String message = ErrorCodePropertiesUtil.getErrorCodeValueByKeyI18n(String.valueOf(code));
		if (message != null && !"".equals(message)) {
			this.msg = message;
		}

		if (datas instanceof String || datas instanceof Byte || datas instanceof Short || datas instanceof Integer
				|| datas instanceof Long || datas instanceof Float || datas instanceof Double
				|| datas instanceof Boolean) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", datas);
			this.datas = (T) map;
		} else {
			this.datas = datas;
		}

	}*/

	
	public ApiReturnObj(int code, String msg, T datas) {
		super();
		this.code = code;
		this.msg = msg;
		if (datas instanceof String || datas instanceof Byte || datas instanceof Short || datas instanceof Integer
				|| datas instanceof Long || datas instanceof Float || datas instanceof Double
				|| datas instanceof Boolean) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", datas);
			this.datas = (T) map;
		} else {
			this.datas = datas;
		}

	}
	
/*
	public ApiReturnObj(int code, String msg, T datas, boolean isAppendMsg) {
		super();
		this.code = code;
		String message = ErrorCodePropertiesUtil.getErrorCodeValueByKeyI18n(String.valueOf(code));
		if(StringUtil.isEmpty(msg)){
			this.msg = message;
		}else{
			if (isAppendMsg) {
				if (!StringUtil.isEmpty(message)) {
					this.msg = message + " ( " + msg + " ) ";
				} else {
					this.msg = msg;
				}

			} else {
				this.msg = msg;
			}
		}
		
		if (datas instanceof String || datas instanceof Byte || datas instanceof Short || datas instanceof Integer
				|| datas instanceof Long || datas instanceof Float || datas instanceof Double
				|| datas instanceof Boolean) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", datas);
			this.datas = (T) map;
		} else {
			this.datas = datas;
		}

	}*/

	public ApiReturnObj(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;

	}

/*	public ApiReturnObj(int code, String msg, boolean isAppendMsg) {
		super();
		this.code = code;
		String message = ErrorCodePropertiesUtil.getErrorCodeValueByKeyI18n(String.valueOf(code));
		if(StringUtil.isEmpty(msg)){
			this.msg = message;
		}else{
			if (isAppendMsg) {
				if (!StringUtil.isEmpty(message)) {
					this.msg = message + " ( " + msg + " ) ";
				} else {
					this.msg = msg;
				}

			} else {
				this.msg = msg;
			}
		}

	}
*/
	public ApiReturnObj(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

}
