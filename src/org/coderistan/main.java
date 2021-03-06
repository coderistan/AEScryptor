package org.coderistan;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) throws Exception {
        if(args.length >= 4){
            String anahtar = (args[0].equalsIgnoreCase("null"))?AESkey.random:args[0];
            Integer mode = Integer.valueOf(args[1]);
            String kaynak = args[2];
            String hedef = args[3];
            
            AESkey key = new AESkey(anahtar);
            Cryptor c = new Cryptor(null,key);
            
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
            
            switch(mode){
                case 0: c.encryptFile(kaynak,hedef);break;
                case 1: c.decryptFile(kaynak,hedef);break;
                default:System.out.println("Bilinmeyen mod: "+mode);
            }
            
            
        }else{
            new UserInterface().setVisible(true);
        }

    }
}
