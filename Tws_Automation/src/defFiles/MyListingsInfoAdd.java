package defFiles;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Lib.Controller.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class MyListingsInfoAdd {
	
	
	
	
	@Then("^I am clicking on AddListings\\\"([^\\\"]*)\\\"$")
	public void MyProfile(String AddListings) {
		String functionName = "AddListings";
		String Titlename = "AddListings";
		try {	if(Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","BodyFramecntrl", 30)) {

			WebElement BodyFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","BodyFramecntrl");
			Driver.Gprops.GetWebDriver().switchTo().frame(BodyFrame);
			Thread.sleep(1000);
			Driver.WebCtrl.clickOnElement("Tws/ListingsInfoandAdd", "AddListings");
			Driver.CUtil.WriteResults("Successfully Clicked on AddListings "+Titlename+"is displayed", functionName, "Pass");
			Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			Driver.Gprops.GetWebDriver().switchTo().defaultContent();
		}
		else {
			Driver.CUtil.WriteResults("Not clicked",functionName , "Fail");
			Assert.assertTrue(false);
		}
			 
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}
	}
	
	
	public void ClearandEnterData(String Screen, String object, String value) {
		if(Driver.WebCtrl.waitTillElementVisibility(Screen, object, 30)){
			Driver.WebCtrl.clear(Screen, object);
			Driver.CUtil.WriteResults("Succesfully cleared" +object,object,"Pass");
			Driver.WebCtrl.sendKeys(Screen, object, value);
			Driver.CUtil.WriteResults("Succesfully entered Data",object,"Pass");
		}
		else {
			Driver.CUtil.WriteResults(object+" is not visible",object,"Fail");
			Assert.assertTrue(false);
		}
	}

	@And("^I am entering\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingInfo1(String Status,String MLSID,String ListingPrice) {

		String functionName="enteringMyListinginfo";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);	
				
				WebElement StatusOselect = Driver.WebCtrl.GetWebElement("Tws/ListingsInfoandAdd", "StatusAdmin");
				Driver.CUtil.WriteResults("Clicked on StatusAdminDropdown", functionName, "Passed");
				Select oSelect = new Select(StatusOselect);
				oSelect.selectByVisibleText(Status);
				Driver.globalValues.put(Thread.currentThread().getName() + "Status1", Status);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminMLSID",MLSID);
				Driver.globalValues.put(Thread.currentThread().getName() + "MLSID1", MLSID);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminListingPrice",ListingPrice);
				Driver.globalValues.put(Thread.currentThread().getName() + "ListingPrice1", ListingPrice);
				
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			}

			else {
				
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	
		
	}
	
	@And("^I am entering infopart2\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingInfo2(String ListingDate , String VirtualTourURL , String ShortDescription) {


		String functionName="enteringMyListinginfo";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);

				ClearandEnterData("Tws/ListingsInfoandAdd","AdminListingDate",ListingDate);
				Driver.globalValues.put(Thread.currentThread().getName() + "ListingDate1", ListingDate);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminVirtualTourURL",VirtualTourURL);
				Driver.globalValues.put(Thread.currentThread().getName() + "VirtualTourURL1", VirtualTourURL);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminShortDescription",ShortDescription);
				Driver.globalValues.put(Thread.currentThread().getName() + "ShortDescription1", ShortDescription);
				
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
				
			}

			else {
				
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	
		
	
		
	}
	
	@Then("^I am entering PropAdress1\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingPropAdress1(String HouseNo , String SuiteNo , String AddressLine1, String AddressLine2) {


		String functionName="enteringMyListingPropAdress1";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);

				ClearandEnterData("Tws/ListingsInfoandAdd","AdminHouseNo",HouseNo);
				Driver.globalValues.put(Thread.currentThread().getName() + "HouseNo1", HouseNo);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminSuiteNo",SuiteNo);
				Driver.globalValues.put(Thread.currentThread().getName() + "SuiteNo1", SuiteNo);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminAddressLine1",AddressLine1);
				Driver.globalValues.put(Thread.currentThread().getName() + "AddressLine11", AddressLine1);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminAddressLine2",AddressLine2);
				Driver.globalValues.put(Thread.currentThread().getName() + "AddressLine21", AddressLine2);
				
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			}

			else {
				
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	
		
	
		
	}
	
	@And("^I am entering PropAdress2\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingPropAdress2(String City , String Province , String Country, String PostalCode) {


		String functionName="enteringMyListingPropAdress2";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				


				ClearandEnterData("Tws/ListingsInfoandAdd","AdminCity",City);
				Driver.globalValues.put(Thread.currentThread().getName() + "City1", City);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminPostalCode",PostalCode);
				Driver.globalValues.put(Thread.currentThread().getName() + "PostalCode1", PostalCode);
				
				WebElement ProvinceDropdown = Driver.WebCtrl.GetWebElement("Tws/ListingsInfoandAdd","ProvinceAdmin");
				Select oSelect = new Select(ProvinceDropdown);
				oSelect.selectByVisibleText(Province);
				Driver.CUtil.WriteResults("Successfully selected the Provice dropdown", functionName, "Passed");
				Driver.globalValues.put(Thread.currentThread().getName() + "Province1", Province);
				boolean province=false;
				String ActualCountry;
				String CanadaProvinces []= {"AB","BC","MB","NB","NL","NT","NS","NU","ON","PE","QC","SK","YT"};
				for(int i=0;i<CanadaProvinces.length;i++) {
					if(CanadaProvinces[i].equals(Province)) {
						Driver.CUtil.WriteResults(Province+" is in canada", "Country", "Passed");
						province=true;
						break;
					}
//for not breaking the loop we didnt wrote else statement inside


				}

				if(province==true) {
					ActualCountry="Canada";
				}
				else {
					ActualCountry="United States";
				}
				
				WebElement SelectCountryDropdown = Driver.WebCtrl.GetWebElement("Tws/ListingsInfoandAdd", "CountryAdmin");
				Select oSelect1 = new Select(SelectCountryDropdown);
				String ExpectedCountry=oSelect1.getFirstSelectedOption().getText();
				Driver.globalValues.put(Thread.currentThread().getName() + "Country1", Country);

				if(ActualCountry.equalsIgnoreCase(ExpectedCountry)) {
					
				}
				else {
					Driver.CUtil.WriteResults("text not cleared", functionName, "Fail");
					Assert.assertTrue(false);
				}
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			
			
			
			
			}

			else {
				
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
			
			
			
			
			
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	
		
	
		
	}
	
	
	@And("^I am entering PropAdress3\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingPropAdress3(String Area , String SchoolDistrict , String PropertyType) {


		String functionName="enteringMyListingPropAdress3";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				


				ClearandEnterData("Tws/ListingsInfoandAdd","AdminArea",Area);
				Driver.globalValues.put(Thread.currentThread().getName() + "Area1", Area);
				
				ClearandEnterData("Tws/ListingsInfoandAdd","AdminSchoolDistrict",SchoolDistrict);
				Driver.globalValues.put(Thread.currentThread().getName() + "SchoolDistrict1", SchoolDistrict);
				
				WebElement PropertyTypeOselect = Driver.WebCtrl.GetWebElement("Tws/ListingsInfoandAdd", "PropertyTypeAdmin");
				Driver.CUtil.WriteResults("Clicked on StatusAdminDropdown", functionName, "Passed");
				Select oSelect = new Select(PropertyTypeOselect);
				oSelect.selectByVisibleText(PropertyType);
				Driver.globalValues.put(Thread.currentThread().getName() + "PropertyType1", PropertyType);
				
				Driver.Gprops.GetWebDriver().switchTo().defaultContent();
			
		
			}

			else {
				
				Driver.CUtil.WriteResults("text not cleared",functionName,"Fail");
				Assert.assertTrue(false);
			}
			
			
			
			
			
		}
		catch(Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), functionName, "Fail");
		}

	
		
	
		
	}
	
	
	

}
