package glog.externalapi.distanceengine;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

//public class ExternalDistanceLookup implements ExternalDistanceEngineInterface {
public class ValidateAddressGoogle implements ExternalDistanceEngineInterface {
	public static final Logger logger = Logger.getLogger(ValidateAddressGoogle.class);

	private String source;
	// private String dest;
	String xsl = Constants.XSL_GEOCODE;
	String result;

	@Override
	public ExtEngineDistance lookupDistance(ExtEngineAddress paramExtEngineAddress1,
			ExtEngineAddress paramExtEngineAddress2, ExtEngineAuxInput paramExtEngineAuxInput, String paramString,
			@SuppressWarnings("rawtypes") Map paramMap) throws ExtEngineException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LocationAddressMatches validateAddress(ExtEngineAddress paramExtEngineAddress,
			ExtEngineAuxInput paramExtEngineAuxInput, String paramString, @SuppressWarnings("rawtypes") Map paramMap)
			throws ExtEngineException {
		// TODO Auto-generated method stub

		
		LocationAddressMatches locationAddressMatches = new LocationAddressMatches();
		List possibleMatches = new ArrayList();
		
		try {
			logger.info("Validation Address Method Started");
			PropertyConfigurator.configure(getClass().getResource("/properties/log4j.properties"));
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
		// Collection address = paramExtEngineAddress.getAddress();
		String address = getAddress(paramExtEngineAddress);
		// System.out.println("Request Data "+ city+" "+countryCode+"
		// "+province+" "+postalcode+" "+destAdd.toString());

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
			// System.out.println("API Response " + result);
			logger.info("Calling Google API for Address Validation End, result is : " + result);

			if (result.isEmpty() || result.contains("ERROR")) {

				throw new ExtEngineException("Error occured in calling Google API");
			}

			result = Utils.stylizer(xsl, result);
			logger.info("API Response after Transformation : " + result);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder;
			// List possibleMatches =new ArrayList();

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

					String nformatted_address = Utils.getTagValue("formattedAddress", eElement);
					// System.out.println("formatted_address
					// "+formatted_address);
					String ncountryCode = Utils.getTagValue("countryCode", eElement);
					// System.out.println("countryCode "+countryCode);
					String npostalCode = Utils.getTagValue("postalCode", eElement);
					if (npostalCode.contains("XX")) {
						npostalCode = "";
					}
					// System.out.println("postalCode "+postalCode);
					String nprovince = Utils.getTagValue("province", eElement);
					// System.out.println("province "+province);
					String nlat = Utils.getTagValue("lat", eElement);
					// System.out.println("lat "+lat);
					String nlon = Utils.getTagValue("lon", eElement);
					// System.out.println("lon "+lon);
					String ncity = Utils.getTagValue("city", eElement);
					if (ncity.contains("XX")) {
						ncity = "";
					}
					
					// System.out.println("city "+city);

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
					// locationAddressMatches.setPossibleMatches(possibleMatches);

				}

				else {
					logger.error("Not a valid response from Google : " + status);
					locationAddressMatches.setValidFlag("N");
					// throw new ExtEngineException("Google API Error");
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
		logger.info("Response returned");
		logger.info("Validation Address Method End");
		return locationAddressMatches;
	}

	@SuppressWarnings("rawtypes")
	public String getAddress(ExtEngineAddress detailAddr) {
		StringBuffer buf = new StringBuffer();
		Collection addressLines = detailAddr.getAddress();
		if (addressLines != null) {
			Iterator lineIter = addressLines.iterator();
			while (lineIter.hasNext())
				buf.append((String) lineIter.next()).append(",");
		}

		// return buf.toString();
		return buf.deleteCharAt(buf.length() - 1).toString();
	}

	private String appendData(ExtEngineAddress detailAddr, String addresslines) {

		StringBuffer buf = new StringBuffer();
		buf.append(addresslines).append(isNull(detailAddr.getCity())).append(isNull(detailAddr.getProvinceCode()))
				.append(isNull(detailAddr.getPostalCode())).append(isNull(detailAddr.getCountryCode()));

		return buf.deleteCharAt(buf.length() - 1).toString();
	}

	public String isNull(String s) {

		if (s != null && (!(s.trim().equals("")))) {
			return s += ",";
		}

		return "";
	}

	private void filterRequest(ExtEngineAddress sourceAddr) throws UnsupportedEncodingException {

		String sourceCity = sourceAddr.getCity();
		String sourceProvinceCode = sourceAddr.getProvinceCode();
		String sourcePostalCode = sourceAddr.getPostalCode();
		String sourceCountryCode = sourceAddr.getCountryCode();
		Collection sourceAddress = sourceAddr.getAddress();
		Double sourceLAT = sourceAddr.getLat();
		Double sourceLON = sourceAddr.getLon();

		// below are all madatory else no result will be displayed
		if (sourceAddress != null && sourceCity != null && sourceProvinceCode != null) {

			source = URLEncoder.encode(appendData(sourceAddr, isNull(getAddress(sourceAddr))), "UTF-8");

		} else if (sourceLAT != null && sourceLON != null) {
			source = sourceLAT + "," + sourceLON;
		}

	}

	public static void main(String[] args) throws ExtEngineException {
		ValidateAddressGoogle obj = new ValidateAddressGoogle();

		// List<String> myList = new ArrayList<String>();
		Collection<String> collection = new ArrayList<String>();
		collection.add("Darpale, Pali(Church)");
		// collection.add("Near Pali church");
		// collection.add("Vasai");

		ExtEngineAddress a = new ExtEngineAddress("Mumbai", "MH", "40070", null, "IN", null, collection, null, null,
				null, null);
		// ExtEngineAddress b = new
		// ExtEngineAddress("Mumbai","MH","40080",null,"IN",null,null,null,null,null,null);
		
		 System.out.println("Output "+org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(obj.validateAddress(a, null, "", null)));
		 
		 
	}
}
