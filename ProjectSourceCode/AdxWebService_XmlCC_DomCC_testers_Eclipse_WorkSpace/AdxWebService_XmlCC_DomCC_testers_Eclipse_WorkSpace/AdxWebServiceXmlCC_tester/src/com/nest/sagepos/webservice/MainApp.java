package com.nest.sagepos.webservice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class MainApp {
	// for logging
	final static Logger logger = Logger.getLogger(MainApp.class);

	public static void main(String[] args) {

		execute();
	}

	@SuppressWarnings({ "static-access", "unused" })
	public static void execute() {
		ErrorManager.updateErrorFile(new Date().toString() + "  :: Running.... ");

		// for getting the splach screen
		Splash.getSplash();
		// reading paths for folders from property file
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();

		// reads X3 SERVER configuration file.
		ConfigurationManager configurationManager = new ConfigurationManager();
		configurationManager.fetchConfiguration();

		// method for getting the POS output text file from POS OUTPUT folder.
		boolean isInnputOk = getPosOutputFile();
		if (!isInnputOk) {
			logger.error("error in input file....");
			ErrorManager.updateErrorFile(new Date().toString() + "::  Input File "
					+ locationManager.getSoPosOutputPath() + "is not correct.. ");
			JOptionPane.showMessageDialog(null, " Error While Reading Input File.. " + System.lineSeparator()
					+ " Please check " + locationManager.getSoPosOutputPath(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} else {
			int j = 0;
			String j00, j01, j02, j03, j04, j05, j06, j07, j08, j09, j010, j011;
			try {
				// ******To Clear Existing Text Files******//
				PrintWriter writer11 = new PrintWriter(locationManager.getSoHeaderTempPath());
				writer11.print("");
				writer11.close();
				PrintWriter writer21 = new PrintWriter(locationManager.getSoLineTempPath());
				writer21.print("");
				writer21.close();
			} catch (Exception e) {
				logger.error("Io Exception.. " + e);
				ErrorManager.updateErrorFile(new Date().toString() + " :: Io Exception.. " + e.toString());
			}
			BufferedReader in = null;
			String read;
			int linenum;
			try {
				// READING THE INPUT FILE
				in = new BufferedReader(new FileReader(locationManager.getSoInputPath()));
			} catch (Exception e) {
				logger.error(" Error in input file.. " + e);
				ErrorManager.updateErrorFile(new Date().toString() + " :: Io Exception.. " + e);
			}
			try {
				// iterating through the lines
				for (linenum = 0; linenum < 100; linenum++) {
					read = in.readLine();
					if (read == null) {
					} else {
						String[] splited = read.split("\\;+");
						for (int i = 0; i < splited.length; i++) {

							String ja = splited[0];
							// checking for Header
							if (ja.equals("E")) {
								String j0, j1, j2, j3, j4, j5, j6, j41;
								j0 = splited[0];
								j1 = splited[1];
								j2 = splited[2];
								j3 = splited[4];
								j41 = splited[3];
								j4 = splited[5];
								j5 = splited[6];
								j6 = splited[7];

								String d2 = j4.substring(0, 4);
								String d3 = j4.substring(4, 6);
								String d4 = j4.substring(6, 8);
								String d5 = d4 + "/" + d3 + "/" + d2;

								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								String dateInString = d5;

								try {

									Date date = formatter.parse(dateInString);
									// creating the connection with database
									Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
									String userName = configurationManager.getSqlUser();
									String password = configurationManager.getSqlPassword();
									String url = "jdbc:sqlserver://" + configurationManager.getSqlServerName()
											+ ";databaseName=TestData;instanceName=X3V7;";
									Connection conn = DriverManager.getConnection(url, userName, password);
									Statement stmt = conn.createStatement();
									;
									// table_E is the table for storing the
									// Header
									// values With E_KEY is the Primary Key.
									stmt.executeUpdate(
											"insert into dbo.table_E(FIRSTVAL,SALFCY,SOHTYP,BPCORD,ORDDAT,STOFCY,CUR) values('"
													+ j0 + "','" + j1 + "','" + j2 + "','" + j3 + "','" + j4 + "','"
													+ j5 + "','" + j6 + "')");
								} catch (Exception e) {
									logger.error(" Error While accessing VM.. " + e);
									ErrorManager.updateErrorFile(
											new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);
									JOptionPane.showMessageDialog(null,
											" Error While accessing VM.. " + System.lineSeparator()
													+ " Please start/Restart the Virtual Machine.. ",
											"Error", JOptionPane.ERROR_MESSAGE);
									System.exit(0);
								}
								// keeping a copy in local machine to avoid
								// duplication of the same.
								FileWriter writer = new FileWriter(locationManager.getSoHeaderTempPath(), true);
								writer.write("\n");
								for (String str : splited) {
									writer.write(str + " ");
								}
								writer.close();
								FileInputStream fstream = new FileInputStream(locationManager.getSoLineTempPath());
								DataInputStream in1 = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(in1));
								String strLine;
								LinkedHashSet<String> set = new LinkedHashSet<String>();
								while ((strLine = br.readLine()) != null) {
									set.add(strLine);
								}
								FileWriter writer1 = new FileWriter(locationManager.getSoHeaderTempPath());
								writer1.write("\n");
								for (String str : set) {
									writer1.write(str + "\n");
								}
								writer1.close();
								in1.close();
							}
							try {
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
								String userName = configurationManager.getSqlUser();
								String password = configurationManager.getSqlPassword();
								String url = "jdbc:sqlserver://" + configurationManager.getSqlServerName()
										+ ";databaseName=TestData;instanceName=X3V7;";
								Connection conn = DriverManager.getConnection(url, userName, password);
								Statement stmt5 = conn.createStatement();
								logger.info("connected");
								// delete the previous entries
								stmt5.executeUpdate(
										"Delete FROM dbo.table_E WHERE E_key NOT IN (SELECT MIN(E_key) FROM dbo.table_E GROUP BY FIRSTVAL,SALFCY,SOHTYP,BPCORD,ORDDAT,STOFCY,CUR)");
								logger.info("DELETED");
							} catch (Exception e) {
								logger.error(e);
								ErrorManager.updateErrorFile(
										new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);
								JOptionPane.showMessageDialog(null,
										" Error While accessing VM.. " + System.lineSeparator()
												+ " Please start/Restart the VM.. ",
										"Error", JOptionPane.ERROR_MESSAGE);
								System.exit(0);
							}
							// for Lines
							if (ja.equals("L")) {
								j00 = splited[0];
								j01 = splited[1];
								j02 = splited[2];
								j03 = splited[3];
								j04 = splited[4];
								j05 = splited[5];
								j06 = splited[6];
								j07 = splited[7];
								j08 = splited[8];
								j09 = splited[9];
								j010 = splited[10];
								j011 = splited[11];

								FileWriter writer = new FileWriter(locationManager.getSoLineTempPath(), true);
								writer.write("\n");
								for (String str : splited) {
									writer.write(str + " ");
									j = j + 1;
								}
								writer.close();
								FileInputStream fstream = new FileInputStream(locationManager.getSoLineTempPath());
								DataInputStream in1 = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(in1));
								String strLine;
								LinkedHashSet<String> set = new LinkedHashSet<String>();
								// Read File Line By Line
								while ((strLine = br.readLine()) != null) {
									// Print the content on the console
									set.add(strLine);
								}
								FileWriter writer1 = new FileWriter(locationManager.getSoLineTempPath());
								writer1.write("\n");
								for (String str : set) {
									writer1.write(str + "\n");
								}
								writer1.close();
								// Close the input stream
								in1.close();
								try {
									Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
									String userName = configurationManager.getSqlUser();
									String password = configurationManager.getSqlPassword();
									String url = "jdbc:sqlserver://" + configurationManager.getSqlServerName()
											+ ";databaseName=TestData;instanceName=X3V7;";
									Connection conn = DriverManager.getConnection(url, userName, password);
									Statement stmt = conn.createStatement();
									Statement stmt2 = conn.createStatement();
									ResultSet rs = stmt.executeQuery("select max(E_key) from dbo.table_E");
									while (rs.next()) {
										int id = rs.getInt(1);
										stmt2.executeUpdate(
												"insert into dbo.table_L(FIRSTVAL,XPOSITM,ITMREF,ITMDES,SAU,QTY,GROPRI,DISCRGVAL1,DISCRGVAL2,DISCRGVAL3,XENTRYTIM,FMI,E_key) values('"
														+ j00 + "','" + j01 + "','" + j02 + "','" + j03 + "','" + j04
														+ "','" + j05 + "','" + j06 + "','" + j07 + "','" + j08 + "','"
														+ j09 + "','" + j010 + "','" + j011 + "'," + id + ")");
									}
								} catch (Exception e) {
									logger.error(" Error While accessing VM.. " + e);
									ErrorManager.updateErrorFile(
											new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);
									JOptionPane.showMessageDialog(null,
											" Error While accessing VM.. " + System.lineSeparator()
													+ " Please start/Restart the VM.. ",
											"Error", JOptionPane.ERROR_MESSAGE);
									System.exit(0);
								}

							}
						}

					}
				}
			} catch (IOException e) {
				logger.error(" Error While accessing VM.. " + e);
				ErrorManager.updateErrorFile(new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);

			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// e.printStackTrace();
					ErrorManager.updateErrorFile(new Date().toString() + " :: IOException.. " + e);

				}
			}

			try {
				logger.info("connecting to database..");
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				String userName = configurationManager.getSqlUser();
				String password = configurationManager.getSqlPassword();
				String url = "jdbc:sqlserver://" + configurationManager.getSqlServerName()
						+ ";databaseName=TestData;instanceName=X3V7;";
				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt5 = conn.createStatement();
				stmt5.executeUpdate(
						"Delete FROM dbo.table_L WHERE L_key NOT IN (SELECT MIN(L_key) FROM dbo.table_L GROUP BY FIRSTVAL, XPOSITM,ITMREF,ITMDES,SAU,QTY,GROPRI,DISCRGVAL1,DISCRGVAL2,DISCRGVAL3,L1,L2,XENTRYTIM,FMI,E_key)");
				deleteFile();

			} catch (Exception e) {
				logger.error(" Error While accessing VM.. " + e);
				ErrorManager.updateErrorFile(new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);
				JOptionPane.showMessageDialog(null,
						" Error While accessing VM.. " + System.lineSeparator() + " Please start/Restart the VM.. ",
						"Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			// ****************************************Calling Xml
			// Creation******************************************************//
			// for Sales order
			Order obj = new Order();
			obj.execute();
		}
	}

	// to delete input file from sagews folder
	private static boolean deleteFile() {
		// reading paths for folders from property file
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();
		try {
			// Specify the file name and path
			File file = new File(locationManager.getSoInputPath());
			/*
			 * the delete() method returns true if the file is deleted
			 * successfully else it returns false
			 */
			// file.deleteOnExit();
			System.out.println(file.exists());
			if (file.delete()) {
				logger.info(file.getName() + " is deleted!");
				return true;
			} else {
				logger.error(file.getName() + " Delete failed: File didn't delete");
				ErrorManager.updateErrorFile(
						new Date().toString() + " :: " + file.getName() + " Delete failed: File didn't delete.. ");

				return false;
			}
		} catch (Exception e) {
			logger.error("Exception occurred" + e);
			ErrorManager.updateErrorFile(new Date().toString() + "Exception occurred" + e);

			return false;

		}
	}

	// method to copy output file to input file
	private static boolean getPosOutputFile() {
		// reading paths for folders from property file
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();

		FileInputStream instream = null;
		FileOutputStream outstream = null;
		logger.info("file is copying...");

		try {
			File infile = new File(locationManager.getSoPosOutputPath());
			File outfile = new File(locationManager.getSoInputPath());
			instream = new FileInputStream(infile);
			outstream = new FileOutputStream(outfile);
			byte[] buffer = new byte[1024];
			int length;
			/*
			 * copying the contents from input stream to output stream using
			 * read and write methods
			 */
			while ((length = instream.read(buffer)) > 0) {
				outstream.write(buffer, 0, length);
			}
			// Closing the input/output file streams
			instream.close();
			outstream.close();
			logger.info("File copied successfully!!");
			return true;

		} catch (IOException ioe) {
			logger.error("Error while copying POS Output file.. " + ioe);
			ErrorManager.updateErrorFile(new Date().toString() + " :: Error while copying POS Output file.. " + ioe);

			return false;
		}

	}
}