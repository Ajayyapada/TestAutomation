package Lib.Exceptions;

import Lib.Controller.*;

public class CustomeThroable extends Throwable {

	public CustomeThroable(String Message){
		System.out.println("Throwable Exception " + Message);
	}

}
