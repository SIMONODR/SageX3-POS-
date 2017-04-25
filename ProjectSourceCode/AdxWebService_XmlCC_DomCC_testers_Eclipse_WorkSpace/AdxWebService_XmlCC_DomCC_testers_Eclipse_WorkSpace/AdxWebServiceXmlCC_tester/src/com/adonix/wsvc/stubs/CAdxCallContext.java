/**
 * CAdxCallContext.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adonix.wsvc.stubs;

public class CAdxCallContext  implements java.io.Serializable {
    public java.lang.String codeLang;

    public java.lang.String codeUser;

    public java.lang.String password;

    public java.lang.String poolAlias;

    public java.lang.String requestConfig;

    public CAdxCallContext() {
    }

    public CAdxCallContext(
           java.lang.String codeLang,
           java.lang.String codeUser,
           java.lang.String password,
           java.lang.String poolAlias,
           java.lang.String requestConfig) {
           this.codeLang = codeLang;
           this.codeUser = codeUser;
           this.password = password;
           this.poolAlias = poolAlias;
           this.requestConfig = requestConfig;
    }


    /**
     * Gets the codeLang value for this CAdxCallContext.
     * 
     * @return codeLang
     */
    public java.lang.String getCodeLang() {
        return codeLang;
    }


    /**
     * Sets the codeLang value for this CAdxCallContext.
     * 
     * @param codeLang
     */
    public void setCodeLang(java.lang.String codeLang) {
        this.codeLang = codeLang;
    }


    /**
     * Gets the codeUser value for this CAdxCallContext.
     * 
     * @return codeUser
     */
    public java.lang.String getCodeUser() {
        return codeUser;
    }


    /**
     * Sets the codeUser value for this CAdxCallContext.
     * 
     * @param codeUser
     */
    public void setCodeUser(java.lang.String codeUser) {
        this.codeUser = codeUser;
    }


    /**
     * Gets the password value for this CAdxCallContext.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this CAdxCallContext.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the poolAlias value for this CAdxCallContext.
     * 
     * @return poolAlias
     */
    public java.lang.String getPoolAlias() {
        return poolAlias;
    }


    /**
     * Sets the poolAlias value for this CAdxCallContext.
     * 
     * @param poolAlias
     */
    public void setPoolAlias(java.lang.String poolAlias) {
        this.poolAlias = poolAlias;
    }


    /**
     * Gets the requestConfig value for this CAdxCallContext.
     * 
     * @return requestConfig
     */
    public java.lang.String getRequestConfig() {
        return requestConfig;
    }


    /**
     * Sets the requestConfig value for this CAdxCallContext.
     * 
     * @param requestConfig
     */
    public void setRequestConfig(java.lang.String requestConfig) {
        this.requestConfig = requestConfig;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CAdxCallContext)) return false;
        CAdxCallContext other = (CAdxCallContext) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codeLang==null && other.getCodeLang()==null) || 
             (this.codeLang!=null &&
              this.codeLang.equals(other.getCodeLang()))) &&
            ((this.codeUser==null && other.getCodeUser()==null) || 
             (this.codeUser!=null &&
              this.codeUser.equals(other.getCodeUser()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.poolAlias==null && other.getPoolAlias()==null) || 
             (this.poolAlias!=null &&
              this.poolAlias.equals(other.getPoolAlias()))) &&
            ((this.requestConfig==null && other.getRequestConfig()==null) || 
             (this.requestConfig!=null &&
              this.requestConfig.equals(other.getRequestConfig())));
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
        if (getCodeLang() != null) {
            _hashCode += getCodeLang().hashCode();
        }
        if (getCodeUser() != null) {
            _hashCode += getCodeUser().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getPoolAlias() != null) {
            _hashCode += getPoolAlias().hashCode();
        }
        if (getRequestConfig() != null) {
            _hashCode += getRequestConfig().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CAdxCallContext.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adonix.com/WSS", "CAdxCallContext"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeLang"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADMIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCONN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "poolAlias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestConfig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
