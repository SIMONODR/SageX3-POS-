package com.example.pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Start {
public static void main(String args[])
{
	String line=" ";
	int lineNo;
	String b="",d="",f="",h="",j="";
	try
	{
		FileReader fr= new FileReader("C:\\WEBSERVICE\\config.txt");
		BufferedReader br=new BufferedReader(fr);
		for(lineNo = 1; lineNo <=11; lineNo++)
		{
			if(lineNo == 9)
			{
			line = br.readLine();
			
			StringTokenizer st=new StringTokenizer(line,"?");
        	while(st.hasMoreTokens())
			{
				String s=st.nextToken();
				if(s.equals("Sales order="))
				{
					String c=s;
				}
				else
				{
				   d=s;
				}
			}
			
			}
			else if(lineNo == 10 )
			{
				line = br.readLine();
				
				StringTokenizer st=new StringTokenizer(line,"?");
				
				while(st.hasMoreTokens())
				{
					String s=st.nextToken();
					if(s.equals("Work Order="))
					{
						String a=s;
					}
					else
					{
					    b=s;// Pool name 
					}
				}
			}
			
			else if(lineNo == 11 )
			{
				line = br.readLine();
				
				StringTokenizer st=new StringTokenizer(line,"?");
				
				while(st.hasMoreTokens())
				{
					String s=st.nextToken();
					if(s.equals("Sales Invoice="))
					{
						String e=s;
					}
					else
					{
					    f=s;// Language code 
					}
				}
				}
			else
			{
				br.readLine();
			}
		}
		
	if(d.equals("Y"))
	{
		System.out.println("Sales Order creation is progressing...");
		SoapCreateTesting obj=new SoapCreateTesting();
		obj.execute();
	}
	else
	{
		System.out.println("Sales Order is not progressing...");
	}
	if(b.equals("Y"))
	{
		System.out.println("Work Order is progressing...");
	}
	else
	{
		System.out.println("Work Order is not progressing...");
	}
	if(f.equals("Y"))
	{
		System.out.println("Sales Invoice is progressing...");
	}
	else
	{
		System.out.println("Sales Invoice is not progressing...");
	}
	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}	
}
}
