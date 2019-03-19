package Lib.Exceptions;

public class ObjectNotFound extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4459270069658502264L;

	public ObjectNotFound(String ExpMsg){
		System.out.println(ExpMsg);
	}
	
}
