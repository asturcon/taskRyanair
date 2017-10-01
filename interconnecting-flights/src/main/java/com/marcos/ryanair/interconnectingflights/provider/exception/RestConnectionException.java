package com.marcos.ryanair.interconnectingflights.provider.exception;

public class RestConnectionException extends Exception {

	private static final long serialVersionUID = 8034834735847590160L;

	public RestConnectionException(String message, Throwable thr) {
		super(message, thr);
	}
	
}
