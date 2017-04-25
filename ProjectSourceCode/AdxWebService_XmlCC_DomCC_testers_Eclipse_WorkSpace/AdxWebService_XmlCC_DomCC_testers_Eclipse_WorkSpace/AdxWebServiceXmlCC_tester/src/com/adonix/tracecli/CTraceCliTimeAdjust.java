package com.adonix.tracecli;

import java.util.Calendar;
import java.util.Date;

class CTraceCliTimeAdjust extends Thread implements CTraceCliWaiter
{
	//--------------------------------------------------------------------
	private CTraceCliConn pConn;
	
	private String pMessageWait = null;
	
	private long pAdjustTime;
	//--------------------------------------------------------------------
	CTraceCliTimeAdjust(CTraceCliConn aConn)
	{
		pConn =aConn;
		pAdjustTime = 0;
	}
		//--------------------------------------------------------------------
	/** initialise le buffer du message attendu.*/
	private synchronized void initBufferMess ()
	{
		pMessageWait = null;
	}
		//--------------------------------------------------------------------
	/** initialise le buffer du message attendu.*/
	private synchronized void setBufferMess (String aBuffer)
	{
		pMessageWait = new String(aBuffer);
	}
	//--------------------------------------------------------------------
	/** initialise le buffer du message attendu.*/
	private synchronized String getBufferMess()
	{
		return pMessageWait;
	}
	//--------------------------------------------------------------------
	/** initialise le buffer du message attendu.*/
	private synchronized boolean hasBufferMess()
	{
		return (pMessageWait!=null);
	}
	//--------------------------------------------------------------------
	/**stocke le message et stoppe l'attente de la réponse en relançant le thread principal.
	 */
	public void storeWaitedReply (String aMessageWait)
	{
		//Trace
		//CTraceCli.traceLocal(this,1,"CTraceCliCanal.storeWaitedReply: begin");
		//Frace
		setBufferMess(aMessageWait);
		
		stopWaiting();
	}
	//--------------------------------------------------------------------
	/**
	 * <p>Utilise la capacité de la méthode notify() utilisée dans un bloc 
	 * synchronisé d'une instance de classe à rendre le verrou à un autre thread
	 * arrêter précédement</p>
	 * <p>L'arrêt précédent à été obtenu par l'emploi de la méthode wait()
	 * depuis un autre bloc synchronisé de la même instance d'objet.</p>
	 * <p>Voir page 178 du livre "Java par la pratique" aux éditions O'Reilly</p>*/
	private synchronized void stopWaiting ()
	{
		notify();
	}
	//--------------------------------------------------------------------
	/**<p>lance l'attente de la réponse en arrêtant le thread principal</p>
	 * <p>Utilise la capacité de la méthode Wait utilisée dans un bloc 
	 * synchronisé d'une instance de classe à rendre le verrou et à s'endormir</p>
	 * <p>Lorsqu'un autre Thread lui rendra en utilisant la méthode notify() 
	 * depuis un autre bloc synchronisé de la même instance d'objet.</p>
	 * <p>Voir page 178 du livre "Java par la pratique" aux éditions O'Reilly</p>*/
	
	private synchronized String waitForReply ()
	{
		//Trace
		//CTraceCli.traceLocal(this,1,"CTraceCliCanal.waitForReply: begin");
		//FTrace

		try{
			wait(10000);//10secondes max !
		}
		catch(InterruptedException e){
		}

		return getBufferMess();
	}
	
	//--------------------------------------------------------------------
	/** demande du temps de référence*/
	private String askRefTime ()
	{
		//Trace
		//CTraceCli.traceLocal(this,1,"CTraceCliCanal.askRefTime: begin");
		//FTrace
		String wResponse = "";
		
		
		initBufferMess();
		// enregistrement du canal en tant que "Waiter"
		pConn.registerWaiter(this);
		// envoi du message
		pConn.writeMess(CTraceCli.CANAL_UNKNOWN,CTraceCli.LEVEL_ZERO,CTraceCli.TRACE_TIME,null,new CTraceBuffer());
		// et lecture de la réponse
		wResponse = waitForReply();
		// desenregistrement du timeAdjuster en tant que "Waiter"
		pConn.unregisterWaiter();
			
		if (wResponse==null) wResponse="";

		return wResponse;
	}
	
	//--------------------------------------------------------------------
	/** préfixage du texte par le nom de la calsse de l'objet "aObj"*/
	
	private long getTimeFromString(String aString)
	{
		long wTime = 0;
		try{
				wTime = new Long(aString).longValue();
		}
		catch (NumberFormatException e){
		}
		return wTime;
	}
	
	//--------------------------------------------------------------------
	/** calcul de la valeur d'ajustement du temp de référence*/
	
