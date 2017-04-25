package com.adonix.tester;

import com.adonix.wsvc.CActivityViewer;
import com.adonix.wsvc.CServiceAdxObjectList;
import com.adonix.wsvc.CServiceAdxCallContext;
import com.adonix.wsvc.CServiceAdxResult;
import com.adonix.wsvc.CServiceAdxKeys;

/**
 * @author Adonix Grenoble
 * 
 */
public class CStubListOaus
{

	private boolean pActivityViewerOn;

	private CActivityViewer pActivityViewer;

	private CServiceAdxObjectList pServiceAdxObjectList;

	/**
	 * @param aActivityViewer
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CStubListOaus(CActivityViewer aActivityViewer, String aUrl, CServiceAdxCallContext aCtx) throws Exception
	{
		pActivityViewerOn = (aActivityViewer != null && aActivityViewer.isTraceOn());
		pActivityViewer = aActivityViewer;

		String wUrl = aUrl + CServiceAdxObjectList.URL_SUBPATH;

		pServiceAdxObjectList = new CServiceAdxObjectList(aActivityViewer, "OAUS", wUrl, aCtx);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new COausListStub ok " + wUrl);
	}

	/**
	 * @param aUserKey
	 * @param aListSize
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult getUsers(String aUserKey, int aListSize) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("getUsers");

		CServiceAdxKeys wKeys = new CServiceAdxKeys(1);
		wKeys.set(0, "1", aUserKey);

		CServiceAdxResult wAdxResultXml = pServiceAdxObjectList.execQuery(wKeys, aListSize);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult getDescription() throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("getDescription");

		CServiceAdxResult wAdxResultXml = pServiceAdxObjectList.execGetDescription();

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}
}
