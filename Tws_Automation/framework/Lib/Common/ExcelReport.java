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
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.tools.ant.taskdefs.optional.ssh.Directory;



import Lib.Controller.Driver;
	
	public class ExcelReport {
		public static HSSFWorkbook hworkbook;
		public static FileOutputStream fout;
		public static ByteArrayOutputStream outputStream;
		public static String excelFilePath;
		public static Workbook workbook;
		public static Sheet sheet;
		public static File file;
		public static int rowNum = 0;
		public static boolean IsFileExist = false;
		
		/**
		 * This function will create an excel workbook. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param String Excel File path
		 * @return this function will return the path of the excel file which is a string
		 * @exception IOException
		 * 
		 */
		
		public String createWorkBook(String excelFilePath) throws IOException {
			try{	
				 fout = new FileOutputStream(excelFilePath);
				 outputStream = new ByteArrayOutputStream();
				 hworkbook = new HSSFWorkbook();
				 hworkbook.write(outputStream);
				 outputStream.writeTo(fout);
				 outputStream.close();
				 fout.close();
				 this.excelFilePath=excelFilePath;
				
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			return excelFilePath;
		}
		
		
		/**
		 * This function will create excel work sheet. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param String sheet name
		 * @return this function will return sheet name which is string
		 * @exception IOException
		 * @exception InvalidFormatException
		 * 
		 */
		
		public String createSheet(String sheetName) throws IOException, InvalidFormatException
		{
		
			workbook = WorkbookFactory.create(new File(excelFilePath));
			sheet = workbook.createSheet(sheetName);
			fout= new FileOutputStream(excelFilePath);
			workbook.write(fout);
			fout.close();
			rowNum=0;
			return sheetName;
		}
		
		
	
		/**
		 * This function will give the sheet name. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param  int index of the sheet
		 * @return this function will return string sheet name>
		 * @exception IOException
		 * 
		 */
		
		public String sh(int i)
		{
			return workbook.getSheetAt(i).getSheetName();
		}
		
		/**
		 * This function will give the number of sheets present in the workbook. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @return this function will return no of sheets ie. int
		 * 
		 */
		
		public int getNumberOfSheet()
		{
			return workbook.getNumberOfSheets();
			
		}
		
		/**
		 * This function will write in the cell. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param String sheet name
		 * @param String title
		 * @param String dexcription
		 * @param String status
		 * @exception IOException
		 * @exception InvalidFormatException
		 * 
		 */
		
		public static void writeExcel(String sheetName,String title,String desc, String status) throws IOException, InvalidFormatException
		{
			sheet=workbook.getSheet(sheetName);
			
			rowNum=sheet.getLastRowNum()+1;
			sheet.createRow(rowNum);
			Row row=sheet.getRow(rowNum);
			row.createCell(0);
			Cell cel=row.getCell(0);
			row.createCell(1);
			Cell cel1=row.getCell(1);
			row.createCell(2);
			Cell cel2=row.getCell(2);
			cel.setCellType(cel.CELL_TYPE_STRING);
			cel.setCellValue(title);
			cel1.setCellType(cel1.CELL_TYPE_STRING);
			cel1.setCellValue(desc);
			cel2.setCellType(cel2.CELL_TYPE_STRING);
			cel2.setCellValue(status);
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			CellStyle myStyle = workbook.createCellStyle();
			Font fnt=workbook.createFont();
			if(status.equalsIgnoreCase("passed"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.GREEN.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel2.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}else if(status.equalsIgnoreCase("failed"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.RED.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel2.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}else if(status.equalsIgnoreCase("warning"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.ORANGE.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel2.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}
			else if(status.equalsIgnoreCase("done"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel2.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}
			fout=new FileOutputStream(excelFilePath);
			workbook.write(fout);
			//rowNum +=1;
			}
		
		
		/**
		 * This function will give the total number of rows present in a sheet. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param String sheet name
		 * @return this function will return number of rows which is int.
		 * 
		 */
		
		public int getRowNum(String sheetName)
		{
			rowNum=sheet.getLastRowNum();
			return rowNum;
		}
		
		
		/**
		 * This function will write in the first line of the sheet. The values are Title, Desc and Status
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param  String sheet name
		 * @exception IOException
		 * 
		 */
		
		public void writeInFistLine(String sheetName) throws IOException
		{
			sheet=workbook.getSheet(sheetName);
			sheet.autoSizeColumn(15);
			Row row=sheet.createRow(0);
			row.createCell(0);
			Cell cel=row.getCell(0);
			row.createCell(1);
			Cell cel1=row.getCell(1);
			row.createCell(2);
			Cell cel2=row.getCell(2);
			cel.setCellType(cel.CELL_TYPE_STRING);
			cel.setCellValue("Title");
			cel1.setCellType(cel1.CELL_TYPE_STRING);
			cel1.setCellValue("Desc");
			cel2.setCellType(cel2.CELL_TYPE_STRING);
			cel2.setCellValue("Status");
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			CellStyle myStyle = workbook.createCellStyle();
			Font fnt=workbook.createFont();
			myStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cel.setCellStyle(myStyle);
			cel1.setCellStyle(myStyle);
			cel2.setCellStyle(myStyle);
			fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
			myStyle.setFont(fnt);
			fout=new FileOutputStream(excelFilePath);
			workbook.write(fout);
		}
			
			
			
		/**
		 * This function will give the excel file name with name as timestamp. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @return this function will return excel file path
		 * 
		 */
	
		
		public String MakeExcelResultFile(){
			String excelFile = "";
			try{
				String Folder = System.getProperty("user.dir")+"/"+"test-output/TestResult/";
				File oFile = new File(Folder);
				if (!oFile.exists())
				{
					oFile.mkdir();
				}
				Folder += System.currentTimeMillis()+".xls" ;
				excelFile = Folder;
			}
			catch(Exception ex){
				excelFile = "error";
			}
			return excelFile;
		}
		
		 
		

	
		/**
		 * This function will give the excel cell value. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @return this function will return excel file path
		 * 
		 */
		
		public String getExcelValue(String excelFilePath, int rowNum, int cellNum) throws IOException {
			String cellContent="";
			try{
				File file=new File(excelFilePath);
				//FileOutputStream ot=new FileOutputStream(new File(excelFilePath));
				Workbook wbk=WorkbookFactory.create(file);
				Sheet sht=wbk.getSheet("Sheet1");
				Row row=sht.getRow(rowNum);
				Cell cel=row.getCell(cellNum);
				cellContent=cel.getStringCellValue();
				}
				catch(Exception ex)
				{
				System.out.println(ex.getMessage());
				}
			return cellContent;
		}
				
		
		/**
		 * This function will give the excel file name with name as timestamp. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @return this function will return excel file path
		 * 
		 */
	
		
		public String MakeExcelResultFileForComparison(){
			String excelFile = "";
			try{
				String Folder = System.getProperty("user.dir")+"/"+"temp/";
				File oFile = new File(Folder);
				if (!oFile.exists())
				{
					oFile.mkdir();
				}
				Folder += System.getProperty("FetFileName").replace(".feature", "")+".xls" ;
				excelFile = Folder;
			}
			catch(Exception ex){
				excelFile = "error";
			}
			return excelFile;
		}
		
		
		/**
		 * This function will write in the first line of the sheet. The values are Title, Desc and Status
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param  String sheet name
		 * @exception IOException
		 * 
		 */
		
		public void writeInFistLineForDataComparison(String sheetName) throws IOException
		{
			sheet=workbook.getSheet(sheetName);
			sheet.autoSizeColumn(15);
			Row row=sheet.createRow(0);
			row.createCell(0);
			Cell cel=row.getCell(0);
			row.createCell(1);
			Cell cel1=row.getCell(1);
			row.createCell(2);
			Cell cel2=row.getCell(2);
			row.createCell(3);
			Cell cel3=row.getCell(3);
			row.createCell(4);
			Cell cel4=row.getCell(4);
			row.createCell(5);
			Cell cel5=row.getCell(5);
			row.createCell(6);
			Cell cel6=row.getCell(6);


			cel.setCellType(cel.CELL_TYPE_STRING);
			cel.setCellValue("City/State");
			cel1.setCellType(cel1.CELL_TYPE_STRING);
			cel1.setCellValue("RDC");
			cel2.setCellType(cel2.CELL_TYPE_STRING);
			cel2.setCellValue("Zillow");
			cel3.setCellType(cel3.CELL_TYPE_STRING);
			cel3.setCellValue("Trulia");
			cel4.setCellType(cel4.CELL_TYPE_STRING);
			cel4.setCellValue("Status");
			cel5.setCellType(cel5.CELL_TYPE_STRING);
			cel5.setCellValue("% Difference in Zillow");
			cel6.setCellType(cel6.CELL_TYPE_STRING);
			cel6.setCellValue("% Difference in Zillow");
			
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);

			CellStyle myStyle = workbook.createCellStyle();
			Font fnt=workbook.createFont();
			myStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cel.setCellStyle(myStyle);
			cel1.setCellStyle(myStyle);
			cel2.setCellStyle(myStyle);
			cel3.setCellStyle(myStyle);
			cel4.setCellStyle(myStyle);
			cel5.setCellStyle(myStyle);
			cel6.setCellStyle(myStyle);
			
			fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
			myStyle.setFont(fnt);
			fout=new FileOutputStream(excelFilePath);
			workbook.write(fout);
		}
		
		
		/**
		 * This function will write in the cell. 
		 * @author Ditimoni Borpatra Gohain  Ditimoni.Borpatra@move.com
		 * @param String sheet name
		 * @param String title
		 * @param String dexcription
		 * @param String status
		 * @exception IOException
		 * @exception InvalidFormatException
		 * 
		 */
		
		public static void writeExcelForData(String fetFileName,String cityState,String rdc,String Zillow,String trulia,String status,String percentageZillow,String percentageTrulia) throws IOException, InvalidFormatException
		{
			sheet=workbook.getSheet(fetFileName);
			
			rowNum=sheet.getLastRowNum()+1;
			sheet.createRow(rowNum);
			Row row=sheet.getRow(rowNum);
			row.createCell(0);
			Cell cel=row.getCell(0);
			row.createCell(1);
			Cell cel1=row.getCell(1);
			row.createCell(2);
			Cell cel2=row.getCell(2);
			row.createCell(3);
			Cell cel3=row.getCell(3);
			row.createCell(4);
			Cell cel4=row.getCell(4);
			row.createCell(5);
			Cell cel5=row.getCell(5);
			row.createCell(6);
			Cell cel6=row.getCell(6);
			cel.setCellType(cel.CELL_TYPE_STRING);
			cel.setCellValue(cityState);
			cel1.setCellType(cel1.CELL_TYPE_STRING);
			cel1.setCellValue(rdc);
			cel2.setCellType(cel2.CELL_TYPE_STRING);
			cel2.setCellValue(Zillow);
			cel3.setCellType(cel3.CELL_TYPE_STRING);
			cel3.setCellValue(trulia);
			cel4.setCellType(cel4.CELL_TYPE_STRING);
			cel4.setCellValue(status);
			cel5.setCellType(cel5.CELL_TYPE_STRING);
			cel5.setCellValue(percentageZillow);
			cel6.setCellType(cel6.CELL_TYPE_STRING);
			cel6.setCellValue(percentageTrulia);
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			CellStyle myStyle = workbook.createCellStyle();
			Font fnt=workbook.createFont();
			if(status.equalsIgnoreCase("passed"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.GREEN.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel4.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}else if(status.equalsIgnoreCase("failed"))
			{
				myStyle.setBorderBottom(CellStyle.BORDER_THIN);
				myStyle.setBorderLeft(CellStyle.BORDER_THIN);
				myStyle.setBorderRight(CellStyle.BORDER_THIN);
				myStyle.setBorderTop(CellStyle.BORDER_THIN);
				myStyle.setFillForegroundColor(HSSFColor.RED.index);
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cel4.setCellStyle(myStyle);
				fnt.setBoldweight(Font.BOLDWEIGHT_BOLD);
				myStyle.setFont(fnt);
			}
			fout=new FileOutputStream(excelFilePath);
			workbook.write(fout);
			//rowNum +=1;
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
		
		public void writeInTextFile(String cityState,String rdc,String Zillow,String trulia,String status,String percentageZillow,String percentageTrulia) 
		{
			FileOutputStream fos=null;PrintWriter writer = null;
			        try {
			        	
						Date dNow = new Date( );
					    String fileName=Driver.Gprops.getTextFilePath();
			        	file=new File(fileName);
			        	fos=new FileOutputStream(file,true);
			            writer=new PrintWriter(fos);  
			            writer.println(cityState+","+rdc+","+Zillow+","+trulia+","+status+","+percentageZillow+","+percentageTrulia);
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


