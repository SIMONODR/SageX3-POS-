package com.webservice.adonix.salesinvoice;

import java.rmi.RemoteException;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SalesInvoiceCreate {
	public static void main(String args[])
	{
		execute();

	}

	public static void execute() {
		
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
	     CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
		 CAdxResultXml result = new CAdxResultXml();
		 StringBuffer xmlBuilder = new StringBuffer(1000);
		 StringBuffer xmlBuilder1 = new StringBuffer(1000);
		 StringBuffer xmlBuilder2 = new StringBuffer(10000);
				try
			    {
			              	    	        
				xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				xmlBuilder.append("<PARAM><GRP ID=\"SIH0_1\">");
				xmlBuilder.append("<FLD NAME=\"SALFCY\" TYPE=\"Char\">DMM01</FLD>");
				xmlBuilder.append("<FLD NAME=\"SIVTYP\" TYPE=\"Char\">ZAINV</FLD>");
				xmlBuilder.append("<FLD NAME=\"ZSIVTYP\" TYPE=\"Char\">Sales Inv</FLD>");
				xmlBuilder.append("<FLD NAME=\"NUM\" TYPE=\"Char\">ZAINV1501DMM01000139</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVREF\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"INVDAT\" TYPE=\"Date\">20150101</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPCINV\" TYPE=\"Char\">AR-15-SAL-0001</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPINAM\" TYPE=\"Char\">Kushnol Equip</FLD>");
				xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">SAR</FLD>");
				xmlBuilder.append("</GRP>");
				
				xmlBuilder.append("<GRP ID=\"SIH1_1\">");
				xmlBuilder.append("<FLD NAME=\"BPCORD\" TYPE=\"Char\">AR-15-SAL-0001</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPCNAM\" TYPE=\"Char\">Kushnol Equip</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPRPAY\" TYPE=\"Char\">AR-15-SAL-0001</FLD>");
				xmlBuilder.append("<FLD NAME=\"ZBPRPAY\" TYPE=\"Char\">Kushnol Equip</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPCGRU\" TYPE=\"Char\">AR-15-SAL-0001</FLD>");
				xmlBuilder.append("<FLD NAME=\"ZBPCGRU\" TYPE=\"Char\">Kushnol Equip</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPAADD\" TYPE=\"Char\">7</FLD>");
				xmlBuilder.append("<FLD NAME=\"BPDNAM\" TYPE=\"Char\">Kushnol Equip</FLD>");
				xmlBuilder.append("</GRP>");
				
				xmlBuilder.append("<GRP ID=\"SIH1_4\">");
				xmlBuilder.append("<FLD NAME=\"PIHNUM\" TYPE=\"Char\"></FLD>");
			    xmlBuilder.append("</GRP>");
				
				
				xmlBuilder.append("<GRP ID=\"SIH1_5\">");
				xmlBuilder.append("<FLD MENULAB=\"Ex tax\" MENULOCAL=\"243\" NAME=\"PRITYP\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("</GRP>");
		
				xmlBuilder.append("<GRP ID=\"SIH1_6\">");
				xmlBuilder.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"STOMVTFLG\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("<FLD NAME=\"STOFCY\" TYPE=\"Char\">DMM01</FLD>");
				xmlBuilder.append("</GRP>");
		
				xmlBuilder.append("<GRP ID=\"SIH1_7\">");
				xmlBuilder.append("<FLD MENULAB=\"Not posted\" MENULOCAL=\"2261\" NAME=\"INVSTA\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"STARPT\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("</GRP>");
				
				xmlBuilder.append("<GRP ID=\"SIH2_1\">");
				xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">SAR</FLD>");
				xmlBuilder.append("<FLD NAME=\"ZCUR\" TYPE=\"Char\">Saudi Arabian riyal</FLD>");
				xmlBuilder.append("<FLD MENULAB=\"Daily Rate\" MENULOCAL=\"202\" NAME=\"CURTYP\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("<FLD NAME=\"RAT1\" TYPE=\"Decimal\">1</FLD>");
				xmlBuilder.append("<FLD NAME=\"LABEL\" TYPE=\"Char\">SAR =</FLD>");
				xmlBuilder.append("<FLD NAME=\"RAT2\" TYPE=\"Decimal\">1</FLD>");
				xmlBuilder.append("<FLD NAME=\"CURMLT\" TYPE=\"Char\">SAR</FLD>");
				xmlBuilder.append("");
				xmlBuilder.append("</GRP>");
				
				
				xmlBuilder.append("<GRP ID=\"SIH2_2\">");
				xmlBuilder.append("<FLD NAME=\"PTE\" TYPE=\"Char\">CHQ30EOM</FLD>");
				xmlBuilder.append("</GRP>");
 
				xmlBuilder.append("<GRP ID=\"SIH2_3\">");
				xmlBuilder.append("</GRP>");
				
				
				xmlBuilder.append("<TAB DIM=\"30\" ID=\"SIH2_5\" SIZE=\"4\">");
				
				xmlBuilder.append("<LIN NUM=\"1\">");
				xmlBuilder.append("	<FLD NAME=\"SHO\" TYPE=\"Char\">Discount %</FLD>");
				xmlBuilder.append("	<FLD NAME=\"INVDTAAMT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("	<FLD MENULAB=\"%\" MENULOCAL=\"2227\" NAME=\"INVDTATYP\" TYPE=\"Integer\">3</FLD>");
				xmlBuilder.append("	</LIN>");
				
				xmlBuilder.append("<LIN NUM=\"2\">");
				xmlBuilder.append("<FLD NAME=\"SHO\" TYPE=\"Char\">Freight char</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVDTAAMT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD MENULAB=\"Tax excl\" MENULOCAL=\"2227\" NAME=\"INVDTATYP\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("</LIN>");
				
				xmlBuilder.append("<LIN NUM=\"3\">");
				xmlBuilder.append("<FLD NAME=\"SHO\" TYPE=\"Char\">Insurance</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVDTAAMT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD MENULAB=\"Tax excl\" MENULOCAL=\"2227\" NAME=\"INVDTATYP\" TYPE=\"Integer\">1</FLD>");
				xmlBuilder.append("</LIN>");
				
				xmlBuilder.append("<LIN NUM=\"4\">");
				xmlBuilder.append("<FLD NAME=\"SHO\" TYPE=\"Char\">Tax-excl. di</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVDTAAMT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD MENULAB=\"%\" MENULOCAL=\"2227\" NAME=\"INVDTATYP\" TYPE=\"Integer\">3</FLD>");
				xmlBuilder.append("</LIN>");
				xmlBuilder.append("</TAB>");
				
				xmlBuilder.append("<GRP ID=\"SIH4_2\">");
				xmlBuilder.append("<FLD NAME=\"PFMTOT\" TYPE=\"Decimal\">-220.40</FLD>");
				xmlBuilder.append("</GRP>");

				xmlBuilder.append("<GRP ID=\"SIH4_3\">");
				xmlBuilder.append("<FLD NAME=\"INVNOT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVATI\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("</GRP>");

				xmlBuilder.append("<TAB DIM=\"300\" ID=\"SIH4_1\" SIZE=\"1\">");
				xmlBuilder.append("<LIN NUM=\"1\">");
				xmlBuilder.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\">74-BEVE-0030</FLD>");
				xmlBuilder.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\">Bottled Water Meal</FLD>");
				xmlBuilder.append("<FLD NAME=\"SAU\" TYPE=\"Char\">UN</FLD>");
				xmlBuilder.append("<FLD NAME=\"QTY\" TYPE=\"Decimal\">10</FLD>");
				xmlBuilder.append("<FLD NAME=\"STU\" TYPE=\"Char\">UN</FLD>");
				xmlBuilder.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"DISCRGVAL4\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"DISCRGVAL5\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"DISCRGVAL6\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"NETPRI\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"CPRPRI\" TYPE=\"Decimal\">22.04</FLD>");
				xmlBuilder.append("<FLD NAME=\"PFM\" TYPE=\"Decimal\">-22.04</FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE1\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE2\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE3\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE4\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE5\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE6\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE7\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE8\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE9\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE10\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE11\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE12\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE13\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE14\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE15\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE16\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE17\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE18\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE19\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"CCE20\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("</LIN>");
				xmlBuilder.append("</TAB>");

				xmlBuilder.append("<GRP ID=\"SIHV_3\">");
				xmlBuilder.append("<FLD NAME=\"INVNOT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVNOTRPT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"DEVRPT1\" TYPE=\"Char\">SAR</FLD>");
				xmlBuilder.append("<FLD NAME=\"BASDEP\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVATI\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"INVATIRPT\" TYPE=\"Decimal\">0</FLD>");
				xmlBuilder.append("<FLD NAME=\"DEVRPT2\" TYPE=\"Char\">SAR</FLD>");
				xmlBuilder.append("</GRP>");

				
				xmlBuilder.append("<GRP ID=\"ADXTEC\">");
				xmlBuilder.append("<FLD NAME=\"WW_MODSTAMP\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("<FLD NAME=\"WW_MODUSER\" TYPE=\"Char\"></FLD>");
				xmlBuilder.append("</GRP>");
			
				xmlBuilder.append("</PARAM>");		
			
		
				String xmlInput = xmlBuilder.toString();
				
							try {
									result = obj.save(cc, "SINVOICE", xmlInput);
									System.out.println(result.getResultXml());
									xmlBuilder.delete(0, xmlBuilder.length());
									xmlBuilder1.delete(0, xmlBuilder1.length());
									xmlBuilder2.delete(0, xmlBuilder2.length());
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
	   catch (Exception e) 
		  {
			 e.printStackTrace();
     	  }
		
	}
}
