package com.nest.sagepos.webservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class ErrorManager {
	final static Logger logger = Logger.getLogger(Order.class);

	static void updateErrorFile(String message) {
		// reading paths for folders from property file
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();
		try {
			File file = new File(locationManager.getSoErrorPath());
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(message);
			bw.close();
		} catch (IOException e) {
			logger.error("error while writing error file.." + e);
		}

	}
}
