package Lib.Common;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;






































import org.apache.http.HttpHost;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cucumber.api.Scenario;
import Lib.Controller.Driver;

import java.awt.Robot; 
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent; 

public class CommonUtil {
	
	/**
	 * This function will get the month no.
	 */
	public String GetMonthNo(String MonthString){
		String MonthNo = "";
		switch(MonthString.toLowerCase()){
		case "jan":
			MonthNo = "01";
			break;
		case "feb":
			MonthNo = "02";
			break;
		case "mar":
			MonthNo = "03";
			break;
		case "apr":
			MonthNo = "04";
			break;
		case "may":
			MonthNo = "05";
			break;
		case "jun":
			MonthNo = "06";
			break;
		case "jul":
			MonthNo = "07";
			break;
		case "aug":
			MonthNo = "08";
			break;
		case "sep":
			MonthNo = "09";
			break;
		case "oct":
			MonthNo = "10";
			break;
		case "nov":
			MonthNo = "11";
			break;
		case "dec":
			MonthNo = "12";
			break;
		//Added by Diti to give full month name	
		case "january":
			MonthNo = "01";
			break;
		case "february":
			MonthNo = "02";
			break;
		case "march":
			MonthNo = "03";
			break;
		case "april":
			MonthNo = "04";
			break;
		case "june":
			MonthNo = "06";
			break;

		case "july":
			MonthNo = "07";
			break;
		case "august":
			MonthNo = "08";
			break;
		case "september":
			MonthNo = "09";
			break;
		case "october":
			MonthNo = "10";
			break;
		case "november":
			MonthNo = "11";
			break;
		case "december":
			MonthNo = "12";
			break;
		}
		return MonthNo;
	}
	
