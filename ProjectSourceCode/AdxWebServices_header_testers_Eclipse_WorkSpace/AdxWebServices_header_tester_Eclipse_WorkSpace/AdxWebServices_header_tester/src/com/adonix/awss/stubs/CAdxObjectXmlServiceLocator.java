/**
 * CAdxObjectXmlServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public class CAdxObjectXmlServiceLocator extends org.apache.axis.client.Service implements com.adonix.awss.stubs.CAdxObjectXmlService {

    public CAdxObjectXmlServiceLocator() {
    }


    public CAdxObjectXmlServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CAdxObjectXmlServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CAdxObjectXml
    private java.lang.String CAdxObjectXml_address = "http://gattazmpro.ech.adx:1898/adxwsvc/services/CAdxObjectXml";

    public java.lang.String getCAdxObjectXmlAddress() {
        return CAdxObjectXml_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CAdxObjectXmlWSDDServiceName = "CAdxObjectXml";

    public java.lang.String getCAdxObjectXmlWSDDServiceName() {
        return CAdxObjectXmlWSDDServiceName;
    }

    public void setCAdxObjectXmlWSDDServiceName(java.lang.String name) {
        CAdxObjectXmlWSDDServiceName = name;
    }

    public com.adonix.awss.stubs.CAdxObjectXml getCAdxObjectXml() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CAdxObjectXml_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCAdxObjectXml(endpoint);
    }

    public com.adonix.awss.stubs.CAdxObjectXml getCAdxObjectXml(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adonix.awss.stubs.CAdxObjectXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxObjectXmlSoapBindingStub(portAddress, this);
            _stub.setPortName(getCAdxObjectXmlWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCAdxObjectXmlEndpointAddress(java.lang.String address) {
        CAdxObjectXml_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adonix.awss.stubs.CAdxObjectXml.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adonix.awss.stubs.CAdxObjectXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxObjectXmlSoapBindingStub(new java.net.URL(CAdxObjectXml_address), this);
                _stub.setPortName(getCAdxObjectXmlWSDDServiceName());
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
        if ("CAdxObjectXml".equals(inputPortName)) {
            return getCAdxObjectXml();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxObjectXmlService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxObjectXml"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CAdxObjectXml".equals(portName)) {
            setCAdxObjectXmlEndpointAddress(address);
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
