package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by arshadhusain on 16-03-14.
 */
public class SearchResultTest extends ActivityInstrumentationTestCase2 {

    public SearchResultTest() {
        super(SearchResult.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAddResult(){
        Item item = new Item("Picnic Table", "Mint Condition", "ArshadHusain");
        SearchResult result = new SearchResult(item, 0);

        assertTrue(result.getItem() == item);
        assertTrue(result.getOrigItemPos() == 0);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
