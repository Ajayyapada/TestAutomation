package Lib.Common;
import java.util.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import Lib.Controller.Driver;

/**
 * This is the base property class where all the environment values are processed and set to property<br>
 * <font color="red"><b>If any changed are required please mail the automation team (#adtfe@move.com)<br>
 * This Class is locked from any kind of modifications</b></font><br><br>
 * <font color="blue"><i>Note:<br>
 * ===<br>
 * <t> All action that are related to the Environment file is coded in this file</t></i></font>
 * @author	Krishna Kishore G	krishnakishore.g@move.com
 * @version 1.1
 * 
 */
public class TestProperties{

	//Private Declaration Done here
	private String BasePath = "";
	private String BaseURL = "";
	private String MongoDatabase = "";
	private String IsPwdEncrypted = "";
	private String DBName = "";
	private String DBServer = "";
	private boolean DBIntegratedSecurity = true;
	private String AUTBrowser = "";
	private String DBUserName = "";
	private String DBPassword = "";
	private String LogFolder = "";
	private String ResultFolder = "";
	private String ObjectPropsFolder = "";
	private String EnvFileFolder = "";
	private String TempFolder = "";
	private boolean OS64Bit = false;
	private boolean IsInstance = false;
	private String DBInstance = "";
	private String GridServer = "";
	private String MailAddress = "";
	private String MailID = "";
	private String PdsVer="";
	private WebDriver otDriver = null;
	private String[][] DBRetValue = null;
	private String StaticAsset = "";
	private String deviceOrientation = null;
	private String SellerDBServer = "";
	private String SellerDBName = "";


	// DBUserName 
	public String GetStaticAssetPath(){return StaticAsset;}
	public void SetStaticAssetPath(String StaticAsset){ this.StaticAsset = StaticAsset;}

	// WebDriver Object 
	public String[][] GetDBReturnValue(){return DBRetValue;}
	public void SetDBReturnValue(String[][] DBRetValue){ this.DBRetValue = DBRetValue;}

	// WebDriver Object 
	public WebDriver GetWebDriver(){return otDriver;}
	public void SetWebDriver(WebDriver otDriver){ this.otDriver = otDriver;}

	// Mail Address 
	public String GetMailAddress(){return MailAddress;}
	public void SetMailAddress(String MailAddress){ this.MailAddress = MailAddress;}
	
	// Mail Address for production By-Vijendra
	public String GetMailID(){return MailID;}
	public void SetMailID(String MailID){ this.MailID = MailID;}

	// DBUserName 
	public String GetDBUserName(){return DBUserName;}
	public void SetDBUserName(String DBUserName){ this.DBUserName = DBUserName;}

	// DBPassword 
	public String GetDBPassword(){return DBPassword;}
	public void SetDBPassword(String DBPassword){ this.DBPassword = DBPassword;}

	// DBUserName 
	public String GetDBInstance(){return DBInstance;}
	public void SetDBInstance(String DBInstance){ this.DBInstance = DBInstance;}

	// Grid2Server 
	public String GetGrid2Server(){return GridServer;}
	public void SetGrid2Server(String GridServer){ this.GridServer = GridServer;}
	//IsDBInstance
	public Boolean GetIsInstance(){return IsInstance;}
	public void SetIsInstance(boolean IsInstance){ this.IsInstance = IsInstance;}
	//OS64Bit
	public Boolean GetOS64Bit(){return OS64Bit;}
	public void SetOS64Bit(boolean OS64Bit){ this.OS64Bit = OS64Bit;}
	private Map<String,String> MoveKVP = null;
	//This Property is for BasePath
	public String GetBasePath(){return BasePath;}
	public void SetBasePath(String BasePath){ this.BasePath = BasePath;}
	// Base URL
	public String GetBaseURL(){return BaseURL;}
	public void SetBaseURL(String BaseURL){ this.BaseURL = BaseURL;}
	// MongoDatabase
	public String GetMongoDatabase(){return MongoDatabase;}
	public void SetMongoDatabase(String value){MongoDatabase=value;}
	// Result Path
	public String GetResultPath(){return ResultFolder;}
	public void SetResultPath(String ResultFolder){ this.ResultFolder = ResultFolder;}
	//IsPwdEncrypted
	public String GetPwdEcnryption(){return IsPwdEncrypted;}
	public void SetPwdEcnryption(String IsPwdEncrypted){ this.IsPwdEncrypted = IsPwdEncrypted;}
	//DBName
	public String GetDBName(){return DBName;}
	public void SetDBName(String DBName){ this.DBName = DBName;}
	//DBServer
	public String GetDBServer(){return DBServer;}
	public void SetDBServer(String DBServer){ this.DBServer = DBServer;}

