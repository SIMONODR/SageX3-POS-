/**
 * CAdxResultDom.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.wsvc.stubs;

public class CAdxResultDom  implements java.io.Serializable {
    private com.adonix.wsvc.stubs.CAdxMessage[] messages;

    private java.lang.Object resultElmt;

    private int status;

    private com.adonix.wsvc.stubs.CAdxTechnicalInfos technicalInfos;

    public CAdxResultDom() {
    }

    public CAdxResultDom(
           com.adonix.wsvc.stubs.CAdxMessage[] messages,
           java.lang.Object resultElmt,
           int status,
           com.adonix.wsvc.stubs.CAdxTechnicalInfos technicalInfos) {
           this.messages = messages;
           this.resultElmt = resultElmt;
           this.status = status;
           this.technicalInfos = technicalInfos;
    }


    /**
     * Gets the messages value for this CAdxResultDom.
     * 
     * @return messages
     */
    public com.adonix.wsvc.stubs.CAdxMessage[] getMessages() {
        return messages;
    }


    /**
     * Sets the messages value for this CAdxResultDom.
     * 
     * @param messages
     */
    public void setMessages(com.adonix.wsvc.stubs.CAdxMessage[] messages) {
        this.messages = messages;
    }


    /**
     * Gets the resultElmt value for this CAdxResultDom.
     * 
     * @return resultElmt
     */
    public java.lang.Object getResultElmt() {
        return resultElmt;
    }


    /**
     * Sets the resultElmt value for this CAdxResultDom.
     * 
     * @param resultElmt
     */
    public void setResultElmt(java.lang.Object resultElmt) {
        this.resultElmt = resultElmt;
    }


    /**
     * Gets the status value for this CAdxResultDom.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CAdxResultDom.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the technicalInfos value for this CAdxResultDom.
     * 
     * @return technicalInfos
     */
    public com.adonix.wsvc.stubs.CAdxTechnicalInfos getTechnicalInfos() {
        return technicalInfos;
    }


    /**
     * Sets the technicalInfos value for this CAdxResultDom.
     * 
     * @param technicalInfos
     */
    public void setTechnicalInfos(com.adonix.wsvc.stubs.CAdxTechnicalInfos technicalInfos) {
        this.technicalInfos = technicalInfos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CAdxResultDom)) return false;
        CAdxResultDom other = (CAdxResultDom) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messages==null && other.getMessages()==null) || 
             (this.messages!=null &&
              java.util.Arrays.equals(this.messages, other.getMessages()))) &&
            ((this.resultElmt==null && other.getResultElmt()==null) || 
             (this.resultElmt!=null &&
              this.resultElmt.equals(other.getResultElmt()))) &&
            this.status == other.getStatus() &&
            ((this.technicalInfos==null && other.getTechnicalInfos()==null) || 
             (this.technicalInfos!=null &&
              this.technicalInfos.equals(other.getTechnicalInfos())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMessages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResultElmt() != null) {
            _hashCode += getResultElmt().hashCode();
        }
        _hashCode += getStatus();
        if (getTechnicalInfos() != null) {
            _hashCode += getTechnicalInfos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CAdxResultDom.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxResultDom"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messages");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxMessage"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultElmt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultElmt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technicalInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "technicalInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxTechnicalInfos"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
