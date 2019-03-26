package com.tav.coupons.exceptions;

import javax.ws.rs.core.Response;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.tav.coupons.beans.ErrorBean;
import com.tav.coupons.logger.Log;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable>{

	// This shit collapses the handler for some reason
	//private static final Logger LOG = Log.getLog();

	@Override
	public Response toResponse(Throwable exception) {
		/*  The first if-statement checks if the exception is an instance of ApplicationException, 
		    and if so, sends the appropriate ErrorCode and information.
		  
		The else-if-statement checks if the exception is an instance of Exception, and if so,
		sends a 601 status code
		
		if neither of the conditions are met, a 500 status code is sent
		 */
		if (exception instanceof ApplicationException) {
			ApplicationException e = (ApplicationException) exception;
			
			int internalErrorCode = e.getErrorType().getInternalErrorCode();
			String internalMessage = e.getMessage();
			String externalMessage = e.getErrorType().getInternalMessage();
			ErrorBean errorBean = new ErrorBean(internalErrorCode, internalMessage, externalMessage);
			return Response.status(internalErrorCode).entity(errorBean).build();
			
		} else if (exception instanceof Exception) {
			
			String iternalMessage = exception.getMessage();
			ErrorBean errorBean = new ErrorBean(601, iternalMessage,"General error");
			return Response.status(601).entity(errorBean).build();
		}
		
		return Response.status(501).entity(null).build();
	}

	
}
