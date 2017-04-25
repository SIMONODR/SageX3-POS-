package com.adonix.tracecli;

public class CTracerConsole extends CAbstractTracer implements ITracer
{

  /**
   * 
   */
  public CTracerConsole()
  {
    super();
    setTraceSysOutOn();
  }
  
  /* (non-Javadoc)
   * @see com.adonix.tracecli.ITracer#trace(java.lang.Object, int, com.adonix.tracecli.CTraceBuffer)
   */
  public void trace(Object aObj,int aLevel,String aS)
  {
    if (isTraceSysOutOn()) 
    {
      traceInSysOut(buildPrefix(aObj,aLevel),  aS.getBytes());
    }
  }
  
}
