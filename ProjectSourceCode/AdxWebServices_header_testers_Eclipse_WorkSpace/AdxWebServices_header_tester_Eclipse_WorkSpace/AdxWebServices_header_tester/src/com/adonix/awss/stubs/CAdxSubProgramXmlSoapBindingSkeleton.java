/**
 * CAdxSubProgramXmlSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public class CAdxSubProgramXmlSoapBindingSkeleton implements com.adonix.awss.stubs.CAdxSubProgramXml, org.apache.axis.wsdl.Skeleton {
    private com.adonix.awss.stubs.CAdxSubProgramXml impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inputXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("runXml", _params, new javax.xml.namespace.QName("", "runXmlReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "runXml"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("runXml") == null) {
            _myOperations.put("runXml", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("runXml")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDescription", _params, new javax.xml.namespace.QName("", "getDescriptionReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "getDescription"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDescription") == null) {
            _myOperations.put("getDescription", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDescription")).add(_oper);
    }

    public CAdxSubProgramXmlSoapBindingSkeleton() {
        this.impl = new com.adonix.awss.stubs.CAdxSubProgramXmlSoapBindingImpl();
    }

    public CAdxSubProgramXmlSoapBindingSkeleton(com.adonix.awss.stubs.CAdxSubProgramXml impl) {
        this.impl = impl;
    }
    public com.adonix.awss.stubs.CAdxResultXml runXml(java.lang.String publicName, java.lang.String inputXml) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.runXml(publicName, inputXml);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml getDescription(java.lang.String publicName) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.getDescription(publicName);
        return ret;
    }

}
