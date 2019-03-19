package Lib.Common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;




import Lib.Controller.Driver;



public class Text {
	public static PrintWriter writer = null;
	public static FileOutputStream fos=null;
	public static String directory=null;
	public static String timeStamp=null;
	public static String userDirec=null;
	public static File file=null;
	public static String fileName;
	public static String fetFileName=null;
	public static FileReader fileReader=null;
	public static BufferedReader br=null;
	public static String line=" ";
	public static StringBuilder sb=null;
	
	/**
	 * This function will create a text file with name as time-stamp
	 * @author Ditimoni Borpatra Gohain   Ditimoni.Borpatra@move.com
	 * @return this function will return the path of the file which is of type String
	 * @exception FileNotFoundException
	 * 
	 */

	
	public String createFile() throws FileNotFoundException
	{
		userDirec=System.getProperty("user.dir");
    	timeStamp=System.currentTimeMillis()+"";
    	directory=userDirec+"/Temp/"+timeStamp+".txt";
    	fos=new FileOutputStream(directory,true);
    	this.fileName=directory;
    	return directory;
	}

	
	/**
	 * This function will write in the text file. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @param String TestcaseName
	 * @param String Title of the page
	 * @param String Description
	 * @param String Status of the step
	 * @exception Exception
	 * 
	 */
	
	public void writeInTextFile(String TCName,String title,String Desc,String status) 
	{
		        try {
					Date dNow = new Date( );
				    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd ' - ' hh:mm:ss a");
		        	file=new File(fileName);
		        	fos=new FileOutputStream(file,true);
		            writer=new PrintWriter(fos);
		            if(status.equalsIgnoreCase("pass") || status.equalsIgnoreCase("fail"))
		            {
		            	status=status+"ed";
		            }
		            
		            writer.println(TCName+"~"+title+"~"+Desc+"~"+status);
		            if(Desc.contains("~"))
		            {
		            	
		            }else if(title.equalsIgnoreCase("Iteration"))
		            {
		            	Reporter.log(title+"----"+Desc+"---"+status);
		            	System.out.println(title+"----"+Desc+"---"+status);
		            }else
		            {
		            Reporter.log(Desc+"---"+status);
		            String[] Temp1 = TCName.split("~");
		            System.out.println(ft.format(dNow) + " -- " + Temp1[0] + " -- " + Desc + " ---> "+status); // Updated by KK on 08/06/2013 to have a clear & understandable console print
		            }
		            
		        	} catch (Exception e) {
		            e.printStackTrace();
		        	} finally {
		            try {
		                writer.close();
		            	} catch (Exception e)
		            	{
		            	}
		        }
		    
	}
	
	
	/**
	 * This function will write in the text file. 
	 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
	 * @param String TestcaseName
	 * @param boolean status of the step
	 * @exception Exception
	 * 
	 */
	
	public void writeContent(String tcName,boolean status)
	{
		try {
        	file=new File(fileName);
        	fos=new FileOutputStream(file,true);
            writer=new PrintWriter(fos);
            writer.println(tcName+"~"+status);
        	} catch (Exception e) {
            e.printStackTrace();
        	} finally {
            try {
                writer.close();
            	} catch (Exception e)
            	{
            	}
        }
	}
	
	
	

	/**
	 * This function will give the total line present in text file. 
	 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
	 * @param String File name
	 * @return this function will return total line number which is integer
	 * @exception IOException
	 * 
	 */
	
	public int TotalLine(String fileName) throws IOException
	{
		int count=0;
		
				 fileReader=new FileReader(fileName);
				 br=new BufferedReader(fileReader);

				 line=br.readLine();
				 sb = new StringBuilder();
				 while (line != null)
				 {
					count=count+1;
					line=br.readLine();
		         }
				 //System.out.println(count);
				 br.close();
				 return count;				

	}
	
	
	
	/**
	 * This function will give the text present in a particular line in the text file. 
	 * @author Ditimoni Borpatra Gohain	  Ditimoni.Borpatra@move.com
	 * @param int linenumber
	 * @return this function will return the particular line which is string
	 * @exception IOException
	 * 
	 */
	
	
	public String textInLine(int lineNum) throws IOException
	{
				String line=null;
				String fileName=Driver.Gprops.getTextFilePath();
				 fileReader=new FileReader(fileName);
				 br=new BufferedReader(fileReader);

				
				 
				 for(int i=1;i<=lineNum;i++)
				 {
					 line=br.readLine();
				 }
				 br.close();
				 return line;				
}
	

