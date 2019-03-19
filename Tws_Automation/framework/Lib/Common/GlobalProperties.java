package Lib.Common;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Dictionary;

import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;

import Lib.Controller.Driver;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.ibm.icu.util.Calendar;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;



/**
 * This is the Global Properties Class in the defFiles.Common package, this is the base class where all the global properties are defined and used.<br> 
 * <font size="3" color="red"><strong><u>Caution:</u> Please be carefull while removing a variable from this class, as they may be used by others in this project </strong></font>
 * @author Krishna Kishore G	krishnakishore.g@move.com
 *
 */
public class GlobalProperties {
	
//	final static String[] FeaturesList = null; 
//	public String[] GetFeatureList(){return FeaturesList;}
//	public void SetFeatureList(String[] FeaturesList){ GlobalProperties.FeaturesList = FeaturesList;}
	
	private int PassCounter = 0;
	public int GetPassCounter(){return PassCounter;}
	public void IncPassCounter(){ this.PassCounter += 1;}
	
	private int FailCounter = 0;
	public int GetFailCounter(){return FailCounter;}
	public void IncFailCounter(){ this.FailCounter += 1;}
	
	private String RunID = "";
	public String GetRunID(){return Driver.globalValues.get("RunID");}
	public void SetRunID(String RunID){ this.RunID = RunID;}
	
	private WebDriver otDriver = null;
	public WebDriver GetWebDriver(){return (WebDriver) Driver.mapper.get(Thread.currentThread().getName());}
	public void SetWebDriver(WebDriver otDriver){ Driver.mapper.put(Thread.currentThread().getName(),otDriver);}
	
	
	private RemoteWebDriver RemoteotDriver = null;
	public RemoteWebDriver GetRemoteWebDriver(){return RemoteotDriver;}
	public void SetRemoteWebDriver(RemoteWebDriver RemoteotDriver){ this.RemoteotDriver = RemoteotDriver;}
	
	
	private String ResultFileAddress = null;
	public String GetResultFileAddress(){return ResultFileAddress;}
	public void SetResultFileAddress(String ResultFileAddress){ this.ResultFileAddress = ResultFileAddress;}
	
	private String SearchCriteria = null;
	//public String GetSearchCriteria(){return SearchCriteria;}
	public String GetSearchCriteria(){return Driver.globalValues.get(Thread.currentThread().getName()+"SearchCriteria");}
	//public void SetSearchCriteria(String SearchCriteria){ this.SearchCriteria = SearchCriteria;}
	public void SetSearchCriteria(String SearchCriteria){Driver.globalValues.put(Thread.currentThread().getName()+"SearchCriteria",SearchCriteria);}
		
	private String InputSearch = null;
	public String GetInputSearch(){return InputSearch;}
	public void SetInputSearch(String InputSearch){ this.InputSearch = InputSearch;}
	
	private String Hometype = null;
	public String GetHometype(){return Hometype;}
	public void SetHometype(String Hometype){ this.Hometype = Hometype;}
	
	private String FunctionBeingExecuted = null;
	public String GetFunctionBeingExecuted(){return FunctionBeingExecuted;}
	public void SetFunctionBeingExecuted(String FunctionBeingExecuted){ this.FunctionBeingExecuted = FunctionBeingExecuted;}
	
	private String MinPrice = null;
	//public String GetMinPrice(){return MinPrice;}
	//public void SetMinPrice(String MinPrice){ this.MinPrice = MinPrice;}
	
	public String GetMinPrice(){return Driver.globalValues.get(Thread.currentThread().getName()+"MinPrice");}
	public void SetMinPrice(String MinPrice){ Driver.globalValues.put(Thread.currentThread().getName()+"MinPrice", MinPrice);}
	
	

	private String MaxPrice = null;
	//public String GetMaxPrice(){return MaxPrice;}
	//public void SetMaxPrice(String MaxPrice){ this.MaxPrice = MaxPrice;}
	public String GetMaxPrice(){return Driver.globalValues.get(Thread.currentThread().getName()+"MaxPrice");}
	public void SetMaxPrice(String MaxPrice){ Driver.globalValues.put(Thread.currentThread().getName()+"MaxPrice", MaxPrice);}
	

	private String Distance = null;
	//public String GetDistance(){return Distance;}
	//public void SetDistance(String Distance){ this.Distance = Distance;}
	
	public String GetDistance(){return Driver.globalValues.get(Thread.currentThread().getName()+"Distance");}
	public void SetDistance(String Distance){ Driver.globalValues.put(Thread.currentThread().getName()+"Distance", Distance);}
	
	private String Beds = null;
	public String GetBeds(){return Driver.globalValues.get(Thread.currentThread().getName()+"Beds");}
	public void SetBeds(String Beds){ Driver.globalValues.put(Thread.currentThread().getName()+"Beds", Beds);}
	
	private String Baths = null;
	//public String GetBaths(){return Baths;}
	//public void SetBaths(String Baths){ this.Baths = Baths;}
	public String GetBaths(){return Driver.globalValues.get(Thread.currentThread().getName()+"Baths");}
	public void SetBaths(String Baths){ Driver.globalValues.put(Thread.currentThread().getName()+"Baths", Baths);}
	
