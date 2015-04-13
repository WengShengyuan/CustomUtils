package main.custom;
  
import java.io.IOException;
import java.math.BigInteger;  
import java.security.SecureRandom;  
  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.spec.SecretKeySpec;  
  
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;  
  
  
/** 
 * 编码工具类 
 * 1.将byte[]转为各种进制的字符串 
 * 2.base 64 encode 
 * 3.base 64 decode 
 * 4.获取byte[]的md5值 
 * 5.获取字符串md5值 
 * 6.结合base64实现md5加密 
 * 7.AES加密 
 * 8.AES加密为base 64 code 
 * 9.AES解密 
 * 10.将base 64 code AES解密 
 * @author uikoo9 
 * @version 0.0.7.20140601 
 */  
public class AESUtil {  
	public static final String KEY = "CAISHOUBIN0828"; 
    public static void main(String[] args) throws Exception {  
        String content = "18511898382";  
        System.out.println("加密前：" + content);  
  
        System.out.println("加密密钥和解密密钥：" + KEY);  
        String encrypt = aesEncrypt(content, KEY);  
        System.out.println("加密后：" + encrypt);  
        String decrypt = aesDecryp(encrypt, KEY);  
        System.out.println("解密后：" + decrypt);  
        System.out.println("String改16进制:");
        System.out.println(toHexString(encrypt));
        System.out.println("16进制改String:");
        System.out.println(toStringHex(toHexString(encrypt)));
        
    }  
      
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
      
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return new BASE64Encoder().encode(bytes);  
    }  
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));  
  
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
          
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
      
    /** 
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return toHexString(base64Encode(aesEncryptToBytes(content, encryptKey)));  
    }  
    
    /** 
     * base 64 code解密为AES 
     * @param content 待解密的内容 
     * @param encryptKey 加密密钥 
     * @return 解密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesDecryp(String content, String encryptKey) throws Exception {  
    	byte[] ret = new BASE64Decoder().decodeBuffer(toStringHex(content));
    	  return aesDecryptByBytes(ret, encryptKey);  
    }
      
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));  
          
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes);  
    }  
 // 转化字符串为十六进制编码 
    public static String toHexString(String s){ 
	    String str=""; 
	    for (int i=0;i<s.length();i++){ 
		    int ch = (int)s.charAt(i); 
		    String s4 = Integer.toHexString(ch); 
		    str = str + s4; 
	    } 
	    return str; 
    } 
    // 转化十六进制编码为字符串 
    public static String toStringHex(String s) { 
	    byte[] baKeyword = new byte[s.length()/2]; 
	    for(int i = 0; i < baKeyword.length; i++){ 
		    try { 
		    	baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16)); 
		    } catch(Exception e) { 
		    	e.printStackTrace(); 
		    } 
	    } 
	    try { 
	    	s = new String(baKeyword, "utf-8");//UTF-16le:Not 
	    }catch (Exception e1) { 
	    	e1.printStackTrace(); 
	    } 
	    return s; 
    } 
      
}  