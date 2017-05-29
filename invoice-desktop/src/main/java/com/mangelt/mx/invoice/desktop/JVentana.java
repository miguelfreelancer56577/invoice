package com.mangelt.mx.invoice.desktop;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mangelt.mx.invoice.desktop.bean.Config;

public class JVentana extends JFrame {
	
	JTextField txtPath;
	JTextField txtOutput;
	JLabel lblPath;
	JLabel lblOutput;
	JButton btnLoad;
	JTextArea lblLog;
	
	StringBuilder log = new StringBuilder("Copie la ruta de la carpeta donde se encuentran las facturas electronicas. \n");
	
	public JVentana(Config config){
		super(config.getTitle());
		onCreate(config);
		onStart();
	}
	
	public void onCreate(Config config){
		getContentPane().setLayout(null);
		setBounds(config.getPositionX(), config.getPositionY(), config.getWidth(), config.getHigh());
		txtPath = new JTextField();
		lblPath = new JLabel("Ruta de Entrada:");
		lblPath.setBounds(5, 5, 100, 20);
		txtPath.setBounds(105, 5, 200, 20);
		txtOutput = new JTextField();
		lblOutput = new JLabel("Nombre del archivo:");
		txtOutput = new JTextField();
		lblOutput.setBounds(5, 30, 120, 20);
		txtOutput.setBounds(125, 30, 200, 20);
		btnLoad = new JButton("Load");
		btnLoad.setBounds(5, 70, 200, 40);
		btnLoad.addActionListener(new ActionListenerSelectImpl(this));
		lblLog = new JTextArea(log.toString());
		lblLog.setBounds(5, 130, 200, 200);
		Border bGreyLine = BorderFactory.createLineBorder(Color.GRAY, 1, true);
		lblLog.setBorder(bGreyLine);
		JScrollPane scrollPane = new JScrollPane(lblLog);
		getContentPane().add(txtPath);
		getContentPane().add(lblPath);
		getContentPane().add(txtOutput);
		getContentPane().add(lblOutput);
		getContentPane().add(btnLoad);
//		getContentPane().add(lblLog);
	}
	
	public void onStart(){
		setVisible(true);
	}
	
	
	
}