	private void calcAdjustTime()
	{
		//Trace
		//CTraceCli.traceLocal(this,1,"CTraceCli.calcAdjustTime: begin");
		//FTrace
		String wReadString;
		CTraceBuffer wTB;
		int wI;
		
		long wDeltaRefTime=0;
		long wDeltaMiniRefTime = 0;
		long wDeltaMaxRefTime = 0;
		long wDeltaAskTime=0;
		long wDeltaMiniAskTime = 0;
		long wDeltaMaxAskTime = 0;
		long wAdjustTime = 0;
		
		long wTblAskTime = 0;
		long wTblRefTime = 0;
		long wTblReplyTime = 0;
		
		wI = 0;
		while (wI < CTraceCli.NB_ASK_TIME_REF) {
			wTblAskTime= new Date().getTime();
			wReadString = this.askRefTime();
			wTblReplyTime= new Date().getTime();
			
			wTblRefTime = getTimeFromString(wReadString);
			
			wDeltaAskTime = wTblReplyTime-wTblAskTime;
			wDeltaRefTime = wTblRefTime-wTblAskTime;
			
			
			// mémorisation du plus petit "delta Ask Time"
			if ((wDeltaMiniAskTime == 0) || (Math.abs(wDeltaMiniAskTime) > Math.abs(wDeltaAskTime))) 
				wDeltaMiniAskTime = wDeltaAskTime;
			// mémorisation du plus grand "delta Ask Time"
			if ((wDeltaMaxAskTime == 0) || (Math.abs(wDeltaMaxAskTime) < Math.abs(wDeltaAskTime))) 
				wDeltaMaxAskTime = wDeltaAskTime;
			
			// mémorisation du plus petit "AdjustTime"
			if ((wDeltaMiniRefTime == 0) || (Math.abs(wDeltaMiniRefTime) > Math.abs(wDeltaRefTime)))
				wDeltaMiniRefTime = wDeltaRefTime;
			// mémorisation du plus petit "AdjustTime"
			if ((wDeltaMaxRefTime == 0) || (Math.abs(wDeltaMaxRefTime) < Math.abs(wDeltaRefTime)))
				wDeltaMaxRefTime = wDeltaRefTime;
			
			wAdjustTime = wDeltaMiniRefTime - wDeltaMaxAskTime;
			
			// mémorisation du plus petit "AdjustTime"
			if ((pAdjustTime == 0) || (Math.abs(pAdjustTime) > Math.abs(wAdjustTime)))
				pAdjustTime = wAdjustTime;
			
			//Trace
			CTraceBuffer wTBt = new CTraceBuffer(128);
			wTBt.append("TIME  Ask[").append(longTime2string(wTblAskTime)).append(']');
			wTBt.append(" D[").append(wDeltaAskTime).append(']');
			wTBt.append(" Ref[").append(longTime2string(wTblRefTime)).append(']');
			wTBt.append(" D[").append(wDeltaRefTime).append(']');
			pConn.writeMess(CTraceCli.CANAL_UNKNOWN,CTraceCli.LEVEL_ZERO,CTraceCli.TRACE_ADDLINE,this,wTBt);
			//FTrace

			wI++;
			
		}
		
		wTB = new CTraceBuffer(256);
		wTB.append(pAdjustTime);// token 3 = sAdjustTime
		wTB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(wDeltaMiniAskTime);// token 4 = wDeltaMiniAskTime
		wTB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(wDeltaMaxAskTime);// token 5 = wDeltaMaxAskTime
		wTB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(wDeltaMiniRefTime);// token 6 = wDeltaMiniRefTime
		wTB.append(CTraceCli.TRACE_PROTOCOLE_DELIM).append(wDeltaMaxRefTime);// token 7 = wDeltaMaxRefTime
		
		//envoi de la requete TIME INFOS
		pConn.writeMess(CTraceCli.CANAL_UNKNOWN,CTraceCli.LEVEL_ZERO,CTraceCli.TRACE_TIMEINFOS,null,wTB);
	}
	//--------------------------------------------------------------------
	private static String longTime2string (long aTime)
	{
		Calendar wRightNow = Calendar.getInstance();
		wRightNow.setTime(new Date(aTime));

		StringBuffer wStrBuff = new StringBuffer(9);
		//wStrBuff.append(aRightNow.get(Calendar.HOUR_OF_DAY));
		//wStrBuff.append(TRACE_TIME_DELIM);
		wStrBuff.append(stripNumString(wRightNow.get(Calendar.MINUTE),2));
		wStrBuff.append(':');
		wStrBuff.append(stripNumString(wRightNow.get(Calendar.SECOND),2));
		wStrBuff.append(':');
		wStrBuff.append(stripNumString(wRightNow.get(Calendar.MILLISECOND),3));
		return wStrBuff.toString();
	}
	//--------------------------------------------------------------------
	static  String stripNumString (int aValue,int aLong)
	{
		String wResult = String.valueOf(aValue);
		if (wResult.length() < aLong) wResult = "0000000000".concat(wResult);
		if (wResult.length() > aLong) wResult = wResult.substring(wResult.length()-aLong);
		return wResult;
	}
//--------------------------------------------------------------------
}
