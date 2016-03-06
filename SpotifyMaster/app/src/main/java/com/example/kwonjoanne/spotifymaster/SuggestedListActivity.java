package com.example.kwonjoanne.spotifymaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SuggestedListActivity extends AppCompatActivity {

    private ListView suggestedView;
    private ArrayAdapter<Concert> suggestedAdapter;
    private ArrayList<Concert> suggestedConcerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        suggestedView = (ListView) findViewById(R.id.suggestedView);

        suggestedConcerts = tmClientManager.getSuggested(); // static method in clientmanager with ticketmaster api

        suggestedAdapter = new ArrayAdapter<Concert>(this, R.layout.concert_listview_item, suggestedConcerts);
        suggestedView.setAdapter(suggestedAdapter);

        suggestedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ConcertDetailsActivity.class);
                //PASSBUNDLE
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
