package com.adonix.wsvc;


import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;
/**
 * @author Adonix Grenoble
 * 
 */
public class CServiceAdx
{
	
	
	CActivityViewer pActivityViewer;

	boolean pActivityViewerOn;

	String pEndpointAddress;

	String pWsId;

	CServiceAdxCallContext pServiceAdxCallContext;

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


	}
	
	
	
  /**
   * @param aStub
   * @param aHeaderCtx
   */
  void setHeaderElement(Stub aStub) throws Exception
  {
    aStub.clearHeaders();
    aStub.setHeader(buildHeaderElement(pServiceAdxCallContext));
  }
	
  final static String NAME_SPACE          = "http://www.adonix.com/WSS";
  
  final static String HEADER_CLASS_NAME   = "CAdxCallingContext";

  final static String HEADER_CODE_LANG    = "codeLang";

  final static String HEADER_CODE_USER    = "codeUser";

  final static String HEADER_PASSWORD     = "password";

  final static String HEADER_POOL_ALIAS   = "poolAlias";

  final static String HEADER_REQUEST_CONF = "requestConfig";
  
  /**
   * @param aHeaderCtx
   * @return
   * @throws Exception
   */
  private SOAPHeaderElement buildHeaderElement(CServiceAdxCallContext aHeaderCtx) throws Exception
  {
    SOAPHeaderElement ctx = new SOAPHeaderElement(NAME_SPACE, HEADER_CLASS_NAME);

    ctx.addChildElement(HEADER_CODE_LANG).addTextNode(aHeaderCtx.getCodeLang());

    ctx.addChildElement(HEADER_CODE_USER).addTextNode(aHeaderCtx.getCodeUser());

    ctx.addChildElement(HEADER_PASSWORD).addTextNode(aHeaderCtx.getPassword());

    ctx.addChildElement(HEADER_POOL_ALIAS).addTextNode(aHeaderCtx.getPoolAlias());

    ctx.addChildElement(HEADER_REQUEST_CONF).addTextNode(aHeaderCtx.getRequestConfig());

    return ctx;
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
	CServiceAdxCallContext getAdxCallContext()
	{
		return pServiceAdxCallContext;
	}
}
