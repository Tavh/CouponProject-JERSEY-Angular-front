package com.tav.coupons.logger;

import org.apache.log4j.Logger;

public class Log {

	public static final Logger LOG = Logger.getLogger(Log.class);

	// This is a test
	public static void main(String[] args) {
		LOG.error("Logging exception ");
	}

	public static Logger getLog() {
		return LOG;
	}
	
	
}	

