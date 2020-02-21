package com.insp.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;

//import javax.net.ssl.HttpsURLConnection;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//import java.util.stream.Collectors;
//import java.nio.file.StandardCopyOption;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import glog.externalapi.distanceengine.ExtEngineAddress;

//import sun.misc.BASE64Decoder;

public class Utils {

	public static final Logger logger = Logger.getLogger(Utils.class);

	/**
	 * returns string wrapped inside some token
	 * 
	 * @param str
	 * @param token
	 * @return
	 */
	public static String extractHttpHeader(String str) {
		String result = "";
		int start = str.indexOf("[");
		int end = str.lastIndexOf("]");
		result = str.substring(start + 1, end);
		return result;

	}

	/**
	 * Stylizer method is used to apply transformation on the provided xml using
	 * provided xsl file.
	 * 
	 * @param xsltFile
	 *            This is the xsl file which will be used for xslt
	 *            transformation.
	 * @param xml
	 *            This is the xml file which needs to be transformed
	 * @return This will be the transformed message.
	 */

	public static String inputStreamToString(InputStream in) throws UnsupportedEncodingException {
		ByteArrayOutputStream result = null;
		try {
			result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			 logger.error("Exception occured in " + new Object()
			 {}.getClass().getEnclosingMethod().getName() + "\n" +
			 errors.toString());
		}
		return result.toString("UTF-8");
	}

	/**
	 * Stylizer method is used to apply transformation on the provided xml using
	 * provided xsl file.
	 * 
	 * @param xsltFile
	 *            This is the xsl file which will be used for xslt
	 *            transformation.
	 * @param xml
	 *            This is the xml file which needs to be transformed
	 * @return This will be the transformed message.
	 */
	public static String stylizer(String xsltFile, String xml) {
		String xmlString = "";
		logger.info("Normalizing Google reponse");
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream in = classLoader.getResourceAsStream(xsltFile);
			String xsl = Utils.inputStreamToString(in);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// logger.debug("Xml for transformation="+xml);
			Document document = builder.parse(new InputSource(new StringReader(xml)));

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource stylesource = new StreamSource(new StringReader(xsl));
			Transformer transformer = tFactory.newTransformer(stylesource);
			DOMSource source = new DOMSource(document);
			// StreamResult result = new StreamResult(new File(outPutFile));
			StreamResult sw = new StreamResult(new StringWriter());
			// StreamResult result = new StreamResult(new File(System.out));
			transformer.transform(source, sw);
			xmlString = sw.getWriter().toString();

		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			 logger.error("Exception occured in " + new Object()
			 {}.getClass().getEnclosingMethod().getName() + "\n" +
			 errors.toString());
		}
		return xmlString;
	}

	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		final StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			 logger.error("Exception occured in " + new Object()
			 {}.getClass().getEnclosingMethod().getName() + "\n" +
			 errors.toString());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					 logger.error("Exception occured in " + new Object()
					 {}.getClass().getEnclosingMethod().getName() + "\n" +
					 errors.toString());
				}
			}
		}
		return sb.toString();

	}

	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
	
	public static String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        return null;
    }

	public static String callGoogleGecodeAPI(String address) {

		String result = "";

		try {

			String requestUrl = Constants.API_URL + "address=" + URLEncoder.encode(address, "UTF-8") + "&key="
					+ URLEncoder.encode(Constants.API_KEY, "UTF-8");
			// System.out.println("API Request URL : " + requestUrl);
			logger.info("Google API Request URL: " + requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("charset", "utf-8");
			// conn.setRequestProperty("Content-Type", "application/xml");
			// conn.setRequestProperty("Content-Length",
			// Integer.toString(content.getBytes().length));
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(address);
			wr.flush();
			wr.close();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
			conn.disconnect();

		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error("Exception occured in " + new Object() {
			}.getClass().getEnclosingMethod().getName() + "\n" + errors.toString());
			return "ERROR";
		}

		return result;

	}
	
	public void filterRequest(ExtEngineAddress sourceAddr) throws UnsupportedEncodingException {
		String source;
		String sourceCity = sourceAddr.getCity();
		String sourceProvinceCode = sourceAddr.getProvinceCode();
		String sourcePostalCode = sourceAddr.getPostalCode();
		String sourceCountryCode = sourceAddr.getCountryCode();
		@SuppressWarnings("rawtypes")
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
	
	@SuppressWarnings("rawtypes")
	public static String getAddress(ExtEngineAddress detailAddr) {
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

	public String appendData(ExtEngineAddress detailAddr, String addresslines) {

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

}
