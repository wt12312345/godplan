package com.hq.web;

import org.apache.log4j.Logger;

/**
 * an abstract class has logger
 * */
public abstract class AbstractClass {
	/**
	 * the logger from log4j
	 * */
	protected Logger logger = null;

	public AbstractClass() {
		logger = Logger.getLogger(this.getClass());
	}
	
}
