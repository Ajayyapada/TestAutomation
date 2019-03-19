package Lib.Controller;

import gherkin.formatter.Formatter;
import cucumber.runtime.model.CucumberFeature;
import gherkin.formatter.Reporter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.ReportInformation;
import net.masterthought.cucumber.ReportParser;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.util.Status;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.gson.JsonSyntaxException;

import cucumber.api.cli.Main;
import Lib.Common.*;
import defFiles.*;
import static java.util.Arrays.asList;

import java.util.*;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;


/**
 * This is the Driver Class in the testScript package, this is the base class for all the tests.<br> 
 * <font size="3" color="red"><strong><u>Caution:</u> Modification to this class will impact the overall framework & test, so please refrain from this class</strong></font>
 * @author Krishna Kishore G	krishnakishore.g@move.com
 *
 */
public class Driver extends Main {
	public static TestProperties Init = null;
	public static CommonUtil CUtil = null;
	public static CommonWeb CWeb = null;
	public static WebCtrlOperations WebCtrl = null;
	public static WebDriver otDriver = null;
	public static HtmlReport htmlRpt = null;
	public static String ResultPath = null;
	//public static sfdc appRDC = null;
	public static DBConnection DBCon = null;
	public static ExcelReport ExlRpt = null;
	public static ResultMailer Mailer = null;
	public static String ExcelResultPath=null;
	public static Text txt=null;
	public static int iteration=1;
	public static GlobalProperties Gprops = null;
	public static List<Throwable> T = null;
	public static RuntimeOptions runtimeOptions = null;

	public static Runtime runtime = null;
	public static CucumberFeature cucumberfeature = null;
	public static String FEFileName=null;
	public static boolean ExecuteCucke = true;
	public static HashMap<String,Object> mapper=new HashMap<String, Object>();
	public static HashMap<String,String> globalValues=new HashMap<String, String>();
	public static HashMap<String,Map> globalValuesMap=new HashMap<String, Map>();
	public static HashMap<String,List> globalValuesList=new HashMap<String, List>();
	public static HashMap<String,WebElement> globalValuesWebElement=new HashMap<String, WebElement>();

	/**
	 * This function will return the current function OR test under execution, this will get the name from the stack
	 * @author Krishna Kishore G
	 * @return	this function will return the name of the caller function
	 */
	public static String MethodName()
	{
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		//StackTraceElement e = stacktrace[2];//coz 0th will be getStackTrace so 1st
		String methodName = "";
		for(int x=3;x<stacktrace.length;x++){
			if(x==0){
				methodName = stacktrace[x].toString() + "<br>";
			}
			else{
				methodName = stacktrace[x].toString() + "<br>" + methodName;
			}
		}
		//String methodName = e.getMethodName();
		return methodName;
	}


	/**
	 * @author krishnakg
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void createConfigTextFile() throws IOException, InterruptedException
	{
		try{
			String absFilePath = Init.GetTempFolder() + Gprops.GetRunID() + ".config";
			File confFile = new File(absFilePath);
			if (confFile.exists()) 
			{
				confFile.createNewFile();
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @author krishnakg
	 * @param FeatureFiles
	 * @param Filters
	 * @return
	 */
	public static void WriteToConfigFile(String Message){
		try{
			String absFilePath = Init.GetTempFolder() + Gprops.GetRunID() + ".config";
			BufferedWriter Writter = new BufferedWriter(new FileWriter(absFilePath));
			Writter.write(Message);
			Writter.close();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] argv) throws Throwable {
		try{

			System.out.println("--START-- Printing the Argument Data in thread name " + Thread.currentThread().getName());
			for(int a=0; a<argv.length;a++){
				System.out.println("Thread Name = " + Thread.currentThread().getName() + " and arg[" + a + "] value is " + argv[a]);
				if(argv[a].contains(".feature")){
					String[] threadfeature= argv[a].split("\\\\");
					System.out.println(Thread.currentThread().getName() +"-"+threadfeature[threadfeature.length-1]);
					Driver.globalValues.put(Thread.currentThread().getName()+"featurefile",threadfeature[threadfeature.length-1]);				
				}
			}
			System.out.println("--END-- Printing the Argument Data  in thread name " + Thread.currentThread().getName());
			//			Driver.Init.SetAUTBrowser(argv[argv.length-1]);
			//			argv = ProcessArray(argv);
			//			Logger.getRootLogger().removeAllAppenders();
			//			Logger.getRootLogger().addAppender(new NullAppender());
			//			setUp();
			globalValues.put(Thread.currentThread().getName()+"RunID", String.valueOf(System.currentTimeMillis()));
			globalValues.put(Thread.currentThread().getName()+"WDWaitCount", String.valueOf("0"));
			String[] args=null;
			String TagName=System.getProperty("Tag");//get the tag name
			System.out.println(TagName);
			String FetFileName=System.getProperty("FetFileName");//get the feature file name
			System.out.println(FetFileName);
			String BasePath = Driver.Init.GetBasePath();//System.getProperty("user.dir");
			Driver.CUtil.WriteResults("Base path for the test = " + BasePath, "main", "info");
			Driver.CUtil.WriteResults("Current Thread Name for the test is \"" + Thread.currentThread().getName()+"\"", "main", "info");
			//			Driver.CUtil.AssociateRunIDToProject(System.getProperty("ProjectName"), Gprops.GetRunID(), System.getProperty("EnvFileName")); // added by KK - 05/12/2013 - this function will write the project name and the current run id
			//			Driver.CUtil.WriteUniqueProjectName(System.getProperty("ProjectName")); // added by KK - 05/12/2013 - this function will add the project name
			if(System.getProperty("build.number")!=null && !System.getProperty("build.number").contains("build")){
				Driver.GetTriggerByValueFromTeamCity(); //add by murthi on 2/18/2014
				Driver.GetBranchNameValueFromTeamCity(); //add by murthi on 2/18/2014
			}			
			run(argv, Thread.currentThread().getContextClassLoader());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}

	}

