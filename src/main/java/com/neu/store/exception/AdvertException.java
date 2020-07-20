package com.neu.store.exception;

public class AdvertException extends Exception
{
	public AdvertException(String msg)
	{
		super("AdvertException-"+ msg);
	}
	
	public AdvertException(String msg, Throwable c)
	{
		super("AdvertException-"+ msg,c);
	}
}
