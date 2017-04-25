package com.adonix.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import com.example.pack.SoapCreateTesting;
import com.webservice.adonix.salesinvoice.SalesInvoiceCreate;
import com.webservice.adonix.salesorder.DataToDBfoSorder;
import com.webservice.adonix.workorder.WorkOrderCreate;

public class X3WEBSEVICE {
	public static void main(String args[])

	{
		String line = " ";
		int lineNo;
		String SalesOrder = "", WorkOrder = "", SalesInvoice = "";
		try {
			FileReader fr = new FileReader("C:\\Sage Webservice\\config.txt");
			BufferedReader br = new BufferedReader(fr);
			for (lineNo = 1; lineNo <= 11; lineNo++) {
				if (lineNo == 9) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Sales order=")) {
							String i = s;
						} else {
							SalesOrder = s; // X3 password
						}
					}
				} else if (lineNo == 10) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Work Order=")) {
							String i = s;
						} else {
							WorkOrder = s; // X3 password
						}
					}
				} else if (lineNo == 11) {
					line = br.readLine();

					StringTokenizer st = new StringTokenizer(line, "?");

					while (st.hasMoreTokens()) {
						String s = st.nextToken();
						if (s.equals("Sales Invoice=")) {
							String i = s;
						} else {
							SalesInvoice = s; // X3 password
						}
					}
				}

				else {
					br.readLine();
				}
			}

			if (SalesOrder.equals("Y")) {
				System.out.println("Sales Order creation is progressing...");
				DataToDBfoSorder obj = new DataToDBfoSorder();
				obj.execute();
			} else {
				System.out.println("Sales Order is not progressing...");
			}
			if (WorkOrder.equals("Y")) {
				System.out.println("Work Order creation is progressing...");
				WorkOrderCreate obj = new WorkOrderCreate();
				obj.execute();
			} else {
				System.out.println("Work Order is not progressing...");
			}
			if (SalesInvoice.equals("Y")) {
				System.out.println("Sales Invoice is progressing...");
				SalesInvoiceCreate obj = new SalesInvoiceCreate();
				obj.execute();
			} else {
				System.out.println("Sales Invoice is not progressing...");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