	//SellerDBServer
	public String GetSellerDBServer(){return SellerDBServer;}
	public void SetSellerDBServer(String SellerDBServer){ this.SellerDBServer = SellerDBServer;}
	//SellerDBName
	public String GetSellerDBName(){return SellerDBName;}
	public void SetSellerDBName(String SellerDBName){ this.SellerDBName = SellerDBName;}

	// DBIntegratedSecurity
	public boolean GetDatabaseIntegratedSecurity(){ return DBIntegratedSecurity; }
	public void SetDatabaseIntegratedSecurity(boolean value){DBIntegratedSecurity = value;}
	//TempFolder
	public String GetTempFolder(){return TempFolder;}
	public void SetTempFolder(String TempFolder){ this.TempFolder = TempFolder;}
	//Environment Folder
	public String GetEnvironmentFolder(){return EnvFileFolder;}
	public void SetEnvironmentFolder(String EnvFileFolder){ this.EnvFileFolder = EnvFileFolder;}
	//AUT Browser
	public String GetAUTBrowser(){return AUTBrowser;}
	public void SetAUTBrowser(String AUTBrowser){ this.AUTBrowser = AUTBrowser;}
	//OR File Path
	public String GetORPath(){return ObjectPropsFolder;}
	//public void SetORPath(String ORPath){ this.ObjectPropsFolder = ORPath;}
	// This Property contains the dictionary
	public Map<String,String> GetMoveKVP(){return MoveKVP;}
	public void SetMoveKVP(Map<String,String> KVP){ this.MoveKVP = KVP;}
	//PDS version
	public String GetPdsVer(){return PdsVer;}
	public void SetPdsVer(String PdsVer){ this.PdsVer = PdsVer;}

	//Device Orientation
	public String GetDeviceOrientation(){return deviceOrientation;}
	public void SetDeviceOrientation(String AUTBrowser){ this.deviceOrientation = deviceOrientation;}

	private HashMap<String, String> Environment = new HashMap<>();
	public HashMap<String, String> GetEnvironment(){return Environment;}
	public void SetEnvironment(HashMap<String, String> Environment){ this.Environment = Environment;}
	public void AddToEnvironment(String Key, String Value){this.Environment.put(Key, Value);}
	public String GetFromEnvironment(String Key){return this.Environment.get(Key.trim().toLowerCase());}

