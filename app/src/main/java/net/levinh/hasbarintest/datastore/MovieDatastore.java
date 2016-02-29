package net.levinh.hasbarintest.datastore;

import net.levinh.hasbarintest.datastore.Model.Movie;

import java.util.List;

/**
 * Created by Levin on 29/02/2016.
 */
public interface MovieDatastore {
    interface MovieListRetrieverListener {
        void onMovieListRetrieved(List<Movie> movieList);
    }
    void loadMovies(MovieListRetrieverListener onMovieListRetrieverListener);
}
