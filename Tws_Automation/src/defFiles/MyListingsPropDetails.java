package defFiles;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Lib.Controller.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class MyListingsPropDetails {


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

	@Then("^I am entering ListingsPropDetails\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingInfo1(String Style,String Parking,String NumberofUnits) {

		String functionName="enteringMyListingPropDetails";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);	
				
				WebElement StyleOselect = Driver.WebCtrl.GetWebElement("Tws/ListingPropDetails", "AdminStyle");
				Driver.CUtil.WriteResults("Clicked on StatusAdminDropdown", functionName, "Passed");
				Select oSelect = new Select(StyleOselect);
				oSelect.selectByVisibleText(Style);
				Driver.globalValues.put(Thread.currentThread().getName() + "Style1", Style);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminParking",Parking);
				Driver.globalValues.put(Thread.currentThread().getName() + "Parking1", Parking);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminNumberofUnits",NumberofUnits);
				Driver.globalValues.put(Thread.currentThread().getName() + "NumberofUnits1", NumberofUnits);
				
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
	
	@And("^I am entering ListingsPropDetails1\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingInfo2(String Beds , String Baths , String PartialBaths) {


		String functionName="enteringMyListinginfo";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);

				ClearandEnterData("Tws/ListingPropDetails","AdminBeds",Beds);
				Driver.globalValues.put(Thread.currentThread().getName() + "Beds1", Beds);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminBaths",Baths);
				Driver.globalValues.put(Thread.currentThread().getName() + "Baths1", Baths);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminPartialBaths",PartialBaths);
				Driver.globalValues.put(Thread.currentThread().getName() + "PartialBaths1", PartialBaths);
				
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
	
	@And("^I am entering ListingsPropDetails2\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingPropAdress1(String SquareFootage , String LotSize , String Age) {


		String functionName="enteringMyListingPropAdress1";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);

				ClearandEnterData("Tws/ListingPropDetails","AdminSquareFootage",SquareFootage);
				Driver.globalValues.put(Thread.currentThread().getName() + "SquareFootage1", SquareFootage);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminLotSize",LotSize);
				Driver.globalValues.put(Thread.currentThread().getName() + "LotSize1", LotSize);
				
				ClearandEnterData("Tws/ListingPropDetails","AdminAge",Age);
				Driver.globalValues.put(Thread.currentThread().getName() + "Age1", Age);
							
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
	
	@Then("^I am entering AdditionalDetails1\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingAdditionalDetails1(String CondoFees , String FeeSchedule , String TaxYear) {


		String functionName="enteringMyListingAddDetails";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);

				ClearandEnterData("Tws/ListingAddFeatures","AdminCondoFees",CondoFees);
				Driver.globalValues.put(Thread.currentThread().getName() + "CondoFees1", CondoFees);
				
				WebElement FeeScheduleOselect = Driver.WebCtrl.GetWebElement("Tws/ListingAddFeatures", "AdminFeeSchedule");
				Driver.CUtil.WriteResults("Clicked on StatusAdminDropdown", functionName, "Passed");
				Select oSelect = new Select(FeeScheduleOselect);
				oSelect.selectByVisibleText(FeeSchedule);
				Driver.globalValues.put(Thread.currentThread().getName() + "FeeSchedule1", FeeSchedule);
				
				ClearandEnterData("Tws/ListingAddFeatures","AdminTaxYear",TaxYear);
				Driver.globalValues.put(Thread.currentThread().getName() + "TaxYear1", TaxYear);
				
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
	
	@And("^I am entering AdditionalDetails2\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingAdditionalDetails2(String MonthlyRent , String Taxes , String InteriorFeatures) {


		String functionName="enteringMyListingAddDetails2";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				
				ClearandEnterData("Tws/ListingAddFeatures","AdminMonthlyRent",MonthlyRent);
				Driver.globalValues.put(Thread.currentThread().getName() + "MonthlyRent1", MonthlyRent);

				ClearandEnterData("Tws/ListingAddFeatures","AdminTaxes",Taxes);
				Driver.globalValues.put(Thread.currentThread().getName() + "Taxes1", Taxes);
				
				ClearandEnterData("Tws/ListingAddFeatures","AdminInteriorFeatures",InteriorFeatures);
				Driver.globalValues.put(Thread.currentThread().getName() + "InteriorFeatures1", InteriorFeatures);
				
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
	
	@And("^I am entering AdditionalDetails3\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void ListingAdditionalDetails3(String ExteriorFeatures , String AdditionalFeaturesandAmenities , String Remarks) {


		String functionName="enteringMyListingAddDetails2";

		try {
			
			
			
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader","Mainframe", 30)) {
				WebElement ParentFrame= Driver.WebCtrl.GetWebElement("Tws/AdminHeader","Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				
				ClearandEnterData("Tws/ListingAddFeatures","AdminExteriorFeatures",ExteriorFeatures);
				Driver.globalValues.put(Thread.currentThread().getName() + "ExteriorFeatures1", ExteriorFeatures);

				ClearandEnterData("Tws/ListingAddFeatures","AdminAdditionalFeaturesandAmenities",AdditionalFeaturesandAmenities);
				Driver.globalValues.put(Thread.currentThread().getName() + "AdditionalFeaturesandAmenities1", AdditionalFeaturesandAmenities);
				
				ClearandEnterData("Tws/ListingAddFeatures","AdminRemarks",Remarks);
				Driver.globalValues.put(Thread.currentThread().getName() + "Remarks1", Remarks);
				
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
