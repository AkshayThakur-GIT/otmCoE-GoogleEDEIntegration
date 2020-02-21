package com.insp.util;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Base64;

public class AuthService {
	public boolean authenticate(String authCredentials) {
 
		if (null == authCredentials)
			return false;
 
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		boolean authenticationStatus = Constants.APP_USERNAME.equals(username)
				&& Constants.APP_PASSWORD.equals(password);
		return authenticationStatus;
	}
 
}
