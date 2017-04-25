//**************************************************************************//
//                        Created By Rameeha.C                              //
//**************************************************************************//
package com.example.pack;
import java.rmi.RemoteException;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxParamKeyValue;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SoapModify {
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
		serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.137:28880/adxwsvc/services/CAdxWebServiceXmlCC");
				
		
		try{

		//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();		//	 Web service instance
		CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
		CAdxResultXml result = new CAdxResultXml();//		 XmlResult instance

	     
//		 Record key: An array of CAdxParamKeyValue 
		CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
		objKey[0] = new CAdxParamKeyValue(); 
		objKey[0].key="SOHNUM";
		objKey[0].value="OUT-DMM01-15-000046";
		
		String xmlHeader = new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		String xmlInput = new String("<PARAM><TAB DIM=\"1002\" ID=\"SOH4_1\" SIZE=\"1\"><LIN NUM=\"1\"><FLD NAME=\"GROPRI\">1000</FLD></LIN></TAB></PARAM>");
							//	<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\" >0</FLD><FLD NAME=\"SHTQTY\" TYPE=\"Decimal\" >0</FLD><FLD NAME=\"WALLQTY\" TYPE=\"Decimal\" >0</FLD><FLD NAME=\"TDLQTY\" TYPE=\"Decimal\" >0</FLD><FLD NAME=\"GROPRI\" TYPE=\"Decimal\" >2500</FLD>

								

		
		try {
			System.out.println("Preparing output...");
			result = obj.modify(cc, "SORDER", objKey, xmlHeader.concat(xmlInput));
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
