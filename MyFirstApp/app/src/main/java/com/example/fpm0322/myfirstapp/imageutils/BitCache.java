package com.example.fpm0322.myfirstapp.imageutils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> lruCache;

    public BitCache(){
        lruCache = new LruCache<String,Bitmap>((int)(Runtime.getRuntime().maxMemory()/1024/8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
}
