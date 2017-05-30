package com.mangelt.mx.reader;

import java.io.File;

import com.mangelt.mx.reader.impl.ReaderImpl;
import com.sun.org.apache.regexp.internal.recompile;

public class Test {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\vn0x53q\\Downloads\\A-18_1raMay.xml");
		
		if(f.exists()){
			
			Reader reader = new ReaderImpl();
			
			System.out.println(reader.readInvoice(f));
			
		}else{
			
			System.out.println("este archivo..");
			
		}
		
		
		
	}
	
}
