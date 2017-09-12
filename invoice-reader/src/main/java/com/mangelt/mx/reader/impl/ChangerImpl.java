package com.mangelt.mx.reader.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mangelt.mx.reader.Changer;

public class ChangerImpl implements Changer {
	
	protected Map<String, String> words = new HashMap<String, String>();
	
	public ChangerImpl(){
		words.put(" {1}Certificado *={1}", " certificado=");
		words.put(" {1}NoCertificado *={1}", " noCertificado=");
		words.put(" {1}CondicionesDePago *={1}", " condicionesDePago=");
		words.put(" {1}Descuento *={1}", " descuento=");
		words.put(" {1}SubTotal *={1}", " subTotal=");
		words.put(" {1}FormaDePago *={1}", " formaDePago=");
		words.put(" {1}Serie *={1}", " serie=");
		words.put(" {1}Version *={1}", " version=");
		words.put(" {1}Folio *={1}", " folio=");
		words.put(" {1}Sello *={1}", " sello=");
		words.put(" {1}Fecha *={1}", " fecha=");
		words.put(" {1}Total *={1}", " total=");
		words.put(" {1}MetodoDePago *={1}", " metodoDePago=");
		words.put(" {1}TipoDeComprobante *={1}", " tipoDeComprobante=");
		words.put(" {1}Rfc *={1}", " rfc=");
		words.put(" {1}Nombre *={1}", " nombre=");
		words.put(" {1}ValorUnitario *={1}", " valorUnitario=");
		words.put(" {1}Importe *={1}", " importe=");
		words.put(" {1}Impuesto *={1}", " impuesto=");
	}

	public void changeWord(File concurrenteInvoice) throws IOException {
		String concurrentContent = null;
		StringBuilder allInvoice = new StringBuilder();
		FileReader reader = new FileReader(concurrenteInvoice);
		BufferedReader bufferReader = new BufferedReader(reader);
		while((concurrentContent = bufferReader.readLine())!=null)
		{
			for (Map.Entry<String, String> entry : words.entrySet()) {
				concurrentContent = concurrentContent.replaceAll(entry.getKey(), entry.getValue());
			}
			allInvoice.append(concurrentContent);
		}
		reader.close();
		bufferReader.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(concurrenteInvoice));
		String content = allInvoice.toString();
		bw.write(content);
		bw.close();
	}

}