	/**
	 * This is the initial function that loads the environment file to the system.
	 * Environment file contains all the basic information that is required to initiate the test execution.<br>
	 * <font color="red"><b>If any changed are required please mail the automation team (#adtfe@move.com)<br>
	 * This Method is locked from any kind of modifications</b></font>
	 * @author Krishna Kishore G - krishnakishore.g@move.com
	 * @exception SAXParseException
	 * @exception SAXException
	 * @exception Throwable
	 * 
	 */
	public void Genesis()
	{
		try
		{
			BasePath = System.getProperty("user.dir") + "/";
			System.out.println(BasePath);
			// Code added by KK on 12/12/13 - this code will help run any future projects to be executed from eclipse
			String ProjectName = System.getProperty("ProjectName").toLowerCase().trim();
			if(BasePath.toLowerCase().trim().contains(ProjectName)){
				//BasePath = BasePath.trim().replace(System.getProperty("ProjectName").trim()+"/", "");
			}
			Driver.CUtil.WriteResults("Base path used in the test: " + BasePath, "Genesis", "");
			LogFolder = BasePath + "framework/Log/";
			ResultFolder = BasePath + "framework/Result/";
			ObjectPropsFolder = BasePath + "framework/ObjectProps/";
			EnvFileFolder = BasePath + "framework/Environment/";
			TempFolder = BasePath + "framework/Temp/";
			StaticAsset = BasePath + "framework/StaticData/";
			Driver.CUtil.WriteResults("LogFolder path used in the test: " + LogFolder, "Genesis", "");
			Driver.CUtil.WriteResults("ResultFolder path used in the test: " + ResultFolder, "Genesis", "");
			Driver.CUtil.WriteResults("ObjectPropsFolder path used in the test: " + ObjectPropsFolder, "Genesis", "");
			Driver.CUtil.WriteResults("EnvFileFolder path used in the test: " + EnvFileFolder, "Genesis", "");
			Driver.CUtil.WriteResults("TempFolder path used in the test: " + TempFolder, "Genesis", "");
			Driver.CUtil.WriteResults("StaticAsset path used in the test: " + StaticAsset, "Genesis", "");

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			System.out.println("Environment File:"+System.getProperty("EnvFileName"));
			if(System.getProperty("EnvFileName").toLowerCase().trim().contains(".xml")){
				Document doc = null;
				if (System.getProperty("EnvFileName") == null){
					doc = docBuilder.parse (new File(EnvFileFolder + "Environment.xml"));
				}
				else{
					doc = docBuilder.parse (new File(EnvFileFolder + System.getProperty("EnvFileName")));
				}
				Driver.CUtil.WriteResults("Environment file path used in the test: " + EnvFileFolder + System.getProperty("EnvFileName"), "Genesis", "");
				NodeList nNameList = doc.getElementsByTagName("Name");
				NodeList nValueList = doc.getElementsByTagName("Value");
				for (int LoopA=0;LoopA<nNameList.getLength();LoopA++)
				{
					try{
						Driver.CUtil.WriteResults("Environment value " + nNameList.item(LoopA).getFirstChild().getNodeValue() + " = " + nValueList.item(LoopA).getFirstChild().getNodeValue(), "Genesis", "");
						AddToEnvironment(nNameList.item(LoopA).getFirstChild().getNodeValue().trim().toLowerCase(), nValueList.item(LoopA).getFirstChild().getNodeValue());
					}
					catch(NullPointerException ne){
						Driver.CUtil.WriteResults("Environment value " + nNameList.item(LoopA).getFirstChild().getNodeValue() + " = " + " ", "Genesis", "");
						AddToEnvironment(nNameList.item(LoopA).getFirstChild().getNodeValue().trim().toLowerCase(), " ");
					}
				}
			}
			else{
				String Query = "SELECT [varname], [varvalue] FROM [MoveAutomation].[dbo].[Environment] where [envfilename] = '" + System.getProperty("EnvFileName") + "' and [IsActive] = 'yes';";
				DBConnection dbCon = new DBConnection();
				String[][] Envi = dbCon.QueryCukeResultDB(Query);
				for(int x=0; x < Envi.length; x++){
					Driver.CUtil.WriteResults("Environment value " + Envi[x][1] + " = " + Envi[x][2], "Genesis", "");
					AddToEnvironment(Envi[x][1].trim().toLowerCase(), Envi[x][2]);
				}
			}
			BaseURL = GetFromEnvironment("baseurl");
			MongoDatabase = GetFromEnvironment("mongodatabase");
			IsPwdEncrypted = GetFromEnvironment("blnisdbpwdencrypted");
			DBServer = GetFromEnvironment("databaseserver");
			DBIntegratedSecurity = Boolean.parseBoolean(GetFromEnvironment("databaseintegratedsecurity"));
			DBName = GetFromEnvironment("databasename");
			AUTBrowser = GetFromEnvironment("webbrowser");
			deviceOrientation = GetFromEnvironment("deviceorientation");
			OS64Bit = Boolean.parseBoolean(GetFromEnvironment("os64bit"));
			GridServer = GetFromEnvironment("grid2server");
			DBUserName = GetFromEnvironment("databaselogin");
			DBPassword = GetFromEnvironment("databasepassword");
			DBInstance = GetFromEnvironment("dbinstance");
			IsInstance = Boolean.parseBoolean(GetFromEnvironment("isinstance"));
			MailAddress = GetFromEnvironment("email");
			MailID = GetFromEnvironment("Demoemail");
			PdsVer = GetFromEnvironment("psver");
			SellerDBServer = GetFromEnvironment("SellerDatabaseServer");
			SellerDBName = GetFromEnvironment("SellerDatabaseName");

			Driver.CUtil.WriteResults("All the environment value added to the system...","Genesis", "");
		}
		catch(SAXParseException err)
		{
			System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			Driver.CUtil.WriteResults(err.getMessage (), "Genesis", "fail");
		}
		catch (SAXException e) 
		{
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();
			Driver.CUtil.WriteResults(e.getMessage (), "Genesis", "fail");
		}
		catch (Throwable t) 
		{
			t.printStackTrace ();
			Driver.CUtil.WriteResults(t.getMessage (), "Genesis", "fail");
		}
	}


}
