package com.webservice.adonix.salesorder;
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

public class SoapCreate {
	public static void main(String args[])
	{
		execute();

	}

	public static void execute() {
		// TODO Auto-generated method stub
		
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
		//	 Web service instance
		//CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();
		CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
//		 XmlResult instance
		 CAdxResultXml result = new CAdxResultXml();
		 StringBuffer xmlBuilder = new StringBuffer(1000);
		 StringBuffer xmlBuilder1 = new StringBuffer(1000);
		 StringBuffer xmlBuilder2 = new StringBuffer(10000);
		try
	    {
	                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	                String userName = "sa";
	                String password = "tiger2015.";
	                String url ="jdbc:sqlserver://10.8.5.251;databaseName=TestData;instanceName=X3V7;";
	                Connection conn=DriverManager.getConnection(url, userName, password);
	                Statement stmt=conn.createStatement();
	                Statement stmt2=conn.createStatement();
	                Statement stmt3=conn.createStatement();
	                Statement stmt4=conn.createStatement();
	                System.out.println("connected");
	                int i=1,r1=0,r2=0,j=0;
	                ResultSet rs1=stmt.executeQuery("select * from dbo.table_E");
	                while (rs1.next()){
	           		j=j+1;
	    	        String da=rs1.getDate(7).toString();
	    	        String da1=da.substring(0,4);
	    	        String da2=da.substring(5,7);
	    	        String da3=da.substring(8,10);
	    	        String da4=da1+da2+da3;
	    	        
		
		
		r1=rs1.getInt(1);
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append("<PARAM><GRP ID=\"SOH0_1\">");
		xmlBuilder.append("<FLD NAME=\"SALFCY\" TYPE=\"Char\">"+rs1.getString(3)+"</FLD>");
		xmlBuilder.append("<FLD NAME=\"SOHTYP\" TYPE=\"Char\">"+rs1.getString(4)+"</FLD>");
		xmlBuilder.append("<FLD NAME=\"ZSOHTYP\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"SOHNUM\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"REVNUM\" TYPE=\"Integer\"/>");
		xmlBuilder.append("<FLD NAME=\"CUSORDREF\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"ORDDAT\" TYPE=\"Date\">"+da4+"</FLD>");
		xmlBuilder.append("<FLD NAME=\"BPCORD\" TYPE=\"Char\">"+rs1.getString(6)+"</FLD>");
		xmlBuilder.append("<FLD NAME=\"BPCNAM\" TYPE=\"Char\"/>");
		xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">"+rs1.getString(10)+"</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<GRP ID=\"SOH1_4\">");
		xmlBuilder.append("<FLD NAME=\"CUR\" TYPE=\"Char\">"+rs1.getString(10)+"</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<GRP ID=\"SOH2_1\">");
		xmlBuilder.append("<FLD NAME=\"STOFCY\" TYPE=\"Char\">"+rs1.getString(9)+"</FLD>");
		xmlBuilder.append("</GRP>");
		xmlBuilder.append("<TAB DIM=\"1002\" ID=\"SOH4_1\" SIZE=\"1\">");

		 ResultSet rs=stmt2.executeQuery("select * from dbo.table_L WHERE E_key="+rs1.getInt(1)+"");

		 while (rs.next()) {
        	r2=rs.getInt(1);
       	 
		xmlBuilder2.append("<LIN NUM=\""+i+"\">");
		xmlBuilder2.append("<FLD NAME=\"NUMLIG\" TYPE=\"Integer\"/>");
		xmlBuilder2.append("<FLD NAME=\"ITMREF\" TYPE=\"Char\">"+rs.getString(4)+"</FLD>");
		xmlBuilder2.append("<FLD NAME=\"ITMDES1\" TYPE=\"Char\">"+rs.getString(5)+"</FLD>");
		xmlBuilder2.append("<FLD NAME=\"DSTOFCY\" TYPE=\"Char\">"+rs1.getString(9)+"</FLD>");
		xmlBuilder2.append("<FLD NAME=\"SAU\" TYPE=\"Char\">"+rs.getString(6)+"</FLD>");
        xmlBuilder2.append("<FLD NAME=\"QTY\" TYPE=\"Decimal\">"+rs.getString(7)+"</FLD>");
        xmlBuilder2.append("<FLD NAME=\"SAUSTUCOE\" TYPE=\"Decimal\"/>");
	    xmlBuilder2.append("<FLD NAME=\"ALLQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder2.append("<FLD NAME=\"SHTQTY\" TYPE=\"Decimal\"/>");
	    xmlBuilder2.append("<FLD NAME=\"WALLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder2.append("<FLD NAME=\"TDLQTY\" TYPE=\"Decimal\"/>");
		xmlBuilder2.append("<FLD NAME=\"GROPRI\" TYPE=\"Decimal\">"+rs.getString(8)+"</FLD>");
		xmlBuilder2.append("<FLD NAME=\"NETPRI\" TYPE=\"Decimal\">"+rs.getString(8)+"</FLD>");
		xmlBuilder2.append( "<FLD NAME=\"CPRPRI\" TYPE=\"Decimal\"/><FLD NAME=\"PFM\" TYPE=\"Decimal\"/><FLD NAME=\"CCE1\" TYPE=\"Char\"/><FLD NAME=\"CCE2\" TYPE=\"Char\"/><FLD NAME=\"CCE3\" TYPE=\"Char\"/><FLD NAME=\"CCE4\" TYPE=\"Char\"/><FLD NAME=\"CCE5\" TYPE=\"Char\"/><FLD NAME=\"CCE6\" TYPE=\"Char\"/><FLD NAME=\"CCE7\" TYPE=\"Char\"/><FLD NAME=\"CCE8\" TYPE=\"Char\"/><FLD NAME=\"CCE9\" TYPE=\"Char\"/><FLD NAME=\"CCE10\" TYPE=\"Char\"/><FLD NAME=\"CCE11\" TYPE=\"Char\"/><FLD NAME=\"CCE12\" TYPE=\"Char\"/><FLD NAME=\"CCE13\" TYPE=\"Char\"/><FLD NAME=\"CCE14\" TYPE=\"Char\"/><FLD NAME=\"CCE15\" TYPE=\"Char\"/><FLD NAME=\"CCE16\" TYPE=\"Char\"/><FLD NAME=\"CCE17\" TYPE=\"Char\"/><FLD NAME=\"CCE18\" TYPE=\"Char\"/><FLD NAME=\"CCE19\" TYPE=\"Char\"/><FLD NAME=\"CCE20\" TYPE=\"Char\"/><FLD NAME=\"LINORDNOT\" TYPE=\"Decimal\"/><FLD NAME=\"LINORDATI\" TYPE=\"Decimal\"/><FLD NAME=\"LINPFM\" TYPE=\"Decimal\"/>" );
		xmlBuilder2.append( "<FLD NAME=\"XPOSITM\" TYPE=\"Char\">"+rs.getString(14)+"</FLD>");
		xmlBuilder2.append("<FLD NAME=\"XENTRYTIM\" TYPE=\"Char\">"+rs.getString(15)+"</FLD>");
		xmlBuilder2.append("</LIN>");
		i=i+1;
		stmt3.executeUpdate("delete from dbo.table_L where E_key="+r1+" and L_key="+r2+"");
		xmlBuilder1.append(xmlBuilder2);
		
		 // xmlBuilder2.delete(0, xmlBuilder2.length());
        }
      //  xmlBuilder1.delete(0, xmlBuilder1.length());
        xmlBuilder.append(xmlBuilder1);
		xmlBuilder.append("</TAB>");
		
		 
	                
	       
	       
	       xmlBuilder.append( "</PARAM>" );
		String xmlInput = xmlBuilder.toString();
		//String xmlInput = xmlBuilder.toString();
	  
//		 Record key: An array of CAdxParamKeyValue 
		//CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
		//objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM01-15-000016"); // Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
		try {
			result = obj.save(cc, "SORDER", xmlInput);
			System.out.println(result.getResultXml());
			  xmlBuilder.delete(0, xmlBuilder.length());
				 xmlBuilder1.delete(0, xmlBuilder1.length());
				 xmlBuilder2.delete(0, xmlBuilder2.length());
				 i=1;
			  }
		
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 stmt4.executeUpdate("delete from dbo.table_E where E_key="+r1+"");
		
	                }
	               
		 
	                System.out.println(j + " No of Order created");         
	           
	             	
	    }
	  		catch (Exception e)
	          {
	              e.printStackTrace();
	          }

	}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//try{
			//   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
          //    String userName = "sa";
           //   String password = "tiger2015.";
           //   String url ="jdbc:sqlserver://10.8.5.251;databaseName=TestData;instanceName=X3V7;";
           //   Connection conn=DriverManager.getConnection(url, userName, password);
			//  Statement stm=conn.createStatement();
		    //   stm.executeUpdate("delete from dbo.table_E where E_key");
		     //  stm.executeUpdate("delete from dbo.table_L where E_key");
	//	}
		//catch(Exception e)
		//{
			//e.printStackTrace();
		//}
	}
}
