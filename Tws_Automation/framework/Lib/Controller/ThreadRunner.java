package Lib.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.velocity.runtime.directive.Break;

import Lib.Common.CommonUtil;

public class ThreadRunner {
	
	


	private static int MYTHREADS = 300;

	public static void main(String[] args) {

		//Declaration
		ExecutorService executor = null;
		String featureFileDir="";
		File[] featureFileList = null;
		String[] featureFileNames=null;
		String[] FeatureFilePath=null;
		String projectName=System.getProperty("ProjectName").trim();
		String userDir=System.getProperty("user.dir").trim();
		String compName=System.getProperty("CompName").trim();
		String featureFileName=System.getProperty("FetFileName").trim();
		String tagName=System.getProperty("Tag").trim();

		System.out.println("Project Name: "+ projectName);
		System.out.println("User Directory: "+ userDir);

		//Check project name is blank or null
		if(projectName.trim().equalsIgnoreCase("") || projectName==null){
			System.out.println("Project name is blank or null. Program exists");
			System.exit(0);
		}//updated below code to limit the number of threads per project by murthi on 10/28/2015
		else if(projectName.trim().equalsIgnoreCase("rwdrdc")){
			MYTHREADS=300;
		}
		else if(projectName.trim().equalsIgnoreCase("uhura")){
			MYTHREADS=300;
		}
		else if(projectName.trim().equalsIgnoreCase("rdcweb")){
			MYTHREADS=3000;
		}
		else if(projectName.trim().equalsIgnoreCase("tigerlead")){
			MYTHREADS=300;
		}
		executor=Executors.newFixedThreadPool(MYTHREADS);


		//Change the user directory if run from eclipse
		if(!userDir.toLowerCase().trim().contains(projectName.toLowerCase().trim())){
			userDir=userDir+"/"+projectName.trim();
			System.out.println("User Directory: "+ userDir);
		}

		if(!userDir.trim().contains(projectName.trim())){
			userDir=userDir+"/"+projectName.trim();
			System.out.println("User Directory: "+ userDir);
		}

		System.out.println("User Directory: "+ userDir);
		//Generate Common run id
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("MdyHmsz");
		String RunID = ft.format(dNow)+String.valueOf(System.currentTimeMillis());
		Driver.globalValues.put("RunID", RunID);

		//Create folder in target with name of Run id 
		new File(System.getProperty("user.dir")+"/target/"+Driver.globalValues.get("RunID")).mkdir();

		//Check feature file name
		if(featureFileName.trim().equals("")||featureFileName.equalsIgnoreCase("all")){
			featureFileName=null;
		}

		//check component name
		if(compName.trim().equals("")||compName.equalsIgnoreCase("all")){
			compName=null;
			Driver.globalValues.put("CompName", "null");
		}
		else{
			Driver.globalValues.put("CompName", compName);
		}

		//check tag name
		if(tagName.trim().equals("")||tagName.equalsIgnoreCase("all")){
			tagName=null;
		}

		//Add feature files
		if(compName!=null && featureFileName !=null){
			boolean found;
			int Count=0;
			System.out.println(featureFileName);
			List<File> FetFiles=new ArrayList<File>();
			String[] CompNames=compName.split(",");
			featureFileNames=featureFileName.split(",");
			for(int Iter=0;Iter<featureFileNames.length;Iter++){
				found=false;
				for(int IterA=0;IterA<CompNames.length;IterA++){
					File file=new File(userDir+"/FeatureFiles/"+CompNames[IterA]+"/"+featureFileNames[Iter]);
					if(file.isFile()){
						found=true;
						FetFiles.add(file);
						Count+=1;
					}
					else{
						if(IterA==CompNames.length-1 && found==false){
							System.out.println("feature file "+featureFileNames[Iter]+" did not exist.Please check the feature file name");
						}
					}
				}
			}
			featureFileList=new File[Count];
			for(int Iter=0; Iter<FetFiles.size();Iter++){
				featureFileList[Iter]=FetFiles.get(Iter);
			}
		}

		//Add feature files directory
		if(compName!=null && featureFileName==null){
			if(compName.contains(",")){
				String[] compNames=compName.split(",");
				List<File> featureFiles=new ArrayList<File>();
				List<File> featureFilesSub=new ArrayList<File>();
				String[] includes=null;
				for(int Iter=0;Iter<compNames.length;Iter++){
					if(!compNames[Iter].equals("")){
						//						featureFileDir=userDir+"/FeatureFiles/"+compNames[Iter].trim();
						//						File directory = new File(featureFileDir.trim());
						//						if(directory.isDirectory()){
						//							String[] extensions = new String[] { "feature" };
						//							featureFilesSub = (List<File>) FileUtils.listFiles(directory, extensions, true);
						//						}
						//						else{
						//							System.out.println("Component "+compName+" did not exist.Please check the component name");
						//							System.exit(-1);
						//						}
						//added by murthi to include wild cards in the component name
						includes=new String[]{compNames[Iter].trim()+"/**/*.feature"};
						DirectoryScanner ds= new DirectoryScanner();
						ds.setBasedir(userDir+"/FeatureFiles/");
						ds.setIncludes(includes);
						ds.setCaseSensitive(false);
						ds.scan();
						String[] files = ds.getIncludedFiles();
						if(ds.getIncludedFilesCount()>0){
							for ( int i = 0; i < files.length; i++ )
							{
								featureFilesSub.add(new File(userDir+"/FeatureFiles/"+files[i]));
							}
						}
						else{
							System.out.println("Component "+compName+" did not exist.Please check the component name");
							System.exit(-1);
						}


					}
					featureFiles.addAll(featureFilesSub);
				}
				featureFileList=new File[featureFiles.size()];
				for(int Iter=0;Iter<featureFiles.size();Iter++){
					featureFileList[Iter]=featureFiles.get(Iter);
				}

			}else{
				List<File> featureFiles=new ArrayList<File>();
				String[] includes=null;
				//				featureFileDir=userDir+"/FeatureFiles/"+compName;
				//				File directory = new File(featureFileDir.trim());
				//				if(directory.isDirectory()){
				//					String[] extensions = new String[] { "feature" };
				//					featureFiles = (List<File>) FileUtils.listFiles(directory, extensions, true);
				//				}
				//				else{
				//					System.out.println("Component "+compName+" did not exist.Please check the component name");
				//					System.exit(-1);
				//				}
				//added by murthi to include wild cards in the component name
				includes=new String[]{compName.trim()+"/**/*.feature"};
				DirectoryScanner ds= new DirectoryScanner();
				ds.setBasedir(userDir+"/FeatureFiles/");
				ds.setIncludes(includes);
				ds.setCaseSensitive(false);
				ds.scan();
				String[] files = ds.getIncludedFiles();
				if(ds.getIncludedFilesCount()>0){
					for ( int i = 0; i < files.length; i++ )
					{
						featureFiles.add(new File(userDir+"/FeatureFiles/"+files[i]));
					}
				}
				else{
					System.out.println("Component "+compName+" did not exist.Please check the component name");
					System.exit(-1);
				}

				featureFileList=new File[featureFiles.size()];
				for(int Iter=0;Iter<featureFiles.size();Iter++){
					featureFileList[Iter]=featureFiles.get(Iter);
				}
			}
		}

		//Adding feature files
		if(compName==null && featureFileName!=null){
			try {
				if(featureFileName.contains("\\")){
					featureFileName=featureFileName.substring(featureFileName.lastIndexOf('\\')+1);
				}
				featureFileDir=userDir+"/FeatureFiles";
				System.out.println("Featuer file path:"+featureFileDir);
				File FetFileFolder=new File(featureFileDir);
				FeatureFilePath=find_files(FetFileFolder,featureFileName);
				featureFileList=new File[FeatureFilePath.length];
				for(int Iter=0;Iter<FeatureFilePath.length;Iter++){
					featureFileList[Iter]=new File(FeatureFilePath[Iter]);
				}			
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

		//Add feature files if compName and feature file name are null
		if(compName==null && featureFileName == null){
			featureFileDir=userDir+"/FeatureFiles";
			System.out.println("Featuer file path:"+featureFileDir);
			File directory = new File(featureFileDir);
			featureFileList=new File[1];
			featureFileList[0]=directory;
		}

		if(featureFileList.length==0){
			System.out.println("Feature file name "+featureFileName+" did not exist.please check the feature file name");
			System.exit(-1);
		}

		//copy arg values
		String[] argv1=null;
		if(tagName==null){
			argv1=new String[args.length+3];
		}
		else{
			argv1=new String[args.length+5];
		}

		for(int IterA=0;IterA<args.length;IterA++){
			argv1[IterA]=args[IterA];
		}

		//Set Feature file count
		Driver.globalValues.put("NoOfFeatureFiles", String.valueOf(featureFileList.length));


		List<Thread> Threads= new ArrayList<Thread>();

		Driver.setUp();
		String[] Browsers = null;

		if(Driver.Init.GetFromEnvironment("webbrowser").trim().equalsIgnoreCase("rwd-all")){
			Browsers = new String[] { "rwd-ie8","rwd-ie9","rwd-ie10","rwd-ie11","rwd-ff","rwd-gc" };
		}
		else{
			Browsers = Driver.Init.GetFromEnvironment("webbrowser").split("~");
		}
		//		int thread_count=0;
		//Start threading
		for(int IterB=0;IterB<featureFileList.length;IterB++){

			//			String[] Tags=tagName.split(",");
			for(int IterB1=0; IterB1<Browsers.length; IterB1++){
				//Get the feature file name or component name
				//				Driver.Init.SetAUTBrowser(Browsers[IterB1]);
				String FileName=featureFileList[IterB].getName();
				if(FileName.contains(".feature")){
					System.out.println("Executing the feature file - "+FileName);
				}
				else{
					System.out.println("Executing the component - " +FileName);
				}


				argv1[args.length]="-f";
				argv1[args.length+1]="json:target/"+Driver.globalValues.get("RunID")+"/cucumber-report"+IterB+".json";

				if(tagName!=null){
					argv1[args.length+2]="--tags";
					argv1[args.length+3]=tagName;
					argv1[args.length+4]=featureFileList[IterB].getAbsolutePath();
					//					argv1[args.length+5]=Browsers[IterB1];
				}
				else{
					argv1[args.length+2]=featureFileList[IterB].getAbsolutePath();
					//					argv1[args.length+3]=Browsers[IterB1];
				}

				final String[] argv2=new String[argv1.length];
				for(int IterC=0;IterC<argv1.length;IterC++){
					argv2[IterC]=argv1[IterC];
				}

				//				thread_count++;
				//				final Thread thread=new Thread(new Runnable(){
				//					
				//					@Override
				//					public void run(){
				//						try {
				//							Driver.main(argv2);
				//						} catch (Throwable e) {
				//							e.printStackTrace();
				//						}
				//					}
				//				});
				//				Threads.add(thread);
				//				thread.start();
				//				Driver.globalValues.put(thread.getName()+"browser", Browsers[IterB1]);
				//				Driver.globalValues.put(thread.getName()+"AssertFlag","true");
				Runnable worker = new Runnable(){
					@Override
					public void run(){
						try {
							Driver.globalValues.put(Thread.currentThread().getName()+"AssertFlag","true");
							Driver.main(argv2);
							//							Driver.globalValues.put(Thread.currentThread().getName()+"browser", browser[IterB1]);

						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
				};
				executor.execute(worker);
				//				
			}

			//			if(thread_count<=MYTHREADS){
			//				for (Thread thread : Threads) {
			//				    try {
			//					    // Wait for this thread to die
			//					    thread.join();
			//				    } catch (InterruptedException e) {
			//					    // Ignore this but print a stack trace
			//					    e.printStackTrace();
			//				    }
			//			    }
			//			}
			//			else{
			//				thread_count=1;
			//			}
		}
		//		
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {

		}

		//	    // Loop through the threads
		//	    for (Thread thread : Threads) {
		//		    try {
		//			    // Wait for this thread to die
		//			    thread.join();
		//		    } catch (InterruptedException e) {
		//			    // Ignore this but print a stack trace
		//			    e.printStackTrace();
		//		    }
		//	    }


		System.out.println("All threads in the test have completed..");


		Driver.CUtil.ComposeProcessedLog();


		// following code added by KK to insert the values to cukeresults table - 05/12/2013
		//@depericated this code as of 03/27/2014
		//        String plHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/ProcessedLog.html";
		//		String foHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/feature-overview.html";
		//		String lgHtml = "http://QAZ02HTP900:8080/Result/"+Driver.Gprops.GetRunID()+"/Log.html";
		//		Driver.DBCon.InsertCuckResult(Driver.Gprops.GetRunID(), System.getProperty("ProjectName"), System.getProperty("EnvFileName"), plHtml, foHtml, lgHtml, String.valueOf(Driver.Gprops.GetPassCounter()), String.valueOf(Driver.Gprops.GetFailCounter()),"0",System.getProperty("FetFileName"),System.getProperty("Tag"), Driver.Init.GetAUTBrowser());
		//		Driver.CUtil.CreateIndexFile();
		String TS="";
		//Added code for Production test By-Vijendra(06/09/2017)
		TS=Driver.globalValues.get("SCENARIO");
		//String CTI=Driver.globalValues.get("SUBJECT");
		if(Driver.generateReports()){
			if(TS!=null){
				Driver.SendMailTWS();
			}
			else{
				Driver.SendMail();
			}

		}
		else{
			System.out.println("Unable to send mail as the file upload of the report generation failed");
		}
		Driver.deleteTimeStampFolder();
	}


	/**
	 * This function will search a feature file in side the root folder
	 * @param Folder to be searched
	 * @param FileName to search
	 * @return array of file paths which are matched
	 * @throws IOException
	 * @author msubramani<murthi.subramani@move.com>
	 * Date updated: 6/5/2015
	 */
	public static String[] find_files(File root,String FileName) throws IOException
	{

		List<File> FetFiles=new ArrayList<File>();
		String[] FileNames=FileName.split(",");
		String[] fileArr = null;
		try{
			try {
				//Search for the feature file in the component
				for(int Iter=0;Iter<FileNames.length;Iter++){
					boolean FileFound=false;
					String FileNameSearched=FileNames[Iter].trim();
					String[] extensions = new String[] { "feature" };
					List<File> featureFiles = (List<File>) FileUtils.listFiles(root, extensions, true);
					for (int IterA=0;IterA<featureFiles.size();IterA++) {
						File feature=featureFiles.get(IterA);
						String featFileName=feature.getName();
						if(featFileName.equals(FileNameSearched)){
							FetFiles.add(feature);
							FileFound=true;
						}
					}	
					if(!FileFound){
						System.out.println("Feature file " +FileNames[Iter].toString()+" did not exist. Please check the file name is matching with actual file name");
					}

				}				    

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

			fileArr = new String[FetFiles.size()];
			for(int IterC=0;IterC<FetFiles.size();IterC++){
				fileArr[IterC]=FetFiles.get(IterC).getCanonicalPath();
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return fileArr;
	}



}
