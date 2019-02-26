package com.architecturecomponents.data.db.v2.repositories;

import android.content.Context;
import android.util.Log;

import com.architecturecomponents.data.db.v2.AppDatabase;
import com.architecturecomponents.data.db.v2.dao.MovieDao;
import com.architecturecomponents.model.Movie;

import java.util.List;


public class MovieRepository {
    private MovieDao movieDao;

    public MovieRepository(final Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        movieDao = db.movieDao();
    }

    public Boolean insert(Movie movie) {
        try {

            long rslt = movieDao.insert(movie);
            Log.e("movieDao", "" + rslt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(Movie movie) {
        try {
            movieDao.update(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        movieDao.deleteAllMovies();
    }

    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
