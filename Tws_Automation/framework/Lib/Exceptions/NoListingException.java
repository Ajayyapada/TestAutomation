/**
 * 
 */
package Lib.Exceptions;

/**
 * @author krishnakg
 *
 */
public class NoListingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885501390745275655L;
	
	public NoListingException(){
		System.out.println("Specified Listing is not available");
	}
	
	public NoListingException(String Address){
		System.out.println(Address + " is not available on searching");
	}
}
