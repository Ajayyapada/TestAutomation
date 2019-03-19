package Lib.Exceptions;

public class ObjIdException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2840809967908637447L;

	public ObjIdException(String Object){
		System.out.println(Object + " object has changed or has invalid locator, please check...");
	}
	
}
