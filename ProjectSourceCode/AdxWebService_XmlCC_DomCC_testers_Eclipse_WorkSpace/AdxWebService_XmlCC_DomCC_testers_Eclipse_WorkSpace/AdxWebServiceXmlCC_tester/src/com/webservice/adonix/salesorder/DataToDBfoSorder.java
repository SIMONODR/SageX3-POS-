package com.webservice.adonix.salesorder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;
			public class DataToDBfoSorder {
				
				public static void main(String[] args) {
					
					execute();	
				}
					public static void execute() {
					// TODO Auto-generated method stub
					
				
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
					int j=0;
					String j00,j01,j02,j03,j04,j05,j06,j07,j08,j09,j010,j011;
					try{
						//******To Clear Existing Text Files******//
					PrintWriter writer11 = new PrintWriter("C:\\WEBSERVICE\\EOUT.txt");
					writer11.print("");
					writer11.close();
					PrintWriter writer21 = new PrintWriter("C:\\WEBSERVICE\\LOUT.txt");
					writer21.print("");
					writer21.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					BufferedReader in = null;
				    String read;
				    int linenum;
				    try{
				    in = new BufferedReader(new FileReader("C:\\WEBSERVICE\\SO2.txt")); 
				    }
				    catch (Exception e) 
				    {
				    	 e.printStackTrace();
				    }
				    try{
				    for (linenum = 0; linenum<100; linenum++){
				    read = in.readLine();
				    if(read == null){} 
				    else{
				    String[] splited = read.split("\\;+");
				    for(int i=0;i<splited.length;i++)
				    {
				    	
				    String ja=splited[0];
				    
				    if(ja.equals("E"))
				    {
				    	String j0,j1,j2,j3,j4,j5,j6,j41;
				    	j0=splited[0];
				    	j1=splited[1];
				    	j2=splited[2];
				    	j3=splited[4];
				    	j41=splited[3];
				    	j4=splited[5];
				    	j5=splited[6];
				    	j6=splited[7];
				         
				    	String d2=j4.substring(0,4);
				    	System.out.println(d2);
				    	String d3=j4.substring(4,6);
				    	System.out.println(d3);
				    	String d4=j4.substring(6,8);
				    	System.out.println(d4);
				    	String d5=d4+"/"+ d3+"/"+d2;
				    	System.out.println(d5);
				    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    	String dateInString = d5;
                        
				    	try{

				    		Date date = formatter.parse(dateInString);
				    		System.out.println(date);
				    		System.out.println(formatter.format(date));
   				                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				                    String userName = SqlUser;
				                    String password = SqlPassword;
				                    String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=TestData;instanceName=X3V7;";
				                    Connection conn=DriverManager.getConnection(url, userName, password);
				                    Statement stmt=conn.createStatement();
				                    System.out.println("connected");
				                    stmt.executeUpdate("insert into dbo.table_E(FIRSTVAL,SALFCY,SOHTYP,BPCORD,ORDDAT,STOFCY,CUR) values('"+j0+"','"+j1+"','"+j2+"','"+j3+"','"+j4+"','"+j5+"','"+j6+"')");
				                    System.out.println("Inserted");
				            }
				                catch (Exception e)
				                {
				                    e.printStackTrace();
				                }
                          
				    	
					    	System.out.println(splited[i]+" ");
					    	FileWriter writer = new FileWriter("C:\\WEBSERVICE\\EOUT.txt",true); 
					    	writer.write("\n");
				                for(String str: splited) {
				    	            writer.write(str+" ");
				    	                                 }
				         	writer.close();
				         	FileInputStream fstream = new FileInputStream("C:\\WEBSERVICE\\EOUT.txt");
				      		DataInputStream in1 = new DataInputStream(fstream);
				      		BufferedReader br = new BufferedReader(new InputStreamReader(in1));
				      		String strLine;
				      		LinkedHashSet<String> set = new LinkedHashSet<String>();
				      		while ((strLine = br.readLine()) != null)
				      		{
				      		set.add(strLine);
				      		System.out.println (strLine);
				      		}
				      		System.out.println("Set :" + set);
				      		     FileWriter writer1 = new FileWriter("C:\\WEBSERVICE\\EOUT.txt"); 
				      	    	 writer1.write("\n");
				      	         for(String str: set) {
				      	    	      writer1.write(str+"\n");
				      	    	                      }
				      	         
				      	         	  writer1.close();
				      	
				      		in1.close();
				      	    
				    }
				    try
			        {
			                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			                    String userName = SqlUser;
			                    String password = SqlPassword;
			                    String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=TestData;instanceName=X3V7;";
			                    Connection conn=DriverManager.getConnection(url, userName, password);
			                    Statement stmt5=conn.createStatement();
			                    System.out.println("connected");
			                    stmt5.executeUpdate("Delete FROM dbo.table_E WHERE E_key NOT IN (SELECT MIN(E_key) FROM dbo.table_E GROUP BY FIRSTVAL,SALFCY,SOHTYP,BPCORD,ORDDAT,STOFCY,CUR)");
			                    System.out.println("DELETED");
			           	 
			   
			        }		   
				    catch (Exception e)
			                {
			                    e.printStackTrace();
			                }    
				    if(ja.equals("L"))
				    {
				    	j00=splited[0];
				    	j01=splited[1];
				    	j02=splited[2];
				    	j03=splited[3];
				    	j04=splited[4];
				    	j05=splited[5];
				    	j06=splited[6];
				    	j07=splited[7];
				    	j08=splited[8];
				    	j09=splited[9];
				    	j010=splited[10];
				    	j011=splited[11];

				   System.out.println(splited[i]+" ");
				   FileWriter writer = new FileWriter("C:\\WEBSERVICE\\LOUT.txt",true); 
				   writer.write("\n");
				      for(String str: splited) 
				        {
				    	writer.write(str+" ");
				    	j=j+1;
				    	}
				            writer.close();
				    	    FileInputStream fstream = new FileInputStream("C:\\WEBSERVICE\\LOUT.txt");
				      		DataInputStream in1 = new DataInputStream(fstream);
				      		BufferedReader br = new BufferedReader(new InputStreamReader(in1));
				      		String strLine;
				      		LinkedHashSet<String> set = new LinkedHashSet<String>();
				      		//Read File Line By Line
				      		while ((strLine = br.readLine()) != null) {
				      		// Print the content on the console
				      		set.add(strLine);
				      		System.out.println (strLine);
				      		}
				      		System.out.println("Set :" + set);
				      				
					      			FileWriter writer1 = new FileWriter("C:\\WEBSERVICE\\LOUT.txt"); 
					      	    	 writer1.write("\n");
								      	      for(String str: set) {
								      	    	  						writer1.write(str+"\n");
								      	    						}
				      	         	writer1.close();
				     
				      		//Close the input stream
				      		in1.close();
				 	try
			        {
			                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			                    String userName = SqlUser;
			                    String password = SqlPassword;
			                    String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=TestData;instanceName=X3V7;";
			                    Connection conn=DriverManager.getConnection(url, userName, password);
			                    Statement stmt=conn.createStatement();
			                    Statement stmt2=conn.createStatement();
			                    System.out.println("connected");
			                    ResultSet rs=stmt.executeQuery("select max(E_key) from dbo.table_E");
			                    while (rs.next()) {
			           	        System.out.println(rs.getString(1));
			           	        int id=rs.getInt(1);
			                    stmt2.executeUpdate("insert into dbo.table_L(FIRSTVAL,XPOSITM,ITMREF,ITMDES,SAU,QTY,GROPRI,DISCRGVAL1,DISCRGVAL2,DISCRGVAL3,XENTRYTIM,FMI,E_key) values('"+j00+"','"+j01+"','"+j02+"','"+j03+"','"+j04+"','"+j05+"','"+j06+"','"+j07+"','"+j08+"','"+j09+"','"+j010+"','"+j011+"',"+id+")");
			                    System.out.println("Inserted");
			           	 }
			        	}		                catch (Exception e)
			                {
			                    e.printStackTrace();
			                }
				    
				    }
				    }
				    
				}
				    } 
				    }
				    catch (IOException e) {System.out.println("There was a problem: " + e);} 
				    
				    try
			        {
			                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			                    String userName = SqlUser;
			                    String password = SqlPassword;
			                    String url ="jdbc:sqlserver://"+SqlServerName+";databaseName=TestData;instanceName=X3V7;";
			                    Connection conn=DriverManager.getConnection(url, userName, password);
			                    Statement stmt5=conn.createStatement();
			                    System.out.println("connected");
			                    stmt5.executeUpdate("Delete FROM dbo.table_L WHERE L_key NOT IN (SELECT MIN(L_key) FROM dbo.table_L GROUP BY FIRSTVAL, XPOSITM,ITMREF,ITMDES,SAU,QTY,GROPRI,DISCRGVAL1,DISCRGVAL2,DISCRGVAL3,L1,L2,XENTRYTIM,FMI,E_key)");
			                    System.out.println("DELETED");
			                    PrintWriter writer110 = new PrintWriter("C:\\WEBSERVICE\\SO2.txt");
								writer110.print("");
								writer110.close();
			     }		     
				    catch (Exception e)
			        {
			           e.printStackTrace();
			        }    
          //****************************************Calling Xml Creation******************************************************//				    
				    SoapCreateLog obj=new SoapCreateLog();
				    obj.execute();
				}
				}