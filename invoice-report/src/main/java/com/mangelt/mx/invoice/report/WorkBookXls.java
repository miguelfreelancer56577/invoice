package com.mangelt.mx.invoice.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mangelt.mx.reader.api.Comprobante;

public abstract class WorkBookXls extends File{
	
	private static final long serialVersionUID = 6483946532992521752L;
	
	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	
	protected String pathName;
	
	protected FileOutputStream fileOut;
	
	public WorkBookXls(String pathName) throws IOException {
		
		super(pathName);
		
		if(!super.exists())
			super.createNewFile();
		
		workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Sat");
		
	}

	protected abstract void createHeadInXls();
	
	public abstract void createBodyXls(List<Comprobante> invoices);
	
	public final boolean createFileXls(){
		try {
			fileOut = new FileOutputStream(this);
			workbook.write(fileOut);
			System.out.println("Your excel file has been generated successfully!");
			return true;
		} catch (IOException e) {
			System.out.println("Error to write a file");
			e.printStackTrace();
			return false;
		}finally{
			if(fileOut != null)
				try {
					fileOut.close();
					System.out.println( pathName + " it has been closed successfully");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error to closed " + pathName + " file.");
				}
		}
		
	}
	
	
}
