package com.mangelt.mx.reader.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mangelt.mx.reader.Reader;
import com.mangelt.mx.reader.api.Comprobante;

public class ReaderImpl implements Reader {
	
	public Comprobante readInvoice(File invoice){
		
		Comprobante comprobante = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance( "com.mangelt.mx.reader.api" );
			Unmarshaller u = jc.createUnmarshaller();		
			comprobante = (Comprobante)u.unmarshal(invoice);
			return comprobante;
		} catch (JAXBException e) {
			System.out.println("Error to try Unmarshall: " + e);
		}
		
		return comprobante;
		
	}
	
	public List<Comprobante> readInvoice(File[] invoices){
		
		List<Comprobante> comprobantes = new  ArrayList<Comprobante>();
		
		for (File invoice : invoices) {
			
			Comprobante comprobante = this.readInvoice(invoice);
			
			if(comprobante != null){
				comprobantes.add(comprobante);
			}
			
		}
		
		return comprobantes;
		
	}
	
}
