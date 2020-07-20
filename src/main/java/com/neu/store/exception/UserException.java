package com.neu.store.exception;

public class UserException extends Exception {

	public UserException(String msg)
	{
		super("UserException-"+msg);
	}
	
	public UserException(String msg, Throwable c)
	{
		super("UserException-"+msg,c);
	}
	
}

