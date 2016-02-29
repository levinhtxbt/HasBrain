package net.levinh.hasbarintest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.levinh.hasbarintest.datastore.AssetBasedMovieDatastore;
import net.levinh.hasbarintest.datastore.Model.Movie;
import net.levinh.hasbarintest.datastore.MovieDatastore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        ListView lsvMovies;
        ListAdapter adapter;
        EditText txtSearch;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            lsvMovies = (ListView)findViewById(R.id.lsvMovies);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            final MovieDatastore movieDatastore = new AssetBasedMovieDatastore(this, "Data.json", gson);
            movieDatastore.loadMovies(new MovieDatastore.MovieListRetrieverListener() {
                @Override
                public void onMovieListRetrieved(List<Movie> movieList) {
                    //TODO: Display your list of movies here.
                    Movie m = movieList.get(0);
                    adapter = new ListAdapter(MainActivity.this, R.layout.movie_item, movieList);
                    lsvMovies.setAdapter(adapter);
                }
            });

            lsvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie m = (Movie) parent.getItemAtPosition(position);
                    if (m.getTagClick())
                        m.setTagClick(false);
                    else m.setTagClick(true);
                    adapter.notifyDataSetChanged();
                }
            });

        }
    }


