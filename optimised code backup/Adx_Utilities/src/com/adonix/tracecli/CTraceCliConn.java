package com.adonix.tracecli;

import java.net.*;
import java.io.*;

/**
 * Implémentation d'une connexion du client de trace
 * @author Adonix Grenoble
 * @version 1.0
 */

public class CTraceCliConn extends Thread
{
	//--------------------------------------------------------------------
	public final static int INFO_ADDRESS = 1;
	public final static int INFO_OPEN = 2;
	/**	
	 * référence sur le client de trace
	 */
	private CTraceCli pTraceCli;
	/**
	 * Clé de la connexion
	 */
	private String pKey;
	/**
	 * Host name du serveur de trace
	 */
	private String pHost;
	/**
	 * InetAddress du serveur de trace
	 */
	private InetAddress pHostInetaddress = null;
	/**
	 * port du serveur de trace
	 */
	private int pPort;
	/**
	 * la socket associée à la connexion
	 */
	private Socket pSocket = null;
	/**
	 * le buffer de lecture associé à la socket
	 */
	private BufferedReader pBufferedReader = null;
	/**
	 * le buffer d'écriture associé à la socket
	 */
	private BufferedWriter pBufferedWriter = null;
	/**
	 * flag de contrôle de la boucle de la méthode Run
	 */
	private boolean pContinue = true;

	private CTraceCliWaiter pTraceCliWaiter = null;

	private CTraceCliTimeAdjust pTimeAdjuster = null;

	private long pAdjustTime = 0;

	private ITracer pTracer;

