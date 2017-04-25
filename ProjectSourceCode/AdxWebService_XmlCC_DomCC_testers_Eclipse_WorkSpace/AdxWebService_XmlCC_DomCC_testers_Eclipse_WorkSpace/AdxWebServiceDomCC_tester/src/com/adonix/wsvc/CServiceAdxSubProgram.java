package com.adonix.wsvc;

import com.adonix.wsvc.stubs.CAdxResultDom;

/**
 * @author Adonix Grenoble
 * 
 */
public class CServiceAdxSubProgram extends CServiceAdx
{
	/**
	 * @param aActivityViewer
	 * @param aObjId
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CServiceAdxSubProgram(CActivityViewer aActivityViewer, String aSpgmId, String aUrl, CServiceAdxCallContext aCtx)
			throws Exception
	{
		super(aActivityViewer, aSpgmId, aUrl, aCtx);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new CServiceAdxSubProgram ok");
	}

	/**
	 * @param aDataXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execRun(String aDataXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execSpgm");

		CAdxResultDom wAdxResultDom = pService.run(getAdxCallContext(), getWsSpgmId(), aDataXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execGetDescription() throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("getDescription");

		CAdxResultDom wAdxResultDom = pService.getDescription(getAdxCallContext(), getWsSpgmId());

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append("WsSpgmId=[").append(getWsSpgmId()).append(']');
		wSB.append('\n');
		wSB.append(super.toString());
		return wSB.toString();
	}
}
