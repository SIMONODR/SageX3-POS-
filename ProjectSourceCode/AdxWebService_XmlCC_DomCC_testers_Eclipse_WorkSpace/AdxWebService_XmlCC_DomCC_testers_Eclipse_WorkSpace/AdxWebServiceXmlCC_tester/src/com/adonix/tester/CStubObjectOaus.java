package com.adonix.tester;

import com.adonix.wsvc.CActivityViewer;
import com.adonix.wsvc.CServiceAdxObject;
import com.adonix.wsvc.CServiceAdxCallContext;
import com.adonix.wsvc.CServiceAdxResult;
import com.adonix.wsvc.CServiceAdxKeys;

/**
 * @author Adonix Grenoble
 * 
 */
public class CStubObjectOaus
{
	private CActivityViewer pActivityViewer;

	private boolean pActivityViewerOn;

	private CServiceAdxObject pServiceAdxObject;

	/**
	 * @param aActivityViewer
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CStubObjectOaus(CActivityViewer aActivityViewer, String aUrl, CServiceAdxCallContext aCtx) throws Exception
	{
		pActivityViewerOn = (aActivityViewer != null && aActivityViewer.isTraceOn());
		pActivityViewer = aActivityViewer;

		String wUrl = aUrl + CServiceAdxObject.URL_SUBPATH;

		pServiceAdxObject = new CServiceAdxObject(aActivityViewer, "OAUS", wUrl, aCtx);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new CStubObjectOaus ok " + wUrl);
	}

	/**
	 * @param aUserId
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult actionTestOnUser(String aUserId) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("actionTestOnUser");

		com.adonix.wsvc.CServiceAdxKeys wKeys = new com.adonix.wsvc.CServiceAdxKeys(1);
		wKeys.set(0, "1", aUserId);

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execActionObject("£", wKeys);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aDataXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult actionTestOnUserWithData(String aDataXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("modifyUser");

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execActionObject("£", aDataXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aUserId
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult deleteUser(String aUserId) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("deleteUser");

		com.adonix.wsvc.CServiceAdxKeys wKeys = new com.adonix.wsvc.CServiceAdxKeys(1);
		wKeys.set(0, "1", aUserId);

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execDelete(wKeys);

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
		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execGetDescription();
		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aUserId
	 * @param aDataXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult modifyUser(String aUserId, String aDataXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("modifyUser");

		CServiceAdxKeys wKeys = new CServiceAdxKeys(1);
		wKeys.set(0, "1", aUserId);

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execModify(wKeys, aDataXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aUserId
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult readUser(String aUserId) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("readUser");

		CServiceAdxKeys wKeys = new CServiceAdxKeys(1);
		wKeys.set(0, "1", aUserId);

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execRead(wKeys);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}

	/**
	 * @param aDataXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult saveUser(String aDataXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("saveUser");

		CServiceAdxResult wAdxResultXml = pServiceAdxObject.execSave(aDataXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return wAdxResultXml;
	}
}
