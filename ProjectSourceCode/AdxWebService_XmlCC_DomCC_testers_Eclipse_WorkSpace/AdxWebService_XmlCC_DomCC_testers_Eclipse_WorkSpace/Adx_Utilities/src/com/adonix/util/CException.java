package com.adonix.util;

import java.util.ArrayList;
import java.util.List;

/**
 *  * Implementation générique des Exceptions.
 * <p>
 * Celle-ci gère une cause explicite pWhy, et un complement d'explication</p>
 * 
 * @author Adonix Grenoble
 * @version 140_003
 */

public class CException extends Exception implements IDescriber
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257852086359437881L;
	/**
	 * 
	 */
	private final static String ADONIX_CLASSES_PREFIX = "com.adonix.";
	/**
	 * 
	 */
	private static final char CHAR_OD = 0x0D;
  /**
   * 14w_008 - Intégration WebService - formatage Exception
   */
  public static final char SEPARATOR_LINE = '\n';
  public static final char SEPARATOR_COMA= ',';
	/**
	 * 
	 */
	private static final String EMPTY = "";
	private final static String MESS_CANT_CLEAN = "Can't clean";
	/**
	 * 
	 */
	private final static String MESS_CANT_GET = "Can't get";
	private final static String MESS_LINES_OF_STACK = "the lines of stack of the Throwable ";
	private final static String MESS_NO_STACK = "No stack available";
	/**
	 * 
	 */
	private static final boolean MESS_WHITH_STACK = true;
	/**
	 * 
	 */
	private final static String MESSAGE_SEPARATOR = " | ";
	private final static char MESSAGES_SEPARATOR = CHAR_OD;
	/**
	 * 
	 */
	private static final String PREFIX_CLASS = "class";
	private static final String PREFIX_MESS = "mess";
	private static final String PREFIX_STACK = "stack";
	private static final String PREFIX_WHY = "why";
	private static final String STACK_PREFIX = "\tat ";
	/**	
	 * Cause inconue
	 */
	public final static int WHY_UNKNOWN = -1;
  /*
   * Cause non spécifiée
   */
  public final static int WHY_UNSPECIFIED = -2;
	/**
	 * Message explicite de la cause inconue
	 */
	public final static String WHY_UNKNOWN_MESS = "Unknown 'Why'";

	/**--------------------------------------------------------------------
	 * Ajoute la composante (classe) d'une exception dans un StringBuffer
	 * 
	 * @param aSB
	 * @param e
	 * @return
	 */
	private static StringBuffer addEClassInSB(StringBuffer aSB, Throwable e)
	{
		return aSB.append(PREFIX_CLASS).append('=').append('[').append(e.getClass().getName()).append(']');
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param e
	 * @return
	 */
	public static StringBuffer addEDescrFullInSB(StringBuffer aSB, Throwable e)
	{
   return addEDescrFullInSB(aSB,e,SEPARATOR_COMA); 
  }
  /**
   * @param aSB
   * @param e
   * @param aSeparator
   * @return
   */
  private static StringBuffer addEDescrFullInSB(StringBuffer aSB, Throwable e,char aSeparator)
  {
    if (aSeparator==SEPARATOR_LINE)
    {
      aSB.append(aSeparator);
    }
		addEClassInSB(aSB, e).append(SEPARATOR_LINE);

		if (e instanceof CException)
		{
			addEWhyInSB(aSB, (CException) e).append(SEPARATOR_LINE);
		}

		addEMessInSB(aSB, e).append(SEPARATOR_LINE);

		addEStackInSB(aSB, e,aSeparator).append(SEPARATOR_LINE);

		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param e
	 * @return
	 */
	public static StringBuffer addEDescrMiniInSB(StringBuffer aSB, Throwable e)
	{
		aSB.append(eClassAndMessInString(e));
		aSB.append(' ');
		aSB.append('-');
		aSB.append(' ');
		aSB.append(firstLineOfStackInString(e));
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * Ajoute la composante (mess) d'une exception dans un StringBuffer
	 * 
	 * @param aSB
	 * @param e
	 * @return
	 */
	private static StringBuffer addEMessInSB(StringBuffer aSB, Throwable e)
	{
		String wMess = e.getMessage();
		boolean wHasMess = (wMess!=null && wMess.length()>0);
		
		if (wHasMess)
		{
			aSB.append(PREFIX_MESS).append('=').append('[').append(wMess).append(']');
		}
		if (e instanceof CException)
		{
			CException wE = (CException) e;

			List wComplement = wE.getListOfMessages();
			int wI = 0;
			int wMax = wComplement.size();
			while (wI < wMax)
			{
				if (wI > 0 || wHasMess)
				{
					aSB.append('\n');
				}
				aSB.append(PREFIX_MESS).append('=').append('[').append(wComplement.get(wI)).append(']');
				wI++;
			}
			return aSB;
		}
		else
		{
			return aSB.append(PREFIX_MESS).append('=').append('[').append(e.getMessage()).append(']');
		}
	}
	/**--------------------------------------------------------------------
	 * Ajoute la composante (mess) d'une exception dans un StringBuffer
	 * 
	 * @param aSB
	 * @param e
	 * @return
	 */
	private static StringBuffer addEStackInSB(StringBuffer aSB, Throwable e,char aSeparator)
	{
		return aSB.append(PREFIX_STACK).append('=').append('[').append(getCleanedStackOfThrowable(e,aSeparator)).append(']');
	}
	/**--------------------------------------------------------------------
	 * Ajoute la composante (why) d'une exception dans un StringBuffer
   * 
   * 14w_008 - Intégration WebService
	 * 
	 * @param aSB
	 * @param e
	 * @return
	 */
	private static StringBuffer addEWhyInSB(StringBuffer aSB, CException e)
	{
    if (e.isWhy(WHY_UNSPECIFIED))
      return aSB;
    else
		  return aSB.append(PREFIX_WHY).append('=').append('[').append(e.getWhyString()).append(']');
	}
	/**--------------------------------------------------------------------
	 * @param aContext
	 * @param e
	 * @return
	 */
	private static String buildStackExceptionMessage(String aContext, Throwable e, boolean aWithStack)
	{
		StringBuffer wMess = new StringBuffer(256);
		wMess.append(aContext);
		wMess.append(' ');
		wMess.append(MESS_LINES_OF_STACK);
		wMess.append('.');
		wMess.append(' ');
		wMess.append('[');
		wMess.append(CStringFacilities.getClasseName(e.getClass()));
		wMess.append(']');
		wMess.append('.');
		if (e.getMessage() != null)
		{
			wMess.append(' ');
			wMess.append('[');
			wMess.append(e.getMessage());
			wMess.append(']');
			wMess.append('.');
		}
		///Java 1.2
		if (aWithStack)
		{
			putStackOfThrowableInSB(wMess, e);
		}
		//FJava 1.2*/

		return wMess.toString();
	}
	//FJava 1.2*/

	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	public static String eClassAndMessInString(Throwable e)
	{
		StringBuffer wSB = new StringBuffer(128);

		wSB.append(CStringFacilities.getClasseName(e.getClass()));

		if (e.getMessage() != null)
		{
			wSB.append(':').append(e.getMessage());
		}

		return wSB.toString();
	}
	/**--------------------------------------------------------------------
	 * Retourne les composantes (classe,why ,mess,stack) d'une exception dans une string
	 * 
   * 14w_008 - Intégration WebServices - refactoring
   * 
	 * @param e
	 * @return
	 */
	public static String eInString(Throwable e)
	{
		return eInString(e,SEPARATOR_COMA);
	}
  /**
   * 14w_008 - Intégration WebServices - new Method
   * 
   * @param e
   * @param aSeparator
   * @return
   */
  public static String eInString(Throwable e,char aSeparator)
  {
    return addEDescrFullInSB(new StringBuffer(512), e,aSeparator).toString();
  }
	/**--------------------------------------------------------------------
	 * Retourne les composantes (classe,mess,firstLineOfstack) d'une exception dans une string
	 * 
	 * @param e
	 * @return
	 */
	public static String eMiniInString(Throwable e)
	{
		return addEDescrMiniInSB(new StringBuffer(128), e).toString();
	}
	/**--------------------------------------------------------------------
	 * @param wStack
	 * @return
	 */
	private static String extractFirstAdonixLineOfStack(String aStack)
	{
		int wPos = aStack.indexOf(ADONIX_CLASSES_PREFIX);

		if (wPos > -1)
		{
			aStack = aStack.substring(wPos);
			wPos = aStack.indexOf(',');
			if (wPos > -1)
			{
				aStack = aStack.substring(0, wPos);
			}
		}
		return aStack;
	}
	/**--------------------------------------------------------------------
	 * @param wStack
	 * @return
	 */
	private static String extractFirstLineOfStack(String aStack,char aSeparator)
	{

		int wPos = aStack.indexOf(aSeparator);
		if (wPos > -1)
		{
			aStack = aStack.substring(0, wPos);
		}

		return aStack;
	}

	/**--------------------------------------------------------------------
	 * retourne la première ligne d'une stack contenant ADONIX_CLASSES_PREFIX
	 * @param e
	 * @return
	 */
	static public String firstAdonixLineOfStackInString(Throwable e)
	{
		String wStack = getCleanedStackOfThrowable(e,SEPARATOR_COMA);

		if (wStack.length() == 0)
		{
			return wStack;
		}
		else
		{
			return extractFirstAdonixLineOfStack(wStack);
		}
	}
	/**--------------------------------------------------------------------
	 * retourne la première ligne d'une stack et la première ligne
	 * contenant ADONIX_CLASSES_PREFIX
	 * 
	 * @param e
	 * @return
	 */
	public static String firstLineAndFirstAdonixOfStackInString(Throwable e)
	{
		String wStack = getCleanedStackOfThrowable(e,SEPARATOR_COMA);

		if (wStack.length() == 0)
		{
			return wStack;
		}
		else if (isFisrtLineIsAdonixLine(wStack))
		{
			return extractFirstLineOfStack(wStack,SEPARATOR_COMA);
		}
		else
		{
			StringBuffer wSB = new StringBuffer(256);
			wSB.append(extractFirstLineOfStack(wStack,SEPARATOR_COMA));
			wSB.append('/');
			wSB.append(extractFirstAdonixLineOfStack(wStack));
			return wSB.toString();
		}
	}

	/**--------------------------------------------------------------------
	 * Retourne la première ligne de la stack dans une String
	 * 
	 * @param e
	 * @return
	 */
	static public String firstLineOfStackInString(Throwable e)
	{
		String wStack = getCleanedStackOfThrowable(e,SEPARATOR_COMA);

		if (wStack.length() == 0)
		{
			return wStack;
		}
		else
		{
			return extractFirstLineOfStack(wStack,SEPARATOR_COMA);
		}
	}
	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	private static String getCleanedStackOfThrowable(Throwable aThrowable,char aSeparator)
	{
		String wCleanedStack = EMPTY;
		try
		{
			wCleanedStack = getStackOfThrowable(aThrowable);

			if (wCleanedStack.length() > 0 && aSeparator != SEPARATOR_LINE)
			{
				wCleanedStack = wCleanedStack.replace('\n', aSeparator);
			}
		}
		catch (Throwable e)
		{
			System.out.println(buildStackExceptionMessage(MESS_CANT_CLEAN, e, MESS_WHITH_STACK));
		}
		return wCleanedStack;
	}
	/**--------------------------------------------------------------------
	 * @param e
	 * @return
	 */
	private static String getStackOfThrowable(Throwable aThrowable)
	{
		/*//J# 1.1.4
		return EMPTY;
		//FJ# 1.1.4 */

		///Java 1.2
		return putStackInString(aThrowable);
		//FJava 1.2*/
	}
	//FJava 1.2*/
	/**--------------------------------------------------------------------
	 * @param aStack
	 * @return
	 */
	private static boolean isFisrtLineIsAdonixLine(String aStack)
	{
		int wPos = aStack.indexOf(ADONIX_CLASSES_PREFIX);

		return (wPos > -1 && wPos < 5);
	}
  /**
   * 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
   * 
   * @param aWhy
   * @return
   */
  public static boolean isValidWhy(int aWhy)
  {
    return (aWhy>0);
  }
	/**--------------------------------------------------------------------
	 * @param aThrowable
	 * @return
	 */
	///Java 1.2
	private static String putStackInString(Throwable aThrowable)
	{
		StringBuffer wRoughStack = new StringBuffer(1024);
		try
		{
			putStackOfThrowableInSB(wRoughStack, aThrowable);
		}
		catch (Throwable e)
		{
			System.out.println(buildStackExceptionMessage(MESS_CANT_GET, e, !MESS_WHITH_STACK));
		}
		return wRoughStack.toString();
	}

	/**--------------------------------------------------------------------
	 * @param aMessage
	 * @param e
	 */
	///Java 1.2
	private static StringBuffer putStackOfThrowableInSB(StringBuffer aSB, Throwable e)
	{
		StackTraceElement[] wStackElements = e.getStackTrace();
		StackTraceElement wStackElement;
		int wMax = wStackElements.length;
		if (wMax == 0)
		{
			aSB.append(MESS_NO_STACK);
		}
		else
		{
			int wI = 0;
			while (wI < wMax)
			{
				wStackElement = (StackTraceElement) wStackElements[wI];
				if (wI > 0)
				{
					aSB.append('\n');
				}
				aSB.append(wStackElement.getClassName());
				aSB.append('(');
				aSB.append(wStackElement.getMethodName());
				aSB.append(':');
				aSB.append(wStackElement.getLineNumber());
				aSB.append(')');
				wI++;
			}
		}
		return aSB;
	}
  /**
   * 14w_008 - Intégration Webservices - new method
   * 
   * @param e
   * @return
   */
  public static  String getStackInString(Throwable e)
  {
    return getStackOfThrowable(e);
  }
	/**	
	 * Complement d'explication de de l'exception
	 */
	private List pMessages = new ArrayList();
	/**	
	 * Pourquoi de de l'exception
	 */
	private int pWhy;
  /** 
   * 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
   * 
   * Pourquoi de l'exception "complementaire"
   */
  private int pCauseWhy = WHY_UNSPECIFIED;

	/**--------------------------------------------------------------------
	 * @param aWhy
	 */
	public CException(int aWhy)
	{
		super();
		setWhy(aWhy);
		pMessages.add(throwableComplementToString(this));

	}
	/**--------------------------------------------------------------------
	 * @param aWhy
	 * @param aMessage
	 */
	public CException(int aWhy, String aMessage)
	{
		super(aMessage);
		setWhy(aWhy);
		pMessages.add(throwableComplementToString(this));
	}
	/**--------------------------------------------------------------------
	 * @param aWhy
	 * @param aMessage
	 * @param aComplement
	 */
	public CException(int aWhy, String aMessage, Throwable aComplement)
	{
		super(aMessage);
		setWhy(aWhy);
		pMessages.add(throwableComplementToString(this));

		if (aComplement instanceof CException)
		{
      //14w_027 - Fiche 36012 - X3Client: Supervision des sockets
      CException wException = ((CException) aComplement);
      pMessages.addAll(wException.getListOfMessages());
      setCauseWhy( (wException.hasCauseWhy())?wException.getCauseWhy():wException.getWhy() ) ;
		}
		else
		{
			pMessages.add(throwableComplementToString(aComplement));
		}
	}
	/**--------------------------------------------------------------------
	 * @param aWhy
	 * @param aMessage
	 * @param aComplement
	 */
	public CException(int aWhy, Throwable aComplement)
	{
		this(aWhy, aComplement.getMessage(), aComplement);
	}
	/**
   * 14w_008 - Intégration WebServices
   * 
	 * @param aComplement
	 */
	public CException(Throwable aComplement)
	{
		this(WHY_UNSPECIFIED, aComplement.getMessage(), aComplement);
	}
  /**
   * 14w_008 - Intégration WebServices
   * @param aMessage 
   */
  public CException(String aMessage)
  {
    this(WHY_UNSPECIFIED, aMessage);
  }
	/**--------------------------------------------------------------------
	 * @param aComplement
	 */
	public void addComplement(String aComplement)
	{
		if (aComplement != null && aComplement.length() > 0)
		{
			pMessages.add(0, aComplement);
		}
	}
	/**--------------------------------------------------------------------
	 * @param aComplement
	 */
	public void addComplement(Throwable aComplement)
	{
		if (aComplement != null)
		{
			pMessages.add(0, throwableComplementToString(aComplement));
		}
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @return
	 */
	public StringBuffer addDescrFullInSB(StringBuffer aSB)
	{
		return addEDescrFullInSB(aSB, this);
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.util.IDescriber#addDescrInSB(java.lang.StringBuffer)
	 */
	public StringBuffer addDescrInSB(StringBuffer aSB)
	{
		aSB.append('{');
		aSB.append(getName());
		aSB.append(MESSAGE_SEPARATOR);
		aSB.append(getMessage());
		aSB.append('}');
		/*
		 * liste des messages
		 */
		if (hasComplement())
		{
			aSB.append(MESSAGES_SEPARATOR);
			addMessagesInSB(aSB, MESSAGES_SEPARATOR);
		}
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @return
	 */
	public StringBuffer addDescrMiniInSB(StringBuffer aSB)
	{
		return addEDescrMiniInSB(aSB, this);
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aFormat
	 * @return
	 */
	public StringBuffer addFormatesMessagesInSB(StringBuffer aSB, String aFormat)
	{
		int wI = 0;
		int wMax = pMessages.size();
		while (wI < wMax)
		{
			aSB.append(CStringFacilities.sprintf(aFormat, (String) pMessages.get(wI)));
			wI++;
		}
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @param aSB
	 * @param aSeparator
	 * @return
	 */
	public StringBuffer addMessagesInSB(StringBuffer aSB, char aSeparator)
	{
		String wMess = getMessage();
		boolean wHasMess = (wMess!=null && wMess.length()>0);
		
		if (wHasMess)
		{
			aSB.append(wMess);
		}
		int wI = 0;
		int wMax = pMessages.size();
		while (wI < wMax)
		{
			if (wI > 0 || wHasMess)
			{
				aSB.append(aSeparator);
			}

			aSB.append((String) pMessages.get(wI));
			wI++;
		}
		return aSB;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String eInString()
	{
		return eInString(this);
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String eMiniInString()
	{
		return eMiniInString(this);
	}
  /**
   * 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
   * 
   * @return
   */
  public int getCauseWhy()
  {
    return pCauseWhy;
  }
	/**--------------------------------------------------------------------
	 * @return
	 */
	private String getFirstMessage()
	{

		return (String) pMessages.get(0);
	}
	/**--------------------------------------------------------------------
	 * retourne la longueur de la description
	 * @return
	 */
	public int getLengthOfDescr()
	{
		return 512;
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public List getListOfMessages()
	{
		return pMessages;
	}
	/**--------------------------------------------------------------------
	 * @param aSeparator
	 * @return
	 */
	public String getMessages(char aSeparator)
	{
		return addMessagesInSB(new StringBuffer(32 * pMessages.size()), aSeparator).toString();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	private String getName()
	{
		return CStringFacilities.getClasseName(this.getClass());
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public int getWhy()
	{
		return pWhy;
	}
	/**--------------------------------------------------------------------
	 * Attention: appelée par reflexion dans CTraceBuffer
	 * @return
	 */
	public String getWhyString()
	{
		return whyToString(pWhy);
	}
  /**
   * 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
   * 
   * @return
   */
  public boolean hasCauseWhy()
  {
    return isValidWhy(pCauseWhy);
  }
	/**--------------------------------------------------------------------
	 * @return
	 */
	public boolean hasComplement()
	{
		return (pMessages.size() > 1);
	}

	/**--------------------------------------------------------------------
	 * @param aWhy
	 * @return
	 */
	public boolean isWhy(int aWhy)
	{
		return pWhy == aWhy;
	}
  /**
   * 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
   * 
   * @param aCauseWhy
   */
  private void setCauseWhy(int aCauseWhy)
  {
    pCauseWhy = aCauseWhy;
  }
	/**--------------------------------------------------------------------
	 * @param aWhy
	 */
	private void setWhy(int aWhy)
	{
		pWhy = aWhy;
    // 14w_027 - Fiche 36012 - X3Client: Supervision des sockets
    setCauseWhy(aWhy);
	}
	/**--------------------------------------------------------------------
	 * @param aThrowable
	 * @return
	 */
	private String throwableComplementToString(Throwable aThrowable)
	{
		StringBuffer wSB = new StringBuffer(256);

		wSB.append(CStringFacilities.getClasseName(aThrowable.getClass()));

		if (aThrowable instanceof CException)
		{
			wSB.append(MESSAGE_SEPARATOR).append(((CException) aThrowable).getWhyString());
		}

		String wMess = aThrowable.getMessage();
		if (wMess != null && wMess.length() > 0)
		{
			wSB.append(MESSAGE_SEPARATOR).append(wMess);
		}

		///Java 1.2
		wSB.append(MESSAGE_SEPARATOR);
		wSB.append(firstLineAndFirstAdonixOfStackInString(aThrowable));
		//FJava 1.2*/

		return wSB.toString();
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @deprecated
	 */
	public String toString()
	{
		return toStringDescr();
	}
	/* --------------------------------------------------------------------
	 * 
	 * (non-Javadoc)
	 * @see com.adonix.util.IDescriber#toStringDescr()
	 */
	public String toStringDescr()
	{
		return addDescrFullInSB(new StringBuffer(getLengthOfDescr())).toString();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String toStringDescrFull()
	{
		return addDescrFullInSB(new StringBuffer(512)).toString();
	}
	/**--------------------------------------------------------------------
	 * @return
	 */
	public String toStringDescrMini()
	{
		return addDescrMiniInSB(new StringBuffer(512)).toString();
	}
	/**
   * 14w_008 - Intégration WebServices
	 * @param aWhy
	 * @return
	 */
	public String whyToString(int aWhy)
	{
    if (aWhy == WHY_UNSPECIFIED)
      return EMPTY;
    else
      return CStringFacilities.padNumString(aWhy, 3);
	}
}
