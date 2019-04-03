# AEScryptor
Dosyaları AES ile şifrelemek ve AES ile şifrelenmiş dosyaları çözmek için yazılmış Java kütüphanesi

# Kullanım

[AEScryptor](https://github.com/coderistan/AEScryptor/blob/master/dist/AEScryptor.jar) jar dosyasını projenize dahil ederek kullanabilirsiniz. Ya da isterseniz konsol üzerinde de çalıştırabilirsiniz. Bunun için;

```
java -jar AEScryptor.jar ANAHTAR MODE KAYNAK HEDEF
```

yazmanız yeterlidir. Anahtar uzunluğu rastgele seçilebilir. Güvenlik için harf,rakam,noktalama işaretleri,büyük ve küçük karakterlerden oluşan bir şifre oluşturmanız tavsiye edilir. Eğer null olarak belirtilirse "123456789abcdefg" kullanılacaktır. Tabi ki isterseniz değiştirebilirsiniz. Random olarak belirlenmiştir. 

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

```java
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

# AesListener

Şifreleme veya çözümleme işlemi sürerken, işlemin yüzde kaçının tamamlandığını görebiliriz. Bunun için AesListener sınıfını implement etmek yeterlidir. Aynı zamanda başladığında ve işlem bittiğinde de gerekli metotlar yardımı ile haberdar edilirsiniz.

```java
import org.coderistan.Cryptor;
import org.coderistan.AESkey;
import org.coderistan.AesListener;

public class Coderistan{
    public static void main(String args[]){
        AESkey key = new AESkey("ANAHTARINIZ");
        Cryptor c = new Cryptor(key);
        
        c.addListener(new AesListener() {
            @Override
            public void onStart() {
                System.out.println("İşlem başladı");
            }

            @Override
            public void onWrite(int rate) {
                System.out.print("\r%"+rate+" tamamlandı");
            }

            @Override
            public void onFinish(long endTime) {
                System.out.println("\n"+(endTime/1000.0)+" saniyede işlem tamamlandı");
            }
        });
        
        String kaynak = "kaynak";
        String hedef = "hedef";
        
        c.encryptFile(kaynak,hedef); // Şifreleme işlemi
        c.decryptFile(kaynak,hedef); // Çözümleme işlemi
    }
}

```
![How To Work](https://raw.githubusercontent.com/coderistan/AEScryptor/master/how_to_work.gif)

Bu şekilde terminal ekranından çalıştırmak istemiyorsanız, jar dosyasını parametresiz veya çift tıklayarak çalıştırabilirsiniz. Karşısına AESCryptor GUI ekranı gelecektir. Şimdilik beta durumunda, hatalar olabilir. Bildirirseniz sevinirim.

![How To Work](https://raw.githubusercontent.com/coderistan/AEScryptor/master/gui.png)

Kütüphane normal boyutlu dosyalarda(1 GB altında) gayet düzgün çalışıyor. Ancak çok yüksek boyutlu dosyalarda kasma olabilir. Şifrelediğiniz veya çözümlediğiniz dosyayı başka bir isimle kaydetmeniz tavsiye olunur...

Kullanımdan oluşacak aksi durumlardan sorumlu değilim, zira güvenlik önlemleri kullanıcının kendisine aittir. Faydalı olması dileğiyle :)
