package com.example.spring.boot.demo.account.utils;

import java.util.Date;

public class AcctResult<T> {
	
	/**
	 * 成功
	 * @return
	 */
	public static <T> AcctResult<T> resultSuccessful() {
		return new AcctResult<T>() ;
	}
	/**
	 * 成功
	 * @param data 返回数据
	 * @return
	 */
	public static <T> AcctResult<T> resultSuccessful(T data) {
		return new AcctResult<T>(data) ;
	}
	/**
	 * 成功
     * @param status 状态码
     * @param msg 返回信息
     * @param data 返回数据
     */
	public static <T> AcctResult<T> resultSuccessful(Integer status,String msg, T data) {
		return new AcctResult<T>(status,msg,data) ;
	}
	public static <T> AcctResult<T> resultSuccessful(AcctResultCode acctResultCode, T data) {
		return new AcctResult<T>(acctResultCode.getStatus(),acctResultCode.getMsg(),data) ;
	}
	/**
	 * 失败
	 * @return
	 */
	public static <T> AcctResult<T> resultFailure() {
		return new AcctResult<T>(AcctResultCode.NORMAL_ERROR.getStatus(),AcctResultCode.NORMAL_ERROR.getMsg(),null) ;
	}
	/**
	 * 失败
	 * @param data
	 * @return
	 */
	public static <T> AcctResult<T> resultFailure(T data) {
		return new AcctResult<T>(AcctResultCode.NORMAL_ERROR.getStatus(),AcctResultCode.NORMAL_ERROR.getMsg(),data) ;
	}
	/**
	 * 失败
     * @param status 状态码
     * @param msg 返回信息
     * @param data 返回数据
     */
	public static <T> AcctResult<T> resultFailure(Integer status,String msg, T data) {
		return new AcctResult<T>(status,msg,data) ;
	}
	public static <T> AcctResult<T> resultFailure(AcctResultCode acctResultCode, T data) {
		return new AcctResult<T>(acctResultCode.getStatus(),acctResultCode.getMsg(),data) ;
	}
	/**
	 * 失败
	 * @param status 状态码
	 * @param msg 返回信息
	 * @param data 返回数据
	 */
	public static <T> AcctResult<T> resultFailure(Integer status,String msg) {
		return new AcctResult<T>(status,msg,null) ;
	}
	
	public static <T> AcctResult<T> resultFailure(AcctResultCode acctResultCode) {
		return new AcctResult<T>(acctResultCode.getStatus(),acctResultCode.getMsg(),null) ;
	}
	
	/**
     * 响应业务状态
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应的数据
     */
    private T data;
    
    private Date timestamp = new Date() ;
    /**
     * @param status 状态码
     * @param msg 返回信息
     * @param data 返回数据
     */
    private AcctResult(Integer status,String msg, T data) {
    	this.status = status ;
    	this.msg = msg;
    	this.data = data ;
    }
    private AcctResult(T data) {
    	this.data = data ;
    	this.status = AcctResultCode.SUCCESS.getStatus();
    	this.msg = AcctResultCode.SUCCESS.getMsg() ;
    }
    
    private AcctResult() {
    	this.status = AcctResultCode.SUCCESS.getStatus();
    	this.msg = AcctResultCode.SUCCESS.getMsg() ;
    }
	public Integer getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
}