	public static String[] ProcessArray(String[] Args){
		String[] rArgs = new String[Args.length-1];
		try {
			for(int x =0; x<rArgs.length; x++){
				rArgs[x] = Args[x];
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rArgs = null;
		}
		return rArgs;
	}

	/**
	 * This function will search a feature file in side the root folder
	 * @param Folder to be searched
	 * @param FileName to search
	 * @return array of file paths which are matched
	 * @throws IOException
	 */
	public static String[] find_files(File root,String FileName) throws IOException
	{

		List<String> FilePath=new ArrayList<String>();
		List<File> FetFiles=new ArrayList<File>();
		String[] FileNames=FileName.split(",");
		String[] fileArr = null;
		try{
			try {
				for(int Iter=0;Iter<FileNames.length;Iter++){
					File[] files = root.listFiles();
					for (int IterA=0;IterA<files.length;IterA++) {
						// code added by KK on 12/12/13 - this if condition is added because if the file object in the list is not a directory then this for loop is throwing a null exception.
						if(files[IterA].isDirectory()){ 
							for (File file : files[IterA].listFiles()) {
								if (file.isFile()) {
									if(file.getName().equals(FileNames[Iter].toString())){
										FilePath.add(file.getCanonicalPath().toString());
										FetFiles.add(file);
										break;
									}
								}
								//				        else if (file.isDirectory()) {
								//				        		find_files(file,FileName);
								//				        }
							}
						}
						else{ // else block added by KK on 12/12/13
							if (files[IterA].isFile()) {
								if(files[IterA].getName().equals(FileNames[Iter].toString())){
									FilePath.add(files[IterA].getCanonicalPath().toString());
									FetFiles.add(files[IterA]);
									//break;
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			fileArr = new String[FetFiles.size()];
			for(int IterC=0;IterC<FetFiles.size();IterC++){
				fileArr[IterC]=FetFiles.get(IterC).getCanonicalPath();
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return fileArr;
	}


	public static byte run(String[] argv, ClassLoader classLoader) throws IOException {
		try{
			RuntimeOptions runtimeOptions = null;
			Runtime runtime = null;
			//	        runtimeOptions = new RuntimeOptions(System.getProperties(), argv);
			//	        runtime = new Runtime(new MultiLoader(classLoader), classLoader, runtimeOptions);
			runtimeOptions = new RuntimeOptions(new ArrayList<String>(asList(argv)));
			ResourceLoader resourceLoader = new MultiLoader(classLoader);
			ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
			runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
			List<String> featureFiles = runtimeOptions.getFeaturePaths();
			List<Object> Filters = runtimeOptions.getFilters();
			List<String> CukeGlues = runtimeOptions.getGlue();
			Driver.Gprops.SetCukeFilters(Thread.currentThread().getName(),Filters);
			Driver.Gprops.SetCukeGlues(Thread.currentThread().getName(),CukeGlues);
			Driver.Gprops.SetFeatureFiles(Thread.currentThread().getName(),featureFiles);
			CheckFeatureFiles(featureFiles,Filters);
			//WriteToConfigFile(ProcessFeaturandFilters(featureFiles,Filters));
			//	        runtime.writeStepdefsJson();
			System.out.println();
			if(ExecuteCucke){
				runtime.run();
				T = runtime.getErrors();
				List<String> MissingSnippets = runtime.getSnippets();
				if(MissingSnippets.size() > 0){
					Driver.CUtil.WriteResults("Printing the missing Step defenition/s", "run", "");
					for(int x=0; x<MissingSnippets.size(); x++){
						Driver.CUtil.WriteResults(MissingSnippets.get(x), "run", "");
					}
				}
				if(T.size() > 0){
					Driver.CUtil.WriteResults("Printing Cucumber RunTime Error/s", "run", "");
					for(int y=0;y<T.size(); y++){
						StackTraceElement[] st = T.get(y).getStackTrace();
						String stString = "";
						for(int z=0; z< st.length; z++){
							stString += " File Name: " +  st[z].getFileName() + " Method name: " + st[z].getMethodName() + "(" + st[z].getLineNumber() + ")<br>";
						}
						stString += "<br>Complete Error Message: " + T.get(y).getMessage() + "<br>";
						Driver.CUtil.WriteResults(stString, "run", "fail");
					}
				}
				//	        	Driver.CUtil.ComposeProcessedLog();

				// following code added by KK to insert the values to cukeresults table - 05/12/2013
				//	            String plHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/ProcessedLog.html";
				//        		String foHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/feature-overview.html";
				//        		String lgHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/Log.html";
				//        		Driver.DBCon.InsertCuckResult(Driver.Gprops.GetRunID(), System.getProperty("ProjectName"), System.getProperty("EnvFileName"), plHtml, foHtml, lgHtml, String.valueOf(Driver.Gprops.GetPassCounter()), String.valueOf(Driver.Gprops.GetFailCounter()));
				//        		Driver.CUtil.CreateIndexFile();

			}
			//        System.exit(runtime.exitStatus());

		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return runtime.exitStatus();
	}


	/**
	 * @author krishnakg
	 * @param FeatureFiles
	 * @param Filters
	 * @return
	 */
	public static String ProcessFeaturandFilters(List<String> FeatureFiles, List<Object> Filters){
		String Files = "FeatureFiles\n";
		File[] matches = null;
		try{
			for (String FileName : FeatureFiles){
				File f = new File(FileName);
				if(f.isDirectory()){
					matches = f.listFiles(new FilenameFilter()
					{
						public boolean accept(File dir, String name)
						{
							return name.endsWith(".feature");
						}
					});
					for(File f1 : matches){
						Files += f1.getCanonicalPath() + "\n";
					}
				}
				else{
					Files += FileName + "\n";
				}
			}
			Files += "Filters\n";
			for (Object Filt : Filters){
				Files += Filt.toString() + "\n";
			}
		}
		catch(Exception ex){
			Files = "error";
		}
		return Files;
	}

	/**
	 * This is the Test initilize method that will prep the required details that are required to run the test
	 * @author Krishna Kishore G	krishnakishore.g@move.com
	 * 
	 */

	public static void setUp(){//TestInit()
		try{
			System.out.println("SetUp Start...");
			Init = new TestProperties();
			Gprops = new GlobalProperties(); // this class creats & holds global properties
			CUtil = new CommonUtil();
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("MdyHmsz");
			String RunID = ft.format(dNow)+String.valueOf(System.currentTimeMillis());
			System.out.println("Run ID : " + RunID);
			Gprops.SetRunID(RunID);
			Init.Genesis(); // this function will read the environment file and set's them to the global properties in the TestProperties class
			ResultPath = CUtil.MakeResultFolder();
			WebCtrl = new WebCtrlOperations();
			CWeb = new CommonWeb();
			//appRDC = new sfdc();
			ExlRpt = new ExcelReport();
			DBCon = new DBConnection();
			htmlRpt = new HtmlReport();
			Mailer=new ResultMailer();
			CUtil.CleanUpDesktop();	// this function will clean the desktop before the execution.
			CUtil.ClearCookies();	// this function will clean the browser cookies.
			CUtil.ClearTempFolder(); // this will clear the temp folder, before test execution
			
			System.out.println("SetUp Complete...");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * This function will be executed after the test suite is executed, this function will read the text result file then creates a excel report and mails it. 
	 * @author Ditimoni Borpatra	Ditimoni.Borpatra@move.com
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void after() throws InvalidFormatException, IOException, MessagingException
	{
		try{
			//			Driver.mapper.put(Thread.currentThread().getName()
			WebDriver oBrowser =(WebDriver)Driver.mapper.get(Thread.currentThread().getName());
			oBrowser.quit();
			Driver.CUtil.CleanUpAfterTest();
			System.out.println("After suite");
			ArrayList<String> arrStr=new ArrayList<String>();
			String SourceDir=System.getProperty("user.dir")+"/target";
			String DestFile=System.getProperty("user.dir")+"/target.zip";
			System.out.println(DestFile);
			arrStr.add(DestFile);
			System.out.println("Sending mail...");
			String st1="test";
			Calendar c = Calendar.getInstance();
			String DateTime = c.getTime().toString();
			String MailSub="TWS Automated Test in "+Driver.Init.GetBaseURL()+ " "+System.getProperty("Tag");
			Mailer.send("tws@move.com", Init.GetMailAddress(), MailSub, st1, arrStr);
			System.out.println("Mail Send to  -->" + Init.GetMailAddress());
			System.out.println("::+------------------------+::");
			System.out.println("::||:: END OF EXECUTION ::||::");
			System.out.println("::+------------------------+::");
		}
		catch(Exception ex){
			System.out.println("Error @ afterTest");
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
		}
	}

	/**
	 * @author krishnakg
	 * This function will send the execution deatails and its results link to the mail.
	 * updated on 10/22/2013 by murthis
	 * Function Re-Written by Krishna Kishore G - krishnakishore.g@move.com on 12/03/2013 - The e-mail report  
	 */
	public static void SendMail(){
		try{
			//			 String FileName = System.getProperty("user.dir")+"/temp/"; 
			//	         File folder = new File(FileName); 
			//	         File[] listOfFiles = folder.listFiles(); 
			//	         ArrayList<String> arrStr=new ArrayList<String>();
			//	         if(listOfFiles.length>1)
			//	         {
			//		         for(int i=0;i<listOfFiles.length;i++) 
			//			     {
			//		        	 	Text txt=new Text();
			//			    	    String listOfFileNames ="";	
			//				        listOfFileNames=listOfFiles[i].toString(); 
			//				        File f=new File(listOfFileNames);
			//				        if(f.length()>1)
			//				        {
			//					        String st=txt.writeInExcelFileFromText(listOfFileNames);
			//					        arrStr.add(st); 
			//					        f.delete();
			//				        }
			//				        
			//			     }
			//	         }
			String RunID=Driver.globalValues.get("RunID");			
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
			String DateTimeInfo = ft.format(dNow);
			String FeatureFilesNames=null;
			String FetFileNames = "";

			//			int pCount = Driver.Gprops.GetPassCounter();
			//			int fCount = Driver.Gprops.GetFailCounter();

			int pStepCount=Integer.parseInt(globalValues.get("NumberOfPassedSteps"));
			int fStepCount=Integer.parseInt(globalValues.get("NumberOfFailedSteps"));
			int penStepCount=Integer.parseInt(globalValues.get("NumberOfSkippedSteps"));

			double passPcent1 = ((double)pStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;
			double failPcent1 = ((double)fStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;
			double penPcent1 = ((double)penStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;

			long passPcent=Math.round(passPcent1);
			long failPcent=Math.round(failPcent1);
			long penPcent=Math.round(penPcent1);

			//			int pCount=Integer.parseInt(globalValues.get("NumberOfPassedScenarios"));
			//			int fCount=Integer.parseInt(globalValues.get("NumberOfFailedScenarios"));

			//			double pPcent1 = ((double)pCount/((double)pCount+(double)fCount))*100;
			//			double fPcent1 = ((double)fCount/((double)pCount+(double)fCount))*100;

			//			long pPcent=Math.round(pPcent1);
			//			long fPcent=Math.round(fPcent1);

			if (Driver.Gprops.GetFeaturefileTag())
			{			
				FeatureFilesNames=Driver.Gprops.GetFeaturefileName();
				System.out.println("Ant block FeatureFilesNames="+FeatureFilesNames);
			}
			if (Driver.Gprops.GetFeaturefileTag())
			{
				FetFileNames = FeatureFilesNames;
				System.out.println("Ant block FeatureFilesNames="+FeatureFilesNames);
			}
			else
			{
				FetFileNames =  System.getProperty("FetFileName");
			}
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
			String HtmlTop = "<html><head><title>E-Mail Report</title></head><body><div align=\"center\" name=\"header\"><hr><h3>TWS Automation Test Report<br>" + DateTimeInfo + "</h3><hr></div><br><br>";
			HtmlTop += "<div name=\"body\">";
			HtmlTop += "<fieldset id=\"summary\"><legend>Summary</legend><table border=\"2\" cellpadding=\"2\" cellspacing=\"2\">";
			HtmlTop += "<tr><td>Execution Date and Time&thinsp;</td><td>&emsp;" + DateTimeInfo + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Environment&thinsp;</td><td>&emsp;" + Driver.Init.GetBaseURL() + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Browser Type&thinsp;</td><td>&emsp;" + bNames + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Browser Version&thinsp;</td><td>&emsp;" + Driver.Gprops.GetBrowserVersion() + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>OS Name&thinsp;</td><td>&emsp;" + Driver.Gprops.GetOSNames() + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Run ID&thinsp;</td><td>&emsp;" + Driver.Gprops.GetRunID() + "&emsp;</td></tr>";
			if(System.getProperty("build.number")!=null && !System.getProperty("build.number").contains("build")){
				HtmlTop += "<tr><td>TeamCity Project Name&thinsp;</td><td>&emsp;" + System.getProperty("teamcity.buildConfName") + "&emsp;</td></tr>";
				HtmlTop += "<tr><td>TeamCity Branch Name&thinsp;</td><td>&emsp;" +globalValues.get("BrachName")+"&emsp;</td></tr>";
				HtmlTop += "<tr><td>TeamCity Build Number&thinsp;</td><td>&emsp;<a href=\""+Driver.Init.GetFromEnvironment("TeamCityBaseURL")+"/viewLog.html?buildId="+System.getProperty("teamcity.build.id")+"&buildTypeId="+System.getProperty("teamcity.buildType.id")+"\">" + System.getProperty("build.number") + "</a>&emsp;</td></tr>";
			}

			if(System.getProperty("jenkins.build.id")!=null && !System.getProperty("jenkins.build.id").contains("BUILD")){

				HtmlTop += "<tr><td>Jenkins Job Name&thinsp;</td><td>&emsp;" + System.getProperty("jenkins.job.name") + "&emsp;</td></tr>";
				HtmlTop += "<tr><td>Jenkins Build ID&thinsp;</td><td>&emsp;" +System.getProperty("jenkins.build.id")+"&emsp;</td></tr>";
				HtmlTop += "<tr><td>Jenkins Build Number&thinsp;</td><td>&emsp;<a href=\""+System.getProperty("jenkins.build.url")+"\">" + System.getProperty("jenkins.build.number") + "</a>&emsp;</td></tr>";
			}

			HtmlTop += "<tr><td>Build Triggered By&thinsp;</td><td>&emsp;" +globalValues.get("TriggeredBy")+" ("+globalValues.get("TriggeredByName")+")"+ "&emsp;</td></tr>";
			HtmlTop += "<tr bgcolor=\"MediumSeaGreen\"><td>Passed Percentage&thinsp;</td><td>&emsp;" + passPcent+ "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfPassedSteps"))+ " Steps) </td></tr>";
			HtmlTop += "<tr bgcolor=\"IndianRed\"><td>Failed Percentage&thinsp;</td><td>&emsp;" + failPcent + "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfFailedSteps"))+ " Steps)</td></tr>";
			HtmlTop += "<tr bgcolor=\"CornflowerBlue\"><td>Skipped Percentage&thinsp;</td><td>&emsp;" + penPcent + "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfSkippedSteps"))+ " Steps)</td></tr>";
			HtmlTop += "</table></fieldset><br>";
			HtmlTop += "<fieldset id=\"detailsoffeatures_tags\">";
			HtmlTop += "<legend>Features and Tags</legend>";
			HtmlTop += "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\">";
			HtmlTop += "<tr><td>Component/s&thinsp;</td><td>&emsp;" + Driver.globalValues.get("CompName") + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Feature File/s&thinsp;</td><td>&emsp;" + FetFileNames + "&emsp;</td></tr>";
			HtmlTop += "<tr><td>Tag/s&thinsp;</td><td>&emsp;" + System.getProperty("Tag") + "&emsp;</td></tr>";
			HtmlTop += "</table></fieldset><br>";
			HtmlTop += "<fieldset id=\"ReportLinks\">";
			HtmlTop += "<legend>Report Links</legend>";
			HtmlTop += "<ol type=\"I\">";
			HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/feature-overview.html\">Click here</a> to view the Cucumber Report</li>";
			HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/Log.html\">Click here</a> to view the Log</li>";
			HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/ProcessedLog.html\">Click here</a> to view the Processed Log</li>";

			if(Driver.Gprops.GetexecLinkChecker()&& !Driver.Gprops.GetIsSEOExists()){
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/IndexLinkChecker.html\">Click here</a> to view the Link checker result file</li>";
			}



			//			if(System.getProperty("ProjectName").toLowerCase().contains("edw")){
			//				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/EDWHtml_Final.html\">Click here</a> to view the EDW lead result file</li>";
			//			}


			if(Driver.Init.GetAUTBrowser().toLowerCase().contains("edw")){
				//HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/TrackingHtml_Final.html\">Click here</a> to view the tracking(Omniture or EDW) result file</li>";
				String htmlAddress=Driver.globalValues.get("directoryAddress");
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+htmlAddress+"\">Click here</a> to view the tracking(Omniture or EDW) result file</li>";
			}



			if(System.getProperty("ProjectName").toLowerCase().contains("mdot")){
				String directory1=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+"/MdotEvent_Final.html";
				File htmlFile = new File(directory1);
				if(htmlFile.exists()){
					HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/MdotEvent_Final.html\">Click here</a> to view the mdot event validation result file</li>";
				}
			}

			if(System.getProperty("ProjectName").toLowerCase().contains("dataagg")){
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/DataAggValidation.html\">Click here</a> to view the Data Agg Validation result file</li>";
			}

			//			try{
			//				if(System.getProperty("ProjectName").toLowerCase().contains("edw")){
			//					HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/EDWReport.html\">Click here</a> to view the EDW report file</li>";
			//				}
			//			}
			//			catch(Exception ex){
			//				System.out.println("Exception @ SendMail while composing the EDW Report " + ex.getMessage());
			//			}
			//boolean IsSEOExists=false;
			if(Driver.Gprops.GetIsSEOExists())
			{
				//				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/LinkingModule.html\">" + "Click Here </a> to View Linking Module report</li>";
				HtmlTop += "</ol></fieldset></div>";
				HtmlTop += "<div name=\"SEOLinks\">";
				int seors = Driver.Gprops.GetSEOLinkerCheckerResults().size();
				if(seors > 0){
					HtmlTop += "<fieldset id=\"SEOLinksResult\">";
					HtmlTop += "<legend>SEO Link Checker result</legend>";
					HtmlTop += "<ol type=\"I\">";
					for(int x=0; x < seors; x++){
						if(!Driver.Gprops.GetSEOLinkerCheckerResults().get(x).trim().equals("")){
							HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/"+Driver.Gprops.GetSEOLinkerCheckerResults().get(x)+"\">"+Driver.Gprops.GetSEOLinkerCheckerResults().get(x)+"</a>";
						}
					}
					HtmlTop += "</ol></fieldset></div>";
				}
			}

			boolean IsRTZtest = false;
			//			String html=Driver.Gprops.GetHtmlValue();
			if(Driver.Gprops.GetIsRTZExists()){
				IsRTZtest = true;
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/RTZ.html\">" + "Click Here </a> to View RDC, Zillo & Trullia Comparision report</li>";
			}

			HtmlTop += "</ol></fieldset></div>";
			HtmlTop += "<div name=\"imgs\">";
			int eis = Driver.Gprops.GetErrorImgNamesList().size();
			if(eis > 0){
				HtmlTop += "<fieldset id=\"ErrImgLinks\">";
				HtmlTop += "<legend>Error / Warning Image Links</legend>";
				HtmlTop += "<ol type=\"I\">";
				/*for(int x=0; x < eis; x++){
					if(!Driver.Gprops.GetErrorImgNamesList().get(x).trim().equals("")){
						HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/"+Driver.Gprops.GetErrorImgNamesList().get(x)+"\">"+Driver.Gprops.GetErrorImgNamesList().get(x)+"</a>";
					}
				}*/
				HtmlTop += "</ol></fieldset></div>";
			}
			HtmlTop += "<div name=\"footer\">";
			HtmlTop += "<br><br><font face=\"Segoe Script\"><b>Regards,<br>TWS QA Automation<br><br></b></font><hr>";
			HtmlTop += "<footer><kbd>";
			HtmlTop += "<b><u>Note:</u></b>";
			HtmlTop += "This is an auto generated e-mail from an unmonitored mail box of the Automation Development Team (TWS QA Automation). Please do not reply directly to this email address. If you have any questions regarding this email or would like to be removed from this notification distribution, please send an email to ";
			HtmlTop += "<a href=\"mailto:#adtfe@move.com\">#Technology ADTFE</a> or you are also welcome to post on our <a href=\"mailto:moveautomationadt-fe+move.com@yammer.com\">Yammer</a> page.</kbd>";
			HtmlTop += "</footer><hr></div></body></html>";
			String BaseDir = System.getProperty("user.dir")+"/target/Temp/";
			boolean objCukeRpt = new File(BaseDir+"feature-overview.html").exists();
			boolean objLog = new File(BaseDir+"Log.html").exists();
			boolean objPrLogRpt = new File(BaseDir+"ProcessedLog.html").exists();
			if(objCukeRpt && objLog && objPrLogRpt){
				String MasterMailContent=HtmlTop;
				System.out.println(Init.GetMailAddress());
				Calendar c = Calendar.getInstance();
				String DateTime = c.getTime().toString();
				System.out.println(DateTime);
				//Code added for CTI test scenarios to change Subject line pass/fail -vijendra(04/14/2017)
				String TS=Driver.globalValues.get("SCENARIO");
				String CTI=Driver.globalValues.get("SUBJECT");
				String CTISUB="TWS "+TS+" IS "+ CTI +"";
				String MailSubject="TWS Automated Test in "+Driver.Init.GetBaseURL()+ " "+System.getProperty("Tag");
				if (Driver.globalValues.get("CompName").contains("ProSoft")||System.getProperty("FetFileName").contains("ProSoft")||Driver.globalValues.get("CompName").contains("CTI")||System.getProperty("FetFileName").contains("CTI")||Driver.globalValues.get("CompName").contains("ZUORA_TEST")||System.getProperty("FetFileName").contains("ZUORA_TEST")||Driver.globalValues.get("CompName").contains("ZuoraTest")||System.getProperty("FetFileName").contains("ZuoraTest")||Driver.globalValues.get("CompName").contains("Production_Connection")||System.getProperty("FetFileName").contains("Production_Connection")||Driver.globalValues.get("CompName").contains("SANITY")||System.getProperty("FetFileName").contains("SANITY")) {
					if(Mailer !=null){
						Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailAddress(), CTISUB, MasterMailContent, null);
					}
				}else {
					if(Mailer !=null){
						Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailAddress(), MailSubject, MasterMailContent, null);
					}	 
				}
				System.out.println("Mail Send to  -->" + Init.GetMailAddress());
			}
			else{
				System.out.println("Unable to Send mail as \"feature-overview.html\"; \"Log.html\"; \"ProcessedLog.html\"; files are not generated");
			}
			System.out.println("::+------------------------+::");
			System.out.println("::||:: END OF EXECUTION ::||::");
			System.out.println("::+------------------------+::");
		}
		catch(Exception ex){
			String StackTraceData = Driver.MethodName();
			System.out.println("Exception @ SendMail and error message " + ex.getMessage()+ " Stack Trace Info"+ StackTraceData.replace("<br>", "/"));
		}
	}


	/**
	 * @author Arun Kumar S
	 * @creationDate 06/06/2016
	 * This method sends the execution details and its results link to the mail only for DIGAN project.
	 */
	public static void SendMailDIGAN(){
		try{
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
			String DateTimeInfo = ft.format(dNow);
			String HtmlTop = "<html><head><title>E-Mail Report</title></head><body><div align=\"center\" name=\"header\"><hr><h3>TWS Automation Test Report<br>" + DateTimeInfo + "</h3><hr></div><br><br>";
			HtmlTop += "<div name=\"body\">";
			HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/feature-overview.html\">Click here</a> to view the Cucumber Report</li>";

			if(Driver.globalValues.get("DIGAN_Report")!=null)
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/DiganReport.html\">Click here</a> to view the DIGAN Report</li>";

			if(Driver.globalValues.get("DIGAN_ReportAPI")!=null)
				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/DiganReportAPI.html\">Click here</a> to view the DIGAN Report (API)</li>";

			HtmlTop += "<br><br><font face=\"Segoe Script\"><b>Regards,<br>Move ADT<br><br></b></font><hr>";
			HtmlTop += "<footer><kbd>";
			HtmlTop += "<b><u>Note:</u></b>";
			HtmlTop += "This is an auto generated e-mail from an unmonitored mail box of the Automation Development Team (Move ADT). Please do not reply directly to this email address. If you have any questions regarding this email or would like to be removed from this notification distribution, please send an email to ";
			HtmlTop += "<a href=\"mailto:#adtfe@move.com\">#Technology ADTFE</a> or you are also welcome to post on our <a href=\"mailto:moveautomationadt-fe+move.com@yammer.com\">Yammer</a> page.</kbd>";
			HtmlTop += "</footer><hr></div></body></html>";
			String BaseDir = System.getProperty("user.dir")+"/target/Temp/";
			boolean objCukeRpt = new File(BaseDir+"feature-overview.html").exists();
			boolean objLog = new File(BaseDir+"Log.html").exists();
			boolean objPrLogRpt = new File(BaseDir+"ProcessedLog.html").exists();
			if(objCukeRpt && objLog && objPrLogRpt){
				String MasterMailContent=HtmlTop;
				System.out.println(Init.GetMailAddress());
				Calendar c = Calendar.getInstance();
				String DateTime = c.getTime().toString();
				System.out.println(DateTime);
				String MailSubject="Automated Test for DIGAN";
				if(Mailer !=null){
					Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailAddress(), MailSubject, MasterMailContent, null);
				}
				System.out.println("Mail Send to  -->" + Init.GetMailAddress());
			}
			else{
				System.out.println("Unable to Send mail as \"feature-overview.html\"; \"Log.html\"; \"ProcessedLog.html\"; files are not generated");
			}
			System.out.println("::+------------------------+::");
			System.out.println("::||:: END OF EXECUTION ::||::");
			System.out.println("::+------------------------+::");
		}
		catch(Exception ex){
			String StackTraceData = Driver.MethodName();
			System.out.println("Exception @ SendMail and error message " + ex.getMessage()+ " Stack Trace Info"+ StackTraceData.replace("<br>", "/"));
		}
	}	


	/**
	 * @author krishnakg
	 */
	public static String ReadConfigFile(){
		String HtmlPart = "";
		try{
			String absFilePath = Init.GetTempFolder() + Gprops.GetRunID() + ".config";
			BufferedReader oBR = new BufferedReader(new FileReader(absFilePath));
			String Data = null;
			while((Data = oBR.readLine()) != null){
				HtmlPart += "<tr align=\"left\"><th>" + Data + "</th></tr>";
			}

		}
		catch(Exception ex){
			HtmlPart = "error";
		}
		return HtmlPart;
	}

	/**
	 * @author krishnakg
	 */
	public static String ReadStories(){
		String HtmlPart = "";
		try{
			String absFilePath = Init.GetResultPath() + Gprops.GetRunID() + ".txt";
			BufferedReader oBR = new BufferedReader(new FileReader(absFilePath));
			String Data = null;
			while((Data = oBR.readLine()) != null){
				HtmlPart += Data;
			}

		}
		catch(Exception ex){
			HtmlPart = "error";
		}
		return HtmlPart;
	}

	/**
	 * this function generate the cucumber report in the target/time stamp folder
	 * @author murthis
	 */
	public static boolean generateReports(){
		boolean returnflag = true;
		try {
			String FTPDirectory="_ADTFE/Result";			
			String RunId=globalValues.get("RunID");
			String TCBuildNumber=System.getProperty("build.number");
			String TCBuildConfigName=System.getProperty("teamcity.buildConfName");
			File reportOutputDirectory = new File(System.getProperty("user.dir")+"/target/"+RunId);
			List<String> jsonReportFiles = new ArrayList<String>();
			for(int IterA=0;IterA<Integer.parseInt(globalValues.get("NoOfFeatureFiles"));IterA++){
				File jsonFile=new File(System.getProperty("user.dir")+"/target/"+RunId+"/cucumber-report"+IterA+".json");
				if(jsonFile.exists()){
					jsonReportFiles.add(System.getProperty("user.dir")+"/target/"+RunId+"/cucumber-report"+IterA+".json");}
			}
			//Updated the result for execution information by murthi on 10/22/2013 
			String buildNumber ="</H2>";
			if(System.getProperty("build.number")!=null && !System.getProperty("build.number").contains("build")){
				buildNumber=buildNumber+"</H2>"+TCBuildNumber+"</H2>";
				buildNumber=buildNumber+"<div><ul><li>TeamCity Project Name: "+TCBuildConfigName+"</li>";
				buildNumber=buildNumber+"<div><ul><li>TeamCity Branch Name: "+globalValues.get("BrachName")+"</li>";
				buildNumber=buildNumber+"<div><ul><li>TeamCity Build Number: <a href=\""+Driver.Init.GetFromEnvironment("TeamCityBaseURL")+"/viewLog.html?buildId="+System.getProperty("teamcity.build.id")+"&buildTypeId="+System.getProperty("teamcity.buildType.id")+"\">" + System.getProperty("build.number") + "</a></li>";
				buildNumber=buildNumber+"<div><ul><li>Build Triggered By: "+ globalValues.get("TriggeredBy")+" ("+globalValues.get("TriggeredByName")+")</li>";
			}

			if(System.getProperty("jenkins.build.id")!=null && !System.getProperty("jenkins.build.id").contains("BUILD")){
				buildNumber=buildNumber+"</H2>"+System.getProperty("jenkins.build.id")+"</H2>";
				buildNumber += "<div><ul><li>Jenkins Job Name : " + System.getProperty("jenkins.job.name") + "</li>";
				Driver.globalValues.put(Thread.currentThread().getName()+ "JenkinJobName",buildNumber);
				buildNumber += "<div><ul><li>Jenkins Build ID : " +System.getProperty("jenkins.build.id")+"</li>";
				buildNumber += "<div><ul><li>Jenkins Build Number : <a href=\""+System.getProperty("jenkins.build.url")+"\">" + System.getProperty("jenkins.build.number") + "</a></li>";
			}
			buildNumber=buildNumber+"<li>Feature file executed: "+System.getProperty("FetFileName")+"</li>";
			buildNumber=buildNumber+"<li>Tag/s: "+System.getProperty("Tag")+"</li>";
			buildNumber=buildNumber+"<li>Component/s: "+Driver.globalValues.get("CompName")+"</li>";
			//			buildNumber=buildNumber+"<li>OS: "+System.getProperty("os.name") + " - " + System.getProperty("os.version")+"</li>";
			buildNumber=buildNumber+"<li>Environment: "+Driver.Init.GetBaseURL()+"</li>";
			buildNumber=buildNumber+"<li>Browser: "+Driver.Init.GetAUTBrowser()+"</li>";
			buildNumber=buildNumber+"<li>Browser Version: "+Driver.Gprops.GetBrowserVersion()+"</li>";
			buildNumber=buildNumber+"<li>OS Name: "+Driver.Gprops.GetOSNames()+"</li>";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			buildNumber=buildNumber+"<li>Date: "+dateFormat.format(date)+"</li>";
			buildNumber=buildNumber+"<li><a href=\"http://QAZ02HTP900:8080/Result/"+RunId+"/Log.html\">click here</a> for the log file</li>";
			buildNumber=buildNumber+"<li><a href=\"http://QAZ02HTP900:8080/Result/"+RunId+"/ProcessedLog.html\">click here</a> for the Detailed log file</li>";
			buildNumber=buildNumber+"<li><a href=\"http://QAZ02HTP900:8080/Result/"+RunId+"/TrendingReport.html\">click here</a> for the failed steps report</li>";
			if(Driver.Gprops.GetexecLinkChecker()){
				buildNumber=buildNumber+"<li><a href=\"http://QAZ02HTP900:8080/Result/"+RunId+"/IndexLinkChecker.html\">click here</a> for the log file</li>";
			}
			buildNumber=buildNumber+"</ul></div><br><br>";
			String buildProjectName = System.getProperty("ProjectName");
			boolean skippedFails = false;
			boolean pendingFails = true;
			boolean undefinedFails = false;
			boolean missingFails = true;
			boolean flashCharts = true;
			boolean runWithJenkins = false;
			boolean parallel = false;
			ReportBuilder reportBuilder;

			reportBuilder = new ReportBuilder(jsonReportFiles,reportOutputDirectory,"",buildNumber,buildProjectName,skippedFails,pendingFails,undefinedFails,missingFails,flashCharts,runWithJenkins,false,"",false,parallel);
			Map<String, String> cusHead=new HashMap<String, String>();
			cusHead.put("Move Automation", "Report");
			reportBuilder.setCustomHeader(cusHead);
			reportBuilder.generateReports();

			generateTrendingReport(jsonReportFiles, reportOutputDirectory);

			//Store number of passed and failed scenarios
			ReportParser reportParser = new ReportParser(jsonReportFiles);
			ReportInformation ri = new ReportInformation(reportParser.getFeatures());

			globalValues.put("NumberOfPassedSteps",String.valueOf(ri.getTotalStepsPassed()));
			globalValues.put("NumberOfFailedSteps",String.valueOf(ri.getTotalStepsFailed()));
			globalValues.put("NumberOfSkippedSteps",String.valueOf(ri.getTotalStepsSkipped()));

			//			globalValues.put("NumberOfPassedScenarios",String.valueOf(ri.getTotalScenariosPassed()));
			//			globalValues.put("NumberOfFailedScenarios",String.valueOf(ri.getTotalScenariosFailed()));
			//DITI TO DO

			if(System.getProperty("ProjectName").toLowerCase().contains("edw") ||Driver.Init.GetAUTBrowser().toLowerCase().contains("edw")){
				String directory=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID();
				String link="";
				File folder1 = new File(directory);
				File[] listOfFiles1 = folder1.listFiles();
				for (File file : listOfFiles1) {
					if (file.isFile()) {
						String fileName=file.getName();
						//			    	System.out.println(fileName);
						if(fileName.toLowerCase().contains("edwhtml"))
						{
							link+="<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/"+fileName+"\">Click here</a> to view the edw report for "+fileName+"</li>";
						}

					}
				}
				//			System.out.println("The HTML Is "+link);
				if(!link.equalsIgnoreCase("")){
					String masterHtml="<html><body><p align=center>"+link+"</p></body></html>";
					PrintWriter htmlPrintWriter=null;
					try{
						String directory1=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+"/EDWHtml_Final.html";
						File htmlFile = new File(directory1);
						htmlPrintWriter = new PrintWriter(htmlFile);
						htmlPrintWriter.println(masterHtml);
					}catch(Throwable th)
					{
						Assert.assertTrue(false);
					}finally{
						htmlPrintWriter.close();
					}
				}

			}
			//added by murthi on 8/12/201
			if(System.getProperty("ProjectName").toLowerCase().contains("mdot")){
				String directory=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID();
				String link="";
				File folder1 = new File(directory);
				File[] listOfFiles1 = folder1.listFiles();
				for (File file : listOfFiles1) {
					if (file.isFile()) {
						String fileName=file.getName();
						if(fileName.toLowerCase().contains("mdotevent"))
						{
							link+="<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/"+fileName+"\">Click here</a> to view the mdot event validation report for"+fileName+"</li>";
						}

					}
				}
				if(!link.equalsIgnoreCase("")){
					String masterHtml="<html><body><p align=center>"+link+"</p></body></html>";
					PrintWriter htmlPrintWriter=null;
					try{
						String directory1=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+"/MdotEvent_Final.html";
						File htmlFile = new File(directory1);
						htmlPrintWriter = new PrintWriter(htmlFile);
						htmlPrintWriter.println(masterHtml);
					}catch(Throwable th)
					{
						Assert.assertTrue(false);
					}finally{
						htmlPrintWriter.close();
					}
				}
			}
			String linkFile=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID();
			File folder = new File(linkFile);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					if(file.getName().contains("LinkingModule"))
					{
						Driver.CUtil.createSEOHtmlReport();
						break;
					}
				}
			}
			String fileName=System.getProperty("FetFileName");
			fileName=fileName.replace(".feature", "");
			fileName=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+ "/"+fileName+".txt";
			File f = new File(fileName);
			if(f.exists()){
				//HtmlTop += "<div id=\"rtz\">" + Driver.CUtil.createRTZHtmlReport() + "<br>Click <a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/RTZ.html\">" + "Here </a> to View the report</div>";
				Driver.CUtil.createRTZHtmlReport();
				//				HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/RTZ.html\">Click here</a> to view the RTZ file</li>";
			}
			copyJsonAndLogFilesToTimeStampFolder();
			//			String fileName=System.getProperty("FetFileName");
			//			fileName=fileName.replace(".feature", "");
			//String fileName=Driver.Gprops.getTextFilePath();

			returnflag = saveFilesToServer(FTPDirectory,reportOutputDirectory);

		} catch (Exception e) {
			e.printStackTrace();
			returnflag = false;
		}
		return returnflag;
	}

	/**
	 * This will generate the html report with failed steps and trending
	 * @author murthi.subramani<murthi.subramani@move.com>	
	 * @param jsonReportFiles
	 * @param ReportingDirectory
	 * Date created: 8/10/2015
	 * @throws IOException 
	 */
	public static void generateTrendingReport(List<String> jsonReportFiles, File ReportingDirectory) throws IOException{

		Map<String, List<Feature>> jsonFeatures;
		Feature feature;
		ArrayList<String> failedSteps=new ArrayList<String>();
		Set<String> uniqueFailedSteps=new TreeSet<String>();
		Map<String,Integer> failedStepsCount=new HashMap<String,Integer>();
		Map<String,String> failedStepsDefinitions=new HashMap<String,String>();
		Map<String,String> failedStepsAndReasons=new HashMap<String,String>();
		Map<String, Map<String, String>> failedScenarios=new HashMap<>();
		Map<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		String Reason=null;

		String Step=null;
		int count=0;


		FileUtils.copyFileToDirectory(new File(Driver.Init.GetBasePath()+"framework/Tools/canvasjs/canvasjs.min.js"), ReportingDirectory);
		String HTMLHead1="<html><head><title>Cucumber Report -- Step Wise</title>"
				+"<script type=\"text/javascript\" src=\"canvasjs.min.js\"></script>";

		String HTMLHead2="</head><body bgcolor='#6BC4CC'><div id=\"chartContainer\" style=\"height: 500px; width: 100%;\"></div>";
		String HTMLBody="";
		String HTMLTail="</tbody></table></body></html>";
		String HTML="";

		try{
			//Parsing JSON report
			ReportParser reportParser = new ReportParser(jsonReportFiles);
			ReportInformation ri = new ReportInformation(reportParser.getFeatures());
			jsonFeatures=reportParser.getFeatures();
			Set<String> jsonFeatureKeys=jsonFeatures.keySet();
			int noOfSteps=0;
			String JiraURL=null;
			for(String key:jsonFeatureKeys){
				if(jsonFeatures.get(key).size()>0){
					feature=jsonFeatures.get(key).get(0);
					for ( Element element:feature.getElements()){
						for (Step step:element.getSteps()){
							if(step.getStatus()==Status.FAILED){
								System.out.println(step.getOutput());
								noOfSteps=element.getSteps().size();
								org.jsoup.nodes.Element ele=Jsoup.parseBodyFragment(element.getSteps().get(noOfSteps-1).getOutput()).getElementById("FailureReason");
								Reason=ele!=null?ele.text():"";
								System.out.println(Reason);
								//updated the code to display jira ticket as link
								if(Reason.toLowerCase().contains("jira") && Reason.toLowerCase().contains("http")){
									JiraURL=Reason.substring(Reason.indexOf("http")).trim();
									Reason=Reason.replace(JiraURL,"<a href=\""+JiraURL+"\">"+JiraURL+"</a>");
								}
								failedStepsAndReasons.put(step.getRawName().toLowerCase(),Reason);
								failedSteps.add(step.getRawName().toLowerCase());
								failedStepsDefinitions.put(step.getRawName().toLowerCase(), step.getMatch().getLocation());
								Map<String,String> temp =failedScenarios.get(step.getRawName().toLowerCase());
								if(temp!=null){
									temp.put(element.getRawName(), feature.getFileName().replace(".html", ""));
								}
								else{
									temp = new HashMap<String, String>();
									temp.put(element.getRawName(), feature.getFileName().replace(".html", ""));
								}
								failedScenarios.put(step.getRawName().toLowerCase(),temp);
							}
						}

					}
				}
				else{
					System.out.println("The json report "+ key +" is empty");
				}
			}
			uniqueFailedSteps.addAll(failedSteps);

			//Adding pass and fail count for scenario and step
			HTMLBody=HTMLBody+"<br><div> Number of scenarios passed: <font color = \"green\">"+ri.getTotalScenariosPassed()+"</font></div>";
			HTMLBody=HTMLBody+"<div> Number of scenarios failed: <font color = \"red\">"+ri.getTotalScenariosFailed()+"</font></div>";
			HTMLBody=HTMLBody+"<div> Number of steps passed: <font color = \"green\">"+ri.getTotalStepsPassed()+"</font></div>";
			HTMLBody=HTMLBody+"<div> Number of steps failed: <font color = \"red\">"+ri.getTotalStepsFailed()+"</font></div>";

			//Creating Table
			HTMLBody= HTMLBody+"<H1 ><font color=\"red\"> Failed Steps</font> </H1>";
			HTMLBody= HTMLBody+"<table border=\"2\" cellpadding=\"2\" cellspacing=\"2\"><tbody>";
			HTMLBody=HTMLBody+"<tr><th> S.No </th><th> Story </th><th> Error count </th><th> Step definition </th><th> Reason for the failure </th>";
			int Iter=1;
			for (String temp : uniqueFailedSteps) {
				count=Collections.frequency(failedSteps, temp);
				failedStepsCount.put(temp, count);
			}

			//Sort the table by values
			sortedMap=sortByValues(failedStepsCount);

			//Print the error count in reverse
			ArrayList<String> keys = new ArrayList<String>(sortedMap.keySet());
			HashMap<String, String> stepAndReportsName=new HashMap<String, String>(keys.size());
			Iter=1;
			for(int i=keys.size()-1; i>=0;i--,Iter++){
				String temp=keys.get(i);
				stepAndReportsName.put(temp, "TrendSubReport"+Iter+".html");
				//			        	HTMLBody=HTMLBody+ "<tr><td>"+Iter+"</td><td><a href=\""+temp.replace(" ", "_").replace("\"", "").replace("%", "").replace("/", "")+".html" +"\">"+temp+"</a></td><td>"+sortedMap.get(temp) +"</td><td>"+failedStepsDefinitions.get(temp) +"</td><td>"+failedStepsAndReasons.get(temp)+"</td></tr>";
				HTMLBody=HTMLBody+ "<tr><td>"+Iter+"</td><td><a href=\""+ stepAndReportsName.get(temp)+"\">"+temp+"</a></td><td>"+sortedMap.get(temp) +"</td><td>"+failedStepsDefinitions.get(temp) +"</td><td>"+failedStepsAndReasons.get(temp)+"</td></tr>";
			}

			//Creating Line Chart Using CanvasJS
			String HTMLPieChartStart="<script type=\"text/javascript\">"
					+"window.onload = function () {"
					+"var chart = new CanvasJS.Chart(\"chartContainer\","
					+"{"
					+"animationEnabled: true,"
					+"animationDuration: 2000,"
					+"backgroundColor: \"#F5DEB3\","
					+"colorSet: \"colorSet1\","
					+"toolTip: {"
					+"backgroundColor: \"rgba(255,255,0,.2)\""
					+"},"
					+"title:{"
					+"	text: \"TEST STATISTICS\",fontColor: \"green\","
					+"},"
					+"axisX:{ title: \"FAILED STORY LINES\",titleFontColor: \"red\",gridDashType: \"dot\"},"
					+"axisY:{ title: \"ERROR COUNT\",titleFontColor: \"blue\",gridDashType: \"dot\"},"

						+"data: ["
						+"{"
						+"type: \"line\","
						+"indexLabelFontSize: 12,"
						+"toolTipContent: \" <b>Story:</b> {storyName}<br> <b>Failed Count:</b> {y} <br> <b>The Reason for failure: </b> {reason} <br> <a href = {name}> Click here </a> to view list of scenarios it belongs to \","
						+"dataPoints: [";
			String HTMLPieChartMiddle="";
			String Story=null;
			String FailReason=null;
			int FailCount=0;
			for (Entry<String, Integer> entry : failedStepsCount.entrySet()){
				Story=entry.getKey();
				FailCount=entry.getValue();
				FailReason=failedStepsAndReasons.get(Story).replace("\"\"", "").replace("\"", "\\\"").split("Build info")[0].replace("\n", "").replace("\r", "");
				HTMLPieChartMiddle=HTMLPieChartMiddle+"{ y: "+FailCount+", Label: \" \",indexLabel: \""+ FailCount +"\", reason: \""+ FailReason +"\", storyName: \""+Story.replace("\"", "\\\"")+"\", name : \""+stepAndReportsName.get(Story)+"\" },";
			}			

			String HTMLPieChartEnd=""			+"]"
					+"}"
					+"]"
					+"});"
					+"chart.render();"
					+"}"
					+"</script>";

			String HTMLPieChart=HTMLPieChartStart+HTMLPieChartMiddle+HTMLPieChartEnd;

			//Creating Trending HTML
			HTML=HTML+HTMLHead1+HTMLPieChart+HTMLHead2+HTMLBody+HTMLTail;

			PrintWriter htmlPrintWriter=null;
			try{
				String directory=ReportingDirectory+"/TrendingReport.html";
				File htmlFile = new File(directory);
				htmlPrintWriter = new PrintWriter(htmlFile);
				htmlPrintWriter.println(HTML);
			}catch(Throwable th)
			{
				System.out.println("Unable to generate the TrendingReport html");
			}finally{
				htmlPrintWriter.close();
			}

			//Creating the HTMLs For each steps
			for (Entry<String, Map<String,String>> entry : failedScenarios.entrySet()){

				String step=entry.getKey();
				Map<String,String> scenario=entry.getValue();
				//String FileName=step.replace(" ", "_").replace("\"", "").replace("%", "").replace("/", "")+".html";
				String FileName=stepAndReportsName.get(step);

				String HTMLTemp="<html><head><title>Cucumber Report -- Step Wise</title></head><body bgcolor='#6BC4CC'>";

				HTMLTemp=HTMLTemp+"<H2> The Story : "+step+"</H2>";

				HTMLTemp=HTMLTemp+"<table border=\"2\" cellpadding=\"2\" cellspacing=\"2\"><tbody>";

				HTMLTemp=HTMLTemp+"<tr><th>S.No</th><th> Scenario </th><th> Feature File </th>";
				int Iter2=1;
				for (Entry<String, String> entryInner : scenario.entrySet()){
					HTMLTemp=HTMLTemp+"<tr><td>"+Iter2+"</td><td><a href=\""+entryInner.getValue()+".html\">"+ entryInner.getKey() +"</a></td><td> "+entryInner.getValue() +" </td><tr>";
					Iter2++;
				}

				HTMLTemp=HTMLTemp+"</tbody></table></body></html>";

				try{
					String directory=ReportingDirectory+"/"+FileName;
					File htmlFile = new File(directory);
					htmlPrintWriter = new PrintWriter(htmlFile);
					htmlPrintWriter.println(HTMLTemp);

				}catch(Throwable th)
				{
					System.out.println("Unable to generate the html"+FileName);

				}finally{
					htmlPrintWriter.close();
				}

			}

		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}






	/**
	 * updated by cmonte
	 * added ftp. setBufferSize(0) to remove ftp upload latency
	 * and set FTP to active mode instead of passive mode
	 * unit test shows ftp upload is less than 20 seconds
	 */
	public static boolean saveFilesToServer(String remoteDest, File localSrc) throws IOException {
		boolean returnflag = true;
		FTPClient ftp = new FTPClient();
		ftp.connect("10.160.128.38",555);
		if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
			ftp.disconnect();
			System.out.println("FTP not disconnected");
		}
		ftp.login("moveautomation", "cucumber12!");
		ftp.enterLocalPassiveMode();
		System.out.println("Connected to FTP server");
		System.out.println(ftp.getReplyString());
		ftp.changeWorkingDirectory(remoteDest);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		try {
			System.out.println("Initiating result folder upload to FTP server...");
			returnflag = upload(localSrc, ftp);
			System.out.println("Completed uploading result folder to FTP server...");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			returnflag = false;
		}
		finally {
			ftp.disconnect();
			System.out.println("FTP disconnected...");
		}
		return returnflag;
	}

	public static boolean upload(File src, FTPClient ftp) throws IOException {
		boolean returnflag = true;
		if (src.isDirectory()) {
			ftp.makeDirectory(src.getName());
			ftp.changeWorkingDirectory(src.getName());
			for (File file : src.listFiles()) {
				upload(file, ftp);
			}
			ftp.changeToParentDirectory();
		}
		else {
			InputStream srcStream = null;
			try {
				srcStream = src.toURI().toURL().openStream();
				ftp.storeFile(src.getName(), srcStream);
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
				returnflag = false;
			}
			finally {
				IOUtils.closeQuietly(srcStream);
			}
		}
		return returnflag;
	}

	/**
	 * This function will manipulate the feature files and tags 
	 * @author krishnakg
	 * @param featureFiles
	 * @param tagFilters
	 */
	public static void CheckFeatureFiles(List<String> featureFiles, List<Object> tagFilters){
		//Added the below calling function :Madhu:31:10:2013
		try{
			if (System.getProperty("FetFileName").isEmpty())
			{
				GetFeatureFileNames(featureFiles, tagFilters);

			}
		}
		catch(Exception ex)
		{

		}
		String ffDir=null;
		try{
			if (featureFiles.size() == 0 && tagFilters.size() == 0){
				System.out.println("No Filters or Feature Specified, Skipping the execution");
				ExecuteCucke = false;
			}
			else{
				if (featureFiles.size() == 1){ //&& tagFilters.size() != 0 // Code updated by KK - on 10/23/2013 
					if(featureFiles.get(0).trim().toLowerCase().contains("{FetFileName}") || featureFiles.get(0).trim().toLowerCase().equals("")){
						runtimeOptions.getFeaturePaths().add(ffDir);
					}
				}
				else if (featureFiles.size() == 0 && tagFilters.size() != 0){
					runtimeOptions.getFeaturePaths().add(ffDir);
				}
				if(tagFilters.contains("{Tag}")){ // featureFiles.size() > 1 && // Code updated by KK - on 10/23/2013 
					Collection<String> x = null;
					x.add("{Tag}");
					x.add("${Tag}");
					runtimeOptions.getFilters().removeAll(x);
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}




	//**************************************************************************************************************************************************
	//Function Name:GetFeatureFileNames
	//Description: This function fetches the list of feature file names under FeatureFiles folder of any project which are under ADT_Resources folder
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:featureFiles:featurepath of the File
	//          :tagFilters:Tags associated;
	//Dependency:Call the function CheckFeatureFiles before calling this function 
	//Date written:31/10/2013
	//***************************************************************************************************************************************************
	public static void readJsonFile() {
		BufferedReader br = null;
		try {
			String CurrentLine;
			String HoldFeatureExecuted=null;
			br = new BufferedReader(new FileReader(Driver.Gprops.GetCucumberreportPath()));
			while ((CurrentLine = br.readLine()) != null) {
				System.out.println("Record:\t" + CurrentLine);
				if(CurrentLine.trim().toLowerCase().contains("uri"))
				{
					int uripostion1=CurrentLine.indexOf("uri");
					if (Driver.Gprops.GetFlagAntExecution())
					{
						String str = CurrentLine;
						String findStr = "uri";
						int lastIndex = 0;
						int count =0;
						String holdfefinames=null;
						while(lastIndex != -1){
							lastIndex = str.indexOf(findStr,lastIndex);
							if( lastIndex != -1){
								count ++;
								String Temp1=str.substring(lastIndex+4);
								String [] FirstFFName=Temp1.split("}");
								HoldFeatureExecuted=HoldFeatureExecuted+"-"+FirstFFName[0];
								lastIndex+=findStr.length();
							}
						}
					}
					else{
						String[] AFterSplit=CurrentLine.split(":");
						System.out.println(AFterSplit[1]);
						System.out.println("Ant block Record:\t" + CurrentLine);
						HoldFeatureExecuted=HoldFeatureExecuted+"-"+AFterSplit[1];
					}
				}
			}
			Driver.Gprops.SetFeaturefileName(HoldFeatureExecuted.replace("null",""));
			System.out.println("Ant block Record Final Featurefilenames:\t" + CurrentLine);
			System.out.println("Feature file names from json"+HoldFeatureExecuted);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	//**************************************************************************************************************************************************
	//Function Name:GetFeatureFileNames
	//Description: This function fetches the list of feature file names under FeatureFiles folder of any project which are under ADT_Resources folder
	//Author Madhusudhana k.s Email:Madhusudhana.Shivalingappa@move.com
	//Parameters:featureFiles:featurepath of the File
	//          :tagFilters:Tags associated;
	//Dependency:Call the function CheckFeatureFiles before calling this function 
	//Date written:31/10/2013
	//***************************************************************************************************************************************************
	public static void GetFeatureFileNames(List<String> featureFiles, List<Object> tagFilters)
	{
		String[] FeaturefilepathArray=null;
		String[] TagArray;
		String ffDir;
		if (System.getProperty("user.dir").contains("ADT_Resources"+ System.getProperty("ProjectName")))	
		{
			//to run from eclipse
			String ProjectCRPath="C:\\Users\\ajay.yapada\\Desktop\\auto\\ADT_Resources"+System.getProperty("ProjectName")+"/target/Temp/cucumber-report.json";
			System.out.println("To run from eclipse ProjectCRPath "+ProjectCRPath);
			Driver.Gprops.SetCucumberreportPath(ProjectCRPath);			
			FeaturefilepathArray = featureFiles.toArray(new String[0]);
			FeaturefilepathArray[0]=FeaturefilepathArray[0]+"/"+System.getProperty("ProjectName");
			TagArray = tagFilters.toArray(new String[0]);
			ffDir = System.getProperty("user.dir") + "/FeatureFiles";
			System.out.println("FeaturefilepathArray "+FeaturefilepathArray[0]);
			System.out.println("FFdir "+ffDir);
		}
		else 
		{
			//To Run From Ant
			Driver.Gprops.SetFlagAntExecution(true);
			FeaturefilepathArray = featureFiles.toArray(new String[0]);
			String AntCRPath=(System.getProperty("user.dir")+"/target/Temp/cucumber-report.json");
			System.out.println("Ant block ANTCRpath="+AntCRPath);
			Driver.Gprops.SetCucumberreportPath(AntCRPath);
			FeaturefilepathArray[0]=System.getProperty("user.dir") +"/"+System.getProperty("ProjectName").trim().toLowerCase()+ "/FeatureFiles";
			TagArray = tagFilters.toArray(new String[0]);
			//String ffDir = System.getProperty("user.dir") + "/FeatureFiles";
			ffDir = System.getProperty("user.dir") +"/"+System.getProperty("ProjectName")+ "/FeatureFiles";
			System.out.println("FeaturefilepathArray "+FeaturefilepathArray[0]);
			System.out.println("FFdir "+ffDir);
		}
		if(FeaturefilepathArray[0].toLowerCase().contains(System.getProperty("user.dir").toLowerCase())||FeaturefilepathArray[0].toLowerCase().equals(System.getProperty("user.dir")+"/edw/featurefiles"))
		{
			Driver.Gprops.SetFeaturefileTag(true);
			System.out.println("Ant block Flag="+Driver.Gprops.GetFeaturefileTag());
			String Tag=null;
		}
	}

	/**
	 *author: murthi
	 */
	public static void copyJsonAndLogFilesToTimeStampFolder(){
		try{
			String RunId=Driver.globalValues.get("RunID");
			File TempFolder=new File(System.getProperty("user.dir")+"/target/Temp/");
			if(TempFolder.exists()){
				FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"/target/Temp/"));
			}else{
				TempFolder.mkdir();
			}
			FileUtils.copyDirectory(new File(System.getProperty("user.dir")+"/target/"+RunId), new File(System.getProperty("user.dir")+"/target/Temp/"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * This function delete the time stamp folder under target folder.
	 * @author msubramani
	 * Date Created: 3/26/2012
	 */
	public static void deleteTimeStampFolder(){
		try{
			String RunId=Driver.globalValues.get("RunID");
			System.gc();
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir")+"/target/"+RunId));
			System.out.println("The time stamp folder "+ new File(System.getProperty("user.dir")+"/target/"+RunId).getName() +" has been deleted after uploading to ftp");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}

	}

	public static void CheckFilters(List<String> featureFiles, List<Object> tagFilters){
		try{

		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * This function will parse the xml and get the trigger by user name
	 * @param XML
	 * @return User Name
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static String GetTriggerByValueFromTeamCity() throws ParserConfigurationException, IOException, SAXException{

		String UserName="";
		String Name="";

		try{
			String Xml=queryTeamCity();

			if(!Xml.equalsIgnoreCase("")){
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(Xml));

				Document doc = db.parse(is);
				NodeList nodes = doc.getElementsByTagName("user");
				if(nodes!=null){
					if(nodes.item(0).getAttributes().getNamedItem("username")!=null){
						UserName=nodes.item(0).getAttributes().getNamedItem("username").getNodeValue();
					}
					System.out.println(UserName);
					globalValues.put("TriggeredBy", UserName);

					if(nodes.item(0).getAttributes().getNamedItem("name")!=null){
						Name=nodes.item(0).getAttributes().getNamedItem("name").getNodeValue();
					}
					System.out.println(Name);
					globalValues.put("TriggeredByName", Name);
				}
				else{
					NodeList node = doc.getElementsByTagName("buildType");
					if(node!=null){
						Name=nodes.item(0).getAttributes().getNamedItem("name").getNodeValue();
						if(Name.contains("Auto")){
							globalValues.put("TriggeredBy", "TeamCity");
							globalValues.put("TriggeredByName", "Auto");
						}
					}
				}
			}
			else{
				//Get host name
				java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
				System.out.println("Host Name: " + localMachine.getHostName());
				UserName=localMachine.getHostName();
				//Get logged in user name
				Name=System.getProperty("user.name");
				System.out.println("User Name: " + Name);

				globalValues.put("TriggeredBy", UserName);
				globalValues.put("TriggeredByName", Name);
			}
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return UserName+"|"+Name;

	}

	/**
	 * This function will parse the xml and get the team city branch name
	 * @param XML
	 * @return BranchName
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */

	public static String GetBranchNameValueFromTeamCity() throws ParserConfigurationException, IOException, SAXException{
		String BranchName="";
		try{
			String Xml=queryTeamCity();

			if(!Xml.equalsIgnoreCase("")){
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(Xml));

				Document doc = db.parse(is);
				NodeList nodes = doc.getElementsByTagName("build");
				BranchName=nodes.item(0).getAttributes().getNamedItem("branchName").getNodeValue();
				System.out.println(BranchName);
				globalValues.put("BrachName", BranchName);
			}else{
				globalValues.put("BrachName", "N/A");
			}

		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return BranchName;
	}

	/**
	 * It queries the team city with build id
	 * @return the XML as string
	 * @throws IOException
	 * @author MurthiS<murthi.subramani@move.com>
	 */
	public static String queryTeamCity() throws IOException{

		URL url;
		StringBuilder sb = new StringBuilder();
		String BuildId=System.getProperty("teamcity.build.id");
		try {
			if(BuildId!=null){
				if(!BuildId.trim().equalsIgnoreCase("")){

					String line;
					String TCurl=Driver.Init.GetFromEnvironment("TeamCityBaseURL");
					if(TCurl.equalsIgnoreCase("")){
						TCurl="http://teamcityrdc:8111";
					}
					url = new URL(TCurl+"/guestAuth/app/rest/builds/id:"+BuildId.trim());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					if (conn.getResponseCode() != 200) {
						throw new IOException(conn.getResponseMessage());
					}

					// Buffer the result into a string
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
					rd.close();
					conn.disconnect();
				}
				else{
					System.out.println("The build id is blank or empty. So couldn't query the team city.");
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return sb.toString();
	}


	/**
	 * Java method to sort Map in Java by value e.g. HashMap or Hashtable
	 * throw NullPointerException if Map contains null values
	 * It also sort values even if they are duplicates
	 * @author murthi.subramani<murthi.subramani@move.com>
	 * @param map
	 * @return Map
	 */
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
		List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		//LinkedHashMap will keep the keys in the order they are inserted
		//which is currently sorted on natural ordering
		Map<K,V> sortedMap = new LinkedHashMap<K,V>();

		for(Map.Entry<K,V> entry: entries){
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
	/**
	 * @author krishnakg
	 * This function will send the execution details and its results link to the mail. This method is used only for salesforce project.
	 * updated on 06/09/2017 by Vijendra Ahirwar
	 * Function Re-Written by Krishna Kishore G - krishnakishore.g@move.com on 12/03/2013 - The e-mail report  
	 */
	public static void SendMailTWS(){
		try{

			String RunID=Driver.globalValues.get("RunID");			
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
			String DateTimeInfo = ft.format(dNow);
			String FeatureFilesNames=null;
			String FetFileNames = "";

			int pStepCount=Integer.parseInt(globalValues.get("NumberOfPassedSteps"));
			int fStepCount=Integer.parseInt(globalValues.get("NumberOfFailedSteps"));
			int penStepCount=Integer.parseInt(globalValues.get("NumberOfSkippedSteps"));

			double passPcent1 = ((double)pStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;
			double failPcent1 = ((double)fStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;
			double penPcent1 = ((double)penStepCount/((double)pStepCount+(double)fStepCount + (double)penStepCount))*100;

			long passPcent=Math.round(passPcent1);
			long failPcent=Math.round(failPcent1);
			long penPcent=Math.round(penPcent1);

			if (Driver.Gprops.GetFeaturefileTag())
			{			
				FeatureFilesNames=Driver.Gprops.GetFeaturefileName();
				System.out.println("Ant block FeatureFilesNames="+FeatureFilesNames);
			}
			if (Driver.Gprops.GetFeaturefileTag())
			{
				FetFileNames = FeatureFilesNames;
				System.out.println("Ant block FeatureFilesNames="+FeatureFilesNames);
			}
			else
			{
				FetFileNames =  System.getProperty("FetFileName");
			}
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
			String TS=Driver.globalValues.get("SCENARIO");
			String CTI=Driver.globalValues.get("SUBJECT");
			String HtmlTop = "<html><head><title>E-Mail Report</title></head><body><div align=\"center\" name=\"header\"><hr><h3>"+TS+" IS "+CTI+"<br></h3><hr></div><br><br>";
			HtmlTop += "<div name=\"body\">";
			if (TS.contains("ZUORA")) {
				if (CTI.contains("FAIL")) {
					HtmlTop += "<p><u><strong>Issue:  </strong>Zuora Billing system is down in Production</u></p>";

					HtmlTop += "<p><u><strong>Business Impact:  </strong>Credit Card auth, Collection payment updates, CPQ amend/cancel delta pricing calculations</u></p>";

					HtmlTop += "<p><u><strong>First reported:  </strong>"+ DateTimeInfo + "</u></p>";


					HtmlTop += "<p><u><strong>Impacted users:  </strong>Sales Reps, Managers, Ops, Billing & Admin support</u></p>";

				}

			}else if (TS.contains("CTI")) {
				if (CTI.contains("FAIL")) {
					HtmlTop += "<p><u><strong>Issue:  </strong>CTI Production system is down</u></p>";

					HtmlTop += "<p><u><strong>Description:  </strong>CTI Production system is down</u></p>";

					HtmlTop += "<p><u><strong>First reported:  </strong>"+ DateTimeInfo + "</u></p>";


					HtmlTop += "<p><u><strong>Impacted users:  </strong>Sales Reps, Managers, Ops, Billing & Admin support</u></p>";
				}
			}else if (TS.contains("PROSOFT")) {
					if (CTI.contains("FAIL")) {
						String sourceURL=Driver.globalValues.get("sourceURL");
						String targetURL=Driver.globalValues.get("targetURL");
						HtmlTop += "<p><u><strong>Issue:  </strong>Prosoft Order confirmation email  URL MisMatch</u></p>";

						HtmlTop += "<p><u><strong>Description:  </strong>Source URL="+sourceURL+"" + "</u></p>";
						HtmlTop += "<p><u>Target URL="+targetURL+"</u></p>";
						
						HtmlTop += "<p><u><strong>First reported:  </strong>"+ DateTimeInfo + "</u></p>";

						HtmlTop += "<p><u><strong>Impacted users:  </strong>Sales Reps, Managers, Ops, Billing & Admin support</u></p>";
					}
			}
			if (CTI.contains("PASS")){
				HtmlTop += "<p><u><strong>Mail sent to :  </strong>Arindom, Vijendra and Roop</u></p>";

				HtmlTop += "<p><u><strong>First reported:  </strong>"+ DateTimeInfo + "</u></p>";
			}

			//HtmlTop += "<a href=\"mailto:#adtfe@move.com\">#Technology ADTFE</a> or you are also welcome to post on our <a href=\"mailto:moveautomationadt-fe+move.com@yammer.com\">Yammer</a> page.</kbd>";
			HtmlTop += "</footer><hr></div></body></html>";
			//HtmlTop += "<tr><td>Environment&thinsp;</td><td>&emsp;" + Driver.Init.GetBaseURL() + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>Browser Type&thinsp;</td><td>&emsp;" + bNames + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>Browser Version&thinsp;</td><td>&emsp;" + Driver.Gprops.GetBrowserVersion() + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>OS Name&thinsp;</td><td>&emsp;" + Driver.Gprops.GetOSNames() + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>Run ID&thinsp;</td><td>&emsp;" + Driver.Gprops.GetRunID() + "&emsp;</td></tr>";


			//HtmlTop += "<tr><td>Build Triggered By&thinsp;</td><td>&emsp;" +globalValues.get("TriggeredBy")+" ("+globalValues.get("TriggeredByName")+")"+ "&emsp;</td></tr>";
			//HtmlTop += "<tr bgcolor=\"MediumSeaGreen\"><td>Passed Percentage&thinsp;</td><td>&emsp;" + passPcent+ "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfPassedSteps"))+ " Steps) </td></tr>";
			//HtmlTop += "<tr bgcolor=\"IndianRed\"><td>Failed Percentage&thinsp;</td><td>&emsp;" + failPcent + "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfFailedSteps"))+ " Steps)</td></tr>";
			//HtmlTop += "<tr bgcolor=\"CornflowerBlue\"><td>Skipped Percentage&thinsp;</td><td>&emsp;" + penPcent + "%&emsp;("+Integer.parseInt(globalValues.get("NumberOfSkippedSteps"))+ " Steps)</td></tr>";
			//HtmlTop += "</table></fieldset><br>";
			//HtmlTop += "<fieldset id=\"detailsoffeatures_tags\">";
			//HtmlTop += "<legend>Features and Tags</legend>";
			//HtmlTop += "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\">";
			//HtmlTop += "<tr><td>Component/s&thinsp;</td><td>&emsp;" + Driver.globalValues.get("CompName") + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>Feature File/s&thinsp;</td><td>&emsp;" + FetFileNames + "&emsp;</td></tr>";
			//HtmlTop += "<tr><td>Tag/s&thinsp;</td><td>&emsp;" + System.getProperty("Tag") + "&emsp;</td></tr>";
			//HtmlTop += "</table></fieldset><br>";
			//HtmlTop += "<fieldset id=\"ReportLinks\">";
			//HtmlTop += "<legend>Report Links</legend>";
			//HtmlTop += "<ol type=\"I\">";
			//HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/feature-overview.html\">Click here</a> to view the Cucumber Report</li>";
			//HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/Log.html\">Click here</a> to view the Log</li>";
			//HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/ProcessedLog.html\">Click here</a> to view the Processed Log</li>";


			HtmlTop += "</ol></fieldset></div>";
			HtmlTop += "<div name=\"imgs\">";
			int eis = Driver.Gprops.GetErrorImgNamesList().size();
			/*if(eis > 0){
				HtmlTop += "<fieldset id=\"ErrImgLinks\">";
				HtmlTop += "<legend>Error / Warning Image Links</legend>";
				HtmlTop += "<ol type=\"I\">";
				for(int x=0; x < eis; x++){
					if(!Driver.Gprops.GetErrorImgNamesList().get(x).trim().equals("")){
						HtmlTop += "<li><a href=\"http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/"+Driver.Gprops.GetErrorImgNamesList().get(x)+"\">"+Driver.Gprops.GetErrorImgNamesList().get(x)+"</a>";
					}
				}
				HtmlTop += "</ol></fieldset></div>";
			}*/
			HtmlTop += "<div name=\"footer\">";
			HtmlTop += "<br><br><font face=\"Segoe Script\"><b>Regards,<br>TWS QA Automation<br><br></b></font><hr>";
			HtmlTop += "<footer><kbd>";

			String BaseDir = System.getProperty("user.dir")+"/target/Temp/";
			boolean objCukeRpt = new File(BaseDir+"feature-overview.html").exists();
			boolean objLog = new File(BaseDir+"Log.html").exists();
			boolean objPrLogRpt = new File(BaseDir+"ProcessedLog.html").exists();
			if(objCukeRpt && objLog && objPrLogRpt){
				String MasterMailContent=HtmlTop;
				//System.out.println(Init.GetMailAddress());
				Calendar c = Calendar.getInstance();
				String DateTime = c.getTime().toString();
				System.out.println(DateTime);
				//Code added for CTI test scenarios to change Subject line pass/fail -vijendra(04/14/2017)
				//String TS=Driver.globalValues.get("SCENARIO");
				//String CTI=Driver.globalValues.get("SUBJECT");
				String CTISUB="TWS "+TS+" IS "+ CTI +"";
				String MailSubject="TWS Automated Test in "+Driver.Init.GetBaseURL()+ " "+System.getProperty("Tag");
				if (Driver.globalValues.get("CompName").contains("ProSoft")||System.getProperty("FetFileName").contains("ProSoft")||Driver.globalValues.get("CompName").contains("CTI")||System.getProperty("FetFileName").contains("CTI")||Driver.globalValues.get("CompName").contains("ZUORA_TEST")||System.getProperty("FetFileName").contains("ZUORA_TEST")||Driver.globalValues.get("CompName").contains("ZuoraTest")||System.getProperty("FetFileName").contains("ZuoraTest")||Driver.globalValues.get("CompName").contains("Production_Connection")||System.getProperty("FetFileName").contains("Production_Connection")) {
					if(Mailer !=null){
						if (CTI.contains("PASS")) {
							Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailID(), CTISUB, MasterMailContent, null);
							System.out.println("Mail Send to --->" + Init.GetMailID());
						}else {
							Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailAddress(), CTISUB, MasterMailContent, null);
							System.out.println("Mail Send to  --->" + Init.GetMailAddress());
						}

					}
				}else {
					if(Mailer !=null){
						Driver.Mailer.send("twsqaautomation@move.com", Init.GetMailAddress(), MailSubject, MasterMailContent, null);
						System.out.println("Mail Send to  --->" + Init.GetMailAddress());
					}	 
				}

			}
			else{
				System.out.println("Unable to Send mail as \"feature-overview.html\"; \"Log.html\"; \"ProcessedLog.html\"; files are not generated");
			}
			System.out.println("::+------------------------+::");
			System.out.println("::||:: END OF EXECUTION ::||::");
			System.out.println("::+------------------------+::");
		}
		catch(Exception ex){
			String StackTraceData = Driver.MethodName();
			System.out.println("Exception @ SendMail and error message " + ex.getMessage()+ " Stack Trace Info"+ StackTraceData.replace("<br>", "/"));
		}
	}







}
