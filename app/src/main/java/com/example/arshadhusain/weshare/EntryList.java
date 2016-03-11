package com.example.arshadhusain.weshare;

import java.security.KeyStore;
import java.util.ArrayList;

/**
 * Created by hasan on 2016-03-10.
 */
public class EntryList {
    private ArrayList<KeyStore.Entry> entrylist;

    public EntryList() {
        this.entrylist = new ArrayList<KeyStore.Entry>();
    }

    public ArrayList<KeyStore.Entry> getEntryList() {
        return entrylist;
    }

    public KeyStore.Entry getEntry(int index){
        return entrylist.get(index);
    }

    public void setEntryList(ArrayList<KeyStore.Entry>entrylist){
        this.entrylist = entrylist;
    }

    public void initEntryList () {
        this.entrylist = new ArrayList<KeyStore.Entry>();
    }

    public boolean hasEntry(KeyStore.Entry entry){
        return entrylist.contains(entry);
    }


    public boolean editEntry(int index, KeyStore.Entry entry) {
        if (entrylist.size() == 0) {
            entrylist.add(0, entry);
            return hasEntry(entry);
        }
        else{
            entrylist.remove(index);
            entrylist.add(index, entry);
            return hasEntry(entry);
        }
    }

    public boolean add(KeyStore.Entry entry) {
        return this.entrylist.add(entry);
    }

    public KeyStore.Entry remove(int position) {
        return this.entrylist.remove(position);
    }
}
