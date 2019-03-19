/*
 * Author : Krishna Kishore G
 * E-Mail : krishnakg@collabera.com - krishnakishore.g@move.com
 * Description :
 * 				This Class contains framework functions that need to create a WebObjects that are to be 
 * 				used in the test.
 */

package Lib.Common;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import com.saucelabs.saucerest.SauceREST;

import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.uiautomation.ios.IOSCapabilities;
import org.xml.sax.InputSource;

import Lib.Controller.Driver;

import com.opera.core.systems.OperaDriver;

import cucumber.api.Scenario;


public class WebCtrlOperations {

	/*	Function : GetWebDriver
	 * 	Parameters : strBrowser of DataType String
	 * 	Description : Based on the BrowserType passed this function will create a WebDriver object 
	 * 					and sends back to the caller 
	 */
	public void GetWebDriver(String strBrowser,Boolean OS64Bit)
	{
		WebDriver objWD = null;
		DesiredCapabilities oDC = null;
		String IEDriverPath=null;
		String ChromeDriverPath=null;
		String OperaDriverPath="";
		String G2S = Driver.Init.GetGrid2Server();
		int WDCount=0;
		String driver="";
		FirefoxProfile ffprof=new FirefoxProfile();
		String PROXY_SERVER=null;
		Proxy proxy=null;
		//		System.out.println("Browser Before over write "+strBrowser);
		//		strBrowser = Driver.globalValues.get(Thread.currentThread().getName()+"browser");
		String disableJS="";
		String tunnelIdentifier = System.getenv("TUNNEL_IDENTIFIER");
		//		System.out.println("Browser after write "+strBrowser);

		//Storing sauce credentials to call the API
		Driver.globalValues.put("sauceusername", "offshoreautomation");
		Driver.globalValues.put("sauceaccesskey", "ca6b6f16-2ae3-4a94-88be-9c91b682fa02");

		try
		{
			try {
				//Adding firefox profile to JavaScriptError api
				JavaScriptError.addExtension(ffprof);
			}
			catch(Exception jse) {
				System.out.println("JavaScriptError api did not load hence continuing without its validation");
			}

			System.out.println("tunnelIdentifier= "+tunnelIdentifier);
			switch(strBrowser.toLowerCase())
			{
			case "ie":
				//This block is for local execution and it will not affect the team city execution					
				if(!OS64Bit)
				{
					IEDriverPath="C:/Selenium/rdc-web-automation-selenium/ADT_Resources/framework/BrowserDrivers/IEDriverServer32.exe";
				}
				else
				{
					IEDriverPath="C:/Selenium/rdc-web-automation-selenium/ADT_Resources/framework/BrowserDrivers/IEDriverServer64.exe";
				}
				File file = new File(IEDriverPath);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capabilities.setCapability("ignoreZoomSetting", true);
				try
				{
					Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2").waitFor();
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objWD = new InternetExplorerDriver(capabilities);
				break;
			case "ff":
				disableJS=Driver.globalValues.get(Thread.currentThread().getName()+"disableJS");
				if(disableJS!=null)
				{
					ffprof.setPreference(disableJS, false);
				}

				objWD = new FirefoxDriver(ffprof);
				break;

			case "gc": // added by shwetha
				ChromeDriverPath="C:\\Users\\ajay.yapada\\Desktop\\auto\\ADT_Resources\\Tws_Automation\\framework\\BrowserDrivers\\chromedriver.exe";
				File chromefile = new File(ChromeDriverPath);
				System.setProperty("webdriver.chrome.driver", chromefile.getAbsolutePath());
				//System.setProperty("webdriver.chrome.args", "--disable-logging");
				System.setProperty("webdriver.chrome.logfile","NULL");
				DesiredCapabilities chromecapabilities = DesiredCapabilities.chrome();
				chromecapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
				try
				{
					Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objWD = new ChromeDriver(chromecapabilities);
				break;

			default: 
				Driver.CUtil.WriteResults("Please check your browser name "+strBrowser+" in your environment file because it is not matching with any case" , "GetWebDriver", "fail");

			}
			if(objWD==null){
				Thread.sleep(10000);
				//				commented the below line by murthi on 1/30/2014
				//				WDCount=Integer.valueOf(Driver.globalValues.get(Thread.currentThread().getName()+"WDWaitCoun"));
				if(WDCount<720){
					WDCount+=1;
					//					Driver.globalValues.put(Thread.currentThread().getName()+"WDWaitCoun",Integer.toString(WDCount));
					GetWebDriver(strBrowser,OS64Bit);
				}

			}
			if((objWD!=null)){
				Driver.Gprops.SetWebDriver(objWD);
				String sessionID=(((RemoteWebDriver) objWD).getSessionId()).toString();
				System.out.println("Session ID = " +sessionID);
				Driver.globalValues.put(Thread.currentThread().getName()+"SessionID", sessionID);
				//added the code to display sauce lab report on jenkins by murthi on 10/16/2015
				//				if(System.getProperty("jenkins.build.id")!=null){
				//				    String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", 
				//				    (((RemoteWebDriver) objWD).getSessionId()).toString(), System.getenv("JENKINS_BUILD_NUMBER"));
				//				    System.out.println(message);
				//				}

				if(!strBrowser.toLowerCase().contains("appium") && !strBrowser.toLowerCase().contains("ios") && !strBrowser.toLowerCase().contains("droid") && !strBrowser.toLowerCase().contains("-ua-")){
					objWD.manage().deleteAllCookies();
					if(!driver.equals("operadriver") && !driver.equals("iosdriver") && !driver.equals("useragent"))
					{
						objWD.manage().window().maximize();
					}
					if(!driver.equals("safaridriver")){
						objWD.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Updated by Ram on 3/28/2016 reduced the implicit wait from 80 sec to 2 sec
						//objWD.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//Increased the page load and script load Time out from 60 to 80:Madhu:06/02/2014
						objWD.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
					}
				}

				//Change view port size if browser is user agent
				if(driver.equals("useragent")){

					Dimension browserSize = objWD.manage().window().getSize();
					Dimension actualViewportSize = getViewportSize(objWD);

					objWD.manage().window().setSize(new Dimension(2 * browserSize.width - actualViewportSize
							.getWidth(), 2 * browserSize.height - actualViewportSize.getHeight()));
				}

				//objWD.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				//Get the browser name, version and OS name
				Capabilities cap = ((RemoteWebDriver) objWD).getCapabilities();
				String browserName = cap.getBrowserName().toLowerCase();
				System.out.println("Browser Name: "+browserName);
				Driver.globalValues.put("BrowserName", browserName);
				//			String os = cap.getPlatform().toString();
				//			System.out.println(os);
				//			Driver.globalValues.put("OsName", os);
				String v = cap.getVersion().toString();
				Driver.Gprops.setBrowserVersion(v);
				System.out.println("Browser Version: "+v);
				//				Driver.globalValues.put("BrowserVersion", v);

				Driver.mapper.put(Thread.currentThread().getName(), objWD);
				String OSName = "";
				if(Driver.Init.GetAUTBrowser().contains("rwd")){
					OSName=Driver.CUtil.GetSystemInfo(Driver.Gprops.GetNodeIPAddress(), "CORP/QAAutomationSVC", "adtadmin28!", "OS Name");
					Driver.globalValues.put("OSName", OSName);
					// below code was added by shwetha kulkarni for environmnet variables addition
					String value1=Driver.Init.GetFromEnvironment("DT_AGENTNAME");
					String value2=Driver.Init.GetFromEnvironment("DT_AGENTACTIVE");
					if(value1!=null && value2!=null)
					{
						setEnvVariable(value1,value2);
					}
				}
				else{
					OSName = System.getProperty("os.name");
				}
				System.out.println("OS Name: "+OSName);
				//Message,functionname,status=> Done,Pass,Fail,Warning,N/A Mar 27, 2014 7:58:22 PM
				Driver.CUtil.WriteResults("OS Name: "+ OSName);
				Driver.Gprops.setOSNames(OSName);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();// updated by murthi to show the exact error message on 3/18/2015
			Driver.CUtil.WriteResults("Error occured in the function GetWebDriver and error message is "+ex.getMessage()+", Stopping execution", "GetWebDriver", "fail");

		}
		finally
		{

		}
	}

	/**
	 * This function will get the ip address of the remote node where the test is getting executed
	 * @author krishna kishore g - krishnakishore.g@move.com
	 * @param remoteDriver1 The web driver object using which the remote session is started
	 * @return this function will return the IPAddress of the node where the test is getting executed
	 * @Updated by Ram on 7/30/2014- Changed the method from Private static to Public string
	 */
	public String getIPOfNode(WebDriver remoteDriver1) { 
		String hostFound = null; 
		if(remoteDriver1!=null){
			RemoteWebDriver remoteDriver = (RemoteWebDriver) remoteDriver1;
			try { 
				HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor(); 
				String hostName = ce.getAddressOfRemoteServer().getHost(); 
				int port = ce.getAddressOfRemoteServer().getPort(); 
				HttpHost host = new HttpHost(hostName, port); 
				DefaultHttpClient client = new DefaultHttpClient(); 
				System.out.println("Session Id: "+ remoteDriver.getSessionId());
				URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + remoteDriver.getSessionId()); 
				BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest( "POST", sessionURL.toExternalForm()); 
				HttpResponse response = client.execute(host, r); 
				InputStream contents = response.getEntity().getContent(); 
				StringWriter writer = new StringWriter(); 
				IOUtils.copy(contents, writer, "UTF8"); 
				JSONObject object = new JSONObject(writer.toString());  
				URL myURL = new URL(object.getString("proxyId")); 
				if ((myURL.getHost() != null) && (myURL.getPort() != -1)) { 
					hostFound = myURL.getHost();
					Driver.Gprops.SetNodeIPAddress(hostFound);
					System.out.println("THE TEST IS GETTING EXECUTED IN THE NODE WITH IPADDRESS = " + hostFound);
					//				Driver.CUtil.WriteResults("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~","*~*~*~*~*~*~*~*");
					//				Driver.CUtil.WriteResults("THE TEST IS GETTING EXECUTED IN THE NODE WITH IPADDRESS = " + hostFound, "getIPOfNode");
					//				Driver.CUtil.WriteResults("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~","*~*~*~*~*~*~*~*");
				} 
			} 
			catch (Exception e) { 
				System.err.println(e); 
			} 
		}
		return hostFound; 
	} 

	/**
	 * This function will take the webdriver and pathvalue and returns a WebElement
	 * @param oBrowser
	 * @param Values
	 * @return
	 */
	public WebElement GetWebElement(WebDriver oBrowser,String Values)
	{
		boolean masterFlag = false;
		WebElement WebCtrl = null;
		try
		{
			//int arrayLength = Values.length;
			//for(int LoopA = 0; LoopA < arrayLength; LoopA++)
			{
				String[] OR1 = Values.split(",",3);
				String LogicalName = OR1[1];
				String LogicalValue = OR1[2];
				boolean LNVFlag = false;
				switch(LogicalName.toLowerCase())
				{
				case "id":
					WebCtrl = oBrowser.findElement(By.id(LogicalValue));
					LNVFlag = true;
					break;
				case "xpath":
					WebCtrl = oBrowser.findElement(By.xpath(LogicalValue));
					LNVFlag = true;
					break;
				case "classname":
					WebCtrl = oBrowser.findElement(By.className(LogicalValue));
					LNVFlag = true;
					break;
				case "cssselector":
					WebCtrl = oBrowser.findElement(By.cssSelector(LogicalValue));
					LNVFlag = true;
					break;
				case "linktext":
					WebCtrl = oBrowser.findElement(By.linkText(LogicalValue));
					LNVFlag = true;
					break;
				case "name":
					WebCtrl = oBrowser.findElement(By.name(LogicalValue));
					LNVFlag = true;
					break;
				case "partiallinktext":
					WebCtrl = oBrowser.findElement(By.partialLinkText(LogicalValue));
					LNVFlag = true;
					break;
				case "tagname":
					WebCtrl = oBrowser.findElement(By.tagName(LogicalValue));
					LNVFlag = true;
					break;
				}
				if(!LNVFlag)
				{
					WebCtrl = null;
				}
				if (WebCtrl.isDisplayed())
				{
					// check if the created object is available in page else try with other properties provided
					masterFlag = true;
					//break;
				}
			}
		}
		catch(Exception ex)
		{
			WebCtrl = null;
		}
		return WebCtrl;
	}

	/**
	 * This function will take the webdriver and pathvalue and returns a WebElement, when the webelement is null, 
	 * then it will use the recovery screen name and buils xpath on the run and trys to get the weblement
	 * @param ScreenName
	 * @param ObjectName
	 * @return This function will return either a webelement or null based on the availabiliy of webelement on the page
	 */
	public WebElement GetWebElement(String ScreenName,String ObjectName)
	{
		//WebDriver oBrowser = Driver.Gprops.GetWebDriver();
		WebDriver oBrowser =(WebDriver) Driver.mapper.get(Thread.currentThread().getName());
		boolean masterFlag = false;
		WebElement WebCtrl = null;
		String RecoveryScreen = "";
		try
		{
			{
				String[] OR1 = Driver.CUtil.ReadObjectFile(ScreenName, ObjectName).split(",",3);
				String LogicalName = OR1[1];
				String LogicalValue = OR1[2];
				Driver.CUtil.WriteResults("Creating WebElement with locator = " + LogicalName + " and its value = " + LogicalValue, "GetWebElement","");
				boolean LNVFlag = false;
				switch(LogicalName.toLowerCase())
				{
				case "id":
					WebCtrl = oBrowser.findElement(By.id(LogicalValue));
					LNVFlag = true;
					break;
				case "xpath":
					WebCtrl = oBrowser.findElement(By.xpath(LogicalValue));
					LNVFlag = true;
					break;
				case "classname":
					WebCtrl = oBrowser.findElement(By.className(LogicalValue));
					LNVFlag = true;
					break;
				case "cssselector":
					WebCtrl = oBrowser.findElement(By.cssSelector(LogicalValue));
					LNVFlag = true;
					break;
				case "linktext":
					WebCtrl = oBrowser.findElement(By.linkText(LogicalValue));
					LNVFlag = true;
					break;
				case "name":
					WebCtrl = oBrowser.findElement(By.name(LogicalValue));
					LNVFlag = true;
					break;
				case "partiallinktext":
					WebCtrl = oBrowser.findElement(By.partialLinkText(LogicalValue));
					LNVFlag = true;
					break;
				case "tagname":
					WebCtrl = oBrowser.findElement(By.tagName(LogicalValue));
					LNVFlag = true;
					break;
				}
				if(!LNVFlag)
				{
					String[] Temp1 = ScreenName.split("\\",2);
					RecoveryScreen = Temp1[0] + "/Recovery/" + Temp1[1]; 
					WebCtrl = null;
					OR1 = Driver.CUtil.ReadObjectFile(RecoveryScreen, ObjectName).split(",",3);
					LogicalName = OR1[1];
					LogicalValue = OR1[2];
					DOMParser parser = new DOMParser();
					parser.parse(new InputSource(oBrowser.getCurrentUrl()));
					org.w3c.dom.Document doc = parser.getDocument();
					DOMReader reader = new DOMReader();
					Document document = reader.read(doc);
					@SuppressWarnings("unchecked")	        
					List<Node> nodes = document.selectNodes(LogicalValue);
					Node n = nodes.get(0);
					//			        System.out.println(n.getUniquePath());
					WebCtrl = oBrowser.findElement(By.xpath(n.getUniquePath()));
				}
				//				if (!WebCtrl.isplayed())
				//				{
				//					System.out.println("Unable to construct the WebElement with the recovery screenname and objectname");
				//					System.out.println("Screen Name = " + LogicalName);
				//					System.out.println("Object Name = "+ LogicalValue);
				//					WebCtrl = null;
				//				}
			}
		}
		catch(Exception ex)
		{
			WebCtrl = null;
			System.out.println(ObjectName+" is not Present");
			//			System.out.println(ex.getMessage());
			//			ex.printStackTrace();
		}
		return WebCtrl;
	}


	/**
	 * This utility function gets the WebElement dynamically based on the pattern and replacement 
	 * @param screenName, provide relative path of the object property file
	 * @param objectName, provide object name exist in the object property file
	 * @param pattern, provide pattern as "$$index$$"
	 * @param replacement, provide replacement as a substitute
	 * @return This function returns the webElement
	 */
	public WebElement GetWebElement(String screenName,String objectName, String pattern, String replacement)
	{
		String getPattern = null;
		String patternRep = null;
		WebElement webElement = null;
		try {
			getPattern = Driver.CUtil.ReadObjectFile(screenName, objectName);
			patternRep = Driver.CUtil.PattenReplacer(getPattern, replacement, pattern);
			Driver.WebCtrl.waitTillElementVisibility(screenName, patternRep, 10);
			webElement = Driver.WebCtrl.GetWebElement(Driver.Gprops.GetWebDriver(), patternRep);
		}
		catch (Exception Ex) {
			Driver.CUtil.WriteResults("Exception " + Ex, "GetWebElement", "Fail");
			Assert.assertTrue(false);
		}
		return webElement;
	}

	/**
	 * This function will take the WebDriver and the strimg to construct the webelement
	 * @param oBrowser
	 * @param Values
	 * @return this function will return a WebElement object
	 * @deprecated
	 */
	public Locatable GetLocatableWebElement(WebDriver oBrowser,String Values)
	{
		boolean masterFlag = false;
		WebElement WebCtrl = null;
		try
		{
			//int arrayLength = Values.length;
			//for(int LoopA = 0; LoopA < arrayLength; LoopA++)
			{
				String[] OR1 = Values.split(",",3);
				String LogicalName = OR1[1];
				String LogicalValue = OR1[2];
				boolean LNVFlag = false;
				switch(LogicalName.toLowerCase())
				{
				case "id":
					WebCtrl = oBrowser.findElement(By.id(LogicalValue));
					LNVFlag = true;
					break;
				case "xpath":
					WebCtrl = oBrowser.findElement(By.xpath(LogicalValue));
					LNVFlag = true;
					break;
				case "classname":
					WebCtrl = oBrowser.findElement(By.className(LogicalValue));
					LNVFlag = true;
					break;
				case "cssselector":
					WebCtrl = oBrowser.findElement(By.cssSelector(LogicalValue));
					LNVFlag = true;
					break;
				case "linktext":
					WebCtrl = oBrowser.findElement(By.linkText(LogicalValue));
					LNVFlag = true;
					break;
				case "name":
					WebCtrl = oBrowser.findElement(By.name(LogicalValue));
					LNVFlag = true;
					break;
				case "partiallinktext":
					WebCtrl = oBrowser.findElement(By.partialLinkText(LogicalValue));
					LNVFlag = true;
					break;
				case "tagname":
					WebCtrl = oBrowser.findElement(By.tagName(LogicalValue));
					LNVFlag = true;
					break;
				}
				if(!LNVFlag)
				{
					WebCtrl = null;
				}
				if (WebCtrl.isDisplayed())
				{
					// check if the created object is available in page else try with other properties provided
					masterFlag = true;
					//break;
				}
			}
		}
		catch(Exception ex)
		{
			WebCtrl = null;
		}
		return (Locatable) WebCtrl;
	}

	/*
	 * This function will get all the list of objects 
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 */
	public List<WebElement> GetWebElements(WebDriver oBrowser,String Values)
	{
		List<WebElement> WebCtrl = null;
		try
		{
			{
				String[] OR1 = Values.split(",",3);
				String LogicalName = OR1[1];
				String LogicalValue = OR1[2];
				boolean LNVFlag = false;
				switch(LogicalName.toLowerCase())
				{
				case "id":
					WebCtrl = oBrowser.findElements(By.id(LogicalValue));
					LNVFlag = true;
					break;
				case "xpath":
					WebCtrl = oBrowser.findElements(By.xpath(LogicalValue));
					LNVFlag = true;
					break;
				case "classname":
					WebCtrl = oBrowser.findElements(By.className(LogicalValue));
					LNVFlag = true;
					break;
				case "cssselector":
					WebCtrl = oBrowser.findElements(By.cssSelector(LogicalValue));
					LNVFlag = true;
					break;
				case "linktext":
					WebCtrl = oBrowser.findElements(By.linkText(LogicalValue));
					LNVFlag = true;
					break;
				case "name":
					WebCtrl = oBrowser.findElements(By.name(LogicalValue));
					LNVFlag = true;
					break;
				case "partiallinktext":
					WebCtrl = oBrowser.findElements(By.partialLinkText(LogicalValue));
					LNVFlag = true;
					break;
				case "tagname":
					WebCtrl = oBrowser.findElements(By.tagName(LogicalValue));
					LNVFlag = true;
					break;
				}
				if(!LNVFlag)
				{
					WebCtrl = null;
				}                          
			}
		}
		catch(Exception ex)
		{
			WebCtrl = null;
		}
		return WebCtrl;
	}

	/*
	 * This function will get all the list of objects 
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 */
	public int[] GetWebElementLocation(WebElement WebE){
		int[] xyPoint = new int[2];
		try{
			Point p = WebE.getLocation();
			xyPoint[0] = p.x;
			xyPoint[1] = p.y;
		}
		catch(Exception ex){
			xyPoint[0] = -1;
			xyPoint[1] = -1;
		}
		return xyPoint;
	}

	/*
	 * This function will get all the list of objects 
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 */
	public int[] GetWebElementSize(WebElement WebE){
		int[] xyPoint = new int[2];
		try{
			Dimension d = WebE.getSize();
			xyPoint[0] = d.height;
			xyPoint[1] = d.width;
		}
		catch(Exception ex){
			xyPoint[0] = -1;
			xyPoint[1] = -1;
		}
		return xyPoint;
	}

	/*
	 * This function will get all the list of objects 
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 */
	public void BrowserGoBack(WebDriver WD){
		try{
			WD.navigate().back();
			Thread.sleep(20000);
		}
		catch(Exception ex){

		}

	}

	/*
	 * This function will get all the list of objects 
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 */
	public void BrowserGoForward(WebDriver WD){
		try{
			WD.navigate().forward();
		}
		catch(Exception ex){

		}

	}

	/*
	 * This function will check for the existance of the object, max it will wait for 60 sec and exit  
	 * @author	krishna kishore g	<krishnakg@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available in the page till the max timeout 
	 * 
	 */
	public boolean WaitForWebElement(WebElement e){
		int i=0;
		boolean oFound = false;
		try{
			while (i <= 5){
				Dimension dim = e.getSize();
				if (dim.height != 0){
					oFound = true;
					WaitForPageToLoad WPT = new WaitForPageToLoad();
					WPT.setTimeToWait(100);
					break;
				}
				i += 1;
			}
		}
		catch(Exception ex){
			oFound = false;
		}
		return oFound;
	}

	/*
	 * This function will check for the existence of the object and click the particular element 
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean clickOnElement(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		WebElement clickobject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (clickobject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10))//Sonu-Added Explicit wait before clicking on any Element 
		{
			if(clickobject.isDisplayed()){
				/*Section to Scroll when it comes to the Submit button on the Advanced search window.
				/*if(ObjectName.contains("AdvSearchSubmit") )
				{
				Actions act = new Actions(otDriver);
				act.sendKeys(Keys.PAGE_DOWN).perform();
				}*/				

				clickobject.click();							
				try{
					Thread.sleep(3000);
				}catch(Throwable th){

				}
				flag=true;
				Driver.CWeb.verifyConsoleErrors();
			}
			else
			{
				Driver.CUtil.WriteResults("Unable to click on "+ObjectName+" as it is not displayed");
			}
		}

