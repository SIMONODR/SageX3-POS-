package com.adonix.tracecli;

/**
 * Implémentation du canal de trace
 * @author Adonix Grenoble
 * @version 1.0
 */

public class CTraceCliChanel implements ITracer
{
	//--------------------------------------------------------------------
	public final static int INFO_ID = 1;
	public final static int INFO_LEVEL = 2;
	public final static int INFO_OPEN = 3;
	/**
	 * Référence sur le cleint de trace
	 */
	private CTraceCli pTraceCli;
	/** 
	 * nom du canal 
	 */
	private String pCanalName;
	/** 
	 * identifiant numérique du canal 
	 */
	private int pCanalID;
	/** 
	 * canal ouvert
	 */
	private boolean pOpened = false;
	/**
	 * gravité supporté: maximal par défaut !
	 */
	private int pLevel = CTraceCli.LEVEL_DEBUG;
	/** 
	 * nom de l'afficheur du canal
	 */
	private String pViewer;
	/**
	 * taille du buffer du canal
	 */
	private int pBufferSize;
	/**
	 * 
	 */
	private ITracer pTracer;
	/**--------------------------------------------------------------------
	 * constructeur explicite
	 * @param aTraceCli
	 * @param aCanalName
	 * @param aLevel
	 * @param aViewer
	 * @param aBufferSize
	 */
	public CTraceCliChanel(CTraceCli aTraceCli, String aCanalName, int aLevel, String aViewer, int aBufferSize)
	{
		pTraceCli = aTraceCli;
		pTracer = aTraceCli.getLocalTracer();
		pCanalName = aCanalName;

		setLevel(aLevel);
		pViewer = aViewer;
		pBufferSize = aBufferSize;

		pCanalID = this.hashCode();
	}
	//--------------------------------------------------------------------
	/** constructeur avec des valeurs par défaut*/
	public CTraceCliChanel(CTraceCli aTraceCli, String aCanalName, int aLevel)
	{
		this(aTraceCli, aCanalName, aLevel, CTraceCli.MERGER_VIEW, 0);
	}
	//--------------------------------------------------------------------
	/** retiurne le nom du canal*/
	public String getCanalName()
	{
		return pCanalName;
	}
	//--------------------------------------------------------------------
	/** retiurne le nom du canal*/
	public String getCanalPrefixName()
	{
		int wPos = pCanalName.indexOf(CTraceCli.CANAL_PREFIX_SEPARATOR);
		if (wPos > -1)
			return pCanalName.substring(0, wPos);
		else
			return pCanalName;
	}
	//--------------------------------------------------------------------
	/** retiurne le nom du canal*/
	public static String buildCanalName(String aCanalName, int aSuffix)
	{
		return new StringBuffer(aCanalName).append(CTraceCli.CANAL_PREFIX_SEPARATOR).append(aSuffix).toString();
	}

	//--------------------------------------------------------------------
	/** retourne l'ID du canal*/
	public int getCanalId()
	{
		return pCanalID;
	}
	//--------------------------------------------------------------------
	/**positionne l'indicateur de canal ouvert*/
	private void setOpened(boolean aOpened)
	{
		pOpened = aOpened;
	}
	//--------------------------------------------------------------------
	/**
	 * retourne vrai si le canal est ouvert
	 */
	public boolean isOpened()
	{
		return pOpened;
	}
	//--------------------------------------------------------------------
	/**
	 * retourne un chaîne contenant la traduction du boolean "vrai" si le canal est ouvert
	 */
	public String isOpenedInString()
	{
		return String.valueOf(pOpened);
	}
	//--------------------------------------------------------------------
	public String getInfo(int aInfoId)
	{
		switch (aInfoId)
		{
			case INFO_ID :
				return pCanalName;
			case INFO_LEVEL :
				return String.valueOf(pLevel);
			case INFO_OPEN :
				return String.valueOf(pOpened);
			default :
				return null;
		}
	}
	//--------------------------------------------------------------------
	public String[] getInfos(int[] aInfoIds)
	{
		int wMax = 0;
		if (aInfoIds != null)
			wMax = aInfoIds.length;
		String[] wInfos = new String[wMax];
		for (int wI = 0; wI < wMax; wI++)
		{
			wInfos[wI] = getInfo(aInfoIds[wI]);
		}
		return wInfos;
	}
	//--------------------------------------------------------------------
	/**
	 * retourne le "level" de trace du canal
	 */
	public int getLevel()
	{
		return pLevel;
	}
	//--------------------------------------------------------------------
	/**
	 * retourne le "level" de trace du canal
	 */
	public String getLevelInString()
	{
		return String.valueOf(pLevel);
	}

