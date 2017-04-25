package com.nest.sagepos.webservice;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class Order {
	final static Logger logger = Logger.getLogger(Order.class);

	public static void main(String args[]) {
		execute();

	}

	public static void execute() {
		// reading paths for folders from property file
		LocationManager locationManager = new LocationManager();
		locationManager.readLocations();
		// server configuration
		ConfigurationManager configurationManager = new ConfigurationManager();
		configurationManager.fetchConfiguration();

		// creating the context
		// Calling context - class CAdxCallContext
		CAdxCallContext cc = new CAdxCallContext(); // Instance of
													// CAdxCallContext
		cc.codeLang = configurationManager.getX3lang(); // Language code
		cc.codeUser = configurationManager.getX3user(); // X3 user
		cc.password = configurationManager.getX3password(); // X3 password
		cc.poolAlias = configurationManager.getX3poolName(); // Pool name
		// Request configuration
		cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8";

		// create the connection with webservice using the url
		CAdxWebServiceXmlCCServiceLocator serviceLocator;

		serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
		// CAdxWebServiceXmlCCService x3Service;; // The web service instance
		serviceLocator.setCAdxWebServiceXmlCCEndpointAddress(
				"http://" + configurationManager.getX3serverName() + "/adxwsvc/services/CAdxWebServiceXmlCC");

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
				String userName = configurationManager.getSqlUser();
				String password = configurationManager.getSqlPassword();
				String url = "jdbc:sqlserver://" + configurationManager.getSqlServerName()
						+ ";databaseName=TestData;instanceName=X3V7;";
				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();
				Statement stmt2 = conn.createStatement();
				Statement stmt3 = conn.createStatement();
				Statement stmt4 = conn.createStatement();
				// System.out.println("connected");
				int i = 1, r1 = 0, r2 = 0, j = 0;
				String SOHNUM = "";
				// getting the header values from db.
				ResultSet rs1 = stmt.executeQuery("select * from dbo.table_E");
				while (rs1.next()) {
					j = j + 1;
					String da = rs1.getDate(7).toString();
					String da1 = da.substring(0, 4);
					String da2 = da.substring(5, 7);
					String da3 = da.substring(8, 10);
					String da4 = da1 + da2 + da3;

					r1 = rs1.getInt(1);
					// StringBuffer xmlBuilder = new StringBuffer(1000);
					// creating the xml from header values.
					xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					xmlBuilder.append("<PARAM><GRP ID=\"SOH0_1\">");
					xmlBuilder.append("<FLD NAME=\"SALFCY\" TYPE=\"Char\">" + rs1.getString(3) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"SOHTYP\" TYPE=\"Char\">" + rs1.getString(4) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"ZSOHTYP\" TYPE=\"Char\"/>");
					xmlBuilder.append("<FLD NAME=\"SOHNUM\" TYPE=\"Char\"/>");
					xmlBuilder.append("<FLD NAME=\"REVNUM\" TYPE=\"Integer\"/>");
					xmlBuilder.append("<FLD NAME=\"CUSORDREF\" TYPE=\"Char\"/>");
					xmlBuilder.append("<FLD NAME=\"ORDDAT\" TYPE=\"Date\">" + da4 + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"BPCORD\" TYPE=\"Char\">" + rs1.getString(6) + "</FLD>");
					xmlBuilder.append("<FLD NAME=\"BPCNAM\" TYPE=\"Char\"/>");
					xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">" + rs1.getString(10) + "</FLD>");
					xmlBuilder.append("</GRP>");
					xmlBuilder.append("<GRP ID=\"SOH1_4\">");
					xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">" + rs1.getString(10) + "</FLD>");
					xmlBuilder.append("</GRP>");
					xmlBuilder.append("<GRP ID=\"SOH2_1\">");
					xmlBuilder.append("<FLD NAME=\"STOFCY\" TYPE=\"Char\">" + rs1.getString(9) + "</FLD>");
					xmlBuilder.append("</GRP>");
					xmlBuilder.append("<TAB DIM=\"1002\" ID=\"SOH4_1\" SIZE=\"1\">");

					// getting the line values from db.
					ResultSet rs = stmt2.executeQuery("select * from dbo.table_L WHERE E_key=" + rs1.getInt(1) + "");

					while (rs.next()) {
						r2 = rs.getInt(1);
						// creating the lines from line values
						xmlBuilder2.append("<LIN NUM=\"" + i + "\">");
						xmlBuilder2.append("<FLD NAME=\"NUMLIG\" TYPE=\"Integer\"/>");
						xmlBuilder2.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\">" + rs.getString(4) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\">" + rs.getString(5) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"DSTOFCY\" TYPE=\"Char\">" + rs1.getString(9) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"SAU\" TYPE=\"Char\">" + rs.getString(6) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"QTY\" TYPE=\"Decimal\">" + rs.getString(7) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"SAUSTUCOE\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"WALLQTY\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"TDLQTY\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">" + rs.getString(8) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"NETPRI\" TYPE=\"Decimal\">" + rs.getString(8) + "</FLD>");
						xmlBuilder2.append(
								"<FLD NAME=\"CPRPRI\" TYPE=\"Decimal\"/><FLD NAME=\"PFM\" TYPE=\"Decimal\"/><FLD NAME=\"CCE1\" TYPE=\"Char\"/><FLD NAME=\"CCE2\" TYPE=\"Char\"/><FLD NAME=\"CCE3\" TYPE=\"Char\"/><FLD NAME=\"CCE4\" TYPE=\"Char\"/><FLD NAME=\"CCE5\" TYPE=\"Char\"/><FLD NAME=\"CCE6\" TYPE=\"Char\"/><FLD NAME=\"CCE7\" TYPE=\"Char\"/><FLD NAME=\"CCE8\" TYPE=\"Char\"/><FLD NAME=\"CCE9\" TYPE=\"Char\"/><FLD NAME=\"CCE10\" TYPE=\"Char\"/><FLD NAME=\"CCE11\" TYPE=\"Char\"/><FLD NAME=\"CCE12\" TYPE=\"Char\"/><FLD NAME=\"CCE13\" TYPE=\"Char\"/><FLD NAME=\"CCE14\" TYPE=\"Char\"/><FLD NAME=\"CCE15\" TYPE=\"Char\"/><FLD NAME=\"CCE16\" TYPE=\"Char\"/><FLD NAME=\"CCE17\" TYPE=\"Char\"/><FLD NAME=\"CCE18\" TYPE=\"Char\"/><FLD NAME=\"CCE19\" TYPE=\"Char\"/><FLD NAME=\"CCE20\" TYPE=\"Char\"/><FLD NAME=\"LINORDNOT\" TYPE=\"Decimal\"/><FLD NAME=\"LINORDATI\" TYPE=\"Decimal\"/><FLD NAME=\"LINPFM\" TYPE=\"Decimal\"/>");
						xmlBuilder2.append("<FLD NAME=\"XPOSITM\" TYPE=\"Char\">" + rs.getString(14) + "</FLD>");
						xmlBuilder2.append("<FLD NAME=\"XENTRYTIM\" TYPE=\"Char\">" + rs.getString(15) + "</FLD>");
						xmlBuilder2.append("</LIN>");
						i = i + 1;
						stmt3.executeUpdate("delete from dbo.table_L where E_key=" + r1 + " and L_key=" + r2 + "");
						xmlBuilder1.append(xmlBuilder2);
					}

					xmlBuilder.append(xmlBuilder1);
					xmlBuilder.append("</TAB>");

					xmlBuilder.append("</PARAM>");
					String xmlInput = xmlBuilder.toString();

					try {
						// calling the save method to create new Order
						result = obj.save(cc, "SORDER", xmlInput);

						// getting the string from the result xml
						final String xmlStr = result.getResultXml();

						// Convert string to Document
						Document doc = convertStringToDocument(xmlStr);

						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						DOMSource source = new DOMSource(doc);
						// create the standard xml from document
						StreamResult result2 = new StreamResult(
								new File(locationManager.getSoXmlOutput() + j + ".xml"));

						// Output to console for testing
						transformer.transform(source, result2);
						logger.info("File saved at" + locationManager.getSoXmlOutput() + j + ".xml");
						xmlBuilder.delete(0, xmlBuilder.length());
						xmlBuilder1.delete(0, xmlBuilder1.length());
						xmlBuilder2.delete(0, xmlBuilder2.length());
						i = 1;
					}

					catch (RemoteException e) {
						logger.error(e);
						ErrorManager.updateErrorFile(
								new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);

					}

					stmt4.executeUpdate("delete from dbo.table_E where E_key=" + r1 + "");
					final String xmlFilePath = locationManager.getSoXmlOutput() + j + ".xml";
					// iterating the xml file for getting the Sales order
					// details.
					try {

						File xmlFile = new File(xmlFilePath);

						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

						Document doc = documentBuilder.parse(xmlFile);

						doc.getDocumentElement().normalize();

						NodeList nodeList2 = doc.getElementsByTagName("FLD");

						for (int itr1 = 0; itr1 < nodeList2.getLength(); itr1++) {

							Node node2 = nodeList2.item(itr1);

							if (node2.getNodeType() == Node.ELEMENT_NODE) {

								Element eElement2 = (Element) node2;

								if (eElement2.hasAttributes()) {

									NamedNodeMap nodeMap = eElement2.getAttributes();

									for (int i1 = 0; i1 < nodeMap.getLength(); i1++) {

										Node node5 = nodeMap.item(i1);

										String fN = node5.getNodeValue();
										String SN = eElement2.getTextContent();

										if (fN.equals("SOHNUM")) {
											SOHNUM = SOHNUM + "\n" + " " + SN;
										}
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error(" :: Exception Occured while xml parsing.. " + e);
						ErrorManager.updateErrorFile(
								new Date().toString() + " :: Exception Occured while xml parsing.. " + e);

					}
				}
				// disposing the splash screen.
				Splash.getSplash().dispose();

				PrintWriter writer11 = new PrintWriter(locationManager.getSoOutputPath());
				writer11.print("");
				writer11.close();
				FileWriter fw = null;
				PrintWriter pw = null;
				fw = new FileWriter(locationManager.getSoOutputPath(), true);
				pw = new PrintWriter(fw);
				pw.write(j + " No of Sales Order created \n");
				pw.write(j + "  \n");
				pw.write(SOHNUM);
				pw.close();
				fw.close();
				if (j == 0) {
					logger.error("Input File Error..");
					ErrorManager.updateErrorFile(
							new Date().toString() + " :: Input File Error... " + locationManager.getSoPosOutputPath());
					JOptionPane.showMessageDialog(null, " Input File Error.. " + System.lineSeparator()
							+ " Please check  " + locationManager.getSoPosOutputPath(), "Error",
							JOptionPane.ERROR_MESSAGE);

				} else {
					logger.info(" Orders Created Successfully...");
					JOptionPane.showMessageDialog(null,
							" Orders Created Successfully in X3... " + SOHNUM + System.lineSeparator()
									+ " Please also check " + locationManager.getSoOutputPath()
									+ " for more details... ",
							"Success", JOptionPane.INFORMATION_MESSAGE);

				}
			} catch (Exception e) {
				logger.error(e);
				ErrorManager.updateErrorFile(new Date().toString() + " :: Cannot Connect to Virtual Machine.. " + e);

			}

		} catch (Exception e) {
			logger.error(e);
			ErrorManager.updateErrorFile(new Date().toString() + " :: Cannot Connect to Webservice.. " + e);

		}

	}

	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			ErrorManager.updateErrorFile(new Date().toString() + " :: Error in Converting String to doc.. " + e);
		}

		return null;
	}
}