	/**
	 * This function will give the content present in text file. 
	 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
	 * @param String File name
	 * @return this fuction will return content which is String.
	 * @exception IOException
	 * 
	 */
	
	
	public String readContent(String fileName) throws IOException
	{
	       
	        String line="";
	        try{
	        FileReader fr= new FileReader(fileName);
	        br= new BufferedReader(fr);
	        String st=br.readLine();
	        String s= br.readLine();
	        Text gn=new Text();
	        int a=gn.TotalLine(fileName);
	        
	        for(int i=1;i<=a;i++)
	        {
	            line=line+gn.textInLine(i)+"\n";
	        }
	    }catch(Exception e)
	    {
	        
	    }finally
	    {
	        br.close();
	    }
	        
	    return line;
	        
	    }

	
	
	
	/**
	 * This function will write content the excel file from text file. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @return this function will return the excel file path
	 * @exception IOException
	 * @exception InvalidFormatException
	 * 
	 */
	
	
	public String writeInExcelFile()
	{		
		try{
				String line=null;
				 fileReader=new FileReader(fileName);
				 br=new BufferedReader(fileReader);
				 Text txt=new Text();
				 int totalLine=txt.TotalLine(fileName);
				 ArrayList<String> arrList=new ArrayList<String>();
				 for(int i=1;i<=totalLine;i++)
				 {
					 String str=txt.textInLine(i);
					 String[] arr=str.split("~");
					 if(arrList.contains(arr[0]))
					 {
						 
					 }else
					 {
						 arrList.add(arr[0]);
					 }
					 
				 }
				int b=arrList.size();
				ExcelReport exp=new ExcelReport();
				String st=exp.MakeExcelResultFile();
				String file1=exp.createWorkBook(st);
				 for(int j=0;j<b;j++)
				 {
					 String name=arrList.get(j);
					 exp.createSheet(name);
					 exp.writeInFistLine(name);
				 }
				 for(int j=1;j<=totalLine;j++)
				 {
					 String str1=txt.textInLine(j);
					 if(str1!="")
					 {
					 String[] arr1=str1.split("~");
					 if(arr1[2].equalsIgnoreCase("status"))
					 {
					 
					 }else
					 {
						 exp.writeExcel(arr1[0], arr1[2], arr1[3], arr1[4]);
					 }
				 }
				 }
				 return file1;
		}
		catch(Exception ex){
			System.out.println("Error @ writeInExcelFile");
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return "";
		}
				 
				 
	}
	
	
	
	/**
	 * This function will give the status of the scripts. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @return this function will return TCName,Status,Pass and Fail counter, which is string
	 * @exception IOException
	 * 
	 */	
	
	public String giveStatus()
	{
		try{
		String line="";
		Text text=new Text();
		
		fileReader=new FileReader(fileName); 
		int c=text.TotalLine(fileName);
		for(int i=1;i<=c;i++)
		{
			String str=text.textInLine(i);
			String[] st=str.split("~");
			if(st[2].equalsIgnoreCase("status"))
			{
				line=line+st[0]+"~"+st[1]+"~"+st[3]+"~"+st[4]+"~"+st[5]+"`";
			}
		}
		
		return line;
		}
		catch(Exception ex){
			System.out.println("Error @ giveStatus");
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return "";
		}
	}
	
	/**
	 * This function will write in the text file and provides screen shot path whenver there is failed scenarios
	 * @author Diti moni Ditimoni.Borpatra@move.com and Shwetha Kulkarni shwetha.kulkarni@move.com
	 * @param String TestcaseName
	 * @param String Title of the page
	 * @param String Description
	 * @param String Status of the step
	 * @param WebDriver webdriver instance
	 * @exception Exception
	 * 
	 */
	public void writeInTextFile(String TCName,String title,String Desc,String status,WebDriver otDriver) 
    {
                   try {
                               Date dNow = new Date( );
                            SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd ' - ' hh:mm:ss a");
                         file=new File(fileName);
                         fos=new FileOutputStream(file,true);
                       writer=new PrintWriter(fos);
                       if(status.equalsIgnoreCase("pass") || status.equalsIgnoreCase("fail"))
                       {
                         status=status+"ed";
                       }
                       if(status.equalsIgnoreCase("failed"))   // Added by shwetha Kulkarni on 8/20/2013  to take screenshot whenever there is fail scenario
                       {
                      String s= Driver.CUtil.CaptureScreenShot(title);  
                Desc=Desc+"Refer the file"+" "+s+" for the error screenshot";
                       }
                       writer.println(TCName+"~"+title+"~"+Desc+"~"+status);
                       if(Desc.contains("~"))
                       {
                         
                       }else if(title.equalsIgnoreCase("Iteration"))
                       {
                         Reporter.log(title+"----"+Desc+"---"+status);
                         System.out.println(title+"----"+Desc+"---"+status);
                       }else
                       {
                       Reporter.log(Desc+"---"+status);
                       String[] Temp1 = TCName.split("~");
                       System.out.println(ft.format(dNow) + " -- " + Temp1[0] + " -- " + Desc + " ---> "+status); // Updated by KK on 08/06/2013 to have a clear & understandable console print
                       }
                       
                         } catch (Exception e) {
                       e.printStackTrace();
                         } finally {
                       try {
                           writer.close();
                         } catch (Exception e)
                         {
                         }
                   }
               
    }

