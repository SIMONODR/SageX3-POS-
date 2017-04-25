package com.webservice.adonix.payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

public class InvoiceSelection {
public static void main(String args[])

    {
	execute();	
    }
public static void execute() {
	   int i1=0;
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
	 try
     {
                 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                 String userName = SqlUser;
                 String password = SqlPassword;
                 String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=x3v6;instanceName=X3V7;";
                 Connection conn=DriverManager.getConnection(url, userName, password);
                 Statement stmt5=conn.createStatement();
                 System.out.println("connected");
              
                 ResultSet rs=stmt5.executeQuery("Select * from HBPLOT.SINVOICE as SI Left join HBPLOT.BPCUSTOMER as BPC on SI.BPR_0=BPC.BPCNUM_0 where BPC.PTE_0='CHQ30EOM' and SI.STA_0='3'");
                 while(rs.next())
                 {
                	 i1=i1+1;
                	 System.out.println(rs.getString(5));
                 }
                 }		 
	    catch (Exception e)
     {
        e.printStackTrace();
     }
	 System.out.println(i1+" Recordes return");
	}
}
