package com.project2.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Password {

	public Password() {
	}
	
	public static String hash(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException();
		}
	}

}
