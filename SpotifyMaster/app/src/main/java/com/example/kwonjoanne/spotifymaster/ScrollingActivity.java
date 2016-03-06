package com.example.kwonjoanne.spotifymaster;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.Set;
import java.util.Vector;


public class ScrollingActivity extends AppCompatActivity {

    //http://developer.echonest.com/api/v4/genre/artists?api_key=FILDTEOIK2HBORODV&format=json&results=5&start=0&bucket=hotttnesss&name=jazz
    public String buildQueryString(String category /*like using genre, etc*/, String subCategory, int numResults)
    {

        return "http://developer.echonest.com/api/v4/"
                + category
                + "/artists"
                + "?api_key=" //input the api get here at some point
                + "&format=json&results=" + numResults
                + "start=0"
                + "&bucket=hotttnesss"
                + "&name="
                + subCategory;
    }

    public JSONObject query(String query) throws Exception //not sure if correct return yet
    {
        //user library querying function
        //put them into results.

        StringBuilder sb = new StringBuilder();
        URL url = new URL(query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line = rd.readLine()) != null)
        {
            sb.append(line);
        }
        rd.close();

        JSONObject res = new JSONObject(sb.toString());

        return new JSONObject(sb.toString());

    }

    public Vector<String> getArtistsFromUserAndUserFriends() {
        /*enter the query to be made*/
        String querytext = "theQuerystring";
        //using our query to grab a JSONOBJECT
        JSONObject queryResult;
        try
        {
            queryResult = query(querytext);
        }
        catch (Exception e)
        {
            System.out.println("lol nope");
        }
        return new Vector<String>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
