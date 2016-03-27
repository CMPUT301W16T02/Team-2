//package com.example.arshadhusain.weshare;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.searchly.jestdroid.DroidClientConfig;
//import com.searchly.jestdroid.JestClientFactory;
//import com.searchly.jestdroid.JestDroidClient;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import io.searchbox.core.Index;
//import io.searchbox.core.DocumentResult;
//
//
///**
// * Created by hasan on 2016-03-24.
// */
//public class ElasticsearchAppController {
//
//    private static JestDroidClient client;
//    private static String url = "http://cmput301.softwareprocess.es:8080/team2";
//
//
//    public JestDroidClient getClient() {
//        return this.client;
//    }
//
//    public static class GetAccountTask extends AsyncTask<String, Void, ArrayList<Account>> {
//        @Override
//        protected ArrayList<Account> doInBackground(String... search_strings) {
//            verifyClient();
//
//            // Start our initial array list (empty)
//            ArrayList<Account> accounts = new ArrayList<Account>();
//
//            // NOTE: I'm making a huge assumption here, that only the first search term
//            // will be used.
//            Search search = new Search.Builder(search_strings[0])
//                    .addIndex("testing")
//                    .addType("tweet")
//                    .build();
//
//            try {
//                SearchResult execute = client.execute(search);
//                if (execute.isSucceeded()) {
//                    // Return out list of tweets
//                    List<Account> returned_tweets = execute.getSourceAsObjectList(Account.class);
//                    accounts.addAll(returned_accounts);
//                } else {
//                    // TODO: Add an error message, because that other thing was puzzling.
//                    // TODO: Right here it will trigger if the search fails.
//                    Log.i("TODO", "We actually failed here, searching for tweets");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    //TODO: A function that adds a tweet
//    public static class AddAccountTask extends AsyncTask<Account, Void, Void> {
//        @Override
//        protected Void doInBackground(NormalTweet... tweets) {
//            verifyClient();
//
//            // Since AsyncTasks work on arrays, we need to work with arrays as well (>= 1 tweet)
//            for (int i = 0; i < tweets.length; i++) {
//                NormalTweet tweet = tweets[i];
//
//                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();
//                try {
//                    DocumentResult result = client.execute(index);
//                    if (result.isSucceeded()) {
//                        // Set the ID to tweet that elasticsearch told me it was
//                        tweet.setId(result.getId());
//                    } else {
//                        // TODO: Add an error message, because this was puzzling.
//                        // TODO: Right here it will trigger if the insert fails.
//                        Log.i("TODO", "We actually failed here, adding a tweet");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//    }
//
//
//    public static void verifyClient() {
//        // 1. Verify that 'client' exists.
//        if (client == null) {
//            // 2. If it doesn't,make it.
//            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(url);
//            DroidClientConfig config = builder.build();
//
//            JestClientFactory factory = new JestClientFactory();
//            factory.setDroidClientConfig(config);
//            client = (JestDroidClient) factory.getObject();
//        }
//    }
//}