		return flag;
	}

	/*
	 * This function will check for the existence of the object and click the particular element using JavaScript
	 * @author	Sonu
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean clickOnElementByJs(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		WebElement clickobject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (clickobject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 5))
		{
			if(clickobject.isDisplayed()){
				JavascriptExecutor executor = (JavascriptExecutor)Driver.Gprops.GetWebDriver();
				executor.executeScript("arguments[0].click();", clickobject);
				try{
					Thread.sleep(3000);
				}catch(Throwable th){

				}
				flag=true;
				Driver.CWeb.verifyConsoleErrors();
			}
			else
			{
				Driver.CUtil.WriteResults("Unable to click on "+ObjectName+" using Javascript as it is not displayed");
			}
		}

		return flag;
	}


	/*
	 * This function will check for the existence of the object and clear the input box if any string is present by default
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean clear(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		WebElement clearObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (clearObject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
		{
			clearObject.clear();
			flag=true;
			Driver.Gprops.SetFlagObjectClear(true);
		}
		else
		{
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}
	/*
	 * This function will check for the existence of the object and clear the input box if any string is present by default
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean sendKeys(String ScreenName, String ObjectName, String Value)
	{
		boolean flag=false;

		WebElement inputObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (inputObject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
		{
			if(inputObject.isDisplayed()){
				inputObject.clear();
				inputObject.sendKeys(Value);
				flag=true;
			}
			else{
				flag=false;
			}
		}
		else
		{
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}
	/*
	 * This function will check for the existence of the object and selects the value from dropdown box with dropdown text value
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean selectByVisibleText(String ScreenName, String ObjectName, String Value)
	{
		boolean flag=false;

		WebElement selectObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectObject!=null) 
		{
			Select select=new Select(selectObject);
			select.selectByVisibleText(Value);
			flag=true;
		}
		else
		{
			Driver.CUtil.WriteResults("Value Not Selected from dropdown", "selectByVisibleText", "Fail");
			Assert.assertTrue(false);
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and selects the value from dropdown box with index value
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean selectByIndex(String ScreenName, String ObjectName,int index)
	{
		boolean flag=false;

		WebElement selectObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectObject!=null) 
		{
			Select select=new Select(selectObject);
			select.selectByIndex(index);
			flag=true;
		}
		else
		{
			Driver.CUtil.WriteResults("Value Not Selected from dropdown", "selectByVisibleText", "Fail");
			Assert.assertTrue(false);
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}
	/*
	 * This function will check for the existence of the object and selects the value from dropdown box with text value
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean selectByValue(String ScreenName, String ObjectName,String Value)
	{
		boolean flag=false;

		WebElement selectObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectObject!=null) 
		{
			Select select=new Select(selectObject);
			select.selectByValue(Value);
			flag=true;
		}
		else
		{
			Driver.CUtil.WriteResults("Value Not Selected from dropdown", "selectByVisibleText", "Fail");
			Assert.assertTrue(false);
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and selects the visible text value from dropdown box which matches with the input value given
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean selectoptions(String ScreenName, String ObjectName,String Value)
	{
		boolean flag=false;
		String actual="";

		WebElement selectObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectObject!=null) 
		{
			Select select=new Select(selectObject);
			List<WebElement> li=select.getOptions();
			for(int i=0;i<li.size();i++){
				actual=li.get(i).getText();
				if(Value.equalsIgnoreCase(actual)){
					select.selectByVisibleText(actual);
					flag=true;
					break;
				}

			}
			if(flag==false){
				System.out.println(Value+" was not present as option in dropdown");
			}

		}

		return flag;
	}
	/*
	 * This function will check for the existence of the object and perform the drag and drop operation for particular element
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean dragAndDrop(String ScreenNamesrc, String ObjectNamesrc,String ScreenNamedest, String ObjectNamedest)
	{
		boolean flag=false;

		Actions act=new Actions(Driver.Gprops.GetWebDriver());
		WebElement srcObject = Driver.WebCtrl.GetWebElement(Driver.Gprops.GetWebDriver(), Driver.CUtil.ReadObjectFile(ScreenNamesrc,ObjectNamesrc));
		WebElement destObject = Driver.WebCtrl.GetWebElement(Driver.Gprops.GetWebDriver(), Driver.CUtil.ReadObjectFile(ScreenNamedest,ObjectNamedest));
		if (srcObject!=null&&destObject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenNamesrc, ObjectNamesrc, 10) && Driver.WebCtrl.waitTillElementVisibility(ScreenNamedest, ObjectNamedest, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			Action action1 = (Action)act.dragAndDrop(srcObject,destObject).build();
			action1.perform();
			flag=true;
		}
		else
		{
			//System.out.println("Webelements returned null value and webelements was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and if element is present, will get the text of element
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a string value.
	 * 
	 */
	public String getText (String ScreenName, String ObjectName)
	{
		String value="";
		WebElement textElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (textElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
		{
			value=textElement.getText();
		}
		else
		{
			//System.out.println("Webelements returned null value and webelements was not present");
			//		value="Text was not found";
		}
		return value;
	}

	/*
	 * This function will check for the existence of the object and check whether the element isdisplayed
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean  isDisplayed(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		Driver.Gprops.SetIsDisplayCustomizedFlag(false);
		WebElement DisplayedElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);

		if (DisplayedElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 5)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			if(DisplayedElement.isDisplayed())
			{
				//System.out.println("Element is displayed");
				flag=true;
				Driver.Gprops.SetIsDisplayCustomizedFlag(true);
			}
			else
			{
				//System.out.println("Element is not dispalyed");
			}
		}
		else
		{
			//System.out.println("Webelement returned null value and webelement was not present");
		}

		return flag;
	}

	/*
	 * This function will check for the existence of the object and check whether the element is enabled else it will wait for 9 seconds to check whether element is enabled
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean  isEnabled(String ScreenName, String ObjectName) throws InterruptedException
	{
		boolean flag=false;
		WebElement selectableElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectableElement!=null)
		{
			for(int i=1; i<=3;i++)
			{
				Thread.sleep(1000); 
				if(selectableElement.isEnabled())
				{
					System.out.println("Element is Enabled");
					flag=true;
					break;
				}
			}

		}
		else
		{
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and checks whether the element has been selected
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean  isSelected(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		WebElement selectableElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (selectableElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			if(selectableElement.isSelected())
			{
				System.out.println("Element was selected");
				flag=true;
			}
			else
			{
				System.out.println("Element was not selected");
			}
		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and perform rightclick operation on element
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean rightClickOnElement(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		Actions act=new Actions(Driver.Gprops.GetWebDriver());
		WebElement rightclickOnElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (rightclickOnElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			Action action1 = (Action)act.contextClick(rightclickOnElement);
			action1.perform();
			flag=true;
		}
		else
		{
			//System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and double click on the element present
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the object is available else false is the object is not available 
	 * 
	 */
	public boolean doubleClickOnElement(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		Actions act=new Actions(Driver.Gprops.GetWebDriver());
		WebElement doubleClickOnElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (doubleClickOnElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			//Action action1 = (Action)act.contextClick(doubleClickOnElement);
			act.moveToElement(doubleClickOnElement,420, 862).moveToElement(doubleClickOnElement).doubleClick(doubleClickOnElement).build().perform();//modified by roop context click to double click.
			//action1.perform();
			flag=true;
		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and instead of using click() and navigating to next page we can use this function, if there is any "href" attribute present for element.
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean navigateToLink(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		WebElement navigateToLinkElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (navigateToLinkElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			String Value=navigateToLinkElement.getAttribute("href");
			Driver.Gprops.GetWebDriver().get(Value);
			flag=true;
		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and verifies whether the text provided and received from element are equal
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean verifyTextPresent(String ScreenName, String ObjectName,String value)
	{
		boolean flag=false;

		WebElement TextElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (TextElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			String Value=TextElement.getText();
			if(Value.equalsIgnoreCase(value))
			{
				//	System.out.println("Text was present");
				flag=true;
			}
			else{
				//	System.out.println("Text was not present");
			}
		}
		else
		{
			System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and click on accept button if any javascript alert is present.
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean javascriptAlertAccept(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		WebElement alertElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (alertElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			try
			{
				Alert alert=Driver.Gprops.GetWebDriver().switchTo().alert();
				alert.accept();
			}
			catch (NoAlertPresentException e)
			{
				//  System.out.println("Alert was not present");
			}
		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and click on cancel button if any javascript alert is present.
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */

	public boolean javascriptAlertDismiss(String ScreenName, String ObjectName)
	{
		boolean flag=false;

		WebElement alertElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (alertElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			try{
				Alert alert=Driver.Gprops.GetWebDriver().switchTo().alert();
				alert.dismiss();
			}
			catch (NoAlertPresentException e) 
			{
				System.out.println("Alert was not present");
			}
		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return flag;
	}
	/*Overloading alert function
	 * @author Vishwa
	 * */
	public void javascriptAlertAccept(){
		Alert alert=null;
		try{
			alert = Driver.Gprops.GetWebDriver().switchTo().alert();
			alert.accept();
		}catch(Throwable th)
		{

		}

	}

	/*
	 * This function will check for the existence of the object and gets the alert message if any javascript alert or popup is present.
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public String javascriptAlertMsg(String ScreenName, String ObjectName)
	{
		String AlertMsg="";

		WebElement alertElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (alertElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{ 
			try{
				Alert alert=Driver.Gprops.GetWebDriver().switchTo().alert();
				AlertMsg=alert.getText();
			}
			catch (NoAlertPresentException e) {
				System.out.println("Alert was not present");
			}

		}
		else
		{
			//	System.out.println("Webelement returned null value and webelement was not present");
		}
		return AlertMsg;
	}

	/*
	 * This function will check for the existence of the object and switch to frame element[iframe/frame tag should be present]
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean switchToFrame(String ObjectName)
	{
		boolean flag=false;

		//WebElement switchToFrameElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		//			if (switchToFrameElement!=null) 
		//			{ 
		Driver.Gprops.GetWebDriver().switchTo().frame(ObjectName);
		flag=true;
		//			}
		//			else
		//			{
		//			//	System.out.println("Webelement returned null value and webelement was not present");
		//			}
		return flag;
	}

	/*
	 * This function will check for the existence of the object and move the control to particular element like mouse hover operation
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean moveToElement(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		JavascriptExecutor jse=(JavascriptExecutor)Driver.Gprops.GetWebDriver();
		WebElement toElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);

		if(toElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
		{
			Actions act=new Actions(Driver.Gprops.GetWebDriver());
			act.moveToElement(toElement).build().perform();      
			act.moveToElement(toElement).perform();
			flag=true;
		}else
		{
			Driver.CUtil.WriteResults(ObjectName+" element is not displayed", "moveToElement", "Warning");
		}
		return flag;
	}
	/*
	 * This function will check for the existence of the object and move the control to particular element like mouse hover operation and click on operations
	 * @author	Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean moveToElementandclickOnElement(String ScreenName, String ObjectName)
	{
		boolean flag=false;
		String FUnction_Name="moveToElementandclickOnElement";
		Actions act=new Actions(Driver.Gprops.GetWebDriver());
		WebElement toElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
		if (toElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
		{
			act.moveToElement(toElement).click().build().perform();      
			flag=true;
			Driver.CWeb.verifyConsoleErrors();
		}
		else
		{
			//Added by Johnsi on 05/21/2014
			Driver.CUtil.WriteResults("Could not identify the Object '" + ObjectName + "'", FUnction_Name, "Fail");
			//	System.out.println("Webelements returned null value and webelements was not present");
		}
		return flag;
	}

	public List<WebElement> GetWebElements(String ScreenName,String ObjectName) // changed parameters by shwetha
	{
		//WebDriver oBrowser = Driver.Gprops.GetWebDriver(); // added by shwetha
		WebDriver oBrowser =(WebDriver) Driver.mapper.get(Thread.currentThread().getName());
		List<WebElement> WebCtrl = null;
		try
		{
			{
				String[] OR1 = Driver.CUtil.ReadObjectFile(ScreenName, ObjectName).split(",",3); // added by shwetha
				String LogicalName = OR1[1];
				String LogicalValue = OR1[2];
				boolean LNVFlag = false;
				switch(LogicalName.toLowerCase())
				{
				case "id":
					WebCtrl = oBrowser.findElements(By.id(LogicalValue));
					LNVFlag = true;
					break;
				case "xpath":
					WebCtrl = oBrowser.findElements(By.xpath(LogicalValue));
					LNVFlag = true;
					break;
				case "classname":
					WebCtrl = oBrowser.findElements(By.className(LogicalValue));
					LNVFlag = true;
					break;
				case "cssselector":
					WebCtrl = oBrowser.findElements(By.cssSelector(LogicalValue));
					LNVFlag = true;
					break;
				case "linktext":
					WebCtrl = oBrowser.findElements(By.linkText(LogicalValue));
					LNVFlag = true;
					break;
				case "name":
					WebCtrl = oBrowser.findElements(By.name(LogicalValue));
					LNVFlag = true;
					break;
				case "partiallinktext":
					WebCtrl = oBrowser.findElements(By.partialLinkText(LogicalValue));
					LNVFlag = true;
					break;
				case "tagname":
					WebCtrl = oBrowser.findElements(By.tagName(LogicalValue));
					LNVFlag = true;
					break;
				}
				if(!LNVFlag)
				{
					WebCtrl = null;
				}				
			}
		}
		catch(Exception ex)
		{
			WebCtrl = null;
		}
		return WebCtrl;
	}

	/*
	 * This function will retrive the windowhandles or the no of windows opened.
	 * @author    Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version   1.0
	 * @return    this function will return set of strings
	 * 
	 */
	public Set<String> getwindowhandles()
	{
		Set<String> windows = Driver.Gprops.GetWebDriver().getWindowHandles();
		return windows;
	}

	/*
	 * this function will transfer the control to the child window
	 * @author    Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version   1.0
	 * @param Set<String> windows as set of string values  
	 * 
	 */
	public void switchtoChildwindow(Set<String> windows)
	{
		if(windows.size()>1)
		{
			String parent = null;
			String child = null;
			Iterator<String> it = windows.iterator();
			while(it.hasNext())
			{
				parent = it.next();
				Driver.Gprops.SetparentWindowhandle(parent);
				child = it.next();
				Driver.Gprops.SetchildWindowhandle(child);
			}
			Driver.Gprops.GetWebDriver().switchTo().window(child);
		}
	}

	/*
	 * this function will transfer the control to the child window
	 * @author    Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version   1.0
	 * @param Set<String> windows as set of string values  
	 * 
	 */
	public WebDriver switchtoChildwindow(Set<String> windows, String pageName)
	{
		String FUnction_Name = "switchtoChildwindow";
		WebDriver driver = null;
		if(windows.size()>1)
		{
			String parent = null;
			String child = null;
			Iterator<String> it = windows.iterator();
			while(it.hasNext())
			{
				parent = it.next();
				child = it.next();
			}
			driver=Driver.Gprops.GetWebDriver().switchTo().window(child);
			Driver.CUtil.WriteResults("Driver is switched to child window from "
					+ pageName);
		}
		return driver;
	}

	/*
	 * this function will transfer the control to the parent window
	 * @author    Shwetha Kulkarni<shwethabk@collabera.com>
	 * @version   1.0    
	 * 
	 */

	public void switchtoParentwindow()
	{
		Driver.Gprops.GetWebDriver().switchTo().defaultContent();
	}

	public void switchtoParentwindowHandle(Set<String> windows)
	{
		if(windows.size()>1)
		{
			String parent = null;
			String child = null;
			Iterator<String> it = windows.iterator();
			while(it.hasNext())
			{
				parent = it.next();
				child = it.next();
			}
			Driver.Gprops.GetWebDriver().switchTo().window(parent);
		}
	}

	/*
	 * This function will return the parent window driver after switching to it, which can use to SetWebDriver
	 * @author	Abhishek B R<Abhishek.br@Move.com>
	 * @version	1.0
	 * @return	this function will return the parent WebDriver
	 * 
	 */
	public WebDriver switchtoParentwindowHandle(Set<String> windows, String pageName)
	{
		String FUnction_Name = "switchtoParentwindowHandle";
		WebDriver driver=null;
		if(windows.size()>1)
		{
			String parent = null;
			String child = null;
			Iterator<String> it = windows.iterator();
			while(it.hasNext())
			{
				parent = it.next();
				child = it.next();
			}	
			driver = Driver.Gprops.GetWebDriver().switchTo().window(parent);
			Driver.CUtil.WriteResults("Driver is switched to Parent window from "
					+ pageName);
		}
		return driver;
	}

	/*
	 * This function will check for the existence of the object and check whether the element isdisplayed
	 * @author	MadhuSudhana K.S<Madhusudhana.Shivalingappa@Move.com>
	 * @version	1.0
	 * @return	this function will return a boolean value, True if the obect is available else false is the object is not available 
	 * 
	 */
	public boolean  isDisplayedCustomised(String ScreenName, String ObjectName,String GUIObjectName,String PageName,String FunctionName )
	{

		boolean flag=false;
		try{

			WebElement DisplayedElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
			if (DisplayedElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
			{
				if(DisplayedElement.isDisplayed())
				{

					Driver.CUtil.WriteResults(GUIObjectName+" is available "+PageName,FunctionName, "pass");

					flag=true;
					Driver.Gprops.SetIsDisplayCustomizedFlag(true);

				}
				else
				{
					Driver.CUtil.WriteResults(GUIObjectName+" is not available "+PageName,FunctionName, "fail");


				}
			}	
			else
			{
				Driver.CUtil.WriteResults(GUIObjectName+" is not available "+PageName,FunctionName, "fail");


			}


			//			else
			//			{
			//				Driver.CUtil.WriteResults(GUIObjectName+" is not available Returned Null "+PageName,FunctionName, "fail");
			//				//Driver.Gprops.IncFailCounter();
			//			}

		}

		catch(Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,FunctionName, "fail");

		}

		if(!flag)
		{
			Driver.Gprops.SetIsDisplayCustomizedFlag(false);	
		}

		return flag;


	}


	/**
	 *This function will set the environment variable in the nodes where the script is running 
	 * @author shwetha kulkarni shwetha.kulkarni@move.com
	 * @param this function will take environment variables DT_AGENTNAME and DT_AGENTACTIVE values as input respectively
	 *both values are string
	 */
	public void setEnvVariable(String value1,String value2)
	{
		try{
			String psPath=Driver.Init.GetBasePath()+"PSTools"+"/PsExec.exe";
			String  outputone= null;
			String  outputtwo= null;
			//CommonUtil cutil=new CommonUtil();
			String hostName=getIPOfNode(Driver.Gprops.GetWebDriver());
			String[] cmd = {psPath,"\\\\"+hostName,"-u","corp\\sfdcqaautomation","-p","adtadmin28!","setx","DT_AGENTNAME",value1};
			Runtime.getRuntime().exec(cmd);
			String[] cmd2 = {psPath,"\\\\"+hostName,"-u","corp\\sfdcqaautomation","-p","adtadmin28!","setx","DT_AGENTACTIVE",value2};
			Runtime.getRuntime().exec(cmd2);
			String[] variable1value = {psPath,"\\\\"+hostName,"-u","corp\\sfdcqaautomation","-p","adtadmin28!","cmd.exe","/c","echo","%DT_AGENTNAME%"};
			Process p1 = Runtime.getRuntime().exec(variable1value);
			String[] variable2value = {psPath,"\\\\"+hostName,"-u","corp\\sfdcqaautomation","-p","adtadmin28!","cmd.exe","/c","echo","%DT_AGENTACTIVE%"};
			Process p2 = Runtime.getRuntime().exec(variable2value);
			BufferedReader stdInputone = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader stdErrorone = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			BufferedReader stdInputtwo = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			BufferedReader stdErrortwo = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
			outputone = stdInputone.readLine();
			if(outputone!=null)
			{// read the output from the command
				if(outputone.contains(value1))
				{
					Driver.CUtil.WriteResults(value1+" value has been set for DT_AGENTNAME environment variable","setEnvVariable","pass");
				}
			}
			else
			{
				outputone=stdErrorone.readLine();
				Driver.CUtil.WriteResults(outputone+" error was generated ","setEnvVariable","warning");

			}

			outputtwo = stdInputtwo.readLine();
			if(outputtwo!=null)
			{// read the output from the command
				if(outputtwo.contains(value2))
				{
					Driver.CUtil.WriteResults(value2+" value has been set for DT_AGENTACTIVE environment variable","setEnvVariable","pass" );
				}
			}
			else
			{
				outputtwo=stdErrortwo.readLine();
				Driver.CUtil.WriteResults(outputtwo+" error was generated ","setEnvVariable","warning");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	//**************************************************************************************************************************************************
	//Function Name:InputSearch
	//Description:Inputs the city in to the search box of the location facet
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:City Name
	//***************************************************************************************************************************************************


	public void InputSearch(String City)

	{

		try{

			WebElement  FacetLocation =Driver.WebCtrl.GetWebElement("RDC/ForSaleSRP", "FacetLocationContainer");
			Thread.sleep(10000);
			WebElement  FacetLocationInput =Driver.WebCtrl.GetWebElement("RDC/ForSaleSRP", "FacetLocationLocWebEdit");
			FacetLocationInput.clear();
			FacetLocationInput.sendKeys(City);
			Driver.CUtil.WriteResults("The City "+City+" is Entered in to the location box of Location Facet Form");

		}


		catch(Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"InputSearch", "fail");
			Driver.Gprops.IncFailCounter();
		}
	}	



	//**************************************************************************************************************************************************
	//Function Name:GetFirstListPropertyInfo
	//Description:Gets First Property Information In the SRP list for the specified object.
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:Screen Name and an Object 
	//Date written:20/01/2014
	//***************************************************************************************************************************************************


	public void GetFirstListPropertyInfo(String ScreenName,String Object)
	{
		try{
			boolean IsFirstListPropertyInfoExist=Driver.WebCtrl.isDisplayed(ScreenName, Object);
			if(IsFirstListPropertyInfoExist && Driver.WebCtrl.waitTillElementVisibility(ScreenName, Object, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
			{

				WebElement  ObjFirstListPropertyInfo =Driver.WebCtrl.GetWebElement(ScreenName, Object);
				String FirstListPropertyInfo=ObjFirstListPropertyInfo.getText();
				FirstListPropertyInfo=FirstListPropertyInfo.replace(",","");
				Scanner IntegerFirstListPropertyInfo = new Scanner(FirstListPropertyInfo).useDelimiter("[^0-9]+");
				int IntFirstListPropertyInfo = IntegerFirstListPropertyInfo.nextInt();
				Driver.Gprops.SetFirstListPropertyInfo(IntFirstListPropertyInfo);

			}else
			{
				Driver.Gprops.SetFirstListPropertyInfo(0);
			}
		}
		catch(Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"GetFirstListPropertyInfo", "fail");
			Driver.Gprops.IncFailCounter();
		}
	} 

	//**************************************************************************************************************************************************
	//Function Name:GetIntgerPartFromString
	//Description:Gets The Integer Part From The String
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:String To be processed
	//Date written:20/01/2014
	//***************************************************************************************************************************************************


	public void GetIntgerPartFromString(String StringTobeProcessed)
	{

		try{
			StringTobeProcessed=StringTobeProcessed.replace(",","");
			Scanner IntgerStringTobeProcessed = new Scanner(StringTobeProcessed).useDelimiter("[^0-9]+");
			int IntStringTobeProcessed = IntgerStringTobeProcessed.nextInt();
			Driver.Gprops.SetIntegerPart(IntStringTobeProcessed);


		} 

		catch(Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"GetIntgerPartFromString", "fail");
			Driver.Gprops.IncFailCounter();
		}

	}


	//**************************************************************************************************************************************************
	//Function Name:GetDateFromGUIaboveListView
	//Description:Gets Date From the Title textabove the List view in SRP once the lists are sorted
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:None
	//Date written:26/03/2014
	//***************************************************************************************************************************************************

	public void GetDateFromGUIaboveListView()
	{

		int IntDateFromGUI=0;
		WebElement Title_ListingsDatedToday=Driver.WebCtrl.GetWebElement("RDC/CommonSRP", "Title_ListingsDatedToday");
		if(Title_ListingsDatedToday!=null)
		{


			String StrText=Title_ListingsDatedToday.getText();
			if(StrText.trim().toLowerCase().contains("today"))
			{
				Driver.CUtil.WriteResults("The listings are sorted by Today and the lists have the title  "+StrText +" Above the List View" , "ValidateSortingInSRP", "Passed");
			}

			else

			{
				Driver.CUtil.WriteResults("The listings are not sorted by Today and the lists have the title  "+StrText +" Above the List View" , "ValidateSortingInSRP", "failed");
				Driver.Gprops.Setassertflag(false);

			}
			String TempStrText=StrText.replace(")","/").replace("\n","").replace("(","/");
			String[] StrTextAfterSplit=TempStrText.split("/");

			String DateFromGUI=StrTextAfterSplit[2];
			IntDateFromGUI=Integer.parseInt(DateFromGUI);

		}
		Driver.Gprops.SetDateFromGUIAboveListView(IntDateFromGUI);    
	}


	//**************************************************************************************************************************************************
	//Function Name:clickOnElementCustomized
	//Description:Clicks on an object
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:Screen,Object and ObjectName
	//Example:Hpmopage,SearchButton,Search Button
	//Date written:15/04/2014
	//***************************************************************************************************************************************************


	public void clickOnElementCustomized(String Screen,String Object,String ObjectName)
	{

		try{


			WebElement clickobject = Driver.WebCtrl.GetWebElement(Screen,Object);
			if (clickobject!=null && Driver.WebCtrl.waitTillElementVisibility(Screen,Object, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
			{
				if(clickobject.isDisplayed())
				{
					clickobject.click();
					Driver.Gprops.SetIsObjectClickedFlag(true);
					Driver.CUtil.WriteResults("The Object "+ObjectName +" is clicked ","clickOnElementCustomized", "passed");
					Thread.sleep(3000);

				}
				else
				{
					Driver.CUtil.WriteResults("Cannot click on the  Object "+ObjectName +" as it is not displayed ","clickOnElementCustomized", "failed");
				}


			}
		}


		catch (Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"clickOnElementCustomized", "failed");	
		}


	}

	/*
	 * This function will check for the existence of the object and if element is present, will get the attribute value of element
	 * @author	Faseena KV<faseena.kv@move.com>
	 * @version	1.0
	 * @return	this function will return a string value.
	 * @Updated this method by Abhishek B R(abhishek.br@move.com)
	 */
	public String getAttribute (String ScreenName, String ObjectName, String Attribute)
	{
		String value="";
		try{

			WebElement webObject = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);
			if (webObject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
			{
				value=webObject.getAttribute(Attribute);
			}
			else
			{
				Driver.CUtil.WriteResults("Webelement returned null","getAttribute", "failed");
				//System.out.println("Webelements returned null value and webelements was not present");
				//		value="Attribute was not found";
			}

		}
		catch (Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"getAttribute", "failed");	
		}
		return value;
	}

	/**
	 * This method waits till given locator is visible within the given timeOut. It handles exception and retries during 'No open windows' / App blank screen appears.
	 * @author Arun<arun.kumar@move.com>
	 * @param webDriver Provide webDriver of type WebDriver
	 * @param xpathLocator Provide xpath locator of type String
	 * @param loadTimeOut Provide timeout of type int, example 300
	 * @throws WebDriverException
	 * @throws RuntimeException
	 * @throws InterruptedException
	 */
	public void waitTillNativeAppLoad(WebDriver webDriver, String xpathLocator, int loadTimeOut) throws InterruptedException
	{
		WebDriverWait driverWait = null;
		Exception exception=null;
		long startTimeOut,endTimeOut,totalTimeOut = 0;
		startTimeOut = System.currentTimeMillis();
		driverWait = new WebDriverWait(webDriver,loadTimeOut);
		do
		{
			try
			{
				driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
				System.out.println("****************************NavtiveApp page loaded successfully in "+totalTimeOut+" seconds..!!************************");
				break;
			}
			catch(WebDriverException e)
			{
				System.out.println("NativeApp page showing blank screen/still not visible, Please wait.....");
				exception = e;
			}
			Thread.sleep(5000);
			endTimeOut = System.currentTimeMillis();
			totalTimeOut = (endTimeOut-startTimeOut)/1000;
			if(totalTimeOut>=loadTimeOut)
			{
				throw new RuntimeException("Unable to locate element within "+loadTimeOut+" seconds");
			}
		} while(exception.getMessage().contains("No open windows"));
	}

	/**
	 * This method waits till the located web object is visible within the given timeOut.
	 * @author Arun<arun.kumar@move.com>
	 * @param ScreenName -Provide file path where locator exists
	 * @param ObjectName -Provide name of the object pointing to locator
	 * @param loadTimeOut -Provide timeout in seconds, example 300
	 */
	public boolean waitTillElementVisibility(String ScreenName, String objectName, int loadTimeOut)
	{
		WebDriver webDriver = null;
		WebDriverWait driverWait = null;
		By by = null;
		String[] OR1 = null;
		try{
			//webDriver = Driver.Gprops.GetWebDriver();
			webDriver = (WebDriver) Driver.mapper.get(Thread.currentThread().getName());
			if(webDriver!=null){
				driverWait = new WebDriverWait(webDriver,loadTimeOut);
			}
			else{
				Driver.CUtil.WriteResults("WebDriver returned null, the browser instance might've got killed!", "waitTillElementVisibility", "Fail");
				Assert.assertTrue(false);
			}

			if(objectName.contains(",")) {
				OR1 = objectName.split(",",3);
				objectName = OR1[0];
			}
			else {
				OR1 = Driver.CUtil.ReadObjectFile(ScreenName, objectName).split(",",3);
			}
			String LogicalName = OR1[1];
			String LogicalValue = OR1[2];
			switch(LogicalName.toLowerCase())
			{
			case "id":
				by = By.id(LogicalValue);
				break;
			case "xpath":
				by = By.xpath(LogicalValue);
				break;
			case "classname":
				by = By.className(LogicalValue);
				break;
			case "cssselector":
				by = By.cssSelector(LogicalValue);
				break;
			case "linktext":
				by = By.linkText(LogicalValue);
				break;
			case "name":
				by = By.name(LogicalValue);
				break;
			case "partiallinktext":
				by = By.partialLinkText(LogicalValue);
				break;
			case "tagname":
				by = By.tagName(LogicalValue);
				break;
			default:
				by = By.xpath(LogicalValue);
				break;
			}
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			System.out.println(objectName+"' object is now visible !!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println(objectName+"' object is not visible even after "+loadTimeOut+" seconds");
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method waits till the page gets idle within the given timeOut.
	 * @author Arun<arun.kumar@move.com>
	 * @param timeOut -Provide timeout in seconds, example 300
	 */
	public boolean waitTillPageLoad(int timeOut)
	{
		boolean browserIdle=false;
		int count=0;

		try 
		{
			while(!browserIdle)
			{
				//Updated by Johnsi on 04/21/2016 by adding document.readyState check
				browserIdle = (Boolean) ((JavascriptExecutor) Driver.Gprops.GetWebDriver()).executeScript("return (window.jQuery != null) && (jQuery.active == 0)");
				Thread.sleep(1000);

				if(count++ > timeOut)
				{
					System.out.println("Page is still loading even after time out of " + timeOut + " second/s");
					return false;
				}
				System.out.println("Page is still loading, elapsed " + count + " second/s");
			}
			System.out.println("Page is completely loaded in " + count + " second/s");

			//Updated By Ram on 4/22/2016 to display the message only if it takes more than 5 seconds
			if(count > 5){
				Driver.CUtil.WriteResults("Page is completely loaded in " + count + " second/s");
			}
			//Till here
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}		
		return true;
	}

	/**
	 * This function will scroll an object to the top or bottom of the page
	 * @author	Faseena KV<faseena.kv@move.com>
	 * @version	1.0
	 * @return	this function will return a string value.
	 * @param Alignment = top or bottom
	 */
	public boolean scrollIntoView (String ScreenName, String ObjectName, String alignment)
	{
		boolean flag= false;
		try{

			WebElement webObject = Driver.WebCtrl.GetWebElement(ScreenName, ObjectName);
			if (webObject!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation 
			{
				WebDriver driver = Driver.Gprops.GetWebDriver();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				if(alignment.toLowerCase().contains("top"))
				{
					jse.executeScript("arguments[0].scrollIntoView(true);", webObject);
				}
				else if(alignment.toLowerCase().contains("bottom"))
				{

					jse.executeScript("arguments[0].scrollIntoView(false);", webObject);
					flag=true;

				}else
				{
					Driver.CUtil.WriteResults("Enter a proper alignment option from: bottom or top","scrollIntoView", "failed");
					flag=true;
				}
			}
			else
			{
				Driver.CUtil.WriteResults("Webelement returned null","scrollIntoView", "failed");

			}

		}
		catch (Exception Ex)
		{
			Driver.CUtil.WriteResults("Exception "+Ex,"scrollIntoView", "failed");	
		}
		return flag;
	}	

	/**
	 * This function can be used for all mouse related operations using java script
	 * @author	Ditimoni Borpatra Gohain
	 */

	public boolean mouseOperationsUsingJavaScript(String ScreenName, String ObjectName, String operation)
	{
		boolean flag=false;
		JavascriptExecutor jse=(JavascriptExecutor)Driver.Gprops.GetWebDriver();
		WebElement toElement = Driver.WebCtrl.GetWebElement(ScreenName,ObjectName);

		if(toElement!=null && Driver.WebCtrl.waitTillElementVisibility(ScreenName, ObjectName, 10)) //Sonu-Added Explicit wait to check element visibility till 10 secs before performing any operation
		{
			boolean isJQDefined=!(boolean) jse.executeScript("return typeof jQuery =='undefined';");
			if(isJQDefined){
				jse.executeScript("$(arguments[0]).trigger('"+operation+"');",toElement);
				flag=true;
			}
		}else
		{
			Driver.CUtil.WriteResults(ObjectName+" element is not displayed", "mouseOperationsUsingJavaScript", "Warning");
		}
		return flag;
	}


	protected static Dimension getViewportSize(WebDriver driver) {
		int width = extractViewportWidth(driver);
		int height = extractViewportHeight(driver);
		return new Dimension(width, height);
	}

	protected static int extractViewportWidth(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int viewportWidth = Integer.parseInt(js.executeScript(JS_GET_VIEWPORT_WIDTH, new Object[0]).toString());
		return viewportWidth;
	}

	protected static int extractViewportHeight(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int result = Integer.parseInt(js.executeScript(JS_GET_VIEWPORT_HEIGHT, new Object[0]).toString());
		return result;
	}

	private static final String JS_GET_VIEWPORT_WIDTH = "var width = undefined; if (window.innerWidth) {width = window.innerWidth;} else if (document.documentElement && document.documentElement.clientWidth) {width = document.documentElement.clientWidth;} else { var b = document.getElementsByTagName('body')[0]; if (b.clientWidth) {width = b.clientWidth;}};return width;";

	private static final String JS_GET_VIEWPORT_HEIGHT = "var height = undefined;  if (window.innerHeight) {height = window.innerHeight;}  else if (document.documentElement && document.documentElement.clientHeight) {height = document.documentElement.clientHeight;}  else { var b = document.getElementsByTagName('body')[0]; if (b.clientHeight) {height = b.clientHeight;}};return height;";

	/**
	 * This function will update Test Info On Sauce
	 * @author	Murthi
	 */
	public void updateTestInfoOnSauce(String JobID,boolean isPassed){
		try {
			String UserID=Driver.globalValues.get("sauceusername");
			String AccessKey=Driver.globalValues.get("sauceaccesskey");
			SauceREST client = new SauceREST(UserID, AccessKey);
			Map<String, Object> updates = new HashMap<String, Object>();
			updates.put("name", JobID); //JobID is session id here
			updates.put("passed", isPassed);

			//Adding custom data to sauce report
			JSONObject jsonObj=new JSONObject();
			Scenario sc=(Scenario) Driver.mapper.get(Thread.currentThread().getName()+"Scenario");
			String Scenario_Name=sc.getName();
			jsonObj.append("Scenario", Scenario_Name);
			updates.put("custom-data", jsonObj);

			client.updateJobInfo(JobID, updates);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
