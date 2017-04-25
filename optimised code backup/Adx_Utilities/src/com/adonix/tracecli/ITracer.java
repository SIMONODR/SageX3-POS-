package com.adonix.tracecli;

/**
 * @author Adonix Grenoble
 * @version 1.0
 */
public interface ITracer
{
	public final static String CONSTRUCTOR = "<init>";
	public final static int LEVEL_BUFFER = 10;
	public final static int LEVEL_CALLS = 1;
	public final static int LEVEL_DEBUG = 9;
	public final static int LEVEL_MAX = LEVEL_BUFFER;
	public final static int LEVEL_MIN = LEVEL_CALLS;
	public final static int LEVEL_ONE = LEVEL_CALLS;
	public final static int LEVEL_PARAMETERS = 5;
	public final static int LEVEL_ZERO = 0;
	public final static String LIB_MODULE_DISABLED = "module trace disabled.";
	/**--------------------------------------------------------------------
	 * @return
	 */
	public double getDuration();
	/**--------------------------------------------------------------------
	 * @return
	 */
	public CTraceBuffer popTraceBuffer();
	/**--------------------------------------------------------------------
	 * @param aStringBuffer
	 */
	public void pushTraceBuffer(CTraceBuffer aStringBuffer);
	/**--------------------------------------------------------------------
	 * @return
	 */
	public double startTimer();
	/**--------------------------------------------------------------------
	 * @return
	 */
	public double stopTimer();
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aSB
	 * @param e
	 */
	public void trace(Object aObj, CTraceBuffer aSB, Throwable e);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aLevel
	 * @param aST
	 */
	public void trace(Object aObj, int aLevel, CTraceBuffer aST);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aLevel
	 * @param aS
	 */
	public void trace(Object aObj, int aLevel, String aS);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aLevel
	 * @param aSB
	 */
	public void trace(Object aObj, int aLevel, StringBuffer aSB);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aS
	 */
	public void trace(Object aObj, String aS);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aS
	 * @param e
	 */
	public void trace(Object aObj, String aS, Throwable e);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aSB
	 */
	public void trace(Object aObj, StringBuffer aSB);
	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param aSB
	 * @param e
	 */
	public void trace(Object aObj, StringBuffer aSB, Throwable e);

	/**--------------------------------------------------------------------
	 * @param aObj
	 * @param e
	 */
	public void trace(Object aObj, Throwable e);
	/**--------------------------------------------------------------------
	 * @param aS
	 */
	public void trace(String aS);
	/**--------------------------------------------------------------------
	 * @param aSB
	 */
	public void trace(StringBuffer aSB);
	/**--------------------------------------------------------------------
	 * @param aLevel
	 * @return
	 */
	public boolean traceLevelFilter(int aLevel);
	/**--------------------------------------------------------------------
	 * @param aModuleName
	 * @return
	 */
	public boolean traceModuleFilter(String aModuleName);
}