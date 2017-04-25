/*
 * Created on 6 janv. 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.adonix.tracecli;

/**
 * 
 * @author Adonix Grenoble
 * @version 140_003
 */
/**
 * 
 * @author Adonix Grenoble
 * @version 140_003
 */
public class CTraceTimer implements ITraceTimer
{
	/*
	 * 
	 */
	final static String JAVA_VENDOR = "java.vendor";
	final static String JAVA_VENDOR_SUN = "sun microsystems";
	final static String JAVA_VERSION = "java.version";
	final static String JAVA_VERSION_14 = "1.4.";
	
  /**
   * 14w_009 - Intégration WebServices
   * @return
   */
  public final static CTraceTimer newStartedTimer()
  {
    CTraceTimer wTimer = new CTraceTimer();
    wTimer.reset();
    wTimer.start();
    return wTimer;
  }
	/**--------------------------------------------------------------------
	 * 
	 * java.vendor	= [Sun Microsystems Inc.] 
	 * java.vendor.url	= [http://java.sun.com/] 
	 * java.version	= [1.5.0_01] 
	 *                 
	 * @return
	 */
	private static boolean testSunPerfAvailable()
	{
		String wVendor = System.getProperty(JAVA_VENDOR);
		if (wVendor!=null && wVendor.toLowerCase().indexOf(JAVA_VENDOR_SUN)>-1)
		{
			String wVersion = System.getProperty(JAVA_VENDOR);
			
			return (wVersion!=null && wVersion.compareTo(JAVA_VERSION_14)>=0);
		}
		return false;
	}
	/**
	 * 
	 */
	boolean pIsSunPerfAvailable = testSunPerfAvailable();
	/**
	 * 
	 */
	double pNbTicks;
	/**
	 * 
	 */
	double pStartTick;
	/**--------------------------------------------------------------------
	 * 
	 */
	public CTraceTimer()
	{
		super();
		reset();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	private static double highResFrequency()
	{
		/*//J# 1.1.4
		return 1000;	
		///FJ# 1.1.4 */

		///Java 1.2
		return sun.misc.Perf.getPerf().highResFrequency();
		//FJava 1.2*/
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	private static long highResCounter()
	{
		/*//J# 1.1.4
		return 0;	
		///FJ# 1.1.4 */

		///Java 1.2
		return sun.misc.Perf.getPerf().highResCounter();
		//FJava 1.2*/
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITraceTimer#getDuration()
	 */
	public double getDuration()
	{
		if (pIsSunPerfAvailable)
			return pNbTicks * 1000 / highResFrequency();
		else
			return pNbTicks;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	private long getHighResCounter()
	{
		if (pIsSunPerfAvailable)
			return highResCounter();
		else
			return System.currentTimeMillis();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public boolean isStarted()
	{
		return (pStartTick != 0);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public boolean isSunPerfAvailable()
	{
		return pIsSunPerfAvailable;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITraceTimer#reset()
	 */
	public void reset()
	{
		pStartTick = 0;
		pNbTicks = 0;
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITraceTimer#start()
	 */
	public void start()
	{
		pStartTick = getHighResCounter();
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.tracecli.ITraceTimer#stop()
	 */
	public void stop()
	{
		pNbTicks = getHighResCounter() - pStartTick;
	}
}
