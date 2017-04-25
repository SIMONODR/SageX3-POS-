package com.webservice.adonix.salesorder;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//         Before running this program you have run DataToDBSorder.java     //
//**************************************************************************//
import java.io.File;

import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

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

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SoapCreateLog {
	public static void main(String args[])
	{
		execute();

	}

	public static void execute() {
		String line=" ";
		int lineNo;
		String X3poolName="",X3serverName="",X3lang="",X3user="",X3password="",SqlServerName="",SqlUser="",SqlPassword="";
		try
		{
			FileReader fr= new FileReader("C:\\WEBSERVICE\\config.txt");
			BufferedReader br=new BufferedReader(fr);
			for(lineNo = 1; lineNo <=8; lineNo++)
			{
				if(lineNo == 1)
				{
				line = br.readLine();
				
				StringTokenizer st=new StringTokenizer(line,"?");
				
				while(st.hasMoreTokens())
				{
					String s=st.nextToken();
					if(s.equals("X3 server name="))
					{
						String c=s;
					}
					else
					{
						X3serverName=s;
					}
				}
				
				}
				else if(lineNo == 2 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("X3 poolname="))
						{
							String a=s;
						}
						else
						{
							X3poolName=s;// Pool name 
						}
					}
					}
				
				else if(lineNo == 3 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("X3 Lang="))
						{
							String e=s;
						}
						else
						{
						    X3lang=s;// Language code 
						}
					}
					}
				else if(lineNo == 4 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("X3 User="))
						{
							String g=s;
						}
						else
						{
						   X3user=s;// X3 user
						}
					}
					}
				else if(lineNo == 5 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("X3 password="))
						{
							String i=s;
						}
						else
						{
						   X3password=s; // X3 password 
						}
					}
					}
				else if(lineNo == 6 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("Sql Server name="))
						{
							String i=s;
						}
						else
						{
						   SqlServerName=s; // X3 password 
						}
					}
					}
				else if(lineNo == 7 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("Sql User="))
						{
							String i=s;
						}
						else
						{
						   SqlUser=s; // X3 password 
						}
					}
					}
				else if(lineNo == 8 )
				{
					line = br.readLine();
					
					StringTokenizer st=new StringTokenizer(line,"?");
					
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
						if(s.equals("Sql Password="))
						{
							String i=s;
						}
						else
						{
						   SqlPassword=s; // X3 password 
						}
					}
					}
				
				
				else
				{
					br.readLine();
				}
			}
				}catch(Exception e)
				{
					System.out.println(e);
				}	
		
		
		// TODO Auto-generated method stub
		
//		 Calling context - class CAdxCallContext 
		CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
		cc.codeLang = X3lang; // Language code 
		cc.codeUser = X3user; // X3 user 
		cc.password = X3password; // X3 password 
		cc.poolAlias = X3poolName; // Pool name 
		cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
	     
		CAdxWebServiceXmlCCServiceLocator serviceLocator;
		
		serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
		//CAdxWebServiceXmlCCService x3Service;; // The web service instance
		serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://"+X3serverName+"/adxwsvc/services/CAdxWebServiceXmlCC");
				
		
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
	                String userName = SqlUser;
	                String password = SqlPassword;
	                String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=TestData;instanceName=X3V7;";
	                Connection conn=DriverManager.getConnection(url, userName, password);
	                Statement stmt=conn.createStatement();
	                Statement stmt2=conn.createStatement();
	                Statement stmt3=conn.createStatement();
	                Statement stmt4=conn.createStatement();
	                System.out.println("connected");
	                int i=1,r1=0,r2=0,j=0;
	                String SOHNUM="";
	                ResultSet rs1=stmt.executeQuery("select * from dbo.table_E");
	                while (rs1.next()){
	           		j=j+1;
	    	        String da=rs1.getDate(7).toString();
	    	        String da1=da.substring(0,4);
	    	        String da2=da.substring(5,7);
	    	        String da3=da.substring(8,10);
	    	        String da4=da1+da2+da3;
	    	        
		
		
		r1=rs1.getInt(1);
		//StringBuffer xmlBuilder = new StringBuffer(1000);
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append( "<PARAM><GRP ID=\"SOH0_1\">");
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
		//	System.out.println(result.getResultXml());
			final String xmlStr =result.getResultXml();
			System.out.println(xmlStr);
			 Document doc = convertStringToDocument(xmlStr);
			 
			//String str12 = convertDocumentToString(doc);
		       // System.out.println(str12);
			
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result2 = new StreamResult(new File("C:\\WEBSERVICE\\XML FILE\\file"+j+".xml"));

				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result2);
				System.out.println("File saved at C:\\WEBSERVICE\\XML FILE!");

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
		final String xmlFilePath = "C:\\WEBSERVICE\\XML FILE\\file"+j+".xml";
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
								
								
									String fN=node5.getNodeValue();
									String SN=eElement2.getTextContent();
									if (fN.equals("SOHNUM"))
									{
									
									System.out.println(SN);
									SOHNUM=SOHNUM+"\n"+SN;
									
									}
								}	
							}
					
						}
					}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
       }
	                System.out.println(j + " No of Order created");      
					PrintWriter writer11 = new PrintWriter("C:\\WEBSERVICE\\SORDERCREATELOG.txt");
					writer11.print("");
					writer11.close();
	                FileWriter fw = null;
					  PrintWriter pw = null;
					  fw = new FileWriter("C:\\WEBSERVICE\\SORDERCREATELOG.txt",true);
				        pw = new PrintWriter(fw);
				        pw.write(j + " No of Sales Order created \n");
				        pw.write(SOHNUM);
				        pw.close();
				        fw.close();
				        System.out.println("LOG file created at C:\\WEBSERVICE\\SORDERCREATELOG.txt ");   
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
