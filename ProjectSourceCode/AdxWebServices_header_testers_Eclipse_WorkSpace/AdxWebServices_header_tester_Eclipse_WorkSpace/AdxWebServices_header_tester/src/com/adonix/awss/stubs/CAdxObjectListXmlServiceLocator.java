/**
 * CAdxObjectListXmlServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.awss.stubs;

public class CAdxObjectListXmlServiceLocator extends org.apache.axis.client.Service implements com.adonix.awss.stubs.CAdxObjectListXmlService {

    public CAdxObjectListXmlServiceLocator() {
    }


    public CAdxObjectListXmlServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CAdxObjectListXmlServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CAdxObjectListXml
    private java.lang.String CAdxObjectListXml_address = "http://gattazmpro.ech.adx:1898/adxwsvc/services/CAdxObjectListXml";

    public java.lang.String getCAdxObjectListXmlAddress() {
        return CAdxObjectListXml_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CAdxObjectListXmlWSDDServiceName = "CAdxObjectListXml";

    public java.lang.String getCAdxObjectListXmlWSDDServiceName() {
        return CAdxObjectListXmlWSDDServiceName;
    }

    public void setCAdxObjectListXmlWSDDServiceName(java.lang.String name) {
        CAdxObjectListXmlWSDDServiceName = name;
    }

    public com.adonix.awss.stubs.CAdxObjectListXml getCAdxObjectListXml() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CAdxObjectListXml_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCAdxObjectListXml(endpoint);
    }

    public com.adonix.awss.stubs.CAdxObjectListXml getCAdxObjectListXml(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adonix.awss.stubs.CAdxObjectListXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxObjectListXmlSoapBindingStub(portAddress, this);
            _stub.setPortName(getCAdxObjectListXmlWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCAdxObjectListXmlEndpointAddress(java.lang.String address) {
        CAdxObjectListXml_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adonix.awss.stubs.CAdxObjectListXml.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adonix.awss.stubs.CAdxObjectListXmlSoapBindingStub _stub = new com.adonix.awss.stubs.CAdxObjectListXmlSoapBindingStub(new java.net.URL(CAdxObjectListXml_address), this);
                _stub.setPortName(getCAdxObjectListXmlWSDDServiceName());
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
        if ("CAdxObjectListXml".equals(inputPortName)) {
            return getCAdxObjectListXml();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxObjectListXmlService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxObjectListXml"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CAdxObjectListXml".equals(portName)) {
            setCAdxObjectListXmlEndpointAddress(address);
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
