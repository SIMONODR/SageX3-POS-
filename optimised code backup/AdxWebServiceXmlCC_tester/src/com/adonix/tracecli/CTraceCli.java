package com.adonix.tracecli;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Implémentation du client de l'outillage de trace
 * @author Adonix Grenoble
 * @version 1.0
 */

public class CTraceCli implements ITracer
{
	//--------------------------------------------------------------------
	final public static String TRACE_VERSION = "20021113";
	final public static String TRACE_ON = "on";
	final public static String TRACE_OFF = "off";
	final public static String DEFAULT_SERVER = "localhost";
	final public static int DEFAULT_PORT = 1515;

	final public static int INFO_ID = 1;
	final public static int INFO_OPEN = 2;
	final public static int INFO_DEFAULT_HOST = 3;
	final public static int INFO_DEFAULT_PORT = 4;
	final public static int INFO_NB_CONNS = 5;
	final public static int INFO_NB_CHANELS = 6;

	/**
	 * Viewer standard du serveur de trace (mode debug)
	 */
	final public static String MERGER_VIEW = "VIEW";
	/**
	 * Viewer "File" du serveur de trace (mode exploitation)
	 */
	final public static String MERGER_FILE = "FILE";

	final public static int CANAL_UNKNOWN = 0;
	final public static char CANAL_PREFIX_SEPARATOR = '_';

	final public static int NB_ASK_TIME_REF = 10;
	final public static int TIMEOUT_SOCKET = 500;

	final public static char TRACE_PROTOCOLE_DELIM = '|';
	final public static char TRACE_LINE_DELIM = '§';
	final public static char TRACE_CR_DELIM = '£';

	final public static char TRACE_TEXT_DELIM = '>';
	final public static char TRACE_TIME_DELIM = ':';
	final public static char TRACE_MILL_DELIM = '~';

	final public static char TRACE_OBJ_FLAG = '¨';
	final public static String TRACE_OBJ_DELIM = "^^";

	final public static String TRACE_EXCEPTION_LIB = "ERROR\n";

	final public static char TRACE_INSTANCE_DELIM = ':';

	final public static char TRACE_QUIT = 'Q'; //"QU";
	final public static char TRACE_OPEN = 'O'; //"OP";
	final public static char TRACE_ADDTEXT = 'T'; //"AT";
	final public static char TRACE_ADDLINE = 'L'; //"AL";
	final public static char TRACE_ADDTRAME = 'B'; //"TR";
	final public static char TRACE_NEWLINE = 'N'; //"NL";
	final public static char TRACE_CLOSE = 'C'; //"CL";
	final public static char TRACE_TIME = 'I'; //"TI";
	final public static char TRACE_TIMEINFOS = 'F'; //"IN";
	final public static char TRACE_TEST = 'Z'; //"TE";
	final public static char TRACE_FROM = 'R'; //"FR";
	final public static char TRACE_PARAMETERS = 'P'; //"PR";

	final public static int TRACE_RED_D = 0; // 128,000,000
	final public static int TRACE_GREEN_D = 1; // 000,128,000
	final public static int TRACE_BLUE_D = 2; // 000,000,128
	final public static int TRACE_VIN = 3; // 128,000,128
	final public static int TRACE_BLUE_V = 4; // 000,128,128
	final public static int TRACE_BROWN = 5; // 128,128,000
	final public static int TRACE_GREY = 6; // 128,128,128
	final public static int TRACE_RED = 7; // 255,000,000
	final public static int TRACE_GREEN = 8; // 000,255,000
	final public static int TRACE_BLUE = 9; // 000,000,255
	final public static int TRACE_ROSE = 10; // 255,000,255
	final public static int TRACE_BLUE_L = 11; // 000,255,255
	final public static int TRACE_YELLO = 12; // 255,255,000
	final public static int TRACE_GREY_L = 13; // 192,192,192
	final public static int TRACE_WHITE = 14; // 128,128,128
	final public static int TRACE_BLACK = 15; // 000,000,000
	final public static int TRACE_MAX_COLOR = 16;

	static final int INFOS_TEXT = 1;
	static final int INFOS_HTML = 2;

