package com.insp.core.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.insp.service.distengine.AddressLines;
import com.insp.service.distengine.EdeParams;
import com.insp.service.distengine.ExtEngineAddress;
import com.insp.service.distengine.ExtEngineDistance;
import com.insp.service.distengine.ExtEngineException;
import com.insp.service.distengine.ExternalDistanceEngine;
import com.insp.service.distengine.KeyValue;
import com.insp.service.distengine.LocationAddressMatches;
import com.insp.service.distengine.LookupDistanceRequest;
import com.insp.service.distengine.PossibleMatches;
import com.insp.service.distengine.ValidateAddressRequest;
import com.insp.util.AuthService;
import com.insp.util.Constants;
import com.insp.util.Utils;

@WebService(targetNamespace = "http://xmlns.oracle.com/apps/otm/distanceengine", endpointInterface = "com.insp.service.distengine.ExternalDistanceEngine", serviceName = "DistanceEngineService", portName = "ExternalDistanceEnginePort")
public class DistanceEngineImpl implements ExternalDistanceEngine {

	@Resource
	WebServiceContext wsctx;

	// This method can be used in future
	@Override
	public ExtEngineDistance lookupDistance(LookupDistanceRequest lookupDistanceRequest) throws ExtEngineException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocationAddressMatches validateAddress(ValidateAddressRequest validateAddressRequest)
			throws ExtEngineException {
		// TODO Auto-generated method stub

		MessageContext mctx = wsctx.getMessageContext();
		@SuppressWarnings("rawtypes")
		Map httpHeaders = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		String encodedUserPassword = httpHeaders.get("authorization").toString();
		encodedUserPassword = Utils.extractHttpHeader(encodedUserPassword);
		AuthService authsvc = new AuthService();
		if (authsvc.authenticate(encodedUserPassword)) {
			System.out.println("============== Request Authentication OK =============");
			EdeParams edeParams = validateAddressRequest.getEdeParams();
			ExtEngineAddress extEngineAddress = validateAddressRequest.getAddress();
			List<KeyValue> param1 = edeParams.getEdeParam();
			String sourceAddType = "";
			String otmLocAddress = "";
			String result = "";
			String xsl = Constants.XSL_GEOCODE;

			for (KeyValue keyValue : param1) {
				// System.out.println(keyValue.getValue()+"
				// "+keyValue.getName());
				if (keyValue.getName().contains("SOURCE_ADDRESS_TYPE")) {
					sourceAddType = keyValue.getValue();
				}
			}

			// System.out.println("SOURCE_ADDRESS_TYPE " + sourceAddType);

			if (sourceAddType.equalsIgnoreCase("ADDRESS")) {
				// Getting address lines from request
				AddressLines addressLines = extEngineAddress.getAddressLines();
				List<String> addresslines = addressLines.getAddressLine();
				for (int i = 0; i < addresslines.size(); i++) {
					otmLocAddress += addresslines.get(i);
				}
			}

			// System.out.println(otmLocAddress);

			// Calling Google API
			result = Utils.callGoogleGecodeAPI(otmLocAddress);
			System.out.println("API Response " + result);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder;
			LocationAddressMatches locationAddressMatches = new LocationAddressMatches();
			PossibleMatches possibleMatches = new PossibleMatches();

			try {

				result = Utils.stylizer(xsl, result);
				domBuilder = domFactory.newDocumentBuilder();
				InputSource sbis = new InputSource(new StringReader(result));
				Document doc = domBuilder.parse(sbis);
				NodeList nList = doc.getElementsByTagName("result");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					// Instantiate below Objects for creating response
					ExtEngineAddress extEngineAddressNorm = new ExtEngineAddress();
					AddressLines normalizedAddLines = new AddressLines();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;

					String status = Utils.getTagValue("status", eElement);

					if (status.equalsIgnoreCase("OK")) {

						// Google returns valid response
						// Proceed for Parsing

						String formatted_address = Utils.getTagValue("formattedAddress", eElement);
						// System.out.println("formatted_address
						// "+formatted_address);
						String countryCode = Utils.getTagValue("countryCode", eElement);
						// System.out.println("countryCode "+countryCode);
						String postalCode = Utils.getTagValue("postalCode", eElement);
						// System.out.println("postalCode "+postalCode);
						String province = Utils.getTagValue("province", eElement);
						// System.out.println("province "+province);
						String lat = Utils.getTagValue("lat", eElement);
						// System.out.println("lat "+lat);
						String lon = Utils.getTagValue("lon", eElement);
						// System.out.println("lon "+lon);
						String city = Utils.getTagValue("city", eElement);
						// System.out.println("city "+city);

						// Preparing response to be send back to OTM application
						extEngineAddressNorm.setCity(city);
						extEngineAddressNorm.setCountryCode(countryCode);
						extEngineAddressNorm.setPostalCode(postalCode);
						extEngineAddressNorm.setProvince(province);
						extEngineAddressNorm.setLatitude(Double.parseDouble(lat));
						extEngineAddressNorm.setLongitude(Double.parseDouble(lon));
						extEngineAddressNorm.setSeqNumber(temp + 1);
						normalizedAddLines.getAddressLine().add(formatted_address);
						extEngineAddressNorm.setAddressLines(normalizedAddLines);
						possibleMatches.getPossibleMatch().add(extEngineAddressNorm);
						locationAddressMatches.setPossibleMatches(possibleMatches);
					}

					else {
						locationAddressMatches.setValidFlag(false);
						throw new ExtEngineException("Google API Error", status);
					}

				}

			} catch (ParserConfigurationException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				throw new ExtEngineException("ParserConfigurationException", errors.toString());
			} catch (SAXException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				throw new ExtEngineException("SAXException", errors.toString());
			} catch (IOException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				throw new ExtEngineException("IO Exception", errors.toString());
			}
			locationAddressMatches.setValidFlag(true);
			return locationAddressMatches;
		}

		else {
			System.out.println("============== Request Authentication FAILED =============");
			throw new ExtEngineException("Unauthorized", "Authentication failed! Wrong username / password!");
		}

	}
}
