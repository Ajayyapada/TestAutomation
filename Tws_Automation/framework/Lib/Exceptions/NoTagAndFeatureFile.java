package Lib.Exceptions;

public class NoTagAndFeatureFile extends Exception {

	private static final long serialVersionUID = -2840809967908637447L;

	public NoTagAndFeatureFile(){
		System.out.println("No feature file name and tag are specified in the ant argument");
	}

}

