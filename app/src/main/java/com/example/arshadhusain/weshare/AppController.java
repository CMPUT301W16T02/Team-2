package com.example.arshadhusain.weshare;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.util.ArrayList;

/**
 * Created by hasan on 2016-03-10.
 */
public class AppController implements Controller {
    EntryList app;
    Gson gson;

    private static final String FILENAME = "file.sav";

    public boolean init() {
        app = WeShareApp.getApp();
        return app.getEntryList().size() >= 0;
    }

    public AppController() {
        this.init();
    }

    @Override
    public void initEntryList() {
        app.initEntryList();
    }

    @Override
    public KeyStore.Entry newEntry() {
        return null;
    }

    @Override
    public boolean add(KeyStore.Entry entry) {
        return app.add(entry);
    }

    @Override
    public boolean add(KeyStore.Entry entry, int index) {
        return app.editEntry(index, entry);
    }

    @Override
    public boolean hasEntry(KeyStore.Entry entry) {
        return app.hasEntry(entry);
    }

    @Override
    public int save(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(app.getEntryList(), out);
            out.flush();
            fos.close();
            return 1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public KeyStore.Entry getAtIndex(int index) {
        return app.getEntry(index);
    }

    @Override
    public int load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            gson = new Gson();

			/* taken from:
			 * https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html */
            Type listType = new TypeToken<ArrayList<KeyStore.Entry>>(){}.getType();
            ArrayList<KeyStore.Entry> tmp;
            tmp = gson.fromJson(in, listType);
            app.setEntryList(tmp);
            return 1;

        } catch (FileNotFoundException e) {
            this.initEntryList();
        }
        return 0;
    }

    /*
    @Override
    public boolean validate(EditText text1, EditText text2, EditText text3, EditText text4, EditText text5, EditText text6) {
        ArrayList<EditText> textBoxes = new ArrayList<EditText>();
        textBoxes.add(text1);
        textBoxes.add(text2);
        textBoxes.add(text3);
        textBoxes.add(text4);
        textBoxes.add(text5);
        textBoxes.add(text6);

        boolean valid = true;

        for (EditText text: textBoxes) {
            int len = text.getText().length();
            if (len == 0) {
                text.setError("Invalid");
                valid = false;
            }
        }
        textBoxes.clear();
        return valid;
    }*/

    public KeyStore.Entry remove(int position) {
        return app.remove(position);
    }
}
