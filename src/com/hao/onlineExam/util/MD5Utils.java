package com.hao.onlineExam.util;

import sun.misc.BASE64Encoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String createMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] temp = md.digest(password.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(temp);
    }

	public static String getMD5Code(String strObj) throws NoSuchAlgorithmException {
//		String resultString = null;
//		try {
//			resultString = new String(strObj);
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			resultString = byteToString(md.digest(strObj.getBytes()));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return resultString;
		MessageDigest md = null;
		BASE64Encoder encoder = null;
		try {
			md = MessageDigest.getInstance("MD5");
			encoder = new BASE64Encoder();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encoder.encode(md.digest(strObj.getBytes()));
	}
	
//	public static void main(String[] args){
//		System.out.println(MD5Utils.GetMD5Code("student1"));
//		System.out.println(MD5Utils.GetMD5Code("teacher1"));
//	}
}


