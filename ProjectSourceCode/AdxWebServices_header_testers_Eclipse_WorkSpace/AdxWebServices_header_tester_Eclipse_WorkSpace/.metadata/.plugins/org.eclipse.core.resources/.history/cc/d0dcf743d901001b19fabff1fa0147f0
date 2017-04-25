package com.adonix.wsvc;

import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCC;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator;

/**
 * @author Adonix Grenoble
 * 
 */
public class CServiceAdx
{
	public static String URL_SUBPATH = "/services/CAdxWebServiceXmlCC";
	
	CActivityViewer pActivityViewer;

	boolean pActivityViewerOn;

	String pEndpointAddress;

	String pWsId;

	CServiceAdxCallContext pServiceAdxCallContext;
	
	CAdxWebServiceXmlCC pService;

	CAdxWebServiceXmlCCServiceLocator pServiceLocator;
	/**
	 * @param aActivityViewer
	 * @param aObjId
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CServiceAdx(CActivityViewer aActivityViewer, String aObjId, String aUrl, CServiceAdxCallContext aCallContext)
			throws Exception
	{

		pActivityViewerOn = (aActivityViewer != null && aActivityViewer.isTraceOn());
		pActivityViewer = aActivityViewer;
		pWsId = aObjId;
		pServiceAdxCallContext = aCallContext;
		pEndpointAddress = aUrl;
		if (pActivityViewerOn)
		{
			pActivityViewer.traceWrite("CServiceAdx.<init>");
			pActivityViewer.traceWriteDescr("EndpointAddress", pEndpointAddress);
		}
		pServiceLocator = new CAdxWebServiceXmlCCServiceLocator();

		pServiceLocator.setCAdxWebServiceXmlCCEndpointAddress(aUrl);

		pService = pServiceLocator.getCAdxWebServiceXmlCC();

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append("url=[").append(pEndpointAddress).append(']');
		wSB.append('\n');
		wSB.append("hasCtx=[").append(pServiceAdxCallContext!=null).append(']');
		return wSB.toString();
	}
	/**
	 * @return
	 */
	String getWsSpgmId()
	{
		return pWsId;
	}
	
	/**
	 * @return
	 */
	String getWsObjId()
	{
		return pWsId;
	}
	/**
	 * @return
	 */
	CAdxCallContext getAdxCallContext()
	{
		return pServiceAdxCallContext.getAdxCallContext();
	}
}
