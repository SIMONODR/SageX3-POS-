
            /**
            * Modify.java
            *
            * This file was auto-generated from WSDL
            * by the Apache Axis2 version: #axisVersion# #today#
            */

            package com.adonix.www.wss;
            /**
            *  Modify bean class
            */
        
        public  class Modify
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adonix.com/WSS",
                "modify",
                "ns1");

            


            /**
            * field for Modify
            */

            protected com.adonix.www.wss.ModifyRequest localModify ;
           
           

           /**
           * Auto generated getter method
           * @return com.adonix.www.wss.ModifyRequest
           */
           public  com.adonix.www.wss.ModifyRequest getModify(){
               return localModify;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param Modify
                   */
                   public void setModify(com.adonix.www.wss.ModifyRequest param){
                    
                   this.localModify=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                
                //We can safely assume an element has only one type associated with it
                
                                if (localModify==null){
                                   return new org.apache.axis2.databinding.utils.reader.NullXMLStreamReader(MY_QNAME);
                                }else{
                                   return localModify.getPullParser(MY_QNAME);
                                }
                            

        }



     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{


        // This is horrible, but the OM implementation of getElementText() does not obey the proper contract.  Specifically, it does
        // does not advance the reader to the END_ELEMENT.  This bug is triggered by calls to getElementText() unpredictably, e.g. it
        // happens with outer (document) elements, but not with inner elements.  The root bug is in OMStAXWrapper.java, which is now part
        // of commons and so cannot just be fixed in axis2.  This method should be removed and the calls to it below replaced with
        // simple calls to getElementText() as soon as this serious bug can be fixed.

        private static java.lang.String getElementTextProperly(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            java.lang.String value = reader.getElementText();
            while (!reader.isEndElement())
                reader.next();
            return value;
        }

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Modify parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Modify object = new Modify();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.adonix.com/WSS","modify").equals(reader.getName())){
                            
                                    object.setModify(com.adonix.www.wss.ModifyRequest.Factory.parse(reader));
                                    

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                            


            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          