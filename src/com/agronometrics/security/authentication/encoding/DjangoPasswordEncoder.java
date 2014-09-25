package com.agronometrics.security.authentication.encoding;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.PasswordEncoder;


public class DjangoPasswordEncoder implements PasswordEncoder {
	
	private static final Logger log = LoggerFactory.getLogger(DjangoPasswordEncoder.class);

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return null;
    }

    @Override
    public boolean isPasswordValid(String passedInPassword, String rawPass, Object salt) {
    	log.info("Parameters received are: ");
    	log.info("Passed in password: "+passedInPassword);
    	log.info("Raw pass: "+rawPass);
    	String[] encodedPassword = passedInPassword.split("\\$");
    	int encodedIterations = Integer.parseInt(encodedPassword[1]);
    	byte[] encodedSalt = encodedPassword[2].getBytes(Charset.forName("UTF-8"));
    	String encodedHash = encodedPassword[3];
    	 
    	SecretKeyFactory f = null;
    	try {
    	    f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    	} catch (NoSuchAlgorithmException e) {
    		log.error("Need a Java implementation with cryptography.", e);
    	    return false;
    	}
    	 
    	KeySpec ks = new PBEKeySpec(rawPass.toCharArray(), encodedSalt, encodedIterations, 256);
    	SecretKey s = null;
    	try {
    	    s = f.generateSecret(ks);
    	} catch (InvalidKeySpecException e) {
    		log.error("Encoded password is corrupt.", e);
    	    return false;
    	}
    	 
    	if (encodedHash.equals(Base64.getEncoder().encodeToString(s.getEncoded()))) {
    		log.debug("User is good.");
    	    return true;
    	} else {
    		log.error("User credentials are bullshit");
    	    return false;
    	}

    	
    }
}