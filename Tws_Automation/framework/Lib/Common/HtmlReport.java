package Lib.Common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlReport {
	
	public static StringBuilder tHtml = new StringBuilder();
	public static int PassCount=0;
	public static int FailCount=0;
	public void CreateHTMLReport()
	{
	 
	 String TestCaseName= new String();
	 TestCaseName="Test Case Name";  
	 
	 tHtml.append("<html>"+"\n");
	 tHtml.append("<head>"+"\n");
	 tHtml.append("<title>"+TestCaseName+"</title>"+"\n");
	 tHtml.append("<head>"+"\n");
	 tHtml.append("<body bgcolor='#6BC4CC'>"+"\n");
	 tHtml.append("<table border='1'>"+"\n");
	 tHtml.append("<strong>"+"\n");
	 tHtml.append("<tr><th width='10%'>" + "Step" + "</th><th width='50%'>" + "Description" + "</th><th width='15%'>" + "Expected Result" +"</th><th width='15%'>" + "Actual Result" +"</th><th width='7%'>"+"Status"+"</th></tr>"+"\n");
	 tHtml.append("</strong>"+"\n");
	 //tHtml.append("</table>"+"\n");
	 //return tHtml.toString();
	}
	
	public void WriteHTMLReport(String Step,String Description,String ExpResult,String ActResult, String Status,String ImageFile)
	{
		//StringBuilder tHtml = new StringBuilder();
		//tHtml.append(Reporter);
		//tHtml.append("<table border='1'>"+"\n");
		if(Status.toLowerCase().trim().equals("pass")){ PassCount+=1;
		if (ImageFile.trim() == ""){
		tHtml.append("<tr><td  width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td  width='7%' bgcolor=\"DarkOliveGreen\"><strong>PASSED</strong></td></tr>"+"\n");
		}
		else{
			tHtml.append("<tr><td width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td width='7%' bgcolor=\"DarkOliveGreen\"><strong><a href='file:///"+ImageFile.trim()+"'>PASSED</a></strong></td></tr>"+"\n");
		}
		}else if(Status.toLowerCase().trim().equals("fail")){ FailCount+=1;
		if(ImageFile.trim() !=""){
		tHtml.append("<tr><td width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td width='7%' bgcolor=\"Red\"><strong><a href='file:///"+ImageFile.trim()+"'>FAILED</a></strong></td></tr>"+"\n");
		}
		else{
			tHtml.append("<tr><td width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td width='7%' bgcolor=\"Red\"><strong>FAILED</strong></td></tr>"+"\n");
		}
		}else if(Status.toLowerCase().trim().equals("warning")){
		tHtml.append("<tr><td width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td width='7%' bgcolor=\"Orange\"><strong>Warning</strong></td></tr>"+"\n");
		}else if(Status.toLowerCase().trim().equals("done")){
		tHtml.append("<tr><td width='10%'>" + Step + "</td><td width='30%'>" + Description + "</td><td width='20%'>" + ExpResult +"</td><td width='20%'>" + ActResult +"</td><td width='7%' bgcolor=\"Blue\"><strong>Done</strong></td></tr>"+"\n");
		}
		//tHtml.append("</table>"+"\n");
		//return tHtml.toString();
	}
	
	public void SaveHTMLReport(String ResultFolderPath,String Filename)
	{
		tHtml.append("</table>"+"\n");
		tHtml.append("<br>");
		tHtml.append("<table><tr><td>Number of steps passed: "+PassCount+"</td><tr>");
		tHtml.append("<table><tr><td>Number of steps failed: "+FailCount+"</td><tr>");
		tHtml.append("<br>");
		tHtml.append("</body>"+"\n");
		tHtml.append("</html>"+"\n");
		String FilePath=ResultFolderPath+(Filename.contains(".html")?Filename:Filename+".html");
		try{
		File file = new File(FilePath);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(tHtml.toString());
		bw.close();}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
    
}
