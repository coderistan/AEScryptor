package org.coderistan;
public interface AesListener {
    public void onStart();
    public void onWrite(int rate);
    public void onFinish(long endTime);
}
