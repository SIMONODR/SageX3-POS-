/*
 * Created on 10 oct. 2005
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
package com.adonix.util.encoder;

import java.io.File;
///Java 1.2
import java.io.FileOutputStream;
// FJava 1.2 */

/**
 * 14w_011 - Intégration WebService
 * 
 * @author Adonix Grenoble
 * @version 14w
 */
public class CBase64Encoder
{

  /**
   * 
   */
  private String pBase64Str = null;

  /**
   * @param aByteArray
   */
  public CBase64Encoder(byte[] aByteArray)
  {
    pBase64Str = aByteArray == null ? "" : CBase64.encodeBytes(aByteArray);
  }

  /**
   * @param aFile
   */
  public CBase64Encoder(File aFile)
  {
    pBase64Str = aFile == null ? "" : CBase64.encodeFromFile(aFile.getAbsolutePath());
  }

  /**
   * @param aFilePath
   */
  public CBase64Encoder(String aFilePath)
  {
    pBase64Str = aFilePath == null ? "" : CBase64.encodeFromFile(aFilePath);
  }

  /**
   * @return
   */
  public String getString()
  {

    return pBase64Str;
  }

  /**
   * @param aFile
   * @throws Exception
   */
  ///Java 1.2
  public void writeFile(File aFile) throws Exception
  {
    if (pBase64Str != null && aFile != null)
    {
      FileOutputStream wStream = new FileOutputStream(aFile, true);
      wStream.write(pBase64Str.getBytes());
      wStream.close();
    }
  }
  // FJava 1.2 */

  /**
   * @param aFilePath
   * @throws Exception
   */
  ///Java 1.2
  public void writeFile(String aFilePath) throws Exception
  {
    if (pBase64Str != null && aFilePath != null)
    {
      writeFile(new File(aFilePath));
    }
  }
  // FJava 1.2 */
}
