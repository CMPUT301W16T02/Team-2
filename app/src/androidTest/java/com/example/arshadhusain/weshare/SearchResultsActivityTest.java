package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by arshadhusain on 16-03-14.
 */
public class SearchResultsActivityTest extends ActivityInstrumentationTestCase2 {

    public SearchResultsActivityTest() {
        super(SearchResultsActivityTest.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityExists() {
        SearchResultsActivity activity = (SearchResultsActivity) getActivity();
        assertNotNull(activity);
    }

    public void testSearchResultsActivity() throws Exception {
        //test

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
