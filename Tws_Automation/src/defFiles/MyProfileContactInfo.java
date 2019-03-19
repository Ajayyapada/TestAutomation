package defFiles;

import java.awt.Robot;
import java.awt.event.KeyEvent;
//import java.sql.DriverAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.bouncycastle.asn1.cmp.ProtectedPart;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Lib.Controller.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyProfileContactInfo {
	//*******************************************************************************************************************************/
	/**
	 * This function will verify the login to the Admin site and validate the Agent details.
	 * @author Ajay
	//******************************************************************************************************************************/

	/*launch the URL*/

	@Given("^I launch TWS login panel$")
	public void launchTws() {
		String functionName = "launchTws";
		try {
			
			Driver.WebCtrl.GetWebDriver(Driver.Init.GetAUTBrowser(), Driver.Init.GetOS64Bit());
			String baseURL = Driver.Init.GetBaseURL();
			Driver.Gprops.GetWebDriver().get(baseURL);
			Driver.CUtil.WriteResults("The user has launched '" + baseURL, functionName, "Done");
			//Thread.sleep(2000);
		} catch (Exception e) {
			Driver.CUtil.WriteResults("Exception occurred. " + e.getMessage(), functionName, "Fail");
			Assert.assertTrue(false);
		}
	}

	/* find the User-name and password control and perform actions*/

	@When("^I am login in to Tws Application$")
	public void loginIntoApplication() {
		String userID = Driver.Init.GetFromEnvironment("userID");
		String userPW = Driver.Init.GetFromEnvironment("userPW");
		String functionName = "loginIntoApplication";
		try {
			if(Driver.WebCtrl.waitTillElementVisibility("Tws/Home", "EnterUNcntrl", 30)) {
				Driver.WebCtrl.sendKeys("Tws/Home", "EnterUNcntrl", userID);
				Driver.WebCtrl.sendKeys("Tws/Home", "EnterPWDcntl", userPW);
				Driver.WebCtrl.clickOnElement("Tws/Home", "SignInButtoncntrl");
				Driver.CUtil.WriteResults("Successfully login in to tws application", functionName, "Pass");

			}else {
				Driver.CUtil.WriteResults("credentials are invalid ", "username", "Fail");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			Driver.CUtil.WriteResults("Exception occurred. " + e.getMessage(), functionName, "Fail");
			Assert.assertTrue(false);
		}
	}

	/*Clicking on MyAccount tab */
	
	@Then("^I am clicking on \\\"([^\\\"]*)\\\"$")
	public void MyAccountClick(String Accounts) {

		String functionName = "MyaccountClick";
		String TitleName = "Manage your account details and profile information";
		try {
			if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","HeaderFramecntrl", 30)) {
				WebElement HeaderFrame=Driver.WebCtrl.GetWebElement("Tws/AdminHeader","HeaderFramecntrl");
				Driver.Gprops.GetWebDriver().switchTo().frame(HeaderFrame);

				Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "Account");
				Driver.WebCtrl.isDisplayedCustomised("Tws/AdminHeader", "Account", "Manage your account details and profile information", "Tws/AdminHeader", TitleName);
				Driver.CUtil.WriteResults("Successfully Clicked on Account tab and "+TitleName+"is displayed", functionName, "Pass");
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
				
			}else {
				Driver.CUtil.WriteResults("Not clicked",functionName , "Fail");
				Assert.assertTrue(false);
			}


		} catch (Exception e) {
			Driver.CUtil.WriteResults("Exception occurred. " + e.getMessage(), functionName, "Fail");
			Assert.assertTrue(false);
		}
	}
	
	/*Clicking on MyProfile item */
	
	@Then("^I am clicking on myProfile\\\"([^\\\"]*)\\\"$")
	public void MyProfile(String profilesetting) {
		String functionName = "myProfileDetailentryValidation";
		String Titlename = "Your profile information is displayed on every page of your Web site. Import or edit your profile below";
		try {
			if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","BodyFramecntrl", 30)) {

				WebElement BodyFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","BodyFramecntrl");
				Driver.Gprops.GetWebDriver().switchTo().frame(BodyFrame);
				Thread.sleep(1000);
				WebElement InsideBodyFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","InsideFramecntrl");
				Driver.Gprops.GetWebDriver().switchTo().frame(InsideBodyFrame);
				Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "Profilecntrl");

				Driver.WebCtrl.isDisplayedCustomised("Tws/AdminHeader", "myProfilePage", "Your profile information is displayed on every page of your Web site. Import or edit your profile below", "Tws/AdminHeader", Titlename);
				Driver.CUtil.WriteResults("Successfully Clicked on MyProfile "+Titlename+"is displayed", functionName, "Pass");
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			}
			else {
				Driver.CUtil.WriteResults("Not clicked",functionName , "Fail");
				Assert.assertTrue(false);
			}

			//enteringMyProfileDetails(Firstname,LastName,Email,Phone1,Phone2,Phone3);
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}




	}
	
	
	@Then("^I am clicking on listing \\\"([^\\\"]*)\\\"$")
	public void MyListingsClick(String Listings) {

		String functionName = "MylistingClick";
		String TitleName = "Manage your account details and profile information";
		try {
			if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","HeaderFramecntrl", 30)) {
				WebElement HeaderFrame=Driver.WebCtrl.GetWebElement("Tws/AdminHeader","HeaderFramecntrl");
				Driver.Gprops.GetWebDriver().switchTo().frame(HeaderFrame);

				Driver.WebCtrl.clickOnElement("Tws/ListingsInfoandAdd", "Listings");
				Driver.WebCtrl.isDisplayedCustomised("Tws/ListingsInfoandAdd", "Listings", "Manage Listings", "Tws/ListingsInfoandAdd", TitleName);
				Driver.CUtil.WriteResults("Successfully Clicked on Account tab and "+TitleName+"is displayed", functionName, "Pass");
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
				
			}else {
				Driver.CUtil.WriteResults("Not clicked",functionName , "Fail");
				Assert.assertTrue(false);
			}


		} catch (Exception e) {
			Driver.CUtil.WriteResults("Exception occurred. " + e.getMessage(), functionName, "Fail");
			Assert.assertTrue(false);
		}
	}
	
	
	
	public void ClearandEnterData(String Screen , String object, String Value) {
		if(Driver.WebCtrl.waitTillElementVisibility(Screen, object, 30)) {
			Driver.WebCtrl.clear(Screen, object);
			Driver.CUtil.WriteResults("Succesfully cleared" +object,object,"Pass");
			Driver.WebCtrl.sendKeys(Screen, object,Value);
			Driver.CUtil.WriteResults("Succesfully entered Data",object,"Pass");
		}
		else {
			Driver.CUtil.WriteResults(object+" is not visible",object,"Fail");
			Assert.assertTrue(false);
		}
	}

	/* Entering FirstName and LastName and saving the profile */
	@And("^I am entering\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void enteringMyProfileDetails(String Firstname, String LastName ,String Email, String Phone1,String Phone2,String Phone3,String Phonetype1,String Phonetype2,String Phonetype3) {
		String functionName="enteringMyProfileDetails";
		String Fname = "FirstName";
		String Lname = "LastName";
		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				Thread.sleep(1000);
				WebElement ChildFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Childframeinside");
				Driver.Gprops.GetWebDriver().switchTo().frame(ChildFrame);
				
				
				ClearandEnterData("Tws/AdminHeader","AdminFirstNamecntrl",Firstname);
				Driver.globalValues.put(Thread.currentThread().getName() + "Firstname1", Firstname);
				ClearandEnterData("Tws/AdminHeader","AdminLastNamecntrl",LastName);
				Driver.globalValues.put(Thread.currentThread().getName() + "LastName1", LastName);
				ClearandEnterData("Tws/AdminHeader","AdminEmailAddress",Email);
				Driver.globalValues.put(Thread.currentThread().getName() + "Adminemail1", Email);
				ClearandEnterData("Tws/AdminHeader","AdminMyPhonenumber1",Phone1);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone1", Phone1);
				ClearandEnterData("Tws/AdminHeader","AdminMyPhonenumber2",Phone2);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone2", Phone2);
				ClearandEnterData("Tws/AdminHeader","AdminMyPhonenumber3",Phone3);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone3", Phone3);
				ClearandEnterData("Tws/AdminHeader","AdminPhonetype1",Phonetype1);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype1", Phonetype1);
				ClearandEnterData("Tws/AdminHeader","AdminPhonetype2",Phonetype2);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype2", Phonetype2);
				ClearandEnterData("Tws/AdminHeader","AdminPhonetype3",Phonetype3);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype3", Phonetype3);
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminFirstNamecntrl");
				Driver.CUtil.WriteResults("clicked",Fname , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminFirstNamecntrl");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminFirstNamecntrl", Firstname);
				Driver.CUtil.WriteResults("Firstname entered",Fname , "Pass");
				Driver.globalValues.put(Thread.currentThread().getName() + "Firstname1", Firstname);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminLastNamecntrl");
				Driver.CUtil.WriteResults("clicked",Lname , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminLastNamecntrl");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminLastNamecntrl", LastName);
				Driver.globalValues.put(Thread.currentThread().getName() + "LastName1", LastName);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminEmailAddress");
				Driver.CUtil.WriteResults("clicked","mailid" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminEmailAddress");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminEmailAddress", Email);
				Driver.globalValues.put(Thread.currentThread().getName() + "Adminemail1", Email);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminMyPhonenumber1");
				Driver.CUtil.WriteResults("clicked","AdminMyPhonenumber1" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminMyPhonenumber1");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminMyPhonenumber1", Phone1);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone1", Phone1);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminMyPhonenumber2");
				Driver.CUtil.WriteResults("clicked","AdminMyPhonenumber2" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminMyPhonenumber2");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminMyPhonenumber2", Phone2);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone2", Phone2);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminMyPhonenumber3");
				Driver.CUtil.WriteResults("clicked","AdminMyPhonenumber3" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminMyPhonenumber3");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminMyPhonenumber3", Phone3);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhone3", Phone3);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminPhonetype1");
				Driver.CUtil.WriteResults("clicked","AdminPhonetype1" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminPhonetype1");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminPhonetype1", Phonetype1);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype1", Phonetype1);*/
				
