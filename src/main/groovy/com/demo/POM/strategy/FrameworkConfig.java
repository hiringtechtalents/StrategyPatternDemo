package com.demo.POM.strategy;

import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

import java.io.File;
import java.net.MalformedURLException;


public class FrameworkConfig implements Cloneable {
	
	private static final FrameworkConfig instance = new FrameworkConfig();
	private static ConfigObject config;
		
	private FrameworkConfig() {
		try {
			config = new ConfigSlurper().
					parse(new File("src/test/resources/Config.groovy")
					.toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("unable to locate Config file " + instance.toString());
			e.printStackTrace();
		}
	}
	
	public static FrameworkConfig getInstance() {
		return instance;
	}
	
	public ConfigObject getConfig() {
		return config;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
