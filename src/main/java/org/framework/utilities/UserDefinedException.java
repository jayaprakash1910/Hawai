package org.framework.utilities;

public class UserDefinedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UserDefinedException() {
		super();
	}
	
	public UserDefinedException(Exception e) {
		super(e);
	}
}
