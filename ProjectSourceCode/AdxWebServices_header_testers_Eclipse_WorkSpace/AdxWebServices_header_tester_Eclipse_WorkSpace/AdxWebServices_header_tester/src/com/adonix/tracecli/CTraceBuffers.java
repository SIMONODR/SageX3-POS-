package com.adonix.tracecli;

import java.util.Stack;

/**
 * @author Adonix Grenoble
 * @version 1.0
 */

public class CTraceBuffers extends Stack
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257009851862561593L;
	//--------------------------------------------------------------------
	private ITracer pTracer;
	//--------------------------------------------------------------------
	public CTraceBuffers(ITracer aTracer)
	{	
		super();
		pTracer = aTracer;
		this.push(new CTraceBuffer(1024));
		this.push(new CTraceBuffer(1024));
		this.push(new CTraceBuffer(1024));
		this.push(new CTraceBuffer(1024));
		
		///Trace
		if (pTracer.traceLevelFilter(ITracer.LEVEL_DEBUG)){
			CTraceBuffer wTB = new CTraceBuffer(128).appendMethodName(ITracer.CONSTRUCTOR);
			wTB.appendDescrRightAligned("size ",this.size(),5);
			pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
		}
		//FTrace*/
	}
	//--------------------------------------------------------------------
	public CTraceBuffer popTraceBuffer()
	{
		if (this.size()==0){
			///Trace
			if (pTracer.traceLevelFilter(ITracer.LEVEL_DEBUG)){
				CTraceBuffer wTB = new CTraceBuffer(128).appendMethodName("popTraceBuffer");
				wTB.appendDescrRightAligned("size before pop",this.size(),5);
				wTB.appendDescrRightAligned("instanciate a new buffer ! BufferLength",1024,5);
				pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
			}
			//FTrace*/
			return new CTraceBuffer(1024);
		}
		else{
			///Trace
			if (pTracer.traceLevelFilter(ITracer.LEVEL_DEBUG)){
				CTraceBuffer wTB = new CTraceBuffer(128).appendMethodName("popTraceBuffer");
				wTB.appendDescrRightAligned("size before pop",this.size(),5);
				wTB.append(" get an existing TraceBuffer");
				pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
			}
			//FTrace*/
			return (CTraceBuffer)super.pop();
		}
	}
	//--------------------------------------------------------------------
	public void pushTraceBuffer(CTraceBuffer aTraceBuffer)
	{
		int wOldLength = aTraceBuffer.length();
		aTraceBuffer.delete();
		super.push(aTraceBuffer);
		
		///Trace
		if (pTracer.traceLevelFilter(ITracer.LEVEL_DEBUG)){
			CTraceBuffer wTB = new CTraceBuffer(128).appendMethodName("pushTraceBuffer");
			wTB.appendDescrRightAligned("wOldLength",wOldLength,5);
			wTB.appendDescrRightAligned("Length after delete",aTraceBuffer.length(),5);
			wTB.appendDescrRightAligned("size after push",this.size(),5);
			pTracer.trace(this,ITracer.LEVEL_DEBUG,wTB);
		}
		//FTrace*/
		
	}
}
