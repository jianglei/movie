package com.umeng.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesHelper {
	private static byte[] password = "uLi4/f4+Pb39.T19".getBytes();
	private static byte[] iv = "nmeug.f9/Om+L823".getBytes();
	
    public static String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);
        byte[] outText = cipher.doFinal(input.getBytes("gbk"));
        return Base64.encodeBase64String(outText);
    }
    
    public static String decrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
        byte[] outText = cipher.doFinal(Base64.decodeBase64(input));
        return new String(outText);
    }  
    
    public static void main(String[] args) {
        try {
        	String en = encrypt("clientid=10002&slotid=10029&slotidx=0&idmd5=a3826e094b8fa86b2cce138665b4997&device_model=Desire HD&appkey=test_key&app_version=1.0&version_code=1&sdk_version=1.0&os=Android&os_version=2.2.1&country=unknown&language=unknown&timezone=8&resolution=800*480&access=Wi-Fi&carrier=中国移动&lat=39.9773529&lng=116.3003163&ad_id=186,242,141,187,148,142,191,140,139,147&ad_id=141&ad_price=200000&cost_per=0");
        	System.out.println(en);
        	String de = decrypt("JOK75uQrm6cXCRhZsc5aD4e1LG4L8KXCfVfeimJMG48vff8O6c0mvVaDX3K9LKGp7Xfhqvn7r2KpPg5hJWjIm85kxgK1Rj+aGH3hZEfuTxoKEwa4frA5ugTk17iV71LPgrCGIck2ZFydrVE0+li8ursLLaIdq1ywW1fkKMuYpWRX7xjIZaZk2rO4MjFtE5TG3xwz3N1Ehc2iL8rbS4nZefBBZqecfLrrcSaOm7fYDiT3sxq958tg4yQH3n3XKuRMuqoNer3O2L+oWsi6dsBAESTR2XELxXdIgRcQIaxVBCvLUOdgLEwCjSMVMIWLpb4BzXBJS58y3kDtnXMP5SJxO7H192kYCqvXJa7MslAJxtgmkTYR0nILmha/K6MiPYmAqtkz3/uaP7oM9vCqp4NHkl2Jn/EWREyHRNzf+WkyxfvE1awn+ZKSKMLDx8h1wvP7");
        	//String de = decrypt(en);
        	System.out.println(de);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}