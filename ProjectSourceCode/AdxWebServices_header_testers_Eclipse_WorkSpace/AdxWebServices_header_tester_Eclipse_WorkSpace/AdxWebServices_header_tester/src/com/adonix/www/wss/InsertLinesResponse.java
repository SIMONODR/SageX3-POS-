
            /**
            * InsertLinesResponse.java
            *
            * This file was auto-generated from WSDL
            * by the Apache Axis2 version: #axisVersion# #today#
            */

            package com.adonix.www.wss;
            /**
            *  InsertLinesResponse bean class
            */
        
        public  class InsertLinesResponse
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = insertLinesResponse
                Namespace URI = http://www.adonix.com/WSS
                Namespace Prefix = ns1
                */
            


            /**
            * field for InsertLinesReturn
            */

            protected org.apache.axiom.om.OMElement localInsertLinesReturn ;
           
           

           /**
           * Auto generated getter method
           * @return org.apache.axiom.om.OMElement
           */
           public  org.apache.axiom.om.OMElement getInsertLinesReturn(){
               return localInsertLinesReturn;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param InsertLinesReturn
                   */
                   public void setInsertLinesReturn(org.apache.axiom.om.OMElement param){
                    
                   this.localInsertLinesReturn=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "insertLinesReturn"));
                            
                            
                                    if (localInsertLinesReturn==null){
                                         throw new RuntimeException("insertLinesReturn cannot be null!!");
                                    }
                                    elementList.add(localInsertLinesReturn);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

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
        public static InsertLinesResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertLinesResponse object = new InsertLinesResponse();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                    
                    reader.next();
                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","insertLinesReturn").equals(reader.getName())){
                            
                                     boolean loopDone1 = false;
                                     javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName(
                                                                         "",
                                                                         "insertLinesReturn");

                                     while(!loopDone1){
                                         if (reader.isStartElement() && startQname1.equals(reader.getName())){
                                             loopDone1 = true;
                                         }else{
                                             reader.next();
                                         }
                                     }

                                     
                                     // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                                     // this is needed by the builder classes
                                     org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder1 =
                                         new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(
                                             new org.apache.axis2.util.StreamWrapper(reader),startQname1);
                                     object.setInsertLinesReturn(builder1.getOMElement());
                                       
                                         reader.next();
                                     

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
           
          