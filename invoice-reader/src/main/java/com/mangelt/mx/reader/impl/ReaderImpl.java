package com.mangelt.mx.reader.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mangelt.mx.reader.Changer;
import com.mangelt.mx.reader.Reader;
import com.mangelt.mx.reader.api.Comprobante;

public class ReaderImpl implements Reader {
	
	public Comprobante readInvoice(File invoice){
		
		System.out.println("Concurrent invoice: " + invoice.getName());
		
		Comprobante comprobante = null;
		
		try {
			Changer changer = new ChangerImpl();
			changer.changeWord(invoice);
			JAXBContext jc = JAXBContext.newInstance( "com.mangelt.mx.reader.api" );
			Unmarshaller u = jc.createUnmarshaller();		
			comprobante = (Comprobante)u.unmarshal(invoice);
			return comprobante;
		} catch (JAXBException e) {
			System.out.println("Error to try Unmarshall: " + e);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return comprobante;
		
	}
	
	public List<Comprobante> readInvoice(File[] invoices){
		
		List<Comprobante> comprobantes = new  ArrayList<Comprobante>();
		
		int numInvoice = 1;
		
		for (File invoice : invoices) {
			
			Comprobante comprobante = this.readInvoice(invoice);
			
			System.out.println("Number: " + numInvoice + " " + invoices.toString() + " - " + comprobante.getCertificado());
			
			numInvoice++;
			
			if(comprobante != null){
				comprobantes.add(comprobante);
			}
			
		}
		
		return comprobantes;
		
	}
	
	public List<Comprobante> readInvoice(List<File> invoices){
		
		List<Comprobante> comprobantes = new  ArrayList<Comprobante>();
		
		int numInvoice = 1;
		
		for (File invoice : invoices) {
			
			Comprobante comprobante = this.readInvoice(invoice);
			
			System.out.println("Number: " + numInvoice + " " + invoices.toString() + " - " + comprobante.getCertificado());
			
			numInvoice++;
			
			if(comprobante != null){
				comprobantes.add(comprobante);
			}
			
		}
		
		return comprobantes;
		
	}
	
}
