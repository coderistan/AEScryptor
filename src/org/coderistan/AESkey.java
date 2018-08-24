package org.coderistan;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESkey {

    private SecretKeySpec secretKey;
    private IvParameterSpec ivParameterSpec;
    public static String random = "123456789abcdefg";

    public AESkey(String key) throws Exception {
        if (key.length() == 16 || key.length() == 24 || key.length() == 32) {
            this.secretKey = new SecretKeySpec(key.getBytes(), "AES");
            this.ivParameterSpec = new IvParameterSpec(this.secretKey.getEncoded());
        }else{
            throw new Exception("Anahtar uzunluğu 16,24 veya 32 olmalıdır");
        }
    }

    public SecretKeySpec getSecret() {
        return this.secretKey;
    }

    public IvParameterSpec getIv() {
        return this.ivParameterSpec;
    }

}