	/**
	 * This function will read input file from xml
	 */
	public Map<String,String> ReadInputFile(String FileName)
	{
		Map<String,String> Master = new HashMap<String, String>();
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (new File(FileName));
	        NodeList nNameList = doc.getElementsByTagName("Parameter");
	        System.out.println(nNameList.getLength());
	        for (int LoopB = 0; LoopB < nNameList.getLength(); LoopB++)
	        {
	        	NamedNodeMap NNM = nNameList.item(LoopB).getAttributes();
	        	Master.put(NNM.getNamedItem("Name").getNodeValue(),nNameList.item(LoopB).getFirstChild().getNodeValue());
	        }
		}
		catch(Exception ex)
		{
			Master.put("error",ex.getMessage());
		}
		return Master;
	}
	
	/**
	 * This function will read the object file and returns the required value based on the keyword passed
	 * @param ScreenName
	 * @param ObjectName
	 * @return	Will return the Line containg the keyword, attribute ID, and its value
	 * @exception Exception
	 */
	public String ReadObjectFile(String ScreenName, String ObjectName)
	{
		String ReturnValue = "";
		try
		{
			String ORPath = Driver.Init.GetORPath() + ScreenName;
			BufferedReader br = new BufferedReader(new FileReader(ORPath));
			String Line = "";
 			while ((Line = br.readLine()) != null)
			{
				//if (Line.toLowerCase().trim().startsWith(ObjectName.toLowerCase().trim()))
				String[] LinContent = Line.split(",",3);
				if(LinContent[0].toLowerCase().trim().equals(ObjectName.toLowerCase().trim()))
				{
					ReturnValue = Line;
					break;
				}
			}
			br.close();
		}
		catch(Exception ex)
		{
		System.out.println(ex.getMessage());	
		}
		return ReturnValue;
	}
			
	/** 
	 * This function will generate a random string value, this function can be 
	 * used where we need to generate First Name, Last Name, etc... in the test script
	 *  
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @return	This function returns a random string value
	 */
	public String GetRandomString(){
		String RetuenValue = "";
		try{
			String[] Chrs = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "Q", "A", "Z", "W", "S", "X", "E", "D", "C", "R", "F", "V", "T", "G", "B", "Y", "H", "N", "U", "J", "M", "I", "K", "O", "L", "P", "qa", "aq", "az", "za", "ws", "sw", "sx", "xs", "ed", "de", "dc", "cd", "rf", "fr", "fv", "vf", "tg", "gt", "gb", "bg", "yh", "hy", "hn", "nh", "uj", "ju", "jm", "mj", "ik", "ki", "ol", "lo", "lp", "pl"};
			String RValue = "";
			int ChrsLength = Chrs.length;
			Random Rnd = new Random();
			for (int x=0;x<10;x++){
				RValue += Chrs[Rnd.nextInt(ChrsLength)];
			}
			RetuenValue = RValue;			
		}
		catch(Exception ex){
			RetuenValue = "error";
		}
		return RetuenValue;
	}

	/** 
	 * This function will clear the temp folder
	 *  
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * 
	 */
	public void ClearTempFolder(){
		try{
			//Driver.Log.info("Clearing Temp execution files");
			File TDir = new File(Driver.Init.GetTempFolder());
			FileUtils.cleanDirectory(TDir);
		}
		catch(Exception ex){
			//Driver.Log.fatal(ex.getMessage());
		}
	}
	
	/** 
	 * This function will create a folder inside the Result folder
	 * The Name of the folder is the current system time in milliseconds
	 *  
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * 
	 */
	public String MakeResultFolder(){
		String ReuturnValue = "";
		try{
			String Folder = Driver.Init.GetResultPath();
//			Folder += dt.getYear() + "" + dt.getMonth() + "" + dt.getDay() + dt.getSecond() + "" + dt.getMinute() + "" + dt.getHour();
			Folder += Driver.Gprops.GetRunID() + "/";
			File nFldr = new File(Folder);
			nFldr.mkdir();
			ReuturnValue = Folder;
		}
		catch(Exception ex){
			ReuturnValue = "error";
		}
		return ReuturnValue;
	}
	
	/** 
	 * This function will clear the Result folder in the framework
	 *  
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * 
	 */
	public void ClearResultFolder(){
		try{
			File TDir = new File(Driver.Init.GetResultPath());
			FileUtils.cleanDirectory(TDir);
		}
		catch(Exception ex){
			
		}
	}
	
	/**
	 * This function use the Robot Class and set the value in the GUI
	 *  <P>
	 * Point to note, the AUT has to be active when this method is called 
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @exception IllegalArgumentException
	 * @exception Exception
	 */
	public void SetValueRobot(String Value){
		try{
			Robot c3po = new Robot();
			int Container = 0;
			String[] arrValues = Value.toLowerCase().split("");
			for(int LoopA = 1; LoopA<arrValues.length;LoopA++){
				switch (arrValues[LoopA]){
					case "a":
						Container = KeyEvent.VK_A;
						break;
					case "b":
						Container = KeyEvent.VK_B;
						break;
					case "c":
						Container = KeyEvent.VK_C;
						break;
					case "d":
						Container = KeyEvent.VK_D;
						break;
					case "e":
						Container = KeyEvent.VK_E;
						break;
					case "f":
						Container = KeyEvent.VK_F;
						break;
					case "g":
						Container = KeyEvent.VK_G;
						break;
					case "h":
						Container = KeyEvent.VK_H;
						break;
					case "i":
						Container = KeyEvent.VK_I;
						break;
					case "j":
						Container = KeyEvent.VK_J;
						break;
					case "k":
						Container = KeyEvent.VK_K;
						break;
					case "l":
						Container = KeyEvent.VK_L;
						break;
					case "m":
						Container = KeyEvent.VK_M;
						break;
					case "n":
						Container = KeyEvent.VK_N;
						break;
					case "o":
						Container = KeyEvent.VK_O;
						break;
					case "p":
						Container = KeyEvent.VK_P;
						break;
					case "q":
						Container = KeyEvent.VK_Q;
						break;
					case "r":
						Container = KeyEvent.VK_R;
						break;
					case "s":
						Container = KeyEvent.VK_S;
						break;
					case "t":
						Container = KeyEvent.VK_T;
						break;
					case "u":
						Container = KeyEvent.VK_U;
						break;
					case "v":
						Container = KeyEvent.VK_V;
						break;
					case "w":
						Container = KeyEvent.VK_W;
						break;
					case "x":
						Container = KeyEvent.VK_X;
						break;
					case "y":
						Container = KeyEvent.VK_Y;
						break;
					case "z":
						Container = KeyEvent.VK_Z;
						break;
					case "1":
						Container = KeyEvent.VK_1;
						break;
					case "2":
						Container = KeyEvent.VK_2;
						break;
					case "3":
						Container = KeyEvent.VK_3;
						break;
					case "4":
						Container = KeyEvent.VK_4;
						break;
					case "5":
						Container = KeyEvent.VK_5;
						break;
					case "6":
						Container = KeyEvent.VK_6;
						break;
					case "7":
						Container = KeyEvent.VK_7;
						break;
					case "8":
						Container = KeyEvent.VK_8;
						break;
					case "9":
						Container = KeyEvent.VK_9;
						break;
					case "0":
						Container = KeyEvent.VK_0;
						break;
					case ",":
						Container = KeyEvent.VK_COMMA;
						break;
					case " ":
						Container = KeyEvent.VK_SPACE;
						break;
				}
				c3po.keyPress(Container);	
				c3po.keyRelease(Container);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Moves mouse pointer to given screen coordinates.
	 * <P>
	 * Presses Left mouse buttons and Releases Left mouse buttons.
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @param	xCord	X Position in the X axis and Y Position in the Y axis  
	 */
	public void MouseClickRobot(int xCord, int yCord){
		try{
			Robot r2d2 = new Robot();
			r2d2.mouseMove(xCord, yCord);
			r2d2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r2d2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		catch(Exception ex){
			
		}
		
	}
	
	/**
	 * Moves mouse pointer to given screen coordinates.
	 * <P>
	 * Presses Right mouse buttons and Releases Right mouse buttons.
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @param	xCord	X Position in the X axis and Y Position in the Y axis  
	 */
	public void MouseRightClickRobot(int xCord, int yCord){
		try{
			Robot r2d2 = new Robot();
			r2d2.mouseMove(xCord, yCord);
			r2d2.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			r2d2.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		}
		catch(Exception ex){
			
		}
		
	}
	
	/**
	 * Rotates the scroll wheel on wheel-equipped mice.
	 * @author	Krishna Kishore G <krishnakg@collabera.com>
	 * @version	1.0
	 * @param	Rolls	number of "notches" to move the mouse wheel Negative values indicate movement up/away from the user, positive values indicate movement down/towards the user.
	 */
	public void ScrollPageRobot(int Rolls){
		try{
			Robot r2d2 = new Robot();
			r2d2.mouseWheel(Rolls);
		}
		catch(Exception ex){
			
		}
		
	}
	
	/**
	 * This function will capture screenshots
	 * @author	Murthi S	<murthis@collabera.com>
	 * @version	1.0
	 */
	public String CaptureScreenShot(WebDriver oBrowser,String MethodName,String ResultPath)
	{
		oBrowser = new Augmenter().augment(oBrowser); // Code added by Krishna Kishore G to support the screenshot at the remote Grid webdriver
		File scrFile = ((TakesScreenshot)oBrowser).getScreenshotAs(OutputType.FILE);
		double RanNo =  Math.random() * (Math.random() * 158624);
		String FileName = MethodName + RanNo + ".png";
		String ABSPath = ResultPath + FileName;
		try 
		{
			FileUtils.copyFile(scrFile, new File(ABSPath));
			
		}
		catch (IOException e) 
		{
			FileName = null;
			e.printStackTrace();
		}
		return FileName;
	}
	
	/**
	 * This function will move mouse
	 */
	public void MoveMouse(int x, int y){
		try{
			Robot Rbt = new Robot();
			Rbt.mouseMove(x, y);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * This function will open Excel file
     * @author    Ditimoni      <ditimonibg@collabera.com>
     * @version   1.0
     */
    private static Workbook OpenExcelFile(String pathName){
           Workbook wb = null;
           try{
                 FileInputStream fis= new FileInputStream(pathName);
                 wb = WorkbookFactory.create(fis);
           }
           catch(Exception ex){
                 
           }
           return wb;
    }
    
    /**
    * This function will get Excel Data
    * @author    Ditimoni      <ditimonibg@collabera.com>
    * @version   1.0
    */
    private static String getExcelData(Workbook WorkBook, int rowNum, int cellNum) 
    {
           Sheet sheet=WorkBook.getSheet("Sheet1");
           Row row=sheet.getRow(rowNum);
           Cell cel=row.getCell(cellNum);
           String cellValue=cel.getStringCellValue();
           return cellValue;
    }
    
    /**
    * This function will get cell count
    * @author    Ditimoni      <ditimonibg@collabera.com>
    * @version   1.0
    */
    private static int getCellCount(Workbook wb) 
    {
           Sheet sheet=wb.getSheet("Sheet1");
           Row row=sheet.getRow(0);
           int cellNum=row.getLastCellNum();
           return cellNum;
    }
    
    /**
    * This function will get row count
    * @author    Ditimoni      <ditimonibg@collabera.com>
    * @version   1.0
    */
    private static int getRowCount(Workbook wb){
           Sheet sheet=wb.getSheet("Sheet1");
           int rowCount=sheet.getLastRowNum();
           return rowCount;
    }
    
    /**
    * @author    Ditimoni      <ditimonibg@collabera.com>
    * @version   1.0
    */    
    public Object[][] getDataProvider(String pathName)
            {
                   //open excel file get the excel object
                   Workbook oWB = OpenExcelFile(pathName);
                   int rowCount=getRowCount(oWB);
                   int columnCount=getCellCount(oWB);
                   Object[][] data=new Object[rowCount+1][columnCount];
                   for(int i=0;i<=rowCount;i++)
                   {
                         for(int j=0;j<columnCount;j++)
                         {
                                data[i][j]=getExcelData(oWB, i,j); //pass the excel object and the i,j
                         
                         }
                   }
                   oWB = null;
                   return data;
            }

    
    /**
     * This function will take a class as input and then will return the absolute path of the file
     * @author	Krishna Kishore G <krishnakishore.g@move.com>
     * @version	1.0
     */
    public String GetClassAbsPath(Class ClsObj){
    	String returnvalue = "";
    	returnvalue = System.getProperty("user.dir") + "/src/" + ClsObj.getPackage().getName().toString().replace(".", "/");
    	return returnvalue;
    }
    
    /**
     * This function will clean the desktop by closing all the unwanted application
     * @author	Krishna Kishore G	<krishnakishore.g@move.com>
     * @version	1.0
     */
    public void CleanUpDesktop() throws Exception{
    	String[] apps = {"iexplore.exe","cmd.exe","WINWORD.EXE","EXCEL.EXE","firefox.exe","chrome.exe"};
    	try{
	    	for(int Loop=0;Loop<apps.length;Loop++){
	    		String command = "taskkill /F /IM chromedriver.exe " + apps[Loop];
	    		Runtime.getRuntime().exec(command);
	    	}
    	}
    	catch(Exception ex){
    		System.out.println("Unable to close the borwser and the error message is "+ ex.getMessage());
    	}
    }
    
    /**
     * This function will clean the remote system by closing all the browsers that may be left open by the test
     * @author	Krishna Kishore G	<krishnakishore.g@move.com>
     * @version	1.0
     */
    public void CleanUpAfterTest() throws Exception{
    	String[] apps = {"iexplore.exe","firefox.exe","chrome.exe"};
    	for(int Loop=0;Loop<apps.length;Loop++){
    		String command = "taskkill /F /U corp\\qaautomationSVC /P adtadmin28! /S " + Driver.Gprops.GetNodeIPAddress() + " /IM " + apps[Loop];
    		Runtime.getRuntime().exec(command);
    	}
    }
    
    /**
     * This function will clear all the cookies
     * @author	Krishna Kishore G	<krishnakishore.g@move.com>
     * @version	1.0
     */
    public void ClearCookies(){
    	try{
    			String[] arrIndex = {"1","2","8","9","16","32","255","4351"};
    			//System.out.println(Driver.Init.GetAUTBrowser().toLowerCase().trim());
	    		if(Driver.Init.GetAUTBrowser().toLowerCase().trim().equals("ie")){
	    			for(int Loop=0; Loop<arrIndex.length; Loop++){
	    				String Command = "RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess " + arrIndex[Loop];
	    				Runtime.getRuntime().exec(Command);
	    			}
	    		}
    	}
    	catch(Exception ex){
    		
    	}
    }
 
    /**
     * This function will finds the patter in the attribute and will replace with the given value
     * @author	Krishna Kishore G
     * @version	1.0
     * @param	Attribute	this variable will hold the attribute line returned by the function ReadObjectFile
     * @param	Replacer	this variable will hold the value that need to be added to the Attribute
     * @param	pattern		this variable will hold the pattern that need to searched in the Attribute, usually this has to in the format $$<<name>>$$
     */
    public String PattenReplacer(String Attribute, String Replacer, String pattern){
    	String rValue = "";
    	try{
    		//rValue = Attribute.toLowerCase().replace(pattern.toLowerCase(), Replacer.toLowerCase());
    		rValue = Attribute.replace(pattern, Replacer);
    		System.out.println("Entered PattenReplacer Function");
    		System.out.println("Function will search for " + pattern + " in the search string" + Attribute + " and will be replaced with " + Replacer);
    		System.out.println("The resultant return string is " + rValue);
    	}
    	catch(Exception ex){
    		rValue = "error";
    	}
    	return rValue;
    }

    /**
     * @author	Krishna Kishore G	<krishnakishore.g@move.com>
     * @version	1.0
     * 
     */
    public String GetElementText(WebDriver oBrowser, String ObjectProp){
    	try{
    		WebElement WebEl = Driver.WebCtrl.GetWebElement(oBrowser, ObjectProp);
    		return WebEl.getText();
    	}
    	catch(Exception ex){
    		return "error";
    	}
    }

    /**
     * This function will generate a random string of 14 chars in length
     * @author krishna kishore G
     * @return	this function will return a random string
     */
    public String RandomString(){
    	String[] chrs = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "d", "f", "g", "h", "j", "k", "l", "s", "z", "x", "c", "v", "b", "n", "m", "Q", "A", "Z", "X", "S", "W", "E", "D", "C", "V", "F", "R", "T", "G", "B", "N", "H", "Y", "U", "J", "M", "I", "K", "O", "P", "L"};
		int minimum = 0;
		int maximum = chrs.length;
		String Name = "";
		for(int x=0; x<14; x++){
			Name += chrs[minimum + (int)(Math.random()*maximum)];
		}
		return Name;
    }
    
    /**
     * This function will generate a random e-mail address
     * @author krishna kishore G
     * @return	this function will return a random e-mail address 
     */
    public String RandomEMailAddress(){
    	String[] chrs = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "d", "f", "g", "h", "j", "k", "l", "s", "z", "x", "c", "v", "b", "n", "m"};//Sonu- Removed all the caps char from random email
		String[] Nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "0", "2", "9", "3", "8", "4", "7", "5", "6"};
		int minimum = 0;
		int maximum = chrs.length;
		int maxNum = Nums.length;
		String Name = "";
		for(int x=0; x<10; x++){
			Name += chrs[minimum + (int)(Math.random()*maximum)];
		}
		for(int x=0; x<4; x++){
			Name += Nums[minimum + (int)(Math.random()*maxNum)];
		}
		Name += "@move";
		//updated by sonu to make all email end with @test.com
		/*for(int x=0; x<5; x++){
			Name += chrs[minimum + (int)(Math.random()*maximum)];
		}*/
		Name += ".";
//		for(int x=0; x<3; x++){
//			Name += chrs[minimum + (int)(Math.random()*maximum)];
//		}
		Name +="com";
		return Name;
    }

    /**
     * This function will read the SQL Query from the query file (SQLQuery) and removed the keyword & returns only the query to the caller
     * @param KeyWord
     * @return	this function will return the sql query
     * @exception	ArrayIndexOutofBound
     * @exception	Exception
     */
    public String GetSQLQuery(String KeyWord){
    	try{
    		return Driver.CUtil.ReadObjectFile("SQLQuery", KeyWord).split(",",2)[1];
    	}
    	catch(Exception ex){
    		return "error";
    	}
    }
    
    /**
     * This function will read the DBdetails file, based on the condition like the KeyWords & the base URL environment
     * and returns the value in a string array
     * array[0] = DataBase Server
     * array[1] = instance
     * array[2] = Database Name
     * array[3] = User Name 
     * array[4] = Password
     * @param KeyWord
     * @return
     */
    public String[] GetDBDetails(String KeyWord){
    	String[] RetValue = null; 
//    	boolean flag=false;
//    	String kWord = KeyWord.trim() + ";" + Driver.Init.GetBaseURL().replace("http://", "").trim();
//		String[] LinContent = null;
//		String ORPath = Driver.Init.GetORPath() + "DBDetails";
//    	try{
//    		BufferedReader br = new BufferedReader(new FileReader(ORPath));
//			String Line = "";
//			while ((Line = br.readLine()) != null)
//			{
//				//if (Line.toLowerCase().trim().startsWith(ObjectName.toLowerCase().trim()))
//				LinContent = Line.split(";",7);
//				if(LinContent[0].toLowerCase().trim().equals(KeyWord.toLowerCase().trim()) && LinContent[1].toLowerCase().trim().equals(Driver.Init.GetBaseURL().replace("http://", "").trim()))
//				{
//					flag=true;
//					break;
//				}
//			}
//			br.close();
//    		if (flag==false){
//    			RetValue = null;
//    			//System.out.println("the db datails are not in the correct format");
//    			System.out.println("UnAble to get the dbdetails");
//    		}
//    		else{
//    			RetValue = new String[LinContent.length-2];
//    			int LoopA = 0;
//	    		for(int Loop=2; Loop < LinContent.length; Loop++){
//	    			System.out.println("DBVal = " + LinContent[Loop]);
//	    			RetValue[LoopA] = LinContent[Loop];
//	    			LoopA += 1;
//	    		}
//    		}
//    	}
    	try{
    		
    		String ServerName="",InstanceName="",UserName="",Password="",DBName="";
    		boolean IsInstance=false;
    		
    		//Get Database details from environment file
    		ServerName=Driver.Init.GetDBServer();
    		IsInstance=Driver.Init.GetIsInstance();
    		if(IsInstance){
    			InstanceName=Driver.Init.GetDBInstance();
    		}
    		DBName=Driver.Init.GetDBName();
    		UserName=Driver.Init.GetDBUserName();
    		Password=Driver.Init.GetDBPassword();
    		
    		//Assign values
    		RetValue =new String[5];
    		RetValue[0]=ServerName;
    		RetValue[1]=InstanceName;
    		RetValue[2]=DBName;
    		RetValue[3]=UserName;
    		RetValue[4]=Password;
    		
    	}
    	catch(Exception ex){
    		RetValue = null;
    		System.out.println("Error @ GetDBDetails : "+ ex.getMessage());
    	}
    	return RetValue;
    }

    /**
     * @author    Shwetha kulkarni <shwethabk@collabera.com>
     * @version   1.0
     */
     public String CaptureScreenShot(String MethodName)
     {
    	    String pFName = "";
    	 	WebDriver otDriver = Driver.Gprops.GetWebDriver();
            File scrFile = null;
            if(otDriver != null){
            	if(Driver.Init.GetAUTBrowser().contains("rwd-hu")){
            		return pFName;
            	}
            	if(Driver.Init.GetAUTBrowser().contains("droid")){
            		// this code will take screen shot for android 
            		scrFile = ((TakesScreenshot) otDriver).getScreenshotAs(OutputType.FILE);
            	}
            	else if (Driver.Init.GetAUTBrowser().contains("rwd")){
	                   WebDriver augmentdriver = new Augmenter().augment(otDriver);// Augmenter().augment(otDriver);
	                   EventFiringWebDriver EFW=new EventFiringWebDriver(augmentdriver);
	                   scrFile = ((TakesScreenshot)augmentdriver).getScreenshotAs(OutputType.FILE);
	                   //scrFile =EFW.getScreenshotAs(OutputType.FILE);
	                   
	            }
	            else{
	                   scrFile = ((TakesScreenshot)otDriver).getScreenshotAs(OutputType.FILE);
	            }
	            pFName =  MethodName + System.currentTimeMillis() + ".jpg";
	            String FileName = System.getProperty("user.dir") + "/target/" + Driver.Gprops.GetRunID() + "/" + pFName;
	            try 
	            {
	                   FileUtils.copyFile(scrFile, new File(FileName));
	                   
	            }
	            catch (IOException e) 
	            {
	            	pFName = null;
	                   e.printStackTrace();
	            }
            }
            return pFName;

     }
     
     /**
 	 * This function will remove all the special characters from a string
 	 * @param String str
 	 * @return	Will return the string without special characters
 	 */
    public String removeSpecialChar(String str)
    {
        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
        Matcher match= pt.matcher(str);
        while(match.find())
        {
            String s= match.group();
            str=str.replaceAll("\\"+s, "");
        }
        
    	return str;
    }

    /**
     * @author Madhusudhana Ks Madhusudhana.shivalingappag@move.com 
     * @param Parm1
     * @param Parm2
     * @deprecated
     */
    public synchronized void  WriteResults(String Message,String FUnction_Name) {
		try {
			String RunId=Driver.Gprops.GetRunID();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			//String ResultPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + ".txt";
			String ResultPath = System.getProperty("user.dir") + "/target/" + RunId + "/Log.html";
			File tsFolder = new File(System.getProperty("user.dir") + "/target/" + RunId + "/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}	
			//System.out.println(ResultPath);
			System.out.println("[" + FUnction_Name + "]-"+ Message);  // added by shwetha kulkarni
			File file = new File(ResultPath);
//			file.getCanonicalPath();
//			Driver.Gprops.SetResultFileAddress(file.getCanonicalPath());
//			String ResultfileAddress = Driver.Gprops.GetResultFileAddress();
			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(ResultPath,true));
			bw.write("[" + dateFormat.format(date) +"] : [" + Thread.currentThread().getName() + "]-[" + FUnction_Name + "]-"+ Message + "<br>");
			bw.close(); 
			
		} catch (IOException e) {
			System.out.println("Exception @ WriteResults " + e.getMessage());
		}
	}
    
    
    /**
     * This function is used to get the ip address of the node where the script is executing
     * @author murthi subramani
     * @param Web driver
     * @return ip addess as string
     */
    
    public String getIPOfNode(RemoteWebDriver remoteDriver) { 
		String hostFound = null; 
		try { 
			HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor(); 
			String hostName = ce.getAddressOfRemoteServer().getHost(); 
			int port = ce.getAddressOfRemoteServer().getPort(); 
			HttpHost host = new HttpHost(hostName, port); 
			DefaultHttpClient client = new DefaultHttpClient(); 
			URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + remoteDriver.getSessionId()); 
			BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest( "POST", sessionURL.toExternalForm()); 
			HttpResponse response = client.execute(host, r); 
			JSONObject object = extractObject(response); 
			URL myURL = new URL(object.getString("proxyId")); 
			if ((myURL.getHost() != null) && (myURL.getPort() != -1)) { 
				hostFound = myURL.getHost(); 
				} 
			} catch (Exception e) { 
				System.err.println(e); 
				} 
		return hostFound; 
		} 
    /**
     * This function is used t extract the json object from Http reponse
     * @param resp
     * @return
     * @throws IOException
     * @throws JSONException
     */
	public JSONObject extractObject(HttpResponse resp) throws IOException, JSONException { 
		InputStream contents = resp.getEntity().getContent(); 
		StringWriter writer = new StringWriter();
		IOUtils.copy(contents, writer, "UTF8"); 
		JSONObject objToReturn = new JSONObject(writer.toString()); 
		return objToReturn; 
		}

    
    /**
     * @author Krishna Kishore G - krishnakishore.g@move.com 
     * @param Message This parameter will hold the message that needs to be writtem to the log file 
     * @param FUnction_Name This Parameter will hold the name of the function that passed the message
     * @param Status This paramter will hold the status of the message, the status that are accepted by this function are * Pass * Fail * Warning * Done * NA
     */
    public void WriteResults(String Message,String functionName, String Status) {
    	String Msg=Message + "<br>";
    	if(Status.toLowerCase().contains("fail") || Status.toLowerCase().contains("warning")){
    		Driver.globalValues.put("FailedMessage"+Thread.currentThread().getName(), Msg);
    	}
    	
		try {
			if(Driver.Init.GetResultPath().trim().equals("")){
				return;
			}
			String pPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + Thread.currentThread().getName() + ".adtl";
			String logPath = System.getProperty("user.dir") + "/target/" + Driver.Gprops.GetRunID() + "/Log_For_Thread_" + Thread.currentThread().getName() + ".html";
			String SlogPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/SLog_For_Thread_" + Thread.currentThread().getName() + ".slog";
//			String fPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + Thread.currentThread().getName() + ".fail";
//			String wPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + Thread.currentThread().getName() + ".warning";
//			String iPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + Thread.currentThread().getName() + ".info";
//			String nPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + Thread.currentThread().getName() + ".na";
			String fPath = pPath;
			String wPath = pPath;
			String iPath = pPath;
			String nPath = pPath;
			File tsFolder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}
			String StackTraceData = Driver.MethodName();
			if(Status.trim().equals("")){
				System.out.println("[" + functionName + "] - {N/A} - "+ Message + "[" + StackTraceData.replace("<br>", "/") + "]");
			}
			else{
				if(Status.trim().equalsIgnoreCase("pass")){Status = "Passed";}
				else if(Status.trim().equalsIgnoreCase("fail")){Status = "Failed";}
				System.out.println("[" + functionName + "] - {" + Status + "} - " + Message + " - [" + StackTraceData.replace("<br>", "/") + "]");
			}
			//Message
			Message = Message + "<br> <i> Stack Trace : " + StackTraceData + "</i>";
			if(Driver.Gprops.GetFeatureFiles().size() > 0){
				if(Driver.Gprops.GetFeatureFiles(Thread.currentThread().getName())!=null){
					try {
						Message = Message + "<br> Feature File Name = " + Driver.Gprops.GetFeatureFiles(Thread.currentThread().getName()).toString();
					} catch (Exception e) {
						System.out.println("This is a native application hence no URL ");
					}
				}
			}
			if (Driver.Gprops.GetWebDriver() != null){
				try {
					Message = Message + "<br>Browser URL - [" + Driver.Gprops.GetWebDriver().getCurrentUrl() + "]<br>Page Title - [" + Driver.Gprops.GetWebDriver().getTitle() + "]";
				} catch (Exception e) {
					System.out.println("This is a native application");
				} 
			}
			else{
				Message = Message + "<br><b>Unable to get the URL and Page title because the browser is not open</b>";
			}
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			// code added by Krishna Kishore Gunasekaran on 02/19/2014 - this code is added to write the content to the log.html file
			File LogFile = new File(logPath);
			if(!LogFile.exists()){
				LogFile.createNewFile();
			}
			File SLogFile = new File(SlogPath);
			if(!SLogFile.exists()){
				SLogFile.createNewFile();
			}
			
			String Msg1=Msg.toLowerCase();
			if (Driver.Gprops.GetWebDriver() != null && ! Msg1.contains("creating webelement")){
				try{
					Scenario sc=(Scenario) Driver.mapper.get(Thread.currentThread().getName()+"Scenario");
					sc.write("<div><span>The user is on the page with url "+Driver.Gprops.GetWebDriver().getCurrentUrl()+"</span></div>");
					sc.write(Msg);
				}
				catch(Exception e){
					
				}
		
			}
			if(!Status.trim().equalsIgnoreCase("")){
				BufferedWriter slogWritter = new BufferedWriter(new FileWriter(SLogFile,true));
				slogWritter.append("["+dateFormat.format(date) + "] :: [" + Thread.currentThread().getName() + "] :: " + Msg+"<br>");
				slogWritter.close();
			}
			
			BufferedWriter logWritter = new BufferedWriter(new FileWriter(LogFile,true));
			logWritter.append("<font color=\"blue\">" + dateFormat.format(date) + " ::-:: FUNCTION ==> " + functionName + " ::-:: MESSAGE ==> " + Message + "</font><br>");
			logWritter.close();
			switch (Status.trim().toLowerCase()) {
				case "passed":
					File pLog = new File(pPath);
					if(!pLog.exists()){
						pLog.createNewFile();
					}
					BufferedWriter plogWritter = new BufferedWriter(new FileWriter(pLog,true));
					plogWritter.append("<tr><td>" + dateFormat.format(date) + "</td><td bgcolor=\"DarkGreen\">PASSED</td><td>" + functionName + "</td><td>" + Message + "</td></tr>"+"\n");
					plogWritter.close();
					break;
				case "failed":
					String imgFileName ="";
					imgFileName = CaptureScreenShot(functionName);
					Driver.Gprops.AddErrorImgNamesInList(imgFileName);
					File fLog = new File(fPath);
					if(!fLog.exists()){
						fLog.createNewFile();
					}
					BufferedWriter flogWritter = new BufferedWriter(new FileWriter(fLog,true));
					flogWritter.append("<tr><td>" + dateFormat.format(date) + "</td><td bgcolor=\"Red\">FAILED</td><td><a href=\""+imgFileName+"\" target=\"_blank\">" + functionName + "</a></td><td>" + Message + "</td></tr>"+"\n");
					flogWritter.close();
					break;
				case "warning"://Added below two lines of code to add snapshot at the time of warning:21/02/2014
					String imgFile = "";
					imgFile = CaptureScreenShot(functionName);
					Driver.Gprops.AddErrorImgNamesInList(imgFile);
					File wLog = new File(wPath);
					if(!wLog.exists()){
						wLog.createNewFile();
					}
					BufferedWriter wlogWritter = new BufferedWriter(new FileWriter(wLog,true));
					wlogWritter.append("<tr><td>" + dateFormat.format(date) + "</td><td bgcolor=\"AntiqueWhite\">WARNING</td><td><a href=\""+imgFile+"\" target=\"_blank\">" + functionName + "</td><td>" + Message + "</td></tr>"+"\n");
					wlogWritter.close();
					break;
				case "done":
					File iLog = new File(iPath);
					if(!iLog.exists()){
						iLog.createNewFile();
					}
					BufferedWriter ilogWritter = new BufferedWriter(new FileWriter(iLog,true));
					ilogWritter.append("<tr><td>" + dateFormat.format(date) + "</td><td bgcolor=\"Moccasin\">INFO</td><td>" + functionName + "</td><td>" + Message + "</td></tr>"+"\n");
					ilogWritter.close();
					break;
				default:
					File nLog = new File(nPath);
					if(!nLog.exists()){
						nLog.createNewFile();
					}
					BufferedWriter nlogWritter = new BufferedWriter(new FileWriter(nLog,true));
					nlogWritter.append("<tr><td>" + dateFormat.format(date) + "</td><td bgcolor=\"Lavender\">N/A</td><td>" + functionName + "</td><td>" + Message + "</td></tr>"+"\n");
					nlogWritter.close();
					break;
			}
			
		} catch (Exception e) {
			System.out.println("Exception @ WriteResults(String Message,String FUnction_Name, String Status) " + e.getMessage());
			e.printStackTrace();
		}
	}
    
    public List<String> GetAllResultLogFiles(String Extn){
    	List<String> FilesList = new ArrayList<>();
    	try {
    		  String files;
    		  File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/");
    		  File[] listOfFiles = folder.listFiles(); 
    		  for (int i = 0; i < listOfFiles.length; i++) 
    		  {
    		   if (listOfFiles[i].isFile()) 
    		   {
    		   files = listOfFiles[i].getName();
    		       if (files.trim().toLowerCase().endsWith(Extn))
    		       {
    		    	   FilesList.add(files);
    		        }
    		     }
    		  }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception @ GetAllResultLogFiles : " + e.getMessage());
			FilesList = null;
		}
    	return FilesList;
    }
    
    /**
     * This function will collect all the data from the log variables created and will create a HTML log file
     * @author krishna kishore g - krishnakishore.g@move.com
     */
    public void ComposeProcessedLog(){
    	try {
    		List<String> pFileNames = GetAllResultLogFiles(".adtl");
    		List<String> lFileNames = GetAllResultLogFiles(".slog");
//    		List<String> fFileNames = GetAllResultLogFiles(".fail");
//    		List<String> wFileNames = GetAllResultLogFiles(".warning");
//    		List<String> iFileNames = GetAllResultLogFiles(".info");
//    		List<String> nFileNames = GetAllResultLogFiles(".na");
    		int pFileCount = pFileNames.size();
//    		int fFileCount = fFileNames.size();
//    		int wFileCount = wFileNames.size();
//    		int iFileCount = iFileNames.size();
//    		int nFileCount = nFileNames.size();
    		String RunId=Driver.Gprops.GetRunID();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		    String DateTimeInfo = ft.format(dNow);
		    int pCount = Driver.Gprops.GetPassCounter();
		    int fCount = Driver.Gprops.GetFailCounter();
		    double pPCent = ((double)pCount/((double)pCount+(double)fCount))*100;
		    double fPCent = ((double)fCount/((double)pCount+(double)fCount))*100;
		    //String ResultPath = Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + ".txt";
			String ResultPath = System.getProperty("user.dir") + "/target/" + RunId + "/ProcessedLog.html";
			String LogPath = System.getProperty("user.dir") + "/target/" + RunId + "/Log.html";
			File tsFolder = new File(System.getProperty("user.dir") + "/target/" + RunId + "/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}	
			File file = new File(ResultPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			File lfile = new File(LogPath);
			if (!lfile.exists()) {
				lfile.createNewFile();
			}
			// Code added by KK - 02/07/2014 -  this code will add all the featurew files that are executed in different threads
			String tFeatureFile = "", tFilters = "", tGlues = "";
	        Collection<List<String>>  FF = Driver.Gprops.GetFeatureFiles().values();
			for(List<String> T2 : FF) {
				for(int z=0;z<T2.size();z++){
					tFeatureFile = tFeatureFile + ";<br>" + T2.get(z);
				}
			}
			Collection<List<Object>>  CF = Driver.Gprops.GetCukeFilters().values();
			for(List<Object> T2 : CF) {
				for(int z=0;z<T2.size();z++){
					tFeatureFile = tFeatureFile + ";<br>" + T2.get(z).toString();
				}
			}
			Collection<List<String>>  CG = Driver.Gprops.GetCukeGlues().values();
			for(List<String> T2 : CG) {
				for(int z=0;z<T2.size();z++){
					tFeatureFile = tFeatureFile + ";<br>" + T2.get(z);
				}
			}
//			for(int x= 0; x < Driver.Gprops.GetFeatureFiles(Thread.currentThread().getName()).size(); x++){
//				tFeatureFile = tFeatureFile + ";<br>" + Driver.Gprops.GetFeatureFiles(Thread.currentThread().getName()).get(x);
//			}
//			for(int y = 0; y < Driver.Gprops.GetCukeFilters(Thread.currentThread().getName()).size(); y++){
//				tFilters = tFilters + ";<br>" + Driver.Gprops.GetCukeFilters(Thread.currentThread().getName()).get(y).toString();
//			}
//			for(int z = 0; z < Driver.Gprops.GetCukeGlues(Thread.currentThread().getName()).size(); z++){
//				tGlues = tGlues + ";<br>" + Driver.Gprops.GetCukeGlues(Thread.currentThread().getName()).get(z);
//			}
			
			String[] Browsers = Driver.Init.GetAUTBrowser().split("~");
			String bNames = "";
			for(int x=0;x<Browsers.length;x++){
				if(x==0){
					bNames = Driver.CUtil.GetBrowserName(Browsers[x]) + ", ";
				}
				else{
					bNames += Driver.CUtil.GetBrowserName(Browsers[x]) + ", ";
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(ResultPath,true));
			bw.write("<html<head><title>Welcome to Processed Log File " + dateFormat.format(date) + "</title></head><body bgcolor=\"DarkGray\">" );
			bw.write("<div align=\"center\"></hr><h2>Log File</h2></hr></div>");
			bw.write("</br><table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
			bw.write("<tr><td>Date of Execution</td><td>" + DateTimeInfo + "</td></tr>");
			bw.write("<tr><td>Remote Node Address</td><td>" + Driver.Gprops.GetNodeIPAddress() + "</td></tr>");
			bw.write("<tr><td>Run ID</td><td>" + Driver.Gprops.GetRunID() + "</td></tr>");
			if(System.getProperty("build.number")!=null && !System.getProperty("build.number").contains("build")){
				bw.write("<tr><td>TeamCity Project Name</td><td>" + System.getProperty("teamcity.buildConfName") + "</td></tr>");
				bw.write("<tr><td>TeamCity Branch Name</td><td>" + Driver.globalValues.get("BrachName") + "</td></tr>");
				bw.write("<tr><td>TeamCity Build Number</td><td><a href=\""+Driver.Init.GetFromEnvironment("TeamCityBaseURL")+"/viewLog.html?buildId="+System.getProperty("teamcity.build.id")+"&buildTypeId="+System.getProperty("teamcity.buildType.id")+"\">" + System.getProperty("build.number") + "</a></td></tr>");
			}
			
			if(System.getProperty("jenkins.build.id")!=null && !System.getProperty("jenkins.build.id").contains("BUILD")){
				bw.write("<tr><td>Jenkins Job Nmae&thinsp;</td><td>&emsp;" + System.getProperty("jenkins.job.name") + "&emsp;</td></tr>");
				bw.write("<tr><td>Jenkins Build ID&thinsp;</td><td>&emsp;" +System.getProperty("jenkins.build.id")+"&emsp;</td></tr>");
				bw.write( "<tr><td>Jenkins Build Number&thinsp;</td><td>&emsp;<a href=\""+System.getProperty("jenkins.build.url")+"\">" + System.getProperty("jenkins.build.number") + "</a>&emsp;</td></tr>");
			}
			bw.write("<tr><td>Build Triggered By</td><td>" +Driver.globalValues.get("TriggeredBy")+" ("+Driver.globalValues.get("TriggeredByName")+")"+ "</td></tr>");
			bw.write("<tr><td>Base Url</td><td>" + Driver.Init.GetBaseURL() + "</td></tr>");
			bw.write("<tr><td>Browser Used</td><td>" + bNames + "</td></tr>");
			bw.write("<tr><td>Browser Version Used</td><td>" +  Driver.Gprops.GetBrowserVersion() + "</td></tr>");
			bw.write("<tr><td>OS Name</td><td>" +  Driver.Gprops.GetOSNames() + "</td></tr>");
			bw.write("<tr><td>Feature File\\s</td><td>" + tFeatureFile + "</td></tr>");
			bw.write("<tr><td>Cuke Filters</td><td>" + tFilters + "</td></tr>");
			bw.write("<tr><td>Cuke Glues</td><td>" + tGlues + "</td></tr>");
			bw.write("</table></hr></br><div align=\"center\">");
			bw.write("<div align=\"center\" style=\"width:100%\">");
//			bw.write("<h3>Failed Steps</h3>");
//			bw.write("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
//			bw.write("<tr><td>Date/Time</td><td>Function Name</td><td>Message</td><td>Status</td></tr>");
//			for(int x=0; x < fFileCount; x++){
//				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "\\" + fFileNames.get(x));
//    			BufferedReader br = new BufferedReader(new FileReader(folder));
//    			String line = "";
//    			while ((line = br.readLine()) != null) {
//    				bw.write(line);
//    			}
//    			br.close();
//			}
//			bw.write("</table></hr><h3>Warning Steps</h3>");
//			bw.write("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
//			bw.write("<tr><td>Date/Time</td><td>Function Name</td><td>Message</td><td>Status</td></tr>");
//			for(int x=0; x < wFileCount; x++){
//				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "\\" + wFileNames.get(x));
//    			BufferedReader br = new BufferedReader(new FileReader(folder));
//    			String line = "";
//    			while ((line = br.readLine()) != null) {
//    				bw.write(line);
//    			}
//    			br.close();
//			}
//			bw.write("</table></hr><h3>Passed Steps</h3>");
			bw.write("</hr><h3>Log</h3>");
			for(int x=0; x < pFileCount; x++){
				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + pFileNames.get(x));
    			BufferedReader br = new BufferedReader(new FileReader(folder));
    			String line = "";
    			bw.write("<div id=\"logfilename\"><dfn>Log File Name = " + Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + pFileNames.get(x) + "</dfn></div>");
    			bw.write("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\" width=\"100%\">");
    			bw.write("<tr><td>Date/Time</td><td>Status</td><td>Function Name</td><td>Message</td></tr>");
    			while ((line = br.readLine()) != null) {
    				bw.write(line);
    			}
    			bw.write("</table>");
    			br.close();
			}
			
			
			BufferedWriter lbw = new BufferedWriter(new FileWriter(LogPath,true));
			lbw.write("<html<head><title>Welcome to Log File " + dateFormat.format(date) + "</title></head><body bgcolor=\"DarkGray\">" );
			lbw.write("<div align=\"center\"></hr><h2>Log File</h2></hr></div>");
			
			for(int x=0; x < pFileCount; x++){
				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + lFileNames.get(x));
    			BufferedReader br = new BufferedReader(new FileReader(folder));
    			String line = "";
    			while ((line = br.readLine()) != null) {
    				lbw.write("<tr><td>");
    				lbw.write(line);
    				lbw.write("</tr></td>");
    			}
    			lbw.write("</table>");
    			br.close();
			}
			
//			bw.write("</table></hr><h3>Information Steps</h3>");
//			bw.write("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
//			bw.write("<tr><td>Date/Time</td><td>Funcation Name</td><td>Message</td><td>Status</td></tr>");
//			for(int x=0; x < iFileCount; x++){
//				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + iFileNames.get(x));
//    			BufferedReader br = new BufferedReader(new FileReader(folder));
//    			String line = "";
//    			while ((line = br.readLine()) != null) {
//    				bw.write(line);
//    			}
//    			br.close();
//			}
//			bw.write("</table></hr>");
//			bw.write("<h3>Other/Miscellaneous Steps</h3>");
//			bw.write("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
//			bw.write("<tr><td>Date/Time</td><td>Funcation Name</td><td>Message</td><td>Status</td></tr>");
//			for(int x=0; x < nFileCount; x++){
//				File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + nFileNames.get(x));
//    			BufferedReader br = new BufferedReader(new FileReader(folder));
//    			String line = "";
//    			while ((line = br.readLine()) != null) {
//    				bw.write(line);
//    			}
//    			br.close();
//			}
//			bw.write("</table></hr>");
			bw.write("<hr>");
			bw.write("<h5><a href=\"mailto:#adtfe@move.com\">Contact Us</a></h5></hr></div></body></html>");
			bw.close(); 
			
			lbw.write("<hr></body></html>");
			lbw.close();
			
			if(System.getProperty("ProjectName").toLowerCase().contains("digan")) {
				String methodName = Driver.globalValues.get("methodName");
				String reportPath = System.getProperty("user.dir") + "/target/" + RunId + "/DiganReport.html";
				String reportPathAPI = System.getProperty("user.dir") + "/target/" + RunId + "/DiganReportAPI.html";
				
				BufferedWriter rbw = new BufferedWriter(new FileWriter(reportPath,true));
				BufferedWriter rbwAPI = new BufferedWriter(new FileWriter(reportPathAPI,true));
				StringBuffer tempHTML = new StringBuffer();
				tempHTML.append("<html>\n");
				tempHTML.append("<head>\n");
				tempHTML.append("<title>" + "DIGAN Report " + "</title>\n");
				tempHTML.append("</head>\n");
				tempHTML.append("<style>\n");
				tempHTML.append("table {width: 80%; border-collapse: collapse;}\n");
				tempHTML.append("th {height: 25px; border: 1px solid black; background-color: #B0171F; color: white;}\n");
				tempHTML.append("tr:hover {background-color: #f5f5f5}\n");
				tempHTML.append("td {text-align: center; height: 25px; border: 1px solid #ddd; padding: 7px;}\n");
				tempHTML.append("</style>\n");
				tempHTML.append("<body bgcolor='#FFFFFF'>\n");
				tempHTML.append("<h2 align=\"center\"><u>DIGAN Report</u></h2>\n");
				tempHTML.append("<br><table align=\"center\">\n");
				if(methodName!=null && methodName.equals("validateReportBySegment"))
					tempHTML.append(Driver.globalValues.get("headerValue")+"\n");
				else
					tempHTML.append("<tr><th>Serial #</th><th>Reports</th><th>Selected Records</th><th>Total Pass</th><th>Total Fail</th><th>Duration</th><th>Status</th></tr>\n");
				
				BufferedReader br = null;
				List<String> reportNames = GetAllResultLogFiles(".digan");
				int counter = 0;
				for(int x=0; x < reportNames.size(); x++){
					File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + reportNames.get(x));
					br = new BufferedReader(new FileReader(folder));
					String line = "";
					while ((line = br.readLine()) != null) {
						tempHTML.append("<tr><td align=\"center\">" + ++counter + "</td>");
						tempHTML.append(line);
						tempHTML.append("</tr>");
					}
				}
				
				if(br!=null)
					br.close();
				if(rbw!=null) {
					tempHTML.append("</table>\n</body>\n</html>");
					rbw.write(tempHTML.toString());
					rbw.close();
				}
				
				StringBuffer tempHTMLAPI = new StringBuffer();
				tempHTMLAPI.append("<html>\n");
				tempHTMLAPI.append("<head>\n");
				tempHTMLAPI.append("<title>" + "DIGAN Report " + "</title>\n");
				tempHTMLAPI.append("</head>\n");
				tempHTMLAPI.append("<style>\n");
				tempHTMLAPI.append("table {width: 80%; border-collapse: collapse;}\n");
				tempHTMLAPI.append("th {height: 25px; border: 1px solid black; background-color: #B0171F; color: white;}\n");
				tempHTMLAPI.append("tr:hover {background-color: #f5f5f5}\n");
				tempHTMLAPI.append("td {text-align: center; height: 25px; border: 1px solid #ddd; padding: 7px;}\n");
				tempHTMLAPI.append("</style>\n");
				tempHTMLAPI.append("<body bgcolor='#FFFFFF'>\n");
				tempHTMLAPI.append("<h2 align=\"center\"><u>DIGAN Report</u></h2>\n");
				tempHTMLAPI.append("<br><table align=\"center\">\n");
				if(methodName!=null && methodName.equals("validateReportBySegment"))
					tempHTMLAPI.append(Driver.globalValues.get("headerValue")+"\n");
				else
					tempHTMLAPI.append("<tr><th>Serial #</th><th>Reports</th><th>Selected Records</th><th>Total Pass</th><th>Total Fail</th><th>Duration</th><th>Status</th></tr>\n");
				
				BufferedReader brAPI = null;
				List<String> reportNamesAPI = GetAllResultLogFiles(".diganapi");
				counter = 0;
				for(int x=0; x < reportNamesAPI.size(); x++){
					File folder = new File(Driver.Init.GetResultPath() + Driver.Gprops.GetRunID() + "/" + reportNamesAPI.get(x));
					brAPI = new BufferedReader(new FileReader(folder));
					String line = "";
					while ((line = brAPI.readLine()) != null) {
						tempHTMLAPI.append("<tr><td align=\"center\">" + ++counter + "</td>");
						tempHTMLAPI.append(line);
						tempHTMLAPI.append("</tr>");
					}
				}
				
				if(brAPI!=null)
					brAPI.close();
				if(rbwAPI!=null) {
					tempHTML.append("</table>\n</body>\n</html>");
					rbwAPI.write(tempHTMLAPI.toString());
					rbwAPI.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception @ ComposeProcessedLog " + e.getMessage());
			e.printStackTrace();
		}
    }
    
    /**
	 * This function will check if element exixts
	 */
    public void CheckElementExist(WebElement oWE){
    	try {
    		oWE.isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ CheckElementExist : " + e.getMessage());
		}
    }
    
    /**
     * This function will return the full name of the browser used for the test
     * @author krishna kishore g - krishnakishore.g@move.com
     * @param Browsername this is the browser keyword used in the test
     * @return this function will return the full name of the browser
     */
    public String GetBrowserName(String Browsername){
    	String bName = "";
    	try {
//    		switch (Browsername.toLowerCase().trim()) {
//			case "ff":
//				bName = "FireFox";
//				break;
//				
//			case "ie":
//				bName = "Internet Explorer";
//				break;
//			case "gc":
//				bName = "Google Chrome";
//				break;
//			case "sd":
//				bName = "Safari";
//				break;
//			case "rwd-ie":
//				bName = "Internet Explorer";
//				break;
//			case "rwd-ff":
//				bName = "Firefox";
//				break;
//			case "rwd-gc":
//				bName = "Google Chrome";
//				break;
//			default:
//				break;
//			}
    		if(Browsername.toLowerCase().trim().contains("ff")){
    			bName = "FireFox";
    		}
    		else if(Browsername.toLowerCase().trim().contains("ie8")){
    			bName = "Internet Explorer 8";
    		}
    		else if(Browsername.toLowerCase().trim().contains("ie9")){
    			bName = "Internet Explorer 9";
    		}
    		else if(Browsername.toLowerCase().trim().contains("ie10")){
    			bName = "Internet Explorer 10";
    		}
    		else if(Browsername.toLowerCase().trim().contains("ie11")){
    			bName = "Internet Explorer 11";
    		}
    		else if(Browsername.toLowerCase().trim().contains("gc")){
    			bName = "Google Chrome";
    		} 
    		else if(Browsername.toLowerCase().trim().contains("sd")){
    			bName = "Safari";
    		}
    		else if(Browsername.toLowerCase().trim().contains("ie")){
    			bName = "Internet Explorer";
    		}
    		else if(Browsername.toLowerCase().trim().contains("all")){
    			bName = "Internet Explorer 8, Internet Explorer 9, Internet Explorer 10, Internet Explorer 11, Firefox, Google Chrome";
    		}
    		else if(Browsername.toLowerCase().trim().contains("droid")){
    			bName = "Android Browser";
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception @ GetBrowserName " + e.getMessage());
		}
    	return bName;
    }
    
    /**
     * This function used to get the process running status on the remote machine.
     * @author murthi
     * @param HostName
     * @param UserNameWithDomain
     * @param Password
     * @param ProcessName
     * @return true if process running else false
     */
    public boolean IsProcessRunning(String HostName,String UserNameWithDomain, String Password, String ProcessName) {
		boolean ProcessRunning=false;
		try {
			String[] arg={"tasklist","/s",HostName.trim(),"/u",UserNameWithDomain.trim(),"/p",Password.trim(),"/fi","imagename eq "+ProcessName.trim()};
			Process pr = Runtime.getRuntime().exec(arg);
			BufferedReader br =	new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String temp = br.readLine();
			while (temp != null) {
				if (temp.indexOf(ProcessName.trim()) > -1) {
					System.out.println("Process "+ProcessName+" is running on the node "+HostName.trim());
					ProcessRunning=true;
					break;
				}
				temp = br.readLine();
			}
		} catch (Exception e) {
		}
		if(!ProcessRunning){
			System.out.println("Process "+ProcessName+" is not running on the node "+HostName.trim());
		}
		return ProcessRunning;
	}
    
    
    /**
     * This function used to get the process running status on the remote machine.
     * @author murthi
     * @param HostName
     * @param UserNameWithDomain
     * @param Password
     * @param property name valid parameters are Host name, os name, os version, etc..
     * @return return the value of property 
     */
    public String GetSystemInfo(String HostName,String UserNameWithDomain, String Password, String Info) {
		String InfoValue="";
		boolean InfoFound=false;
		try {
			String[] arg={"systeminfo","/s",HostName.trim(),"/u",UserNameWithDomain.trim(),"/p",Password.trim()};
			Process pr = Runtime.getRuntime().exec(arg);
			BufferedReader br =	new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String temp = br.readLine();
			while (temp != null) {
				if (temp.toLowerCase().indexOf(Info.trim().toLowerCase()) > -1) {
					//System.out.println(temp);
					InfoFound=true;
					String[] InfoArr=temp.split(":");
					InfoValue=InfoArr[1].trim();
					break;
				}
				temp = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(!InfoFound){
			System.out.println("Unable to get the property "+Info+" value. please check property name.");
		}
		return InfoValue;
	}
    
    public void CreateIndexFile(){
    	String RunId = Driver.Gprops.GetRunID();
    	try {
    		String IndexPath = System.getProperty("user.dir") + "/target/Index.html";
    		
    		
			File tsFolder = new File(System.getProperty("user.dir") + "/target/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}	
			File file = new File(IndexPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(IndexPath,true));
			bw.write("");
			bw.write("<html>");
			bw.write("<head><title>Automated Link Checker</title>");
			bw.write("</head>");
			bw.write("<frameset cols=\"30%,*\">");
			bw.write("<frame src=\"LeftIndex.html\" name=\"lwFrame\">");
			bw.write("<frame src=\"RightIndex.html\" name=\"rwFrame\">");
			bw.write("</frameset>");
			bw.write("</html>");
			bw.close();
			CreateRightIndexFile();
			CreateLeftIndexFile();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ CreateIndexFile " + e.getMessage());
		}
    }
    
    /**
     * This function will create "RightIndex.html" file under the target folder
     * this function will create the chart by processing the info from the database
     * @author krishna kishore g - krishnakishore.g@move.com
     */
    public void CreateRightIndexFile(){
    	try {
    		String[][] passfailcount = Driver.DBCon.QueryCukeResultDB("select sum(cast(passcounter as int)) PassCounter, sum(cast(failcounter as int)) FailCounter from CukeResults;");
    		for(int x=0;x<passfailcount.length;x++){
    			for(int y=0; y<passfailcount[x].length;y++){
    				System.out.println("passfailcount : ["+ x + "][" + y + "] : " + passfailcount[x][y]);
    			}
    		}
    		String RightPath = System.getProperty("user.dir") + "/target/RightIndex.html";
    		File Rightfile = new File(RightPath);
			if (Rightfile.exists()) {
				Rightfile.delete();
			}
			Rightfile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(RightPath,true));
			bw.write("");
			bw.write("<html><head>");
			bw.write("<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js\"></script>");
			bw.write("<script type=\"text/javascript\">");
			bw.write("$(function () {");
			bw.write("$('#container').highcharts({");
			bw.write("chart: {");
			bw.write("plotBackgroundColor: null,");
			bw.write("plotBorderWidth: null,");
			bw.write("plotShadow: false");
			bw.write("},");
			bw.write("title: {");
			bw.write("text: 'OverAll Automation Execution Status'");
			bw.write("},");
			bw.write("tooltip: {");
			bw.write("pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'");
			bw.write("},");
			bw.write("plotOptions: {");
			bw.write("pie: {");
			bw.write("allowPointSelect: true,");
			bw.write("cursor: 'pointer',");
			bw.write("dataLabels: {");
			bw.write("enabled: true,");
			bw.write("color: '#000000',");
			bw.write("connectorColor: '#000000',");
			bw.write("format: '<b>{point.name}</b>: {point.percentage:.1f} %'");
			bw.write("}");
			bw.write("}");
			bw.write("},");
			bw.write("series: [{");
			bw.write("type: 'pie',");
			bw.write("name: 'Execution Status',");
			bw.write("data: [");
			bw.write("['Pass_Count'," + passfailcount[0][1] + "],");
			bw.write("['Fail_Count', " + passfailcount[0][2] + "],");
			bw.write("]");
			bw.write("}]");
			bw.write("});");
			bw.write("});");
			String[][] projectnames = Driver.DBCon.QueryCukeResultDB("SELECT [projectname] FROM [MoveAutomation].[dbo].[CukeProjects];");
    		
			bw.write("$(function () {");
			bw.write("$('#projectstat').highcharts({");
			bw.write("chart: {");
			bw.write("plotBackgroundColor: null,");
			bw.write("plotBorderWidth: null,");
			bw.write("plotShadow: false");
			bw.write("},");
			bw.write("title: {");
			bw.write("text: 'Project Status'");
			bw.write("},");
			bw.write("tooltip: {");
			bw.write("pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'");
			bw.write("},");
			bw.write("plotOptions: {");
			bw.write("pie: {");
			bw.write("allowPointSelect: true,");
			bw.write("cursor: 'pointer',");
			bw.write("dataLabels: {");
			bw.write("enabled: true,");
			bw.write("color: '#000000',");
			bw.write("connectorColor: '#000000',");
			bw.write("format: '<b>{point.name}</b>: {point.percentage:.1f} %'");
			bw.write("}");
			bw.write("}");
			bw.write("},");
			bw.write("series: [{");
			bw.write("type: 'bar',");
			bw.write("name: 'Execution Status',");
			bw.write("data: [");
			for(int x=0;x<projectnames.length;x++){
    			String ProjectName = projectnames[x][1];
    			String[][] ProjectPassFail = Driver.DBCon.QueryCukeResultDB("select sum(cast(passcounter as int)) PassCounter, sum(cast(failcounter as int)) FailCounter from CukeResults Where project ='" + ProjectName + "';");
    			for(int y = 0; y < ProjectPassFail.length; y++){
    				bw.write("['" + ProjectName + "_Pass'," + passfailcount[0][1] + "],");
    				bw.write("['" + ProjectName + "_Fail', " + passfailcount[0][2] + "],");
    			}
    		}
			bw.write("]");
			bw.write("}]");
			bw.write("});");
			bw.write("});");
			bw.write("</script>");
			bw.write("</head>");
			bw.write("<body>");
			bw.write("<script src=\"http://qaz02htp900:8080/StaticAsset/highcharts.js\"></script>");
			bw.write("<script src=\"http://qaz02htp900:8080/StaticAsset/exporting.js\"></script>");
			bw.write("<div id=\"container\" style=\"max-width: 500px; height: 420px; margin: 0 auto\"></div>");
			bw.write("<div id=\"projectstat\" style=\"max-width: 500px; height: 420px; margin: 0 auto\"></div>");
			bw.write("</body></html>");
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ CreateRightIndexFile " + e.getMessage());
		}
    }
    
    
    /**
     * This function will create a left index html file and will read the "ProjectToRunID.txt" file and composes the urls and
     * adds it to the html file
     * @author krishna kishore g - krishnakishore.g@move.com
     *  
     */
    public void CreateLeftIndexFile(){
    	try {
    		List<String> ProUrls = new ArrayList<>();
    		List<String> BetaUrls = new ArrayList<>();
    		List<String> QaUrls = new ArrayList<>();
    		List<String> StableUrls = new ArrayList<>();
    		List<String> CIUrls = new ArrayList<>();
    		List<String> Othersurls = new ArrayList<>();
    		List<String> ProjectToRunID = new ArrayList<>();
    		String T1 = "";
    		// read the file
    		String ResultPath = System.getProperty("user.dir") + "/target/ProjectToRunID.txt";
    		String LeftPath = System.getProperty("user.dir") + "/target/LeftIndex.html";
    		File Leftfile = new File(LeftPath);
			if (Leftfile.exists()) {
				Leftfile.delete();
			}
			Leftfile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(Leftfile,true));
			bw.newLine();
    		File file = new File(ResultPath);
    		BufferedReader br = new BufferedReader(new FileReader(file));
			while ((T1 = br.readLine()) != null) {
				Driver.CUtil.WriteResults(T1, "CreateLeftIndexFile");
				ProjectToRunID.add(T1);
			}
			br.close();
			//construct the links
    		String[][] projectnames = Driver.DBCon.QueryCukeResultDB("SELECT [projectname] FROM [MoveAutomation].[dbo].[CukeProjects];");
    		for(int x=0;x<projectnames.length;x++){
    			for(int y=0; y<projectnames[x].length;y++){
    				System.out.println("Project Name : ["+ x + "][" + y + "] : " + projectnames[x][y]);
    			}
    		}
    		bw.write("<html><body bgcolor=\"Grey\"><ol>");
    		for (int a=0; a<projectnames.length; a++){
    			String Projectname = projectnames[a][1];
    			bw.write("<li>" + Projectname.toUpperCase() + "<ul>");
    			for(int b=0; b<ProjectToRunID.size(); b++){
    				String Line = ProjectToRunID.get(b);
    				String[] LData = Line.split(",");
    				if(Line.toLowerCase().trim().contains(Projectname.toLowerCase().trim())){
    					if(Line.toLowerCase().trim().contains("prod")){
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						ProUrls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    					else if(Line.toLowerCase().trim().contains("beta")){
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						BetaUrls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    					else if(Line.toLowerCase().trim().contains("qa")){
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						QaUrls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    					else if(Line.toLowerCase().trim().contains("stable")){
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						StableUrls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    					else if(Line.toLowerCase().trim().contains("ci")){
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						CIUrls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    					else{
    						Driver.CUtil.WriteResults(LData[1]+"/feature-overview.html", "CreateLeftIndexFile");
    						Othersurls.add("<a href=\"" + LData[1] + "/feature-overview.html\" target=\"rwFrame\">" + LData[1] + "</a>");
    					}
    				}
    			}
    			//write to the left index file
    			bw.write("<li>Production<ul>");
    			for(int c = 0; c < ProUrls.size(); c++){
    				bw.write("<li>" + ProUrls.get(c) + "</li>");
    			}
    			bw.write("</ul></li><li>Beta<ul>");
    			for(int c = 0; c < BetaUrls.size(); c++){
    				bw.write("<li>" + BetaUrls.get(c) + "</li>");
    			}
    			bw.write("</ul></li><li>QA<ul>");
    			for(int c = 0; c < QaUrls.size(); c++){
    				bw.write("<li>" + QaUrls.get(c) + "</li>");
    			}
    			bw.write("</ul></li><li>Stable<ul>");
    			for(int c = 0; c < StableUrls.size(); c++){
    				bw.write("<li>" + StableUrls.get(c) + "</li>");
    			}
    			bw.write("</ul></li><li>CI<ul>");
    			for(int c = 0; c < CIUrls.size(); c++){
    				bw.write("<li>" + CIUrls.get(c) + "</li>");
    			}
    			bw.write("</ul></li><li>Others<ul>");
    			for(int c = 0; c < Othersurls.size(); c++){
    				bw.write("<li>" + Othersurls.get(c) + "</li>");
    			}
    			bw.write("</ul></li></ul></li>");
    		}
    		bw.write("");
    		bw.write("</ol></body></html>");
//    		bw.newLine();
    		bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ CreateRightIndexFile " + e.getMessage());
		}
    }
    
    /**
     * This function will create a file "ProjectToRunID.txt" in the target folder and will write the project name and the corresponding run id
     * @author krishna kishore G - krishnakishore.g@move.com
     * @param ProjectName
     * @param RunID
     */
    public void AssociateRunIDToProject(String ProjectName, String RunID, String Environment){
    	try {
    		String ResultPath = System.getProperty("user.dir") + "/target/ProjectToRunID.txt";
			File tsFolder = new File(System.getProperty("user.dir") + "/target/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}	
			File file = new File(ResultPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(ResultPath,true));
			bw.write(ProjectName + "," + RunID + ","+ Environment);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ AssociateRunIDToProject " + e.getMessage());
		}
    }
    
    /**
     * This function will open the file "UniqueProject.txt" check if the project name is available else it will write the project name to the file 
     * @author krishnakg
     * @param ProjectName
     */
    public void WriteUniqueProjectName(String ProjectName){
    	try {
    		boolean IsFileExist = false, IsProjectAvailable = false;
    		String ResultPath = System.getProperty("user.dir") + "/target/UniqueProject.txt";
			File tsFolder = new File(System.getProperty("user.dir") + "/target/");
			if(!tsFolder.exists()){
				tsFolder.mkdirs();
			}	
			File file = new File(ResultPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			else{
				IsFileExist = true;
			}
			String Text = "", T1 = "";
			if(IsFileExist){
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((T1 = br.readLine()) != null) {
					Text = T1;
					if(Text.equalsIgnoreCase(ProjectName)){
						IsProjectAvailable = true;
					}
				}
				br.close();
			}
			
			if(!IsProjectAvailable){
				BufferedWriter bw = new BufferedWriter(new FileWriter(ResultPath,true));
				bw.write(ProjectName);
				bw.newLine();
				bw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error @ WriteUniqueProjectName " + e.getMessage());
		}
    }
    
    
    /**
     * this function will take any type of array and converts it to a comma (,) seperated string value and returns the same.
     * @author krishna kishore g - krishnakishore.g@move.com
     * @param values
     * @return
     */
    public String AnyArrayToString(Object[] values){
    	String returnvalues = "";
    	try {
			for(int x=0; x< values.length; x++){
				if(x==0){
					returnvalues = String.valueOf(values[x]);
				}
				else{
					returnvalues += "," + String.valueOf(values[x]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	return returnvalues;
    }

    /***
     * This function will read the static data which is saved under the framework <br>
     * Date : Feb 24, 2014; Time : 1:46:47 PM
     * @author krishna kishore g - krishnakishore.g@move.com
     * @param Component
     * @param FileName
     */
    public List<String> ReadGlobalStaticData(String Component, String FileName){
    	String FUnction_Name = "ReadGlobalStaticData";
    	String bPath = Driver.Init.GetBasePath();
    	String ProjName = System.getProperty("ProjectName").trim().toLowerCase();
    	String Line = "";
    	List<String> FileLines = new ArrayList<>();
    	try {
			String AbsPath = bPath + "framework/GlobalStaticData/" + Component + "/" + FileName;
			//Message,functionname,status=> Done,Pass,Fail,Warning,N/A Feb 24, 2014 3:03:59 PM
			Driver.CUtil.WriteResults("Abs File Path = " + AbsPath, FUnction_Name, "info");
			File objFile = new File(AbsPath);
			BufferedReader ofileReader = new BufferedReader(new FileReader(objFile));
			while ((Line = ofileReader.readLine()) != null)
			{
				FileLines.add(Line);
			}
			ofileReader.close();
			//Message,functionname,status=> Done,Pass,Fail,Warning,N/A Feb 24, 2014 3:05:22 PM
			Driver.CUtil.WriteResults("processed the static data file and returning back to the caller");
		} catch (Exception ex) {
			//Message,functionname,status=> Done,Pass,Fail,Warning,N/A Feb 24, 2014 3:05:51 PM
			Driver.CUtil.WriteResults("Unknown exception, message = " + ex.getMessage(), FUnction_Name, "fail");
			ex.printStackTrace();
			FileLines = null;
		}
    	return FileLines;
    }
    
    /**
     * This function will read the state file and will give the DB partition where the info is stored
     * @author krishna kishore g - krishnakishore.g@move.com 
     * @param StateCode
     */
    public String[] GetStatePartitionInfo(String StateCode){
    	String bPath = Driver.Init.GetBasePath();
    	String Line ="";
    	String Partition = "";
    	String BaseUrl = Driver.Init.GetBaseURL();
    	String AbsStatePath = "";
    	String[] PartitionData = null;
    	String pBuilder = "";
    	String[] PartDBDetails = null;
    	try {
    		// the below code will get the partition information
    		String AbsPath = bPath + "framework/GlobalStaticData/CityState/State";
    		File objFile = new File(AbsPath);
			BufferedReader ofileReader = new BufferedReader(new FileReader(objFile));
			while ((Line = ofileReader.readLine()) != null)
			{
				String[] Data = Line.split(",");
				if(Data[1].trim().equalsIgnoreCase(StateCode)){
					Partition = Data[2]; 
					break;
				}
			}
			ofileReader.close();
			//below code will get the db details corresponding to the partition
			Line = "";
			AbsStatePath = bPath + "framework/GlobalStaticData/CityState/Partition";
    		File oStateFile = new File(AbsStatePath);
			BufferedReader oStateReader = new BufferedReader(new FileReader(oStateFile));
			while ((Line = oStateReader.readLine()) != null)
			{
				String[] ParData = Line.split("\\|");
				if(ParData[0].trim().equalsIgnoreCase(Partition)){
					PartitionData = ParData; 
					break;
				}
			}
			oStateReader.close();
			if ((BaseUrl.toLowerCase().trim().startsWith("beta.www.")) || (BaseUrl.toLowerCase().trim().startsWith("www."))){
				pBuilder = PartitionData[1] + "|" + PartitionData[3] + "|" + PartitionData[4]; 
			}
			else{
				pBuilder = PartitionData[2] + "|" + PartitionData[3] + "|" + PartitionData[4];
			}
			PartDBDetails = pBuilder.split("\\|");
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
			PartDBDetails = null;
		}
    	return PartDBDetails;
    }
    
    /**
     * This function will create the html for Zillow, Trulia comparison with RDC
     * @author Ditimoni Borpatra Gohain - DBorpatra@move.com 
     * @param StateCode
     * @throws IOException 
     */
    public String createRTZHtmlReport() throws IOException
    {
    	String head="<html><head><title>RDC, Zillow and Trulia Comparison</title></head><body><h4>RDC, Zillow and Trulia Comparison</h4><p align=center><table border=\"2\" cellspacing=\"5\" cellpadding=\"5\"><tr><td>City/State</td><td>RDC</td><td>Zillo</td><td>Trulia</td><td>Status</td><td>% Difference in Zillow</td><td>% Difference in Trulia</td></tr>";
    	String tail="</table></p></body></html>";
    	String line="";String directory="";
    	 BufferedReader br=null;
	        try{
	        	String fileName=System.getProperty("FetFileName");
	    		fileName=fileName.replace(".feature", "");
	    		fileName=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+ "/"+fileName+".txt";
	        	//String fileName=Driver.Gprops.getTextFilePath();
//		        FileReader fr= new FileReader(fileName);
//		        br= new BufferedReader(fr);
//		        String st=br.readLine();
//		        String s= br.readLine();
//		        Text t=new Text();
//		        int a=t.TotalLine(fileName);
		        String lin="<h4><u>RDC, Zillow and Trulia Comparison</u></h4><br><table border=\"2\" cellspacing=\"5\" cellpadding=\"5\"><tr><td>City/State</td><td>RDC</td><td>Zillo</td><td>Trulia</td><td>Status</td><td>% Difference in Zillow</td><td>% Difference in Trulia</td></tr>";
//		        for(int i=1;i<=a;i++)
//		        {
//		        	String d=t.textInLine(i);
//		        	if(t.textInLine(i).isEmpty()){}
//		        	else{
//		        		String ln=t.textInLine(i);
//		        		if(ln.contains("~"))
//		        		{
//		        			String[] strArr=ln.split("~");
//		        			if(strArr[4].trim().toLowerCase().equals("passed"))
//		        			{
//		        				lin="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td>"+strArr[2]+"</td><td>"+strArr[3]+"</td><td><font color=darkgreen>"+strArr[4]+"</font></td><td>"+strArr[5]+"</td><td>"+strArr[6]+"</td></tr>";
//		        			}else if(strArr[4].trim().toLowerCase().equals("failed"))
//		        			{
//		        				lin="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td>"+strArr[2]+"</td><td>"+strArr[3]+"</td><td><font color=red>"+strArr[4]+"</font></td><td>"+strArr[5]+"</td><td>"+strArr[6]+"</td></tr>";
//		        			}
//		        		}
//		        		line=line+lin+"\n";
//		        	}
//		        }
		        String Line = "";
		        File testFile = new File(fileName);
				br = new BufferedReader(new FileReader(fileName));
				while ((Line = br.readLine()) != null)
				{
					String[] strArr=Line.split("~");
					if(strArr[4].trim().toLowerCase().equals("passed"))
        			{
        				lin+="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td>"+strArr[2]+"</td><td>"+strArr[3]+"</td><td><font color=darkgreen>"+strArr[4]+"</font></td><td>"+strArr[5]+"</td><td>"+strArr[6]+"</td></tr>";
        			}else if(strArr[4].trim().toLowerCase().equals("failed"))
        			{
        				lin+="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td>"+strArr[2]+"</td><td>"+strArr[3]+"</td><td><font color=red>"+strArr[4]+"</font></td><td>"+strArr[5]+"</td><td>"+strArr[6]+"</td></tr>";
        			}
					
				}
				br.close();
//		        line=head+line+tail;
				line=lin + "</table>";
		        PrintWriter htmlPrintWriter=null;
		        try {
		        	directory=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+ "/RTZ.html";
		        	File htmlFile = new File(directory);
		        	htmlPrintWriter = new PrintWriter(htmlFile);
		        	htmlPrintWriter.println(line);
		        } catch (Exception e) {
		            e.printStackTrace();
		        	} finally {
		            try {
		            	htmlPrintWriter.close();
		            	Driver.Gprops.SetHtmlValue(line);
		            	} catch (Exception e)
		            	{
		            	}
		        }
	    }catch(Exception e)
	    {
	        
	    }finally
	    {
//	        br.close();
	    }
	     System.out.println(line);   
	    return directory;
       }
    
    
    /**
     * This function will convert the json to string
     * @author Ditimoni Borpatra Gohain - DBorpatra@move.com 
     * @param Reader rd
     * @throws IOException 
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
    
    
    /**
     * This function will return json object of API query
     * @author Ditimoni Borpatra Gohain - Ditimoni.Borpatra@move.com 
     * @param String url
     * @throws IOException , JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
  	  JSONObject json=null;
  	  try {
  		  
  		  javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier( 
  					new javax.net.ssl.HostnameVerifier()
  					{ 
  						public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) 
  					{ 
  						if (hostname.equals("qa.api.move.com")) 
  						{ return true; 
  						} 
  						return false; 
  						} 
  					});
  	// Create a trust manager that does not validate certificate chains
  	    final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
  	        @Override
  	        public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
  	        }
  	        @Override
  	        public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
  	        }
  	        @Override
  	        public X509Certificate[] getAcceptedIssuers() {
  	            return null;
  	        }
  	    } };
  	    
  	    // Install the all-trusting trust manager
  	    final SSLContext sslContext = SSLContext.getInstance( "SSL" );
  	    sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
  	    // Create an ssl socket factory with our all-trusting manager
  	    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
  	    
  	    
  	    // All set up, we can get a resource through https now:
  	    final URLConnection urlCon = new URL( url ).openConnection();
  	    // Tell the url connection object to use our socket factory which bypasses security checks
  	    ( (HttpsURLConnection) urlCon ).setSSLSocketFactory( sslSocketFactory );
  	    
  	    final InputStream input = urlCon.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        json = new JSONObject(jsonText);
        input.close();
        //return json;
      } catch (NoSuchAlgorithmException e) {
  		e.printStackTrace();
  	} catch (KeyManagementException e) {
  		e.printStackTrace();
  	}
  	return json;
    }
    
    
    /**
     * This function will create the html for linking module
     * @author Ditimoni Borpatra Gohain - Ditimoni.Borpatra@move.com 
     * @throws IOException 
     */
    public String createSEOHtmlReport() throws IOException
    {
    	String head="<html><head><title>Validates SEO content</title></head><body><h4>Validates SEO content</h4><p align=center><table border=\"2\" cellspacing=\"5\"cellpadding=\"5\"><tr><td>Link Name</td><td>Href</td><td>Status</td></tr>";
    	String tail="</table></p></body></html>";
    	String line="";String directory="";
    	 BufferedReader br=null;
	        try{
//	        	String fileName=System.getProperty("FetFileName");
//	    		fileName=fileName.replace(".feature", "");
//	    		fileName=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID();
		        String lin="<h4><u>Validates SEO content</u></h4><br><table border=\"2\" cellspacing=\"5\" cellpadding=\"5\"><tr><td>Links Name</td><td>Href</td><td>Status</td></tr>";

		        String Line = "";
		        String linkFile=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID();
				File folder = new File(linkFile);
				File[] listOfFiles = folder.listFiles();
				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	String a=file.getName();
				    	if(file.getName().contains("LinkingModule"))
				    	{
				    		File testFile = new File(file+"");
				    		br = new BufferedReader(new FileReader(file+""));
				    		while ((Line = br.readLine()) != null)
							{
								String[] strArr=Line.split("~");
								if(strArr[2].trim().toLowerCase().equals("passed"))
			        			{
			        				lin+="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td><font color=darkgreen>"+strArr[2]+"</font></td></tr>";
			        			}else if(strArr[2].trim().toLowerCase().equals("failed"))
			        			{
			        				lin+="<tr><td>"+strArr[0]+"</td><td>"+strArr[1]+"</td><td><font color=red>"+strArr[2]+"</font></td></tr>";
			        			}
								
							}
				    	}
				    }
				}
				br.close();
				line=lin + "</table>";
		        PrintWriter htmlPrintWriter=null;
		        try {
		        	directory=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+ "/LinkingModule.html";
		        	File htmlFile = new File(directory);
		        	htmlPrintWriter = new PrintWriter(htmlFile);
		        	htmlPrintWriter.println(line);
		        } catch (Exception e) {
		            e.printStackTrace();
		        	} finally {
		            try {
		            	htmlPrintWriter.close();
		            	Driver.Gprops.SetHtmlValue(line);
		            	} catch (Exception e)
		            	{
		            	}
		        }
	    }catch(Exception e)
	    {
	        
	    }finally
	    {
//	        br.close();
	    }
	     System.out.println(line);   
	    return directory;
       }

    
  //*******************************************************************************************************************************/
	  /**
	  * This function will return a random integer between the specified range
	  * @creationdate 06/08/2015   
	  * @author Johnsi Rani <johnsirani.sivakumar@move.com>
	  * @param maximum: Pass the maximum integer 
	  * @param minimum: Pass the minimum integer
	  * @throws
	  * 
	  **/
	//*******************************************************************************************************************************/
    public static int getRandomInteger(int maximum, int minimum){
    	int randomInt = 0;
    	randomInt = ((int) (Math.random()*(maximum - minimum))) + minimum;
    	return randomInt;
    }
    
    
    /**
     * This function will read the Environment file to get the Database server, Database Instance, Database UserName, DataBase Password
     * and returns the value in a string array
     * @creationdate 03/16/2016
     * @author Ramachandra Patkar <ramachandra.patkar@move.com>
     * @param1 leadType eg : Seller leads or any of the property type
     * array[0] = DataBase Server
     * array[1] = instance
     * array[2] = Database Name
     * array[3] = User Name 
     * array[4] = Password
     * @return : Database server, Database Instance, Database UserName, DataBase Password
     */
    public String[] GetDataBaseDetails(String leadType){
    	String[] RetValue = null;
    	String ServerName="",InstanceName="",UserName="",Password="",DBName="";
		boolean IsInstance=false;

    	try{    		
    		
    		switch (leadType.toLowerCase()) {
				case "sellerleads":	
					//Get Database details from environment file					
					ServerName=Driver.Init.GetSellerDBServer();		    		
		    		if(IsInstance){
		    			InstanceName=Driver.Init.GetDBInstance();
		    		}
		    		DBName=Driver.Init.GetSellerDBName();
		    		UserName=Driver.Init.GetDBUserName();
		    		Password=Driver.Init.GetDBPassword();
		    		
		    		//Assign values
		    		RetValue =new String[5];
		    		RetValue[0]=ServerName;
		    		RetValue[1]=InstanceName;
		    		RetValue[2]=DBName;
		    		RetValue[3]=UserName;
		    		RetValue[4]=Password;
					 
					break;
    		}		   		   		 		
    	}
    	catch(Exception ex){
    		RetValue = null;
    		System.out.println("Error @ GetDBDetails : "+ ex.getMessage());
    	}
    	return RetValue;
    }
    
	/**
	 * This function will display the message in report withot accepting the status ie Pass/Fail
	 * @author Sonu
	 * @creationdate 04/14/2016
	 * @param Message
	 *            This parameter will hold the message that needs to be writtem
	 *            to the log file
	 */
	public void WriteResults(String message) {
		String msg = message + "<br>";
		try {
			if (Driver.Init.GetResultPath().trim().equals("")) {
				return;
			}
			String logPath = System.getProperty("user.dir") + "/target/"
					+ Driver.Gprops.GetRunID() + "/Log_For_Thread_"
					+ Thread.currentThread().getName() + ".html";
			String sLogPath = Driver.Init.GetResultPath()
					+ Driver.Gprops.GetRunID() + "/SLog_For_Thread_"
					+ Thread.currentThread().getName() + ".slog";
			File tsFolder = new File(Driver.Init.GetResultPath()
					+ Driver.Gprops.GetRunID() + "/");
			if (!tsFolder.exists()) {
				tsFolder.mkdirs();
			}
			File logFile = new File(logPath);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			File sLogFile = new File(sLogPath);
			if (!sLogFile.exists()) {
				sLogFile.createNewFile();
			}
			String msglower = msg.toLowerCase();
			if (Driver.Gprops.GetWebDriver() != null
					&& !msglower.contains("creating webelement")) {
				try {
					Scenario sc = (Scenario) Driver.mapper.get(Thread
							.currentThread().getName() + "Scenario");
					sc.write("<div><span>"+msg+"</div></span>");
				} catch (Exception e) {
					System.out.println("Unable to write anything in report");
				}
			}
			System.out.println(msg);
			BufferedWriter logWritter = new BufferedWriter(new FileWriter(
					logFile, true));
			logWritter.close();
		} catch (Exception e) {
			System.out.println("Unable to write anything in report");
		}
	}
}
