package com.adonix.util;

/**
 * @author Adonix Grenoble
 * @version 140_000
 */

public class CCharFacilities
{
	//--------------------------------------------------------------------
	/**
	 * 
	 */
	public static int LEN_OF_CHAR_UTF16 = 2;
	/**
	 * 
	 */
	public static int LEN_OF_CHAR_UTF16BE = LEN_OF_CHAR_UTF16;
	/**
	 * 
	 */
	public static int LEN_OF_CHAR_UTF16LE = LEN_OF_CHAR_UTF16;
	//--------------------------------------------------------------------
	/**
   * retourne la valeur ASCII d'un octet ou -1 si > FF
   */
	public static int char2CodeASCII(char aChar)
	{
		int aVal = (int)aChar;
		if (aVal>255) 
			return -1;
		else
			return  (0x00FF & aVal);
	}
	//--------------------------------------------------------------------
	public static char int2Char(int aInt)
	{
		return (char)(aInt);
	}
	//--------------------------------------------------------------------
	public static char int2Char(char aStartChar,int aInt)
	{
		return (char)( (int)aStartChar + aInt);
	}	
	//--------------------------------------------------------------------
	private static char buildChar(int aStartInt,int aInt)
	{
		return  (char)(aStartInt + aInt);
	}
}
