package com.adonix.tracecli;

import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Implémentation de la liste des connexion du client de trace
 * @author Adonix Grenoble
 * @version 1.0
 */

public class CTraceCliChanels extends  Hashtable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3761124942426419506L;
	//--------------------------------------------------------------------
	/**
	 * référence sur le tracer associé au client de trace
	 */
	private ITracer pTracer;
	/**
	 * référence sur le client de trace
	 */
	private CTraceCli pTraceCli;
	//--------------------------------------------------------------------
	CTraceCliChanels(ITracer aTracer,CTraceCli aTraceCli)
	{
		super();
		pTracer = aTracer;
		pTraceCli = aTraceCli;
		///Trace
		if (pTracer.traceLevelFilter(ITracer.LEVEL_DEBUG)){
			CTraceBuffer wTB = new CTraceBuffer(128).appendMethodName(ITracer.CONSTRUCTOR);
			wTB.appendDescrRightAligned("size ",this.size(),5);
			pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
		}
		//FTrace*/
	}
	//--------------------------------------------------------------------
	StringBuffer addInfos(StringBuffer aText,int aKind)
	{
		CTraceCli.addInfoInLine(aText,aKind,"NbChanels",String.valueOf(size()));

		Enumeration wElems = elements();
		int wI=0;
		while (wElems.hasMoreElements()){
			if (aKind==CTraceCli.INFOS_HTML) aText.append("<li>");
			wI++;
			aText.append("Chanel [").append(wI).append(']');
			if (aKind==CTraceCli.INFOS_HTML) aText.append("</li>");
			if (aKind==CTraceCli.INFOS_HTML) aText.append("<ul>");
			((CTraceCliChanel)wElems.nextElement()).addInfos(aText,aKind);
			if (aKind==CTraceCli.INFOS_HTML) aText.append("</ul>");
		}

		return aText;
	}
	
}
