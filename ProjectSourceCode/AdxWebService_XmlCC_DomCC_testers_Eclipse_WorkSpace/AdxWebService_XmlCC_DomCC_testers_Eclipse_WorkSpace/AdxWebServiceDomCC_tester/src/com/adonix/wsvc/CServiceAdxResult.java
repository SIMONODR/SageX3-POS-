package com.adonix.wsvc;

import javax.xml.soap.SOAPElement;
import com.adonix.wsvc.stubs.CAdxResultDom;
//import org.apache.xml.serialize.OutputFormat;
//import org.apache.xml.serialize.XMLSerializer;

public class CServiceAdxResult extends CAdxResultDom
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -969561754974218450L;

	/**
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @return
	 */
	private static StringBuffer addDescrInSB(StringBuffer aSB, String aId, String aValue)
	{
		if (aSB.length() > 0 && aSB.toString().lastIndexOf('\n') != aSB.length() - 1)
		{
			aSB.append(' ');
		}
		aSB.append(aId);
		aSB.append("=[");
		aSB.append(aValue);
		aSB.append(']');

		return aSB;
	}

	/**
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 * @return
	 */
	private static StringBuffer addDescrInSB(StringBuffer aSB, String aId, String aValue, String aValueB)
	{
		return addDescrInSB(aSB, aId, aValue + " | " + aValueB);
	}

	/**
	 * @param aAdxResultXml
	 */
	public CServiceAdxResult(CAdxResultDom aAdxResultDom)
	{
		setMessages(aAdxResultDom.getMessages());
		setResultElmt(aAdxResultDom.getResultElmt());
		setStatus(aAdxResultDom.getStatus());
		setTechnicalInfos(aAdxResultDom.getTechnicalInfos());
	}

	/**
	 * @return
	 */
	public String dump()
	{
		StringBuffer wSB = new StringBuffer();

		addDescrInSB(wSB, "Status", String.valueOf(getStatus()));
		boolean wHasStatus = (getStatus() > -1);
		wSB.append('\n');
		addDescrInSB(wSB, "HasValidStatus", String.valueOf(getStatus()));

		if (wHasStatus)
		{
			int wNbMess = getMessages().length;
			wSB.append('\n');
			addDescrInSB(wSB, "nbMess", String.valueOf(wNbMess));

			int wI = 0;
			while (wI < wNbMess)
			{
				wSB.append('\n');
				addDescrInSB(wSB, String.valueOf(wI), getMessages()[wI].getType(), getMessages()[wI].getMessage());
				wI++;
			}

			Object wResultElmt = getResultElmt();
			boolean wHasResultXml = (wResultElmt != null);
			addDescrInSB(wSB, "HasResultElmt", String.valueOf(wHasResultXml));
			if (wHasResultXml)
			{
				addDescrInSB(wSB, "ResultElmt.class",wResultElmt.getClass().getName());
				if (wResultElmt instanceof SOAPElement)
				{
					SOAPElement wSOAPElmt = (SOAPElement)wResultElmt;
					
					String wResultXml = wSOAPElmt.toString();
					wSB.append('\n');
					addDescrInSB(wSB, "ResultXml", '\n' + wResultXml + '\n');
				}
			}
		}

		return wSB.toString();
	}
}
