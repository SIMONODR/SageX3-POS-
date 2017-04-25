/**
 * CAdxSubProgramXml.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public interface CAdxSubProgramXml extends java.rmi.Remote {
    public com.adonix.awss.stubs.CAdxResultXml runXml(java.lang.String publicName, java.lang.String inputXml) throws java.rmi.RemoteException;
    public com.adonix.awss.stubs.CAdxResultXml getDescription(java.lang.String publicName) throws java.rmi.RemoteException;
}