	//--------------------------------------------------------------------
	/**positionne le "level" de trace du canal*/
	public void setLevel(int aLevel)
	{
		if ((aLevel >= CTraceCli.LEVEL_MIN) && (aLevel <= CTraceCli.LEVEL_MAX))
			pLevel = aLevel;
	}
	//--------------------------------------------------------------------
	/** conditione la trace en fonction d'un niveau de gravité.
	* <p>si level = LEVEL_ZERO alors trace même si le canal est fermé.</p>*/
	public boolean levelFilter(int aLevel)
	{
		return ((pOpened) && (aLevel <= pLevel) || (aLevel == CTraceCli.LEVEL_ZERO));
	}
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * demande la construction du message d'ajout d'une trame*/
	/*public void addExceptionObj(Object aObj,Throwable e)
	{
	  addExceptionObj(aObj,null,e);
	}
	*/
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * demande la construction du message d'ajout d'une trame
	 */
	/*
	public void addExceptionObj(Object aObj,String aLine,Throwable e)
	{
	    sendMessSecure(pCanalID,0,CTraceCli.TRACE_ADDLINE,CTraceCli.traceFormatException(aObj,aLine,e));
	}
	*/
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * demande la construction du message d'ajout d'une ligne de texte*/
	/*public void addLineObj (Object aObj,int aLevel,String aString)
	{
	  if (levelFilter(aLevel)){
	          sendMessSecure(pCanalID,aLevel,CTraceCli.TRACE_ADDLINE,CTraceCli.traceFormat(aObj,aString));
	  }
	}
	*/
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * demande la construction du message d'ajout d'une ligne de texte*/
	/*public void addLine (int aLevel,String aString)
	{
	  if (levelFilter(aLevel)){
	          sendMessSecure(pCanalID,aLevel,CTraceCli.TRACE_ADDLINE,aString);
	  }
	}
	*/
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * demande la construction du message d'ajout de texte en fin de ligne
	* courante */
	/*
	 * public void addText (int aLevel,String aString)
	{
	  if (levelFilter(aLevel)){
	          sendMessSecure(pCanalID,aLevel,CTraceCli.TRACE_ADDTEXT,aString);
	  }
	}
	*/
	//--------------------------------------------------------------------
	/** demande la construction du message d'ajout d'une trame*/
	public void addTrame(int aLevel, String aString)
	{
		if (levelFilter(aLevel))
		{
			sendMessSecure(pCanalID, aLevel, CTraceCli.TRACE_ADDTRAME, null, new CTraceBuffer(aString));
		}
	}

