package com.adonix.tester;


import com.adonix.tracecli.ITracer;
import com.adonix.wsvc.CActivityViewer;

/**
 * @author Adonix Grenoble
 * 
 */
public class CActivityViewerTraceCli extends CActivityViewer 
{
	private ITracer pTracer;

	/*
	 * instancie un viewer "tracecli"
	 */
	public CActivityViewerTraceCli(ITracer aTracer)
	{
		pTracer = aTracer;
	}
	public void  writeLine(String aLine)
	{
		pTracer.trace(this,aLine);
	}
	public  boolean isTraceOn()
	{
		return pTracer.traceLevelFilter(1);
	}
}
