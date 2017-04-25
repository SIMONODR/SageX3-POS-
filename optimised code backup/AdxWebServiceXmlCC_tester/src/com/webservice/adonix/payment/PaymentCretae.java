package com.webservice.adonix.payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.w3c.dom.Document;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class PaymentCretae {
	public static void main(String args[]) {
		execute();
	}

	public static void execute() {
		int j = 0, b = 0, b1 = 0, b2 = 0, len = 0;
		String j1 = "", j2 = "", CNUM = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48
		int i1 = 0;
		String line = " ";
		int lineNo;
		String X3poolName = "", X3serverName = "", X3lang = "", X3user = "", X3password = "", SqlServerName = "",
				SqlUser = "", SqlPassword = "", CHQ = "";
		try {
			FileReader fr = new FileReader("C:\\WEBSERVICE\\config.txt");
			BufferedReader br = new BufferedReader(fr);
			for (lineNo = 1; lineNo <= 12; lineNo++) {
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
				} else if (lineNo == 12) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("CHQNUM=")) {
							String i = s;
						} else {
							CHQ = s; // X3 password
						}
					}
				}

				else {
					br.readLine();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		CAdxCallContext cc = new CAdxCallContext(); // Instance of
													// CAdxCallContext
		cc.codeLang = "ENG"; // Language code
		cc.codeUser = "ADMIN"; // X3 user
		cc.password = ""; // X3 password
		cc.poolAlias = "SCONN"; // Pool name
		cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request
																																		// configuration
																																		// string

		CAdxWebServiceXmlCCServiceLocator serviceLocator;

		serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
		// CAdxWebServiceXmlCCService x3Service;; // The web service instance
		serviceLocator
				.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.251:28880/adxwsvc/services/CAdxWebServiceXmlCC");

		try {
			// Web service instance
			// CAdxWebServiceXmlCCService wsvc = new
			// CAdxWebServiceXmlCCServiceLocator();
			CAdxWebServiceXmlCCSoapBindingStub obj = new CAdxWebServiceXmlCCSoapBindingStub();
			// XmlResult instance
			CAdxResultXml result = new CAdxResultXml();
			StringBuffer xmlBuilder = new StringBuffer(1000);
			StringBuffer xmlBuilder1 = new StringBuffer(1000);
			StringBuffer xmlBuilder2 = new StringBuffer(10000);
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				String userName = SqlUser;
				String password = SqlPassword;
				String url = "jdbc:sqlserver://" + SqlServerName + ";databaseName=x3v6;instanceName=X3V7;";
				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt5 = conn.createStatement();
				System.out.println("connected");
				j1 = CHQ.substring(6, 11);
				len = j1.length();
				j2 = CHQ.substring(0, 6);
				b = Integer.parseInt(j1.trim());
				ResultSet rs = stmt5.executeQuery(
						"Select * from HBPLOT.SINVOICE as SI Left join HBPLOT.BPCUSTOMER as BPC on SI.BPR_0=BPC.BPCNUM_0 where BPC.PTE_0='CHQ30EOM' and SI.STA_0='3'");
				while (rs.next()) {
					b = b + 1;
					i1 = i1 + 1;
					b1 = Integer.toString(i1).length();
					CNUM = j2 + ((j1 + Integer.toString(b)).substring(Integer.toString(b).length()));
					System.out.println(rs.getString(5));
					System.out.println(CNUM);
					xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					xmlBuilder.append("<PARAM>");
					xmlBuilder.append("<GRP ID=\"PAY0_1\" >");
					xmlBuilder.append("<FLD NAME=\"NUM\" TYPE=\"Char\" > </FLD>");
					xmlBuilder.append("<FLD NAME=\"STA2\" TYPE=\"Char\" >Entered</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"PAY1_1\" >");
					xmlBuilder.append("<FLD NAME=\"FCY\" TYPE=\"Char\" >" + rs.getString(8) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"ZFCY\" TYPE=\"Char\" >Corn</FLD>");
					xmlBuilder.append("<FLD NAME=\"BPR\" TYPE=\"Char\" >" + rs.getString(5) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"BPRSAC\" TYPE=\"Char\" > " + rs.getString(6) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"ACC\" TYPE=\"Char\" >12130000</FLD>");
					xmlBuilder.append("<FLD NAM<FLD NAME=\"BPAINV\" TYPE=\"Char\" >" + rs.getString(175) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"BPRNAM\" TYPE=\"Char\" > " + rs.getString(176) + "</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"PAY1_2\">");
					xmlBuilder.append("<FLD NAME=\"ACCDAT\" TYPE=\"Date\" >" + rs.getString(11) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"REF\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"BAN\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"ZBAN\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\" >" + rs.getString(27) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"ZCUR\" TYPE=\"Char\" >Riyal</FLD>");
					xmlBuilder.append("<FLD NAME=\"AMTCUR\" TYPE=\"Decimal\">" + rs.getString(88) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"CURAMTCUR\" TYPE=\"Char\"> " + rs.getString(27) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"AMTBAN\" TYPE=\"Decimal\">0</FLD>");
					xmlBuilder.append("<FLD NAME=\"CURAMTBAN\" TYPE=\"Char\"></FLD>");
					xmlBuilder.append("<FLD NAME=\"CHQNUM\" TYPE=\"Char\" >" + CNUM + "</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"PAY1_6\" >");
					xmlBuilder.append("<FLD NAME=\"TOTIMPUT\" TYPE=\"Decimal\" >" + rs.getString(88) + "</FLD>");
					xmlBuilder.append(" <FLD NAME=\"DEVISE\" TYPE=\"Char\" > " + rs.getString(27) + "</FLD>");
					xmlBuilder.append(" <FLD NAME=\"RSTIMPUT\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"TOTBQE\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"DEVBQE\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<TAB DIM=\"50\" ID=\"PAY1_4\" SIZE=\"1\" >");
					xmlBuilder.append("<LIN NUM=\"1\">");
					xmlBuilder.append("<FLD NAME=\"DENCOD\" TYPE=\"Char\" >ZAREC</FLD>");
					xmlBuilder.append("<FLD NAME=\"VCRTYP\" TYPE=\"Char\" >" + rs.getString(9) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"VCRNUM\" TYPE=\"Char\" >" + rs.getString(3) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"FCYLIN\" TYPE=\"Char\" >" + rs.getString(8) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"CURLIN\" TYPE=\"Char\" > " + rs.getString(27) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"AMTLIN\" TYPE=\"Decimal\" > " + rs.getString(88) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"AMTLIN2\" TYPE=\"Decimal\" > " + rs.getString(88) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"VATLIN\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"UOM\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"QTYLIN\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"DSP\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE1\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE2\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE3\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE4\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE5\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE6\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE7\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE8\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE9\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE10\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE11\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE12\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE13\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE14\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE15\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE16\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE17\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE18\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE19\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"CCE20\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("</LIN>");
					xmlBuilder.append("</TAB>");

					xmlBuilder.append("<GRP ID=\"PAY4_2\">");
					xmlBuilder.append("<FLD NAME=\"NBRPAY\" TYPE=\"Integer\" >0</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"PAY4_3\">");
					xmlBuilder.append("<FLD NAME=\"TOTLOC\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"CPYCUR\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"VTL_1\">");
					xmlBuilder.append("<FLD NAME=\"DSP\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"ZDSP\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"VTL_3\">");
					xmlBuilder.append("<FLD NAME=\"TOTAL\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"TOTREP\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"VTL_4\">");
					xmlBuilder.append("<FLD NAME=\"QTOTAL\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("<FLD NAME=\"UOM\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"QTOTREP\" TYPE=\"Decimal\" >0</FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("<GRP ID=\"ADXTEC\">");
					xmlBuilder.append("<FLD NAME=\"WW_MODSTAMP\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("<FLD NAME=\"WW_MODUSER\" TYPE=\"Char\" ></FLD>");
					xmlBuilder.append("</GRP>");

					xmlBuilder.append("</PARAM>");
					String xmlInput = xmlBuilder.toString();

					try {
						result = obj.save(cc, "PAYMENT", xmlInput);
						final String xmlStr = result.getResultXml();
						System.out.println(xmlStr);
					}

					catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				System.out.println(i1 + " No of Payment created");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println(i1+" Recordes return");
			// System.out.println(j1);
			// System.out.println(j2);
			// System.out.println(Integer.toString(len));
			// System.out.println(b);
			// System.out.println(CNUM);
			// System.out.println(CHQ);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
