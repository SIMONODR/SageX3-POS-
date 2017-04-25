package com.adonix.util;

import java.util.*;

/**
 * Regroupement de fonction de manipulation de Chaine , Date, et Caractères
 * Décomposition des paramètres URL
 * @author Adonix Grenoble
 * @version 140_000
 */

public class CStringFacilities
{

	public final static String ATTRIB_VALUE_START = "=\"";
	private final static String CHAR_BEL = "#Bel";
	private final static String CHAR_CR = "#cr";
	private final static String CHAR_LF = "#lf";
	private static final char CHAR_OD = 0x0D;
	private final static String CHAR_TAB = "#tab";
	public final static String CHARS_ACCENT = "^¨`";
	public final static String CHARS_PONCTUATION = ".,;:!?'\"";
	public final static String CHARS_SIGNE = "&§%$£@#~/|*-+=";
	public final static char DESCR_VALUE_END = ']';
	public final static String DESCR_VALUE_START = "=[";
	private static final String FIELD_WHAY_NAME = "pWhy";
	final static char NO_SEPARATOR = '?';
	private static final String PREFIX_CLASS = "class:";
	private static final String PREFIX_MESS = "mess:";
	private static final String PREFIX_STACK = "stack:";
	private static final String PREFIX_WHY = "why:";
	public final static char SEPARATOR_THOUSAND = '.';
	private static final String STACK_PREFIX = "\tat ";
	public final static String NULL = "null";
	public final static String EMPTY = "";

	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	static public StringBuffer addeToStringOneLineInSB(StringBuffer aSB, Throwable e)
	{
		aSB.append(eClassToString(e)).append(',');
		if (e instanceof CException)
		{
			aSB.append(eWhyToString((CException) e)).append(',');
		}
		aSB.append(eMessToString(e)).append(',');
		aSB.append(firstLineOfeStackInString(e));
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aCode
	 */
	private final static void addHtmlCode(StringBuffer aSB, int aCode)
	{
		aSB.append('&').append('#').append(String.valueOf(aCode)).append(';');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringParams(StringBuffer aSB, String aId, String aValue)
	{
		aSB.append(aId).append('=').append(aValue).append(';');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringParams(StringBuffer aSB, String aId, int aValue)
	{
		aSB.append(aId).append('=').append(aValue).append(';');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringAttributs(StringBuffer aSB, String aId, char aValue)
	{
		aSB.append(' ').append(aId).append(ATTRIB_VALUE_START).append(aValue).append('\"');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringAttributs(StringBuffer aSB, String aId, int aValue)
	{
		aSB.append(' ').append(aId).append(ATTRIB_VALUE_START).append(aValue).append('\"');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringAttributs(StringBuffer aSB, String aId, long aValue)
	{
		aSB.append(' ').append(aId).append(ATTRIB_VALUE_START).append(aValue).append('\"');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringAttributs(StringBuffer aSB, String aId, String aValue)
	{
		aSB.append(' ').append(aId).append(ATTRIB_VALUE_START).append(aValue).append('\"');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, boolean aValue)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append(DESCR_VALUE_END);
	}

	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aClass
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, Class aClass)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(getClasseName(aClass)).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aDescriber
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, IDescriber aDescriber)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START);
		aDescriber.addDescrInSB(aSB);
		aSB.append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, int aValue)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, int aValue, int aValueB)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append('|').append(aValueB).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, long aValue)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, long aValue, long aValueB)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append('|').append(aValueB).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, String aValue)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value]" dans le StringBuffer en cadrant la valeur sur "aValueSize" caractères.
	 * @param aSB
	 * @param aId
	 * @param aValueSize
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, Integer aValueSize, String aValue)
	{
		aSB.append(' ');
		aSB.append(aId);
		aSB.append(DESCR_VALUE_START);
		addAlignStringToLeftInSB(aSB, aValue, aValueSize.intValue());
		aSB.append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value]" dans le StringBuffer en cadrant l'id sur "aIdSize" caractères
	 * et la valeur sur "aValueSize" caractères
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValueSize
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, Integer aIdSize, String aId, Integer aValueSize, String aValue)
	{
		aSB.append(' ');
		addAlignStringToLeftInSB(aSB, aId, aIdSize.intValue());
		aSB.append(DESCR_VALUE_START);
		addAlignStringToLeftInSB(aSB, aValue, aValueSize.intValue());
		aSB.append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value]" dans le StringBuffer en cadrant l'id sur "aIdSize" caractères.
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValueSize
	 * @param aValue
	 */
	public static void addInStringDescr(StringBuffer aSB, Integer aIdSize, String aId, String aValue)
	{
		aSB.append(' ');
		addAlignStringToLeftInSB(aSB, aId, aIdSize.intValue());
		aSB.append(DESCR_VALUE_START);
		aSB.append(aValue);
		aSB.append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value|valueB]" dans le StringBuffer en cadrant l'id sur "aIdSize" caractères.
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public static void addInStringDescr(StringBuffer aSB, Integer aIdSize, String aId, String aValue, String aValueB)
	{
		aSB.append(' ');
		addAlignStringToLeftInSB(aSB, aId, aIdSize.intValue());
		aSB.append(DESCR_VALUE_START);
		aSB.append(aValue).append('|').append(aValueB);
		aSB.append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	static public StringBuffer addAlignStringToLeftInSB(StringBuffer aSB, String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
		{
			aSB.append(aValue).append(buildStringFromChar(CConstants.CHAR_SPACE, aLen - wLen));
		}
		else if (wLen > aLen)
		{
			aSB.append(aValue.substring(0, aLen));
		}
		else
		{
			aSB.append(aValue);
		}

		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, String aValue, long aValueB)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append('|').append(aValueB).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, String aValue, String aValueB)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append('|').append(aValueB).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 * @param aValueC
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, String aValue, String aValueB, String aValueC)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append('|').append(aValueB).append('|').append(aValueC).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aId
	 * @param e
	 */
	public static void addInStringDescr(StringBuffer aSB, String aId, Throwable e)
	{
		aSB.append(' ').append(aId).append(DESCR_VALUE_START).append(e.toString()).append(DESCR_VALUE_END);
	}
	/**--------------------------------------------------------------------
	 * @param aParamstring
	 * @param aParamId
	 * @param aValue
	 */
	private static void addOneParam(StringBuffer aParamstring, String aParamId, String aValue)
	{
		aParamstring.append(aParamId).append('=').append(aValue).append('&');
	}
	/**--------------------------------------------------------------------
	 * Aligne un chaîne à gauche en ajoutant a gauche les n spaces nécessaires.
	 * 
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	static public String alignStringToLeft(String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
		{
			return new StringBuffer(aLen).append(aValue).append(buildStringFromChar(CConstants.CHAR_SPACE, aLen - wLen)).toString();
		}
		if (wLen > aLen)
			return aValue.substring(0, aLen);

		return aValue;
	}

	/**--------------------------------------------------------------------
	 * Aligne un chaîne à droite en ajoutant a gauche les n spaces nécessaires.
	 * 
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	static public String alignStringToRight(String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
			return new StringBuffer(buildStringFromChar(CConstants.CHAR_SPACE, aLen - wLen)).append(aValue).toString();

		if (aValue.length() > aLen)
			return aValue.substring(aValue.length() - aLen);

		return aValue;
	}
	//--------------------------------------------------------------------
	public static String[] argumentSubstitution(String[] args, Hashtable vars)
	{
		//StringBuffer argBuf = new StringBuffer();

		String[] result = new String[args.length];

		for (int aIdx = 0; aIdx < args.length; ++aIdx)
		{
			String argStr = args[aIdx];

			int index = argStr.indexOf('$');

			if (index < 0)
			{
				result[aIdx] = argStr;
				continue;
			}
			else
			{
				result[aIdx] = CStringFacilities.stringSubstitution(argStr, vars);
			}
		}

		return result;
	}
	//--------------------------------------------------------------------
	static public String ascii2HexString(int aCodeASCII)
	{
		String wHexa = Integer.toHexString(aCodeASCII).toUpperCase();
		int wLenHexa = wHexa.length();
		if (wLenHexa == 2)
			return wHexa;
		else if (wLenHexa > 2)
			return wHexa.substring(wLenHexa - 2);
		else
			return "0" + wHexa;

	}

	//--------------------------------------------------------------------
	static public String ascii2String(int aCodeASCII)
	{
		StringBuffer wSB = new StringBuffer(6).append('(');
		switch (aCodeASCII)
		{
			case 7 :
				wSB.append(CHAR_BEL);
				break;
			case 9 :
				wSB.append(CHAR_TAB);
				break;
			case 13 :
				wSB.append(CHAR_CR);
				break;
			case 10 :
				wSB.append(CHAR_LF);
				break;
			default :
				wSB.append('x').append(ascii2HexString(aCodeASCII));
				break;
		}
		wSB.append(')');
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * converti les caractère "binaire" d'une chaine sans toucher les autre
	 */
	static public String binCharInString2Hexa(byte[] wBuffer)
	{

		StringBuffer wSB = new StringBuffer(wBuffer.length * 2);
		byte wCurentByte;
		int wByteValue;
		int wI = 0;
		int wMax = wBuffer.length;
		while (wI < wMax)
		{
			wCurentByte = wBuffer[wI];
			wByteValue = new Byte(wCurentByte).intValue();
			if (wByteValue < 32)
				wSB.append(ascii2String(wByteValue));
			else if (wByteValue > 127)
				wSB.append(ascii2String(wByteValue));
			else
				wSB.append((char) wCurentByte);
			wI++;
		}
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * converti les caractère "binaire" d'une chaine sans toucher les autre
	 */
	static public String binCharInString2Hexa(String aString)
	{
		if (aString != null)
		{
			return binCharInString2Hexa(aString.getBytes());
		}
		else
		{
			return "";
		}
	}

	//--------------------------------------------------------------------
	/** appel de la trace pour un buffer
	 * @deprecated */
	static public String bufferBin2String(char[] aBuffer, int aOffset, int aLong)
	{
		return charBufferBin2String(aBuffer, aOffset, aLong);
	}

	//--------------------------------------------------------------------
	public static String buildParamsString(Properties aTable)
	{
		Enumeration wNames = aTable.keys();
		StringBuffer wParams = new StringBuffer();
		String wKey;
		while (wNames.hasMoreElements())
		{
			wKey = (String) wNames.nextElement();
			addOneParam(wParams, wKey, aTable.getProperty(wKey));
		}
		return wParams.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * construit unechaine de N caractères.
	 * */
	static public String buildStringFromChar(char aChar, int aLen)
	{
		char[] wBuffer = new char[aLen];
		for (int wI = 0; wI < aLen; wI++)
		{
			wBuffer[wI] = aChar;
		}
		return String.valueOf(wBuffer);
	}
	/**
   * 14w_015 - Lisibilité des traces (2)
   * 
	 * construit unechaine de N chaînes.
	 * */
	static public String buildStringFromString(String aStr, int aNb)
	{
    if (aNb==0)return EMPTY;
		int wLen = 0;
		if (aStr != null && aStr.length() > 0)
			wLen = aNb * aStr.length();
		StringBuffer wSB = new StringBuffer(wLen);
		if (wLen > 0)
		{
			for (int wI = 0; wI < aNb; wI++)
			{
				wSB.append(aStr);
			}
		}
		return wSB.toString();
	}

	//--------------------------------------------------------------------
	/**
	 * retourne la vaelur ASCII d'un octet
	 * @deprecated voir dans CBytesFacilities
	 */
	static public int byte2CodeASCII(byte mybyte)
	{
		// the byte to translate is mybyte
		return (0x00FF & mybyte);
	}
	//--------------------------------------------------------------------
	/**
	 * appel de la trace pour un buffer
	 * @deprecated voir dans CBytesFacilities
	 */
	static public String byteBufferBin2Hexa(byte[] aBuffer, int aOffset, int aLong)
	{
		return CBytesFacilities.byteBufferBin2Hexa(aBuffer, aOffset, aLong);
	}
	//--------------------------------------------------------------------
	/**
	 * retourne la vaelur ASCII d'un octet
	 * @deprecated voir dans CCharFacilities
	 */
	static public int char2CodeASCII(char aChar)
	{
		return CCharFacilities.char2CodeASCII(aChar);
	}
	//--------------------------------------------------------------------
	/** appel de la trace pour un buffer */
	static public String charBufferBin2Hexa(char[] aBuffer, int aOffset, int aLong)
	{
		String wS = String.valueOf(aBuffer, aOffset, aLong);
		return CBytesFacilities.byteBufferBin2Hexa(wS.getBytes(), 0, wS.length());
	}
	/**--------------------------------------------------------------------
	 * appel de la trace pour un buffer
	 * 
	 * @param aBuffer
	 * @param aOffset
	 * @param aLong
	 * @return
	 */
	static public String charBufferBin2String(char[] aBuffer, int aOffset, int aLong)
	{
		StringBuffer wResult = new StringBuffer(aLong + 256); // hypothèse !
		char wEntry;
		//int wCodeASCII;
		int wI = aOffset;
		int wMax = aLong;
		try
		{
			while (wI < wMax)
			{

				wEntry = aBuffer[wI];

				if (Character.isISOControl(wEntry))
				{
					wResult.append(ascii2String(char2CodeASCII(wEntry)));

				}
				else
				{
					wResult.append(wEntry);
				}
				wI++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			wResult.append("");
		}
		return wResult.toString();
	}
	/**--------------------------------------------------------------------
	 * @param aStr
	 * @return
	 */
	public static String cleanStartOfString(String aStr)
	{
		if (aStr!=null && aStr.length()>0)
		{
			int wI = 0;
			while (wI<aStr.length() && aStr.charAt(wI) < 32)
			{
				wI++;
			}
			if (wI>0)
			{
				aStr = aStr.substring(wI);
			}
		}
		return aStr;
	}
	/**--------------------------------------------------------------------
	 * compte le nombre de caractère 'aChar' présent dans la chaîne aString
	 * 
	 * @param aChar
	 * @param aAdapiTypedString
	 * @return
	 */
	static public int countChar(char aChar, String aString)
	{
		int wMax = aString.length();
		int wCount = 0;
		int wI = 0;
		while (wI < wMax)
		{
			if (aString.charAt(wI) == aChar)
				wCount++;
			wI++;
		}
		return wCount;
	}
	/**--------------------------------------------------------------------
	 * Retourne la composante (classe) d'une exception dans une string
	 * 
	 * @param e
	 * @return
	 */
	private static String eClassToString(Throwable e)
	{
		return new StringBuffer(32).append(PREFIX_CLASS).append('[').append(e.getClass().getName()).append(']').toString();
	}
	/**--------------------------------------------------------------------
	 * Retourne la composante (mess) d'une exception dans une string
	 * 
	 * @param e
	 * @return
	 */
	private static String eMessToString(Throwable e)
	{
		return new StringBuffer(64).append(PREFIX_MESS).append('[').append(e.getMessage()).append(']').toString();
	}
	/**--------------------------------------------------------------------
	 * Retourne la stack d'une exception dans une String
	 * 
	 * @param e
	 * @return
	 */
	static public String eStackToString(Throwable e)
	{
		try
		{
			java.io.StringWriter wSW = new java.io.StringWriter();

			e.printStackTrace(new java.io.PrintWriter(wSW));

			String wS = wSW.toString();

			int wPos = wS.indexOf(STACK_PREFIX);
			if (wPos > -1)
			{
				wS = wS.substring(wPos);
				wS = wS.replace('\t', '-');
				wS = wS.replace(CHAR_OD, ',');
				if (wS.lastIndexOf('\n') == wS.length() - 1)
					wS = wS.substring(0, wS.length() - 1);
			}
			return wS;
		}
		catch (Exception e2)
		{
			return "no stack";
		}
	}
	/**--------------------------------------------------------------------
	 * Retourne la stack d'une exception dans une String
	 * 
	 * @param e
	 * @return
	 */
	static public String[] eStackToTblString(Throwable e)
	{
		return tabulizeTokenizer(new StringTokenizer(eStackToString(e), "\n"));
	}
	/**--------------------------------------------------------------------
	 * Retourne les trois composantes (classe,mess,stack) d'une exception dans une string
	 * 
	 * @param e
	 * @return
	 */
	static public String eToString(Throwable e)
	{
		StringBuffer wSB = new StringBuffer(500);
		wSB.append(eClassToString(e)).append('\n');
		if (e instanceof CException)
		{
			wSB.append(eWhyToString((CException) e)).append('\n');
		}
		wSB.append(eMessToString(e)).append('\n');
		wSB.append(PREFIX_STACK).append('[').append(eStackToString(e)).append(']').append('\n');
		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	static public String eToStringOneLine(Throwable e)
	{
		return addeToStringOneLineInSB(new StringBuffer(500), e).toString();
	}
	//--------------------------------------------------------------------
	/**Retourne les trois composantes (classe,mess,stack) d'une exception
	 * dans une table string  */
	static public String[] eToTblString(Throwable e)
	{
		String[] wTblStrStack = eStackToTblString(e);
		int wNbLine = wTblStrStack.length + 2; //stack + class + mess
		if (e instanceof CException)
			wNbLine++;
		String[] wTblStr = new String[wNbLine];

		int wNumLine = 0;
		wTblStr[wNumLine] = eClassToString(e);

		if (e instanceof CException)
		{
			wNumLine++;
			wTblStr[wNumLine] = eWhyToString((CException) e);
		}

		wNumLine++;
		wTblStr[wNumLine] = eMessToString(e);

		for (int wI = 0; wI < wTblStrStack.length; wI++)
		{
			wNumLine++;
			wTblStr[wNumLine] = wTblStrStack[wI];
		}
		return wTblStr;
	}
	//--------------------------------------------------------------------
	/**Retourne la composante (why) d'une exception dans une string  */
	private static String eWhyToString(CException e)
	{
		return new StringBuffer(32).append(PREFIX_WHY).append('[').append(e.getWhyString()).append(']').toString();
	}
	//--------------------------------------------------------------------
	/** 
	 * Retourne la première ligne de la stack d'une exception dans une String
	 */
	public static String firstLineOfeStackInString(Throwable e)
	{
		try
		{
			java.io.StringWriter wSW = new java.io.StringWriter();

			e.printStackTrace(new java.io.PrintWriter(wSW));

			String wS = wSW.toString();

			int wPos = wS.indexOf(STACK_PREFIX);
			if (wPos > -1)
			{
				wS = wS.substring(wPos);
				wPos = wS.indexOf('\n');
				if (wPos > -1)
					wS = wS.substring(0, wPos);
				wS = wS.replace('\t', '-');
				wS = wS.replace(CHAR_OD, ' ');

			}
			return wS;
		}
		catch (Throwable e2)
		{
			return "can't get firstLineOfStackInString ! " + e2.getMessage();
		}
	}
	//--------------------------------------------------------------------
	/**
	 * formate un entier "court" en mettant le séparateur de millier par défaut
	 */
	static public String formatNumString(int aValue)
	{
		return formatNumString(String.valueOf(aValue));
	}

	/**=======================================================================
	 * FORMATAGE
	 *======================================================================*/

	//--------------------------------------------------------------------
	/**
	 * formate un entier "long" en mettant le séparateur de millier par défaut
	 */
	static public String formatNumString(long aValue)
	{
		return formatNumString(String.valueOf(aValue));
	}
	//--------------------------------------------------------------------
	/**
	 * formate un une chaîne contenant un entier en mettant le séparateur de millier par défaut
	 */
	static public String formatNumString(String aValue)
	{
		return formatNumString(aValue, SEPARATOR_THOUSAND);
	}
	//--------------------------------------------------------------------
	/**
	 * formate un une chaîne contenant un entier en mettant un séparateur de millier
	 */
	static public String formatNumString(String aValue, char aSep)
	{

		StringBuffer wSB = new StringBuffer(16); //hypothèse !
		int wI = 0;
		char wChar;

		int Max = aValue.length();
		while (wI < Max)
		{
			wChar = aValue.charAt(wI);
			if ((((Max - wI) % 3) == 0) && (wI < Max) && (wI > 0))
				wSB.append(aSep);
			wSB.append(wChar);
			wI++;
		}
		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aObject
	 * @return
	 */
	public static StringBuffer addClasseNameInSB(StringBuffer aSB, Class aClass)
	{
		String wName = NULL;
		if (aClass != null)
		{

			wName = aClass.getName();
			int wPos = wName.lastIndexOf('.');
			if (wPos > -1 && wPos < wName.length() - 1)
			{
				wName = wName.substring(wPos + 1);
			}
			wPos = wName.lastIndexOf('$');
			if (wPos > -1 && wPos < wName.length() - 1)
			{
				wName = wName.substring(wPos + 1);
			}
		}
		aSB.append( wName);
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aClass
	 * @return
	 */
	public static String getClasseName(Class aClass)
	{
		return addClasseNameInSB(new StringBuffer(64), aClass).toString();
		
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aObject
	 * @return
	 */
	public static StringBuffer addClasseNameAndCodeInSB(StringBuffer aSB, Object aObject)
	{
		if (aObject == null)
		{
			aSB.append(NULL);
		}
		else
		{
			aSB.append(getClasseName(aObject.getClass())).append('_').append(aObject.hashCode());
		}
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aObject
	 * @return
	 */
	public static String getClasseNameAndCode(Object aObject)
	{
		return addClasseNameAndCodeInSB(new StringBuffer(64), aObject).toString();
	}

	//--------------------------------------------------------------------
	// AAAA MM JJ
	//--------------------------------------------------------------------

	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "aaaa-mm-jj".
	 * */
	static public String getTimeStringAAAAMMJJ()
	{
    /*
     * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
     */
		return getTimeStringAAAAMMJJ('-');
	}
  /**
   * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
   * 
   * @param aSep
   * @return
   */
  static public String getTimeStringAAAAMMJJ(char aSep)
  {
    return time2StringSepAAAAMMJJ(0, aSep);
  }
	/**=======================================================================
	 * HH MM SS
	 *======================================================================*/
	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "hh:mm:ss" */
	static public String getTimeStringHHMMSS()
	{
    /*
     * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
     */
		return getTimeStringHHMMSS(':');
	}
  
  /**
   * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
   * 
   * @param aSep
   * @return
   */
  static public String getTimeStringHHMMSS(char aSep)
  {
    return time2StringSepHHMMSS(0, aSep);
  }
	/**=======================================================================
	 * JJ MM AAAA
	 *======================================================================*/
	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "jj-mm-aaaa".
	 * */
	static public String getTimeStringJJMMAAAA()
	{
    /*
     * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
     */
		return getTimeStringJJMMAAAA( '-');
	}
  
  /**
   * 14w_019 Enrichissement des utilitaires de manipulation des chaines date et heure
   * 
   * @param aSep
   * @return
   */
  static public String getTimeStringJJMMAAAA(char aSep)
  {
    return time2StringSepJJMMAAAA(0, aSep);
  }
	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "jj/mm/aaaa".
	 * */
	static public String getTimeStringJJMMAAAA2()
	{
		return time2StringSepJJMMAAAA(0, '/');
	}

	/**=======================================================================
	 * JJ MM AAA HH MM SS
	 *======================================================================*/
	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "jj-mm-aaaa,hh:mm:ss" */
	static public String getTimeStringJJMMAAAAHHMMSS()
	{
		return timeToStringJJMMAAAAHHMMSS(System.currentTimeMillis());
	}
	/**=======================================================================
	 * MM SS mmm
	 *======================================================================*/
	//--------------------------------------------------------------------
	/** retourne le "temps courant" sous la forme "mm:ss:mmm" */
	static public String getTimeStringMMSSmmm()
	{
		return time2StringSepMMSSmmm(0, ';');
	}
	//--------------------------------------------------------------------
	/** retourne la "valeur" "temps courant" sous la forme "aaaammjj".
	 * */
	static public String getValueTimeStringAAAAMMJJ()
	{
		return time2StringSepAAAAMMJJ(0, NO_SEPARATOR);
	}
	//--------------------------------------------------------------------
	/** retourne la "valeur" du "temps courant" sous la forme "hhmmss" */
	static public String getValueTimeStringHHMMSS()
	{
		return time2StringSepHHMMSS(0, NO_SEPARATOR);
	}
	//--------------------------------------------------------------------
	/** retourne la "valeur" "temps courant" sous la forme "jjmmaaaa"/
	 * */
	static public String getValueTimeStringJJMMAAAA()
	{
		return time2StringSepJJMMAAAA(0, NO_SEPARATOR);
	}
	//--------------------------------------------------------------------
	/** retourne la "valeur" du "temps courant" sous la forme "mmssmmm" */
	static public String getValueTimeStringMMSSmmm()
	{
		return time2StringSepMMSSmmm(0, NO_SEPARATOR);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	static public String getValueTimeStringHHMMSSmmm()
	{
		return time2StringSepHHMMSSmmm(0, NO_SEPARATOR);
	}

	//--------------------------------------------------------------------
	public static String html8859ToHtmlValue(String aValue)
	{
		if (aValue == null)
			return aValue;
		int wMax = aValue.length();
		StringBuffer wSB = new StringBuffer(wMax + 256);
		int wI = 0;
		char wChar;
		while (wI < wMax)
		{
			wChar = aValue.charAt(wI);
			/*
			switch (wChar){
			  case 160: addHtmlCode(wSB,160);break; // nbsp   -- "&#160;" -- no-break space = non-breaking space, U+00A0 ISOnum
			  case '¡': addHtmlCode(wSB,161);break; // iexcl  -- "&#161;" -- inverted exclamation mark, U+00A1 ISOnum
			  case '¢': addHtmlCode(wSB,162);break; // cent   -- "&#162;" -- cent sign, U+00A2 ISOnum
			  case '£': addHtmlCode(wSB,163);break; // pound  -- "&#163;" -- pound sign, U+00A3 ISOnum
			  case '¤': addHtmlCode(wSB,164);break; // curren -- "&#164;" -- currency sign, U+00A4 ISOnum
			  case '¥': addHtmlCode(wSB,165);break; // yen    -- "&#165;" -- yen sign = yuan sign, U+00A5 ISOnum
			  case '¦': addHtmlCode(wSB,166);break; // brvbar -- "&#166;" -- broken bar = broken vertical bar, U+00A6 ISOnum
			  case '§': addHtmlCode(wSB,167);break; // sect   -- "&#167;" -- section sign, U+00A7 ISOnum
			  case '¨': addHtmlCode(wSB,168);break; // uml    -- "&#168;" -- diaeresis = spacing diaeresis, U+00A8 ISOdia
			  case '©': addHtmlCode(wSB,169);break; // copy   -- "&#169;" -- copyright sign, U+00A9 ISOnum
			  case 'ª': addHtmlCode(wSB,170);break; // ordf   -- "&#170;" -- feminine ordinal indicator, U+00AA ISOnum
			  case '«': addHtmlCode(wSB,171);break; // laquo  -- "&#171;" -- left-pointing double angle quotation mark = left pointing guillemet, U+00AB ISOnum
			  case '¯': addHtmlCode(wSB,172);break; // not    -- "&#172;" -- not sign, U+00AC ISOnum
			  //case '?': addHtmlCode(wSB,000);break; // shy    -- "&#173;" -- soft hyphen = discretionary hyphen, U+00AD ISOnum
			  case '®': addHtmlCode(wSB,174);break; // reg    -- "&#174;" -- registered sign = registered trade mark sign, U+00AE ISOnum
			  //case '?': addHtmlCode(wSB,000);break; // macr   -- "&#175;" -- macron = spacing macron = overline = APL overbar, U+00AF ISOdia
			  case '°': addHtmlCode(wSB,176);break; // deg    -- "&#176;" -- degree sign, U+00B0 ISOnum
			  case '±': addHtmlCode(wSB,177);break; // plusmn -- "&#177;" -- plus-minus sign = plus-or-minus sign, U+00B1 ISOnum
			  case '²': addHtmlCode(wSB,178);break; // sup2   -- "&#178;" -- superscript two = superscript digit two = squared, U+00B2 ISOnum
			  case '³': addHtmlCode(wSB,179);break; // sup3   -- "&#179;" -- superscript three = superscript digit three = cubed, U+00B3 ISOnum
			  case '´': addHtmlCode(wSB,180);break; // acute  -- "&#180;" -- acute accent = spacing acute, U+00B4 ISOdia
			  case 'µ': addHtmlCode(wSB,181);break; // micro  -- "&#181;" -- micro sign, U+00B5 ISOnum
			  case '·': addHtmlCode(wSB,182);break; // para   -- "&#182;" -- pilcrow sign = paragraph sign, U+00B6 ISOnum
			  case '¸': addHtmlCode(wSB,183);break; // middot -- "&#183;" -- middle dot = Georgian comma = Greek middle dot, U+00B7 ISOnum
			  //case '?': addHtmlCode(wSB,000);break; // cedil  -- "&#184;" -- cedilla = spacing cedilla, U+00B8 ISOdia
			  case '¹': addHtmlCode(wSB,185);break; // sup1   -- "&#185;" -- superscript one = superscript digit one, U+00B9 ISOnum
			  //case '?': addHtmlCode(wSB,000);break; // ordm   -- "&#186;" -- masculine ordinal indicator, U+00BA ISOnum
			  case '»': addHtmlCode(wSB,187);break; // raquo  -- "&#187;" -- right-pointing double angle quotation mark = right pointing guillemet, U+00BB ISOnum
			  case '¼': addHtmlCode(wSB,188);break; // frac14 -- "&#188;" -- vulgar fraction one quarter = fraction one quarter, U+00BC ISOnum
			  case '½': addHtmlCode(wSB,189);break; // frac12 -- "&#189;" -- vulgar fraction one half = fraction one half, U+00BD ISOnum
			  case '¾': addHtmlCode(wSB,190);break; // frac34 -- "&#190;" -- vulgar fraction three quarters = fraction three quarters, U+00BE ISOnum
			  case '¿': addHtmlCode(wSB,191);break; // iquest -- "&#191;" -- inverted question mark = turned question mark, U+00BF ISOnum
			  case 'À': addHtmlCode(wSB,192);break; // Agrave -- "&#192;" -- latin capital letter A with grave = latin capital letter A grave, U+00C0 ISOlat1
			  case 'Á': addHtmlCode(wSB,193);break; // Aacute -- "&#193;" -- latin capital letter A with acute, U+00C1 ISOlat1
			  case 'Â': addHtmlCode(wSB,194);break; // Acirc  -- "&#194;" -- latin capital letter A with circumflex, U+00C2 ISOlat1
			  case 'Ã': addHtmlCode(wSB,195);break; // Atilde -- "&#195;" -- latin capital letter A with tilde, U+00C3 ISOlat1
			  case 'Ä': addHtmlCode(wSB,196);break; // Auml   -- "&#196;" -- latin capital letter A with diaeresis, U+00C4 ISOlat1
			  case 'Å': addHtmlCode(wSB,197);break; // Aring  -- "&#197;" -- latin capital letter A with ring above = latin capital letter A ring, U+00C5 ISOlat1
			  case 'Æ': addHtmlCode(wSB,198);break; // AElig  -- "&#198;" -- latin capital letter AE = latin capital ligature AE, U+00C6 ISOlat1
			  case 'Ç': addHtmlCode(wSB,199);break; // Ccedil -- "&#199;" -- latin capital letter C with cedilla, U+00C7 ISOlat1
			  case 'È': addHtmlCode(wSB,200);break; // Egrave -- "&#200;" -- latin capital letter E with grave, U+00C8 ISOlat1
			  case 'É': addHtmlCode(wSB,201);break; // Eacute -- "&#201;" -- latin capital letter E with acute, U+00C9 ISOlat1
			  case 'Ê': addHtmlCode(wSB,202);break; // Ecirc  -- "&#202;" -- latin capital letter E with circumflex, U+00CA ISOlat1
			  case 'Ë': addHtmlCode(wSB,203);break; // Euml   -- "&#203;" -- latin capital letter E with diaeresis, U+00CB ISOlat1
			  case 'Ì': addHtmlCode(wSB,204);break; // Igrave -- "&#204;" -- latin capital letter I with grave, U+00CC ISOlat1
			  case 'Í': addHtmlCode(wSB,205);break; // Iacute -- "&#205;" -- latin capital letter I with acute, U+00CD ISOlat1
			  case 'Î': addHtmlCode(wSB,206);break; // Icirc  -- "&#206;" -- latin capital letter I with circumflex, U+00CE ISOlat1
			  case 'Ï': addHtmlCode(wSB,207);break; // Iuml   -- "&#207;" -- latin capital letter I with diaeresis, U+00CF ISOlat1
			  case 'Ð': addHtmlCode(wSB,208);break; // ETH    -- "&#208;" -- latin capital letter ETH, U+00D0 ISOlat1
			  case 'Ñ': addHtmlCode(wSB,209);break; // Ntilde -- "&#209;" -- latin capital letter N with tilde, U+00D1 ISOlat1
			  case 'Ò': addHtmlCode(wSB,210);break; // Ograve -- "&#210;" -- latin capital letter O with grave, U+00D2 ISOlat1
			  case 'Ó': addHtmlCode(wSB,211);break; // Oacute -- "&#211;" -- latin capital letter O with acute, U+00D3 ISOlat1
			  case 'Ô': addHtmlCode(wSB,212);break; // Ocirc  -- "&#212;" -- latin capital letter O with circumflex, U+00D4 ISOlat1
			  case 'Õ': addHtmlCode(wSB,213);break; // Otilde -- "&#213;" -- latin capital letter O with tilde, U+00D5 ISOlat1
			  case 'Ö': addHtmlCode(wSB,214);break; // Ouml   -- "&#214;" -- latin capital letter O with diaeresis, U+00D6 ISOlat1
			  //case '?': addHtmlCode(wSB,000);break; // times  -- "&#215;" -- multiplication sign, U+00D7 ISOnum
			  case 'Ø': addHtmlCode(wSB,216);break; // Oslash -- "&#216;" -- latin capital letter O with stroke = latin capital letter O slash, U+00D8 ISOlat1
			  case 'Ù': addHtmlCode(wSB,217);break; // Ugrave -- "&#217;" -- latin capital letter U with grave, U+00D9 ISOlat1
			  case 'Ú': addHtmlCode(wSB,218);break; // Uacute -- "&#218;" -- latin capital letter U with acute, U+00DA ISOlat1
			  case 'Û': addHtmlCode(wSB,219);break; // Ucirc  -- "&#219;" -- latin capital letter U with circumflex, U+00DB ISOlat1
			  case 'Ü': addHtmlCode(wSB,220);break; // Uuml   -- "&#220;" -- latin capital letter U with diaeresis, U+00DC ISOlat1
			  case 'Ý': addHtmlCode(wSB,221);break; // Yacute -- "&#221;" -- latin capital letter Y with acute, U+00DD ISOlat1
			  case 'Þ': addHtmlCode(wSB,222);break; // THORN  -- "&#222;" -- latin capital letter THORN, U+00DE ISOlat1
			  case 'ß': addHtmlCode(wSB,223);break; // szlig  -- "&#223;" -- latin small letter sharp s = ess-zed, U+00DF ISOlat1
			  case 'à': addHtmlCode(wSB,224);break; // agrave -- "&#224;" -- latin small letter a with grave= latin small letter a grave,U+00E0 ISOlat1
			  case 'á': addHtmlCode(wSB,225);break; // aacute -- "&#225;" -- latin small letter a with acute,U+00E1 ISOlat1
			  case 'â': addHtmlCode(wSB,226);break; // acirc  -- "&#226;" -- latin small letter a with circumflex,U+00E2 ISOlat1
			  case 'ã': addHtmlCode(wSB,227);break; // atilde -- "&#227;" -- latin small letter a with tilde,U+00E3 ISOlat1
			  case 'ä': addHtmlCode(wSB,228);break; // auml   -- "&#228;" -- latin small letter a with diaeresis,U+00E4 ISOlat1
			  case 'å': addHtmlCode(wSB,229);break; // aring  -- "&#229;" -- latin small letter a with ring above= latin small letter a ring,U+00E5 ISOlat1
			  case 'æ': addHtmlCode(wSB,230);break; // aelig  -- "&#230;" -- latin small letter ae= latin small ligature ae, U+00E6 ISOlat1
			  case 'ç': addHtmlCode(wSB,231);break; // ccedil -- "&#231;" -- latin small letter c with cedilla,U+00E7 ISOlat1
			  case 'è': addHtmlCode(wSB,232);break; // egrave -- "&#232;" -- latin small letter e with grave, U+00E8 ISOlat1
			  case 'é': addHtmlCode(wSB,233);break; // eacute -- "&#233;" -- latin small letter e with acute, U+00E9 ISOlat1
			  case 'ê': addHtmlCode(wSB,234);break; // ecirc  -- "&#234;" -- latin small letter e with circumflex, U+00EA ISOlat1
			  case 'ë': addHtmlCode(wSB,235);break; // euml   -- "&#235;" -- latin small letter e with diaeresis,U+00EB ISOlat1
			  case 'ì': addHtmlCode(wSB,236);break; // igrave -- "&#236;" -- latin small letter i with grave,U+00EC ISOlat1
			  case 'í': addHtmlCode(wSB,237);break; // iacute -- "&#237;" -- latin small letter i with acute,U+00ED ISOlat1
			  case 'î': addHtmlCode(wSB,238);break; // icirc  -- "&#238;" -- latin small letter i with circumflex,U+00EE ISOlat1
			  case 'ï': addHtmlCode(wSB,239);break; // iuml   -- "&#239;" -- latin small letter i with diaeresis,U+00EF ISOlat1
			  case 'ð': addHtmlCode(wSB,240);break; // eth    -- "&#240;" -- latin small letter eth, U+00F0 ISOlat1
			  case 'ñ': addHtmlCode(wSB,241);break; // ntilde -- "&#241;" -- latin small letter n with tilde, U+00F1 ISOlat1
			  case 'ò': addHtmlCode(wSB,242);break; // ograve -- "&#242;" -- latin small letter o with grave, U+00F2 ISOlat1
			  case 'ó': addHtmlCode(wSB,243);break; // oacute -- "&#243;" -- latin small letter o with acute,U+00F3 ISOlat1
			  case 'ô': addHtmlCode(wSB,244);break; // ocirc  -- "&#244;" -- latin small letter o with circumflex, U+00F4 ISOlat1
			  case 'õ': addHtmlCode(wSB,245);break; // otilde -- "&#245;" -- latin small letter o with tilde, U+00F5 ISOlat1
			  case 'ö': addHtmlCode(wSB,246);break; // ouml   -- "&#246;" -- latin small letter o with diaeresis, U+00F6 ISOlat1
			  case '÷': addHtmlCode(wSB,247);break; // divide -- "&#247;" -- division sign, U+00F7 ISOnum
			  case 'ø': addHtmlCode(wSB,248);break; // oslash -- "&#248;" -- latin small letter o with stroke, = latin small letter o slash, U+00F8 ISOlat1
			  case 'ù': addHtmlCode(wSB,249);break; // ugrave -- "&#249;" -- latin small letter u with grave, U+00F9 ISOlat1
			  case 'ú': addHtmlCode(wSB,250);break; // uacute -- "&#250;" -- latin small letter u with acute, U+00FA ISOlat1
			  case 'û': addHtmlCode(wSB,251);break; // ucirc  -- "&#251;" -- latin small letter u with circumflex, U+00FB ISOlat1
			  case 'ü': addHtmlCode(wSB,252);break; // uuml   -- "&#252;" -- latin small letter u with diaeresis, U+00FC ISOlat1
			  case 'ý': addHtmlCode(wSB,253);break; // yacute -- "&#253;" -- latin small letter y with acute, U+00FD ISOlat1
			  case 'þ': addHtmlCode(wSB,254);break; // thorn  -- "&#254;" -- latin small letter thorn with, U+00FE ISOlat1
			  case 'ÿ': addHtmlCode(wSB,255);break; // yuml   -- "&#255;" -- latin small letter y with diaeresis, U+00FF ISOlat1
			  default:wSB.append(wChar);break;
			}
			*/
			if ((int) wChar > 159 && (int) wChar < 256)
				addHtmlCode(wSB, (int) wChar);
			else
				wSB.append(wChar);
			wI++;
		}
		return wSB.toString();
	}

	//--------------------------------------------------------------------
	/**
	 * @deprecated
	 * @see Character.forDigit
	 */
	static public char int2Char(int aCode)
	{
		return (char) aCode;

	}
	//--------------------------------------------------------------------
	static public boolean isCharAccent(char aChar)
	{
		return (CHARS_ACCENT.indexOf(aChar) > -1);
	}
	//--------------------------------------------------------------------
	static public boolean isCharPonctuation(char aChar)
	{
		return (CHARS_PONCTUATION.indexOf(aChar) > -1);
	}
	//--------------------------------------------------------------------
	static public boolean isCharSigne(char aChar)
	{
		return (CHARS_SIGNE.indexOf(aChar) > -1);
	}
	//--------------------------------------------------------------------
	static public boolean isNumeric(String aValue)
	{
		int wMax = aValue.length();
		int wI = 0;
		while (wI < wMax)
		{
			if (!Character.isDigit(aValue.charAt(wI)))
				return false;
			wI++;
		}
		return true;
	}
	//--------------------------------------------------------------------
	public static String join(String[] strings, String sep)
	{
		StringBuffer result = new StringBuffer();

		for (int i = 0; strings != null && i < strings.length; ++i)
		{
			if ((sep != null) && (i > 0))
				result.append(sep);
			result.append(strings[i]);
		}

		return result.toString();
	}
	//--------------------------------------------------------------------
	/** Utilise String.valueOf(data,offset,count) pour retourner les n
	 *  caractères de gauche d'un tableau de caractères.
	 */
	public static String leftChar2String(char[] aChar, int aLen)
	{
		return String.valueOf(aChar, 0, aLen);
	}
	//--------------------------------------------------------------------
	/**
	 * retourne les "aLen" caractères de gauche.
	 */
	static public String leftString(String aString, int aLen)
	{
		if (aLen < aString.length())
		{
			return aString.substring(0, aLen);
		}
		else
		{
			return aString;
		}
	}
	//--------------------------------------------------------------------
	/**
	 *  formate un chaîne à droite en ajoutant a gauche les n spaces nécessaires.
	 * @deprecated
	 * @see alignStringToRight
	 */
	static public String padLeftString(String aValue, int aLen)
	{
		return alignStringToRight(aValue, aLen);
	}
	//--------------------------------------------------------------------
	/**
	 * formate une chaîne contenant un entier à droite en ajoutant les zéros non significatif.
	 */
  static public String padNum2String(String aValue, int aLen)
  {
    return padNum2String(aValue, aLen,CConstants.CHAR_ZERO);
  }
  /**
   * 14w_007 - Intégration WebService
   * 
   * formate une chaîne contenant un entier à droite en ajoutant les zéros non significatif.
   * 
	 * @param aValue
	 * @param aLen
	 * @param aPaddingChar
	 * @return
	 */
	static public String padNum2String(String aValue, int aLen,char aPaddingChar)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
			return new StringBuffer(aLen).append(buildStringFromChar(aPaddingChar, aLen - wLen)).append(aValue).toString();

		if (aValue.length() > aLen)
			return aValue.substring(aValue.length() - aLen);

		return aValue;
	}
	//--------------------------------------------------------------------
	/**
	 * formate une chaîne contenant un entier à gauche (!) en ajoutant les zéros non significatif
	 */
	static public String padNum2StringLeft(String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
			return new StringBuffer(aValue).append(buildStringFromChar(CConstants.CHAR_ZERO, aLen - wLen)).toString();

		if (wLen > aLen)
			return aValue.substring(0, aLen);

		return aValue;
	}
	//--------------------------------------------------------------------
	/**
	 * formate un entier "court" à droite en ajoutant les zéros non significatif.
	 */
	static public String padNumString(int aValue, int aLen)
	{
		return padNum2String(String.valueOf(aValue), aLen);
	}
  /**
   * 14w_007 - Intégration WebService
   * 
   * @param aValue
   * @param aLen
   * @param aPaddingChar
   * @return
   */
  static public String padNumString(int aValue, int aLen,char aPaddingChar)
  {
    return padNum2String(String.valueOf(aValue), aLen,aPaddingChar);
  }
	//--------------------------------------------------------------------
	/**
	 * formate un entier "long" à droite en ajoutant les zéros non significatif.
	 */
	static public String padNumString(long aValue, int aLen)
	{
		return padNum2String(String.valueOf(aValue), aLen);
	}
	//--------------------------------------------------------------------
	/** formate un entier "court" à gauche (!) en ajoutant les zéros significatifs */
	static public String padNumStringLeft(int aValue, int aLen)
	{
		return padNum2StringLeft(String.valueOf(aValue), aLen);
	}
	//--------------------------------------------------------------------
	/**
	 * formate un entier "long" à gauche (!) en ajoutant les zéros significatifs
	 */
	static public String padNumStringLeft(long aValue, int aLen)
	{
		return padNum2StringLeft(String.valueOf(aValue), aLen);
	}
	//--------------------------------------------------------------------
	/**
	 * formate un chaîne à gauche en ajoutant a droite les n spaces nécessaires.
	 * @deprecated
	 * @see alignStringToLeft
	 */
	static public String padString(String aValue, int aLen)
	{
		return alignStringToLeft(aValue, aLen);
	}
	//--------------------------------------------------------------------
	public static String[] parseArgumentString(String argStr)
	{
		String[] result = null;

		Vector vector = CStringFacilities.parseArgumentVector(argStr);

		if (vector != null && vector.size() > 0)
		{
			result = new String[vector.size()];
			vector.copyInto(result);
		}

		return result;
	}
	//--------------------------------------------------------------------
	public static Vector parseArgumentVector(String argStr)
	{
		Vector result = new Vector();
		StringBuffer argBuf = new StringBuffer();

		boolean backSlash = false;
		boolean matchSglQuote = false;
		boolean matchDblQuote = false;

		for (int cIdx = 0; cIdx < argStr.length(); ++cIdx)
		{
			char ch = argStr.charAt(cIdx);

			switch (ch)
			{
				//
				// W H I T E S P A C E
				//
				case ' ' :
				case '\t' :
				case '\n' :
				case '\r' :
					if (backSlash)
					{
						argBuf.append(ch);
						backSlash = false;
					}
					else if (matchSglQuote || matchDblQuote)
					{
						argBuf.append(ch);
					}
					else if (argBuf.length() > 0)
					{
						result.addElement(argBuf.toString());
						argBuf.setLength(0);
					}
					break;

				case '\\' :
					if (backSlash)
					{
						argBuf.append("\\");
					}
					backSlash = !backSlash;
					break;

				case '\'' :
					if (backSlash)
					{
						argBuf.append("'");
						backSlash = false;
					}
					else if (matchSglQuote)
					{
						result.addElement(argBuf.toString());
						argBuf.setLength(0);
						matchSglQuote = false;
					}
					else if (!matchDblQuote)
					{
						matchSglQuote = true;
					}
					break;

				case '"' :
					if (backSlash)
					{
						argBuf.append("\"");
						backSlash = false;
					}
					else if (matchDblQuote)
					{
						result.addElement(argBuf.toString());
						argBuf.setLength(0);
						matchDblQuote = false;
					}
					else if (!matchSglQuote)
					{
						matchDblQuote = true;
					}
					break;

				default :
					if (backSlash)
					{
						switch (ch)
						{
							case 'b' :
								argBuf.append('\b');
								break;
							case 'f' :
								argBuf.append('\f');
								break;
							case 'n' :
								argBuf.append('\n');
								break;
							case 'r' :
								argBuf.append('\r');
								break;
							case 't' :
								argBuf.append('\t');
								break;

							default :
								char ch2 = argStr.charAt(cIdx + 1);
								char ch3 = argStr.charAt(cIdx + 2);
								if ((ch >= '0' && ch <= '7') && (ch2 >= '0' && ch2 <= '7') && (ch3 >= '0' && ch3 <= '7'))
								{
									int octal = (((ch - '0') * 64) + ((ch2 - '0') * 8) + (ch3 - '0'));
									argBuf.append((char) octal);
									cIdx += 2;
								}
								else if (ch == '0')
								{
									argBuf.append('\0');
								}
								else
								{
									argBuf.append(ch);
								}
								break;
						}
					}
					else
					{
						argBuf.append(ch);
					}

					backSlash = false;
					break;
			}
		}

		if (argBuf.length() > 0)
		{
			result.addElement(argBuf.toString());
		}

		return result;
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les n instances d'une sous chaine par une autre chaine.
	 */
	static public String replaceSubsStr(String aString, String aWhat, String aBy)
	{
		if (aString == null)
			return null;
		if ((aWhat == null) || (aBy == null))
			return aString;

		StringTokenizer wST = new StringTokenizer(aString, aWhat);
		if (wST.countTokens() <= 1)
		{
			return aString;
		}
		else
		{
			int wLen = aString.length();
			int wDelta = aBy.length() - aWhat.length();
			if (wDelta > 0)
				wLen = wLen + (wST.countTokens() * wDelta);
			StringBuffer wSB = new StringBuffer(wLen);

      boolean wStartByWhat = aString.startsWith(aWhat);
			if (wStartByWhat)
				wSB.append(aBy);
			wSB.append(wST.nextToken());
			while (wST.hasMoreTokens())
			{
				wSB.append(aBy).append(wST.nextToken());
			}
			return wSB.toString();
		}
	}
	//--------------------------------------------------------------------
	/**
	* Split a string into a string array containing the substrings
	* between the delimiters.
	*
	* NOTE This method WILL <strong>NOT</strong> return an empty
	* token at the end of the array that is returned, if the string
	* ends with the delimiter. If you wish to have a property string
	* array that ends with the delimiter return an empty string at
	* the end of the array, use <code>vectorString()</code>.
	*/
	static public String[] splitString(String splitStr, String delim)
	{
		int i, count;
		String[] result;
		StringTokenizer toker;

		toker = new StringTokenizer(splitStr, delim);

		count = toker.countTokens();

		result = new String[count];

		for (i = 0; i < count; ++i)
		{
			try
			{
				result[i] = toker.nextToken();
			}
			catch (NoSuchElementException ex)
			{
				result = null;
				break;
			}
		}

		return result;
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les tags n "%x" d'un format par les n objects du tableau .
	 */
	static public String sprintf(String aFormat, Object[] aObjects)
	{
		CPrintfFormat wPrintfFormat = new CPrintfFormat(aFormat);
		return wPrintfFormat.sprintf(aObjects);
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace le tags  "%x" d'un format par la chaîne passée en paramètre .
	 */
	static public String sprintf(String aFormat, String aP1)
	{
		Object[] wObjects = new Object[1];
		wObjects[0] = aP1;
		return sprintf(aFormat, wObjects);
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les 2 tags  "%x" d'un format par les 2 chaînes passées en paramètre .
	 */
	static public String sprintf(String aFormat, String aP1, String aP2)
	{
		Object[] wObjects = new Object[2];
		wObjects[0] = aP1;
		wObjects[1] = aP2;
		return sprintf(aFormat, wObjects);
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les 3 tags  "%x" d'un format par les 3 chaînes passées en paramètre .
	 */
	static public String sprintf(String aFormat, String aP1, String aP2, String aP3)
	{
		Object[] wObjects = new Object[3];
		wObjects[0] = aP1;
		wObjects[1] = aP2;
		wObjects[2] = aP3;
		return sprintf(aFormat, wObjects);
	}
	//--------------------------------------------------------------------
	/**
	 * Remplace les 4 tags  "%x" d'un format par les 3 chaînes passées en paramètre .
	 */
	static public String sprintf(String aFormat, String aP1, String aP2, String aP3, String aP4)
	{
		Object[] wObjects = new Object[4];
		wObjects[0] = aP1;
		wObjects[1] = aP2;
		wObjects[2] = aP3;
		wObjects[3] = aP4;
		return sprintf(aFormat, wObjects);
	}

  static public String sprintf(String aFormat, String aP1, String aP2, String aP3, String aP4,String aP5)
  {
    Object[] wObjects = new Object[5];
    wObjects[0] = aP1;
    wObjects[1] = aP2;
    wObjects[2] = aP3;
    wObjects[3] = aP4;
    wObjects[4] = aP5;
    return sprintf(aFormat, wObjects);
  }
	//--------------------------------------------------------------------
	/** appel de la trace pour un buffer */
	static public String stringBin2Hexa(String aString)
	{
		return byteBufferBin2Hexa(aString.getBytes(), 0, aString.length());
	}
	//--------------------------------------------------------------------
	// ASCII
	//--------------------------------------------------------------------

	//--------------------------------------------------------------------
	/** appel de la trace pour un buffer */
	static public String stringBin2String(String aString)
	{
		return bufferBin2String(aString.toCharArray(), 0, aString.length());
	}
	//--------------------------------------------------------------------
	public static String stringSubstitution(String argStr, Hashtable vars)
	{
		StringBuffer argBuf = new StringBuffer();

		for (int cIdx = 0; cIdx < argStr.length();)
		{
			char ch = argStr.charAt(cIdx);

			switch (ch)
			{
				case '$' :
					StringBuffer nameBuf = new StringBuffer();
					for (++cIdx; cIdx < argStr.length(); ++cIdx)
					{
						ch = argStr.charAt(cIdx);
						if (ch == '_' || Character.isLetterOrDigit(ch))
							nameBuf.append(ch);
						else
							break;
					}

					if (nameBuf.length() > 0)
					{
						String value = (String) vars.get(nameBuf.toString());

						if (value != null)
						{
							argBuf.append(value);
						}
					}
					break;

				default :
					argBuf.append(ch);
					++cIdx;
					break;
			}
		}

		return argBuf.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * converti une date sous la forme "aaaammjjhhmmssmmm" ou "aaaa/mm/jj:hh:mm:ss,mmm" en millisecs
	 */
	static public long stringTimeAAAAmmjjhhmmssmmmToLong(String aTime) throws Exception
	{
		return stringTimeToLong(aTime, 17);
	}
	//--------------------------------------------------------------------
	/**
	 * converti une date sous la forme "aaaammjjhhmmss" ou "aaaa/mm/jj,hh:mm:ss" en millisecs
	 */
	static public long stringTimeAAAAmmjjhhmmssToLong(String aTime) throws Exception
	{
		return stringTimeToLong(aTime, 14);
	}
	//--------------------------------------------------------------------
	/**
	 * converti une date sous la forme "aaaammjj" ou "aaaa/mm/jj" en millisecs
	 */
	static public long stringTimeAAAAmmjjToLong(String aTime) throws Exception
	{
		return stringTimeToLong(aTime, 8);
	}
	//--------------------------------------------------------------------
	static private long stringTimeToLong(String aTime, int aLong) throws Exception
	{
		if (aTime == null)
			throw new Exception("no string time");
		int wPos = aTime.indexOf('/');
		while (wPos > -1)
		{
			aTime = aTime.substring(0, wPos).concat(aTime.substring(wPos + 1));
			wPos = aTime.indexOf('/');
		}
		if (aLong > 8)
		{
			wPos = aTime.indexOf(':');
			while (wPos > -1)
			{
				aTime = aTime.substring(0, wPos).concat(aTime.substring(wPos + 1));
				wPos = aTime.indexOf(':');
			}
			wPos = aTime.indexOf(',');
			while (wPos > -1)
			{
				aTime = aTime.substring(0, wPos).concat(aTime.substring(wPos + 1));
				wPos = aTime.indexOf(',');
			}
		}
		if (aTime.length() != aLong)
			throw new Exception("wrong format string time");
		Calendar wRightNow = Calendar.getInstance();
		int wYear = Integer.parseInt(aTime.substring(0, 4));
		int wMonth = Integer.parseInt(aTime.substring(4, 6)) - 1; //les mois vont de 0 à 11 !!!
		int wDay = Integer.parseInt(aTime.substring(6, 8));
		if (aLong == 8)
			wRightNow.set(wYear, wMonth, wDay);
		else
		{
			int wHour = Integer.parseInt(aTime.substring(8, 10));
			int wMin = Integer.parseInt(aTime.substring(10, 12));
			int wSec = Integer.parseInt(aTime.substring(12, 14));
			wRightNow.set(wYear, wMonth, wDay, wHour, wMin, wSec);
		}
		long wTime = wRightNow.getTime().getTime();
		if (aLong == 17)
		{
			wTime += Long.parseLong(aTime.substring(14, 17));
		}
		return wTime;
	}

	//--------------------------------------------------------------------
	/**Codage String => Codage HTML.
	 * <ul>
	 * <li>caractère lighter (<) "&#60;" = entité prédéfinie   (&lt;)
	 * <li>caractère greater (>) "&#62;" = entité prédéfinie   (&gt;)
	 * <li>caractère ampersand (&) "&#38;" = entité prédéfinie   (&amp;)
	 * <li>caractère apostrophe (') "&#39;"    = entité prédéfinie   (&apos;)
	 * <li>caractère quotation mark (") "&#34;" = entité prédéfinie  (&quot;)
	 * </ul>
	 * */
	public static String stringToHtmlValue(String aValue)
	{
		if (aValue == null)
			return "";
		int wMax = aValue.length();
		StringBuffer wValue = new StringBuffer(wMax + 32);
		int wI = 0;
		char wChar;
		while (wI < wMax)
		{
			wChar = aValue.charAt(wI);
			switch (wChar)
			{
				case CConstants.CHAR_LT :
					wValue.append(CConstants.XML_LT);
					break;
				case CConstants.CHAR_GT :
					wValue.append(CConstants.XML_GT);
					break;
				case CConstants.CHAR_AMP :
					wValue.append(CConstants.XML_AMP);
					break;
				case CConstants.CHAR_APOS :
					wValue.append(CConstants.XML_APOS);
					break;
				case CConstants.CHAR_QUOT :
					wValue.append(CConstants.XML_QUOT);
					break;
				default :
					wValue.append(wChar);
					break;
			}
			wI++;
		}
		return wValue.toString();
	}
	//--------------------------------------------------------------------
	/**
	 * supprime tout ce qui est à droite à partir de ...
	 */
	static public String stripRightWhiteAndControl(String aString)
	{
		if (aString != null)
		{
			int wMax = aString.length() - 1;
			char wChar;
			for (int wI = wMax; wI >= 0; wI--)
			{
				wChar = aString.charAt(wI);
				//si le dernier caractère est une lettre ou un chiffre ou de la
				//if ( Character.isLetterOrDigit(wChar) || isCharPonctuation(wChar) || isCharSigne(wChar)){
				if (!Character.isISOControl(wChar) && !Character.isWhitespace(wChar))
				{
					if (wMax == wI)
						return aString;
					else
						return leftString(aString, wI + 1);
				}
			}
		}
		return "";
	}

	//--------------------------------------------------------------------
	/**
	 * supprime tout ce qui est à droite à partir du premier espace
	 */
	static public String stripString(String aString)
	{
		int wEnd = aString.indexOf(' ');
		if (wEnd > -1)
		{
			return leftString(aString, wEnd);
		}
		else
		{
			return aString;
		}
	}
	//--------------------------------------------------------------------
	public static String tableOfStringsToString(String[] aValues)
	{
		StringBuffer wSB = new StringBuffer(256);
		if (aValues != null)
		{
			int wMax = aValues.length;
			for (int wI = 0; wI < wMax; wI++)
			{
				if (wI > 0)
					wSB.append(',');
				wSB.append(aValues[wI]);
			}
		}
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	public static String[] tabulizeTokenizer(StringTokenizer aTok)
	{
		if ((aTok == null) || (aTok.countTokens() == 0))
		{
			return new String[0];

		}
		else
		{
			String[] wTblStr = new String[aTok.countTokens()];
			int wI = -1;
			while (aTok.hasMoreTokens())
			{
				wI++;
				wTblStr[wI] = aTok.nextToken();
			}
			return wTblStr;
		}
	}
	//--------------------------------------------------------------------
	public static String[] tabulizeVector(Vector aVector)
	{
		if ((aVector == null) || (aVector.size() == 0))
		{
			return new String[0];

		}
		else
		{
			int wSize = aVector.size();
			String[] wTblStr = new String[wSize];
			Enumeration wEnum = aVector.elements();
			int wI = -1;
			while (wEnum.hasMoreElements())
			{
				wI++;
				wTblStr[wI] = (String) wEnum.nextElement();
			}
			return wTblStr;
		}
	}
	//--------------------------------------------------------------------
	/** formate le "temps aTime" sous la forme "aaaa-mm-jj" */
	static public String time2StringAAAAMMJJ(long aTime)
	{
		return time2StringSepJJMMAAAA(aTime, '-');
	}
	//--------------------------------------------------------------------
	/** formate le "temps aTime" sous la forme "hh:mm:ss" */
	static public String time2StringHHMMSS(long aTime)
	{
		return time2StringSepHHMMSS(aTime, ':');
	}
	//--------------------------------------------------------------------
	/** formate le "temps aTime" sous la forme "jj-mm-aaaa" */
	static public String time2StringJJMMAAAA(long aTime)
	{
		return time2StringSepJJMMAAAA(aTime, '-');
	}
	//--------------------------------------------------------------------
	/** formate le "temps" sous la forme "mm:ss,mmm" */
	static public String time2StringMMSSmmm(long aTime)
	{
		return time2StringSepMMSSmmm(aTime, ':');
	}
	//--------------------------------------------------------------------
	/** formate le "temps aTime" sous la forme "aaaa-mm-jj" en fonction du séparateur*/
	static private String time2StringSepAAAAMMJJ(long aTime, char aSep)
	{
		StringBuffer wSB;

		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));

		wSB = new StringBuffer();
		wSB.append(padNumString(wRightNow.get(Calendar.YEAR), 4));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.MONTH) + 1, 2)); // mois + 1  car de 0 à 11
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.DAY_OF_MONTH), 2));
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/** formate le "temps" sous la forme "hh:mm:ss" */
	static private String time2StringSepHHMMSS(long aTime, char aSep)
	{
		StringBuffer wSB;

		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));

		wSB = new StringBuffer();
		wSB.append(padNumString(wRightNow.get(Calendar.HOUR_OF_DAY), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.MINUTE), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.SECOND), 2));

		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/** formate le "temps" sous la forme "jj-mm-aaaa" */
	static private String time2StringSepJJMMAAAA(long aTime, char aSep)
	{
		StringBuffer wSB;

		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));

		wSB = new StringBuffer();
		wSB.append(padNumString(wRightNow.get(Calendar.DAY_OF_MONTH), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.MONTH) + 1, 2)); // mois + 1  car de 0 à 11
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.YEAR), 4));

		return wSB.toString();
	}
	//--------------------------------------------------------------------
	/** formate le "temps" sous la forme "mm:ss,mmm" */
	static public String time2StringSepMMSSmmm(long aTime, char aSep)
	{
		StringBuffer wSB;

		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));

		wSB = new StringBuffer(16);
		wSB.append(padNumString(wRightNow.get(Calendar.MINUTE), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.SECOND), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(',');
		wSB.append(padNumString(wRightNow.get(Calendar.MILLISECOND), 3));
		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * @param aTime
	 * @param aSep
	 * @return
	 */
	static public String time2StringSepHHMMSSmmm(long aTime, char aSep)
	{
		StringBuffer wSB;

		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));

		wSB = new StringBuffer(16);
		wSB.append(padNumString(wRightNow.get(Calendar.HOUR), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.MINUTE), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(aSep);
		wSB.append(padNumString(wRightNow.get(Calendar.SECOND), 2));
		if (aSep != NO_SEPARATOR)
			wSB.append(',');
		wSB.append(padNumString(wRightNow.get(Calendar.MILLISECOND), 3));
		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * @param aTime
	 * @return
	 */
	static public String time2StringJJJ(long aTime)
	{
		Calendar wRightNow = Calendar.getInstance();
		if (aTime > 0)
			wRightNow.setTime(new Date(aTime));
			
		return padNumString(wRightNow.get(Calendar.DAY_OF_YEAR), 3);
	}
	/**--------------------------------------------------------------------
	 * @param aTime
	 * @param aSep
	 * @return
	 */
	static public String time2StringJJJHHMMSSmmm(long aTime)
	{
		StringBuffer wSB = new StringBuffer(32);
		wSB.append(time2StringJJJ(aTime));
		wSB.append(',');
		wSB.append(time2StringHHMMSS(aTime));
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	static public String timeToStringJJMMAAAAHHMMSS(long aTime)
	{
		StringBuffer wSB = new StringBuffer(32);
		wSB.append(time2StringSepJJMMAAAA(aTime, '-'));
		wSB.append(',');
		wSB.append(time2StringHHMMSS(aTime));
		return wSB.toString();
	}

	/**--------------------------------------------------------------------
	 * @param aTime
	 * @return
	 */
	public static String timeToStringAAAAMMJJHHMMSS(long aTime)
	{
		StringBuffer wSB = new StringBuffer(32);
		wSB.append(time2StringSepAAAAMMJJ(aTime, '-'));
		wSB.append(',');
		wSB.append(time2StringHHMMSS(aTime));
		return wSB.toString();
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, boolean aValue)
	{
		return toStringDescr(aId, String.valueOf(aValue));
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, Class aClass)
	{
		return toStringDescr(aId, getClasseName(aClass));
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, int aValue)
	{
		return toStringDescr(aId, String.valueOf(aValue));
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, long aValue)
	{
		return toStringDescr(aId, String.valueOf(aValue));
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, String aValue)
	{
		int wLen = 4 + aId.length();
		if (aValue != null)
			wLen += aValue.length();
		else
			wLen += 4; //null
		StringBuffer wSBa = new StringBuffer(wLen);
		wSBa.append(' ').append(aId).append(DESCR_VALUE_START).append(aValue).append(DESCR_VALUE_END);
		return wSBa.toString();
	}
	//--------------------------------------------------------------------
	public static String toStringDescr(String aId, String[] aValues)
	{
		return toStringDescr(aId, tableOfStringsToString(aValues));
	}
	//--------------------------------------------------------------------
	/**
	* Split a string into a string Vector containing the substrings
	* between the delimiters.
	*
	* NOTE This method WILL return an empty
	* token at the end of the array that is returned, if the string
	* ends with the delimiter.
	*/
	static public Vector vectorString(String splitStr, String delim)
	{
		boolean tokeWasDelim = false;
		int i, count;
		StringTokenizer toker;

		Vector result = new Vector();

		toker = new StringTokenizer(splitStr, delim, true);
		count = toker.countTokens();

		for (i = 0; i < count; ++i)
		{
			String toke;

			try
			{
				toke = toker.nextToken();
			}
			catch (NoSuchElementException ex)
			{
				break;
			}

			if (toke.equals(delim))
			{
				if (tokeWasDelim)
					result.addElement("");
				tokeWasDelim = true;
			}
			else
			{
				result.addElement(toke);
				tokeWasDelim = false;
			}
		}

		if (tokeWasDelim)
			result.addElement("");

		return result;
	}
	/*
	** Tim Endres' utilities package.
	** Copyright (c) 1997 by Tim Endres
	**
	** This program is free software.
	**
	** You may redistribute it and/or modify it under the terms of the GNU
	** General Public License as published by the Free Software Foundation.
	** Version 2 of the license should be included with this distribution in
	** the file LICENSE, as well as License.html. If the license is not
	** included    with this distribution, you may find a copy at the FSF web
	** site at 'www.gnu.org' or 'www.fsf.org', or you may write to the
	** Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139 USA.
	**
	** THIS SOFTWARE IS PROVIDED AS-IS WITHOUT WARRANTY OF ANY KIND,
	** NOT EVEN THE IMPLIED WARRANTY OF MERCHANTABILITY. THE AUTHOR
	** OF THIS SOFTWARE, ASSUMES _NO_ RESPONSIBILITY FOR ANY
	** CONSEQUENCE RESULTING FROM THE USE, MODIFICATION, OR
	** REDISTRIBUTION OF THIS SOFTWARE.
	**
	*/

	//--------------------------------------------------------------------
	static public String[] vectorToStringArray(Vector sV)
	{
		int sz = sV.size();
		String[] result = new String[sz];

		for (int i = 0; i < sz; ++i)
		{
			result[i] = (String) sV.elementAt(i);
		}

		return result;
	}

	//--------------------------------------------------------------------
}
