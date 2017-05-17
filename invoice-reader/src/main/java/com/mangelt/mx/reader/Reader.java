package com.mangelt.mx.reader;

import java.io.File;
import java.util.List;

import com.mangelt.mx.reader.api.Comprobante;

public interface Reader {

	Comprobante readInvoice(File invoice);
	
	List<Comprobante> readInvoice(File[] invoice);
	
}
