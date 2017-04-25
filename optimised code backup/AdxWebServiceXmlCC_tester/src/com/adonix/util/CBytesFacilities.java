package com.adonix.util;


import com.adonix.util.encoder.CBase64Encoder;
import com.adonix.util.encoder.CBase64Decoder;
/**
 * @author Adonix Grenoble
 * @version 140_000
 */

public class CBytesFacilities
{
	
	/*
	 * ATTENTION: 
	 * Ces définitions sont utilisées par la procédure ANT de compilation automatique pour J#
	 * 
	 * 
	 * Notes
	 * La méthode GetEncoding s'appuie sur la plate-forme sous-jacente pour prendre en charge 
	 * la plupart des pages de codes.
	 * Toutefois, un support système est fourni pour les cas suivants : le codage par défaut, 
	 * c'est-à-dire celui spécifié dans les paramètres régionaux de l'ordinateur exécutant 
	 * cette méthode, Unicode avec primauté des bits de poids faible (UTF-16LE), Unicode avec 
	 * primauté des bits de poids fort (UTF-16BE), système d'exploitation Windows (windows-1252), 
	 * UTF-7, UTF-8, ASCII et GB18030 (Chinois simplifié).
	 * 
	 * Spécifiez l'un des noms répertoriés dans le tableau suivant pour obtenir le codage 
	 * pris en charge par le système qui correspond à cette page de codes.
	 * 
	 * page de codes nom 
	 * 1200 "UTF-16LE", "utf-16", "ucs-2", "unicode" ou "ISO-10646-UCS-2" 
	 * 1201 "UTF-16BE" ou "unicodeFFFE" 
	 * 1252 "windows-1252" 
	 * 65000 "utf-7", "csUnicode11UTF7", "unicode-1-1-utf-7", "unicode-2-0-utf-7", "x-unicode-1-1-utf-7" ou "x-unicode-2-0-utf-7" 
	 * 65001 "utf-8", "unicode-1-1-utf-8", "unicode-2-0-utf-8", "x-unicode-1-1-utf-8" ou "x-unicode-2-0-utf-8" 
	 * 20127 "us-ascii", "us", "ascii", "ANSI_X3.4-1968", "ANSI_X3.4-1986", "cp367", "csASCII", "IBM367", "iso-ir-6", "ISO646-US" ou "ISO_646.irv:1991"
	 * 54936 "GB18030" 
	 * 
	 * 
	 * 
	 * Use "UnicodeLittle" for little-endian Unicode (UTF-16LE) and "UnicodeBig" for big-endian Unicode (UTF-16BE)
	 * ShankarA, Microsoft Visual J# .NET Product Team.
	 * This posting is provided "AS IS" with no warranties, and confers no rights. Use of included 
	 * script samples are subject to the terms specified at http://www.microsoft.com/info/cpyright.htm 
	 * 
	 * String.getBytes("ASCII")
	 * String.getBytes("Cp1252")
	 * String.getBytes("ISO8859_1")
	 * String.getBytes("UTF8")
	 * String.getBytes("UTF-16")
	 * String.getBytes("UnicodeBig")
	 * String.getBytes("UnicodeBigUnmarked")
	 * String.getBytes("UnicodeLittle")
	 * String.getBytes("UnicodeLittleUnmarked")
	 */
	 
