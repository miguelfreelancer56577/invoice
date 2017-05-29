package com.mangelt.mx.invoice.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mangelt.mx.invoice.report.Report;

public class ActionListenerSelectImpl implements ActionListener{

	JVentana v;
	StringBuilder log;

	public ActionListenerSelectImpl(JVentana v) {
		super();
		this.v = v;
		log = v.log;
	}
	
	public void actionPerformed(ActionEvent e){
		
		log.append("Procesando Directorio...\n");
		
		String path = v.txtPath.getText();
		
		String nameOutput = v.txtOutput.getText();
		
		boolean statusOK = true;
		
		if(path.equals("")){
			JOptionPane.showMessageDialog(v, "Por favor ingrese la ruta donde se encuentran las facturas.", "Directorio", JOptionPane.ERROR_MESSAGE);
			statusOK = false;
		}
		
		if(nameOutput.equals("")){
			JOptionPane.showMessageDialog(v, "Por favor ingrese el nombre del archivo de salida.", "Nombre del reporte", JOptionPane.ERROR_MESSAGE);
			statusOK = false;
		}
		
		if(statusOK == true){
			Report report = new Report();
	    	boolean result = report.doCreate(path,nameOutput);
		}
		
	}
	
	public void doDestroy(){
		v.lblLog.setText(log.toString());
	}
	
}
