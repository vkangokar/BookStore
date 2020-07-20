package com.neu.store.exception;

public class CategoryException extends Exception {

	public CategoryException(String msg)
	{
		super("CategoryException-"+msg);
	}
	
	public CategoryException(String msg, Throwable c)
	{
		super("CategoryException-"+msg,c);
	}
	
}
