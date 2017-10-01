package com.marcos.ryanair.interconnectingflights.service.exception;

/**
 * Exception fot the service layer.
 * 
 * @author mlg
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7446822521674239851L;

	public ServiceException(String message, Throwable thr) {
		super(message, thr);
	}

}
