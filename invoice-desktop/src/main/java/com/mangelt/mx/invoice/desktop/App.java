package com.mangelt.mx.invoice.desktop;

import com.mangelt.mx.invoice.desktop.bean.Config;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Config config = new Config();
    	config.setTitle("Test");
    	config.setPositionX(10);
    	config.setPositionY(10);
    	config.setWidth(400);
    	config.setHigh(400);
    	
        JVentana name = new JVentana(config);
    }
}
