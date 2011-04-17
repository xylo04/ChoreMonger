package com.android.chores;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ActivationCodeGenerator {
	
	public String generateCode(){
		SecureRandom random=new SecureRandom();
		return new BigInteger(64,random).toString(32);

	}
	
}
