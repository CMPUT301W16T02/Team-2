package com.example.arshadhusain.weshare;

/**
 *  Search result
 *
 *  @author Arshad
 *  @version  1.0
 */
public class SearchResult {

    private Item item;
    private int origItemPos;

    public SearchResult(Item item, int origItemPos){
        this.item = item;
        this.origItemPos = origItemPos;
    }


    public int getOrigItemPos(){
        return origItemPos;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public String toString(){

        return item.getName() + "\n" + item.statusToString() + "\nOwner: " + item.getOwner();
    }



}
