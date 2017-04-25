package com.adonix.tester;

import com.adonix.wsvc.CActivityViewer;

/**
 * @author Adonix Grenoble
 * 
 */
public class CTesterArguments
{
	private CActivityViewer pActivityViewer;

	private boolean pActivityViewerOn;

	public String pTraceHost = "localhost";

	public int pTraceLevel = 9;

	public boolean pTraceOn = false;

	public int pTracePort = 1515;

	public boolean pViewActivityOn = true;

	public boolean pViewSoapOn = false;

	public String pWsvcAlias = "X3";

	public String pWsvcConfig = "adxwss.trace.on=on&amp;adxwss.trace.size=16384&amp;adonix.trace.on=on&amp;adonix.trace.level=3&amp;adonix.trace.size=8";

	public String pWsvcHost = "localhost";

	public String pWsvcLang = "FRA";

	public String pWsvcPass = "";

	public String pWsvcPath = "adxwsvc";

	public String pWsvcUser = "ADMIN";

	/**
	 * @param aActivityViewer
	 * @param aArguments
	 */
	public CTesterArguments(CActivityViewer aActivityViewer, String[] aArguments)
	{
		pActivityViewer = aActivityViewer;
		pActivityViewerOn = (pActivityViewer != null && pActivityViewer.isTraceOn());

		initTesterArguments(aArguments);

		if (pActivityViewerOn)
			pActivityViewer.traceWrite("new CTesterArguments ok");

	}

	/**
	 * @param aArguments
	 */
	private void initTesterArguments(String[] aArguments)
	{
		if (pActivityViewerOn)
			pActivityViewer.traceBeginStep("initTesterArguments");

		int wMax = aArguments.length;
		String wCurrentState = null;
		String wArg;
		for (int i = 0; i < wMax; i++)
		{
			wArg = aArguments[i];
			if (wArg.startsWith("-"))
			{
				wCurrentState = wArg;
			}
			else
			{
				// -wsvchost gattazmpro:9080 -wsvcpath adxwsvc
				if ("-wsvchost".equals(wCurrentState))
				{
					pWsvcHost = wArg;
				}
				else if ("-wsvcpath".equals(wCurrentState))
				{
					pWsvcPath = wArg;
				}
				// -wsvclang FRA -wsvcuser OG -wsvcpass none -wsvcalias X3TEST130
				// -wsvcconfig
				else if ("-wsvclang".equals(wCurrentState))
				{
					pWsvcLang = wArg;
				}
				else if ("-wsvcuser".equals(wCurrentState))
				{
					pWsvcUser = wArg;
				}
				else if ("-wsvcpass".equals(wCurrentState))
				{
					if ("none".equals(wArg))
						wArg = "";
					pWsvcPass = wArg;
				}
				else if ("-wsvcalias".equals(wCurrentState))
				{
					pWsvcAlias = wArg;
				}
				else if ("-wsvcconfig".equals(wCurrentState))
				{
					pWsvcConfig = wArg;
				}
				// -viewactivityon on -viewsoapon on
				else if ("-viewactivityon".equals(wCurrentState))
				{
					pViewActivityOn = ("on".equals(wArg));
				}
				else if ("-viewsoapon".equals(wCurrentState))
				{
					pViewSoapOn = ("on".equals(wArg));
				}
				// -traceon on -tracehost gattampro - traceport 1515 -tracelevel 9
				else if ("-traceon".equals(wCurrentState))
				{
					pTraceOn = ("on".equals(wArg));
				}
				else if ("-tracehost".equals(wCurrentState))
				{
					pTraceHost = wArg;
				}
				else if ("-traceport".equals(wCurrentState))
				{
					pTracePort = Integer.parseInt(wArg);
				}
				else if ("-tracehost".equals(wCurrentState))
				{
					pTraceLevel = Integer.parseInt(wArg);
				}
				wCurrentState = null;
			}
		}

		if (pActivityViewerOn)
		{
			pActivityViewer.traceWrite(toStringDescr());
			pActivityViewer.traceEndStep();
		}

	}

	/**
	 * @return
	 */
	public String toStringDescr()
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append("WsvcHost=[").append(pWsvcHost).append(']');
		wSB.append('\n');
		wSB.append("WsvcPath=[").append(pWsvcPath).append(']');

		wSB.append('\n');
		wSB.append("WsvcLang=[").append(pWsvcLang).append(']');
		wSB.append('\n');
		wSB.append("WsvcUser=[").append(pWsvcUser).append(']');
		wSB.append('\n');
		wSB.append("WsvcPass=[").append(pWsvcPass).append(']');
		wSB.append('\n');
		wSB.append("WsvcAlias=[").append(pWsvcAlias).append(']');
		wSB.append('\n');
		wSB.append("WsvcConfig=[").append(pWsvcConfig).append(']');

		wSB.append('\n');
		wSB.append("TraceOn=[").append(pTraceOn).append(']');
		wSB.append('\n');
		wSB.append("TraceHost=[").append(pTraceHost).append(']');
		wSB.append('\n');
		wSB.append("TracePort=[").append(pTracePort).append(']');
		wSB.append('\n');
		wSB.append("TraceLevel=[").append(pTraceLevel).append(']');
		return wSB.toString();
	}
}
