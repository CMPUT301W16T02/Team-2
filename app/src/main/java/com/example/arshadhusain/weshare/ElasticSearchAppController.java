package com.example.arshadhusain.weshare;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by arshadhusain on 16-03-23.
 */
public class ElasticSearchAppController {
    private static JestDroidClient client;
    public static class GetAccountTask extends AsyncTask<String,Void,ArrayList<Account>> {

        public List<Account> foundUsername;

        @Override
        protected ArrayList<Account> doInBackground(String... params) {
            verifyConfig();

            // Hold (eventually) the tweets that we get back from Elasticsearch
            ArrayList<Account> accounts = new ArrayList<Account>();

            String search_string;

            search_string = "{\"query\":{\"match\":{ \"username\" : \"" +params[0]+ "\"}}}";


            Search search = new Search.Builder(search_string).addIndex("team2").addType("users").build();

            System.out.println(search.toString());
            try {
                SearchResult execute = client.execute(search);
                System.out.println(execute.getResponseCode());
                if(execute.isSucceeded()) {

                    foundUsername = execute.getSourceAsObjectList(Account.class);
                    if(foundUsername.isEmpty())
                    {
                        System.out.println("no user found");

                    }

                    accounts.addAll(foundUsername);
                    System.out.println("Username found");
                } else {
                    Log.i("TODO", "Search was unsuccessful, do something!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return accounts;
        }
    }

    public static class AddAccountTask extends AsyncTask<Account,Void,Void> {

        @Override
        protected Void doInBackground(Account... params) {
            verifyConfig();

            for(Account account : params) {
                Index index = new Index.Builder(account).index("team2").type("users").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()) {
                        account.setId(execute.getId());
                    } else {
                        Log.e("TODO", "Account add failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public static class EditAccountTask extends AsyncTask<Account,Void,Void> {

        @Override
        protected Void doInBackground(Account... params) {
            verifyConfig();

            for(Account account : params) {
                Index index = new Index.Builder(account).index("team2").type("users").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()) {
                        System.out.println("Update successful");
                    } else {
                        Log.e("TODO", "Account Edit failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }


    public static class GetMyItemsTask extends AsyncTask<String,Void,ArrayList<Item>> {

        public List<Item> foundItems;

        @Override
        protected ArrayList<Item> doInBackground(String... params) {
            verifyConfig();

            // Hold (eventually) the tweets that we get back from Elasticsearch
            ArrayList<Item> items = new ArrayList<Item>();

            String search_string;
            if(params[0] == "") {
                search_string = "{\"query\":{\"match_all\":{}}}";
            } else {
                search_string = "{\"query\":{\"match\":{ \"owner\" : \"" +params[0]+ "\"}}}";

            }




            Search search = new Search.Builder(search_string).addIndex("team2").addType("items").build();

            System.out.println(search.toString());
            try {
                SearchResult execute = client.execute(search);
                System.out.println(execute.getResponseCode());
                if(execute.isSucceeded()) {

                    foundItems = execute.getSourceAsObjectList(Item.class);
                    if(foundItems.isEmpty())
                    {
                        System.out.println("no user found");

                    }

                    items.addAll(foundItems);
                    System.out.println("Username found");
                } else {
                    Log.i("TODO", "Search was unsuccessful, do something!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return items;
        }
    }





    public static class AddItemTask extends AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... params) {
            verifyConfig();

            for(Item item : params) {
                Index index = new Index.Builder(item).index("team2").type("items").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()) {
                        item.setId(execute.getId());
                    } else {
                        Log.e("TODO", "Account add failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public static class EditItemTask extends AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... params) {
            verifyConfig();

            for(Item item : params) {
                Index index = new Index.Builder(item).index("team2").type("items").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()) {
                        System.out.println("Update successful");
                    } else {
                        Log.e("TODO", "Item Edit failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }


    public static class DeleteItemTask extends AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... params) {
            verifyConfig();

            for(Item item : params) {
                //Index index = new Index.Builder(item).index("team2").type("items").build();

                Delete delete = new Delete.Builder(item.getId()).index("team2").type("items").build();
                try {
                    DocumentResult execute = client.execute(delete);
                    if(execute.isSucceeded()) {
                        //item.setId(execute.getId());
                        System.out.println("Item deleted");
                    } else {
                        Log.e("TODO", "Account add failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public static class AddBidTask extends AsyncTask<Bid,Void,Void> {

        @Override
        protected Void doInBackground(Bid... params) {
            verifyConfig();

            for(Bid bid : params) {
                Index index = new Index.Builder(bid).index("team2").type("bids").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()) {
                        bid.setId(execute.getId());
                        System.out.println(bid.getId());
                        System.out.println("****SET ID FOR BID****");
                    } else {
                        Log.e("TODO", "Bid add failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public static class DeleteBidTask extends AsyncTask<Bid,Void,Void> {

        @Override
        protected Void doInBackground(Bid... params) {
            verifyConfig();

            for(Bid bid : params) {
                //Index index = new Index.Builder(item).index("team2").type("items").build();

                Delete delete = new Delete.Builder(bid.getId()).index("team2").type("bids").build();
                try {
                    DocumentResult execute = client.execute(delete);
                    if(execute.isSucceeded()) {
                        //item.setId(execute.getId());
                        System.out.println("Bid deleted");
                    } else {
                        Log.e("TODO", "Bid delete failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public static class GetBidsTask extends AsyncTask<Bid,Void,ArrayList<Bid>> {

        public List<Bid> foundBids;

        @Override
        protected ArrayList<Bid> doInBackground(Bid... params) {
            verifyConfig();

            // Hold (eventually) the tweets that we get back from Elasticsearch
            ArrayList<Bid> bids = new ArrayList<Bid>();

            String search_string;
            //if(params[0] == "") {
            search_string = "{\"query\":{\"match_all\":{}}}";
            //} else {
                //search_string = "{\"query\":{\"match\":{ \"owner\" : \"" +params[0]+ "\"}}}";

            //}




            Search search = new Search.Builder(search_string).addIndex("team2").addType("bids").build();

            System.out.println(search.toString());
            try {
                SearchResult execute = client.execute(search);
                System.out.println(execute.getResponseCode());
                if(execute.isSucceeded()) {

                    foundBids = execute.getSourceAsObjectList(Bid.class);
                    if(foundBids.isEmpty())
                    {
                        System.out.println("no user found");

                    }

                    bids.addAll(foundBids);
                    System.out.println("Username found");
                } else {
                    Log.i("TODO", "Search was unsuccessful, do something!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bids;
        }
    }

    public static void verifyConfig() {
        if(client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


}