	//--------------------------------------------------------------------
	/*
	public void test (String aString)
	{
	  if (pOpened){
	          sendMessSecure(pCanalID,CTraceCli.LEVEL_ONE,CTraceCli.TRACE_TEST,aString);
	  }
	}
	*/
	//--------------------------------------------------------------------
	/**
	 * demande la construction du message de test
	 */
	private void sendMessSecure(int aCanalId, int aLevel, char aVerbe, Object aObj, CTraceBuffer aLine)
	{
		pTraceCli.getConns().writeMess(aCanalId, aLevel, aVerbe, aObj, aLine.replaceSpecialTraceChars());
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les n instances d'une sous chaine par une autre chaine.
	 */
	/*static public String replaceCharByStr(String aString,char aWhat, String aBy)
	{
	  if (aString==null) return null;
	  if (aBy==null) return aString;
	  if (aString.indexOf(aWhat)==-1)return aString;
	
	  String wWhat = String.valueOf(aWhat);
	  boolean wStartByWhat = aString.startsWith(wWhat);
	  StringTokenizer wST = new StringTokenizer(aString,wWhat);
	
	  if (wST.countTokens()<=1) return aString; //protrction ???
	
	  int wLen = aString.length();
	  int wDelta = aBy.length()-1;
	  if (wDelta>0)  wLen = wLen + (wST.countTokens()*wDelta);
	  StringBuffer wSB = new StringBuffer(wLen);
	  if (wStartByWhat) wSB.append(aBy);
	  wSB.append(wST.nextToken());
	  while (wST.hasMoreTokens()){
	          wSB.append(aBy).append(wST.nextToken());
	  }
	  return wSB.toString();
	}*/
	//--------------------------------------------------------------------
	/**
	 * formate le message envoyé au serveur de trace.
	 */
	private void sendMess(int aCanalId, int aLevel, char aVerbe, CTraceBuffer aLine)
	{
		int wPos = aLine.indexOf('\n');
		if (wPos > -1)
		{
			///Trace
			pTraceCli.setLocalTraceFileOn(true);
			CTraceBuffer wTBe = pTraceCli.popTraceBuffer();
			wTBe.appendMethodName("sendMess");
			wTBe.appendDescr("WRITE_ERROR", new Exception("Line with '\\n' at index=[" + wPos + ']'));
			pTracer.trace(this, 0, wTBe);
			pTraceCli.setLocalTraceFileOn(false);
			//FTrace*/
		}
		pTraceCli.getConns().writeMess(aCanalId, aLevel, aVerbe, null, aLine);
	}

	/* --------------------------------------------------------------------
	 * @deprecated
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return toStringDescr();
	}
	/**--------------------------------------------------------------------
	 * Retourne une description du canal
	 * @return
	 */
	public String toStringDescr()
	{
		return addInfos(new StringBuffer(256), CTraceCli.INFOS_TEXT).toString();
	}
	//--------------------------------------------------------------------
	/** Retourne une description du canal */
	String toHtml()
	{
		return addInfos(new StringBuffer(256), CTraceCli.INFOS_HTML).toString();
	}
	//--------------------------------------------------------------------
	/** Retourne une description du canal */
	StringBuffer addInfos(StringBuffer aText, int aKind)
	{
		CTraceCli.addInfoInLine(aText, aKind, "name", pCanalName);
		CTraceCli.addInfoInLine(aText, aKind, "level", String.valueOf(pLevel));
		CTraceCli.addInfoInLine(aText, aKind, "opened", String.valueOf(pOpened));
		return aText;
	}

	//--------------------------------------------------------------------
	/** ouverture d'un canal: ne s'exécute que si le canal est fermé*/
	public void openCanal()
	{
		if ((pTraceCli.hasConnOpened()) && (!pOpened))
		{

			// construction du message OPEN en passant les ID du client du canal et le nombre de canaux
			CTraceBuffer wSB = new CTraceBuffer(128);
			wSB.append(pTraceCli.getClientName()); //token 3
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(pCanalName); //token 4
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(pCanalID); //token 5
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(pViewer); //token 6
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(pTraceCli.getNbChanels()); //token 7

			// envoi du message d'ouverture
			sendMess(pCanalID, CTraceCli.LEVEL_ONE, CTraceCli.TRACE_OPEN, wSB);

			// positionne l'indicateur de canal ouvert !
			setOpened(true);
		}
	}
	//--------------------------------------------------------------------
	/** fermeture du canal:  ne s'exécute que si le canal est ouvert.
	* <p>Cete méthode ne doit  pas être appelé directement !!!</p>
	* <p>Il faut utiliser la méthode statique du client de trace :
	* CTracCli.closeCanal(id);</p>
	* */
	public void closeCanal()
	{
		if (pOpened)
		{
			/*//Trace
			pTraceCli.trace(this,new Exception("DUMP STACK"));
			//FTrace*/

			// construction du message CLOSE en passant l'ID du canal et le nombre de canaux
			CTraceBuffer wSB = new CTraceBuffer(128);
			wSB.append(pCanalID); //token 3
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM);
			wSB.append(pTraceCli.getNbChanels() - 1); //token 4

			// envoi du message
			sendMess(pCanalID, CTraceCli.LEVEL_ONE, CTraceCli.TRACE_CLOSE, wSB);

			pOpened = false; // canal fermé !
		}
	}

	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, Throwable e)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, traceFormatException(null, e));
	}
	//-------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, String aS, Throwable e)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, traceFormatException(aS, e));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, StringBuffer aSB, Throwable e)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, traceFormatException(aSB.toString(), e));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, CTraceBuffer aTB, Throwable e)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, traceFormatException(aTB.toString(), e));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(String aS)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, null, new CTraceBuffer(aS));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(StringBuffer aSB)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, null, new CTraceBuffer(aSB.toString()));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, String aS)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, new CTraceBuffer(aS));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, StringBuffer aSB)
	{
		sendMessSecure(pCanalID, 0, CTraceCli.TRACE_ADDLINE, aObj, new CTraceBuffer(aSB.toString()));
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, String aS)
	{
		if (levelFilter(aLevel))
		{
			sendMessSecure(pCanalID, aLevel, CTraceCli.TRACE_ADDLINE, aObj, new CTraceBuffer(aS));
		}
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, StringBuffer aSB)
	{
		if (levelFilter(aLevel))
		{
			sendMessSecure(pCanalID, aLevel, CTraceCli.TRACE_ADDLINE, aObj, new CTraceBuffer(aSB.toString()));
		}
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, CTraceBuffer aST)
	{
		if (levelFilter(aLevel))
		{
			sendMessSecure(pCanalID, aLevel, CTraceCli.TRACE_ADDLINE, aObj, aST);
		}
	}
	//--------------------------------------------------------------------
	/**
	 * Tranforme l'erreur "e" en texte préfixé ou non par la chaine "aString"..
	 */
	private CTraceBuffer traceFormatException(String aString, Throwable e)
	{
		CTraceBuffer wTB = new CTraceBuffer(1024);

		if (aString != null)
			wTB.append(aString).append('\n');

		wTB.append(CTraceCli.TRACE_EXCEPTION_LIB);

		wTB.appendThrowableDescr(e);

		return wTB;
	}
	//--------------------------------------------------------------------
	/**
	 * conditione la trace en fonction d'un niveau de gravité.
	 * <p>si level = LEVEL_ZERO alors trace même si le canal est fermé.</p>
	 */
	public boolean traceLevelFilter(int aLevel)
	{
		return ((pOpened) && (aLevel <= pLevel) || (aLevel == CTraceCli.LEVEL_ZERO));
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#traceModuleFilter(java.lang.String)
	 */
	public boolean traceModuleFilter (String aModuleName)
	{
		return pTraceCli.traceModuleFilter(aModuleName);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double startTimer()
	{
		return pTraceCli.startTimer();
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double stopTimer()
	{
		return pTraceCli.stopTimer();
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double getDuration()
	{
		return pTraceCli.getDuration();
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public CTraceBuffer popTraceBuffer()
	{
		return pTraceCli.popTraceBuffer();
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void pushTraceBuffer(CTraceBuffer aTraceBuffer)
	{
		pTraceCli.pushTraceBuffer(aTraceBuffer);
	}
}
