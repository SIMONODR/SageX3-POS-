package com.adonix.tester;

import com.adonix.util.CStringFacilities;
import com.adonix.wsvc.CActivityViewer;
import com.adonix.wsvc.CServiceAdxSubProgram;
import com.adonix.wsvc.CServiceAdxCallContext;
import com.adonix.wsvc.CServiceAdxResult;

/**
 * @author Adonix Grenoble
 * 
 */
public class CStubSpgmQlfWsSlp
{

	private CActivityViewer pActivityViewer;

	private boolean pActivityViewerOn;

	private CServiceAdxSubProgram pServiceAdxSubProgram;

	private String QLFWSSLP_DATA_FORMAT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><FLD NAME=\"NBSEC\" >%s</FLD><FLD NAME=\"OK\" >-1</FLD></PARAM>";

	/**
	 * @param aActivityViewer
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CStubSpgmQlfWsSlp(CActivityViewer aActivityViewer, String aUrl, CServiceAdxCallContext aCtx) throws Exception
	{
		pActivityViewerOn = (aActivityViewer != null && aActivityViewer.isTraceOn());
		pActivityViewer = aActivityViewer;

		String wUrl = aUrl + CServiceAdxSubProgram.URL_SUBPATH;

		pServiceAdxSubProgram = new CServiceAdxSubProgram(aActivityViewer, "QLFWSSLP", wUrl, aCtx);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new CQlfWsSlpStub ok " + wUrl);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult getDescription() throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("getDescription");

		CServiceAdxResult wAdxResultXml = pServiceAdxSubProgram.execGetDescription();

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aNbSec
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult sleep(int aNbSec) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("sleep");

		String wValueXml = CStringFacilities.sprintf(QLFWSSLP_DATA_FORMAT, String.valueOf(aNbSec));
		if (pActivityViewerOn)
			pActivityViewer.traceWrite(wValueXml);

		CServiceAdxResult wAdxResultXml = pServiceAdxSubProgram.execRun(wValueXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}
}
