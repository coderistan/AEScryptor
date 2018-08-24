# AEScryptor
Dosyaları AES ile şifrelemek ve AES ile şifrelenmiş dosyaları çözmek için yazılmış Java kütüphanesi

# Kullanım

[AEScryptor](https://github.com/coderistan/AEScryptor/dist/AEScryptor.jar) jar dosyasını projenize dahil ederek kullanabilirsiniz. Ya da isterseniz konsol üzerinde de çalıştırabilirsiniz. Bunun için;

```
java -jar AEScryptor.jar ANAHTAR MODE KAYNAK HEDEF
```

yazmanız yeterlidir. Anahtar uzunluğu 16,24 veya 32 olmalıdır. Eğer null olarak belirtilirse "123456789abcdefg" kullanılacaktır. Tabi ki isterseniz değiştirebilirsiniz. Random olarak belirlenmiştir. 

Kaynak dosyasının tam adı verilmelidir.

```
D:\\Video\\video.mp4
```

şeklinde yazılmalıdır. Hedef dosyası, şifrelendikten sonra kaydedilecek dosyanın adıdır. Eğer kaydettiğiniz yerde aynı isimde başka dosya varsa ÜZERİNE yazacaktır. O yüzden dikkat ediniz. 

Mod olarak 0 şifrelemeyi, 1 ise çözümlemeyi ifade eder. Örnek bir şifreleme ve çözümleme

Şifreleme:
```
java -jar AEScryptor null 0 "D:\\Video\\video.mp4" "D:\\sifreli.mp4"
```

Çözümleme
```
java -jar AEScryptor null 1 "D:\\sifreli.mp4" "D:\\cozulmus.mp4"
```

Şifrelerken kullandığınız anahtarı, çözümlerken de kullanmak zorundasınız. Aksi takdirde çözülmeyecektir. Kendi belirlediğiniz şifreyi unutmayın :)

# Program içerisine dahil etme

Eğer projenize dahil edip kullanmak isterseniz, şu şekilde kullanabilirsiniz

```
import org.coderistan.Cryptor;
import org.coderistan.AESkey;

public class Coderistan{
    public static void main(String args[]){
        AESkey key = new AESkey("ANAHTARINIZ");
        Cryptor c = new Cryptor(key);
        String kaynak = "kaynak";
        String hedef = "hedef";
        
        c.encryptFile(kaynak,hedef); // Şifreleme işlemi
        c.decryptFile(kaynak,hedef); // Çözümleme işlemi
    }
}
```

Kütüphane normal boyutlu dosyalarda gayet düzgün çalışıyor. Ancak çok yüksek boyutlu dosyalarda kasma olabilir. Faydalı olması dileğiyle :)