	//--------------------------------------------------------------------
	CTraceCliConn(CTraceCli aTraceCli, String aKey, String aHost, int aPort)
	{
		pTraceCli = aTraceCli;
		pTracer = aTraceCli.getLocalTracer();
		pKey = aKey;
		pHost = aHost;
		pPort = aPort;
		open();

		if (isOpened())
			pTimeAdjuster = new CTraceCliTimeAdjust(this);
	}
	//--------------------------------------------------------------------
	/** si la socket est crée et donc ouverte... */
	boolean isOpened()
	{
		return (pSocket != null);
	}
	//--------------------------------------------------------------------
	/** création et ouverture de la socket si l'adresse du serveur est bonne */
	void open()
	{
		pHostInetaddress = verifyAddress(pHost);

		if (pHostInetaddress != null)
		{
			try
			{
				pSocket = new Socket(pHostInetaddress, pPort);
				initBuffers();
				initTimeOut();
				//initLinger();
				//initTcpDelay();
				setPriority(NORM_PRIORITY - 1);
				start();
			}
			catch (IOException e)
			{
				//Trace
				pTracer.trace(this, 0, ".open: erreur:" + e.toString());
				//FTrace
			}
		}
	}
	//--------------------------------------------------------------------
	InetAddress verifyAddress(String aHostName)
	{
		InetAddress wAddress = null;
		try
		{
			wAddress = InetAddress.getByName(aHostName);
		}
		catch (UnknownHostException e)
		{
			pTracer.trace(this, 0, ".verifyAddress: getByName[" + aHostName + "] " + e.toString());
		}
		return wAddress;
	}
	//--------------------------------------------------------------------
	void initBuffers()
	{
		try
		{
			pBufferedReader = new BufferedReader(new InputStreamReader(pSocket.getInputStream()));
			pBufferedWriter = new BufferedWriter(new OutputStreamWriter(pSocket.getOutputStream()));
		}
		catch (IOException e)
		{
			pTracer.trace(this, 0, ".open: erreur:" + e);
		}
	}
	//--------------------------------------------------------------------
	/** Mise en place de la valeur du TimeOut sur la socket de la connexion */
	private void initTimeOut()
	{
		try
		{
			pSocket.setSoTimeout(CTraceCli.TIMEOUT_SOCKET);
		}
		catch (SocketException e)
		{
			pTracer.trace(this, 0, ".setTimeOut: erreur:" + e);
		}
	}
	//--------------------------------------------------------------------
	/** Mise en place de la valeur du TimeOut sur la socket de la connexion */
	private void initTcpDelay()
	{
		try
		{
			pSocket.setTcpNoDelay(true); //Enable/disable TCP_NODELAY (disable/enable Nagle's algorithm).
		}
		catch (SocketException e)
		{
			pTracer.trace(this, 0, ".setTcpDelay: erreur:" + e);
		}
	}
	//--------------------------------------------------------------------
	/** Désactivation de SO_LINGER */
	private void initLinger()
	{
		/**
		   SO_LINGER configure les actions à entreprendre s'il  reste
		   des  messages  en  attente d'émission alors qu'un close(2)
		   est effectué sur la socket.  Si la  socket  nécessite  une
		   délivrance  garantie  des  messages, SO_LINGER est validé,
		   l'appel à close sera bloquant pour le processus jusqu'à ce
		   que  les  données  aient été envoyées, ou jusqu'à ce qu'il
		   renonce à l'émission (un délai de timeout, appelé  persis­
		   tance   est   spécifié   dans   l'appel   setsockopt  avec
		   SO_LINGER).
			 Si SO_LINGER est désactive et que l'on  appelle  close  le
		   systeme  fermera la connexion de facon a permettre au pro­
		   cessus de continuer le plus rapidement possible.
		 */
		try
		{
			pSocket.setSoLinger(true, 1); // Enable/disable SO_LINGER with the specified linger time in seconds.
			// If the specified timeout value exceeds 65,535 it will be reduced to 65,535.
		}
		catch (SocketException e)
		{
			pTracer.trace(this, 0, ".setLinger: erreur:" + e);
		}
	}
	//--------------------------------------------------------------------
	/**  */
	private String calcTraceTime()
	{
		long wTraceTime = System.currentTimeMillis() + pAdjustTime;
		return String.valueOf(wTraceTime);
	}
	private CTraceBuffer pPrefix = new CTraceBuffer(128);
	private final static String NULL = "(null)";
	private final static int CLASS_LEN = 23;
	/**--------------------------------------------------------------------
	 * @param aObject
	 */
	/**--------------------------------------------------------------------
	 * @param aTB
	 * @param aObject
	 * @return
	 */
	private void addClassName(Object aObject)
	{
		int wLen = 0;
		pPrefix.append(CTraceCli.TRACE_OBJ_FLAG);

		if (aObject == null)
		{
			pPrefix.append(NULL);
			wLen += NULL.length();
		}
		else
		{
			String wInfo = null;
			if (aObject instanceof Class)
			{
				wInfo = ((Class) aObject).getName();
			}
			else if (aObject instanceof String)
      {
        wInfo = (String) aObject;
      }
      else
			{
				wInfo = aObject.getClass().getName();
			}
			int wPos = wInfo.lastIndexOf('.');
			if (wPos > -1)
			{
				wInfo = wInfo.substring(wPos+1);
			}

			if (wInfo.length() > CLASS_LEN)
			{
				wInfo = wInfo.substring(0, CLASS_LEN - 1);
			}

			pPrefix.append(wInfo);
			wLen += wInfo.length();

			if (wLen + 1 < CLASS_LEN)
			{
				wInfo =  String.valueOf( aObject.hashCode() );
				if (wInfo.length() + wLen + 1 > CLASS_LEN)
				{
					wInfo = wInfo.substring(0, CLASS_LEN - ( wLen + 1) );
				}
				
				pPrefix.append('_').append(wInfo);

				wLen += 1 + wInfo.length();
			}
		}
		if (wLen < CLASS_LEN)
		{
			addChars(' ', CLASS_LEN - wLen);
		}
		pPrefix.append(CTraceCli.TRACE_OBJ_DELIM);

	}

	/**--------------------------------------------------------------------
	 * @param aChar
	 * @param aLen
	 */
	private void addChars(char aChar, int aLen)
	{
		for (int wI = 0; wI < aLen; wI++)
		{
			pPrefix.append(aChar);
		}
	}