	private int FacetResults=0;
	//public int GetFacetResults(){return FacetResults;}
	//public void SetFacetResults(int FacetResults){ this.FacetResults = FacetResults;}
	
	
	public int GetFacetResults(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"IntFacetRsults"));}
	//public void SetSRPTitleCount(int SRPTitleCount){ this.SRPTitleCount = SRPTitleCount;}
	public void SetFacetResults(int FacetResults){Driver.globalValues.put(Thread.currentThread().getName()+"IntFacetRsults",String.valueOf(FacetResults));}

	
	private int IntegerPart ;
	public int  GetIntegerPart(){return IntegerPart;}
	public void SetIntegerPart(int IntegerPart){ this.IntegerPart = IntegerPart;}
	
	

	private int FirstListPropertyInfo ;
	//public int GetFirstListPropertyInfo(){return FirstListPropertyInfo;}
	public int GetFirstListPropertyInfo(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"FirstListPropertyInfo"));}
	//public void SetFirstListPropertyInfo(int FirstListPropertyInfo){ this.FirstListPropertyInfo = FirstListPropertyInfo;}
	public void SetFirstListPropertyInfo(int FirstListPropertyInfo){Driver.globalValues.put(Thread.currentThread().getName()+"FirstListPropertyInfo",String.valueOf(FirstListPropertyInfo));}
	
	private int SRPTitleCount=0; 
	//public int GetSRPTitleCount(){return SRPTitleCount;}
	public int GetSRPTitleCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"SRPPropertyCount"));}
	//public void SetSRPTitleCount(int SRPTitleCount){ this.SRPTitleCount = SRPTitleCount;}
	public void SetSRPTitleCount(int SRPTitleCount){Driver.globalValues.put(Thread.currentThread().getName()+"SRPPropertyCount",String.valueOf(SRPTitleCount));}

	private int RandomNo = 0;
	public int GetRandomNo(){return RandomNo;}
	public void SetRandomNo(int RandomNo){ this.RandomNo = RandomNo;}
	
	
	private String[][] EventsAreEasyTohandle = null;
	public String[][] GetEventsAreEasyTohandle(){return EventsAreEasyTohandle;}
	public void SetEventsAreEasyTohandle(String[][] EventsAreEasyTohandle){ this.EventsAreEasyTohandle = EventsAreEasyTohandle;}
	
	private List<String> EDWEvents = null;
	public List<String> GetEDWEvents(){return EDWEvents;}
	public void SetEDWEvents(List<String> EDWEvents){ this.EDWEvents = EDWEvents;}
	
	private List<String> ErrorImgNames = new ArrayList<String>();
	public List<String> GetErrorImgNamesList(){return ErrorImgNames;}
	public void AddErrorImgNamesInList(String ImgName){ ErrorImgNames.add(ImgName);}
	
	private List<String> SEOLinkCheckResults = new ArrayList<String>();
	public List<String> GetSEOLinkerCheckerResults(){return SEOLinkCheckResults;}
	public void AddSEOLinkCheckResult(String fileName){ SEOLinkCheckResults.add(fileName);}
	
	private File[] Fetaurefiles = null;
	public File[] GetFetaurefiles(){return Fetaurefiles;}
	public void SetFetaurefiles(File[] Fetaurefiles){ this.Fetaurefiles = Fetaurefiles;}
		
	private int MessageCount ;
	public int GetMessageCount(){return MessageCount;}
	public void SetMessageCount(int MessageCount){ this.MessageCount = MessageCount;}
	
	private boolean ShowcaseListingFlag = false;
	public boolean GetShowcaseListingFlag(){return ShowcaseListingFlag;}
	public void SetShowcaseListingFlag(boolean ShowcaseListingFlag){ this.ShowcaseListingFlag = ShowcaseListingFlag;}
	
	private boolean IsRTZExists = false;
	public boolean GetIsRTZExists(){return IsRTZExists;}
	public void SetIsRTZExists(boolean IsRTZExists){ this.IsRTZExists = IsRTZExists;}
	
	
	private boolean IsSEOExists = false;
	public boolean GetIsSEOExists(){return IsSEOExists;}
	public void SetIsSEOExists(boolean IsSEOExists){ this.IsSEOExists = IsSEOExists;}
		
	private boolean BasicLisitingExist =false;
	public boolean GetBasicLisitingExist(){return BasicLisitingExist;}
	public void SetBasicLisitingExist(boolean BasicLisitingExist){ this.BasicLisitingExist = BasicLisitingExist;}
	
	

	private boolean ShowcaseLisitingExist =false;
	public boolean GetShowcaseLisitingExist(){return ShowcaseLisitingExist;}
	public void SetShowcaseLisitingExist(boolean ShowcaseLisitingExist){ this.ShowcaseLisitingExist = ShowcaseLisitingExist;}
	

	private boolean SRPTitleExist =false;
	public boolean GetSRPTitleExist(){return SRPTitleExist;}
	public void SetSRPTitleExist(boolean SRPTitleExist){ this.SRPTitleExist = SRPTitleExist;}
	

	private String SRPPropertyCount =null;
	//public String GetSRPPropertyCount(){return SRPPropertyCount;}
	//public void SetSRPPropertyCount(String SRPPropertyCount){ this.SRPPropertyCount = SRPPropertyCount;}
	
	
	public String GetSRPPropertyCount(){return Driver.globalValues.get(Thread.currentThread().getName()+"SRPTitle");}
	//public void SetSRPTitleCount(int SRPTitleCount){ this.SRPTitleCount = SRPTitleCount;}
	public void SetSRPPropertyCount(String SRPPropertyCount){Driver.globalValues.put(Thread.currentThread().getName()+"SRPTitle",SRPPropertyCount);}

	
	private boolean FeaturedLisitingExist =false;
	public boolean GetFeaturedLisitingExist(){return FeaturedLisitingExist;}
	public void SetFeaturedLisitingExist(boolean FeaturedLisitingExist){ this.FeaturedLisitingExist = FeaturedLisitingExist;}
	

	private String[] FacetLocationDistance = {"0 Mile","1 Mile","5 Miles","10 Miles","20 Miles"};
	public String[] GetFacetLocationDistance(){return FacetLocationDistance;}
	public void SetFacetLocationDistance(String[] FacetLocationDistance){ this.FacetLocationDistance = FacetLocationDistance;}
	
	
	
	private PrintWriter PW=null;
	public PrintWriter GetPW(){return PW;}
	public void SetPW(PrintWriter PW){ this.PW = PW;}
	
	private PrintWriter PWriter=null;
	public PrintWriter GetPWriter(){return PWriter;}
	public void SetPWriter(PrintWriter PWriter){ this.PWriter = PWriter;}
	
	private boolean FeaturefileTag =false;
	public boolean GetFeaturefileTag(){return FeaturefileTag;}
	public void SetFeaturefileTag(boolean FeaturefileTag){ this.FeaturefileTag = FeaturefileTag;}
	
	private String FeaturefileName =null;
	public String GetFeaturefileName(){return FeaturefileName;}
	public void SetFeaturefileName(String FeaturefileName){ this.FeaturefileName = FeaturefileName;}
	
	
	private String CucumberreportPath =null;
	public String GetCucumberreportPath(){return CucumberreportPath;}
	public void SetCucumberreportPath(String CucumberreportPath){ this.CucumberreportPath = CucumberreportPath;}
	
	
	private boolean FlagAntExecution =false;
	public boolean GetFlagAntExecution(){return FlagAntExecution;}
	public void SetFlagAntExecution(boolean FlagAntExecution){ this.FlagAntExecution = FlagAntExecution;}
	
	private List<String> PassMsg = new ArrayList<>();
	private List<String> FailMsg = new ArrayList<>();
	private List<String> WarnMsg = new ArrayList<>();
	private List<String> DoneMsg = new ArrayList<>();
	private List<String> NAMsg = new ArrayList<>();
	public List<String> GetPassMsg(){return PassMsg;}
	public void SetPassMsg(String Var1){ this.PassMsg.add(Var1);}
	public List<String> GetFailMsg(){return FailMsg;}
	public void SetFailMsg(String Var1){ this.FailMsg.add(Var1);}
	public List<String> GetWarnMsg(){return WarnMsg;}
	public void SetWarnMsg(String Var1){ this.WarnMsg.add(Var1);}
	public List<String> GetDoneMsg(){return DoneMsg;}
	public void SetDoneMsg(String Var1){ this.DoneMsg.add(Var1);}
	public List<String> GetNAMsg(){return NAMsg;}
	public void SetNAMsg(String Var1){ this.NAMsg.add(Var1);}
	
	private String NodeIPAddress = "";
	public String GetNodeIPAddress(){return Driver.globalValues.get(Thread.currentThread().getName()+"NodeIPAddress");}
	public void SetNodeIPAddress(String NodeIPAddress){Driver.globalValues.put(Thread.currentThread().getName()+"NodeIPAddress",NodeIPAddress);}
	
	private int Var1 = 0;
	public int GetVar1int(){return Var1;}
	public void SetVar1int(int Var1){ this.Var1 = Var1;}
	
	private int Var2 = 0;
	public int GetVar2int(){return Var2;}
	public void SetVar2int(int Var2){ this.Var2 = Var2;}
	
	private int Var3 = 0;
	public int GetVar3int(){return Var3;}
	public void SetVar3int(int Var3){ this.Var3 = Var1;}
	
	private int Var4 = 0;
	public int GetVar4int(){return Var4;}
	public void SetVar4int(int Var4){ this.Var4 = Var4;}
	
	private int Var5 = 0;
	public int GetVar5int(){return Var5;}
	public void SetVar5int(int Var5){ this.Var5 = Var5;}
	
	private String sVar1 = "";
	public String GetVar1String(){return sVar1;}
	public void SetVar1String(String sVar1){ this.sVar1 = sVar1;}
		
	private String sVar2 = "";
	public String GetVar2String(){return sVar2;}
	public void SetVar2String(String sVar2){ this.sVar2 = sVar2;}
	
	private String sVar3 = "";
	public String GetVar3String(){return sVar3;}
	public void SetVar3String(String sVar3){ this.sVar3 = sVar1;}
	
	private String sVar4 = "";
	public String GetVar4String(){return sVar4;}
	public void SetVar4String(String sVar4){ this.sVar4 = sVar4;}
	
	private String sVar5 = "";
	public String GetVar5String(){return sVar5;}
	public void SetVar5String(String sVar5){ this.sVar5 = sVar5;}

	private String[] saVar1 = null;
	public String[] GetVar1Stringarray(){return saVar1;}
	public void SetVar1Stringarray(String[] saVar1){ this.saVar1 = saVar1;}

	private String[] saVar2 = null;
	public String[] GetVar2Stringarray(){return saVar2;}
	public void SetVar2Stringarray(String[] saVar2){ this.saVar2 = saVar2;}

	private String[] saVar3 = null;
	public String[] GetVar3Stringarray(){return saVar3;}
	public void SetVar3Stringarray(String[] saVar3){ this.saVar3 = saVar3;}

	private String[] saVar4 = null;
	public String[] GetVar4Stringarray(){return saVar4;}
	public void SetVar4Stringarray(String[] saVar4){ this.saVar4 = saVar4;}

	private String[] saVar5 = null;
	public String[] GetVar5Stringarray(){return saVar5;}
	public void SetVar5Stringarray(String[] saVar5){ this.saVar5 = saVar5;}
	
	private String srpFirstAddress = "";
