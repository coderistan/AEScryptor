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
    private byte[] buffer = new byte[8 * 1024];
    private int length;
    private Cipher encryptCipher;
    private AesListener listener = null;
    private Worker parent;

    public Cryptor(Worker parent, AESkey key) throws Exception {
        try {
            this.encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.parent = (parent == null) ? new Worker(null, null, null, null, -1) : parent;
            this.dfault = new AESkey("123456789abcdefg");
            this.key = (key != null) ? key : this.dfault; // null olarak belirlenirse, default anahtar kullanılır
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cryptor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * <font color = "red">input:</font> Kaynak dosya yani şifrelenecek dosyanın
     * tam yolu(ör: C:\Video\a.mp4)<br/>
     * <font color = "red">output:</font> Hedef dosya, şifreleme sonucu oluşacak
     * dosyanın tam yolu
     *
     */
    public boolean encryptFile(String input, String output) {
        try {
            long startTime = System.currentTimeMillis();
            this.writeListenerStart();

            encryptCipher.init(Cipher.ENCRYPT_MODE, this.key.getSecret(), this.key.getIv());

            File inFile = new File(input);
            File outFile = new File(output);

            OutputStream os = new FileOutputStream(outFile);
            InputStream is = new FileInputStream(inFile);

            float temp = 0;

            CipherOutputStream cos = new CipherOutputStream(os, this.encryptCipher);

            while ((length = is.read(this.buffer)) > 0) {
                if (this.parent.getRunnable()) {
                    cos.write(buffer, 0, length);
                    temp += length;
                    int rate = (int) ((100 * temp) / inFile.length());
                    this.writeListenerRate(rate);
                } else {
                    Thread.sleep(100);
                }
            }

            is.close();
            cos.flush();
            cos.close();

            long endTime = System.currentTimeMillis() - startTime;
            this.writeListenerFinish(endTime);

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
        } catch (InterruptedException ex) {
            Logger.getLogger(Cryptor.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    /**
     * <font color = "red">input:</font> Kaynak dosya, şifresi çözülecek
     * dosyanın tam yolu(ör: C:\Video\a.mp4)<br/>
     * <font color = "red">output:</font> Hedef dosya, çözülme sonucu oluşacak
     * dosyanın tam yolu
     *
     */
    public boolean decryptFile(String input, String output) {
        try {
            long startTime = System.currentTimeMillis();
            this.writeListenerStart();
            encryptCipher.init(Cipher.DECRYPT_MODE, this.key.getSecret(), this.key.getIv());

            File inFile = new File(input);
            File outFile = new File(output);

            InputStream is = new FileInputStream(inFile);
            OutputStream os = new FileOutputStream(outFile);
            CipherInputStream cis = new CipherInputStream(is, this.encryptCipher);

            float temp = 0;

            while ((length = cis.read(buffer)) > 0) {
                if (this.parent.getRunnable()) {
                    os.write(buffer, 0, length);
                    temp += length;
                    int rate = (int) ((100 * temp) / inFile.length());
                    this.writeListenerRate(rate);
                }else{
                    System.out.println("[-] Deleting file: "+outFile.getName());
                    
                    outFile.deleteOnExit();
                    break;
                }

            }

            cis.close();
            os.flush();
            os.close();

            long endTime = System.currentTimeMillis() - startTime;
            this.writeListenerFinish(endTime);

            return true;
        } catch (Exception e) {
            System.out.println("Hata");
            e.printStackTrace();
            return false;
        }
    }

    public void addListener(AesListener listener) {
        this.listener = listener;
    }

    private void writeListenerRate(int rate) {
        if (this.listener != null) {
            this.listener.onWrite(rate);
        }
    }

    private void writeListenerStart() {
        if (this.listener != null) {
            this.listener.onStart();
        }
    }

    private void writeListenerFinish(long endTime) {
        if (this.listener != null) {
            this.listener.onFinish(endTime);
        }
    }
}
