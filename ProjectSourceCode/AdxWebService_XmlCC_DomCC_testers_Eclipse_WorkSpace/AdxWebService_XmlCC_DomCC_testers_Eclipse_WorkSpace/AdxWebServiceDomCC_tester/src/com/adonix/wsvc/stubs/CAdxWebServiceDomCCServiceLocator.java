/**
 * CAdxWebServiceDomCCServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.wsvc.stubs;

public class CAdxWebServiceDomCCServiceLocator extends org.apache.axis.client.Service implements com.adonix.wsvc.stubs.CAdxWebServiceDomCCService {

    public CAdxWebServiceDomCCServiceLocator() {
    }


    public CAdxWebServiceDomCCServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CAdxWebServiceDomCCServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CAdxWebServiceDomCC
    private java.lang.String CAdxWebServiceDomCC_address = "http://gattazmpro.ech.adx:1898/adxwsvc/services/CAdxWebServiceDomCC";

    public java.lang.String getCAdxWebServiceDomCCAddress() {
        return CAdxWebServiceDomCC_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CAdxWebServiceDomCCWSDDServiceName = "CAdxWebServiceDomCC";

    public java.lang.String getCAdxWebServiceDomCCWSDDServiceName() {
        return CAdxWebServiceDomCCWSDDServiceName;
    }

    public void setCAdxWebServiceDomCCWSDDServiceName(java.lang.String name) {
        CAdxWebServiceDomCCWSDDServiceName = name;
    }

    public com.adonix.wsvc.stubs.CAdxWebServiceDomCC getCAdxWebServiceDomCC() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CAdxWebServiceDomCC_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCAdxWebServiceDomCC(endpoint);
    }

    public com.adonix.wsvc.stubs.CAdxWebServiceDomCC getCAdxWebServiceDomCC(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adonix.wsvc.stubs.CAdxWebServiceDomCCSoapBindingStub _stub = new com.adonix.wsvc.stubs.CAdxWebServiceDomCCSoapBindingStub(portAddress, this);
            _stub.setPortName(getCAdxWebServiceDomCCWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCAdxWebServiceDomCCEndpointAddress(java.lang.String address) {
        CAdxWebServiceDomCC_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adonix.wsvc.stubs.CAdxWebServiceDomCC.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adonix.wsvc.stubs.CAdxWebServiceDomCCSoapBindingStub _stub = new com.adonix.wsvc.stubs.CAdxWebServiceDomCCSoapBindingStub(new java.net.URL(CAdxWebServiceDomCC_address), this);
                _stub.setPortName(getCAdxWebServiceDomCCWSDDServiceName());
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
        if ("CAdxWebServiceDomCC".equals(inputPortName)) {
            return getCAdxWebServiceDomCC();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxWebServiceDomCCService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxWebServiceDomCC"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CAdxWebServiceDomCC".equals(portName)) {
            setCAdxWebServiceDomCCEndpointAddress(address);
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
