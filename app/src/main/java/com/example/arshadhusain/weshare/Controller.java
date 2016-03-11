package com.example.arshadhusain.weshare;

import android.content.Context;

import java.security.KeyStore;

/**
 * Created by hasan on 2016-03-10.
 */
public interface Controller {
    int save(Context context);
    int load(Context context);
    void initEntryList();
    KeyStore.Entry newEntry();
    boolean add(KeyStore.Entry entry, int index);
    boolean add(KeyStore.Entry entry);
    KeyStore.Entry remove(int index);
    KeyStore.Entry getAtIndex(int index);
    boolean hasEntry(KeyStore.Entry entry);
    //boolean validate(EditText text1, EditText text2, EditText text3, EditText text4, EditText text5, EditText text6);
}
