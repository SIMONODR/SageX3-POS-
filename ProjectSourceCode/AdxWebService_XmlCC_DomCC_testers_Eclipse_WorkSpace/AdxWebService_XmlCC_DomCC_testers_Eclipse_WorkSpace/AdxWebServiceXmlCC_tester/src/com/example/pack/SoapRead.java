package com.example.pack;
//**************************************************************************//
//                      Created By Rameeha.C                                //
//**************************************************************************//
import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.StringTokenizer;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxParamKeyValue;
import com.adonix.wsvc.stubs.CAdxResultXml;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCSoapBindingStub;

public class SoapRead {
	
		public static void main(String args[])
		{
			String line=" ";
			int lineNo;
			String X3poolName="",X3serverName="",X3lang="",X3user="",X3password="";
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
					else
					{
						br.readLine();
					}
			CAdxWebServiceXmlCCServiceLocator serviceLocator;
			serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
			//CAdxWebServiceXmlCCService x3Service;; // The web service instance
			serviceLocator.setCAdxWebServiceXmlCCEndpointAddress("http://"+X3serverName+"/adxwsvc/services/CAdxWebServiceXmlCC");
				}
				
			}catch(Exception e)
			{
				System.out.println(e);
			}	
//			 Calling context - class CAdxCallContext 
			CAdxCallContext cc = new CAdxCallContext(); // Instance of CAdxCallContext 
			cc.codeLang = X3lang; 
			cc.codeUser = X3user;  
			cc.password = X3password;
			cc.poolAlias = X3poolName; 
			cc.requestConfig = "adxwss.trace.on=on&adxwss.trace.size=16384 &adonix.trace.on=on&adonix.trace.level=3 &adonix.trace.size=8"; // Request configuration string
			try{
			 //Web service instance
			 //CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCServiceLocator();
			CAdxWebServiceXmlCCSoapBindingStub obj=new CAdxWebServiceXmlCCSoapBindingStub();
             //XmlResult instance
			CAdxResultXml result = new CAdxResultXml();  
//			 Record key: An array of CAdxParamKeyValue 
			CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one element (Simple object) 
			objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM06-15-000013"); // Method: read(CAdxCallContext <context>, String <publicname>, CAdxParamKeyValue <key>) 
			try {
				System.out.println("Waitng for the response...");
				result = obj.read(cc, "SORDER", objKey);
				System.out.println(result.getResultXml());
			} catch (RemoteException e)
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