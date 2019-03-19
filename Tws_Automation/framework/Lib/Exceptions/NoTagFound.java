package Lib.Exceptions;

public class NoTagFound extends Exception {
	private static final long serialVersionUID = -2840809967908637447L;

	public NoTagFound(){
		System.out.println("No tag name specified in the ant argument");
	}


}
