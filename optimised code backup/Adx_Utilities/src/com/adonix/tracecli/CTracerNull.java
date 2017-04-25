package com.adonix.tracecli;

/**
 * Implementation d'un tracer "Null" qui ne fait rien => coût minimum
 *  
 * @author ogattaz
 * @version 140_000
 */

public final class CTracerNull implements ITracer
{
	/**
	 * Singletion TracerNull
	 * 
	 * Abandon de la synchronisation et utilise un initialiseur static !
	 * 
	 * Cette solution présente l'avantage, non négligeable, d'éliminer 
	 * la synchronisation lors de chaque accès. Cela fonctionne car la 
	 * machine virtuelle garantit qu'un objet d'une classe ne peut être 
	 * accédé tant que la classe n'est pas complètement chargée. 
	 * Toutefois cette solution n'est viable que si toutes les informations 
	 * nécessaires à la création du Singleton sont disponibles au moment du 
	 * chargement, ce qui ne peut être toujours garanti. En effet, la machine 
	 * virtuelle charge les classes comme elle le veut et il n'y a aucune 
	 * garantie que le chargement soit différé jusqu'au premier appel de 
	 * la méthode getInstance().
	 * 
	 * http://christophej.developpez.com/tutoriel/java/singleton/multithread/
	 */
	private static CTracerNull sTracerNull = new CTracerNull();
	
	/**--------------------------------------------------------------------
	 * retourne la référence du singleton.
	 * 
	 * @return
	 */
	public static ITracer getInstance()
	{
		return sTracerNull;
	}
	/**
	 * 
	 */
	private double pTimer = 0;
	/**
	 * 
	 */
	private boolean pTimerOn = false;

	/**--------------------------------------------------------------------
	 * 
	 */
	CTracerNull()
	{
		super();
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#getDuration()
	 */
	public double getDuration()
	{
		if (!pTimerOn)
		{
			return pTimer;
		}
		else
			return -1;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#popTraceBuffer()
	 */
	public CTraceBuffer popTraceBuffer()
	{
		return new CTraceBuffer(512);
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#pushTraceBuffer(com.adonix.tracecli.CTraceBuffer)
	 */
	public void pushTraceBuffer(CTraceBuffer aTraceBuffer)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#startTimer()
	 */
	public double startTimer()
	{
		if (!pTimerOn)
		{
			pTimer = System.currentTimeMillis();
			pTimerOn = true;
			return pTimer;
		}
		else
			return -1;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#stopTimer()
	 */
	public double stopTimer()
	{
		if (pTimerOn)
		{
			pTimer = System.currentTimeMillis() - pTimer;
			pTimerOn = false;
			return pTimer;
		}
		else
			return -1;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, com.adonix.tracecli.CTraceBuffer, java.lang.Throwable)
	 */
	public void trace(Object aObj, CTraceBuffer aTB, Throwable e)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, com.adonix.tracecli.CTraceBuffer)
	 */
	public void trace(Object aObj, int aLevel, CTraceBuffer aST)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, java.lang.String)
	 */
	public void trace(Object aObj, int aLevel, String aS)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, java.lang.StringBuffer)
	 */
	public void trace(Object aObj, int aLevel, StringBuffer aSB)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.String)
	 */
	public void trace(Object aObj, String aS)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.String, java.lang.Throwable)
	 */
	public void trace(Object aObj, String aS, Throwable e)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.StringBuffer)
	 */
	public void trace(Object aObj, StringBuffer aSB)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.StringBuffer, java.lang.Throwable)
	 */
	public void trace(Object aObj, StringBuffer aSB, Throwable e)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Object aObj, Throwable e)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.String)
	 */
	public void trace(String aS)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#trace(java.lang.StringBuffer)
	 */
	public void trace(StringBuffer aSB)
	{
		//nothing !
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#traceLevelFilter(int)
	 */
	public boolean traceLevelFilter(int aLevel)
	{
		return false;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITracer#traceModuleFilter(java.lang.String)
	 */
	public boolean traceModuleFilter(String aModuleName)
	{
		return false;
	}
}
