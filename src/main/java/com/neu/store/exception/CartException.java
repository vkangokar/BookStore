package com.neu.store.exception;

public class CartException extends Exception
{
	public CartException(String msg)
	{
		super("CartException-"+ msg);
	}
	
	public CartException(String msg, Throwable c)
	{
		super("CartException-"+ msg,c);
	}
}
