package com.webservice.adonix.workorder;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//         Before running this program you have run DataToDBSorder.java     //
//**************************************************************************//
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class WorkOrderCreate {
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
		 StringBuffer xmlBuilder6 = new StringBuffer(1000);
		 StringBuffer xmlBuilder1 = new StringBuffer(1000);
		 StringBuffer xmlBuilder2 = new StringBuffer(10000);
				try
			    {
			              	    	        
				xmlBuilder6.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				xmlBuilder6.append("<PARAM><GRP ID=\"MFG0_1\">");
				xmlBuilder6.append("<FLD NAME=\"PLNFCY\" TYPE=\"Char\" >DMM01</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ZPLNFCY\" TYPE=\"Char\" >Corniche Branch</FLD>");
				xmlBuilder6.append("<FLD NAME=\"MFGFCY\" TYPE=\"Char\" >DMM01</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ZMFGFCY\" TYPE=\"Char\" >Corniche Branch</FLD>");
				xmlBuilder6.append("<FLD NAME=\"MFGNUM\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("</GRP>");
				
				xmlBuilder6.append("<GRP ID=\"MFG0_2\">");
				xmlBuilder6.append("<FLD MENULAB=\"Firm\" MENULOCAL=\"317\" NAME=\"MFGSTA\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"Complete\" MENULOCAL=\"333\" NAME=\"MFGMOD\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("</GRP>");
				
				xmlBuilder6.append("<GRP ID=\"MFG1_2\">");
				xmlBuilder6.append("<FLD MENULAB=\"Backward\" MENULOCAL=\"334\" NAME=\"SCDMOD\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"STRDAT\" TYPE=\"Date\" >20120130</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XENTRYTIM\" TYPE=\"Char\" >0000</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ENDDAT\" TYPE=\"Date\" >20120130</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XENDTIM\" TYPE=\"Char\" >0000</FLD>");
				xmlBuilder6.append("<FLD NAME=\"AVAOFS\" TYPE=\"Char\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"LTIREDCOE\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("</GRP>");
								
				xmlBuilder6.append("<GRP ID=\"MFG1_3\" >");
				xmlBuilder6.append("<FLD MENULAB=\"Normal\" MENULOCAL=\"365\" NAME=\"MFGPIO\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"MFGEXTQTY\" TYPE=\"Decimal\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"MFGSTU\" TYPE=\"Char\" >UN</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XALLSTA\" TYPE=\"Char\" >Complete/Shortage</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XSCDFLG\" TYPE=\"Char\" >Scheduled</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XTRKFLG\" TYPE=\"Char\" >Pending</FLD>");
			    xmlBuilder6.append("<FLD NAME=\"LABEL\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("</GRP>");
		
				xmlBuilder6.append("<GRP ID=\"MFG1_4\" >");
				xmlBuilder6.append("<FLD NAME=\"ROUNUM\" TYPE=\"Char\" >71-MEAL-0001</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ROUDES\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("<FLD NAME=\"ROUALT\" TYPE=\"Integer\" >45</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ROUALTDES\" TYPE=\"Char\" >Production Grenoble</FLD>");
				xmlBuilder6.append("</GRP>");
		
				xmlBuilder6.append("<TAB DIM=\"10\" ID=\"MFG1_1\" SIZE=\"1\" >");
				xmlBuilder6.append("<LIN NUM=\"1\" >");
				xmlBuilder6.append("	<FLD NAME=\"ITMREF\" TYPE=\"Char\" >71-MEAL-0001</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"ITMDES1\" TYPE=\"Char\" >Chicken Fillet Meal</FLD>");
				xmlBuilder6.append("	<FLD MENULAB=\"Pending\" MENULOCAL=\"363\" NAME=\"ITMSTA\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"UOM\" TYPE=\"Char\" >UN</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"UOMEXTQTY\" TYPE=\"Decimal\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"UOMSTUCOE\" TYPE=\"Decimal\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"STU\" TYPE=\"Char\" >UN</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"EXTQTY\" TYPE=\"Decimal\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"BOMALT\" TYPE=\"Integer\" >45</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"TBOSHO\" TYPE=\"Char\" >Manuf Gren</FLD>");
				xmlBuilder6.append("	<FLD MENULAB=\"Site\" MENULOCAL=\"722\" NAME=\"BPCTYPDEN\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"BPCNUM\" TYPE=\"Char\" >DMM01</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"BPCDES\" TYPE=\"Char\" >Corn</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"VCRNUMORI\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"MFGDES\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("	<FLD MENULAB=\"Product\" MENULOCAL=\"2301\" NAME=\"ITMTYP\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"SHR\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("	<FLD NAME=\"PLANNER\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("	<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"XFMI\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("</LIN>");
				xmlBuilder6.append("</TAB>");
		
				xmlBuilder6.append("<GRP ID=\"MFG2_1\" >");
				xmlBuilder6.append("<FLD NAME=\"MFGEXTQTY\" TYPE=\"Decimal\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"AVAMFGQTY\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"MFGSTU\" TYPE=\"Char\" >UN</FLD>");
				xmlBuilder6.append("</GRP>");
		
				xmlBuilder6.append("<TAB DIM=\"100\" ID=\"MFG2_2\" SIZE=\"3\" >");
				xmlBuilder6.append("<LIN NUM=\"1\">");
				xmlBuilder6.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\" >11-MEAT-0001</FLD>");
				xmlBuilder6.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\" >Chicken Breast</FLD>");
				xmlBuilder6.append("<FLD NAME=\"XMATSTA\" TYPE=\"Char\" >Pending</FLD>");
				xmlBuilder6.append("<FLD NAME=\"RETQTY\" TYPE=\"Decimal\" >0.96</FLD>");
		     	xmlBuilder6.append("<FLD NAME=\"STU\" TYPE=\"Char\" >KG</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Physical\" MENULOCAL=\"2311\" NAME=\"TYPQTY\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\" >0.96</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"AVAQTY\" TYPE=\"Decimal\" >0</FLD>");
		   		xmlBuilder6.append("<FLD NAME=\"USEQTY\" TYPE=\"Decimal\" >0</FLD>");
		  		xmlBuilder6.append("<FLD MENULAB=\"Global with shortage\" MENULOCAL=\"340\" NAME=\"ALLSTA\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"RETDAT\" TYPE=\"Date\" >20120130</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"BOMOPE\" TYPE=\"Integer\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SCA\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Normal\" MENULOCAL=\"438\" NAME=\"CPNTYP\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"RELSCATIA\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"BOMSEQ\" TYPE=\"Integer\" >5</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"MFGLIN\" TYPE=\"Integer\" >1000</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"PLANNER\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"CUMFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"PICPRN\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Internal\" MENULOCAL=\"2225\" NAME=\"SCOFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Component not weighed at station\" MENULOCAL=\"2328\" NAME=\"PKC\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("</LIN>");
				
		        xmlBuilder6.append("<LIN NUM=\"2\" >");
		        xmlBuilder6.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\" >31-PPER-0010</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\" >Garlic Powder Case</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"XMATSTA\" TYPE=\"Char\" >Pending</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"RETQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"STU\" TYPE=\"Char\" >UN</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Physical\" MENULOCAL=\"2311\" NAME=\"TYPQTY\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"AVAQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"USEQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Global\" MENULOCAL=\"340\" NAME=\"ALLSTA\" TYPE=\"Integer\" >3</FLD>");
		       	xmlBuilder6.append("<FLD NAME=\"RETDAT\" TYPE=\"Date\" >20120130</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"BOMOPE\" TYPE=\"Integer\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SCA\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Normal\" MENULOCAL=\"438\" NAME=\"CPNTYP\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"RELSCATIA\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"BOMSEQ\" TYPE=\"Integer\" >10</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"MFGLIN\" TYPE=\"Integer\" >1000</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"PLANNER\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"CUMFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"PICPRN\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Internal\" MENULOCAL=\"2225\" NAME=\"SCOFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Component not weighed at station\" MENULOCAL=\"2328\" NAME=\"PKC\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("</LIN>");
		
		        xmlBuilder6.append("<LIN NUM=\"3\"  >");
		        xmlBuilder6.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\" >16-GROC-0016</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\" >Black Pepper Powder</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"XMATSTA\" TYPE=\"Char\" >Pending</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"RETQTY\" TYPE=\"Decimal\" >0.02</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"STU\" TYPE=\"Char\" >KG</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Physical\" MENULOCAL=\"2311\" NAME=\"TYPQTY\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\" >0.02</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"AVAQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"USEQTY\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Global with shortage\" MENULOCAL=\"340\" NAME=\"ALLSTA\" TYPE=\"Integer\" >2</FLD>");
		       	xmlBuilder6.append("<FLD NAME=\"RETDAT\" TYPE=\"Date\" >20120130</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"BOMOPE\" TYPE=\"Integer\" >0</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"SCA\" TYPE=\"Decimal\" >0</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Normal\" MENULOCAL=\"438\" NAME=\"CPNTYP\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"RELSCATIA\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"BOMSEQ\" TYPE=\"Integer\" >15</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"MFGLIN\" TYPE=\"Integer\" >1000</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"PLANNER\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"CUMFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"PICPRN\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Internal\" MENULOCAL=\"2225\" NAME=\"SCOFLG\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Component not weighed at station\" MENULOCAL=\"2328\" NAME=\"PKC\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("</LIN>");
		        xmlBuilder6.append("</TAB>");
		        
		        xmlBuilder6.append("<GRP ID=\"MFG3_1\" >");
		        xmlBuilder6.append("<FLD MENULAB=\"Backward\" MENULOCAL=\"334\" NAME=\"SCDMOD\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD MENULAB=\"Scheduled\" MENULOCAL=\"335\" NAME=\"SCDFLG\" TYPE=\"Integer\" >2</FLD>");
		        xmlBuilder6.append("</GRP>");
		
		        xmlBuilder6.append("<TAB DIM=\"50\" ID=\"MFG3_2\" SIZE=\"1\" >");
		        xmlBuilder6.append("<LIN NUM=\"1\" >");
		        xmlBuilder6.append("	<FLD NAME=\"OPENUM\" TYPE=\"Integer\" >5</FLD>");
		        xmlBuilder6.append("	<FLD MENULAB=\"Pending\" MENULOCAL=\"308\" NAME=\"OPESTA\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"EXTWST\" TYPE=\"Char\" >DEFSTMAC</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"EXTWSTSHO\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"XWSTTYP\" TYPE=\"Char\" >M/</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"OPESTR\" TYPE=\"Date\" >20120130</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"OPEEND\" TYPE=\"Date\" >20120130</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"FRCSTRHOU\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"WCR\" TYPE=\"Char\" >TRIM</FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"ROODES\" TYPE=\"Char\" ></FLD>");
		        xmlBuilder6.append("	<FLD NAME=\"EXTWSTNBR\" TYPE=\"Integer\" >1</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"EXTQTY\" TYPE=\"Decimal\" >1</FLD>");
		        xmlBuilder6.append("<FLD NAME=\"OPEUOM\" TYPE=\"Char\" >UN</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"Hours\" MENULOCAL=\"301\" NAME=\"TIMUOMCOD\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"EXTSETTIM\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"EXTUNTTIM\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"EXTOPETIM\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"FXDTIM\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD NAME=\"VARTIM\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"311\" NAME=\"SCOCOD\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"SCOWCR\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("<FLD NAME=\"SCOWST\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("<FLD NAME=\"SCOITMREF\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("<FLD NAME=\"SCOLTI\" TYPE=\"Decimal\" >0</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"Calendar Days\" MENULOCAL=\"291\" NAME=\"JOUR1\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("</LIN>");
				xmlBuilder6.append("</TAB>");
		           
				xmlBuilder6.append("<GRP ID=\"MFG4_1\" >");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"PICLISFLG\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"TOEDIT1\" TYPE=\"Char\" >To be prin</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"ROUSHEFLG\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"TOEDIT2\" TYPE=\"Char\" >To be prin</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"LABTIKFLG\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"TOEDIT3\" TYPE=\"Char\" >To be prin</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"MFGTIKFLG\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"TOEDIT4\" TYPE=\"Char\" >To be prin</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"TECCRDFLG\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("<FLD NAME=\"TOEDIT5\" TYPE=\"Char\" >To be prin</FLD>");
				xmlBuilder6.append("</GRP>");
				
				xmlBuilder6.append("<GRP ID=\"MFG4_2\" >");
				xmlBuilder6.append("<FLD MENULAB=\"Yes\" MENULOCAL=\"1\" NAME=\"LBEMAN\" TYPE=\"Integer\" >2</FLD>");
				xmlBuilder6.append("<FLD MENULAB=\"No\" MENULOCAL=\"1\" NAME=\"LBEAUT\" TYPE=\"Integer\" >1</FLD>");
				xmlBuilder6.append("</GRP>");
				
				xmlBuilder6.append("<GRP ID=\"ADXTEC\" >");
				xmlBuilder6.append("<FLD NAME=\"WW_MODSTAMP\" TYPE=\"Char\" ></FLD>");
				xmlBuilder6.append("<FLD NAME=\"WW_MODUSER\" TYPE=\"Char\" >ADMIN</FLD>");
				xmlBuilder6.append("</GRP>");
			
				xmlBuilder6.append("</PARAM>");		
			
		
				String xmlInput = xmlBuilder6.toString();
				
							try {
									result = obj.save(cc, "WORDER", xmlInput);
									System.out.println(result.getResultXml());
									xmlBuilder6.delete(0, xmlBuilder6.length());
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
