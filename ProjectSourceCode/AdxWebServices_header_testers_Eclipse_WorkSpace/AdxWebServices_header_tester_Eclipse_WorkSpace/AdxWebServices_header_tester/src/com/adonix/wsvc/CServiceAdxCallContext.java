package com.adonix.wsvc;

/**
 * @author Adonix Grenoble
 * @version 14w
 * 
 */
public class CServiceAdxCallContext
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4726294204693622749L;

	/**
	 * @param aActivityViewer
	 * @param aCodeLang
	 * @param aCodeUser
	 * @param aPassword
	 * @param aPoolAlias
	 * @param aRequestConfig
	 * @return
	 */
	public static CServiceAdxCallContext buildAdxCallContext(CActivityViewer aActivityViewer, String aCodeLang,
			String aCodeUser, String aPassword, String aPoolAlias, String aRequestConfig)
	{
		boolean wActivityViewerOn = (aActivityViewer != null && aActivityViewer.isTraceOn());
		if (wActivityViewerOn)
			aActivityViewer.traceBeginStep("buildCallingContext");

		CServiceAdxCallContext wServiceAdxCallContext = new CServiceAdxCallContext(aCodeLang, aCodeUser, aPassword,
				aPoolAlias, aRequestConfig);

		if (wActivityViewerOn)
		{
			aActivityViewer.traceWrite(wServiceAdxCallContext.dump());
			aActivityViewer.traceEndStep();
		}
		return wServiceAdxCallContext;
	}

	String pCodeLang;

	String pCodeUser;

	String pPassword;

	String pPoolAlias;

	String pRequestConfig;

	/**
	 * @param aAdxCallContext
	 */
	CServiceAdxCallContext(String aCodeLang, String aCodeUser, String aPassword, String aPoolAlias, String aRequestConfig)
	{
		setCodeLang(aCodeLang);
		setCodeUser(aCodeUser);
		setPassword(aPassword);
		setPoolAlias(aPoolAlias);
		setRequestConfig(aRequestConfig);
	}

	/**
	 * @return
	 */
	public String dump()
	{
		StringBuffer wSB = new StringBuffer();
		wSB.append("codeLang=[").append(this.getCodeLang()).append(']');
		wSB.append('\n');
		wSB.append("codeUser=[").append(this.getCodeUser()).append(']');
		wSB.append('\n');
		wSB.append("password=[").append(this.getPassword()).append(']');
		wSB.append('\n');
		wSB.append("poolAlias=[").append(this.getPoolAlias()).append(']');
		wSB.append('\n');
		wSB.append("requestConfig=[").append(this.getRequestConfig()).append(']');
		return wSB.toString();
	}

	/**
	 * @return
	 */
	String getCodeLang()
	{
		return pCodeLang;
	}

	/**
	 * @return
	 */
	String getCodeUser()
	{
		return pCodeUser;
	}

	/**
	 * @return
	 */
	String getPassword()
	{
		return pPassword;
	}

	/**
	 * @return
	 */
	String getPoolAlias()
	{
		return pPoolAlias;
	}

	/**
	 * @return
	 */
	String getRequestConfig()
	{
		return pRequestConfig;
	}

	/**
	 * @param aCodeLang
	 */
	void setCodeLang(String aCodeLang)
	{
		pCodeLang = aCodeLang;
	}

	/**
	 * @param aCodeUser
	 */
	void setCodeUser(String aCodeUser)
	{
		pCodeUser = aCodeUser;
	}

	/**
	 * @param aPassword
	 */
	void setPassword(String aPassword)
	{
		pPassword = aPassword;
	}

	/**
	 * @param aPoolAlias
	 */
	void setPoolAlias(String aPoolAlias)
	{
		pPoolAlias = aPoolAlias;
	}

	/**
	 * @param aRequestConfig
	 */
	void setRequestConfig(String aRequestConfig)
	{
		pRequestConfig = aRequestConfig;
	}
}
