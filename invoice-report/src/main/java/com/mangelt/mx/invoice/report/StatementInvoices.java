package com.mangelt.mx.invoice.report;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.mangelt.mx.reader.api.Comprobante;
import com.mangelt.mx.reader.api.Traslado;

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
		rowhead.createCell(1).setCellValue("RFC Emisor");
		rowhead.createCell(2).setCellValue("Nombre o Razón Social del Emisor");
		rowhead.createCell(3).setCellValue("RFC Receptor");
		rowhead.createCell(4).setCellValue("Nombre o Razón Social del Receptor");
		rowhead.createCell(5).setCellValue("Fecha de Emisión");
		rowhead.createCell(6).setCellValue("Iva");
		rowhead.createCell(7).setCellValue("monto");
		rowhead.createCell(8).setCellValue("exento");
		rowhead.createCell(9).setCellValue("Total");
		rowhead.createCell(10).setCellValue("Efecto del Comprobante");
		
		positionRow++;
		
	}

	@Override
	public void createBodyXls(List<Comprobante> invoices) {
		
		for (Comprobante comprobante : invoices) {
			
			HSSFRow rowhead = sheet.createRow((short)positionRow);
			rowhead.createCell(0).setCellValue(comprobante.getComplemento().getTimbreFiscalDigital().getUUID());
			rowhead.createCell(1).setCellValue(comprobante.getEmisor().getRfc());
			rowhead.createCell(2).setCellValue(comprobante.getEmisor().getNombre());
			rowhead.createCell(3).setCellValue(comprobante.getReceptor().getRfc());
			rowhead.createCell(4).setCellValue(comprobante.getReceptor().getNombre());
			rowhead.createCell(5).setCellValue(comprobante.getFecha().toString());
			
			try {
				List<Traslado> impuestos = comprobante.getImpuestos().getTraslados().getTraslado();
				
				for (Traslado traslado : impuestos) {
					if(traslado.getImpuesto().equalsIgnoreCase("Iva")){
						rowhead.createCell(6).setCellValue(traslado.getImporte());
						break;
					}
				}
			} catch (Exception e) {
				rowhead.createCell(6).setCellValue(0);
			}
//			
			rowhead.createCell(7).setCellValue("monto");
			rowhead.createCell(8).setCellValue("exento");
			rowhead.createCell(9).setCellValue("Total");
			rowhead.createCell(10).setCellValue(comprobante.getTipoDeComprobante());
			
			positionRow++;
		}

	}

}
