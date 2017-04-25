package com.adonix.tracecli;

abstract class CAbstractTracer implements ITracer
{
  private final static String TENZEROSTRING = "0000000000";

  CTraceBuffer pPrefix = new CTraceBuffer(128);

  private double  pTimer   = 0;

  private boolean pTimerOn = false;

  /**
   * Flag d'activation de la trace locale dans la sysOut Standard
   */
  private boolean pTraceSysOutOn = false; // off

  /**
   * @param aObject
   * @param aGravite
   * @return
   */
  synchronized byte[] buildPrefix(Object aObject, int aGravite)
  {
    // Construction d'un préfixe

    pPrefix.delete();
    pPrefix.appendDateMMSSmmm();
    pPrefix.append('|');
    pPrefix.append(aGravite);
    pPrefix.append('|');
    pPrefix.appendClassName(aObject);
    pPrefix.append('\t');

    return pPrefix.getBytes();
  }

  /**
   * Retourne une chaine contenant la valeur cadrées à droite
   * @param aValue
   * @param aLong
   * @return
   */
  String formatNum(int aValue, int aLong)
  {
    String wResult = String.valueOf(aValue);
    if (wResult.length() < aLong)
      wResult = new StringBuffer(20).append(TENZEROSTRING).append(wResult).toString();
    if (wResult.length() > aLong)
      wResult = wResult.substring(wResult.length() - aLong);
    return wResult;
  }

  /*
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

  /**
   * @return
   */
  public boolean isTraceSysOutOn()
  {
    return pTraceSysOutOn;
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#popTraceBuffer()
   */
  public CTraceBuffer popTraceBuffer()
  {
    return new CTraceBuffer(512);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#pushTraceBuffer(com.adonix.tracecli.CTraceBuffer)
   */
  public void pushTraceBuffer(CTraceBuffer aTraceBuffer)
  {
    // nothing !
  }

  /**
   * 
   */
  void setTraceSysOutOff()
  {
    setTraceSysOutOn(false);
  }

  /**
   * 
   */
  void setTraceSysOutOn()
  {
    setTraceSysOutOn(true);
  }

  /**
   * @param aTraceSysOutOn
   */
  void setTraceSysOutOn(boolean aTraceSysOutOn)
  {
    pTraceSysOutOn = aTraceSysOutOn;
  }

  /*
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
    {
      return -1;
    }
  }

  // --------------------------------------------------------------------
  /**
   * implémentation de ITracer
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

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object,
   *      com.adonix.tracecli.CTraceBuffer, java.lang.Throwable)
   */
  public void trace(Object aObj, CTraceBuffer aTB, Throwable e)
  {
    trace(aObj, aTB.toString(), e);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, java.lang.String)
   */
  public abstract void trace(Object aObj, int aLevel, String aS);

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, java.lang.StringBuffer)
   */
  public void trace(Object aObj, int aLevel, StringBuffer aSB)
  {
    trace(aObj, aLevel, aSB.toString());
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.String)
   */
  public void trace(Object aObj, String aS)
  {
    trace(aObj, 1, aS);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.String,
   *      java.lang.Throwable)
   */
  public void trace(Object aObj, String aS, Throwable e)
  {
    CTraceBuffer wTB = new CTraceBuffer(512).append(aS).append('\n').appendThrowableDescr(e);
    trace(aObj, 0, wTB);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.StringBuffer)
   */
  public void trace(Object aObj, StringBuffer aSB)
  {
    trace(aObj, 1, aSB.toString());
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.StringBuffer,
   *      java.lang.Throwable)
   */
  public void trace(Object aObj, StringBuffer aSB, Throwable e)
  {
    trace(aObj, aSB.toString(), e);
  }
  /* (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, com.adonix.tracecli.CTraceBuffer)
   */
  public void trace(Object aObj,int aLevel,CTraceBuffer aTB)
  {
    trace(aObj, aLevel, aTB.toString());
  }
  final static String EMPTY = "";
  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, java.lang.Throwable)
   */
  public void trace(Object aObj, Throwable e)
  {
    trace(aObj, EMPTY, e);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.String)
   */
  public void trace(String aS)
  {
    trace(null, 1, aS);
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.StringBuffer)
   */
  public void trace(StringBuffer aSB)
  {
    trace(null, 1, aSB.toString());
  }

  /**
   * @param aPrefix
   * @param aLine
   */
  void traceInSysOut(byte[] aPrefix, byte[] aLine)
  {
    System.out.write(aPrefix,0,aPrefix.length);
    System.out.write(aLine,0,aLine.length);
    System.out.print('\n');
  }

  /*
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#traceLevelFilter(int)
   */
  public boolean traceLevelFilter(int aLevel)
  {
    return (pTraceSysOutOn);
  }

  /*
   * --------------------------------------------------------------------
   * 
   * (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#traceModuleFilter(java.lang.String)
   */
  public boolean traceModuleFilter(String aModuleName)
  {
    return true;
  }
}
