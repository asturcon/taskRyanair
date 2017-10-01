package com.marcos.ryanair.interconnectingflights.provider.exception;

/**
 * Exception for errors when fetching data from providers.
 * 
 * @author mlg
 *
 */
public class DataProviderException extends Exception {

	private static final long serialVersionUID = 6502220779957797867L;

	public DataProviderException(String message, Throwable thr) {
		super(message, thr);
	}

}
