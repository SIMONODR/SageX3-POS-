import com.adonix.wsvc.stubs.CAdxCallContext;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCC;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCService;
import com.adonix.wsvc.stubs.CAdxWebServiceXmlCCServiceLocator; // Note this new one
import com.adonix.awss.stubs.CAdxParamKeyValue;
import com.adonix.awss.stubs.CAdxResultXml;
import com.adonix.awss.stubs.CAdxMessage;
import java.io.StringReader; // String reading/building classes
import javax.xml.parsers.DocumentBuilder; // DOM builder
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import java.rmi.server.ObjID;
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

public class SoapRead {
	private static String destination = "http://10.8.5.137:28880/adxwsvc/services/CAdxObjectXml";
	private static String publicName = "SORDER";
	private static String objectKeys = "OUT-DMM01-15-000016";

	public static void execute() {

		try {
			// Web service instance

			// First create the connection
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			MessageFactory messageFactory = MessageFactory.newInstance();

			SOAPMessage message = messageFactory.createMessage();

			// Create objects for the message parts
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPHeader header = envelope.getHeader();
			SOAPElement hElement1 = prepareHeader(envelope, header);
			SOAPBody body = envelope.getBody();
			// SOAPElement bodyElement = prepareCreate(body, envelope);
			SOAPElement bodyElement = prepareRead(body, envelope);
			System.out.println(bodyElement);
			message.saveChanges();

			// Check the input
			System.out.println("\nREQUEST:\n");
			message.writeTo(System.out);
			System.out.println();

			// Send the message
			SOAPMessage reply = connection.call(message, destination);

			// Check the output
			System.out.println("\nRESPONSE:\n");
			// Create the transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// Extract the content of the reply
			Source sourceContent = reply.getSOAPPart().getContent();
			// Set the output for the transformation
			StreamResult result1 = new StreamResult(System.out);
			transformer.transform(sourceContent, result1);
			System.out.println();

			// Close the connection

			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static SOAPElement prepareHeader(SOAPEnvelope envelope,
			SOAPHeader header) {
		SOAPElement hElement1 = null;
		try {
			hElement1 = header.addChildElement(envelope.createName(
					"CAdxCallingContext", "wss", "http://www.adonix.com/WSS"));
			// Add content
			hElement1.addChildElement(
					envelope.addChildElement("codeLang", "wss",
							"http://www.adonix.com/WSS")).addTextNode("ENG");
			hElement1.addChildElement(
					envelope.addChildElement("codeUser", "wss",
							"http://www.adonix.com/WSS")).addTextNode("ADMIN");
			hElement1.addChildElement(
					envelope.addChildElement("password", "wss",
							"http://www.adonix.com/WSS")).addTextNode("");
			hElement1.addChildElement(
					envelope.addChildElement("poolAlias", "wss",
							"http://www.adonix.com/WSS")).addTextNode("SCONN");
			hElement1.addChildElement(
					envelope.addChildElement("requestConfig", "wss",
							"http://www.adonix.com/WSS")).addTextNode("trace");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hElement1;
	}

	public static SOAPElement prepareRead(SOAPBody body, SOAPEnvelope envelope) {
		SOAPElement bodyElement = null;
		SOAPElement bodyElement1 = null;
		// CAdxWebServiceXmlCCService wsvc = new CAdxWebServiceXmlCCService();
		// XmlResult instance
		CAdxResultXml result = new CAdxResultXml();
		try {
			bodyElement = body.addChildElement(envelope.createName("read",
					"wss", "http://www.adonix.com/WSS"));

			// Add content
			bodyElement.addChildElement("publicName").addTextNode(publicName);

			CAdxParamKeyValue[] objKey = new CAdxParamKeyValue[1];
			objKey[0] = new CAdxParamKeyValue("SOHNUM", "OUT-DMM01-15-000028");
			// result = wsvc.read("CAdxCallingContext", "SORDER", objKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bodyElement;
	}

	public static void main(String ar[]) {

		execute();
	}

}
