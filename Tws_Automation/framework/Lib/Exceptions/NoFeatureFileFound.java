package Lib.Exceptions;

public class NoFeatureFileFound extends Exception {

	private static final long serialVersionUID = -2840809967908637447L;

	public NoFeatureFileFound(){
		System.out.println("No feature file name specified in the ant argument");
	}

}
