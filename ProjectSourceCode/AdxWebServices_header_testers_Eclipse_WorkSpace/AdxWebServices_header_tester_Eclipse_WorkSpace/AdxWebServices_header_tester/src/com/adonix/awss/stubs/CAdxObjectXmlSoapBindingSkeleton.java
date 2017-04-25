/**
 * CAdxObjectXmlSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public class CAdxObjectXmlSoapBindingSkeleton implements com.adonix.awss.stubs.CAdxObjectXml, org.apache.axis.wsdl.Skeleton {
    private com.adonix.awss.stubs.CAdxObjectXml impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("save", _params, new javax.xml.namespace.QName("", "saveReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "save"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("save") == null) {
            _myOperations.put("save", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("save")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("delete", _params, new javax.xml.namespace.QName("", "deleteReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "delete"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("delete") == null) {
            _myOperations.put("delete", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("delete")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("read", _params, new javax.xml.namespace.QName("", "readReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "read"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("read") == null) {
            _myOperations.put("read", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("read")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modify", _params, new javax.xml.namespace.QName("", "modifyReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "modify"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modify") == null) {
            _myOperations.put("modify", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modify")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "actionCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("actionObject", _params, new javax.xml.namespace.QName("", "actionObjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "actionObject"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("actionObject") == null) {
            _myOperations.put("actionObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("actionObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "actionCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("actionObject", _params, new javax.xml.namespace.QName("", "actionObjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "actionObject"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("actionObject") == null) {
            _myOperations.put("actionObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("actionObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "blocKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "lineKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOf_xsd_string"), java.lang.String[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteLines", _params, new javax.xml.namespace.QName("", "deleteLinesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "deleteLines"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteLines") == null) {
            _myOperations.put("deleteLines", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteLines")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "publicName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectKeys"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.adonix.com/WSS", "ArrayOfCAdxParamKeyValue"), com.adonix.awss.stubs.CAdxParamKeyValue[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "blocKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "lineKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "lineXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("insertLines", _params, new javax.xml.namespace.QName("", "insertLinesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultXml"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "insertLines"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("insertLines") == null) {
            _myOperations.put("insertLines", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("insertLines")).add(_oper);
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

    public CAdxObjectXmlSoapBindingSkeleton() {
        this.impl = new com.adonix.awss.stubs.CAdxObjectXmlSoapBindingImpl();
    }

    public CAdxObjectXmlSoapBindingSkeleton(com.adonix.awss.stubs.CAdxObjectXml impl) {
        this.impl = impl;
    }
    public com.adonix.awss.stubs.CAdxResultXml save(java.lang.String publicName, java.lang.String objectXml) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.save(publicName, objectXml);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml delete(java.lang.String publicName, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.delete(publicName, objectKeys);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml read(java.lang.String publicName, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.read(publicName, objectKeys);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml modify(java.lang.String publicName, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String objectXml) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.modify(publicName, objectKeys, objectXml);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml actionObject(java.lang.String publicName, java.lang.String actionCode, java.lang.String objectXml) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.actionObject(publicName, actionCode, objectXml);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml actionObject(java.lang.String publicName, java.lang.String actionCode, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.actionObject(publicName, actionCode, objectKeys);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml deleteLines(java.lang.String publicName, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String blocKey, java.lang.String[] lineKeys) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.deleteLines(publicName, objectKeys, blocKey, lineKeys);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml insertLines(java.lang.String publicName, com.adonix.awss.stubs.CAdxParamKeyValue[] objectKeys, java.lang.String blocKey, java.lang.String lineKey, java.lang.String lineXml) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.insertLines(publicName, objectKeys, blocKey, lineKey, lineXml);
        return ret;
    }

    public com.adonix.awss.stubs.CAdxResultXml getDescription(java.lang.String publicName) throws java.rmi.RemoteException
    {
        com.adonix.awss.stubs.CAdxResultXml ret = impl.getDescription(publicName);
        return ret;
    }

}