	/**
	 * java.nio.charset.Charset:
	 * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
	 */
	private final static String DEF_ISO_8859_1_CONSTANT_JAVA = "ISO-8859-1";
	/**
	 * .NET :
	 * 
	 */
	private final static String DEF_ISO_8859_1_CONSTANT_JSHARP = DEF_ISO_8859_1_CONSTANT_JAVA; // la même !
	/**
	 * java.nio.charset.Charset:
	 * Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
	 */
	private final static String DEF_US_ASCII_CONSTANT_JAVA = "US-ASCII";
	/**
	 * .NET:
	 * Encodes Unicode characters as single, 7-bit ASCII characters. 
	 * This encoding supports only character values between U+0000 and U+007F
	 */
	private final static String DEF_US_ASCII_CONSTANT_JSHARP = "ASCII";
	/**
	 * java.nio.charset.Charset:
	 * Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark
	 */
	private final static String DEF_UTF_16_CONSTANT_JAVA = "UTF-16";
	/**
	 * .NET :
	 * Encodes each Unicode character as two consecutive bytes, using little endian (code page 1200) byte ordering.
	 */
	private final static String DEF_UTF_16_CONSTANT_JSHARP = "Unicode";
	/**
	 * java.nio.charset.Charset:
	 * Sixteen-bit UCS Transformation Format, big-endian byte order
	 */
	private final static String DEF_UTF_16BE_CONSTANT_JAVA = "UTF-16BE";
	/**
	 * Encodes each Unicode character as two consecutive bytes, using big endian (code page 1201) byte ordering.
	 */
	private final static String DEF_UTF_16BE_CONSTANT_JSHARP = "UnicodeBig";
	/**
	 * java.nio.charset.Charset:
	 * Sixteen-bit UCS Transformation Format, little-endian byte order
	 */
	private final static String DEF_UTF_16LE_CONSTANT_JAVA = "UTF-16LE";
	/**
	 * .NET :
	 * Encodes each Unicode character as two consecutive bytes, using little endian (code page 1200) byte ordering.
	 */
	private final static String DEF_UTF_16LE_CONSTANT_JSHARP = "UnicodeLittle";
	/**
	 * .NET :
	 * Encodes Unicode characters using the UTF-7 encoding. (UTF-7 stands for UCS Transformation Format, 7-bit form.) 
	 * This encoding supports all Unicode character values and can be accessed as code page 65000.
	 */
	private final static String DEF_UTF_7_CONSTANT_JSHARP = "UTF7";
	/**
	 * java.nio.charset.Charset:
	 * Eight-bit UCS Transformation Format
	 */
	private final static String DEF_UTF_8_CONSTANT_JAVA = "UTF-8";
	/**
	 * .NET :
	 * Encodes Unicode characters using the UTF-8 encoding. (UTF-8 stands for UCS Transformation Format, 8-bit form.)
	 * This encoding supports all Unicode character values and can be accessed as code page 65001.
	 */
	private final static String DEF_UTF_8_CONSTANT_JSHARP = "UTF8";
	/**
	 * ATTENTION: 
	 * Ces définitions sont modifiables par la procédure ANT de compilation automatique pour J#.
	 * La procédure remplace "xxxxxxCONSTANT_JAVA; //EndDef" par "xxxxxxCONSTANT_JSHARP; //EndDef"
	 * Attention de laisser un espace entre le ";" et le commentaire...
	 */
	public final static String ENCODING_ISO_8859_1 = DEF_ISO_8859_1_CONSTANT_JAVA; //EndDef;
	public final static String ENCODING_US_ASCII = DEF_US_ASCII_CONSTANT_JAVA; //EndDef;
	public final static String ENCODING_UTF_16 = DEF_UTF_16_CONSTANT_JAVA; //EndDef;
	public final static String ENCODING_UTF_16BE = DEF_UTF_16BE_CONSTANT_JAVA; //EndDef;
	public final static String ENCODING_UTF_16LE = DEF_UTF_16LE_CONSTANT_JAVA; //EndDef;
	public final static String ENCODING_UTF_8 = DEF_UTF_8_CONSTANT_JAVA; //EndDef;
	/**
	 * 
	 */
	private final static char[] HEXA_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  
  /**
   * 14w_009 - Intégration WebServices
   */
  private final static String HEXA_BUFFER_DIGITS = ".x0123456789ABCDEF";
  
  /**
   * 14w_009 - Intégration WebServices
   */
  private final static String HEXA_PREFIX = ".x";
  /**
	 * 
	 */
	public static int LEN_OF_BYTE = 1;
	/**
	 * 
	 */
	public static int LEN_OF_INT = 4;
	/**
	 * 
	 */
	public static int LEN_OF_LONG = 8;
	/**
	 * 
	 */
	public static int LEN_OF_SHORT = 2;
	//--------------------------------------------------------------------
	/** 
	 * ajoute la représentation HEXA d'un octet dans un StringBuffer
	 */
	static public StringBuffer addByte2DecimalInSB(StringBuffer aSB, byte mybyte)
	{
		aSB.append('.');
		aSB.append('d');
		aSB.append(CStringFacilities.padNumString(mybyte, 3));
		return aSB;
	}

