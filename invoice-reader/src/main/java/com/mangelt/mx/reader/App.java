package com.mangelt.mx.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.mangelt.mx.reader.impl.ChangerImpl;

public class App {

	public static void main(String[] args) {
		Changer changer = new ChangerImpl();
		File f = new File("C:\\Users\\vn0x53q\\Documents\\SAT\\declaracion jul-ago\\emitidas\\871CFE2B-9E5A-4F97-98E3-C80F686ED294.xml");
		try {
			changer.changeWord(f);
			System.out.println("Everything was ok.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
