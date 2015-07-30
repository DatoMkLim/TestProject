package com.epsilon.rfcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class TestActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CardView cardView;

    private ArrayList<FeddProperties> os_versions;

    private RecyclerView.Adapter mAdapter;
    // private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        initContrls();
    }

    private void initContrls() {

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  cardView = (CardView) findViewById(R.id.cardList);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //if (toolbar != null) {
        //  setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Android Versions");

        //}

        final String[] versions = {"Alpha", "Beta"};
        //final int[] icons = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.donut, R.drawable.eclair, R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.icecream_sandwhich, R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop};


        os_versions = new ArrayList<FeddProperties>();

        for (int i = 0; i < versions.length; i++) {
            FeddProperties feed = new FeddProperties();

            feed.setTitle(versions[i]);
            //feed.setThumbnail(icons[i]);
            os_versions.add(feed);
        }

        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Grid View
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(os_versions);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);


    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
