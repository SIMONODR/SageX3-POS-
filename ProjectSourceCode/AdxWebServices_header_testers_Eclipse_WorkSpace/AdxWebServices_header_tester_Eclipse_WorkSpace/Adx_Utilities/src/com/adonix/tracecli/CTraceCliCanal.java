package com.adonix.tracecli;

/**
 * @author Adonix Grenoble
 * @deprecated
 * @version 1.0
 */
public class CTraceCliCanal extends CTraceCliChanel
{
  //--------------------------------------------------------------------
  /** 
   * constructeur explicite.
   * @param aCanalName nom du canal
   * @param aViewer  CTraceCli.VIEWER_STD ou CTraceCli.VIEWER_LOG
   * @param aBufferSize taille du buffer de trace
   */
  public CTraceCliCanal(CTraceCli aTraceCli,String aCanalName,int aLevel,String aViewer,int aBufferSize)
  {
    super(aTraceCli,aCanalName,aLevel,aViewer,aBufferSize);
  }  
  //--------------------------------------------------------------------
  /** constructeur avec des valeurs par défaut*/
  public CTraceCliCanal(CTraceCli aTraceCli,String aCanalName,int aLevel)
  {
    super(aTraceCli,aCanalName,aLevel,CTraceCli.MERGER_VIEW,0);
  }
}