	/**
	 * Le nom de l'application cliente
	 * */
	static private final String NO_NAME = "???";
	private String pClientName;
	private String pInstanceId = null;
	/**
	 * table des connexion(s) au(x) serveur(s) de trace
	 */
	private CTraceCliConns pConns;
	/**
	 * table des canaux ouverts dans l'application cliente
	 */
	private CTraceCliChanels pChanels;
	/**
	 * Flag général
	 */
	private boolean pTraceOn = false;
	/** 
	 * Le client de trace a-t-il une connexion par défaut
	 */
	private boolean pHasDefaultConn = false;
	/**
	 * Host serveur de trace
	 */
	private String pDefaultHost = null;
	/**
	 * port du serveur de trace
	 */
	private int pDefaultPort = 0;
	/**
	 * référence sur le canal par défaut :
	 * le premier ouvert, puis le premier de la table si il est fermé
	 */
	private CTraceCliChanel pDefaultCanal = null;

	private CTracerLocal pTraceLocal;

	private CTraceBuffers pTraceBuffers;

	// create an ITimer using the Factory class:
	private ITraceTimer pTimer = null;

	private Hashtable pModuleFilters = new Hashtable();
  /*
   * 14w_006 - Intégration TermServer - Contrôle Trace
   */
  private int pDefaultLevel = 0;

