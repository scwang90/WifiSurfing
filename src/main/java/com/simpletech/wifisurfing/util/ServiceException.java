package com.simpletech.wifisurfing.util;

/**
 * Service层公用的Exception.
 * @author 树朾
 * @date 2015-11-18 09:39:08 中国标准时间
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}