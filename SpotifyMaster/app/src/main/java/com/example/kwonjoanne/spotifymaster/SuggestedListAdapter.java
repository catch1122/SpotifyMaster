package com.example.kwonjoanne.spotifymaster;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kwonjoanne on 3/6/16.
 */
public class SuggestedListAdapter extends ArrayAdapter<Concert> {
    private AppCompatActivity suggestedListActivity;
    private List<Concert> suggestedList;

    public SuggestedListAdapter (AppCompatActivity context, int resource, List<Concert> objects) {
        super(context, resource, objects);
        this.suggestedListActivity = context;
        this.suggestedList = objects;
    }

    @Override
    public Concert getItem(int position) {
        return suggestedList.get(position);
    }
    private static class ViewHolder {
        private TextView title;
        private TextView artist;
        private TextView location;
//        private TextView date;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.suggestedTitleText);
            artist = (TextView) view.findViewById(R.id.suggestedArtistText);
            location = (TextView) view.findViewById(R.id.suggestedLocationText);
//            date = (TextView) view.findViewById(R.id.suggestedTitleText);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder cHolder;
        LayoutInflater cInflator = (LayoutInflater) suggestedListActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = cInflator.inflate(R.layout.concert_listview_item, parent, false);
            cHolder = new ViewHolder(convertView);
            convertView.setTag(cHolder);
        }
        else {
            cHolder = (ViewHolder) convertView.getTag();
            //holder.ratingBar.getTag(position);
        }

//        cHolder.ratingBar.setTag(position);
        cHolder.title.setText(getItem(position).getTitle());
        cHolder.artist.setText(getItem(position).getArtist());
        cHolder.location.setText(getItem(position).getLocation());

        return convertView;
    }

}
