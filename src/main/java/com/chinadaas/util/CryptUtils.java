package com.chinadaas.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/** 加密工具类 */
public class CryptUtils {

	private static final String DES = "DES";
	
	public static final String CRYPT_DES_KEY = "gsinfo123456";

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key) {
		// DES算法要求有一个可信任的随机数源
		try {
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] src, byte[] key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 数据解密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return String 
	 * @throws Exception
	 */
	private final static String decrypt(String data, String key) {
		return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
	}

	/**
	 * 数据加密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	private final static String encrypt(String data, String key) {
		if (data != null)
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/** 对字符串按密钥进行DES加密 */
	public static String encode(String data) {
		return encrypt(data, CRYPT_DES_KEY);
	}
	
	/** 对字符串按密钥进行DES解密 */
	public static String decode(String data) {
		return decrypt(data, CRYPT_DES_KEY);
	}

	/** 对字符串按密钥进行DES加密 */
	public static String encode(String data, String key) {
		if(data != null && key != null) {
			try {
				SecretKey secretKey = generateSecretKey(key);
				Cipher cipher = Cipher.getInstance(DES);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return encryptBASE64(cipher.doFinal(data.getBytes()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/** 对字符串按密钥进行DES解密 */
	public static String decode(String data, String key) {
		if(data != null && key != null) {
			try {
				SecretKey secretKey = generateSecretKey(key);
				Cipher cipher = Cipher.getInstance(DES); // Get the cipher
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(decryptBASE64(data)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	// 根据密码和keyGenerator生成密钥。
	private static SecretKey generateSecretKey(String key) {
		SecretKey secretKey = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = key.getBytes();
			md.update(bytes, 0, bytes.length);
			byte[] mdBytes = md.digest(); // Generate 16 bytes
			byte[] truncatedBytes = Arrays.copyOf(mdBytes, 8); // Fetch 8 bytes for DESKeySpec
			DESKeySpec keySpec = new DESKeySpec(truncatedBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	/** 转换成字符串 */
	public static String convertToHexString(byte data[]) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if(hex.length() == 1) {
				hex = '0' + hex;
			}
			strBuffer.append(hex.toUpperCase());
		}
		return strBuffer.toString();
	}

	/** BASE64加密 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new Base64()).encodeToString(key);
//		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/** BASE64解密 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new Base64()).decode(key);
//		return (new BASE64Decoder()).decodeBuffer(key);
	}

	public static void main(String[] args) {
		System.out.println(decode("C5AEDEF791086548C86B8ADD66C9EDD2"));
	}
}