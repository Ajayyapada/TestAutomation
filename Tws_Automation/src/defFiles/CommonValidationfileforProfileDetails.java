package defFiles;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import Lib.Controller.Driver;
import cucumber.api.java.en.Then;



public class CommonValidationfileforProfileDetails{
	
	public void validationTest(String name1,String enteredValue ,String name2,String expectedValue ) {
		String functionName = "  Agent info validation";
		try {
			if (enteredValue.equals(expectedValue)) {
				Driver.CUtil.WriteResults("Data is validated successfully  "+name1+"    "+name2+  functionName,"  Validation :","Pass" );
			}
			else {
				Driver.CUtil.WriteResults("Data validation failed   "+name1+"    "+name2+  functionName,"  Validation :","Fail" );
			Assert.assertTrue(false);
			}
		}
		catch(Exception e){
			Driver.CUtil.WriteResults("Data is not validated ,"+name1+"  "+name2,"   Validation :","Fail");
			Assert.assertTrue(false);
		}
		
	
	}	
	
	
	
@Then("^I am verifying customsite$")
public void verifiyingCustomSiteContactinfo() {
	String functionName = "verifiyingCustomSiteAgentDetails";
	try {
		if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","HeaderFrame", 30)) {

			WebElement HeaderFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","HeaderFrame");
			Driver.Gprops.GetWebDriver().switchTo().frame(HeaderFrame);
			Thread.sleep(1000);
			/*WebElement InsideBodyFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","InsideFramecntrl");
			Driver.Gprops.GetWebDriver().switchTo().frame(InsideBodyFrame);*/
			
			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "ViewSite");
			Driver.CUtil.WriteResults("Successfully Clicked on view site", "ViewSite", "Pass");
			
			Set<String> w = Driver.Gprops.GetWebDriver().getWindowHandles();
			String windows[] = new String[w.size()];
			int i = 0;
			for (String window : w) {
				windows[i] = window;
				i++;
			}
			Driver.Gprops.GetWebDriver().switchTo().window(windows[1]);
			Thread.sleep(4000);
			Driver.WebCtrl.waitTillPageLoad(8);
			
			String ActualFirstName = Driver.globalValues.get(Thread.currentThread().getName() + "Firstname1");
			String ActualLastName = Driver.globalValues.get(Thread.currentThread().getName() + "LastName1");
			String Primaryemailid = Driver.globalValues.get(Thread.currentThread().getName()+"Adminemail1");
			String ActualPhNum1 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhone1");
			String ActualPhNum2 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhone2");
			String ActualPhNum3 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhone3");
			String AdminPrimaryMessage = Driver.globalValues.get(Thread.currentThread().getName()+ "PHMessage1");
			String AdminSecondaryMessage = Driver.globalValues.get(Thread.currentThread().getName()+ "SHMessage1");
			/*String AdminPhonetype1 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhonetype1");
			String AdminPhonetype2 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhonetype2");
			String AdminPhonetype3 = Driver.globalValues.get(Thread.currentThread().getName()+ "AdminPhonetype3");*/
			String AdminProfTitle1 = Driver.globalValues.get(Thread.currentThread().getName()+ "ProfTitle1");
			String AdminSpecialities1 = Driver.globalValues.get(Thread.currentThread().getName()+ "Specialities1");
			String AdminAreas_served1 = Driver.globalValues.get(Thread.currentThread().getName()+ "Areas_served1");
			String AdminProf_Design1 = Driver.globalValues.get(Thread.currentThread().getName()+ "Prof_Design1");
			String AdminAgentLiNum1 = Driver.globalValues.get(Thread.currentThread().getName()+ "AgentLiNum1");
			
			
			
			
			
			
			String ExpectedAgentNameTitle=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentname");
			String ExpectedAgentNameonHeaderCard=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentNameonCardDetails");
			String ExpectedAgentNameonFooterCard=Driver.WebCtrl.getText("Tws/AdminHeader", "FooterAgentNameonCardDetails");
			String ExpectedAgentPrimaryemailHeader=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressHeader");
			String ExpectedAgentPrimaryemailfooter=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressfooter");
			String ExpectedAgentPhNumTitle=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumbertitle1");
			String ExpectedAgentPhNumHeader1=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader1");
			String ExpectedAgentPhNumHeader2=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader2");
			String ExpectedAgentPhNumHeader3=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader3");
			String ExpectedAgentPhNumfooter1=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter1");
			String ExpectedAgentPhNumfooter2=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter2");
			String ExpectedAgentPhNumfooter3=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter3");
			/*String ExpectedphonetypecustomH1=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomH1");
			String ExpectedphonetypecustomH2=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomH2");
			String ExpectedphonetypecustomH3=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomH3");
			String ExpectedphonetypecustomF1=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomF1");
			String ExpectedphonetypecustomF2=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomF2");
			String ExpectedphonetypecustomF3=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "phonetypecustomF3");*/
			String ExpectedPrimarycustomMesage=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "PrimarycustomMesage");
			String ExpectedSecondarycustomMessage=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "SecondarycustomMessage");
			String ExpectedProfessionalTitleCustomH=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "ProfessionalTitleCustomH");
			String ExpectedSpecialitiesCustomH=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "SpecialitiesCustomH");
			String ExpectedAreasservedCustomH=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "AreasservedCustomH");
			String ExpectedProfessionalDesigCustomH=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "ProfessionalDesigCustomH");
			String ExpectedProfessionalTitleCustomF=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "ProfessionalTitleCustomF");
			String ExpectedSpecialitiesCustomF=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "SpecialitiesCustomF");
			String ExpectedAreasservedCustomF=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "AreasservedCustomF");
			String ExpectedProfessionalDesigCustomF=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "ProfessionalDesigCustomF");
			String ExpectedAgentlicencenumCustom=Driver.WebCtrl.getText("Tws/Contactinfo&expertiseCustomsite", "AgentlicencenumCustom");
			

			
			
