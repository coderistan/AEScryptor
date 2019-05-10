/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coderistan;

import java.io.File;
import java.util.Queue;

class Worker extends Thread {

    private boolean flag;
    private Queue<String> works;
    private String sourceFile;
    private String destFile;
    private String extend;
    private Cryptor chiper;
    private int chipMode;
    private int completed;

    public Worker(Queue works, String destFile, String extend, AESkey key, int chipMode) throws Exception {
        this.flag = true;
        this.works = works;
        this.destFile = destFile;
        this.extend = extend.replace(".", "");
        this.chiper = new Cryptor(key);
        this.chipMode = chipMode;

        this.chiper.addListener(new AesListener() {
            @Override
            public void onStart() {}

            @Override
            public void onWrite(int rate) {
                setCompleted(rate);
            }

            @Override
            public void onFinish(long endTime) {}
        });
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public int getCompleted() {
        return completed;
    }
    
    private void setCompleted(int value){
        this.completed = value;
    }

    @Override
    public void run() {
        while (flag) {
            synchronized (works) {
                if (works.isEmpty()) {
                    break;
                }
                sourceFile = works.remove();
            }

            File s = new File(sourceFile);

            switch (chipMode) {
                case 0:
                    // encode mode
                    this.chiper.encryptFile(sourceFile, destFile + File.separator + s.getName() + "." + extend);
                    break;
                case 1:
                    // decode mode
                    this.chiper.decryptFile(sourceFile, destFile + File.separator + s.getName().replace("." + extend, ""));
                    break;
            }

        }
        flag = false;
    }

}
