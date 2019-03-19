package Lib.Exceptions;

public class NoProjectNameFound extends Exception {
	
	private static final long serialVersionUID = -2840809967908637447L;

	public NoProjectNameFound(){
		System.out.println("No project name specified in the ant argument");
	}

}
