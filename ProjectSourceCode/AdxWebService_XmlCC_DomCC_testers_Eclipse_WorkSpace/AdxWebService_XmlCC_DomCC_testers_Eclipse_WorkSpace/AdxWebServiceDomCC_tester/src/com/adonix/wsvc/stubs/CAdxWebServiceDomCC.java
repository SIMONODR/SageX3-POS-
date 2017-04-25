/**
 * CAdxWebServiceDomCC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.wsvc.stubs;

public interface CAdxWebServiceDomCC extends java.rmi.Remote {
    public com.adonix.wsvc.stubs.CAdxResultDom run(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, java.lang.String inputXml) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom save(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, java.lang.String objectXml) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom delete(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom read(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom query(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys, int listSize) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom getDescription(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom modify(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String objectXml) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom actionObject(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, java.lang.String actionCode, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom actionObject(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, java.lang.String actionCode, java.lang.String objectXml) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom deleteLines(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String blocKey, java.lang.String[] lineKeys) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom insertLines(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName, com.adonix.wsvc.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String blocKey, java.lang.String lineKey, java.lang.String lineXml) throws java.rmi.RemoteException;
    public com.adonix.wsvc.stubs.CAdxResultDom getDataXmlSchema(com.adonix.wsvc.stubs.CAdxCallContext callContext, java.lang.String publicName) throws java.rmi.RemoteException;
}
