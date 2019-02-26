package com.architecturecomponents.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.architecturecomponents.R;
import com.architecturecomponents.data.remote.task.HttpGetRequest;
import com.architecturecomponents.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskActivity extends AppCompatActivity implements HttpGetRequest.Listeners {
    private TextView txtContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        txtContenu = findViewById(R.id.txtContenu);
        setTitle("AsyncTaskActivity");
        new HttpGetRequest(this).execute("http://www.mocky.io/v2/5c6acf9d3300009f3c7f4d4e");
    }


    public void showMovies(List<Movie> movies) {
        for (Movie movie : movies)
            txtContenu.append(movie.getId() + " " + movie.getName() + "\n");
    }

    long startTime = 0;

    @Override
    public void onPreExecute() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onPostExecute(String result) {
        try {
            Log.e("onPostExecute", result.toString());
            JSONArray arr = new JSONArray(result);
            List<Movie> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject object = arr.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(object.getInt("id"));
                movie.setName(object.getString("name"));
                list.add(movie);
            }
            showMovies(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("AsyncTask " + (endTime - startTime));
    }
}
