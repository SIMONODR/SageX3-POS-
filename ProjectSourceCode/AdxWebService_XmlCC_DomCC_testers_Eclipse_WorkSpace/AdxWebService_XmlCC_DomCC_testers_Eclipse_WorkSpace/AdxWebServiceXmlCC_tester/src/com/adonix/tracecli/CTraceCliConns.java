package com.adonix.tracecli;

import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Implémentation de la liste des connexion du client de trace
 * @author Adonix Grenoble
 * @version 1.0
 */
class CTraceCliConns extends Hashtable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3258415044868911410L;
	//--------------------------------------------------------------------
	/**
	 * référence sur la première connexion
	 */
	private CTraceCliConn pFirstTraceCliConn;
	/**
	 * référence sur le tracer associé au client de trace
	 */
	private ITracer pTracer;
	/**
	 * référence sur le client de trace
	 */
	private CTraceCli pTraceCli;
	//--------------------------------------------------------------------
	CTraceCliConns(ITracer aTracer,CTraceCli aTraceCli)
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
		CTraceCli.addInfoInLine(aText,aKind,"NbConnections",String.valueOf(size()));
		Enumeration wElems = elements();
		int wI=0;
		while (wElems.hasMoreElements()){
			if (aKind==CTraceCli.INFOS_HTML) aText.append("<li>");
			wI++;
			aText.append("connection [").append(wI).append(']');
			if (aKind==CTraceCli.INFOS_HTML) aText.append("</li>");
			if (aKind==CTraceCli.INFOS_HTML) aText.append("<ul>");
			 ((CTraceCliConn)wElems.nextElement()).addInfos(aText,aKind);
			if (aKind==CTraceCli.INFOS_HTML) aText.append("</ul>");
		}
		return aText;
	}
	//--------------------------------------------------------------------
	void addOneConn(String aHost,int aPort)
	{
		///Trace
		if (pTraceCli.isLocalTraceFileOn()){
			CTraceBuffer wTB = pTraceCli.popTraceBuffer();
			wTB.appendMethodName("addOneConn");
			wTB.appendDescr("aHost",aHost);
			wTB.appendDescr("aPort",aPort);
			pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
			pTraceCli.pushTraceBuffer(wTB);
		}
		//FTrace*/
		
		if ((aHost!=null)&&(aPort>0)){
			String wKey = buildConnKey(aHost,aPort);
			if (!containsKey(wKey)){
				CTraceCliConn wConn = new CTraceCliConn(pTraceCli,wKey,aHost,aPort);
				if (wConn.isOpened())
					this.put(wKey,wConn);
			}
		}
	}
	//--------------------------------------------------------------------
	/** ouverture de la socket avec le serveur */
	static String buildConnKey(String aHost,int aPort)
	{
		return new StringBuffer(32).append(aHost).append(':').append(String.valueOf(aPort)).toString();
	}
	//--------------------------------------------------------------------
	public void clear()
	{
		super.clear();
		pFirstTraceCliConn = null;
	}
	//--------------------------------------------------------------------
	/**
	 *  fermeture de toutes les connexions
	 */
	void closeAllConns()
	{
		//supprime l'instance de connexion si elle existe
		if (!isEmpty()) {
			Enumeration wConns = elements();
			while(wConns.hasMoreElements()){
				closeOneConn((CTraceCliConn)wConns.nextElement());
			}
		}
	}
	//--------------------------------------------------------------------
	/**
	 * fermeture d'une connexions avec le serveur Host + port.
	 */
	void closeOneConn(String aHost,int aPort)
	{
		if ((aHost!=null)&&(aPort>0)){
			String wKey = buildConnKey(aHost,aPort);
			
			closeOneConn( (CTraceCliConn)super.get(wKey) );
		}
	}
	//--------------------------------------------------------------------
	/**
	 * fermeture d'une connexions .
	 */
	private void closeOneConn(CTraceCliConn aTraceCliConn)
	{
		///Trace
		if (pTraceCli.isLocalTraceFileOn()){
			CTraceBuffer wTB = pTraceCli.popTraceBuffer();
			wTB.appendMethodName("closeOneConn");
			wTB.appendDescr("Key",((aTraceCliConn!=null)?aTraceCliConn.getName():"???"));
			pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
			pTraceCli.pushTraceBuffer(wTB);
		}
		//FTrace*/
		
		if (aTraceCliConn!=null)
		{
			aTraceCliConn.end();
			remove(aTraceCliConn.getKey());
		}
		//si la connexion que l'on vient de fermer était la première
		if (aTraceCliConn == pFirstTraceCliConn)
		{
			// s'il n'y a plus de connexion
			if (isEmpty()){
				pFirstTraceCliConn = null;
			}
			else{
				pFirstTraceCliConn = (CTraceCliConn)this.elements().nextElement();
			}
		}
	}
	//--------------------------------------------------------------------
	boolean hasDefaultConnOpened(String aDefaultHost,int aDefaultPort)
	{
		return (! isEmpty() && containsKey(CTraceCliConns.buildConnKey(aDefaultHost,aDefaultPort)));
	}
	//--------------------------------------------------------------------
	CTraceCliConn getFirstTraceCliConn()
	{
		return pFirstTraceCliConn;
	}
	//--------------------------------------------------------------------
	CTraceCliConn put(String aKey,CTraceCliConn aTraceCliConn)
	{
		if (pFirstTraceCliConn ==null) pFirstTraceCliConn = aTraceCliConn;
		return (CTraceCliConn) super.put(aKey,aTraceCliConn);
	}
	//--------------------------------------------------------------------
	/**méthode appelée par les canaux pour envoyer les message à tous
	 * les serveurs connectés
	 */
	void writeMess(int aCanalId,int aLevel,char aVerbe,Object aObjet,CTraceBuffer aLine)
	{
		if (size()==1){
			pFirstTraceCliConn.writeMess(aCanalId,aLevel,aVerbe,aObjet,aLine);
		}
		else{
			Enumeration wConns = elements();
			while(wConns.hasMoreElements()){
				((CTraceCliConn)wConns.nextElement()).writeMess(aCanalId,aLevel,aVerbe,aObjet,aLine);
			}
		}
	}
}
