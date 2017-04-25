
            /**
            * DeleteLinesRequest.java
            *
            * This file was auto-generated from WSDL
            * by the Apache Axis2 version: #axisVersion# #today#
            */

            package com.adonix.www.wss;
            /**
            *  DeleteLinesRequest bean class
            */
        
        public  class DeleteLinesRequest
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = deleteLinesRequest
                Namespace URI = http://www.adonix.com/WSS
                Namespace Prefix = ns1
                */
            


            /**
            * field for LineKeys
            */

            protected org.apache.axiom.om.OMElement localLineKeys ;
           
           

           /**
           * Auto generated getter method
           * @return org.apache.axiom.om.OMElement
           */
           public  org.apache.axiom.om.OMElement getLineKeys(){
               return localLineKeys;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param LineKeys
                   */
                   public void setLineKeys(org.apache.axiom.om.OMElement param){
                    
                   this.localLineKeys=param;
                   }
                


            /**
            * field for CallContext
            */

            protected org.apache.axiom.om.OMElement localCallContext ;
           
           

           /**
           * Auto generated getter method
           * @return org.apache.axiom.om.OMElement
           */
           public  org.apache.axiom.om.OMElement getCallContext(){
               return localCallContext;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param CallContext
                   */
                   public void setCallContext(org.apache.axiom.om.OMElement param){
                    
                   this.localCallContext=param;
                   }
                


            /**
            * field for BlocKey
            */

            protected java.lang.String localBlocKey ;
           
           

           /**
           * Auto generated getter method
           * @return java.lang.String
           */
           public  java.lang.String getBlocKey(){
               return localBlocKey;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param BlocKey
                   */
                   public void setBlocKey(java.lang.String param){
                    
                   this.localBlocKey=param;
                   }
                


            /**
            * field for PublicName
            */

            protected java.lang.String localPublicName ;
           
           

           /**
           * Auto generated getter method
           * @return java.lang.String
           */
           public  java.lang.String getPublicName(){
               return localPublicName;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param PublicName
                   */
                   public void setPublicName(java.lang.String param){
                    
                   this.localPublicName=param;
                   }
                


            /**
            * field for ObjectKeys
            */

            protected org.apache.axiom.om.OMElement localObjectKeys ;
           
           

           /**
           * Auto generated getter method
           * @return org.apache.axiom.om.OMElement
           */
           public  org.apache.axiom.om.OMElement getObjectKeys(){
               return localObjectKeys;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param ObjectKeys
                   */
                   public void setObjectKeys(org.apache.axiom.om.OMElement param){
                    
                   this.localObjectKeys=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "lineKeys"));
                            
                            
                                    if (localLineKeys==null){
                                         throw new RuntimeException("lineKeys cannot be null!!");
                                    }
                                    elementList.add(localLineKeys);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "callContext"));
                            
                            
                                    if (localCallContext==null){
                                         throw new RuntimeException("callContext cannot be null!!");
                                    }
                                    elementList.add(localCallContext);
                                
                             elementList.add(new javax.xml.namespace.QName("",
                                                                      "blocKey"));
                            
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBlocKey));
                                
                             elementList.add(new javax.xml.namespace.QName("",
                                                                      "publicName"));
                            
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPublicName));
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "objectKeys"));
                            
                            
                                    if (localObjectKeys==null){
                                         throw new RuntimeException("objectKeys cannot be null!!");
                                    }
                                    elementList.add(localObjectKeys);
                                

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
        public static DeleteLinesRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            DeleteLinesRequest object = new DeleteLinesRequest();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                    
                    reader.next();
                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","lineKeys").equals(reader.getName())){
                            
                                     boolean loopDone1 = false;
                                     javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName(
                                                                         "",
                                                                         "lineKeys");

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
                                     object.setLineKeys(builder1.getOMElement());
                                       
                                         reader.next();
                                     

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                                        
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","callContext").equals(reader.getName())){
                            
                                     boolean loopDone2 = false;
                                     javax.xml.namespace.QName startQname2 = new javax.xml.namespace.QName(
                                                                         "",
                                                                         "callContext");

                                     while(!loopDone2){
                                         if (reader.isStartElement() && startQname2.equals(reader.getName())){
                                             loopDone2 = true;
                                         }else{
                                             reader.next();
                                         }
                                     }

                                     
                                     // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                                     // this is needed by the builder classes
                                     org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder2 =
                                         new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(
                                             new org.apache.axis2.util.StreamWrapper(reader),startQname2);
                                     object.setCallContext(builder2.getOMElement());
                                       
                                         reader.next();
                                     

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                                        
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","blocKey").equals(reader.getName())){
                            
                                    java.lang.String content = getElementTextProperly(reader);
                                    object.setBlocKey(
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertTostring(content));
                                      
                                        reader.next();
                                    

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                                        
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","publicName").equals(reader.getName())){
                            
                                    java.lang.String content = getElementTextProperly(reader);
                                    object.setPublicName(
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertTostring(content));
                                      
                                        reader.next();
                                    

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                                        
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","objectKeys").equals(reader.getName())){
                            
                                     boolean loopDone5 = false;
                                     javax.xml.namespace.QName startQname5 = new javax.xml.namespace.QName(
                                                                         "",
                                                                         "objectKeys");

                                     while(!loopDone5){
                                         if (reader.isStartElement() && startQname5.equals(reader.getName())){
                                             loopDone5 = true;
                                         }else{
                                             reader.next();
                                         }
                                     }

                                     
                                     // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                                     // this is needed by the builder classes
                                     org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder5 =
                                         new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(
                                             new org.apache.axis2.util.StreamWrapper(reader),startQname5);
                                     object.setObjectKeys(builder5.getOMElement());
                                       
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
           
          