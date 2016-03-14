package com.example.arshadhusain.weshare;

/**
 * Created by Chris on 3/14/2016.
 */
public class SearchResult {

    private Item item;
    private int origItemPos;

    public SearchResult(Item item, int origItemPos){
        this.item = item;
        this.origItemPos = origItemPos;
    }

    @Override
    public String toString(){

        return item.getName() + "\n" + item.statusToString() + "\nOwner: " + item.getOwner();
    }

    public int getOrigItemPos(){
        return origItemPos;
    }

}
