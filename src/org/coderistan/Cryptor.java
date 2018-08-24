package org.coderistan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

/**
 * AES şifreleme sınıfı
 *
 * Dosyaları şifrelemeye ve şifrelenmiş verileri çözmeye yardımcı olur<br/>
 * Bunun için bir AESkey parametresiyle tanımlamak yeterlidir. Eğer parametre
 * değerini<br/>
 * <font color = "orange">null</font> olarak belirtirseniz, varsayılan anahtar
 * <font color = "red">123456789abcdefg</font> olarak belirlenecektir
 */
public class Cryptor {

    private AESkey key;
    private AESkey dfault;
    private byte[] buffer = new byte[2048];
    private int length;
    private Cipher encryptCipher;

    public Cryptor(AESkey key) throws Exception {
        try {
            this.encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            this.dfault = new AESkey("123456789abcdefg");
            this.key = (key != null) ? key : this.dfault; // null olarak belirlenirse, default anahtar kullanılır
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cryptor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * <font color = "red">input:</font> Kaynak dosya yani şifrelenecek dosyanın tam yolu(ör:
     * C:\Video\a.mp4)<br/>
     * <font color = "red">output:</font> Hedef dosya, şifreleme sonucu oluşacak dosyanın tam
     * yolu
     *
     */
    public boolean encryptFile(String input, String output) {
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, this.key.getSecret(), this.key.getIv());

            File inFile = new File(input);
            File outFile = new File(output);

            OutputStream os = new FileOutputStream(outFile);
            InputStream is = new FileInputStream(inFile);

            CipherOutputStream cos = new CipherOutputStream(os, this.encryptCipher);

            while ((length = is.read(this.buffer)) > 0) {
                cos.write(buffer, 0, length);
            }

            is.close();
            cos.flush();
            cos.close();

            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

            return false;
        } catch (IOException ex) {
            ex.printStackTrace();

            return false;
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();

            return false;
        } catch (InvalidAlgorithmParameterException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    /**
     * <font color = "red">input:</font> Kaynak dosya, şifresi çözülecek dosyanın tam yolu(ör:
     * C:\Video\a.mp4)<br/>
     * <font color = "red">output:</font> Hedef dosya, çözülme sonucu oluşacak dosyanın tam
     * yolu
     *
     */
    public boolean decryptFile(String input, String output) {
        try {
            encryptCipher.init(Cipher.DECRYPT_MODE, this.key.getSecret(), this.key.getIv());

            File inFile = new File(input);
            File outFile = new File(output);

            InputStream is = new FileInputStream(inFile);
            OutputStream os = new FileOutputStream(outFile);
            CipherInputStream cis = new CipherInputStream(is, this.encryptCipher);

            while ((length = cis.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            cis.close();
            os.flush();
            os.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
