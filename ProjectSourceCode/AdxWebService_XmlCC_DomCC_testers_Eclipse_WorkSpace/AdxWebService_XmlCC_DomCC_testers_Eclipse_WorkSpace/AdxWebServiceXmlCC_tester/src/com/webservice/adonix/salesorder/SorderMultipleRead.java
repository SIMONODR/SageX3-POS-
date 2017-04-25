package com.webservice.adonix.salesorder;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//**************************************************************************//
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxParamKeyValue;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SorderMultipleRead {
	
		public static void main(String args[])
		{
//			 Calling context - class CAdxCallContext 
			CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
			cc.codeLang = "ENG"; // Language code 
			cc.codeUser = "ADMIN"; // X3 user 
			cc.password = ""; // X3 password 
			cc.poolAlias = "SCONN"; // Pool name 
			cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
		     
			//**********************************************Assign field name and data which u want to retrieve from Web service*********//
			try{
				FileInputStream fstream = new FileInputStream("C:\\check.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null) {
			
					 String FLD_NAME="SOHNUM";
			         String FLD_VALUE=strLine;
			
			CAdxWebServiceXmlCCServiceLocator serviceLocator;
			
			serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
			//CAdxWebServiceXmlCCService x3Service;; // The web service instance
			serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.251:28880/adxwsvc/services/CAdxWebServiceXmlCC");
					
			
			try{
				
			//Web service instance
			//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();
			CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
           //XmlResult instance
			CAdxResultXml result = new CAdxResultXml();
	
		     
             //Record key: An array of CAdxParamKeyValue 
			CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
			objKey[0] = new CAdxParamKeyValue(FLD_NAME, strLine); 
			System.out.println(objKey[0]);
			System.out.println("Requested By "+FLD_NAME);
			System.out.println("Requested SOHNUM IS "+strLine);
			 FileWriter fw = null;
			  PrintWriter pw = null;
			    fw = new FileWriter("C:\\request.txt");
		        pw = new PrintWriter(fw);
		        pw.write("Requested Sales order number to the web service is..\n");
		        pw.write(strLine);
		        pw.close();
		        fw.close();
		        
			// Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
			try {
				System.out.println("Waitng for the response...");
				result = obj.read(cc, "SORDER", objKey);
				System.out.println(result.getResultXml());
				final String xmlStr =result.getResultXml();
				 Document doc = convertStringToDocument(xmlStr);
				 
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result2 = new StreamResult(new File("C:\\response.xml"));
				transformer.transform(source, result2);
				System.out.println("Response XML saved at C:\\");
				
				final String xmlFilePath = "C:\\response.xml";
				try {
					
					File xmlFile = new File(xmlFilePath);

					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

					Document doc1 = documentBuilder.parse(xmlFile);

					doc1.getDocumentElement().normalize();

					NodeList nodeList2 = doc1.getElementsByTagName("FLD");
							
					for (int itr1 = 0; itr1 < nodeList2.getLength(); itr1++) {

								Node node2 = nodeList2.item(itr1);

								

								if (node2.getNodeType() == Node.ELEMENT_NODE) {

									Element eElement2 = (Element) node2;
								
									if (eElement2.hasAttributes()) {

										NamedNodeMap nodeMap = eElement2.getAttributes();

										for (int i1 = 0; i1 < nodeMap.getLength(); i1++) {

											Node node5 = nodeMap.item(i1);
										
										
											String fN=node5.getNodeValue();
											String SN=eElement2.getTextContent();
											if (fN.equals("SOHNUM"))
											{
											
											System.out.println(SN);
											 FileWriter fw1 = null;
											  PrintWriter pw1 = null;
											    fw1 = new FileWriter("C:\\response.txt");
										        pw1 = new PrintWriter(fw1);
										        pw1.write("Response \nSales order number from the web service number is..\n");
										        pw1.write(strLine);
										        pw1.close();
										        fw1.close();
											
											}
										}	
									}
							
								}
							}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//System.out.println(xmlStr);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				}
			}
			catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
				}
		}

		private static Document convertStringToDocument(String xmlStr) {
			
			// TODO Auto-generated method stub
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try 
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
			return null;
		}
}