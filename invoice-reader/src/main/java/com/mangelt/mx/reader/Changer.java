package com.mangelt.mx.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Changer {
	
	void changeWord(File concurrenteInvoice) throws FileNotFoundException, IOException;
	
}
