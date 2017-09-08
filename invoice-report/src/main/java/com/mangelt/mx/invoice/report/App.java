package com.mangelt.mx.invoice.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.mangelt.mx.reader.Reader;
import com.mangelt.mx.reader.impl.ReaderImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Report report = new Report();
    	
    	boolean result = report.doCreate("C:\\Users\\vn0x53q\\Documents\\SAT\\declaracion jul-ago\\emitidas");
    	
    	System.out.println(result);
    	
    }
}
