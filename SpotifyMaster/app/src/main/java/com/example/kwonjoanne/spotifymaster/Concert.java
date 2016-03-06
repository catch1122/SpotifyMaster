package com.example.kwonjoanne.spotifymaster;

import java.util.Date;

/**
 * Created by kwonjoanne on 3/6/16.
 */
public class Concert {

    private String title;
    private String artist;
    private String location;
    private Date date;

    private String url;

    public Concert(String title_, String artist_, String location_, Date date_) {
        title = title_;
        artist = artist_;
        location = location_;
        date = date_;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() { return url; }

}
