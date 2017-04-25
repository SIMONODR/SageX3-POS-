package com.nest.sagepos.webservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class ConfigurationManager {
	final static Logger logger = Logger.getLogger(MainApp.class);
	private String X3poolName = "", X3serverName = "", X3lang = "", X3user = "", X3password = "", SqlServerName = "",
			SqlUser = "", SqlPassword = "";

	@SuppressWarnings("unused")
	public void fetchConfiguration() {
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();

		// reads X3 SERVER configuration file.
		BufferedReader br = null;
		FileReader fr = null;
		String line = " ";
		int lineNo;
		/*
		 * String X3poolName = "", X3serverName = "", X3lang = "", X3user = "",
		 * X3password = "", SqlServerName = "", SqlUser = "", SqlPassword = "";
		 */
		try {
			fr = new FileReader(locationManager.getConfigPath());
			br = new BufferedReader(fr);
			for (lineNo = 1; lineNo <= 8; lineNo++) {
				if (lineNo == 1) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("X3 server name=")) {
							String c = s;
						} else {
							X3serverName = s;
						}
					}

				} else if (lineNo == 2) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("X3 poolname=")) {
							String a = s;
						} else {
							X3poolName = s;// Pool name
						}
					}
				}

				else if (lineNo == 3) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("X3 Lang=")) {
							String e = s;
						} else {
							X3lang = s;// Language code
						}
					}
				} else if (lineNo == 4) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("X3 User=")) {
							String g = s;
						} else {
							X3user = s;// X3 user
						}
					}
				} else if (lineNo == 5) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("X3 password=")) {
							String i = s;
						} else {
							X3password = s; // X3 password
						}
					}
				} else if (lineNo == 6) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Sql Server name=")) {
							String i = s;
						} else {
							SqlServerName = s; // X3 password
						}
					}
				} else if (lineNo == 7) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Sql User=")) {
							String i = s;
						} else {
							SqlUser = s; // X3 password
						}
					}
				} else if (lineNo == 8) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Sql Password=")) {
							String i = s;
						} else {
							SqlPassword = s; // X3 password
						}
					}
				}

				else {
					br.readLine();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			ErrorManager.updateErrorFile(new Date().toString() + " :: Error while reading the configuration file " + e);
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				logger.error("finally " + e);
				ErrorManager.updateErrorFile(
						new Date().toString() + " :: in finally.. Error while reading the configuration file " + e);
			}
		}
	}

	public String getX3poolName() {
		return X3poolName;
	}

	public void setX3poolName(String x3poolName) {
		X3poolName = x3poolName;
	}

	public String getX3serverName() {
		return X3serverName;
	}

	public void setX3serverName(String x3serverName) {
		X3serverName = x3serverName;
	}

	public String getX3lang() {
		return X3lang;
	}

	public void setX3lang(String x3lang) {
		X3lang = x3lang;
	}

	public String getX3user() {
		return X3user;
	}

	public void setX3user(String x3user) {
		X3user = x3user;
	}

	public String getX3password() {
		return X3password;
	}

	public void setX3password(String x3password) {
		X3password = x3password;
	}

	public String getSqlServerName() {
		return SqlServerName;
	}

	public void setSqlServerName(String sqlServerName) {
		SqlServerName = sqlServerName;
	}

	public String getSqlUser() {
		return SqlUser;
	}

	public void setSqlUser(String sqlUser) {
		SqlUser = sqlUser;
	}

	public String getSqlPassword() {
		return SqlPassword;
	}

	public void setSqlPassword(String sqlPassword) {
		SqlPassword = sqlPassword;
	}
}