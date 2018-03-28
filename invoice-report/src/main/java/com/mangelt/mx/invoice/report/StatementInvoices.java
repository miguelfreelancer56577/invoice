package com.mangelt.mx.invoice.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.mangelt.mx.reader.api.Comprobante;
import com.mangelt.mx.reader.api.Traslado;

public class StatementInvoices extends WorkBookXls {
	
	public int positionRow = 0;
	
	public final float porcentage = 100;
	
	public final float tasa = 16;
	
	public float totalIvaEmitido = 0;
	
	public float totalIvaRecibido = 0;
	
	public float totalEmitido = 0;
	
	public float totalRecibido = 0;
	
	public Map<String, Integer> rowNumbers; 
	
	protected enum TypeInvoice {
		
		A("Emitidas"), B("Recibidas"), C("Egreso"), D("Sin contabilizar");
		
		String typeInvoice;
		
		TypeInvoice(String typeInvoice){
			this.typeInvoice = typeInvoice;
		}

		public String getTypeInvoice() {
			return typeInvoice;
		}
		
	}

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
		
		List<Comprobante> issued = new ArrayList<Comprobante>();
		
		List<Comprobante> received = new ArrayList<Comprobante>();
		
		List<Comprobante> withoutEffect = new ArrayList<Comprobante>();
		
		List<Comprobante> other = new ArrayList<Comprobante>();
		
		for (Comprobante comprobante : invoices) {
			
			if(comprobante.getEmisor().getRfc() != null && comprobante.getEmisor().getRfc().equalsIgnoreCase("torm8908224f4")){
				issued.add(comprobante);
				continue;
			}
			
			if(comprobante.getTipoDeComprobante() !=null && comprobante.getTipoDeComprobante().equalsIgnoreCase("egreso")){
				withoutEffect.add(comprobante);
				continue;
			}
			
			if((comprobante.getReceptor().getRfc() != null && comprobante.getTipoDeComprobante() != null && comprobante.getReceptor().getRfc().equalsIgnoreCase("torm8908224f4")) && comprobante.getTipoDeComprobante().equalsIgnoreCase("ingreso") || comprobante.getTipoDeComprobante().equalsIgnoreCase("I") ){
				received.add(comprobante);
				continue;
			}
			
			other.add(comprobante);
			
		}
		
		rowNumbers = new HashMap<String, Integer>(); 
		
		rowNumbers.put("issued", issued.size());
		rowNumbers.put("received", received.size());
		rowNumbers.put("withoutEffect", withoutEffect.size());
		rowNumbers.put("other", other.size());
		
		positionRow = printContent(issued, positionRow, TypeInvoice.A) + 1;
		
		positionRow = printContent(received, positionRow, TypeInvoice.B) + 1;
		
		positionRow = printContent(withoutEffect, positionRow, TypeInvoice.C) + 1;
		
		positionRow = printContent(other, positionRow, TypeInvoice.D);
		
		declaration(positionRow);

	}
	
	public void declaration(int endPosition){
		
		HSSFRow rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("Iva a declarar");
		rowhead.createCell(1).setCellValue(totalIvaEmitido - totalIvaRecibido);
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("Comparacion de gastos");
		rowhead.createCell(1).setCellValue(totalEmitido - totalRecibido);
		
//		se saltan dos filas
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("No. emitidas");
		rowhead.createCell(1).setCellValue(rowNumbers.get("issued"));
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("No. recibidas C/E:");
		rowhead.createCell(1).setCellValue(rowNumbers.get("received"));
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("No. recibidas S/E");
		rowhead.createCell(1).setCellValue(rowNumbers.get("withoutEffect"));
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("No. otras");
		rowhead.createCell(1).setCellValue(rowNumbers.get("other"));
		rowhead = sheet.createRow((short)endPosition++);
		rowhead.createCell(0).setCellValue("No. facturas");
		rowhead.createCell(1).setCellValue((rowNumbers.get("issued")+rowNumbers.get("received")+rowNumbers.get("withoutEffect")+rowNumbers.get("other")));
		
	}
	
	public int printContent(List<Comprobante> invoices, int startPosition, TypeInvoice typeInvoice){
		
		if(invoices.size()==0)
			return startPosition;
		
		float totalIva = 0;
		float totalMonto = 0;
		float totalExento = 0;
		float total = 0;
		
		for (Comprobante comprobante : invoices) {
			
			float monto = 0;
			
			float exento = 0;
			
			HSSFRow rowhead = sheet.createRow((short)startPosition);
			rowhead.createCell(0).setCellValue(comprobante.getComplemento().getTimbreFiscalDigital().getUUID());
			rowhead.createCell(1).setCellValue(comprobante.getEmisor().getRfc());
			rowhead.createCell(2).setCellValue(comprobante.getEmisor().getNombre());
			rowhead.createCell(3).setCellValue(comprobante.getReceptor().getRfc());
			rowhead.createCell(4).setCellValue(comprobante.getReceptor().getNombre());
			rowhead.createCell(5).setCellValue(comprobante.getFecha().toString());
			
			try {
				List<Traslado> impuestos = comprobante.getImpuestos().getTraslados().getTraslado();
				
				for (Traslado traslado : impuestos) {
					if(traslado.getImpuesto().equalsIgnoreCase("Iva") || traslado.getImpuesto().equalsIgnoreCase("002")){
						
						rowhead.createCell(6).setCellValue(traslado.getImporte());
						
						totalIva += traslado.getImporte();
						
						monto = (porcentage * traslado.getImporte())/tasa;
						
						totalMonto += monto; 
						
						exento = comprobante.getTotal() - (traslado.getImporte() + monto);
						
//						if(exento < 0)
//							exento = 0;
						
						totalExento += exento; 
						
						break;
					}
				}
			} catch (Exception e) {
				rowhead.createCell(6).setCellValue(monto);
			}
//			
			rowhead.createCell(7).setCellValue(monto);
			rowhead.createCell(8).setCellValue(exento);
			
			rowhead.createCell(9).setCellValue(comprobante.getTotal());
			
			total += comprobante.getTotal();
			
			rowhead.createCell(10).setCellValue(comprobante.getTipoDeComprobante());
			
			startPosition++;
		}
		
		HSSFRow rowhead = sheet.createRow((short)startPosition);
		rowhead.createCell(5).setCellValue("Totales");
		rowhead.createCell(6).setCellValue(totalIva);
		rowhead.createCell(7).setCellValue(totalMonto);
		rowhead.createCell(8).setCellValue(totalExento);
		rowhead.createCell(9).setCellValue(total);
		
		switch (typeInvoice) {
		
			case A:
				totalIvaEmitido = totalIva;
				totalEmitido = totalMonto;
				break;
				
			case B:
				totalIvaRecibido = totalIva;
				totalRecibido = totalMonto;
				break;
	
			default:
				break;
				
		}
		
		return startPosition+2;
		
	}

}
