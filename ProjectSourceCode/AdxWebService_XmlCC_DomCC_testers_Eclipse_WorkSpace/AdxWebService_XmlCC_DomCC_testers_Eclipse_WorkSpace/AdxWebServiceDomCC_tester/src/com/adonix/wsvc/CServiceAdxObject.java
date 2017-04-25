package com.adonix.wsvc;


import com.adonix.wsvc.stubs.CAdxResultDom;


public class CServiceAdxObject extends CServiceAdx
{
	

	/**
	 * @param aActivityViewer
	 * @param aObjId
	 * @param aUrl
	 * @param aCtx
	 * @throws Exception
	 */
	public CServiceAdxObject(CActivityViewer aActivityViewer, String aObjId, String aUrl, CServiceAdxCallContext aCtx)
			throws Exception
	{
		super(aActivityViewer, aObjId, aUrl, aCtx);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new CServiceAdxObject ok");
	}

	/**
	 * @param actionCode
	 * @param aObjectKeys
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execActionObject(String actionCode, CServiceAdxKeys aObjectKeys) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execActionObject");

		CAdxResultDom wAdxResultDom = pService.actionObject(getAdxCallContext(), getWsObjId(), actionCode, aObjectKeys
				.getAdxParamsKeys());

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param actionCode
	 * @param objectXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execActionObject(String actionCode, String objectXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execActionObject");

		CAdxResultDom wAdxResultDom = pService.actionObject(getAdxCallContext(), getWsObjId(), actionCode, objectXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param aObjectKeys
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execDelete(CServiceAdxKeys aObjectKeys) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execDelete");

		CAdxResultDom wAdxResultDom = pService.delete(getAdxCallContext(), getWsObjId(), aObjectKeys.getAdxParamsKeys());

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param aObjectKeys
	 * @param blocKey
	 * @param lineKey
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execDeleteLines(CServiceAdxKeys aObjectKeys, String blocKey, String[] lineKey)
			throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execInsertLines");

		CAdxResultDom wAdxResultDom = pService.deleteLines(getAdxCallContext(), getWsObjId(), aObjectKeys.getAdxParamsKeys(),
				blocKey, lineKey);

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
			pActivityViewer.traceBeginStep("execGetDescription");

		CAdxResultDom wAdxResultDom = pService.getDescription(getAdxCallContext(), getWsObjId());

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param aObjectKeys
	 * @param blocKey
	 * @param lineKey
	 * @param lineXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execInsertLines(CServiceAdxKeys aObjectKeys, String blocKey, String lineKey, String lineXml)
			throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execInsertLines");

		CAdxResultDom wAdxResultDom = pService.insertLines(getAdxCallContext(), getWsObjId(), aObjectKeys.getAdxParamsKeys(),
				blocKey, lineKey, lineXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param aObjectKeys
	 * @param objectXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execModify(CServiceAdxKeys aObjectKeys, String objectXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execModify");

		CAdxResultDom wAdxResultDom = pService.modify(getAdxCallContext(), getWsObjId(), aObjectKeys.getAdxParamsKeys(),
				objectXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param aObjectKeys
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execRead(CServiceAdxKeys aObjectKeys) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execRead");

		CAdxResultDom wAdxResultDom = pService.read(getAdxCallContext(), getWsObjId(), aObjectKeys.getAdxParamsKeys());

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}

	/**
	 * @param objectXml
	 * @return
	 * @throws Exception
	 */
	public CServiceAdxResult execSave(String objectXml) throws Exception
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("execSave");

		CAdxResultDom wAdxResultDom = pService.save(getAdxCallContext(), getWsObjId(), objectXml);

		if (pActivityViewerOn)
			pActivityViewer.traceEndStep();
		return new CServiceAdxResult(wAdxResultDom);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append("WsObjId=[").append(getWsObjId()).append(']');
		wSB.append('\n');
		wSB.append(super.toString());
		return wSB.toString();
	}

}
