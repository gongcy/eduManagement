package com.zslin.basic.auth.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DesUtil {

	/** 字符串默认键值 */
	private static String strDefaultKey = "default_key";

	/** 加密工具 */
	private Cipher encryptCipher = null;

	/** 解密工具 */
	private Cipher decryptCipher = null;

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public byte[] hexStr2ByteArr(String strIn) {
		try {
			byte[] arrB = strIn.getBytes("UTF-8");
			int iLen = arrB.length;

			// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
			byte[] arrOut = new byte[iLen / 2];
			for (int i = 0; i < iLen; i = i + 2) {
				String strTmp = new String(arrB, i, 2);
				arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
			}
			return arrOut;
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public DesUtil() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws Exception
	 */
	public DesUtil(String strKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Key key = getKey(strKey.getBytes("UTF-8"));

			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);

			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException{
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws IllegalBlockSizeException, BadPaddingException{
		try {
			return byteArr2HexStr(encrypt(strIn.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws IllegalBlockSizeException, BadPaddingException {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private Key getKey(byte[] arrBTmp) {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 * 加密
	 * @param key 自定义密钥
	 * @param value 原文 密码
	 * @return 返回加密后的密文
	 */
	public static String password(String key, String value) {
		if(value==null || "".equals(value)) {return "";}
		String result = "-1";
		try {
			DesUtil des = new DesUtil(key);
			result = des.encrypt(value);
		} catch (Exception e) {
			result = value;
		}
		return result;
	}
	
	public static String password(String resource) {
		return password(strDefaultKey, resource);
	}
	
	public static String unPassword(String code) {
		return unPassword(strDefaultKey, code);
	}
	
	/**
	 * 解密
	 * @param key 自定义密钥
	 * @param value 密文
	 * @return 返回原文
	 */
	public static String unPassword(String key, String value) {
		if(value==null || "".equals(value)) {return "";}
		String result = "-1";
		try {
			DesUtil des = new DesUtil(key);
			result = des.decrypt(value);
		}  catch (Exception e) {
			result = value;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String val = DesUtil.password("a_+_b_+_1_+_0");
		System.out.println(val);
		System.out.println(DesUtil.unPassword(val));
	}
}
