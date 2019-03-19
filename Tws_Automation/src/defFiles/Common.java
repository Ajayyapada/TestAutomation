package defFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.sitebricks.rendering.resource.Assets;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import Lib.Controller.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Common {

	String filePath1 = System.getProperty("user.dir");
	
	private int i;

	// Use UTF-8 Encoding

	@Before
	public void beforeScenario(Scenario sc) {
		Driver.mapper.put(Thread.currentThread().getName()+"Scenario", sc);
	}


	@After
	public void afterScenario(Scenario result) {
		WebDriver otDriver = Driver.Gprops.GetWebDriver();
		String globalxLeadsVerification = Driver.Gprops.GetxLeadsVerificationPassed();
		if (result.isFailed() && globalxLeadsVerification==null) {
			result.write("<div><span><b>The reason for the above failure:</b></span><span id=\"FailureReason\">"
					+ Driver.globalValues.get("FailedMessage"
							+ Thread.currentThread().getName())
					+ "</span></div>");
			if (otDriver != null) {

				result.write("<div><span><b>The browser url when scenario failed:</b></span><span><a href=\""
						+ otDriver.getCurrentUrl()
						+ "\">"
						+ otDriver.getCurrentUrl() + "</a></span></div>");
				String imgFileName = Driver.CUtil.CaptureScreenShot("Failed");
				result.write("<div><a href=\""
						+ imgFileName
						+ "\" target=\"_blank\">Click here for error screen shot of above failure</a></div>");
			} else {
				result.write("<div><span><b>The browser url when scenario failed:</b></span><span>Unable to get the URL and screenshot of the page because browser is not open </span></div>");
			}
		}
		if (otDriver != null) {

			//To display sauce labs report link on cucumber report updated by teena on 01/26/2016
			if(Driver.Init.GetAUTBrowser().contains("sauce")){
				String SLReportsURL="https://saucelabs.com/tests/"+Driver.globalValues.get(Thread.currentThread().getName()+"SessionID");
				result.write("<div><span><b>The sauce labs report URL:</b></span><span><a href=\""
						+ SLReportsURL
						+ "\">"
						+ SLReportsURL + "</a></span></div>");
			}
			/*if(Driver.Init.GetAUTBrowser().contains("sauce")){
				String SLReportsURL="C:\\Users\\vijendra.ahirwar\\Desktop\\Daily_Report_01-22-15.htm";
				result.write("<div><span><b>Excel Report URL:</b></span><span><a href=\""
						+ SLReportsURL
						+ "\">"
						+ SLReportsURL + "</a></span></div>");
			}*/
			//update sauce labs info
			if (result.isFailed())
			{
				if(Driver.Init.GetAUTBrowser().contains("sauce"))
					Driver.WebCtrl.updateTestInfoOnSauce(Driver.globalValues.get(Thread.currentThread().getName()+"SessionID"), false);

			}
			else{
				if(Driver.Init.GetAUTBrowser().contains("sauce"))
					Driver.WebCtrl.updateTestInfoOnSauce(Driver.globalValues.get(Thread.currentThread().getName()+"SessionID"), true);
			}

			otDriver.quit();
			Driver.mapper.put(Thread.currentThread().getName(), null);
			Driver.Gprops.SetWebDriver(null);
			Driver.CUtil.WriteResults("Closing the Browser Instance",
					"afterScenario", "Done");
		}

		String browser = Driver.Init.GetAUTBrowser();
		if (browser.trim().toLowerCase().contains("edw")) {
			//				RDCWebProxy.StopRemoteProxyonPort();
		}


	}
	//*******************************************************************************************************************************/
	/**
	 * This function will take WebElement and then stored title of that attribute and check values of title is 'Checked' or 'UnChecked'.
	 * @author Vijendra Ahirwar <Vijendra.Ahirwar@move.com>
	 * @param element of type WebElement
	 * @return this function will return 'true' or 'false'
	 */
	//*******************************************************************************************************************************/
	public boolean verifyCheckBox(WebElement element)
	{
		boolean flag=false;

		WebElement TextElement = element;
		String checkBoxAttribute=TextElement.getAttribute("title");
		if(checkBoxAttribute.equalsIgnoreCase("Checked"))
		{
			flag=true;
		}
		else{
			flag=false;
		}
		return flag;
	}
	
	
	
	
	
	



}


