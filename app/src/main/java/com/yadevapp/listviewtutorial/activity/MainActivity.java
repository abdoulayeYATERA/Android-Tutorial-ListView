package com.yadevapp.listviewtutorial.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yadevapp.listviewtutorial.R;
import com.yadevapp.listviewtutorial.adapter.MovieAdapter;
import com.yadevapp.listviewtutorial.element.MoviesJsonFile;

import java.io.IOException;

/**
 * Created by abdoulaye on 7/7/16.
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ListView mListView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.activity_main_listview);
        //parse asset file movies.json to get the movielist
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            //parse the assets file movies.json
            MoviesJsonFile moviesJsonFile = mapper.readValue(getAssets().open("movies.json"), MoviesJsonFile.class);
            //instanciate the adapter
            mMovieAdapter = new MovieAdapter(this, moviesJsonFile.getMovieArray());
            //bind the adapter to the listview
            mListView.setAdapter(mMovieAdapter);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }
    }
}
