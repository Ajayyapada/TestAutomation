package Lib.Common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import Lib.Controller.Driver;

public class CommonWeb {

	/*
	 * this function will launch the default URL specified in the Environment.xml file
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 */
	public void LaunchURL(){
		WebDriver otDriver = Driver.Gprops.GetWebDriver();
		//oDC.setCapability(CapabilityType.PROXY, false);
		String BaseURL = "";
		try{
			if (Driver.Init.GetBaseURL().startsWith("http") || Driver.Init.GetBaseURL().startsWith("https")){
				BaseURL = Driver.Init.GetBaseURL();
			}
			else{
				BaseURL = "http://" + Driver.Init.GetBaseURL();
			}
			otDriver.get(BaseURL);
			//Sonu-Commented the wait time
			//Thread.sleep(10000); 
			if (PageValidation(otDriver, "LaunchURL", BaseURL, Driver.Init.GetResultPath())){
				System.exit(-1);
			}
		}
		catch(Exception ex){
			Reporter.log(ex.getMessage());
		}
		
	}

	/**
	 * this function will launch the URL specified as the input to this function
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @param	otDriver	The Webdriver object
	 * @param	URL	The URL to which we need to browse to
	 * @exception Exception
	 */
	public boolean LaunchCustomURL(String URL){
		boolean Flag = true;
		try{
			WebDriver otDriver = Driver.Gprops.GetWebDriver();
			if(otDriver==null){
				Driver.WebCtrl.GetWebDriver(Driver.Init.GetAUTBrowser(), Driver.Init.GetOS64Bit());
				otDriver = Driver.Gprops.GetWebDriver();
			} 
			
			String BaseURL = "";
			if (URL.startsWith("http") || URL.startsWith("https")){
				BaseURL = URL;
			}
			else{
				BaseURL = "http://" + URL;
			}
			otDriver.get(BaseURL);
			Thread.sleep(5000);
			if (PageValidation(otDriver, "LaunchCustomURL", BaseURL, Driver.Init.GetResultPath())){
				System.exit(-1);
				Flag = false;
			}
		}
		catch(Exception ex){
			Flag = false;
			System.out.println(ex.getMessage());
		}
		return Flag;
	}
	
	/**
	 * This function validates the error text in the page once it loaded
	 * @author	Murthi S	<murthis@collabera.com>
	 * @param OBrowser the web driver object
	 * @param pageName the name of the page for reporting
	 * @param Expected URL the expected url of the page
	 * @param ResultFolderpath the folder to store the screenshot of the error if exists
 	 * @return  Boolean true if page contains errors, false if not
	 * @version	1.0
	 */
	public boolean PageValidation(WebDriver oBrowser,String pageName,String ExpectedUrl,String ResultFolderPath)
	{
		Boolean containsErrText=false;
		String[] arrErrorText={"Oops, there's been a glitch!","Sever Error","Failed to run solr search","The webpage cannot be found","HTTP 404","The page cannot be found","Page Not Found","MVPO_Data.poResponse is empty for MRP ID","failed to run solr search","The resource cannot be found","Http 1.1 Service Unavailable"};
		for(int Iter=0; Iter<arrErrorText.length;Iter++)
		{
			//String pageTitle=oBrowser.getTitle();
			String pageSource=oBrowser.getPageSource();
			String ErrorText=arrErrorText[Iter];
		
			//Assert.assertEquals(pageTitle.toLowerCase().contains(ErrorText.toLowerCase()), false);
			if(pageSource.toLowerCase().contains(ErrorText.toLowerCase()))
			{
				containsErrText=true;
				//String ImagePath = Driver.CUtil.CaptureScreenShot(oBrowser,pageName,Driver.ResultPath);
				Reporter.log(pageName+" Page consists of error text: " + ErrorText + ". Test can't proceed further");
				
				//Driver.htmlRpt.WriteHTMLReport(pageName, "Page consists of error text:"+ErrorText+". Test can't proceed further",ExpectedUrl, oBrowser.getCurrentUrl(), "fail",ImagePath);
			}
			else
			{
				Reporter.log(pageName+" Page doesn't consist of error text:"+ErrorText);
				//containsErrText=true;
				//Driver.htmlRpt.WriteHTMLReport(pageName, "Page doesn't consists of error text:"+ErrorText,ExpectedUrl, oBrowser.getCurrentUrl(), "pass","");
			}
		}
		return containsErrText;
	}
	
	
		
