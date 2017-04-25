package com.webservice.adonix.salesorder;

import java.io.File;
import java.io.FileWriter;
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

public class ModifySalesOrder {
	public static void main(String args[])
	{
//		 Calling context - class CAdxCallContext 
		CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
		cc.codeLang = "ENG"; // Language code 
		cc.codeUser = "ADMIN"; // X3 user 
		cc.password = ""; // X3 password 
		cc.poolAlias = "SCONN"; // Pool name 
		cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
	     
		CAdxWebServiceXmlCCServiceLocator serviceLocator;
		
		serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
		//CAdxWebServiceXmlCCService x3Service;; // The web service instance
		serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.251:28880/adxwsvc/services/CAdxWebServiceXmlCC");
				
		
		try{

		//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();		//	 Web service instance
		CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
		CAdxResultXml result = new CAdxResultXml();//		 XmlResult instance
        
		 String FLD_NAME="SOHNUM";
	     String FLD_VALUE="OUT-DMM01-15-000046";
	     
//  	Record key: An array of CAdxParamKeyValue 
		CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
		objKey[0] = new CAdxParamKeyValue(); 
		objKey[0].key=FLD_NAME;
		objKey[0].value=FLD_VALUE;
		
		String xmlHeader = new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		StringBuffer xmlBuilder = new StringBuffer(1000);
	    xmlBuilder.append("<PARAM>");
		xmlBuilder.append("<TAB DIM=\"1002\" ID=\"SOH4_1\" SIZE=\"1\">");
		xmlBuilder.append("<LIN NUM=\"1\">");
		xmlBuilder.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">2500</FLD>");
		xmlBuilder.append("</LIN>");
    	xmlBuilder.append("</TAB>");
	    xmlBuilder.append("</PARAM>");
		String xmlInput = xmlBuilder.toString();
	
		try {
			
			System.out.println("Waitng for the response...");
			result = obj.read(cc, "SORDER", objKey);
			
			final String xmlStr =result.getResultXml();
			if(xmlStr==null)
			{
				System.out.println("No " +FLD_VALUE +" sales order exist in the list");
				System.out.println("C:\\WEBSERVICE\\READ\\ErrorLog.txt contains the details");
				FileWriter fw1 = null;
				  PrintWriter pw1 = null;
				    fw1 = new FileWriter("C:\\WEBSERVICE\\READ\\ErrorLog.txt");
			        pw1 = new PrintWriter(fw1);
			        pw1.write("No " +FLD_VALUE +" sales order exist in the list");
			       //pw1.write(FLD_VALUE);
			        pw1.close();
			        fw1.close();
			}
			else
			{
				
			 System.out.println(result.getResultXml());
			 Document doc = convertStringToDocument(xmlStr);
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
			 Transformer transformer = transformerFactory.newTransformer();
			 DOMSource source = new DOMSource(doc);
			 StreamResult result2 = new StreamResult(new File("C:\\WEBSERVICE\\MODIFY\\OLD.xml"));
			 transformer.transform(source, result2);
			 System.out.println("Response XML saved at C:\\WEBSERVICE\\MODIFY");
			
			final String xmlFilePath = "C:\\WEBSERVICE\\MODIFY\\OLD.xml";
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
										    fw1 = new FileWriter("C:\\WEBSERVICE\\MODIFY\\response.txt");
									        pw1 = new PrintWriter(fw1);
									        pw1.write("Response \nSales order number from the web service number is..\n");
									        pw1.write(FLD_VALUE);
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
			
			}	
			
			System.out.println("Preparing output...");
			result = obj.modify(cc, "SORDER", objKey, xmlHeader.concat(xmlInput));
			//System.out.println(result.getResultXml());
			final String xmlStr1 =result.getResultXml();
			System.out.println(result.getResultXml());
			
			 Document doc = convertStringToDocument(xmlStr1);
			 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result2 = new StreamResult(new File("C:\\WEBSERVICE\\MODIFY\\MODIFIED.xml"));
			transformer.transform(source, result2);
			System.out.println("Response XML saved at C:\\WEBSERVICE\\MODIFY");
			
			final String xmlFilePath = "C:\\WEBSERVICE\\MODIFY\\MODIFIED.xml";
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
										    fw1 = new FileWriter("C:\\WEBSERVICE\\MODIFY\\response.txt");
									        pw1 = new PrintWriter(fw1);
									        pw1.write("Response \nSales order number from the web service number is..\n");
									        pw1.write(FLD_VALUE);
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
		    } 
		    catch (RemoteException e) 
		       {
			      e.printStackTrace();
		       }
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