//			boolean ExpectedAgentNameTitle1=true,ExpectedAgentNameonHeaderCard1=true,ExpectedAgentNameonFooterCard1=true;
			validationTest("NameEnteredinAdminsite",ActualFirstName+" "+ActualLastName,"ExpectedAgentNameTitleCustomsite",ExpectedAgentNameTitle);
			validationTest("NameEnteredinAdminsite",ActualFirstName+" "+ActualLastName,"ExpectedAgentNameonHeaderCardCustomsite",ExpectedAgentNameonHeaderCard);
			validationTest("NameEnteredinAdminsite",ActualFirstName+" "+ActualLastName,"ExpectedAgentNameonFooterCardCustomsite",ExpectedAgentNameonFooterCard);
			validationTest("EmailenteredinAdminsite",Primaryemailid,"ExpectedemailonCustomsiteHeader",ExpectedAgentPrimaryemailHeader);
			validationTest("EmailenteredinAdminsite",Primaryemailid,"ExpectedemailonCustomsiteFooter",ExpectedAgentPrimaryemailfooter);
			validationTest("PhNumEnteredinAdminsite",ActualPhNum1,"ExpectedAgentPhnNumTitleCustomsite",ExpectedAgentPhNumTitle.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum1,"ExpectedAgentPhnNumHeaderCustomsite1",ExpectedAgentPhNumHeader1.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum2,"ExpectedAgentPhnNumHeaderCustomsite2",ExpectedAgentPhNumHeader2.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum3,"ExpectedAgentPhnNumHeaderCustomsite3",ExpectedAgentPhNumHeader3.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum1,"ExpectedAgentPhnNumfooterCustomsite1",ExpectedAgentPhNumfooter1.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum2,"ExpectedAgentPhnNumfooterCustomsite2",ExpectedAgentPhNumfooter2.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("PhNumEnteredinAdminsite",ActualPhNum3,"ExpectedAgentPhnNumfooterCustomsite3",ExpectedAgentPhNumfooter3.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
			validationTest("AdminPrimaryMessage",AdminPrimaryMessage,"ExpectedPrimarycustomMesage",ExpectedPrimarycustomMesage);
			validationTest("AdminSecondaryMessage",AdminSecondaryMessage,"ExpectedSecondarycustomMessage",ExpectedSecondarycustomMessage);
/*			validationTest("AdminPrimaryMessage",AdminPhonetype1,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomH1);
			validationTest("AdminPrimaryMessage",AdminPhonetype2,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomH2);
			validationTest("AdminPrimaryMessage",AdminPhonetype3,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomH3);
			validationTest("AdminPrimaryMessage",AdminPhonetype1,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomF1);
			validationTest("AdminPrimaryMessage",AdminPhonetype2,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomF2);
			validationTest("AdminPrimaryMessage",AdminPhonetype3,"ExpectedPrimarycustomMesage",ExpectedphonetypecustomF3);*/
			validationTest("AdminProfTitle1",AdminProfTitle1,"ExpectedProfessionalTitleCustomH",ExpectedProfessionalTitleCustomH);
			validationTest("AdminSpecialities1",AdminSpecialities1,"ExpectedSpecialitiesCustomH",ExpectedSpecialitiesCustomH);
			validationTest("AdminAreas_served1",AdminAreas_served1,"ExpectedAreasservedCustomH",ExpectedAreasservedCustomH);
			validationTest("AdminProf_Design1",AdminProf_Design1,"ExpectedProfessionalDesigCustomH",ExpectedProfessionalDesigCustomH);
			validationTest("AdminProfTitle1",AdminProfTitle1,"ExpectedProfessionalTitleCustomF",ExpectedProfessionalTitleCustomF);
			validationTest("AdminSpecialities1",AdminSpecialities1,"ExpectedSpecialitiesCustomF",ExpectedSpecialitiesCustomF);
			validationTest("AdminAreas_served1",AdminAreas_served1,"ExpectedAreasservedCustomF",ExpectedAreasservedCustomF);
			validationTest("AdminProf_Design1",AdminProf_Design1,"ExpectedProfessionalDesigCustomF",ExpectedProfessionalDesigCustomF);
			validationTest("AdminAgentLiNum1",AdminAgentLiNum1,"ExpectedAgentlicencenumCustom",ExpectedAgentlicencenumCustom);
			
			
			}
			
		else {
			Driver.CUtil.WriteResults("Data not Matching",functionName , "Fail");
			Assert.assertTrue(false);
		}


	}
	catch(Exception e) {
		Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
	}
	/*			if(ExpectedAgentNameTitle.equals(ActualFirstName+" "+ActualLastName)) {
	ExpectedAgentNameTitle1=true;
	Driver.CUtil.WriteResults("Successfully Validated the ExpectedAgentNameTitle of the agent in customer site", "ExpectedAgentNameTitle", "Pass");
}

else {
	ExpectedAgentNameTitle1=false;
	Driver.CUtil.WriteResults("Failed Validate the ExpectedAgentNameTitle", "", "Pass");
	}
if(ExpectedAgentNameonHeaderCard.equals(ActualFirstName+" "+ActualLastName)) {
	ExpectedAgentNameonHeaderCard1=true;
	Driver.CUtil.WriteResults("Successfully Validated the ExpectedAgentNameonHeaderCard of the agent in customer site", "ExpectedAgentNameonHeaderCard", "Pass");
}

else {
	ExpectedAgentNameonHeaderCard1=false;
	Driver.CUtil.WriteResults("Failed Validate the ExpectedAgentNameonHeaderCard", "ExpectedAgentNameonHeaderCard", "Fail");
	}
if(ExpectedAgentNameonFooterCard.equals(ActualFirstName+" "+ActualLastName)) {
	ExpectedAgentNameonFooterCard1=true;
	Driver.CUtil.WriteResults("Successfully Validated the ExpectedAgentNameonFooterCard of the agent in customer site", "ExpectedAgentNameonFooterCard", "Pass");
}

else {
	ExpectedAgentNameonFooterCard1=false;
	Driver.CUtil.WriteResults("Failed Validate the ExpectedAgentNameonFooterCard", "ExpectedAgentNameonFooterCard", "Fail");
	}



if(ExpectedAgentNameTitle1==true&&ExpectedAgentNameonHeaderCard1==true&&ExpectedAgentNameonFooterCard1==true) {


}	
else {

Assert.assertTrue(false);
}*/	
}


