package com.adonix.tracecli;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Calendar;

class CTracerLocal extends CAbstractTracer implements ITracer
{
  private final static String FILE_NAME_PREFIX = "TRACECLI";

  private final static String FILE_NAME_SUFFIX = ".txt";

  private final static String SUB_PATH_1       = "Adonix_X3";

  private final static String SUB_PATH_2       = "TRACE";

  private final static String TEMP_PATH        = "C:\\TEMP";

  private final byte OA = 0x0A;

  private final byte OD = 0x0D;

  /**
   * Id utilisé pour constituer le filename de la trace locale
   * "TRACECLI_aFileId_1980_00_06_11-07-20.txt"
   */
  private String              pFileId;

  /**
   * référence suir le "RandomAccessFile" de la trace locale
   */
  private RandomAccessFile    pRAF             = null;

  /**
   * Flag d'activation de la trace locale dans le fichier
   * "C:\TEMP\Adonix_X3\TRACE\TRACECLI_aFileId_1980_00_06_11-07-20.txt"
   */
  private boolean             pTraceFileOn     = false;       // off

  // --------------------------------------------------------------------
  CTracerLocal(String aFileId)
  {
    super();
    pFileId = aFileId;
  }

  // --------------------------------------------------------------------
  /**
   * Retourne le filename de la trace locale "TRACECLI_aFileId_1980_00_06_11-07-20.txt"
   */
  private String buildFileName(String aFileId)
  {
    Calendar wCalendar = Calendar.getInstance();
    StringBuffer wSB = new StringBuffer(64);
    wSB.append(FILE_NAME_PREFIX);
    wSB.append('_');
    wSB.append(aFileId);
    wSB.append('_');
    wSB.append(formatNum(wCalendar.get(Calendar.YEAR), 4));
    wSB.append('_');
    wSB.append(formatNum(wCalendar.get(Calendar.MONTH) + 1, 2));// + 1 car MONTH de 0 à 11 !!!
    wSB.append('_');
    wSB.append(formatNum(wCalendar.get(Calendar.DAY_OF_MONTH), 2));
    wSB.append('_');
    wSB.append(formatNum(wCalendar.get(Calendar.HOUR_OF_DAY), 2));
    wSB.append('-');
    wSB.append(formatNum(wCalendar.get(Calendar.MINUTE), 2));
    wSB.append('-');
    wSB.append(formatNum(wCalendar.get(Calendar.SECOND), 2));
    wSB.append(FILE_NAME_SUFFIX);
    return wSB.toString();
  }
  // --------------------------------------------------------------------
  /**
   * Retourne le filepath de la trace locale
   * "C:\TEMP\Adonix_X3\TRACE\TRACECLI_aFileId_1980_00_06_11-07-20.txt"
   */
  private String buildFilePath()
  {
    StringBuffer wSB = new StringBuffer(128);
    wSB.append(TEMP_PATH);
    wSB.append(File.separatorChar);
    wSB.append(SUB_PATH_1);
    wSB.append(File.separatorChar);
    wSB.append(SUB_PATH_2);
    wSB.append(File.separatorChar);
    wSB.append(buildFileName(pFileId));

    return wSB.toString();
  }
  /**
   * @return
   */
  boolean isTraceFileOn()
  {
    return pTraceFileOn;
  }
  /**
   * 
   */
  void setTraceFileOff()
  {
    setTraceFileOn(false);
  }
  /**
   * 
   */
  void setTraceFileOn()
  {
    setTraceFileOn(true);
  }
  
  /**
   * @param aTraceFileOn
   */
  void setTraceFileOn(boolean aTraceFileOn)
  {
    pTraceFileOn = aTraceFileOn;
    try
    {
      if (pTraceFileOn)
      {
        if (pRAF == null)
          pRAF = new RandomAccessFile(new File(buildFilePath()), "rw");
      }
      else
      {

        if (pRAF != null)
          pRAF.close();
        pRAF = null;
      }
    }
    catch (Throwable e)
    {
      System.out.println(new CTraceBuffer(512).appendThrowableDescr(e).getValue());
      pTraceFileOn = false;
      pRAF = null;
    }
  }

  /* (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, java.lang.String)
   */
  public void trace(Object aObj, int aLevel, String aS)
  {
    byte[] wPrefix = buildPrefix(aObj, aLevel);
    byte[] wLine = aS.getBytes();

    if (pTraceFileOn)
    {
      traceInFile(wPrefix, wLine);
    }
    if (isTraceSysOutOn())
    {
      traceInSysOut(wPrefix, wLine);
    }
  }

  /**
   * @param aPrefix
   * @param aLine
   */
  void traceInFile(byte[] aPrefix, byte[] aLine)
  {
    try
    {
      pRAF.seek(pRAF.length());
      pRAF.write(aPrefix);
      pRAF.write(aLine);
      pRAF.write(OA);
    }
    catch (Throwable e)
    {
      System.out.println(new CTraceBuffer(512).appendThrowableDescr(e).getValue());
    }
  }

  public boolean traceLevelFilter(int aLevel)
  {
    return (pTraceFileOn || super.traceLevelFilter(aLevel));
  }
}
