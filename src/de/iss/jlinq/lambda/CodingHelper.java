package de.iss.jlinq.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

public class CodingHelper {

	private static final char[] ALLOWED_CHARACTERS = "ABCDEFGHIQKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_0123456789".toCharArray();
	private static final char[] FORBIDDEN_STARTING_CHARACTERS = "0123456789".toCharArray();
	
	private static Random rnd = new Random();
	
	
	public static String getRandomString(int length){
		char[] resultChars = new char[length];
		String result;
		do{
			for(int i=0; i<length; i++){
				resultChars[i] = ALLOWED_CHARACTERS[rnd.nextInt(ALLOWED_CHARACTERS.length)];
			}
			result = new String(resultChars);
		}while(isForbiddenChar(result.charAt(0)));
		return result;
	}
	
	public static String getRandomString(){
		int length = rnd.nextInt(10) + 1;
		return getRandomString(length);
	}
	
	public static String getRandomMethodName(CtClass type){
		String mN;
		List<String> forbiddenNames = new ArrayList<String>();
		for(CtMethod m : type.getMethods()){
			if(m.getDeclaringClass() != type && Modifier.isPrivate(m.getModifiers())) continue;
			forbiddenNames.add(m.getName());
		}
		do{
			mN = getRandomString();
		}while(forbiddenNames.contains(mN));
		return mN;
	}
	
	private static boolean isForbiddenChar(char c){
		for(char forbidden : FORBIDDEN_STARTING_CHARACTERS) if(c == forbidden) return true;
		return false;
	}
	
	public static void nop(){
		
	}
	
	

}