/*				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminPhonetype2");
				Driver.CUtil.WriteResults("clicked","AdminPhonetype2" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminPhonetype2");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminPhonetype2", Phonetype2);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype2", Phonetype2);
				
				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","AdminPhonetype3");
				Driver.CUtil.WriteResults("clicked","AdminPhonetype3" , "Pass");
				Driver.WebCtrl.clear("Tws/AdminHeader","AdminPhonetype3");
				Driver.WebCtrl.sendKeys("Tws/AdminHeader","AdminPhonetype3", Phonetype3);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdminPhonetype2", Phonetype3);
				*/
				
				
				//wait(10);


			}

			else {
				Driver.CUtil.WriteResults("Notclicked",Fname , "Fail");
				Driver.CUtil.WriteResults("Notclicked",Lname , "Fail");
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	}
	
	
	@And("^I am entering\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void EnterProffesionalDetails(String PHMessage,String SHMessage, String ProfTitle, String AgentLiNum, String Exp_years, String Specialities, String Areas_served,String Prof_Design ) {
		String function = "Entering Proffessional details of agent";
		try {
			
			ClearandEnterData("Tws/AdminHeader","primaryHeadermessageAdmin",PHMessage);
			Driver.globalValues.put(Thread.currentThread().getName() + "PHMessage1", PHMessage);
			
			ClearandEnterData("Tws/AdminHeader","SecondaryHeadermessageAdmin",SHMessage);
			Driver.globalValues.put(Thread.currentThread().getName() + "SHMessage1", SHMessage);
			
			WebElement ProfessionalTitle = Driver.WebCtrl.GetWebElement("Tws/AdminHeader", "ProfessionalTitleAdmin");
			Driver.CUtil.WriteResults("Clicked on ProfessionalTitleAdminDropdown", function, "Passed");
			Select oSelect = new Select(ProfessionalTitle);
			oSelect.selectByVisibleText(ProfTitle);
			Driver.globalValues.put(Thread.currentThread().getName() + "ProfTitle1", ProfTitle);
			
			ClearandEnterData("Tws/AdminHeader","AgentlicenceNumAdmin",AgentLiNum);
			Driver.globalValues.put(Thread.currentThread().getName() + "AgentLiNum1", AgentLiNum);
			
			ClearandEnterData("Tws/AdminHeader","SpecialitiesAdmin",Specialities);
			Driver.globalValues.put(Thread.currentThread().getName() + "Specialities1", Specialities);
			
			ClearandEnterData("Tws/AdminHeader","AreasServedAdmin",Areas_served);
			Driver.globalValues.put(Thread.currentThread().getName() + "Areas_served1", Areas_served);
			
			ClearandEnterData("Tws/AdminHeader","ProfessionalDesignationAdmin",Prof_Design);
			Driver.globalValues.put(Thread.currentThread().getName() + "Prof_Design1", Prof_Design);
			
			
			
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader","primaryHeadermessageAdmin");
			Driver.CUtil.WriteResults("Clicked on Primary header Message",function , "Pass");
			Driver.WebCtrl.clear("Tws/AdminHeader","primaryHeadermessageAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader","primaryHeadermessageAdmin", PHMessage);
			Driver.globalValues.put(Thread.currentThread().getName() + "PHMessage1", PHMessage);*/
				
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "SecondaryHeadermessageAdmin");
			Driver.CUtil.WriteResults("Clicked on Secondary header Message", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminHeader", "SecondaryHeadermessageAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader", "SecondaryHeadermessageAdmin", SHMessage);
			Driver.globalValues.put(Thread.currentThread().getName() + "SHMessage1", SHMessage);*/
			
/*			WebElement ProfessionalTitle = Driver.WebCtrl.GetWebElement("Tws/AdminHeader", "ProfessionalTitleAdmin");
			Driver.CUtil.WriteResults("Clicked on ProfessionalTitleAdminDropdown", function, "Passed");
			Select oSelect = new Select(ProfessionalTitle);
			oSelect.selectByVisibleText(ProfTitle);
			Driver.globalValues.put(Thread.currentThread().getName() + "ProfTitle1", ProfTitle);*/
			
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "AgentlicenceNumAdmin");
			Driver.CUtil.WriteResults("Clicked on AgentlicenceNumAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminHeader", "AgentlicenceNumAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader", "AgentlicenceNumAdmin", AgentLiNum);
			Driver.globalValues.put(Thread.currentThread().getName() + "AgentLiNum1", AgentLiNum);*/
			
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "SpecialitiesAdmin");
			Driver.CUtil.WriteResults("Clicked on SpecialitiesAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminHeader", "SpecialitiesAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader", "SpecialitiesAdmin", Specialities);
			Driver.globalValues.put(Thread.currentThread().getName() + "Specialities1", Specialities);*/
			
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "AreasServedAdmin");
			Driver.CUtil.WriteResults("Clicked on AreasServedAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminHeader", "AreasServedAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader", "AreasServedAdmin", Areas_served);
			Driver.globalValues.put(Thread.currentThread().getName() + "Areas_served1", Areas_served);*/
			
/*			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "ProfessionalDesignationAdmin");
			Driver.CUtil.WriteResults("Clicked on ProfessionalDesignationAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminHeader", "ProfessionalDesignationAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminHeader", "ProfessionalDesignationAdmin", Prof_Design);
			Driver.globalValues.put(Thread.currentThread().getName() + "Prof_Design1", Prof_Design);*/
			
				
				Driver.WebCtrl.clickOnElement("Tws/AdminHeader","SaveMyptofile");
/*				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_TAB);	
				Thread.sleep(1000);	
				robot.keyRelease(KeyEvent.VK_TAB);	
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				robot.keyRelease(KeyEvent.VK_ENTER);*/
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
				

		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), function, "Fail");
		}
	
	}
	
	
	

	
/* Clicking on View-site and jumping to next tab i.e customer site and validating the user entered First-name and Last-name is same as entered in AdmSin site */
	
	
}