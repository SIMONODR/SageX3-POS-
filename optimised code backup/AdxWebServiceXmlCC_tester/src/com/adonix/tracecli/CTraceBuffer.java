package com.adonix.tracecli;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

/**
 * @author Adonix Grenoble
 * @version 1.0
 */

/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * A trace-buffer implements a mutable sequence of characters. 
 * A trace-buffer is like a {@link String}, but can be modified. At any 
 * point in time it contains some particular sequence of characters, but 
 * the length and content of the sequence can be changed through certain 
 * method calls.
 * <p>
 * String buffers are safe for use by multiple threads. The methods 
 * are synchronized where necessary so that all the operations on any 
 * particular instance behave as if they occur in some serial order 
 * that is consistent with the order of the method calls made by each of 
 * the individual threads involved. 
 * <p>
 * String buffers are used by the compiler to implement the binary 
 * string concatenation operator <code>+</code>. For example, the code:
 * <p><blockquote><pre>
 *     x = "a" + 4 + "c"
 * </pre></blockquote><p>
 * is compiled to the equivalent of: 
 * <p><blockquote><pre>
 *     x = new StringBuffer().append("a").append(4).append("c")
 *                           .toString()
 * </pre></blockquote>
 * which creates a new trace-buffer (initially empty), appends the string
 * representation of each operand to the trace-buffer in turn, and then
 * converts the contents of the trace-buffer to a string. Overall, this avoids
 * creating many temporary strings.
 * <p>
 * The principal operations on a <code>StringBuffer</code> are the 
 * <code>append</code> and <code>insert</code> methods, which are 
 * overloaded so as to accept data of any type. Each effectively 
 * converts a given datum to a string and then appends or inserts the 
 * characters of that string to the trace-buffer. The 
 * <code>append</code> method always adds these characters at the end 
 * of the buffer; the <code>insert</code> method adds the characters at 
 * a specified point. 
 * <p>
 * For example, if <code>z</code> refers to a trace-buffer object 
 * whose current contents are "<code>start</code>", then 
 * the method call <code>z.append("le")</code> would cause the string 
 * buffer to contain "<code>startle</code>", whereas 
 * <code>z.insert(4, "le")</code> would alter the trace-buffer to 
 * contain "<code>starlet</code>". 
 * <p>
 * In general, if sb refers to an instance of a <code>StringBuffer</code>, 
 * then <code>sb.append(x)</code> has the same effect as 
 * <code>sb.insert(sb.length(),&nbsp;x)</code>.
 * <p>
 * Every trace-buffer has a capacity. As long as the length of the 
 * character sequence contained in the trace-buffer does not exceed 
 * the capacity, it is not necessary to allocate a new internal 
 * buffer array. If the internal buffer overflows, it is 
 * automatically made larger. 
 *
 * @author	Arthur van Hoff
 * @version 	1.63, 02/06/02
 * @see     java.io.ByteArrayOutputStream
 * @see     java.lang.String
 * @since   JDK1.0
 */

public final class CTraceBuffer implements java.io.Serializable
{
	/**
	 * 
	 */
	private final static String ADONIX_CLASSES_PREFIX = "com.adonix.";
	private static final char CHAR_OD = 0x0D;
	public final static char DESCR_VALUE_END = ']';

