package com.example.pack;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//**************************************************************************//
import java.rmi.RemoteException;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SoapCreateTesting {
	public static void main(String args[])
	{
	      execute();	
	}
	public static void execute() {
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
		//	 Web service instance
		//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();
		CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
//		 XmlResult instance
		CAdxResultXml result = new CAdxResultXml();
		StringBuffer xmlBuilder = new StringBuffer(1000);
		//StringBuffer xmlBuilder = new StringBuffer(1000);
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append( "<PARAM><GRP ID=\"SOH0_1\">");
		xmlBuilder.append("<FLD NAME=\"SALFCY\" TYPE=\"Char\">DMM06</FLD>");
		xmlBuilder.append("<FLD NAME=\"SOHTYP\" TYPE=\"Char\">OUT</FLD>");
		xmlBuilder.append("<FLD NAME=\"ZSOHTYP\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"SOHNUM\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"REVNUM\" TYPE=\"Integer\"/>");
		xmlBuilder.append("<FLD NAME=\"CUSORDREF\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"ORDDAT\" TYPE=\"Date\">20150708</FLD>");
		xmlBuilder.append("<FLD NAME=\"BPCORD\" TYPE=\"Char\">AR-11-FRN-0001</FLD>");
		xmlBuilder.append("<FLD NAME=\"BPCNAM\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">SAR</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<GRP ID=\"SOH1_4\">");
		xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">SAR</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<GRP ID=\"SOH2_1\">");
		xmlBuilder.append("<FLD NAME=\"STOFCY\" TYPE=\"Char\">DMM01</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<TAB DIM=\"1002\" ID=\"SOH4_1\" SIZE=\"1\">");
     	xmlBuilder.append("<LIN NUM=\"1\">");
		xmlBuilder.append("<FLD NAME=\"NUMLIG\" TYPE=\"Integer\"/>");
		xmlBuilder.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\">72-SNDW-0017</FLD>");
		xmlBuilder.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\">Veggie Burger Sandwich</FLD>");
		xmlBuilder.append("<FLD NAME=\"DSTOFCY\" TYPE=\"Char\">DMM01</FLD>");
		xmlBuilder.append("<FLD NAME=\"SAU\" TYPE=\"Char\">UN</FLD>");
        xmlBuilder.append("<FLD NAME=\"QTY\" TYPE=\"Decimal\">10</FLD>");
        xmlBuilder.append("<FLD NAME=\"SAUSTUCOE\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"WALLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder.append("<FLD NAME=\"TDLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">1000</FLD>");
		xmlBuilder.append("<FLD NAME=\"NETPRI\" TYPE=\"Decimal\">1000</FLD>");
		xmlBuilder.append( "<FLD NAME=\"CPRPRI\" TYPE=\"Decimal\"/><FLD NAME=\"PFM\" TYPE=\"Decimal\"/><FLD NAME=\"CCE1\" TYPE=\"Char\"/><FLD NAME=\"CCE2\" TYPE=\"Char\"/><FLD NAME=\"CCE3\" TYPE=\"Char\"/><FLD NAME=\"CCE4\" TYPE=\"Char\"/><FLD NAME=\"CCE5\" TYPE=\"Char\"/><FLD NAME=\"CCE6\" TYPE=\"Char\"/><FLD NAME=\"CCE7\" TYPE=\"Char\"/><FLD NAME=\"CCE8\" TYPE=\"Char\"/><FLD NAME=\"CCE9\" TYPE=\"Char\"/><FLD NAME=\"CCE10\" TYPE=\"Char\"/><FLD NAME=\"CCE11\" TYPE=\"Char\"/><FLD NAME=\"CCE12\" TYPE=\"Char\"/><FLD NAME=\"CCE13\" TYPE=\"Char\"/><FLD NAME=\"CCE14\" TYPE=\"Char\"/><FLD NAME=\"CCE15\" TYPE=\"Char\"/><FLD NAME=\"CCE16\" TYPE=\"Char\"/><FLD NAME=\"CCE17\" TYPE=\"Char\"/><FLD NAME=\"CCE18\" TYPE=\"Char\"/><FLD NAME=\"CCE19\" TYPE=\"Char\"/><FLD NAME=\"CCE20\" TYPE=\"Char\"/><FLD NAME=\"LINORDNOT\" TYPE=\"Decimal\"/><FLD NAME=\"LINORDATI\" TYPE=\"Decimal\"/><FLD NAME=\"LINPFM\" TYPE=\"Decimal\"/>" );
		xmlBuilder.append( "<FLD NAME=\"XPOSITM\" TYPE=\"Char\">50009</FLD>");
		xmlBuilder.append("<FLD NAME=\"XENTRYTIM\" TYPE=\"Char\">1032</FLD>");
		xmlBuilder.append("</LIN>");
		xmlBuilder.append("<LIN NUM=\"2\">");
		xmlBuilder.append("<FLD NAME=\"NUMLIG\" TYPE=\"Integer\"/>");
		xmlBuilder.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\">72-SNDW-0017</FLD>");
		xmlBuilder.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\">Veggie Burger Sandwich</FLD>");
		xmlBuilder.append("<FLD NAME=\"DSTOFCY\" TYPE=\"Char\">DMM01</FLD>");
		xmlBuilder.append("<FLD NAME=\"SAU\" TYPE=\"Char\">UN</FLD>");
        xmlBuilder.append("<FLD NAME=\"QTY\" TYPE=\"Decimal\">10</FLD>");
        xmlBuilder.append("<FLD NAME=\"SAUSTUCOE\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder.append("<FLD NAME=\"WALLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder.append("<FLD NAME=\"TDLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">1000</FLD>");
		xmlBuilder.append("<FLD NAME=\"NETPRI\" TYPE=\"Decimal\">1000</FLD>");
		xmlBuilder.append( "<FLD NAME=\"CPRPRI\" TYPE=\"Decimal\"/><FLD NAME=\"PFM\" TYPE=\"Decimal\"/><FLD NAME=\"CCE1\" TYPE=\"Char\"/><FLD NAME=\"CCE2\" TYPE=\"Char\"/><FLD NAME=\"CCE3\" TYPE=\"Char\"/><FLD NAME=\"CCE4\" TYPE=\"Char\"/><FLD NAME=\"CCE5\" TYPE=\"Char\"/><FLD NAME=\"CCE6\" TYPE=\"Char\"/><FLD NAME=\"CCE7\" TYPE=\"Char\"/><FLD NAME=\"CCE8\" TYPE=\"Char\"/><FLD NAME=\"CCE9\" TYPE=\"Char\"/><FLD NAME=\"CCE10\" TYPE=\"Char\"/><FLD NAME=\"CCE11\" TYPE=\"Char\"/><FLD NAME=\"CCE12\" TYPE=\"Char\"/><FLD NAME=\"CCE13\" TYPE=\"Char\"/><FLD NAME=\"CCE14\" TYPE=\"Char\"/><FLD NAME=\"CCE15\" TYPE=\"Char\"/><FLD NAME=\"CCE16\" TYPE=\"Char\"/><FLD NAME=\"CCE17\" TYPE=\"Char\"/><FLD NAME=\"CCE18\" TYPE=\"Char\"/><FLD NAME=\"CCE19\" TYPE=\"Char\"/><FLD NAME=\"CCE20\" TYPE=\"Char\"/><FLD NAME=\"LINORDNOT\" TYPE=\"Decimal\"/><FLD NAME=\"LINORDATI\" TYPE=\"Decimal\"/><FLD NAME=\"LINPFM\" TYPE=\"Decimal\"/>" );
		xmlBuilder.append( "<FLD NAME=\"XPOSITM\" TYPE=\"Char\">50009</FLD>");
		xmlBuilder.append("<FLD NAME=\"XENTRYTIM\" TYPE=\"Char\">1032</FLD>");
		xmlBuilder.append("</LIN></TAB>");
		xmlBuilder.append( "</PARAM>" );
		String xmlInput = xmlBuilder.toString();
		//String xmlInput = xmlBuilder.toString();
	     
//		 Record key: An array of CAdxParamKeyValue 
		//CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
		//objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM01-15-000016"); // Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
		try {
			result = obj.save(cc, "SORDER", xmlInput);
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
