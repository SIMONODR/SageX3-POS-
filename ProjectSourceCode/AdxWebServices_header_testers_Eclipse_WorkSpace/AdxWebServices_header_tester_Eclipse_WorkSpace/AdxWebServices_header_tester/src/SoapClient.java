import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCC;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCService;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator; // Note this new one
import com.adonix.awss.stubs.CAdxParamKeyValue;
import com.adonix.awss.stubs.CAdxResultXml;
import com.adonix.awss.stubs.CAdxMessage;
import java.io.StringReader; // String reading/building classes

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder; // DOM builder
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.TypeMappingRegistry;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.ObjID;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.w3c.dom.Document; // May be useful for Xml document parsing
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SoapClient implements CAdxWebServiceXmlCCService {
	public static void main(String args[]) {
		CAdxWebServiceXmlCCServiceLocator serviceLocator = new CAdxWebServiceXmlCCServiceLocator();
		CAdxWebServiceXmlCCService x3Service;
		serviceLocator
				.setCAdxWebServiceXmlCCEndpointAddress("http://10.8.5.137:28880/adxwsvc/services/CAdxObjectXml");
		// x3Service = serviceLocator.getCAdxWebServiceXmlCC();

		CAdxCallContext cc = new CAdxCallContext(); // Instance of
													// CAdxCallContext
		cc.codeLang = "ENG"; // Language code
		cc.codeUser = "ADMIN"; // X3 user
		cc.password = ""; // X3 password
		cc.poolAlias = "SCONN"; // Pool name
		cc.requestConfig = "trace";

		// Web service instance
		CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCService();
		// XmlResult instance
		CAdxResultXml result = new CAdxResultXml();

		CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1]; // Only one
																// element
																// (Simple
																// object)
		objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM01-15-000028"); // Method:
																			// read(CAdxCallContext
																			// <context>,
																			// String
																			// <publicname>,
																			// CAdxParamKeyValue
																			// <key>)
																			// result
																			// =
																			// wsvc.read(cc,
																			// "WS_BPR",
																			// objKey)
result = wsvc.read(cc, "WS_BPR", objKey);
	}

	public CAdxWebServiceXmlCC getCAdxWebServiceXmlCC() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public CAdxWebServiceXmlCC getCAdxWebServiceXmlCC(URL portAddress)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCAdxWebServiceXmlCCAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public Call createCall() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Call createCall(QName arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Call createCall(QName arg0, QName arg1) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Call createCall(QName arg0, String arg1) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Call[] getCalls(QName arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public HandlerRegistry getHandlerRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public Remote getPort(Class arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Remote getPort(QName arg0, Class arg1) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator getPorts() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QName getServiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	public TypeMappingRegistry getTypeMappingRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getWSDLDocumentLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
