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
public class CTraceClock
{
	/**
	 * 
	 */
	public final static char CHAR_ZERO = '0';

	/**--------------------------------------------------------------------
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	private static StringBuffer padNumInSB(StringBuffer aSB, int aValue, int aLen)
	{
		return padNumInSB(aSB, String.valueOf(aValue), aLen);
	}
	/**--------------------------------------------------------------------
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	private static StringBuffer padNumInSB(StringBuffer aSB, String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
		{
			int wMax = aLen - wLen;
			int wI = 0;
			while (wI < wMax)
			{
				aSB.append(CHAR_ZERO);
				wI++;
			}
			aSB.append(aValue);
		}
		else if (wLen > aLen)
		{
			int wI = 0;
			while (wI < aLen)
			{
				aSB.append(aValue.charAt(wI));
				wI++;
			}
		}
		else // (wLen == aLen)
			{
			aSB.append(aValue);
		}

		return aSB;
	}
	//FJava 1.2*/
	private long m_lTicksPerSecond;
	///Java 1.2
	private sun.misc.Perf m_perf;

	/**--------------------------------------------------------------------
	 * 
	 */
	public CTraceClock()
	{
		super();
		///Java 1.2
		m_perf = sun.misc.Perf.getPerf(); // may throw SecurityException
		m_lTicksPerSecond = m_perf.highResFrequency();
		//FJava 1.2*/
	}

	/**--------------------------------------------------------------------
	 * Retrieve system time in microseconds.
	 * This method retrieves the time by calling
	 * {@link sun.misc.Perf}.
	 * @return the system time in microseconds
	 */
	public long getMicroseconds()
	{
		/*//J# 1.1.4
		return System.currentTimeMillis() * 1000 ;
		///FJ# 1.1.4 */

		///Java 1.2
		return (m_perf.highResCounter() * 1000000) / m_lTicksPerSecond;
		//FJava 1.2*/
	}

	/**--------------------------------------------------------------------
	 * @param aTime
	 * @param aSep
	 * @return
	 */
	public String getSSmmmxxx()
	{
		String wNow = String.valueOf(getMicroseconds());

		int wLen = wNow.length();

		if (wLen < 7)
		{
			return wNow;
		}
		else
		{
			StringBuffer wSB = new StringBuffer(32);

			padNumInSB(wSB, wNow.substring(0, wLen - 6), 2);
			wSB.append('\"');
			padNumInSB(wSB, wNow.substring(wLen - 6, wLen - 3), 3);
			wSB.append('.');
			padNumInSB(wSB, wNow.substring(wLen - 3), 3);

			return wSB.toString();
		}
	}
}