	/**--------------------------------------------------------------------
	 * Création d'un client de trace
	 * 
	 * @param aClientName
	 * @param aTraceOn
	 * @param aDefaultHost
	 * @param aDefaultPort
	 */
	public CTraceCli(String aClientName, boolean aTraceOn, String aDefaultHost, int aDefaultPort)
	{
		pClientName = aClientName;

		setTraceOn(aTraceOn);

		pTraceLocal = new CTracerLocal(aClientName);

		pTraceBuffers = new CTraceBuffers(pTraceLocal);

		pTimer = new CTraceTimer();

		pConns = new CTraceCliConns(pTraceLocal, this);

		pChanels = new CTraceCliChanels(pTraceLocal, this);

		setDefaultConn(aDefaultHost, aDefaultPort);
	}
	/**--------------------------------------------------------------------
	 * Création d'un client de trace avec un canal par un premier canal de niveau "aLevel"
	 * 
	 * @param aClientName
	 * @param aTraceOn
	 * @param aDefaultHost
	 * @param aDefaultPort
	 * @param aLevel
	 */
	public CTraceCli(String aClientName, boolean aTraceOn, String aDefaultHost, int aDefaultPort, int aDefaultLevel)
	{
		this(aClientName, aTraceOn, aDefaultHost, aDefaultPort);
    /*
     * 14w_006 - Intégration TermServer - Contrôle Trace
     */
    pDefaultLevel = aDefaultLevel;
    newChanel(aTraceOn, aClientName, aDefaultLevel);
	}
	/**--------------------------------------------------------------------
	 * @param aInstanceId
	 */
	public void setInstanceId(String aInstanceId)
	{
		pInstanceId = aInstanceId;
	}
  /**
   * 14w_006 - Intégration TermServer - Contrôle des traces
   * @return
   */
  public String getInstanceId()
  {
    return pInstanceId;
  }
  /**
   * 14w_006 - Intégration TermServer - Contrôle des traces
   * @return
   */
  public boolean hasInstanceId()
  {
    return (pInstanceId!=null);
  }
	/**--------------------------------------------------------------------
	 * Active la trace locale du client de trace !
	 * @param aTraceFileOn
	 */
	public void setLocalTraceFileOn(boolean aTraceFileOn)
	{
		pTraceLocal.setTraceFileOn(aTraceFileOn);
	}
	/**--------------------------------------------------------------------
	 * @param aModuleName
	 * @param aFilter
	 */
	public void setModuleFilter(String aModuleName, boolean aFilter)
	{
		pModuleFilters.put(aModuleName, new Boolean(aFilter));
	}
	//--------------------------------------------------------------------
	public void setTraceOn(boolean aTraceOn)
	{
		pTraceOn = aTraceOn;
	}
	/**--------------------------------------------------------------------
	 * @version 142
	 */
	public void setTraceOn()
	{
		setTraceOn(true);
	}
	/**--------------------------------------------------------------------
	 * @version 142
	 */
	public void setTraceOff()
	{
		setTraceOn(false);
	}
	/**--------------------------------------------------------------------
	 * @return
	 * @version 142
	 */
	public boolean isTraceOn()
	{
		return pTraceOn;
	}
	//--------------------------------------------------------------------
	public void setTraceTimer(ITraceTimer aTimer)
	{
		pTimer = aTimer;
	}
	//--------------------------------------------------------------------
	/**  */
	public void setDefaultConn(String aDefaultHost, int aDefaultPort)
	{
		if (!hasConnOpened())
		{
			pHasDefaultConn = ((aDefaultHost != null) && (aDefaultPort > 0));
			if (pHasDefaultConn)
			{
				pDefaultHost = aDefaultHost;
				pDefaultPort = aDefaultPort;
			}
		}
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String getDefaultHost()
	{
		return pDefaultHost;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public int getDefaultPort()
	{
		return pDefaultPort;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public CTraceCliChanel getDefaultChanel()
	{
		if (hasDefaultChanel())
		{
			return pDefaultCanal;
		}
		else
		{
			{
				if (pDefaultCanal == null)
				{
					pTraceLocal.trace(this, 9, ".addTextInChanel: no default canal !");
				}
				return null;
			}
		}
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public boolean hasDefaultChanel()
	{
		return (hasConnOpened() && pDefaultCanal != null);
	}
	//--------------------------------------------------------------------
	/**
	 * 
	 */
	public ITracer getDefaultTracer()
	{
		return getDefaultChanel();
	}

	//--------------------------------------------------------------------
	public String getInfo(int aInfoId)
	{
		switch (aInfoId)
		{
			case INFO_ID :
				return getClientName();
			case INFO_OPEN :
				return String.valueOf(hasConnOpened());
			case INFO_DEFAULT_HOST :
				return getDefaultHost();
			case INFO_DEFAULT_PORT :
				return String.valueOf(getDefaultPort());
			case INFO_NB_CONNS :
				return String.valueOf(getNbConns());
			case INFO_NB_CHANELS :
				return String.valueOf(getNbChanels());
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
	/**
   * 14w_006 - Intégration TermServer - Contrôle Trace
	 * @return
	 */
	public int getLevel()
	{
		if (hasConnOpened() && pDefaultCanal != null)
			return pDefaultCanal.getLevel();
		else
			return pDefaultLevel;
	}
  /**
   * 14w_006 - Intégration TermServer - Contrôle Trace
   * @param aDefaultLevel
   */
  public void setDefaultLevel(int aDefaultLevel)
  {
    pDefaultLevel = aDefaultLevel;
    /*
     * propagation sur les canaux existant
     */
    CTraceCliChanel wTraceCliCanal;
    Enumeration wElems = pChanels.elements();
    while (wElems.hasMoreElements())
    {
      wTraceCliCanal = (CTraceCliChanel) wElems.nextElement();
      wTraceCliCanal.setLevel(aDefaultLevel);
    }
    
  }
	//--------------------------------------------------------------------
	ITracer getLocalTracer()
	{
		return pTraceLocal;
	}

	//--------------------------------------------------------------------
	/**  */
	public boolean isLocalTraceFileOn()
	{
		return pTraceLocal.isTraceFileOn();
	}
	//--------------------------------------------------------------------
	/** ouverture de la socket avec le serveur */
	public boolean isTheDefaultConn(String aHost, int aPort)
	{
		return (pDefaultHost.equalsIgnoreCase(aHost) && pDefaultPort == aPort);
	}

	//--------------------------------------------------------------------
	/** retourne le nmbre de connexions ouvertes */
	public int getNbConns()
	{
		return pConns.size();
	}
	//--------------------------------------------------------------------
	/** retourne la table des connexions ouvertes */
	public CTraceCliConns getConns()
	{
		return pConns;
	}
	//--------------------------------------------------------------------
	/** retourne la liste des connexions ouvertes */
	public ArrayList getConnsList()
	{
		ArrayList wList = new ArrayList();
		// modif .Net
		//wList.addAll(pConns.values());
		Enumeration wConns = pConns.elements();
		while (wConns.hasMoreElements())
		{
			wList.add(wConns.nextElement());
		}
		return wList;
	}
	//--------------------------------------------------------------------
	public boolean hasConnOpened()
	{
		return (!pConns.isEmpty());
	}
	//--------------------------------------------------------------------
	public boolean hasDefaultConnOpened()
	{
		return (pHasDefaultConn && pConns.hasDefaultConnOpened(pDefaultHost, pDefaultPort));
	}
	//--------------------------------------------------------------------
	/** retourne le nmbre de canaux ouverts */
	public int getNbChanels()
	{
		return pChanels.size();
	}
	//--------------------------------------------------------------------
	/** retourne la table des canaux ouvert */
	public Hashtable getChanels()
	{
		return pChanels;
	}
	//--------------------------------------------------------------------
	/** retourne la liste des connexions ouvertes */
	public ArrayList getChanelsList()
	{
		ArrayList wList = new ArrayList();

		Enumeration wChanels = pChanels.elements();
		while (wChanels.hasMoreElements())
		{
			wList.add(wChanels.nextElement());
		}
		return wList;
	}
	/**--------------------------------------------------------------------
	 * @param aCanalName
	 * @return
	 */
	public boolean hasChanel(String aCanalName)
	{
		return pChanels.containsKey(aCanalName);
	}
	//--------------------------------------------------------------------
	public CTraceCliChanel getChanel(String aCanalName)
	{
		return (CTraceCliChanel) pChanels.get(aCanalName);
	}
	//--------------------------------------------------------------------
	/** retourne le nmbre de canaux ayant le prefixe */
	public int countChanels(String aPrefix)
	{
		int wCount = 0;
		String wPrefix;
		Enumeration wElems = pChanels.elements();
		while (wElems.hasMoreElements())
		{
			wPrefix = ((CTraceCliChanel) wElems.nextElement()).getCanalPrefixName();
			if (aPrefix.equalsIgnoreCase(wPrefix))
				wCount++;
		}
		return wCount;
	}
	//--------------------------------------------------------------------
	/** ferme tous les canaux */
	public void closeAllChanels()
	{
		CTraceCliChanel wTraceCliCanal;
		Enumeration wElems = pChanels.elements();
		while (wElems.hasMoreElements())
		{
			wTraceCliCanal = (CTraceCliChanel) wElems.nextElement();
			wTraceCliCanal.closeCanal();
		}
	}
	//--------------------------------------------------------------------
	/** ferme tous les canaux */
	public void openAllChanels()
	{
		CTraceCliChanel wTraceCliCanal;
		Enumeration wElems = pChanels.elements();
		while (wElems.hasMoreElements())
		{
			wTraceCliCanal = (CTraceCliChanel) wElems.nextElement();
			wTraceCliCanal.openCanal();
		}
	}
	/**
   * 14w_006 - Intégration TermServer - Contrôle des traces
   * retourne le nom du client suffixé par l'ide de l'instance
	 * @return
	 */
	public String getClientName()
	{
		if (!hasInstanceId())
		{
      return pClientName;
    }
		else
		{
      return pClientName + TRACE_INSTANCE_DELIM + pInstanceId;
		}
	}
  /**
   * 14w_006 - Intégration TermServer - Contrôle des traces
   * retourne le nom du client brut
   * @return
   */
  public String getClientId()
  {
    return pClientName;
  }
	//--------------------------------------------------------------------
	/** ouverture d'une socket avec un serveur.</p>
	 * <p>Cette méthode est protégée, une socket n'est ouverte qu'une fois
	 * avec un serveur identifié par "host" + "port"</p>*/
	public void addOneConn(String aHost, int aPort)
	{
		pConns.addOneConn(aHost, aPort);
	}
	//--------------------------------------------------------------------
	/** ouverture de la connexion par défault */
	public void openDefaultConn()
	{
		//crée une instance de connexion si la trace doit être ouverte et
		//si il n'y a pas encore de connexion
		if (pHasDefaultConn)
		{
			addOneConn(pDefaultHost, pDefaultPort);
		}
	}
	//--------------------------------------------------------------------
	/** fermeture d'une connexions avec le serveur par défaut.
	 * */
	public void closeDefaultConn()
	{
		//crée une instance de connexion si la trace doit être ouverte et
		//si il n'y a pas encore de connexion
		if (pHasDefaultConn)
		{
			pConns.closeOneConn(pDefaultHost, pDefaultPort);
		}
	}
	//--------------------------------------------------------------------
	/** fermeture de toutes les connexions*/
	void closeAllConns()
	{
		//supprime l'instance de connexion si elle existe
		if (hasConnOpened())
			pConns.closeAllConns();

	}
	//--------------------------------------------------------------------
	/**Envoi la commande quit à tous les serveurs connectés
	 */
	public void stopAllServers()
	{
		///Trace
		pTraceLocal.trace(this, 1, ".stopAllServers: begin");
		//FTrace*/
		pConns.writeMess(CTraceCli.CANAL_UNKNOWN, CTraceCli.LEVEL_ZERO, CTraceCli.TRACE_QUIT, null, new CTraceBuffer());
	}

	//--------------------------------------------------------------------
	/** creation et/ou ouverture d'un canal*/
	public CTraceCliChanel newChanel(String aCanalName)
	{
		return newChanel(true, aCanalName, LEVEL_MAX);
	}
	//--------------------------------------------------------------------
	/** creation et/ou ouverture d'un canal*/
	public CTraceCliChanel newChanel(String aCanalName, int aLevel)
	{
		return newChanel(true, aCanalName, aLevel);
	}
	//--------------------------------------------------------------------
	/**
	 * creation et/ou ouverture d'un canal de trace
	 */
	public CTraceCliChanel newChanel(boolean aCanalOpen, String aCanalName, int aLevel)
	{

		///Trace
		if (isLocalTraceFileOn())
		{
			CTraceBuffer wTB = popTraceBuffer();
			wTB.appendMethodName("newChanel");
			wTB.appendDescr("CanalOpen", aCanalOpen);
			wTB.appendDescr("CanalName", aCanalName);
			wTB.appendDescr("Level", aLevel);
      wTB.appendDescr("ClientName", getClientId());// sans l'instanceID
      wTB.appendDescr("InstanceId", pInstanceId);
      wTB.appendDescr("ClientOn", isTraceOn());
			getLocalTracer().trace(this, ITracer.LEVEL_DEBUG, wTB);
			pushTraceBuffer(wTB);
		}
		//FTrace*/

		CTraceCliChanel wCanal = null;

		//si trace générale fermée => canal fermé !
		if (!isTraceOn())
		{
			aCanalOpen = false;
		}

		//recherche du canal
		wCanal = (CTraceCliChanel) pChanels.get(aCanalName);

		//si le canal existe déja => on ajuste le Level
		if (wCanal != null)
		{
			wCanal.setLevel(aLevel);

		}
		else
		{

			//ouvre la connexion au cas ou...
			if (isTraceOn())
				openDefaultConn();

			// création du canal
			wCanal = new CTraceCliChanel(this, aCanalName, aLevel);

			// mise ne place du canal dans la table
			pChanels.put(aCanalName, wCanal);

			// prise en compte du canal créer comme canal par défaut s'il n'y en a pas encore...
			if (pDefaultCanal == null)
				pDefaultCanal = wCanal;


      // ouverture du canal (méthode protégée si connexion fermée)
      if (aCanalOpen)
      {
        wCanal.openCanal();
        
        CTraceBuffer wTBo = popTraceBuffer();
        wTBo.appendMethodName("newChanel");
        wTBo.appendDescr("CanalOpen", aCanalOpen);
        wTBo.appendDescr("CanalName", aCanalName);
        wTBo.appendDescr("Level", aLevel);
        wTBo.appendDescr("ClientName", getClientId());// sans l'instanceID
        wTBo.appendDescr("InstanceId", pInstanceId);
        wCanal.trace(this, 0, wTBo);
        pushTraceBuffer(wTBo);
      }
		}
		return wCanal;
	}
	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 */
	public void destroyAllChanels(String aCanalName)
	{
		destroyAllChanels();
	}
	//--------------------------------------------------------------------
	public void destroyAllChanels()
	{
		Enumeration wEntries = pChanels.elements();
		CTraceCliChanel wCanal;
		while (wEntries.hasMoreElements())
		{
			wCanal = (CTraceCliChanel) wEntries.nextElement();
			destroyChanel(wCanal);
		}
	}
	//--------------------------------------------------------------------
	public void destroyChanel(String aCanalName)
	{
		// recherche de l'objet canal dans la table des cannaux (static)
		CTraceCliChanel wCanal = (CTraceCliChanel) pChanels.get(aCanalName);
		destroyChanel(wCanal);
	}
	//--------------------------------------------------------------------
	public void destroyChanel(CTraceCliChanel aCanal)
	{
		if (aCanal != null)
		{
			// fermeture du canal
			aCanal.closeCanal();
			pChanels.remove(aCanal.getCanalName());

			// si le canal par défaut à été supprimé : recherche d'un nouveau canal
			// par défaut
			if (pDefaultCanal == null || !pChanels.contains(pDefaultCanal))
			{
				pDefaultCanal = null;
				if (pChanels.size() > 0)
				{
					pDefaultCanal = (CTraceCliChanel) pChanels.elements().nextElement();
				}
			}
			// s'il n'y a plus de canaux
			if (pChanels.size() == 0)
				closeAllConns(); // ferme la/les connexion(s)
		}
	}

	//--------------------------------------------------------------------
	//--------------------------------------------------------------------

	// UTILITAIRES

	//--------------------------------------------------------------------
	//--------------------------------------------------------------------

	static StringBuffer addInfoInLine(StringBuffer aLine, int aKind, String aLib, String aValue)
	{
		if (aKind == INFOS_HTML)
			return addInfoInHtmlLine(aLine, aLib, aValue);
		else
			return addInfoInTextLine(aLine, aLib, aValue);
	}

	//--------------------------------------------------------------------
	static StringBuffer addInfoInHtmlLine(StringBuffer aLine, String aLib, String aValue)
	{
		aLine.append("<li>").append(infoToString(aLib, aValue)).append("</li>");
		return aLine;
	}
	//--------------------------------------------------------------------
	static StringBuffer addInfoInTextLine(StringBuffer aLine, String aLib, String aValue)
	{
		aLine.append(' ').append(infoToString(aLib, aValue)).append(',');
		return aLine;
	}
	//--------------------------------------------------------------------
	static String infoToString(String aLib, String aValue)
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append(aLib).append("=[").append(aValue).append(']');
		return wSB.toString();
	}

	//--------------------------------------------------------------------
	/** retourne la version de la trace coté client */
	static public String getVersion()
	{
		return TRACE_VERSION;
	}
	//--------------------------------------------------------------------
	/**
	 * Retourne les composantes (classe,why,mess,stack) d'une exception dans une string
	 * @deprecated
	 */
	/*
	 * public static String eToString(Throwable e)
	{
		return  new CTraceBuffer(256).appendThrowableDescr(e).toString();
	}
	*/

	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 */
	public String toHtml()
	{
		return toInfos(INFOS_HTML, INFO_CNXS + INFO_CHANELS);
	}
	final static int INFO_CNXS = 1;
	final static int INFO_CHANELS = 2;
	final static int INFO_MODULES = 4;
	/**--------------------------------------------------------------------
	 * @param aPresentation
	 * @param aContent
	 * @return
	 */
	private String toInfos(int aPresentation, int aContent)
	{
		StringBuffer wSB = new StringBuffer(512);
		if (aPresentation == INFOS_HTML)
		{
			wSB.append("<ul>");
		}
		addInfoInLine(wSB, aPresentation, "TraceOn", String.valueOf(isTraceOn()));
		addInfoInLine(wSB, aPresentation, "ClientName", getClientName());
		addInfoInLine(wSB, aPresentation, "DefaultHost", pDefaultHost);
		addInfoInLine(wSB, aPresentation, "DefaultPort", String.valueOf(pDefaultPort));
		addInfoInLine(wSB, aPresentation, "HasConnOpened", String.valueOf(hasConnOpened()));
		if ((aContent & INFO_CNXS) != 0)
		{
			pConns.addInfos(wSB, aPresentation);
		}
		if ((aContent & INFO_CHANELS) != 0)
		{
			pChanels.addInfos(wSB, aPresentation);
		}
		if ((aContent & INFO_MODULES) != 0)
		{
			addModulesInInfos(wSB, aPresentation);
		}
		if (aPresentation == INFOS_HTML)
		{
			wSB.append("</ul>");
		}
		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * @param aText
	 * @param aPresentation
	 * @return
	 */
	private StringBuffer addModulesInInfos(StringBuffer aText, int aPresentation)
	{
		CTraceCli.addInfoInLine(aText, aPresentation, "NbModules", String.valueOf(pModuleFilters.size()));

		Enumeration wElems = pModuleFilters.keys();
		String wKey;
		Boolean wValue;
		int wI = 0;
		while (wElems.hasMoreElements())
		{
			wI++;
			wKey = (String) wElems.nextElement();
			wValue = (Boolean) pModuleFilters.get(wKey);
			if (aPresentation == INFOS_HTML)
				aText.append("<li>");
			aText.append("Module (").append(wI).append(")[").append(wKey).append(']').append(":[").append(wValue).append(']');
			if (aPresentation == INFOS_HTML)
				aText.append("</li>");
		}
		return aText;
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
	 * @return
	 */
	public String toStringDescr()
	{
		return toInfos(INFOS_TEXT, INFO_CNXS);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String toStringDescrFull()
	{
		return toInfos(INFOS_TEXT, INFO_CNXS + INFO_CHANELS + INFO_MODULES);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String toStringDescrWithModules()
	{
		return toInfos(INFOS_TEXT, INFO_CNXS + INFO_MODULES);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, Throwable e)
	{
		trace(aObj, "", e);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, String aS, Throwable e)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aObj, aS, e);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, StringBuffer aSB, Throwable e)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aObj, aSB.toString(), e);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, CTraceBuffer aTB, Throwable e)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aObj, aTB.toString(), e);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(String aS)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aS);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(StringBuffer aSB)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aSB);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, String aS)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aObj, aS);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, StringBuffer aSB)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			wDefaultChanel.trace(aObj, aSB.toString());
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, String aS)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			pDefaultCanal.trace(aObj, aLevel, aS);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, StringBuffer aSB)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			pDefaultCanal.trace(aObj, aLevel, aSB);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void trace(Object aObj, int aLevel, CTraceBuffer aST)
	{
		CTraceCliChanel wDefaultChanel = getDefaultChanel();
		if (wDefaultChanel != null)
			pDefaultCanal.trace(aObj, aLevel, aST);
	}

	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public boolean traceLevelFilter(int aLevel)
	{
		if (hasConnOpened() && pDefaultCanal != null)
			return pDefaultCanal.levelFilter(aLevel);
		else
			return false;
	}
	/* --------------------------------------------------------------------
	 * Retourne vrai si le module n'est pas référencé. 
	 * Sinon, retourne le flag du module
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#traceModuleFilter(java.lang.String)
	 */
	public boolean traceModuleFilter(String aModuleName)
	{
		Boolean wFilter = (Boolean) pModuleFilters.get(aModuleName);

		return (wFilter == null || wFilter.booleanValue());
	}
	//--------------------------------------------------------------------
	private boolean pTimerOn = false;
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double startTimer()
	{
		if (pTimer != null && !pTimerOn)
		{
			pTimer.reset();
			pTimer.start();
			pTimerOn = true;
			return System.currentTimeMillis();
		}
		else
			return 0;
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double stopTimer()
	{
		if (pTimer != null && pTimerOn)
		{
			pTimer.stop();
			pTimerOn = false;
			return pTimer.getDuration();
		}
		else
			return 0;
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public double getDuration()
	{
		if (pTimer != null && !pTimerOn)
			return pTimer.getDuration();
		else
			return 0;
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public CTraceBuffer popTraceBuffer()
	{
		return pTraceBuffers.popTraceBuffer(); // new CTraceBuffer(256);
	}
	//--------------------------------------------------------------------
	/**
	 * implémentation de ITracer
	 */
	public void pushTraceBuffer(CTraceBuffer aTraceBuffer)
	{
		pTraceBuffers.pushTraceBuffer(aTraceBuffer);
	}

	//--------------------------------------------------------------------
}
