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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
public class DistanceEngine implements ExternalDistanceEngine {
	public static final Logger logger = Logger.getLogger(DistanceEngine.class);
	@Resource
	WebServiceContext wsctx;

	// This method can be used in future
	@Override
	public ExtEngineDistance lookupDistance(LookupDistanceRequest lookupDistanceRequest) throws ExtEngineException {
		return null;
	}

	@Override
	public LocationAddressMatches validateAddress(ValidateAddressRequest validateAddressRequest)
			throws ExtEngineException {

		try {

			PropertyConfigurator.configure(getClass().getResource("/properties/log4j.properties"));
			logger.info("Address Validation Method Started");
			logger.info("Loaded log4j properties for logging");
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error("Exception occured in loading log4j.properties file. " + new Object() {
			}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
			logger.info("Address Validation Method End");
			return null;
		}

		MessageContext mctx = wsctx.getMessageContext();
		@SuppressWarnings("rawtypes")
		Map httpHeaders = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		String encodedUserPassword = httpHeaders.get("authorization").toString();
		logger.info("Authorizing incoming request");
		encodedUserPassword = Utils.extractHttpHeader(encodedUserPassword);
		logger.info("Authorization details: " + encodedUserPassword);
		AuthService authsvc = new AuthService();
		if (authsvc.authenticate(encodedUserPassword)) {
			logger.info("Authorization successful");
			EdeParams edeParams = validateAddressRequest.getEdeParams();
			ExtEngineAddress extEngineAddress = validateAddressRequest.getAddress();
			List<KeyValue> param1 = edeParams.getEdeParam();
			String sourceAddType = "";
			String otmLocAddress = "";
			String result = "";
			String xsl = Constants.XSL_GEOCODE;

			for (KeyValue keyValue : param1) {
				if (keyValue.getName().contains("SOURCE_ADDRESS_TYPE")) {
					sourceAddType = keyValue.getValue();
				}
			}
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
			logger.info("Calling Google API for Address Validation Started");
			result = Utils.callGoogleGecodeAPI(otmLocAddress);
			logger.info("Calling Google API for Address Validation End, result is : " + result);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder;
			LocationAddressMatches locationAddressMatches = new LocationAddressMatches();
			PossibleMatches possibleMatches = new PossibleMatches();

			try {

				result = Utils.stylizer(xsl, result);
				logger.info("API Response after Transformation : " + result);
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

					String status = Utils.getString("status", eElement);

					if (status.equalsIgnoreCase("OK")) {

						// Google returns valid response
						// Proceed for Parsing

						String formatted_address = Utils.getString("formattedAddress", eElement);
						String countryCode = Utils.getString("countryCode", eElement);
						String postalCode = Utils.getString("postalCode", eElement);
						String province = Utils.getString("province", eElement);
						String lat = Utils.getString("lat", eElement);
						String lon = Utils.getString("lon", eElement);
						String city = Utils.getString("city", eElement);

						logger.info("formattedAddress -- " + formatted_address);
						logger.info("countryCode -- " + countryCode);
						logger.info("postalCode -- " + postalCode);
						logger.info("province -- " + province);
						logger.info("lat -- " + lat);
						logger.info("lon -- " + lon);
						logger.info("city -- " + city);

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
						logger.error("Not a valid response from Google : " + status);
						logger.info("Address Validation Method End");
						locationAddressMatches.setValidFlag(false);
						throw new ExtEngineException("Google API Error", status);
					}

				}

			} catch (ParserConfigurationException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error("Exception occured " + new Object() {
				}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
				logger.info("Address Validation Method End");
				throw new ExtEngineException("ParserConfigurationException", errors.toString());
			} catch (SAXException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error("Exception occured " + new Object() {
				}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
				logger.info("Address Validation Method End");
				throw new ExtEngineException("SAXException", errors.toString());
			} catch (IOException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error("Exception occured " + new Object() {
				}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
				logger.info("Address Validation Method End");
				throw new ExtEngineException("IO Exception", errors.toString());
			}
			logger.info("Setting response object to send to OTM");
			locationAddressMatches.setValidFlag(true);
			logger.info("Response set");
			logger.info("Address Validation Method End");
			return locationAddressMatches;
		}

		else {
			logger.info("Authorization failed, Wrong username / password!");
			logger.info("Address Validation Method End");
			throw new ExtEngineException("Unauthorized", "Authentication failed! Wrong username / password!");
		}

	}
}
