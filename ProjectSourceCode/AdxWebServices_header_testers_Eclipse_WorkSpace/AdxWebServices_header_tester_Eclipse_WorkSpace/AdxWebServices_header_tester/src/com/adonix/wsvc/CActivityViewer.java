package com.adonix.wsvc;

import com.adonix.util.CStringFacilities;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author Adonix Grenoble
 * 
 */
public abstract class CActivityViewer
{

	private static int SIZEMAX_LINE = 100;

	private static CActivityViewer sPostActivityViewer = null;

	/**
	 * @return
	 */
	public static CActivityViewer getPostFormActivityViewer()
	{
		return sPostActivityViewer;
	}

	/**
	 * @param aActivityViewer
	 */
	public static void setPostFormActivityViewer(CActivityViewer aActivityViewer)
	{
		sPostActivityViewer = aActivityViewer;
	}

	private int pSizeLine = SIZEMAX_LINE;

	private Stack pStackStepLine = new Stack();

	/**
	 * 
	 */
	public CActivityViewer()
	{
	}

	public abstract boolean isTraceOn();

	/**
	 * @param aSizeLine
	 */
	public void setSizeLine(int aSizeLine)
	{
		pSizeLine = aSizeLine;
	}

	/**
	 * 
	 */
	public void traceBeginStep()
	{
		traceBeginStep(null);
	}

	/**
	 * @param aLine
	 */
	public void traceBeginStep(String aLine)
	{
		if (aLine == null)
		{
			aLine = "-";
		}
		traceWrite("Begin " + aLine);
		pStackStepLine.push(aLine);
	}

	/**
	 * 
	 */
	public void traceEndStep()
	{
		if (pStackStepLine.size() > 0)
		{
			String wLine = (String) pStackStepLine.pop();
			traceWrite("End " + wLine);
		}
	}

	/**
	 * @param aLine
	 */
	public void traceWrite(String aLine)
	{
		StringTokenizer wST = new StringTokenizer(aLine, "\n");
		while (wST.hasMoreTokens())
		{
			traceWriteLine(wST.nextToken());
		}
	}

	/**
	 * @param aId
	 * @param aValue
	 */
	public void traceWriteDescr(String aId, String aValue)
	{
		traceWrite(aId + "=[" + aValue + ']');
	}

	/**
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public void traceWriteDescr(String aId, String aValue, String aValueB)
	{
		traceWriteDescr(aId, aValue + " | " + aValueB);
	}

	/**
	 * @param aLine
	 */
	public void traceWriteLine(String aLine)
	{
		int wNbChar = pStackStepLine.size() * 2;
		String wPrefix = CStringFacilities.buildStringFromChar(' ', wNbChar);

		int wMaxLen = pSizeLine - wPrefix.length();
		if (aLine.length() <= wMaxLen)
		{
			writeLine(wPrefix + aLine);
		}
		else
		{
			int wMax = aLine.length();
			int wPos = 0;
			int wLen = 0;
			while (wPos < wMax)
			{
				wLen = (wMax - wPos > wMaxLen) ? wMaxLen : wMax - wPos;
				writeLine(wPrefix + aLine.substring(wPos, wPos + wLen));
				wPos += wMaxLen;
			}
		}
	}

	public abstract void writeLine(String aLine);
}
