package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *  Activity shows a list of items that belong to the active user.
 *
 *  Started from NavigationMainActivity
 *  Can start EditItemActivity
 *
 *  @author Badran
 *  @version  1.0
 */
public class MyItemsActivity extends AppCompatActivity {

    public static ArrayList<Item> myItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String MyUsername;

    private ListView myItemsList;

    private static int selectedItemPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);


        Intent intent = getIntent();


        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        myItemsList = (ListView) findViewById(R.id.MyItemList);

        myItemsList.setOnItemClickListener(onItemClickListener);

        myItems.clear();
        /*for (int x=0; x<NavigationMainActivity.allItems.size(); x++) {

            System.out.printf("allItems: %s MyUsername %s\n", NavigationMainActivity.allItems.get(x).getOwner(), MyUsername);

            if((NavigationMainActivity.allItems.get(x).getOwner()).equals(MyUsername))
            {

                Item ItemToCopy = NavigationMainActivity.allItems.get(x);
                System.out.printf("allItems: %s MyUsername %s\n", NavigationMainActivity.allItems.get(x).getOwner(), MyUsername);
                myItems.add(ItemToCopy);
            }

        }*/
        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute(MyUsername);

        try {
            myItems.addAll(getMyItemsTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");

        System.out.printf("%d\n", myItems.size());

        for (int x=0; x<myItems.size(); x++) {
            System.out.println(myItems.get(x).getName());


        }

        adapter = new ArrayAdapter<Item>(this,
                R.layout.list_item, myItems);
        myItemsList.setAdapter(adapter);
    }

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItemPos = position;
            editItem();
        }
    };

    /**
     * Method starts the EditItemActivity to edit an item
     */
    public void editItem() {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("itemPos", selectedItemPos);
        intent.putExtra("activeUser", MyUsername);

        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK);
            //NavigationMainActivity.saveInFile();
        }
    }

}