//	public String GetSRPAddress(){return srpFirstAddress;}
//	public void SetSRPAddress(String srpFirstAddress){ this.srpFirstAddress = srpFirstAddress;}
	public String GetSRPAddress(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstAddress");}
	public void SetSRPAddress(String srpFirstAddress){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstAddress",srpFirstAddress);}
	
	private String srpFirstListBed = "";
//	public String GetSRPFirstListBed(){return srpFirstListBed;}
//	public void SetSRPFirstListBed(String srpFirstListBed){ this.srpFirstListBed = srpFirstListBed;}
	public String GetSRPFirstListBed(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListBed");}
	public void SetSRPFirstListBed(String srpFirstListBed){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListBed", srpFirstListBed);}
	
	private String srpFirstListBath = "";
//	public String GetSRPFirstListBath(){return srpFirstListBath;}
//	public void SetSRPFirstListBath(String srpFirstListBath){ this.srpFirstListBath = srpFirstListBath;}
	public String GetSRPFirstListBath(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListBath");}
	public void SetSRPFirstListBath(String srpFirstListBath){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListBath", srpFirstListBath);}
	
	private String srpFirstListPrice = "";
//	public String GetSRPFirstListPrice(){return srpFirstListPrice;}
//	public void SetSRPFirstListPrice(String srpFirstListPrice){ this.srpFirstListPrice = srpFirstListPrice;}
	public String GetSRPFirstListPrice(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListPrice");}
	public void SetSRPFirstListPrice(String srpFirstListPrice){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListPrice", srpFirstListPrice);}	
	
	private String srpFirstListHouseSize = "";
//	public String GetSRPFirstListHouseSize(){return srpFirstListHouseSize;}
//	public void SetSRPFirstListHouseSize(String srpFirstListHouseSize){ this.srpFirstListHouseSize = srpFirstListHouseSize;}
	public String GetSRPFirstListHouseSize(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListHouseSize");}
	public void SetSRPFirstListHouseSize(String srpFirstListHouseSize){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListHouseSize", srpFirstListHouseSize);}
	
	private String srpFirstListLotSize = "";
//	public String GetSRPFirstListLotSize(){return srpFirstListLotSize;}
//	public void SetSRPFirstListLotSize(String srpFirstListLotSize){ this.srpFirstListLotSize = srpFirstListLotSize;}	
	public String GetSRPFirstListLotSize(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListLotSize");}
	public void SetSRPFirstListLotSize(String srpFirstListLotSize){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListLotSize", srpFirstListLotSize);}
	
	private String srpFirstListPhotoCount = "";
	public String GetSRPFirstListPhotoCount(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirstListPhotoCount");}
	public void SetSRPFirstListPhotoCount(String srpFirstListPhotoCount){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirstListPhotoCount", srpFirstListPhotoCount);}
	
	private String trafficData = "";
	public String GettrafficData(){return Driver.globalValues.get(Thread.currentThread().getName()+"trafficData");}
	public void SettrafficData(String trafficData){ Driver.globalValues.put(Thread.currentThread().getName()+"trafficData", trafficData);}
	
	private String[][] dbValue = null;
	public String[][] GetdbValue(){return dbValue;}
	public void SetdbValue(String[][] dbValue){ this.dbValue = dbValue;}
	
	private boolean checkcount=true;
	public boolean Getcheckcount(){return checkcount;}
	public void Setcheckcount(boolean checkcount){ this.checkcount = checkcount;}

	private int startDate = 0;
	public int GetStartDate(){return startDate;}
	public void SetStartDate(int startDate){ this.startDate = startDate;}
	public void SetStartDate(Date startDate) {
		Calendar c = Calendar.getInstance();
		c.setTime( startDate );
		this.startDate = c.get( Calendar.YEAR ) * 10000 + ( c.get( Calendar.MONTH ) + 1 ) * 100 + c.get( Calendar. DAY_OF_MONTH );
	}
	
	private int endDate = 0;
	public int GetEndDate(){return endDate;}
	public void SetEndDate(int endDate){ this.endDate = endDate;}
	public void SetEndDate(Date endDate) {
		Calendar c = Calendar.getInstance();
		c.setTime( endDate );
		this.endDate = c.get( Calendar.YEAR ) * 10000 + ( c.get( Calendar.MONTH ) + 1 ) * 100 + c.get( Calendar. DAY_OF_MONTH );
	}
	
	private String mongoDBValue = null;
	public String GetMongodbValue(){return mongoDBValue;}
//	public void SetMongodbValue(String mongoDBValue){ this.mongoDBValue = mongoDBValue;}
	public void SetMongodbValue(String value){ this.mongoDBValue = value;}
	
	private String officeId = null;
	public String GetOfficeId(){return officeId;}
	public void SetOfficeId(String value){ this.officeId = value;}
	
	private String officeProduct = null;
	public String GetOfficeProduct() { return officeProduct; }
	public void SetOfficeProduct(String value) { this.officeProduct = value; }
	
	private Set<String> windowsid=null;
    public Set<String> GetWindowsID(){return windowsid;}
    public void SetWindowsID(Set<String> windowsid){ this.windowsid = windowsid;}
    
    private boolean flag=false;
	public boolean GetCheckRightRail(){return flag;}
	public void SetCheckRightRail(boolean flag){ this.flag = flag;}
	
	private String firstName=null;
	public String GetFirstName(){return Driver.globalValues.get(Thread.currentThread().getName()+"firstName");}
	public void SetFirstName(String firstName){ Driver.globalValues.put(Thread.currentThread().getName()+"firstName", firstName);}
	
	private boolean flagOne=false;
	public boolean GetCheckMCC(){return flagOne;}
	public void SetCheckMCC(boolean flagOne){ this.flagOne = flagOne;}
	
	private String moveFrom="";
	public String GetMoveFrom(){return moveFrom;}
	public void SetMoveFrom(String moveFrom){ this.moveFrom = moveFrom;}
	
	private String moveTo="";
	public String GetMoveTo(){return moveTo;}
	public void SetMoveTo(String moveTo){ this.moveTo = moveTo;}

	private boolean rentalsStateWid=true;
	public boolean GetRentalsStateWid(){return rentalsStateWid;}
	public void SetRentalsStateWid(boolean rentalsStateWid){ this.rentalsStateWid = rentalsStateWid;}
	
	private boolean rentalsStateLink=true;
	public boolean GetRentalsStateLink(){return rentalsStateLink;}
	public void SetRentalsStateLink(boolean rentalsStateLink){ this.rentalsStateLink = rentalsStateLink;}
	
	private String state="";
	public String GetRentalsState(){return state;}
	public void SetRentalsState(String state){ this.state = state;}

	
	
	
	private boolean marketviewsummary=false;
	public boolean Getmarketviewsummary(){return marketviewsummary;}
	public void Setmarketviewsummary(boolean marketviewsummary){ this.marketviewsummary = marketviewsummary;}
	
	private String strHomesCnt=null;
	public String GetstrHomesCnt(){return strHomesCnt;}
	public void SetstrHomesCnt(String strHomesCnt){ this.strHomesCnt = strHomesCnt;}
	
	private boolean supportHome=false;
	public boolean GetsupportHome(){return supportHome;}
	public void SetsupportHome(boolean supportHome){ this.supportHome = supportHome;}
	
	private boolean nearbywidget=false;
	public boolean Getnearbywidget(){return nearbywidget;}
	public void Setnearbywidget(boolean nearbywidget){ this.nearbywidget = nearbywidget;}
	
	private boolean fcmaloc =false;
	public boolean Getfcmaloc(){return fcmaloc;}
	public void Setfcmaloc(boolean fcmaloc){ this.fcmaloc = fcmaloc;}
	
	private boolean NCFCMA =false;
	public boolean GetNCFCMA(){return NCFCMA;}
	public void SetNCFCMA(boolean NCFCMA){ this.NCFCMA = NCFCMA;}
	
	private boolean localmil =false;
	public boolean Getlocalmil(){return localmil;}
	public void Setlocalmil(boolean localmil){ this.localmil = localmil;}
	
	private boolean nearbycityFR =false;
	public boolean GetnearbycityFR(){return nearbycityFR;}
	public void SetnearbycityFR(boolean nearbycityFR){ this.nearbycityFR = nearbycityFR;}
	
	private boolean locationmap=false;
	public boolean Getlocationmap(){return locationmap;}
	public void Setlocationmap(boolean locationmap){ this.locationmap = locationmap;}
	
	private boolean overviewtabcontent=true;
	public boolean Getoverviewtabcontent(){return overviewtabcontent;}
	public void Setoverviewtabcontent(boolean overviewtabcontent){ this.overviewtabcontent = overviewtabcontent;}
	
	private boolean schoolsNeighborhood =false;
	public boolean GetSchoolsNeighborhood(){return schoolsNeighborhood;}
	public void SetSchoolsNeighborhood(boolean schoolsNeighborhood){ this.schoolsNeighborhood = schoolsNeighborhood;}
	
	private boolean nearbypins =false;
	public boolean Getnearbypins(){return nearbypins;}
	public void Setnearbypins(boolean nearbypins){ this.nearbypins = nearbypins;}
	
	private String ldppinsadress ="";
	public String Getldppinsadress(){return ldppinsadress;}
	public void Setldppinsadress(String ldppinsadress){ this.ldppinsadress = ldppinsadress;}
	
	private String ldppinprice ="";
	public String Getldppinprice(){return ldppinprice;}
	public void Setldppinprice(String ldppinprice){ this.ldppinprice = ldppinprice;}
	
	private String ldppinbed ="";
	public String Getldppinbed(){return ldppinbed;}
	public void Setldppinbed(String ldppinbed){ this.ldppinbed = ldppinbed;}
	
	private String ldppinbath ="";
	public String Getldppinbath(){return ldppinbath;}
	public void Setldppinbath(String ldppinbath){ this.ldppinbath = ldppinbath;}
	
	private String ldppinlotsize ="";
	public String Getldppinlotsize(){return ldppinlotsize;}
	public void Setldppinlotsize(String ldppinlotsize){ this.ldppinlotsize = ldppinlotsize;}
	
	private String ldppinacresize ="";
	public String Getldppinacresize(){return ldppinacresize;}
	public void Setldppinacresize(String ldppinacresize){ this.ldppinacresize = ldppinacresize;}
	
	private String ldpphotocount ="";
	public String Getldpphotocount(){return ldpphotocount;}
	public void Setldpphotocount(String ldpphotocount){ this.ldpphotocount = ldpphotocount;}
	
	private boolean ldppinprtydtl =false;
	public boolean Getldppinprtydtl(){return ldppinprtydtl;}
	public void Setldppinprtydtl(boolean ldppinprtydtl){ this.ldppinprtydtl = ldppinprtydtl;}
	
	private boolean execLinkChecker =false;
	public boolean GetexecLinkChecker(){return execLinkChecker;}
	public void SetexecLinkChecker(boolean execLinkChecker){ this.execLinkChecker = execLinkChecker;}
	private boolean cdplisting =false;
	public boolean Getcdplisting(){return cdplisting;}
	public void Setcdplisting(boolean cdplisting){ this.cdplisting = cdplisting;}
	
	private String cdpBed ="";
	public String GetcdpBed(){return cdpBed;}
	public void SetcdpBed(String cdpBed){ this.cdpBed = cdpBed;}
	
	private String cdpBath ="";
	public String GetcdpBath(){return cdpBath;}
	public void SetcdpBath(String cdpBath){ this.cdpBath = cdpBath;}
	
	private String PlanName ="";
	public String GetPlanName(){return PlanName;}
	public void SetPlanName(String PlanName){ this.PlanName = PlanName;}
	
	private String SqFt ="";
	public String GetSqFt(){return SqFt;}
	public void SetSqFt(String SqFt){ this.SqFt = SqFt;}
	
	private String Garages ="";
	public String GetGarages(){return Garages;}
	public void SetGarages(String Garages){ this.Garages = Garages;}
	
	private String availability ="";
	public String Getavailability(){return availability;}
	public void Setavailability(String availability){ this.availability = availability;}
	
	private String price ="";
	public String Getprice(){return price;}
	public void Setprice(String price){ this.price = price;}
	
	private String photocount ="";
	public String Getphotocount(){return photocount;}
	public void Setphotocount(String photocount){ this.photocount = photocount;}
	
	private boolean agentCardLink =false;
	public boolean GetAgentCardLink(){return agentCardLink;}
	public void SetAgentCardLink(boolean agentCardLink){ this.agentCardLink = agentCardLink;}
	
	private boolean CheckRPS =false;
	public boolean GetCheckRPS(){return CheckRPS;}
	public void SetCheckRPS(boolean CheckRPS){ this.CheckRPS = CheckRPS;}
	
	
	private boolean assertflag =true;
	//public boolean Getassertflag(){return assertflag;}
	//public void Setassertflag(boolean assertflag){ this.assertflag = assertflag;}
	public boolean Getassertflag(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"AssertFlag"));}
	public void Setassertflag(boolean assertflag){ Driver.globalValues.put(Thread.currentThread().getName()+"AssertFlag",String.valueOf(assertflag));}
	private ArrayList<String> verificationFailures=new ArrayList<String>();
	public ArrayList<String> GetverificationFailures(){return verificationFailures;}
	public void SetverificationFailures(ArrayList<String> verificationFailures){ this.verificationFailures = verificationFailures;}
	
	private boolean agentOtherListing =true;
	public boolean GetAgentOtherList(){return agentOtherListing;}
	public void SetAgentOtherList(boolean agentOtherListing){ this.agentOtherListing = agentOtherListing;}
	
	private String strAgentOtherLink ="";
	public String GetAgentOtherLink(){return strAgentOtherLink;}
	public void SetAgentOtherLink(String strAgentOtherLink){ this.strAgentOtherLink = strAgentOtherLink;}
	
	private String srpSecondLink ="";
	public String GetSRPSecondLink(){return srpSecondLink;}
	public void SetSRPSecondLink(String srpSecondLink){ this.srpSecondLink = srpSecondLink;}
	
	private boolean homesNear =true;
	public boolean GetHomesNearTable(){return homesNear;}
	public void SetHomesNearTable(boolean homesNear){ this.homesNear = homesNear;}
	
	private String strHomesNearLink ="";
	public String GetHomesNearLink(){return strHomesNearLink;}
	public void SetHomesNearLink(String strHomesNearLink){ this.strHomesNearLink = strHomesNearLink;}
	
	private boolean ldpFooter =true;
	public boolean GetLDPFooter(){return ldpFooter;}
	public void SetLDPFooter(boolean ldpFooter){ this.ldpFooter = ldpFooter;}
	
	private boolean rmdTab =true;
	public boolean GetRMDTab(){return rmdTab;}
	public void SetRMDTab(boolean rmdTab){ this.rmdTab = rmdTab;}
	
	private String userName ="";
	public String GetUserName(){return userName;}
	public void SetUserName(String userName){ this.userName = userName;}
	

	private boolean estimatemonthlypayment =false;
	public boolean Getestimatemonthlypayment(){return estimatemonthlypayment;}
	public void Setestimatemonthlypayment(boolean estimatemonthlypayment){ this.estimatemonthlypayment = estimatemonthlypayment;}
	
	private boolean Estimatemypayment =false;
	public boolean GetEstimatemypayment(){return Estimatemypayment;}
	public void SetEstimatemypayment(boolean Estimatemypayment){ this.Estimatemypayment = Estimatemypayment;}
	
	private boolean FreeMovingQuote =false;
	public boolean GetFreeMovingQuote(){return FreeMovingQuote;}
	public void SetFreeMovingQuote(boolean FreeMovingQuote){ this.FreeMovingQuote = FreeMovingQuote;}
	
	private String signUpUserName ="";
	public String GetSignUpUserName(){return Driver.globalValues.get(Thread.currentThread().getName()+"signUpUserName");}
	public void SetSignUpUserName(String signUpUserName){ Driver.globalValues.put(Thread.currentThread().getName()+"signUpUserName", signUpUserName);}
	
	
	private String LogInUserName = null;
	public String GetLogInUserName(){return Driver.globalValues.get(Thread.currentThread().getName()+"LogInUserName");}
	public void SetLogInUserName(String LogInUserName){ Driver.globalValues.put(Thread.currentThread().getName()+"LogInUserName",LogInUserName); }
	
	
	
	
	private String signUpYSUserName ="";
	public String GetSignUpYSUserName(){return Driver.globalValues.get(Thread.currentThread().getName()+"signUpYSUserName");}
	public void SetSignUpYSUserName(String signUpYSUserName){Driver.globalValues.put(Thread.currentThread().getName()+"signUpYSUserName",signUpYSUserName);}
	
	private String signInYSUserName ="";
	public String GetSignInYSUserName(){return Driver.globalValues.get(Thread.currentThread().getName()+"signInYSUserName");}
	public void SetSignInYSUserName(String signInYSUserName){ Driver.globalValues.put(Thread.currentThread().getName()+"signInYSUserName",signInYSUserName);}
	
	private String srpBreadCrumb ="";
	public String GetsrpBreadCrumb(){return srpBreadCrumb;}
	public void SetsrpBreadCrumb(String srpBreadCrumb){ this.srpBreadCrumb = srpBreadCrumb;}
	
	
	private boolean cancellink =false;
	public boolean Getcancellink(){return cancellink;}
	public void Setcancellink(boolean cancellink){ this.cancellink = cancellink;}
	
	private String ldpAgentName ="";
	public String GetLdpAgentName(){return ldpAgentName;}
	public void SetLdpAgentName(String ldpAgentName){ this.ldpAgentName = ldpAgentName;}
	
	private boolean officeCardLink =false;
	public boolean GetOfficeCardLink(){return officeCardLink;}
	public void SetOfficeCardLink(boolean officeCardLink){ this.officeCardLink = officeCardLink;}
	
	private boolean requestmoredetailstab =false;
	public boolean Getrequestmoredetailstab(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"requestmoredetailstab"));}
	public void Setrequestmoredetailstab(boolean requestmoredetailstab){ Driver.globalValues.put(Thread.currentThread().getName()+"requestmoredetailstab", String.valueOf(requestmoredetailstab));}
	
	private HashMap<String, String> SRPMapVariables = new HashMap<>();
	public void AddToSRPMapVariables(String Key, String Value){this.SRPMapVariables.put(Thread.currentThread().getName()+Key, Value);}
	public String GetFromSRPMapVariables(String Key){return this.SRPMapVariables.get(Thread.currentThread().getName()+Key);}
	public void RemoveSRPMapVariables(String Key){this.SRPMapVariables.remove(Key);}
	private HashMap<String, String> srpProp = new HashMap<>();
//	public HashMap<String, String> GetEnvironment(){return Environment;}
//	public void SetEnvironment(HashMap<String, String> Environment){ this.Environment = Environment;}
	public void AddToSrpProp(String Key, String Value){this.srpProp.put(Thread.currentThread().getName()+Key.trim().toLowerCase(), Value);}
	public String GetsrpProp(String Key){return this.srpProp.get(Thread.currentThread().getName()+Key.trim().toLowerCase());}
	
	private boolean nextLink =false;
	public boolean GetNextLink(){return nextLink;}
	public void SetNextlink(boolean nextLink){ this.nextLink = nextLink;}
	
	private boolean prevLink =false;
	public boolean GetPrevLink(){return prevLink;}
	public void SetPrevlink(boolean prevLink){ this.prevLink = prevLink;}
	
	private int count =0;
	public int GetCount(){return count;}
	public void SetCount(int count){ this.count = count;}
	
//	private boolean noProp =false;
//	public boolean GetProp(){return noProp;}
//	public void SetProp(boolean noProp){ this.noProp = noProp;}
	
	private int srpCount = 0;
	public int GetSrpCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"srpCount"));}
	public void SetSrpCount(int srpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"srpCount",String.valueOf(srpCount)); }
	
	private HashMap<String, List<String>> CukeFeatureFiles = new HashMap<>();
	public List<String> GetFeatureFiles(String Key){return CukeFeatureFiles.get(Key);}
	public HashMap<String, List<String>> GetFeatureFiles(){return CukeFeatureFiles;}
	public void SetFeatureFiles(String Key, List<String> Valye){CukeFeatureFiles.put(Key, Valye);}
	
	private HashMap<String, List<Object>> CukeFilters = new HashMap<>();
	public List<Object> GetCukeFilters(String Key){return CukeFilters.get(Key);}
	public HashMap<String, List<Object>> GetCukeFilters(){return CukeFilters;}
	public void SetCukeFilters(String Key, List<Object> Valye){CukeFilters.put(Key, Valye);}
	
	private HashMap<String, List<String>> CukeGlues = new HashMap<>();
	public List<String> GetCukeGlues(String Key){return CukeGlues.get(Key);}
	public HashMap<String, List<String>> GetCukeGlues(){return CukeGlues;}
	public void SetCukeGlues(String Key, List<String> Valye){CukeGlues.put(Key, Valye);}
	
	private String PropOption = "", PropPage = "";
	public String GetPropOption(){return PropOption;}
	public void SetPropOption(String PropOption){ this.PropOption = PropOption;}
	public String GetPropPage(){return PropPage;}
	public void SetPropPage(String PropPage){ this.PropPage = PropPage;}
	
	private String broVersion = "";
	public String GetBrowserVersion(){return broVersion; }
	public void setBrowserVersion(String broVersion){
		if(this.broVersion.contains(broVersion)){}
		else{
			this.broVersion += broVersion + ", ";
		}
	}
	
	private String OSNames = "";
	public String GetOSNames(){return OSNames; }
	public void setOSNames(String OSNames){
		if(this.OSNames.contains(OSNames)){}
		else{
			this.OSNames += OSNames + ", ";
		}
	}
	
//	private int TruliaSrpCount = 0;
//	public int GetTruliaSrpCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"TruliaSrpCount"));}
//	public void SetTruliaSrpCount(int TruliaSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"TruliaSrpCount",String.valueOf(TruliaSrpCount)); }
//	
//	private int ZillowSrpCount = 0;
//	public int GetZillowSrpCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"ZillowSrpCount"));}
//	public void SetZillowSrpCount(int ZillowSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"ZillowSrpCount",String.valueOf(ZillowSrpCount)); }
//	
//	private int RealtorSrpCount = 0;
//	public int GetRealtorSrpCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"RealtorSrpCount"));}
//	public void SetRealtorSrpCount(int RealtorSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"RealtorSrpCount",String.valueOf(RealtorSrpCount)); }
	
	private String TruliaSrpCount = "";
	public String GetTruliaSrpCount(){return Driver.globalValues.get(Thread.currentThread().getName()+"TruliaSrpCount");}
	public void SetTruliaSrpCount(String TruliaSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"TruliaSrpCount",TruliaSrpCount); }
	
	private String ZillowSrpCount = "";
	public String GetZillowSrpCount(){return Driver.globalValues.get(Thread.currentThread().getName()+"ZillowSrpCount");}
	public void SetZillowSrpCount(String ZillowSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"ZillowSrpCount",ZillowSrpCount); }
	
	private String RealtorSrpCount = "";
	public String GetRealtorSrpCount(){return Driver.globalValues.get(Thread.currentThread().getName()+"RealtorSrpCount");}
	public void SetRealtorSrpCount(String RealtorSrpCount){ Driver.globalValues.put(Thread.currentThread().getName()+"RealtorSrpCount",RealtorSrpCount); }
	
	private String drivingdirections ="";
	public String Getdrivingdirections(){return Driver.globalValues.get(Thread.currentThread().getName()+"drivingdirections");}
	public void Setdrivingdirections(String drivingdirections){ Driver.globalValues.put(Thread.currentThread().getName()+"drivingdirections",drivingdirections); }
	
	private String cityState = "";
	public String GetCityState(){return Driver.globalValues.get(Thread.currentThread().getName()+"cityState");}
	public void SetCityState(String cityState){ Driver.globalValues.put(Thread.currentThread().getName()+"cityState",cityState); }
	
	private String propType = "";
	public String GetPropType(){return Driver.globalValues.get(Thread.currentThread().getName()+"propType");}
	public void SetPropType(String propType){ Driver.globalValues.put(Thread.currentThread().getName()+"propType",propType); }
	
	
	private int DateFromGUIAboveListView = 0;
	public int GetDateFromGUIAboveListView(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"DateFromGUIAboveListView"));}
	public void SetDateFromGUIAboveListView(int DateFromGUIAboveListView){ Driver.globalValues.put(Thread.currentThread().getName()+"DateFromGUIAboveListView",String.valueOf(DateFromGUIAboveListView)); }
		
	
	private boolean IsDisplayCustomizedFlag =false;
	public boolean GetIsDisplayCustomizedFlag(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"IsDisplayCustomizedFlag"));}
	public void SetIsDisplayCustomizedFlag(boolean IsDisplayCustomizedFlag){ Driver.globalValues.put(Thread.currentThread().getName()+"IsDisplayCustomizedFlag",String.valueOf(IsDisplayCustomizedFlag));}
		


	private boolean IsObjectClickedFlag =false;
	public boolean GetIsObjectClickedFlag(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"IsObjectClickedFlag"));}
	public void SetIsObjectClickedFlag(boolean IsObjectClickedFlag){ Driver.globalValues.put(Thread.currentThread().getName()+"IsObjectClickedFlag",String.valueOf(IsObjectClickedFlag));}
		

	private boolean FlagObjectClear =false;
	public boolean GetFlagObjectClear(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"FlagObjectClear"));}
	public void SetFlagObjectClear(boolean FlagObjectClear){ Driver.globalValues.put(Thread.currentThread().getName()+"FlagObjectClear",String.valueOf(FlagObjectClear));}
	
	
	private String LocationBoxisAt = null;
	public String GetLocationBoxisAt(){return Driver.globalValues.get(Thread.currentThread().getName()+"LocationBoxisAt");}
	public void SetLocationBoxisAt(String LocationBoxisAt){ Driver.globalValues.put(Thread.currentThread().getName()+"LocationBoxisAt",LocationBoxisAt); }
	
	private String textFilePath = "";
	//public String GetSearchCriteria(){return SearchCriteria;}
	public String getTextFilePath(){return Driver.globalValues.get(Thread.currentThread().getName()+"textFilePath");}
	//public void SetSearchCriteria(String SearchCriteria){ this.SearchCriteria = SearchCriteria;}
	public void setTextFilePath(String textFilePath){Driver.globalValues.put(Thread.currentThread().getName()+"textFilePath",textFilePath);}
	

	private String HeaderSearchHomeType = null;
	public String GetHeaderSearchHomeType(){return Driver.globalValues.get(Thread.currentThread().getName()+"HeaderSearchHomeType");}
	public void SetHeaderSearchHomeType(String HeaderSearchHomeType){ Driver.globalValues.put(Thread.currentThread().getName()+"HeaderSearchHomeType",HeaderSearchHomeType); }
	
	private String PageName = null;
	public String GetPageName(){return Driver.globalValues.get(Thread.currentThread().getName()+"PageName");}
	public void SetPageName(String PageName){ Driver.globalValues.put(Thread.currentThread().getName()+"PageName",PageName); }
	
	private String HtmalValue = "";
	public String GetHtmlValue(){return Driver.globalValues.get(Thread.currentThread().getName()+"HtmalValue");}
	public void SetHtmlValue(String HtmalValue){ Driver.globalValues.put(Thread.currentThread().getName()+"HtmalValue",HtmalValue);}
	
	private String srpFirst = "";