	//--------------------------------------------------------------------
	/** 
	 * ajoute la représentation HEXA d'un octet dans un StringBuffer
	 */
	static public StringBuffer addByte2HexaInSB(StringBuffer aSB, byte mybyte)
	{
		aSB.append('.');
		aSB.append('x');
		aSB.append(HEXA_DIGITS[(0x00F0 & mybyte) >> 4]);
		aSB.append(HEXA_DIGITS[0x000F & mybyte]);
		return aSB;
	}

	//--------------------------------------------------------------------
	/**
	 * Ajoute un "byte" (1) octets au buffer.
	 * @return la nouvelle position
	 */
	static public int appendByteInBuffer(byte[] aBuffer, int aPos, int aValue) throws ArrayIndexOutOfBoundsException
	{
		if (aBuffer.length - aPos < LEN_OF_BYTE)
			throw new IllegalArgumentException("Byte array should contain at least 2 bytes");

		aBuffer[aPos] = (byte) (aValue & 0x000000FF);

		return aPos + LEN_OF_BYTE;
	}
	//--------------------------------------------------------------------
	/**
	 * Ajoute un "short" 2 octets au buffer
	 * @return la nouvelle position
	 */
	static public int appendBytesInBuffer(byte[] aBuffer, int aPos, byte[] aInfo) throws ArrayIndexOutOfBoundsException
	{
		int wLen = aInfo.length;
		System.arraycopy(aInfo, 0, aBuffer, aPos, wLen);
		return aPos + wLen;
	}
	//--------------------------------------------------------------------
	/**
	 * Ajoute un "entier" 4 octets au buffer.
	 * @return la nouvelle position
	 */
	public static int appendBytetInBuffer(byte[] aBuffer, int aPos, int aValue) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_BYTE)
			throw new IllegalArgumentException("Byte array should contain at least " + LEN_OF_BYTE + " bytes");

		aBuffer[aPos] = (byte) (aValue & 0x000000FF);

		return aPos + LEN_OF_BYTE;
	}
	/**--------------------------------------------------------------------
	 * 
	 * @param aDestBuffer
	 * @param aDestPos
	 * @param aAddedBuffer
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	static public byte[] appendInExpendableBuffer(byte[] aDestBuffer, int aDestPos, byte[] aAddedBuffer) throws ArrayIndexOutOfBoundsException
	{
		return appendInExpendableBuffer(aDestBuffer,aDestPos,aAddedBuffer,0,aAddedBuffer.length);
	}
	/**--------------------------------------------------------------------
	 * Ajoute aAddedLen octets du aAddedBuffer a partir de aAddedOffset dans le buffer "extensible" aDestBuffer en position aDestPos
	 * 
	 * @param aDestBuffer
	 * @param aDestPos
	 * @param aAddedBuffer
	 * @param aOffset
	 * @param aAddedLen
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	static public byte[] appendInExpendableBuffer(byte[] aDestBuffer, int aDestPos, byte[] aAddedBuffer,int aAddedOffset,int aAddedLen) throws ArrayIndexOutOfBoundsException
	{
		if (aDestBuffer.length - aDestPos >= aAddedLen)
		{
			System.arraycopy(aAddedBuffer, aAddedOffset, aDestBuffer, aDestPos, aAddedLen);
			return aDestBuffer;
		}
		else
		{
			byte[] wBuffer = new byte[aDestPos + aAddedLen];
			System.arraycopy(aDestBuffer, 0, wBuffer, 0, aDestPos);
			System.arraycopy(aAddedBuffer, aAddedOffset, wBuffer, aDestPos, aAddedLen);
			return wBuffer;
		}
	}
	private final static String  MESS_NOT_ENOUGH_LARGE = "The byte array is not enough large. It should have at least [%s] bytes to accept an [%s]";
	/**--------------------------------------------------------------------
	 * @param aSize
	 * @param aContent
	 * @return
	 */
	private static String builNotEnoughLargeMess(int aSize,String aContent)
	{
		return CStringFacilities.sprintf(MESS_NOT_ENOUGH_LARGE,String.valueOf(aSize),aContent);
	}
	//--------------------------------------------------------------------
	/**
	 * Ajoute un "entier" 4 octets au buffer.
	 * @return la nouvelle position
	 */
	public static int appendIntInBuffer(byte[] aBuffer, int aPos, int aValue) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_INT)
			throw new IllegalArgumentException(builNotEnoughLargeMess( LEN_OF_INT,"int") );

		for (int i = 3; i >= 0; i--)
		{
			aBuffer[aPos + 3 - i] = (byte) (aValue >>> (8 * i));
		}
		return aPos + LEN_OF_INT;
	}
	//--------------------------------------------------------------------
	/**
	 * Ajoute un "long" 8 octets au buffer.
	 * @return la nouvelle position
	 */
	public static int appendLongInBuffer(byte[] aBuffer, int aPos, long aValue) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_LONG)
			throw new IllegalArgumentException(builNotEnoughLargeMess( LEN_OF_LONG,"long") );

		for (int i = 7; i >= 0; i--)
		{
			aBuffer[aPos + 7 - i] = (byte) (aValue >>> (8 * i));
		}
		return aPos + LEN_OF_LONG;
	}
	/**--------------------------------------------------------------------
	 * Ajoute un n octets contenant la valeur "aValue" au buffer.
	 * @param aBuffer
	 * @param aPos
	 * @param aValue
	 * @param aNb
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int appendNBytesInBuffer(byte[] aBuffer, int aPos, byte aValue, int aNb) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_LONG)
			throw new IllegalArgumentException("Byte array should contain at least 8 bytes");
		int wI = 0;
		while (wI < aNb)
		{
			aBuffer[aPos] = aValue;
			aPos++;
			wI++;
		}
		return aPos + aNb;
	}
	//--------------------------------------------------------------------
	/**
	 * Ajoute un "short" 2 octets au buffer.
	 * @return la nouvelle position
	 */
	static public int appendShortInBuffer(byte[] aBuffer, int aPos, int aValue) throws ArrayIndexOutOfBoundsException
	{
		if (aBuffer.length - aPos < LEN_OF_SHORT)
			throw new IllegalArgumentException("Byte array should contain at least 2 bytes");

		aBuffer[aPos] = (byte) ((aValue >>> 8) & 0x000000FF);
		aBuffer[aPos + 1] = (byte) (aValue & 0x000000FF);

		return aPos + LEN_OF_SHORT;
	}

	//--------------------------------------------------------------------
	/** 
	 * retourne la valeur ASCII d'un octet 
	 */
	static public int byte2CodeASCII(byte mybyte)
	{
		// the byte to translate is mybyte
		return (0x00FF & mybyte);
	}
	//--------------------------------------------------------------------
	/** 
	 * retourne la valeur ASCII d'un octet 
	 */
	static public String byte2Hexa(byte mybyte)
	{
		return new String(byte2HexaInChars(mybyte));
	}
	//--------------------------------------------------------------------
	static private char[] byte2HexaInChars(byte mybyte)
	{
		char[] wResult = new char[4];
		wResult[0] = '.';
		wResult[1] = 'x';
		wResult[2] = HEXA_DIGITS[(0x00F0 & mybyte) >> 4];
		wResult[3] = HEXA_DIGITS[0x000F & mybyte];
		return wResult;
	}
	/**--------------------------------------------------------------------
	 * dump d'un buffer sous forme d'une chaine Dé&cimale : .d001.d255 ...
	 * 
	 * @param aBuffer
	 * @param aOffset
	 * @param aLong
	 * @return
	 */
	static public String byteBufferBin2Decimal(byte[] aBuffer, int aOffset, int aLong)
	{
		if (aLong > aBuffer.length - aOffset)
		{
			StringBuffer wMess = new StringBuffer(256);
			wMess.append("byteBufferBin2Hexa : aLong < aBuffer.length - aOffset !");
			CStringFacilities.addInStringAttributs(wMess, "Buffer.length", aBuffer.length);
			CStringFacilities.addInStringAttributs(wMess, "Offset", aOffset);
			CStringFacilities.addInStringAttributs(wMess, "Long", aLong);
			return wMess.toString();
		}

		StringBuffer wSB = new StringBuffer(aLong * 4);
		int wI = aOffset;
		int wMax = aOffset + aLong;
		try
		{
			while (wI < wMax)
			{
				addByte2DecimalInSB(wSB, aBuffer[wI]);
				wI++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			wSB.append(" ArrayIndexOutOfBoundsException" + e.getLocalizedMessage());
		}
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * appel de la trace pour un buffer
	 */
	static public String byteBufferBin2Hexa(byte[] aBuffer)
	{
		return byteBufferBin2Hexa(aBuffer, 0, aBuffer.length);
	}
	/**--------------------------------------------------------------------
	 * dump d'un buffer sous forme d'une chaine Hex : .xFF.xFE ...
	 * 
	 * @param aBuffer
	 * @param aOffset
	 * @param aLong
	 * @return
	 */
	static public String byteBufferBin2Hexa(byte[] aBuffer, int aOffset, int aLong)
	{
		if (aLong > aBuffer.length - aOffset)
		{
			StringBuffer wMess = new StringBuffer(256);
			wMess.append("byteBufferBin2Hexa : aLong < aBuffer.length - aOffset !");
			CStringFacilities.addInStringAttributs(wMess, "Buffer.length", aBuffer.length);
			CStringFacilities.addInStringAttributs(wMess, "Offset", aOffset);
			CStringFacilities.addInStringAttributs(wMess, "Long", aLong);
			return wMess.toString();
		}

		StringBuffer wSB = new StringBuffer(aLong * 4);
		int wI = aOffset;
		int wMax = aOffset + aLong;
		try
		{
			while (wI < wMax)
			{
				addByte2HexaInSB(wSB, aBuffer[wI]);
				wI++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			wSB.append(" ArrayIndexOutOfBoundsException" + e.getLocalizedMessage());
		}
		return wSB.toString();
	}
  /**
   * 14w_009 - Intégration Webservices
   * 14w_011 - Intégration Webservices
   * 
   * appel de la trace pour un buffer
   */
  static public String byteBufferBin2Base64(byte[] aBuffer)
  {
    CBase64Encoder wBase64Encoder = new CBase64Encoder(aBuffer);
    
    return wBase64Encoder.getString();
  }
  /**
   * 14w_009 - Intégration Webservices
   * 14w_011 - Intégration Webservices
   * 
   * @param aBuffer
   * @param aOffset
   * @param aLong
   * @return
   */
  static public String byteBufferBin2Base64(byte[] aBuffer, int aOffset, int aLong)
  {
    byte[] wBuffer = new byte[aLong];
    
    System.arraycopy(aBuffer, aOffset, wBuffer, 0, aLong);
    
    return  byteBufferBin2Base64(wBuffer);
  }
  
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int bytesToInt(byte[] aBuffer) throws IllegalArgumentException
	{
		return bytesToInt(aBuffer, 0);
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aPos
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int bytesToInt(byte[] aBuffer, int aPos) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_INT)
			throw new IllegalArgumentException("Byte array should contain at least 4 bytes");
		int l = 0;
		for (int i = 0; i < LEN_OF_INT; i++)
		{
			l += (unsignedByteToLong(aBuffer[aPos + 3 - i]) << (8 * i));
		}
		return l;
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static long bytesToLong(byte[] aBuffer) throws IllegalArgumentException
	{
		return bytesToLong(aBuffer, 0);
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aPos
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static long bytesToLong(byte[] aBuffer, int aPos) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_LONG)
			throw new IllegalArgumentException("Byte array should contain at least 8 bytes");
		long l = 0;
		for (int i = 0; i < LEN_OF_LONG; i++)
		{
			l += (unsignedByteToLong(aBuffer[aPos + 7 - i]) << (8 * i));
		}
		return l;
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aPos
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static short bytesToShort(byte[] aBuffer, int aPos) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_SHORT)
			throw new IllegalArgumentException("Byte array should contain at least 2 bytes");

		short l = 0;
		for (int i = 0; i < LEN_OF_SHORT; i++)
		{
			l += (unsignedByteToLong(aBuffer[aPos + 1 - i]) << (8 * i));
		}
		return l;
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aPos
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int bytesToUnsignedShort(byte[] aBuffer, int aPos) throws IllegalArgumentException
	{
		if (aBuffer.length - aPos < LEN_OF_SHORT)
			throw new IllegalArgumentException("Byte array should contain at least 2 bytes");

		int l = 0;
		for (int i = 0; i < LEN_OF_SHORT; i++)
		{
			l += (unsignedByteToLong(aBuffer[aPos + 1 - i]) << (8 * i));
		}
		return l;
	}	

	/**--------------------------------------------------------------------
	 * @param aLong
	 * @return
	 */
	public static byte[] byteToBytes(byte aByte)
	{
		byte[] wBuff = new byte[1];
		wBuff[0] = aByte;
		return wBuff;
	}
	//--------------------------------------------------------------------
	private static long getSignedByte(byte b)
	{
		return (b & 127);
	}
  
  /**
   * 14w_009 - Intégration WebServices
   * 14w_011 - Intégration WebServices
   * 
   * @param aBase64Buffer
   * @return
   * @throws IllegalArgumentException
   */
  public static byte[] base64BufferToBytes(String aBase64Buffer)
  {
    CBase64Decoder wBase64Decoder = new CBase64Decoder(aBase64Buffer);
    
    return wBase64Decoder.getBytes();
  }
	/**
   * 14w_009 - Intégration WebServices
   * 
	 * @param aHexaBuffer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static byte[] hexaBufferToBytes(String aHexaBuffer) throws IllegalArgumentException
	{
	  /*
     * test complet du buffer 
	   */
    testHexaBufferBytes(aHexaBuffer,true);
    
    
		int wStreamSize = aHexaBuffer.length() / 4;

		byte[] wBytes = new byte[wStreamSize];

		String wHexa;
		int wOffsetNextDot;
		int wOffsetDot = 0;
		int wI = 0;
		while (wI < wStreamSize)
		{
			wOffsetNextDot = aHexaBuffer.indexOf('.', wOffsetDot + 1);
			if (wOffsetNextDot == -1)
				wOffsetNextDot = aHexaBuffer.length();

			wHexa = aHexaBuffer.substring(wOffsetDot + 2, wOffsetNextDot).toLowerCase();

			try
			{
				wBytes[wI] = (byte) (Integer.parseInt(wHexa, 16) & 0x000000FF);
			}
			catch (Throwable e)
			{
				throw new IllegalArgumentException(
					"Can't parse hexa value [" + wHexa + "] localized at offset [" + (wOffsetDot + 2) + "] in hexaBuffer [" + aHexaBuffer + "]");
			}
			wOffsetDot = wOffsetNextDot;
			wI++;
		}

		return wBytes;
	}
  /**
   * 14w_009 - Intégration WebServices
   * 
   * @param aHexaBuffer
   * @return
   * @throws IllegalArgumentException
   */
  public static boolean isHexaBufferBytes(String aHexaBuffer)
  {
    return testHexaBufferBytes(aHexaBuffer,false);
  }


  /**
   * 14w_009 - Intégration WebServices
   * 
   * @param aHexaBuffer
   * @param aThrowingException
   * @return
   * @throws IllegalArgumentException
   */
  private static boolean testHexaBufferBytes(String aHexaBuffer,boolean aThrowingException) throws IllegalArgumentException
   {
    if (aHexaBuffer==null)
    {
      if(aThrowingException)
        throwBadHexaBufferBytes("The buffer is null." );
      else
        return false;
    }
    if ((aHexaBuffer.length() % 4) != 0)
    {
      if(aThrowingException)
        throwBadHexaBufferBytes( "The buffer length is ["+aHexaBuffer.length()+"]" );
      else
        return false;
    }
    if (!aHexaBuffer.startsWith(HEXA_PREFIX))
    {
      if(aThrowingException)
        throwBadHexaBufferBytes("The buffer des not start with ["+HEXA_PREFIX+"]." );
      else
        return false;
    }
    
    return testCharsOfHexaBufferBytes(aHexaBuffer,aThrowingException);
  }
  
  /**
   * 14w_009 - Intégration WebServices
   * 
   * @param aHexaBuffer
   * @param aThrowingException
   * @return
   * @throws IllegalArgumentException
   */
  private static boolean testCharsOfHexaBufferBytes(String aHexaBuffer,boolean aThrowingException)throws IllegalArgumentException
  {
    int wMax = aHexaBuffer.length();
    char wChar;
    int wI = 0;
    while (wI < wMax)
    {
      wChar = aHexaBuffer.charAt(wI);
      if ( HEXA_BUFFER_DIGITS.indexOf(wChar)<0 )
      {
        if (aThrowingException)
          throwBadHexaBufferBytes("At the offset ["+wI+"], the buffer contains the char ["+wChar+"]  which is not a valid character ("+HEXA_PREFIX+")." );
        else
          return false;
      }
      wI++;
    }
    return true;
  }
  /**
   * 14w_009 - Intégration WebServices
   * 
   * @throws IllegalArgumentException
   */
  private static void throwBadHexaBufferBytes(String aMess) throws IllegalArgumentException
  {
    String wMess = "Hexa string length should be at least a multiple of 4 characters like \".xFF.x0A\";";
    if (aMess!=null && aMess.length()>0)
    {
      wMess += aMess;
    }
    throw new IllegalArgumentException(wMess);
  }

	/**--------------------------------------------------------------------
	 * @param aInt
	 * @return
	 */
	public static byte[] intToBytes(int aInt) 
	{
		byte[] wBuffer = new byte[LEN_OF_INT];
		appendIntInBuffer(wBuffer, 0,aInt);
		return wBuffer;
	}
	//--------------------------------------------------------------------
	public static byte[] intToOneByte(int aValue)
	{
		byte[] wBuff = new byte[1];
		wBuff[0] = (byte) (aValue & 0x000000FF);
		return wBuff;
	}

	/**--------------------------------------------------------------------
	 * @param aLong
	 * @return
	 */
	public static byte[] longToBytes(long aLong) 
	{
		byte[] wBuffer = new byte[LEN_OF_LONG];
		appendLongInBuffer( wBuffer, 0,aLong);
		return wBuffer;
	}
	/**--------------------------------------------------------------------
	 * One byte oo Int
	 * @param aBuffer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int oneByteToInt(byte[] aBuffer, int aPos) throws IllegalArgumentException
	{
		return unsignedByteToInt( aBuffer[aPos]);
	}
	/**--------------------------------------------------------------------
	 * @param aShort
	 * @return
	 */
	public static byte[] shortToBytes(short aShort) 
	{
		byte[] wBuffer = new byte[LEN_OF_SHORT];
		appendShortInBuffer(wBuffer, 0,aShort);
		return wBuffer;
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aPrefix
	 * @return
	 */
	public static boolean startWith(byte[] aBuffer, byte[] aPrefix)
	{

		if (aBuffer == null || aPrefix == null || aBuffer.length < aPrefix.length)
		{
			return false;
		}

		int wMax = aPrefix.length;
		int wI = 0;
		while (wI < wMax)
		{
			if (aBuffer[wI] != aPrefix[wI])
			{
				return false;
			}
			wI++;
		}

		return true;
	}
	/**--------------------------------------------------------------------
	 * @param aBuffer
	 * @param aHexaPrefix
	 * @return
	 */
	public static boolean startWith(byte[] aBuffer, String aHexaPrefix)
	{
		return startWith(aBuffer, hexaBufferToBytes(aHexaPrefix));
	}
	//--------------------------------------------------------------------
	public static int unsignedByteToInt(byte b)
	{
		return (b & 128) + (b & 127);
	}
	//--------------------------------------------------------------------
	public static long unsignedByteToLong(byte b)
	{
		return (b & 128) + (b & 127);
	}

}