/**
 * CAdxSubProgramXmlServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public class CAdxSubProgramXmlServiceLocator extends org.apache.axis.client.Service implements com.adonix.awss.stubs.CAdxSubProgramXmlService {

    public CAdxSubProgramXmlServiceLocator() {
    }


    public CAdxSubProgramXmlServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CAdxSubProgramXmlServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CAdxSubProgramXml
    private java.lang.String CAdxSubProgramXml_address = "http://10.8.5.137:28880/adxwsvc/services/CAdxObjectXml";

    public java.lang.String getCAdxSubProgramXmlAddress() {
        return CAdxSubProgramXml_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CAdxSubProgramXmlWSDDServiceName = "CAdxSubProgramXml";

    public java.lang.String getCAdxSubProgramXmlWSDDServiceName() {
        return CAdxSubProgramXmlWSDDServiceName;
    }

    public void setCAdxSubProgramXmlWSDDServiceName(java.lang.String name) {
        CAdxSubProgramXmlWSDDServiceName = name;
    }

    public com.adonix.awss.stubs.CAdxSubProgramXml getCAdxSubProgramXml() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CAdxSubProgramXml_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCAdxSubProgramXml(endpoint);
    }

    public com.adonix.awss.stubs.CAdxSubProgramXml getCAdxSubProgramXml(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adonix.awss.stubs.CAdxSubProgramXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxSubProgramXmlSoapBindingStub(portAddress, this);
            _stub.setPortName(getCAdxSubProgramXmlWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCAdxSubProgramXmlEndpointAddress(java.lang.String address) {
        CAdxSubProgramXml_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adonix.awss.stubs.CAdxSubProgramXml.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adonix.awss.stubs.CAdxSubProgramXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxSubProgramXmlSoapBindingStub(new java.net.URL(CAdxSubProgramXml_address), this);
                _stub.setPortName(getCAdxSubProgramXmlWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CAdxSubProgramXml".equals(inputPortName)) {
            return getCAdxSubProgramXml();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxSubProgramXmlService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxSubProgramXml"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CAdxSubProgramXml".equals(portName)) {
            setCAdxSubProgramXmlEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
