package com.adonix.wsvc;

public class CActivityViewerConsole extends CActivityViewer
{

	/*
	 * instancie un viewer "console"
	 */
	public CActivityViewerConsole()
	{
		super();
	}

	public void writeLine(String aLine)
	{
		System.out.println(aLine);
	}

	public boolean isTraceOn()
	{
		return true;
	}

}
