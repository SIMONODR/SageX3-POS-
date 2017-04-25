package com.webservice.adonix.salesorder;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//**************************************************************************//
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import org.xml.sax.InputSource;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxParamKeyValue;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SMultipleRead {
	
		public static void main(String args[])
		
		{
			
            int i=0;
			CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
			cc.codeLang = "ENG"; // Language code 
			cc.codeUser = "ADMIN"; // X3 user 
			cc.password = ""; // X3 password 
			cc.poolAlias = "SCONN"; // Pool name 
			cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
            CAdxWebServiceXmlCCServiceLocator serviceLocator;
			serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
            serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.251:28880/adxwsvc/services/CAdxWebServiceXmlCC");
			try{
				PrintWriter writer11 = new PrintWriter("C:\\WEBSERVICE\\READMULTIPLE\\responseSOHNUM.txt");
				writer11.print("");
				writer11.close();
				CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
				CAdxResultXml result = new CAdxResultXml();
	     		CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; 
	     		FileInputStream fstream = new FileInputStream("C:\\WEBSERVICE\\READMULTIPLE\\check.txt");
	     		File file = new File("C:\\WEBSERVICE\\READMULTIPLE\\responseSOHNUM.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null) {
	     		 String FLD_NAME="SOHNUM";
		         String FLD_VALUE=strLine;
				objKey[0] = new CAdxParamKeyValue(FLD_NAME, FLD_VALUE); // Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
				try {
					System.out.println("Waitng for the response...");
					result = obj.read(cc, "SORDER", objKey);
					final String xmlStr =result.getResultXml();
					i=i+1;
					if(xmlStr==null)
					{
							System.out.println("No " +FLD_VALUE +" sales order exist in the list");
							System.out.println("C:\\WEBSERVICE\\READ\\ErrorLog.txt contains the details");
								FileWriter fw1 = null;
								PrintWriter pw1 = null;
								fw1 = new FileWriter("C:\\WEBSERVICE\\READMULTIPLE\\ErrorLog.txt");
							    pw1 = new PrintWriter(fw1);
							    pw1.write("No " +FLD_VALUE +" sales order exist in the list");
							    pw1.close();
						        fw1.close();
					}
					else
					{
					
					
								FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(FLD_VALUE);
								bw.write("\n");
								bw.close();
								
								System.out.println(result.getResultXml());
								System.out.println(FLD_VALUE);
								Document doc = convertStringToDocument(xmlStr);
								 
								TransformerFactory transformerFactory = TransformerFactory.newInstance();
								Transformer transformer = transformerFactory.newTransformer();
								DOMSource source = new DOMSource(doc);
								StreamResult result2 = new StreamResult(new File("C:\\WEBSERVICE\\READMULTIPLE\\MULTY\\response"+i+".xml"));
								transformer.transform(source, result2);
					
					}
					
				} catch (RemoteException e)
					{
						e.printStackTrace();
					}
				}
					System.out.println("No of order read "+i);
			}
				catch (Exception e)
				{
				   e.printStackTrace();
				}
				
			
		}

		private static Document convertStringToDocument(String xmlStr) {
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