	/**
	 * This function will create a text file with name as time-stamp
	 * @author Ditimoni Borpatra Gohain   Ditimoni.Borpatra@move.com
	 * @return this function will return the path of the file which is of type String
	 * @exception FileNotFoundException
	 * 
	 */

	
	public String createTextFile() throws FileNotFoundException
	{
		String fileName=System.getProperty("FetFileName");
		fileName=fileName.replace(".feature", "");
		String directory=System.getProperty("user.dir")+ "/target/" + Driver.Gprops.GetRunID()+ "/"+fileName+"LinkingModule.txt";
		File file = new File(directory);
		if(file.exists())
		{
			
		}else
		{
    	fos=new FileOutputStream(directory,true);
    	this.fileName=directory;
    	
		}
		Driver.Gprops.setTextFilePath(directory);
		return directory;
	}
	
	
	/**
	 * This function will write in the text file. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @param String TestcaseName
	 * @param String Title of the page
	 * @param String Description
	 * @param String Status of the step
	 * @exception Exception
	 * 
	 */
	
	public void writeInTextFileData(String cityState,String rdc,String Zillow,String trulia,String status,String percentageZillow,String percentageTrulia) 
	{
		        try {
		        	String fileName=Driver.Gprops.getTextFilePath();
		        	file=new File(fileName);
		        	fos=new FileOutputStream(file,true);
		            writer=new PrintWriter(fos);  
		            writer.println(cityState+"~"+rdc+"~"+Zillow+"~"+trulia+"~"+status+"~"+percentageZillow+"~"+percentageTrulia);
		        	} catch (Exception e) {
		            e.printStackTrace();
		        	} finally {
		            try {
		                writer.close();
		            	} catch (Exception e)
		            	{
		            	}
		        }
		    
	}
	
	/**
	 * This function will write content the excel file from text file. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @return this function will return the excel file path
	 * @exception IOException
	 * @exception InvalidFormatException
	 * 
	 */
	
	
	public String writeInExcelFileFromText(String fileName)
	{		
		try{
			String line=null;
			//fileName=Driver.Gprops.getTextFilePath();
			 fileReader=new FileReader(fileName);
			 br=new BufferedReader(fileReader);
			 Text txt=new Text();
			 int totalLine=txt.TotalLine(fileName);
			 ArrayList<String> arrList=new ArrayList<String>();
			 for(int i=1;i<=totalLine;i++)
			 {
				 String str=txt.textInLine(i);
				 String[] arr=str.split("~");
				 if(arrList.contains(arr[0]))
				 {
					 
				 }else
				 {
					 arrList.add(arr[0]);
				 }
				 
			 }
			int b=arrList.size();
			ExcelReport exp=new ExcelReport();
			String st=exp.MakeExcelResultFileForComparison();
			String file1=exp.createWorkBook(st);
			 for(int j=0;j<b;j++)
			 {
				 String name=arrList.get(j);
				 exp.createSheet(name);
				 exp.writeInFistLineForDataComparison(name);
			 }
			 for(int j=1;j<=totalLine;j++)
			 {
				 String str1=txt.textInLineForData(j);
				 if(str1!="")
				 {
				 String[] arr1=str1.split("~");
				 if(arr1[2].equalsIgnoreCase("status"))
				 {
				 
				 }else
				 {
					 exp.writeExcelForData(arr1[0], arr1[1], arr1[2], arr1[3], arr1[4], arr1[5], arr1[6], arr1[7]);
				 }
			 }
			 }
			 return file1;
	}
		catch(Exception ex){
			System.out.println("Error @ writeInExcelFile");
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return "";
		}
				 
				 
	}

	public String textInLineForData(int lineNum) throws IOException
	{
				String line=null;
				fileName=Driver.Gprops.getTextFilePath();
				 fileReader=new FileReader(fileName);
				 br=new BufferedReader(fileReader);

				
				 
				 for(int i=1;i<=lineNum;i++)
				 {
					 line=br.readLine();
				 }
				 br.close();
				 return line;				
}
	
	/**
	 * This function will write in the text file. 
	 * @author Ditimoni Borpatra Gohain Ditimoni.Borpatra@move.com
	 * @param String link
	 * @param String Status of the step
	 * @exception Exception
	 * 
	 */
	
	public void writeInTextFileData(String link,String href,String status) 
	{
		        try {
		        	String fileName=Driver.Gprops.getTextFilePath();
		        	file=new File(fileName);
		        	fos=new FileOutputStream(file,true);
		            writer=new PrintWriter(fos);  
		            writer.println(link+"~"+href+"~"+status);
		        	} catch (Exception e) {
		            e.printStackTrace();
		        	} finally {
		            try {
		                writer.close();
		            	} catch (Exception e)
		            	{
		            	}
		        }
		    
	}
	
}

