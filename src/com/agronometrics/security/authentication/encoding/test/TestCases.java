package com.agronometrics.security.authentication.encoding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.agronometrics.security.authentication.encoding.DjangoPasswordEncoder;

public class TestCases {

	@Test
	public void testPato() {
		String plainpassword =  "patolo";
		String djangopassword = "pbkdf2_sha256$12000$ySDMNAaP8Dt4$sVL4iWpOg4Ehrcx+1/hFgLFLPLxTGwcXC61I7jmoQR0=";
		DjangoPasswordEncoder encoder = new DjangoPasswordEncoder();
		boolean response = encoder.isPasswordValid(djangopassword, plainpassword, "dummy");
		assertTrue(response);
	}

	
	@Test
	public void testAgronometrics() {
		String plainpassword =  "agronometrics";
		String djangopassword = "pbkdf2_sha256$12000$CjyRUJvU6z7x$/DnEiqWK2BuTaljMmQZ8wjU8eQ/gHg8hqIRBrHTD0vM=";
		DjangoPasswordEncoder encoder = new DjangoPasswordEncoder();
		boolean response = encoder.isPasswordValid(djangopassword, plainpassword, "dummy");
		assertFalse(response);
	}
}
