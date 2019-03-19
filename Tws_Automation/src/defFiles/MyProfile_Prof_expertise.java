package defFiles;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.webdriven.commands.IsChecked;

import Lib.Controller.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyProfile_Prof_expertise {


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



	@And("^I am entering\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void EnterProffesionalAddressDetails(String OfficeName, String Addressdetails, String City, String ZipCode,
			String StateProvince, String Country) {
		String function = "Entering office information details of agent";
		try {
			if (Driver.WebCtrl.waitTillElementVisibility("Tws/AdminHeader", "Mainframe", 30)) {
				WebElement ParentFrame = Driver.WebCtrl.GetWebElement("Tws/AdminHeader", "Mainframe");
				Driver.Gprops.GetWebDriver().switchTo().frame(ParentFrame);
				Thread.sleep(1000);
				WebElement ChildFrame = Driver.WebCtrl.GetWebElement("Tws/AdminHeader", "Childframeinside");
				Driver.Gprops.GetWebDriver().switchTo().frame(ChildFrame);
				ClearandEnterData("Tws/AdminprofileAddress","OfficeNameAdmin",OfficeName);
				Driver.globalValues.put(Thread.currentThread().getName() + "OfficeName1", OfficeName);
				ClearandEnterData("Tws/AdminprofileAddress","AddressdetailsAdmin",Addressdetails);
				Driver.globalValues.put(Thread.currentThread().getName() + "Addressdetails1", Addressdetails);
				ClearandEnterData("Tws/AdminprofileAddress","CityAdmin",City);
				Driver.globalValues.put(Thread.currentThread().getName() + "City1", City);
				ClearandEnterData("Tws/AdminprofileAddress","ZipCodeAdmin",ZipCode);
				Driver.globalValues.put(Thread.currentThread().getName() + "ZipCode1", ZipCode);
				
				
				/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "OfficeNameAdmin");
				Driver.CUtil.WriteResults("Clicked on office name ", function, "Pass");
				Driver.WebCtrl.clear("Tws/AdminprofileAddress", "OfficeNameAdmin");
				Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "OfficeNameAdmin", OfficeName);
				Driver.globalValues.put(Thread.currentThread().getName() + "OfficeName1", OfficeName);*/

				/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "AddressdetailsAdmin");
				Driver.CUtil.WriteResults("Clicked on AddressdetailsAdmin Message", function, "Passed");
				Driver.WebCtrl.clear("Tws/AdminprofileAddress", "AddressdetailsAdmin");
				Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "AddressdetailsAdmin", Addressdetails);
				Driver.globalValues.put(Thread.currentThread().getName() + "Addressdetails1", Addressdetails);*/

				/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "CityAdmin");
				Driver.CUtil.WriteResults("Clicked on CityAdmin", function, "Passed");
				Driver.WebCtrl.clear("Tws/AdminprofileAddress", "CityAdmin");
				Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "CityAdmin", City);
				Driver.globalValues.put(Thread.currentThread().getName() + "City1", City);*/

				/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "ZipCodeAdmin");
				Driver.CUtil.WriteResults("Clicked on ZipCodeAdmin", function, "Passed");
				Driver.WebCtrl.clear("Tws/AdminprofileAddress", "ZipCodeAdmin");
				Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "ZipCodeAdmin", ZipCode);
				Driver.globalValues.put(Thread.currentThread().getName() + "ZipCode1", ZipCode);*/

				WebElement SelectProvinceDropdown = Driver.WebCtrl.GetWebElement("Tws/AdminprofileAddress","StateProvinceAdmin");
				Select oSelect = new Select(SelectProvinceDropdown);
				oSelect.selectByVisibleText(StateProvince);
				Driver.CUtil.WriteResults("Successfully selected the Provice dropdown", function, "Passed");
				Driver.globalValues.put(Thread.currentThread().getName() + "StateProvince1", StateProvince);
				boolean province=false;
				String ActualCountry;
				String CanadaProvinces []= {"AB","BC","MB","NB","NL","NT","NS","NU","ON","PE","QC","SK","YT"};
				for(int i=0;i<CanadaProvinces.length;i++) {
					if(CanadaProvinces[i].equals(StateProvince)) {
						Driver.CUtil.WriteResults(StateProvince+" is in canada", "Country", "Passed");
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

//
				WebElement SelectCountryDropdown = Driver.WebCtrl.GetWebElement("Tws/AdminprofileAddress", "CountryAdmin");
				Select oSelect1 = new Select(SelectCountryDropdown);
				String ExpectedCountry=oSelect1.getFirstSelectedOption().getText();
				Driver.globalValues.put(Thread.currentThread().getName() + "Country1", Country);

				if(ActualCountry.equalsIgnoreCase(ExpectedCountry)) {
					
				}
				else {
					
				}
					
				
				// WebElement ProfileCheckbox =
				// Driver.WebCtrl.GetWebElement("Tws/AdminprofileAddress", "checkbox");

			}

			else {
				Driver.CUtil.WriteResults("Notclicked", function, "Fail");
				Driver.CUtil.WriteResults("text not cleared", function, "Fail");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), function, "Fail");
		}

	}

	@Then("^I am entering values\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\",\\\"([^\\\"]*)\\\"$")
	public void EnterProffesionalSocialsiteDetails(String Facebook, String LinkedIn, String Twitter, String Realtor,
			String Pinterest, String Instagram) {
		String function = "SocialsitesAdmin";
		try {
			
			ClearandEnterData("Tws/AdminprofileAddress","FacebooklinkAdmin",Facebook);
			Driver.globalValues.put(Thread.currentThread().getName() + "Facebook1", Facebook);
			ClearandEnterData("Tws/AdminprofileAddress","LinkedInlinkAdmin",LinkedIn);
			Driver.globalValues.put(Thread.currentThread().getName() + "LinkedIn1", LinkedIn);
			ClearandEnterData("Tws/AdminprofileAddress","Twitterlink",Twitter);
			Driver.globalValues.put(Thread.currentThread().getName() + "Twitter1", Twitter);
			ClearandEnterData("Tws/AdminprofileAddress","RealtorProfile",Realtor);
			Driver.globalValues.put(Thread.currentThread().getName() + "Realtor1", Realtor);
			ClearandEnterData("Tws/AdminprofileAddress","PinterestLink",Pinterest);
			Driver.globalValues.put(Thread.currentThread().getName() + "Pinterest1", Pinterest);
			ClearandEnterData("Tws/AdminprofileAddress","Instagramlink",Instagram);
			Driver.globalValues.put(Thread.currentThread().getName() + "Instagram1", Instagram);
			
			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "FacebooklinkAdmin");
			Driver.CUtil.WriteResults("Clicked on FacebooklinkAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "FacebooklinkAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "FacebooklinkAdmin", Facebook);
			Driver.globalValues.put(Thread.currentThread().getName() + "Facebook1", Facebook);*/

			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "LinkedInlinkAdmin");
			Driver.CUtil.WriteResults("Clicked on LinkedInlinkAdmin", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "LinkedInlinkAdmin");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "LinkedInlinkAdmin", LinkedIn);
			Driver.globalValues.put(Thread.currentThread().getName() + "LinkedIn1", LinkedIn);*/

			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "Twitterlink");
			Driver.CUtil.WriteResults("Clicked on Twitterlink", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "Twitterlink");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "Twitterlink", Twitter);
			Driver.globalValues.put(Thread.currentThread().getName() + "Twitter1", Twitter);*/

			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "RealtorProfile");
			Driver.CUtil.WriteResults("Clicked on RealtorProfile", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "RealtorProfile");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "RealtorProfile", Realtor);
			Driver.globalValues.put(Thread.currentThread().getName() + "Realtor1", Realtor);*/

			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "PinterestLink");
			Driver.CUtil.WriteResults("Clicked on PinterestLink", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "PinterestLink");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "PinterestLink", Pinterest);
			Driver.globalValues.put(Thread.currentThread().getName() + "Pinterest1", Pinterest);*/

			/*Driver.WebCtrl.clickOnElement("Tws/AdminprofileAddress", "Instagramlink");
			Driver.CUtil.WriteResults("Clicked on Instagramlink", function, "Passed");
			Driver.WebCtrl.clear("Tws/AdminprofileAddress", "Instagramlink");
			Driver.WebCtrl.sendKeys("Tws/AdminprofileAddress", "Instagramlink", Instagram);
			Driver.globalValues.put(Thread.currentThread().getName() + "Instagram1", Instagram);*/
			

			// Check box code validation
			
			
			/*			if(Driver.WebCtrl.isDisplayed("Tws/Checkbox", "FooterCheckboxes")) {
				List<WebElement>FooterCheckboxes=Driver.WebCtrl.GetWebElements("Tws/Checkbox", "FooterCheckboxes");
				for(int i=0;i<FooterCheckboxes.size();i++)
				{
					FooterCheckboxes=Driver.WebCtrl.GetWebElements("Tws/Checkbox", "FooterCheckboxes");
					FooterCheckboxes.get(i).click();
				}
				
				for(int i=0;i<FooterCheckboxes.size();i++)
				{
					FooterCheckboxes=Driver.WebCtrl.GetWebElements("Tws/Checkbox", "FooterCheckboxes");
					FooterCheckboxes.get(i).click();
				}
			}
			else {
				
			}*/
 
			Driver.WebCtrl.clickOnElement("Tws/AdminHeader", "SaveMyptofile");
			/*Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_TAB);

			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);*/
			Driver.Gprops.GetWebDriver().switchTo().defaultContent();
		} catch (Exception e) {

			Driver.CUtil.WriteResults("exception occured" + e.getMessage(), function, "Fail");
		}
	}
}