// customer expertise details

@Then("^I am verifying customsiteExpertdetails$")
public void verifiyingCustomSiteExpertdetails() {
	String functionName = "verifiyingCustomSiteAgentDetails";
	try {
		if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","HeaderFrame", 30)) {

			WebElement HeaderFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","HeaderFrame");
			Driver.Gprops.GetWebDriver().switchTo().frame(HeaderFrame);
			Thread.sleep(1000);
			/*WebElement InsideBodyFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","InsideFramecntrl");
			Driver.Gprops.GetWebDriver().switchTo().frame(InsideBodyFrame);*/
			
			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "ViewSite");
			Driver.CUtil.WriteResults("Successfully Clicked on view site", "ViewSite", "Pass");
			
			Set<String> w = Driver.Gprops.GetWebDriver().getWindowHandles();
			String windows[] = new String[w.size()];
			int i = 0;
			for (String window : w) {
				windows[i] = window;
				i++;
			}
			Driver.Gprops.GetWebDriver().switchTo().window(windows[1]);
			Thread.sleep(4000);
			Driver.WebCtrl.waitTillPageLoad(8);
			
			String AdminOfficeName = Driver.globalValues.get(Thread.currentThread().getName() + "OfficeName1");
			String AdminAddressdetails = Driver.globalValues.get(Thread.currentThread().getName() + "Addressdetails1");
			String AdminCity = Driver.globalValues.get(Thread.currentThread().getName()+"City1");
			String AdminZipCode = Driver.globalValues.get(Thread.currentThread().getName()+ "ZipCode1");
			String AdminStateProvince = Driver.globalValues.get(Thread.currentThread().getName()+ "StateProvince1");
			String AdminCountry = Driver.globalValues.get(Thread.currentThread().getName()+ "Country");
			String AdminFacebook = Driver.globalValues.get(Thread.currentThread().getName()+ "Facebook1");
			String AdminLinkedIn = Driver.globalValues.get(Thread.currentThread().getName()+ "LinkedIn1");
			String AdminTwitter = Driver.globalValues.get(Thread.currentThread().getName()+ "Twitter1");
			String AdminRealtor = Driver.globalValues.get(Thread.currentThread().getName()+ "Realtor1");
			String AdminPinterest = Driver.globalValues.get(Thread.currentThread().getName()+ "Pinterest1");
			String AdminInstagram = Driver.globalValues.get(Thread.currentThread().getName()+ "Instagram1");
			
	
			
			String ExpectedOfficeNameTitle=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentname");
			String ExpectedOfficeName1=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentname");
			String ExpectedOfficeName2=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentname");
			String ExpectedAddressdetailsH=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentNameonCardDetails");
			String ExpectedCityH=Driver.WebCtrl.getText("Tws/AdminHeader", "FooterAgentNameonCardDetails");
			String ExpectedZipCodeH=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressHeader");
			String ExpectedStateProvinceH=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressfooter");
			String ExpectedCountryH=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumbertitle1");
			
			
			String ExpectedAddressdetailsF=Driver.WebCtrl.getText("Tws/AdminHeader", "HeaderAgentNameonCardDetails");
			String ExpectedCityF=Driver.WebCtrl.getText("Tws/AdminHeader", "FooterAgentNameonCardDetails");
			String ExpectedZipCodeF=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressHeader");
			String ExpectedStateProvinceF=Driver.WebCtrl.getText("Tws/AdminHeader", "CUstomersiteEmailAddressfooter");
			String ExpectedCountryF=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumbertitle1");
			
			String ExpectedFacebook=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader1");
			String ExpectedLinkedIn=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader2");
			String ExpectedTwitter=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberHeader3");
			String ExpectedRealtor=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter1");
			String ExpectedPinterest=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter2");
			String ExpectedInstagram=Driver.WebCtrl.getText("Tws/AdminHeader", "CustomsiteMyPhonenumberFooter3");
			
			

			
			
//			boolean ExpectedAgentNameTitle1=true,ExpectedAgentNameonHeaderCard1=true,ExpectedAgentNameonFooterCard1=true;
			
			validationTest("AdminOfficeName",AdminOfficeName,"ExpectedemailonCustomsiteHeader",ExpectedOfficeNameTitle);
			validationTest("AdminOfficeName",AdminOfficeName,"ExpectedemailonCustomsiteHeader",ExpectedOfficeName1);
			validationTest("AdminOfficeName",AdminOfficeName,"ExpectedemailonCustomsiteHeader",ExpectedOfficeName2);
			
			validationTest("AdminAddressdetails",AdminAddressdetails,"ExpectedAddressdetailsH",ExpectedAddressdetailsH);
			validationTest("AdminCity",AdminCity,"ExpectedCityH",ExpectedCityH);
			validationTest("AdminZipCode",AdminZipCode,"ExpectedZipCodeH",ExpectedZipCodeH);
			validationTest("AdminStateProvince",AdminStateProvince,"ExpectedStateProvinceH",ExpectedStateProvinceH);
			validationTest("AdminCountry",AdminCountry,"ExpectedCountryH",ExpectedCountryH);
			
			validationTest("AdminAddressdetails",AdminAddressdetails,"ExpectedAddressdetailsF",ExpectedAddressdetailsF);
			validationTest("AdminCity",AdminCity,"ExpectedCityF",ExpectedCityF);
			validationTest("AdminZipCode",AdminZipCode,"ExpectedZipCodeF",ExpectedZipCodeF);
			validationTest("AdminStateProvince",AdminStateProvince,"ExpectedStateProvinceF",ExpectedStateProvinceF);
			validationTest("AdminCountry",AdminCountry,"ExpectedCountryF",ExpectedCountryF);
			
			validationTest("AdminFacebook",AdminFacebook,"ExpectedFacebook",ExpectedFacebook);
			validationTest("AdminLinkedIn",AdminLinkedIn,"ExpectedLinkedIn",ExpectedLinkedIn);
			validationTest("AdminTwitter",AdminTwitter,"ExpectedTwitter",ExpectedTwitter);
			validationTest("AdminRealtor",AdminRealtor,"ExpectedRealtor",ExpectedRealtor);
			validationTest("AdminPinterest",AdminPinterest,"ExpectedPinterest",ExpectedPinterest);
			validationTest("AdminInstagram",AdminInstagram,"ExpectedInstagram",ExpectedInstagram);
			
			
			
			}
			
		else {
			Driver.CUtil.WriteResults("Data not Matching",functionName , "Fail");
			Assert.assertTrue(false);
		}


	}
	catch(Exception e) {
		Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
	}
	
}
}