	//--------------------------------------------------------------------
	/**
	 * écrit le message dans la socket
	 */
	void writeMess(int aCanalId, int aLevel, char aVerbe, Object aObject, CTraceBuffer aLine)
	{
		if (isOpened())
		{

			/*
			 * StringBuffer wSB = new StringBuffer(256 + aString.length());
			wSB.append(aVerbe);
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(aCanalId);
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(aLevel);
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(calcTraceTime());
			wSB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(aString);
			*/
			pPrefix.delete();
			pPrefix.append(aVerbe);
			pPrefix.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(aCanalId);
			pPrefix.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(aLevel);
			pPrefix.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(calcTraceTime());
			pPrefix.append(CTraceCli.TRACE_PROTOCOLE_DELIM);

			if (aVerbe == CTraceCli.TRACE_ADDLINE)
			{
				addClassName(aObject);

				//				pPrefix.append(CTraceCli.TRACE_OBJ_FLAG);
				//				if (aObject != null)
				//				{
				//					String wName = null;
				//					if (aObject instanceof Class)
				//						wName = ((Class) aObject).getName();
				//					else
				//						wName = aObject.getClass().getName();
				//					int wPos = wName.lastIndexOf('.');
				//					if (wPos > -1)
				//						wName = wName.substring(wPos);
				//					pPrefix.append(wName);
				//					pPrefix.append('_').append(aObject.hashCode());
				//				}
				//				else
				//				{
				//					pPrefix.append("(null)");
				//				}
				//				pPrefix.append(CTraceCli.TRACE_OBJ_DELIM);
			}

			///Trace
			if (pTraceCli.isLocalTraceFileOn())
			{
				CTraceBuffer wTB = pTraceCli.popTraceBuffer();
				wTB.appendMethodName("writeMess");
				wTB.append(pPrefix);
				wTB.append(aLine);
				pTracer.trace(this, ITracer.LEVEL_DEBUG, wTB);
				pTraceCli.pushTraceBuffer(wTB);
			}
			//FTrace*/
			try
			{
				pBufferedWriter.write(pPrefix.getChars(), 0, pPrefix.length());
				pBufferedWriter.write(aLine.getChars(), 0, aLine.length());
				pBufferedWriter.newLine();
				pBufferedWriter.flush();
			}
			catch (IOException e)
			{
				///Trace
				pTraceCli.setLocalTraceFileOn(true);
				CTraceBuffer wTBe = pTraceCli.popTraceBuffer();
				wTBe.appendMethodName("writeMess");
				wTBe.appendDescr("WRITE_ERROR", e);
				pTracer.trace(this, 0, wTBe);
				pTraceCli.setLocalTraceFileOn(false);
				//FTrace*/
			}
		}
	}
	//--------------------------------------------------------------------
	/** */
	synchronized void registerWaiter(CTraceCliWaiter aTraceCliWaiter)
	{
		/*//Trace
		pTracer.addLineObj(this,1,".registerWaiter: begin");
		//FTrace*/
		pTraceCliWaiter = aTraceCliWaiter;
	}
	//--------------------------------------------------------------------
	/** */
	synchronized void unregisterWaiter()
	{
		/*//Trace
		CTraceCli.traceLocal(1,"CTraceCliConn.unregisterWaiter: begin");
		//FTrace*/
		pTraceCliWaiter = null;
	}
	//--------------------------------------------------------------------
	String getInfo(int aInfoId)
	{
		switch (aInfoId)
		{
			case INFO_ADDRESS :
				return String.valueOf(isOpened());
			case INFO_OPEN :
				return getCliInetAddresssInString();
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
	/** */
	synchronized CTraceCliWaiter getWaiter()
	{
		return pTraceCliWaiter;
	}
	//--------------------------------------------------------------------
	/** */
	private void doDispatch(String aReadString)
	{
		/*//Trace
		CTraceCli.traceLocal(5,"CTraceCliConn.doDispatch: begin:"+aReadString);
		//FTrace*/
		CTraceCliWaiter wWaiter = getWaiter();
		if (wWaiter != null)
		{
			wWaiter.storeWaitedReply(aReadString);
		}
	}
	//--------------------------------------------------------------------
	private synchronized void setContinue(boolean aContinue)
	{
		pContinue = aContinue;
	}
	//--------------------------------------------------------------------
	private synchronized boolean getContinue()
	{
		return pContinue;
	}
	//--------------------------------------------------------------------
	/** */
	public void run()
	{
		/*//Trace
		pTracer.addLineObj(this,1,".run: begin");
		//FTrace*/
		int wI = 0;
		String wBuffer;

		while (getContinue())
		{

			//lecture d'une "ligne" sur la socket
			try
			{
				wBuffer = pBufferedReader.readLine();
				if (wBuffer == null)
				{
					//Trace
					pTracer.trace(this, 5, ".run: REMOTE CLOSE");
					//Trace*/
					setContinue(false);
				}
				else
				{
					doDispatch(wBuffer);
				}
			}
			catch (IOException e)
			{
				wI++;
				//BUG: ogat le 22/06/2000
				//mise en place de cette attente pour ne pas boucler en prenant 100% de la CPU
				try
				{
					sleep(100);
				}
				catch (Exception e2)
				{
				}
				/*//Trace
				pTracer.addLineObj(this,5,".run: time out:"+wI);// trace sortie de TimeOut
				//Trace*/
			}
			/*//Trace
			if (!pLecture) CTraceCli.traceLocal(5,"CTraceCliConn.run: WANT STOP");
			//Trace*/
		}
		close();

		/*//Trace
		CTraceCli.traceLocal(1,"CTraceCliConn.run: end");
		//FTrace*/
	}
	//--------------------------------------------------------------------
	/** */
	void close()
	{
		///Trace
		pTracer.trace(this, 1, "CTraceCliConn.close: begin");
		//FTrace*/
		///Trace
		pTracer.trace(this, new Exception("DUMP STACK"));
		//FTrace*/
		try
		{
			pSocket.close(); // fermeture de la socket
		}
		catch (IOException e)
		{
			pTracer.trace(this, 0, ".close: erreur:" + e);
		}
		pSocket = null;
		// suppression des buffers
		pBufferedReader = null;
		pBufferedWriter = null;

		///Trace
		pTracer.trace(this, 1, "CTraceCliConn.close: end");
		//FTrace*/
	}
	//--------------------------------------------------------------------
	/** */
	void end()
	{
		///Trace
		pTracer.trace(this, 1, "CTraceCliConn.end: begin");
		//FTrace*/
		setContinue(false);
		try
		{
			if (this.isAlive())
				join(3000);
			// 3 secondes max
		}
		catch (InterruptedException e)
		{
			pTracer.trace(this, 0, ".end: erreur:" + e);
		}
		///Trace
		pTracer.trace(this, 1, "CTraceCliConn.end: end");
		//FTrace*/
	}

	//--------------------------------------------------------------------
	InetAddress getCliInetAddresss()
	{
		if (pSocket != null)
			return pSocket.getLocalAddress();
		else
			return null;
	}
	//--------------------------------------------------------------------
	String getCliInetAddresssInString()
	{
		if (pSocket != null)
			return pSocket.getLocalAddress().toString();
		else
			return "-";
	}
	//--------------------------------------------------------------------
	String getCliHostname()
	{
		InetAddress wInetAddress = getCliInetAddresss();

		if (wInetAddress != null)
			return wInetAddress.toString();
		else
			return null;
	}
	//--------------------------------------------------------------------
	InetAddress getHostInetAddresss()
	{
		return pHostInetaddress;
	}
	//--------------------------------------------------------------------
	String getHostHostname()
	{
		InetAddress wInetAddress = getHostInetAddresss();

		if (wInetAddress != null)
			return wInetAddress.toString();
		else
			return null;
	}
	//--------------------------------------------------------------------
	/**
	 * Returns the pKey.
	 * @return String
	 */
	String getKey()
	{
		return pKey;
	}
	//--------------------------------------------------------------------
	/**
	 * Sets the pKey.
	 * @param pKey The pKey to set
	 */
	void setKey(String aKey)
	{
		this.pKey = aKey;
	}
	//--------------------------------------------------------------------
	boolean isConnectedWithOtherMachine()
	{
		return ((!pHost.equals(CTraceCli.DEFAULT_SERVER)) && (!pHostInetaddress.equals(getCliInetAddresss())));
	}

	//--------------------------------------------------------------------
	/** Retourne une description du canal */
	public String toString()
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
	/** Retourne une description de la connexion */
	StringBuffer addInfos(StringBuffer aText, int aKind)
	{
		CTraceCli.addInfoInLine(aText, aKind, "isOpened", String.valueOf(isOpened()));
		CTraceCli.addInfoInLine(aText, aKind, "Adr", getCliInetAddresssInString());
		return aText;
	}

}