	/**
	 * This function validates the error text in the page once it loaded
	 * @author	Ditimoni Borpatra Gohain	<DBorpatra@move.com>
	 * @param OBrowser the web driver object
	 * @param pageName the name of the page for reporting
 	 * @return  it will return string ie. the error
	 * @version	1.0
	 */
	public String PageValidation(WebDriver oBrowser, String pageName)
	{
		String ErrorText="";
		String[] arrErrorText={"this website is under heavy load","We Are Sorry.","Oops. It's our fault, not yours.","ooops. guilty as charged.","Oh, this stinks.","Oops, there's been a glitch!","Sever Error","Server not found","The webpage cannot be found","HTTP 404","The page cannot be found","Page Not Found","MVPO_Data.poResponse is empty for MRP ID","failed to run solr search","The resource cannot be found","File or directory not found","Http/1.1 Service Unavailable","NoMethodError","Sorry, invalid request or we had a problem finding a property record","We're sorry, but something went wrong", "Bad Gateway","It appears we came up empty"};
		String pageSource=oBrowser.getPageSource();
		//System.out.println(pageSource);
		//Added by ram on 7/20/2015 to handle iPad execution
		if(pageSource.contains("Firefox can't load this page for some reason.")){								
			Driver.Gprops.GetWebDriver().navigate().refresh();
			System.out.println("Page Refreshed");
		}
		//Till Here
		for(int Iter=0; Iter<arrErrorText.length;Iter++)
		{
			ErrorText=arrErrorText[Iter];
			if(pageSource.toLowerCase().contains(ErrorText.toLowerCase()))
			{
				System.out.println(pageName+" Page consists of error text: " + ErrorText + ". Test can't proceed further");
				break;
			}
			else
			{
				System.out.println(pageName+" Page doesn't consist of error text:"+ErrorText);
				ErrorText="";
			}
		}
		return ErrorText;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	 /**
     * This function will take List Webelement and the item to select in the list and selects the same
     * @author krishnakg
     * @param WebEl	The Select webelement
     * @param SelectText	the text to be selected in the list
     * @return	boolean		true if the select is done else fail
     * @exception Exception
     */
    public boolean selectInSelect(WebElement WebEl, String SelectText){
    	try{
    		boolean flag = false;
    		Select WebSelect = new Select(WebEl);
    		List<WebElement> Options = WebSelect.getOptions();
    		for (int x=0;x<Options.size();x++){
    			if(Options.get(x).getText().toLowerCase().trim().equals(SelectText.toLowerCase().trim()))
    				flag = true;
    		}
    		if(flag){
    			WebSelect.selectByVisibleText(SelectText);
    			return true;
    		}
    		else
    			return false;
    	}
    	catch(Exception ex){
    		System.out.println("Error @ selectInSelect");
    		System.out.println(ex.getMessage());
    		return false;
    	}
    }

/**
 * This function will wait for ajax calls to finish
 * Updated by Krishna Kishore G on 01/29/2014 
 * @author MurthiS
 * @param WebDriver driver object
 * @param int	number of seconds to wait
 * @exception Exception InterruptedException
 */
public void waitForAjax(int timeoutInSeconds)  {
	  WebDriver driver = Driver.Gprops.GetWebDriver();
	  System.out.println("Checking active ajax calls by calling jquery.active");
	  try {
		    if (driver instanceof JavascriptExecutor) {
		    	JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
						
		        for (int i = 0; i< timeoutInSeconds; i++) 
		        {
				    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
				    // return should be a number
				    if (numberOfAjaxConnections instanceof Long) {
				        Long n = (Long)numberOfAjaxConnections;
				        System.out.println("Number of active jquery ajax calls: " + n);
				        if (n.longValue() == 0L)
				       	  break;
				     }
			         Thread.sleep(1000);
				}
			}
			else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		}
		catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
/**
 * This function closes all other tabs and browser except main browser or tab
 * @author MurthiS
 * @param WebDriver driver object
 */

public void closeSecondaryBrowsers(WebDriver otDriver)
{
	//Store the current window handle
	String winHandleBefore = otDriver.getWindowHandle();

	//Perform the click operation that opens new window

	//Switch to new window opened
	for(String winHandle : otDriver.getWindowHandles()){
		otDriver.switchTo().window(winHandle);
	}

	// Perform the actions on new window

	//Close the new window, if that window no more required
	otDriver.close();

	//Switch back to original browser (first window)

	otDriver.switchTo().window(winHandleBefore);

}

/**
 * This function executes the java script using driver
 * @param jscode
 * @author msubramani
 * @DateCreated 1/14/2015
 */
	public String executeJavascript(String jscode){
		WebDriver driver = Driver.Gprops.GetWebDriver();
		return (String) ((JavascriptExecutor)driver).executeScript(jscode);
	}

	/**
	 * This is a web utility method which captures and returns Javascript errors which happen to appear during page load or any actions on the web objects.
	 * @author Arun Kumar S<arun.kumar@move.com>
	 * @creationDate 03/11/2016
	 * @param driver -Provide current thread web driver instance.
	 */
	@SuppressWarnings("unchecked")
	public List<String> getJavaScriptErrors(WebDriver driver)
	{
		List<String> list = new ArrayList<String>();
		try 
		{
			if(Driver.Init.GetAUTBrowser().contains("ff")) {
				List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
				for(JavaScriptError jsError: jsErrors)
					if(!jsError.toString().contains("mutating the [[Prototype]] of an object will cause your code to run very slowly"))
						list.add(jsError.toString());
			}
			else {
				/*JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"window.jsErrors = [];\n" +
						"window.onerror = function(errorMessage) {\n" +
						"window.jsErrors[window.jsErrors.length] = errorMessage;\n }"
						);*/
				Object jsErrors = ((JavascriptExecutor) driver).executeScript("return window.jsErrors");

				if(jsErrors!=null) {
					for(String jsError: (List<String>)jsErrors) {
						list.add(jsError.toString());
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception occured during JavaScriptError");
		}
		return list;
	}
	
	
//*******************************************************************************************************************************/
/**
* This function will do a slow scroll down vertically by the passed pixels and the passed Scroll count. Then it scrolls up to go to the top of the page to handle the Lazy Load implementation.
* @creationdate 03/21/2016   
* @author Johnsi Rani <johnsirani.sivakumar@move.com>
* param1 noOfTimesToScroll: Pass the scroll count, i.e no of times to scroll
* @param2 pixelsToScroll: Pass the pixels to scroll 
* @throws
**/
//*******************************************************************************************************************************/
public void pageScroll(int noOfTimesToScroll, int pixelsToScroll)
{
	//Variables declaration and Initialization
	String functionName = "pageScroll";
	WebDriver driver = Driver.Gprops.GetWebDriver();
	JavascriptExecutor jse = (JavascriptExecutor)driver;	
	int scrollCount = 0;		
	try
	{
		//Scrolls down the page vertically based on the pixels passed
		for(scrollCount = 1;; scrollCount++)
		{
	        if(scrollCount > noOfTimesToScroll){
	            break;	            
	        }  
	        jse.executeScript("window.scrollBy(0," + pixelsToScroll + ");");    		
		}		
		System.out.println("No of times page scrolled down vertically is: " + noOfTimesToScroll);
		//Scrolling to the top of the Page by pressing 'CTRL + HOME' keys
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
		System.out.println("Scrolling the page to the top by pressing 'CTRL + HOME' keys");
		//Waits to a maximum of 5 seconds for the Header to be visible 
		Driver.WebCtrl.waitTillElementVisibility("RWD/Header", "RealtorLogoInHeader", 5);				
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Driver.CUtil.WriteResults("Exception occurred during Page Scroll", functionName, "Fail");
		
	}
}
	
/***********************************************************************************************************************************************************************
 * This utility function captures console errors for RDC Project.
 * @creationDate 06/28/2016
 * @author Arun Kumar S <arun.kumar@move.com>
 * @param page Provide name of the page or event name or object name. 
 *************************************************************************************************************************************************************************/

public void verifyConsoleErrors(String page) {
	String functionName = "verifyConsoleErrors";
	List<String> errorList = null;
	boolean isBypassed = false;
	try {
		
		if(!System.getProperty("ProjectName").toLowerCase().contains("rwdrdc"))
			return;
		
		if(!page.isEmpty()) {
			Driver.WebCtrl.waitTillPageLoad(10);
			Driver.CWeb.waitForAjax(10);
			page = "in "+page;
		}


		errorList = Driver.CWeb.getJavaScriptErrors(Driver.Gprops.GetWebDriver());

		if(errorList.isEmpty()) {
			if(!page.isEmpty())
				Driver.CUtil.WriteResults("Console errors not found "+page, functionName,"Pass");
		}
		else {
			String specFilePath=Driver.Init.GetBasePath()+"/"+System.getProperty("ProjectName").trim()+"/TrackConsoleErrorsInJira";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(specFilePath));
				String line = null;		

				Driver.CUtil.WriteResults(errorList.size()+" console error/s found "+page+"<br>"+errorList);
				for(String error : errorList) {
					while ((line = br.readLine()) != null) {
						String[] errorContent=line.split(",",3);
						if(!errorContent[0].startsWith("#"))
							if(error.contains(errorContent[1])) {
								if(errorContent[0].contains("CORE-"))
									Driver.CUtil.WriteResults("Jira ticket logged for this issue is: <a href=\"https://jira.move.com/browse/"+errorContent[0]+"\">https://jira.move.com/browse/"+errorContent[0]+"</a>");
								else if(errorContent[0].contains("SKIP"))
									Driver.CUtil.WriteResults("Skipping this console error, "+ ((int)errorContent.length > 2 ? errorContent[2]:"reason not specified under TrackConsoleErrorsInJira.") + " ["+errorContent[1]+"]");

								isBypassed = true;
								break;
							}
						isBypassed = false;
					}

					if(!isBypassed)
					{
						Driver.CUtil.WriteResults("Found a new console error ["+error+"] <b>Please create a Jira ticket to track this error.</b>");
						Assert.assertTrue(false);
					}
				}
				br.close();
			}
			catch (Exception e) {
				Driver.CUtil.WriteResults("Exception occurred. "+e.getMessage(),functionName, "Fail");
				Assert.assertTrue(false);
			}
		}
	}
	catch(Exception e) {
		Driver.CUtil.WriteResults("Exception occurred. "+e.getMessage(),functionName, "Fail");
		Assert.assertTrue(false);
	}
}


/***********************************************************************************************************************************************************************
 * Overloaded method for verifyConsoleErrors(page)
 * @creationDate 06/28/2016
 * @author Arun Kumar S <arun.kumar@move.com>
 *************************************************************************************************************************************************************************/
public void verifyConsoleErrors() {
	verifyConsoleErrors("");
}

}
