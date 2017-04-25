package com.nest.sagepos.webservice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class LocationManager {

	private String configPath;
	private String soPosOutputPath;
	private String soInputPath;
	private String soHeaderTempPath;
	private String soLineTempPath;
	private String soOutputPath;
	private String soErrorPath;
	private String soXmlOutput;

	public String getSoXmlOutput() {
		return soXmlOutput;
	}

	public void setSoXmlOutput(String soXmlOutput) {
		this.soXmlOutput = soXmlOutput;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	public String getSoPosOutputPath() {
		return soPosOutputPath;
	}

	public void setSoPosOutputPath(String soPosOutputPath) {
		this.soPosOutputPath = soPosOutputPath;
	}

	public String getSoInputPath() {
		return soInputPath;
	}

	public void setSoInputPath(String soInputPath) {
		this.soInputPath = soInputPath;
	}

	public String getSoHeaderTempPath() {
		return soHeaderTempPath;
	}

	public void setSoHeaderTempPath(String soHeaderTempPath) {
		this.soHeaderTempPath = soHeaderTempPath;
	}

	public String getSoLineTempPath() {
		return soLineTempPath;
	}

	public void setSoLineTempPath(String soLineTempPath) {
		this.soLineTempPath = soLineTempPath;
	}

	public String getSoOutputPath() {
		return soOutputPath;
	}

	public void setSoOutputPath(String soOutputPath) {
		this.soOutputPath = soOutputPath;
	}

	public String getSoErrorPath() {
		return soErrorPath;
	}

	public void setSoErrorPath(String soErrorPath) {
		this.soErrorPath = soErrorPath;
	}

	public void readLocations() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("C:\\SageWS\\CONFIG\\path.properties");

			// load a properties file
			prop.load(input);

			// get the property value
			configPath = prop.getProperty("path.config");
			soPosOutputPath = prop.getProperty("path.posOutput");
			soInputPath = prop.getProperty("path.soInput");
			soHeaderTempPath = prop.getProperty("path.soHeaderTemp");
			soLineTempPath = prop.getProperty("path.soLineTemp");
			soOutputPath = prop.getProperty("path.soOutput");
			soErrorPath = prop.getProperty("path.soError");
			soXmlOutput = prop.getProperty("path.soXmlOutput");

		} catch (IOException ex) {
			ErrorManager.updateErrorFile(
					new Date().toString() + " :: Error while reading the location configuration file " + ex);

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					ErrorManager.updateErrorFile(new Date().toString()
							+ " ::in finally..  Error while reading the location configuration file " + e);

				}
			}
		}
	}

}
