package com.mangelt.mx.invoice.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.mangelt.mx.reader.Reader;
import com.mangelt.mx.reader.impl.ReaderImpl;

public class Report {
	
	public boolean doCreate(String path, String output){
		File dir = new File(path);
		return doCreate(dir, output);
	}
	
	public boolean doCreate(String path){
		File dir = new File(path);
		return doCreate(dir, "report");
	}
	
	public boolean doCreate(File dir, String output){
		
		try {
        	
        	List<File> invoices = new ArrayList<File>();  
        	
//        	It gets all files 
        	File[] files = dir.listFiles();
        	
//        	it verifies each files 
        	for (File file : files) {
        		
//        		it checks if the file is a directory
				if(file.isDirectory()){

//					it gets all files into of the directory  
					File[] concurrentFiles = file.listFiles();
					
//					it verifies each file
					for (File file2 : concurrentFiles) {
						
//						it gets the file extension
						String ext = FilenameUtils.getExtension(file2.getName());
						
//						if it is an xml file then is added
						if(ext.equals("xml")){
							invoices.add(file2);
						}
						
					}
					
				}else{
					
//					it gets the file extension
					String ext = FilenameUtils.getExtension(file.getName());
					
//					if it is an xml file then is added
					if(ext.equals("xml")){
						invoices.add(file);
					}
					
				}
				
			}
			
			Reader reader = new ReaderImpl();
        	
			StatementInvoices statementInvoices= new StatementInvoices(dir.getAbsolutePath()+"\\"+output+".xls");
			
			statementInvoices.createHeadInXls();
			
			statementInvoices.createBodyXls(reader.readInvoice(invoices));
			
			return statementInvoices.createFileXls();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
