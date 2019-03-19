package Lib.Exceptions;

public class NullDatabaseValue extends Exception{
	
	private static final long serialVersionUID = -2840809967908637447L;

	public NullDatabaseValue(){
		System.out.println("Null value returned from database");
	}


}
