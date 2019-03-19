package Lib.Exceptions;

public class NoSearchBtnFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032022024379450734L;

	public NoSearchBtnFound(){
		System.out.println("The Search button in the Home page search widget is not available");
	}
}
