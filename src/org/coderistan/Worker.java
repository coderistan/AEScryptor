/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coderistan;

import java.io.File;
import java.util.Queue;

class Worker implements Runnable {

    private boolean flag;
    private Queue<String> work;
    private String sourceFile;
    private String destFile;
    private String extend;
    private Cryptor chiper;
    private int chipMode;
    private int threadNo;

    public Worker(Queue work, String destFile, String extend, AESkey key, int chipMode, int threadNo) throws Exception {
        this.flag = true;
        this.work = work;
        this.destFile = destFile;
        this.extend = extend.replace(".", "");
        this.chiper = new Cryptor(key);
        this.chipMode = chipMode;
        this.threadNo = threadNo;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    @Override
    public void run() {
        while (flag) {
            synchronized (work) {
                if (work.isEmpty()) {
                    break;
                }
                sourceFile = work.remove();
            }

            File s = new File(sourceFile);

            switch (chipMode) {
                case 0:
                    // encode mode
                    this.chiper.encryptFile(sourceFile, destFile + File.separator + s.getName() + "." + extend);
                    break;
                case 1:
                    // decode mode
                    this.chiper.decryptFile(sourceFile, destFile + File.separator + s.getName().replace("."+extend,""));
                    break;
            }

        }
        flag = false;
    }

}
