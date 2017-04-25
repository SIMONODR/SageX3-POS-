/*
 * Created on 10 oct. 2005
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
package com.adonix.util.encoder;

///Java 1.2
import java.io.FileOutputStream;
import java.io.File;
//FJava 1.2 */
/**
 * 
 * @author Adonix Grenoble
 * @version 14w
 */
public class CBase64Decoder
{
  /**
   * 
   */
  private byte[] pByteArray = null;

  /**
   * @param aAscii7bits
   */
  public CBase64Decoder(String aAscii7bits)
  {
    pByteArray = aAscii7bits == null ? new byte[0] : CBase64.decode(aAscii7bits);
  }

  /**
   * @return
   */
  public byte[] getBytes()
  {

    return pByteArray;
  }

  /**
   * @param aFile
   * @throws Exception
   */
  ///Java 1.2
  public void writeFile(File aFile) throws Exception
  {
    if (pByteArray != null && aFile != null)
    {
      FileOutputStream wStream = new FileOutputStream(aFile, true);
      wStream.write(pByteArray);
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
    if (pByteArray != null && aFilePath != null)
    {
      writeFile(new File(aFilePath));
    }
  }
  // FJava 1.2 */
}
