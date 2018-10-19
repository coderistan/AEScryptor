package org.coderistan;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESkey {

    private SecretKeySpec secretKey;
    private IvParameterSpec ivParameterSpec;
    public static String random = "123456789abcdefg";

    public AESkey(String key) throws Exception {
        if (key != null) {

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] shakey = sha.digest(key.getBytes("UTF-8"));
            shakey = Arrays.copyOf(shakey,16);

            this.secretKey = new SecretKeySpec(shakey, "AES");
            this.ivParameterSpec = new IvParameterSpec(this.secretKey.getEncoded());

        } else {
            this.secretKey = new SecretKeySpec(this.random.getBytes(), "AES");
            this.ivParameterSpec = new IvParameterSpec(this.secretKey.getEncoded());
        }
    }

    public SecretKeySpec getSecret() {
        return this.secretKey;
    }

    public IvParameterSpec getIv() {
        return this.ivParameterSpec;
    }

}
