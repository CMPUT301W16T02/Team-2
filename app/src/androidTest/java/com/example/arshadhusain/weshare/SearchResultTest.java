package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

import io.searchbox.core.SearchResult;

/**
 * Created by arshadhusain on 16-03-14.
 */
public class SearchResultTest extends ActivityInstrumentationTestCase2 {

    public SearchResultTest() {
        super(SearchResult.class);
    }

    public void testAddResult(){
        Item item = new Item("Picnic Table", "Mint Condition", "ArshadHusain");
        //SearchResult result = new SearchResult(item, 0);


        //assertTrue(result.getItem() == item);
        //assertTrue(result.getOrigItemPos() == 0);
    }




}
