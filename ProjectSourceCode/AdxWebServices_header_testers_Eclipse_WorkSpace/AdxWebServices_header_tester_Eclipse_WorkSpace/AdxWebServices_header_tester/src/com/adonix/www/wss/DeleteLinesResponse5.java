
            /**
            * DeleteLinesResponse5.java
            *
            * This file was auto-generated from WSDL
            * by the Apache Axis2 version: #axisVersion# #today#
            */

            package com.adonix.www.wss;
            /**
            *  DeleteLinesResponse5 bean class
            */
        
        public  class DeleteLinesResponse5
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adonix.com/WSS",
                "deleteLinesResponse",
                "ns1");

            


            /**
            * field for DeleteLinesResponse
            */

            protected com.adonix.www.wss.DeleteLinesResponse localDeleteLinesResponse ;
           
           

           /**
           * Auto generated getter method
           * @return com.adonix.www.wss.DeleteLinesResponse
           */
           public  com.adonix.www.wss.DeleteLinesResponse getDeleteLinesResponse(){
               return localDeleteLinesResponse;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param DeleteLinesResponse
                   */
                   public void setDeleteLinesResponse(com.adonix.www.wss.DeleteLinesResponse param){
                    
                   this.localDeleteLinesResponse=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                
                //We can safely assume an element has only one type associated with it
                
                                if (localDeleteLinesResponse==null){
                                   return new org.apache.axis2.databinding.utils.reader.NullXMLStreamReader(MY_QNAME);
                                }else{
                                   return localDeleteLinesResponse.getPullParser(MY_QNAME);
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
        public static DeleteLinesResponse5 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            DeleteLinesResponse5 object = new DeleteLinesResponse5();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.adonix.com/WSS","deleteLinesResponse").equals(reader.getName())){
                            
                                    object.setDeleteLinesResponse(com.adonix.www.wss.DeleteLinesResponse.Factory.parse(reader));
                                    

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
           
          