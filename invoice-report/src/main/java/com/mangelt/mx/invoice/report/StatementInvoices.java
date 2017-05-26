package com.mangelt.mx.invoice.report;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.mangelt.mx.reader.api.Comprobante;

public class StatementInvoices extends WorkBookXls {
	
	public int positionRow = 0;

	public StatementInvoices(String pathName) throws IOException {
		super(pathName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4635937628687043213L;

	@Override
	protected void createHeadInXls() {
		
		HSSFRow rowhead = sheet.createRow((short)positionRow);
		rowhead.createCell(0).setCellValue("Folio Fiscal");
		rowhead.createCell(0).setCellValue("RFC Emisor");
		rowhead.createCell(0).setCellValue("Nombre o Razón Social del Emisor");
		rowhead.createCell(0).setCellValue("RFC Receptor");
		rowhead.createCell(0).setCellValue("Nombre o Razón Social del Receptor");
		rowhead.createCell(0).setCellValue("Fecha de Emisión");
		rowhead.createCell(0).setCellValue("Iva");
		rowhead.createCell(0).setCellValue("monto");
		rowhead.createCell(0).setCellValue("exento");
		rowhead.createCell(0).setCellValue("Total");
		rowhead.createCell(0).setCellValue("Efecto del Comprobante");
		
		positionRow++;
		
	}

	@Override
	public void createBodyXls(List<Comprobante> invoices) {
		
		for (Comprobante comprobante : invoices) {
			
			HSSFRow rowhead = sheet.createRow((short)positionRow);
			rowhead.createCell(0).setCellValue("Folio Fiscal");
			rowhead.createCell(0).setCellValue("RFC Emisor");
			rowhead.createCell(0).setCellValue("Nombre o Razón Social del Emisor");
			rowhead.createCell(0).setCellValue("RFC Receptor");
			rowhead.createCell(0).setCellValue("Nombre o Razón Social del Receptor");
			rowhead.createCell(0).setCellValue("Fecha de Emisión");
			rowhead.createCell(0).setCellValue("Iva");
			rowhead.createCell(0).setCellValue("monto");
			rowhead.createCell(0).setCellValue("exento");
			rowhead.createCell(0).setCellValue("Total");
			rowhead.createCell(0).setCellValue("Efecto del Comprobante");
			
			positionRow++;
		}

	}

}
