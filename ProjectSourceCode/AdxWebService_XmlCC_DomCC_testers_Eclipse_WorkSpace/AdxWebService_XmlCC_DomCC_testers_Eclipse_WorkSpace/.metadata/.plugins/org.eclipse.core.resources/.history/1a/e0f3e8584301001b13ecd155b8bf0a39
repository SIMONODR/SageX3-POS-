package com.adonix.wsvc;

import com.adonix.wsvc.stubs.CAdxResultXml;

/**
 * @author Adonix Grenoble
 *
 */
public class CServiceAdxSubProgram  extends CServiceAdx
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
  public CServiceAdxResult execRun(String aDataXml)throws Exception
	{
		if (pActivityViewerOn)pActivityViewer.traceBeginStep("execSpgm");

          CAdxResultXml wAdxResultXml = pService.run(getAdxCallContext(), getWsSpgmId(), aDataXml);
	
		if (pActivityViewerOn)pActivityViewer.traceEndStep();
          return new CServiceAdxResult(wAdxResultXml);
	}

      /**
       * @return
       * @throws Exception
       */
      public CServiceAdxResult execGetDescription() throws Exception
		{
			if (pActivityViewerOn)pActivityViewer.traceBeginStep("getDescription");

              CAdxResultXml wAdxResultXml = pService.getDescription(getAdxCallContext(), getWsSpgmId());

			if (pActivityViewerOn)pActivityViewer.traceEndStep();
              return new CServiceAdxResult(wAdxResultXml);
		}

		/* (non-Javadoc)
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
