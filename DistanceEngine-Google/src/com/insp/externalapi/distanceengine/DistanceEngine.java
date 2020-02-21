package com.insp.externalapi.distanceengine;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.insp.util.Constants;
import com.insp.util.Utils;
import glog.externalapi.distanceengine.ExtEngineAddress;
import glog.externalapi.distanceengine.ExtEngineAuxInput;
import glog.externalapi.distanceengine.ExtEngineDistance;
import glog.externalapi.distanceengine.ExtEngineException;
import glog.externalapi.distanceengine.ExternalDistanceEngineInterface;
import glog.externalapi.distanceengine.LocationAddressMatches;

public class DistanceEngine implements ExternalDistanceEngineInterface {
	public static final Logger logger = Logger.getLogger(DistanceEngine.class);

	@SuppressWarnings({ "rawtypes" })
	@Override
	public ExtEngineDistance lookupDistance(ExtEngineAddress paramExtEngineAddress1,
			ExtEngineAddress paramExtEngineAddress2, ExtEngineAuxInput paramExtEngineAuxInput, String paramString,
			Map paramMap) throws ExtEngineException {

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LocationAddressMatches validateAddress(ExtEngineAddress paramExtEngineAddress,
			ExtEngineAuxInput paramExtEngineAuxInput, String paramString, Map paramMap) throws ExtEngineException {

		String xsl = Constants.XSL_GEOCODE;
		String result = "";

		LocationAddressMatches locationAddressMatches = new LocationAddressMatches();
		List possibleMatches = new ArrayList();

		try {
			
			PropertyConfigurator.configure(getClass().getResource("/properties/log4j.properties"));
			logger.info("Validation Address Method Started");
			logger.info("Loaded log4j properties for logging");
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error("Exception occured in loading log4j.properties file. " + new Object() {
			}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
			return null;
		}

		// Getting OTM input parameters from Location Object
		String city = paramExtEngineAddress.getCity();
		String countryCode = paramExtEngineAddress.getCountyCode();
		String province = paramExtEngineAddress.getProvinceCode();
		String postalcode = paramExtEngineAddress.getPostalCode();
		String address = Utils.getAddress(paramExtEngineAddress);

		logger.info("OTM Request Data -------- ");
		logger.info("city -- " + city);
		logger.info("countryCode -- " + countryCode);
		logger.info("province -- " + province);
		logger.info("postalcode -- " + postalcode);
		logger.info("address -- " + address.toString());

		try {

			// filterRequest(paramExtEngineAddress);
			// System.out.println("Formatted Address from OTM "+source);

			// Calling Google API
			logger.info("Calling Google API for Address Validation Started");
			result = Utils.callGoogleGecodeAPI(address.toString());
			logger.info("Calling Google API for Address Validation End, result is : " + result);

			if (result.isEmpty() || result.contains("ERROR")) {

				throw new ExtEngineException("Error occured in calling Google API");
			}

			result = Utils.stylizer(xsl, result);
			logger.info("API Response after Transformation : " + result);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder;
			domBuilder = domFactory.newDocumentBuilder();
			InputSource sbis = new InputSource(new StringReader(result));
			Document doc = domBuilder.parse(sbis);
			NodeList nList = doc.getElementsByTagName("result");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				ExtEngineAddress extEngineAddressNorm = new ExtEngineAddress();

				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String status = Utils.getTagValue("status", eElement);
				Collection<String> collection = new ArrayList<String>();
				if (status.equalsIgnoreCase("OK")) {

					// Google returns valid response
					// Proceed for Parsing
					logger.info("Valid response, proceed for data extraction. Result : " + temp);

					String nformatted_address = Utils.getString("formattedAddress", eElement);
					String ncountryCode = Utils.getString("countryCode", eElement);
					String npostalCode = Utils.getString("postalCode", eElement);
					String nprovince = Utils.getString("province", eElement);
					String nlat = Utils.getString("lat", eElement);
					String nlon = Utils.getString("lon", eElement);
					String ncity = Utils.getString("city", eElement);
					
					logger.info("formattedAddress -- " + nformatted_address);
					logger.info("countryCode -- " + ncountryCode);
					logger.info("postalCode -- " + npostalCode);
					logger.info("province -- " + nprovince);
					logger.info("lat -- " + nlat);
					logger.info("lon -- " + nlon);
					logger.info("city -- " + ncity);

					// Preparing response to be send back to OTM application
					extEngineAddressNorm.setCity(ncity);
					extEngineAddressNorm.setCountryCode(ncountryCode);
					extEngineAddressNorm.setPostalCode(npostalCode);
					extEngineAddressNorm.setProvinceCode(nprovince);
					extEngineAddressNorm.setLat(Double.parseDouble(nlat));
					extEngineAddressNorm.setLon(Double.parseDouble(nlon));
					extEngineAddressNorm.setSeqNumber(temp + 1);
					collection.add(nformatted_address);
					extEngineAddressNorm.setAddress(collection);
					locationAddressMatches.setNewAddressObject(extEngineAddressNorm);
					possibleMatches.add(extEngineAddressNorm);

				}

				else {
					logger.error("Not a valid response from Google : " + status);
					locationAddressMatches.setValidFlag("N");
					locationAddressMatches.setPossibleMatches(null);
					return locationAddressMatches;
				}

			}

		} catch (Throwable t) {
			StringWriter errors = new StringWriter();
			t.printStackTrace(new PrintWriter(errors));
			logger.error("Exception occured in " + new Object() {
			}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
			throw new ExtEngineException(t, "External Distance Engine class failed " + address.toString());

		}

		logger.info("Setting response object to send to OTM");
		locationAddressMatches.setValidFlag("Y");
		locationAddressMatches.setPossibleMatches(possibleMatches);
		logger.info("Response set");
		logger.info("Validation Address Method End");
		return locationAddressMatches;
		
	}

	// Method to Test the code
	public static void main(String[] args) throws ExtEngineException {
		DistanceEngine obj = new DistanceEngine();
		Collection<String> collection = new ArrayList<String>();
		collection.add("rr,");
		// collection.add("Near Pali church");
		// collection.add("Vasai");
		ExtEngineAddress extEngineAddress = new ExtEngineAddress("Mumbai", "MH", "40070", null, "IN", null, collection,
				null, null, null, null);
		System.out.println("Output " + org.apache.commons.lang.builder.ToStringBuilder
				.reflectionToString(obj.validateAddress(extEngineAddress, null, "", null)));

	}

}
