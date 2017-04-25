package com.example.pack;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//**************************************************************************//
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxParamKeyValue;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SoapReadXML {
	
		public static void main(String args[])
		{
//			 Calling context - class CAdxCallContext 
			CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
			cc.codeLang = "ENG"; // Language code 
			cc.codeUser = "ADMIN"; // X3 user 
			cc.password = ""; // X3 password 
			cc.poolAlias = "SCONN"; // Pool name 
			cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
		     
			CAdxWebServiceXmlCCServiceLocator serviceLocator;
			
			serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
			//CAdxWebServiceXmlCCService x3Service;; // The web service instance
			serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.137:28880/adxwsvc/services/CAdxWebServiceXmlCC");
					
			
			try{
			//	 Web service instance
			//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();
			CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
//			 XmlResult instance
			CAdxResultXml result = new CAdxResultXml();
	
		     
//			 Record key: An array of CAdxParamKeyValue 
			CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
			objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM01-15-000016"); // Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
			try {
				    result = obj.read(cc, "SORDER", objKey);//Calling function read for RETRIEVING data from web service
				    File file = new File("D:\\out1.txt"); //Your file where we want to see the XML output
				    FileOutputStream fos = new FileOutputStream(file);
				    PrintStream ps = new PrintStream(fos);
				    System.out.println(result.getResultXml());//Printing result to console
				    System.setOut(ps);//printing result to out1.txt file
				    System.out.println(result.getResultXml());

				
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