	public final static String DESCR_VALUE_START = "=[";
	/**
	 * taille maximun d'un nom de classe
	 */
	private final static int LENGTH_MAX_CLASSNAME = 20;
	/**
	 * taille maximun d'un nom de méthode
	 */
	private final static int LENGTH_MAX_METHODNAME = 20;
	private final static String LIB_CLASS = "class:[";
	private final static String LIB_MESS = "mess: [";
	private final static String LIB_MESS_IE = "mess: [CTraceBuffer internal error: ";
	private final static String LIB_STACK = "stack:[";
	private final static String LIB_WHY = "why:  [";
	private final static String METHOD_GETLISTOFMESS = "getListOfMessages";
	private final static String METHOD_WHY = "getWhyString";
	/**
	 * 
	 */
	private final static String METHODNAME_SUFFIX = " *";
	/**
	 * 
	 */
	private final static String NOMESS = "no message";
	/**
	 * 
	 */
	private final static String PREFIX = " ### ";
	/**
	 * use serialVersionUID from JDK 1.0.2 for interoperability
	 */
	private static final long serialVersionUID = 3388685877147921107L;
	/**
	 * 
	 */
	private static final String STACK_PREFIX = "\tat ";
	/**
	 * 
	 */
	private final static char[] TENZEROCHARS = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	/**--------------------------------------------------------------------
	 * @param aText
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	private static CTraceBuffer addNumStringInText(CTraceBuffer aText, int aValue, int aLen)
	{
		String wResult = String.valueOf(aValue);
		int wLen = wResult.length();
		if (wLen == aLen)
			return aText.append(wResult);
		else if (wLen < aLen)
			return aText.append(TENZEROCHARS, 0, aLen - wLen).append(wResult);
		else //if (wResult.length() > aLen)
			return aText.append(wResult.substring(wLen - aLen));
	}
	/**--------------------------------------------------------------------
	 * @param aStatck
	 * @return
	 */
	private static String cutStackAfterComAdonix(String aStatck)
	{
		int wPos = aStatck.lastIndexOf(ADONIX_CLASSES_PREFIX);
		if (wPos > -1)
		{
			wPos = aStatck.indexOf('\n', wPos);
			aStatck = aStatck.substring(0, wPos);
		}
		return aStatck;
	}
	/**--------------------------------------------------------------------
	 * Retourne la stack d'une exception dans une String
	 * 
	 * @param e
	 * @return
	 */
	private static String eStackToString(Throwable e)
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
			wS = cutStackAfterComAdonix(wS);
		}
		return wS;
	}
	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	private static String eToName(Throwable e)
	{
		String wName = e.getClass().getName();
		int wPos = wName.lastIndexOf('.');
		if (wPos > -1)
			wName = wName.substring(wPos + 1);
		return wName;
	}
	/**--------------------------------------------------------------------
	 * Retourne la première ligne de la stack d'une exception dans une String
	 * 
	 * @param e
	 * @return
	 */
	private static String firstAdonixLineOfeStackInString(Throwable e)
	{
		return firstPackageLineOfeStackInString(e, "com.adonix.");
	}
	/**--------------------------------------------------------------------
	 * Retourne la première ligne de la stack d'une exception dans une String
	 * 
	 * @param e
	 * @return
	 */
	private static String firstLineOfeStackInString(Throwable e)
	{
		return firstPackageLineOfeStackInString(e, null);
	}
	/**--------------------------------------------------------------------
	 * Retourne dans une String la première ligne d'une la stack d'une exception 
	 * concernat le package "aPackagePrefix"
	 * 
	 * @param e
	 * @param aPackagePrefix
	 * @return
	 */
	private static String firstPackageLineOfeStackInString(Throwable e, String aPackagePrefix)
	{
		try
		{
			java.io.StringWriter wSW = new java.io.StringWriter();

			e.printStackTrace(new java.io.PrintWriter(wSW));

			String wS = wSW.toString();

			String wStart = STACK_PREFIX;

			if (aPackagePrefix != null)
				wStart += aPackagePrefix;

			int wPos = wS.indexOf(wStart);
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
			return "can't get firstPackageLineOfeStackInString ! " + e2.getMessage();
		}
	}
	/** 
	 * The count is the number of characters in the buffer.
	 * 
	 * @serial
	 */
	private int count;
	/**
	 * A flag indicating whether the buffer is shared 
	 *
	 * @serial
	 */
	private boolean shared;
	/**
	 * The value is used for character storage.
	 * 
	 * @serial
	 */
	private char value[];

	/**
	 * Constructs a trace-buffer with no characters in it and an 
	 * initial capacity of 16 characters. 
	 */
	public CTraceBuffer()
	{
		this(16);
	}

	/**
	 * Constructs a trace-buffer with no characters in it and an 
	 * initial capacity specified by the <code>length</code> argument. 
	 *
	 * @param      length   the initial capacity.
	 * @exception  NegativeArraySizeException  if the <code>length</code>
	 *               argument is less than <code>0</code>.
	 */
	public CTraceBuffer(int length)
	{
		value = new char[length];
		shared = false;
	}

	/**
	 * Constructs a trace-buffer so that it represents the same 
	 * sequence of characters as the string argument; in other
	 * words, the initial contents of the trace-buffer is a copy of the 
	 * argument string. The initial capacity of the trace-buffer is 
	 * <code>16</code> plus the length of the string argument. 
	 *
	 * @param   str   the initial contents of the buffer.
	 */
	public CTraceBuffer(String str)
	{
		this(str.length() + 16);
		append(str);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aValue
	 * @param aLen
	 * @return
	 */
	public CTraceBuffer addAlignStringToLeft(String aValue, int aLen)
	{
		int wLen = aValue.length();

		if (wLen < aLen)
		{
			append(aValue);
			addNChars(' ', aLen - wLen);
		}
		else if (wLen > aLen)
		{
			append(aValue.substring(0, aLen));
		}
		else
		{
			append(aValue);
		}

		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aChar
	 * @param aLen
	 * @return
	 */
	public CTraceBuffer addNChars(char aChar, int aLen)
	{
		for (int wI = 0; wI < aLen; wI++)
		{
			append(aChar);
		}
		return this;
	}

	/**
	 * Appends the string representation of the <code>boolean</code> 
	 * argument to the trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   b   a <code>boolean</code>.
	 * @return  a reference to this <code>CTraceBuffer</code>.
	 * @see     java.lang.String#valueOf(boolean)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer append(boolean b)
	{
		return append(String.valueOf(b));
	}

	/**
	 * Appends the string representation of the <code>char</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is appended to the contents of this trace-buffer. 
	 * The length of this trace-buffer increases by <code>1</code>. 
	 * <p>
	 * The overall effect is exactly as if the argument were converted to 
	 * a string by the method {@link String#valueOf(char)} and the character 
	 * in that string were then {@link #append(String) appended} to this 
	 * <code>CTraceBuffer</code> object.
	 *
	 * @param   c   a <code>char</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 */
	public synchronized CTraceBuffer append(char c)
	{
		int newcount = count + 1;
		if (newcount > value.length)
			expandCapacity(newcount);
		value[count++] = c;
		return this;
	}

	/**
	 * Appends n times the string representation of the <code>char</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is appended to the contents of this trace-buffer. 
	 * The length of this trace-buffer increases by <code>n</code>. 
	 * <p>
	 * The overall effect is exactly as if the argument were converted to 
	 * a string by the method {@link String#valueOf(char)} and the character 
	 * in that string were then {@link #append(String) appended} to this 
	 * <code>CTraceBuffer</code> object.
	 *
	 * @param   c   a <code>char</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 */
	public synchronized CTraceBuffer append(char c, int nbChar)
	{
		int newcount = count + nbChar;
		if (newcount > value.length)
			expandCapacity(newcount);
		int wI = 0;
		while (wI < nbChar)
		{
			value[count++] = c;
			wI++;
		}
		return this;
	}

	/**
	 * Appends the string representation of the <code>char</code> array 
	 * argument to this trace-buffer. 
	 * <p>
	 * The characters of the array argument are appended, in order, to 
	 * the contents of this trace-buffer. The length of this string 
	 * buffer increases by the length of the argument. 
	 * <p>
	 * The overall effect is exactly as if the argument were converted to 
	 * a string by the method {@link String#valueOf(char[])} and the 
	 * characters of that string were then {@link #append(String) appended} 
	 * to this <code>CTraceBuffer</code> object.
	 *
	 * @param   str   the characters to be appended.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 */
	public synchronized CTraceBuffer append(char str[])
	{
		int len = str.length;
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		System.arraycopy(str, 0, value, count, len);
		count = newcount;
		return this;
	}

	/**
	 * Appends the string representation of a subarray of the 
	 * <code>char</code> array argument to this trace-buffer. 
	 * <p>
	 * Characters of the character array <code>str</code>, starting at 
	 * index <code>offset</code>, are appended, in order, to the contents 
	 * of this trace-buffer. The length of this trace-buffer increases 
	 * by the value of <code>len</code>. 
	 * <p>
	 * The overall effect is exactly as if the arguments were converted to 
	 * a string by the method {@link String#valueOf(char[],int,int)} and the
	 * characters of that string were then {@link #append(String) appended} 
	 * to this <code>CTraceBuffer</code> object.
	 *
	 * @param   str      the characters to be appended.
	 * @param   offset   the index of the first character to append.
	 * @param   len      the number of characters to append.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 */
	public synchronized CTraceBuffer append(char str[], int offset, int len)
	{
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		System.arraycopy(str, offset, value, count, len);
		count = newcount;
		return this;
	}

	/**
	 * Appends the string representation of the <code>double</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   d   a <code>double</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.String#valueOf(double)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer append(double d)
	{
		return append(String.valueOf(d));
	}

	/**
	 * Appends the string representation of the <code>float</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   f   a <code>float</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.String#valueOf(float)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer append(float f)
	{
		return append(String.valueOf(f));
	}
	/**
	 * Appends the string representation of the <code>int</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   i   an <code>int</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.String#valueOf(int)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer append(int i)
	{
		return append(String.valueOf(i));
	}
	/**
	 * Appends the string representation of the <code>long</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   l   a <code>long</code>.
	 * @return  a referenct to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.String#valueOf(long)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer append(long l)
	{
		return append(String.valueOf(l));
	}
	/**
	 * Appends the string representation of the <code>Object</code> 
	 * argument to this trace-buffer. 
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then appended to this trace-buffer. 
	 *
	 * @param   obj   an <code>Object</code>.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.String#valueOf(java.lang.Object)
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public synchronized CTraceBuffer append(Object obj)
	{
		return append(String.valueOf(obj));
	}
	/**
	 * Appends the string to this trace-buffer. 
	 * <p>
	 * The characters of the <code>String</code> argument are appended, in 
	 * order, to the contents of this trace-buffer, increasing the 
	 * length of this trace-buffer by the length of the argument. 
	 * If <code>str</code> is <code>null</code>, then the four characters 
	 * <code>"null"</code> are appended to this trace-buffer.
	 * <p>
	 * Let <i>n</i> be the length of the old character sequence, the one 
	 * contained in the trace-buffer just prior to execution of the 
	 * <code>append</code> method. Then the character at index <i>k</i> in 
	 * the new character sequence is equal to the character at index <i>k</i> 
	 * in the old character sequence, if <i>k</i> is less than <i>n</i>; 
	 * otherwise, it is equal to the character at index <i>k-n</i> in the 
	 * argument <code>str</code>.
	 *
	 * @param   str   a string.
	 * @return  a reference to this <code>CTraceBuffer</code>.
	 */
	public synchronized CTraceBuffer append(String str)
	{
		if (str == null)
		{
			str = String.valueOf(str);
		}

		int len = str.length();
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		str.getChars(0, len, value, count);
		count = newcount;
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param str
	 * @param aMaxLen
	 * @return
	 */
	public synchronized CTraceBuffer append(String str, int aMaxLen)
	{
		if (str == null)
		{
			str = String.valueOf(str);
		}

		int len = str.length();

		int newcount = count + aMaxLen;
		if (newcount > value.length)
			expandCapacity(newcount);
		if (len >= aMaxLen)
			str.getChars(0, aMaxLen, value, count);
		else
		{
			str.getChars(0, len, value, count);
			int wMax = count + aMaxLen;
			int wI = count + len;
			while (wI < wMax)
			{
				value[wI] = ' '; //space
				wI++;
			}
		}
		count = newcount;
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aObject
	 * @return
	 */
	public CTraceBuffer appendClassName(Object aObject)
	{
		if (aObject != null)
		{
			String wName;
			if (aObject instanceof Class)
				wName = ((Class) aObject).getName();
			else
				wName = aObject.getClass().getName();

			int wPos = wName.lastIndexOf('.');
			return append(((wPos > -1 && wPos < wName.length() - 1) ? wName.substring(wPos + 1) : wName), LENGTH_MAX_CLASSNAME);
		}
		else
		{
			return append('(').append(String.valueOf(null)).append(')');
		}
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public CTraceBuffer appendConstructorName()
	{
		return appendMethodName(ITracer.CONSTRUCTOR);
	}
	/**--------------------------------------------------------------------
	 * ajoute le "temps" sous la forme "mm:ss:mmm" dans le 
	 * @return
	 */
	public CTraceBuffer appendDateMMSSmmm()
	{
		Calendar wRightNow = Calendar.getInstance();
		addNumStringInText(this, wRightNow.get(Calendar.MINUTE), 2);
		this.append(':');
		addNumStringInText(this, wRightNow.get(Calendar.SECOND), 2);
		this.append('~');
		addNumStringInText(this, wRightNow.get(Calendar.MILLISECOND), 3);

		return this;
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value]" dans le CTraceBuffer en cadrant l'id sur "aIdSize" caractères
	 * et la valeur sur "aValueSize" caractères
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValueSize
	 * @param aValue
	 */
	public CTraceBuffer appendDescr(Integer aIdSize, String aId, Integer aValueSize, String aValue)
	{
		append(' ');
		addAlignStringToLeft(aId, aIdSize.intValue());
		append(DESCR_VALUE_START);
		addAlignStringToLeft(aValue, aValueSize.intValue());
		append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value]" dans le CTraceBuffer en cadrant l'id sur "aIdSize" caractères.
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValueSize
	 * @param aValue
	 */
	public CTraceBuffer appendDescr(Integer aIdSize, String aId, String aValue)
	{
		append(' ');
		addAlignStringToLeft(aId, aIdSize.intValue());
		append(DESCR_VALUE_START);
		append(aValue);
		append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * Ajoute un couple " id=[value|valueB]" dans le CTraceBuffer en cadrant l'id sur "aIdSize" caractères.
	 * @param aSB
	 * @param aIdSize
	 * @param aId
	 * @param aValue
	 * @param aValueB
	 */
	public CTraceBuffer appendDescr(Integer aIdSize, String aId, String aValue, String aValueB)
	{
		append(' ');
		addAlignStringToLeft(aId, aIdSize.intValue());
		append(DESCR_VALUE_START);
		append(aValue).append('|').append(aValueB);
		append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aDescriber
	 * @return
	 */
	public CTraceBuffer appendDescr(ITraceDescriber aDescriber)
	{
		aDescriber.addDescrInTB(this);
		return this;
	}
	/**
	 * Appends the "description " of the <code>boolean</code> value
	 * argument to this trace buffer:<br>
	 * <code>aId=[theBoolValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 * <p>
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aBoolValue   a <code>boolean</code> value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, boolean aBoolValue)
	{
		return appendDescr(aId, String.valueOf(aBoolValue));
	}
  /**
   * @param aId
   * @param aBoolValue
   * @param aBoolValueB
   * @return
   */
  public CTraceBuffer appendDescr(String aId, boolean aBoolValue, boolean aBoolValueB)
  {
    return appendDescr(aId, String.valueOf(aBoolValue),String.valueOf(aBoolValueB));
  }
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aBoolValue
	 * @param aIntValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, boolean aBoolValue, int aIntValue)
	{
		return appendDescr(aId, String.valueOf(aBoolValue), String.valueOf(aIntValue));
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aBoolValue
	 * @param aLongValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, boolean aBoolValue, long aLongValue)
	{
		return appendDescr(aId, String.valueOf(aBoolValue), String.valueOf(aLongValue));
	}
  public CTraceBuffer appendDescr(String aId, boolean aBoolValue, String aValue)
  {
    return appendDescr(aId, String.valueOf(aBoolValue), aValue);
  }
	/**
	 * Appends the "description" of the <code>int</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[theIntValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aIntValue   the <code>int</code> value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, char aCharValue)
	{
		return appendDescr(aId, String.valueOf(aCharValue));
	}

	/**
	 * Appends the "description" of the <code>Class</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[theClassName]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>getName()</code>, and the characters of that 
	 * string are then appended to this trace-buffer like :<br>
	 * <code>aId=[theClasseName]</code>
	 *
	 * @param   aId   the <code>String</code> identifier.
	 * @param   aClass   the <code>Class</code> which name is the value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, Class aClass)
	{
		if (aClass == null)
			return appendDescr(aId, (String) null);
		else
		{
			String wName = aClass.getName();
			int wPos = wName.lastIndexOf('.');
			return appendDescr(aId, ((wPos > -1 && wPos < wName.length() - 1) ? wName.substring(wPos + 1) : wName));
		}
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aIntValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, int aIntValue)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aIntValue).append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aIntValue
	 * @param aIntValueB
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, int aIntValue, int aIntValueB)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aIntValue).append('|').append(aIntValueB).append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aIntValue
	 * @param aIntValueB
	 * @param aIntValueC
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, int aIntValue, int aIntValueB, int aIntValueC)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aIntValue).append('|').append(aIntValueB).append('|').append(aIntValueC).append(
			DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aIntValue
	 * @param aValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, int aIntValue, String aValue)
	{
		return appendDescr(aId, String.valueOf(aIntValue), aValue);
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aIntValue
	 * @param aValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, double aDoubleValue)
	{
		return appendDescr(aId, String.valueOf(aDoubleValue));
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aDescriber
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, ITraceDescriber aDescriber)
	{
		append(' ').append(aId).append(DESCR_VALUE_START);
		aDescriber.addDescrInTB(this);
		append(DESCR_VALUE_END);
		return this;
	}
  /**
   * 14w_006 - Intégration TermServer - Contrôle des traces
   * 
   * @param aTB
   * @param aTracer
   * @return
   */
  public CTraceBuffer appendDescr(String aId, ITracer aTracer)
  {
    appendDescr(aId, aTracer.getClass());
    
    if (aTracer instanceof CTraceCli )
    {
      append( ((CTraceCli)aTracer).toStringDescrFull());
    } 
    else if (aTracer instanceof CTraceCliChanel )
    {
      append( ((CTraceCliChanel)aTracer).toStringDescr());
    }
      
    return this;
  }
	/**
	 * Appends the "description" of the <code>long</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[theLongValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aLongValue   the <code>long</code> value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, long aLongValue)
	{
		return appendDescr(aId, String.valueOf(aLongValue));
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aLongValue
	 * @param aLongValueB
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, long aLongValue, long aLongValueB)
	{
		return appendDescr(aId, String.valueOf(aLongValue), String.valueOf(aLongValueB));
	}
	/**
	 * Appends the "description " of the <code>String</code> value
	 * argument to this trace buffer:<br>
	 * <code>aId=[theStringValue]</code>
	 * <p>
	 * The characters of the <code>String</code> argument are appended, in 
	 * order, to the contents of this trace-buffer, increasing the 
	 * length of this trace-buffer by the length of the argument.
	 * <p>
	 * If <code>str</code> is <code>null</code>, then the four characters 
	 * <code>"null"</code> are appended to this trace-buffer.
	 * <p>
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aStringValue   a <code>String</code> value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, String aStringValue)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aStringValue).append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aValue
	 * @param aIntValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, String aValue, int aIntValue)
	{
		return appendDescr(aId, aValue, String.valueOf(aIntValue));
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aValue
	 * @param aIntValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, String aValue, long aLongValue)
	{
		return appendDescr(aId, aValue, String.valueOf(aLongValue));
	}
	/**
   * 14w_005
   * 
	 * @param aId
	 * @param aStringValueA
	 * @param aStringValueB
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, String aStringValueA, String aStringValueB)
	{
		append(' ').append(aId).append(DESCR_VALUE_START).append(aStringValueA);
    
    if (aStringValueB!=null)
    {
      append('|').append(aStringValueB);
    }
    
    append(DESCR_VALUE_END);
		return this;
	}
	/**
   * 14w_005
   *
	 * @param aId
	 * @param aStringValueA
	 * @param aStringValueB
	 * @param aStringValueC
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, String aStringValueA, String aStringValueB, String aStringValueC)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aStringValueA);
    
    append('|').append(aStringValueB);
    
    if (aStringValueB!=null)
    {
      append('|').append(aStringValueC);
    }
    
    append(DESCR_VALUE_END);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param aId
	 * @param aStringValue
	 * @return
	 */
	public CTraceBuffer appendDescr(String aId, StringBuffer aStringValue)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START).append(aStringValue).append(DESCR_VALUE_END);
		return this;
	}
	/**
	 * Appends the "description " of the <code>boolean</code> value
	 * argument to this trace buffer:<br>
	 * <code>aId=[theBoolValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 * <p>
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aBoolValue   a <code>boolean</code> value.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescr(String aId, Throwable e)
	{
		String wClassName = e.getClass().getName();
		int wPos = wClassName.lastIndexOf('.');
		wClassName = ((wPos > -1 && wPos < wClassName.length() - 1) ? wClassName.substring(wPos + 1) : wClassName);

		String wMess = e.getLocalizedMessage();

		append(PREFIX).append(aId).append(DESCR_VALUE_START).append(wClassName);

		if (wMess != null)
			append(':').append(wMess);

		//append(':').append(firstLineOfeStackInString(e));
		append(':').append(firstAdonixLineOfeStackInString(e));

		append(DESCR_VALUE_END);

		return this;
	}
	/**
	 * Appends the "description Left Aligned" of the <code>Class</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[theValue + space(s) to pad to the total length ]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>getName()</code>, and the characters of that 
	 * string are then appended to this trace-buffer like :<br>
	 * <code>aId=[theClasseName]</code>
	 *
	 * @param   aId   the <code>String</code> identifier.
	 * @param   aClass   the <code>Class</code> which name is the value.
	 * @param   aValueLength   the length.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescrLeftAligned(String aId, Class aClass, int aValueLength)
	{
		if (aClass == null)
			return appendDescrLeftAligned(aId, (String) null, aValueLength);
		else
		{
			String wName = aClass.getName();
			int wPos = wName.lastIndexOf('.');
			return appendDescrLeftAligned(aId, ((wPos > -1 && wPos < wName.length() - 1) ? wName.substring(wPos + 1) : wName), aValueLength);
		}
	}
	/**
	 * Appends the "description Left Aligned" of the <code>String</code> value
	 * argument to this trace buffer:<br>
	 * <code>aId=[theStringValue + space(s) to pad to the total length ]</code>
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aStringValue   a <code>String</code> value.
	 * @param   aValueLength   the length.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescrLeftAligned(String aId, String aStringValue, int aValueLength)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START);
		int wLen = aStringValue.length();
		if (wLen > aValueLength)
			this.append(aStringValue.substring(0, aValueLength));
		else
			this.append(aStringValue).append(' ', aValueLength - wLen);
		this.append(DESCR_VALUE_END);
		return this;
	}
	/**
	 * Appends the "description Right Aligned" of the <code>int</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[space(s) to pad to the total length + theIntValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aIntValue   the <code>int</code> value.
	 * @param   aValueLength   the length.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescrRightAligned(String aId, int aIntValue, int aValueLength)
	{
		return appendDescrRightAligned(aId, String.valueOf(aIntValue), aValueLength);
	}
	/**
	 * Appends the "description Right Aligned" of the <code>long</code> 
	 * argument to this trace buffer:<br>
	 * <code>aId=[space(s) to pad to the total length + theLongValue]</code>
	 * <p>
	 * The argument is converted to a string as if by the method 
	 * <code>String.valueOf()</code>, and the characters of that 
	 * string are then appended to this trace-buffer.
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aLongValue   the <code>long</code> value.
	 * @param   aValueLength   the length.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescrRightAligned(String aId, long aLongValue, int aValueLength)
	{
		return appendDescrRightAligned(aId, String.valueOf(aLongValue), aValueLength);
	}

	/**
	 * Appends the "description Left Aligned" of the <code>String</code> value
	 * argument to this trace buffer:<br>
	 * <code>aId=[space(s) to pad to the total length + theStringValue]</code>
	 *
	 * @param   aId   a <code>String</code> identifier.
	 * @param   aStringValue   a <code>String</code> value.
	 * @param   aValueLength   the length.
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendDescrRightAligned(String aId, String aStringValue, int aValueLength)
	{
		this.append(' ').append(aId).append(DESCR_VALUE_START);
		int wLen = aStringValue.length();
		if (wLen > aValueLength)
			this.append(aStringValue.substring(0, aValueLength));
		else
			this.append(' ', aValueLength - wLen).append(aStringValue);
		this.append(DESCR_VALUE_END);
		return this;
	}
	/**
	 * Appends the "formated" MethodName argument to this trace buffer:<br>
	 * <code>.aMethodName\t</code>
	 *
	 * @param   aMethodName   a <code>String</code> name of a method
	 * @return  a reference to this <code>CTraceBuffer</code> object.
	 * @see     java.lang.CTraceBuffer#append(java.lang.String)
	 */
	public CTraceBuffer appendMethodName(String aMethodName)
	{
		this.append('.').append(aMethodName, LENGTH_MAX_METHODNAME).append(METHODNAME_SUFFIX);
		return this;
	}
	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	public CTraceBuffer appendThrowableDescr(Throwable e)
	{
		String wStack = eStackToString(e);
		//int wLen = wStack.length() + 128;

		append(LIB_CLASS).append(eToName(e)).append(']').append('\n');

		//Affichage du "why"
		try
		{
			Class wClass = e.getClass();
			Method wMethod = wClass.getMethod(METHOD_WHY, null);
			if (wMethod != null)
			{
				String wWhy = (String) wMethod.invoke(e, null);
				append(LIB_WHY).append(wWhy).append(']').append('\n');
			}
		}
		catch (NoSuchMethodException e1)
		{
			//nothing
		}
		catch (Exception e2)
		{
			append(LIB_MESS_IE).append("Can't get the 'why' of the Throwable.").append(']').append('\n');
			append(LIB_MESS_IE).append(e2.getMessage()).append(']').append('\n');
			append(LIB_MESS_IE).append(e2.getClass().getName()).append(']').append('\n');
			append(LIB_MESS_IE).append(eStackToString(e2)).append(']').append('\n');
		}

		//	Affichage du "premier message"
		append(LIB_MESS).append(e.getMessage()).append(']').append('\n');
		//	Affichage des messages "complémentaires"
		try
		{
			Class wClass = e.getClass();
			Method wMethod = wClass.getMethod(METHOD_GETLISTOFMESS, null);
			//si la méthode existe
			if (wMethod != null)
			{
				List wComplement = (List) wMethod.invoke(e, null);
				int wMax = wComplement.size();
				if (wMax > 1)
				{
					int wI = 0;
					while (wI < wMax)
					{
						append(LIB_MESS).append(wComplement.get(wI)).append(']').append('\n');
						wI++;
					}
				}
			}
		}
		catch (NoSuchMethodException e1)
		{
			//nothing
		}
		catch (Exception e3)
		{
			append(LIB_MESS).append("Can't get the 'message(s)' of the Throwable.").append(']').append('\n');
			append(LIB_MESS).append(e3.getMessage()).append(']').append('\n');
			append(LIB_MESS).append(e3.getClass().getName()).append(']').append('\n');
			append(LIB_MESS).append(eStackToString(e3)).append(']').append('\n');
		}

		append(LIB_STACK).append('\n').append(wStack).append(']').append('\n');

		return this;
	}
	/**
	 * Returns the current capacity of the String buffer. The capacity
	 * is the amount of storage available for newly inserted
	 * characters; beyond which an allocation will occur.
	 *
	 * @return  the current capacity of this trace-buffer.
	 */
	public int capacity()
	{
		return value.length;
	}
	/**
	 * The specified character of the sequence currently represented by 
	 * the trace-buffer, as indicated by the <code>index</code> argument, 
	 * is returned. The first character of a trace-buffer is at index 
	 * <code>0</code>, the next at index <code>1</code>, and so on, for 
	 * array indexing. 
	 * <p>
	 * The index argument must be greater than or equal to 
	 * <code>0</code>, and less than the length of this trace-buffer. 
	 *
	 * @param      index   the index of the desired character.
	 * @return     the character at the specified index of this trace-buffer.
	 * @exception  IndexOutOfBoundsException  if <code>index</code> is 
	 *             negative or greater than or equal to <code>length()</code>.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized char charAt(int index)
	{
		if ((index < 0) || (index >= count))
		{
			throw new StringIndexOutOfBoundsException(index);
		}
		return value[index];
	}
	/**
	 * Copies the buffer value.  This is normally only called when shared
	 * is true.  It should only be called from a synchronized method.
	 */
	private final void copy()
	{
		char newValue[] = new char[value.length];
		System.arraycopy(value, 0, newValue, 0, count);
		value = newValue;
		shared = false;
	}
	/**
	 * @return     This trace-buffer.
	 */
	public synchronized CTraceBuffer delete()
	{
		count = 0;
		value[0] = '\0';
		shared = false;
		return this;
	}

	/**
	 * Removes the characters in a substring of this <code>CTraceBuffer</code>.
	 * The substring begins at the specified <code>start</code> and extends to
	 * the character at index <code>end - 1</code> or to the end of the
	 * <code>CTraceBuffer</code> if no such character exists. If
	 * <code>start</code> is equal to <code>end</code>, no changes are made.
	 *
	 * @param      start  The beginning index, inclusive.
	 * @param      end    The ending index, exclusive.
	 * @return     This trace-buffer.
	 * @exception  StringIndexOutOfBoundsException  if <code>start</code>
	 *             is negative, greater than <code>length()</code>, or
	 *		          greater than <code>end</code>.
	 * @since      1.2
	 */
	public synchronized CTraceBuffer delete(int start, int end)
	{
		if (start < 0)
			throw new StringIndexOutOfBoundsException(start);
		if (end > count)
			end = count;
		if (start > end)
			throw new StringIndexOutOfBoundsException();

		int len = end - start;
		if (len > 0)
		{
			if (shared)
				copy();
			System.arraycopy(value, start + len, value, start, count - end);
			count -= len;
		}
		return this;
	}
	/**
	 * Removes the character at the specified position in this
	 * <code>CTraceBuffer</code> (shortening the <code>CTraceBuffer</code>
	 * by one character).
	 *
	 * @param       index  Index of character to remove
	 * @return      This trace-buffer.
	 * @exception   StringIndexOutOfBoundsException  if the <code>index</code>
	 *		    is negative or greater than or equal to
	 *		    <code>length()</code>.
	 * @since       1.2
	 */
	public synchronized CTraceBuffer deleteCharAt(int index)
	{
		if ((index < 0) || (index >= count))
			throw new StringIndexOutOfBoundsException();

		if (shared)
			copy();
		System.arraycopy(value, index + 1, value, index, count - index - 1);
		count--;
		return this;
	}
	/**
	 * Ensures that the capacity of the buffer is at least equal to the
	 * specified minimum.
	 * If the current capacity of this trace-buffer is less than the 
	 * argument, then a new internal buffer is allocated with greater 
	 * capacity. The new capacity is the larger of: 
	 * <ul>
	 * <li>The <code>minimumCapacity</code> argument. 
	 * <li>Twice the old capacity, plus <code>2</code>. 
	 * </ul>
	 * If the <code>minimumCapacity</code> argument is nonpositive, this
	 * method takes no action and simply returns.
	 *
	 * @param   minimumCapacity   the minimum desired capacity.
	 */
	public synchronized void ensureCapacity(int minimumCapacity)
	{
		if (minimumCapacity > value.length)
		{
			expandCapacity(minimumCapacity);
		}
	}
	/**
	 * This implements the expansion semantics of ensureCapacity but is
	 * unsynchronized for use internally by methods which are already
	 * synchronized.
	 *
	 * @see java.lang.CTraceBuffer#ensureCapacity(int)
	 */
	private void expandCapacity(int minimumCapacity)
	{
		int newCapacity = (value.length + 1) * 2;
		if (newCapacity < 0)
		{
			newCapacity = Integer.MAX_VALUE;
		}
		else if (minimumCapacity > newCapacity)
		{
			newCapacity = minimumCapacity;
		}

		char newValue[] = new char[newCapacity];
		System.arraycopy(value, 0, newValue, 0, count);
		value = newValue;
		shared = false;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	final byte[] getBytes()
	{
		return new String(this.value, 0, count).getBytes();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	final char[] getChars()
	{
		return value;
	}
	/**
	 * Characters are copied from this trace-buffer into the 
	 * destination character array <code>dst</code>. The first character to 
	 * be copied is at index <code>srcBegin</code>; the last character to 
	 * be copied is at index <code>srcEnd-1</code>. The total number of 
	 * characters to be copied is <code>srcEnd-srcBegin</code>. The 
	 * characters are copied into the subarray of <code>dst</code> starting 
	 * at index <code>dstBegin</code> and ending at index:
	 * <p><blockquote><pre>
	 * dstbegin + (srcEnd-srcBegin) - 1
	 * </pre></blockquote>
	 *
	 * @param      srcBegin   start copying at this offset in the trace-buffer.
	 * @param      srcEnd     stop copying at this offset in the trace-buffer.
	 * @param      dst        the array to copy the data into.
	 * @param      dstBegin   offset into <code>dst</code>.
	 * @exception  NullPointerException if <code>dst</code> is 
	 *             <code>null</code>.
	 * @exception  IndexOutOfBoundsException  if any of the following is true:
	 *             <ul>
	 *             <li><code>srcBegin</code> is negative
	 *             <li><code>dstBegin</code> is negative
	 *             <li>the <code>srcBegin</code> argument is greater than 
	 *             the <code>srcEnd</code> argument.
	 *             <li><code>srcEnd</code> is greater than 
	 *             <code>this.length()</code>, the current length of this 
	 *             trace-buffer.
	 *             <li><code>dstBegin+srcEnd-srcBegin</code> is greater than 
	 *             <code>dst.length</code>
	 *             </ul>
	 */
	public synchronized void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin)
	{
		if (srcBegin < 0)
		{
			throw new StringIndexOutOfBoundsException(srcBegin);
		}
		if ((srcEnd < 0) || (srcEnd > count))
		{
			throw new StringIndexOutOfBoundsException(srcEnd);
		}
		if (srcBegin > srcEnd)
		{
			throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
		}
		System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	final char[] getValue()
	{
		return value;
	}
	/**--------------------------------------------------------------------
	 * @param aChar
	 * @return
	 */
	public synchronized int indexOf(char aChar)
	{
		int wI = 0;
		while (wI < count)
		{
			if (value[wI] == aChar)
				return wI;
			wI++;
		}
		return -1;
	}
	/**
	 * Inserts the string representation of the <code>boolean</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the indicated 
	 * offset. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      b        a <code>boolean</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(boolean)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public CTraceBuffer insert(int offset, boolean b)
	{
		return insert(offset, String.valueOf(b));
	}

	/**
	 * Inserts the string representation of the <code>char</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is inserted into the contents of this string 
	 * buffer at the position indicated by <code>offset</code>. The length 
	 * of this trace-buffer increases by one. 
	 * <p>
	 * The overall effect is exactly as if the argument were converted to 
	 * a string by the method {@link String#valueOf(char)} and the character 
	 * in that string were then {@link #insert(int, String) inserted} into 
	 * this <code>CTraceBuffer</code> object at the position indicated by
	 * <code>offset</code>.
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      c        a <code>char</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  IndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized CTraceBuffer insert(int offset, char c)
	{
		int newcount = count + 1;
		if (newcount > value.length)
			expandCapacity(newcount);
		else if (shared)
			copy();
		System.arraycopy(value, offset, value, offset + 1, count - offset);
		value[offset] = c;
		count = newcount;
		return this;
	}
	/**
	 * Inserts the string representation of the <code>char</code> array 
	 * argument into this trace-buffer. 
	 * <p>
	 * The characters of the array argument are inserted into the 
	 * contents of this trace-buffer at the position indicated by 
	 * <code>offset</code>. The length of this trace-buffer increases by 
	 * the length of the argument. 
	 * <p>
	 * The overall effect is exactly as if the argument were converted to 
	 * a string by the method {@link String#valueOf(char[])} and the 
	 * characters of that string were then 
	 * {@link #insert(int,String) inserted} into this 
	 * <code>CTraceBuffer</code>  object at the position indicated by
	 * <code>offset</code>.
	 *
	 * @param      offset   the offset.
	 * @param      str      a character array.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 */
	public synchronized CTraceBuffer insert(int offset, char str[])
	{
		if ((offset < 0) || (offset > count))
		{
			throw new StringIndexOutOfBoundsException();
		}
		int len = str.length;
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		else if (shared)
			copy();
		System.arraycopy(value, offset, value, offset + len, count - offset);
		System.arraycopy(str, 0, value, offset, len);
		count = newcount;
		return this;
	}
	/**
	 * Inserts the string representation of a subarray of the <code>str</code>
	 * array argument into this trace-buffer. The subarray begins at the
	 * specified <code>offset</code> and extends <code>len</code> characters.
	 * The characters of the subarray are inserted into this trace-buffer at
	 * the position indicated by <code>index</code>. The length of this
	 * <code>CTraceBuffer</code> increases by <code>len</code> characters.
	 *
	 * @param      index    position at which to insert subarray.
	 * @param      str       A character array.
	 * @param      offset   the index of the first character in subarray to
	 *		   to be inserted.
	 * @param      len      the number of characters in the subarray to
	 *		   to be inserted.
	 * @return     This trace-buffer.
	 * @exception  StringIndexOutOfBoundsException  if <code>index</code>
	 *             is negative or greater than <code>length()</code>, or
	 *		   <code>offset</code> or <code>len</code> are negative, or
	 *		   <code>(offset+len)</code> is greater than
	 *		   <code>str.length</code>.
	 * @since 1.2
	 */
	public synchronized CTraceBuffer insert(int index, char str[], int offset, int len)
	{
		if ((index < 0) || (index > count))
			throw new StringIndexOutOfBoundsException();
		if ((offset < 0) || (offset + len < 0) || (offset + len > str.length))
			throw new StringIndexOutOfBoundsException(offset);
		if (len < 0)
			throw new StringIndexOutOfBoundsException(len);
		int newCount = count + len;
		if (newCount > value.length)
			expandCapacity(newCount);
		else if (shared)
			copy();
		System.arraycopy(value, index, value, index + len, count - index);
		System.arraycopy(str, offset, value, index, len);
		count = newCount;
		return this;
	}
	/**
	 * Inserts the trace-buffer. into this trace-buffer. 
	 * <p>
	 * The characters of the <code>CTraceBuffer</code> argument are inserted, in 
	 * order, into this trace-buffer at the indicated offset, moving up any 
	 * characters originally above that position and increasing the length 
	 * of this trace-buffer by the length of the argument. If 
	 * <code>aTraceBuffer</code> is <code>null</code>, then the four characters 
	 * <code>"null"</code> are inserted into this trace-buffer.
	 *
	 * @param      offset   the offset.
	 * @param      aTraceBuffer      a trace-buffer to be inserted
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized CTraceBuffer insert(int offset, CTraceBuffer aTraceBuffer)
	{
		if ((offset < 0) || (offset > count))
		{
			throw new StringIndexOutOfBoundsException();
		}
		if (aTraceBuffer == null)
			return insert(offset, String.valueOf(null));

		int len = aTraceBuffer.length();
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		else if (shared)
			copy();
		System.arraycopy(value, offset, value, offset + len, count - offset);
		aTraceBuffer.getChars(0, len, value, offset);
		count = newcount;
		return this;

	}
	/**
	 * Inserts the string representation of the <code>double</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the indicated 
	 * offset. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      d        a <code>double</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(double)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public CTraceBuffer insert(int offset, double d)
	{
		return insert(offset, String.valueOf(d));
	}
	/**
	 * Inserts the string representation of the <code>float</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the indicated 
	 * offset. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      f        a <code>float</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(float)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public CTraceBuffer insert(int offset, float f)
	{
		return insert(offset, String.valueOf(f));
	}
	/**
	 * Inserts the string representation of the second <code>int</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the indicated 
	 * offset. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      i        an <code>int</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(int)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public CTraceBuffer insert(int offset, int i)
	{
		return insert(offset, String.valueOf(i));
	}
	/**
	 * Inserts the string representation of the <code>long</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the position 
	 * indicated by <code>offset</code>. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      l        a <code>long</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(long)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public CTraceBuffer insert(int offset, long l)
	{
		return insert(offset, String.valueOf(l));
	}
	/**
	 * Inserts the string representation of the <code>Object</code> 
	 * argument into this trace-buffer. 
	 * <p>
	 * The second argument is converted to a string as if by the method 
	 * <code>String.valueOf</code>, and the characters of that 
	 * string are then inserted into this trace-buffer at the indicated 
	 * offset. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      obj      an <code>Object</code>.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.String#valueOf(java.lang.Object)
	 * @see        java.lang.CTraceBuffer#insert(int, java.lang.String)
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized CTraceBuffer insert(int offset, Object obj)
	{
		return insert(offset, String.valueOf(obj));
	}
	/**
	 * Inserts the string into this trace-buffer. 
	 * <p>
	 * The characters of the <code>String</code> argument are inserted, in 
	 * order, into this trace-buffer at the indicated offset, moving up any 
	 * characters originally above that position and increasing the length 
	 * of this trace-buffer by the length of the argument. If 
	 * <code>str</code> is <code>null</code>, then the four characters 
	 * <code>"null"</code> are inserted into this trace-buffer.
	 * <p>
	 * The character at index <i>k</i> in the new character sequence is 
	 * equal to:
	 * <ul>
	 * <li>the character at index <i>k</i> in the old character sequence, if 
	 * <i>k</i> is less than <code>offset</code> 
	 * <li>the character at index <i>k</i><code>-offset</code> in the 
	 * argument <code>str</code>, if <i>k</i> is not less than 
	 * <code>offset</code> but is less than <code>offset+str.length()</code> 
	 * <li>the character at index <i>k</i><code>-str.length()</code> in the 
	 * old character sequence, if <i>k</i> is not less than 
	 * <code>offset+str.length()</code>
	 * </ul><p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than or equal to the length of this 
	 * trace-buffer. 
	 *
	 * @param      offset   the offset.
	 * @param      str      a string.
	 * @return     a reference to this <code>CTraceBuffer</code> object.
	 * @exception  StringIndexOutOfBoundsException  if the offset is invalid.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized CTraceBuffer insert(int offset, String str)
	{
		if ((offset < 0) || (offset > count))
		{
			throw new StringIndexOutOfBoundsException();
		}

		if (str == null)
		{
			str = String.valueOf(str);
		}
		int len = str.length();
		int newcount = count + len;
		if (newcount > value.length)
			expandCapacity(newcount);
		else if (shared)
			copy();
		System.arraycopy(value, offset, value, offset + len, count - offset);
		str.getChars(0, len, value, offset);
		count = newcount;
		return this;
	}
	/**
	 * Returns the length (character count) of this trace-buffer.
	 *
	 * @return  the length of the sequence of characters currently 
	 *          represented by this trace-buffer.
	 */
	public int length()
	{
		return count;
	}
	/**
	 * readObject is called to restore the state of the CTraceBuffer from
	 * a stream.
	 */
	private synchronized void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException
	{
		s.defaultReadObject();
		value = (char[]) value.clone();
		shared = false;
	}
	/**--------------------------------------------------------------------
	 * @param aWhy
	 * @param aBy
	 * @return
	 */
	public synchronized CTraceBuffer replace(char aWhy, char aBy)
	{
		int wI = 0;
		while (wI < count)
		{
			if (value[wI] == aWhy)
				value[wI] = aBy;
			wI++;
		}
		return this;
	}
	/**
	 * Replaces the characters in a substring of this <code>CTraceBuffer</code>
	 * with characters in the specified <code>String</code>. The substring
	 * begins at the specified <code>start</code> and extends to the character
	 * at index <code>end - 1</code> or to the end of the
	 * <code>CTraceBuffer</code> if no such character exists. First the
	 * characters in the substring are removed and then the specified
	 * <code>String</code> is inserted at <code>start</code>. (The
	 * <code>CTraceBuffer</code> will be lengthened to accommodate the
	 * specified String if necessary.)
	 * 
	 * @param      start    The beginning index, inclusive.
	 * @param      end      The ending index, exclusive.
	 * @param      str   String that will replace previous contents.
	 * @return     This trace-buffer.
	 * @exception  StringIndexOutOfBoundsException  if <code>start</code>
	 *             is negative, greater than <code>length()</code>, or
	 *		   greater than <code>end</code>.
	 * @since      1.2
	 */
	public synchronized CTraceBuffer replace(int start, int end, String str)
	{
		if (start < 0)
			throw new StringIndexOutOfBoundsException(start);
		if (end > count)
			end = count;
		if (start > end)
			throw new StringIndexOutOfBoundsException();

		int len = str.length();
		int newCount = count + len - (end - start);
		if (newCount > value.length)
			expandCapacity(newCount);
		else if (shared)
			copy();

		System.arraycopy(value, end, value, start + len, count - end);
		str.getChars(0, len, value, start);
		count = newCount;
		return this;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public synchronized CTraceBuffer replaceSpecialTraceChars()
	{
		int wI = 0;
		while (wI < count)
		{
			if (value[wI] == CTraceCli.TRACE_PROTOCOLE_DELIM)
				value[wI] = '!';
			else if (value[wI] == '\n')
				value[wI] = CTraceCli.TRACE_LINE_DELIM;
			else if (value[wI] == '\r')
				value[wI] = CTraceCli.TRACE_CR_DELIM;
			wI++;
		}
		return this;
	}

	/**
	 * The character sequence contained in this trace-buffer is 
	 * replaced by the reverse of the sequence. 
	 * <p>
	 * Let <i>n</i> be the length of the old character sequence, the one 
	 * contained in the trace-buffer just prior to execution of the 
	 * <code>reverse</code> method. Then the character at index <i>k</i> in 
	 * the new character sequence is equal to the character at index 
	 * <i>n-k-1</i> in the old character sequence.
	 *
	 * @return  a reference to this <codeCTraceBuffer</code> object..
	 * @since   JDK1.0.2
	 */
	public synchronized CTraceBuffer reverse()
	{
		if (shared)
			copy();
		int n = count - 1;
		for (int j = (n - 1) >> 1; j >= 0; --j)
		{
			char temp = value[j];
			value[j] = value[n - j];
			value[n - j] = temp;
		}
		return this;
	}

	/**
	 * The character at the specified index of this trace-buffer is set 
	 * to <code>ch</code>. The trace-buffer is altered to represent a new 
	 * character sequence that is identical to the old character sequence, 
	 * except that it contains the character <code>ch</code> at position 
	 * <code>index</code>. 
	 * <p>
	 * The offset argument must be greater than or equal to 
	 * <code>0</code>, and less than the length of this trace-buffer. 
	 *
	 * @param      index   the index of the character to modify.
	 * @param      ch      the new character.
	 * @exception  IndexOutOfBoundsException  if <code>index</code> is 
	 *             negative or greater than or equal to <code>length()</code>.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized void setCharAt(int index, char ch)
	{
		if ((index < 0) || (index >= count))
		{
			throw new StringIndexOutOfBoundsException(index);
		}
		if (shared)
			copy();
		value[index] = ch;
	}

	/**
	 * Sets the length of this String buffer.
	 * This trace-buffer is altered to represent a new character sequence 
	 * whose length is specified by the argument. For every nonnegative 
	 * index <i>k</i> less than <code>newLength</code>, the character at 
	 * index <i>k</i> in the new character sequence is the same as the 
	 * character at index <i>k</i> in the old sequence if <i>k</i> is less 
	 * than the length of the old character sequence; otherwise, it is the 
	 * null character <code>'\u0000'</code>. 
	 *  
	 * In other words, if the <code>newLength</code> argument is less than 
	 * the current length of the trace-buffer, the trace-buffer is 
	 * truncated to contain exactly the number of characters given by the 
	 * <code>newLength</code> argument. 
	 * <p>
	 * If the <code>newLength</code> argument is greater than or equal 
	 * to the current length, sufficient null characters 
	 * (<code>'&#92;u0000'</code>) are appended to the trace-buffer so that 
	 * length becomes the <code>newLength</code> argument. 
	 * <p>
	 * The <code>newLength</code> argument must be greater than or equal 
	 * to <code>0</code>. 
	 *
	 * @param      newLength   the new length of the buffer.
	 * @exception  IndexOutOfBoundsException  if the
	 *               <code>newLength</code> argument is negative.
	 * @see        java.lang.CTraceBuffer#length()
	 */
	public synchronized void setLength(int newLength)
	{
		if (newLength < 0)
		{
			throw new StringIndexOutOfBoundsException(newLength);
		}

		if (newLength > value.length)
		{
			expandCapacity(newLength);
		}

		if (count < newLength)
		{
			if (shared)
				copy();
			for (; count < newLength; count++)
			{
				value[count] = '\0';
			}
		}
		else
		{
			count = newLength;
			if (shared)
			{
				if (newLength > 0)
				{
					copy();
				}
				else
				{
					// If newLength is zero, assume the CTraceBuffer is being
					// stripped for reuse; Make new buffer of default size
					value = new char[16];
					shared = false;
				}
			}
		}
	}
	/**--------------------------------------------------------------------
	 * The following two methods are needed by String to efficiently
	 * convert a CTraceBuffer into a String.  They are not public.
	 * They shouldn't be called by anyone but String.
	 */
	final void setShared()
	{
		shared = true;
	}

	/**
	 * Returns a new <code>String</code> that contains a subsequence of
	 * characters currently contained in this <code>CTraceBuffer</code>.The 
	 * substring begins at the specified index and extends to the end of the
	 * <code>CTraceBuffer</code>.
	 * 
	 * @param      start    The beginning index, inclusive.
	 * @return     The new string.
	 * @exception  StringIndexOutOfBoundsException  if <code>start</code> is
	 *             less than zero, or greater than the length of this
	 *             <code>CTraceBuffer</code>.
	 * @since      1.2
	 */
	public String substring(int start)
	{
		return substring(start, count);
	}
	/**
	 * Returns a new <code>String</code> that contains a subsequence of
	 * characters currently contained in this <code>CTraceBuffer</code>. The 
	 * substring begins at the specified <code>start</code> and 
	 * extends to the character at index <code>end - 1</code>. An
	 * exception is thrown if 
	 *
	 * @param      start    The beginning index, inclusive.
	 * @param      end      The ending index, exclusive.
	 * @return     The new string.
	 * @exception  StringIndexOutOfBoundsException  if <code>start</code>
	 *             or <code>end</code> are negative or greater than
	 *		   <code>length()</code>, or <code>start</code> is
	 *		   greater than <code>end</code>.
	 * @since      1.2 
	 */
	public synchronized String substring(int start, int end)
	{
		if (start < 0)
			throw new StringIndexOutOfBoundsException(start);
		if (end > count)
			throw new StringIndexOutOfBoundsException(end);
		if (start > end)
			throw new StringIndexOutOfBoundsException(end - start);
		return new String(value, start, end - start);
	}

	/**
	 * Converts to a string representing the data in this trace-buffer.
	 * A new <code>String</code> object is allocated and initialized to 
	 * contain the character sequence currently represented by this 
	 * trace-buffer. This <code>String</code> is then returned. Subsequent 
	 * changes to the trace-buffer do not affect the contents of the 
	 * <code>String</code>. 
	 * <p>
	 * Implementation advice: This method can be coded so as to create a new
	 * <code>String</code> object without allocating new memory to hold a 
	 * copy of the character sequence. Instead, the string can share the 
	 * memory used by the trace-buffer. Any subsequent operation that alters 
	 * the content or capacity of the trace-buffer must then make a copy of 
	 * the internal buffer at that time. This strategy is effective for 
	 * reducing the amount of memory allocated by a string concatenation 
	 * operation when it is implemented using a trace-buffer.
	 *
	 * @return  a string representation of the trace-buffer.
	 */
	public String toString()
	{
		/*
		 * ogattaz 
		 * return new String(this);
		 */
		return new String(this.value, 0, count);
	}
}
