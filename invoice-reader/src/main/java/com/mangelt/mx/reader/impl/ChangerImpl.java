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
	}

	public void changeWord(File concurrenteInvoice) throws IOException {
		String concurrentContent = null;
		StringBuilder allInvoice = new StringBuilder();
		FileReader reader = new FileReader(concurrenteInvoice);
		BufferedReader bufferReader = new BufferedReader(reader);
		while((concurrentContent = bufferReader.readLine())!=null)
		{
			for (Map.Entry<String, String> entry : words.entrySet()) {
				concurrentContent = concurrentContent.replaceFirst(entry.getKey(), entry.getValue());
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
