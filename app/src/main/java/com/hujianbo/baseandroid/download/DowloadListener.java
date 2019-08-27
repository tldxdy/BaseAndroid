package com.hujianbo.baseandroid.download;

public interface DowloadListener {


    public void progress(int progress);

    public void complete(String path);

    public void onfail();
}