//	public String GetSRPAddress(){return srpFirstAddress;}
//	public void SetSRPAddress(String srpFirstAddress){ this.srpFirstAddress = srpFirstAddress;}
	public String GetsrpFirstLink(){return Driver.globalValues.get(Thread.currentThread().getName()+"srpFirst");}
	public void SetsrpFirstLink(String srpFirst){ Driver.globalValues.put(Thread.currentThread().getName()+"srpFirst",srpFirst);}
	
	private String input = "";
//	public String GetSRPAddress(){return srpFirstAddress;}
//	public void SetSRPAddress(String srpFirstAddress){ this.srpFirstAddress = srpFirstAddress;}
	public String GetAddForRecentSearch(){return Driver.globalValues.get(Thread.currentThread().getName()+"input");}
	public void SetAddForRecentSearch(String input){ Driver.globalValues.put(Thread.currentThread().getName()+"input",input);}
	

	private boolean FlagLinkingModule =false;
	public boolean GetFlagLinkingModule(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"FlagLinkingModule"));}
	public void SetFlagLinkingModule(boolean FlagLinkingModule){ Driver.globalValues.put(Thread.currentThread().getName()+"FlagLinkingModule",String.valueOf(FlagLinkingModule));}
	
	
	private String LinkingModuleLinkText = "";
	public String GetLinkingModuleLinkText(){return Driver.globalValues.get(Thread.currentThread().getName()+"LinkingModuleLinkText");}
	public void SetLinkingModuleLinkText(String LinkingModuleLinkText){ Driver.globalValues.put(Thread.currentThread().getName()+"LinkingModuleLinkText",LinkingModuleLinkText);}
	
	
	private boolean FlagLinkingModuleNoPropFound =false;
	public boolean GetFlagLinkingModuleNoPropFound(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"FlagLinkingModuleNoPropFound"));}
	public void SetFlagLinkingModuleNoPropFound(boolean FlagLinkingModuleNoPropFound){ Driver.globalValues.put(Thread.currentThread().getName()+"FlagLinkingModuleNoPropFound",String.valueOf(FlagLinkingModuleNoPropFound));}
	
	
	private boolean FlagLinkingModuleNoRedirections =false;
	public boolean GetFlagLinkingModuleNoRedirections(){return Boolean.parseBoolean(Driver.globalValues.get(Thread.currentThread().getName()+"FlagLinkingModuleNoRedirections"));}
	public void SetFlagLinkingModuleNoRedirections(boolean FlagLinkingModuleNoRedirections){ Driver.globalValues.put(Thread.currentThread().getName()+"FlagLinkingModuleNoRedirections",String.valueOf(FlagLinkingModuleNoRedirections));}
	
	//Added by Johnsi on 06/12/2014 to check the Pin it link in share bar based on the LDP Photo count 
	private String NoPhotoNoPinIt ="";
	public String GetNoPhotoNoPinIt(){return Driver.globalValues.get(Thread.currentThread().getName()+"NoPhotoNoPinIt");}
	public void SetNoPhotoNoPinIt(String NoPhotoNoPinIt){ Driver.globalValues.put(Thread.currentThread().getName()+"NoPhotoNoPinIt",NoPhotoNoPinIt); }

	//Added by Ditimoni on 16/06/14 to validate details in RR with MAL
	private HashMap<String, String> rrDetails = new HashMap<>();
	public void AddToRRDetails(String Key, String Value){this.rrDetails.put(Thread.currentThread().getName()+Key.trim().toLowerCase(), Value);}
	public String GetRRDetails(String Key){return this.rrDetails.get(Thread.currentThread().getName()+Key.trim().toLowerCase());}
	
	private String CommonURL = "";
	public String GetCommonURL(){return Driver.globalValues.get(Thread.currentThread().getName()+"CommonURL");}
	public void SetCommonURL(String CommonURL){ Driver.globalValues.put(Thread.currentThread().getName()+"CommonURL",CommonURL);}
	
	private String FacebookUserName = null;
	public String GetFacebookUserName(){return Driver.globalValues.get(Thread.currentThread().getName()+"FacebookUserName");}
	public void SetFacebookUserName(String FacebookUserName){ Driver.globalValues.put(Thread.currentThread().getName()+"FacebookUserName",FacebookUserName); }
	
	private String RREmailAdd ="";
	public String GetEmailRRAdd(){return Driver.globalValues.get(Thread.currentThread().getName()+"RREmailAdd");}
	public void SetEmailRRAdd(String RREmailAdd){ Driver.globalValues.put(Thread.currentThread().getName()+"RREmailAdd",RREmailAdd); }

	
	private String RRFullName ="";
	public String GetFullNameRR(){return Driver.globalValues.get(Thread.currentThread().getName()+"RRFullName");}
	public void SetFullNameRR(String RRFullName){ Driver.globalValues.put(Thread.currentThread().getName()+"RRFullName",RRFullName); }

	
	
	private String ListingID ="";
	public String GetListingID(){return Driver.globalValues.get(Thread.currentThread().getName()+"ListingID");}
	public void SetListingId(String ListingID){ Driver.globalValues.put(Thread.currentThread().getName()+"ListingID",ListingID); }
	
	private int BathResultCount=0;
	public int GetBathResultCount(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"BathResultCount"));}	
	public void SetBathResultCount(int BathResultCount){ Driver.globalValues.put(Thread.currentThread().getName()+"BathResultCount",String.valueOf(BathResultCount)); }

	private String[][] ValidateDelivery = null;
	public String[][] GetValidateDelivery(){return ValidateDelivery;}
	public void SetValidateDelivery(String[][] ValidateDelivery){ this.ValidateDelivery = ValidateDelivery;}
	
	private ProxyServer server = null;
	public ProxyServer GetProxyServer(){return (ProxyServer) Driver.mapper.get(Thread.currentThread().getName());}
	public void SetProxyServer(ProxyServer server){ Driver.mapper.put(Thread.currentThread().getName(),server);}
	
	private String Jobname = null;
	public String GetJobname(){return Hometype;}
	public void SetJobname(String Jobname){ this.Hometype = Jobname;}
	
	private boolean Pvtshowing=false;
	public boolean GetPrivateShowing(){return Pvtshowing;}
	public void SetPrivateShowing(boolean Pvtshowing){ this.Pvtshowing = Pvtshowing;}
	
	private boolean PvtshowingExist=false;
	public boolean GetPrivateShowingExist(){return PvtshowingExist;}
	public void SetPrivateShowingExist(boolean PvtshowingExist){ this.PvtshowingExist = PvtshowingExist;}
	
	private boolean Attributevaluestatus=false;
	public boolean Getattributevaluestatus(){return Attributevaluestatus;}
	public void SetAttributevaluestatus(boolean Attributevaluestatus){ this.Attributevaluestatus = Attributevaluestatus;}
	
	private String leadstatus = null;
	//public String GetMaxPrice(){return MaxPrice;}
	//public void SetMaxPrice(String MaxPrice){ this.MaxPrice = MaxPrice;}
	public String GetLeadStatusId(){return Driver.globalValues.get(Thread.currentThread().getName()+"leadstatus");}
	public void SetLeadStatusId(String leadstatus){ Driver.globalValues.put(Thread.currentThread().getName()+"leadstatus", leadstatus);}
	
	private String listingAgent = "";
	public String GetListingAgent(){return Driver.globalValues.get(Thread.currentThread().getName()+"listingAgent");}
	public void SetListingAgent(String listingAgent){ Driver.globalValues.put(Thread.currentThread().getName()+"listingAgent",listingAgent); }
	
	private String listedBy = "";
	public String GetListedBy(){return Driver.globalValues.get(Thread.currentThread().getName()+"listedBy");}
	public void SetListedBy(String listedBy){ Driver.globalValues.put(Thread.currentThread().getName()+"listedBy",listedBy); }
	
	private String brokerLocation = "";
	public String GetBrokerLocation(){return Driver.globalValues.get(Thread.currentThread().getName()+"brokerLocation");}
	public void SetBrokerLocation(String brokerLocation){ Driver.globalValues.put(Thread.currentThread().getName()+"brokerLocation",brokerLocation); }
	
	private String dataSource = "";
	public String GetDataSource(){return Driver.globalValues.get(Thread.currentThread().getName()+"dataSource");}
	public void SetDataSource(String dataSource){ Driver.globalValues.put(Thread.currentThread().getName()+"dataSource",dataSource); }
	
	private String buyerBroker = "";
	public String GetBuyerBroker(){return Driver.globalValues.get(Thread.currentThread().getName()+"buyerBroker");}
	public void SetBuyerBroker(String buyerBroker){ Driver.globalValues.put(Thread.currentThread().getName()+"buyerBroker",buyerBroker); }
	
	private String sourcePropertyID = "";
	public String GetSourcePropertyID(){return Driver.globalValues.get(Thread.currentThread().getName()+"sourcePropertyID");}
	public void SetSourcePropertyID(String sourcePropertyID){ Driver.globalValues.put(Thread.currentThread().getName()+"sourcePropertyID",sourcePropertyID); }
	
	private String addedToSite = "";
	public String GetaddedToSite(){return Driver.globalValues.get(Thread.currentThread().getName()+"addedToSite");}
	public void SetaddedToSite(String addedToSite){ Driver.globalValues.put(Thread.currentThread().getName()+"addedToSite",addedToSite); }
	
	private String boardName = "";
	public String GetboardName(){return Driver.globalValues.get(Thread.currentThread().getName()+"boardName");}
	public void SetboardName(String boardName){ Driver.globalValues.put(Thread.currentThread().getName()+"boardName",boardName); }
	
	private int pickBoardPosition = 0;
	public int GetpickBoardPosition(){return Integer.parseInt(Driver.globalValues.get(Thread.currentThread().getName()+"pickBoardPosition"));}
	public void SetpickBoardPosition(int pickBoardPosition){ Driver.globalValues.put(Thread.currentThread().getName()+"pickBoardPosition",String.valueOf(pickBoardPosition)); }
	
	private String parentWindowhandle = "";
	public String GetparentWindowhandle(){return Driver.globalValues.get(Thread.currentThread().getName()+"parentWindowhandle");}
	public void SetparentWindowhandle(String parentWindowhandle){ Driver.globalValues.put(Thread.currentThread().getName()+"parentWindowhandle",parentWindowhandle); }
	
	private String childWindowhandle = "";
	public String GetchildWindowhandle(){return Driver.globalValues.get(Thread.currentThread().getName()+"childWindowhandle");}
	public void SetchildWindowhandle(String childWindowhandle){ Driver.globalValues.put(Thread.currentThread().getName()+"childWindowhandle",childWindowhandle); }
	
	public String GetcolorVerificationFailed(){return (Driver.globalValues.get(Thread.currentThread().getName()+"ColorVerification"));}
	public void SetcolorVerificationFailed(boolean colorVerificationStatus){Driver.globalValues.put(Thread.currentThread().getName()+"ColorVerification",String.valueOf(colorVerificationStatus));}
	
	public String GetxLeadsVerificationPassed(){return Driver.globalValues.get(Thread.currentThread().getName()+"XleadsVerification");}
	public void SetxLeadsVerificationPassed(boolean xLeadsVerificationStatus){Driver.globalValues.put(Thread.currentThread().getName()+"XleadsVerification",String.valueOf(xLeadsVerificationStatus));}
		
	public String GetfontVerificationFailed(){return (Driver.globalValues.get(Thread.currentThread().getName()+"FontVerification"));}
	public void SetfontVerificationFailed(boolean fontVerificationStatus){Driver.globalValues.put(Thread.currentThread().getName()+"FontVerification",String.valueOf(fontVerificationStatus));}
	
	public String GetSubMenucolorVerificationFailed(){return (Driver.globalValues.get(Thread.currentThread().getName()+"SubMenuColorVerification"));}
	public void SetSubMenucolorVerificationFailed(boolean SubMenucolorVerificationStatus){Driver.globalValues.put(Thread.currentThread().getName()+"ColorVerification",String.valueOf(SubMenucolorVerificationStatus));}
	

}
